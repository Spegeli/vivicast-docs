# 02 – Live-TV Mockup Specification

Status: Spezifikation v2

## Ziel

Das Live-TV-Mockup konkretisiert den wichtigsten Vivicast-Screen visuell.

Der Screen muss auf den ersten Blick zeigen:

```text
Provider/Kategorien links
Senderliste mittig
EPG/Vorschau rechts
aktueller Fokus eindeutig sichtbar
```

## Referenz

```text
Wireframe: design/wireframes/01-live-tv-browser.md
Design-System: design/design-system/
```

## Bildformat

```text
1920 x 1080
16:9
Dark Theme
```

## Layout

```text
Safe Area: 64 dp horizontal, 48 dp vertikal
Top Bar: 72 dp
Content Höhe: Rest
```

Spalten:

```text
Provider/Kategorien: 300 dp
Senderliste: 620 dp
EPG/Vorschau: restliche Breite
Spaltenabstand: 16 dp
```

## Screen-Komposition

```text
┌──────────────────────────────────────────────────────────────────────────────────────────────┐
│ Vivicast                         Home  Live-TV  Filme  Serien  Suche  Einstellungen     │
├───────────────────┬──────────────────────────────────────┬───────────────────────────────────┤
│ Provider          │ Sender                               │ EPG / Vorschau                    │
│                   │                                      │                                   │
│ Live-TV Favoriten │ FOKUS: ARD HD                         │ Vorschauplatzhalter               │
│ ▼ Provider A      │ weitere Sender                        │ EPG Informationen                 │
│   News            │                                      │ Aktionen                          │
│   Sport           │                                      │                                   │
│ ▶ Provider B      │                                      │                                   │
└───────────────────┴──────────────────────────────────────┴───────────────────────────────────┘
```

## Zustand nach erstem OK in der Senderspalte

```text
Provider/Kategorien werden ausgeblendet.
Senderliste bleibt links sichtbar und markiert den ausgewaehlten Sender als aktiv.
Sender-EPG erscheint in der mittleren Spalte.
Live-Vorschau startet gleichzeitig rechts.
Der sichtbare Fokus liegt auf der aktuellen Sendung im Sender-EPG, sofern vorhanden.
Das naechste OK auf dieser Sendung oeffnet Vollbild.
```

Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK.

## Visuelle Details

### Hintergrund

```text
App Background: #070A0F
Panels: #0B1220 / #111827
Spaltentrenner: dezente Linien mit geringer Deckkraft
```

### Top Bar

Inhalt:

```text
Vivicast links
Hauptnavigation rechts
```

Aktiver Bereich:

```text
Live-TV mit Akzent-Unterstrich oder aktiver Pill-Fläche
```

### Provider/Kategorien

Provider Row:

```text
Höhe: 56 dp
Text: 18-20 sp
Icon: Expand/Collapse
```

Kategorie Row:

```text
Höhe: 48 dp
Einzug: 28 dp
```

Aktive Kategorie:

```text
leicht aufgehellter Hintergrund
Akzentlinie links
```

Fokussierte Kategorie:

```text
Fokusrahmen oder starke Hintergrundfläche
```

### Senderliste

Sender Row:

```text
Höhe: 96 dp
Logo: 64 x 40 dp
Titel: 20 sp SemiBold
Aktuelle Sendung: 16-18 sp
Progress: 6 dp hoch
```

Fokussierter Sender:

```text
Hintergrund #1E3A5F
Fokusrahmen #7DD3FC
leichte Skalierung 1.02
```

Nicht fokussierte Sender:

```text
dunkle Karte
leichte Trennlinie
```

### EPG/Vorschau Panel

Oben:

```text
Vorschaufläche 16:9
Fallback-Text: Vorschau noch nicht gestartet
```

Darunter:

```text
Sendername
Aktuelle Sendung
Zeit
Beschreibung
Nächste Sendung
Aktionen
```

Aktionen:

```text
Ansehen
Catch-Up
Favorit
Mehr
```

Fokus auf Aktion:

```text
Akzentfläche
```

## Status-Badges

```text
LIVE: Rot
Catch-Up: Violett
Favorit: Gelb
Aktualisierung läuft: Blau
Verbindungsfehler: Rot
Deaktiviert: Grau
```

Badges immer mit Text oder eindeutigem Symbol.

## Fallbacks im Mockup darstellen

Mockup soll mindestens enthalten:

```text
1 Sender mit Logo
1 Sender ohne Logo
1 Sender ohne EPG
1 Sender mit Favorit
1 Sender mit Catch-Up
1 Provider mit Verbindungsfehler oder Aktualisierung läuft
```

## Fokuszustand im Mockup

Primärfokus:

```text
ARD HD in Senderliste
```

Der Fokus muss so deutlich sein, dass er auch ohne Erklärung sichtbar ist.

## Akzeptanz für visuelles Mockup

```text
Fokus auf ARD HD sofort sichtbar
Provider-Isolation erkennbar
Globale Live-TV Favoriten oberhalb des ersten Providers erkennbar
EPG/Vorschau rechts klar zugeordnet
Keine Autoplay-Wirkung beim bloßen Fokus
Erstes OK zeigt Sender-Modus, aktive Preview und Fokus auf aktueller EPG-Sendung
Zweites OK auf der aktuellen EPG-Sendung oeffnet Vollbild
Keine Preview-Einstellung sichtbar
Sender ohne Logo wirkt sauber, nicht kaputt
Sender ohne EPG bleibt normal nutzbar
Screen wirkt bedienbar, nicht dekorativ
```
