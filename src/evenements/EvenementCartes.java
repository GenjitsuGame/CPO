/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements;

/**
 *
 * @author scalpa
 */
public class EvenementCartes extends EvenementJeu<String> {

    /**
     * Constructeur EvenementCartes
     * @param o
     * @param idEvenement
     * @param object 
     */
    public EvenementCartes(Object o, int idEvenement, String... object) {
        super(o, idEvenement, object);
    }

    public static class ID {
    }


}
