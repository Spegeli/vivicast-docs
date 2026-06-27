# Vivicast Visual Implementation Spec v2

Status: Arbeitsreferenz für spätere App-Implementierung  
Priorität: Die neu gerenderten High-Fidelity-Mockups sind visuell primär.

## Quellenpriorität für visuelle Umsetzung

1. Neu gerenderte High-Fidelity-Mockups aus dieser Chat-Session
2. Diese Visual Implementation Spec
3. Kotlin-Designsystem-Vorlage in diesem Paket
4. Bestehende Repo-Design-Tokens nur als technische Sekundärquelle
5. Ältere Stitch-/Repo-Mockups nur als historische Referenz, nicht als Zielbild

## Wichtige Klarstellung

Android-TV-Apps werden nicht hart auf eine einzige Pixelauflösung wie `1920x1080` programmiert.  
Die Mockups nutzen `1920x1080` als Referenz-Artboard. Die Umsetzung erfolgt in Compose mit `dp` und `sp`.

Das bedeutet:

```text
Designreferenz: 1920 x 1080 / 16:9
Implementierung: dp/sp-basiert, skalierend auf Android-TV-Geräten
```

## Zielbild

Vivicast soll wirken wie:

```text
Premium Android-TV-App
dunkel
ruhig
technisch sauber
inhaltzentriert
D-Pad-first
starker cyanfarbener Fokus
```

Nicht gewünscht:

```text
Mobile UI
Web Dashboard
Bottom Navigation
weiße Flächen
Touch-first-Controls
unauffälliger Fokus
```

## Design Tokens

### Farben

```text
Background              #050914
Background Deep         #020617
Background Elevated     #07111F

Surface                 #0B1628
Surface High            #101D33
Surface Focus           #102B46
Surface Pressed         #15395C

Panel                   #07111F mit Alpha / dunkles Glas
Panel Strong            #0B1628 mit Alpha
Dialog                  #0B1628 fast deckend

Border                  #22334A
Border Soft             #17263A

Text Primary            #F8FAFC
Text Secondary          #CBD5E1
Text Tertiary           #94A3B8
Text Disabled           #64748B

Accent                  #00C8FF
Accent Strong           #38D5FF
Focus Ring              #00D4FF
Focus Glow              #6600BFFF
Progress                #159BFF

Live                    #FF2D3A
Favorite                #FACC15
Success                 #22C55E
Warning                 #F59E0B
Error                   #EF4444
Catch-Up                #0EA5E9
```

### Typography

```text
Display Large       40 sp   Bold       Hero-Titel / Detailtitel
Display Medium      34 sp   Bold       Screen-Titel
Title Large         28 sp   SemiBold   Bereichstitel
Title Medium        24 sp   SemiBold   Card-/Row-Titel
Title Small         20 sp   SemiBold   Listenüberschriften

Body Large          20 sp   Regular    Haupttext TV
Body Medium         18 sp   Regular    Settings-Text, Listen
Body Small          16 sp   Regular    Metadaten, Hilfetext

Label Large         18 sp   SemiBold   Buttons, Tabs, Chips
Label Medium        16 sp   SemiBold   Badges
Label Small         14 sp   Medium     kleine technische Labels
```

### Spacing

```text
Basiseinheit: 8 dp

Screen horizontal padding:      48 dp
Screen vertical padding:        32 dp
Panel padding:                  24 dp
Card padding:                   16 dp
Settings row horizontal:        24 dp
Settings row vertical:          16 dp
Grid gap:                       24 dp
Row gap:                        8 dp
Section gap:                    32 dp
Column gap:                     24 dp
```

### Shapes

```text
Small radius           8 dp
Medium radius          12 dp
Large radius           16 dp
Panel radius           24 dp
Dialog radius          24 dp
Pill radius            999 dp
```

### Fokus

```text
Focus Ring Width       2 dp
Focus Glow Elevation   ca. 18 dp
Focus Scale Small      1.02
Focus Scale Card       1.06
Focus Scale Button     1.03
Focus Animation        120 ms
```

Fokus darf nie nur durch Farbe erkennbar sein.  
Pflicht: Farbe + Rahmen/Fläche/Skalierung.

## Umsetzung in Compose

Codex soll die Werte aus den `.kt`-Dateien als Startpunkt verwenden:

```text
VivicastColors.kt
VivicastTypography.kt
VivicastSpacing.kt
VivicastShapes.kt
VivicastFocus.kt
VivicastTheme.kt
```

Empfohlene spätere Komponenten:

```text
VivicastTopNavigation
VivicastSettingsShell
VivicastSettingsSidebar
VivicastSettingsRow
VivicastPanel
VivicastChannelCard
VivicastPosterCard
VivicastHeroPanel
VivicastSearchField
VivicastPlayerOverlay
VivicastPlayerTimeline
VivicastActionPill
VivicastStatusBadge
VivicastDialog
VivicastPinDialog
```

## Grenzen dieser Vorlage

Diese Vorlage definiert technische Tokenwerte und Compose-Startpunkte.

Sie definiert keine Produkt-, Navigations-, Settings-, Sicherheits-, PIN-, Backup/Restore- oder Playback-Regeln neu.

Für solche Regeln gelten die aktiven Quellen gemäß `DOCS-GOVERNANCE.md`, insbesondere:

```text
prd/PRD-v1/
design/screens/
design/wireframes/
design/components/
design/interaction/
architecture/decisions/
```

## Hinweis für spätere Feinjustierung

Die Werte sind bewusst als robuste Startwerte definiert.  
Bei der echten Compose-Umsetzung können Fokus-Glow, Row-Höhen und Postergrößen auf Emulator/TV feinjustiert werden, aber nur zentral im Designsystem.
