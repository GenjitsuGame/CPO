/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cartes;

import java.util.Objects;
import jeux.Carte;

/**
 *
 * @author Adrien
 */
public class CarteClassic extends AbstractCarte {
    private final String valeur;
    private final String type;
    
    /**
    * Constructeur CarteClassic
    * @param type
    * @param valeur 
    */
    public CarteClassic(String type, String valeur){
        this.valeur = valeur;
        this.type = type;
    }

    /**
     * Retourne le type de CarteClassic
     * @return type
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Retourne la valeur de CarteClassic
     * @return valeur 
     */
    @Override
    public String getValeur() {
        return this.valeur;
    }

    /**
     * Compare cette carte a une autre
     * @param Carte la carte à comparer à this
     * @return 
     */
    @Override
    public int compareTo(Carte c) {
        throw new UnsupportedOperationException("CompareTo n'est pas implementee dans CarteClassic, il faut utiliser une sous-classe qui l'implemente.");
    }

    /**
     * Compare l'égalité entre this et un Object
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarteClassic other = (CarteClassic) obj;
        if (!Objects.equals(this.valeur, other.valeur)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    

}
