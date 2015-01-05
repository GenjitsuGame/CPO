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
import util.RingBuffer;
import util.Subject;

/**
 *
 * @author scalpa
 */
public abstract class AbstractSynchronizedJeu<T> extends AbstractJeu implements Runnable, Subject, Observer<T> {

    protected final Object verrou;

    protected final Notifier notifier;
    protected final RingBuffer<T> evenements;

    private final Thread thread;

    public AbstractSynchronizedJeu() {
        this.verrou = new Object();
        this.notifier = new Notifier();
        this.evenements = new RingBuffer<>();
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
    public void commencer() {
        this.start();
    }

    @Override
    public Notifier getNotifier() {
        return this.notifier;
    }

    @Override
    public void onNotify(T event) {
        evenements.add(event);
    }

    @Override
    public void reprendre() {
        synchronized (this.verrou) {
            this.verrou.notifyAll();
        }
    }

}
