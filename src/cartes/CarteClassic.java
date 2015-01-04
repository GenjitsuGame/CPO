/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cartes;

import java.util.Comparator;
import jeux.Carte;

/**
 *
 * @author Adrien
 */
public class CarteClassic extends AbstractCarte {
    private final String valeur;
    private final String type;
    
    public CarteClassic(String valeur, String type){
        this.valeur = valeur;
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getValeur() {
        return this.valeur;
    }

    @Override
    public int compareTo(Carte c) {
        throw new UnsupportedOperationException("CompareTo n'est pas implementee dans CarteClassic, il faut utiliser une sous-classe qui l'implemente.");
    }
    

}
