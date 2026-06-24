# Vivicast Documentation Remediation Plan - Deep-Research-Follow-up

Status: abgeschlossen v2

## Einordnung

Diese Datei ist der aktive Folgeplan fuer die offenen Restarbeiten nach `deep-research-report.md`.

Der abgeschlossene Plan `DOCUMENTATION-REMEDIATION-PLAN.md` bleibt historische Dokumentation der Pakete A bis N. Er wird nicht flaechig umgeschrieben.

Dieser Folgeplan ist keine App-Anforderung, keine Architekturentscheidung und keine Designquelle. Fachliche oder technische Regeln muessen in die passende normative Quelle uebernommen werden.

## Grundlage

Der Plan konsolidiert:

- `deep-research-report.md`
- den aktuellen Stand von `vivicast-docs` am 2026-06-24
- die Owner-Entscheidungen zu sichtbaren UI-Begriffen, Trefferklassifikation, Diagrammrolle und Changelog-Behandlung

## Arbeitsweise

Jedes Paket wird einzeln abgearbeitet und validiert.

Pro Paket:

1. relevante aktuelle Quellen pruefen
2. historische Treffer bewusst getrennt erfassen
3. passende Hauptquelle aktualisieren
4. doppelte oder unklare Wiederholungen bereinigen
5. Links, Markdown und bei Diagrammen Mermaid pruefen
6. Changelog auf Englisch aktualisieren
7. Abschluss mit `umgesetzt`, `bewusst historisch belassen` und `offen` dokumentieren

## Scope und Trefferklassifikation

### Aktuelle und normative Quellen

- `README.md`
- `DOCS-GOVERNANCE.md`
- `codex/README.md`
- `codex/coding-rules.md`
- `prd/PRD-v1/`
- `architecture/decisions/`
- `design/screens/`
- `design/wireframes/`
- `design/interaction/`
- `design/components/`
- `design/design-system/`
- `design/mockups/README.md`
- `design/mockups/05-settings-search-mockup-spec.md`
- `design/mockups/high-fidelity/README.md`
- `design/mockups/high-fidelity/02-ui-direction-decisions.md`

### Aktuelle nicht-normative Onboarding-Hilfen

- `architecture/diagrams/`

### High-Fidelity-Scope

- `design/mockups/high-fidelity/02-ui-direction-decisions.md` ist die konkrete freigegebene UI-Richtungsentscheidung.
- `design/mockups/high-fidelity/README.md` ist aktueller Index.
- `design/mockups/high-fidelity/01-visual-target.md` ist `Entwurf v1` und wird als unterstuetzende visuelle Referenz geprueft, aber nicht staerker gewichtet als Owner Feedback oder UI Direction Decisions.
- `design/mockups/high-fidelity/rendered/*.png` sind visuelle Referenzassets, aber keine Markdown-/Grep-Quelle.

### Aktuelle QA- und Review-Hilfen

Aktuelle QA- und Review-Hilfen duerfen gezielt angepasst werden, wenn sie aktuelle Pruefpunkte, Begriffe oder Rueckverweise enthalten.

Historische Review-Dateien oder historische Abschnitte werden nicht flaechig umgeschrieben.

### Historische Trefferquellen

- `DOCUMENTATION-REMEDIATION-PLAN.md`
- `deep-research-report.md`
- aeltere Abschnitte in `CHANGELOG.md`
- historische QA-/Review-Dateien oder historische Abschnitte, sofern sie ausdruecklich als historisch markiert sind

Treffer in historischen Quellen werden im Abschlussbericht mit Datei, Begriff und Begruendung dokumentiert.

### Changelog-Regel

Aeltere Changelog-Eintraege bleiben historische Trefferquelle und werden nicht rueckwirkend umgeschrieben.

Neu hinzukommende Changelog-Eintraege zur Umsetzung dieses Folgeplans gelten als aktueller Arbeitsstand, bleiben Englisch und duerfen keine neuen Inkonsistenzen einfuehren.

