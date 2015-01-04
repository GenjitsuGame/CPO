/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.List;
import jeux.Carte;

/**
 *
 * @author scalpa
 */
public interface Affichable {

    public int getNbJoueurs();
    
    public boolean main();
    
    public int tailleMain(int joueur);
    
    public int tailleDeckJoueur(int joueur);
    
    public int tailleDeckJeu();
    
    public int tailleFausse();
    
    public List<Carte> cartesJoueur(int joueur);
}
