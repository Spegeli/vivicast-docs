# TV Compose Reference Guide

Status: verbindlich v3

## Ziel

Dieses Dokument schließt die Lücke zwischen Vivicast-Zieloptik und echter Compose-for-TV-Umsetzung.

Es definiert **keine eigene visuelle Zielrichtung**.

Die High-Fidelity-Renderings, UI Direction Decisions und das Vivicast Designsystem definieren, **wie Vivicast aussehen soll**.

Die TV-/Compose-Referenzen definieren nur, **wie diese Vivicast-Zielwirkung technisch TV-tauglich, wartbar und Compose-gerecht umgesetzt werden kann**.

## Quellenhierarchie für UI-Arbeiten

```text
1. PRD, ADRs, Screen Specs, Wireframes, Interaction und Components
   -> Produktumfang, Verhalten, Navigation, Struktur, Fokuspfade, sichtbare Labels

2. High-Fidelity-Renderings und UI Direction Decisions
   -> visuelle Zielwirkung und freigegebene UI-Richtung

3. design/design-system/compose-template/
   -> technische Vivicast-Tokenwerte und Compose-Designsystem-Startwerte

4. Dieses Dokument und externe TV-/Compose-Referenzen
   -> technische Umsetzungsmechanik, TV-Best-Practices und Compose-Beispiele

5. JetStreamCompose, TvMaterialCatalog und android/tv-samples
   -> Beispielcode und technische Patterns, keine Vivicast-Zieloptik
```

## Technische Referenzen

Codex soll bei TV-UI-Arbeiten diese Referenzen als technische Orientierung nutzen:

```text
android/tv-samples
JetStreamCompose
TvMaterialCatalog
Android Developers: Compose for TV
Android Developers: Design for Android TV
```

Diese Quellen sind **technische Referenzen**, keine Designquelle für Vivicast.

## Konkrete Quellen

Codex darf diese Quellen direkt verwenden, wenn im App-Repo-Kontext Internetzugriff vorhanden ist:

```text
Google TV Samples Repository:
https://github.com/android/tv-samples

JetStreamCompose Sample:
https://github.com/android/tv-samples/tree/main/JetStreamCompose

TV Material Catalog Sample:
https://github.com/android/tv-samples/tree/main/TvMaterialCatalog

Android Developers – Use Jetpack Compose on Android TV:
https://developer.android.com/training/tv/playback/compose

Android Developers – Design for Android TV:
https://developer.android.com/design/ui/tv
```

Wenn Codex lokal mit Internetzugriff arbeitet, darf Codex das `android/tv-samples` Repository außerhalb des Vivicast-App-Projekts als Referenz ansehen.

Die Referenz darf nicht als Submodule, Dependency, kopierter Quellbaum oder Fork in das Vivicast-App-Repo übernommen werden.

## Einordnung

```text
High-Fidelity Renderings
  = visuelle Zielwirkung

UI Direction Decisions
  = freigegebene visuelle Richtung

Vivicast Compose Template
  = technische Tokenwerte und Designsystem-Startwerte

JetStreamCompose
  = Beispiel für Compose-for-TV-Screenmechanik, Focus Handling, Rows, Detailbereiche

TvMaterialCatalog
  = Beispiel für androidx.tv.material3-Komponentenverhalten

android/tv-samples
  = Beispielcode, technische Patterns und API-Verwendung

Android Developers TV Design Docs
  = TV-Best-Practices, Lesbarkeit, D-Pad, Fokus, Navigation
```

JetStreamCompose, TvMaterialCatalog und android/tv-samples ersetzen niemals:

```text
Vivicast High-Fidelity-Renderings
Vivicast Design-Tokens
Vivicast Screen Specs
Vivicast Wireframes
Vivicast Components
Vivicast Interaction Specs
PRD oder ADRs
```

## Grundsatz

Nicht kopieren.

Nicht JetStream nachbauen.

Nicht fremdes Branding, fremde Assets oder fremde Daten übernehmen.

Nicht fremde Screen-Struktur blind übernehmen.

Nutzen als Referenz für:

```text
Focus Handling
D-Pad-Navigation
LazyRows und LazyGrids
Content Rows
Poster Cards
Hero / Featured Area
Detailbereiche
Compose-for-TV-Struktur
androidx.tv.material3-Komponenten
TV-Lesbarkeit
```

