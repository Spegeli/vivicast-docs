# 02 – UI Direction Decisions

Status: beschlossen v8

Rolle: visuelle Richtungsentscheidung. Diese Datei konkretisiert die visuelle Richtung, ist aber keine Produktanforderung und keine Umsetzungsreihenfolge.

## Grundlage

Diese Datei ist die aktive Markdown-Quelle für die High-Fidelity-UI-Richtung. Die aktuellen Renderings unter `rendered/` sind die visuelle Hauptreferenz.

Aktuell bestätigte Owner-Entscheidungen aus frueheren Archiv-Artefakten sind hier integriert. Archivdateien dürfen nicht ohne erneute Pruefung gegen aktuelle PRD-, ADR-, Design-, Codex- und Governance-Quellen reaktiviert werden.


## Aktuelle Zielreferenz v2

Die aktuellen neu gerenderten High-Fidelity-Mockups unter `design/mockups/high-fidelity/rendered/` sind die aktive visuelle Hauptreferenz für die Zieloptik.

Die technische Designsystem-Ableitung liegt unter:

```text
design/design-system/compose-template/
```

Dort enthaltene Markdown-, JSON- und Kotlin-Dateien sind keine App-Code-Dateien im Docs-Repository. Sie sind technische Designsystem-Vorlagen für die spätere Compose-Umsetzung im App-Repository.

Quellenrolle für die visuelle Umsetzung:

```text
High-Fidelity-Renderings: visuelle Zielwirkung
UI Direction Decisions: visuelle Richtung und Stilentscheidungen
Compose Template: technische Token- und Compose-Startwerte
Bestehende Design-System-Dateien: Markdown-Kontext, konsistent mit Compose Template
```

Gerenderte PNGs bleiben nicht normativ für Navigation, Labels, UI-Texte, Produktlogik, Datenmodell, Sicherheit, Backup/Restore, PIN oder Playback. Dafür gelten PRD, ADRs, Screens, Wireframes, Interaction Specs und Components gemaess `DOCS-GOVERNANCE.md`.


## Grundentscheidung

Vivicast nutzt eine hochwertige dunkle Android-TV-Richtung.

Zielwirkung:

```text
Premium-TV-App
ruhig
klar
schnell
inhaltzentriert
```

## Allgemein übernehmen

```text
dunkler atmosphaerischer Hintergrund
weiche Panels
subtile Tiefe
grosse Typografie
Cyan-Fokus mit Ring und Glow
runde Action-Pills
ruhige Animationen
Top-Navigation
```

## Live-TV Entscheidung

Der Live-TV Browser nutzt ein adaptives Spaltenmodell.

Kategorie-Modus:

```text
Provider/Kategorien | Senderliste | Vorschau/Details
```

Sender-Modus:

```text
Senderliste | Sender-EPG | Vorschau/Details
```

Regeln:

```text
Provider-Spalte bleibt im Kategorie-Modus sichtbar
erstes OK in der Senderspalte blendet Provider/Kategorien aus und aktiviert den Sender-Modus
mittlere Spalte zeigt dann EPG für den ausgewählten Sender
Stream-Vorschau startet gleichzeitig mit dem Sender-Modus
Fokus springt auf die aktuelle Sendung in der EPG-Spalte, sofern vorhanden
OK auf der fokussierten aktuellen Sendung oeffnet Vollbild
wenn der Nutzer nach links zurück navigiert, erscheint die Provider-Spalte wieder
EPG-Button in der rechten Spalte entfaellt
keine Preview-Einstellung und kein direkter Vollbildstart beim ersten OK
```

## Player Entscheidung

```text
Vollbild zuerst
Bottom Overlay nur bei Bedienung
transluzentes dunkles Overlay
Timeline ist zentrales Bedienelement
```

Player Controls:

```text
Pause-Button entfernen
Zurückspulen-Button entfernen
Vorspulen-Button entfernen
Timeline fokussierbar machen
OK auf Timeline pausiert
zweites OK auf Timeline spielt weiter
Links/Rechts auf Timeline spult
```

Auto-Next Panel für Serien:

```text
Auto-Next Aus: Nächste Folge abspielen erst nach dem Episodenende
Auto-Next Ein: Nächste Folge in X entsprechend Countdown vor dem Episodenende
Hauptbutton bleibt fokussiert und startet bei OK sofort
bei Ablauf startet die nächste Episode automatisch
Zurück erscheint zeitgleich neben dem Hauptbutton; kein Button Abbrechen
OK auf Zurück oder die Zurück-Taste fuehrt mit erhaltenem Staffel-/Episodenkontext zur Serien-Detailseite
kein Panel nach der letzten Serienepisode
```

Stream-Badges:

```text
Qualitaet: HD, Full HD, 4K
FPS: 25 FPS, 50 FPS, 60 FPS
Audio: Mono, Stereo, 5.1
```

