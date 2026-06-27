# 06 - Cross-Document QA

Status: Review-Referenz v18

## Zweck

Diese Datei dokumentiert die Cross-Document-QA nach der Settings-Verfeinerung und den nachfolgenden Remediation-Paketen.

Geprueft wurden Widersprueche, fehlende Referenzen, Codex-Einstiegspunkte und offensichtliche Dopplungen zwischen PRD, Screen Specs, Wireframes, Components, Review-Dateien, README und Codex-Referenzen.

## Gepruefte Bereiche

- `README.md`
- `codex/README.md`
- `codex/coding-rules.md`
- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `prd/PRD-v1/10-backup-import-requirements.md`
- `prd/PRD-v1/11-about-app-requirements.md`
- `prd/PRD-v1/12-parser-source-contracts.md`
- `prd/PRD-v1/13-test-strategy.md`
- `architecture/decisions/ADR-014-security-data-network-backup.md`
- `architecture/diagrams/README.md`
- `design/design-system/README.md`
- `design/components/README.md`
- `design/mockups/README.md`
- `design/screens/07-settings.md`
- `design/wireframes/README.md`
- `design/wireframes/08-about-app.md`
- `design/components/settings.md`
- `design/components/about-app.md`
- `archive/review/2026-06-24/04-visual-acceptance-checklist.md`
- `archive/review/2026-06-24/05-about-app-qa.md`

## Ergebnis

Die Settings-Tabs sind fachlich durchgaengig dokumentiert:

- Allgemein
- Wiedergabelisten
- EPG
- Optik
- Wiedergabe
- Kindersicherung
- Backup
- Über die App

## Gefundene und behobene Punkte

### 1. Wireframe-Index unvollstaendig

`design/wireframes/README.md` kannte `08-about-app.md` noch nicht.

Behoben:

- Wireframe-Index aktualisiert.
- `08-about-app.md` aufgenommen.
- Fokusnotation robuster formuliert.

### 2. Codex-Referenzen unvollstaendig

`README.md` und `codex/README.md` kannten die neuen Detailreferenzen noch nicht.

Behoben:

- `prd/PRD-v1/10-backup-import-requirements.md` ergaenzt.
- `prd/PRD-v1/11-about-app-requirements.md` ergaenzt.
- `design/wireframes/08-about-app.md` ergaenzt.
- `design/components/about-app.md` ergaenzt.
- `archive/review/2026-06-24/05-about-app-qa.md` ergaenzt.

### 3. Settings-Screen-Quellenliste unvollstaendig

`design/screens/07-settings.md` nutzte About-App-Komponenten, hatte `design/components/about-app.md` aber noch nicht als Quelle.

Behoben:

- Quelle in `design/screens/07-settings.md` ergaenzt.

### 4. Sicherheitsregel zu pauschal

`codex/coding-rules.md` war bei URL-Speicherung zu pauschal formuliert.

Problem:

- Normale nicht-geheime Quellenadressen muessen fuer App-Funktion und Backup-Konfiguration speicherbar sein.
- Geheime Zugangswerte und private Quellenadressen mit eingebetteten Zugangswerten muessen geschuetzt bleiben.

Behoben:

- Coding Rule differenziert jetzt zwischen normalen Konfigurationsadressen und geheimen Werten.
- Backup-PRD stellt klar, dass Standard-Backups keine privaten Quellenadressen mit eingebetteten Zugangswerten enthalten.

### 5. Spaetere Owner-Entscheidungen O-03a bis O-03c3 zum Diagnose-Export

Der urspruengliche Stand dieser QA ging davon aus, dass Log-Export nicht Bestandteil von v1 ist.

Dieser Stand wurde durch O-03a bis O-03c3 ersetzt:

