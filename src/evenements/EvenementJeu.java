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
 */
public abstract class EvenementJeu extends EventObject {

    public static enum ID {

        FIN_TOUR,
        FIN_PARTIE,
        DEBUT_PARTIE,
        COUP_ILLEGAL,
        ID_INCONNUE
    }

    public EvenementJeu(Object o) {
        super(o);
    }

}