Nicht nutzen für:

```text
Vivicast-Optik ersetzen
Vivicast-Farben ersetzen
Vivicast-Spacing ersetzen
Vivicast-Screen-Struktur überschreiben
sichtbare Vivicast-Labels ändern
neue Produktlogik ableiten
fremde Assets übernehmen
```

## Wichtig für Codex

Codex soll High-Fidelity-Renderings nicht pixelgenau nachbauen.

Codex soll die Renderings aber als visuelle Zielwirkung respektieren.

Stattdessen soll Codex:

```text
1. Vivicast PRD, Screens, Wireframes, Components und Interaction lesen
2. Vivicast High-Fidelity-Renderings und UI Direction Decisions für Zielwirkung lesen
3. Vivicast Compose Template für technische Tokenwerte lesen
4. passende TV-/Compose-Referenzen für technische Umsetzung ansehen
5. eigene Vivicast-Komponenten daraus ableiten
6. Screens aus fertigen Vivicast-Komponenten zusammensetzen
```

## Verbindliche Komponentenstrategie

Bei visueller UI-Arbeit erst Komponenten bauen oder verbessern, dann Screens.

Reihenfolge:

```text
1. Vivicast Theme Tokens
2. Focus Modifier / Focus Surface
3. TV Cards
4. Content Rows
5. Hero Panels
6. Screen Layouts
```

Nicht direkt ganze Screens neu erfinden.

## Empfohlene Vivicast-Komponenten

```text
VivicastScreenBackground
VivicastFocusSurface
VivicastGlassPanel
VivicastTopNavigation
VivicastTopNavItem
VivicastContentRow
VivicastPosterCard
VivicastChannelCard
VivicastHeroPanel
VivicastDetailsPanel
VivicastSearchResultCard
VivicastSettingsRow
VivicastPlayerOverlay
VivicastPlayerTimeline
VivicastStatusBadge
VivicastStreamInfoBadge
```

## Design Tokens als Code

Codex soll keine freien Werte raten.

Technische Startwerte liegen unter:

```text
design/design-system/compose-template/
```

Wenn die App-Repo-Baseline ein `:core:designsystem` oder ähnliches Modul verwendet, sollen dort zentrale Tokens verwendet werden für:

```text
Farben
Typografie
Spacing
Corner Radius
Border Width
Focus Glow
Focus Scale
Panel Alpha
Card-Größen
Poster Aspect Ratio
Animation Durations
```

## TV-spezifische Regeln

```text
androidx.tv.material3 bevorzugen, sofern passend
keine mobile-first Material-UI für TV-Screens
D-Pad-Fokus immer sichtbar
Fokus ist nicht nur Farbe, sondern Form, Scale, Ring oder Glow
LazyRows und LazyGrids für große Listen
Screens müssen aus TV-Entfernung lesbar sein
```

## Asset-Regel

Die High-Fidelity-Renderings enthalten AI-generierte Logos, Poster und Backdrops.

Diese nicht als App-Assets übernehmen.

Erlaubt:

```text
kontrollierte Platzhalter
Gradient-Poster
Icon-Placeholder
später echte Providerlogos
später echte Providerposter, falls vorhanden
```

## Workflow für UI-Polish

Nicht die ganze App auf einmal polishen.

Stattdessen:

```text
1. eine Komponente verbessern
2. im Emulator prüfen
3. Screenshot erstellen
4. gegen Vivicast High-Fidelity-Referenz vergleichen
5. erst dann nächste Komponente oder Screen
```

## UI-Alignment Empfehlung

Wenn die App optisch nicht dem freigegebenen Vivicast-Design entspricht, keinen globalen Visual-Pass starten.

Stattdessen:

```text
Compose-for-TV Referenzen ansehen
Vivicast Designsystem gezielt verbessern
Screens nur mit verbesserten Vivicast-Komponenten aktualisieren
keine neuen Features mit UI-Polish vermischen
```

## Nicht-Ziele

```text
kein JetStream Fork
kein Kopieren fremder App-Struktur
keine fremden Assets
kein Pixel-Nachbau der High-Fidelity-Renderings
keine komplette UI-Neuentwicklung in einem Schritt
keine Ableitung von Vivicast-Produktlogik aus Beispielprojekten
```
