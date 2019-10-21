/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuenfgewinnt.opt;

import fuenfgewinnt.game.FinalsVars;
import fuenfgewinnt.game.PointsField;
import fuenfgewinnt.game.SettingsKI;

/**
 *
 * @author Jotschi
 */
public class FuenfGewinntThr_opt extends Thread implements FinalsVars {
    //Variablen

    private SettingsKI ki1, ki2;
    private PointsField points1, points2;
    protected boolean running;  //true solange das Spiel läuft
    protected int dim, // Spielfeldgröße
            akt_x, akt_y, //Position des aktuellen Button
            last_x, last_y, // Position von des im vorherigen Zug ausgewählten Felds
            p1color, p2color; //meine Farbe, Farbe des Gegners
    private boolean getauscht;
    protected int[][] map;  /* Zustand des Spiels: Red = 1
     *                                           Blue = 2
     *                                           None = else */


    public FuenfGewinntThr_opt(SettingsKI ki1, SettingsKI ki2, int dim, boolean getauscht) {
        this.dim = dim;
        this.p1color = RED;
        this.p2color = BLUE;
        this.getauscht = getauscht;

        this.ki1 = ki1;
        this.ki2 = ki2;

        points1 = new PointsField(dim);
        points2 = new PointsField(dim);
        //Map Initialisieren
        map = new int[dim][dim];
        for (int j = 0; j < dim; j++) {
            for (int i = 0; i < dim; i++) {
                map[j][i] = NONE;  //alle Felder leer
            }
        }

        running = true;
    }

    @Override
    public void run() {
        this.runLoop();
    }

    /*bekommt letze Änderung der map übergeben und übrpüft ob das Spiel vorbei
     * ist (5 in einer Reihe sind) */
    protected boolean isFinished(int x, int y) {
        int n, o, s, w;
        int color = map[x][y];

        //Ost-West Richtung
        w = x;
        for (int i = x; i >= 0; i--) {  //w
            if (map[i][y] == color) {
                w = i;
            } else {
                break;
            }
        }
        o = x;
        for (int i = x; i < dim; i++) {  //o
            if (map[i][y] == color) {
                o = i;
            } else {
                break;
            }
        }
        if (o - w >= 4) {
            return true;
        }


        //Nord Süd Richtung
        n = y;
        for (int i = y; i >= 0; i--) {  //s
            if (map[x][i] == color) {
                n = i;
            } else {
                break;
            }
        }
        s = y;
        for (int i = y; i < dim; i++) {  //n
            if (map[x][i] == color) {
                s = i;
            } else {
                break;
            }
        }
        if (s - n >= 4) {
            return true;
        }

        //Süd-West Nord-Ost Richtung
        s = y;
        w = x;
        int j = y;
        for (int i = x; i >= 0; i--) {  //sw
            if (j >= dim) {
                break;
            }
            if (map[i][j] == color) {
                s = j;
                w = i;
            } else {
                break;
            }
            j++;
        }

        n = y;
        o = x;
        j = y;
        for (int i = x; i < dim; i++) {  //so
            if (j < 0) {
                break;
            }
            if (map[i][j] == color) {
                n = j;
                o = i;
            } else {
                break;
            }
            j--;
        }
        if (s - n >= 4 && o - w == 4) {
            return true;
        }

        //Nord-West Süd-Ost Richtung
        n = y;
        w = x;
        j = y;
        for (int i = x; i >= 0; i--) {  //nw
            if (j < 0) {
                break;
            }
            if (map[i][j] == color) {
                n = j;
                w = i;
            } else {
                break;
            }
            j--;
        }

        s = y;
        o = x;
        j = y;
        for (int i = x; i < dim; i++) {  //so
            if (j >= dim) {
                break;
            }
            if (map[i][j] == color) {
                s = j;
                o = i;
            } else {
                break;
            }
            j++;
        }
        if (s - n >= 4 && o - w == 4) {
            return true;
        }

        return false;
    }

