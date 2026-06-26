# Vivicast Design System

Status: verbindliche Design-Referenz v4

Dieses Verzeichnis definiert das visuelle und interaktive Design-System fuer Vivicast.

Vivicast ist eine Android-TV-IPTV-App. Das Design-System ist daher nicht Mobile-first, sondern konsequent TV-first: grosse Betrachtungsdistanz, D-Pad-Bedienung, klarer Fokuszustand, wenige Ebenen, schnelle Orientierung und performante Darstellung grosser Listen.

## Verbindliche Grundlagen

Primaere Quellen im Repository:

```text
prd/PRD-v1/
architecture/decisions/
design/screens/
design/wireframes/
design/interaction/
design/components/
DOCS-GOVERNANCE.md
```

Diese Liste dient nur als Einstieg. Die kanonische Quellenverantwortung und Konfliktregel steht in `DOCS-GOVERNANCE.md`.

Das Design-System ist verbindlich fuer visuelle Grundlagen, wiederverwendbare Komponenten, Fokusdarstellung und Screen-Patterns. Bei Produkt-, Daten-, Sicherheits- oder Architekturfragen gilt `DOCS-GOVERNANCE.md`.

## Dokumente

```text
design/design-system/
  README.md
  01-foundations.md
  02-design-tokens.md
  03-components.md
  04-focus-navigation.md
  05-screen-patterns.md
  compose-template/
    README.md
    VIVICAST-VISUAL-IMPLEMENTATION-SPEC-v2.md
    vivicast_visual_tokens_v2.json
    VivicastColors.kt
    VivicastFocus.kt
    VivicastShapes.kt
    VivicastSpacing.kt
    VivicastTheme.kt
    VivicastTypography.kt
```

## Compose Template

`compose-template/` enthaelt eine technische Designsystem-Umsetzungsvorlage fuer spaetere Jetpack-Compose-for-TV-Implementierung.

Diese Dateien sind:

```text
Designsystem-Vorlagen
Token-Referenzen
Compose-Startpunkte
```

Sie sind nicht:

```text
App-Code im Docs-Repository
finale App-Repo-Modulstruktur
verbindliche Library-Versionen
```

Codex darf diese Dateien spaeter als Startreferenz fuer ein App-Repository-Designsystem nutzen, muss die echte Umsetzung aber im App-Repository `Spegeli/vivicast` erstellen.

## Design-Ziele

1. Inhalte schneller erreichbar machen als Einstellungen.
2. Aktiver Fokus muss jederzeit eindeutig sichtbar sein.
3. Jede Aktion muss mit D-Pad, OK und Zurueck erreichbar sein.
4. Keine Touch-only-Interaktionen.
5. Grosse Sender-, Film-, Serien- und EPG-Mengen duerfen die UI nicht ueberladen.
6. Provider-Isolation muss auch visuell verstaendlich bleiben.
7. Fehlende Logos, Poster, EPG- und Metadaten muessen sauber degradiert werden.
8. Benutzerdefinierte Optik darf das Grundlayout nicht zerstoeren.

## Visuelle Richtung

Vivicast nutzt ein dunkles, ruhiges Premium-TV-Interface mit klaren Flaechen, starkem cyanfarbenem Fokusrahmen, subtilem Glow und reduzierten Animationen.

Die aktuellen High-Fidelity-Renderings unter `design/mockups/high-fidelity/rendered/` sind die visuelle Hauptreferenz fuer die Zielwirkung.

## Nicht-normativer Umsetzungshinweis

Die konkrete Modulstruktur wird im App-Repository entschieden. Falls Codex die Arbeitsbaseline aus `codex/coding-rules.md` verwendet, kann das Design-System spaeter in folgendem App-Modul umgesetzt werden:

```text
:core:designsystem
```

Dieser Hinweis ist keine PRD- oder Design-Anforderung. Verbindlich sind hier nur die visuellen Tokens, Komponentenregeln und Fokus-/Layoutgrundlagen.

## Abgrenzung

Wireframes liegen separat unter:

```text
design/wireframes/
```

Mockups liegen separat unter:

```text
design/mockups/
```

Die Compose-Template-Dateien liegen bewusst im Design-System, weil sie Designwerte in eine spaetere technische Umsetzung uebersetzen.
