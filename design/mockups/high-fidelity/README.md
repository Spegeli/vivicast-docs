# High-Fidelity Mockups

Status: v4

Dieses Verzeichnis beschreibt die hochwertige visuelle Zielrichtung von Vivicast.

## Rolle

Die Markdown-Dateien in diesem Ordner beschreiben die aktive High-Fidelity-UI-Richtung.

Die gerenderten PNGs unter `rendered/` sind visuelle Stilreferenzen. Sie sind keine normative Quelle fuer Navigation, Labels oder UI-Texte. Falls sichtbare Navigation oder Labels in PNGs von aktuellen PRD-, Design- oder Wireframe-Markdown-Quellen abweichen, gelten die Markdown-Quellen.

Die aktuelle Top Navigation ist:

```text
Home | Live-TV | Filme | Serien | Suche | Einstellungen
```

## Aktive Markdown-Dateien

- `README.md`
- `01-visual-target.md`
- `02-ui-direction-decisions.md`

## Aktive Renderings

Aktuelle High-Fidelity-Renderings werden direkt unter folgendem Ordner abgelegt:

```text
rendered/
```

Der Ordner `rendered/` enthaelt zusaetzlich eine eigene `README.md` mit Regeln fuer aktuelle Renderings und Archivabgrenzung.

Aktuell neu zu erzeugende v1-Renderings sollen direkt in `rendered/` liegen, nicht in einem zusaetzlichen `current/`-Unterordner.

## Archiv

Historische Renderings liegen unter:

```text
rendered/archive/
```

Codex muss `rendered/archive/` fuer aktuelle App-Umsetzung ignorieren, ausser der Owner fordert ausdruecklich einen historischen Vergleich an.

Archivierte Renderings duerfen keine aktuellen PRD-, ADR-, Design-, Interaction-, Component- oder Codex-Regeln ueberschreiben.

## Aktive Umsetzungsquelle

- `02-ui-direction-decisions.md`

## Einordnung

Low-Fidelity prueft Struktur, Dichte und Fokuspositionen.

High-Fidelity prueft Zieloptik, visuelle Qualitaet und UI-Richtung.

Aktive UI-Entscheidungen stehen in `02-ui-direction-decisions.md`.

Die echte Umsetzung erfolgt spaeter in Jetpack Compose for TV.
