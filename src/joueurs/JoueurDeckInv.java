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
public class JoueurDeckInv extends AbstractJoueur {

    protected List<Carte> deck;
    
    public JoueurDeckInv() {
        this.deck = new LinkedList<>();
    }

    @Override
    public Carte getCarte() {
        return deck.remove(0);
    }

    @Override
    public Carte getCarte(int i) {
        try {
            return deck.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("L'indice " + i + " ne reference aucune carte de la main de joueur.", e);
        }
    }

    @Override
    public void ajouterCarte(Carte carte) {
        deck.add(carte);
    }

    @Override
    public int getNbCartesDeck() {
        return this.deck.size();
    }

    @Override
    public boolean sansCarte() {
        return this.deck.isEmpty();
    }

    @Override
    public int getNbCartesMain() {
        throw new UnsupportedOperationException("Le type de joueur "+ this.getClass().getSimpleName() + "ne possède pas de main"); 
    }
}
