# 01 – Visual Direction

Status: visuelle Übersicht v2

## Rolle

Diese Datei beschreibt die übergeordnete visuelle Richtung für Vivicast.

Sie ist keine Token-Quelle und keine pixelgenaue Mockup-Spezifikation. Exakte Farben, Spacing, Radien, Typografie und Fokuswerte liegen zentral unter:

```text
design/design-system/compose-template/
```

Aktuelle High-Fidelity-Renderings liegen unter:

```text
design/mockups/high-fidelity/rendered/
```

## Zielbild

Vivicast soll wie eine hochwertige Android-TV-App wirken:

```text
dunkel
ruhig
schnell
technisch sauber
fokusstark
inhaltzentriert
TV-first
```

Vivicast soll nicht wie ein überladenes Streaming-Portal, eine Touch-App oder ein Web-Dashboard wirken.

## Nicht gewünscht

```text
Touch-App-Optik
Bottom Navigation
überladene Startseiten
bunte Portal-Kacheln ohne Ordnung
Autoplay beim Fokus
zu kleine Texte
schwache Fokuszustände
unnötige Blur-Effekte
zu viele Animationen
Mobile-first-Layouts
```

## Gewünscht

```text
klare dunkle Flächen
starke Fokusrahmen
gute Lesbarkeit aus TV-Entfernung
saubere Spaltenlayouts
ruhige Poster-Raster
reduzierte Overlays
sichtbare Status-Badges
kontrollierte Fallbacks bei fehlenden Bildern
```

---

# Farbrichtung

Basis:

```text
Background: sehr dunkles Navy/Black
Surface: dunkles Blau-Grau
Focus: helles Cyan/Blau
Accent: Cyan/Blau
Error: Rot
Warning: Amber
Favorite: Gelb
Catch-Up: Blau
```

Die technische Farbdefinition liegt im Compose-Template. Diese Datei beschreibt nur die Zielwirkung.

---

# Typografie-Richtung

```text
Titel groß und ruhig
Listen gut lesbar
Metadaten kleiner, aber nicht dünn
keine Mini-Texte
keine extrem schmalen Schriftschnitte
```

TV-Regel:

```text
Wichtige Texte müssen aus 2-3 Metern Entfernung lesbar sein.
```

---

# Fokus-Stil

Primärer Fokus:

```text
heller Fokusrahmen
leicht vergrößerte Karte oder Zeile
aufgehellter Hintergrund
subtiler Glow
```

Für Listen:

```text
volle Zeilenfläche + Rahmen
```

Für Poster:

```text
Skalierung + Rahmen + Schatten/Glow
```

Für Buttons:

```text
Akzentfläche oder klarer Akzentrahmen
```

## Fokus darf nicht subtil sein

Falsch:

```text
nur minimale Farbänderung
nur Schatten
nur kleine Textänderung
```

Richtig:

```text
Farbe + Rahmen + Form-/Flächenänderung
```

---

# Bildsprache

Vivicast verlässt sich auf Providerdaten.

Das bedeutet:

```text
Senderlogos können fehlen
Poster können fehlen
Backdrops können fehlen
Metadaten können inkonsistent sein
```

Das UI darf dadurch nicht kaputt wirken.

Fallback-Flächen sollen bewusst gestaltet sein:

```text
dunkle Karte
Icon
Fallback-Text
Titel sichtbar
```

---

# Layout-Richtung

## Live-TV

```text
spaltenbasiert
Provider-Isolation sichtbar
hohe Informationsdichte
schnelle Sendernavigation
keine Cover-Art-Dominanz
```

## VOD

```text
Provider-/Kategorien-Spalte links
Hero-/Detailbereich rechts
Poster-Raster rechts
ruhige obere Informationszone
```

## Player

```text
Vollbild zuerst
keine Top Navigation
Overlay nur temporär
Timeline als primärer Fokus
```

## Einstellungen

```text
Master-Detail
links Bereiche
rechts Optionen
Dialoge nur bei Bedarf
keine komplexen Tabellen
```

---

# Animationen

Richtung:

```text
kurz
direkt
unterstützend
nicht verspielt
```

Wenn Animationen deaktiviert sind:

```text
alle Screens bleiben vollständig verständlich
```

Konkrete Motion-Werte liegen im Compose-Template.

---

# Mockup-Referenz

Die aktuelle visuelle Referenz sind die High-Fidelity-Renderings:

```text
design/mockups/high-fidelity/rendered/
```

Die technische Designreferenz bleibt:

```text
16:9
1920 x 1080 als Artboard-Referenz
dp/sp-basierte Compose-Umsetzung
```

Die aktuellen PNGs müssen nicht exakt 1920 x 1080 groß sein, solange sie 16:9-Zielwirkung und TV-Lesbarkeit zeigen.

---

# Visueller Qualitätsmaßstab

Ein Mockup oder eine spätere Compose-Umsetzung ist akzeptabel, wenn:

```text
Fokus sofort erkennbar ist
wichtige Texte groß genug sind
Screen nicht überladen wirkt
D-Pad-Richtung logisch lesbar ist
Fallbacks nicht wie Fehler aussehen
Live-TV schnell bedienbar wirkt
Player nicht vom Overlay dominiert wird
Provider-Isolation sichtbar bleibt
Settings klar in Gruppen getrennt sind
```
