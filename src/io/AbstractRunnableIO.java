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
public abstract class AbstractRunnableIO extends AbstractIO implements Runnable {

    private final Thread thread;

    public AbstractRunnableIO(Affichable jeu) {
        super(jeu);
        this.thread = new Thread(this);
    }

}
