package fuenfgewinnt.game.ui;

import fuenfgewinnt.game.AgainThread;
import fuenfgewinnt.game.Akt_xy;
import fuenfgewinnt.game.FinalsVars;
import fuenfgewinnt.game.FuenfGewinntMP;
import fuenfgewinnt.game.Settings;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jotschi
 */
public class FuenfGewinntUIMP extends FuenfGewinntParentUI implements FinalsVars {

    private Thread thr, agth;
    private Socket skt;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    /** Creates new form FuenfGewinntSpiedUI */
    public FuenfGewinntUIMP(Settings set, boolean server, Socket skt) throws IOException {
        this.skt = skt;

        //Öffne Streams
        oos = new ObjectOutputStream(skt.getOutputStream());
        ois = new ObjectInputStream(skt.getInputStream());

        //Einstellungen (Dimension) setzen
        if (server) {  // Server
            this.set = set;
            oos.writeObject(set);
            oos.flush();
        } else {  //Client
            try {
                this.set = (Settings) ois.readObject();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Fehler beim Synchronisieren! (Gleiche Version?)");
                this.exit();
                Logger.getLogger(FuenfGewinntUIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dim = this.set.getDimension();

        initSpielfeld();
        setFrameMiddle();

        //Verbindungsobjekt anlegen
        akt = new Akt_xy();

        //Thread anlegen
        if (server) {
            thr = new FuenfGewinntMP(this.set, oos, ois, akt, true, this);
        } else {
            thr = new FuenfGewinntMP(this.set, oos, ois, akt, false, this);
        }

        thr.setName("RunningLoop");
        thr.start();
    }

    protected void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {
        if (thr.isAlive()) {
            ((FuenfGewinntMP) thr).resign();
        }
        this.exit();
    }

    protected void jButtonAgainActionPerformed(java.awt.event.ActionEvent evt) {
        ((AgainThread) agth).exit(); //Again-Thread beenden
        try {
            Thread.sleep(51);
        } catch (InterruptedException ex) {
            Logger.getLogger(FuenfGewinntUIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            oos.writeBoolean(true);  //Revanche aufforderung schicken
            oos.flush();
            if (ois.readBoolean()) {
                this.startAgain(false);  //false = Spiele Blau im nächsten Spiel
            } else {
                JOptionPane.showMessageDialog(this, "Der Mitspieler hat abgelehnt!");
                this.exit();
            }
        } catch (IOException ex) {
            Logger.getLogger(FuenfGewinntUIMP.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Verbindung abgebrochen!");
            this.exit();
        }
    }

    //wenn Revanche gefordert wird
    public void setAgain(boolean again) {
        int input;
        Object[] options = {"Ja", "Nein"};

        if (again) {
            input = JOptionPane.showOptionDialog(this,
                    "Euer Mitspieler fordert eine Revanche!",
                    "Nochmal?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[0]); //default button title
            try {
                if (input == 0) { //nochmal
                    oos.writeBoolean(true);
                    System.out.println("Sende True");
                    oos.flush();
                    this.startAgain(true);
                } else { //nicht nochmal
                    oos.writeBoolean(false);
                    oos.flush();
                    this.exit();
                }
            } catch (IOException ex) {
                Logger.getLogger(FuenfGewinntUIMP.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Verbindung abgebrochen!");
                this.exit();
            }
        }
    }

    private void startAgain(boolean begin) {
        jButtonAgain.setVisible(false);  //Verstecke Nochmal Button
        for (int j = 0; j < dim; j++) {  //Lösche Spielfeld
            for (int i = 0; i < dim; i++) {
                this.setSelected(i, j, NONE);
            }
        }
        this.setButtonText("Aufgeben");

        //Thread anlegen
        if (begin) {
            thr = new FuenfGewinntMP(set, oos, ois, akt, true, this);
        } else {
            thr = new FuenfGewinntMP(set, oos, ois, akt, false, this);
        }
        thr.setName("RunningLoop");
        thr.start();
    }

    public void exit() {
        try {
            if (agth != null) {
                if (agth.isAlive()) {
                    ((AgainThread) agth).exit(); //Lausch-Thread beenden
                }
            }
            oos.close();
            ois.close();
            skt.close();
        } catch (IOException ex) {
            Logger.getLogger(FuenfGewinntUIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        //nichts
    }

    @Override
    //startet AgainThread nachdem ein Spiel vorbei ist, blendet den Nochmal Button ein
    public void showButtonAgain() {
        jButtonAgain.setVisible(true);
        agth = new AgainThread(ois, this);
        agth.setName("WaitforAgain");
        agth.start();
    }
}
