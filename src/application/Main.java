/*
 * CPO - Cartes
 */
package application;

import io.SimpleIO;
import jeux.Bataille;

/**
 *
 * @author Pascal Luttgens
 */
public class Main {
    public static void main(String[] args) {
        IO io = new SimpleIO(new Bataille());
        io.start();
        while (true) {            
            io.input();
        }
    }
}
