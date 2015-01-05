/*
 * CPO - Cartes
 */
package application;

import evenements.EvenementCartes;
import evenements.EvenementJeu;
import io.BatailleIO;
import io.SynchronizedIOImpl;
import jeux.Bataille;

/**
 *
 * @author Pascal Luttgens
 */
public class Main {
    public static void main(String[] args) {
        EvenementJeu.ID lel = new EvenementJeu.ID();
        EvenementCartes.ID e = new EvenementCartes.ID();
        IO io = new BatailleIO(new Bataille());
        io.start();
        while (true) {            
            io.input();
        }
    }
}
