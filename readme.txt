-----------------------------
------- FÜNF-GEWINNT --------
---- © Johannes Wawerda -----
--------Version 1.3----------

--- Bauen ----------------------
--------------------------------
Mittels:
mvn package
Im target Verzeichnis findet sich dann die FuenfGewinnt.jar und die FuenfGewinnt_OptCMD.jar.
--------------------------------

--- Starten --------------------
--------------------------------
Zum Starten des Programms (unter Windows) java -jar FuenfGewinnt.jar ausführen.
Sollte das nicht funktionieren ist wahrscheinlich nicht die aktuelle Java
Version auf dem Computer installiert. 
--------------------------------


--- Windows Vista/Windows 7 ---
-------------------------------
WICHTIG! Das Spiel braucht Schreibzugriff auf seinen Ordner. Das ist unter
Windos 7/Vista im Programme Ordner nicht der Fall. Einfachste Lösung: Den
"5Gewinnt" Ordner irgendwo unter Eigene Dateien, oder am Desktop ablegen.
-------------------------------


--- Multiplayermouds ----------
-------------------------------
Es ist Möglich Fünf-Gewinnt über Lan (oder Internet) im Multiplayermodus zu 
spielen. Dazu muss einer der Spieler auf "Einleiten" klicken. Der andere 
Spieler klickt "Beitreten" und gibt die IP des Einleitenden Spielers an.
Zum Aufbauen der Verbindung wird Port 12234 (TCP) verwendet. Bitte darauf 
Achten das beide Spieler die selbe Version verwenden.
-------------------------------


--- Einzelspielermodus --------
-------------------------------
Im Einzelspielermodus kann gegen den Computer gespielt werden. Die Stärke des
Computer (bzw die KI) kann man unter "Einstellungen" auswählen.
-------------------------------


--- Ki vs. Ki -----------------
-------------------------------
Der Kivs.Ki Modus ist dazu gedacht selbst gebaute KIs zu testen und zu verbessern,
bzw festzustellen wessen KI stärker ist. Die KIs kann man im Einstellungsmenü auswählen
Es beginnt immer KI_1 (= Einzelspieler KI, die Obere) gegen KI_2 (= KIvsKI, die Untere). 
-------------------------------


--- Debug Modus ---------------
-------------------------------
Der Debug Modus ist dazu gedacht die Berchnungen einer (selbst erstellen) KI genau verfolgen
zu können. Aktivieren kann man den Debug Modus im Einstellungs Menü. Bei Aktiviertem Debug
Modus bekommt man im Einzelspieler und Kivs.Ki Modus einen Tooltip über jedem freien Feld in 
dem der zuletzt berechnete Punktewert des Computers steht. Außerdem gibt es ein Detailfenster 
in dem die Berechnung des Feldes das die KI zuletzt gesetzt hat genau aufgeschlüsselt steht.
TIPP: Mit einem Shift-Klick auf ein beliebiges Feld wird die Detailberechnung dieses Feldes 
anngezeigt.
ACHTUNG: Der Debug Modus zeigt immer die Berechnung des LETZTEN Zuges. Daher gibt es auch ein
gesetztes Feld mit Punktewert (nämlich genau das, welches der Computer zuletzt gesetzt hat).
-------------------------------



--- setting.txt ---------------
-------------------------------
In der "setting.txt" werden alle Einstellungen gespeichert. Ich empfehle diese Datei NICHT
per Editor zu ändern sondern nur im Einstellungsmenü im Spiel. Änderungen die gespeichert 
werden während das Spiel läuft werden evtl. wieder überschrieben
-------------------------------


--- .ki Datein ----------------
-------------------------------
In den .ki Datein stehen alle Einstellungen für eine KI. Hier kann man eine eigene KI
erstellen. Die Datein können mit einem beliebgen Editor geöffnet werden. Ich empfehle 
sie nur zu ändern während das Spiel nicht läuft. Auswählen kann man die KI im 
Einstellungsmenü. 
-------------------------------


--- Parameter der KI ----------
-------------------------------
Jede KI hat folgende Parameter:
___
SELF_0
SELF_1
SELF_2
SELF_3
SELF_4UP
MUL_SELF_OPEN_0
MUL_SELF_OPEN_1
MUL_SELF_OPEN_2
OP_0
OP_1
OP_2
OP_3
OP_4UP
MUL_OP_OPEN_0
MUL_OP_OPEN_1
MUL_OP_OPEN_2
MUL_OP_OPEN_3
KI_BLUR
KI_ACCURACY_PERCENT
___
Alle Parameter sind Integer (ganzzahlige Werte). Außerdem müssen alle Werte müssen positiv 
(oder Null) sein.

