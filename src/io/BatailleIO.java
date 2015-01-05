/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Ce joueur ne peut pas jouer.");
    }

    @Override
    protected void finTour() {
        System.out.println(jeu.cartesJoueesCeTour());
        if (!jeu.getGagnantTour().isEmpty()) {
            System.out.println("Le joueur  " + jeu.getGagnantTour().get(0) + " remporte : ");
            List<String> cartesRemportees = new ArrayList<>();
            if (!jeu.getPli().isEmpty()) {
                cartesRemportees.addAll(jeu.getPli());
            }
            for (List<String> cartes : jeu.cartesJoueesCeTour()) {
                cartesRemportees.addAll(cartes);
            }
            System.out.println(cartesRemportees);
        }
        System.out.println("TOUR FINI");
        this.jeu.reprendre();
    }

}
