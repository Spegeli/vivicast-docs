# Vivicast Codex Reference Guide

Status: verbindlich v15

Dieses Verzeichnis enthaelt Referenz-, Arbeits- und Planungsregeln fuer Codex.

Der Masterplan fuer die App-Umsetzung liegt unter `codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md`.

Der Masterplan steuert Reihenfolge, Paketlogik und Kontrollpunkte fuer Codex. Er ersetzt keine PRD-, ADR-, Design-, Teststrategie- oder Governance-Vorgaben.

Codex erstellt die eigentlichen technischen Arbeitsplaene im App-Repository `Spegeli/vivicast`.

Der Ordner `vivicast-docs/codex/` ist kein App-Code-Verzeichnis. App-Code, App-Module und technische Arbeitsplaene gehoeren in das separate Repository `Spegeli/vivicast`.

Das Docs-Repository ist waehrend der App-Implementierung eine read-only Referenz fuer Codex. Codex darf aktive Dateien in `vivicast-docs` nur aendern, wenn der Owner ausdruecklich eine Dokumentationsaenderung beauftragt.

## Zweck

Codex soll dieses Verzeichnis nutzen, um:

- die kanonischen Dokumentrollen und Konfliktregeln zu finden
- zentrale Coding- und Architekturregeln zu beachten
- Compose-for-TV Referenzen fuer die UI-Umsetzung zu finden
- PRD-, Architektur- und Designentscheidungen korrekt einzuordnen
- den Masterplan als verbindlichen Umsetzungsfahrplan fuer Reihenfolge und Paketlogik zu nutzen
- das Schutzkonzept fuer Daten, Netzwerk, PIN, Diagnose und Backup korrekt zu beruecksichtigen
- die Teststrategie, Pflichtfixtures und messbaren DoD-Ziele korrekt zu beruecksichtigen
- die Architekturdiagramme als Onboarding-Hilfen zu nutzen, ohne daraus neue Regeln abzuleiten
- pro Masterplan-Paket eigene technische Detailplaene im App-Repository zu erstellen

## Einstieg und Quellenmodell

Codex muss vor jeder groesseren Umsetzung die relevanten Quellen lesen, den Masterplan pruefen und daraus einen eigenen technischen Detailplan fuer das App-Repository bilden.

Die kanonische Regel fuer Dokumentrollen, Quellenverantwortung und Konflikte steht in:

- `DOCS-GOVERNANCE.md`

Codex liest diese Governance fuer Rollen und Konfliktloesung, aber nicht als Produkt-, UI- oder Architekturanforderung.

Praktischer Einstieg fuer App-Repo-Arbeiten:

1. `codex/README.md`
2. `DOCS-GOVERNANCE.md` fuer Rollen und Konflikte
3. `codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md` fuer Reihenfolge, Paketlogik und Kontrollpunkte
4. betroffene PRD-Dateien
5. `prd/PRD-v1/13-test-strategy.md`, wenn Tests, DoD, Performance, Migration, Backup/Restore, Parser, UI-QA oder Releasefaehigkeit betroffen sind
6. betroffene ADRs
7. betroffene Design-, Interaction- und Component-Dateien
8. bei UI-/Designsystem-Arbeiten zusaetzlich `design/mockups/high-fidelity/rendered/` und `design/design-system/compose-template/` pruefen
9. passende Diagramme aus `architecture/diagrams/` als Onboarding-Hilfe

## Codex-Dateien

```text
codex/README.md
codex/coding-rules.md
codex/tv-compose-reference-guide.md
codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md
```

### `codex/coding-rules.md`

Enthaelt technische Arbeitsregeln, die aus PRD, Architekturentscheidungen und Android-TV-Anforderungen abgeleitet sind.

### `codex/tv-compose-reference-guide.md`

Enthaelt Hinweise zu Compose for TV, technischen Android-TV-/Compose-Referenzen, `androidx.tv.material3`, JetStreamCompose und TV Material Catalog. Diese Referenzen sind keine Vivicast-Designquelle.

### `codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md`

Enthaelt den aktiven Masterplan fuer Codex.

Der Masterplan definiert:

- Umsetzungsreihenfolge
- Paketgrenzen
- Kontrollpunkte
- Context-Refresh-Regeln
- Paket-Definition-of-Done
- Owner-Frage-Regeln
- Pflicht zur technischen Detailplanung im App-Repository

Der Masterplan ist keine Produkt-, Design-, Architektur- oder Testquelle.

Er darf PRD, ADRs, Designquellen, Teststrategie, Codex-Regeln oder Governance nicht ueberschreiben.

