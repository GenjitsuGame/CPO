/*
 * CPO - Cartes
 */
package jeux;

/**
 *
 * @author Pascal Luttgens
 */
public interface Carte extends Comparable<Carte> {
    
    String getType();
    
    String getValeur();
}
