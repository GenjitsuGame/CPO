/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.games;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author scalpa
 */
public class BatailleGame extends AbstractGame {

    protected List<Player> players_;

    public BatailleGame(File config, File cards) {
        super( config, cards);

    }

    @Override
    public void innit(Map<String, String[]> options) {
        Map<String, String[]> playerOptions = new HashMap<>();
        for (String option : this.config_.getPlayerOptions()) {
            if (options.containsKey(option)) {
                playerOptions.put(option, options.get(option));
            }
        }
        this.players_ = innitPlayers(playerOptions);
        distributeCards();
    }

    protected void distributeCards() {
        Collections.shuffle(this.cards_);

        int nbCardsPerPlayer = this.cards_.size() / this.options_.getNbPlayers();
        for (Player player : this.players_) {
            player.giveCards(pickCards(nbCardsPerPlayer));
        }
    }

    @Override
    public void play() {
        List<Card> played;
        int bataille;
        int indP1;
        int indP2;
        while (!finished()) {
            bataille = 0;
            indP1 = 0;
            indP2 = 1;
            played = new LinkedList<>();
            while (bataille == 0) {
             //   players_.
            }
            if (bataille < 0) {
                //this.player2_.giveCards(played);
            } else {
                //this.player1_.giveCards(played);
            }
            played.clear();
        }

    }

    @Override
    public boolean finished() {
      /**  if (this.player1_.getNbCardsInDeck() == this.config_.getNbCards()
                || this.player2_.getNbCardsInDeck() == 0) {
            addWinner(this.player1_);
            addLoser(this.player2_);
            return true;
        } else if (this.player1_.getNbCardsInDeck() == 0
                || this.player2_.getNbCardsInDeck() == this.config_.getNbCards()) {
            addWinner(this.player2_);
            addLoser(this.player2_);
            return true;
        } **/
        return false;
    }

    @Override
    public boolean handleInput(String input) {
        return true;
    }

}
