/*
 * CPO - Cartes
 */
package joueurs;

import jeux.Carte;
import java.util.ArrayList;

/**
 *
 * @author Adrien
 */

/**
 * Joueur pour 7 familles
 * 
 */
public class JoueurF {
    private ArrayList<CarteF> main;
    
    public JoueurF(){
       this.main = new  ArrayList<CarteF>();
    }
    
    public void ajoutcarte(CarteF carte){
        main.add(carte);
    }
      
    public void retirercarte(){
        main.remove(1);
    }
    
    public CarteF getpremcarte(){
        return main.get(1);
    }

    void interroge(JoueurF j,CarteF c) {
        for(int i = 0 ; i < j.main.size() ; i ++){
            if(c.compare(j.main.get(i))){
                j.retirercarte();
                this.ajoutcarte(c);
            }
            else{
                //this.pioche(deck);
            }
        }
    }

    private int getsize() {
        return main.size();
    }
}
