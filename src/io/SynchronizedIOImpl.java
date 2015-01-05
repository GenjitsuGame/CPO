/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import evenements.EvenementJeu;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author scalpa
 */
public class SynchronizedIOImpl extends AbstractSynchronizedIO<EvenementJeu.ID> {

    public SynchronizedIOImpl(Jeu jeu) {
        super(jeu);
    }

    @Override
    public final void onNotify(EvenementJeu.ID event) {
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
    protected void traiteEvenement(EvenementJeu.ID evenement) {
        switch (evenement) {
            case DEBUT_PARTIE:
                this.debutPartie();
                break;
            case COUP_ILLEGAL:
                this.coupIllegal();
                break;
            case FIN_TOUR:
                this.finTour();
                break;
            case FIN_PARTIE:
                this.finPartie();
                break;
            case ID_INCONNUE:
                this.idInconnue();
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
