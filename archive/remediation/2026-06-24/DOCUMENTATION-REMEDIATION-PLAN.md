# Vivicast Documentation Remediation Plan

Status: abgeschlossen v31

Historischer Hinweis: Dieser Plan dokumentiert die abgeschlossene Remediation bis Paket N. Offene Restarbeiten aus dem Deep-Research-Follow-up werden ab dem 2026-06-24 im Folgeplan `DOCUMENTATION-REMEDIATION-PLAN-2026-06-24.md` gefuehrt. Historische Treffer in dieser Datei werden nicht flaechig umgeschrieben.

## Einordnung

Diese Datei ist ein menschlicher Arbeitsplan fuer die weitere Pflege von `Spegeli/vivicast-docs`.

Sie ist keine App-Anforderung, keine Architekturentscheidung, keine Designquelle und keine feste Umsetzungsreihenfolge fuer Codex. Codex plant die App-Umsetzung weiterhin selbst im App-Repository.

## Grundlage

Der Plan konsolidiert:

- einen unabhaengigen Fresh-Developer-Audit
- den vom Owner bereitgestellten Claude-Korrekturbericht Rev. 2
- den aktuellen Stand auf `main`
- bereits dokumentierte Owner-Entscheidungen

## Arbeitsweise

Jedes Paket wird einzeln mit dem Owner besprochen.

Pro Paket:

1. offene Entscheidungen klaeren
2. kanonische Hauptquelle festlegen
3. betroffene Dateien aktualisieren
4. veraltete Wiederholungen entfernen oder auf die Hauptquelle verweisen
5. Querverweise pruefen
6. Statuslabels und Changelog aktualisieren
7. Paket abschliessen

---

# 1. Bereits entschiedene Punkte

## D-01 Suche

- Genau vier Ergebnisgruppen
- Kanaele, Filme, Serien, EPG
- Keine eigene Episoden-Gruppe
- Episoden bleiben auf der Serien-Detailseite

Entschieden am 2026-06-22: sichtbarer Gruppenname `Kanaele`, technischer Begriff `Channel`.

## D-02 Log-Export

- Bereinigter Log-Export ist Bestandteil von v1
- Verbindlicher UI-Ort: `Einstellungen > Ueber die App > Diagnose und Support`
- Logs sind export-only
- Der Inhalt einer Log- oder Diagnoseprotokolldatei darf niemals direkt in der App angezeigt werden
- Logdatei-Inhalte duerfen nicht in die Zwischenablage kopiert werden
- Nach Export duerfen nur Status, Fehler oder Exportziel angezeigt werden
- Exportformat: standardkonformes ZIP-Archiv
- MIME-Type: `application/zip`
- Dateiname: `vivicast-diagnostics-YYYYMMDD-HHmmss.zip`
- verpflichtende UTF-8-Eintraege: `vivicast-diagnostics.log` und `diagnostics-metadata.json`
- `vivicast-diagnostics.log` enthaelt ausschliesslich bereinigte technische Ereignisse zu App-Start und Absturz, Playlist-/EPG-Aktualisierung, Player, Netzwerk, Backup/Restore, Cache und Datenbank
- `diagnostics-metadata.json` enthaelt technische Exportmetadaten wie App-/Build-Version, Android-Version, Geraetemodell, Datenbank-Version, Sprache, Zeitzone, Exportzeitpunkt und abgedeckten Protokollzeitraum
- sensible Werte, URLs, rohe Providerdaten, Inhaltsnamen, Suchverlauf, Datenbank-Dumps, Screenshots und ungefiltertes System-Logcat sind ausgeschlossen
- neutrale interne IDs duerfen bei Bedarf als technische Referenzen verwendet werden
- Vivicast erzeugt und schreibt das ZIP-Archiv selbst als Stream; eine externe ZIP-App ist nicht erforderlich
- Diagnoseprotokollierung ist manuell ein- und ausschaltbar; Standard ist `Aus`
- Aufbewahrungsdauer ist 1 bis 7 Tage; Standard ist 1 Tag
- pro App-Prozessstart wird bei aktiver Diagnoseprotokollierung eine neue logische Sitzung im privaten App-Speicher begonnen
- jede Sitzung fuehrt `sessionId`, `startedAt`, `endedAt`, `lastRecordedAt`, `endReason` und `endTimeAccuracy`
- nicht sauber beendete Sitzungen werden beim naechsten App-Start anhand von `ApplicationExitInfo` ab Android 11 oder ersatzweise anhand von `lastRecordedAt` abgeschlossen
- vorhandene Sitzungen bleiben bei deaktivierter Protokollierung bis zum Ablauf der Aufbewahrungsdauer exportierbar
- automatische Altersbereinigung erfolgt beim App-Start, nach Sitzungsabschluss und nach einer Verkuerzung der Aufbewahrungsdauer; die aktive Sitzung wird nie vollstaendig geloescht
- interne Diagnosedaten sind fest auf 20 MiB beziehungsweise 20.971.520 Bytes begrenzt
- jedes physische Segment ist auf 2 MiB beziehungsweise 2.097.152 Bytes begrenzt
- pro logischer Sitzung werden hoechstens drei Segmente beziehungsweise 6 MiB Logdaten aufbewahrt
- bei sitzungsinterner Rotation wird das aelteste geschlossene Segment der Sitzung entfernt
- bei Erreichen des Gesamtlimits werden nach der Altersbereinigung zuerst die aeltesten abgeschlossenen Sitzungen entfernt
- das aktuelle Schreibsegment und die Metadaten der aktiven Sitzung bleiben erhalten
- Groessenlimit und Rotation sind feste interne v1-Werte und keine sichtbaren Einstellungen
- entfernte oder gekuerzte Daten werden in `diagnostics-metadata.json` ausgewiesen; der Exportzeitraum basiert nur auf tatsaechlich noch enthaltenen Ereignissen

O-03 ist vollstaendig entschieden und dokumentiert.

## D-03 Timeshift

- Timeshift ist ein- und ausschaltbar; Standard ist `Ein`
- maximale Dauer: 15, 30, 60 oder 120 Minuten; Standard sind 30 Minuten
- Speicher: Automatisch, RAM oder Festplatte; Standard ist Automatisch
- Festplatte nutzt appverwalteten persistenten Geraetespeicher ohne freie Pfadauswahl

Entschieden und dokumentiert am 2026-06-22.

## D-04 PIN-Eingabe

- Android-Systemtastatur im Ziffernmodus
- Kein eigener Ziffernblock
- Anzeige maskiert

## D-05 EPG-Standardintervall

- Das globale EPG-Aktualisierungsintervall hat den Standardwert 24 Stunden.
- Der Wert wird in DataStore als `epgRefreshIntervalHours = 24` gespeichert.
- App-Start, Playlist-Aenderung und manuelle Aktualisierung bleiben separate Ausloeser.

Entschieden und dokumentiert am 2026-06-23.

## D-06 Startbereich

- Der Startbereich ist eine globale Auswahl unter `Einstellungen > Allgemein`.
- Sichtbare Werte sind Home, Live-TV, Filme und Serien.
- Standardwert und DataStore-Default sind Home beziehungsweise `startDestination = HOME`.
- Die Auswahl gilt ab dem naechsten regulaeren App-Start ohne explizites Ziel und navigiert die laufende Sitzung nicht sofort um.
- App-Autostart verwendet denselben Startbereich; Deep Links und andere explizite Android-TV-Ziele haben Vorrang.
- Rueckkehr aus dem Hintergrund behaelt den aktuellen Kontext.
- Bei fehlenden Inhalten erscheint der normale Empty State des gewaehlten Bereichs; es gibt keinen stillen Fallback.

Entschieden und dokumentiert am 2026-06-23.

## D-07 Live-TV-Preview

- Der Live-TV-Startablauf ist fest und nicht konfigurierbar.
- Beim blossen Fokussieren eines Senders wird kein Stream gestartet.
- Erstes OK in der Senderspalte aktiviert den Sender-Modus, blendet Provider/Kategorien aus, oeffnet die EPG-Spalte und startet gleichzeitig die Live-Vorschau rechts.
- Danach springt der Fokus auf die aktuelle Sendung in der EPG-Spalte, sofern eine aktuelle EPG-Sendung vorhanden ist.
- Zweites OK auf der fokussierten aktuellen Sendung oeffnet die Vollbildwiedergabe des ausgewaehlten Senders.
- Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK in der Senderspalte.

Entschieden und dokumentiert am 2026-06-23.

## D-08 Auto-Next

