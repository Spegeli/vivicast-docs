# 03 - Player

Status: verbindlich v7

## Zweck

Der Player zeigt Live-TV, Filme, Serienepisoden und Catch-Up im Vollbild.

Diese Datei beschreibt Overlay, Fokus und Bedienung. Wiedergabe- und Fehlerlogik bleibt im PRD und in der Architektur.

## Quellen

- `prd/PRD-v1/03-movies-series-requirements.md`
- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `architecture/decisions/ADR-013-player-playback-progress.md`
- `design/interaction/02-player-timeline-controls.md`
- `design/components/player.md`
- `design/interaction/focus.md`

## Layout-Zonen

1. Bildbereich
2. Bottom Overlay
3. Timeline
4. sekundaere Aktionen
5. Stream-Info-Badges
6. Dialoge

## Vollbildmodus

Standardmaessig ist keine UI sichtbar.

UI erscheint nur bei Interaktion, Statuswechsel oder Fehler.

## Overlay öffnen

Wenn kein Overlay sichtbar ist, oeffnet OK das Overlay.

Der Fokus liegt nach dem Öffnen auf der Timeline.

## Overlay ausblenden

Das Overlay blendet nach 5 Sekunden Inaktivitaet automatisch aus.

Bei erneutem Öffnen wird der letzte sinnvolle Fokus wiederhergestellt.

## Initialfokus

Beim Öffnen des Overlays liegt der Fokus auf der Timeline.

Wenn ein Dialog sichtbar ist, liegt der Fokus auf der primaeren Dialogaktion.

## Timeline

Die Timeline ist das zentrale Bedienelement.

Wenn Timeline fokussiert ist:

- OK = Play/Pause
- Links/Rechts = Seek oder Timeshift-Bewegung
- Lang Links/Rechts = schneller Seek

## Live-TV ohne Timeshift

Timeline bleibt sichtbar und zeigt den Fortschritt der aktuellen Sendung.

Links/Rechts zeigen einen Hinweis, dass Timeshift für diesen Sender nicht verfuegbar ist.

Dies gilt sowohl bei deaktiviertem Timeshift als auch bei einem Stream ohne Timeshift-Unterstuetzung.

## Live-TV mit Timeshift

Die Timeline zeigt das Timeshift-Fenster und den Live-Punkt.

Das Fenster darf die konfigurierte maximale Dauer von 15, 30, 60 oder 120 Minuten nicht überschreiten.

Die Speicherwahl `Automatisch`, `RAM` oder `Festplatte` verändert die sichtbare Timeline-Bedienung nicht.

Bei Senderwechsel wird das Timeshift-Fenster verworfen.

## Live-TV Overlay

Zeigt:

- Senderlogo
- Sendername
- aktuelle Sendung
- Startzeit und Endzeit
- verbleibende Zeit
- Beschreibung
- nächste Sendung
- Stream-Info-Badges
- Timeline
- Audio
- Untertitel
- Bildformat

## VOD Overlay

Zeigt:

- Titel
- Laufzeit
- aktuelle Position
- Fortschritt
- Timeline
- Audio
- Untertitel
- Bildformat
- Stream-Info-Badges

Beschreibung und Poster werden nicht im Player-Overlay angezeigt.

## Auto-Next bei Serienepisoden

Das Auto-Next-Panel wird nur angezeigt, wenn eine nächste Episode vorhanden ist.

Bei deaktiviertem Auto-Next erscheint es erst nach dem tatsaechlichen Episodenende:

```text
Nächste Folge abspielen | Zurück
```

Bei aktiviertem Auto-Next erscheint es um den konfigurierten Zeitraum vor dem tatsaechlichen Episodenende:

```text
Nächste Folge in 10 | Zurück
```

Der Zahlenwert wird sekundenweise aktualisiert. Hauptbutton und `Zurück` erscheinen in beiden Varianten zeitgleich nebeneinander; der Fokus liegt auf dem Hauptbutton. Einen Button `Abbrechen` gibt es nicht.

