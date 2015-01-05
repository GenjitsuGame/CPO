/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputhandlers;

import io.Jeu;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import jeux.Pouilleux;

/**
 *
 * @author scalpa
 */
class PouilleuxInputHandler extends AbstractInputHandler<Pouilleux, Integer> {

    public PouilleuxInputHandler(Jeu jeu) {
        super(jeu);
    }

    @Override
    public void run() {
        while (true) {
            Integer carteAPiocher = Integer.parseInt(this.evenements.get());
                      
            this.jeu.onNotify(carteAPiocher);
        }
    }
    
}
