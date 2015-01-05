/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

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
public class SimpleIO extends AbstractIO {

    private final Object verrou;

    private final Notifier<String> notifier;

    private final Thread threadAffichage;
    private final RingBuffer<Integer> evenementsAffichage;

    private final Thread threadInput;
    private final RingBuffer<String> evenementsInput;

    private final InputHandler inputHandler;

    public SimpleIO(Jeu jeu) {
        super(jeu);
        this.verrou = new Object();
        this.notifier = new Notifier();
        this.inputHandler = InputHandlerFactory.getInstance("Bataille", jeu);
        this.evenementsAffichage = new RingBuffer<>();
        this.threadAffichage = new Thread(() -> {
            while (true) {
                Integer evenement = evenementsAffichage.get();
                switchEvenementID(evenement);
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
        this.inputHandler.getNotifier().registerObserver(this);
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
    protected void coupIllegal() {
        System.out.println("Coup Illegal");
    }

    @Override
    protected void finPartie() {
        System.out.println("PARTIE FINIE");
        System.out.println(Arrays.toString(jeu.getGagnantPartie()));
    }

    @Override
    protected void debutPartie() {
        System.out.println("DEBUT PARTIE");
    }

    @Override
    protected void finTour() {
        System.out.println(Arrays.toString(jeu.getGagnantTour()));
        System.out.println("TOUR FINI");
    }

}
