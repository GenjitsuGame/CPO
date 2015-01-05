/*
 * CPO - Cartes
 */
package inputhandlers;

import io.Jeu;
import io.InputHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Notifier;
import util.RingBuffer;

/**
 *
 * @author Pascal luttgens
 * @param <T>
 */
public abstract class AbstractInputHandler<T extends Jeu, U> implements InputHandler, Runnable {

    protected final T jeu;
    protected final RingBuffer<String> evenements;
    private final Thread thread;
    protected final Notifier<U> notifier;

    public AbstractInputHandler(Jeu jeu) {
        this.thread = new Thread(this);
        this.jeu = (T) jeu;
        this.evenements = new RingBuffer<>();
        this.notifier = new Notifier<>();
    }

    @Override
    public void traite(String input) {
        evenements.add(input);
    }

    private Thread.UncaughtExceptionHandler setUncaughtExceptionHandler() {
        return (Thread thread1, Throwable thrwbl) -> {
            handleException(thread1, thrwbl);
        };
    }
    
    protected void handleException(Thread thread, Throwable thrwbl) {
        
    }

    @Override
    public void start() {
        this.thread.setUncaughtExceptionHandler(this.setUncaughtExceptionHandler());
        this.thread.start();
    }

    public void stop() {
        try {
            this.thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractInputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Notifier getNotifier() {
        return this.notifier;
    }

}