    protected void kiDraw(SettingsKI ki, PointsField pf, int color) {
        int max_points = 0, sel_point = 0, akt_point;
        boolean full = true;
        int x = akt_x;
        int y = akt_y;

        //Erster Zug => Alle Felder neu berechnen
        if (pf.first) {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (map[i][j] == NONE) {
                        full = false;
                        pf.points[i][j] = this.getRating(i, j, ki, color);

                    } else {
                        pf.points[i][j] = -1;
                    }
                }
            }
            pf.first = false;
        } else {  //Update Felder die betroffen sind
            //Letztes Feld
            pf.points[x][y] = -1;
            //horinzontal
            updateLine(x, y, 1, 0, pf, ki, color);
            updateLine(x, y, -1, 0, pf, ki, color);
            //vertikal
            updateLine(x, y, 0, 1, pf, ki, color);
            updateLine(x, y, 0, -1, pf, ki, color);
            //diagonal 1
            updateLine(x, y, 1, 1, pf, ki, color);
            updateLine(x, y, -1, -1, pf, ki, color);
            //diagonal 2
            updateLine(x, y, 1, -1, pf, ki, color);
            updateLine(x, y, -1, 1, pf, ki, color);

            //Vorletztes Feld
            x = last_x;
            y = last_y;
            pf.points[x][y] = -1;
            //horinzontal
            updateLine(x, y, 1, 0, pf, ki, color);
            updateLine(x, y, -1, 0, pf, ki, color);
            //vertikal
            updateLine(x, y, 0, 1, pf, ki, color);
            updateLine(x, y, 0, -1, pf, ki, color);
            //diagonal 1
            updateLine(x, y, 1, 1, pf, ki, color);
            updateLine(x, y, -1, -1, pf, ki, color);
            //diagonal 2
            updateLine(x, y, 1, -1, pf, ki, color);
            updateLine(x, y, -1, 1, pf, ki, color);
        }


        // Letztes ausgewähltes Feld merken
        last_x = akt_x;
        last_y = akt_y;

        // max_pf.points Wert feststellen und Full prüfen
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (map[i][j] == NONE) {
                    full = false;
                }
                if (pf.points[i][j] > max_points) {
                    max_points = pf.points[i][j];
                }
            }
        }

        if (full) {  // Feld voll => unentschieden
            OptMain.u++;
            running = false;
            return;
        }

        // Höchstes Feld auswählen
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (pf.points[i][j] >= max_points) {
                    akt_point = max_points + (int) (Math.random() * 100 * (max_points + 1));
                    if (sel_point < akt_point) {
                        sel_point = akt_point;
                        akt_x = i;
                        akt_y = j;
                    }
                }
            }
        }

        //Gewinner hochzählen
        map[akt_x][akt_y] = color;  //map aktualisieren
        if (this.isFinished(akt_x, akt_y)) { // verloren?
            if ((color == RED && !getauscht) || (color == BLUE && getauscht)) {
                OptMain.w1++;
                running = false;
            } else if ((color == BLUE && !getauscht) || color == RED && getauscht) {
                OptMain.w2++;
                running = false;
            }
            return;
        }
    }

     // Updatet den Punkte Wert aller Felder von x/y bis 5 Felder in Richtung dir_x/y+dir_y.
    private void updateLine(int x, int y, int dir_x, int dir_y, PointsField pf, SettingsKI ki, int color) {
        for (int i = x + dir_x, j = y + dir_y, k = 0; i >= 0 && i < dim && j >= 0 && j < dim && k < 5; i = i + dir_x, j = j + dir_y, k++) {
            if (map[i][j] == NONE) {
                pf.points[i][j] = this.getRating(i, j, ki, color);
            } else {
                pf.points[i][j] = -1;
            }
        }
    }

    /* Gibt die Anzahl der gleichfarbigen in einer Reihe direkt hintereinander angrezenden Punkte an
     * x, y => Ausgangspunkt           Bps:  numberOfBordering(2, 1, 1, 0, RED);
     * dir_x, dir_y => Richtung (+1, -1, 0)    01234567
     * color => betreffende Farbe            0 OOO000OO
     *                                       1 OR0RRROO
     *                                       2 OROOOOOO
     *                                 return: 3
     * Richtungen: 1, 0 => Rechts
     *             0, 1 => Unten  usw.
     */
    private int numberOfBordering(int x, int y, int dir_x, int dir_y, int color) {
        int anz = 0;
        for (int i = x + dir_x, j = y + dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i + dir_x, j = j + dir_y) {
            if (map[i][j] == color) {
                anz++;
            } else {
                return anz;
            }
        }
        return anz;
    }

    // Liefert die Anzahl der Enden an denen noch eine eigene 5er Reihe entstehen könnte nachdem x, y gesetzt wurde. Parameter siehe "numberOfBordering".
    private int openEndsSelf(int x, int y, int dir_x, int dir_y, int color) {
        int anz = 1, open = 0;

        //Sind auf der Linie überhaupt noch 5 in einer Reihe möglich
        for (int i = x + dir_x, j = y + dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i + dir_x, j = j + dir_y) {
            if (map[i][j] == color || map[i][j] == NONE) {
                anz++;
            } else {
                break;
            }
        }
        for (int i = x - dir_x, j = y - dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i - dir_x, j = j - dir_y) {
            if (map[i][j] == color || map[i][j] == NONE) {
                anz++;
            } else {
                break;
            }
        }
        if (anz < 5) {
            return 0;
        }

        // Offen in die eine Richtung?
        for (int i = x + dir_x, j = y + dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i + dir_x, j = j + dir_y) {
            if (map[i][j] == color) {
                //nothing
            } else if (map[i][j] == NONE) {
                open++;
                break;
            } else {
                break;
            }
        }

        // Offen in die andere Richtung?
        for (int i = x - dir_x, j = y - dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i - dir_x, j = j - dir_y) {
            if (map[i][j] == color) {
                //nothing
            } else if (map[i][j] == NONE) {
                open++;
                break;
            } else {
                break;
            }
        }

        return open;
    }

    // Liefert die Anzahl der Enden an denen noch eine gegnerische 5er Reihe entstehen könnte bevor x, y gesetzt wurde. Parameter siehe "numberOfBordering".
    private int openEndsOp(int x, int y, int dir_x, int dir_y, int color) {
        int anz = 1, open = 1;
        boolean isColorBordering = false, isColorBorderingTemp = false;

        //Sind auf der Linie überhaupt noch 5 in einer Reihe möglich
        for (int i = x + dir_x, j = y + dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i + dir_x, j = j + dir_y) {
            if (map[i][j] == color || map[i][j] == NONE) {
                anz++;
            } else {
                break;
            }
        }
        for (int i = x - dir_x, j = y - dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i - dir_x, j = j - dir_y) {
            if (map[i][j] == color || map[i][j] == NONE) {
                anz++;
            } else {
                break;
            }
        }
        if (anz < 5) {
            return 0;
        }

        // Offen in die eine Richtung?
        for (int i = x + dir_x, j = y + dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i + dir_x, j = j + dir_y) {
            if (map[i][j] == color) {
                isColorBorderingTemp = true;
            } else if (map[i][j] == NONE) {
                if (isColorBorderingTemp) {
                    open++;
                }
                break;
            } else {
                break;
            }
        }

        isColorBordering = isColorBorderingTemp;
        isColorBorderingTemp = false;

        // Offen in die andere Richtung?
        for (int i = x - dir_x, j = y - dir_y; i >= 0 && i < dim && j >= 0 && j < dim; i = i - dir_x, j = j - dir_y) {
            if (map[i][j] == color) {
                isColorBorderingTemp = true;
            } else if (map[i][j] == NONE) {
                if (isColorBorderingTemp) {
                    open++;
                }
                break;
            } else {
                break;
            }
        }

        //Kein Angrenzendes Feld mit der Farbe "color"
        if (!isColorBordering && !isColorBorderingTemp) {
            return 0;
        }
        return open;
    }

    private int kiValue(int anz, int color, int mycolor, SettingsKI ki) {
        if (color == mycolor) {
            if (anz == 0) {
                return ki.getSelf_0();
            }
            if (anz == 1) {
                return ki.getSelf_1();
            }
            if (anz == 2) {
                return ki.getSelf_2();
            }
            if (anz == 3) {
                return ki.getSelf_3();
            }
            if (anz >= 4) {
                return ki.getSelf_4up();
            }
        } else {
            if (anz == 0) {
                return ki.getOp_0();
            }
            if (anz == 1) {
                return ki.getOp_1();
            }
            if (anz == 2) {
                return ki.getOp_2();
            }
            if (anz == 3) {
                return ki.getOp_3();
            }
            if (anz >= 4) {
                return ki.getOp_4up();
            }
        }
        return -1;
    }

    private int kiOpenValue(int open, int color, int mycolor, SettingsKI ki) {
        if (color == mycolor) {
            if (open == 0) {
                return ki.getMul_self_open_0();
            }
            if (open == 1) {
                return ki.getMul_self_open_1();
            }
            if (open == 2) {
                return ki.getMul_self_open_2();
            }
        } else {
            if (open == 0) {
                return ki.getMul_op_open_0();
            }
            if (open == 1) {
                return ki.getMul_op_open_1();
            }
            if (open == 2) {
                return ki.getMul_op_open_2();
            }
            if (open == 3) {
                return ki.getMul_op_open_3();
            }
        }
        return -1;
    }

    private int getOpColor(int mycolor) {
        if (mycolor == RED) {
            return BLUE;
        } else {
            return RED;
        }
    }

    private int getRating(int x, int y, SettingsKI ki, int mycolor) {
        int p = 0, eig, op, offen;
        int opcolor = getOpColor(mycolor);
        // West-Ost
        eig = numberOfBordering(x, y, 1, 0, mycolor) + numberOfBordering(x, y, -1, 0, mycolor);
        offen = openEndsSelf(x, y, 1, 0, mycolor);
        p = p + kiValue(eig, mycolor, mycolor, ki) * kiOpenValue(offen, mycolor, mycolor, ki);

        op = numberOfBordering(x, y, 1, 0, opcolor) + numberOfBordering(x, y, -1, 0, opcolor);
        offen = openEndsOp(x, y, 1, 0, opcolor);
        p = p + kiValue(op, opcolor, mycolor, ki) * kiOpenValue(offen, opcolor, mycolor, ki);

        // Süd-Nord
        eig = numberOfBordering(x, y, 0, 1, mycolor) + numberOfBordering(x, y, 0, -1, mycolor);
        offen = openEndsSelf(x, y, 0, 1, mycolor);
        p = p + kiValue(eig, mycolor, mycolor, ki) * kiOpenValue(offen, mycolor, mycolor, ki);

        op = numberOfBordering(x, y, 0, 1, opcolor) + numberOfBordering(x, y, 0, -1, opcolor);
        offen = openEndsOp(x, y, 0, 1, opcolor);
        p = p + kiValue(op, opcolor, mycolor, ki) * kiOpenValue(offen, opcolor, mycolor, ki);

        // Südost-Nordwest
        eig = numberOfBordering(x, y, 1, 1, mycolor) + numberOfBordering(x, y, -1, -1, mycolor);
        offen = openEndsSelf(x, y, 1, 1, mycolor);
        p = p + kiValue(eig, mycolor, mycolor, ki) * kiOpenValue(offen, mycolor, mycolor, ki);

        op = numberOfBordering(x, y, 1, 1, opcolor) + numberOfBordering(x, y, -1, -1, opcolor);
        offen = openEndsOp(x, y, 1, 1, opcolor);
        p = p + kiValue(op, opcolor, mycolor, ki) * kiOpenValue(offen, opcolor, mycolor, ki);

        // Südwest-Nordost
        eig = numberOfBordering(x, y, -1, +1, mycolor) + numberOfBordering(x, y, +1, -1, mycolor);
        offen = openEndsSelf(x, y, -1, +1, mycolor);
        p = p + kiValue(eig, mycolor, mycolor, ki) * kiOpenValue(offen, mycolor, mycolor, ki);


        op = numberOfBordering(x, y, -1, +1, opcolor) + numberOfBordering(x, y, +1, -1, opcolor);
        offen = openEndsOp(x, y, -1, +1, opcolor);
        p = p + kiValue(op, opcolor, mycolor, ki) * kiOpenValue(offen, opcolor, mycolor, ki);

        return p;
    }

    protected void runLoop() {
        while (running) {
            this.kiDraw(ki1, points1, p1color);
            if(!running) {
                break;
            }
            this.kiDraw(ki2, points2, p2color);
        }
    }
}
