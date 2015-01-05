/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import jeux.Carte;
import org.json.JSONObject;
import util.JSONUtil;

/**
 *
 * @author Pascal Luttgens
 */
public class CarteFrancaise extends CarteClassic {

    /**
     * Constructeur CarteFrancaise
     * @param type
     * @param valeur 
     */
    public CarteFrancaise(String type, String valeur) {
        super(type, valeur);
    }

    /**
     * Compare cette carte a une autre
     * @param c
     * @return 
     */
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
        JSONObject jsono = JSONUtil.getJSONObjectFromFile(new File("decks/ordre_valeur_cf.deck"));
        JSONObject JSONvaleurs = jsono.getJSONObject("valeurs");
        Iterator<String> it = JSONvaleurs.keys();
        while (it.hasNext()) {
            String key = it.next();
            valeurs.put(key, JSONvaleurs.getInt(key));
        }
    }

}