- Auto-Next ist eine globale Wiedergabe-Einstellung fuer Serienepisoden im internen Vivicast-Player.
- `Automatisch naechste Folge` ist ein Toggle; Standard ist `Aus`.
- `Countdown naechste Folge` ist eine Auswahl mit 5, 10, 15 oder 30 Sekunden; Standard sind 10 Sekunden.
- Die Countdown-Auswahl bleibt bei deaktiviertem Auto-Next sichtbar, ist aber nicht bedienbar.
- Bei deaktiviertem Auto-Next erscheint erst nach dem tatsaechlichen Episodenende der fokussierte Hauptbutton `Naechste Folge abspielen`; ohne Nutzeraktion startet nichts automatisch.
- Bei aktiviertem Auto-Next erscheint der fokussierte Hauptbutton `Naechste Folge in X` genau um den konfigurierten Zeitraum vor dem tatsaechlichen Episodenende. Der Wert wird sekundenweise aktualisiert.
- Ein OK auf dem Hauptbutton startet die naechste Episode sofort. Ohne Eingabe startet sie beim Ablauf des Countdowns am tatsaechlichen Episodenende; die letzten Sekunden werden nicht automatisch uebersprungen.
- Der sichtbare Button `Zurueck` erscheint in beiden Zustaenden zeitgleich neben dem Hauptbutton; einen Button `Abbrechen` gibt es im Auto-Next-Panel nicht.
- OK auf `Zurueck` oder die Zurueck-Taste beendet den Auto-Next-Ablauf, verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit dem zuvor gewaehlten Staffel-/Episodenkontext zurueck.
- Nach der letzten Episode einer Staffel folgt die erste verfuegbare Episode der naechsten Staffel. Nach der letzten Episode der Serie erscheint kein Auto-Next-Panel.
- DataStore verwendet `autoNextEpisodeEnabled = false` und `autoNextEpisodeCountdownSeconds = 10`; zulaessige Countdown-Werte sind 5, 10, 15 und 30.
- Externe Player sind durch O-11 von Auto-Next und automatischer Fortschrittsrueckgabe abgegrenzt.

Entschieden und dokumentiert am 2026-06-23.

## D-09 Abschluss-Schwelle

- Filme und einzelne Serienepisoden gelten ab einem Wiedergabefortschritt von mindestens 95 Prozent als abgeschlossen.
- Der Wert ist fuer v1 fest und wird nicht als Einstellung angeboten.
- Ein tatsaechliches Medienende setzt den Inhalt unabhaengig von berechenbarer Dauer oder Prozentwert immer auf abgeschlossen.
- Abgeschlossene Filme und Episoden erhalten `isCompleted = true` und sind keine direkten Fortsetzen-Ziele; bei Serien darf der Serien-Eintrag auf die naechste Episode bei Position 0 wechseln.
- Das Erreichen der 95-Prozent-Schwelle beendet die laufende Wiedergabe nicht und loest weder das Auto-Next-Panel noch einen Episodenwechsel aus; Auto-Next richtet sich weiterhin nach dem tatsaechlichen Medienende.
- Filme und einzelne Episoden koennen manuell als gesehen oder ungesehen markiert werden.
- `Als gesehen markieren` setzt den Abschlussstatus; `Als ungesehen markieren` loescht den zugehoerigen gespeicherten Wiedergabefortschritt vollstaendig.
- Fuer komplette Staffeln oder Serien gibt es in v1 keine manuelle Gesehen-/Ungesehen-Aktion und keinen eigenen Abschlussdatensatz.

Entschieden und dokumentiert am 2026-06-23.

## D-10 Cache und Verlauf

- Backup zeigt die aktuelle Groesse des Medien-Caches.
- `Cache leeren` ist eine sichtbare Wartungsaktion.
- Die Groesse des Medien-Caches und die Cache-Rotation sind in v1 nicht frei konfigurierbar.
- Medien-Cache-Dateien fuer Senderlogos, Film-Poster, Serien-Poster, Staffelbilder und Episodenbilder sind nicht Teil des Standard-Backups.
- Verlauf kann fuer Live-TV, Filme, Serien, Suche oder komplett geloescht werden.
- Filmverlauf-Loeschung umfasst Film-Wiedergabefortschritt.
- Serienverlauf-Loeschung umfasst Episoden-Wiedergabefortschritt.
- Freie Verlaufslimits werden in v1 nicht angeboten.
- Suchverlauf bleibt fest auf maximal 20 Eintraege begrenzt.

Entschieden und dokumentiert am 2026-06-23.

## D-11 Kindersicherung nach Restore

- Wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN fuer Backup/Restore verlangt, muss vor dem Einspielen eines Backups die aktuell lokale PIN bestaetigt werden.
- Eine in der Backup-Datei enthaltene fruehere PIN oder fruehere Schutzkonfiguration darf fuer diese Autorisierung nicht verwendet werden.
- PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags aus Backups werden nicht wiederhergestellt.
- Nach Restore ist Kindersicherung deaktiviert.
- Wenn die Backup-Datei ausweist, dass Kindersicherung beim Export aktiv war, zeigt die App nach dem Restore einen Hinweis, dass die PIN-Funktion vor dem Backup aktiv war, nach dem Restore deaktiviert wurde und unter `Einstellungen > Kindersicherung` manuell wieder aktiviert werden muss.

Entschieden und dokumentiert am 2026-06-23.

## D-12 Externer Player und Progress

- Der interne Vivicast-Player ist in v1 die einzige verlaessliche Quelle fuer automatische VOD-Position, Dauer, Fortschritt, Medienende, Abschlussstatus und Auto-Next.
- Externe Player erzeugen und aktualisieren keinen `PlaybackProgressEntity`-Datensatz.
- Rueckgabewerte externer Player werden nicht als Position, Dauer, Fortschritt oder Medienende uebernommen.
- Vorhandener Fortschritt bleibt nach externer Wiedergabe unveraendert.
- Wenn vorher kein Fortschritt existierte, wird durch externe Wiedergabe kein neuer Fortschritt erzeugt.
- Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.
- Manuelle Aktionen `Als gesehen markieren` und `Als ungesehen markieren` bleiben verfuegbar.
- Auto-Next gilt nur im internen Vivicast-Player; externe Player zeigen kein Auto-Next-Panel und loesen keinen automatischen Episodenwechsel aus.

Entschieden und dokumentiert am 2026-06-23.

---

# 2. Owner-Entscheidungen

Diese Punkte werden einzeln entschieden und nach der konsistenten Dokumentationsaktualisierung als umgesetzt markiert.

## O-01 Suchgruppenname - entschieden und umgesetzt

- Entscheidung: sichtbarer Name `Kanaele`
- Technischer Begriff: `Channel`
- Umgesetzt am 2026-06-22 in den betroffenen Search-Spezifikationen, Akzeptanzkriterien und im Changelog
- Die separat geplante Entfernung der Episoden-Gruppe ist durch B-07 und Paket H umgesetzt.

## O-02 Timeshift-Umfang - entschieden und umgesetzt

- Timeshift: Ein/Aus; Standard Ein
- maximale Dauer: 15/30/60/120 Minuten; Standard 30 Minuten
- Speicher: Automatisch/RAM/Festplatte; Standard Automatisch
- Festplatte: appverwalteter persistenter Geraetespeicher ohne freie Pfadauswahl
- Dauer und Speicher bleiben bei Timeshift Aus sichtbar, aber deaktiviert
- Umgesetzt am 2026-06-22 in ADR, PRD, DataStore-Vertrag, Settings-Screen, Wireframe, Komponenten, Mockup-Spec, Player-Referenzen, Akzeptanzkriterien und QA

## O-03 Bereinigter Log-Export - entschieden und umgesetzt

### O-03a Ort und Anzeigeverhalten - entschieden und dokumentiert

- UI-Ort: `Einstellungen > Ueber die App > Diagnose und Support`
- sichtbare Aktion: `Diagnoseprotokoll exportieren`
- Logdatei-Inhalt wird niemals in der App angezeigt
- keine Logvorschau, kein Logtext-Panel und kein Kopieren von Logdatei-Inhalten
- allgemeine nicht-private Support-Informationen duerfen weiterhin angezeigt und kopiert werden
- Exportstatus darf nur Erfolg, Fehler oder Exportziel zeigen

### O-03b Dateiformat - entschieden und dokumentiert

- Format: ZIP
- MIME-Type: `application/zip`
- Dateiname: `vivicast-diagnostics-YYYYMMDD-HHmmss.zip`
- verpflichtende ZIP-Eintraege gemaess O-03c1: `vivicast-diagnostics.log` und `diagnostics-metadata.json`
- ZIP wird mit den standardmaessigen Android-ZIP-APIs von Vivicast selbst erzeugt
- Archiv wird direkt in den Ziel-OutputStream geschrieben und nicht vollstaendig im RAM aufgebaut
- keine Abhaengigkeit von einer externen ZIP- oder Dateimanager-App
- bei Fehler kein stiller Wechsel auf ein anderes Format
- Grundlage: ZIP-Schreibunterstuetzung ist Bestandteil der Android-Plattform seit API-Level 1; Android-kompatible Geraete muessen die SDK-APIs bereitstellen

### O-03c Exportumfang - entschieden und umgesetzt

#### O-03c1 Inhalt - entschieden und dokumentiert

Verpflichtende ZIP-Eintraege:

- `vivicast-diagnostics.log` in UTF-8
- `diagnostics-metadata.json` in UTF-8/JSON

`vivicast-diagnostics.log` enthaelt ausschliesslich bereinigte technische Ereignisse:

- App-Start, App-Version und Abstuerze
- Playlist-Importe und Aktualisierungen mit Beginn, Ende, Ergebnis, Dauer und Anzahl verarbeiteter Eintraege
- EPG-Aktualisierung und Mapping mit Beginn, Ende, Ergebnis, Dauer und Anzahl
- Player-Startfehler, Retries, Reconnects, Decoder- und Timeshift-Fehler
- Netzwerkfehler mit Fehlerart, HTTP-Status und Dauer, jedoch ohne URL
- Backup-, Restore-, Cache- und Datenbankaktionen sowie zugehoerige Fehler
- bereinigte Stacktraces fuer Warnungen, Fehler und Abstuerze

`diagnostics-metadata.json` enthaelt:

