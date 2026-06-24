# 08 - ADR Alignment QA

Status: Review-Referenz v12

## Zweck

Diese Datei dokumentiert den ADR-Abgleich nach Settings-, Backup-, Cross-Document- und Datenmodell-QA.

Geprueft wurden die ADRs gegen PRD, Datenmodell, Codex-Regeln und aktuelle Settings-Entscheidungen.

## Gepruefte ADRs

- `architecture/decisions/ADR-001-provider-isolation.md`
- `architecture/decisions/ADR-002-epg-strategy.md`
- `architecture/decisions/ADR-003-refresh-strategy.md`
- `architecture/decisions/ADR-004-backup-strategy.md`
- `architecture/decisions/ADR-005-local-search.md`
- `architecture/decisions/ADR-006-timeshift-strategy.md`
- `architecture/decisions/ADR-007-trailer-strategy.md`
- `architecture/decisions/ADR-008-android-tv-integration.md`
- `architecture/decisions/ADR-009-provider-deletion-and-favorites.md`
- `architecture/decisions/ADR-010-stable-identities-and-restore-keys.md`
- `architecture/decisions/ADR-011-parser-source-contracts.md`
- `architecture/decisions/ADR-012-atomic-import-refresh.md`
- `architecture/decisions/ADR-013-player-playback-progress.md`
- `architecture/decisions/ADR-014-security-data-network-backup.md`

## Ergebnis

Keine blockierenden ADR-Widersprueche mehr fuer:

- Provider-Isolation
- EPG-Strategie
- Refresh-Strategie
- Backup-Strategie
- lokale Suche
- Timeshift
- Trailer-Strategie
- Android-TV-Integration
- Provider-Loeschverhalten und Favoriten
- stabile Identitaeten und Restore-Schluessel
- quellbezogene EPG-Pipeline
- Parser- und Quellenvertraege
- atomarer Import und Refresh
- Player, Catch-Up und Progress
- Schutzkonzept fuer Daten, Netzwerk, PIN, Diagnose und Backup
- Android-TV-Systemintegration mit stabilen Deep Links, Systemsuche und Watch Next

## Gefundene und behobene Punkte

### 1. ADR-004 Backup Strategy veraltet

Problem:

- ADR und PRD beschrieben zwischenzeitlich unterschiedliche Restore-Modi.
- ADR beschrieb Backup-Teilrestore als v1-Funktion.
- Neue Backup-Regel definiert Standard-Backup, verschluesseltes Vollbackup und Restore-Ersetzen als einziges v1-Restore-Modell.

Behoben:

- ADR-004 an `prd/PRD-v1/10-backup-import-requirements.md` angepasst.
- Backup-Teilrestore aus v1 entfernt.
- Restore ersetzt in v1 immer den Backup-Umfang.
- Backup-Schema-Migration alter kompatibler Versionen bleibt erlaubt, ist aber kein Zusammenfuehren lokaler und importierter Daten.
- Standard-Backup und verschluesseltes Vollbackup dokumentiert.
- O-10 ergaenzt: Restore nutzt bei aktivem lokalem Einstellungsschutz oder lokaler Backup-/Restore-PIN-Schutzregel die aktuell lokale PIN; PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus Backups werden nicht wiederhergestellt.

### 2. ADR-002 und ADR-003 EPG-Pipeline nach Paket E abgeglichen

Problem:

- ADR-002 beschrieb globale EPG-Quellen, aber nicht die quellbezogene Kanal-/Programm-Pipeline.
- ADR-003 beschrieb EPG-Refresh und Mapping, aber nicht die neue Reihenfolge mit EPG-Kanälen, Programmen und Retention-Cleanup.

Behoben:

- ADR-002 legt `EPGSource -> EPGChannel -> EPGProgram` als quellbezogene Pipeline fest.
- ADR-002 legt sichtbare globale EPG-Aufbewahrung fest: Vergangenheit 1 bis 14 Tage, Default 1; Zukunft 1 bis 14 Tage, Default 7.
- ADR-003 aktualisiert den Refresh-Ablauf um quellbezogene Speicherung, Mapping/Prioritaet und Retention-Cleanup.

### 3. ADR-006 Timeshift Strategy nach O-02 neu abgeglichen

Vorheriger Stand:

- Die erste QA sah fuer v1 nur einen Timeshift-Ein/Aus-Schalter vor.
- Speicherorte und Zeitlimits waren keine verpflichtenden UI-Einstellungen.

