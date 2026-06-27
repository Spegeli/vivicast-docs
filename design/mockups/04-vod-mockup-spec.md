# 04 – VOD Mockup Specification

Status: aktive High-Fidelity-Zielbeschreibung v7

Diese Datei beschreibt die visuelle Absicht für Filme und Serien. Exakte Farben, Spacing, Radien, Typografie und Fokuswerte liegen zentral unter `design/design-system/compose-template/`.

## Grundstruktur

Filme und Serien nutzen die gleiche VOD-Grundstruktur:

```text
Provider-/Kategorien-Spalte | Hero-/Detailbereich + Poster-Raster
```

Links:

```text
Favoriten
Fortsetzen, falls vorhanden
Provider A
  Provider-Kategorien
Provider B
  Provider-Kategorien
```

Rechts:

```text
Hero-/Detailbereich
Poster-Raster
```

Gleichnamige Kategorien verschiedener Provider werden nicht zusammengefuehrt.

## Filme Übersicht

Zeigt links die Kategorien-Spalte und rechts:

```text
Hero-Informationen zum fokussierten Film
Poster-Grid
```

Poster Card zeigt:

```text
Poster oder Fallback
Titel
Jahr
Bewertung
Fortschritt, falls begonnen und nicht abgeschlossen
Gesehen-Status, falls abgeschlossen
Favoritenstatus
```

OK auf Poster oeffnet die Film-Detailseite.

## Film-Detailseite

Zeigt:

```text
Poster
Titel
Bewertung
Genre
Jahr
Laufzeit
Beschreibung
Regie
Besetzung
Aktionen
```

Aktionen je Zustand folgen der PRD.

## Serien Übersicht

Analog Filme, aber für Serien.

OK auf Serie oeffnet die Serien-Detailseite.

## Serien-Detailseite

Zeigt:

```text
Serientitel
Metadaten
Beschreibung
Fortsetzen
Favoriten-Aktion
Staffel-Auswahl
Episodenliste
```

Episode Row zeigt:

```text
Thumbnail oder Fallback
Folge / Titel
Beschreibung
Laufzeit
Fortschritt oder Gesehen-Status
fokussierte Markierungsaktion
```

Nur die fokussierte Episode zeigt `Als gesehen markieren` oder `Als ungesehen markieren`.

## Visuelle Quellen

Aktuelle Zielbilder:

```text
design/mockups/high-fidelity/rendered/03_movies_overview.png
design/mockups/high-fidelity/rendered/04_series_detail.png
```

Konkrete Tokenquelle:

```text
design/design-system/compose-template/
```


## Label-Konsistenz

Sichtbare Favoriten-Aktionen verwenden `Zu Favoriten` oder `Zu Favoriten hinzufügen`. Merkliste-/Meine-Liste-Terminologie ist in v1 nicht zulässig.
