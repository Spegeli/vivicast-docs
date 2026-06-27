# Vivicast PRD v1
## Kapitel 7 - Hintergrundjobs, Synchronisation, Caching, Fehlerbehandlung und Performance

Status: bereinigt v13

---

# 7. Ziel

Vivicast muss grosse IPTV-Bibliotheken zuverlässig und performant verwalten.

Dazu verwendet die App:

```text
WorkManager
Delta-Synchronisation
Caching
Lokale Datenhaltung
```

---

# 7.1 Hintergrundjobs

## Technologie

```text
Android WorkManager
```

## Unterstuetzte Jobs in v1

```text
PlaylistRefreshWorker
EPGRefreshWorker
LogoRefreshWorker
CacheCleanupWorker
```

Backup wird in v1 manuell gestartet und ist kein automatischer Hintergrundjob.

Der fachliche Backup-Datenvertrag liegt in:

```text
prd/PRD-v1/10-backup-import-requirements.md
```

---

# Hintergrundaktualisierung

## Einstellung

```text
Aktiviert
Deaktiviert
```

## Aktiviert

Automatische Aktualisierungen dürfen im Hintergrund laufen, soweit Android TV dies zulaesst.

Konkrete Ausloeser wie App-Start oder Playlist-Änderung folgen den jeweiligen Settings.

## Deaktiviert

Automatische Hintergrundaktualisierungen werden nicht ausgefuehrt.

Manuelle Aktualisierung bleibt möglich.

---

# Appstart

Wenn App-Start-Aktualisierung aktiviert ist und Daten überfaellig sind:

```text
App startet sofort
↓
Vorhandene Daten anzeigen
↓
Aktualisierung starten, ohne UI zu blockieren
```

Wenn der globale Hauptschalter für Hintergrundaktualisierung deaktiviert ist, startet keine automatische App-Start-Aktualisierung.

---

# Keine Blockierung

Nicht anzeigen:

```text
Bitte warten...
```

Die App zeigt vorhandene Daten und aktualisiert im Hintergrund oder nach manueller Aktion.

---

# 7.2 Globaler Refresh Scheduler

## Ziel

Vermeidung mehrfacher Aktualisierungen.

## Ablauf

```text
1. Faellige Playlists sammeln
2. Playlists aktualisieren
3. Benoetigte EPG Quellen sammeln
4. Duplikate entfernen
5. EPG Quellen einmalig aktualisieren
6. EPG-Kanäle und EPG-Programme quellbezogen speichern
7. EPG Mapping und Provider-EPG-Prioritäten anwenden
8. EPG-Programmdaten ausserhalb der konfigurierten Aufbewahrung entfernen
9. Logos aktualisieren
10. Cache Cleanup
```

## EPG Optimierung

Wenn mehrere Provider dieselbe Quelle nutzen:

```text
EPG Quelle
↓
1x aktualisieren
```

Beispiel:

```text
Provider A -> EPG 1
Provider B -> EPG 1
Provider C -> EPG 1
```

Ergebnis:

```text
EPG 1 wird einmal geladen.
```

## Gleichzeitige Aktualisierungen

Interne technische Begrenzung.

Standard:

```text
2
```

Diese Begrenzung ist in v1 keine zwingende UI-Einstellung.

---

# 7.3 Atomarer Import und Refresh

## Grundregel

Import und Refresh sind atomar pro Provider und pro EPG-Quelle.

Der globale Refresh-Scheduler koordiniert mehrere Jobs, bildet aber keine globale Alles-oder-nichts-Transaktion über alle Provider und EPG-Quellen.

Wenn Provider A erfolgreich aktualisiert wird und Provider B wegen Netzwerkfehler fehlschlaegt, wird Provider A committed und Provider B behält alte Daten.

Wenn EPG-Quelle A erfolgreich aktualisiert wird und EPG-Quelle B fehlschlaegt, wird EPG-Quelle A committed und EPG-Quelle B behält alte EPG-Daten bis zum normalen Cleanup.

Die Architekturentscheidung dazu liegt in:

