/*
 * CPO - Cartes
 */
package jeux;

/**
 *
 * @author Pascal Luttgens
 */
public interface Carte extends Comparable<Carte> {
 
    /**
     * Retourne le type de la carte
     * @return type
     */
    String getType();
   
    /**
     * Retourne la valeur de la carte
     * @return valeur
     */
    String getValeur();
}
