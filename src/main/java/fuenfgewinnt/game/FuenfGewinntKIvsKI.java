/*
    Thread für KIvsKI Spiel.
 */
package fuenfgewinnt.game;

import fuenfgewinnt.game.ui.FuenfGewinntUIKIvsKI;
import fuenfgewinnt.game.ui.DebugFrame;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Jotschi
 */
public class FuenfGewinntKIvsKI extends FuenfGewinntThr {
    //Variablen
    private int automs = 500;
    private boolean auto = false;
    private SettingsKI ki1, ki2;
    private PointsField points1, points2;

    public FuenfGewinntKIvsKI(Settings set, FuenfGewinntUIKIvsKI ui) {
        super(set, ui);
        ki1 = set.getKi1();
        ki2 = set.getKi2();

        points1 = new PointsField(dim);
        points2 = new PointsField(dim);

        if(DEBUG) {
            df = new DebugFrame();
            df.setVisible(true);
            message = new String[dim][dim];
        }
    }

    protected void exit() {
        running = false;
        if(DEBUG) {
            df.setVisible(false);
        }
    }



    protected void runLoop() {
        while (running) {
            this.kiDraw(ki1, points1, "KI_1", p1color);
            if (!running) {
                break;
            }
            ui.setHeadline("Warten auf Zug des Computers: Ki_2!");
            synchronized (this) {
                try {
                    if(auto) {
                        this.wait(automs);
                    }else{
                        this.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(FuenfGewinntSP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.kiDraw(ki2, points2, "KI_2", p2color);
            if (!running) {
                break;
            }
            ui.setHeadline("Warten auf Zug des Computers: Ki_1!");
            synchronized (this) {
                try {
                    if(auto) {
                        this.wait(automs);
                    }else{
                        this.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(FuenfGewinntSP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    protected void winner(int color) {
        if(color == p1color) {
            ui.setHeadline("KI_1 hat gewonnen!");
        }else {
            ui.setHeadline("KI_2 hat gewonnen!");
        }
        ui.buttonClickable(true);
        ui.setButtonText("Schließen");
        ui.showButtonAgain();
        this.exit();
    }

    public void resign() {
        this.exit();
    }

    public void setAuto(boolean auto, int automs) {
        this.auto = auto;
        this.automs = automs;
    }
}
