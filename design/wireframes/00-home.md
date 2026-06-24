# 00 - Wireframe: Home

Status: verbindlich v2

## Zweck

Home ist fester Bestandteil von Vivicast und der Standard-Startscreen.

Es zeigt nur Fortsetzen fuer Filme/Serien und zuletzt gesehene Live-TV-Sender.

## Primaerlayout

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+--------------------------------------------------------------------------------+
| HERO                                                                           |
| <erstes Element aus Fortsetzen>                                                |
| falls Fortsetzen leer: <erster zuletzt gesehener Live-TV-Sender>               |
+--------------------------------------------------------------------------------+
| Fortsetzen                                                                     |
| (*)[Poster Film/Serie] [Poster Film/Serie] [Poster Film/Serie] ...             |
+--------------------------------------------------------------------------------+
| Zuletzt gesehene Live-TV-Sender                                                |
| [Logo Sender] [Logo Sender] [Logo Sender] ...                                  |
+--------------------------------------------------------------------------------+
```

## Initialfokus

```text
1. erstes Element in Fortsetzen
2. erster zuletzt gesehener Live-TV-Sender
3. Wiedergabeliste hinzufuegen im Empty State
```

## Fokuswege

```text
LEFT / RIGHT  innerhalb einer Row
UP / DOWN     zwischen Hero, Fortsetzen und Live-TV-Verlauf
OK            Inhalt oeffnen
BACK          zuerst Top Navigation fokussieren
```

## Inhalte

Fortsetzen:

```text
Filme und Serien gemischt
nur nicht abgeschlossene direkte Resume-Ziele
Serien duerfen nach einer abgeschlossenen Episode auf die naechste Episode bei Position 0 weitergehen
kein Live-TV
```

Zuletzt gesehen:

```text
nur Live-TV-Sender
kompakte Channel Cards
```

## Empty State

```text
+------------------------------------------------+
| Noch keine Inhalte                             |
| Fuege eine Wiedergabeliste hinzu, um zu starten.|
|                                                |
| (*)[Wiedergabeliste hinzufuegen]               |
+------------------------------------------------+
```

## Nicht im Home-Bereich

```text
Favoriten Row
Provider-/Update-Statusbereich
Live-TV in Fortsetzen
```

Provider- und Update-Status liegen in den Einstellungen.
