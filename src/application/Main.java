/*
 * CPO - Cartes
 */
package application;

import io.BatailleIO;
import io.SynchronizedIOImpl;
import jeux.Bataille;

/**
 *
 * @author Pascal Luttgens
 */
public class Main {
    public static void main(String[] args) {
        IO io = new BatailleIO(new Bataille());
        io.start();
        while (true) {            
            io.input();
        }
    }
}
