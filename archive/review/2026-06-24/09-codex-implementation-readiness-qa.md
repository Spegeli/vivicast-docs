# 09 - Codex Implementation Readiness QA

Status: Review-Referenz v10

## Zweck

Diese Datei prueft fachbereichsweise, ob Codex genug Dokumentationsgrundlage hat, um im App-Repository `Spegeli/vivicast` selbststaendig einen technischen Umsetzungsplan zu erstellen.

Diese Datei ist keine Umsetzungsreihenfolge, kein Phasenplan und kein Prompt.

Diese Datei ist keine pauschale Implementierungsfreigabe fuer das gesamte Produkt.

Codex entscheidet die konkrete Reihenfolge der Umsetzung selbst.

## Readiness-Kriterien

Ein Fachbereich ist bereit fuer Codex-Planung, wenn folgende Punkte fuer diesen Bereich erfuellt sind:

- Produktumfang ist im PRD definiert.
- Architekturentscheidungen sind in ADRs vorhanden und QA-abgeglichen.
- Datenmodell ist gegen Backup, Verlauf, Favoriten, Suchverlauf und Wiedergabefortschritt abgeglichen.
- Lokale Suche besitzt FTS-, Ranking-, Limit- und Indexpflege-Regeln.
- VOD-Abschluss, manuelle Gesehen-Markierung und die Abgrenzung zu Auto-Next sind als fachlicher Vertrag definiert.
- Player/Progress besitzt PlaybackRequest-, Catch-Up-, Progress-Speicher-, Track-Auswahl-, External-Player- und Timeshift-Grenzfallregeln.
- Schutzkonzept besitzt Datenklassifizierung, Secret-Store-, Vollbackup-Krypto-, PIN-Sperr-, HTTP/TLS- und Diagnosebereinigungsregeln.
- Android-TV-Systemintegration besitzt stabile Deep-Link-Ziele, Systemsuche-, Watch-Next-, Providerstatus- und Kindersicherungsregeln.
- Teststrategie besitzt reproduzierbare Pflichtfaelle, Referenzfixtures, Mockserver-Szenarien und messbare Budgets.
- Settings-Tabs sind fachlich und visuell ausreichend beschrieben.
- Settings-Persistenz ist mit eindeutigen DataStore-Schluesseln, Defaults und nicht persistenten Dialogzustaenden beschrieben.
- Screen Specs und Wireframes sind fuer betroffene UI-Bereiche vorhanden.
- Komponentenregeln sind fuer wiederverwendbare UI-Bausteine vorhanden.
- Architekturdiagramme sind als Onboarding-Hilfen vorhanden und ersetzen keine PRD-/ADR-Regeln.
- Codex kennt die kanonischen Dokumentrollen und Konfliktregeln aus `DOCS-GOVERNANCE.md`.
- Codex weiss, dass es keinen festen Phasenplan aus dem Docs-Repo gibt.
- Codex weiss, dass es eigene Annahmen explizit nennen muss.
- Codex weiss, dass es vor groesseren Aenderungen im App-Repo einen eigenen Plan dokumentieren muss.
- Finale Cross-QA und Fresh-Developer-Dry-Run sind in `archive/review/2026-06-24/10-final-cross-qa-and-dry-run.md` dokumentiert.

## Verbindliche Einstiegspunkte fuer Codex

Codex startet mit:

1. `codex/README.md`
2. `DOCS-GOVERNANCE.md` fuer Dokumentrollen und Konfliktregeln
3. den fuer die Aufgabe relevanten PRD-Dateien
4. `prd/PRD-v1/13-test-strategy.md`, wenn Tests, DoD, Performance, Migration, Backup/Restore, Parser, UI-QA oder Releasefaehigkeit betroffen sind
5. den betroffenen ADRs
6. den betroffenen Design- und Interaction-Dateien
7. den betroffenen Component-Dateien
8. passenden Diagrammen aus `architecture/diagrams/`, wenn Systemkontext oder Kernfluesse betroffen sind
9. den passenden QA-Dateien in `archive/review/2026-06-24/`
10. `archive/review/2026-06-24/10-final-cross-qa-and-dry-run.md` fuer Traceability und Abschlussstatus

Diese Liste ist eine Quellenorientierung, keine Umsetzungsreihenfolge.

## Muss Codex vor Umsetzung klaeren

Vor groesseren Aenderungen muss Codex pruefen:

