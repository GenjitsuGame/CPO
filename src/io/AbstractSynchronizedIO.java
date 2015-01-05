/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import evenements.EvenementJeu;
import inputhandlers.InputHandlerFactory;
import java.util.List;
import java.util.Scanner;
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
    public void input() {
        Scanner sc = new Scanner(System.in);
        evenementsInput.add(sc.nextLine());
    }

    @Override
    public final void onNotify(T event) {
        this.evenementsAffichage.add(event);
    }

    protected abstract void traiteEvenement(T evenement);

    protected void switchEvenementID(Integer evenement) {
        if (evenement == EvenementJeu.ID.DEBUT_PARTIE) {
            this.debutPartie();
        } else if (evenement == EvenementJeu.ID.COUP_ILLEGAL) {
            this.coupIllegal();
        } else if (evenement == EvenementJeu.ID.FIN_TOUR) {
            this.finTour();
        } else if (evenement == EvenementJeu.ID.FIN_PARTIE) {
            this.finPartie();
        } else if (evenement == EvenementJeu.ID.ID_INCONNUE) {
            this.idInconnue();
        } else if (evenement == EvenementJeu.ID.TOUR_NUL) {
            this.tourNul();
        } else if (evenement == EvenementJeu.ID.NOUVEAU_GAGNANT) {
            this.nouveauGagnant();
        } else if (evenement == EvenementJeu.ID.NOUVEAU_PERDANT) {
            this.nouveauPerdant();
        }
    }

    protected void coupIllegal() {
        System.out.println("COUP ILLEGAL");
    }

    protected void finPartie() {
        System.out.println("PARTIE FINIE");
        System.out.println(jeu.getGagnantPartie());
    }

    protected void debutPartie() {
        System.out.println("DEBUT PARTIE");
    }

    protected void finTour() {
        List<Integer> gagnants = jeu.getGagnantTour();
        System.out.println((gagnants.isEmpty()) ? "TOUR NUL" : gagnants);
        System.out.println("TOUR FINI");
    }

    protected void tourNul() {
        System.out.println("TOUR NUL");
    }

    protected void nouveauPerdant() {
        System.out.println("NOUVEAU PERDANT");
    }

    protected void nouveauGagnant() {
        System.out.println("NOUVEAU GAGNANT");
    }

    protected void idInconnue() {
        System.out.println("JOUEUR INCONNU");
    }

}
