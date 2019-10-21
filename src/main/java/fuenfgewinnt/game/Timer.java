/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.game;

import fuenfgewinnt.game.ui.FuenfGewinntParentUI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jotschi
 */
public class Timer extends Thread {

    private int mytime, optime;
    private int addtime;
    private boolean mydraw, running;
    FuenfGewinntParentUI ui;
    FuenfGewinntMP runThr;

    public Timer(Settings set, FuenfGewinntParentUI ui, FuenfGewinntMP runThr, boolean begin) {
        mytime = set.getStarttime();
        optime = set.getStarttime();
        addtime = set.getAddtime();
        this.ui = ui;
        this.runThr = runThr;
        this.mydraw = begin;
        running = true;
    }

    @Override
    public void run() {
        long t = System.currentTimeMillis(); //Vergleichszeit
        ui.setLeftCorner("" + mytime / 1000 + "," + mytime / 100 % 10);
        ui.setRightCorner("" + optime / 1000 + "," + optime / 100 % 10);
        while (running) {
            if(mydraw) {
                mytime = mytime + addtime;
            }
            while (mydraw) {  //eigener Zug
                if (!running) {
                    return;
                }
                mytime = mytime - (int) (System.currentTimeMillis() - t);
                t = System.currentTimeMillis(); //Vergleichszeit
                if (mytime <= 0) {  //Zeit abgelaufen
                    mytime = 0;
                    ui.setLeftCorner("" + mytime / 1000 + "," + mytime / 100 % 10);
                    runThr.timegone();
                    return;
                }
                ui.setLeftCorner("" + mytime / 1000 + "," + mytime / 100 % 10);
                sleep(100);
            }
            optime = optime + addtime;
            while (!mydraw) {  //gegnerischer Zug
                if (!running) {
                    return;
                }
                optime = optime - (int) (System.currentTimeMillis() - t);
                t = System.currentTimeMillis(); //Vergleichszeit
                if (optime <= 0) {
                    optime = 0;
                }
                ui.setRightCorner("" + optime / 1000 + "," + optime / 100 % 10);
                sleep(100);
            }
        }
    }

    public void setmydraw(boolean mydraw) {
        this.mydraw = mydraw;
    }

    public void exit() {
        running = false;
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
