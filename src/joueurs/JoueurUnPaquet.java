/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueurs;

import java.util.LinkedList;
import java.util.List;
import jeux.Carte;

/**
 *
 * @author scalpa
 */
public abstract class JoueurUnPaquet extends AbstractJoueur {

    protected final List<Carte> cartes;

    /**
     * Constructeur JoueurUnPaquet
     */
    public JoueurUnPaquet() {
        this.cartes = new LinkedList<>();
    }

    /**
     * Retourne la carte du deck à l'indice voulu
     * @param i
     * @return la carte à l'index i
     */
    @Override
    public Carte getCarte(int i) {
        try {
            return cartes.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("L'indice " + i + " ne reference aucune carte.", e);
        }
    }

    /**
     * Ajoute au deck du joueur
     * @param carte 
     */
    @Override
    public void ajouterCarte(Carte carte) {
        cartes.add(carte);
    }

    /**
     * Retourne la taille du deck
     * @return la taille de la liste
     */
    @Override
    public int getNbCartesDeck() {
        return this.cartes.size();
    }

    /**
     * Regarde si le deck de JoueurUnPaquet est vide
     * @return vrai si cartes est vide
     */
    @Override
    public boolean sansCarte() {
        return this.cartes.isEmpty();
    }

    /**
     * Pose la carte du dessus de notre deck
     * @return la premiere carte de notre deck
     */
    @Override
    public Carte getCarte() {
        return this.cartes.remove(0);
    }

    /**
     * Ajoute au deck du joueur un autre deck
     * @param cartes 
     */
    @Override
    public void ajouterCartes(List<Carte> cartes) {
        this.cartes.addAll(cartes);
    }

    /**
     * Retourne la taille du deck
     * @return la taille de la liste
     */
    @Override
    public int getNbCartesMain() {
        return this.cartes.size();
    }

    /**
     * Retourne la carte à l'index i du deck
     * @param i
     * @return 
     */
    @Override
    public Carte consulterCarte(int i) {
        return this.cartes.get(i);
    }

    /**
     * Retourne le deck du joueur
     * @return cartes
     */
    @Override
    public List<Carte> getMain() {
        return this.cartes;
    }

}
