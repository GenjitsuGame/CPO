/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeux;

import cartes.CarteN;
import java.util.ArrayList;

/**
 *
 * @author Adrien
 */

/**
 * 
 * Joueur classique
 */
public class JoueurB {
    private ArrayList<CarteN> main;
    
    public JoueurB(){
       this.main = new  ArrayList<CarteN>();
    }
    
    public void ajoutcarte(CarteN carte){
        main.add(carte);
    }
      
    public void retirercarte(){
        main.remove(1);
    }
    
    public CarteN getpremcarte(){
        return main.get(1);
    }

    void defaussedouble() {
        //cherche doublon
    }
}
