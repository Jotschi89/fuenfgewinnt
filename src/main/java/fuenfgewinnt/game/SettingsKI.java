/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jotschi
 */
public class SettingsKI implements Serializable {
    //KI Setup

    private int self_0;
    private int self_1;
    private int self_2;
    private int self_3;
    private int self_4up;
    private int mul_self_open_0;
    private int mul_self_open_1;
    private int mul_self_open_2;
    private int op_0;
    private int op_1;
    private int op_2;
    private int op_3;
    private int op_4up;
    private int mul_op_open_0;
    private int mul_op_open_1;
    private int mul_op_open_2;
    private int mul_op_open_3;
    private int ki_blur;
    private int ki_accurcy_percent;
    // Error
    private StringBuffer err = new StringBuffer("");

    public SettingsKI(String file) {
        //Standartvalues
        self_0 = 1;
        self_1 = 2;
        self_2 = 3;
        self_3 = 15;
        self_4up = 1000;
        mul_self_open_0 = 0;
        mul_self_open_1 = 1;
        mul_self_open_2 = 2;
        op_0 = 1;
        op_1 = 2;
        op_2 = 3;
        op_3 = 10;
        op_4up = 500;
        mul_op_open_0 = 0;
        mul_op_open_1 = 1;
        mul_op_open_2 = 2;
        mul_op_open_3 = 3;
        ki_blur = 0;
        ki_accurcy_percent = 100;

        readSettings(file);
        writeSettings(file);
    }

    public SettingsKI(int vals[]) {
        self_0 = vals[0];
        self_1 = vals[1];
        self_2 = vals[2];
        self_3 = vals[3];
        self_4up = vals[4];
        mul_self_open_0 = vals[5];
        mul_self_open_1 = vals[6];
        mul_self_open_2 = vals[7];
        op_0 = vals[8];
        op_1 = vals[9];
        op_2 = vals[10];
        op_3 = vals[11];
        op_4up = vals[12];
        mul_op_open_0 = vals[13];
        mul_op_open_1 = vals[14];
        mul_op_open_2 = vals[15];
        mul_op_open_3 = vals[16];
        ki_blur = 0;
        ki_accurcy_percent = 100;
    }