## Key Decisions

- Sichtbarer UI-Begriff ist `Kanäle`.
- `Kanaele` darf nicht mehr als aktueller sichtbarer Produkt- oder UI-Begriff vorkommen.
- Technische Begriffe bleiben unveraendert: `Channel`, `ChannelSearchFts`, `EPGChannel`.
- Sichtbare UI-Texte verwenden Deutsch mit Umlauten.
- Technische Namen und Dateinamen duerfen Englisch oder ASCII bleiben.
- Changelog bleibt Englisch.
- Diagramme sind reine nicht-normative Onboarding- und Architekturhilfen.
- PRD, ADR und Codex-Regeln bleiben massgeblich.
- Diagramme duerfen keine neuen Modul-, Layer- oder Datenflussentscheidungen einfuehren.

## Pakete

### Paket A - Folgeplan und historische Abgrenzung

Status: erledigt am 2026-06-24

Aufgaben:

- diese Folgeplandatei anlegen
- Scope- und Trefferklassifikation inklusive High-Fidelity- und Changelog-Regel dokumentieren
- im alten `DOCUMENTATION-REMEDIATION-PLAN.md` nur einen kurzen historischen Hinweis auf diesen Folgeplan ergaenzen

Umsetzung:

- Folgeplandatei mit aktueller Quellenklassifikation, High-Fidelity-Scope, QA-/Review-Abgrenzung und Changelog-Regel angelegt.
- Alter Remediation-Plan nur um einen historischen Hinweis auf diese Folgeplandatei ergaenzt.

Erfolgskriterium:

- Es gibt einen aktiven Folgeplan, ohne den alten Plan flaechig umzuschreiben.

### Paket B - Suchlabel und sichtbare UI-Begriffe

Status: erledigt am 2026-06-24

Aufgaben:

- aktuelle normative und UI-nahe Quellen auf sichtbares `Kanäle` normieren
- `Kanaele` nur noch in bewusst historischen Kontexten zulassen
- technische Begriffe wie `Channel`, `ChannelSearchFts` und `EPGChannel` unveraendert lassen
- aktuelle QA-/Review-Hilfen nur dort anpassen, wo sie aktuelle Pruefpunkte oder aktuelle Begriffe enthalten
- historische QA-/Review-Treffer und aeltere Changelog-Treffer nur dokumentieren

Umsetzung:

- Aktuelle Produkt-, UI-, Codex- und QA-nahe Quellen verwenden sichtbar `Kanäle`.
- Technische `Channel`-Begriffe bleiben unveraendert.
- Historische Treffer in altem Plan, Research-Bericht, aelteren Changelog-Eintraegen und historischen Reviews bleiben bewusst als Historie erhalten.

Erfolgskriterium:

- Aktuelle Produkt- und UI-Quellen verwenden sichtbar nur `Kanäle`.

### Paket C - Settings-Vertrag und Persistenzregistry

Status: erledigt am 2026-06-24

Aufgaben:

- unklare oder alternative Speicherorte im aktuellen Settings-Vertrag entfernen
- dauerhaft gespeicherte Werte mit Speicherort, Key, Typ, Default und erlaubten Werten dokumentieren
- Dialogzustaende und reine Aktionen klar als nicht dauerhaft gespeichert markieren
- PRD, Datenmodell, Settings-Screen, Components, Wireframe, Mockup-Spec, DoD und aktuelle visuelle QA gegenpruefen

Verbindliche Defaults:

- `epgRefreshOnAppStartEnabled = true`
- `epgRefreshOnPlaylistChangeEnabled = true`
- `localLogoFolderUri = null`
- Der Exportdialog enthaelt das Feld `Backup-Typ` mit Dialog-Default `STANDARD`.

Umsetzung:

