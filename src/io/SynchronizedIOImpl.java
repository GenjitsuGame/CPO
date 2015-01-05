/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

/**
 *
 * @author scalpa
 * @param <T>
 */
public class SynchronizedIOImpl extends AbstractSynchronizedIO<Integer> {

    /**
     * Constructeur SynchronizedIOImpl
     * @param jeu 
     */
    public SynchronizedIOImpl(Jeu jeu) {
        super(jeu);
    }

    @Override
    protected void traiteEvenement(Integer evenement) {
        this.switchEvenementID(evenement);
    }


}