- App-Version und Build-Nummer
- Android-Version und Geraetemodell
- Datenbank-Version
- aktive App-Sprache und Zeitzone
- Exportzeitpunkt
- Beginn und Ende des abgedeckten Protokollzeitraums

Nicht enthalten:

- Benutzername, Passwort, PIN oder Backup-Passphrase
- Tokens, Cookies, HTTP-Header oder sonstige Zugangsdaten
- Server-, M3U-, EPG- oder Stream-URLs
- rohe M3U-, XMLTV-, Playlist- oder EPG-Inhalte
- Provider-Namen, Sendernamen, Film-/Serientitel oder Suchverlauf
- Datenbank-Dumps, Screenshots oder ungefiltertes System-Logcat

Bei Bedarf duerfen nur neutrale interne IDs wie `providerId=3` oder `channelId=128` als technische Referenz verwendet werden.

#### O-03c2 Zeitraum - entschieden und dokumentiert

- sichtbare Einstellung `Diagnoseprotokollierung`: Ein/Aus; Standard `Aus`
- sichtbare Einstellung `Aufbewahrungsdauer`: 1 bis 7 Tage; Standard 1 Tag
- die Aufbewahrungsdauer bleibt bei ausgeschalteter Diagnoseprotokollierung sichtbar, ist dann aber nicht aenderbar
- vorhandene Sitzungen bleiben nach dem Ausschalten bis zum regulaeren Ablauf der Aufbewahrungsdauer exportierbar
- bei aktivierter Diagnoseprotokollierung beginnt pro App-Prozessstart eine neue logische Sitzung im privaten App-Speicher
- wird die Diagnoseprotokollierung in einem laufenden Prozess aktiviert, beginnt die Sitzung zum Aktivierungszeitpunkt
- die internen Segmentdateien enthalten Startzeitpunkt, stabile `sessionId` und Segmentnummer; die Endzeit wird in Metadaten gefuehrt und nicht durch eine spaetere Dateiumbenennung erzwungen
- jede Sitzung fuehrt `sessionId`, `startedAt`, `endedAt`, `lastRecordedAt`, `endReason` und `endTimeAccuracy`
- kontrolliertes Beenden verwendet `endReason=USER_EXIT` und `endTimeAccuracy=EXACT`
- Ausschalten der Diagnoseprotokollierung beendet die aktive Sitzung mit `endReason=DIAGNOSTICS_DISABLED` und `endTimeAccuracy=EXACT`
- Bildschirm-Aus, Standby oder TV-Aus beendet eine Sitzung nicht kuenstlich, solange der App-Prozess weiterlebt
- eine beim naechsten App-Prozessstart noch offene Sitzung wird nachtraeglich abgeschlossen
- ab Android 11/API 30 wird dafuer, wenn passend vorhanden, `ApplicationExitInfo` mit dessen Systemzeitpunkt verwendet; `endTimeAccuracy=SYSTEM_REPORTED`
- ohne passenden Systemdatensatz wird `endedAt=lastRecordedAt` und `endTimeAccuracy=ESTIMATED` verwendet
- erlaubte Abschlussgruende sind `USER_EXIT`, `DIAGNOSTICS_DISABLED`, `CRASH`, `SYSTEM_KILL` und `UNKNOWN`
- bei einer aktiven Sitzung bleibt `endedAt` im Export leer; `lastRecordedAt` dient als aktueller Endpunkt und `active=true` wird ausgewiesen
- geschlossene Sitzungen altern anhand von `endedAt`, unvollstaendige anhand von `lastRecordedAt`
- Altersbereinigung laeuft beim App-Start, nach Sitzungsabschluss und unmittelbar nach einer Verkuerzung der Aufbewahrungsdauer
- die aktive Sitzung wird durch Altersbereinigung niemals geloescht
- der Export umfasst alle noch vorhandenen Diagnosedaten; sein Zeitraum wird aus den tatsaechlich noch enthaltenen Ereignissen und nicht pauschal aus der konfigurierten Tageszahl bestimmt
- umgesetzt am 2026-06-23 in PRD, DataStore-/Dateivertrag, Settings, About-App-Komponenten, Wireframe, Coding Rules, Akzeptanzkriterien und QA

#### O-03c3 Groessenbegrenzung und Rotation - entschieden und dokumentiert

- festes Gesamtlimit fuer alle internen Diagnosedaten: 20 MiB beziehungsweise 20.971.520 Bytes
- festes Limit pro physischem Logsegment: 2 MiB beziehungsweise 2.097.152 Bytes
- hoechstens drei gleichzeitig aufbewahrte Segmente pro logischer Sitzung; damit maximal 6 MiB beziehungsweise 6.291.456 Bytes Logdaten pro Sitzung
- die Werte sind in v1 nicht benutzerkonfigurierbar und erzeugen keine weitere Settings-Zeile
- eine logische Diagnosesitzung kann aus fortlaufend nummerierten Segmenten bestehen
- vor einem Schreibvorgang, der die Segmentgrenze ueberschreiten wuerde, wird das aktuelle Segment geschlossen und innerhalb derselben Sitzung ein neues Segment angelegt
- sind bereits drei Segmente der Sitzung vorhanden, wird vor dem Anlegen des naechsten Segments das aelteste geschlossene Segment dieser Sitzung entfernt
- ein einzelnes bereits bereinigtes Ereignis, das selbst nicht in ein leeres Segment passt, wird passend gekuerzt und mit `recordTruncated=true` gekennzeichnet
- das Gesamtlimit umfasst Logsegmente und zugehoerige Sitzungs-/Segmentmetadaten im privaten App-Speicher; der direkt in das Exportziel gestreamte ZIP-Export zaehlt nicht dazu
- bei erforderlicher Groessenbereinigung wird zuerst die altersbasierte Aufbewahrungsbereinigung ausgefuehrt
- reicht der Platz danach nicht aus, werden die aeltesten abgeschlossenen Sitzungen vollstaendig entfernt
- bei globaler Groessenbereinigung werden keine Segmente der aktiven Sitzung entfernt; deren aelteste geschlossene Segmente werden nur durch die feste Drei-Segment-Rotation ersetzt
- passt ein Schreibvorgang trotz Altersbereinigung, abgeschlossener Sitzungsloeschung und sitzungsinterner Rotation nicht innerhalb der Grenzen, wird das Ereignis verworfen und als Kuerzung gezaehlt
- das aktuelle Schreibsegment und die Metadaten der aktiven Sitzung werden niemals entfernt
- der Export verbindet nur die noch vorhandenen Segmente in chronologischer Reihenfolge zu `vivicast-diagnostics.log`
- `diagnostics-metadata.json` enthaelt die festen Grenzwerte sowie `contentTruncated`, `firstRetainedAt`, `discardedSessionCount`, `discardedSegmentCount` und `discardedEventCount`
- der ausgewiesene Protokollzeitraum beginnt beim fruehesten tatsaechlich noch exportierten Ereignis und nicht zwingend bei `startedAt` der aeltesten Sitzung
- umgesetzt am 2026-06-23 in PRD, DataStore-/Dateivertrag, Settings-Referenzen, About-App-Komponenten, Wireframe, Coding Rules, Akzeptanzkriterien und QA

## O-04 EPG-Standardintervall - entschieden und umgesetzt

- Entscheidung: Standardwert 24 Stunden
- gilt global fuer den automatischen intervallgesteuerten EPG-Refresh
- Speicherung in DataStore als `epgRefreshIntervalHours = 24`
- App-Start-, Playlist-Aenderungs- und manuelle Aktualisierung bleiben separate Ausloeser
- umgesetzt am 2026-06-23 in PRD, DataStore, Hintergrundjob-Vertrag, Settings-Screen, Wireframe, Mockup-Spec, Komponenten, Coding Rules, Akzeptanzkriterien und QA

## O-05 Startbereich - entschieden und umgesetzt

- globale Select-Row unter `Einstellungen > Allgemein`
- sichtbare Werte: Home, Live-TV, Filme, Serien
- technischer DataStore-Wert: `startDestination = HOME | LIVE_TV | MOVIES | SERIES`
- Standardwert und Fallback bei ungueltigem Wert: `HOME`
- Aenderungen gelten ab dem naechsten regulaeren App-Start ohne explizites Ziel und navigieren die laufende Sitzung nicht sofort um
- App-Autostart verwendet den konfigurierten Startbereich
- Deep Links, Android-TV-Suche, Watch Next und andere explizite Ziele haben Vorrang
- Rueckkehr aus dem Hintergrund behaelt den aktuellen Kontext
- bei fehlenden Inhalten wird der normale Empty State des gewaehlten Bereichs gezeigt; es gibt keinen automatischen Fallback auf Home
- Suche und Einstellungen sind bewusst keine waehbaren Startbereiche
- umgesetzt am 2026-06-23 in Produktuebersicht, Settings-PRD, DataStore, Navigationsreferenz, Settings-Screen, Wireframe, Mockup-Spec, Komponenten, Coding Rules, Akzeptanzkriterien und QA

## O-06 Live-TV-Preview - entschieden und umgesetzt

