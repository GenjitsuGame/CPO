/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import application.IO;

/**
 *
 * @author scalpa
 */
public abstract class AbstractIO implements IO {
    
    protected final Affichable jeu;

    public AbstractIO(Affichable jeu) {
        this.jeu = jeu;
    }
    
    
}
