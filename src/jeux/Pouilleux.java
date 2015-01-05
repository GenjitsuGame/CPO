/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux;

import evenements.EvenementJeu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien
 */
public class Pouilleux extends AbstractSynchronizedJeu<Integer> {

    protected final List<Carte> deck;
    protected final List<Joueur> joueurs;
    protected final List<String> doublesList;
    protected final List<Integer> gagnants;

    protected Carte cartePiochee;
    protected int tour;
    protected int nbJoueursEnJeu;

    public Pouilleux() {
        this.deck = new LinkedList<>();
        this.joueurs = new ArrayList<>();
        this.doublesList = new ArrayList<>();
        this.gagnants = new ArrayList<>();
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
        if (!this.deck.removeIf((Carte carte) -> {
            return carte.getType().equals(this.getOption("carteRetireeType")) && carte.getValeur().equals(this.getOption("carteRetireeValeur"));
        })) {
            throw new IllegalStateException("Le deck fourni ne contient pas de valet de trefle.");
        }
        this.tour = Integer.parseInt(this.getOption("joueurDebut"));
        this.melanger();
        this.distribution();
        this.defausse();
    }

    protected void melanger() {
        Collections.shuffle(this.deck);
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

    protected void defausse() {
        for (Joueur joueur : joueurs) {
            List<Carte> mainJoueur = joueur.getMain();
            this.retirerDoubles(mainJoueur);
        }
    }

    protected boolean estDoublon(Carte c1, Carte c2) {
        for (String doubleType : doublesList) {
            if (doubleType.contains(c1.getType()) && doubleType.contains(c2.getType())) {
                return true;
            }
        }
        return false;
    }

    protected boolean retirerDoubles(final List<Carte> main) {
        ArrayList<Carte> nouvelleMain = new ArrayList<>();
        ArrayList<Carte> doublesARetirer = new ArrayList<>();

        nouvelleMain.addAll(main);

        for (int i = 0; i < main.size(); ++i) {
            if (!doublesARetirer.contains(main.get(i))) {
                for (int j = 0; j < main.size(); ++j) {
                    if (i != j && !doublesARetirer.contains(main.get(j))) {
                        if (this.estDoublon(main.get(i), main.get(j))) {
                            doublesARetirer.add(main.get(i));
                            doublesARetirer.add(main.get(j));
                        }
                    }
                }
            }
        }

        if (doublesARetirer.isEmpty()) {
            return false;
        }

        nouvelleMain.removeAll(doublesARetirer);
        main.clear();
        main.addAll(nouvelleMain);
        return true;
    }

    @Override
    public int getNbJoueurs() {
        return this.joueurs.size();
    }

    @Override
    public boolean main() {
        return true;
    }

    @Override
    public int tailleMain(int joueur) {
        return this.joueurs.get(joueur).getNbCartesMain();
    }

    @Override
    public int tailleDeckJoueur(int joueur) {
        return 0;
    }

    @Override
    public int tailleDeckJeu() {
        return 0;
    }

    @Override
    public int tailleFausse() {
        throw new UnsupportedOperationException("Il n'y pas de fausse dans le pouilleux"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> cartesJoueur(int joueur) {
        List<String> ret = new ArrayList<>();
        for (Carte carte : this.joueurs.get(joueur).getMain()) {
            ret.add(carte.toString());
        }
        return ret;
    }

    @Override
    public List<List<String>> cartesJoueesCeTour() {
        List<List<String>> ret = new ArrayList<>();
        List<String> ret2 = new ArrayList<>();
        ret2.add(this.cartePiochee.toString());
        ret.add(ret2);
        return ret;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.verrou) {
                Integer indexCarte = this.evenements.get();
                Joueur joueurCourant = this.joueurs.get(this.tour);

                int indiceProchainJoueur = this.getIndiceProchainJoueur(this.tour);

                Carte carte = this.joueurs.get(indiceProchainJoueur).getCarte(indexCarte);
                this.cartePiochee = carte;
                this.notifier.notifyObservers(new EvenementJeu<String>(this, EvenementJeu.ID.PIOCHE_CARTE, carte.toString()));

                if (checkGagnant(indiceProchainJoueur)) {
                    if (this.gagnants.size() == this.joueurs.size() - 1) {
                        this.finirPartie(this.tour);
                        this.notifier.notifyObservers(new EvenementJeu<>(this, EvenementJeu.ID.FIN_PARTIE));
                        break;
                    }
                }

                joueurCourant.ajouterCarte(carte);

                if (this.retirerDoubles(joueurCourant.getMain())) {
                    this.checkGagnant(this.tour);
                    if (this.gagnants.size() == this.joueurs.size() - 1) {
                        this.finirPartie(indiceProchainJoueur);
                        this.notifier.notifyObservers(new EvenementJeu<>(this, EvenementJeu.ID.FIN_PARTIE));
                        break;
                    }
                    this.tour = indiceProchainJoueur;
                    while (this.gagnants.contains(this.tour)) {
                        this.tour = this.getIndiceProchainJoueur(this.tour);
                    }
                }
            }
        }
    }

    protected boolean checkGagnant(int joueur) {
        if (this.joueurs.get(joueur).sansCarte()) {
            gagnants.add(joueur);
            this.notifier.notifyObservers(new EvenementJeu<Integer>(this, EvenementJeu.ID.NOUVEAU_GAGNANT, joueur));
            return true;
        }
        return false;
    }

    protected int getIndiceProchainJoueur(int joueurCourant) {
        if (joueurCourant >= this.joueurs.size() || joueurCourant < 0) {
            throw new IllegalArgumentException("Joueur courant inconnu.");
        }
        int indice;
        for (int i = 0; i < this.joueurs.size(); ++i) {
            indice = (joueurCourant + 1) % this.getNbJoueurs();
            if (this.gagnants.contains(indice)) {
                continue;
            }
            return indice;
        }
        throw new IllegalStateException("Aucun prochain joueur trouve.");
    }

    protected void finirPartie(int perdant) {
        Carte derniere = this.joueurs.get(perdant).consulterCarte(0);
        if (!(derniere.getType().equals(this.getOption("carteRetireeType")) && derniere.getValeur().equals(this.getOption("carteRetireeValeur")))) {
            throw new IllegalStateException("La derniere carte du jeu n'est pas celle attendue.");
        }
        this.gagnants.add(perdant);
    }

    @Override
    public List<Integer> getGagnantPartie() {
        return this.gagnants;
    }

    @Override
    public List<Integer> getGagnantTour() {
        throw new UnsupportedOperationException("il n'y a pas de gagnant à chaque tour au pouilleux"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> getPerdantPartie() {
        if (this.gagnants.size() != this.joueurs.size()) {
            throw new IllegalStateException("La partie n'est pas finie.");
        }
        List<Integer> ret = new ArrayList<>();
        ret.add(this.gagnants.get(this.gagnants.size() - 1));
        return ret;
    }

    @Override
    public List<String> getPli() {
        throw new UnsupportedOperationException("Il n'y a pas de pli dans le pouilleux."); //To change body of generated methods, choose Tools | Templates.
    }

}