```text
architecture/decisions/ADR-012-atomic-import-refresh.md
```

## Lebenszyklus

Jeder Provider-Refresh und jeder EPG-Quellen-Refresh folgt diesem Ablauf:

```text
1. Download
2. Parse
3. Validate
4. Stage
5. Diff
6. Commit
7. Cleanup
```

Vor `Commit` dürfen keine produktiven Provider-, Medien-, EPG- oder nutzerbezogenen Daten verändert werden.

## Staging

Download-, Parser- und Validierungsergebnisse werden vor dem Commit in einem technischen Staging gehalten.

Staging darf als temporaere Datei, In-Memory-Struktur oder dedizierte Staging-Tabelle umgesetzt werden.

Staging-Daten sind keine produktiven App-Daten:

- sie werden nicht in normalen Listen angezeigt
- sie sind nicht Teil von Backup oder Restore
- sie dürfen keine neue lokale Identitaet für Nutzerreferenzen erzeugen
- sie müssen über einen Laufkontext wie `refreshRunId` oder gleichwertig bereinigt werden können

Nach Prozessabbruch, App-Absturz oder Systemabbruch werden verwaiste Staging-Daten beim nächsten App-Start oder vor dem nächsten Refresh geloescht.

## Validate und Diff

Validate prueft mindestens:

- Quelle lesbar
- Authentifizierung erfolgreich, falls erforderlich
- Format nutzbar
- aktivierter Importumfang vorhanden
- stabile Schluessel ableitbar
- benoetigte Pflichtfelder vorhanden
- produktive Constraints vor Commit einhaltbar

Diff wird ausschliesslich aus validierten Staging-Daten und bestehenden produktiven Daten berechnet.

Lokale Room-IDs sind für Diff-Identitaet nicht massgeblich. Für Provider-Inhalte gilt `providerStableKey + mediaType + mediaStableKey`. Für EPG gilt `epgSourceStableKey + epgChannelStableKey + startTimeUtc + normalizedTitle` beziehungsweise der im EPG-Vertrag definierte stabile Schluessel.

## Commit-Grenzen

Der Commit für einen Provider laeuft in einer Room-Transaktion und umfasst die produktive Änderung dieses Providers:

- Provider-Metadaten und Status
- Kategorien
- Sender
- Filme
- Serien
- Staffeln
- Episoden
- stabile Verknuepfung pending Nutzerreferenzen
- Entfernen nicht mehr vorhandener providerbezogener Inhalte, sofern der betroffene Teilbereich autoritativ ist
- Entfernen zugehoeriger Favoriten, Verlaeufe und Wiedergabefortschritte, sofern Inhalte autoritativ entfernt werden

Der Commit für eine EPG-Quelle laeuft in einer Room-Transaktion und umfasst die produktive Änderung dieser EPG-Quelle:

- EPG-Quellen-Metadaten und Status
- EPG-Kanäle
- EPG-Programme
- technische Zaehler und letzte erfolgreiche Aktualisierung

EPG-Mapping und Provider-EPG-Prioritäten dürfen nach erfolgreichen Provider- oder EPG-Commits separat aktualisiert werden. Manuelle EPG-Zuordnungen dürfen durch einen fehlgeschlagenen oder nicht autoritativen Refresh nie geloescht oder überschrieben werden.

## Autoritative Ergebnisse

`Erfolgreich` ist autoritativ für den kompletten aktivierten Importumfang.

`Erfolgreich mit Teilfehlern` darf valide neue oder geänderte Eintraege committen.

Destruktive Entfernt-Loeschungen dürfen bei `Erfolgreich mit Teilfehlern` nur für Teilbereiche ausgefuehrt werden, die vollstaendig gelesen und validiert wurden. Teilbereiche mit übersprungenen Eintraegen, Bereichsfehlern oder nicht garantierter Vollstaendigkeit behalten vorhandene alte Inhalte, die im aktuellen Lauf nicht sicher zugeordnet wurden.

