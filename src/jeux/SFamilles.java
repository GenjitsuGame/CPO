/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeux;

import cartes.CarteF;
import cartes.CarteN;
import java.util.ArrayList;

/**
 *
 * @author Adrien
 */

/**
 * 
 * jeux sept familles
 */
public class SFamilles {
    private ArrayList<CarteN> paquet = new ArrayList<>();
    private ArrayList<JoueurB> joueurs = new ArrayList<>();
    
    public SFamilles(int n){
       assert(n>2 && n<6);
       for(int i = 0 ; i < n ; i++){
           joueurs.add(new JoueurB());
       }
       initpaquet();
    }
    
    public void initpaquet(){
        //initalise le paquet avec toutes les cartes CarteF
    }
    
        
    public void melanger(){
        //fonction qui melange le paquet        
    } 
    
    public void distribution(){
        int x= 0;
        int j = 0 ;
        int i = 52/joueurs.size();
        for(int cpt=0;cpt < joueurs.size(); cpt++){
            for (j = 0 ; j < i ; j++){
                joueurs.get(x).ajoutcarte(paquet.get(j));
            }
            x++;
        }
    }   
    
    public void defausse(){
        for(int i = 0; i < joueurs.size() ; i++){
            joueurs.get(i).defaussedouble();
        }
    }
    
    public void joue(JoueurF j,CarteF f){
        //joueur demande a qq un carte j.interroge(j2,f);
    }
    
    public int getNbjoueur(){
        return joueurs.size();
    }
    
    
}