OK auf dem Hauptbutton startet die nächste Episode sofort. Ohne Eingabe startet sie bei aktivem Auto-Next beim Ablauf des Countdowns am tatsaechlichen Episodenende. Bei deaktiviertem Auto-Next erfolgt ohne OK kein Start.

OK auf `Zurück` oder die Zurück-Taste beendet den Ablauf, verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit dem zuvor gewählten Staffel-/Episodenkontext zurück.

Das Auto-Next-Panel unterliegt nicht dem normalen 5-Sekunden-Auto-Hide des Player-Overlays.

Nach der letzten Episode einer Serie erscheint kein Panel.

Die feste Abschluss-Schwelle von 95 Prozent verändert nur den gespeicherten Gesehen-/Fortsetzen-Status. Ihr Erreichen beendet die Wiedergabe nicht, blendet kein Auto-Next-Panel ein und startet keine nächste Episode.

## Externer Player

Wenn ein Film oder eine Episode extern abgespielt wird, zeigt Vivicast keinen eigenen Player-Overlay-Kontext.

Nach Rueckkehr aus einem externen Player:

- kehrt Vivicast zum passenden Film- oder Serienkontext zurück
- bleibt vorhandener Fortschritt unverändert
- wird kein neuer Fortschritt erzeugt
- zeigt Vivicast den Hinweis `Fortschritt konnte nicht automatisch ermittelt werden`

Auto-Next-Panel und automatische Episodenwechsel sind bei externen Playern nicht sichtbar.

## Sekundaere Aktionen

Mögliche Aktionen:

- Audio
- Untertitel
- Bildformat
- Mehr

Pause, Vor- und Zurückspulen werden nicht als separate Hauptbuttons benoetigt, weil die Timeline diese Bedienung übernimmt.

Ein separater EPG-Chip ist im Player-Overlay nicht Teil von v1. EPG-Kontext wird im Live-TV-Browser angezeigt, nicht als Player-Aktion.

## Bedienung

- OK oeffnet Overlay, wenn es geschlossen ist.
- OK auf Timeline pausiert oder setzt fort.
- Links/Rechts auf Timeline sucht oder bewegt Timeshift.
- Hoch wechselt zu Sekundaeraktionen.
- Zurück schliesst das normale Player-Overlay, wenn es sichtbar ist.
- OK auf dem sichtbaren Button `Zurück` oder die Zurück-Taste beendet den Auto-Next-Ablauf und kehrt mit wiederhergestelltem Staffel-/Episodenkontext zur Serien-Detailseite zurück.
- Zurück verlaesst den Player, wenn kein Overlay oder Panel sichtbar ist.

## Rueckkehr nach Player

- Live-TV kehrt zum Sender im Live-TV Browser zurück.
- Film kehrt zur Film-Detailseite zurück.
- Serie kehrt zur Episode oder Serien-Detailseite zurück.
- Externe Film- oder Episodenwiedergabe kehrt zum Film- oder Serienkontext zurück und zeigt einen Hinweis zum nicht ermittelbaren Fortschritt.

## Varianten

Live-TV ohne Timeshift zeigt den Fortschritt der aktuellen Sendung.

Live-TV mit Timeshift zeigt das begrenzte Timeshift-Fenster und den Live-Punkt.

VOD zeigt Gesamtlaufzeit und gespeicherten Fortschritt.

Catch-Up verhält sich wie VOD mit EPG-Kontext.

Catch-Up wird intern abgespielt und erzeugt keinen VOD-Fortschritt.

Externe Player sind nur für Filme und Episoden vorgesehen, nicht für Live-TV oder Catch-Up.

## Fehlerzustaende

Senderstartfehler: Fehlerdialog mit Retry.

Streamabbruch: Reconnect-Zustand anzeigen.

Timeshift nicht verfuegbar: Hinweis bei Seek-Versuch.

## Komponenten

- Player Overlay
- Player Timeline
- Info Badge
- Action Chip
- Auto-Next Panel
- Dialog Panel
- Error Dialog