`Fehlgeschlagen` und `Abgebrochen` dürfen keine produktiven Inhaltsaenderungen committen.

## Fatalfehler und Abbruchschwelle

Ein Lauf ist fatal fehlgeschlagen, wenn mindestens eine dieser Bedingungen zutrifft:

- Quelle nicht lesbar
- Authentifizierung fehlgeschlagen
- Antwortformat nicht nutzbar
- kein verwertbarer Eintrag im aktivierten Importumfang
- ein aktivierter Xtream-Bereich liefert wegen Endpoint-, Netzwerk- oder Formatfehler kein vollstaendig auswertbares Ergebnis
- produktive Constraints können vor Commit nicht erfuellt werden

Ein leerer, aber gültig geladener aktivierter Xtream-Bereich ist kein Fatalfehler, wenn der Provider diesen Bereich plausibel leer liefert.

Die Abbruchschwelle ist fachlich qualitativ und nicht als sichtbare Prozent-Einstellung definiert.

## Abbruch und Prozessfehler

Wenn Download, Parse, Validate, Stage oder Diff abbrechen, bleiben produktive Daten unverändert.

Wenn der Prozess während einer Room-Transaktion abbricht, muss die Datenbanktransaktion vollstaendig committen oder vollstaendig zurückrollen.

Wenn der Prozess nach erfolgreichem Commit, aber vor Cleanup abbricht, bleiben die committed produktiven Daten gültig. Verwaiste Staging- und Temporaerdaten werden später bereinigt.

Aktive Streams dürfen durch Import oder Refresh nicht unterbrochen werden.

## Scheduling und Wiederholungen

Der Scheduler dedupliziert gleichartige Refresh-Anfragen für dieselbe Provider- oder EPG-Quelle.

Für denselben Provider oder dieselbe EPG-Quelle darf nicht gleichzeitig mehr als ein produktiver Refresh laufen.

Manuelle Aktualisierung darf einen ausstehenden automatischen Lauf vorziehen oder ersetzen, aber keinen zweiten parallelen Lauf für dieselbe Einheit starten.

Parallele Refreshes verschiedener Provider oder EPG-Quellen sind erlaubt und intern begrenzt.

Wiederholungen sind begrenzt. Ein erneuter Versuch darf erst nach einem vollstaendig fehlgeschlagenen oder abgebrochenen Lauf starten und muss einen neuen Laufkontext verwenden.

## Initiales Hinzufügen

Der Verbindungstest entscheidet, ob eine neue Wiedergabeliste gespeichert werden darf.

Nach erfolgreichem Verbindungstest wird der Provider gespeichert und anschließend importiert.

Wenn dieser erste Import fehlschlaegt, bleibt der Provider mit Fehlerstatus bestehen. Es dürfen keine halb importierten Inhalte sichtbar werden.

---

# 7.3a Listenfenster und Paging

Sender, Filme und Serien duerfen nicht vollstaendig in UI-State geladen werden.

Room-Abfragen fuer grosse Listen muessen paging- oder fensterfaehig sein.

Der UI-State haelt nur:

- sichtbares Fenster
- Fokus-ID
- aktive Filter
- aktive Sortierung
- notwendigen Screen-Kontext

Poster und Logos werden nur fuer sichtbare oder kurz bevorstehende Elemente geladen.

EPG-Tagesansichten werden pro Sender lazy beziehungsweise fensterfaehig geladen.

Horizontale Suchreihen bleiben bei maximal 20 Treffern je Gruppe.

Import-, Refresh- und FTS-Rebuild-Arbeiten duerfen keine blockierenden UI-Pfade erzeugen.

# 7.4 Playlist Refresh

## Synchronisation

Delta-Synchronisation.

## Verhalten

```text
Neu       -> hinzufügen
Geändert -> aktualisieren
Entfernt -> löschen, wenn Teilbereich autoritativ
```

## Entfernte Inhalte

Wenn Inhalte beim Provider nach einem autoritativen Refresh nicht mehr vorhanden sind, werden sie lokal entfernt.