Codex muss fuer jedes groessere Paket einen eigenen technischen Detailplan im App-Repository erstellen.

## Source Map

| Bereich | Primaere Quellen |
| --- | --- |
| Home | `prd/PRD-v1/01-product-overview.md`, `prd/PRD-v1/05-iptv-epg-favorites.md`, `design/screens/01-home.md`, `design/wireframes/00-home.md`, `design/interaction/nav.md` |
| Live-TV | `prd/PRD-v1/02-live-tv-requirements.md`, `architecture/decisions/ADR-001-provider-isolation.md`, `architecture/decisions/ADR-003-refresh-strategy.md`, `design/screens/02-live-tv.md`, `design/interaction/01-live-tv-adaptive-columns.md` |
| VOD | `prd/PRD-v1/03-movies-series-requirements.md`, `prd/PRD-v1/05-iptv-epg-favorites.md`, `architecture/decisions/ADR-013-player-playback-progress.md`, `design/screens/04-movies.md`, `design/screens/05-series.md` |
| Suche | `prd/PRD-v1/04-search-settings-player-requirements.md`, `architecture/decisions/ADR-005-local-search.md`, `design/screens/06-search.md`, `design/wireframes/04-search.md` |
| Settings | `prd/PRD-v1/04-search-settings-player-requirements.md`, `prd/PRD-v1/10-backup-import-requirements.md`, `prd/PRD-v1/11-about-app-requirements.md`, `design/screens/07-settings.md`, `design/components/settings.md` |
| Datenmodell, Backup und Sicherheit | `prd/PRD-v1/06-data-model.md`, `prd/PRD-v1/08-android-tv-security.md`, `prd/PRD-v1/10-backup-import-requirements.md`, `architecture/decisions/ADR-010-stable-identities-and-restore-keys.md`, `architecture/decisions/ADR-014-security-data-network-backup.md` |
| Quellen, Parser und EPG | `prd/PRD-v1/05-iptv-epg-favorites.md`, `prd/PRD-v1/07-background-jobs-performance.md`, `prd/PRD-v1/12-parser-source-contracts.md`, `architecture/decisions/ADR-011-parser-source-contracts.md`, `architecture/decisions/ADR-012-atomic-import-refresh.md` |
| Player und Progress | `prd/PRD-v1/02-live-tv-requirements.md`, `prd/PRD-v1/03-movies-series-requirements.md`, `prd/PRD-v1/04-search-settings-player-requirements.md`, `architecture/decisions/ADR-006-timeshift-strategy.md`, `architecture/decisions/ADR-013-player-playback-progress.md`, `design/screens/03-player.md` |
| Android-TV-Systemintegration | `prd/PRD-v1/08-android-tv-security.md`, `architecture/decisions/ADR-008-android-tv-integration.md`, `design/interaction/nav.md`, `design/screens/06-search.md` |
| Tests und DoD | `prd/PRD-v1/09-implementation-and-dod.md`, `prd/PRD-v1/13-test-strategy.md` |
| Visuelle Zielwirkung | `design/mockups/high-fidelity/02-ui-direction-decisions.md`, `design/mockups/high-fidelity/rendered/` |
| Technische Designsystem-Tokens / Compose-Vorlage | `design/design-system/compose-template/VIVICAST-VISUAL-IMPLEMENTATION-SPEC-v2.md`, `design/design-system/compose-template/*.kt`, `design/design-system/compose-template/vivicast_visual_tokens_v2.json` |
| Technische TV-/Compose-Referenzen | `codex/tv-compose-reference-guide.md` |

Mockup-Markdownquellen bleiben aktiv. Aktuelle gerenderte PNGs unter `design/mockups/high-fidelity/rendered/` sind visuelle Zielreferenzen, aber keine normative Quelle fuer Navigation, Labels oder UI-Texte.

Die Dateien unter `design/design-system/compose-template/` sind Designsystem-Vorlagen für spätere Compose-Umsetzung. Sie sind kein App-Code und definieren keine finale App-Repo-Modulstruktur.

JetStreamCompose, TvMaterialCatalog und android/tv-samples sind technische Referenzen für Compose-for-TV-Mechanik, Fokusverhalten und TV-Best-Practices. Sie sind keine visuelle Zielquelle und dürfen Vivicast-Renderings, Design-Tokens, Screen Specs oder Wireframes nicht überschreiben.

## Architekturdiagramme

### `architecture/diagrams/`

Enthaelt Onboarding-Hilfen fuer Systemkontext, Modul-/Layer-Abhaengigkeiten, Import/Refresh, EPG, Player/Progress und Backup/Restore.

