/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.game;

import fuenfgewinnt.game.ui.FuenfGewinntUIMP;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jotschi
 */
public class AgainThread extends Thread {

    private ObjectInputStream ois;
    FuenfGewinntUIMP ui;
    boolean running = true;

    public AgainThread(ObjectInputStream ois, FuenfGewinntUIMP ui) {
        this.ois = ois;
        this.ui = ui;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(50);
                if(ois.available() != 0) {
                    ui.setAgain(ois.readBoolean());
                    System.out.println("Boolean readed");
                    running = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(AgainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void exit() {
        running = false;
    }
}
