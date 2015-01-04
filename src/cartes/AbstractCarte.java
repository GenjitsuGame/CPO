/*
 * CPO - Cartes
 */
package cartes;

import jeux.Carte;

/**
 *
 * @author Pascal Luttgens
 */
public abstract class AbstractCarte implements Carte {

    @Override
    public String toString() {
        return this.getType() + " " + this.getValeur();
    }
}