- Bereinigter Log-Export ist Bestandteil von v1.
- UI-Ort ist `Einstellungen > Über die App > Diagnose und Support`.
- Der Inhalt von Log- und Diagnoseprotokolldateien wird niemals in der App angezeigt oder kopiert.
- Allgemeine nicht-private Support-Informationen duerfen weiterhin angezeigt und kopiert werden.
- Exportformat ist ein selbst durch Vivicast erzeugtes ZIP-Archiv mit MIME-Type `application/zip`.
- Der Dateiname folgt `vivicast-diagnostics-YYYYMMDD-HHmmss.zip`.
- Das Archiv enthaelt verpflichtend `vivicast-diagnostics.log` und `diagnostics-metadata.json` in UTF-8.
- Die Logdatei enthaelt nur freigegebene technische Ereignisse; die JSON-Datei enthaelt technische Exportmetadaten.
- Zugangswerte, URLs, Rohdaten, Provider-/Inhaltsnamen, Suchverlauf, Datenbank-Dumps, Screenshots und ungefiltertes System-Logcat sind ausgeschlossen.
- Technische Zuordnungen verwenden nur neutrale interne IDs.
- Das Archiv wird direkt in den Ziel-OutputStream geschrieben und nicht vollstaendig im RAM aufgebaut.
- Diagnoseprotokollierung ist standardmaessig aus; die Aufbewahrungsdauer liegt bei 1 bis 7 Tagen mit Standard 1 Tag.
- Pro App-Prozessstart entsteht eine logische private Sitzung; nicht saubere Enden werden systemgestuetzt oder anhand des letzten dauerhaften Ereignisses abgeschlossen.
- Interne Grenzen sind 20 MiB insgesamt, 2 MiB je Segment und drei Segmente beziehungsweise 6 MiB Logdaten je Sitzung.
- Rotation und globale Bereinigung schuetzen das aktuelle Schreibsegment und die Metadaten der aktiven Sitzung.
- Der Export weist Kuerzungen aus und bildet den Zeitraum nur aus tatsaechlich noch vorhandenen Ereignissen.
- O-03 ist damit vollstaendig entschieden und dokumentiert.

Aktualisiert wurden insbesondere PRD, Settings-Screen, About-App-Wireframe, About-App-Komponenten und About-App-QA.

### 6. Owner-Entscheidung O-04 zum EPG-Standardintervall

Der globale Standardwert ist jetzt durchgaengig auf 24 Stunden festgelegt.

Geprueft und angeglichen wurden:

- Settings-PRD und EPG-Fachvertrag
- DataStore mit `epgRefreshIntervalHours = 24`
- Hintergrundjob-Vertrag
- Settings-Screens, Wireframe, Mockup-Spec und Komponenten
- Akzeptanzkriterien und Coding Rules

App-Start, Playlist-Aenderung und manuelle Aktualisierung bleiben als separate Ausloeser dokumentiert.

### 7. Owner-Entscheidung O-05 zum Startbereich

Der zuvor nur in Produktuebersicht, Navigation und DoD genannte Startbereich ist jetzt als vollstaendige Allgemein-Einstellung dokumentiert.

Geprueft und angeglichen wurden:

- zulaessige sichtbare Werte Home, Live-TV, Filme und Serien
- Standardwert Home
- DataStore mit `startDestination = HOME` und vier erlaubten Enum-Werten
- Wirkung ab dem naechsten regulaeren App-Start ohne sofortige Umleitung
- Vorrang expliziter Deep Links und Android-TV-Ziele
- Verhalten bei App-Autostart, Rueckkehr aus dem Hintergrund und leeren Zielbereichen
- Settings-Screen, Wireframe, Mockup-Spec, Komponenten, Akzeptanzkriterien und Coding Rules

Suche und Einstellungen bleiben bewusst von der Startbereich-Auswahl ausgeschlossen.

### 8. Owner-Entscheidung O-06 zum Live-TV-Preview-Ablauf

Der zuvor widerspruechlich als fest oder einstellbar beschriebene Live-TV-Startablauf ist jetzt eindeutig:

- Beim blossen Senderfokus wird kein Stream gestartet.
- Erstes OK in der Senderspalte aktiviert den Sender-Modus, oeffnet die EPG-Spalte und startet gleichzeitig die Live-Vorschau.
- Der Fokus springt danach auf die aktuelle Sendung in der EPG-Spalte, sofern vorhanden.
- Zweites OK auf der fokussierten aktuellen Sendung oeffnet Vollbild.
- Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK.
- Suchdokumente definieren keinen eigenen Preview-Ablauf, sondern verweisen auf die Live-TV-Spezifikation.

Geprueft und angeglichen wurden Live-TV-PRD, DoD, Screen, Komponenten, Wireframe, Interaction Spec, Design-System, High-Fidelity-Entscheidungen, Owner-Feedback, Mockup-Spec, Suchreferenzen, Coding Rules und visuelle QA.

