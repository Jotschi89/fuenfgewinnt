/*
    Thread für Einzelspielerspiel.
 */
package fuenfgewinnt.game;

import fuenfgewinnt.game.ui.DebugFrame;
import fuenfgewinnt.game.ui.FuenfGewinntUISP;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jotschi
 */
public class FuenfGewinntSP extends FuenfGewinntThr {
    private Akt_xy akt;  //syncronisations Objekt, enthält Nachrichten vom ui
    private SettingsKI ki;
    private PointsField points;

    public FuenfGewinntSP(Settings set, Akt_xy akt, FuenfGewinntUISP ui) {
        super(set, ui);
        this.akt = akt;
        ki = set.getKi1();
        points = new PointsField(dim);

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
            this.myDraw();
            if (!running) {
                break;
            }
            this.kiDraw(ki, points, "Computer", p2color);
        }
    }

    private void myDraw() {
        //Mein Zug
        boolean notCorrect, full = true;
        ui.buttonClickable(true);
        ui.setHeadline("Ihr seid am Zug!");


        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (map[i][j] == NONE) {
                    full = false;
                }
            }
        }

        if(full) {
            unentschieden();
            return;
        }

        // Letztes ausgewähltes Feld merken
        last_x = akt_x;
        last_y = akt_y;
        
        //warte auf Tastendruck
        do {
            synchronized (akt) {
                try {
                    akt.wait();
                    akt_x = akt.akt_x;
                    akt_y = akt.akt_y;
                } catch (InterruptedException ex) {
                    Logger.getLogger(FuenfGewinntSP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (map[akt_x][akt_y] == NONE) {
                map[akt_x][akt_y] = p1color;  //map aktualisieren
                ui.setSelected(last_x, last_y, p2color);
                ui.setSelected_pale(akt_x, akt_y, p1color);
                notCorrect = false;
            } else {
                notCorrect = true;
            }
        } while (notCorrect);

        if (this.isFinished(akt_x, akt_y)) { // gewonnen?
            winner(p1color);
        }
    }

    public void resign() {
        this.exit();
    }
}
