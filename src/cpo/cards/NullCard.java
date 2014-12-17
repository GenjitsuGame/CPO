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
public class NullCard extends AbstractCard {

    public NullCard() {
    }

    @Override
    protected int compareTo(AbstractCard c) {
        return 0;
    }

    @Override
    public Map<String, String> getValue() {
        Map<String, String> ret = new HashMap(1);
        ret.put("","");
        return ret;
    }
    
    @Override
    public NullCard clone() {
        return new NullCard();
    }
    
}
