/*
 * CPO - Cartes
 */
package util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Pascal Luttgens
 */
public class JSONUtil {

    public static JSONObject getJSONObjectFromFile(File file) {
        try (FileReader fr = new FileReader(file)) {
            String json = new String();
            while (fr.ready()) {
                json += (char) fr.read();
            }
            int index = json.indexOf("{");
            json = json.substring(index);
            return new JSONObject(json);
        } catch (IOException ex) {
            Logger.getLogger(JSONUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
