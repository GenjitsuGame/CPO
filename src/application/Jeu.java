/*
 * CPO - Cartes
 */
package application;

import java.util.HashMap;

/**
 *
 * @author Pascal Luttgens
 */
public interface Jeu {
    
    public void setOptions(HashMap<String, String> options);
    
    public void commencer();
    
    public int[] getGagnant();
}
