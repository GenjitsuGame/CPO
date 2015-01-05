/*
 * CPO - Cartes
 */
package application;

import io.PouilleuxIO;
import jeux.Pouilleux;

/**
 *
 * @author Pascal Luttgens
 */
public class Main {
    public static void main(String[] args) {
        IO io = new PouilleuxIO(new Pouilleux());
        io.start();
        while (true) {            
            io.input();
        }
    }
}
