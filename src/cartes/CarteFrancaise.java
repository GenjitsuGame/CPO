/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes;

import java.util.HashMap;
import jeux.Carte;

/**
 *
 * @author Pascal Luttgens
 */
public class CarteFrancaise extends CarteClassic {

    public CarteFrancaise(String valeur, String type) {
        super(valeur, type);
    }

    @Override
    public int compareTo(Carte c) {
        Integer thisValeur = valeurs.get(this.getValeur());
        Integer cValeur = valeurs.get(c.getValeur());
        if (thisValeur == null || cValeur == null) {
            throw new RuntimeException("La valeur de la carte a comparer n'est pas connue : " + c.getValeur());
        }
        return thisValeur.compareTo(cValeur);
    }

    private static final HashMap<String, Integer> valeurs = new HashMap<>();

    static {
        valeurs.put("deux", 1);
        valeurs.put("trois", 2);
        valeurs.put("quatre", 3);
        valeurs.put("cinq", 4);
        valeurs.put("six", 5);
        valeurs.put("sept", 6);
        valeurs.put("huit", 7);
        valeurs.put("neuf", 8);
        valeurs.put("dix", 9);
        valeurs.put("valet", 10);
        valeurs.put("reine", 11);
        valeurs.put("roi", 12);
        valeurs.put("as", 13);
    }

}
