/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.games;

import java.util.List;


/**
 *
 * @author scalpa
 */
public interface Player {
    
    String getName();
    
    Card playCardOnTop();
    
    Card playCard(Card card);
    
    List<Card> playCards(List<Card> cards);
    
    void giveCards(List<Card> cards);
    
    Card[] checkHand();
    
    int getNbCardsInDeck();
    
    int getNbCardsInHand();
    
}
