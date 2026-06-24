# Vivicast PRD v1
## Kapitel 13 - Teststrategie und messbare Definition of Done

Status: verbindlich v1

## Zweck

Diese Datei definiert die verbindliche v1-Teststrategie fuer Vivicast.

Sie legt fest, welche kritischen Anforderungen automatisiert, instrumentiert oder manuell mit Nachweis geprueft werden muessen.

Sie ergaenzt `prd/PRD-v1/09-implementation-and-dod.md`.

Die fachlichen Anforderungen bleiben in den jeweiligen PRD-Dateien, ADRs und Designquellen. Diese Datei definiert, wie ihre Umsetzung reproduzierbar nachgewiesen wird.

## Grundregel

Jede kritische Muss-Anforderung braucht mindestens einen reproduzierbaren Testfall.

Ein Testfall muss enthalten:

- eindeutigen Bezug auf die Anforderung oder Quelle
- Testdaten oder Fixture
- erwartetes Ergebnis
- Automatisierungsart
- Nachweisort im App-Repository

Automatisierung ist Pflicht, wenn das Verhalten ohne echte Android-TV-Hardware zuverlaessig pruefbar ist.

Manuelle QA ist nur fuer echte TV-Interaktion, Fokus, visuelle Skalierung, Geraeteverhalten und Systemintegrationen erlaubt, die nicht stabil automatisierbar sind. Manuelle QA braucht protokollierte Schritte und Ergebnisnachweis.

## Testarten

Vivicast verwendet folgende Testarten:

| Testart | Zweck |
| --- | --- |
| Unit-Test | reine fachliche Logik, Parser, Normalisierung, Stable Keys, Redaction, Validierung |
| JVM-/Repository-Test | Use Cases, Repository-Regeln, Backup-Schema, Migration ohne echtes Geraet |
| Room-Migrationstest | Datenbankmigrationen, Indizes, Constraints, FTS-Rebuilds |
| Instrumented Android Test | DataStore, Keystore-Grenzen, Compose-TV-Fokus, Systemintegration, echte Android-Laufzeit |
| Mockserver-Integrationstest | M3U, Xtream Codes, XMLTV, Timeouts, Fehlerantworten, Redirects |
| Benchmark | Import, Suche, Datenbank, EPG, Speicherverhalten |
| Manuelle Android-TV-QA | D-Pad, Fokus, Back-Pfade, grosse Schrift, 720p/1080p/4K, Systemoberflaechen |

## Referenzdaten

Das App-Repository muss reproduzierbare Fixtures fuer kleine, grosse und fehlerhafte Datenstaende fuehren.

### Kleine Fixtures

Kleine Fixtures dienen schnellen CI-Tests:

- 10 Provider
- 100 Sender
- 500 Filme
- 100 Serien
- 1.000 Episoden, sofern Seriendetails getestet werden
- 5.000 EPG-Programme
- Favoriten, Verlauf, Suchverlauf und Playback Progress fuer alle Medientypen

### Grosse Fixtures

Grosse Fixtures dienen Performance-, Migrations- und Speicherpruefungen:

- 10.000 Sender
- 50.000 Filme
- 20.000 Serien
- 200.000 Episoden, sofern Serienepisoden im getesteten Datenpfad materialisiert werden
- 3.000.000 EPG-Programme
- 1.000 Favoriten
- 2.000 Live-TV-Verlaufseintraege
- 2.000 Playback-Progress-Eintraege
- 500 pending Restore-Referenzen

### Fehlerfixtures

Fehlerfixtures muessen mindestens enthalten:

- ungueltige Encoding-Varianten
- UTF-8 mit BOM
- `LF`, `CRLF` und gemischte Zeilenenden
- fehlende Pflichtfelder
- Duplikate
- unvollstaendige Xtream-Antworten
- ungueltige oder mehrdeutige XMLTV-Zeiten
- ZIP ohne XMLTV-Datei
- ZIP mit mehreren XMLTV-Dateien
- Timeouts
- HTTP-Fehler
- Redirects
- beschaedigte Backups
- alte kompatible Backup-Schema-Versionen
- alte inkompatible Backup-Schema-Versionen
- alte Merge-, Konflikt- oder Kopie-Felder in Backups