Abhängige lokale Daten für diesen Inhalt werden ebenfalls entfernt:

```text
Favoriten
Playback Progress
Verlauf
```

Wenn ein Refresh fehlschlaegt, abgebrochen wird oder für einen Teilbereich nicht autoritativ ist, bleiben bestehende Inhalte dieses Teilbereichs erhalten.

## Fehlgeschlagene Aktualisierung

Verhalten:

```text
Alte Daten behalten
```

Beispiele:

```text
Server offline
Timeout
DNS Fehler
```

Anzeige:

```text
Status: Verbindungsfehler
```

---

# 7.5 EPG Refresh

## Aktualisierungsintervall

Der globale Standardwert für den automatischen intervallgesteuerten EPG-Refresh betraegt 24 Stunden.

Der Wert wird in DataStore als `epgRefreshIntervalHours = 24` gespeichert.

App-Start-Aktualisierung, Aktualisierung bei Playlist-Änderung und manuelle Aktualisierung bleiben separate Ausloeser. Ist der globale Hauptschalter für Hintergrundaktualisierung deaktiviert, wird kein automatischer Intervall-Refresh ausgefuehrt.

## Speicherung

EPG-Daten werden quellbezogen gespeichert:

```text
EPGSource
-> EPGChannel
-> EPGProgram
```

Provider-Sender erhalten Programme über EPG-Mapping und Provider-EPG-Prioritäten.

EPG-Programme werden nicht als providerbezogene Kopien gespeichert.

EPG-Programme sind Cache-artige Daten und werden im Standard-Backup nicht exportiert.

## Aufbewahrung

V1 verwendet eine globale, sichtbare EPG-Aufbewahrung.

DataStore-Schluessel und Defaults:

```text
epgPastRetentionDays = 1
epgFutureRetentionDays = 7
```

Zulässige Werte:

```text
1 bis 14 Tage
```

Cleanup entfernt EPG-Programmdaten ausserhalb des konfigurierten Fensters.

Cleanup entfernt keine EPG-Quellen, EPG-Kanäle, Provider-EPG-Zuordnungen oder manuellen Mappings.

## Zeitregeln

Alle EPG-Programmzeiten werden intern als UTC-Zeitpunkte gespeichert.

`timeShiftMinutes` der EPG-Quelle wird beim Import angewendet.

Wenn eine Endzeit fehlt, darf das Programm gespeichert werden. Für Now/Next und Tagesansicht darf die Endzeit aus dem nächsten Programmbeginn desselben EPG-Kanals abgeleitet werden. Wenn keine plausible Endzeit ermittelbar ist, ist das Programm für Now/Next nur eingeschraenkt nutzbar.

Überlappende Programme fuehren nicht zum Abbruch des Imports. Für Anzeige und Now/Next gewinnt das plausiblere Zeitfenster. Wenn beide Eintraege gleich plausibel sind, gewinnt der später importierte Datensatz innerhalb derselben Quelle und desselben EPG-Kanals.

Duplikate werden innerhalb derselben EPG-Quelle und desselben EPG-Kanals über `startTimeUtc + normalizedTitle` dedupliziert.

## Mapping und Priorität

Manuelle EPG-Zuordnungen gewinnen immer vor automatischen Zuordnungen.

Automatisches Mapping darf Vorschlaege oder automatische Zuordnungen erzeugen, aber keine manuelle Zuordnung überschreiben.

Wenn mehrere EPG-Quellen für einen Provider Programme liefern, gewinnt die niedrigere Provider-EPG-Priorität.

Now/Next nutzt für einen Sender zuerst die gültige manuelle Zuordnung, sonst die beste automatische Zuordnung entsprechend Provider-EPG-Priorität.

Tagesansicht berechnet lokale Tagesgrenzen in UTC und fragt die quellbezogenen Programme über das Mapping ab.

EPG-Suche laeuft lokal über Titel, Untertitel und Beschreibung. Treffer bleiben in der eigenen Suchgruppe `EPG` und werden nicht mit Sender-, Film- oder Serienergebnissen verschmolzen.

