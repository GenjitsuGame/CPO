/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import evenements.EvenementJeu;
import inputhandlers.InputHandlerFactory;

/**
 *
 * @author scalpa
 */
public class PouilleuxIO extends AbstractSynchronizedIO<EvenementJeu> {

    /**
     * Constructeur PouilleuxIO
     * @param jeu 
     */
    public PouilleuxIO(Jeu jeu) {
        super(jeu);
                this.inputHandler = InputHandlerFactory.getInstance("Pouilleux", jeu);
        this.inputHandler.getNotifier().registerObserver(this);
    }

    @Override
    protected void traiteEvenement(EvenementJeu evenement) {
        if (!switchEvenementID(evenement.getID())) {
            if (evenement.getID() == EvenementJeu.ID.PIOCHE_CARTE) {
                System.out.println("Joueur " + this.jeu.getJoueurCourant() + " recupere :");
                System.out.println(((String[]) evenement.getObject()).toString());
            }
        }
        
    }

    
}