Fixtures duerfen keine echten Zugangsdaten, Tokens, privaten URLs, Provider-Inhaltslisten oder personenbezogenen Daten enthalten.

## Referenzgeraet und Messung

Vor v1-Release muss das App-Repository mindestens ein Referenzgeraet oder Emulatorprofil dokumentieren.

Mindestklasse:

- Android TV oder Google TV
- vom App-Repository unterstuetztes Android-API-Level
- 2 GiB RAM oder weniger, wenn ein bewusst niedrigeres Zielgeraet gewaehlt wird

Alle Performancebudgets werden auf diesem Referenzgeraet oder Profil gemessen.

Benchmarkberichte muessen enthalten:

- App-Version oder Commit
- Geraet oder Emulatorprofil
- Android-Version
- Datenfixture
- Wiederholungszahl
- Median
- p95, sofern sinnvoll
- Speicher-Hoechstwert, sofern relevant
- Abweichung von Budget und Entscheidung

Wenn ein Budget nicht erreicht wird, darf die Abweichung nur mit dokumentierter Risikoentscheidung in den Release gehen.

## Parser-Golden-Tests

Parser-Golden-Tests sind Pflicht fuer M3U, Xtream Codes und XMLTV.

Jeder Golden-Test prueft:

- importierte Anzahl
- uebersprungene Anzahl
- Fehlerkategorien
- Teilfehlerstatus
- Stable-Key-Ableitung
- Kategorien
- EPG-Zuordnung, soweit betroffen
- Catch-Up-Metadaten, soweit betroffen
- Redaction von Diagnose- und Fehlerdaten

### M3U

Pflichtfaelle:

- gueltiges Extended M3U
- UTF-8 mit BOM
- `LF`, `CRLF` und gemischte Zeilenenden
- unbekannte Attribute
- `tvg-id`, `tvg-name`, `tvg-logo`, `group-title`, `tvg-chno`
- `catchup`, `catchup-days`, `catchup-source`
- `catchup=default`
- `catchup=append`
- Stream-Zeile ohne `#EXTINF`
- `#EXTINF` ohne nutzbare Stream-Zeile
- Eintrag ohne verwertbaren Namen
- Eintrag ohne verwertbare Stream-Referenz
- Duplikate mit gleichem Stable Key
- grosse Datei mit streaming- oder chunkfaehiger Verarbeitung
- private URLs mit Tokens, ohne Klartext in Stable Key, Log, Diagnose oder Backup

### Xtream Codes

Pflichtfaelle:

- gueltige Kategorien
- gueltige Live-TV-Streams
- gueltige Filme
- gueltige Serien
- Staffeln und Episoden
- fehlende optionale Metadaten
- fehlende Kategorie
- fehlende Kern-ID
- fehlender Name
- unvollstaendige Antworten mit Teilimport
- ungueltige Zugangsdaten
- Timeouts
- HTTP-Fehler
- Redirects
- begrenzte Wiederholungen
- begrenzte Parallelitaet
- Catch-Up-Metadaten ohne persistierte finale Stream-URL

### XMLTV

Pflichtfaelle:

- plain XML
- gzip
- ZIP mit genau einer XML-Datei
- ZIP ohne XML-Datei
- ZIP mit mehreren XML-Dateien
- UTF-8 mit BOM
- XML-Deklaration mit Encoding
- fehlendes `channel`
- fehlendes `start`
- fehlender Titel mit neutralem lokalem Fallback
- fehlendes `stop`
- Zeitzonen
- `timeShiftMinutes`
- Duplikate
- Ueberlappungen
- Programme ausserhalb der EPG-Aufbewahrung
- rohe XMLTV-Inhalte ohne Log-, Diagnose- oder Backup-Leak

## Mockserver-Tests

Ein lokaler Mockserver ist Pflicht fuer Quellen- und Netzwerkpfade.

