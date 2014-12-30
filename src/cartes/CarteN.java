/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cartes;

/**
 *
 * @author Adrien
 */
public class CarteN {
    private int valeur;
    private char type;

    public CarteN(int valeur, char type){
        this.valeur = valeur;
        this.type = type;
    }

    public int getval(){
        return this.valeur;
    }
    
    public char gettype(){
    return this.type;
    }
    
    public int compare(CarteN x){
       return  this.valeur - x.valeur;
    }

}