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
public class CarteF {
    private int famille;
    private char role;

    public CarteF(int valeur, char type){
        this.famille = valeur;
        this.role = type;
    }

    public int getfamille(){
        return this.famille;
    }
    
    public char getrole(){
        return this.role;
    }
    
    public boolean compare(CarteF x){
        if(x.role==this.role && this.famille==x.famille){
            return true;
        }
        else
            return false;
    }

}
