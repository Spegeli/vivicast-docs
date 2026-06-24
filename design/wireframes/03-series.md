# 03 - Wireframe: Serien

Status: verbindlich v6

## Zweck

Der Serienbereich nutzt denselben Grundaufbau wie Filme und fuehrt von der Serie zur Detailseite mit Staffeln und Episoden.

## Uebersicht

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+------------------+-------------------------------------------------------------+
| KATEGORIEN       | DETAILBEREICH                                               |
| [Favoriten]      | <Serientitel>                                               |
| [Fortsetzen]     | <Bewertung> | <Genre> | <Jahr>                         |
| v Provider A     | <Beschreibung>                                             |
|   (*)[Thriller]  +-------------------------------------------------------------+
|   [Action]       | SERIEN GRID                                                 |
|   [Drama]        | (*)[Poster] [Poster] [Kein Bild] [Poster] [Poster] ...      |
| v Provider B     |                                                             |
|   [Thriller]     |                                                             |
|   [Action]       |                                                             |
+------------------+-------------------------------------------------------------+
```

## Initialfokus

```text
erste Kategorie des ersten Providers
```

Globale Kategorien wie Favoriten und Fortsetzen sind sichtbar, aber nicht der initiale Inhaltsfokus.

## Kategorien

```text
Favoriten
Fortsetzen, falls nicht abgeschlossener Fortschritt oder eine naechste Episode existiert
Provider A
- Provider-Kategorien
Provider B
- Provider-Kategorien
```

Gleichnamige Kategorien verschiedener Provider werden nicht zusammengefuehrt.

## Fokuswege Uebersicht

```text
LEFT aus Grid       -> Kategorien
RIGHT im Grid       -> naechste Serie
UP/DOWN im Grid     -> Reihe wechseln
OK auf Serie        -> Serien-Detailseite
BACK Detailseite    -> Grid
BACK Grid           -> Kategorien
BACK Kategorien     -> Top Navigation
```

## Detailseite

```text
+--------------------------------------------------------------------------------+
| BACK                                                                           |
+--------------------------------------------------------------------------------+
| <Serientitel>                                                                  |
| <Bewertung> | <Genre> | <Jahr>                                                 |
| <Beschreibung>                                                                 |
+--------------------------------------------------------------------------------+
| Fortsetzen                                                                     |
| (*)[Fortsetzen] [Zu Favoriten]                                                 |
+--------------------------------------------------------------------------------+
| Staffeln                                                                       |
| [Staffel 1] (*)[Staffel 2] [Staffel 3] ...                                      |
+--------------------------------------------------------------------------------+
| Episoden                                                                       |
| (*)[Thumb] Folge 1  <Beschreibung> <Laufzeit> <Fortschritt> [Als gesehen markieren] |
|    [Thumb] Folge 2  <Beschreibung> <Laufzeit> {Gesehen}                         |
+--------------------------------------------------------------------------------+
```

## Episode

```text
OK auf Episode       -> direkt starten oder fortsetzen
RIGHT aus Episode    -> sichtbare Markierungsaktion der fokussierten Episode
OK auf Markierung    -> Als gesehen / Als ungesehen ausfuehren
LEFT aus Markierung  -> Episode
keine separate Episode-Detailseite
```

Nur die fokussierte Episode zeigt die Markierungsaktion. Ab 95 Prozent oder beim tatsaechlichen Medienende gilt die Episode als gesehen. `Als ungesehen markieren` loescht ihren gespeicherten Fortschritt. Es gibt keine entsprechende Aktion fuer komplette Staffeln oder Serien.

## Automatische naechste Folge

```text
Automatisch naechste Folge: Aus (Standard) / Ein
Countdown: 5 / 10 / 15 / 30 Sekunden (Standard 10)
Countdown ist bei Aus sichtbar, aber deaktiviert.
```

Auto-Next Aus, erst nach dem Episodenende:

```text
+---------------------------------------------+
| <Serientitel> <Staffel/Folge>                |
| (*)[Naechste Folge abspielen] [Zurueck]      |
+---------------------------------------------+
```

Auto-Next Ein, Beispiel 10 Sekunden vor dem Episodenende:

```text
+---------------------------------------------+
| <Serientitel> <Staffel/Folge>                |
| (*)[Naechste Folge in 10] [Zurueck]          |
+---------------------------------------------+
```

Der Countdown aktualisiert den Hauptbutton sekundenweise. OK startet sofort; ohne Eingabe startet die naechste Episode beim Ablauf am Episodenende. `Zurueck` wird in beiden Zustaenden zeitgleich neben dem Hauptbutton angezeigt. OK auf `Zurueck` oder die Zurueck-Taste verwirft den Ablauf und fuehrt mit erhaltenem Staffel-/Episodenkontext zur Serien-Detailseite. Einen Button `Abbrechen` gibt es nicht.

Nach der letzten Episode der Serie erscheint kein Panel.

Die 95-Prozent-Abschluss-Schwelle veraendert nur den Gesehen-/Fortsetzen-Status. Sie zeigt das Panel nicht an und startet keine Folge.

## Zustaende

```text
Loading      -> Skeleton Cards und Episode Rows
Empty        -> Keine Serien in dieser Kategorie
No Metadata  -> fehlende Werte auslassen
No Episodes  -> Serie sichtbar, Hinweis im Episodenbereich
Error        -> Inline im betroffenen Bereich
```
