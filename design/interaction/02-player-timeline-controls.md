# 02 - Player Timeline Controls

Status: verbindlich v5

## Ziel

Der Player nutzt die Timeline als zentrales Bedienelement.

Separate Pause-, Zurueckspulen- und Vorspulen-Buttons werden nicht als Hauptbuttons angezeigt.

## Grundaufbau

```text
Vollbild-Video
Bottom Overlay bei Bedienung
Sender-/Titelinformationen
Timeline
sekundaere Aktionen
Stream-Info-Badges
```

## Overlay oeffnen

Wenn kein Overlay sichtbar ist, oeffnet OK das Overlay.

Der Initialfokus liegt auf der Timeline.

## Timeline-Fokus

Die Timeline ist fokussierbar.

Fokusdarstellung:

```text
Cyan-Ring oder Cyan-Glow
sichtbarer Fokuspunkt auf Timeline
leicht erhoehte Timeline-Hoehe
klare Zeitwerte links/rechts
```

## Bedienung

Wenn Timeline fokussiert ist:

```text
OK = Play/Pause
Links/Rechts = Seek oder Timeshift-Bewegung
Lang Links/Rechts = schneller Seek
Zurueck verlaesst Timeline-Fokus oder schliesst Overlay
```

## Live-TV ohne Timeshift

Wenn Timeshift deaktiviert oder fuer den aktuellen Stream nicht verfuegbar ist:

```text
Timeline zeigt Fortschritt der aktuellen Sendung
Links/Rechts zeigen Hinweis
Fokus bleibt moeglich, aber Seek-Aktion ist gesperrt
```

Hinweistext:

```text
Timeshift fuer diesen Sender nicht verfuegbar
```

## Live-TV mit Timeshift

Wenn Timeshift aktiviert und fuer den aktuellen Stream verfuegbar ist:

```text
Timeline zeigt Timeshift-Fenster
Links/Rechts bewegen Position im Timeshift-Fenster
OK pausiert oder setzt fort
Live-Button oder Live-Badge fuehrt zurueck zum Live-Punkt
```

Das Timeshift-Fenster darf die konfigurierte maximale Dauer von 15, 30, 60 oder 120 Minuten nicht ueberschreiten.

Die Speicherwahl `Automatisch`, `RAM` oder `Festplatte` veraendert nicht die Timeline-Bedienung.

Bei Senderwechsel wird das vorhandene Timeshift-Fenster verworfen.

## VOD

Bei Filmen und Episoden:

```text
Timeline zeigt gesamte Laufzeit
Links/Rechts suchen innerhalb der Laufzeit
OK pausiert oder setzt fort
Fortschritt wird gespeichert
```

Automatischer Fortschritt wird nur fuer interne Filme und interne Episoden gespeichert. Catch-Up nutzt zwar eine laufzeitbezogene Timeline, erzeugt aber keinen VOD-Fortschritt und kein Resume-Ziel.

## Sekundaere Aktionen

Unter oder neben der Timeline koennen stehen:

```text
Audio
Untertitel
Bildformat
Mehr
```

Audio- und Untertitelauswahl im Player gilt nur fuer die aktuelle Wiedergabe und aendert keine globalen Wiedergabe-Einstellungen.

Nicht als Hauptbuttons anzeigen:

```text
Pause
Zurueckspulen
Vorspulen
```

## Stream-Info-Badges

Anzeigen:

```text
Qualitaet: HD, Full HD, 4K
FPS: 25 FPS, 50 FPS, 60 FPS
Audio: Mono, Stereo, 5.1
```

Die Position darf Untertitel und wichtige Video-Inhalte nicht dauerhaft verdecken.

## Overlay-Verhalten

```text
OK oeffnet Overlay, wenn es geschlossen ist
Fokus liegt nach Oeffnen auf Timeline
Zurueck schliesst Overlay, wenn es sichtbar ist
erneut Zurueck verlaesst Player
Overlay blendet nach 5 Sekunden Inaktivitaet aus
Fokus wird beim erneuten Oeffnen wiederhergestellt
```