- Settings-Vertrag und Datenmodell enthalten eine eindeutige Persistenzregistry mit Speicherort, Key, Typ, Default und erlaubten Werten.
- Settings Screen, Components, Wireframe, Mockup-Spec und DoD sind auf dieselben Defaults und Speicherorte abgeglichen.
- `Backup-Typ` ist nur als transientes Feld im Exportdialog dokumentiert; es ist keine eigene Settings-Zeile und kein dauerhaft gespeicherter Wert.
- `localLogoFolderUri` bleibt als persistierbare URI mit Default `null` erlaubt.

Erfolgskriterium:

- PRD, Datenmodell und UI-Doku beschreiben dieselben Settings-Defaults und Persistenzorte.

### Paket D - PlaybackRequest und v1-Netzwerkpolicy

Status: erledigt am 2026-06-24

Aufgaben:

- alte Formulierungen wie `sichere Header-Referenzen` und `geschuetzte Header` entfernen
- kein provider-spezifisches Header-, Cookie- oder User-Agent-Konzept fuer v1 einfuehren
- globalen User-Agent als App-Einstellung beschreiben, die zentral durch Netzwerk-/Streamaufloesung angewendet wird

Umsetzung:

- Legacy-Formulierungen zu sicheren Header-Referenzen und geschuetzten Headern aus PlaybackRequest-Kontexten entfernt.
- PRD und ADR-013 stellen klar, dass v1 keine provider-spezifischen Header-, Cookie- oder User-Agent-Konzepte im PlaybackRequest fuehrt.

Erfolgskriterium:

- PlaybackRequest widerspricht nicht mehr der v1-Netzwerkpolicy.

### Paket E - Dokumentrollen und Modulhinweise

Status: erledigt am 2026-06-24

Aufgaben:

- technische Modulverbindlichkeit aus Designquellen entfernen oder klar als nicht-normativen Umsetzungshinweis markieren
- `:core:designsystem` in Designquellen nur noch als nicht-normativer Hinweis, Codex-/App-Repo-Baseline oder App-Repo-offen zulassen
- `:core:designsystem` nicht als PRD- oder Design-Anforderung formulieren

Umsetzung:

- Design-System- und High-Fidelity-Quellen markieren `:core:designsystem` nur noch als nicht-normativen Codex-/App-Repo-Baseline-Hinweis.
- Konkrete Modulstruktur bleibt als App-Repo-offen eingeordnet.

Erfolgskriterium:

- Design-Dateien beschreiben Design; technische Modulstruktur steht nicht als Designnorm.

### Paket F - Sprach- und Benennungskonvention

Status: erledigt am 2026-06-24

Aufgaben:

- Konvention fuer sichtbare UI-Texte, technische Namen und Changelog dokumentieren
- sichtbare aktuelle Vorkommen von `Ueber die App` auf `Über die App` normieren
- englische Dateinamen wie `about-app.md` unveraendert erlauben

Umsetzung:

- Governance dokumentiert sichtbare deutsche UI-Begriffe mit Umlauten, technische englische/ASCII-Namen und englische Changelog-Eintraege.
- Aktuelle sichtbare Produkt- und UI-Quellen verwenden `Über die App`; englische Dateinamen bleiben unveraendert erlaubt.

Erfolgskriterium:

- Sichtbare Produktbereiche sind in aktuellen Quellen einheitlich benannt.

### Paket G - Architekturdiagramme

Status: erledigt am 2026-06-24

Aufgaben:

- alle geplanten Mermaid-Diagramme unter `architecture/diagrams/` erstellen:
  - `01-system-context.md`
  - `02-module-layer-dependencies.md`
  - `03-import-refresh-flow.md`
  - `04-epg-flow.md`
  - `05-player-progress-flow.md`
  - `06-backup-restore-flow.md`
- Quellenhinweise und Konflikthinweis in jedem Diagramm dokumentieren
- speziell im Modul-/Layer-Diagramm nur belegte ADR-/Codex-Regeln visualisieren
- unbelegte Modulstruktur als App-Repo-offen markieren

Umsetzung:

