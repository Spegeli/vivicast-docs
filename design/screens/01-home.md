# 01 - Home

Status: verbindlich v4

## Zweck

Home ist fester Bestandteil von Vivicast und der Standard-Startscreen.

Es bietet schnellen Zugriff auf Fortsetzen und zuletzt gesehene Live-TV-Sender.

Fachliche Anforderungen bleiben im PRD. Diese Datei beschreibt Screen-Aufbau und Bedienung.

## Quellen

- `design/interaction/nav.md`
- `design/interaction/focus.md`
- `design/components/list-grid-items.md`

## Layout-Zonen

1. Top Navigation
2. Hero Bereich
3. Fortsetzen Row
4. Zuletzt gesehene Live-TV-Sender Row

## Home Rows

Vorerst enthalten:

- Fortsetzen
- Zuletzt gesehene Live-TV-Sender

Nicht enthalten:

- Favoriten Row
- Provider- oder Update-Statusbereich

Provider- und Update-Status werden nur in den Einstellungen angezeigt.

## Fortsetzen Row

Fortsetzen zeigt Filme und Serien gemischt in einer gemeinsamen Row.

Direkte Resume-Ziele sind nur nicht abgeschlossene Filme und Episoden. Wenn die zuletzt relevante Episode abgeschlossen und eine nächste Episode vorhanden ist, darf der Serien-Eintrag auf diese nächste Episode bei Position 0 weitergehen.

Abgeschlossene Filme und Episoden selbst erscheinen nicht in der Row.

Live-TV erscheint nicht in Fortsetzen.

## Zuletzt gesehene Live-TV-Sender Row

Diese Row zeigt zuletzt gesehene Live-TV-Sender.

Sie ist getrennt von Fortsetzen.

## Hero Bereich

Der Hero Bereich zeigt das erste Element aus Fortsetzen.

Wenn Fortsetzen leer ist, zeigt der Hero Bereich den ersten zuletzt gesehenen Live-TV-Sender.

Wenn beide Bereiche leer sind, wird der Empty State angezeigt.

## Initialfokus

Der Initialfokus liegt auf dem ersten relevanten Inhalt in Fortsetzen.

Wenn Fortsetzen leer ist, liegt der Fokus auf dem ersten Eintrag in zuletzt gesehenen Live-TV-Sendern.

Wenn auch dort keine Inhalte existieren, liegt der Fokus auf der Aktion `Wiedergabeliste hinzufügen` im Empty State.

## Fokus bei Rueckkehr

Home startet bei Rueckkehr fresh.

Ein vorheriger Home-Fokus wird nicht wiederhergestellt.

## Bedienung

- Links/Rechts wechselt Elemente innerhalb einer Row.
- Hoch/Runter wechselt zwischen Rows.
- OK oeffnet Inhalt oder Zielbereich.
- Zurück springt zuerst in die Top Navigation.
- Erst aus der Top Navigation greift die doppelte Zurück-Bestätigung zum Beenden.

## Zustaende

Loading: Skeleton Rows mit Platzhaltern.

Empty: Hinweis plus Aktion `Wiedergabeliste hinzufügen`.

Error: Fehler wird nur angezeigt, wenn Home-Inhalte nicht geladen werden können.

Provider- oder Update-Status erscheint nicht im Home-Bereich.

## Komponenten

- Top Navigation
- Hero Panel
- Content Row
- Poster Card
- Channel Card
- Empty State
- Primary Action Button
