/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StartingFrame.java
 *
 * Created on 09.04.2010, 23:24:57
 */

package fuenfgewinnt.game.ui;

import fuenfgewinnt.game.FinalsVars;
import fuenfgewinnt.game.Settings;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Jotschi
 */
public class StartingFrame extends javax.swing.JFrame implements FinalsVars{
    private Settings set;
    /** Creates new form StartingFrame */
    public StartingFrame(Settings set) {
        initComponents();
        this.set = set;
        this.jLabelVersion.setText("Version " + VERSION);
        
        //gegenfalls Fehler ausgeben (Settings File Problem)
        String s = set.getError();
        if(!s.equals("")) {
            JOptionPane.showMessageDialog(this, "Achtung:" + s);
        }

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();  //Fenster in die Mitte setzten
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
        this.setLocation((d.width - getSize().width) / 2, (d.height - getSize().height) / 2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButtonHost = new javax.swing.JButton();
        jButtonJoin = new javax.swing.JButton();
        jButtonSingel = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabelVersion = new javax.swing.JLabel();
        jButtonSettings = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1KIvsKI = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("5 Gewinnt ");
        setMinimumSize(new java.awt.Dimension(300, 300));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jButtonHost.setText("Einleiten");
        jButtonHost.setPreferredSize(new java.awt.Dimension(129, 30));
        jButtonHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHostActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(jButtonHost, gridBagConstraints);

        jButtonJoin.setText("Beitreten");
        jButtonJoin.setPreferredSize(new java.awt.Dimension(129, 30));
        jButtonJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJoinActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jButtonJoin, gridBagConstraints);

        jButtonSingel.setText("Einzelspieler");
        jButtonSingel.setPreferredSize(new java.awt.Dimension(129, 30));
        jButtonSingel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSingelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        getContentPane().add(jButtonSingel, gridBagConstraints);

        jButtonExit.setText("Verlassen");
        jButtonExit.setPreferredSize(new java.awt.Dimension(129, 30));
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        getContentPane().add(jButtonExit, gridBagConstraints);

        jLabelVersion.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabelVersion.setText("Version");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 0, 0);
        getContentPane().add(jLabelVersion, gridBagConstraints);

        jButtonSettings.setText("Einstellungen");
        jButtonSettings.setPreferredSize(new java.awt.Dimension(129, 30));
        jButtonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSettingsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        getContentPane().add(jButtonSettings, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 9));
        jLabel1.setText("© Johannes Wawerda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        getContentPane().add(jLabel1, gridBagConstraints);

        jButton1KIvsKI.setText("Ki vs. Ki");
        jButton1KIvsKI.setPreferredSize(new java.awt.Dimension(129, 30));
        jButton1KIvsKI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1KIvsKIActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        getContentPane().add(jButton1KIvsKI, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHostActionPerformed
        new HostingFrame(set).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonHostActionPerformed

    private void jButtonJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJoinActionPerformed
        new JoiningFrame(set).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonJoinActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonSingelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSingelActionPerformed
        new FuenfGewinntUISP(set).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSingelActionPerformed

    private void jButtonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSettingsActionPerformed
        new SettingsFrame(set).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSettingsActionPerformed

    private void jButton1KIvsKIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1KIvsKIActionPerformed
        new FuenfGewinntUIKIvsKI(set).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1KIvsKIActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1KIvsKI;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonHost;
    private javax.swing.JButton jButtonJoin;
    private javax.swing.JButton jButtonSettings;
    private javax.swing.JButton jButtonSingel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelVersion;
    // End of variables declaration//GEN-END:variables

}
