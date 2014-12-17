/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.games;

/**
 *
 * @author scalpa
 */
public abstract class InputHandler {

    private final Player inputProvider_;

    public InputHandler(Player inputProvider) {
        this.inputProvider_ = inputProvider;
    }

    
}