## VOD Entscheidung

```text
Provider-/Kategorien-Spalte links
Hero-/Detailbereich und Poster-Raster rechts
Provider-Kategorien bleiben sichtbar getrennt
Globale Kategorien Favoriten und Fortsetzen stehen oberhalb der Provider
Poster-Cards mit Fokus-Skalierung
Fortsetzen-Fortschritt direkt auf Cards
Fallback-Poster bewusst gestalten
Bewertung auf Posterkarte anzeigen
Titel unter Poster anzeigen
```

Kategorie-Chips sind nicht die primaere VOD-Navigation. Sie dürfen nur für untergeordnete Filter oder Detailkontexte verwendet werden, wenn die Screen-Spezifikation dies erlaubt.

Fallback-Regel:

```text
Wenn kein Poster vorhanden ist, muss der Titel trotzdem sichtbar sein
```

Abschluss- und Gesehen-Regel, Owner-Entscheidung O-08:

```text
Filme und einzelne Episoden gelten ab 95 Prozent oder am tatsaechlichen Medienende als abgeschlossen
95 Prozent ist ein fester v1-Wert und keine Einstellung
Filme und fokussierte Episode Rows bieten Als gesehen markieren oder Als ungesehen markieren
Als ungesehen markieren loescht den gespeicherten Wiedergabefortschritt vollstaendig
komplette Staffeln und Serien besitzen keine entsprechende manuelle Aktion
95 Prozent beendet die Wiedergabe nicht und loest kein Auto-Next aus
```

## Suche Entscheidung

```text
grosses Suchfeld oben
Voice-Button als separate Aktion
Ergebnisgruppen als horizontale Reihen
Kanäle, Filme, Serien und EPG getrennt
fokussierte Ergebniscard mit Cyan-Fokus
Bewertung bei Filmen und Serien anzeigen
Alle-anzeigen-Aktion entfernen
Suchverlauf-Chips dauerhaft sichtbar halten
```

## Einstellungen Entscheidung

```text
Master-Detail Layout
Bereiche links
Optionskarten rechts
Werte rechtsbuendig
kurzer Hilfetext unter dem Titel
fokussierte Settings-Row mit Cyan-Ring
aktuelles Mockup ohne größere Änderungen übernehmen
```

## Nicht direkt übernehmen

```text
zufaellige Logos
zufaellige Poster
zufaellige Videobilder
uneinheitliche Icons
zufaellige Textfehler
inkonsistente Badge-Formen
```

Echte App-Elemente müssen später aus Design-System, Providerdaten oder kontrollierten Fallbacks entstehen.

## Compose Designsystem Ableitung

Nicht-normativer Umsetzungshinweis für ein späteres Compose-Designsystem, zum Beispiel gemaess Codex-Baseline `:core:designsystem`:

```text
VivicastTheme
VivicastBackground
VivicastPanel
VivicastFocusContainer
VivicastTopNavigation
VivicastProviderRail
VivicastChannelCard
VivicastChannelEpgColumn
VivicastPosterCard
VivicastHeroHeader
VivicastSearchField
VivicastSearchResultRow
VivicastSettingsRow
VivicastPlayerOverlay
VivicastPlayerTimeline
VivicastActionPill
VivicastStatusBadge
VivicastStreamInfoBadge
```

Die konkrete Modulstruktur bleibt App-Repo-offen. Diese Liste beschreibt wiederverwendbare UI-Bausteine, nicht die verbindliche Modulaufteilung.

## Aktuelle visuelle Quellenrolle

```text
Renderings = Zielwirkung
UI Direction Decisions = Stilrichtung
Compose Template = technische Tokenwerte und Compose-Startpunkte
Design-System Markdown = erläuternder Designsystem-Kontext
```

Gerenderte PNGs bleiben visuelle Referenzen. Sie ersetzen keine PRD-, Screen-, Wireframe-, Interaction- oder Component-Vorgaben für Produktlogik, Labels, Navigation oder Fokuspfade.

## Offene Designpunkte

```text
exakte Fokusstaerke
finale Akzentfarbe
Player Overlay Hoehe
Poster Card Größen
Settings Row Hoehen bei grosser Schrift
EPG Ergebnisdarstellung in Suche
Fallback-Look für fehlende Backdrops
Timeline-Verhalten bei Live-TV ohne Timeshift
Position der Stream-Info-Badges
```

## Relevanz für App-Planung

App-Repo-Planung für betroffene UI-Bereiche soll diese Quellen beruecksichtigen:

```text
PRD
ADRs
Design-System
Wireframes
High-Fidelity Mockups
UI Direction Decisions
Compose Designsystem Template
```

Status:

```text
UI-Richtung als visuelle Referenz freigegeben
```

Diese Quellenliste ist keine Umsetzungsreihenfolge und kein Phasenplan.
