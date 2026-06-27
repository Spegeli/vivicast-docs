# 10 - Final Cross-QA and Fresh-Developer Dry Run

Status: Abschlussgate v2

## Zweck

Diese Datei dokumentiert die finale Cross-QA fuer `vivicast-docs` nach Abschluss der Remediation-Pakete A bis M und des Deep-Research-Follow-ups vom 2026-06-24.

Sie ist eine Review-Referenz und keine neue Produkt-, Architektur- oder Designquelle.

Ziel ist zu pruefen, ob ein neuer Entwickler ohne Gespraechskontext einen technischen Umsetzungsplan fuer das App-Repository `Spegeli/vivicast` erstellen kann, ohne Produktentscheidungen erfinden zu muessen.

## Gepruefter Stand

Datum: 2026-06-24

Gepruefte Hauptquellen:

- `README.md`
- `DOCS-GOVERNANCE.md`
- `codex/README.md`
- `codex/coding-rules.md`
- `prd/PRD-v1/`
- `architecture/decisions/`
- `architecture/diagrams/README.md`
- `architecture/diagrams/01-system-context.md`
- `architecture/diagrams/02-module-layer-dependencies.md`
- `architecture/diagrams/03-import-refresh-flow.md`
- `architecture/diagrams/04-epg-flow.md`
- `architecture/diagrams/05-player-progress-flow.md`
- `architecture/diagrams/06-backup-restore-flow.md`
- `design/screens/`
- `design/wireframes/`
- `design/interaction/`
- `design/components/`
- `design/design-system/`
- `archive/review/2026-06-24/`
- `design/mockups/`

Archivierte historische Quellen:

- `archive/remediation/2026-06-24/DOCUMENTATION-REMEDIATION-PLAN.md`
- `archive/remediation/2026-06-24/DOCUMENTATION-REMEDIATION-PLAN-2026-06-24.md`
- `archive/remediation/2026-06-24/deep-research-report.md`

Diese Archivdateien sind keine aktuelle Arbeitsgrundlage; sie dokumentieren nur abgeschlossene Remediation- und Research-Historie.

## Technische Pruefungen

| Pruefung | Ergebnis |
| --- | --- |
| Markdown-Dateibestand per `rg --files` erfasst | bestanden |
| Backtick-Dateiverweise auf `.md`-Dateien geprueft | keine fehlenden Referenzen gefunden |
| Markdown-Codefences geprueft | alle Codefences ausgeglichen |
| Offene V- und T-Issue-Marker geprueft | keine offenen V- oder T-Marker im Issue-Register |
| Redundante `.gitkeep`-Dateien geprueft | keine `.gitkeep`-Dateien vorhanden |
| Alte PRD-Kapitelkollisionen geprueft | keine produktiven Treffer fuer die alten Kapitel-8/9/10/11-Kollisionsformulierungen |
| Alte Diagnose-Kapitelreferenz geprueft | keine produktiven Treffer fuer die alte unklare Diagnose-Kapitelreferenz |
| Alte Mockup-Phasenformulierung geprueft | keine produktiven Treffer fuer alte Phasen- oder Prioritaetsueberschriften im Mockup-Index |
| Deep-Research-Follow-up geprueft | Paket A bis H umgesetzt; historische Treffer getrennt dokumentiert |
| Mermaid-Diagramm-Codefences geprueft | Diagramm-Codefences vorhanden und ausgeglichen |

Tooling-Hinweis:

`git status --short` konnte im Arbeitsverzeichnis nicht ausgefuehrt werden, weil `C:\Users\Andreas\Desktop\codex\vivicast-docs` kein Git-Repository ist.

## Ueberholte Aussagen

Die Suche nach ueberholten Aussagen ergab keine blockierenden Widersprueche.

Einordnung der verbleibenden erwarteten Treffer:

