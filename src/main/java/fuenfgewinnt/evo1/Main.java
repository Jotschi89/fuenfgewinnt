/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.evo1;

import fuenfgewinnt.game.SettingsKI;
import fuenfgewinnt.opt.OptMain;

/**
 *
 * @author Jotschi
 */
public class Main {

    // args: <start.ki><AnzSpiele><AnzKinder><Dim><Generations><MaxMutationPercent>
    public static void main(String[] args) {
        SettingsKI parKi = null;
        int anzGames = 0;
        int anzChild = 0;
        int dim = 0;
        int anzGen = 0;
        int maxMutaPer = 0;
        int erg[] = new int[4];
        int max_erg, max_index = 0, p;
        int tmpKIvar[] = new int[17];
        int parKIvar[] = new int[17];
        try {
            parKi = new SettingsKI(args[0]);
            anzGames = Integer.parseInt(args[1]);
            anzChild = Integer.parseInt(args[2]);
            dim = Integer.parseInt(args[3]);
            anzGen = Integer.parseInt(args[4]);
            maxMutaPer = Integer.parseInt(args[5]);
        } catch (Exception e) {
            System.out.println("Fehler bei der Parameterübergabe! Richtig: \n<StartKIPfad> <AnzSpiele> <AnzKinder> <Spielfeldgröse> <Generations> <MaxMutationPercent>");
            System.exit(0);
        }
        SettingsKI child[] = new SettingsKI[anzChild];

        // Jede Generation
        for (int i = 0; i < anzGen; i++) {
            // lade Eltern Vars
            parKIvar = parKi.getKiVars();
            erg = OptMain.getErg(parKi, parKi, dim, anzGames);
            p = (erg[0]-erg[1]) * 3 + erg[2];
            max_erg = p;
            max_index = -1;

            //Jedes Kind
            for (int j = 0; j < anzChild; j++) {
                // Für jeden Parameter
                for (int k = 0; k < 17; k++) {
                    // Mutieren
                    tmpKIvar[k] = mutate(parKIvar[k], maxMutaPer);
                }
                // Erzeuge kind
                child[j] = new SettingsKI(tmpKIvar);

                // führe Spiele aus, Abstufungen um Performance zu sparen
                erg = OptMain.getErg(child[j], parKi, dim, 10);
                p = (erg[0]-erg[1]) * 3 + erg[2];
                if (p > 3) {
                    erg = OptMain.getErg(child[j], parKi, dim, 100);
                    p = (erg[0]-erg[1]) * 3 + erg[2];
                    if (p > 40) {
                        erg = OptMain.getErg(child[j], parKi, dim, anzGames);
                        p = (erg[0]-erg[1]) * 3 + erg[2];
                        if (p > max_erg) {  // Überlebensbedinung
                            max_erg = p;
                            max_index = j;
                        }
                    }
                }
                System.out.println("G" + i + "_Child" + j + ":  w:" + erg[0] + " l:" + erg[1] + " u:" + erg[2] + " t:" + erg[3] + " p:" + p);

            }
            System.out.println("G" + i + "Wenigsten Verloren: Index = " + max_index + " mit " + max_erg + " Punkten.");
            if (max_index != -1) {
                parKi = child[max_index];
            }
            parKi.writeSettings("erg"+i+".ki");
        }
        /*
        try {
            parKi.writeSettings("erg.ki");
        } catch (IOException ex) {
            System.out.println("Speichern der 'erg.ki' nicht möglich!");
        }*/
    }

    // Verändert den Übergebenen Parameter 'val' um Max +-'maxMutaPer' Prozent.
    private static int mutate(int val, int maxMutaPer) {
        double d;
        d = val * (1 + ((Math.random() - 0.5) * 2) * maxMutaPer / 100);
        if (d > val) {
            d++;
        }
        val = (int) d;
        if (val < 0) {
            val = 0;
        }
        return val;
    }
}
