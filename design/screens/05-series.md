# 05 - Serien

Status: verbindlich v8

## Zweck

Der Serien-Screen zeigt Serien, Staffeln und Episoden nach Provider und Kategorie.

Fachliche Regeln bleiben im PRD.

## Quellen

- `prd/PRD-v1/03-movies-series-requirements.md`
- `design/components/list-grid-items.md`
- `design/interaction/focus.md`

## Layout-Zonen

1. Top Navigation
2. linke Kategorien-Spalte
3. Fokus-Detailbereich oberhalb des Grids
4. rechtes Serien Grid
5. Serien-Detailseite mit Staffel- und Episodenbereich

## Aufbau

Der Serienbereich folgt demselben Grundaufbau wie Filme.

Links stehen Kategorien, rechts das Serien Grid, darueber der Fokus-Detailbereich.

## Initialfokus

Beim frischen Oeffnen liegt der Fokus auf der ersten Kategorie des ersten Providers.

Globale App-Kategorien wie Favoriten und Fortsetzen sind sichtbar, aber nicht der initiale Inhaltsfokus.

## Kategorien

Oben stehen globale App-Kategorien:

- Favoriten
- Fortsetzen, falls nicht abgeschlossene Wiedergabefortschritte oder eine abgeleitete naechste Episode existieren

Darunter stehen Provider mit eigenen Kategorien.

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

## Serien Grid

Jede Serien Card zeigt Poster oder Fallback, Titel, Jahr oder Zeitraum, Fortschritt und Favoritenstatus.

## Detailseite

OK auf eine Serie oeffnet die Serien-Detailseite.

Die Detailseite enthaelt Serieninformationen, Fortsetzen-Bereich, Staffelauswahl, Episodenliste und Favoriten-Aktion.

## Episoden

OK auf eine Episode startet oder setzt sie direkt fort.

Es gibt keine separate Episode-Detailseite.

Die fokussierte Episode zeigt rechts eine eigene fokussierbare Sekundaeraktion:

- `Als gesehen markieren`, solange die Episode nicht abgeschlossen ist
- `Als ungesehen markieren`, sobald die Episode abgeschlossen ist

Rechts aus der Episode wechselt auf diese Aktion; Links kehrt zur Episode zurueck. Die Aktion wird nur fuer die fokussierte Episode eingeblendet, damit die Liste ruhig bleibt.

## Abschluss und Fortsetzen

Die feste Abschluss-Schwelle betraegt 95 Prozent und gilt nur fuer einzelne Episoden.

Ab 95 Prozent oder beim tatsaechlichen Medienende wird die Episode als gesehen dargestellt und nicht mehr als direktes Resume-Ziel verwendet. Ist eine naechste Episode verfuegbar, kann der Serien-Fortsetzen-Eintrag auf diese Episode bei Position 0 weitergehen.

`Als ungesehen markieren` loescht den gespeicherten Episodenfortschritt. Fuer komplette Staffeln oder Serien gibt es in v1 keine entsprechende Aktion.

## Automatische naechste Folge

Auto-Next ist unter `Einstellungen > Wiedergabe` einstellbar und standardmaessig deaktiviert.

Der Countdown bietet 5, 10, 15 oder 30 Sekunden; Standard sind 10 Sekunden. Bei deaktiviertem Auto-Next bleibt die Auswahl sichtbar, ist aber nicht bedienbar.

Bei deaktiviertem Auto-Next erscheint `Naechste Folge abspielen` erst nach dem tatsaechlichen Episodenende. Ein Start erfolgt nur nach OK.

Bei aktiviertem Auto-Next erscheint `Naechste Folge in X` X Sekunden vor dem tatsaechlichen Episodenende. OK startet sofort; ohne Eingabe startet die naechste Episode beim Ablauf.

Der sichtbare Button `Zurueck` erscheint in beiden Auto-Next-Zustaenden zeitgleich neben dem Hauptbutton. OK auf `Zurueck` oder die Zurueck-Taste verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit dem zuvor gewaehlten Staffel-/Episodenkontext zurueck. Einen Button `Abbrechen` gibt es nicht.

Nach der letzten Episode einer Staffel folgt die erste verfuegbare Episode der naechsten Staffel. Nach der letzten Episode der Serie erscheint kein Auto-Next-Panel.

Das Erreichen der 95-Prozent-Abschluss-Schwelle blendet kein Auto-Next-Panel ein und startet keine naechste Episode. Dafuer bleibt das tatsaechliche Medienende massgeblich.

Das konkrete Player-Layout steht in `design/screens/03-player.md`.

## Bedienung

- Links aus dem Grid fokussiert Kategorien.
- OK auf Serie oeffnet die Detailseite.
- OK auf Fortsetzen startet gespeicherte Position.
- OK auf Episode startet oder setzt fort.
- Rechts aus der fokussierten Episode wechselt auf `Als gesehen markieren` oder `Als ungesehen markieren`.
- Links aus der Markierungsaktion kehrt zur Episode zurueck.
- Zurueck aus Player kehrt zum Serien- oder Episodenkontext zurueck.
- Zurueck aus Episodenliste geht zur Serien-Detailseite.
- Zurueck aus Serien-Detailseite geht ins Serien Grid.
- Zurueck aus Grid fokussiert Kategorien.
- Zurueck aus Kategorien springt in die Top Navigation.

## Zustaende

Loading: Skeleton Cards und Episoden-Platzhalter.

Empty: Kategorie enthaelt keine Serien.

No Metadata: fehlende Werte werden nicht geraten.

No Episodes: Serie bleibt sichtbar, Episodenbereich zeigt Hinweis.

Error: Providerdaten koennen nicht geladen werden.

## Komponenten

- Top Navigation
- Category List
- Provider Group Item
- Poster Card
- Details Panel
- Season Selector
- Episode Row
- Progress Badge
- Rating Badge
- Favorite Indicator