- fester Ablauf ohne sichtbare oder interne Nutzeroption
- beim blossen Senderfokus wird kein Stream gestartet
- erstes OK in der Senderspalte aktiviert den Sender-Modus, blendet Provider/Kategorien aus, oeffnet die EPG-Spalte und startet gleichzeitig die Live-Vorschau
- der Fokus springt danach auf die aktuelle Sendung in der EPG-Spalte, sofern vorhanden
- zweites OK auf der fokussierten aktuellen Sendung oeffnet die Vollbildwiedergabe
- kein direkter Vollbildstart beim ersten OK und keine Preview-Einstellung in den Settings
- Suchdokumente definieren keinen abweichenden Preview-Ablauf, sondern verweisen auf den Live-TV-Browser
- umgesetzt am 2026-06-23 in PRD, Live-TV-Screen, Komponenten, Wireframe, Interaction Spec, Design-System, Mockup- und Owner-Entscheidungen, Suche, Coding Rules, Akzeptanzkriterien und QA

## O-07 Auto-Next - entschieden und umgesetzt

- globale Wiedergabe-Einstellung fuer Serienepisoden im internen Vivicast-Player
- Toggle `Automatisch naechste Folge`; Standard `Aus`
- Auswahl `Countdown naechste Folge`; 5/10/15/30 Sekunden, Standard 10 Sekunden
- Countdown-Auswahl bleibt bei `Aus` sichtbar, aber deaktiviert
- bei `Aus`: erst nach dem tatsaechlichen Episodenende Hauptbutton `Naechste Folge abspielen`, kein automatischer Start
- bei `Ein`: Hauptbutton `Naechste Folge in X` erscheint X Sekunden vor dem tatsaechlichen Episodenende; OK startet sofort, Ablauf startet am Episodenende automatisch
- sichtbarer Button `Zurueck` erscheint zeitgleich neben dem Hauptbutton; kein Button `Abbrechen`
- OK auf `Zurueck` oder die Zurueck-Taste verwirft den Ablauf und fuehrt zur Serien-Detailseite mit dem zuvor gewaehlten Staffel-/Episodenkontext zurueck
- Staffelwechsel zur ersten verfuegbaren Episode der naechsten Staffel; kein Panel nach dem Serienende
- DataStore: `autoNextEpisodeEnabled = false`, `autoNextEpisodeCountdownSeconds = 10`
- externe Player sind durch O-11 von Auto-Next abgegrenzt
- umgesetzt am 2026-06-23 in Serien- und Player-PRD, Settings-/DataStore-Vertrag, Screens, Komponenten, Wireframes, Design-System, Mockup-Spezifikationen, Coding Rules, Akzeptanzkriterien und QA

## O-08 Abschluss-Schwelle - entschieden und umgesetzt

- fester v1-Wert von 95 Prozent; keine sichtbare Einstellung und kein DataStore-Schluessel
- gilt fuer Filme und einzelne Serienepisoden
- bei mindestens 95 Prozent oder beim tatsaechlichen Medienende wird `isCompleted = true` gesetzt
- abgeschlossene Filme und Episoden werden als direkte Resume-Ziele aus Home-, Film- und Serien-Fortsetzen entfernt; ein Serien-Eintrag darf auf die naechste Episode bei Position 0 wechseln
- die 95-Prozent-Schwelle beendet die Wiedergabe nicht und loest kein Auto-Next aus
- Filme und einzelne Episoden bieten `Als gesehen markieren` beziehungsweise `Als ungesehen markieren`
- `Als ungesehen markieren` loescht den vollstaendigen zugehoerigen `PlaybackProgressEntity`-Datensatz
- keine manuelle Markierung oder eigene Abschlussentitaet fuer komplette Staffeln oder Serien in v1
- umgesetzt am 2026-06-23 in VOD-, Player- und Android-TV-PRD, Continue-Watching-Vertrag, Datenmodell, ADR-008, Screens, Komponenten, Wireframes, Design-System, Mockup-Spezifikationen, High-Fidelity-Owner-/UI-Richtungsdokumenten, Coding Rules, Akzeptanzkriterien und QA

## O-09 Cache und Verlauf - entschieden und umgesetzt

- Backup zeigt Medien-Cache-Informationen, mindestens die aktuelle Groesse.
- `Cache leeren` ist sichtbar und loescht nur Medien-Cache-Dateien.
- Die Groesse des Medien-Caches und die Cache-Rotation sind keine frei konfigurierbaren v1-Einstellungen.
- Medien-Cache-Dateien sind nicht Teil des Standard-Backups.
- Verlauf kann fuer Live-TV, Filme, Serien, Suche oder komplett geloescht werden.
- Film- und Serienverlauf-Loeschung umfassen den jeweiligen Wiedergabefortschritt.
- Freie Verlaufslimits werden nicht angeboten.
- Suchverlauf bleibt fest auf maximal 20 Eintraege begrenzt.
- umgesetzt am 2026-06-23 in PRD, Datenmodell, Cache-/Hintergrundjob-Vertrag, Settings, Wireframe, Komponenten, Coding Rules, Akzeptanzkriterien, QA und Changelog

## O-10 Kindersicherung nach Standard-Restore - entschieden und umgesetzt

- Restore nutzt fuer eine erforderliche PIN-Abfrage immer die aktuell lokale PIN, wenn lokal Einstellungsschutz aktiv ist oder Backup/Restore per PIN geschuetzt ist.
- Backup-PIN und Backup-Schutzkonfiguration duerfen keine Restore-Autorisierung erteilen.
- PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus Backups werden nicht uebernommen.
- Nach Restore ist Kindersicherung deaktiviert.
- Wenn Kindersicherung beim Export aktiv war, zeigt die App einen Hinweis zur manuellen Reaktivierung unter `Einstellungen > Kindersicherung`.
- umgesetzt am 2026-06-23 in Backup-PRD, Settings-PRD, Datenmodell, ADR-004, Settings-Screen, Wireframe, Komponenten, Coding Rules, Akzeptanzkriterien, QA und Changelog

## O-11 Externer Player und Progress - entschieden und umgesetzt

- Interner Vivicast-Player ist die einzige verlaessliche Quelle fuer automatische VOD-Position, Dauer, Fortschritt, Medienende, Abschlussstatus und Auto-Next.
- Externe Player schreiben in v1 keinen automatischen Fortschritt zurueck.
- Externe Player erzeugen oder aktualisieren keinen `PlaybackProgressEntity`-Datensatz.
- Externe Player setzen keinen Abschlussstatus.
- Vorhandener Fortschritt bleibt nach externer Wiedergabe unveraendert.
- Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.
- Manuelle Aktionen `Als gesehen markieren` und `Als ungesehen markieren` bleiben verfuegbar.
- Auto-Next gilt nicht fuer externe Player.
- umgesetzt am 2026-06-23 in VOD-/Player-PRD, Settings-PRD, Datenmodell, Player-Screen, Settings-Screen, Komponenten, Wireframes, Design-System, Mockup-Spezifikation, Coding Rules, Akzeptanzkriterien, QA und Changelog

---

# 3. Paket A - Quellenmodell und Dokumentrollen

Prioritaet: P0

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- eine kanonische Quellen- und Konfliktregel bestimmen - erledigt
- Rollen trennen: normativ, historisch, QA, Arbeitsregel - erledigt
- QA-Dateien nicht als neue Produktanforderungen behandeln - erledigt
- dreifache Pflege in README, Governance und Codex reduzieren - erledigt
- Implementierungsbereitschaft pro Fachbereich statt pauschal ausweisen - erledigt
- Architekturpflichten aus Codex-Regeln entweder als ADR dokumentieren oder als App-Repo-Entscheidung markieren - erledigt als App-Repo-Arbeitsbaseline
- historische Phasen-Sprache in Review-/Direction-Dateien kennzeichnen oder neutralisieren - erledigt

Betroffen insbesondere:

- `README.md`
- `DOCS-GOVERNANCE.md`
- `codex/README.md`
- `codex/coding-rules.md`
- `archive/review/2026-06-24/01-wireframe-design-system-check.md`
- `archive/review/2026-06-24/02-high-fidelity-mockup-review.md`
- `design/mockups/high-fidelity/02-ui-direction-decisions.md`

Abschluss: Jeder Entwickler erkennt sofort, welche Datei bei welcher Frage gewinnt.

Umsetzung:

- `DOCS-GOVERNANCE.md` ist die kanonische Quelle fuer Dokumentrollen, Quellenverantwortung und Konfliktregeln.
- `README.md` und `codex/README.md` verweisen auf `DOCS-GOVERNANCE.md`, statt die Quellenprioritaet vollstaendig zu duplizieren.
- QA- und Review-Dateien sind als Pruef-, Review- oder historische Referenzen eingeordnet und duerfen keine neuen Produktanforderungen einfuehren.
- `codex/coding-rules.md` markiert nicht durch PRD oder ADR gedeckte Architekturpflichten als App-Repo-Arbeitsbaseline.
- Implementierungsbereitschaft wird fachbereichsweise ausgewiesen.

---

# 4. Paket B - Design-System und Mockup-Drift

Prioritaet: P0

Status: entschieden und umgesetzt am 2026-06-23

## B-01 Player

- separate Pause-/Spulen-Hauptbuttons entfernen - erledigt
- Timeline als Primaerfokus durchziehen - erledigt
- sekundaere Aktionen auf Audio, Untertitel, Bildformat, Mehr begrenzen - erledigt

Betroffen:

- `design/design-system/03-components.md`
- `design/design-system/05-screen-patterns.md`
- `design/mockups/03-player-mockup-spec.md`

## B-02 EPG-Button

- aus Player-Controls und rechter Live-TV-Spalte entfernen - erledigt