Der Mockserver muss reproduzierbar pruefen:

- HTTP und HTTPS, soweit technisch im Testaufbau moeglich
- unsichere HTTP-Markierung
- TLS-Fehler ohne Bypass
- Redirects nur zu `http` oder `https`
- Timeouts
- langsame Antworten
- unterbrochene Downloads
- HTTP-Statuscodes
- grosse Antworten
- unvollstaendige Antworten
- falsche MIME-Typen, sofern relevant
- keine Zugangswerte in Fehlern, Logs oder Diagnoseexporten

Xtream-Mockserver muessen mindestens `player_api.php` fuer Kategorien, Live-TV, VOD, Serien und Details abbilden.

## Datenbank- und Migrationstests

Room- und Migrationstests sind Pflicht fuer:

- jede Datenbankversionsaenderung
- neue Indizes
- neue Unique Constraints
- FTS-Tabellen
- Stable-Key-Spalten
- Pending-Reference-Felder
- Backup-/Restore-bezogene Tabellen
- Android-TV-Systemintegrationstabellen
- EPG-Quellenmodell

Pflichtszenarien:

- Migration aus der letzten produktiven Schema-Version
- Migration aus kleinen Fixtures
- Migration aus grossen Fixtures
- Rollback oder Abbruch bei nicht migrierbaren Daten
- FTS-Rebuild nach Migration
- FTS-Rebuild nach Restore
- Verknuepfung pending Favoriten nach erfolgreichem Provider-Refresh
- Verknuepfung pending Playback Progress nach erfolgreichem Provider-Refresh
- pending Referenzen bleiben erhalten, wenn Zugangsdaten fehlen
- erfolgreiche Refreshes loeschen entfernte Inhalte gemaess Entfernte-Inhalte-Regel
- lokale Room-IDs werden nicht als Backup- oder Restore-Identitaet verwendet

## Refresh-, Abbruch- und Atomizitaetstests

Refresh-Tests muessen Provider-Refresh und EPG-Refresh getrennt pruefen.

Pflichtszenarien:

- Fehler vor Commit veraendert produktive Daten nicht
- Fehler innerhalb der Commit-Transaktion rollt vollstaendig zurueck
- Nutzerabbruch vor Commit laesst produktive Daten unveraendert
- Nutzerabbruch nach sicherem Commit-Punkt erzeugt keinen teilzerstoerten Zustand
- Prozessabbruch hinterlaesst nur bereinigbare Staging-Daten
- naechster App-Start bereinigt verwaiste Staging-Daten
- `Erfolgreich mit Teilfehlern` uebernimmt valide Daten
- destruktive Loeschungen erfolgen nur fuer vollstaendig gelesene und validierte autoritative Teilbereiche
- keine parallelen produktiven Refreshes fuer denselben Provider
- keine parallelen produktiven Refreshes fuer dieselbe EPG-Quelle
- manuelle Aktualisierung ersetzt oder priorisiert einen ausstehenden Lauf, ohne Parallelcommit fuer dieselbe Einheit
- aktive Streams werden durch Refresh nicht unterbrochen

## Backup- und Restore-Tests

Backup- und Restore-Roundtrips sind Pflicht fuer Standard-Backup und verschluesseltes Vollbackup.

Pflichtszenarien:

