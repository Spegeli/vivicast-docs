# 04 - Filme

Status: verbindlich v6

## Zweck

Der Filme-Screen zeigt VOD-Filme nach Provider und Kategorie.

Fachliche Regeln zu Provider-Trennung, Metadaten, Trailern und Wiedergabefortschritt bleiben im PRD.

## Quellen

- `prd/PRD-v1/03-movies-series-requirements.md`
- `design/components/list-grid-items.md`
- `design/interaction/focus.md`

## Layout-Zonen

1. Top Navigation
2. linke Kategorien-Spalte
3. Fokus-Detailbereich oberhalb des Grids
4. rechtes Poster Grid
5. optionale Sortier- oder Filterzeile

## Aufbau

Links steht die Kategorien-Spalte.

Rechts steht das Poster Grid.

Der Fokus-Detailbereich liegt oberhalb des Poster Grids und aktualisiert sich beim Fokussieren eines Films.

## Initialfokus

Beim frischen Öffnen liegt der Fokus auf der ersten Kategorie des ersten Providers.

Globale App-Kategorien wie Favoriten und Fortsetzen sind sichtbar, aber nicht der initiale Inhaltsfokus.

## Kategorien

Die Kategorien-Spalte zeigt zuerst globale App-Kategorien:

- Favoriten
- Fortsetzen, falls nicht abgeschlossene Wiedergabefortschritte existieren

Darunter folgen Provider mit ihren eigenen Kategorien:

```text
Provider A
- Thriller
- Action
- Drama

Provider B
- Thriller
- Action
- Drama
```

Gleichnamige Kategorien verschiedener Provider werden nicht zusammengefuehrt.

Provider-Kategorien werden nicht normalisiert.

## Poster Grid

Jede Poster Card zeigt:

- Poster oder Platzhalter
- Titel
- Jahr, falls vorhanden
- Bewertung, falls vorhanden
- Fortschritt, falls begonnen und nicht abgeschlossen
- Gesehen-Status, falls abgeschlossen
- Favoritenstatus

Nur sichtbare Poster werden geladen.

## Fokus-Detailbereich

Zeigt zum fokussierten Film:

- Titel
- Bewertung
- Genres
- Jahr
- Laufzeit
- Beschreibung
- Besetzung und Regie, falls vorhanden
- primaere Aktion

## Detailseite

OK auf einen Film im Grid oeffnet immer die Detailseite.

Die Detailseite zeigt:

- grosses Poster oder Fallback
- Titel
- Bewertung
- Genre
- Jahr
- Laufzeit
- Beschreibung
- Regisseur
- Schauspieler
- Aktionen

## Aktionen

Neuer Film:

- Abspielen
- Trailer
- Zu Favoriten hinzufügen
- Als gesehen markieren

Begonnener Film:

- Fortsetzen
- Von Anfang an
- Trailer
- Zu Favoriten hinzufügen
- Als gesehen markieren

Gesehener Film:

- Status `Gesehen` anzeigen
- Von Anfang an
- Trailer
- Zu Favoriten hinzufügen
- Als ungesehen markieren

## Abschluss und Fortsetzen

Die feste Abschluss-Schwelle betraegt 95 Prozent und ist keine Einstellung.

Ab 95 Prozent oder beim tatsaechlichen Medienende wird der Film als gesehen dargestellt und aus Fortsetzen entfernt. Die Wiedergabe laeuft beim Erreichen von 95 Prozent weiter.

`Als ungesehen markieren` entfernt auch den gespeicherten Fortschritt; der Film erscheint danach wieder als nicht begonnen.

## Trailer

Wenn eine gültige YouTube-URL vorhanden ist, wird die YouTube-App geöffnet.

Wenn keine URL vorhanden ist, wird YouTube mit Suchbegriff geöffnet:

```text
<Titel> Trailer
```

Wenn die YouTube-App fehlt, wird ein Hinweis angezeigt.

## Bedienung

- Links aus dem Grid fokussiert Kategorien.
- Rechts im Grid bewegt zum nächsten Poster.
- Hoch/Runter bewegt im Grid oder zwischen Bereichen.
- OK auf Poster oeffnet die Detailseite.
- Zurück aus Detailseite kehrt zum Grid zurück.
- Zurück aus Grid fokussiert Kategorien.
- Zurück aus Kategorien springt in die Top Navigation.

## Zustaende

Loading: Poster Grid mit Skeleton Cards.

Empty: Kategorie enthält keine Filme.

No Poster: Gradient- oder Icon-Platzhalter.

No Metadata: fehlende Werte werden ausgelassen, nicht geraten.

Error: Providerdaten können nicht geladen werden.

## Komponenten

- Top Navigation
- Category List
- Provider Group Item
- Poster Card
- Details Panel
- Action Button
- Progress Badge
- Rating Badge
- Favorite Indicator
