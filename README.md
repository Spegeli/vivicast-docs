# Vivicast Docs

Planungs- und Dokumentationsrepository fuer **Vivicast**, eine Android-TV-App.

Dieses Repository enthaelt Produkt-, Architektur-, UX-, Design- und Codex-Referenzunterlagen.

Der App-Code entsteht im separaten Repository: `Spegeli/vivicast`.

Der Ordner `codex/` in diesem Repository enthaelt nur Codex-Referenzmaterial fuer Dokumentation und App-Planung. Er ist kein App-Code-Verzeichnis und kein Modul des App-Repositories.

## Projekt

- Produktname: **Vivicast**
- Android Package Name: `com.vivicast.tv`
- Zielplattform: Android TV
- UI-Technologie: Kotlin und Jetpack Compose for TV
- Medienbasis spaeter: Media3 / ExoPlayer
- Lokale Datenbasis spaeter: Room und DataStore

## Status

- PRD v1: abgeschlossen
- ADRs: vorhanden und QA-abgeglichen
- Design-System: verbindliche Design-Referenz und driftbereinigt
- Wireframes: vorhanden und aktualisiert
- Screen Specs: vorhanden
- Component Specs: vorhanden
- Low-Fidelity Mockups: vorhanden
- High-Fidelity Mockups: vorhanden
- UI Direction Decisions: beschlossen
- Interaction Specs: vorhanden
- Codex-Referenzunterlagen: vorhanden
- Wiedergabelisten Add/Edit Flow: detailliert dokumentiert
- VOD Provider-Kategorien: providergruppiert dokumentiert
- Suche: vier Ergebnisgruppen ohne eigene Episoden-Gruppe und mit Room-FTS4-Vertrag dokumentiert
- Einstellungen: QA-verfeinert und Settings-Vertrag v1 dokumentiert
- Allgemein: globaler User-Agent als letzte Option dokumentiert
- Optik: TV-sichere Anpassungen QA-verfeinert
- Wiedergabe: Player-/Stream-Optionen QA-verfeinert
- Externer Player: keine automatische Fortschrittsrueckgabe dokumentiert
- Player/Progress: PlaybackRequest, Catch-Up, Track-Auswahl, Timeshift-Grenzfaelle und Progress-Speicherrhythmus dokumentiert
- Android-TV-Systemintegration: stabile Deep Links, Systemsuche, Watch Next und Kindersicherungsschutz dokumentiert
- Teststrategie: Pflichtfixtures, Mockserver, Roundtrips, Android-TV-QA und messbare DoD-Ziele dokumentiert
- EPG: quellbezogene Pipeline und globale Aufbewahrung dokumentiert
- Parser: M3U-, Xtream- und XMLTV-Vertraege mit tolerantem Teilimport dokumentiert
- Import/Refresh: atomarer Commit pro Provider und EPG-Quelle dokumentiert
- Schutzkonzept: Datenklassifizierung, Secret Store, Vollbackup-Krypto, PIN-Sperrlogik, HTTP/TLS-Policy und Diagnosebereinigung dokumentiert
- Kindersicherung: PIN- und Schutzbereichsflows QA-verfeinert
- Backup: Restore-Ersetzen und stabile Restore-Schluessel dokumentiert
- Backup/Kindersicherung: Restore deaktiviert Backup-PIN- und Schutzflags dokumentiert
- Cache und Verlauf: lokale Wartungsaktionen QA-verfeinert
- Über die App: App-Info-, Support- und Lizenzbereiche QA-verfeinert
- Datenmodell: Room/DataStore/Keystore und Backup-Bezug QA-verfeinert
- Implementierungsbereitschaft: ueber PRD, ADRs, Designquellen, Teststrategie, Codex-Regeln und Diagramme ableitbar
- Deep-Research-Follow-up: Restbereinigung abgeschlossen
- Architekturdiagramme: Onboarding-Hilfen vorhanden

## Dokumentationsregeln

Fuer Dokumentrollen, Quellenverantwortung und Konfliktregeln gilt kanonisch:

- `DOCS-GOVERNANCE.md`

Diese README wiederholt die Quellenprioritaet bewusst nicht vollstaendig.

Bei Widerspruechen gewinnt `DOCS-GOVERNANCE.md`.

`DOCS-GOVERNANCE.md` ist keine App-Anforderung. Codex darf daraus keine Produkt-, UI- oder Architekturanforderungen ableiten.

Codex soll fuer App-Umsetzung weiterhin mit `codex/README.md` starten.

## Struktur

- `prd/PRD-v1/`
- `architecture/decisions/`
- `architecture/diagrams/`
- `design/design-system/`
- `design/screens/`
- `design/wireframes/`
- `design/interaction/`
- `design/components/`
- `design/mockups/`
- `codex/README.md`
- `codex/coding-rules.md`
- `codex/tv-compose-reference-guide.md`

## Aktuelle Detailreferenzen

- `prd/PRD-v1/10-backup-import-requirements.md`
- `prd/PRD-v1/11-about-app-requirements.md`
- `prd/PRD-v1/13-test-strategy.md`
- `design/wireframes/08-about-app.md`
- `design/components/about-app.md`
- `architecture/diagrams/README.md`

## Codex Einstieg

Codex soll zuerst `codex/README.md` lesen.

Danach soll Codex auf Grundlage der verbindlichen Quellen eigenstaendig einen Umsetzungsplan fuer das App-Repository erstellen.

Historische Remediation- und Research-Artefakte liegen unter `archive/remediation/2026-06-24/`. Sie sind keine aktive Arbeitsgrundlage fuer neue App-Repo-Planung.

Historische Review-/QA-Artefakte liegen unter `archive/review/2026-06-24/` und sind nicht Teil der aktiven Arbeitsquellen.

Das Docs-Repository enthaelt bewusst keinen festen Phasenplan und keine fertigen Implementierungs-Prompts mehr.

## Dokumentrollen und Konflikte

Die kanonische Quellen- und Konfliktregel steht in `DOCS-GOVERNANCE.md`.

Kurzfassung:

- PRD-Dateien definieren Produktumfang, fachliche Regeln, Datenregeln und Sicherheitsanforderungen.
- ADRs definieren Architekturentscheidungen.
- Design-Dateien definieren Screen-Struktur, Layout, Interaktion, Komponenten und visuelle Grundlagen.
- UI Direction Decisions konkretisieren die visuelle Richtung, soweit sie PRD und ADRs nicht widersprechen.
- Codex-Dateien enthalten Arbeitsregeln und Umsetzungshinweise, aber keinen festen Umsetzungsplan.

## Grundsaetze

- Android TV first
- Fernbedienungsoptimierte Bedienung
- Lokale Datenhaltung
- Provider-Isolation
- Grosse Bibliotheken performant unterstuetzen
- Klare Fokuszustaende auf allen Screens
- Keine Touch-only-Interaktionen
