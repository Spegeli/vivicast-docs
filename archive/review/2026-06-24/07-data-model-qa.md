# 07 - Datenmodell QA

Status: Review-Referenz v17

## Zweck

Diese Datei dokumentiert die Datenmodell-QA nach der Settings-, Backup- und Cross-Document-QA.

Geprueft wurden:

- Room/DataStore/Keystore-Aufteilung
- Backup-Datenvertrag
- Favoriten
- Verlauf
- Suchverlauf
- Such-FTS-Indexe
- Wiedergabefortschritt
- Provider-Isolation
- FSK-18-Datenmodell
- Hintergrundjobs und Backup-v1-Abgrenzung
- EPG-Pipeline und EPG-Aufbewahrung
- atomarer Import und Refresh
- Schutzklassen, Secret Store und PIN-Sperrstatus
- Android-TV-Systemsuche, Deep Links und Watch Next

## Gepruefte Dateien

- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `prd/PRD-v1/06-data-model.md`
- `prd/PRD-v1/07-background-jobs-performance.md`
- `prd/PRD-v1/10-backup-import-requirements.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `architecture/decisions/ADR-014-security-data-network-backup.md`
- `architecture/decisions/ADR-008-android-tv-integration.md`
- `codex/coding-rules.md`

## Gefundene und behobene Punkte

### 1. DataStore-Aufteilung veraltet

Problem:

- Sprache lag noch unter Optik.
- User-Agent fehlte in DataStore Allgemein.
- Backup- und neue Wiedergabe-/Optik-Optionen waren nicht vollstaendig abgebildet.

Behoben:

- Sprache und User-Agent liegen jetzt unter Allgemein.
- Optik enthaelt nur visuelle Optionen.
- Wiedergabe, Backup und Kindersicherung wurden ergaenzt.

### 2. Suchverlauf fehlte im Datenmodell

Problem:

- Suche und Backup fordern Suchverlauf.
- Im Datenmodell gab es keine `SearchHistoryEntity`.

Behoben:

- `SearchHistoryEntity` ergaenzt.
- Maximal 20 Eintraege dokumentiert.
- Backup-Bezug dokumentiert.

### 3. PlaybackProgress zu unscharf

Problem:

- Wiedergabefortschritt fuer Serien/Episoden war nicht eindeutig.

Behoben:

- `PlaybackProgressEntity` nutzt `MOVIE` und `EPISODE`.
- Serien-Fortsetzen basiert auf Episodenfortschritt.

### 4. FSK-18-Datenmodell fehlte

Problem:

- Kindersicherung verlangte FSK-18-Schutz.
- Movie, Series und Episode hatten keine expliziten Felder dafuer.

Behoben:

- `ageRating` und `isAdult` in Movie, Series und Episode ergaenzt.
- Fehlende Altersdaten bedeuten nicht automatisch 18+.

### 5. Keystore-Regeln zu pauschal

Problem:

- Aeltere Regeln behandelten alle URLs pauschal als geschuetzt.
- Backup-Regeln unterscheiden normale Quellenadressen von privaten Quellenadressen mit eingebetteten Zugangswerten.

Behoben:

- Keystore-Regeln differenzieren jetzt geheime Werte, private Quellenadressen und normale Konfigurationsadressen.

### 6. Hintergrundjobs widersprachen Backup-v1

Problem:

- Kapitel 7 nannte `BackupWorker`, obwohl Backup in v1 manuell ist.

Behoben:

- `BackupWorker` aus v1-Hintergrundjobs entfernt.
- Backup bleibt manuell.

### 7. Diagnose-Export wurde nach dieser QA neu entschieden

Frueherer QA-Stand:

- Log-Export war nicht Teil von v1.
- `Über die App` enthielt nur bereinigte allgemeine technische Daten.

Durch Owner-Entscheidungen O-03a bis O-03c3 ersetzt:

- Bereinigter Log-Export ist Bestandteil von v1.
- UI-Ort ist `Einstellungen > Über die App > Diagnose und Support`.
- Logs sind export-only und werden niemals direkt in der App angezeigt oder kopiert.
- Allgemeine nicht-private Support-Informationen duerfen weiterhin angezeigt und kopiert werden.
- Exportformat ist ZIP mit MIME-Type `application/zip`.
- Dateiname ist `vivicast-diagnostics-YYYYMMDD-HHmmss.zip`.
- Das ZIP enthaelt verpflichtend `vivicast-diagnostics.log` und `diagnostics-metadata.json` in UTF-8.
- Die Logdatei ist auf bereinigte technische Ereignisse begrenzt; die JSON-Datei enthaelt technische Exportmetadaten.
- Zugangswerte, URLs, Rohdaten, Provider-/Inhaltsnamen, Suchverlauf, Datenbank-Dumps, Screenshots und ungefiltertes System-Logcat sind ausgeschlossen.
- Technische Zuordnungen verwenden nur neutrale interne IDs.
- Vivicast erzeugt das Archiv selbst und schreibt es direkt in den Ziel-OutputStream.
- `diagnosticsLoggingEnabled=false` und `diagnosticsRetentionDays=1` sind DataStore-Defaults; die Dauer ist auf 1 bis 7 Tage begrenzt.
- Sitzungen und Segmente liegen als interne Dateien und Metadaten im privaten App-Speicher, nicht in Room, DataStore oder dem Standard-Backup.
- Pro Sitzung sind Abschlussgrund und Zeitqualitaet rekonstruierbar; nicht saubere Enden werden systemgestuetzt oder geschaetzt abgeschlossen.
- Feste Grenzen sind 20 MiB insgesamt, 2 MiB je Segment und drei Segmente beziehungsweise 6 MiB Logdaten je Sitzung.
- Rotations- und Trunkierungsmetadaten werden dauerhaft rekonstruiert und im Export ausgewiesen.
- O-03 ist damit vollstaendig entschieden und dokumentiert.

Aktuelle normative Quellen:

- `prd/PRD-v1/07-background-jobs-performance.md`
- `prd/PRD-v1/11-about-app-requirements.md`

### 8. EPG-Standardintervall fehlte im DataStore-Vertrag

Problem:

- Der Settings-Wireframe zeigte 24 Stunden, PRD und DataStore legten den Standardwert jedoch nicht verbindlich fest.

Behoben durch O-04:

- globaler Standardwert 24 Stunden
- DataStore-Schluessel `epgRefreshIntervalHours = 24`
- Intervall-Refresh klar von App-Start, Playlist-Aenderung und manueller Aktualisierung getrennt

### 9. Startbereich fehlte im DataStore- und Settings-Vertrag

Problem:

- Produktuebersicht, Navigation und DoD forderten einen einstellbaren Startbereich.
- Settings und DataStore enthielten dafuer weder Option, Werte noch Default.

Behoben durch O-05:

- globale Allgemein-Auswahl mit Home, Live-TV, Filme und Serien
- Standardwert und Fallback Home
- DataStore-Schluessel `startDestination = HOME`
- zulaessige technische Werte `HOME`, `LIVE_TV`, `MOVIES` und `SERIES`
- Wirkung ab dem naechsten regulaeren App-Start; explizite Android-TV-Ziele haben Vorrang
- App-Einstellung ist Bestandteil des Standard-Backups

### 10. Auto-Next fehlte im DataStore- und Settings-Vertrag

Problem:

- Serien-PRD und DoD forderten Auto-Next, aber DataStore und Wiedergabe-Settings enthielten weder Schluessel noch vollstaendigen Ablauf.
- Der bisherige Player-Dialog verwendete andere Buttontexte und beschrieb den manuellen Zustand bei deaktiviertem Auto-Next nicht.

Behoben durch O-07:

- `autoNextEpisodeEnabled = false`
- `autoNextEpisodeCountdownSeconds = 10`
- zulaessige Countdown-Werte 5, 10, 15 und 30 Sekunden
- Countdown-Wert bleibt bei deaktiviertem Toggle gespeichert
- keine eigene Room-Entity; Episode-Reihenfolge und Playback Progress bleiben getrennt
- manuelles Panel nach Episodenende bei `Aus`, Vorab-Countdown bei `Ein`

### 11. Abschluss-Schwelle und manuelle Markierung fehlten

Problem:

- Der Abschlussstatus war als optionaler benutzerdefinierter Schwellenwert beschrieben.
- DataStore, Continue Watching, Watch Next und Auto-Next waren nicht eindeutig voneinander abgegrenzt.
- Die Datenwirkung von `Als ungesehen markieren` war nicht festgelegt.

Behoben:

- `PLAYBACK_COMPLETION_THRESHOLD_PERCENT = 95` ist eine feste fachliche Konstante und kein DataStore-Wert.
- Die Regel gilt nur fuer `MOVIE` und `EPISODE`.
- `progressPercent >= 95` oder das tatsaechliche Medienende setzt `isCompleted = true`.
- Abgeschlossene Datensaetze sind keine direkten Resume-Ziele; bei Serien darf daraus die naechste Episode bei Position 0 abgeleitet werden.
- `Als gesehen markieren` legt den Datensatz bei Bedarf an oder aktualisiert ihn.
- `Als ungesehen markieren` loescht den gesamten `PlaybackProgressEntity`-Datensatz.
- Auto-Next bleibt ausschliesslich an das tatsaechliche Medienende gebunden.

### 12. Backup-Regeln in Kapitel 5 veraltet

Problem:

- Kapitel 5, Backup-Vertrag und Settings beschrieben unterschiedliche Restore-Modi.
- Backup-Teilrestore, Backup-Rotation und Kopie-Import waren als unterstuetzt oder offen dokumentiert.

Behoben:

- Kapitel 5 verweist auf den verbindlichen Backup-Datenvertrag.
- Restore ersetzt in v1 immer den Backup-Umfang.
- Backup-Schema-Migration alter kompatibler Versionen bleibt erlaubt, ist aber kein Zusammenfuehren lokaler und importierter Daten.
- `Zusammenfuehren`, Import-Konfliktdialoge und `Als Kopie importieren` sind nicht Teil von v1.
- Backup-Teilrestore und Backup-Rotation sind nicht Teil von v1, sofern spaeter nicht explizit entschieden.

### 13. Cache- und Verlaufsoptionen widerspruechlich

Problem:

- DataStore nannte noch ein Verlaufs-Aktivierungsflag, Verlaufslimits und einen Cachegroessenwert als gespeicherte Werte.
- Hintergrundjobs beschrieben die Cachegroesse des Medien-Caches als moegliche freie Einstellung.
- Settings-Dokumente hatten keine sichtbaren Cache- und Verlaufswartungen.

Behoben durch O-09:

- Die Groesse des Medien-Caches, die Cache-Rotation und frei konfigurierbare Verlaufslimits sind keine DataStore-Werte.
- Die aktuelle Groesse des Medien-Caches wird aus dem Dateisystem berechnet.
- `Cache leeren` entfernt nur Medien-Cache-Dateien fuer Logos, Poster, Staffelbilder und Episodenbilder.
- Medien-Cache-Dateien sind nicht Teil des Standard-Backups.
- Verlauf kann fuer Live-TV, Filme, Serien, Suche oder komplett geloescht werden.
- Film- und Serienverlauf-Loeschung umfassen den jeweiligen Wiedergabefortschritt.
- Der Suchverlauf bleibt fest auf maximal 20 Eintraege begrenzt.

### 14. Kindersicherung nach Restore unklar

Problem:

- DataStore und Backup-Vertrag liessen offen, ob Kindersicherung-Schutzflags aus einer Backup-Datei nach Restore wieder aktiv werden.
- Dadurch konnte eine alte PIN- oder Schutzkonfiguration nach Restore unbemerkt wieder greifen.

Behoben durch O-10:

- Restore nutzt fuer PIN-Autorisierung ausschliesslich die aktuell lokale PIN, wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN fuer Backup/Restore verlangt.
- PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus Backups werden ignoriert.
- Nach Restore ist Kindersicherung deaktiviert.
- Falls Kindersicherung beim Export aktiv war, wird nur ein Hinweis fuer die manuelle Reaktivierung angezeigt.

### 15. Externer Player und Fortschritt unklar

Problem:

- Fortschritt, Abschlussstatus und Auto-Next waren fuer externe Player nicht eindeutig abgegrenzt.
- Externe Player liefern keine verlaessliche, einheitliche Rueckgabe fuer Position, Dauer oder Medienende.

Behoben durch O-11:

- Nur der interne Vivicast-Player schreibt automatische Fortschrittsdaten.
- Externe Player erzeugen oder aktualisieren keine `PlaybackProgressEntity`.
- Externe Player setzen keinen Abschlussstatus.
- Externe Player loesen kein Auto-Next aus.
- Vorhandener Fortschritt bleibt nach externer Wiedergabe unveraendert.
- Nach Rueckkehr aus externer VOD-Wiedergabe zeigt Vivicast einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.

### 16. EPG-Pipeline und Aufbewahrung fehlten

Problem:

- EPG-Programme waren im Datenmodell noch provider-/senderbezogen beschrieben.
- Eine eigene EPG-Kanal-Entity fehlte.
- EPG-Aufbewahrung war intern statt als globale v1-Settings definiert.

Behoben durch Paket E:

- EPG-Datenmodell verwendet `EPGSource -> EPGChannel -> EPGProgram`.
- Provider-Sender werden ueber Mapping und Provider-EPG-Prioritaet angebunden.
- Manuelle EPG-Zuordnung gewinnt vor automatischer Zuordnung.
- EPG-Vergangenheit und EPG-Zukunft sind globale DataStore-Werte mit 1 bis 14 Tagen; Defaults sind 1 und 7.
- Cleanup entfernt nur EPG-Programmdaten ausserhalb des konfigurierten Fensters.

### 17. Atomarer Import und Refresh fehlten

Problem:

- Kapitel 7, Datenmodell und DoD beschrieben noch keine klare Commit-Grenze fuer Provider- und EPG-Refreshes.
- Teilfehler waren als Parser-Verhalten definiert, aber noch nicht mit Loeschentscheidungen und Staging verbunden.

Behoben durch Paket G:

- Provider-Refreshes committen atomar pro Provider.
- EPG-Refreshes committen atomar pro EPG-Quelle.
- Staging-Daten sind technische Zwischendaten und keine produktiven App-Daten.
- Fehler vor Commit behalten alte Daten; Commit-Fehler rollen vollstaendig zurueck.
- Teilfehler duerfen valide Eintraege uebernehmen, aber Loeschungen nur aus autoritativen Teilbereichen ableiten.

### 18. Such-FTS-Indexe fehlten

Problem:

- ADR-005 und PRD forderten lokale Suche, aber Datenmodell und Indexpflege beschrieben noch keine konkrete FTS-Struktur.
- Der Suchverlauf war dokumentiert, die eigentlichen Suchindexe aber nicht.

Behoben durch Paket H:

- Datenmodell definiert `ChannelSearchFts`, `MovieSearchFts`, `SeriesSearchFts` und `EPGProgramSearchFts`.
- FTS-Indexe sind technische Ableitungen aus produktiven Daten und nicht Teil von Backups.
- Provider-FTS wird im atomaren Provider-Commit gepflegt.
- EPG-FTS wird im atomaren EPG-Quellen-Commit gepflegt.
- Restore, Migration, Delete und EPG-Cleanup aktualisieren oder rebuilden betroffene FTS-Indexe.

### 19. Player-/Progress-Speicherrhythmus fehlte

Problem:

- `PlaybackProgressEntity` war fachlich abgegrenzt, aber Mindestposition, Speicherrhythmus und Abgrenzung zu Catch-Up waren noch nicht vollstaendig dokumentiert.

Behoben durch Paket I:

- Automatischer Progress wird nur fuer interne Filme und Episoden geschrieben.
- Neue automatische Datensaetze entstehen erst ab 10 Sekunden Position oder 1 Prozent Fortschritt.
- Nach Anlage wird mindestens alle 10 Sekunden sowie bei Pause, Seek-Ende, Player-Verlassen, App-Hintergrund und Medienende gespeichert.
- Live-TV und Catch-Up erzeugen keinen `PlaybackProgressEntity`-Datensatz.
- Audio-/Untertitel-Defaults und External-Player-Modus sind als DataStore-Werte dokumentiert; sitzungsbezogene Track-Auswahl ist nicht persistent.

### 20. Schutzklassen und geschuetzte Speicherung fehlten

Problem:

- Keystore-Regeln waren noch zu grob und trennten nicht eindeutig zwischen Keystore-Schluessel, Secret Store, normalen Daten, sensiblen Nutzerdaten und lokal sicherheitswirksamen Zustaenden.
- PIN-Fehlversuche und Sperrstatus waren nicht als lokale Sicherheitszustaende abgebildet.
- Verlust geschuetzter lokaler Geheimnisse war nicht datenmodellseitig beschrieben.

Behoben durch Paket J:

- Datenmodell unterscheidet `Normal`, `Sensibel`, `Geheim` und `Sicherheitswirksam lokal`.
- Android Keystore speichert nicht exportierbare Schluessel; der geschuetzte Secret Store speichert geheime Nutzwerte verschluesselt im privaten App-Speicher.
- Secret-Store-Verlust markiert betroffene Quellen als `Zugangsdaten erforderlich`.
- PIN-Fehlversuchszaehler, Sperrstufe und `lockedUntil` sind lokale Sicherheitszustaende.
- Aktive PIN-Freigaben sind nur im Speicher.
- PIN-Pruefwerte, aktive Freigaben, Sperrstatus und Kindersicherung-Schutzflags bleiben auch aus verschluesselten Vollbackups ausgeschlossen.

### 21. Android-TV-Systemintegrationsdaten fehlten

Problem:

- Deep Links, Android-TV-Systemsuche und Watch Next waren als Funktionen genannt, aber nicht mit stabilen Zielreferenzen, Datenpflege und Cleanup-Regeln im Datenmodell abgebildet.
- Provider-Deaktivierung, Restore, pending Referenzen und Kindersicherung waren fuer Systemoberflaechen nicht eindeutig.

Behoben durch Paket K:

- Datenmodell definiert Deep-Link-Referenzen ueber `providerStableKey + mediaType + mediaStableKey`.
- Android-TV-Systemsuchindex ist als abgeleiteter technischer Index aus produktiven Room-Daten dokumentiert.
- Watch-Next-Publikationen sind als technische Systemzuordnungen dokumentiert und nicht backupfaehig.
- Lokale Room-IDs sind keine extern sichtbaren Deep-Link- oder System-IDs.
- Geschuetzte Inhalte, pending Restore-Referenzen und Inhalte deaktivierter Provider werden nicht in Systemsuche oder Watch Next veroeffentlicht.
- Provider-Deaktivierung, Provider-Loeschung, Restore, Migration und Schutzregel-Aenderung loesen Cleanup aus.

## Ergebnis

Keine blockierenden Datenmodell-Widersprueche mehr fuer:

- Settings
- Backup
- Favoriten
- Verlauf
- Suchverlauf
- Such-FTS-Indexe
- Wiedergabefortschritt
- Player-/Progress-Speicherrhythmus
- Provider-Isolation
- Kindersicherung
- EPG-Pipeline und EPG-Aufbewahrung
- atomarer Import und Refresh
- Schutzklassen, Secret Store, PIN-Sperrstatus und Vollbackup-Schutzformat
- Android-TV-Systemsuche, Deep Links und Watch Next

Der Diagnose-Export ist hinsichtlich Ort, Export-only-Verhalten, ZIP-Dateiformat, Inhalt, Sitzungszeitraum, Aufbewahrung, Groessenbegrenzung und Rotation entschieden und im Datenmodell konsistent abgebildet.

Das globale EPG-Aktualisierungsintervall ist mit dem Standardwert 24 Stunden und dem DataStore-Schluessel `epgRefreshIntervalHours` konsistent abgebildet.

Die EPG-Pipeline ist mit `EPGSource -> EPGChannel -> EPGProgram`, Mapping/Prioritaet, manueller Zuordnung, DataStore-Aufbewahrung und Cleanup konsistent abgebildet.

Der Import-/Refresh-Vertrag ist mit Staging, autoritativen Teilbereichen, Room-Transaktionen pro Provider beziehungsweise EPG-Quelle und pending Restore-Referenzen konsistent abgebildet.

Die Such-FTS-Indexe sind als ableitbare technische Daten mit produktiver Commit-Kopplung, Rebuild-Regeln und Backup-Abgrenzung konsistent abgebildet.

Der Startbereich ist mit den vier erlaubten Enum-Werten und dem DataStore-Default `startDestination = HOME` konsistent abgebildet.

Auto-Next ist mit Toggle, Countdown-Werten, Defaults und Backup-Bezug konsistent in DataStore und Settings abgebildet. Eine eigene Room-Entity ist nicht erforderlich.

Die Abschluss-Schwelle ist mit dem festen Wert 95 Prozent, `isCompleted`, manueller Markierung, vollstaendigem Progress-Loeschen bei `Als ungesehen markieren`, Serien-Weiterleitung zur naechsten Episode und klarer Trennung von Auto-Next konsistent abgebildet.

Cache- und Verlaufswartung ist ohne DataStore-Drift konsistent abgebildet: Medien-Cache-Werte sind berechnete Dateisysteminformationen, freie Cache-/Verlaufslimits sind keine v1-Einstellungen und Suchverlauf bleibt auf 20 Eintraege begrenzt.

Kindersicherung nach Restore ist konsistent abgegrenzt: Backup-Inhalte reaktivieren keine PIN-Funktion, keine Schutzflags und keine PIN-Freigaben; die lokale aktuelle PIN schuetzt nur den Restore-Vorgang selbst.

External-Player-Progress ist konsistent abgegrenzt: Externe Player erzeugen oder aktualisieren keine `PlaybackProgressEntity`, setzen keinen Abschlussstatus und loesen kein Auto-Next aus; nur der interne Vivicast-Player schreibt automatische Fortschrittsdaten.

Player-/Progress-Speicherung ist konsistent abgegrenzt: Nur interne Filme und Episoden erzeugen automatische Fortschrittsdaten; Catch-Up und Live-TV bleiben ohne VOD-Resume-Ziel; Track-Defaults sind globale DataStore-Werte, manuelle Track-Auswahl bleibt sitzungsbezogen.

Schutzklassen und Secret Store sind konsistent abgegrenzt: Room und DataStore enthalten keine geheimen Klartextwerte, Android Keystore fuehrt nicht exportierbare Schluessel, der Secret Store haelt geheime Nutzwerte verschluesselt und lokal sicherheitswirksame PIN-/Kindersicherungszustaende werden nie aus Backups uebernommen.

Android-TV-Systemintegrationsdaten sind konsistent abgegrenzt: Systemsuche und Watch Next werden nur aus produktiven Daten abgeleitet, nutzen stabile Zielreferenzen, sind kein Backup-Bestandteil und werden bei Provider-, Restore-, Migrations-, Fortschritts- und Kindersicherungsaenderungen bereinigt.

## Naechste sinnvolle QA

- ADR-Abgleich, sobald die ADR-Dateien gezielt geprueft werden koennen
- Implementierungsbereitschaft fuer `Spegeli/vivicast`
- Modul- und Datenbank-Migrationsplan fuer die App-Implementierung

## Empfehlung fuer Codex

Vor Datenmodell-Implementierung mindestens lesen:

1. `prd/PRD-v1/05-iptv-epg-favorites.md`
2. `prd/PRD-v1/06-data-model.md`
3. `prd/PRD-v1/07-background-jobs-performance.md`
4. `prd/PRD-v1/10-backup-import-requirements.md`
5. `prd/PRD-v1/08-android-tv-security.md`
6. `architecture/decisions/ADR-014-security-data-network-backup.md`
7. `architecture/decisions/ADR-008-android-tv-integration.md`
8. `codex/coding-rules.md`
