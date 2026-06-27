# Vivicast Compose Designsystem Template

Status: Vorlage für spätere Android-TV-App-Implementierung  
Ziel: Die aktuellen High-Fidelity-Mockups in konkrete Jetpack-Compose-for-TV-Designwerte übersetzen.

## Wichtig

Diese Dateien sind keine fertige App-Implementierung. Sie sind eine Startvorlage für ein späteres `:core:designsystem` oder ähnliches Modul.

Die `.kt`-Dateien enthalten bewusst keinen finalen Package-Namen. Beim Kopieren in das App-Repository muss Codex den echten Package-Namen und die echte Modulstruktur dort festlegen.

Die aktuellen neu gerenderten Mockups sind visuell primär. Die bestehenden Repo-Design-Tokens dienen nur noch als sekundäre technische Orientierung, wenn die Mockups keine klare Aussage liefern.

## Enthaltene Dateien

```text
VivicastColors.kt
VivicastTypography.kt
VivicastSpacing.kt
VivicastShapes.kt
VivicastFocus.kt
VivicastTheme.kt
VIVICAST-VISUAL-IMPLEMENTATION-SPEC-v2.md
vivicast_visual_tokens_v2.json
```

## Verwendung für Codex

Codex soll:

1. Die Visual Implementation Spec lesen.
2. Diese Kotlin-Dateien als Designsystem-Baseline verwenden.
3. Die Werte zentral halten und nicht pro Screen hart verdrahten.
4. Die Mockups nicht pixelgenau nachraten, sondern über diese Tokens und Komponenten ableiten.
5. Abweichungen nur begründen und dokumentieren.

## Zielplattform

```text
Android TV
Jetpack Compose for TV
D-Pad / OK / Zurück
16:9 TV-Layout
dp/sp-basiertes responsives Layout
```
