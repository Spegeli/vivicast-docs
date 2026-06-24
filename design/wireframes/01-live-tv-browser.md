# 01 - Wireframe: Live-TV Browser

Status: verbindlich v3

## Zweck

Der Live-TV Browser ist der zentrale Screen zum Finden, Pruefen und Starten von Sendern.

Er bildet das aktuelle adaptive Spaltenmodell ab.

## Kategorie-Modus

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+---------------------+-----------------------------+----------------------------+
| FAVORITEN / PROVIDER| SENDERLISTE                 | PREVIEW / DETAILS          |
|                     |                             |                            |
| [Live-TV Favoriten] | [Logo] ARD HD               | [Senderlogo oder Preview]  |
|                     | Tagesschau                  | ARD HD                     |
| v Provider A        | Fortschritt 20:00-20:15     | Tagesschau                 |
|   (*)[News]         |                             | 20:00-20:15                |
|   [Sport]           | [Logo] ZDF HD               | Danach: Wetter vor acht    |
|   [Nicht kat.]      | heute journal               |                            |
| > Provider B        |                             | Keine Preview bis OK       |
| > Provider C        | ...                         |                            |
+---------------------+-----------------------------+----------------------------+
```

## Sender-Modus nach OK auf Sender

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+-----------------------------+-----------------------------+----------------------------+
| SENDERLISTE                 | SENDER-EPG                  | PREVIEW / DETAILS          |
|                             |                             |                            |
| [aktiv][Logo] ARD HD        | 19:45 Vorabend              | [Live Preview]             |
| Tagesschau                  | (*)20:00 Tagesschau         | ARD HD                     |
| Fortschritt 20:00-20:15     | 20:15 Wetter vor acht       | Tagesschau                 |
|                             | 20:20 Brennpunkt            | Noch 6 Minuten             |
| [Logo] ZDF HD               | ...                         | [Stream Info]              |
| heute journal               |                             |                            |
+-----------------------------+-----------------------------+----------------------------+
```

## Initialfokus

```text
erste Kategorie des ersten Providers
```

Der erste Provider ist beim frischen Oeffnen aufgeklappt.

Die globale Favoriten-Kategorie steht oberhalb des ersten Providers und ist providerunabhaengig.

## Fokuswege

```text
Kategorie-Modus:
LEFT aus Senderliste      -> Provider/Kategorien
RIGHT aus Kategorie       -> Senderliste
FOCUS auf Kategorie       -> Senderliste aktualisiert sofort
OK auf Sender             -> Sender-Modus + EPG-Spalte + Preview
                              Fokus -> aktuelle EPG-Sendung, sofern vorhanden

Sender-Modus:
OK auf aktueller EPG-Sendung -> Vollbild-Player
RIGHT aus Senderliste        -> EPG-Spalte
RIGHT aus EPG-Spalte         -> Preview/Details
LEFT entsprechend zurueck
```

## OK-Verhalten

```text
OK auf Provider              -> ein-/ausklappen
OK auf Kategorie             -> Kategorie aktivieren
OK in Senderspalte           -> Sender-Modus, EPG-Spalte, Preview
                               Fokus auf aktuelle EPG-Sendung, sofern vorhanden
OK auf aktueller EPG-Sendung -> Vollbild-Player
```

## Zurueck-Verhalten

```text
BACK aus EPG-Spalte        -> Senderliste
BACK aus Senderliste       -> Provider/Kategorien
BACK aus Provider/Kategorien -> Top Navigation
```

## CH-Tasten

```text
Browser: CH+ / CH- bewegt Fokus in Senderliste
Player:  CH+ / CH- startet direkt naechsten/vorherigen Sender
```

## Channel Card im Live-TV Browser

Informationsreich:

```text
Sendernummer | Logo | Sendername | aktuelles Programm | Fortschritt | Favorit | Catch-Up
```

## Empty / Error / Loading

```text
Kategorie leer          -> Keine Sender in dieser Kategorie
Kein EPG                -> Keine Programminformationen verfuegbar
Kein Logo               -> Icon-Fallback + Sendername
Provider Fehler         -> Inline im Providerbereich
Loading                 -> Skeleton-Zeilen, Navigation bleibt nutzbar
```

## Akzeptanzkriterien

```text
globale Navigation vollstaendig
Favoriten global ueber erstem Provider
erster Provider aufgeklappt
OK auf Sender startet festen Sender-Modus und Preview
Fokus springt auf aktuelle EPG-Sendung, sofern vorhanden
OK auf aktueller EPG-Sendung startet Vollbild
keine Preview-Einstellung
RIGHT Senderliste -> EPG -> Preview
BACK-Kette korrekt
```
