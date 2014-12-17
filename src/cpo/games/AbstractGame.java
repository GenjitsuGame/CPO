/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.games;

import cpo.application.Game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author scalpa
 */
public abstract class AbstractGame implements Game {

    protected class Options {

        private int nbPlayers_;

        public int getNbPlayers() {
            return this.nbPlayers_;
        }

        public void setNbPlayers(int nbJoueurs) {
            this.nbPlayers_ = nbJoueurs;
        }

    }
   // private final Class<? extends Card> cardClass_;
    protected List<String> inputs_;
    protected List<Card> cards_;
    protected final Config config_;
    protected Options options_;
    protected List<Player> winners_;
    protected List<Player> losers_;

    public AbstractGame(File config, File cards) {
        this.cards_ = loadDeck(cards);
        this.config_ = loadConfig(config);
    }

    /**
     * Charge un deck à partir d'un fichier
     *
     * @param file
     */
    private List<Card> loadDeck(File file) {
     /**   try {
            List<Card> deck = new LinkedList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            String cardType = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parameters = line.split("\\s+");
                deck.add(cardClass_.get
            }
            return deck;
        } catch (IOException e) {
            throw new IllegalArgumentException("Le fichier ne respecte pas le format.", e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("Le deck n'a pas pu etre crée à partir du fichier", e);
        }**/
        return null;
    } 

    private Config loadConfig(File file) {
        return new Config() {

            @Override
            public int getMaximumPlayer() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<String> getPlayerOptions() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getPlayerTypeOption() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getNbCards() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    protected List<Player> innitPlayers(Map<String, String[]> playerOptions) {
        String[] playerType = playerOptions.get(config_.getPlayerTypeOption());
        List<Player> players = new ArrayList<>(playerType.length);
        for (int i = 0; i < playerType.length; ++i) {
            Map<String, String> options = new HashMap<>();
            for (Map.Entry<String, String[]> entrySet : playerOptions.entrySet()) {
                String key = entrySet.getKey();
                String[] value = entrySet.getValue();

                options.put(key, value[i]);
            }
            players.add(loadNewPlayer(playerType[i], options));
        }
        return players;
    }

    protected Player loadNewPlayer(String playerType, Map<String, String> playerOptions) {
        return  null;//this.factory_.getPlayerFactory_().getPlayer(playerType, playerOptions);
    }

    protected List<Card> pickCards(int ammount) {
        if (this.cards_ == null) {
            throw new IllegalStateException("Le deck du jeu doit être initialisé");
        } else if (this.cards_.isEmpty()) {
            throw new IllegalStateException("Le deck est vide");
        } else {
            List<Card> ret = new LinkedList<>();
            int nbCards;
            if (ammount > this.cards_.size()) {
                nbCards = this.cards_.size();
            } else {
                nbCards = ammount;
            }
            for (int i = 0; i < nbCards; ++i) {
                ret.add(this.cards_.remove(0));
            }
            return ret;
        }
    }

    @Override
    public String[] getWinners() {
        return playersToNameArray(this.winners_);
    }

    @Override
    public String[] getLosers() {
        return playersToNameArray(this.losers_);
    }
    
    private String[] playersToNameArray(List<Player> players) {
        String[] names = new String[players.size()];
        for (int i = 0; i < players.size(); ++i) {
            names[i] = players.get(i).getName();
        }
        return names;
    }
    
    protected void addWinner(Player winner) {
        this.winners_.add(winner);
    }

    protected void addLoser(Player loser) {
        this.losers_.add(loser);
    }

}
