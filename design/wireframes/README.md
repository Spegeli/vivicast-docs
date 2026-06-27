# Vivicast Wireframes

Status: verbindlich v3

Dieses Verzeichnis enthält textbasierte Wireframes für die Android-TV-Oberflaeche von Vivicast.

Die Wireframes sind keine finalen Mockups. Sie definieren grobe Struktur, Fokuswege, Zustaende und Screen-Verhalten. Visuelle Details wie Farben, Abstaende, Typografie und Komponentenregeln liegen im Design-System und in den Component Specs.

## Verbindliche Grundlage

- `prd/PRD-v1/`
- `architecture/decisions/`
- `design/screens/`
- `design/interaction/`
- `design/components/`
- `design/design-system/`
- `codex/`

## Globale Hauptnavigation

Alle Hauptscreens verwenden diese Navigation:

`Home | Live-TV | Filme | Serien | Suche | Einstellungen`

Der Player ist ein Fullscreen-Kontext ohne dauerhaft sichtbare Hauptnavigation.

## Wireframe-Ziele

1. Hauptscreens vor der Implementierung validieren.
2. D-Pad-Navigation sichtbar machen.
3. Fokus- und Zurück-Verhalten pro Screen zeigen.
4. Empty-, Loading- und Error-States skizzieren.
5. Provider-Isolation auch im Layout abbilden.
6. Grosse IPTV-Bibliotheken lazy und skalierbar darstellen.

## Dateien

- `00-home.md`
- `01-live-tv-browser.md`
- `02-movies.md`
- `03-series.md`
- `04-search.md`
- `05-settings.md`
- `06-player.md`
- `07-dialogs-states.md`
- `08-about-app.md`

## Notation

- `[ ]`: fokussierbares Element
- `(*)`: aktuell fokussiertes Element
- `{ }`: Status oder Badge
- `< >`: dynamischer Inhalt
- `...`: weitere lazy geladene Inhalte
- `|`: Spaltentrennung

## Fokusnotation

- `UP / DOWN / LEFT / RIGHT`: D-Pad Richtung
- `OK`: Aktion auslösen
- `BACK`: Zurück-Taste
- `CHANNEL UP / CHANNEL DOWN`: Kanalwechsel oder Senderlistenfokus je Kontext

## Screen-Priorität

1. Home
2. Live-TV Browser
3. Player
4. Filme
5. Serien
6. Suche
7. Einstellungen
8. Dialoge und globale Zustaende
9. Über die App Details

Grund: Home ist Startpunkt, Live-TV und Player sind die kritischsten TV-Flows. Danach folgen VOD, Suche, Konfiguration und Detailbereiche.
