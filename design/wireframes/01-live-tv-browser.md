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

Der erste Provider ist beim frischen Öffnen aufgeklappt.

Die globale Favoriten-Kategorie steht oberhalb des ersten Providers und ist providerunabhängig.

## Fokuswege

```text
Kategorie-Modus:
LEFT aus Senderliste      -> Provider/Kategorien
RIGHT aus Kategorie       -> Senderliste
FOCUS auf Kategorie       -> Senderliste aktualisiert sofort
OK auf Sender             -> Sender-Modus + EPG-Spalte + Preview
                              Fokus -> aktuelle EPG-Sendung, sofern vorhanden
                              sonst Fokus -> No-EPG-Placeholder

Sender-Modus:
OK auf aktueller EPG-Sendung -> Vollbild-Player
OK auf No-EPG-Placeholder    -> Vollbild-Player ohne Catch-Up-Kontext
RIGHT aus Senderliste        -> EPG-Spalte
RIGHT aus EPG-Spalte         -> Preview/Details
LEFT entsprechend zurück
```

## OK-Verhalten

```text
OK auf Provider              -> ein-/ausklappen
OK auf Kategorie             -> Kategorie aktivieren
OK in Senderspalte           -> Sender-Modus, EPG-Spalte, Preview
                               Fokus auf aktuelle EPG-Sendung, sofern vorhanden
                               sonst Fokus auf No-EPG-Placeholder
OK auf aktueller EPG-Sendung -> Vollbild-Player
OK auf No-EPG-Placeholder    -> Vollbild-Player ohne Catch-Up-Kontext
```

## Zurück-Verhalten

```text
BACK aus EPG-Spalte        -> Senderliste
BACK aus Senderliste       -> Provider/Kategorien
BACK aus Provider/Kategorien -> Top Navigation
```

## CH-Tasten

```text
Browser: CH+ / CH- bewegt Fokus in Senderliste
Player:  CH+ / CH- startet direkt nächsten/vorherigen Sender
```

## Channel Card im Live-TV Browser

Informationsreich:

```text
Sendernummer | Logo | Sendername | aktuelles Programm | Fortschritt | Favorit | Catch-Up
```

## Empty / Error / Loading

```text
Kategorie leer          -> Keine Sender in dieser Kategorie
Kein EPG                -> Keine Programminformationen verfuegbar; fokussierbarer No-EPG-Placeholder im Sender-Modus
Kein Logo               -> Icon-Fallback + Sendername
Provider Fehler         -> Inline im Providerbereich
Loading                 -> Skeleton-Zeilen, Navigation bleibt nutzbar
```

## Akzeptanzkriterien

```text
globale Navigation vollstaendig
Favoriten global über erstem Provider
erster Provider aufgeklappt
OK auf Sender startet festen Sender-Modus und Preview
Fokus springt auf aktuelle EPG-Sendung, sofern vorhanden
Wenn keine aktuelle EPG-Sendung vorhanden ist, springt Fokus auf den No-EPG-Placeholder
OK auf aktueller EPG-Sendung startet Vollbild
OK auf No-EPG-Placeholder startet Vollbild ohne Catch-Up-Kontext
keine Preview-Einstellung
RIGHT Senderliste -> EPG -> Preview
BACK-Kette korrekt
```
