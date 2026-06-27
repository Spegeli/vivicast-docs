# 04 - Wireframe: Suche

Status: verbindlich v7

## Zweck

Die Suche findet Kanäle, Filme, Serien und EPG-Inhalte zentral.

## Leerer Zustand

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+--------------------------------------------------------------------------------+
| (*)[Suche...]                                             [Mikrofon]           |
+--------------------------------------------------------------------------------+
| Suchverlauf                                                                    |
| [ARD] [Film] [Serie] [Sender] [Sport]                                          |
+--------------------------------------------------------------------------------+
| Gib einen Suchbegriff ein.                                                     |
+--------------------------------------------------------------------------------+
```

## Mit Ergebnissen

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+--------------------------------------------------------------------------------+
| [Suchbegriff]                                            (*)[Mikrofon]         |
+--------------------------------------------------------------------------------+
| Suchverlauf                                                                    |
| [ARD] [Film] [Serie] [Sender] [Sport]                                          |
+--------------------------------------------------------------------------------+
| Kanäle                                                                         |
| (*)[Sender] [Sender] [Sender] ...                                              |
| Filme                                                                          |
| [Poster] [Poster] [Poster] ...                                                 |
| Serien                                                                         |
| [Poster] [Poster] ...                                                          |
| EPG                                                                            |
| [Sendung auf Sender] [Sendung auf Sender] ...                                  |
+--------------------------------------------------------------------------------+
```

## Initialfokus

```text
Suchfeld
```

## Eingabe

```text
OK auf Suchfeld -> System-Tastatur
OK auf Mikrofon -> Android-Sprachsuche
Sprachsuche startet nie automatisch
Debounce: 300 ms
Datenquelle: Room FTS4
```

## Suchhistorie

```text
dauerhaft im Suchscreen sichtbar
auch bei gefuelltem Suchfeld sichtbar
auch bei Ergebnisgruppen sichtbar
maximal 20 Eintraege
Eintrag erneut verwenden
Eintrag löschen
Verlauf löschen
```

## Ergebnisgruppen

```text
Kanäle
Filme
Serien
EPG
```

Jede Gruppe ist eine horizontale Row.

Episoden sind keine eigene Suchgruppe. EPG-Ergebnisse erscheinen erst ab fachlicher Mindestlaenge. Eine verpflichtende "Alle anzeigen"-Aktion gibt es in v1 nicht.

## OK auf Ergebnis

```text
Kanal   -> zugehoerigen Sender im Live-TV-Browser öffnen; weiterer Ablauf gemaess Live-TV-Spezifikation
Film    -> Film-Detailseite
Serie   -> Serien-Detailseite
EPG     -> Live-TV Sender mit fokussiertem EPG-Eintrag
```

EPG-Zielzustand:

```text
Live-TV im Sender-Modus
passender Sender aktiv
EPG-Spalte zum Zielprogrammpunkt gescrollt
Fokus auf Zielprogrammpunkt
aktuelle Sendung -> Vollbild Live-TV
vergangene Sendung mit Catch-Up -> Catch-Up im internen Player
vergangene Sendung ohne Catch-Up -> Details/Info, keine Wiedergabe
zukuenftige Sendung -> Details/Info, keine Wiedergabe
```

## Zurück

```text
BACK schliesst zuerst Texteingabe
BACK danach -> Top Navigation
```

## Zustaende

```text
Initial     -> Suchfeld + Suchhistorie
Typing      -> Suchhistorie bleibt sichtbar, Ergebnisse nach Debounce
Loading     -> Row-Platzhalter, falls noetig
Empty       -> Keine Ergebnisse gefunden, Suchhistorie bleibt sichtbar
Error       -> Suche derzeit nicht verfuegbar
```