- Standard-Backup ohne geheime Zugangswerte
- Vollbackup mit Passphrase und Zugangsdaten
- falsche Vollbackup-Passphrase bricht vor lokaler Datenaenderung ab
- beschaedigtes Backup bricht vor lokaler Datenaenderung ab
- inkompatible Backup-Version bricht vor lokaler Datenaenderung ab
- kompatible alte Backup-Version wird vor Restore migriert
- alte Merge-, Konflikt- und Kopie-Felder werden ignoriert oder in das Ersetzen-Modell ueberfuehrt
- lokaler Datenstand Provider A, B, C und Backup A, C ergibt nach Restore nur A und C
- Provider, EPG-Quellen, Mappings, Favoriten, Verlauf, Fortschritt und Einstellungen entsprechen nach Restore dem Backup-Stand
- Standard-Backup verwendet keine lokalen Room-IDs als Identitaet
- Standard-Backup verwendet keine geheimen Klartextdaten als Identitaet
- Standard-Backup mit fehlenden Zugangsdaten markiert Quellen als `Zugangsdaten erforderlich`
- Vollbackup stellt Quellenzugangsdaten nach korrekter Passphrase wieder her
- PIN-Pruefwerte, aktive PIN-Freigaben, PIN-Sperrstatus und Kindersicherung-Schutzflags werden nie wiederhergestellt
- nach Restore ist Kindersicherung deaktiviert
- Hinweis erscheint, wenn Kindersicherung beim Export aktiv war
- vor Restore wird die aktuell lokale PIN abgefragt, wenn lokale Schutzregeln dies verlangen
- internes Sicherheitsbackup wird nach Validierung und vor Ersetzen versucht
- Fehlschlag des Sicherheitsbackups erlaubt Abbruch ohne lokale Datenaenderung
- bewusstes Fortsetzen trotz fehlgeschlagenem Sicherheitsbackup ersetzt danach den Backup-Umfang
- Favoriten und Fortschritt duerfen nach Restore pending sein
- pending Referenzen werden nach erfolgreichem Provider-Refresh verbunden

Es gibt keine v1-Tests fuer Restore-Zusammenfuehren, Import-Konfliktdialoge oder `Als Kopie importieren`, ausser als Legacy-Migrationsfaelle, die belegen, dass solche alten Felder nicht als Restore-Merge interpretiert werden.

## Player-, Catch-Up-, Timeshift- und Progress-Tests

Pflichtszenarien:

- PlaybackRequest wird vor jedem Start mit stabilen Referenzen erzeugt
- Stream-URL-Aufloesung ist just in time
- finale Redirect-URLs werden nicht persistiert oder geloggt
- Live-TV erzeugt Verlauf, aber keinen VOD-Progress
- Catch-Up bleibt EPG-Kontext und erzeugt keinen VOD-Progress
- Catch-Up startet nur mit verwertbarem EPG-Zeitfenster und Quellenunterstuetzung
- aktuelle Live-Sendung wird nicht als Catch-Up gestartet
- interne Filmwiedergabe erzeugt Progress erst ab Mindestposition
- interne Episodenwiedergabe erzeugt Progress erst ab Mindestposition
- Progress wird mindestens alle 10 Sekunden und bei relevanten Lifecycle-Ereignissen gespeichert
- 95 Prozent setzt Abschlussstatus, beendet aber keine Wiedergabe und loest kein Auto-Next aus
- tatsaechliches Medienende setzt Abschlussstatus
- `Als gesehen markieren` setzt Abschlussstatus fuer Film oder einzelne Episode
- `Als ungesehen markieren` loescht den kompletten Progress-Datensatz
- externe Player erzeugen keinen Progress, keinen Abschlussstatus, kein Watch Next und kein Auto-Next
- Rueckkehr aus externem Player zeigt den Fortschritt-nicht-verfuegbar-Hinweis
- Auto-Next aus startet nie automatisch
- Auto-Next ein startet erst am tatsaechlichen Episodenende automatisch
- Auto-Next-Countdown bleibt visuell stabil
- letzte Episode zeigt kein Auto-Next-Panel
- Timeshift nur fuer Live-TV
- Timeshift-Fehler lassen Live-TV ohne Timeshift weiterlaufen und sperren Seek mit Hinweis
- Senderwechsel verwirft Timeshift-Puffer

## Android-TV-UI- und Fokus-Tests

Jeder Screen braucht D-Pad-, Fokus- und Back-Pfad-Pruefungen.

Pflichtscreens:

- Home
- Live-TV
- Filme
- Serien
- Suche
- Player
- Einstellungen
- Wiedergabelisten und EPG-Quellen
- Dialoge, PIN, Fehler, Empty und Loading
- Über die App

Pflichtpruefungen:

