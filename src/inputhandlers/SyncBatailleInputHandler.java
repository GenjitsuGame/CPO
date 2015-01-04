/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputhandlers;

import application.Jeu;
import jeux.Bataille;

/**
 *
 * @author Pascal Luttgens
 */
public class SyncBatailleInputHandler extends AbstractInputHandler {
    
    private final Bataille jeu;

    public SyncBatailleInputHandler(Bataille jeu) {
        this.jeu = jeu;
    }

    
    
}