Für Now/Next, Tagesansicht und Suche müssen Indizes auf EPG-Quelle, EPG-Kanal, Startzeit, Endzeit und Titel beziehungsweise normalisiertem Titel vorhanden sein.

## Fehlgeschlagene Aktualisierung

```text
Altes EPG behalten
```

Bei fehlgeschlagenem oder abgebrochenem EPG-Refresh bleiben vorhandene EPG-Programme bis zum normalen Cleanup erhalten.

Wenn ein EPG-Refresh mit Teilfehlern abgeschlossen wird, dürfen valide Programme committed werden. Destruktives Ersetzen vorhandener Programme ist nur für vollstaendig gelesene und validierte EPG-Teilbereiche erlaubt.

---

# 7.6 Logo Refresh

## Standard

```text
Nur geänderte Logos aktualisieren
```

## Optional

```text
Immer alle Logos aktualisieren
```

## Logo Cache Neuaufbau

Unterstuetzt als Wartungsfunktion.

```text
Logo Cache neu aufbauen
```

## Fehlende Logos

Anzeige:

```text
TV Standard Icon
```

---

# 7.7 Medien Cache

## Enthält

```text
Senderlogos
Film Poster
Serien Poster
Staffelbilder
Episodenbilder
```

## Größenlimit

Das Medien-Cache-Limit ist ein interner v1-Wert.

Eine freie Größenwahl oder Cache-Rotation in den Settings ist nicht Teil von v1.

## Cache Informationen

Anzeige unter `Einstellungen > Backup`:

```text
Aktuelle Größe
Dateianzahl
Interne Grenze, falls technisch verfuegbar
```

## Cache Verwaltung

Sichtbare v1-Aktion:

```text
Cache leeren
```

`Cache leeren` entfernt heruntergeladene Medien-Cache-Dateien für Senderlogos, Film-Poster, Serien-Poster, Staffelbilder und Episodenbilder.

Nicht entfernt werden Providerdaten, Favoriten, Verlauf, Wiedergabefortschritt, Suchverlauf, EPG-Zuordnungen, Zugangsdaten, EPG-Programmdaten sowie aktive Stream- oder Timeshift-Puffer.

Medien-Cache-Daten sind nicht Teil des Standard-Backups.

---

# 7.8 Fehlerbehandlung

## Ziel

Fehler dürfen niemals unkontrollierten Datenverlust verursachen.

## Senderstart

Verhalten:

```text
5 Retries
```

Danach:

```text
Fehlerdialog
```

## Streamabbruch

Verhalten:

```text
5 Reconnect-Versuche
```

Danach:

```text
Fehlerdialog
```

## M3U Import

Der verbindliche Parser- und Quellenvertrag liegt in `prd/PRD-v1/12-parser-source-contracts.md`.

Fehlerhafte Eintraege:

```text
Überspringen
Weiter importieren
```

Diagnose:

```text
interne Diagnoseinformationen
```

Diagnoseinformationen dürfen keine geheimen Zugangswerte enthalten.

Toleranter Teilimport ist Standard. Einzelne fehlerhafte M3U-, Xtream- oder XMLTV-Eintraege werden übersprungen, solange verwertbare Eintraege importiert werden können.

Bei Teilfehlern zeigt die App einen zusammenfassenden Status und speichert nur bereinigte technische Zaehler.

## Import- und Refresh-Fehler

Provider- und EPG-Refreshes dürfen keine halb aktualisierte Bibliothek hinterlassen.

Fehler vor Commit behalten alte Daten. Fehler innerhalb der Commit-Transaktion müssen vollstaendig zurückrollen. Fehler nach Commit dürfen nur Cleanup oder technische Metadaten betreffen.

## EPG Fehler

```text
Alte EPG-Daten behalten
```

## Fehlende Poster

Anzeige:

```text
Kein Bild verfuegbar
```

## Fehlende Kategorien

Anzeige:

```text
Nicht kategorisiert
```

Intern:

```text
__UNCATEGORIZED__
```

## Authentifizierungsfehler

