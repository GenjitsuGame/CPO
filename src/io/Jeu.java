/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.HashMap;
import java.util.List;
import jeux.Carte;
import util.Observer;
import util.Subject;

/**
 *
 * @author scalpa
 */
public interface Jeu extends Subject, Observer<Integer> {

        
    public static final int TOUR_FINI = 1000;
    public static final int PARTIE_FINIE = 1001;
    public static final int DEBUT_PARTIE = 1002;
    
    public void setOption(String option, String valeur);
    
    public void commencer();
    
    public int[] getGagnant();
    
    public int getNbJoueurs();
    
    public boolean main();
    
    public int tailleMain(int joueur);
    
    public int tailleDeckJoueur(int joueur);
    
    public int tailleDeckJeu();
    
    public int tailleFausse();
    
    public List<Carte> cartesJoueur(int joueur);
}
