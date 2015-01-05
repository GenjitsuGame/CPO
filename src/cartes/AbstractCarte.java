/*
 * CPO - Cartes
 */
package cartes;

import jeux.Carte;


public abstract class AbstractCarte implements Carte {

    /**
    *Retourne le type et la valeur de la carte
    *@return le type et la valeur de la carte sous forme de chaîne de caractère
    */
    @Override
    public String toString() {
        return this.getType() + " " + this.getValeur();
    }
}
