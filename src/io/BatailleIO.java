/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

/**
 *
 * @author scalpa
 */
public class BatailleIO extends SynchronizedIOImpl {

    public BatailleIO(Jeu jeu) {
        super(jeu);
    }

    @Override
    protected void coupIllegal() {
        System.out.println("Ce joueur a deja joue.");
    }
    
    @Override
    protected void finTour() {
        System.out.println(jeu.cartesJoueesCeTour());
        super.finTour();
    }
    
}
