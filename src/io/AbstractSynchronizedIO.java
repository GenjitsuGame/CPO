/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import inputhandlers.InputHandlerFactory;
import util.Notifier;
import util.Observer;
import util.RingBuffer;
import util.Subject;

/**
 *
 * @author scalpa
 * @param <T>
 */
public abstract class AbstractSynchronizedIO<T> extends AbstractIO implements Observer<T> {

    protected final Object verrou;

    protected final Notifier<String> notifier;

    protected final Thread threadAffichage;
    protected final RingBuffer<T> evenementsAffichage;

    protected final Thread threadInput;
    protected final RingBuffer<String> evenementsInput;
    
    protected final InputHandler inputHandler;

    public AbstractSynchronizedIO(Jeu jeu) {
        super(jeu);

        Subject subject = null;
        try {
            subject = (Subject) jeu;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Le jeu passe en parametre n'est pas compatible avec une SynchronizedIO.");
        }
        subject.getNotifier().registerObserver(this);

        this.verrou = new Object();
        
        this.notifier = new Notifier();
        this.evenementsAffichage = new RingBuffer<>();
        
        
        this.inputHandler = InputHandlerFactory.getInstance("Bataille", jeu);
        this.inputHandler.getNotifier().registerObserver(this);
        
        this.threadAffichage = new Thread(() -> {
            while (true) {
                T evenement = evenementsAffichage.get();
                traiteEvenement(evenement);
            }
        });

        this.evenementsInput = new RingBuffer<>();
        this.threadInput = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    inputHandler.traite(evenementsInput.get());
                }
            }
        });
    }

    protected abstract void traiteEvenement(T evenement);

}
