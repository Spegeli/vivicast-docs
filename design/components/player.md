# Player Components

Status: verbindlich v6

## Zweck

Diese Datei beschreibt wiederverwendbare Player-Komponenten.

Sie gilt fuer Live-TV, Filme, Serienepisoden und Catch-Up.

## Overlay Container

Der Overlay Container liegt ueber dem Vollbildvideo.

Regeln:

- erscheint bei Bedienung
- OK oeffnet Overlay, wenn es geschlossen ist
- Fokus liegt nach Oeffnen auf Timeline
- blendet nach 5 Sekunden Inaktivitaet aus
- darf Untertitel nicht dauerhaft verdecken
- stellt letzten sinnvollen Fokus wieder her

## Player Timeline

Die Timeline ist das primaere fokussierbare Bedienelement im Overlay.

Varianten:

- Live-TV Fortschritt
- Timeshift-Fenster
- VOD-Laufzeit
- gespeicherte Position

Fokus:

- klar sichtbarer Ring oder Glow
- Fokuspunkt sichtbar
- Zeitwerte lesbar

Bedienung:

- OK = Play/Pause
- Links/Rechts = Seek oder Timeshift-Bewegung
- Lang Links/Rechts = schneller Seek

Live-TV ohne Timeshift:

- Timeline bleibt sichtbar
- Links/Rechts zeigen Hinweis
- Seek bleibt gesperrt

## Live-TV Info Block

Enthaelt:

- Senderlogo
- Sendername
- aktuelle Sendung
- Startzeit und Endzeit
- verbleibende Zeit
- Beschreibung
- naechste Sendung

## VOD Info Block

Enthaelt:

- Titel
- Laufzeit
- aktuelle Position
- Fortschritt

Beschreibung und Poster werden im Player-Overlay nicht angezeigt.

## Action Chip

Verwendung fuer sekundaere Aktionen:

- Audio
- Untertitel
- Bildformat
- Mehr

Action Chips sind nicht fuer Pause, Vor- oder Zurueckspulen als Hauptaktionen gedacht.

## Info Badge

Verwendung fuer:

- HD
- Full HD
- 4K
- FPS
- Audioformat
- Live
- Timeshift

Position so waehlen, dass wichtige Bildinhalte nicht dauerhaft verdeckt werden.

## Auto-Next Panel

Verwendung nur bei Serienepisoden mit vorhandener naechster Episode.

Inhalt:

- Serien- oder Episodenkontext
- fokussierter Hauptbutton
- zeitgleich sichtbare sekundaere Aktion `Zurueck`

Zustaende:

```text
MANUAL_AFTER_END -> Hauptbutton `Naechste Folge abspielen`
COUNTDOWN        -> Hauptbutton `Naechste Folge in X`
```

Regeln:

- `MANUAL_AFTER_END` erscheint bei deaktiviertem Auto-Next erst nach dem tatsaechlichen Episodenende.
- `COUNTDOWN` erscheint bei aktiviertem Auto-Next um den gespeicherten Zeitraum vor dem tatsaechlichen Episodenende.
- X wird sekundenweise aktualisiert, ohne Fokus, Groesse oder Position des Hauptbuttons springen zu lassen.
- OK auf dem Hauptbutton startet die naechste Episode sofort.
- Nur `COUNTDOWN` startet beim Ablauf automatisch.
- Hauptbutton und `Zurueck` werden in beiden Zustaenden gleichzeitig und nebeneinander angezeigt.
- OK auf `Zurueck` oder die Zurueck-Taste verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit dem zuvor gewaehlten Staffel-/Episodenkontext zurueck.
- Einen Button `Abbrechen` gibt es in diesem Panel nicht.
- Das Panel blendet nicht nach der normalen Overlay-Inaktivitaetszeit aus.
- Nach der letzten Episode einer Serie wird das Panel nicht erzeugt.
- Die 95-Prozent-Abschluss-Schwelle ist kein Panel-Ausloeser und beendet die Wiedergabe nicht; fuer Anzeige und automatischen Wechsel bleibt das tatsaechliche Medienende massgeblich.
- Das Panel wird bei externen Playern nie erzeugt.

## External Player Return Notice

Verwendung nach Rueckkehr aus externer Film- oder Episodenwiedergabe.

Text:

```text
Fortschritt konnte nicht automatisch ermittelt werden.
```

Regeln:

- Hinweis ist nicht blockierend.
- Vorhandener Fortschritt bleibt unveraendert.
- Es wird kein neuer Fortschrittsdatensatz erzeugt.
- Manuelle Aktionen `Als gesehen markieren` und `Als ungesehen markieren` bleiben im Film- oder Serienkontext erreichbar.
- Externe Player werden nur fuer Filme und einzelne Episoden angeboten, nicht fuer Live-TV oder Catch-Up.

## Catch-Up Player Context

Verwendung fuer Catch-Up-Wiedergabe aus einem EPG-Programmpunkt.

Regeln:

- Wird im internen Vivicast-Player angezeigt.
- Nutzt EPG-Kontext fuer Titel, Sender und Zeitfenster.
- Verhaelt sich in der Timeline wie begrenztes VOD.
- Erzeugt keinen `PlaybackProgressEntity`-Datensatz.
- Erzeugt kein Resume-Ziel.
- Wird nicht an externe Player uebergeben.

## Track Selection Dialogs

Verwendung fuer Audio- und Untertitelauswahl im Player.

Regeln:

- Auswahl ist per D-Pad bedienbar.
- Globale Defaults werden beim Streamstart angewendet.
- Manuelle Auswahl gilt nur fuer die aktuelle Wiedergabe.
- Manuelle Auswahl aendert keine globalen Wiedergabe-Einstellungen.

## Dialog Panel

Verwendung fuer:

- Fehler
- externe Player-Auswahl
- Audioauswahl
- Untertitelauswahl
- Bestaetigungen

Dialoge muessen per D-Pad bedienbar sein.
