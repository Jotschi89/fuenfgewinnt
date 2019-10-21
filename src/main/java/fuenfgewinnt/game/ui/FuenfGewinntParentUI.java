/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FuenfGewinntSpiedUI.java
 *
 * Created on 10.04.2010, 11:21:37
 */
package fuenfgewinnt.game.ui;

import fuenfgewinnt.game.Akt_xy;
import fuenfgewinnt.game.FinalsVars;
import fuenfgewinnt.game.Settings;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 *
 * @author Jotschi
 */
public abstract class FuenfGewinntParentUI extends javax.swing.JFrame implements FinalsVars {

    protected Settings set;
    protected JLabel[][] buttons;
    protected int dim;
    protected Akt_xy akt; //Verbindungsobjekt zum Thread

    /** Creates new form FuenfGewinntSpiedUI */
    public FuenfGewinntParentUI() {
        initComponents();
        jButtonAgain.setVisible(false);

        //ActionListener für die Buttons
        jButtonAgain.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgainActionPerformed(evt);
            }
        });
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });
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

        jPanelButtons = new javax.swing.JPanel();
        jButtonAgain = new javax.swing.JButton();
        jButtonReturn = new javax.swing.JButton();
        jPanelSpielfeld = new javax.swing.JPanel();
        jPanelHeadline = new javax.swing.JPanel();
        jLabelLeftCorner = new javax.swing.JLabel();
        jLabelHeadline = new javax.swing.JLabel();
        jLabelRightCorner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("5 Gewinnt");
        setResizable(false);

        jPanelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonAgain.setText("Nochmal");
        jPanelButtons.add(jButtonAgain);

        jButtonReturn.setText("Aufgeben");
        jPanelButtons.add(jButtonReturn);

        jPanelSpielfeld.setLayout(new java.awt.GridBagLayout());

        jPanelHeadline.setPreferredSize(new java.awt.Dimension(556, 80));

        jLabelLeftCorner.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabelHeadline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelRightCorner.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelRightCorner.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanelHeadlineLayout = new javax.swing.GroupLayout(jPanelHeadline);
        jPanelHeadline.setLayout(jPanelHeadlineLayout);
        jPanelHeadlineLayout.setHorizontalGroup(
            jPanelHeadlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeadlineLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(jPanelHeadlineLayout.createSequentialGroup()
                .addComponent(jLabelLeftCorner)
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeadlineLayout.createSequentialGroup()
                .addContainerGap(556, Short.MAX_VALUE)
                .addComponent(jLabelRightCorner))
        );
        jPanelHeadlineLayout.setVerticalGroup(
            jPanelHeadlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeadlineLayout.createSequentialGroup()
                .addGroup(jPanelHeadlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLeftCorner, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabelRightCorner, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelSpielfeld, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelHeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelSpielfeld, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected abstract void jButtonAgainActionPerformed(ActionEvent evt);

    protected abstract void jButtonReturnActionPerformed(ActionEvent evt);

    protected abstract void buttonClicked(int x, int y);

    protected abstract void shiftClicked(int x, int y);

    public abstract void showButtonAgain();

    public abstract void exit();

    /* player
     * red = 1 
     * blue = 2
     * none = else */
    public void setSelected(int x, int y, int player) {
        if (x < dim && y < dim && x >= 0 && y >= 0) {
            if (player == 1) {
                buttons[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Button_red.gif")));
            } else if (player == 2) {
                buttons[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Button_blue.gif")));
            } else {
                buttons[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Button.gif")));
            }
        }
    }

    public void setSelected_pale(int x, int y, int player) {
        if (x < dim && y < dim && x >= 0 && y >= 0) {
            if (player == 1) {
                buttons[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Button_red_s.gif")));
            } else if (player == 2) {
                buttons[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Button_blue_s.gif")));
            } else {
                buttons[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Button.gif")));
            }
        }
    }

    public void buttonClickable(boolean clickable) {
        jButtonReturn.setEnabled(clickable);
    }

    public void setHighlighted(int x, int y, int color) {
        if (x < dim && y < dim && x >= 0 && y >= 0) {
            if (color == RED) {
                buttons[x][y].setIcon((new javax.swing.ImageIcon(getClass().getResource("/ui/Button_red_high.gif"))));
            }
            if (color == BLUE) {
                buttons[x][y].setIcon((new javax.swing.ImageIcon(getClass().getResource("/ui/Button_blue_high.gif"))));
            }
        }
    }

    public void setHeadline(String s) {
        this.jLabelHeadline.setText(s);
    }

    public void setLeftCorner(String s) {
        this.jLabelLeftCorner.setText(s);
    }
        
    public void setRightCorner(String s) {
        this.jLabelRightCorner.setText(s);
    }

    public void setButtonText(String s) {
        this.jButtonReturn.setText(s);
    }

    //Debug
    public void setFieldText(String s, int x, int y) {
        buttons[x][y].setToolTipText(s);
    }

    //Fenster in die Mitte setzten
    protected void setFrameMiddle() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    //erstelle Spielfeld
    protected void initSpielfeld() {
        JLabel b;
        java.awt.GridBagConstraints gridBagConstraints;

        buttons = new JLabel[dim][dim];
        for (int j = 0; j < dim; j++) {
            for (int i = 0; i < dim; i++) {
                final int x = i;
                final int y = j;
                b = new javax.swing.JLabel();
                b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Button.gif")));
                b.addMouseListener(new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (evt.isShiftDown()) {
                            shiftClicked(x, y);
                        } else {
                            buttonClicked(x, y);
                        }
                    }
                });
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridy = j;
                jPanelSpielfeld.add(b, gridBagConstraints);
                buttons[x][y] = b;
            }
        }
        this.setSize(dim * 30 + 100, dim * 30 + 150);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jButtonAgain;
    protected javax.swing.JButton jButtonReturn;
    protected javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabelLeftCorner;
    private javax.swing.JLabel jLabelRightCorner;
    protected javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelHeadline;
    protected javax.swing.JPanel jPanelSpielfeld;
    // End of variables declaration//GEN-END:variables
}