/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputhandlers;

import io.Jeu;
import java.util.HashSet;
import jeux.Bataille;

/**
 *
 * @author Pascal Luttgens
 */
public class BatailleInputHandler extends AbstractInputHandler<Bataille> {

    public BatailleInputHandler(Jeu jeu) {
        super(jeu);
    }

    @Override
    public void run() {
        HashSet<Integer> ayantJoue = new HashSet<>();
        while(true) {
            Integer joueur = Integer.parseInt(evenements.get());
            if (ayantJoue.contains(joueur)) {
                throw new IllegalArgumentException("Le joueur " + joueur + " a deja joue ce tour.");
            }
            ayantJoue.add(joueur);
            this.jeu.onNotify(joueur);
            if (ayantJoue.size() == this.jeu.getNbJoueurs()) {
                ayantJoue.clear();
            }
        }
    }

}
