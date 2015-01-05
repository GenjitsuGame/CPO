/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import application.IO;
import util.Observer;

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

    protected void switchEvenementID(int evenement) {
        switch (evenement) {
            case Jeu.DEBUT_PARTIE:
                this.debutPartie();
                break;
            case Jeu.COUP_ILLEGAL:
                this.coupIllegal();
                break;
            case Jeu.FIN_TOUR:
                this.finTour();
                break;
            case Jeu.FIN_PARTIE:
                this.finPartie();
                break;
        }
    }

    protected void finTour() {
    }

    protected void debutPartie() {
    }

    protected void finPartie() {
    }

    protected void coupIllegal() {
    }

}