Betroffen:

- `design/mockups/02-live-tv-mockup-spec.md`
- `design/mockups/03-player-mockup-spec.md`

## B-03 Adaptives Live-TV-Modell

- Kategorie-Modus und Sender-Modus in allen Design-Schichten angleichen - erledigt
- Provider-Spalte im Sender-Modus ausblenden - erledigt

Betroffen:

- `design/design-system/01-foundations.md`
- `design/design-system/05-screen-patterns.md`
- `design/mockups/02-live-tv-mockup-spec.md`

## B-04 Home

- Home in App Shell und Hauptnavigation ergaenzen - erledigt
- Top Navigation als aktuelles Pattern festhalten - erledigt

Betroffen:

- `design/design-system/03-components.md`
- gegebenenfalls `01-foundations.md` und `02-design-tokens.md`

## B-05 Favoriten

- global sichtbare Favoriten-Kategorie beschreiben - erledigt
- Eintraege bleiben intern providergebunden - erledigt
- alte Aussagen zu Favoriten je Provider korrigieren - erledigt

## B-06 Initialfokus

- frisches Oeffnen, Rueckkehr aus Unterdialog und Player-Rueckkehr getrennt definieren - erledigt
- alte Fokusregeln in Design-System-Dateien an aktuelle Screen Specs angleichen - erledigt

## B-07 Suche

- Episoden aus Suchumfang, Ergebnisgruppen, Wireframe und Search Components entfernen - erledigt
- Episode Row fuer Serien-Detailseite behalten - erledigt
- Begriff nach O-01 vereinheitlichen - erledigt: sichtbarer Gruppenname `Kanaele`

## B-08 Design-System-Status

- nach Bereinigung entweder verbindlich freigeben oder Rolle/Prioritaet begrenzen - erledigt: verbindliche Design-Referenz innerhalb der Governance-Rolle

Abschluss: Keine bekannte Drift zwischen Design-System, Screens, Interaction, Components, Mockup-Specs und Owner-Entscheidungen.

Umsetzung:

- Player-Design ist Timeline-zentriert; Pause und Spulen laufen ueber die Timeline.
- EPG-Buttons wurden aus Player-Controls und rechter Live-TV-Spalte entfernt.
- Live-TV nutzt durchgaengig Kategorie-Modus und Sender-Modus; im Sender-Modus ist Provider/Kategorien ausgeblendet.
- Home ist Teil der Top Navigation und Standard-Startscreen.
- Live-TV Favoriten sind global sichtbar; Eintraege bleiben intern providergebunden.
- Initialfokus-Regeln wurden an Screen Specs und Interaction-Dateien angepasst.
- Suche nutzt vier Ergebnisgruppen: `Kanaele`, `Filme`, `Serien`, `EPG`; Episoden bleiben auf der Serien-Detailseite.
- Das Design-System ist als verbindliche Design-Referenz markiert und bleibt der Governance-Konfliktregel untergeordnet.

---

# 5. Paket C - Settings- und Produktvertrag

Prioritaet: P0/P1

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- Log-Export als v1 konsistent machen und UI-Flow definieren - erledigt
- Timeshift-Details nach O-02 aufnehmen - erledigt
- PIN-Systemtastatur in Screen, Components und Wireframe festhalten - erledigt
- EPG-Defaultintervall festlegen - erledigt
- Startbereich nach O-05 angleichen - erledigt
- Live-TV-Preview nach O-06 angleichen - erledigt
- Auto-Next nach O-07 in Settings/DataStore abbilden - erledigt
- Abschluss-Schwelle nach O-08 definieren - erledigt
- Cache-/Verlaufsoptionen nach O-09 klaeren - erledigt
- Kindersicherung bei Restore nach O-10 absichern - erledigt
- externen Player nach O-11 klaeren - erledigt

Abschluss: Jede sichtbare v1-Option besitzt Typ, Werte, Default, Speicherort und Wirkung.

Umsetzung:

- `prd/PRD-v1/04-search-settings-player-requirements.md` enthaelt einen kompakten Settings-Vertrag mit sichtbarer Option, Typ, Werten, Default, Speicherort und Wirkung.
- Diagnose-/Log-Export bleibt v1 unter `Einstellungen > Ueber die App > Diagnose und Support`; Export ist ZIP-only und ohne Logvorschau oder Loginhalt-Kopieren.
- PIN-Felder nutzen die Android-/TV-Systemtastatur als numerische Passwort-Eingabe.
- Vivicast baut keine eigene Zifferntastatur fuer PINs.
- PIN-Eingaben werden nach vier Ziffern nicht automatisch bestaetigt; sichtbare Aktionen wie `Speichern`, `Entsperren` oder `Deaktivieren` bleiben erforderlich.
- Bei temporaerer PIN-Sperre oeffnet keine Tastatur; UI zeigt Restzeit und `Abbrechen`.
- DoD, Security-PRD, Settings-Screen, Settings-Komponenten, Settings-Wireframe, Coding Rules und Visual Acceptance Checklist wurden angeglichen.

---

# 6. Paket D - Stabile Identitaeten und Datenmodell

Prioritaet: P0 vor Datenquellenimplementierung

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- lokale ID, stabile fachliche ID und Remote-ID je Entity unterscheiden - erledigt
- M3U-Identitaetsstrategie definieren - erledigt
- Xtream-Identitaetsstrategie definieren - erledigt
- Backup-/Restore-Schluessel definieren - erledigt
- Verhalten bei `Als Kopie importieren` definieren - erledigt; nicht Teil von v1
- Unique Constraints, Foreign Keys, Delete-Verhalten und Migrationen festlegen - erledigt

Betroffen:

- Provider
- Kategorien
- Sender
- Filme
- Serien
- Staffeln
- Episoden
- EPG-Objekte
- Favoriten
- Verlauf
- Playback Progress

Abschluss: Refresh und Restore koennen Objekte deterministisch wiedererkennen.

Umsetzung:

- `prd/PRD-v1/06-data-model.md` unterscheidet `id`, `stableKey` und `remoteId` und fuehrt `providerStableKey`, `mediaStableKey` sowie pending Restore-Referenzen ein.
- Xtream Stable Keys werden aus Provider-IDs wie `category_id`, `stream_id`, `series_id` und `episode_id` abgeleitet.
- M3U Stable Keys werden deterministisch aus nicht geheimen normalisierten Attributen erzeugt; Zugangswerte, Tokens und private URLs bleiben ausgeschlossen.
- Favoriten, Live-TV-Verlauf und Wiedergabefortschritt werden ueber `providerStableKey + mediaType + mediaStableKey` backupfaehig referenziert.
- Manuelle EPG-Zuordnungen werden ueber `providerStableKey + channelStableKey + epgSourceStableKey + epgChannelStableKey` backupfaehig referenziert.
- Restore aus Backup ersetzt in v1 den Backup-Umfang; `Zusammenfuehren`, Restore-Konfliktdialoge und `Als Kopie importieren` sind nicht Teil von v1.
- Backup-Schema-Migration alter kompatibler Versionen bleibt erlaubt, ist aber kein Zusammenfuehren lokaler und importierter Daten.
- Vor Restore werden Backup-Datei, Passphrase, Schema und Inhalt validiert; danach wird ein internes Sicherheitsbackup versucht.
- Wenn das interne Sicherheitsbackup fehlschlaegt, entscheidet der Nutzer zwischen Abbruch und bewusstem Fortsetzen.
- Unique Constraints, Foreign Keys, Delete-Verhalten und Migrationen sind im Datenmodell festgelegt.
- ADR-004 wurde auf Restore-Ersetzen aktualisiert; ADR-010 dokumentiert stabile Identitaeten und Restore-Schluessel.

---

# 7. Paket E - EPG-Datenpipeline

Prioritaet: P0 vor EPG-Implementierung

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- quellbezogene oder providerbezogene Speicherung entscheiden - erledigt
- Empfehlung: EPG-Kanaele/Programme quellbezogen, Provider-Sender ueber Mapping - erledigt
- `EPGProgramEntity` und Mapping-Entities ueberpruefen - erledigt
- Prioritaets- und Konfliktregeln definieren - erledigt
- Zeitzonen, fehlende Endzeiten, Ueberlappungen und Duplikate definieren - erledigt
- Aufbewahrung und Cleanup verbindlich machen - erledigt
- Indizes und Query-Muster fuer Now/Next, Tagesansicht und Suche definieren - erledigt

Abschluss: ADR-002, Datenmodell und Refresh-Strategie beschreiben dieselbe Pipeline.

Umsetzung:

- EPG-Daten werden quellbezogen als `EPGSource -> EPGChannel -> EPGProgram` gespeichert.
- Provider-Sender werden ueber `EPGChannelMappingEntity` und Provider-EPG-Prioritaeten angebunden.
- EPG-Programme werden nicht als providerbezogene Kopien gespeichert.
- Manuelle EPG-Zuordnung gewinnt immer vor automatischer Zuordnung.
- Alle Programmzeiten werden intern als UTC-Zeitpunkte gespeichert; `timeShiftMinutes` der Quelle wird beim Import angewendet.
- Fehlende Endzeiten duerfen aus dem naechsten Programmbeginn desselben EPG-Kanals abgeleitet werden.
- Ueberlappungen und Duplikate brechen den Import nicht ab; Duplikate werden innerhalb derselben Quelle und desselben EPG-Kanals ueber `startTimeUtc + normalizedTitle` dedupliziert.
- EPG-Aufbewahrung ist global sichtbar: Vergangenheit 1 bis 14 Tage, Default 1; Zukunft 1 bis 14 Tage, Default 7.
- Cleanup entfernt nur EPG-Programmdaten ausserhalb des konfigurierten Fensters.
- EPG-Quellen, EPG-Kanaele, Provider-Zuordnungen und manuelle Mappings bleiben beim Cleanup erhalten.
- Now/Next, Tagesansicht und EPG-Suche laufen ueber Mapping, Prioritaet und die quellbezogenen EPG-Programme.

