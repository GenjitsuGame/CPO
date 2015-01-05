/*
 * CPO - Cartes
 */
package jeux;

import java.util.List;


/**
 *
 * @author Pascal Luttgens
 */
public interface Joueur {
 
    /**
     * Retourne une carte aléatoire du deck 
     * @return Carte
     */
    public Carte getCarte();


    /**
     * Retourne la carte du deck à l'indice voulu
     * @param i
     * @return la carte à l'index i
     */
    public Carte getCarte(int i);
    
    /**
     * Ajoute une carte à notre deck
     * @param carte 
     */
    public void ajouterCarte(Carte carte);
    
    /**
     * Ajoute un deck à notre main actuelle
     * @param cartes 
     */
    public void ajouterCartes(List<Carte> cartes);
    
    /**
     * Retourne le nombre de carte du joueur
     * @return nombre de carte
     */
    public int getNbCartesMain();

     /**
     * Retourne le nombre de carte du joueur
     * @return nombre de carte
     */
    public int getNbCartesDeck();
 
    /**
     * Regarde si le joueurs a des cartes ou non
     * @return vrai si le joueur n'a pas de carte
     */
    public boolean sansCarte();
 
    /**
     * Consulte la carte à l'indice i du deck
     * @param i
     * @return 
     */
    public Carte consulterCarte(int i);
  
    /**
     * Retourne le deck du joueur
     * @return la liste des cartes du joueur
     */
    public List<Carte> getMain();
    
}
