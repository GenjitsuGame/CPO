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
public class Bataille {
    private JoueurB J;
    private JoueurB J2;
    private ArrayList<CarteN> paquet = new ArrayList<>();

    
    public Bataille(){
       J = new JoueurB();
       J2 = new JoueurB(); 
       initpaquet();
    }
    
    public void initpaquet(){
        paquet.add(new CarteN(8,'t'));
        paquet.add(new CarteN(8,'p'));
        paquet.add(new CarteN(8,'c'));
        paquet.add(new CarteN(8,'a'));
        paquet.add(new CarteN(9,'t'));
        paquet.add(new CarteN(9,'t'));
        paquet.add(new CarteN(9,'t'));
        paquet.add(new CarteN(9,'t'));
        paquet.add(new CarteN(10,'t'));
        paquet.add(new CarteN(10,'t'));
        paquet.add(new CarteN(10,'t'));
        paquet.add(new CarteN(10,'t'));
        paquet.add(new CarteN(11,'t'));
        paquet.add(new CarteN(11,'t'));
        paquet.add(new CarteN(11,'t'));
        paquet.add(new CarteN(11,'t'));
        paquet.add(new CarteN(12,'t'));
        paquet.add(new CarteN(12,'t'));
        paquet.add(new CarteN(12,'t'));
        paquet.add(new CarteN(12,'t'));
        paquet.add(new CarteN(13,'t'));
        paquet.add(new CarteN(13,'t'));        
        paquet.add(new CarteN(13,'t'));
        paquet.add(new CarteN(13,'t')); 
        paquet.add(new CarteN(14,'t'));
        paquet.add(new CarteN(14,'t'));        
        paquet.add(new CarteN(14,'t'));
        paquet.add(new CarteN(14,'t'));
        paquet.add(new CarteN(15,'t'));
        paquet.add(new CarteN(15,'t'));        
        paquet.add(new CarteN(15,'t'));
        paquet.add(new CarteN(15,'t')); 
    }
    
    public void melanger(){
        for(int i = 0 ; i < 16;i++){
            
        }
                
    } 
    
    public void distribution(){
        int i = 0 ;
        
        while(i<32){
            if(i%2==0){
                J.ajoutcarte(paquet.get(i));
            }
            else{
                J2.ajoutcarte(paquet.get(i));
            }
            i++;
        }
    }
    
    
    
    public void jouer(){
        CarteN c  = J.getpremcarte();
        CarteN c1  = J2.getpremcarte();
        
        if(c.compare(c1)>0){
            J2.retirercarte();
            J.ajoutcarte(c1);
        }
        else if(c.compare(c1)<0){
            J.retirercarte();
            J2.ajoutcarte(c1);            
        }
        else{
            //jouer()??
        }
    }
    
}