Entscheidung O-02:

- Timeshift: Ein/Aus, Standard Ein.
- Maximale Dauer: 15, 30, 60 oder 120 Minuten, Standard 30 Minuten.
- Speicher: Automatisch, RAM oder Festplatte, Standard Automatisch.
- Festplatte nutzt appverwalteten persistenten Geraetespeicher ohne freie Pfadauswahl.

Behoben:

- ADR-006 an O-02 angepasst.
- PRD, Settings-Screen, Wireframe, Komponenten und Akzeptanzkriterien angeglichen.
- DataStore um Aktivstatus, maximale Dauer und Speicherwahl ergaenzt.

### 4. ADR-008 Android TV Integration unscharf zu Suche und Serienfortschritt

Problem:

- Globale Android-TV-Suche und interne Vivicast-Suche waren nicht klar getrennt.
- Serien-Continue-Watching war nicht explizit an Episodenfortschritt gekoppelt.
- Deep-Link-Ziel-IDs, Watch-Next-Pflege, Providerstatus und Kindersicherung fuer Android-Systemoberflaechen waren noch nicht verbindlich.

Behoben:

- ADR-008 stellt klar: globale Android-TV-Suche ohne EPG, interne Vivicast-Suche darf EPG enthalten.
- Serien-Fortsetzen basiert auf Episoden-Wiedergabefortschritt.
- Abgeschlossene Episoden sind keine direkten Resume-Ziele; Watch Next und Continue Watching koennen auf die naechste Episode weitergehen.
- Die fachliche 95-Prozent-Schwelle und das tatsaechliche Medienende sind mit dem VOD-Datenmodell abgeglichen.
- Deep Links verwenden stabile fachliche Ziel-IDs statt lokaler Room-IDs.
- Android-TV-Systemsuche enthaelt keine EPG-Treffer, keine Episoden als eigene Treffer, kein Catch-Up und keine geschuetzten Inhalte.
- Watch Next gilt nur fuer Filme und Serienepisoden und wird nicht durch externe Player aktualisiert.
- Provider-Deaktivierung, Provider-Loeschung, Restore, Migration, Schutzregel-Aenderung und Fortschrittsaenderungen bereinigen Systemeintraege.

### 5. ADR-013 Player Playback and Progress ergaenzt

Problem:

- Der vollstaendige Player-/Progress-Vertrag war bisher ueber PRD, Datenmodell, Settings, O-11 und Designquellen verteilt.
- Catch-Up-URL-Erzeugung, PlaybackRequest, Progress-Speicherrhythmus, Track-Auswahl und Timeshift-Grenzfaelle waren nicht zentral architekturell festgelegt.

Behoben:

- ADR-013 legt interne und externe Wiedergabemedien fest.
- ADR-013 definiert PlaybackRequest, just-in-time Streamaufloesung und Nicht-Ziele fuer v1.
- Catch-Up fuer M3U und Xtream wird aus EPG-Kontext und Quellenmetadaten abgeleitet, ohne finale URLs zu persistieren.
- Automatischer VOD-Fortschritt entsteht nur fuer interne Filme und Episoden mit Mindestposition und Speicherrhythmus.
- Audio-/Untertitel-Defaults, sitzungsbezogene Track-Auswahl und Timeshift-Grenzfaelle sind abgegrenzt.

### 6. ADR-014 Security Data Network Backup ergaenzt

Problem:

- Schutzklassen, lokaler Secret Store, Vollbackup-Krypto, PIN-Fehlversuche, HTTP/TLS-Policy und zentrale Diagnosebereinigung waren ueber mehrere Dokumente verteilt oder offen.

Behoben:

- ADR-014 definiert `Normal`, `Sensibel`, `Geheim` und `Sicherheitswirksam lokal`.
- ADR-014 trennt Room, DataStore, Android Keystore und geschuetzten Secret Store.
- Verlust geschuetzter lokaler Daten fuehrt zu `Zugangsdaten erforderlich` und deaktivierter Kindersicherung.
- Verschluesselte Vollbackups nutzen ein versioniertes AES-GCM-Schutzformat mit KDF-Metadaten.
- PIN-Sperrlogik nutzt fuenf Fehlversuche, 30 Sekunden, 60 Sekunden und danach 5 Minuten.
- HTTP ist fuer Nutzerquellen erlaubt, aber unsicher markiert und policygebunden; TLS-Fehler werden nicht bypassed.
- Diagnosebereinigung greift vor dauerhaftem Schreiben und vor Export.

