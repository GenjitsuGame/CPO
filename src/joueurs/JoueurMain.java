/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueurs;

import java.util.Random;
import jeux.Carte;

/**
 *
 * @author scalpa
 */
public class JoueurMain extends JoueurUnPaquet {

    /**
     * Recupere une carte aleatoire de notre deck
     * @return une carte de notre liste de cartes
     */
    @Override
    public Carte getCarte() {
        Random r = new Random();
        int indice = r.nextInt(cartes.size() - 1);
        return cartes.remove(indice);
    }

    @Override
    public int getNbCartesDeck() {
        throw new UnsupportedOperationException("Le type de joueur " + this.getClass().getSimpleName() + "ne possède pas de deck");
    }
    
    
}
