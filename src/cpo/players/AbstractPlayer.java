/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.players;

import cpo.games.Card;
import cpo.games.Player;
import java.util.List;

/**
 *
 * @author scalpa
 */
public abstract class AbstractPlayer implements Player {
    
    protected final List<Card> cards_;

    public AbstractPlayer(List<Card> cards) {
        this.cards_ = cards;
    }
    
    @Override
    public final Card[] checkHand() {
        Card[] hand = new Card[this.cards_.size()];
        for (int i = 0 ; i < hand.length ; ++i ) {
            hand[i] = this.cards_.get(i).clone();
        }
        return hand;
    }
    
    
}