## Aktuell gueltige ADRs

### ADR-001 Provider Isolation

Passt zu PRD und Datenmodell.

Provider bleiben getrennt, Favoriten und Verlauf bleiben intern providergebunden.

### ADR-002 EPG Strategy

Passt zu PRD und Datenmodell.

EPG-Quellen sind globale Objekte, werden pro Refresh-Zyklus maximal einmal geladen und speichern EPG-Kanäle sowie Programme quellbezogen.

### ADR-003 Refresh Strategy

Passt zu PRD und Kapitel 7.

Refresh-Reihenfolge ist weiterhin verbindlich und enthaelt quellbezogene EPG-Speicherung, Mapping/Prioritaet und Retention-Cleanup.

Commit- und Rollback-Grenzen liegen in ADR-012.

### ADR-005 Local Search

Passt zu PRD und Datenmodell.

Suche erfolgt lokal ueber Room FTS4, Debounce 300 ms, vier Ergebnisgruppen, Suchverlauf maximal 20 Eintraege und maximal 20 Treffer pro Gruppe.

EPG-Suche startet erst ab drei normalisierten Zeichen. Episoden sind keine eigene Suchgruppe.

### ADR-007 Trailer Strategy

Keine Konflikte gefunden.

### ADR-009 Provider Deletion and Favorites

Passt zu PRD und Datenmodell.

### ADR-011 Parser and Source Contracts

Passt zu Parser-PRD und Import-/Diagnose-Sicherheitsregeln.

M3U, Xtream Codes und XMLTV verwenden toleranten Teilimport mit bereinigten technischen Fehlerzaehlern.

### ADR-012 Atomic Import and Refresh

Passt zu Kapitel 7, Datenmodell und DoD.

Provider-Refreshes committen atomar pro Provider, EPG-Refreshes atomar pro EPG-Quelle. Fehler vor Commit behalten alte Daten; Teilfehler duerfen valide Eintraege uebernehmen, aber Loeschungen nur aus autoritativen Teilbereichen ableiten.

Provider-Loeschen entfernt providerbezogene Inhalte und Abhaengigkeiten, EPG-Quellen bleiben erhalten.

### ADR-013 Player Playback and Progress

Passt zu Player-PRD, Live-TV-PRD, VOD-PRD, Parser-Vertrag, Datenmodell, Designquellen und DoD.

PlayerRequest, Catch-Up, Streamaufloesung, externer Player, Progress-Speicherrhythmus, Track-Auswahl und Timeshift-Grenzfaelle sind konsistent definiert.

### ADR-008 Android TV Integration

Passt zu Android-TV-PRD, Datenmodell, Navigation, DoD und Coding Rules.

Deep Links, globale Android-TV-Suche, Watch Next, Providerstatus, pending Restore-Referenzen und Kindersicherung sind konsistent definiert.

### ADR-014 Security Data Network Backup

Passt zu Security-PRD, Backup-PRD, About-App-PRD, Datenmodell, Settings-Design, Quellenverwaltung, Coding Rules und DoD.

Schutzklassen, Secret Store, Vollbackup-Krypto, PIN-Sperrlogik, HTTP/TLS-Policy und zentrale Diagnosebereinigung sind konsistent definiert.

## Empfehlung fuer Codex

Vor Architektur- oder Datenmodellumsetzung muss Codex neben PRD und Codex-Regeln auch die betroffenen ADRs lesen.

Relevant fuer Settings und Datenmodell:

1. `ADR-001-provider-isolation.md`
2. `ADR-002-epg-strategy.md`
3. `ADR-003-refresh-strategy.md`
4. `ADR-004-backup-strategy.md`
5. `ADR-005-local-search.md`
6. `ADR-006-timeshift-strategy.md`
7. `ADR-008-android-tv-integration.md`
8. `ADR-009-provider-deletion-and-favorites.md`
9. `ADR-010-stable-identities-and-restore-keys.md`
10. `ADR-011-parser-source-contracts.md`
11. `ADR-012-atomic-import-refresh.md`
12. `ADR-013-player-playback-progress.md`
13. `ADR-014-security-data-network-backup.md`