    public void readSettings(String file) {
        RandomAccessFile raf;
        String s;
        try {
            raf = new RandomAccessFile(file, "r");
            while ((s = raf.readLine()) != null) {
                parseLine(s);
            }
            raf.close();
        } catch (FileNotFoundException ex) {
            err.append("\n'"+file+"' nicht gefunden! Wird angelegt...");
            System.out.println("KI Setting Datei nicht gefunden!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            err.append("\nFehler beim lese von '"+file+"' Standardwerte werden verwendet.");
            System.out.println("Fehler beim lese der KI File!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void writeSettings(String file) {
        File f = new File(file);
        f.delete();
        try {
            RandomAccessFile raf;
            raf = new RandomAccessFile(file, "rw");
            raf.writeBytes("SELF_0=" + getSelf_0() + "\r\n");
            raf.writeBytes("SELF_1=" + getSelf_1() + "\r\n");
            raf.writeBytes("SELF_2=" + getSelf_2() + "\r\n");
            raf.writeBytes("SELF_3=" + getSelf_3() + "\r\n");
            raf.writeBytes("SELF_4UP=" + getSelf_4up() + "\r\n");
            raf.writeBytes("MUL_SELF_OPEN_0=" + getMul_self_open_0() + "\r\n");
            raf.writeBytes("MUL_SELF_OPEN_1=" + getMul_self_open_1() + "\r\n");
            raf.writeBytes("MUL_SELF_OPEN_2=" + getMul_self_open_2() + "\r\n");
            raf.writeBytes("OP_0=" + getOp_0() + "\r\n");
            raf.writeBytes("OP_1=" + getOp_1() + "\r\n");
            raf.writeBytes("OP_2=" + getOp_2() + "\r\n");
            raf.writeBytes("OP_3=" + getOp_3() + "\r\n");
            raf.writeBytes("OP_4UP=" + getOp_4up() + "\r\n");
            raf.writeBytes("MUL_OP_OPEN_0=" + getMul_op_open_0() + "\r\n");
            raf.writeBytes("MUL_OP_OPEN_1=" + getMul_op_open_1() + "\r\n");
            raf.writeBytes("MUL_OP_OPEN_2=" + getMul_op_open_2() + "\r\n");
            raf.writeBytes("MUL_OP_OPEN_3=" + getMul_op_open_3() + "\r\n");
            raf.writeBytes("KI_BLUR=" + getKi_blur() + "\r\n");
            raf.writeBytes("KI_ACCURACY_PERCENT=" + getKi_accurcy_percent() + "\r\n");
            raf.close();
        } catch (IOException ex) {
            err.append("\n'"+file+"' konnte nicht gespeichert werden!");
            System.out.println("Fehler beim schreiben der KI File!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parseLine(String s) {
        String[] sf;
        sf = s.split("=");
        try {
            if (sf[0].equals("SELF_0")) {
                self_0 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("SELF_1")) {
                self_1 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("SELF_2")) {
                self_2 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("SELF_3")) {
                self_3 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("SELF_4UP")) {
                self_4up = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("MUL_SELF_OPEN_0")) {
                mul_self_open_0 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("MUL_SELF_OPEN_1")) {
                mul_self_open_1 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("MUL_SELF_OPEN_2")) {
                mul_self_open_2 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("OP_0")) {
                op_0 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("OP_1")) {
                op_1 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("OP_2")) {
                op_2 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("OP_3")) {
                op_3 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("OP_4UP")) {
                op_4up = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("MUL_OP_OPEN_0")) {
                mul_op_open_0 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("MUL_OP_OPEN_1")) {
                mul_op_open_1 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("MUL_OP_OPEN_2")) {
                setMul_op_open_2(Integer.parseInt(sf[1]));
            }
            if (sf[0].equals("MUL_OP_OPEN_3")) {
                mul_op_open_3 = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("KI_BLUR")) {
                ki_blur = Integer.parseInt(sf[1]);
            }
            if (sf[0].equals("KI_ACCURACY_PERCENT")) {
                ki_accurcy_percent = Integer.parseInt(sf[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            err.append("\nSyntaxfehler in einer Ki-Parameter Datei!");
            System.out.println("Syntaxfehler in einer Ki-Parameter Datei!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, e);
        } catch (NumberFormatException e) {
            err.append("\nFehler beim lesen zumindest eines Parameters!");
            System.out.println("Fehler beim lesen zumindest eines Parameters!");
        }
    }

    /**
     * @return the self_0
     */
    public int getSelf_0() {
        return self_0;
    }

    /**
     * @return the self_1
     */
    public int getSelf_1() {
        return self_1;
    }

    /**
     * @return the self_2
     */
    public int getSelf_2() {
        return self_2;
    }

    /**
     * @return the self_3
     */
    public int getSelf_3() {
        return self_3;
    }

    /**
     * @return the self_4up
     */
    public int getSelf_4up() {
        return self_4up;
    }

    /**
     * @return the mul_self_open_0
     */
    public int getMul_self_open_0() {
        return mul_self_open_0;
    }

    /**
     * @return the mul_self_open_1
     */
    public int getMul_self_open_1() {
        return mul_self_open_1;
    }

    /**
     * @return the mul_self_open_2
     */
    public int getMul_self_open_2() {
        return mul_self_open_2;
    }

    /**
     * @return the op_0
     */
    public int getOp_0() {
        return op_0;
    }

    /**
     * @return the op_1
     */
    public int getOp_1() {
        return op_1;
    }

    /**
     * @return the op_2
     */
    public int getOp_2() {
        return op_2;
    }

    /**
     * @return the op_3
     */
    public int getOp_3() {
        return op_3;
    }

    /**
     * @return the op_4up
     */
    public int getOp_4up() {
        return op_4up;
    }

    /**
     * @return the mul_op_open_0
     */
    public int getMul_op_open_0() {
        return mul_op_open_0;
    }

    /**
     * @return the mul_op_open_1
     */
    public int getMul_op_open_1() {
        return mul_op_open_1;
    }

    /**
     * @return the mul_op_open_2
     */
    public int getMul_op_open_2() {
        return mul_op_open_2;
    }

    /**
     * @param mul_op_open_2 the mul_op_open_2 to set
     */
    public void setMul_op_open_2(int mul_op_open_2) {
        this.mul_op_open_2 = mul_op_open_2;
    }

    /**
     * @return the mul_op_open_3
     */
    public int getMul_op_open_3() {
        return mul_op_open_3;
    }

    /**
     * @return the ki_blur
     */
    public int getKi_blur() {
        return ki_blur;
    }

    /**
     * @return the ki_accurcy_percent
     */
    public int getKi_accurcy_percent() {
        return ki_accurcy_percent;
    }

    //Liefert die ersten 17 Parameter der Ki in einem Array zurück
    public int[] getKiVars() {
        int KIvar[] = new int[17];
        KIvar[0] = self_0;
        KIvar[1] = self_1;
        KIvar[2] = self_2;
        KIvar[3] = self_3;
        KIvar[4] = self_4up;
        KIvar[5] = mul_self_open_0;
        KIvar[6] = mul_self_open_1;
        KIvar[7] = mul_self_open_2;
        KIvar[8] = op_0;
        KIvar[9] = op_1;
        KIvar[10] = op_2;
        KIvar[11] = op_3;
        KIvar[12] = op_4up;
        KIvar[13] = mul_op_open_0;
        KIvar[14] = mul_op_open_1;
        KIvar[15] = mul_op_open_2;
        KIvar[16] = mul_op_open_3;
        return KIvar;
    }

    public String getError() {
        String s = err.toString();
        err = new StringBuffer("");
        return s;
    }
}