Diagramme ersetzen keine PRD-, ADR-, Codex- oder App-Repo-Entscheidungen.

## Archivierte Historie

Historische Remediation- und Research-Artefakte liegen unter `archive/remediation/2026-06-24/`.

Diese Dateien sind keine aktive Arbeitsgrundlage fuer neue App-Repo-Planung. Sie koennen bei Bedarf erklaeren, warum alte Treffer bewusst archiviert wurden.

## Teststrategie

### `prd/PRD-v1/13-test-strategy.md`

Definiert reproduzierbare Pflichttests, Referenzfixtures, Mockserver-Szenarien, Backup-/Restore-Roundtrips, Android-TV-QA und messbare Budgets fuer Import, Suche, Datenbank, EPG und Speicherverbrauch.

## Aktuelle Detailreferenzen

- `prd/PRD-v1/10-backup-import-requirements.md`
- `prd/PRD-v1/11-about-app-requirements.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `prd/PRD-v1/13-test-strategy.md`
- `architecture/decisions/ADR-014-security-data-network-backup.md`
- `design/wireframes/08-about-app.md`
- `design/components/about-app.md`
- `architecture/diagrams/README.md`

## Konfliktregeln

Bei Widerspruechen gilt die kanonische Regel aus `DOCS-GOVERNANCE.md`.

Kurzregel fuer Codex:

- PRD, ADRs und Designquellen sind normative Quellen.
- Codex-Dateien sind Arbeitsregeln und duerfen PRD, ADRs und Designentscheidungen nicht ersetzen.
- Archivierte Arbeitsnachweise sind keine aktive Quelle.

## Erwartung an Codex

Codex soll fuer die App-Umsetzung den Masterplan unter `codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md` nutzen.

Der Masterplan gibt Reihenfolge, Paketlogik und Kontrollpunkte vor.

Codex soll nicht blind nach Erinnerung oder Chat-Kontext arbeiten.

Stattdessen soll Codex:

1. `codex/README.md`, `DOCS-GOVERNANCE.md` und den Masterplan lesen
2. die relevanten PRD-, ADR-, Design-, Teststrategie- und Codex-Quellen pro Paket erneut pruefen
3. bei Unsicherheit einen Context Refresh gegen die aktiven Docs machen
4. fuer jedes groessere Paket einen eigenen technischen Detailplan im App-Repository erstellen
5. Scope, Nicht-Scope, betroffene Module, Risiken, Tests und Owner-Fragen dokumentieren
6. erst implementieren, wenn keine blockierenden Owner-Fragen offen sind
7. relevante Tests, Messungen oder manuelle Android-TV-QA aus `prd/PRD-v1/13-test-strategy.md` einplanen
8. passende Architekturdiagramme als Onboarding-Hilfen pruefen, ohne daraus neue Regeln abzuleiten
9. nach jedem Paket berichten, was erledigt, offen oder bewusst nicht umgesetzt wurde

Vor groesseren App-Repo-Aenderungen muss Codex einen eigenen technischen Plan im App-Repository dokumentieren.

Der Plan nennt:

- gelesene Quellen
- betroffenes Masterplan-Paket
- betroffene Module oder Schichten
- Risiken und Annahmen
- geplante Implementierung
- relevante Tests, Messungen oder manuelle Android-TV-QA
- bekannte offene Punkte
- Owner-Fragen, falls vorhanden

Codex darf technische Detailentscheidungen treffen, solange sie keine PRD-, ADR-, Design-, Teststrategie-, Codex- oder Governance-Vorgabe verletzen.

Codex muss den Owner fragen, wenn eine Entscheidung Produktumfang, sichtbare UI, Navigation, Labels, Datenmodell, Persistenz, Backup/Restore, PIN, Sicherheit, Playback oder Architektur beeinflussen wuerde.

## Nicht enthalten

Dieses Verzeichnis enthaelt nicht:

- App-Code
- finale App-Repo-Modulstruktur
- konkrete Klassen- oder Dateinamen fuer die Implementierung
- konkrete Library-Versionen
- Commit- oder Branching-Strategie
- fertige technische Paketplaene fuer das App-Repository
- blinde Schritt-fuer-Schritt-Prompts, die ohne erneute Quellenpruefung abgearbeitet werden duerfen
- Demo-Datenvorgaben als Produktquelle

Die konkreten technischen Arbeitsplaene entstehen im App-Repository `Spegeli/vivicast`.

## Paketname

```text
com.vivicast.tv
```

## Repo-Zuordnung

```text
Spegeli/vivicast-docs = Produkt-, Architektur-, Design- und Codex-Referenzen
Spegeli/vivicast = App-Code
```