### 9. Owner-Entscheidung O-07 zu Auto-Next

Auto-Next ist jetzt als vollstaendige Wiedergabe-Einstellung und als zweistufiger Player-Ablauf dokumentiert:

- Toggle `Automatisch naechste Folge`, Standard `Aus`
- Countdown 5, 10, 15 oder 30 Sekunden, Standard 10 Sekunden
- deaktivierter Countdown bleibt sichtbar, aber nicht bedienbar
- bei `Aus` erscheint `Naechste Folge abspielen` erst nach dem tatsaechlichen Episodenende und startet nur nach OK
- bei `Ein` erscheint `Naechste Folge in X` X Sekunden vor dem Episodenende; OK startet sofort, Ablauf startet automatisch am Episodenende
- sichtbarer Button `Zurueck` erscheint in beiden Zustaenden zeitgleich neben dem Hauptbutton; kein Button `Abbrechen`
- OK auf `Zurueck` oder die Zurueck-Taste verwirft einen laufenden Countdown und stellt den vorherigen Staffel-/Episodenkontext auf der Serien-Detailseite wieder her
- Staffelwechsel und Serienende sind eindeutig behandelt
- DataStore verwendet `autoNextEpisodeEnabled = false` und `autoNextEpisodeCountdownSeconds = 10`
- externer Player ist durch O-11 separat geklaert

Geprueft und angeglichen wurden Serien- und Player-PRD, DataStore, Settings- und Player-Screens, Komponenten, Wireframes, Design-System, Mockup-Spezifikationen, Coding Rules, Akzeptanzkriterien und Datenmodell-QA.

### 10. Owner-Entscheidung O-08 zur Abschluss-Schwelle

Der zuvor optionale beziehungsweise offene Abschlusswert ist jetzt eindeutig und ohne Settings-Drift festgelegt:

- fester v1-Wert von 95 Prozent fuer Filme und einzelne Episoden
- kein DataStore-Schluessel und keine sichtbare Einstellung
- tatsaechliches Medienende setzt immer `isCompleted = true`
- abgeschlossene Inhalte sind keine direkten Resume-Ziele
- Serien-Fortsetzen und Watch Next duerfen nach einer abgeschlossenen Episode auf die naechste Episode bei Position 0 weitergehen
- 95 Prozent beendet die Wiedergabe nicht und loest kein Auto-Next aus
- Filme und fokussierte Episode Rows bieten `Als gesehen markieren` beziehungsweise `Als ungesehen markieren`
- `Als ungesehen markieren` loescht den vollstaendigen Playback-Progress-Datensatz
- komplette Staffeln und Serien besitzen keine eigene manuelle Abschlussmarkierung

Geprueft und angeglichen wurden VOD-, Player- und Android-TV-PRD, Datenmodell, ADR-008, Home-/Film-/Serien-/Player-Screens, Komponenten, Wireframes, Settings-Abgrenzung, Design-System, Mockup-Spezifikationen, Coding Rules, Akzeptanzkriterien und QA.

### 11. Owner-Entscheidung O-09 zu Cache und Verlauf

Die zuvor widerspruechlich beschriebenen Cache- und Verlaufsoptionen sind jetzt eindeutig:

- Backup zeigt die aktuelle Groesse des Medien-Caches.
- `Cache leeren` ist eine sichtbare Wartungsaktion.
- Die Groesse des Medien-Caches und die Cache-Rotation sind in v1 nicht frei konfigurierbar.
- Medien-Cache-Dateien fuer Logos, Poster, Staffelbilder und Episodenbilder sind nicht Teil des Standard-Backups.
- Verlauf kann fuer Live-TV, Filme, Serien, Suche oder komplett geloescht werden.
- Filmverlauf-Loeschung umfasst Film-Wiedergabefortschritt.
- Serienverlauf-Loeschung umfasst Episoden-Wiedergabefortschritt.
- Freie Verlaufslimits werden in v1 nicht angeboten.
- Der Suchverlauf bleibt fest auf maximal 20 Eintraege begrenzt.

Geprueft und angeglichen wurden Settings-PRD, Verlauf-/Backup-Kapitel, Datenmodell, Hintergrundjobs/Cache, Settings-Screen, Wireframe, Komponenten, Coding Rules, Akzeptanzkriterien und QA.

