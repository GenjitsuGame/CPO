/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements;

import java.util.EventObject;

/**
 *
 * @author scalpa
 * @param <T>
 */
public class EvenementJeu<T> extends EventObject {

    private static int eventID = 1000;
    private final static Object verrou = new Object();

    protected final static int setID() {
        synchronized (verrou) {
            return eventID++;
        }
    }

    public static class ID {

        public final static int FIN_TOUR = setID();
        public final static int FIN_PARTIE = setID();
        public final static int DEBUT_PARTIE = setID();
        public final static int COUP_ILLEGAL = setID();
        public final static int ID_INCONNUE = setID();
        public final static int PIOCHE_CARTE = setID();
        public final static int NOUVEAU_GAGNANT = setID();
        public final static int TOUR_NUL = setID();
        public final static int NOUVEAU_PERDANT = setID();
    }

    private final int idEvenement;
    private final T[] object;

    public EvenementJeu(Object o, int idEvenement, T... object) {
        super(o);
        this.idEvenement = idEvenement;
        this.object = object;
    }
    
    public final int getID() {
        return this.idEvenement;
    }
    
    public final T[] getObject() {
        return this.object;
    }
}
