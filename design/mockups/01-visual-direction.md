# 01 – Visual Direction

Status: Spezifikation v1

## Ziel

Dieses Dokument legt die visuelle Richtung für Vivicast fest.

Vivicast soll wie eine moderne Android-TV-App wirken, aber nicht wie ein überladenes Streaming-Portal.

## Kurzbeschreibung

```text
Dunkel
ruhig
schnell
technisch sauber
fokusstark
inhaltzentriert
```

## Nicht gewünscht

```text
Touch-App-Optik
überladene Startseiten
bunte Portal-Kacheln ohne Ordnung
Autoplay beim Fokus
zu kleine Texte
schwache Fokuszustände
unnötige Blur-Effekte
zu viele Animationen
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
Catch-Up: Violett
```

Beispielwirkung:

```text
Hintergrund dunkel und zurückhaltend
Content-Flächen leicht abgehoben
Fokus klar und hell
Aktionen sparsam akzentuiert
```

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
Skalierung + Rahmen + Schatten
```

Für Buttons:

```text
Akzentfläche + Text auf Akzent
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
Farbe + Rahmen + Formänderung
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
funktional
spaltenbasiert
hohe Informationsdichte
keine Cover-Art-Dominanz
```

## VOD

```text
hero-orientiert
Poster-Raster
ruhiger oberer Informationsbereich
```

## Player

```text
maximal reduziert
Vollbild zuerst
Overlay nur temporär
```

## Einstellungen

```text
Master-Detail
links Bereiche
rechts Optionen
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

Fokusanimation:

```text
120 ms
```

Overlayanimation:

```text
180 ms
```

Wenn Animationen deaktiviert sind:

```text
alle Screens bleiben vollständig verständlich
```

---

# Mockup-Referenzauflösung

```text
1920 x 1080
16:9
```

Safe Area:

```text
64 dp horizontal
48 dp vertikal
```

Mockups müssen später zusätzlich in 720p und 4K gedanklich geprüft werden.

---

# Visueller Qualitätsmaßstab

Ein Mockup ist akzeptabel, wenn:

```text
Fokus sofort erkennbar ist
wichtige Texte groß genug sind
Screen nicht überladen wirkt
D-Pad-Richtung logisch lesbar ist
Fallbacks nicht wie Fehler aussehen
Live-TV schnell bedienbar wirkt
Player nicht vom Overlay dominiert wird
Provider-Isolation sichtbar bleibt
```