- Begriffe wie `Phasenplan` oder `Phasen-Prompts` erscheinen nur als Abgrenzung, dass das Docs-Repository keinen festen Implementierungsplan vorgibt.
- Begriffe wie `Zusammenfuehren`, `Restore-Konfliktdialoge` und `Als Kopie importieren` erscheinen nur als v1-Ausschluss oder Legacy-Migrationskontext.
- Historische QA-Dateien enthalten weiterhin `Problem`/`Behoben`-Abschnitte. Diese sind als Review-Historie eingeordnet und duerfen keine neuen Anforderungen einfuehren.
- Historische Treffer in `archive/remediation/2026-06-24/`, aelteren `CHANGELOG.md`-Eintraegen und historischen Review-Dateien sind bewusst belassen.
- Wireframe-Notation wie `[ ]` ist keine offene Aufgabe.

## Traceability-Matrix

| Bereich | PRD-Quellen | ADR-Quellen | Design-/UX-Quellen | QA-/Codex-Quellen | Readiness |
| --- | --- | --- | --- | --- | --- |
| Home | `01-product-overview.md`, `05-iptv-epg-favorites.md`, `09-implementation-and-dod.md` | keine eigene ADR erforderlich | `design/screens/01-home.md`, `design/wireframes/00-home.md`, `design/interaction/nav.md`, `design/design-system/05-screen-patterns.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `codex/README.md` | bereit fuer App-Repo-Planung |
| Live-TV | `02-live-tv-requirements.md`, `05-iptv-epg-favorites.md`, `07-background-jobs-performance.md`, `09-implementation-and-dod.md` | `ADR-001-provider-isolation.md`, `ADR-003-refresh-strategy.md`, `ADR-009-provider-deletion-and-favorites.md`, `ADR-012-atomic-import-refresh.md` | `design/screens/02-live-tv.md`, `design/wireframes/01-live-tv-browser.md`, `design/interaction/01-live-tv-adaptive-columns.md`, `design/design-system/04-focus-navigation.md`, `design/design-system/05-screen-patterns.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| VOD | `03-movies-series-requirements.md`, `05-iptv-epg-favorites.md`, `06-data-model.md`, `09-implementation-and-dod.md` | `ADR-007-trailer-strategy.md`, `ADR-009-provider-deletion-and-favorites.md`, `ADR-013-player-playback-progress.md` | `design/screens/04-movies.md`, `design/screens/05-series.md`, `design/wireframes/02-movies.md`, `design/wireframes/03-series.md`, `design/components/list-grid-items.md`, `design/mockups/04-vod-mockup-spec.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `archive/review/2026-06-24/07-data-model-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Suche | `04-search-settings-player-requirements.md`, `06-data-model.md`, `07-background-jobs-performance.md`, `09-implementation-and-dod.md` | `ADR-005-local-search.md`, `ADR-008-android-tv-integration.md` | `design/screens/06-search.md`, `design/wireframes/04-search.md`, `design/mockups/05-settings-search-mockup-spec.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `archive/review/2026-06-24/09-codex-implementation-readiness-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Settings | `04-search-settings-player-requirements.md`, `10-backup-import-requirements.md`, `11-about-app-requirements.md` | `ADR-004-backup-strategy.md`, `ADR-014-security-data-network-backup.md` | `design/screens/07-settings.md`, `design/screens/08-playlist-epg.md`, `design/wireframes/05-settings.md`, `design/wireframes/08-about-app.md`, `design/components/settings.md`, `design/components/about-app.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `archive/review/2026-06-24/05-about-app-qa.md`, `archive/review/2026-06-24/06-cross-document-qa.md` | bereit fuer App-Repo-Planung |
| Datenmodell | `06-data-model.md`, `10-backup-import-requirements.md`, `13-test-strategy.md` | `ADR-004-backup-strategy.md`, `ADR-008-android-tv-integration.md`, `ADR-010-stable-identities-and-restore-keys.md`, `ADR-014-security-data-network-backup.md` | Designquellen referenzieren Daten nur, definieren sie nicht neu | `archive/review/2026-06-24/07-data-model-qa.md`, `archive/review/2026-06-24/08-adr-alignment-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Quellen und Parser | `04-search-settings-player-requirements.md`, `05-iptv-epg-favorites.md`, `07-background-jobs-performance.md`, `12-parser-source-contracts.md`, `13-test-strategy.md` | `ADR-011-parser-source-contracts.md`, `ADR-012-atomic-import-refresh.md`, `ADR-014-security-data-network-backup.md` | `design/screens/08-playlist-epg.md`, `design/components/settings.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| EPG | `04-search-settings-player-requirements.md`, `05-iptv-epg-favorites.md`, `06-data-model.md`, `07-background-jobs-performance.md`, `12-parser-source-contracts.md` | `ADR-002-epg-strategy.md`, `ADR-003-refresh-strategy.md`, `ADR-012-atomic-import-refresh.md` | `design/screens/08-playlist-epg.md`, `design/wireframes/05-settings.md`, `design/design-system/05-screen-patterns.md` | `archive/review/2026-06-24/07-data-model-qa.md`, `archive/review/2026-06-24/08-adr-alignment-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Player und Progress | `02-live-tv-requirements.md`, `03-movies-series-requirements.md`, `04-search-settings-player-requirements.md`, `06-data-model.md`, `09-implementation-and-dod.md` | `ADR-006-timeshift-strategy.md`, `ADR-013-player-playback-progress.md` | `design/screens/03-player.md`, `design/wireframes/06-player.md`, `design/interaction/02-player-timeline-controls.md`, `design/components/player.md`, `design/mockups/03-player-mockup-spec.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `archive/review/2026-06-24/07-data-model-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Backup und Restore | `04-search-settings-player-requirements.md`, `06-data-model.md`, `10-backup-import-requirements.md`, `13-test-strategy.md` | `ADR-004-backup-strategy.md`, `ADR-010-stable-identities-and-restore-keys.md`, `ADR-014-security-data-network-backup.md` | `design/screens/07-settings.md`, `design/wireframes/05-settings.md`, `design/components/settings.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `archive/review/2026-06-24/07-data-model-qa.md`, `archive/review/2026-06-24/08-adr-alignment-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Schutzkonzept | `08-android-tv-security.md`, `10-backup-import-requirements.md`, `11-about-app-requirements.md`, `13-test-strategy.md` | `ADR-014-security-data-network-backup.md` | `design/screens/07-settings.md`, `design/wireframes/05-settings.md`, `design/components/settings.md`, `design/interaction/nav.md` | `archive/review/2026-06-24/04-visual-acceptance-checklist.md`, `archive/review/2026-06-24/05-about-app-qa.md`, `archive/review/2026-06-24/08-adr-alignment-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Android-TV-Systemintegration | `04-search-settings-player-requirements.md`, `06-data-model.md`, `08-android-tv-security.md`, `09-implementation-and-dod.md` | `ADR-008-android-tv-integration.md` | `design/interaction/nav.md`, `design/screens/06-search.md`, `archive/review/2026-06-24/04-visual-acceptance-checklist.md` | `archive/review/2026-06-24/08-adr-alignment-qa.md`, `archive/review/2026-06-24/09-codex-implementation-readiness-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |
| Tests und DoD | `09-implementation-and-dod.md`, `13-test-strategy.md` | ADRs je betroffenem Fachbereich | `archive/review/2026-06-24/04-visual-acceptance-checklist.md` fuer UI-QA | `archive/review/2026-06-24/09-codex-implementation-readiness-qa.md`, `codex/coding-rules.md` | bereit fuer App-Repo-Planung |

