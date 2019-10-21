package fuenfgewinnt.game.ui;

import fuenfgewinnt.game.FinalsVars;
import fuenfgewinnt.game.FuenfGewinntKIvsKI;
import fuenfgewinnt.game.FuenfGewinntThr;
import fuenfgewinnt.game.Settings;

/**
 *
 * @author Jotschi
 */
public class FuenfGewinntUIKIvsKI extends FuenfGewinntParentUI implements FinalsVars {

    private FuenfGewinntThr thr;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JCheckBox jCheckBoxAuto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldMs;

    /** Creates new form FuenfGewinntSpiedUI*/
    public FuenfGewinntUIKIvsKI(Settings set) {
        //Einstellungen (Dimension) setzen
        this.set = set;
        dim = set.getDimension();

        initSpielfeld();
        setFrameMiddle();

        initComponents();

        //Thread anlegen
        thr = new FuenfGewinntKIvsKI(set, this);

        thr.setName("RunningLoop");
        thr.start();
    }

    private void initComponents() {
        jButtonNext = new javax.swing.JButton();
        jCheckBoxAuto = new javax.swing.JCheckBox();
        jTextFieldMs = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jButtonReturn.setText("Schließen");

        jButtonNext.setText("Nächster Zug");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jCheckBoxAuto.setText("Automatisch");
        jCheckBoxAuto.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAutoActionPerformed(evt);
            }
        });
        jTextFieldMs.setText("500");
        jLabel1.setText("ms");
        jLabel2.setText("Alle");

        jPanelButtons.add(jLabel2, 1);
        jPanelButtons.add(jTextFieldMs, 2);
        jPanelButtons.add(jLabel1, 3);
        jPanelButtons.add(jCheckBoxAuto, 4);
        jPanelButtons.add(jButtonNext, 5);
    }

    protected void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {
        if (thr.isAlive()) {
            ((FuenfGewinntKIvsKI) thr).resign();
        }
        this.exit();
    }

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {
        synchronized (thr) {
            thr.notifyAll();
        }
    }

    private void jCheckBoxAutoActionPerformed(java.awt.event.ActionEvent evt) {
        int automs = 500;
        boolean auto;
        auto = this.jCheckBoxAuto.isSelected();
        if (auto) {
            try {
                automs = Integer.parseInt(this.jTextFieldMs.getText());
                if (automs <= 0) {
                    automs = 500;
                    this.jTextFieldMs.setText("500");
                }
            } catch (NumberFormatException e) {
                automs = 500;
                this.jTextFieldMs.setText("500");
            }
            this.jTextFieldMs.setEnabled(false);
            synchronized (thr) {
                thr.notifyAll();
            }
        } else {
            this.jTextFieldMs.setEnabled(true);
        }
        ((FuenfGewinntKIvsKI) thr).setAuto(auto, automs);
    }

    protected void jButtonAgainActionPerformed(java.awt.event.ActionEvent evt) {
        this.startAgain();
    }

    public void showButtonAgain() {
        this.jButtonAgain.setVisible(true);
        this.jButtonNext.setVisible(false);
        this.jCheckBoxAuto.setVisible(false);
        this.jLabel1.setVisible(false);
        this.jLabel2.setVisible(false);
        this.jTextFieldMs.setVisible(false);
    }

    private void startAgain() {
        jButtonAgain.setVisible(false);  //Verstecke Nochmal Button
        this.jButtonNext.setVisible(true);
        this.jCheckBoxAuto.setVisible(true);
        this.jLabel1.setVisible(true);
        this.jLabel2.setVisible(true);
        this.jTextFieldMs.setVisible(true);
        this.jCheckBoxAuto.setSelected(false);
        this.jTextFieldMs.setEnabled(true);
        for (int j = 0; j < dim; j++) {  //Lösche Spielfeld
            for (int i = 0; i < dim; i++) {
                this.setSelected(i, j, NONE);
            }
        }

        //Thread anlegen
        thr = new FuenfGewinntKIvsKI(set, this);

        thr.setName("RunningLoop");
        thr.start();
    }

    public void exit() {
        this.setVisible(false);
        new StartingFrame(set).setVisible(true);
    }

    protected void buttonClicked(int x, int y) {
        //nichts
    }

    @Override
    protected void shiftClicked(int x, int y) {
        thr.shiftClicked(x, y);
    }
}
