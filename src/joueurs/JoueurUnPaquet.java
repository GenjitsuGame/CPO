/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueurs;

import java.util.LinkedList;
import java.util.List;
import jeux.Carte;

/**
 *
 * @author scalpa
 */
public abstract class JoueurUnPaquet extends AbstractJoueur {

    protected final List<Carte> cartes;

    public JoueurUnPaquet() {
        this.cartes = new LinkedList<>();
    }

    @Override
    public Carte getCarte(int i) {
        try {
            return cartes.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("L'indice " + i + " ne reference aucune carte.", e);
        }
    }

    @Override
    public void ajouterCarte(Carte carte) {
        cartes.add(carte);
    }

    @Override
    public int getNbCartesDeck() {
        return this.cartes.size();
    }

    @Override
    public boolean sansCarte() {
        return this.cartes.isEmpty();
    }

    @Override
    public Carte getCarte() {
        return this.cartes.remove(0);
    }

    @Override
    public void ajouterCartes(List<Carte> cartes) {
        this.cartes.addAll(cartes);
    }

    @Override
    public int getNbCartesMain() {
        return this.cartes.size();
    }

    @Override
    public Carte consulterCarte(int i) {
        return this.cartes.get(i);
    }

    @Override
    public List<Carte> getMain() {
        return this.cartes;
    }

}
