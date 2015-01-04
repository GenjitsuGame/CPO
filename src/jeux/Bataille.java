/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.RingBuffer;

/**
 *
 * @author Adrien
 */
public class Bataille extends AbstractRunnableJeu {
    
    protected final List<Carte> deck;
    protected final List<Joueur> joueurs;
    protected int nbCartesPourGagner;
    protected int gagnant;
    
    protected final TreeSet<Carte> cartesJouees;
    

    private final RingBuffer<Integer> evenements;

    public Bataille() {
        this.joueurs = new ArrayList<>();
        this.deck = new LinkedList<>();
        this.cartesJouees = new TreeSet<>();
        this.evenements = new RingBuffer<>();
        this.init();
    }

    private void init() {
        for (int i = 0 ;
                 i < Integer.parseInt(getOption("nbJoueurs"));
                 ++i ) {
            try {
                this.joueurs.add((Joueur) Class.forName("joueurs." + getOption("typeJoueur")).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Bataille.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.deck.addAll(chargerDeck(getOption("deck")));
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
                joueurs.get(i).ajouterCarte(deck.get(0));
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

    private int checkGagnant() {
        for (int i = 0 ; i < joueurs.size() ; ++i) {
            if (joueurs.get(i).getNbCartesDeck()== nbCartesPourGagner) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void run() {
        HashSet<Integer> ayantJoue = new HashSet<>();
       
        while (true) {
            int joueurActuel = evenements.get();
            if (ayantJoue.contains(joueurActuel)) {
                throw new IllegalArgumentException("Le joueur "+ joueurActuel + " a deja joue.");
            }
            
            ayantJoue.add(joueurActuel);
            
            Carte carte = joueurs.get(joueurActuel).getCarte();
            
            if (cartesJouees.contains(carte)) {
                throw new IllegalStateException("La meme carte est jouee plusieurs fois.");
            }
            
            cartesJouees.add(carte);
            
            if (cartesJouees.size() == joueurs.size() && ayantJoue.size() == joueurs.size()) {
                ArrayList cartesTriees = new ArrayList(cartesJouees);
                Collections.sort(cartesTriees);
                System.out.println(cartesTriees.get(0));
                notifier.notifyObservers(Bataille.TOUR_FINI);
                if ((gagnant = this.checkGagnant()) >= 0) {
                    notifier.notifyObservers(PARTIE_FINIE);
                    break;
                }
            }

        }
    }

    @Override
    public int[] getGagnant() {
        if (gagnant < 0) {
            throw new IllegalStateException("La partie n'est pas finie");
        }
        else {
            int[] ret = {gagnant};
            return ret;
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
    public List<Carte> cartesJoueur(int joueur) {
        return null;
    }

    @Override
    public void onNotify(Integer event) {
        evenements.add(event);
    }

}
