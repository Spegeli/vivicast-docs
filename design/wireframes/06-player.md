# 06 - Wireframe: Player

Status: verbindlich v5

## Zweck

Der Player ist der Vollbildbereich fuer Live-TV, Filme, Serien und Catch-Up.

Standard ist Vollbild ohne sichtbare UI.

## Vollbild

```text
+--------------------------------------------------------------------------------+
|                                                                                |
|                                                                                |
|                                <Videobild>                                     |
|                                                                                |
|                                                                                |
+--------------------------------------------------------------------------------+
```

## Overlay oeffnen und schliessen

```text
OK ohne Overlay      -> Overlay oeffnen, Fokus auf Timeline
BACK mit Overlay     -> Overlay schliessen
BACK ohne Overlay    -> Player verlassen
Auto-Hide            -> 5 Sekunden Inaktivitaet
```

Rueckkehr:

```text
Live-TV -> Sender im Live-TV Browser
Film    -> Film-Detailseite
Serie   -> Episode oder Serien-Detailseite
Catch-Up -> Sender/EPG-Kontext
```

## Live-TV Overlay

```text
+--------------------------------------------------------------------------------+
|                                <Videobild>                                     |
+--------------------------------------------------------------------------------+
| [Logo] Sendername                                             {LIVE} {HD}       |
| Aktuelle Sendung                                                               |
| 20:00 - 20:15 | Noch 6 Minuten                                                 |
| Beschreibung                                                                   |
| Danach: Naechste Sendung 20:15                                                 |
|                                                                                |
| (*) Timeline: ███████░░░░░░░░░░                                                |
| [Audio] [Untertitel] [Bildformat]                                              |
+--------------------------------------------------------------------------------+
```

## VOD Overlay

```text
+--------------------------------------------------------------------------------+
|                                <Videobild>                                     |
+--------------------------------------------------------------------------------+
| Titel                                                         {HD} {5.1}        |
| 00:42:18 / 02:46:00                                                            |
|                                                                                |
| (*) Timeline: █████░░░░░░░░░░░░                                                |
| [Audio] [Untertitel] [Bildformat]                                              |
+--------------------------------------------------------------------------------+
```

Keine Beschreibung und kein Poster im VOD Overlay.

## Timeline Bedienung

```text
OK                 -> Play/Pause
LEFT / RIGHT       -> Seek oder Timeshift-Bewegung
LONG LEFT / RIGHT  -> schneller Seek
```

Live-TV ohne Timeshift:

```text
Timeline sichtbar
LEFT / RIGHT -> Hinweis: Timeshift fuer diesen Sender nicht verfuegbar
```

## CH-Tasten

```text
CH+ -> naechster Sender
CH- -> vorheriger Sender
```

## Auto-Next Serie

Nur wenn eine naechste Episode vorhanden ist.

Auto-Next deaktiviert, nach dem tatsaechlichen Episodenende:

```text
+---------------------------------------------+
| <Serientitel> <Staffel/Folge>                |
| (*)[Naechste Folge abspielen] [Zurueck]      |
+---------------------------------------------+
```

Ohne OK startet keine weitere Episode.

Auto-Next aktiviert, Beispiel 10 Sekunden vor dem tatsaechlichen Episodenende:

```text
+---------------------------------------------+
| <Serientitel> <Staffel/Folge>                |
| (*)[Naechste Folge in 10] [Zurueck]          |
+---------------------------------------------+
```

Der Wert wird sekundenweise aktualisiert. OK startet sofort; ohne Eingabe startet die naechste Episode beim Ablauf des Countdowns am Episodenende.

`Zurueck` erscheint in beiden Zustaenden zeitgleich neben dem Hauptbutton. OK auf `Zurueck` oder die Zurueck-Taste verwirft einen laufenden Countdown und fuehrt mit erhaltenem Staffel-/Episodenkontext zur Serien-Detailseite. Einen Button `Abbrechen` gibt es nicht. Nach der letzten Episode der Serie erscheint kein Panel.

Die feste Abschluss-Schwelle von 95 Prozent veraendert nur den Gesehen-/Fortsetzen-Status. Sie beendet die Wiedergabe nicht und zeigt oder startet kein Auto-Next.

## Rueckkehr aus externem Player

```text
+---------------------------------------------+
| Fortschritt konnte nicht automatisch         |
| ermittelt werden.                            |
+---------------------------------------------+
```

Regeln:

```text
vorhandener Fortschritt bleibt unveraendert
kein neuer Fortschrittsdatensatz
kein Auto-Next-Panel
Rueckkehr zum Film- oder Serienkontext
nur fuer Film und Episode, nicht fuer Live-TV oder Catch-Up
```

## Catch-Up

```text
interner Player
EPG-Kontext sichtbar
keine externe Player-Uebergabe
kein VOD-Fortschritt
kein Resume-Ziel
```

## Fehlerdialoge

```text
Stream konnte nicht gestartet werden.
(*)[Erneut versuchen] [Anderen Sender waehlen] [Schliessen]
```

```text
Verbindung unterbrochen.
(*)[Erneut versuchen] [Anderen Sender waehlen] [Schliessen]
```

## Akzeptanzkriterien

```text
Vollbild standardmaessig ohne UI
OK oeffnet Overlay mit Fokus auf Timeline
BACK schliesst Overlay vor Player-Rueckkehr
Timeline ist zentrales Bedienelement
Live-TV ohne Timeshift zeigt Hinweis bei Seek
VOD Overlay bleibt minimal
Auto-Next Aus zeigt die manuelle Aktion erst nach dem Episodenende
Auto-Next Ein zeigt den dynamischen Hauptbutton vor dem Episodenende
Auto-Next-Hauptbutton behaelt Fokus und Position
BACK oder OK auf Zurueck im Auto-Next-Panel fuehrt mit erhaltenem Staffel-/Episodenkontext zur Serien-Detailseite
Nach Serienende erscheint kein Auto-Next-Panel
95-Prozent-Abschluss-Schwelle beendet Wiedergabe nicht und loest kein Auto-Next aus
Externe Player schreiben keinen Fortschritt zurueck
Catch-Up bleibt intern und erzeugt keinen VOD-Fortschritt
```
