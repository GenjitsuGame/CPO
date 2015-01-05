/*
 * CPO - Cartes
 */
package jeux;

import io.Jeu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import util.JSONUtil;
import util.Notifier;

/**
 *
 * @author Pascal Luttgens
 */
public abstract class AbstractJeu implements Jeu {

    protected static final String CHEMIN_CLASS_CARTES = "cartes.";
    protected static final String CHEMIN_CONFIG = "configs/";
    protected static final String CHEMIN_DECKS = "decks/";

    protected static final HashMap<String, String> OPTIONS_PAR_DEFAUT = new HashMap<>();
    protected final HashMap<String, String> options;

    public AbstractJeu() {
        this.options = new HashMap<>();
        JSONObject jsono = JSONUtil.getJSONObjectFromFile(new File(CHEMIN_CONFIG + this.getClass().getSimpleName() + ".cfg"));
        Iterator<String> it = jsono.keys();
        while (it.hasNext()) {
            String key = it.next();
            String value = jsono.getString(key);
            OPTIONS_PAR_DEFAUT.put(key, value);
        }
    }
    
    protected String getOption (String nomOption) {
        return ((options.get(nomOption) == null) ? OPTIONS_PAR_DEFAUT.get(nomOption) : options.get(nomOption));
    }
    
    @Override
    public void setOption(String nom, String valeur) {
        this.options.put(nom, valeur);
    }
    
    protected LinkedList<Carte> chargerDeck(String chemin) {
        try {
            LinkedList<Carte> nouveauDeck = new LinkedList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(CHEMIN_DECKS + chemin + ".deck"))));
            String ligne = null;
            while ((ligne = br.readLine()) != null ) {
                String[] parametres = ligne.split("\\s+");
                nouveauDeck.add((Carte) Class.forName("cartes." + parametres[0]).getConstructor(String.class, String.class).newInstance(parametres[1], parametres[2]));
            }
            return nouveauDeck;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AbstractJeu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AbstractJeu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(AbstractJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException("Les cartes n'ont pas ete chargees.");
    }

}
