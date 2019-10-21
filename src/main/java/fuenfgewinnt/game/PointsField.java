/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.game;

/**
 *
 * @author Jotschi
 */
public class PointsField {

    public int points[][];
    public boolean first;  //Hilfsvariable für den FuenfGewinntThr

    public PointsField(int dim) {
        points = new int[dim][dim];
        first = true;

    }
}