### 12. Owner-Entscheidung O-10 zu Kindersicherung nach Restore

Das Verhalten von PIN und Schutzflags nach Backup-Restore ist jetzt eindeutig:

- Restore nutzt fuer eine erforderliche PIN-Abfrage immer die aktuell lokale PIN, wenn lokal Einstellungsschutz aktiv ist oder Backup/Restore per PIN geschuetzt ist.
- Eine in der Backup-Datei enthaltene fruehere PIN oder Schutzkonfiguration darf keine Restore-Autorisierung erteilen.
- PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags aus Backups werden nicht wiederhergestellt.
- Nach Restore ist Kindersicherung deaktiviert.
- Wenn Kindersicherung beim Export aktiv war, zeigt die App einen Hinweis zur manuellen Reaktivierung unter `Einstellungen > Kindersicherung`.

Geprueft und angeglichen wurden Backup-PRD, Settings-PRD, Datenmodell, ADR-004, Settings-Screen, Wireframe, Komponenten, Coding Rules, Akzeptanzkriterien und QA.

### 13. Owner-Entscheidung O-11 zu externem Player und Fortschritt

Externe Player sind jetzt klar von Vivicast-Fortschritt und Auto-Next abgegrenzt:

- Der interne Vivicast-Player ist die einzige verlaessliche Quelle fuer automatische VOD-Position, Dauer, Fortschritt, Medienende, Abschlussstatus und Auto-Next.
- Externe Player erzeugen oder aktualisieren keinen `PlaybackProgressEntity`-Datensatz.
- Rueckgabewerte externer Player werden nicht als Fortschritt uebernommen.
- Vorhandener Fortschritt bleibt nach externer Wiedergabe unveraendert.
- Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.
- Auto-Next gilt nicht fuer externe Player.

Geprueft und angeglichen wurden VOD-/Player-PRD, Settings-PRD, Datenmodell, Player-Screen, Settings-Screen, Komponenten, Wireframes, Design-System, Mockup-Spezifikation, Coding Rules, Akzeptanzkriterien und QA.

### 14. Paket H zu lokaler Suche und FTS

Die interne Vivicast-Suche ist jetzt technisch und fachlich eindeutig:

- Room FTS4 ist die v1-Strategie fuer lokale Volltextsuche.
- Ergebnisgruppen sind exakt `Kanäle`, `Filme`, `Serien` und `EPG`.
- Episoden sind keine eigene Suchgruppe und kein eigenes Suchergebnis.
- Query und Indextexte werden gleich normalisiert.
- Prefix-Suche ist tokenbasiert; reine Infix-Suche ist kein v1-Ziel.
- EPG-Suche startet erst ab drei normalisierten Zeichen.
- Jede Gruppe liefert maximal 20 Treffer.
- FTS-Indexe werden aus produktiven Daten gepflegt und sind kein Backup-Bestandteil.

Geprueft und angeglichen wurden Such-PRD, Datenmodell, Hintergrundjobs/Performance, ADR-005, Search-Screen, Design-System, Coding Rules, Akzeptanzkriterien und QA.

### 15. Paket I zu Player, Catch-Up und Progress

Der vollstaendige Player-/Progress-Vertrag ist jetzt eindeutig:

- Interne Wiedergabe umfasst Live-TV, Filme, Serienepisoden und Catch-Up.
- Externe Player gelten nur fuer Filme und einzelne Episoden; Live-TV und Catch-Up bleiben intern.
- PlaybackRequest, Rueckkehrziel, stabile Medienreferenzen, EPG-/Timeshift-Kontext und just-in-time Streamaufloesung sind definiert.
- M3U- und Xtream-Catch-Up werden aus Quellenmetadaten und EPG-Kontext erzeugt, ohne finale URLs zu speichern oder zu loggen.
- Automatischer VOD-Fortschritt entsteht nur fuer interne Filme und Episoden ab Mindestposition und wird regelmaessig sowie bei relevanten Playerereignissen gespeichert.
- Catch-Up erzeugt keinen VOD-Fortschritt und kein Resume-Ziel.
- Audio-/Untertitel-Defaults und sitzungsbezogene Track-Auswahl sind getrennt.
- Timeshift-Grenzfaelle sind auf Live-TV begrenzt und brechen Live-Wiedergabe bei Timeshift-Fehlern nicht ab.

