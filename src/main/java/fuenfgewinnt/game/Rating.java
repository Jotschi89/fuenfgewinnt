/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fuenfgewinnt.game;

/**
 *
 * @author Jotschi
 */
public class Rating {
    public int anz,  //Anzahl der eigenen Elemente in einer möglichen Reihe
               offen, // Anzahl der offenen Enden der Reihe
               op_anz;  //Anzahl der gegnerischen Elemente in einer möglichen Reihe
    
    public Rating(int anz, int op_anz, int offen) {
        this.anz = anz;
        this.offen = offen;
        this.op_anz = op_anz;
    }
}
