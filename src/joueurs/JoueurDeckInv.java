/*
 * CPO - Cartes
 */
package joueurs;

import jeux.Carte;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrien
 */
/**
 *
 * Joueur classique
 */
public class JoueurDeckInv extends JoueurUnPaquet {

    @Override
    public int getNbCartesMain() {
        throw new UnsupportedOperationException("Le type de joueur "+ this.getClass().getSimpleName() + "ne possède pas de main"); 
    }

    @Override
    public Carte consulterCarte(int i) {
        throw new UnsupportedOperationException("Impossible de consulter une carte d'un joueur ayant un deck caché.");
    }

    @Override
    public List<Carte> getMain() {
        throw new UnsupportedOperationException("Le type de joueur "+ this.getClass().getSimpleName() + "ne possède pas de main");
    }
    
}