Geprueft und angeglichen wurden Player-PRD, Live-TV-PRD, VOD-PRD, Parser-Vertrag, Datenmodell, ADR-006, ADR-013, Player-Screen, Settings-Screen, Player-Komponenten, Design-System, Coding Rules, Akzeptanzkriterien und QA.

### 16. Paket J zu Schutzkonzept, Netzwerk und Backup

Das Sicherheitskonzept ist jetzt fachbereichsuebergreifend eindeutig:

- Daten sind als `Normal`, `Sensibel`, `Geheim` oder `Sicherheitswirksam lokal` klassifiziert.
- Room, DataStore, Android Keystore und geschuetzter Secret Store sind getrennt beschrieben.
- Secret-Store-Verlust markiert betroffene Quellen als `Zugangsdaten erforderlich`; PIN und Kindersicherung werden deaktiviert.
- Standard-Backups enthalten keine Geheimnisse.
- Verschluesselte Vollbackups nutzen ein versioniertes AES-GCM-Schutzformat mit KDF-Metadaten und Passphrase-Pruefung vor lokalen Datenaenderungen.
- PIN-Fehlversuche sind mit fuenf Versuchen, 30 Sekunden, 60 Sekunden und danach 5 Minuten Sperre definiert.
- Session-Freigaben sind nur im Speicher und nicht backupfaehig.
- HTTP bleibt fuer IPTV-/EPG-/Stream-Quellen erlaubt, wird aber als unsicher markiert und durch zentrale Netzwerkpolicy gefuehrt.
- TLS-Zertifikatsfehler werden nicht bypassed; provider-spezifische Header-, Cookie- und User-Agent-Optionen sind nicht Teil von v1.
- Diagnoseereignisse werden vor dauerhaftem Schreiben und vor Export zentral bereinigt.

Geprueft und angeglichen wurden Security-PRD, Backup-PRD, About-App-PRD, Datenmodell, ADR-004, ADR-014, Settings-Screens, Quellen-Screen, Wireframe, Komponenten, Coding Rules, Akzeptanzkriterien und QA.

### 17. Paket K zu Android-TV-Systemintegration

Android-TV-Systemintegration ist jetzt eindeutig abgegrenzt:

- Deep Links verwenden stabile fachliche Ziel-IDs und keine lokalen Room-IDs.
- Deep Links enthalten keine Stream-URLs, Tokens, Zugangswerte, HTTP-Header oder Cookies.
- Android-TV-Systemsuche enthaelt Live-TV, Filme und Serien, aber keine EPG-Treffer, keine Episoden als eigene Treffer und kein Catch-Up.
- Geschuetzte Inhalte erscheinen nicht in Android-TV-Systemsuche oder Watch Next, solange der jeweilige Schutz aktiv ist.
- Watch Next gilt nur fuer Filme und Serienepisoden.
- Externe Player erzeugen keine Watch-Next-Updates.
- Pending Restore-Referenzen werden erst nach erfolgreichem Provider-Refresh in Systemsuche oder Watch Next veroeffentlicht.
- Provider-Deaktivierung, Provider-Loeschung, Restore, Migration, Schutzregel-Aenderung und Fortschritts-/Abschlussaenderung bereinigen Systemeintraege.
- Fehlende, geloeschte, pending oder deaktivierte Ziele zeigen kontrollierte Fehler- oder Nicht-verfuegbar-Zustaende statt stillem Home-Fallback.

Geprueft und angeglichen wurden ADR-008, Android-TV-PRD, Settings-/Search-PRD, IPTV-PRD, Datenmodell, DoD, Navigation, Coding Rules, visuelle QA, README, Changelog und Remediation-Plan.

### 18. Paket L zu Teststrategie und messbarer DoD

Die Teststrategie ist jetzt als eigene normative PRD-Quelle dokumentiert:

