/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.application;

import java.util.Map;

/**
 *
 * @author scalpa
 */
public interface Game {
    
    void innit(Map<String, String[]> options);
    
    void play();
    
    boolean finished();
    
    String[] getWinners();
    
    String[] getLosers();
    
    boolean handleInput(String input);
}
