# Vivicast Design System

Status: verbindliche Design-Referenz v3

Dieses Verzeichnis definiert das visuelle und interaktive Design-System für Vivicast.

Vivicast ist eine Android-TV-IPTV-App. Das Design-System ist daher nicht Mobile-first, sondern konsequent TV-first: große Betrachtungsdistanz, D-Pad-Bedienung, klarer Fokuszustand, wenige Ebenen, schnelle Orientierung und performante Darstellung großer Listen.

## Verbindliche Grundlagen

Primäre Quellen im Repository:

```text
prd/PRD-v1/00-prd-boundaries.md
prd/PRD-v1/01-product-overview.md
prd/PRD-v1/02-live-tv-requirements.md
prd/PRD-v1/03-movies-series-requirements.md
prd/PRD-v1/04-search-settings-player-requirements.md
prd/PRD-v1/05-iptv-epg-favorites.md
prd/PRD-v1/06-data-model.md
prd/PRD-v1/07-background-jobs-performance.md
prd/PRD-v1/08-android-tv-security.md
prd/PRD-v1/09-implementation-and-dod.md
prd/PRD-v1/10-backup-import-requirements.md
prd/PRD-v1/11-about-app-requirements.md
prd/PRD-v1/12-parser-source-contracts.md
prd/PRD-v1/13-test-strategy.md
architecture/decisions/*.md
DOCS-GOVERNANCE.md
```

Diese Liste dient nur als Einstieg. Die kanonische Quellenverantwortung und Konfliktregel steht in `DOCS-GOVERNANCE.md`.

Das Design-System ist verbindlich fuer visuelle Grundlagen, wiederverwendbare Komponenten, Fokusdarstellung und Screen-Patterns. Bei Produkt-, Daten-, Sicherheits- oder Architekturfragen gilt `DOCS-GOVERNANCE.md`.

Externe Orientierung:

```text
Android TV UI Design Guidelines
Android TV Navigation Guidelines
Jetpack Compose for TV / Focus Navigation
Material Design for TV Components
```

## Dokumente

```text
design/design-system/
  README.md
  01-foundations.md
  02-design-tokens.md
  03-components.md
  04-focus-navigation.md
  05-screen-patterns.md
```

## Design-Ziele

1. Inhalte schneller erreichbar machen als Einstellungen.
2. Aktiver Fokus muss jederzeit eindeutig sichtbar sein.
3. Jede Aktion muss mit D-Pad, OK und Zurück erreichbar sein.
4. Keine Touch-only-Interaktionen.
5. Große Sender-, Film-, Serien- und EPG-Mengen dürfen die UI nicht überladen.
6. Provider-Isolation muss auch visuell verständlich bleiben.
7. Fehlende Logos, Poster, EPG- und Metadaten müssen sauber degradiert werden.
8. Benutzerdefinierte Optik darf das Grundlayout nicht zerstören.

## Visuelle Richtung

Vivicast nutzt ein dunkles, ruhiges TV-Interface mit klaren Flächen, kräftigem Fokusrahmen und reduzierten Animationen.

Der visuelle Stil soll hochwertig wirken, aber nicht verspielt. IPTV-Bibliotheken enthalten oft uneinheitliche Logos, Poster und Metadaten. Das UI muss diese Unordnung abfangen und darf nicht selbst unruhig werden.

## Nicht-normativer Umsetzungshinweis

Die konkrete Modulstruktur wird im App-Repository entschieden. Falls Codex die Arbeitsbaseline aus `codex/coding-rules.md` verwendet, kann das Design-System spaeter in folgendem App-Modul umgesetzt werden:

```text
:core:designsystem
```

Dieser Hinweis ist keine PRD- oder Design-Anforderung. Verbindlich sind hier nur die visuellen Tokens, Komponentenregeln und Fokus-/Layoutgrundlagen.

## Abgrenzung

Dieses Design-System enthält keine finalen Wireframes und keine Mockups.

Wireframes liegen separat unter:

```text
design/wireframes/
```

Mockups liegen separat unter:

```text
design/mockups/
```