- Welche PRD-Dateien sind betroffen?
- Welche ADRs sind betroffen?
- Welche Screen Specs, Wireframes und Components sind betroffen?
- Gibt es eine bereichsspezifische QA-Datei?
- Gibt es Konflikte zwischen Quellen?
- Falls ja: Welche Konfliktregel loest den Konflikt?
- Gibt es Annahmen, die nicht eindeutig dokumentiert sind?
- Sind Sicherheitsregeln betroffen?
- Sind geheime Werte, HTTP/TLS, PIN, Backup-Krypto oder Diagnoseexport betroffen?
- Sind Datenmigrationen oder Schemaaenderungen betroffen?
- Sind Abschluss-, Fortsetzen- oder Auto-Next-Regeln betroffen?
- Sind Deep Links, Android-TV-Systemsuche oder Watch Next betroffen?
- Sind UI-Fokus, D-Pad, Loading, Empty und Error States betroffen?
- Welche Pflichttests aus `prd/PRD-v1/13-test-strategy.md` sind betroffen?
- Werden Performancebudgets, Referenzfixtures oder Mockserver-Faelle beruehrt?

## Nicht erlaubt

Codex darf nicht:

- PRD-Regeln durch eigene UI-Ideen ueberschreiben.
- ADRs ignorieren.
- Datenmodellregeln ohne Begruendung aendern.
- geheime Zugangswerte im Klartext speichern.
- geschuetzte Speicherung auf `EncryptedSharedPreferences` oder Jetpack `security-crypto` als neue Basis aufbauen.
- PIN-Fehlversuche ohne die dokumentierte Sperrlogik behandeln.
- TLS-Zertifikatsfehler bypassed behandeln.
- HTTP fuer App-eigene Dienste oder ohne zentrale Netzwerkpolicy verwenden.
- Diagnoseereignisse ohne zentrale Bereinigung dauerhaft schreiben.
- Touch-only-Bedienung einfuehren.
- vorhandene Provider automatisch zusammenfuehren.
- Backup/Restore-Verhalten anders implementieren als im Backup-Datenvertrag beschrieben.
- eine v1-Funktion aus spaeteren optionalen Ideen ableiten, wenn sie nicht verbindlich dokumentiert ist.
- die feste 95-Prozent-Abschluss-Schwelle als Einstellung oder als Auto-Next-Ausloeser umsetzen.
- lokale Room-IDs, Stream-URLs oder Geheimnisse in Deep Links oder Android-TV-Systemeintraege schreiben.
- geschuetzte Inhalte in Android-TV-Systemsuche oder Watch Next veroeffentlichen.
- kritische Muss-Anforderungen ohne reproduzierbaren Testfall als releasefertig behandeln.
- Performance- oder Speicherverhalten ohne Messnachweis als erledigt markieren.

## Erwarteter Codex-Plan im App-Repository

Codex soll im App-Repository vor groesseren Aenderungen einen eigenen technischen Plan dokumentieren.

Dieser Plan soll enthalten:

- gelesene Quellen
- betroffene Module oder Schichten
- erkannte Risiken
- offene Annahmen
- geplante Tests oder Validierungen
- relevante Pflichttests aus `prd/PRD-v1/13-test-strategy.md`
- relevante QA-Dateien

Der Plan darf eine von Codex gewaehlte Umsetzungsgliederung enthalten.

Diese Gliederung entsteht im App-Repository und wird nicht aus dem Docs-Repository vorgegeben.

## Fachbereichsweises Readiness-Ergebnis

Aktueller Stand nach QA:

- Home: bereit fuer Codex-Planung
- Live-TV: bereit fuer Codex-Planung
- VOD: bereit fuer Codex-Planung
- EPG: bereit fuer Codex-Planung
- Quellen und Parser: bereit fuer Codex-Planung
- Settings: bereit fuer Codex-Planung
- Settings-Persistenz: bereit fuer Codex-Planung
- Datenmodell: bereit fuer Codex-Planung
- VOD-Fortsetzen und Abschlussstatus: bereit fuer Codex-Planung
- Suche/FTS: bereit fuer Codex-Planung
- Player/Progress: bereit fuer Codex-Planung
- Backup/Restore: bereit fuer Codex-Planung
- Schutzkonzept: bereit fuer Codex-Planung
- Android-TV-Systemintegration: bereit fuer Codex-Planung
- Teststrategie und messbare DoD: bereit fuer Codex-Planung
- Finale Cross-QA und Dry-Run: abgeschlossen
- Deep-Research-Follow-up: abgeschlossen
- Architekturdiagramme: Onboarding-Hilfen vorhanden
- ADR-Abgleich: bereit fuer Codex-Planung
- Codex-Einstieg: ausreichend definiert

## Noch sinnvoll vor echter Implementierung

Vor dem ersten grossen App-Repo-PR sollte Codex im App-Repository pruefen:

- vorhandene Modulstruktur
- vorhandene Gradle-Konfiguration
- vorhandene Compose-for-TV-Abhaengigkeiten
- vorhandene Room/DataStore/Keystore-Abhaengigkeiten
- vorhandene Media3-Abhaengigkeiten
- vorhandene Teststruktur
- vorhandene Mockserver-, Benchmark-, Room-Migration- und Android-TV-Instrumentation-Struktur

Diese Punkte koennen erst im App-Repository `Spegeli/vivicast` vollstaendig bewertet werden.