---

# 8. Paket F - M3U-, Xtream- und XMLTV-Vertraege

Prioritaet: P0 vor Parserimplementierung

Status: entschieden und umgesetzt am 2026-06-23

## M3U

- Encoding/BOM/Zeilenenden - erledigt
- bekannte und unbekannte Attribute - erledigt
- Identitaet, Gruppe, Logo, Kanalnummer - erledigt
- Catch-Up - erledigt
- Duplikate und fehlerhafte Eintraege - erledigt
- grosse Dateien und Teilfehler - erledigt

## Xtream

- verwendete Endpunkte - erledigt
- Authentifizierung und URL-Normalisierung - erledigt
- Zeitlimits, Wiederholungen und Parallelitaet - erledigt
- API-Varianten und unvollstaendige Antworten - erledigt
- Remote-IDs, Catch-Up und Metadaten - erledigt

## XMLTV

- Encoding und Komprimierung - erledigt
- Streaming-Verarbeitung - erledigt
- Kanal-/Programmfelder - erledigt
- Zeitzonen - erledigt
- fehlende/ueberlappende Daten - erledigt
- Duplikate und Teilfehler - erledigt

## Verbindungstest

- je Quelltyp definieren, was Erfolg und welcher Fehlerzustand ist - erledigt

## Testfixtures

- gueltig, fehlerhaft, gross, verschiedene Encodings, Duplikate und Verbindungsfehler - als Teststrategie-Eingabe definiert

Abschluss: M3U, Xtream Codes und XMLTV haben einen verbindlichen Parser- und Quellenvertrag.

Umsetzung:

- `prd/PRD-v1/12-parser-source-contracts.md` definiert M3U-, Xtream- und XMLTV-Vertraege.
- Toleranter Teilimport ist Standard.
- Importstatus unterscheidet `Erfolgreich`, `Erfolgreich mit Teilfehlern`, `Fehlgeschlagen` und `Abgebrochen`.
- M3U akzeptiert UTF-8 mit BOM, `LF`, `CRLF` und gemischte Zeilenenden.
- M3U wertet bekannte Attribute wie `tvg-id`, `tvg-name`, `tvg-logo`, `group-title`, `tvg-chno`, `catchup`, `catchup-days` und `catchup-source` aus.
- Xtream Codes nutzt `player_api.php` als v1-Basisendpunkt und leitet Stream-/XMLTV-URLs nur ab, ohne sie dauerhaft als Klartext zu speichern.
- Xtream Remote-IDs folgen dem Stable-Key-Vertrag fuer Kategorien, Streams, Serien, Staffeln und Episoden.
- XMLTV unterstuetzt plain XML, gzip und ZIP mit genau einer XML-Datei.
- XMLTV-Kanaele werden zu `EPGChannelEntity`, Programme zu `EPGProgramEntity`.
- Parserdiagnose nutzt nur bereinigte technische Zaehler und Fehlerkategorien.
- Rohdaten, URLs, Zugangswerte, Tokens, HTTP-Header, Provider-/Inhaltsnamen und ungefilterte Playlist-/XMLTV-Inhalte bleiben aus Logs, Diagnoseexporten und unverschluesselten Backups ausgeschlossen.

Abschluss: Parser koennen ohne freie Produktannahmen implementiert werden.

---

# 9. Paket G - Atomarer Import und Refresh

Prioritaet: P0

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- Lebenszyklus Download, Parse, Validate, Stage, Diff, Commit, Cleanup definieren - erledigt
- Transaktionsgrenzen festlegen - erledigt
- Verhalten bei Prozess- oder Netzwerkabbruch definieren - erledigt
- tolerierbare Teilfehler und Abbruchschwelle definieren - erledigt
- Umgang mit temporaer fehlenden Inhalten klaeren - erledigt
- Folgen fuer Favoriten, Verlauf und Progress festlegen - erledigt
- Scheduling, Parallelitaet und Wiederholungen dokumentieren - erledigt

Abschluss: Ein Fehler hinterlaesst keine halb aktualisierte Bibliothek.

Umsetzung:

- `prd/PRD-v1/07-background-jobs-performance.md` definiert den atomaren Import-/Refresh-Vertrag.
- `architecture/decisions/ADR-012-atomic-import-refresh.md` dokumentiert die Architekturentscheidung.
- Provider-Refreshes committen atomar pro Provider.
- EPG-Refreshes committen atomar pro EPG-Quelle.
- Der Lebenszyklus ist `Download -> Parse -> Validate -> Stage -> Diff -> Commit -> Cleanup`.
- Vor `Commit` werden keine produktiven Provider-, Medien-, EPG- oder nutzerbezogenen Daten veraendert.
- Fehler vor Commit behalten alte Daten; Commit-Fehler rollen die betroffene Room-Transaktion vollstaendig zurueck.
- `Erfolgreich mit Teilfehlern` darf valide Eintraege uebernehmen, aber Loeschungen nur aus autoritativen Teilbereichen ableiten.
- Fehlgeschlagene, abgebrochene oder nicht autoritative Refreshes loeschen keine bestehenden Inhalte und keine pending Restore-Referenzen.
- Gleichzeitige produktive Refreshes fuer denselben Provider oder dieselbe EPG-Quelle sind ausgeschlossen.

---

# 10. Paket H - Lokale Suche und FTS

Prioritaet: P1

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- FTS oder normalisierte Suchtabellen entscheiden - erledigt
- indexierte Entities festlegen - erledigt
- Schreibweisen, Umlaute, Sonderzeichen und Prefix-Suche definieren - erledigt
- Ranking und Ergebnislimits je Gruppe definieren - erledigt
- Indexpflege bei Refresh/Delete/Migration definieren - erledigt
- Suchumfang auf vier Gruppen begrenzen - erledigt

Abschluss: Suche ist bei Zielgroessen messbar performant.

Umsetzung:

- Interne Vivicast-Suche nutzt Room FTS4 als v1-Volltextstrategie.
- FTS-Indexe sind getrennt nach `ChannelSearchFts`, `MovieSearchFts`, `SeriesSearchFts` und `EPGProgramSearchFts`.
- Ergebnisgruppen sind exakt `Kanaele`, `Filme`, `Serien` und `EPG`.
- Episoden sind keine eigene Suchgruppe und kein eigenes Suchergebnis.
- Query und Indextexte werden gleich normalisiert, inklusive Whitespace, Satzzeichen, Umlauten, Diakritika und `ss`/`ß`.
- Prefix-Suche ist tokenbasiert; reine Infix-Suche ist kein v1-Ziel.
- EPG-Suche startet erst ab drei normalisierten Zeichen.
- Jede Ergebnisgruppe liefert maximal 20 Treffer.
- Ranking bevorzugt exakte Titel-/Name-Treffer, Prefixe, Token-Treffer und danach Metadaten; EPG bevorzugt laufende und nahe zukuenftige Sendungen.
- FTS-Indexe werden aus produktiven Daten gepflegt, nicht aus Staging, und bei Restore, Migration, Delete oder EPG-Cleanup aktualisiert oder rebuilt.

---

# 11. Paket I - Player, Catch-Up und Progress

Prioritaet: P1

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- unterstuetzte Medienarten und explizite Nicht-Ziele definieren - erledigt
- Request-Kontext und Weiterleitungen definieren - erledigt
- Catch-Up-URL-Erzeugung fuer M3U und Xtream beschreiben - erledigt
- Speicherrhythmus und Abschlussregeln fuer Progress definieren - erledigt
- externen Player nach O-11 behandeln - erledigt
- Audio-/Untertitel-Sprachlogik definieren - erledigt
- Timeshift-Puffer und Grenzfaelle dokumentieren - erledigt

Abschluss: Jeder Playerzustand besitzt eindeutige Daten- und Bedienregeln.

Umsetzung:

- `architecture/decisions/ADR-013-player-playback-progress.md` dokumentiert den Player-/Playback-/Progress-Vertrag.
- Interne Wiedergabe umfasst Live-TV, Filme, Serienepisoden und Catch-Up.
- Externe Player gelten nur fuer Filme und einzelne Episoden; Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.
- PlaybackRequest, Herkunft, Rueckkehrziel, stabile Medienreferenzen, EPG-/Timeshift-Kontext und just-in-time Streamaufloesung sind definiert.
- HTTP-Weiterleitungen bleiben Laufzeitdaten; finale Redirect-URLs werden nicht gespeichert oder geloggt.
- Stream- und Catch-Up-URLs werden nicht dauerhaft als Klartext gespeichert und nicht geloggt.
- M3U-Catch-Up nutzt `catchup`, `catchup-days`, `catchup-source`, `default`, `append` und EPG-Platzhalterersetzung.
- Xtream-Catch-Up wird aus Archiv-/Catch-Up-Metadaten, Stream-ID, EPG-Zeitfenster und geschuetzten Zugangsdaten abgeleitet.
- Automatischer VOD-Fortschritt entsteht nur fuer interne Filme und Episoden ab 10 Sekunden Position oder 1 Prozent Fortschritt.
- Progress wird nach Anlage mindestens alle 10 Sekunden sowie bei Pause, Seek-Ende, Player-Verlassen, App-Hintergrund und Medienende gespeichert.
- Live-TV und Catch-Up erzeugen keinen `PlaybackProgressEntity`-Datensatz.
- Audio-/Untertitel-Defaults werden beim Streamstart angewendet; manuelle Track-Auswahl gilt nur fuer die aktuelle Wiedergabe.
- Timeshift gilt nur fuer Live-TV; Speicher- oder Ressourcenfehler lassen Live-TV ohne Timeshift weiterlaufen.

