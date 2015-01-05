/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux;

import evenements.EvenementJeu;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class Bataille extends AbstractSynchronizedJeu<Integer> {

    protected final List<Carte> deck;
    protected final List<Joueur> joueurs;
    protected final List<Carte> pli;
    protected final List<Integer> perdants;

    protected int nbCartesPourGagner;
    protected final List<Integer> gagnantPartie;
    protected final List<Integer> gagnantTour;

    protected final TreeMap<Integer, Carte> cartesJoueesParJoueur;
    protected final List<Carte> cartesJoueesTriees;

    public Bataille() {
        this.joueurs = new ArrayList<>();
        this.perdants = new ArrayList<>();
        this.gagnantPartie = new ArrayList<>();
        this.gagnantTour = new ArrayList<>();
        this.deck = new LinkedList<>();
        this.pli = new ArrayList<>();
        this.cartesJoueesParJoueur = new TreeMap<>();
        this.cartesJoueesTriees = new ArrayList<>();
        this.init();
    }

    protected void init() {
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

    protected int checkGagnantPartie() {
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
        synchronized (this.verrou) {
            this.notifier.notifyObservers(EvenementJeu.ID.DEBUT_PARTIE);
            while (true) {

                int joueurActuel = this.evenements.get();
                if (this.cartesJoueesParJoueur.containsKey(joueurActuel)) {
                    throw new IllegalArgumentException("Le joueur " + joueurActuel + " a deja joue.");
                }

                if (estPerdant(joueurActuel)) {
                    this.notifier.notifyObservers(EvenementJeu.ID.COUP_ILLEGAL);
                    continue;
                }

                Carte carte = this.joueurs.get(joueurActuel).getCarte();

                if (this.cartesJoueesParJoueur.containsValue(carte)) {
                    throw new IllegalStateException("La meme carte est jouee plusieurs fois.");
                }

                this.cartesJoueesParJoueur.put(joueurActuel, carte);

                if (this.cartesJoueesParJoueur.size() == this.joueurs.size()) {

                    this.trierCartesJouees();
                    if (this.checkTourNul()) {
                        this.pli.addAll(this.cartesJoueesTriees);
                    } else {
                        this.setGagnantTour();
                        this.joueurs.get(this.gagnantTour.get(0)).ajouterCartes(cartesJoueesTriees);
                        if (!this.pli.isEmpty()) {
                            this.joueurs.get(this.gagnantTour.get(0)).ajouterCartes(this.pli);
                        }
                    }

                    this.notifier.notifyObservers(EvenementJeu.ID.FIN_TOUR);

                    try {
                        this.verrou.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Bataille.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!this.gagnantTour.isEmpty()) {
                        pli.clear();
                    }

                    this.gagnantTour.clear();
                    this.cartesJoueesParJoueur.clear();
                    this.cartesJoueesTriees.clear();

                    if (!this.gagnantPartie.isEmpty()) {
                        this.notifier.notifyObservers(EvenementJeu.ID.FIN_PARTIE);
                        break;
                    }
                }
            }

        }

    }

    @Override
    public List<Integer> getGagnantTour() {
        synchronized (this.verrou) {
            return this.gagnantTour;
        }
    }

    protected boolean estPerdant(int joueur) {
        return this.perdants.contains(joueur);
    }

    protected boolean checkPerdant() {
        boolean ret = false;
        for (int i = 0; i < this.joueurs.size(); ++i) {
            if (this.joueurs.get(i).getNbCartesDeck() == 0) {
                this.perdants.add(i);
                this.notifier.notifyObservers(EvenementJeu.ID.NOUVEAU_PERDANT);
                ret = true;
            }
        }
        return ret;
    }

    protected void setGagnantTour() {
        for (Map.Entry<Integer, Carte> entrySet : this.cartesJoueesParJoueur.entrySet()) {
            Integer key = entrySet.getKey();
            Carte value = entrySet.getValue();

            if (value.equals(cartesJoueesTriees.get(cartesJoueesTriees.size() - 1))) {
                this.gagnantTour.add(key);
            }
        }
    }

    @Override
    public List<Integer> getGagnantPartie() {
        synchronized (this.verrou) {
            if (this.gagnantPartie.isEmpty()) {
                throw new IllegalStateException("La partie n'est pas finie");
            } else {
                List<Integer> ret = new ArrayList<>();
                ret.add(this.gagnantPartie.get(0));
                return ret;
            }
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

    protected void trierCartesJouees() {
        for (Map.Entry<Integer, Carte> entry : this.cartesJoueesParJoueur.entrySet()) {
            this.cartesJoueesTriees.add(entry.getValue());
        }
        Collections.sort(this.cartesJoueesTriees);
    }

    protected boolean checkTourNul() {
        Carte carte = this.cartesJoueesTriees.get(0);
        for (int i = 1; i < this.joueurs.size(); ++i) {
            if (!carte.getValeur().equals(this.cartesJoueesTriees.get(i).getValeur())) {
                return false;
            }
        }
        this.notifier.notifyObservers(EvenementJeu.ID.TOUR_NUL);
        return true;
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

    @Override
    public List<Integer> getPerdantPartie() {
        return this.perdants;
    }

    @Override
    public List<String> getPli() {
        synchronized (this.verrou) {
            ArrayList<String> ret = new ArrayList<>();
            for (Carte carte : this.pli) {
                ret.add(carte.toString());
            }
            return ret;
        }
    }

}