- jede kritische Muss-Anforderung braucht mindestens einen reproduzierbaren Testfall
- Parser-Golden-Tests fuer M3U, Xtream Codes und XMLTV sind Pflicht
- lokale Mockserver pruefen Quellen-, Fehler-, Timeout-, Redirect- und Redaction-Pfade
- Room-Migrationen, Stable Keys, Pending References und FTS-Rebuilds sind testpflichtig
- Provider- und EPG-Refreshes brauchen Tests fuer Fehler vor Commit, Rollback, Abbruch, Prozessabbruch und Teilimport
- Backup und Restore brauchen Roundtrips fuer Standard-Backup, Vollbackup, Legacy-Schema-Migration und Restore-Ersetzen
- Player, Catch-Up, Timeshift und Progress besitzen Pflichtszenarien fuer interne und externe Wiedergabe
- D-Pad-, Fokus- und Back-Pfade werden je betroffenem Screen geprueft
- grosse Schrift sowie 720p, 1080p und 4K sind fuer UI-Aenderungen pruefpflichtig
- Schutz-, Redaction-, Android-TV-Systemsuche- und Watch-Next-Bereinigungstests sind verbindlich
- Import, Suche, Datenbank, EPG und Speicherverbrauch besitzen messbare v1-Budgets

Geprueft und angeglichen wurden DoD, Performance-PRD, Backup-PRD, Parser-Vertrag, Coding Rules, Codex-Einstieg, visuelle QA, Readiness-QA, README, Governance, Changelog und Remediation-Plan.

### 19. Paket M zu Referenzen und Housekeeping

Die offenen Housekeeping- und V-Marker-Restpunkte sind geschlossen:

- Design-System-README verweist auf aktuelle PRD-Dateien `00` bis `13` und auf `DOCS-GOVERNANCE.md` als kanonische Konfliktregel.
- Components-README kennt `about-app.md`.
- Mockups-README kennt `design/mockups/high-fidelity/02-ui-direction-decisions.md`.
- PRD-Kapitelkollision ist bereinigt: Kapitel 8 umfasst Android-TV-Integration und Sicherheit, Kapitel 9 umfasst Akzeptanzkriterien und DoD.
- Diagnoseexport verweist auf `prd/PRD-v1/11-about-app-requirements.md` statt auf eine unklare Kapitelnummer.
- `architecture/diagrams/README.md` kennzeichnet Architekturdiagramme als geplant.
- Redundante `.gitkeep`-Dateien wurden aus befuellten Verzeichnissen entfernt.
- `codex/` ist als reiner Referenzordner des Docs-Repositories abgegrenzt, nicht als App-Code-Verzeichnis.

Geprueft und angeglichen wurden Design-System-README, Components-README, Mockups-README, PRD-Kapitelueberschriften, About-App-Referenz, Architecture-Diagrams-README, README, Codex-README, Changelog und Remediation-Plan.

## Abschlussstand

Keine blockierenden Widersprueche fuer die bereits entschiedenen Teile der Settings-Dokumentation gefunden.

Die frueher offenen Folgepruefungen wurden spaeter abgeschlossen:

- globale Cross-QA aller PRD-Kapitel gegen ADRs: siehe `archive/review/2026-06-24/08-adr-alignment-qa.md` und `archive/review/2026-06-24/10-final-cross-qa-and-dry-run.md`
- Datenmodell-QA gegen Backup, Favoriten, Verlauf und Wiedergabefortschritt: siehe `archive/review/2026-06-24/07-data-model-qa.md`
- Implementierungsbereitschaft-QA fuer das App-Repository `Spegeli/vivicast`: siehe `archive/review/2026-06-24/09-codex-implementation-readiness-qa.md` und `archive/review/2026-06-24/10-final-cross-qa-and-dry-run.md`

## Empfehlung fuer Codex

Vor Settings-Implementierung muss Codex mindestens lesen:

1. `codex/README.md`
2. `prd/PRD-v1/04-search-settings-player-requirements.md`
3. `prd/PRD-v1/10-backup-import-requirements.md`
4. `prd/PRD-v1/11-about-app-requirements.md`
5. `design/screens/07-settings.md`
6. `design/screens/08-playlist-epg.md`
7. `design/wireframes/05-settings.md`
8. `design/wireframes/08-about-app.md`
9. `design/components/settings.md`
10. `design/components/about-app.md`
11. `archive/review/2026-06-24/04-visual-acceptance-checklist.md`
12. `archive/review/2026-06-24/05-about-app-qa.md`
13. `architecture/decisions/ADR-014-security-data-network-backup.md`
