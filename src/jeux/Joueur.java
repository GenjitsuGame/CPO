/*
 * CPO - Cartes
 */
package jeux;


/**
 *
 * @author Pascal Luttgens
 */
public interface Joueur {
    
    public Carte getCarte();
    
    public Carte getCarte(int i);
    
    public void ajouterCarte(Carte carte);
    
    public int getNbCartesMain();
    
    public int getNbCartesDeck();
    
    public boolean sansCarte();
    
    
}