Bedeutung der Parameter:
Die KI berechnet für jedes freie Feld jede Runde einen Wert. In der Regel wird das auf Feld mit den 
höchsten Punkten gesetzt. Bei mehreren gleich hohen wird (zwischen diesen) gewürfelt. Die 
Berchnung für ein einzelnes Feld ist in 4 Achsen unterteilt (Horinzontal, Vertikal und die
beiden Diagonalen). Die Punkte der 4 Achsen werden addiert und geben den Feldwert. Für die
Berechnung der einzelnen Achsen sind jetzt die Parameter in der KI File ausschlaggebend:
___
SELF_0 bis SELF_4UP:
Punkte die eine eigene anliegende 0er bis 4(oder mehr)er Reihe geben soll. 
Bsp: Computer spielt Blau. Bewertet wird Feld x:2 y:3 in der Horinzontalen. Auf Feld x:3 y:3 
und x:4 y:3 sind bereits Blaue Punkte. Feld x:2 y:3 bekommt in der Horinzontalen soviele Punkte 
wie unter SELF_2 steht.

___
OP_0 bis OP_4UP:
Punkte die eine gegnerische anliegende 0er bis 4(oder mehr)er Reihe geben soll. Diese Punkte
werden mit denen von 'SELF' addiert. 

___
MUL_SELF_OPEN_0 bis MUL_SELF_OPEN_2:
Multipikator für die 'SELF' Punkte. Hängt von der Anzahl der offenen Enden der Reihe NACH 
dem Zug ab. (Es wird so getan als wäre das zu bewertende Feld schon gesetzt)
Bsp: Computer spielt Blau. Bewertet wird Feld x:2 y:3 in der Horinzontalen. Auf Feld x:3 y:3 
und x:4 y:3 sind bereits Blaue Punkte. Auf Feld x:5 y:3 ist noch kein Punkt. Auf Feld x:1 y:3
ist ein Roter Punkt. In diesen Fall wird SELF_2 mit MUL_SELF_OPEN_1 multipiziert. Nach Rechts
ist die Reihe offen, nach links wäre sie nicht offen da hier ein Roter Punkt blockiert.

Fals eine Reihe zwar in eine oder beide Richtungen offen ist aber sich insgesamt keine 5er
Reihe mehr ausgeht wird automatisch MUL_SELF_OPEN_0 genommen.

___
MUL_OP_OPEN_0 bis MUL_OP_OPEN_3:
Multipikator für die 'OP' Punkte. Hängt von der Anzahl der offenen Enden der Reihe VOR
dem Zug ab. Daher ist es auch möglich, dass es 3 offene Enden gibt (Das zu bewertende Feld 
wird mitgezählt!).
Bsp: Der Gegner hat links und rechts von dem in der Horinzontalen zu bewertenden Feld je
2 Punkte. Wenn links von der Linken 2er Reihe offen ist und rechts von Rechten ebenfalls,
so gibt es, mit dem mittleren (gerade Bewerteten) Feld insgesamt 3 Offene. 

Auch hier wird automatisch MUL_OP_OPEN_0 genommen fals sich für den Gegner keine ganze 5er 
Reihe mehr ausgeht. 

___
KI_BLUR
Gibt einen Unschärfewert an um die KI schwächer zu machen. Es wird nicht zwischen allen
höchsten gewürfelt sondern zwischen allen, die dem höchsten um KI_BLUR nahe kommen. Wird 
nach KI_ACCURACY_PERCENT berechnet.
KI am stärksten mit KI_BLUR=0

___
KI_ACCURACY_PERCENT
Ähnlich wie KI_BLUR. Es wird nicht zwischen allen höchsten gewürfelt sondern zwischen allen
die mindestens KI_ACCURACY_PERCENT Prozent des höchsten haben. Wird vor KI_BLUR berechnet.
KI am stärksten mit KI_ACCURACY_PERCENT=100

Sollte der Bewertungsvorgang jetzt noch nicht ganz klar sein empfehle ich ein paar
Einzelspielerspiele mit eingeschalteten Debug Modus zu machen und die Züge des Computers 
genau zu beobachten.

UPDATE: Für eine bebilderte Beschreibung aller KI-Parameter und einen verbesserten Debug Modus 
in der Facebook-App "http://apps.facebook.com/fivewin/" in den Ki-Builder Modus gehen.
-------------------------------


--- FuenfGewinnt_OptCMD (Kommandozeile) --
Die FuenfGewinnt_OptCMD.jar ist ein Kommandozeilenprogramm zur Auswertung der Sieges von KIs. 
Das Programm spielt dann die Anzahl der Spiele und liefert eine Auswertung zurück.
___
Parameter werden wie folgt übergeben:
<KI_File1><KI_File2><Feldgröße><AnzahlderSpiel>
___
Ausgabe:
<SiegeKI_1><SiegeKI_2><Unentschieden><DauerinMS>

ACHTUNG! Da dieser Modus auf Perfomance optimiert ist werden die Parameter KI_ACCURACY_PERCENT
und KI_BLUR nicht berücksichtig!
-------------------------------

--- Kontakt -------------------
-------------------------------
Gefundene Bugs, Vorschläge, ect: 
joh.wawerda@gmx.at
-------------------------------
