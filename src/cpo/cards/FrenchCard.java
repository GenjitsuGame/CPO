/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.cards;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author scalpa
 */
public class FrenchCard extends AbstractCard {

    protected enum Suit {

        HEARTS,
        SPADES,
        CLUBS,
        DIAMONDS
    }

    private final Suit suit_;
    private final int value_;
    private final int priority_;

    public FrenchCard(Suit suit, int value, int priority) {
        this.suit_ = suit;
        this.value_ = value;
        this.priority_ = priority;
    }

    protected int getPriority() {
        return this.priority_;
    }

    @Override
    protected int compareTo(AbstractCard c) {
        try {
            FrenchCard card = (FrenchCard) c;
            return compareTo(this);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Les cartes comparées sont différentes :\n"
                    + this.getClass().getName() + " et " + c.getClass().getName());
        }

    }

    protected int compareTo(FrenchCard c) {
        if (c.getPriority() == this.getPriority()) {
            return 0;
        } else if (c.getPriority() < this.getPriority()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public Map<String, String> getValue() {
        HashMap values = new HashMap(2);
        values.put("suit", this.suit_.toString());
        values.put("value", Integer.toString(this.value_));
        return values;
    }

    @Override
    public FrenchCard clone() {
        return new FrenchCard(this.suit_, this.value_, this.priority_);
    }
}
