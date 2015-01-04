/*
 * CPO - Cartes
 */
package jeux;

import application.Jeu;
import io.Affichable;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;
import util.JSONUtil;

/**
 *
 * @author Pascal Luttgens
 */
public abstract class AbstractJeu implements Jeu, Affichable {
    protected static final String CHEMIN_CLASS_CARTES = "cartes.";
    protected static final String CHEMIN_CONFIG = "config/";
    protected static final String CHEMIN_DECKS = "resources/";
    
    protected static final HashMap<String, String> OPTIONS_PAR_DEFAUT = new HashMap<>();

    public AbstractJeu() {
        JSONObject jsono =  JSONUtil.getJSONObjectFromFile(new File(CHEMIN_CONFIG + this.getClass().getSimpleName() + ".cfg"));
        Iterator<String> it = jsono.keys();
        while (it.hasNext()) {
            String key = it.next();
            String value = jsono.getString(key);
            OPTIONS_PAR_DEFAUT.put(key, value);
        }
    }
}
