/*
    Thread für Mehrspielerspiel.
 */
package fuenfgewinnt.game;

import fuenfgewinnt.game.ui.FuenfGewinntParentUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jotschi
 */
public class FuenfGewinntMP extends FuenfGewinntThr {

    boolean begin;   //true wenn Thread Server ist
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean timer;
    private Timer timerThr;
    private Settings set;
    protected Akt_xy akt;  //syncronisations Objekt, enthält Nachrichten vom ui

    public FuenfGewinntMP(Settings set, ObjectOutputStream oos, ObjectInputStream ois, Akt_xy akt, boolean begin, FuenfGewinntParentUI ui) {
        super(set, ui);
        this.akt = akt;
        this.ois = ois;
        this.oos = oos;
        this.begin = begin;
        this.timer = set.isTimer();
        this.set = set;
    }

    protected void runLoop() {
        if(timer) {
            timerThr = new Timer(set, ui, this, begin);
            timerThr.setName("TimerThr");
            timerThr.start();
        }

        try {
            if (begin) {
                this.myDraw();
            }
            while (running) {
                this.opDraw();
                if (!running) {
                    break;
                }
                this.myDraw();
            }
        } catch (IOException ex) {
            Logger.getLogger(FuenfGewinntMP.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(ui, "Verbindung abgebrochen!");
            this.exit();
            ui.exit();
        }
    }

    private void myDraw() throws IOException {
        //Mein Zug
        boolean notCorrect;
        ui.buttonClickable(true);
        ui.setHeadline("Ihr seid am Zug!");
        if(timer) {
            timerThr.setmydraw(true);
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
                    if(!running) {
                        return;
                    }
                }
            }
            if (map[akt_x][akt_y] == NONE) {
                map[akt_x][akt_y] = this.p1color;  //map aktualisieren
                ui.setSelected(last_x, last_y, p2color);
                ui.setSelected_pale(akt_x, akt_y, p1color);
                notCorrect = false;
            } else {
                notCorrect = true;
            }
        } while (notCorrect);

        oos.writeInt(akt_x); //übergebe Zug
        oos.writeInt(akt_y);
        oos.flush();
        if (this.isFinished(akt_x, akt_y)) { // gewonnen?
            winner(p1color);
        }
    }

    private void opDraw() throws IOException {
        //Zug des Gegners
        ui.buttonClickable(false);
        ui.setHeadline("Warten auf Zug des Mitspielers!");
        if(timer) {
            timerThr.setmydraw(false);
        }

        // Letztes ausgewähltes Feld merken
        last_x = akt_x;
        last_y = akt_y;

        akt_x = ois.readInt();  //auf Zug warten
        if (akt_x == RESIGN) {
            ui.setHeadline("Sie haben gewonnen!");
            ui.buttonClickable(true);
            ui.setButtonText("Schließen");
            this.exit();
            return;
        }
        if (akt_x == TIMELOSE) {
            winner(p1color);
            System.out.println("running" + running);
            return;
        }
        akt_y = ois.readInt();
        map[akt_x][akt_y] = p2color;  //map aktualisieren
        ui.setSelected(last_x, last_y, p1color);
        ui.setSelected_pale(akt_x, akt_y, p2color);
        if (this.isFinished(akt_x, akt_y)) { // verloren?
            winner(p2color);
        }
    }

 
    public void exit() {
        running = false;
        if(timer) {
            timerThr.exit();
        }
    }

    public void resign() {
        try {
            oos.writeInt(RESIGN);
            oos.flush();
            this.exit();
        } catch (IOException ex) {
            Logger.getLogger(FuenfGewinntMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void timegone() {
        System.out.println("timegone");
        try {
            oos.writeInt(TIMELOSE);
            oos.flush();
            winner(p2color);
            this.interrupt();
        } catch (IOException ex) {
            Logger.getLogger(FuenfGewinntMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
