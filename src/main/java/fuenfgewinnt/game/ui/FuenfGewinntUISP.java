package fuenfgewinnt.game.ui;

import fuenfgewinnt.game.Akt_xy;
import fuenfgewinnt.game.FinalsVars;
import fuenfgewinnt.game.FuenfGewinntSP;
import fuenfgewinnt.game.FuenfGewinntThr;
import fuenfgewinnt.game.Settings;

/**
 *
 * @author Jotschi
 */
public class FuenfGewinntUISP extends FuenfGewinntParentUI implements FinalsVars {

    private FuenfGewinntThr thr;

    /** Creates new form FuenfGewinntSpiedUI*/
    public FuenfGewinntUISP(Settings set) {
        //Einstellungen (Dimension) setzen
        this.set = set;
        dim = set.getDimension();

        initSpielfeld();
        setFrameMiddle();

        //Verbindungsobjekt anlegen
        akt = new Akt_xy();

        //Thread anlegen
        thr = new FuenfGewinntSP(set, akt, this);

        thr.setName("RunningLoop");
        thr.start();
    }

    protected void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {
        if (thr.isAlive()) {
            ((FuenfGewinntSP) thr).resign();
        }
        this.exit();
    }

    protected void jButtonAgainActionPerformed(java.awt.event.ActionEvent evt) {
        this.startAgain();
    }

    public void showButtonAgain() {
        this.jButtonAgain.setVisible(true);
    }

    private void startAgain() {
        jButtonAgain.setVisible(false);  //Verstecke Nochmal Button
        for (int j = 0; j < dim; j++) {  //Lösche Spielfeld
            for (int i = 0; i < dim; i++) {
                this.setSelected(i, j, NONE);
            }
        }
        this.setButtonText("Aufgeben");

        //Thread anlegen
        thr = new FuenfGewinntSP(set, akt, this);

        thr.setName("RunningLoop");
        thr.start();
    }

    public void exit() {
        this.setVisible(false);
        new StartingFrame(set).setVisible(true);
    }

    @Override
    protected void buttonClicked(int x, int y) {
        synchronized (akt) {
            akt.akt_x = x;
            akt.akt_y = y;
            akt.notifyAll();
        }
    }

    @Override
    protected void shiftClicked(int x, int y) {
        thr.shiftClicked(x, y);
    }
}
