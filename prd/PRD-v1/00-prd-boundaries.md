# PRD Boundaries

Status: verbindlich v1

## Zweck

Diese Datei legt fest, welche Informationen im PRD stehen und welche Informationen in Design- oder Architekturdateien gehoeren.

## PRD ist Hauptquelle für

- Produktumfang
- fachliche Anforderungen
- fachliche Datenregeln
- Plattformanforderungen
- Nicht-Ziele
- Akzeptanzkriterien

## PRD ist nicht Hauptquelle für

- konkrete Screen-Layouts
- Fokuspfade
- D-Pad-Navigation je Screen
- UI-Komponentenlisten
- visuelle Tokens
- Wireframe-Details

## Verweise für Layout und Bedienung

Wenn ein PRD-Abschnitt Layout oder Bedienung beruehrt, soll er auf die passende Designquelle verweisen.

Quellen:

- `design/screens/`
- `design/wireframes/`
- `design/interaction/`
- `design/components/`
- `design/design-system/`

## Regel für spätere Bereinigung

Bestehende Layoutdetails im PRD sollen nicht erweitert werden.

Wenn sie überarbeitet werden, sollen sie gekuerzt und durch Verweise auf die passende Designquelle ersetzt werden.
