/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.cards;

import cpo.games.Card;

/**
 *
 * @author scalpa
 */
public abstract class AbstractCard implements Card {

    @Override
    public final int compareTo(Object o) {
        try {
            AbstractCard card = (AbstractCard) o;
            return compareTo(card);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Les cartes comparées sont différentes :\n"
            + this.getClass().getName() + " et " + o.getClass().getName());
        }
    }

    protected abstract int compareTo(AbstractCard c);
    @Override
    public abstract AbstractCard clone();
}
