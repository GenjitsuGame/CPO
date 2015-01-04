/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scalpa
 */
public abstract class AbstractRunnableJeu extends AbstractJeu implements Runnable {

    private final Thread thread;

    public AbstractRunnableJeu() {
        this.thread = new Thread(this);
    }

    protected void start() {
        this.thread.start();
    }

    protected void stop() {
        try {
            this.thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractRunnableJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected abstract void recevoirInput(Object input);

}