Anzeige:

```text
Anmeldedaten ungültig
```

---

# Diagnose und Support

## Sichtbare Einstellungen

```text
Diagnoseprotokollierung: Aus | Ein
Aufbewahrungsdauer: 1 bis 7 Tage
```

Defaults:

```text
Diagnoseprotokollierung: Aus
Aufbewahrungsdauer: 1 Tag
```

Die Aufbewahrungsdauer bleibt bei ausgeschalteter Protokollierung sichtbar, ist dann aber nicht änderbar. Bereits vorhandene Sitzungen bleiben bis zu ihrem regulaeren Ablauf exportierbar.

## Diagnoseprotokollierung

Bei aktivierter Diagnoseprotokollierung können intern bereinigte technische Ereignisse zu Refreshes, Fehlern, Warnungen, Player, Netzwerk, Backup/Restore, Cache und Datenbank geschrieben werden.

Pro App-Prozessstart wird eine neue logische Diagnosesitzung im privaten App-Speicher begonnen. Aktivierung während eines laufenden Prozesses beginnt eine Sitzung zum Aktivierungszeitpunkt.

Jede Sitzung fuehrt mindestens:

```text
sessionId
startedAt
endedAt
lastRecordedAt
endReason
endTimeAccuracy
```

Kontrolliertes Beenden verwendet `USER_EXIT`/`EXACT`; Ausschalten der Diagnoseprotokollierung verwendet `DIAGNOSTICS_DISABLED`/`EXACT`.

Bildschirm-Aus, Standby oder TV-Aus beendet die Sitzung nicht, solange der Prozess weiterlebt. Eine beim nächsten Prozessstart noch offene Sitzung wird ab Android 11/API 30 nach Möglichkeit mit einem passenden `ApplicationExitInfo`-Zeitstempel als `SYSTEM_REPORTED` geschlossen. Fehlt ein passender Systemdatensatz, wird `lastRecordedAt` als geschaetztes Ende mit `endTimeAccuracy=ESTIMATED` verwendet.

## Aufbewahrung

Die sichtbare Aufbewahrungsdauer bietet 1 bis 7 Tage und steht standardmaessig auf 1 Tag.

- geschlossene Sitzungen altern anhand von `endedAt`
- unvollstaendige Sitzungen altern anhand von `lastRecordedAt`
- die aktive Sitzung wird durch Altersbereinigung nie vollstaendig geloescht
- Altersbereinigung erfolgt beim App-Start, nach Sitzungsabschluss und nach einer Verkuerzung der Aufbewahrungsdauer

## Export in v1

Der bereinigte Log-Export ist Bestandteil von v1.

Verbindlicher UI-Ort:

```text
Einstellungen > Über die App > Diagnose und Support
```

Sichtbare Aktion:

```text
Diagnoseprotokoll exportieren
```

Logs sind export-only. Der Inhalt einer Log- oder Diagnoseprotokolldatei darf niemals direkt in der App angezeigt oder in die Zwischenablage kopiert werden.

Die App darf nach dem Export nur Erfolg, Fehler oder Exportziel anzeigen. Logzeilen oder Ausschnitte aus der Exportdatei dürfen dabei nicht wiedergegeben werden.

Technische Ereignisse werden bereits vor dem dauerhaften Schreiben bereinigt. Vor dem Export wird der Ausgabestrom erneut zentral auf geheime Zugangswerte, Tokens, private URLs und vergleichbare sensible Werte geprueft.

## Exportformat

```text
Format: ZIP
MIME-Type: application/zip
Dateiname: vivicast-diagnostics-YYYYMMDD-HHmmss.zip
Verpflichtende Eintraege: vivicast-diagnostics.log, diagnostics-metadata.json
Kodierung beider Eintraege: UTF-8
```

Vivicast erzeugt das ZIP-Archiv selbst mit den standardmaessigen Android-ZIP-APIs. Eine externe ZIP- oder Dateimanager-App ist für die Erstellung nicht erforderlich.

