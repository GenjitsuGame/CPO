/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import application.IO;
import io.Jeu;
import util.Observer;
import util.RingBuffer;

/**
 *
 * @author scalpa
 */
public abstract class AbstractIO implements IO, Observer<Integer> {

    protected final Jeu jeu;

 
    public AbstractIO(final Jeu jeu) {
        this.jeu = (Jeu) jeu;

        jeu.getNotifier().registerObserver(this);
    }

}
