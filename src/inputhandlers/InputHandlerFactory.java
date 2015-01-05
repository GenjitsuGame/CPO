/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputhandlers;

import io.Jeu;
import io.InputHandler;

/**
 *
 * @author scalpa
 */
public class InputHandlerFactory {

    public static InputHandler getInstance(String type, Jeu jeu) {
        if (type.equals("Bataille")) {
            return new BatailleInputHandler(jeu);
        } else if (type.equals("Pouilleux")) {
            return new PouilleuxInputHandler(jeu);
        } else {
            throw new IllegalArgumentException("Type d'InputHandler inconnu. Les types connus sont :\n" + getListe());
        }
    }

    public static String getListe() {
        return "Bataille,";
    }
}