Das Archiv wird direkt in den Ziel-OutputStream geschrieben. Es darf nicht vollstaendig als grosse Bytefolge im RAM aufgebaut werden.

Wenn Erstellen oder Schreiben fehlschlaegt, wird ein konkreter Exportfehler angezeigt. Es erfolgt kein stiller Wechsel auf ein anderes Dateiformat, und eine unvollstaendige Datei darf nicht als erfolgreicher Export gemeldet werden.

## Verbindlicher Exportinhalt

Das ZIP-Archiv enthält verpflichtend:

```text
vivicast-diagnostics.log
diagnostics-metadata.json
```

`vivicast-diagnostics.log` enthält ausschliesslich bereinigte technische Ereignisse zu:

- App-Start, App-Version und Abstuerzen
- Playlist-Importen und -Aktualisierungen mit Beginn, Ende, Ergebnis, Dauer und Anzahl verarbeiteter Eintraege
- EPG-Aktualisierung und Mapping mit Beginn, Ende, Ergebnis, Dauer und Anzahl
- Player-Startfehlern, Retries, Reconnects, Decoder- und Timeshift-Fehlern
- Netzwerkfehlern mit Fehlerart, HTTP-Status und Dauer, jedoch ohne URL
- Backup-, Restore-, Cache- und Datenbankaktionen sowie Fehlern
- bereinigten Stacktraces für Warnungen, Fehler und Abstuerze

`diagnostics-metadata.json` enthält App-/Build-Version, Android-Version, Gerätemodell, Datenbank-Version, Sprache, Zeitzone, Exportzeitpunkt, Aufbewahrungsdauer, feste Größengrenzen, den tatsaechlich abgedeckten Zeitraum sowie Sitzungs- und Trunkierungsmetadaten.

Nicht exportiert werden Zugangswerte, Tokens, Cookies, HTTP-Header, URLs, rohe Playlist-/EPG-Inhalte, Provider- oder Inhaltsnamen, Suchverlauf, Datenbank-Dumps, Screenshots oder ungefiltertes System-Logcat.

Bei Bedarf dürfen neutrale interne IDs als technische Referenz verwendet werden.

## Größenbegrenzung und bytebasierte Rotation

Verbindliche feste v1-Grenzen:

```text
diagnosticsMaxTotalBytes = 20_971_520          // 20 MiB
diagnosticsSegmentMaxBytes = 2_097_152         // 2 MiB
diagnosticsMaxSegmentsPerSession = 3
diagnosticsMaxSessionLogBytes = 6_291_456      // 6 MiB
```

Die Grenzen sind interne Konstanten und nicht benutzerkonfigurierbar.

Eine logische Diagnosesitzung kann aus mehreren physischen Segmenten bestehen. Bevor ein bereinigtes Ereignis die Segmentgrenze überschreiten wuerde, wird das aktuelle Segment geschlossen und ein neues Segment derselben Sitzung geoeffnet.

Sind bereits drei Segmente vorhanden, wird das aelteste geschlossene Segment dieser Sitzung entfernt. Ein einzelnes zu grosses, bereits bereinigtes Ereignis wird passend gekuerzt und mit `recordTruncated=true` markiert.

Das Gesamtlimit umfasst Segmente und zugehoerige Sitzungs-/Segmentmetadaten. Vor größenbedingter Bereinigung laeuft zuerst die Altersbereinigung. Danach werden die aeltesten abgeschlossenen Sitzungen vollstaendig entfernt. Die globale Größenbereinigung entfernt keine Segmente der aktiven Sitzung; deren aelteste geschlossene Segmente werden nur durch die feste Drei-Segment-Rotation ersetzt.

Kann ein Ereignis danach nicht innerhalb der Grenzen geschrieben werden, wird es verworfen und als Kuerzung gezaehlt. Das aktuelle Schreibsegment und die Metadaten der aktiven Sitzung bleiben geschuetzt.

Bei Datenverlust werden mindestens diese Werte fortgeschrieben und exportiert:

```text
contentTruncated
firstRetainedAt
discardedSessionCount
discardedSegmentCount
discardedEventCount
```

