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
    
    public Carte getCarte();
    
    public Carte getCarte(int i);
    
    public void ajouterCarte(Carte carte);
    
    public void ajouterCartes(List<Carte> cartes);
    
    public int getNbCartesMain();
    
    public int getNbCartesDeck();
    
    public boolean sansCarte();
    
    public Carte consulterCarte(int i);
    
    public List<Carte> getMain();
    
}