- Alle sechs geplanten Diagrammdateien unter `architecture/diagrams/` erstellt und im Diagramm-Index verlinkt.
- Jede Diagrammdatei enthaelt Quellenhinweise und den Konflikthinweis, dass PRD, ADR und Codex-Regeln gewinnen.
- Modul-/Layer-Diagramm markiert App-Repo-offene Strukturpunkte und erzeugt keine neue Modulentscheidung.

Erfolgskriterium:

- Diagramme helfen beim Onboarding, ohne neue Entscheidungen zu erzeugen.

### Paket H - QA-Referenzen und Abschlussstatus

Status: erledigt am 2026-06-24

Aufgaben:

- aktuelle QA-/Review-Hilfen pruefen, wo sie Anforderungen wiederholen
- Wiederholungen durch Verweise auf zustaendige PRD-/ADR-/Designquellen ergaenzen oder entschaerfen
- historische QA-/Review-Dateien nicht flaechig umschreiben
- Final Cross-QA und Readiness-QA nach Paketen A-G aktualisieren

Umsetzung:

- Aktuelle QA- und Readiness-Dateien verweisen auf normative Quellen und ordnen Diagramme als Onboarding-Hilfen ein.
- Final Cross-QA dokumentiert Deep-Research-Follow-up, technische Checks und historische Trefferquellen getrennt.
- Historische QA-/Review-Dateien wurden nicht flaechig umgeschrieben.

Erfolgskriterium:

- QA bleibt Review-Hilfe und wird nicht zur parallelen Normquelle.

## Validierung nach jedem Paket

- aktuelle Quellen gegen paketbezogene Altbegriffe durchsuchen
- aktuelle QA-/Review-Hilfen separat bewerten
- historische Quellen separat erfassen
- neue Changelog-Eintraege als aktuellen Arbeitsstand pruefen
- aeltere Changelog-Eintraege als historische Treffer behandeln
- Backtick-Markdown-Links pruefen
- Markdown-Codefences pruefen
- Mermaid-Codefences und Diagrammsyntax pruefen, soweit lokal moeglich
- offene Marker nur in aktuellen Arbeitsstaenden als Fehler werten

## Finaler Testplan

- kein aktueller sichtbarer UI-Kontext verwendet `Kanaele`
- `Kanäle` ist der einzige sichtbare Suchgruppenname in aktuellen Produkt-/UI-Quellen
- technische `Channel`-Begriffe bleiben erhalten
- kein aktueller Settings-Vertrag enthaelt unklare oder alternative Speicherorte
- persistierte Settings haben Key, Typ, Default und erlaubte Werte
- PlaybackRequest enthaelt keine Header-/Cookie-/Provider-User-Agent-Konzepte
- `:core:designsystem` erscheint nicht mehr in Designquellen als verbindliche Vorgabe
- Diagramme enthalten keine neuen Regeln und markieren App-Repo-offene Strukturpunkte klar
- QA-Dateien verweisen auf normative Quellen, statt eigene Regeln zu etablieren
- neue Changelog-Eintraege sind Englisch und enthalten keine neuen Inkonsistenzen
- historische Treffer sind mit Datei, Begriff und Begruendung dokumentiert

## Abschlussbericht

### Umgesetzt

- Aktiver Folgeplan erstellt und alter Remediation-Plan nur historisch verlinkt.
- Nacharbeit umgesetzt: `Home` als erlaubte sichtbare Produkt-Ausnahme in der Governance dokumentiert.
- Nacharbeit umgesetzt: `architecture/diagrams/` in der Governance als nicht-normative Onboarding-Hilfe eingeordnet; `architecture/decisions/` bleibt normative ADR-Quelle.
- Nacharbeit umgesetzt: `Backup-Typ` aus Settings-Zeilen entfernt und nur als Exportdialog-Feld belassen.
- Sichtbare aktuelle UI-Begriffe auf `Kanäle` und `Über die App` normiert.
- Settings-Vertrag und Datenmodell auf eindeutige Persistenzorte, Keys, Typen, Defaults und erlaubte Werte abgeglichen.
- PlaybackRequest-v1-Regeln ohne provider-spezifische Header-, Cookie- oder User-Agent-Konzepte dokumentiert.
- Design-Modulhinweise zu `:core:designsystem` als nicht-normative Codex-/App-Repo-Baseline eingeordnet.
- Architekturdiagramme als Onboarding-Hilfen erstellt und gegen PRD-/ADR-/Codex-Vorrang abgegrenzt.
- Aktuelle QA-/Readiness-Dateien als Review-Hilfen mit Rueckverweisen statt paralleler Normquelle aktualisiert.
- Neuer Changelog-Eintrag in Englisch ergaenzt.