Der Export verbindet nur die noch vorhandenen Segmente chronologisch. Sein Zeitraum wird aus dem fruehesten und spaetesten tatsaechlich noch enthaltenen Ereignis bestimmt.

Die vollstaendige Inhalts-, Sitzungs- und Rotationsregel liegt in `prd/PRD-v1/11-about-app-requirements.md`.

Allgemeine Support-Informationen im Bereich `Über die App` dürfen weiterhin bereinigte technische Daten anzeigen und kopieren.

---

# 7.9 Wartung

## Datenbank

Unterstuetzt optional:

```text
Datenbank optimieren
```

Diese Funktion muss nicht zwingend im v1-UI sichtbar sein.

## Cache

Sichtbare v1-Wartung liegt unter `Einstellungen > Speicher & Verlauf`:

```text
Cache leeren
```

Optional für interne oder spätere Support-Wartung:

```text
Logo Cache neu aufbauen
Poster Cache neu aufbauen
```

## IPTV

Unterstuetzt über vorhandene Refresh-Aktionen:

```text
EPG neu herunterladen
Playlist komplett neu einlesen
```

---

# 7.10 Statistik

Anzeige, falls Support- oder Debugansicht vorhanden ist:

```text
Provider
Sender
Filme
Serien
EPG Eintraege
Größe des Medien-Caches
Datenbankgröße
Favoriten
Verlaufseintraege
```

Diese Werte dürfen in Support-Informationen nur ohne geheime Details angezeigt werden.

---

# 7.11 Performance Anforderungen

## Zielgrößen

```text
10.000+ Sender
50.000+ Filme
20.000+ Serien
Millionen EPG Eintraegen
```

Messbare v1-Budgets für Import, Suche, Datenbank, EPG und Speicherverbrauch liegen in `prd/PRD-v1/13-test-strategy.md`.

Dieses Kapitel definiert Laufzeitverhalten und Performanceanforderungen. Kapitel 13 definiert die reproduzierbare Messung.

## Lazy Rendering

Pflicht.

Senderlisten:

```text
LazyColumn
```

Filme:

```text
LazyGrid
```

Serien:

```text
LazyGrid
```

## Suche

Debounce:

```text
300 ms
```

Datenquelle:

```text
Room FTS4
```

Suche erfolgt ausschliesslich lokal.

Die interne Suche verwendet getrennte FTS-Indexe für:

```text
Kanäle
Filme
Serien
EPG
```

Episoden sind keine eigene Suchgruppe.

Leere Suche fuehrt keine Ergebnisabfrage aus.

Bei einem normalisierten Zeichen durchsucht Vivicast nur Titel- oder Name-Prefixe für Kanäle, Filme und Serien.

EPG-Suche startet erst ab drei normalisierten Zeichen.

Jede Ergebnisgruppe liefert maximal 20 Treffer.

FTS-Indexpflege laeuft als Teil der produktiven Commit-Pfade:

- Provider-Refresh aktualisiert Channel-, Movie- und Series-FTS im selben atomaren Provider-Commit.
- EPG-Refresh aktualisiert EPG-FTS im selben atomaren EPG-Quellen-Commit.
- EPG-Cleanup entfernt zugehoerige EPG-FTS-Eintraege.
- Restore und Migration loesen einen FTS-Rebuild aus, wenn suchrelevante produktive Daten ersetzt oder verändert wurden.

FTS-Rebuilds dürfen die UI nicht blockieren. Wenn ein Rebuild nach Restore oder Migration noch laeuft, darf die Suche einen Loading- oder temporaeren Error-Zustand anzeigen, aber keine Staging-Daten durchsuchen.

## Channel Zapping Optimierung

Wenn mehrere Sender schnell ausgewählt werden:

```text
Nur letzte Auswahl starten
```

Vorherige Startvorgaenge werden abgebrochen.

---

# Speicherziel

Die App soll auch auf schwaecheren Android-TV-Geräten fluessig bedienbar bleiben.
