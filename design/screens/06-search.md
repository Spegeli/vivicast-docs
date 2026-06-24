# 06 - Suche

Status: verbindlich v8

## Zweck

Die Suche findet Kanäle, Filme, Serien und EPG-Inhalte zentral.

Episoden werden nicht als eigene Suchgruppe angezeigt. Nutzer erreichen Episoden ueber die Serien-Detailseite.

Fachliche Suchregeln bleiben im PRD.

## Quellen

- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `architecture/decisions/ADR-005-local-search.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `design/interaction/focus.md`
- `design/components/list-grid-items.md`

## Layout-Zonen

1. Top Navigation
2. Suchfeld mit Sprachsuche-Aktion
3. Suchhistorie
4. Ergebnisgruppen
5. Detailbereich fuer fokussiertes Ergebnis

## Initialfokus

Beim Oeffnen liegt der Fokus auf dem Suchfeld.

## Suchfeld

Das Suchfeld steht im oberen Bereich des Screens.

OK auf dem Suchfeld oeffnet die System-Tastatur.

Eigene On-Screen-Tastatur ist fuer v1 nicht vorgesehen.

Die Sprachsuche ist eine eigene fokussierbare Aktion und startet nie automatisch.

## Suchhistorie

Suchhistorie wird dauerhaft im Suchscreen angezeigt.

Sie bleibt sichtbar, auch wenn Suchfeld und Ergebnisgruppen gefuellt sind.

Eintraege werden als Chips dargestellt.

Unterstuetzt:

- Suchbegriff erneut verwenden
- einzelnen Eintrag loeschen
- gesamten Verlauf loeschen

## Ergebnisgruppen

Ergebnisse werden gruppiert nach:

- Kanäle
- Filme
- Serien
- EPG

Jede Gruppe wird als horizontale Row dargestellt.

Es gibt keine verpflichtende `Alle anzeigen`-Aktion.

Jede Gruppe zeigt hoechstens die technisch gelieferten v1-Treffer an.

EPG-Ergebnisse erscheinen erst, wenn die Suche die fachliche Mindestlaenge erreicht.

## Detailbereich

Der Detailbereich zeigt zum fokussierten Ergebnis:

- Ergebnistyp
- Titel
- Provider, falls relevant
- Kategorie oder Kontext
- Beschreibung, falls vorhanden
- primaere Aktion

## OK auf Ergebnis

- Kanal-Ergebnis oeffnet den zugehoerigen Sender im Live-TV-Browser. Der weitere Preview-/Vollbildablauf folgt der Live-TV-Spezifikation.
- Film oeffnet Film-Detailseite.
- Serie oeffnet Serien-Detailseite.
- EPG oeffnet Live-TV Sender mit fokussiertem EPG-Eintrag.

## Bedienung

- OK auf Suchfeld oeffnet System-Tastatur.
- OK auf Sprachsuche startet Android-Sprachsuche.
- Runter wechselt zu Historie oder Ergebnissen.
- Links/Rechts navigiert innerhalb einer Gruppe.
- Hoch/Runter wechselt zwischen Gruppen.
- OK auf Ergebnis oeffnet Ziel gemaess Ergebnistyp.
- Zurueck schliesst zuerst Texteingabe, danach springt die Suche in die Top Navigation.

## Zustaende

Initial: Suchfeld und Suchhistorie sichtbar.

Typing: Ergebnisse aktualisieren nach Debounce, Suchhistorie bleibt sichtbar.

Short Query: Suchhistorie bleibt sichtbar; EPG-Ergebnisse muessen noch nicht erscheinen.

Loading: Ergebnisgruppen zeigen Platzhalter.

Empty: keine Ergebnisse mit kurzem Hinweis, Suchhistorie bleibt sichtbar.

Error: Suche konnte nicht ausgefuehrt werden.

## Komponenten

- Search Field
- Voice Search Action
- Search History Chip
- Content Row
- Channel Card
- Poster Card
- Search Result Card
- Details Panel
- Empty State
