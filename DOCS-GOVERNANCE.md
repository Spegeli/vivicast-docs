# Vivicast Docs Governance

Status: verbindlich v7

Diese Datei ist die kanonische Quelle fuer Dokumentrollen, Quellenverantwortung und Konfliktregeln im Vivicast-Docs-Repository.

Sie dient als Arbeitsregel fuer menschliche Aenderungen am Docs-Repository.

## Wichtig fuer Codex

Diese Datei ist keine App-Anforderung, keine Designquelle und keine technische Umsetzungsreferenz fuer `Spegeli/vivicast`.

Codex soll diese Datei verwenden, um Dokumentrollen, Quellenprioritaet und Konfliktregeln zu verstehen.

Codex darf aus dieser Datei keine Produkt-, UI- oder Architekturanforderungen ableiten.

Codex soll fuer die App-Umsetzung mit `codex/README.md` starten.

Fuer die App-Umsetzung nutzt Codex zusaetzlich den Masterplan unter `codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md`.

Der Masterplan steuert Reihenfolge und Paketlogik fuer Codex, ist aber keine Produkt-, UI-, Architektur- oder Testquelle.

Die eigentlichen technischen Arbeitsplaene erstellt Codex im App-Repository `Spegeli/vivicast`.

`codex/README.md` verweist fuer Rollen und Konflikte auf diese Datei.

## Ziel

Das Repository soll eine eindeutige Referenzbasis fuer die spaetere App-Umsetzung sein.

Codex soll nicht aus widerspruechlichen oder mehrfach gepflegten Informationen raten muessen.

## Grundregel

Jede Information soll genau eine verbindliche Hauptquelle haben.

Andere Dateien duerfen darauf verweisen, sollen die Information aber nicht erneut vollstaendig beschreiben.

## Dokumentrollen

Normative Quellen definieren verbindliche Anforderungen oder Entscheidungen.

Codex-Dateien enthalten Arbeitsregeln und Umsetzungshinweise fuer Codex. Sie duerfen PRD, ADRs und Designentscheidungen nicht ersetzen.

Archivierte Arbeitsnachweise dokumentieren abgeschlossene Pruef- und Remediation-Staende. Sie sind keine aktive Produkt-, Design-, Architektur- oder Codex-Quelle.

## Quellenverantwortung

| Bereich | Hauptquelle | Rolle |
| --- | --- | --- |
| Produktumfang | `prd/PRD-v1/` | normativ |
| fachliche Anforderungen | `prd/PRD-v1/` | normativ |
| Datenregeln | `prd/PRD-v1/` | normativ |
| Sicherheitsanforderungen | `prd/PRD-v1/` und `prd/PRD-v1/08-android-tv-security.md` | normativ |
| Backup- und Importvertrag | `prd/PRD-v1/10-backup-import-requirements.md` | normativ |
| Teststrategie und messbare DoD | `prd/PRD-v1/13-test-strategy.md` und `prd/PRD-v1/09-implementation-and-dod.md` | normativ |
| Architekturentscheidungen | `architecture/decisions/` | normativ |
| Architekturdiagramme | `architecture/diagrams/` | nicht-normative Onboarding- und Visualisierungshilfe |
| technische Struktur | `architecture/decisions/` und dokumentierte App-Repo-Entscheidungen | normativ nur, soweit als ADR oder App-Repo-Entscheidung dokumentiert |
| Screen-Struktur | `design/screens/` | normativ fuer UI-Struktur |
| Wireframes | `design/wireframes/` | normativ fuer Layout |
| Fokus und Fernbedienung | `design/interaction/` | normativ fuer Bedienung |
| UI-Komponenten | `design/components/` | normativ fuer Komponentenverwendung |
| visuelles Designsystem | `design/design-system/` | normativ fuer visuelle Grundlagen |
| UI Direction Decisions | `design/mockups/high-fidelity/02-ui-direction-decisions.md` | normativ fuer visuelle Richtung, soweit sie PRD und ADRs nicht widerspricht |
| Codex-Arbeitsregeln | `codex/` | Arbeitsregel, keine Produktquelle |
| Codex-Umsetzungsplaene | `codex/plans/` | aktive Codex-Planungsquelle fuer Reihenfolge und Paketlogik, keine Produktquelle |
| archivierte Arbeitsnachweise | `archive/` | historisch, keine aktive Arbeitsquelle |

## PRD-Regeln

Das PRD beschreibt, was die App fachlich koennen muss.

Das PRD soll enthalten:

- Featureumfang
- Muss-, Soll- und Kann-Anforderungen
- fachliche Regeln
- Datenregeln
- Sicherheitsanforderungen
- Plattformanforderungen
- Verweise auf Design- und Architekturquellen

Das PRD soll nicht enthalten:

- detaillierte Layout-Zonen
- konkrete Wireframe-Beschreibungen
- Pixel-, Spacing- oder Farbwerte
- screenbezogene Fokuspfade
- konkrete UI-Komponentenlisten
- feste Implementierungsphasen
- Codex-Vorgaben

Wenn Layout oder Interaktion relevant ist, verweist das PRD auf die passende Design-Datei.

Beispiel:

```text
Layout und Fokusverhalten: siehe design/screens/02-live-tv.md und design/interaction/01-live-tv-adaptive-columns.md
```

## Design-Regeln

Design-Dateien beschreiben, wie die App aussieht und bedient wird.

Dazu gehoeren:

- Screen-Aufbau
- Layout-Zonen
- Navigation innerhalb eines Screens
- Fokus-Startpunkt
- D-Pad-Verhalten
- OK-, Zurueck- und Menu-Verhalten
- Empty-, Loading- und Error-States
- Komponentenverwendung
- visuelle Dichte