### Bewusst historisch belassen

- `DOCUMENTATION-REMEDIATION-PLAN.md`: abgeschlossene Paket-Historie mit alten Such-, Settings- und Review-Treffern; nur ein Hinweis auf diesen Folgeplan wurde ergaenzt.
- `deep-research-report.md`: Research-Artefakt und Trefferquelle; Inhalte bleiben unveraendert, auch wenn dort alte Begriffe und Vorschlaege stehen.
- `CHANGELOG.md`: aeltere Eintraege sind historische Trefferquelle und werden nicht rueckwirkend umgeschrieben; nur der neue 2026-06-24-Eintrag gilt als aktueller Arbeitsstand.
- Historische Review-Dateien oder historische Review-Abschnitte bleiben Review-Historie und duerfen keine neuen Anforderungen einfuehren.

### Konkrete verbleibende Grep-Treffer

Aktuelle erlaubte Treffer:

- `codex/coding-rules.md:65`: Diagnosedaten duerfen nicht in Room oder Standard-Backup; keine alternative Settings-Persistenz.
- `prd/PRD-v1/06-data-model.md:1161`: Diagnosesitzungen nicht in Room, DataStore oder Standard-Backup; Backup-/Datenschutz-Abgrenzung.
- `prd/PRD-v1/06-data-model.md:1231`: Medien-Cache nicht in Room, DataStore oder Standard-Backup; Cache-Abgrenzung.
- `prd/PRD-v1/08-android-tv-security.md:256`: Security-Flags duerfen in DataStore oder gleichwertigem privatem Speicher liegen; kein Restore-Ziel und kein Settings-Vertragsfeld.
- `prd/PRD-v1/09-implementation-and-dod.md:404`: Diagnosedaten nur appinterne Dateien; DoD-Abgrenzung, keine Settings-Alternative.
- `design/review/07-data-model-qa.md:132`: QA-Spiegelung der Diagnosedaten-Abgrenzung; Review-Hilfe, keine Normquelle.

Historische Treffer:

- `DOCUMENTATION-REMEDIATION-PLAN.md:43,47,52,205,223,522,538,568,633,653,710,772`: alte Paketentscheidungen und abgeschlossene Arbeitshistorie mit `Kanaele`, `Ueber die App` und EPG-Kanaele.
- `deep-research-report.md:19,24,25,29,44,48,52,60,71,72,74`: Research-Befunde und Vorschlaege zu Settings, Suchlabel, PlaybackRequest und Benennung; bleiben als Ausgangsbefund unveraendert.
- `CHANGELOG.md:138,170`: aeltere Changelog-Eintraege mit `Ueber die App`; bleiben historische Eintraege.
- `DOCUMENTATION-REMEDIATION-PLAN-2026-06-24.md:90,127,177,216,290`: Altbegriffe nur als Pruef-, Ausschluss- oder Umsetzungskriterien dieses Folgeplans.

### Offen

- Mermaid wurde lokal nur statisch ueber Codefences geprueft; ein Rendercheck per Mermaid CLI wurde nicht ausgefuehrt, weil kein lokales `mmdc` verfuegbar war.
- Konkrete Gradle-/Modulstruktur, vorhandene Tests und Performancewerte muessen im App-Repository `Spegeli/vivicast` geprueft werden.
