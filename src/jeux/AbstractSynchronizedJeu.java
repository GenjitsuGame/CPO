/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux;

import java.util.logging.Level;
import java.util.logging.Logger;
import util.Notifier;
import util.Observer;
import util.Subject;

/**
 *
 * @author scalpa
 */
public abstract class AbstractSynchronizedJeu<T> extends AbstractJeu implements Runnable, Subject, Observer<T> {

    protected final Notifier notifier;

    private final Thread thread;

    public AbstractSynchronizedJeu() {
        this.notifier = new Notifier();
        this.thread = new Thread(this);
    }

    protected void start() {
        this.thread.start();
    }

    protected void stop() {
        try {
            this.thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractSynchronizedJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Notifier getNotifier() {
        return this.notifier;
    }
    
    protected abstract void recevoirInput(Object input);

}
