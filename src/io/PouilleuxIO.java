/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import evenements.EvenementJeu;

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
    }

    @Override
    protected void traiteEvenement(EvenementJeu evenement) {
        switchEvenementID(evenement.getID());
    }

    
}