Architekturdiagramme aus `architecture/diagrams/` sind Onboarding-Hilfen fuer Systemkontext, Modul-/Layer-Abhaengigkeiten, Import/Refresh, EPG, Player/Progress und Backup/Restore. Sie ersetzen keine PRD-, ADR- oder Codex-Regeln.

## Readiness-Bewertung

| Bereich | Ergebnis |
| --- | --- |
| Home | bereit |
| Live-TV | bereit |
| VOD | bereit |
| Suche | bereit |
| Settings | bereit |
| Datenmodell | bereit |
| Quellen und Parser | bereit |
| EPG | bereit |
| Player und Progress | bereit |
| Backup und Restore | bereit |
| Schutzkonzept | bereit |
| Android-TV-Systemintegration | bereit |
| Tests und DoD | bereit |
| Architekturdiagramme | Onboarding-Hilfen vorhanden |
| Deep-Research-Follow-up | abgeschlossen |

Bereit bedeutet:

- Produktregeln sind im PRD dokumentiert.
- Architekturentscheidungen sind als ADR oder klar abgegrenzte App-Repo-Arbeitsbaseline dokumentiert.
- UI-Struktur, Fokus, Navigation oder Komponenten sind in Designquellen dokumentiert, sofern der Bereich UI besitzt.
- QA- und Review-Dateien enthalten keine neue Anforderungsquelle, sondern pruefen oder verweisen.
- Codex kann im App-Repository einen technischen Plan erstellen.

