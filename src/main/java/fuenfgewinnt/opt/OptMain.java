/*
Für die Ausgabe auf der Kommandozeile
 */
package fuenfgewinnt.opt;

import fuenfgewinnt.game.SettingsKI;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jotschi
 */
public class OptMain {

    public static int w1 = 0, w2 = 0, u = 0;

    /**
     * @param args the command line arguments
     */
    // agrs  <ki1File><ki2File><Feldgröße><Anzahl>
    public static void main(String[] args) {
        SettingsKI ki1 = null;
        SettingsKI ki2 = null;
        int dim = 0;
        int anz = 0;
        try {
            ki1 = new SettingsKI(args[0]);
            ki2 = new SettingsKI(args[1]);
            dim = Integer.parseInt(args[2]);
            anz = Integer.parseInt(args[3]);
        } catch (Exception e) {
            System.out.println("Fehler bei der Parameterübergabe! Richtig: \n<ki1File> <ki2File> <Feldgröße> <Anzahlspiele>");
            System.exit(0);
        }
        int z;
        z = getErg(ki1, ki2, dim, anz)[3];
        System.out.println(w1 + " " + w2 + " " + u + " " + z);
    }

    // Returnt Feld mit: <ki1Siege><ki2Siege><Unentschieden><Rechenzeit>
    // ACHTUNG: Da die Methode mit statischen Variablen arbeitet kann immer nur EINE
    // gleichzetig richtig funktionieren
    public static int[] getErg(SettingsKI ki1, SettingsKI ki2, int dim, int anz) {
        int i;
        Date d = new Date();
        long z = d.getTime();
        int erg[] = new int[4];
        w1 = 0;
        w2 = 0;
        u = 0;

        for (i = 0; i < anz / 2; i++) {
            new FuenfGewinntThr_opt(ki1, ki2, dim, false).start();
        }
        for (; i < anz; i++) {
            new FuenfGewinntThr_opt(ki2, ki1, dim, true).start();
        }

        while (w2 + w1 + u < anz) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(OptMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        d = new Date();
        z = d.getTime() - z;

        erg[0] = w1;
        erg[1] = w2;
        erg[2] = u;
        erg[3] = (int) z;

        return erg;
    }
}
