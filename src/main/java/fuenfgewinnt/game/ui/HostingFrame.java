/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HostingFrame.java
 *
 * Created on 09.04.2010, 23:40:02
 */
package fuenfgewinnt.game.ui;

import fuenfgewinnt.game.Server;
import fuenfgewinnt.game.SetSocketAble;
import fuenfgewinnt.game.Settings;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jotschi
 */
public class HostingFrame extends javax.swing.JFrame implements SetSocketAble {
    private Settings set;

    private Socket skt;
    private Server ser;

    /** Creates new form HostingFrame */
    public HostingFrame(Settings set) {
        initComponents();
        
        this.set = set;
        try {
            this.jLabelHostIP.setText(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException ex) {
            this.jLabelHostIP.setText("Keine IP vorhanden!");
            Logger.getLogger(HostingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();  //Fenster in die Mitte setzten
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
        this.setLocation((d.width - getSize().width) / 2, (d.height - getSize().height) / 2);

        //Server Thread starten
        ser = new Server(this);
        ser.setName("Server");
        ser.start();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReturn = new javax.swing.JButton();
        jLabelHeadline = new javax.swing.JLabel();
        jLabelHostIP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("5 Gewinnt");

        jButtonReturn.setText("Zurück");
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        jLabelHeadline.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabelHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHeadline.setText("Warte auf Mitspieler ...");

        jLabelHostIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHostIP.setText("IP:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHeadline, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addComponent(jButtonReturn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addComponent(jLabelHostIP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHeadline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelHostIP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButtonReturn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        this.back();
    }//GEN-LAST:event_jButtonReturnActionPerformed

   
    public void setSocket(Socket s) {
        if (s != null) {
            try {
                skt = s;
                this.setVisible(false);
                new FuenfGewinntUIMP(set, true, skt).setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Verbindung abgebrochen!");
                Logger.getLogger(HostingFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Fehler beim Verbinden! (Port belegt)");
            this.back();
        }
    }

    private void back() {
        ser.exit();
        new StartingFrame(set).setVisible(true);
        this.setVisible(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabelHostIP;
    // End of variables declaration//GEN-END:variables
}