Bereit bedeutet nicht:

- dass die App bereits implementiert ist
- dass App-Repo-Module, Gradle, Abhaengigkeiten oder Tests bereits geprueft sind
- dass Performancebudgets ohne Messung auf Zielgeraet erfuellt sind

## Fresh-Developer-Dry-Run

Simulierter Einstieg ohne Gespraechskontext:

1. Neuer Entwickler liest `README.md`.
2. README trennt `Spegeli/vivicast-docs` von `Spegeli/vivicast` und verweist auf `codex/README.md`.
3. Neuer Entwickler liest `codex/README.md`.
4. Codex-Einstieg verweist auf `DOCS-GOVERNANCE.md`, relevante PRD-Dateien, `prd/PRD-v1/13-test-strategy.md`, ADRs, Designquellen und QA-Dateien.
5. Neuer Entwickler liest `DOCS-GOVERNANCE.md` fuer Rollen und Konfliktregel.
6. Neuer Entwickler waehlt fuer einen Fachbereich die Quellen aus der Traceability-Matrix.
7. Neuer Entwickler erstellt im App-Repository einen technischen Plan mit gelesenen Quellen, betroffenen Modulen, Risiken, Annahmen und Testnachweisen.

Dry-Run-Ergebnis:

Ein neuer Entwickler kann einen technischen Plan erstellen, ohne Produktentscheidungen aus dem Chat rekonstruieren zu muessen.

## Rest-Risiken

Keine blockierenden Dokumentationsrisiken fuer die App-Repo-Planung gefunden.

Bewusste Restpunkte:

- Die konkrete Modulstruktur, Gradle-Konfiguration und vorhandene Testinfrastruktur muessen im App-Repository `Spegeli/vivicast` geprueft werden.
- Architekturdiagramme sind vorhanden, aber reine Onboarding-Hilfen und ersetzen keine ADRs.
- Performancebudgets aus `prd/PRD-v1/13-test-strategy.md` muessen spaeter auf einem dokumentierten Referenzgeraet oder Referenzprofil gemessen werden.
- Historische Review-Dateien bleiben im Repository, sind aber als Review-Historie oder Pruefhilfen eingeordnet.

## Abschlussurteil

`vivicast-docs` ist nach dieser Cross-QA ausreichend konsistent, um als Referenzbasis fuer die technische App-Repo-Planung zu dienen.

Bewusst historisch belassen bleiben archivierte Plan-/Research-Dateien unter `archive/remediation/2026-06-24/`, aeltere Changelog-Eintraege und historische Review-Treffer.

Der naechste sinnvolle Schritt liegt im App-Repository `Spegeli/vivicast`: vorhandene Projektstruktur pruefen und darauf basierend einen technischen Implementierungsplan erstellen.
