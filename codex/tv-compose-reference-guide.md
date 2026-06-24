# TV Compose Reference Guide

Status: verbindlich v2

## Ziel

Dieses Dokument schliesst die Luecke zwischen High-Fidelity-Bildern und echter Compose-for-TV-Umsetzung.

High-Fidelity-Bilder bleiben visuelle Zielrichtung.

Fuer echte Code-Umsetzung sind jedoch Compose-for-TV-Referenzen wichtiger als Bilder.

## Technische Referenzen

Codex soll bei TV-UI-Arbeiten diese Referenzen als technische Orientierung nutzen:

```text
android/tv-samples
JetStreamCompose
TvMaterialCatalog
Android Developers: Compose for TV
```

## Konkrete Quellen

Codex soll diese Quellen direkt verwenden:

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

Wenn Codex lokal mit Internetzugriff arbeitet, darf Codex das `android/tv-samples` Repository ausserhalb des Vivicast-App-Projekts als Referenz verwenden.

Die Referenz darf nicht als Submodule, Dependency, kopierter Quellbaum oder Fork in das Vivicast-App-Repo uebernommen werden.

## Einordnung

```text
High-Fidelity Renderings = visuelle Zielrichtung
JetStreamCompose = technische Screen-/Layout-Referenz
TvMaterialCatalog = technische Komponenten-Referenz
Vivicast Design-System = verbindliche eigene Umsetzung
UI Direction Decisions + Governance-Konfliktregel = verbindliche Einordnung fuer visuelle Richtung
```

## Grundsatz

Nicht kopieren.

Nicht JetStream nachbauen.

Nicht fremdes Branding, fremde Assets oder fremde Daten uebernehmen.

Nutzen als Referenz fuer:

```text
TV-typische Layouts
Focus Handling
Content Rows
Poster Cards
Hero / Featured Area
Navigation
Details-Bereiche
Compose-for-TV-Struktur
androidx.tv.material3 Komponenten
```

## Wichtig fuer Codex

Codex soll High-Fidelity-Bilder nicht pixelgenau nachbauen.

Stattdessen soll Codex:

```text
1. passende TV-Referenz-Komponenten ansehen
2. eigene Vivicast-Komponenten daraus ableiten
3. Design-Tokens hart definieren
4. Screens aus fertigen Komponenten zusammensetzen
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

Wenn die App-Repo- oder Codex-Baseline `:core:designsystem` verwendet, sollen dort feste Tokens verwendet werden fuer:

```text
Farben
Typografie
Spacing
Corner Radius
Border Width
Focus Glow
Focus Scale
Panel Alpha
Card Groessen
Poster Aspect Ratio
Animation Durations
```

## TV-spezifische Regeln

```text
androidx.tv.material3 bevorzugen
keine mobile-first Material-UI fuer TV-Screens
D-Pad-Fokus immer sichtbar
Fokus ist nicht nur Farbe, sondern Form, Scale, Ring oder Glow
Lazy Rows und Lazy Grids fuer grosse Listen
Screens muessen aus TV-Entfernung lesbar sein
```

## Asset-Regel

Die High-Fidelity-Bilder enthalten AI-generierte Logos, Poster und Backdrops.

Diese nicht als App-Assets uebernehmen.

Erlaubt:

```text
kontrollierte Platzhalter
Gradient-Poster
Icon-Placeholder
spaeter echte Providerlogos
spaeter echte Providerposter, falls vorhanden
```

## Workflow fuer UI-Polish

Nicht die ganze App auf einmal polishen.

Stattdessen:

```text
1. eine Komponente verbessern
2. im Emulator pruefen
3. Screenshot erstellen
4. gegen High-Fidelity-Referenz vergleichen
5. erst dann naechste Komponente oder Screen
```

## UI-Alignment Empfehlung

Wenn die App optisch nicht dem freigegebenen Design entspricht, keinen globalen Visual-Pass starten.

Stattdessen:

```text
Compose-for-TV Referenzen ansehen
Vivicast Designsystem gezielt verbessern
Screens nur mit verbesserten Komponenten aktualisieren
keine neuen Features mit UI-Polish vermischen
```

## Nicht-Ziele

```text
kein JetStream Fork
kein Kopieren fremder App-Struktur
keine fremden Assets
kein Pixel-Nachbau der High-Fidelity-Renderings
keine komplette UI-Neuentwicklung in einem Schritt
```
