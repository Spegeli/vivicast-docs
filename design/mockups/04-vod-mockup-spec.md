# 04 – VOD Mockup Specification

Status: Spezifikation v2

## Ziel

Diese Spezifikation beschreibt die visuellen Mockups für Filme und Serien.

Filme und Serien teilen sich die Grundstruktur:

```text
Hero-Info oben
Kategorie-Chips darunter
Poster-Raster darunter
```

## Referenz

```text
Wireframes:
- design/wireframes/02-movies.md
- design/wireframes/03-series.md
Design-System:
- design/design-system/
```

## Bildformat

```text
1920 x 1080
16:9
Dark Theme
```

---

# Variante 1: Filme Übersicht

## Komposition

```text
Top Bar
Hero Info Panel
Kategorie-Chips
Poster-Raster
```

## Hero Info

Inhalt:

```text
Titel
Bewertung
Genre
Jahr
Laufzeit
Beschreibung
```

Beispiel:

```text
Dune: Part Two
★ 8.5   Action • Sci-Fi • 2024 • 166 Min
Paul Atreides vereint sich mit Chani und den Fremen...
```

## Visueller Stil

```text
Hero Panel dunkel
optional stark abgedunkelter Backdrop
Text linksbündig
maximal 3-4 Zeilen Beschreibung
```

## Kategorie-Chips

```text
Favoriten
Fortsetzen
Action
Drama
Thriller
Horror
Science Fiction
```

Aktiver Chip:

```text
Akzentlinie oder aktive Pill-Fläche
```

Fokussierter Chip:

```text
Fokusrahmen + stärkere Fläche
```

## Poster-Raster

Postergrößen:

```text
160 x 240 dp Standard
Fokus-Skalierung 1.06x
```

Inhalt im Mockup:

```text
1 fokussiertes Poster
1 Fortsetzen-Film mit Fortschrittsbalken
1 Favorit
1 gesehen
1 fehlendes Poster
```

Primärfokus:

```text
erstes Poster im Raster
```

---

# Variante 2: Film Detail

## Komposition

```text
Poster links
Metadaten rechts
Beschreibung
Aktionen
```

Aktionen bei begonnenem Film:

```text
Fortsetzen
Von Anfang an
Trailer
Zu Favoriten
Als gesehen markieren
```

Aktionen bei gesehenem Film:

```text
Gesehen Badge
Von Anfang an
Trailer
Zu Favoriten
Als ungesehen markieren
```

Primärfokus:

```text
Fortsetzen
```

## Visuelle Details

```text
Poster groß und ruhig
Aktionen als große TV-Buttons
Text nicht zu breit laufen lassen
Metadaten als dezente Zeile
```

---

# Variante 3: Serien Übersicht

Analog Filme, aber mit Serien-spezifischen Metadaten.

Hero Inhalt:

```text
Titel
Bewertung
Genre
Jahr
Beschreibung
```

Card Zusatz bei Fortsetzen:

```text
S02E07 • 42 %
```

---

# Variante 4: Serien Detail

## Komposition

```text
Titel / Beschreibung oben
Fortsetzen Card
Aktionen
Staffel Chips
Episodenliste
```

## Fortsetzen Card

```text
Fortsetzen
Staffel 2 Folge 7
00:18:42 / 00:44:00
[Fortsetzen] [Von Anfang an]
```

## Staffel-Auswahl

```text
Staffel 1
Staffel 2
Staffel 3
...
```

Aktive Staffel klar markieren.

## Episodenliste

Episode mit vollständigen Daten:

```text
Thumbnail
Folge 7 – Titel
Beschreibung
44 Min • 42 %
bei Fokus: [Als gesehen markieren]
```

Abgeschlossene Episode:

```text
Thumbnail
Folge 8 – Titel
44 Min • Gesehen
bei Fokus: [Als ungesehen markieren]
```

Nur die fokussierte Episode zeigt den Markierungsbutton. Es gibt keinen entsprechenden Button fuer Staffel oder Serie.

Fallback:

```text
Folge 1
Folge 2
Folge 3
```

---

# Fallback-Anforderungen

Mockups müssen zeigen:

```text
fehlendes Poster
fehlendes Thumbnail
fehlende Bewertung
fehlende Beschreibung
begonnener Inhalt
Favorit
gesehen
```

Fallbacks dürfen nicht wie technische Fehler aussehen.

---

# Akzeptanz für visuelle Mockups

```text
Hero-Info ist sofort verständlich
Fokus im Poster-Raster ist eindeutig
Aktive Kategorie ist sichtbar
Fortsetzen- und Gesehen-Zustände sind verständlich
Manuelle Gesehen-/Ungesehen-Aktionen sind bei Film und fokussierter Episode eindeutig
Fehlende Poster wirken sauber
Detailseiten haben klare Primäraktion
Serien-Detail macht Staffel/Episode logisch sichtbar
UI bleibt ruhig und TV-tauglich
```