---

# 12. Paket J - Schutzkonzept fuer Daten, Netzwerk und Backup

Prioritaet: P0/P1

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- normale und geschuetzte Konfigurationswerte klassifizieren - erledigt
- geschuetzte lokale Speicherung konkretisieren - erledigt
- Verhalten bei Verlust geschuetzter lokaler Daten definieren - erledigt
- Schutzformat fuer Vollbackups dokumentieren - erledigt
- PIN-Pruefung, Fehlversuche und Session-Freigabe konkretisieren - erledigt
- HTTP/TLS-Verhalten verbindlich festlegen - erledigt
- zentrale Bereinigung fuer Diagnoseexporte definieren - erledigt

Abschluss: Keine sicherheitsrelevante Produktentscheidung bleibt implizit.

## Umsetzung

- `architecture/decisions/ADR-014-security-data-network-backup.md` dokumentiert Schutzklassen, Keystore/Secret Store, Vollbackup-Krypto, PIN-Sperrlogik, HTTP/TLS-Policy und Diagnosebereinigung.
- Datenklassen sind `Normal`, `Sensibel`, `Geheim` und `Sicherheitswirksam lokal`.
- Room und DataStore speichern keine geheimen Klartextwerte; geheime Nutzwerte liegen im geschuetzten Secret Store mit Keystore-gebundenem Schluessel.
- Neue geschuetzte Speicherung baut nicht auf `EncryptedSharedPreferences` oder Jetpack `security-crypto` auf.
- Verlust geschuetzter lokaler Daten markiert betroffene Quellen als `Zugangsdaten erforderlich`; PIN und Kindersicherung werden deaktiviert.
- Standard-Backups enthalten keine Geheimnisse.
- Verschluesselte Vollbackups nutzen ein versioniertes AES-GCM-Schutzformat mit KDF-Metadaten, Salt, Nonce, Ciphertext und Authentifizierungstag.
- Backup-Passphrase wird nicht gespeichert, nicht geloggt und nicht in Diagnoseexporte geschrieben.
- PIN wird nur als langsamer gesalzener Pruefwert gespeichert.
- Nach fuenf falschen PIN-Eingaben gelten 30 Sekunden, 60 Sekunden und danach 5 Minuten Sperre; App-Neustart hebt die Sperre nicht auf.
- Session-Freigaben sind nur im Speicher und nicht backupfaehig.
- HTTP bleibt fuer nutzerdefinierte IPTV-, EPG- und Stream-Quellen erlaubt, wird aber als unsicher markiert und zentral policygebunden.
- TLS-Zertifikatsfehler werden nicht bypassed; provider-spezifische Header-, Cookie- und User-Agent-Optionen sind nicht Teil von v1.
- Diagnoseereignisse werden vor dauerhaftem Schreiben und vor Export zentral bereinigt.
- umgesetzt in Security-PRD, IPTV-/Backup-/Parser-PRD, Datenmodell, Backup-PRD, Settings-/Player-PRD, About-App-PRD, DoD, ADR-004, ADR-014, Settings-/Quellen-Screens, Wireframe, Komponenten, Coding Rules, QA, README und Changelog

---

# 13. Paket K - Android-TV-Systemintegration

Prioritaet: P1

Status: entschieden und umgesetzt am 2026-06-23

## Aufgaben

- Deep-Link-Schema und stabile Ziel-IDs definieren - erledigt
- Back Stack und fehlende Inhalte behandeln - erledigt
- globale Suche: Index, Update und Cleanup definieren - erledigt
- Watch Next: Publish, Update und Delete definieren - erledigt
- Provider-Loeschung und deaktivierte Provider behandeln - erledigt
- Kindersicherung fuer Deep Links, globale Suche und Watch Next durchziehen - erledigt

Abschluss: Systemfunktionen koennen Provider-Isolation und Kindersicherung nicht umgehen.

## Umsetzung

- `architecture/decisions/ADR-008-android-tv-integration.md` dokumentiert Deep Links, globale Android-TV-Suche, Watch Next, Providerstatus, pending Restore-Referenzen und Kindersicherungsschutz.
- Deep Links verwenden stabile fachliche Schluessel und keine lokalen Room-IDs.
- Deep Links enthalten keine Stream-URLs, Tokens, Zugangswerte, HTTP-Header oder Cookies.
- Android-TV-Systemsuche enthaelt Live-TV, Filme und Serien, aber keine EPG-Treffer, keine Episoden als eigene Treffer und kein Catch-Up.
- Watch Next gilt nur fuer Filme und Serienepisoden.
- Geschuetzte Inhalte erscheinen nicht in Android-TV-Systemsuche oder Watch Next, solange der jeweilige Schutz aktiv ist.
- Deep Links pruefen beim Oeffnen trotzdem aktuelle Providerverfuegbarkeit, Zugangsdatenstatus und Kindersicherung.
- Pending Restore-Referenzen werden erst nach erfolgreichem Provider-Refresh veroeffentlicht.
- Provider-Deaktivierung, Provider-Loeschung, Restore, Migration, Schutzregel-Aenderung und Fortschritts-/Abschlussaenderung bereinigen Android-TV-Systemsuche und Watch Next.
- Fehlende, geloeschte, pending oder deaktivierte Ziele zeigen kontrollierte Fehler- oder Nicht-verfuegbar-Zustaende statt stillem Home-Fallback.
- umgesetzt in ADR-008, Android-TV-PRD, Settings-/Search-PRD, IPTV-PRD, Datenmodell, DoD, Navigation, Coding Rules, QA, README und Changelog

---

# 14. Paket L - Teststrategie und messbare DoD

Prioritaet: P1

Status: erledigt am 2026-06-23

## Aufgaben

- Parser-Golden-Tests und Mockserver - erledigt
- Datenbank- und Migrationstests - erledigt
- Refresh-/Abbruchtests - erledigt
- Backup-/Restore-Roundtrips und Legacy-Konfliktfelder - erledigt
- Player-, Catch-Up-, Timeshift- und Progress-Tests - erledigt
- D-Pad-, Fokus- und Back-Pfade je Screen - erledigt
- grosse Schrift sowie 720p/1080p/4K - erledigt
- Schutz- und Bereinigungstests - erledigt
- messbare Ziele fuer Import, Suche, Datenbank, EPG und Speicherverbrauch - erledigt

Abschluss: Jede kritische Muss-Anforderung besitzt mindestens einen reproduzierbaren Testfall.

## Umsetzung

- `prd/PRD-v1/13-test-strategy.md` als normative Teststrategie ergaenzt.
- Testarten fuer Unit-, Repository-, Room-Migrations-, instrumentierte Android-, Mockserver-, Benchmark- und manuelle Android-TV-QA definiert.
- Kleine, grosse und fehlerhafte Referenzfixtures fuer Parser, Datenbank, Migration, Backup/Restore und Performance definiert.
- Parser-Golden-Tests fuer M3U, Xtream Codes und XMLTV festgelegt.
- Mockserver-Pflichtfaelle fuer Quellen-, Netzwerk-, Fehler-, Timeout-, Redirect- und Redaction-Pfade dokumentiert.
- Room-/Migrationstests fuer Stable Keys, FTS, Pending References, Restore und Systemintegration definiert.
- Refresh-/Abbruch-/Atomizitaetstests fuer Provider und EPG-Quellen definiert.
- Backup-/Restore-Roundtrips fuer Standard-Backup, Vollbackup, Legacy-Schema-Migration und Restore-Ersetzen definiert.
- Player-, Catch-Up-, Timeshift-, Progress-, External-Player- und Auto-Next-Pflichtszenarien dokumentiert.
- D-Pad-, Fokus-, Back-, grosse-Schrift- und 720p/1080p/4K-Pruefungen je betroffenem Screen festgelegt.
- Schutz-, Redaction-, Android-TV-Systemsuche- und Watch-Next-Bereinigungstests definiert.
- Messbare v1-Budgets fuer Import, Suche, Datenbank, EPG und Speicherverbrauch festgelegt.
- umgesetzt in Teststrategie-PRD, DoD, Performance-PRD, Backup-PRD, Parser-Vertrag, Coding Rules, Codex-Einstieg, visueller QA, Readiness-QA, README, Governance und Changelog

---

# 15. Paket M - Referenzen und Housekeeping

Prioritaet: P2

Status: erledigt am 2026-06-23

## Aufgaben

