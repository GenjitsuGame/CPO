/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpo.games;

import java.util.List;
import java.util.Map;

/**
 *
 * @author scalpa
 */
public interface Card extends Comparable, Cloneable {
 
    Map<String, String> getValue();

    Card clone();
    
}
