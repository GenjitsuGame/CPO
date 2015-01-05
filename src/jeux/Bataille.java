/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux;

import evenements.EvenementJeu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.RingBuffer;

/**
 *
 * @author Adrien
 */
public class Bataille extends AbstractSynchronizedJeu<Integer> {

    protected final Object verrou;

    protected final List<Carte> deck;
    protected final List<Joueur> joueurs;
    protected int nbCartesPourGagner;

    protected int gagnantPartie = -1;
    protected int gagnantTour = -1;

    protected final TreeMap<Integer, Carte> cartesJoueesParJoueur;
    protected final List<Carte> cartesJoueesTriees;

    private final RingBuffer<Integer> evenements;

    public Bataille() {
        this.verrou = new Object();
        this.joueurs = new ArrayList<>();
        this.deck = new LinkedList<>();
        this.evenements = new RingBuffer<>();
        this.cartesJoueesParJoueur = new TreeMap<>();
        this.cartesJoueesTriees = new ArrayList<>();
        this.init();
    }

    private void init() {
        for (int i = 0;
                i < Integer.parseInt(getOption("nbJoueurs"));
                ++i) {
            try {
                this.joueurs.add((Joueur) Class.forName("joueurs." + getOption("typeJoueur")).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Bataille.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.deck.addAll(chargerDeck(getOption("deck")));
        this.nbCartesPourGagner = this.deck.size();
        this.melanger();
        this.distribution();
    }

    protected void melanger() {
        Collections.shuffle(deck);
    }

    protected void distribution() {
        int nbCartesParJoueur = deck.size() / joueurs.size();
        int reste = deck.size() % joueurs.size();

        for (int i = 0; i < joueurs.size(); ++i) {
            for (int j = 0; j < nbCartesParJoueur; ++j) {
                joueurs.get(i).ajouterCarte(deck.remove(0));
            }
        }

        if (reste != 0) {
            for (int i = 0; i < reste; ++i) {
                joueurs.get(i).ajouterCarte(deck.get(0));
            }
        }

    }

    @Override
    public void commencer() {
        this.start();
    }

    private int checkGagnantPartie() {
        synchronized (this.verrou) {
            for (int i = 0; i < joueurs.size(); ++i) {
                if (joueurs.get(i).getNbCartesDeck() == nbCartesPourGagner) {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.verrou) {
                int joueurActuel = this.evenements.get();
                if (this.cartesJoueesParJoueur.containsKey(joueurActuel)) {
                    throw new IllegalArgumentException("Le joueur " + joueurActuel + " a deja joue.");
                }

                Carte carte = this.joueurs.get(joueurActuel).getCarte();

                if (this.cartesJoueesParJoueur.containsValue(carte)) {
                    throw new IllegalStateException("La meme carte est jouee plusieurs fois.");
                }

                this.cartesJoueesParJoueur.put(joueurActuel, carte);

                if (this.cartesJoueesParJoueur.size() == this.joueurs.size()) {

                    this.trierCartesJouees();
                    this.setGagnantTour();

                    Iterator<Carte> it = cartesJoueesParJoueur.values().iterator();
                    while (it.hasNext()) {
                        Carte carteGagnee = it.next();
                        this.joueurs.get(this.gagnantTour).ajouterCarte(carteGagnee);
                    }

                    this.notifier.notifyObservers(EvenementJeu.ID.FIN_TOUR);

                    try {
                        this.verrou.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Bataille.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    this.cartesJoueesParJoueur.clear();
                    this.cartesJoueesTriees.clear();

                    if ((this.gagnantPartie = this.checkGagnantPartie()) >= 0) {
                        this.notifier.notifyObservers(EvenementJeu.ID.FIN_PARTIE);
                        break;
                    }

                }
            }

        }

    }

    @Override
    public int[] getGagnantTour() {
        synchronized (this.verrou) {
            int[] ret = {this.gagnantTour};
            this.verrou.notifyAll();
            return ret;
        }
    }

    protected void setGagnantTour() {
        for (Map.Entry<Integer, Carte> entrySet : this.cartesJoueesParJoueur.entrySet()) {
            Integer key = entrySet.getKey();
            Carte value = entrySet.getValue();

            if (value.equals(cartesJoueesTriees.get(cartesJoueesTriees.size() - 1))) {
                this.gagnantTour = key;
            }
        }
    }

    @Override
    public int[] getGagnantPartie() {
        synchronized (this.verrou) {
            if (gagnantPartie < 0) {
                throw new IllegalStateException("La partie n'est pas finie");
            } else {
                int[] ret = {gagnantPartie};
                return ret;
            }
        }
    }

    @Override
    protected void recevoirInput(Object indexJoueur) {
        try {
            Integer evenement = (Integer) indexJoueur;

        } catch (ClassCastException e) {
            throw new IllegalArgumentException("L'input n'a pas pu etre traite : " + e.getMessage(), e);
        }
    }

    @Override
    public int getNbJoueurs() {
        return joueurs.size();
    }

    @Override
    public boolean main() {
        return false;
    }

    @Override
    public int tailleMain(int joueur) {
        throw new UnsupportedOperationException("Les joueurs ne possèdent pas de main dans la bataille.");
    }

    @Override
    public int tailleDeckJoueur(int joueur) {
        return this.joueurs.get(joueur).getNbCartesDeck();
    }

    @Override
    public int tailleDeckJeu() {
        return 0;
    }

    @Override
    public int tailleFausse() {
        return 0;
    }

    @Override
    public List<String> cartesJoueur(int joueur) {
        return null;
    }

    @Override
    public void onNotify(Integer event) {
        evenements.add(event);
    }

    private void trierCartesJouees() {
        for (Map.Entry<Integer, Carte> entry : cartesJoueesParJoueur.entrySet()) {
            cartesJoueesTriees.add(entry.getValue());
        }
        Collections.sort(cartesJoueesTriees);
    }

    @Override
    public List<List<String>> cartesJoueesCeTour() {
        synchronized (this.verrou) {
            ArrayList<List<String>> ret = new ArrayList<>();
            Iterator<Carte> it = cartesJoueesParJoueur.values().iterator();
            while (it.hasNext()) {
                Carte carte = it.next();
                ArrayList<String> temp = new ArrayList<>();
                temp.add(carte.toString());
                ret.add(temp);
            }
            return ret;
        }
    }
}