- Initialfokus gemaess Screen-Spec
- D-Pad hoch, runter, links und rechts
- OK-Aktion
- Zurueck-Verhalten
- keine Fokusfallen
- Fokus bleibt sichtbar
- Fokus nach Loading, Empty und Error States
- grosse Schrift
- 720p
- 1080p
- 4K
- sichere Bereiche und Overscan
- lange Titel und lange Provider-/Quellennamen
- PIN-Dialoge mit Systemtastatur
- Backup-/Restore-Dialoge
- Deep Links
- Android-TV-Systemsuche
- Watch Next
- fehlende oder pending Systemziele

UI-QA prueft betroffene Aenderungen direkt gegen die jeweils zustaendigen PRD-, Screen-, Wireframe-, Interaction-, Component-, Design-System- und Mockup-Markdownquellen.

Pflichtbereiche bei betroffenen UI-Aenderungen:

- D-Pad, OK, Zurueck und Fokuspfade
- Initialfokus, Fokusfallen und sichtbarer Fokus
- Fokus nach Loading, Empty und Error States
- 720p, 1080p, 4K, grosse Schrift, lange Texte und Overscan
- Komponenten-Konsistenz und Screen-Struktur
- Player Overlay, Timeline und Untertitel-/Overlay-Freiraum
- Settings-, Backup-/Restore-, PIN- und Diagnose-Dialoge
- Android-TV-Systemintegration, Deep Links, Systemsuche und Watch Next

Gerenderte Mockup-PNGs sind visuelle Stilreferenzen. Sie sind keine normative Quelle fuer Navigation, Labels oder UI-Texte.

## Schutz- und Bereinigungstests

Pflichtszenarien:

- PIN-Sperre nach fuenf Fehlversuchen
- Sperrdauern 30 Sekunden, 60 Sekunden und danach 5 Minuten
- Sperre ueber App-Neustart hinweg
- Session-Freigaben nur im Speicher
- geschuetzte Inhalte erscheinen nicht in Android-TV-Systemsuche
- geschuetzte Inhalte erscheinen nicht in Watch Next
- Deep Links pruefen Kindersicherung beim Oeffnen erneut
- Provider-Deaktivierung bereinigt Android-TV-Systemsuche und Watch Next
- Provider-Loeschung bereinigt Android-TV-Systemsuche und Watch Next
- Restore bereinigt Android-TV-Systemsuche und Watch Next
- Migration bereinigt oder baut Systemeintraege neu auf
- Schutzregel-Aenderung bereinigt Systemeintraege
- Abschlussstatus-Aenderung aktualisiert Watch Next
- Diagnoseexport enthaelt keine Zugangswerte, Tokens, Cookies, Header, URLs, Rohdaten, Provider-/Inhaltsnamen, Suchverlaeufe, Datenbank-Dumps, Screenshots oder ungefiltertes Logcat
- Backup-Export enthaelt keine Geheimnisse im Standard-Backup
- Vollbackup-Metadaten enthalten keine Geheimnisse

## Messbare Performancebudgets

Die folgenden Werte sind v1-Zielbudgets.

Sie gelten fuer das dokumentierte Referenzgeraet oder Referenzprofil und die grossen Fixtures aus dieser Datei.

