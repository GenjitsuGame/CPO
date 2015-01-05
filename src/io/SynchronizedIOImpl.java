/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import evenements.EvenementJeu;
import inputhandlers.InputHandlerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import util.Notifier;
import util.RingBuffer;

/**
 *
 * @author scalpa
 */
public class SynchronizedIOImpl extends AbstractSynchronizedIO<Integer> {

    public SynchronizedIOImpl(Jeu jeu) {
        super(jeu);
    }

    @Override
    public final void onNotify(Integer event) {
        this.evenementsAffichage.add(event);
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        evenementsInput.add(sc.nextLine());
    }

    @Override
    public void start() {
        synchronized (verrou) {
            this.jeu.commencer();
            this.threadAffichage.start();
            this.threadInput.start();
            this.inputHandler.start();
        }
    }

    @Override
    protected void traiteEvenement(Integer evenement) {
        switch (evenement) {
            case EvenementJeu.ID.DEBUT_PARTIE:
                this.debutPartie();
                break;
            case EvenementJeu.ID.COUP_ILLEGAL:
                this.coupIllegal();
                break;
            case EvenementJeu.ID.FIN_TOUR:
                this.finTour();
                break;
            case EvenementJeu.ID.FIN_PARTIE:
                this.finPartie();
                break;
        }
    }

    protected void coupIllegal() {
        System.out.println("COUP ILLEGAL");
    }

    protected void finPartie() {
        System.out.println("PARTIE FINIE");
        System.out.println(Arrays.toString(jeu.getGagnantPartie()));
    }

    protected void debutPartie() {
        System.out.println("DEBUT PARTIE");
    }

    protected void finTour() {
        System.out.println(Arrays.toString(jeu.getGagnantTour()));
        System.out.println("TOUR FINI");
    }

    protected void idInconnue() {
        System.out.println("JOUEUR INCONNU");
    }
}
