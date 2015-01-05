/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.List;
import util.Observer;
import util.Subject;

/**
 *
 * @author scalpa
 */
public interface Jeu extends Subject, Observer<Integer> {

        
    public static final int FIN_TOUR = 1000;
    public static final int FIN_PARTIE = 1001;
    public static final int DEBUT_PARTIE = 1002;
    public static final int COUP_ILLEGAL = 2000;
    
    public void setOption(String option, String valeur);
    
    public void commencer();
    
    public int[] getGagnantPartie();
    
    public int[] getGagnantTour();
    
    public int getNbJoueurs();
    
    public boolean main();
    
    public int tailleMain(int joueur);
    
    public int tailleDeckJoueur(int joueur);
    
    public int tailleDeckJeu();
    
    public int tailleFausse();
    
    public List<String> cartesJoueur(int joueur);
    
    public List<List<String>> cartesJoueesCeTour();
}
