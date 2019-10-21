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
public class Settings implements Serializable, FinalsVars {
    //Debug

    private boolean debug;
    //Other
    private int dimension;
    private int playerColor;
    private String ki_file1;
    private String ki_file2;
    private SettingsKI ki1;
    private SettingsKI ki2;
    private int starttime;
    private int addtime;
    private boolean timer;
    //Error
    private StringBuffer err = new StringBuffer("");

    public Settings(String file) {
        debug = false;
        dimension = 15;
        playerColor = RED;
        ki_file1 = "standard.ki";
        ki_file2 = "standard.ki";
        starttime = 10000;
        addtime = 2000;
        timer = true;

        readSettings(file);
        writeSettings(file);

        ki1 = new SettingsKI(getKi_file1());
        err.append(ki1.getError());
        ki2 = new SettingsKI(getKi_file2());
        err.append(ki2.getError());
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
            err.append("\n'"+file+"' Datei nicht gefunden! Wird angelegt...");
            System.out.println("Setting Datei nicht gefunden!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            err.append("\nFehler beim lese von '"+file+"'! Standardwerte werden verwendet.");
            System.out.println("Fehler beim lese der Settings File!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeSettings(String file) {
        File f = new File(file);
        f.delete();
        try {
            RandomAccessFile raf;
            raf = new RandomAccessFile(file, "rw");
            raf.writeBytes("DEBUG=" + this.isDebug() + "\r\n");
            raf.writeBytes("DIMENSION=" + this.getDimension() + "\r\n");
            if (getPlayerColor() == RED) {
                raf.writeBytes("PLAYERCOLOR=red\r\n");
            } else if (getPlayerColor() == BLUE) {
                raf.writeBytes("PLAYERCOLOR=blue\r\n");
            }
            raf.writeBytes("KI_FILE1=" + getKi_file1() + "\r\n");
            raf.writeBytes("KI_FILE2=" + getKi_file2() + "\r\n");
            raf.writeBytes("TIMER=" + this.isTimer() + "\r\n");
            raf.writeBytes("STARTTIME=" + this.getStarttime() + "\r\n");
            raf.writeBytes("ADDTIME=" + this.getAddtime() + "\r\n");
            raf.close();
        } catch (IOException ex) {
            err.append("\n'"+file+"' konnte nicht gespeichert werden!");
            System.out.println("Fehler beim schreiben der Settings File!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parseLine(String s) {
        String[] sf;
        sf = s.split("=");
        try {
            if (sf[0].equals("DEBUG")) {
                this.setDebug(Boolean.parseBoolean(sf[1]));
            }
            if (sf[0].equals("DIMENSION")) {
                try {
                    setDimension(Integer.parseInt(sf[1]));
                } catch (NumberFormatException e) {
                    err.append("\nFehler beim lesen der Spielfeldgröße!");
                    System.out.println("Fehler beim lesen der Spielfeldgröße!");
                }
            }
            if (sf[0].equals("PLAYERCOLOR")) {
                if (sf[1].equals("red") || sf[1].equals("Red") || sf[1].equals("RED")) {
                    this.setPlayerColor(RED);
                } else if (sf[1].equals("blue") || sf[1].equals("Blue") || sf[1].equals("BLUE")) {
                    this.setPlayerColor(BLUE);
                } else {
                    err.append("\nFehler beim lesen der Spielerfarbe!");
                    System.out.println("Fehler beim lesen der Spielerfarbe!");
                }
            }
            if (sf[0].equals("KI_FILE1")) {
                this.setKi_file1(sf[1]);
            }
            if (sf[0].equals("KI_FILE2")) {
                this.setKi_file2(sf[1]);
            }
            if(sf[0].equals("TIMER")) {
                this.setTimer(Boolean.parseBoolean(sf[1]));
            }
            if (sf[0].equals("STARTTIME")) {
                try {
                    setStarttime(Integer.parseInt(sf[1]));
                } catch (NumberFormatException e) {
                    err.append("\nFehler beim lesen der Startzeit!");
                    System.out.println("Fehler beim lesen der Startzeit!");
                }
            }
            if (sf[0].equals("ADDTIME")) {
                try {
                    setAddtime(Integer.parseInt(sf[1]));
                } catch (NumberFormatException e) {
                    err.append("\nFehler beim lesen der Zeit pro Runde!");
                    System.out.println("Fehler beim lesen der Zeit pro Runde!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            err.append("\nSyntaxfehler in der Setting Datei!");
            System.out.println("Syntaxfehler in der Setting Datei!");
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String toString() {
        return "DEBUG=" + this.isDebug()
                + "\r\nDIMENSION=" + this.getDimension()
                + "\r\nPLAYERCOLOR=" + this.getPlayerColor()
                + "\r\nKI_FILE1=" + getKi_file1()
                + "\r\nKI_FILE2=" + getKi_file2();
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @return the dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * @return the playerColor
     */
    public int getPlayerColor() {
        return playerColor;
    }

    /**
     * @return the ki1
     */
    public SettingsKI getKi1() {
        return ki1;
    }

    /**
     * @return the ki2
     */
    public SettingsKI getKi2() {
        return ki2;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @param dimension the dimension to set
     */
    public void setDimension(int dimension) {
        if (dimension > 50) {
            dimension = 50;
        }
        if (dimension < 10) {
            dimension = 10;
        }
        this.dimension = dimension;
    }

    /**
     * @param playerColor the playerColor to set
     */
    public void setPlayerColor(int playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * @param ki_file1 the ki_file1 to set
     */
    public void setKi_file1(String ki_file1) {
        this.ki_file1 = ki_file1;
        ki1 = new SettingsKI(this.getKi_file1());
        err.append(ki1.getError());
    }

    /**
     * @param ki_file2 the ki_file2 to set
     */
    public void setKi_file2(String ki_file2) {
        this.ki_file2 = ki_file2;
        ki2 = new SettingsKI(this.getKi_file2());
        err.append(ki2.getError());
    }

    /**
     * @return the ki_file1
     */
    public String getKi_file1() {
        return ki_file1;
    }

    /**
     * @return the ki_file2
     */
    public String getKi_file2() {
        return ki_file2;
    }

    public String getError() {
        String s = err.toString();
        err = new StringBuffer("");
        return s;
    }

    /**
     * @return the starttime
     */
    public int getStarttime() {
        return starttime;
    }

    /**
     * @param starttime the starttime to set
     */
    public void setStarttime(int starttime) {
        this.starttime = starttime;
        if(this.starttime < 0) {
            this.starttime = 0;
        }
    }

    /**
     * @return the addtime
     */
    public int getAddtime() {
        return addtime;
    }

    /**
     * @param addtime the addtime to set
     */
    public void setAddtime(int addtime) {
        this.addtime = addtime;
        if(this.addtime < 0) {
            this.addtime = 0;
        }
    }

    /**
     * @return the timer
     */
    public boolean isTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(boolean timer) {
        this.timer = timer;
    }
}
