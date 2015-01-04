/*
 * CPO - Cartes
 */
package inputhandlers;

import io.Jeu;
import io.InputHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.RingBuffer;

/**
 *
 * @author Pascal luttgens
 * @param <T>
 */
public abstract class AbstractInputHandler<T extends Jeu> implements InputHandler, Runnable {

    protected final T jeu;
    protected final RingBuffer<String> evenements;
    private final Thread thread;
    
    public AbstractInputHandler(Jeu jeu) {
        this.thread = new Thread(this);
        this.jeu = (T) jeu;
        this.evenements = new RingBuffer<>();
    }
    
    @Override
    public void traite(String input) {
        evenements.add(input);
    }
    
    @Override
    public void start() {
        this.thread.start();
    }

    public void stop() {
        try {
            this.thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractInputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
