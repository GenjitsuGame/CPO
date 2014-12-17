/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.games;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author scalpa
 */
public abstract class Move {

    private final List<Card> from_;
    private final List<Card> to_;
    private boolean isExecuted_;

    public Move(List<Card> from, List<Card> to) {
        this.from_ = from;
        this.to_ = to;
        isExecuted_ = false;
    }

    public void execute() {
        if (!isExecuted_) {
            for (Card card : this.from_) {
                this.to_.add(card.clone());
            }
            isExecuted_ = true;
        }
    }

    public void undo() {
        if (isExecuted_) {
            this.to_.removeAll(this.from_);
        }
        isExecuted_ = false;
    }
    


}