Design-Dateien duerfen fachliche Anforderungen nur referenzieren, nicht neu definieren.

## Architektur-Regeln

Architekturdateien beschreiben technische Struktur und Entscheidungen.

Sie sollen nicht UI-Layouts oder Produktumfang neu definieren.

Bei fachlichen Fragen gilt das PRD.

Bei Layoutfragen gelten die Design-Dateien.

`architecture/decisions/` enthaelt normative ADRs.

`architecture/diagrams/` ist nicht normativ. Diagramme visualisieren bestehende PRD-, ADR-, Codex- oder App-Repo-Regeln als Onboarding-Hilfe und duerfen keine neuen Produkt-, Modul-, Layer-, Persistenz- oder Datenflussentscheidungen einfuehren.

## Codex-Regeln

Der Codex-Ordner enthaelt Referenz-, Arbeits- und Planungsregeln fuer Codex.

`codex/README.md` ist der Einstieg fuer Codex.

`codex/coding-rules.md` enthaelt technische Arbeitsregeln.

`codex/tv-compose-reference-guide.md` enthaelt TV-/Compose-Referenzen.

`codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md` enthaelt den aktiven Masterplan fuer Reihenfolge, Paketlogik und Codex-Kontrollpunkte.

Der Masterplan ist verbindlich fuer Codex als Umsetzungsfahrplan, aber er darf PRD, ADRs, Designquellen, Teststrategie oder diese Governance nicht ueberschreiben.

Codex muss aus dem Masterplan fuer jedes Umsetzungspaket einen eigenen technischen Detailplan im App-Repository `Spegeli/vivicast` ableiten.

Diese App-Repo-Arbeitsplaene duerfen den Masterplan konkretisieren, aber keine Produkt-, Design-, Architektur-, Sicherheits-, Backup/Restore-, PIN-, Playback- oder Persistenzentscheidung eigenstaendig aendern.

Das Repository `vivicast-docs` ist waehrend der App-Implementierung eine read-only Referenz fuer Codex.

Codex darf aktive Dateien in `vivicast-docs` nicht eigenstaendig aendern, ausser der Owner beauftragt ausdruecklich eine Dokumentationsaenderung.

Architekturpflichten in `codex/coding-rules.md`, die nicht direkt aus PRD oder ADR ableitbar sind, gelten als App-Repo-Arbeitsbaseline.

Wenn daraus eine neue verbindliche Architekturentscheidung entsteht oder davon abgewichen wird, muss sie als ADR oder als dokumentierte App-Repo-Entscheidung festgehalten werden.

## Benennungskonvention

Sichtbare UI-Texte und Produktbereiche werden in Deutsch mit Umlauten geschrieben.

Verbindliche sichtbare Begriffe:

- `Kanäle`
- `Über die App`

Erlaubte sichtbare Produkt-Ausnahme:

- `Home` bleibt als Top-Navigation-, Startbereich- und Startscreen-Label sichtbar erlaubt.

Technische Namen, Entity-Namen, FTS-Namen, Dateinamen und ADR-Dateinamen duerfen Englisch oder ASCII verwenden, zum Beispiel `Channel`, `ChannelSearchFts`, `EPGChannel` oder `design/components/about-app.md`.

`CHANGELOG.md` bleibt Englisch. Aeltere Changelog-Eintraege sind historische Trefferquellen und werden nicht rueckwirkend umgeschrieben.

## Archiv-Regeln

Archivierte Arbeitsnachweise duerfen zur historischen Nachvollziehbarkeit erhalten bleiben.

Sie duerfen nicht:

- neue v1-Funktionen einfuehren
- PRD-, ADR-, Design- oder Codex-Regeln ueberschreiben
- als Grundlage fuer App-Verhalten dienen
- eine feste Umsetzungsreihenfolge vorgeben
- ohne erneute Pruefung gegen aktuelle Quellen reaktiviert oder in aktive Dokumente uebernommen werden

Wenn ein Archivfund fachlich relevant wirkt, muss er zuerst gegen aktuelle PRD-, ADR-, Design-, Codex- und Governance-Quellen geprueft werden.

## Konfliktregel

Wenn sich Dateien widersprechen, gilt:

1. PRD fuer Produktumfang und fachliche Regeln
2. ADRs fuer Architekturentscheidungen
3. Design-System fuer visuelle Grundlagen
4. Screen Specs und Wireframes fuer Layout
5. Interaction Specs fuer Bedienung und Fokus
6. Component Specs fuer wiederverwendbare UI-Bausteine
7. UI Direction Decisions fuer konkrete visuelle Richtung, soweit sie PRD, ADRs, Screen Specs und Interaction Specs nicht widersprechen
8. Codex-Dateien und Codex-Umsetzungsplaene nur als Arbeits-, Referenz- und Planungsregeln
9. archivierte Arbeitsnachweise nie als aktive Quelle

## Pflegeprozess

Bei jeder groesseren Dokumentationsaenderung pruefen:

- Entsteht eine doppelte Information?
- Gibt es eine eindeutig passende Hauptquelle?
- Muss eine alte Stelle gekuerzt und auf die neue Quelle verweisen?
- Muss die Quellenprioritaet angepasst werden?
- Muss `README.md` aktualisiert werden?
- Muss `CHANGELOG.md` aktualisiert werden?

## Zielzustand

Am Ende soll Codex fuer jeden App-Bereich wissen:

- was fachlich gebaut werden muss
- wo die Layoutquelle liegt
- wie Navigation und Fokus funktionieren
- welche Komponenten verwendet werden
- welche Zustandsvarianten existieren
- welche Datei bei Konflikten gewinnt
