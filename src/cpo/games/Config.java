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
public interface Config {
    
    int getMaximumPlayer();
    
    int getNbCards();
    
    List<String> getPlayerOptions();
    
    String getPlayerTypeOption();
}
