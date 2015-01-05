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
public interface Jeu {
    
    public void setOption(String option, String valeur);
    
    public void commencer();
    
    public List<Integer> getGagnantPartie();
    
    public List<Integer> getGagnantTour();
    
    public List<Integer> getPerdantPartie();
    
    public int getNbJoueurs();
    
    public boolean main();
    
    public int tailleMain(int joueur);
    
    public int tailleDeckJoueur(int joueur);
    
    public int tailleDeckJeu();
    
    public int tailleFausse();
    
    public List<String> cartesJoueur(int joueur);
    
    public List<List<String>> cartesJoueesCeTour();
    
    public List<String> getPli();
    
    public void reprendre();
}
