/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputhandlers;

import evenements.EvenementJeu;
import io.Jeu;
import java.util.HashSet;
import jeux.Bataille;

/**
 *
 * @author Pascal Luttgens
 */
public class BatailleInputHandler extends AbstractInputHandler<Bataille, Integer> {
    
    protected HashSet<Integer> ayantJoue = new HashSet<>();
    
    public BatailleInputHandler(Jeu jeu) {
        super(jeu);
    }

    @Override
    public void run() {
        while (true) {
            Integer joueur = Integer.parseInt(evenements.get());
            if (ayantJoue.contains(joueur)) {
                notifier.notifyObservers(EvenementJeu.ID.COUP_ILLEGAL);
                continue;
            }
            if (joueur >= jeu.getNbJoueurs()) {
                notifier.notifyObservers(EvenementJeu.ID.ID_INCONNUE);
                continue;
            }
            
            ayantJoue.add(joueur);
            this.jeu.onNotify(joueur);
            if (ayantJoue.size() == this.jeu.getNbJoueurs()) {
                ayantJoue.clear();
            }
        }
    }

}
