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

    /**
     * Constructeur Pouilleux
     */
    public Pouilleux() {
        this.deck = new LinkedList<>();
        this.joueurs = new ArrayList<>();
        this.doublesList = new ArrayList<>();
        this.gagnants = new ArrayList<>();
    }

    /**
     * Initialise un jeu du Pouilleux : nombre de joueurs, distribution , défausse
     */
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

    /**
     * Melange le deck initial
     */
    protected void melanger() {
        Collections.shuffle(this.deck);
    }

    /**
     * Distribution des cartes aux n joueurs
     */
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

    /**
     * Appel une fonction pour défausser les paires de chacune des mains des joueurs
     */
    protected void defausse() {
        for (Joueur joueur : joueurs) {
            List<Carte> mainJoueur = joueur.getMain();
            this.retirerDoubles(mainJoueur);
        }
    }

    /**
     * Compare la parité entre deux carte
     * @param c1
     * @param c2
     * @return vrai si c1 et c2 sont des doublons
     */
    protected boolean estDoublon(Carte c1, Carte c2) {
        for (String doubleType : doublesList) {
            if (doubleType.contains(c1.getType()) && doubleType.contains(c2.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retire les doublons d'un deck
     * @param main
     * @return vrai si le defaussage est effectué
     */
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

    /**
     * Retourne le nombre de joueurs dans la partie
     * @return le nombre de joueurs
     */
    @Override
    public int getNbJoueurs() {
        return this.joueurs.size();
    }

    @Override
    public boolean main() {
        return true;
    }

    /**
     * Retourne le nombre de carte dans le deck du joueur
     * @param joueur
     * @return le nombre de carte du joueur
     */
    @Override
    public int tailleMain(int joueur) {
        return this.joueurs.get(joueur).getNbCartesMain();
    }


    @Override
    public int tailleDeckJoueur(int joueur) {
        return 0;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int tailleDeckJeu() {
        return 0;
    }

    @Override
    public int tailleFausse() {
        throw new UnsupportedOperationException("Il n'y pas de fausse dans le pouilleux"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retourne le deck d'un joueur
     * @param joueur
     * @return la liste des cartes d'un joueur
     */
    @Override
    public List<String> cartesJoueur(int joueur) {
        List<String> ret = new ArrayList<>();
        for (Carte carte : this.joueurs.get(joueur).getMain()) {
            ret.add(carte.toString());
        }
        return ret;
    }

    /**
     * Retourne un deck des cartes jouees à ce tour
     * @return la liste des cartes jouees à ce tour
     */
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
    
    /**
     * Regarde si le joueur est gagnant 
     * @param joueur
     * @return vrai si joueur n'a plus de carte dans son deck
     */
    protected boolean checkGagnant(int joueur) {
        if (this.joueurs.get(joueur).sansCarte()) {
            gagnants.add(joueur);
            this.notifier.notifyObservers(new EvenementJeu<Integer>(this, EvenementJeu.ID.NOUVEAU_GAGNANT, joueur));
            return true;
        }
        return false;
    }

    /**
     * Recherche l'indice du prochain joueur à jouer
     * @param joueurCourant
     * @return indice du prochain joueur
     */
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

    /**
     * Regarde si la dernière carte en jeu est la perdante et finit le jeu
     * @param perdant 
     */
    protected void finirPartie(int perdant) {
        Carte derniere = this.joueurs.get(perdant).consulterCarte(0);
        if (!(derniere.getType().equals(this.getOption("carteRetireeType")) && derniere.getValeur().equals(this.getOption("carteRetireeValeur")))) {
            throw new IllegalStateException("La derniere carte du jeu n'est pas celle attendue.");
        }
        this.gagnants.add(perdant);
    }

    /**
     * Retourne les gagnants de la partie
     * @return la liste des gagnants
     */
    @Override
    public List<Integer> getGagnantPartie() {
        return this.gagnants;
    }

    /**
     * Retourne les tours auxquels les gagnants ont finit 
     * @return la liste "des tours gagnants"
     */
    @Override
    public List<Integer> getGagnantTour() {
        throw new UnsupportedOperationException("il n'y a pas de gagnant à chaque tour au pouilleux"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
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