- Design-System-README auf aktuelle PRD-Dateinamen umstellen - erledigt
- `design/components/README.md` um `about-app.md` ergaenzen - erledigt
- `design/mockups/README.md` um `design/mockups/high-fidelity/02-ui-direction-decisions.md` ergaenzen - erledigt
- kollidierende PRD-Kapitelnummern bereinigen - erledigt
- `architecture/diagrams/` fuellen oder als geplant kennzeichnen - erledigt
- redundante `.gitkeep` entfernen - erledigt
- `codex/`-Namensdoppeldeutigkeit fuer das App-Repo dokumentieren - erledigt
- Deutsch/Englisch und Fachbegriffe konsistent machen - erledigt

Empfohlene Diagramme:

- Systemkontext
- Modul-/Layer-Abhaengigkeiten
- Import-/Refresh-Fluss
- EPG-Fluss
- Player-/Progress-Fluss
- Backup-/Restore-Fluss

Abschluss: Keine gebrochenen Referenzen oder irrefuehrenden Verzeichnisangaben.

## Umsetzung

- `design/design-system/README.md` auf aktuelle PRD-Dateien `00` bis `13` erweitert und auf `DOCS-GOVERNANCE.md` als kanonische Konfliktregel verwiesen.
- `design/components/README.md` um `about-app.md` ergaenzt.
- `design/mockups/README.md` um `design/mockups/high-fidelity/02-ui-direction-decisions.md` ergaenzt und Phasen-/Prioritaetsformulierung neutralisiert.
- PRD-Kapitelkollision bereinigt: Kapitel 8 fasst Android-TV-Integration und Sicherheit, Kapitel 9 enthaelt Akzeptanzkriterien und DoD.
- Alte unklare Kapitelreferenz fuer Diagnoseexport auf `prd/PRD-v1/11-about-app-requirements.md` umgestellt.
- `architecture/diagrams/README.md` als geplante Diagrammuebersicht angelegt.
- Redundante `.gitkeep`-Dateien aus befuellten Verzeichnissen entfernt.
- `codex/`-Namensdoppeldeutigkeit in README und Codex-README dokumentiert.
- Offene V-Marker V-01, V-02, V-04, V-05, V-06, V-08, V-09, V-10, V-11, V-12, V-14, V-15, V-16, V-17 und V-18 erneut geprueft und geschlossen.
- umgesetzt in Design-System-README, Components-README, Mockups-README, PRD-Kapitelueberschriften, Architecture-Diagrams-README, README, Codex-README, Cross-QA, Remediation-Plan und Changelog

---

# 16. Paket N - Finale Cross-QA und Fresh-Developer-Dry-Run

Prioritaet: Abschlussgate

Status: erledigt am 2026-06-23

## Aufgaben

- interne Links und Dateireferenzen pruefen - erledigt
- nach allen ueberholten Aussagen suchen - erledigt
- Traceability-Matrix je Hauptbereich erstellen - erledigt
- Readiness getrennt bewerten fuer Home, Live-TV, VOD, Suche, Settings, Datenmodell, Quellen, EPG, Player, Backup, Schutzkonzept, Systemintegration und Tests - erledigt
- erneuten Fresh-Developer-Audit ohne Gespraechskontext durchfuehren - erledigt
- README, Changelog und Codex-Einstieg erst danach finalisieren - erledigt

Abschluss: Ein neuer Entwickler kann einen technischen Plan erstellen, ohne Produktentscheidungen erfinden zu muessen.

## Umsetzung

- `archive/review/2026-06-24/10-final-cross-qa-and-dry-run.md` als finales Abschlussgate angelegt.
- Technische Pruefungen fuer Markdown-Dateiverweise, Codefences, offene V-/T-Marker, alte Kapitelkollisionen, alte Diagnose-Kapitelreferenz, redundante `.gitkeep`-Dateien und ueberholte Begriffe dokumentiert.
- Traceability-Matrix fuer Home, Live-TV, VOD, Suche, Settings, Datenmodell, Quellen/Parser, EPG, Player/Progress, Backup/Restore, Schutzkonzept, Android-TV-Systemintegration und Tests erstellt.
- Readiness je Hauptbereich als bereit fuer App-Repo-Planung bewertet.
- Fresh-Developer-Dry-Run ohne Gespraechskontext dokumentiert.
- README, Codex-Einstieg, Implementation-Readiness-QA, Cross-QA, Remediation-Plan und Changelog finalisiert.
- Bewusste Restpunkte auf App-Repo-Struktur, reale Testinfrastruktur, Referenzgeraete und geplante Architekturdiagramme begrenzt.

---

# 17. Vollstaendiges Issue-Register

## Claude Rev. 2

- [x] V-01 Player-Hauptbuttons veraltet - geklaert in Paket I und final geschlossen in Paket M
- [x] V-02 EPG-Button veraltet - geklaert in Paket B und final geschlossen in Paket M
- [x] V-03 Episoden aus Suchgruppen entfernen - geklaert in Paket B und Paket H
- [x] V-04 Live-TV-Spaltenmodell angleichen - geklaert in Paket B und final geschlossen in Paket M
- [x] V-05 Home in Navigation/Design-System ergaenzen - geklaert in Paket B und final geschlossen in Paket M
- [x] V-06 Log-Export als v1 konsistent machen - geklaert in O-03 und final geschlossen in Paket M
- [x] V-07 Timeshift-Details in Settings aufnehmen
- [x] V-08 alte Phasen-Sprache entschaerfen - geklaert in Paket A und final geschlossen in Paket M
- [x] V-09 gebrochene PRD-Referenzen - geklaert in Paket M
- [x] V-10 Components-README unvollstaendig - geklaert in Paket M
- [x] V-11 Mockups-README unvollstaendig - geklaert in Paket M
- [x] V-12 PIN ueber Systemtastatur - geklaert in Paket C und final geschlossen in Paket M
- [x] V-13 EPG-Defaultintervall
- [x] V-14 Architekturdiagramme leer - geklaert in Paket M
- [x] V-15 Quellenprioritaet dreifach gepflegt - geklaert in Paket A und final geschlossen in Paket M
- [x] V-16 PRD-Kapitelkollision - geklaert in Paket M
- [x] V-17 redundante `.gitkeep` - geklaert in Paket M
- [x] V-18 `codex/`-Namensdoppeldeutigkeit - geklaert in Paket M

## Zusaetzlicher Fresh-Developer-Audit

- [x] T-01 stabile fachliche IDs fehlen - geklaert in Paket D
- [x] T-02 EPG-Speichermodell unklar - geklaert in Paket E
- [x] T-03 M3U-Vertrag fehlt - geklaert in Paket F
- [x] T-04 Xtream-Vertrag fehlt - geklaert in Paket F
- [x] T-05 XMLTV-Vertrag fehlt - geklaert in Paket F
- [x] T-06 atomarer Import-/Refresh-Vertrag fehlt - geklaert in Paket G
- [x] T-07 lokale FTS-/Ranking-Strategie fehlt - geklaert in Paket H
- [x] T-08 Player-/Progress-/External-Player-Vertrag unvollstaendig - geklaert in Paket I
- [x] T-09 Schutz-/Netzwerk-/Backup-Vertrag unvollstaendig - geklaert in Paket J
- [x] T-10 Android-TV-Systemintegration unvollstaendig - geklaert in Paket K
- [x] T-11 Backup-Merge und Kindersicherung unvollstaendig - geklaert in Paket D, O-10 und Paket J
- [x] T-12 messbare Tests und Grenzwerte fehlen - geklaert in Paket L
- [x] T-13 Startbereich-Setting fehlt trotz PRD-Anforderung
- [x] T-14 Auto-Next-Settings fehlen trotz PRD-Anforderung
- [x] T-15 Cache-/Verlaufsoptionen widerspruechlich
- [x] T-16 Live-TV-Preview-Ablauf widerspruechlich
- [x] T-17 Fokus-Initialzustand in alten Design-Dateien widerspruechlich - geklaert in Paket B
- [x] T-18 globale Favoriten in alten Design-Dateien falsch beschrieben - geklaert in Paket B
- [x] T-19 Architekturpflichten stehen ausserhalb der ADR-Hauptquelle - geklaert durch App-Repo-Arbeitsbaseline in Paket A
- [x] T-20 Readiness-Aussage ist breiter als die durchgefuehrte QA - geklaert durch fachbereichsweisen Readiness-Status in Paket A

---

# 18. Bearbeitungsreihenfolge fuer die Dokumentation

Diese Reihenfolge betrifft nur die Dokumentationsbereinigung, nicht die App-Implementierung.

1. O-01 bis O-11 einzeln entscheiden - erledigt
2. Paket A Quellenmodell - erledigt
3. Paket B Design-Drift - erledigt
4. Paket C Settings-/Produktvertrag - erledigt
5. Paket D stabile Identitaeten - erledigt
6. Paket E EPG-Pipeline - erledigt
7. Paket F Parser-Vertraege - erledigt
8. Paket G atomarer Import/Refresh - erledigt
9. Paket H Suche/FTS - erledigt
10. Paket I Player/Progress - erledigt
11. Paket J Schutzkonzept - erledigt
12. Paket K Android-TV-Systemintegration - erledigt
13. Paket L Teststrategie - erledigt
14. Paket M Housekeeping - erledigt
15. Paket N finale Cross-QA - erledigt

---

# 19. Naechster Schritt

Die Owner-Entscheidungen O-01 bis O-11 sowie Paket A bis Paket N sind geklaert.

Naechster Schritt:

```text
Dokumentationsbereinigung abgeschlossen. Naechster fachlicher Schritt ist die App-Repo-Planung in Spegeli/vivicast.
```
