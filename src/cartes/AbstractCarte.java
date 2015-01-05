/*
 * CPO - Cartes
 */
package cartes;

import jeux.Carte;

/**
 *Retourne le type et la valeur de la carte
 *@return le type et la valeur de la carte sous forme de chaîne de caractère
 * 
 */
public abstract class AbstractCarte implements Carte {

    @Override
    public String toString() {
        return this.getType() + " " + this.getValeur();
    }
}
