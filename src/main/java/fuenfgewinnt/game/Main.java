/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fuenfgewinnt.game;

import fuenfgewinnt.game.ui.StartingFrame;


/**
 *
 * @author Jotschi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StartingFrame(new Settings("settings.txt")).setVisible(true);
    }
}