| Bereich | Messung | Zielbudget |
| --- | --- | --- |
| Suche | lokale Suche nach 300 ms Debounce mit grossem Fixture | erste Ergebnislieferung <= 500 ms p95 und <= 1.000 ms p99 |
| Suche | Ergebnisumfang | maximal 20 Treffer je Ergebnisgruppe |
| Suche | UI | keine DB-Abfrage auf dem Main Thread |
| Datenbank | erste Listen-Seite fuer Sender, Filme oder Serien | <= 300 ms p95 |
| Datenbank | Detail-Lookup fuer Film, Serie, Episode oder Sender | <= 150 ms p95 |
| Datenbank | Migration aus letzter produktiver Schema-Version mit grossem Fixture | erfolgreich ohne Datenverlust; Dauer im Benchmarkbericht ausgewiesen |
| Datenbank | FTS-Rebuild nach Restore oder Migration | laeuft ausserhalb blockierender UI-Pfade; Suche zeigt kontrollierten Loading- oder temporaeren Error-Zustand |
| EPG | Now/Next fuer 50 sichtbare Sender | <= 500 ms p95 |
| EPG | Tagesansicht eines Senders | <= 500 ms p95 |
| EPG | Cleanup ausserhalb `epgPastRetentionDays` und `epgFutureRetentionDays` | im Hintergrund, wiederaufnehmbar nach Neustart, ohne UI-Blockade |
| Import M3U | 10.000 Sender aus lokalem Mockserver | <= 120 Sekunden fuer Download, Parse, Stage und Commit |
| Import Xtream | 10.000 Live-TV-Eintraege, 50.000 Filme, 20.000 Serien aus lokalem Mockserver | <= 15 Minuten fuer Metadaten-Import ohne Bilddownloads und ohne Wiedergabestarts |
| Import XMLTV | 3.000.000 Programme aus lokalem Mockserver | <= 30 Minuten fuer Download, Parse, Stage, Commit und Cleanup |
| Abbruch | Nutzerabbruch vor Commit | produktive Daten unveraendert, sichtbarer Abbruchstatus innerhalb von 5 Sekunden am naechsten sicheren Abbruchpunkt |
| Restore | Standard-Backup-Roundtrip mit grossem Nutzer-Datenfixture | lokale Daten erst nach Validierung und Sicherheitsbackup-Versuch ersetzen |
| Speicher | grosse Provider-Imports | kein OOM; Peak App Heap unter 70 Prozent des App-Heap-Limits und hoechstens 256 MiB ueber App-Start-Baseline |
| Speicher | grosse XMLTV-Imports | kein OOM; Peak App Heap unter 70 Prozent des App-Heap-Limits und hoechstens 384 MiB ueber App-Start-Baseline |
| Speicher | wiederholtes Oeffnen und Schliessen zentraler Screens | nach 10 Zyklen kein monotones Heap-Wachstum ueber 25 MiB |
| UI | Fokuswechsel auf Hauptscreens | keine sichtbare Blockade durch Import, FTS-Rebuild, Bildladen oder EPG-Abfragen |

Bilddownloads, echte WAN-Latenz, Provider-Drosselung und externe Player sind nicht Teil der Importzeitbudgets. Sie muessen getrennt gemessen oder als Laufzeitumgebung dokumentiert werden.

## Release-DoD fuer Tests

Ein Vivicast-Release-Kandidat darf nur freigegeben werden, wenn:

- alle kritischen Muss-Anforderungen einen Testfall besitzen
- alle Parser-Golden-Tests erfolgreich sind
- alle Mockserver-Quellenpfade erfolgreich sind
- alle Datenbankmigrationen erfolgreich sind
- Backup- und Restore-Roundtrips erfolgreich sind
- Schutz- und Redaction-Tests erfolgreich sind
- Android-TV-Systemintegration gegen Kindersicherung und Providerstatus geprueft ist
- D-Pad-, Fokus- und Back-Pfade fuer betroffene Screens geprueft sind
- 720p, 1080p, 4K und grosse Schrift fuer betroffene UI-Aenderungen geprueft sind
- Performancebudgets gemessen und bewertet sind
- bekannte Budgetabweichungen mit Risikoentscheidung dokumentiert sind

## App-Repository-Nachweis

Das App-Repository soll fuer groessere PRs einen Testnachweis enthalten:

```text
Test Evidence:
- Sources checked:
- Requirement links:
- Automated tests:
- Instrumented tests:
- Mockserver scenarios:
- Migration tests:
- Backup/restore roundtrips:
- UI focus/back QA:
- Resolution/font QA:
- Benchmarks:
- Redaction/security checks:
- Known gaps:
```

Dieser Nachweis ersetzt keine Tests. Er macht sichtbar, welche Testarten fuer die Aenderung relevant waren.
