# 03 – Player Mockup Specification

Status: aktive High-Fidelity-Zielbeschreibung v6

Diese Datei beschreibt die visuelle Absicht für Player-Mockups. Exakte Farben, Spacing, Radien, Typografie und Fokuswerte liegen zentral unter `design/design-system/compose-template/`.

## Ziel

Der Player ist ein Fullscreen-Kontext mit minimaler, hochwertiger Overlay-UI.

```text
Fullscreen zuerst
keine Top Navigation
Bottom Overlay nur bei Bedienung
Timeline als primaeres Bedienelement
starker cyanfarbener Timeline-Fokus
```

## Fullscreen-Zustand

Standard:

```text
Videobild ohne sichtbare UI
```

UI erscheint durch:

```text
OK
Statuswechsel
Fehler
Auto-Next Panel
```

## Live-TV Overlay

Zeigt:

```text
Senderlogo
Sendername
aktuelle Sendung
Zeitbereich
verbleibende Minuten
Beschreibung
nächste Sendung
Stream-Info-Badges
Timeline
Audio
Untertitel
Bildformat
Mehr
```

Ein EPG-Chip ist im Player-Overlay nicht vorgesehen.

Die Timeline zeigt den Fortschritt der aktuellen Sendung oder das Timeshift-Fenster.

## VOD Overlay

Zeigt:

```text
Titel
aktuelle Position
Gesamtlaufzeit
Fortschritt
Timeline
Audio
Untertitel
Bildformat
Stream-Info-Badges
```

Beschreibung und Poster werden im VOD-Overlay nicht angezeigt.

## Timeline

Die Timeline ist das primaere fokussierbare Element.

Bedienung:

```text
OK = Play/Pause
Links/Rechts = Seek oder Timeshift-Bewegung
Lang Links/Rechts = schneller Seek
```

Keine separaten Hauptbuttons für:

```text
Pause
Zurückspulen
Vorspulen
```

## Auto-Next Panel

Nur für Serienepisoden im internen Player.

Zustaende:

```text
Auto-Next Aus -> Nächste Folge abspielen | Zurück
Auto-Next Ein -> Nächste Folge in X | Zurück
```

Kein Button `Abbrechen`.

## Visuelle Quelle

Aktuelle Zielbilder:

```text
design/mockups/high-fidelity/rendered/06_player_overlay_live_tv.png
```

Konkrete Tokenquelle:

```text
design/design-system/compose-template/
```
