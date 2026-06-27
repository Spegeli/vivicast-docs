# 02 - Wireframe: Filme

Status: verbindlich v5

## Zweck

Der Filmbereich zeigt VOD-Filme nach Provider und Kategorie.

## Übersicht

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+------------------+-------------------------------------------------------------+
| KATEGORIEN       | DETAILBEREICH                                               |
| [Favoriten]      | <Filmtitel>                                                |
| [Fortsetzen]     | <Bewertung> | <Genre> | <Jahr> | <Laufzeit>              |
| v Provider A     | <Beschreibung>                                             |
|   (*)[Thriller]  +-------------------------------------------------------------+
|   [Action]       | POSTER GRID                                                 |
|   [Drama]        | (*)[Poster] [Poster] [Kein Bild] [Poster] [Poster] ...      |
| v Provider B     | <Titel>  <Titel>  <Titel>                                  |
|   [Thriller]     |                                                             |
|   [Action]       |                                                             |
+------------------+-------------------------------------------------------------+
```

## Initialfokus

```text
erste Kategorie des ersten Providers
```

Globale Kategorien wie Favoriten und Fortsetzen sind sichtbar, aber nicht der initiale Inhaltsfokus.

## Kategorien

```text
Favoriten
Fortsetzen, falls nicht abgeschlossener Fortschritt existiert
Provider A
- Provider-Kategorien
Provider B
- Provider-Kategorien
```

Gleichnamige Kategorien verschiedener Provider werden nicht zusammengefuehrt.

## Fokuswege

```text
LEFT aus Grid       -> Kategorien
RIGHT im Grid       -> nächstes Poster
UP/DOWN im Grid     -> Reihe wechseln
OK auf Poster       -> Film-Detailseite
BACK Detailseite    -> Grid
BACK Grid           -> Kategorien
BACK Kategorien     -> Top Navigation
```

## Poster Card

Filme und Serien verwenden denselben Poster-Card-Stil.

```text
Poster oder Fallback
Titel
Jahr / Bewertung optional
Fortschritt optional, solange nicht abgeschlossen
Gesehen-Status optional
Favoritenstatus optional
```

## Detailseite

```text
+--------------------------------------------------------------------------------+
| BACK                                                                           |
+----------------------+---------------------------------------------------------+
| Poster / Fallback    | <Filmtitel>                                             |
|                      | Bewertung | Genre | Jahr | Laufzeit                    |
|                      | Beschreibung                                             |
|                      | Regie / Cast soweit vorhanden                            |
|                      | (*)[Abspielen] [Trailer] [Zu Favoriten] [Als gesehen markieren] |
+----------------------+---------------------------------------------------------+
```

Begonnener Film:

```text
(*)[Fortsetzen] [Von Anfang an] [Trailer] [Zu Favoriten] [Als gesehen markieren]
```

Gesehener Film:

```text
{Gesehen}
(*)[Von Anfang an] [Trailer] [Zu Favoriten] [Als ungesehen markieren]
```

Ab 95 Prozent oder beim tatsaechlichen Medienende wird `{Gesehen}` gesetzt und der Film aus Fortsetzen entfernt. Die Wiedergabe endet dadurch nicht automatisch.

## Trailer

```text
YouTube-URL vorhanden -> YouTube-App
ohne URL              -> YouTube-Suche mit "<Titel> Trailer"
YouTube-App fehlt     -> Hinweisdialog
```

## Zustaende

```text
Loading      -> Skeleton Poster Cards
Empty        -> Keine Filme in dieser Kategorie
No Poster    -> Fallback Card
No Metadata  -> fehlende Werte auslassen
Error        -> Inline im betroffenen Bereich
```
