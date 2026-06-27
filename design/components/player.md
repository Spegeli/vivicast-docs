# Player Components

Status: verbindlich v6

## Zweck

Diese Datei beschreibt wiederverwendbare Player-Komponenten.

Sie gilt für Live-TV, Filme, Serienepisoden und Catch-Up.

## Overlay Container

Der Overlay Container liegt über dem Vollbildvideo.

Regeln:

- erscheint bei Bedienung
- OK oeffnet Overlay, wenn es geschlossen ist
- Fokus liegt nach Öffnen auf Timeline
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

Enthält:

- Senderlogo
- Sendername
- aktuelle Sendung
- Startzeit und Endzeit
- verbleibende Zeit
- Beschreibung
- nächste Sendung

## VOD Info Block

Enthält:

- Titel
- Laufzeit
- aktuelle Position
- Fortschritt

Beschreibung und Poster werden im Player-Overlay nicht angezeigt.

## Action Chip

Verwendung für sekundaere Aktionen:

- Audio
- Untertitel
- Bildformat
- Mehr

Action Chips sind nicht für Pause, Vor- oder Zurückspulen als Hauptaktionen gedacht.

Zulaessige Action Chips im Player-Overlay sind nur Audio, Untertitel, Bildformat und Mehr. EPG, Pause, Vor und Zurück sind keine Action Chips.

## Info Badge

Verwendung für:

- HD
- Full HD
- 4K
- FPS
- Audioformat
- Live
- Timeshift

Position so wählen, dass wichtige Bildinhalte nicht dauerhaft verdeckt werden.

## Auto-Next Panel

Verwendung nur bei Serienepisoden mit vorhandener nächster Episode.

Inhalt:

- Serien- oder Episodenkontext
- fokussierter Hauptbutton
- zeitgleich sichtbare sekundaere Aktion `Zurück`

Zustaende:

```text
MANUAL_AFTER_END -> Hauptbutton `Nächste Folge abspielen`
COUNTDOWN        -> Hauptbutton `Nächste Folge in X`
```

Regeln:

- `MANUAL_AFTER_END` erscheint bei deaktiviertem Auto-Next erst nach dem tatsaechlichen Episodenende.
- `COUNTDOWN` erscheint bei aktiviertem Auto-Next um den gespeicherten Zeitraum vor dem tatsaechlichen Episodenende.
- X wird sekundenweise aktualisiert, ohne Fokus, Größe oder Position des Hauptbuttons springen zu lassen.
- OK auf dem Hauptbutton startet die nächste Episode sofort.
- Nur `COUNTDOWN` startet beim Ablauf automatisch.
- Hauptbutton und `Zurück` werden in beiden Zustaenden gleichzeitig und nebeneinander angezeigt.
- OK auf `Zurück` oder die Zurück-Taste verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit dem zuvor gewählten Staffel-/Episodenkontext zurück.
- Einen Button `Abbrechen` gibt es in diesem Panel nicht.
- Das Panel blendet nicht nach der normalen Overlay-Inaktivitaetszeit aus.
- Nach der letzten Episode einer Serie wird das Panel nicht erzeugt.
- Die 95-Prozent-Abschluss-Schwelle ist kein Panel-Ausloeser und beendet die Wiedergabe nicht; für Anzeige und automatischen Wechsel bleibt das tatsaechliche Medienende massgeblich.
- Das Panel wird bei externen Playern nie erzeugt.

## External Player Return Notice

Verwendung nach Rueckkehr aus externer Film- oder Episodenwiedergabe.

Text:

```text
Fortschritt konnte nicht automatisch ermittelt werden.
```

Regeln:

- Hinweis ist nicht blockierend.
- Vorhandener Fortschritt bleibt unverändert.
- Es wird kein neuer Fortschrittsdatensatz erzeugt.
- Manuelle Aktionen `Als gesehen markieren` und `Als ungesehen markieren` bleiben im Film- oder Serienkontext erreichbar.
- Externe Player werden nur für Filme und einzelne Episoden angeboten, nicht für Live-TV oder Catch-Up.

## Catch-Up Player Context

Verwendung für Catch-Up-Wiedergabe aus einem EPG-Programmpunkt.

Regeln:

- Wird im internen Vivicast-Player angezeigt.
- Nutzt EPG-Kontext für Titel, Sender und Zeitfenster.
- Verhält sich in der Timeline wie begrenztes VOD.
- Erzeugt keinen `PlaybackProgressEntity`-Datensatz.
- Erzeugt kein Resume-Ziel.
- Wird nicht an externe Player übergeben.

## Track Selection Dialogs

Verwendung für Audio- und Untertitelauswahl im Player.

Regeln:

- Auswahl ist per D-Pad bedienbar.
- Globale Defaults werden beim Streamstart angewendet.
- Manuelle Auswahl gilt nur für die aktuelle Wiedergabe.
- Manuelle Auswahl ändert keine globalen Wiedergabe-Einstellungen.

## Dialog Panel

Verwendung für:

- Fehler
- externe Player-Auswahl
- Audioauswahl
- Untertitelauswahl
- Bestätigungen

Dialoge müssen per D-Pad bedienbar sein.
