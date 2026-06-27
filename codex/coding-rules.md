# Vivicast – Coding Rules

Status: Codex-Arbeitsregel v12

Diese Datei enthaelt Arbeitsregeln fuer Codex und die App-Implementierung.

Sie ist keine Produktquelle und ersetzt keine PRD- oder ADR-Entscheidung.

Architekturvorgaben in dieser Datei, die nicht direkt aus PRD oder ADR ableitbar sind, gelten als App-Repo-Arbeitsbaseline. Neue oder abweichende verbindliche Architekturentscheidungen muessen als ADR oder als dokumentierte App-Repo-Entscheidung festgehalten werden.

## Sprache und Plattform

- Kotlin verwenden.
- Android TV als primäre Plattform behandeln.
- Jetpack Compose for TV verwenden.
- Media3 / ExoPlayer für Wiedergabe verwenden.

## Architektur

- Clean Architecture einhalten.
- UI Layer, Domain Layer und Data Layer trennen.
- Repository Pattern verwenden.
- Use Cases für fachliche Logik verwenden.
- Unidirectional Data Flow verwenden.
- Keine Datenbankzugriffe direkt aus UI-Komponenten.

## Module

- Feature-Code gehört in `:feature:*`.
- Datenquellen gehören in `:data:*`.
- Parser gehören in `:iptv:*`.
- Wiederverwendbare Technik gehört in `:core:*`.
- Hintergrundjobs gehören in `:worker`.

## Sicherheit

- Daten nach `Normal`, `Sensibel`, `Geheim` und `Sicherheitswirksam lokal` klassifizieren.
- Keine Klartextspeicherung geheimer Zugangswerte.
- Private Quellen-URLs mit eingebetteten Zugangswerten wie Tokens oder Credentials wie geheime Werte behandeln.
- Normale nicht-geheime Server- oder Quellenadressen duerfen als Konfiguration gespeichert werden, wenn sie fuer die Funktion notwendig sind.
- Android Keystore nur fuer nicht exportierbare Schluessel verwenden.
- Geheime Nutzwerte in einem geschuetzten Secret Store im privaten App-Speicher speichern und mit Keystore-gebundenem Schluessel verschluesseln.
- Geschuetzte Speicherung nicht auf `EncryptedSharedPreferences` oder Jetpack `security-crypto` als neue Basis aufbauen.
- Bei Keystore- oder Secret-Store-Verlust Quellen mit benoetigten Geheimnissen als `Zugangsdaten erforderlich` markieren.
- Bei Verlust geschuetzter lokaler PIN-/Kindersicherungsdaten Kindersicherung deaktivieren und Neueinrichtung verlangen.
- HTTPS bevorzugen.
- HTTP fuer nutzer- oder providerdefinierte IPTV-, EPG- und Stream-Endpunkte erlauben, aber sichtbar als unsicher markieren und nur ueber zentrale Netzwerkpolicy ausfuehren.
- App-eigene Dienste nicht ueber HTTP anbinden.
- Keine unkontrollierte breite Manifest-Freigabe fuer Klartextverkehr verwenden.
- TLS-Zertifikatsfehler nicht bypassed behandeln.
- Certificate Pinning fuer nutzerdefinierte IPTV-Server nicht als v1-Default erzwingen.
- Provider-spezifische User-Agent-, Header- oder Cookie-Einstellungen nicht einfuehren; nur den globalen User-Agent unter Allgemein verwenden.
- Diagnoseereignisse vor dauerhaftem Schreiben bereinigen und den Exportstrom vor Ausgabe erneut zentral pruefen.
- Bei unklarer Sensibilitaet Diagnosefelder verwerfen oder neutralisieren statt exportieren.
- Log- oder Diagnoseprotokolldateien nur exportieren; ihren Inhalt niemals direkt in der App anzeigen oder in die Zwischenablage kopieren.
- Das Diagnose-ZIP muss `vivicast-diagnostics.log` und `diagnostics-metadata.json` enthalten.
- Diagnoseprotokollierung standardmaessig deaktivieren; die Aufbewahrungsdauer in DataStore auf 1 bis 7 Tage begrenzen und mit 1 Tag vorbelegen.
- Pro App-Prozessstart bei aktiver Protokollierung eine neue private logische Diagnosesitzung mit `sessionId`, `startedAt`, `endedAt`, `lastRecordedAt`, `endReason` und `endTimeAccuracy` fuehren.
- Nicht sauber beendete Sitzungen beim naechsten Prozessstart mit passendem `ApplicationExitInfo` oder ersatzweise mit `lastRecordedAt` abschliessen; keine erfundene exakte Endzeit speichern.
- Interne Diagnosedaten auf 20 MiB insgesamt, 2 MiB je Segment und drei Segmente beziehungsweise 6 MiB Logdaten je Sitzung begrenzen.
- Bei sitzungsinterner Rotation das aelteste geschlossene Segment entfernen.
- Bei globalem Platzbedarf nach Altersbereinigung zuerst die aeltesten abgeschlossenen Sitzungen entfernen; Segmente der aktiven Sitzung nur durch die feste Drei-Segment-Rotation ersetzen.
- Aktuelles Schreibsegment und Metadaten der aktiven Sitzung bei Rotation schuetzen; nicht mehr speicherbare Ereignisse verwerfen und als Kuerzung zaehlen.
- Trunkierung und entfernte Sitzungen, Segmente und Ereignisse in `diagnostics-metadata.json` ausweisen; den Exportzeitraum nur aus tatsaechlich vorhandenen Ereignissen bilden.
- Diagnosesitzungen und Segmente nur im privaten App-Speicher halten und nicht in Room oder das Standard-Backup aufnehmen.
- Nur freigegebene technische Ereignisse und Metadaten exportieren; keine Zugangswerte, Tokens, Cookies, HTTP-Header, URLs, Rohdaten, Provider-/Inhaltsnamen, Suchverlaeufe, Datenbank-Dumps, Screenshots oder ungefiltertes System-Logcat.
- Netzwerkfehler nur mit Fehlerart, HTTP-Status und Dauer, jedoch ohne URL exportieren.
- Bereinigte Stacktraces nur fuer Warnungen, Fehler und Abstuerze exportieren.
- Technische Zuordnungen bei Bedarf nur ueber neutrale interne IDs herstellen.
- Exportstatus darf nur Erfolg, Fehler oder Exportziel und niemals Loginhalt zeigen.
- Standard-Backups duerfen keine geheimen Zugangswerte exportieren.
- Verschluesselte Vollbackups duerfen geheime Zugangswerte nur enthalten, wenn der Nutzer aktiv eine Backup-Passphrase setzt.
- Verschluesselte Vollbackups mit versioniertem AES-GCM-Container, KDF-Metadaten, Salt, Nonce, Ciphertext und Authentifizierungstag umsetzen.
- Fuer v1 PBKDF2-HMAC-SHA256 als KDF bevorzugen; Argon2id nur nach bewusster Abhaengigkeitsentscheidung verwenden.
- Backup-Passphrase niemals speichern, loggen oder in Diagnoseexporte schreiben.
- Falsche Vollbackup-Passphrase vor jeder lokalen Datenaenderung abbrechen.
- Vor Restore bei aktivem lokalem Einstellungsschutz oder lokaler Backup-/Restore-PIN-Schutzregel immer die aktuell lokale PIN abfragen; niemals eine PIN oder Schutzkonfiguration aus der Backup-Datei zur Autorisierung verwenden.
- Nach Restore Kindersicherung deaktivieren und PIN-Pruefwerte, aktive PIN-Freigaben sowie Schutzflags aus Backups ignorieren.
- Wenn ein Backup ausweist, dass Kindersicherung beim Export aktiv war, nach Restore einen Hinweis zur manuellen Reaktivierung unter `Einstellungen > Kindersicherung` anzeigen.
- PIN-Felder als numerische Passwort-Eingabe ueber die Android-/TV-Systemtastatur umsetzen; keine eigene Zifferntastatur bauen.
- PIN nach vier Ziffern nicht automatisch bestaetigen; Speichern, Entsperren oder Deaktivieren bleiben bewusste Aktionen.
- PIN-Eingaben verdecken und ohne Zwischenablage, Autovervollstaendigung oder Klartextanzeige umsetzen.
- PIN nur als langsamen gesalzenen Pruefwert speichern; niemals PIN-Klartext speichern.
- Nach fuenf falschen PIN-Eingaben temporaer sperren.
- PIN-Sperrdauern 30 Sekunden, 60 Sekunden und danach 5 Minuten verwenden.
- Laufende PIN-Sperre ueber App-Neustart hinweg respektieren.
- PIN-Session-Freigaben nur im Speicher halten; nicht persistieren, nicht exportieren und nicht aus Backups wiederherstellen.

## Performance

- Große Listen ausschließlich lazy rendern.
- Senderlisten mit LazyColumn.
- Filme und Serien mit LazyGrid.
- Suche mit 300 ms Debounce.
- Room-Indizes für Such- und EPG-Abfragen anlegen.
- Keine vollständigen IPTV-Bibliotheken in UI-State laden.
- Medien-Cache- und Verlaufsaktionen unter `Einstellungen > Speicher & Verlauf` fuehren; nicht unter Backup.
- Medien-Cache-Grenze und Cache-Rotation als interne Werte behandeln; keine freie Settings-Konfiguration anbieten.
- Medien-Cache-Informationen aus dem Dateisystem berechnen, mindestens die aktuelle Groesse.
- `Cache leeren` darf nur Medien-Cache-Dateien fuer Senderlogos, Film-Poster, Serien-Poster, Staffelbilder und Episodenbilder entfernen.
- `Cache leeren` darf keine Providerdaten, Favoriten, Verlaeufe, Wiedergabefortschritt, Suchverlauf, EPG-Zuordnungen, Zugangsdaten, EPG-Programmdaten oder aktive Stream-/Timeshift-Puffer entfernen.

- Room-Listen fuer Sender, Filme und Serien paging- oder fensterfaehig abfragen.
- UI-State darf keine vollstaendige Provider-Bibliothek halten.
- EPG-Tagesansichten nur sender-, quellen-, mapping- und zeitfensterbezogen laden.
- Poster und Logos nur fuer sichtbare oder kurz bevorstehende Elemente laden.

## Test- und DoD-Regeln

- Fuer jede kritische Muss-Anforderung einen reproduzierbaren Testfall anlegen oder eine begruendete manuelle Android-TV-QA mit Nachweis dokumentieren.
- Teststrategie, Pflichtfixtures, Mockserver-Faelle, Roundtrips und Performancebudgets aus `prd/PRD-v1/13-test-strategy.md` beruecksichtigen.
- Parser-Golden-Tests fuer M3U, Xtream Codes und XMLTV pflegen.
- Quellen- und Netzwerkpfade ueber lokale Mockserver testen; echte Zugangsdaten, Tokens oder private URLs nie als Testfixture verwenden.
- Room-Migrationen mit kleinen und grossen Fixtures testen.
- Backup- und Restore-Roundtrips fuer Standard-Backup, Vollbackup, Legacy-Schema-Migration und Restore-Ersetzen testen.
- Fehler vor Commit, Commit-Rollback, Nutzerabbruch und Prozessabbruch fuer Provider- und EPG-Refreshes testen.
- Pending Favoriten, Verlauf und Playback Progress nach Restore bis zum erfolgreichen Provider-Refresh testen.
- D-Pad-, Fokus- und Back-Pfade fuer betroffene Screens auf Android-TV-Laufzeit pruefen.
- 720p, 1080p, 4K und grosse Schrift fuer betroffene UI-Aenderungen pruefen.
- Schutz-, Redaction-, Systemsuche- und Watch-Next-Bereinigungstests fuer sicherheitsrelevante Aenderungen ausfuehren.
- Import-, Suche-, Datenbank-, EPG- und Speicherbudgets mit Benchmarknachweis bewerten.

## Suchregeln

- Interne Suche vollstaendig lokal ueber Room FTS4 implementieren.
- Keine Suchanfragen an Provider senden.
- Genau vier Ergebnisgruppen verwenden: `Kanäle`, `Filme`, `Serien`, `EPG`.

- Sichtbare Favoriten-Aktion einheitlich als `Zu Favoriten` oder `Zu Favoriten hinzufügen` beschriften; keine Merkliste-/Meine-Liste-Terminologie verwenden.
- Episoden nicht als eigene Suchgruppe und nicht als eigenes Suchergebnis indexieren.
- Suchverlauf fest auf maximal 20 Eintraege begrenzen.
- Query und Indextexte gleich normalisieren: trimmen, Kleinschreibung, Whitespace zusammenfassen, Satzzeichen als Trenner, Umlaute/Diakritika tolerant, `ae`/`oe`/`ue`/`ss` als Varianten.
- Prefix-Suche tokenbasiert umsetzen; reine Infix-Suche nicht als v1-Ziel behandeln.
- Leere Suche ohne Ergebnisabfrage lassen.
- Bei einem normalisierten Zeichen nur Titel-/Name-Prefixe fuer Kanäle, Filme und Serien abfragen.
- EPG-Suche erst ab drei normalisierten Zeichen starten.
- EPG-Ergebnisse oeffnen Live-TV im Sender-Modus mit aktivem Sender, zur Zielsendung gescrollter EPG-Spalte und Fokus auf dem Zielprogrammpunkt.
- Aktuelle EPG-Treffer duerfen Vollbild Live-TV starten; vergangene Treffer starten nur mit erlaubtem Catch-Up; vergangene Treffer ohne Catch-Up und zukuenftige Treffer zeigen Details/Info ohne Wiedergabe.
- Globale Android-TV-Systemsuche enthaelt keine EPG-Treffer.
- Pro Ergebnisgruppe maximal 20 Treffer liefern.
- Ranking je Gruppe deterministisch halten: exakter Titel/Name, Prefix, Token-Treffer, Metadaten.
- EPG-Ranking laufende Sendungen vor naher Zukunft und alte Treffer niedriger sortieren.
- FTS-Indexe nur aus produktiven Daten befuellen, nie aus Import-/Refresh-Staging.
- Provider-FTS-Aenderungen im atomaren Provider-Commit ausfuehren.
- EPG-FTS-Aenderungen im atomaren EPG-Quellen-Commit ausfuehren.
- Nach Restore, Migration, Delete oder EPG-Cleanup betroffene FTS-Indexe aktualisieren oder neu aufbauen.

## Android TV UX

- Den globalen Startbereich in DataStore als `startDestination` mit `HOME` als Default speichern.
- Fuer `startDestination` nur `HOME`, `LIVE_TV`, `MOVIES` und `SERIES` zulassen; ungueltige Werte auf `HOME` zuruecksetzen.
- Den Startbereich nur beim regulaeren App-Start ohne explizites Ziel anwenden; eine Settings-Aenderung darf die laufende Sitzung nicht sofort umleiten.
- App-Autostart verwendet den gespeicherten Startbereich. Deep Links und explizite Android-TV-Ziele haben Vorrang; Rueckkehr aus dem Hintergrund behaelt den aktuellen Kontext.
- Leere Zielbereiche zeigen ihren normalen Empty State und fallen nicht still auf Home zurueck.
- Deep Links, Android-TV-Systemsuche und Watch Next mit stabilen fachlichen Zielreferenzen umsetzen, nie mit lokalen Room-IDs.
- Deep Links duerfen keine Stream-URLs, Tokens, Zugangswerte, HTTP-Header oder Cookies enthalten.
- Deep-Link-Ziele mit `providerStableKey + mediaType + mediaStableKey` beziehungsweise Sender-/Serien-/Episoden-Stable-Keys aufloesen.
- Fehlende, pending, geloeschte, deaktivierte oder zugangsdatenlose Systemziele kontrolliert als nicht verfuegbar anzeigen; keinen stillen Home-Fallback verwenden.
- Geschuetzte Systemziele beim Öffnen gegen die aktuelle Kindersicherung pruefen.
- Geschuetzte Inhalte nicht in Android-TV-Systemsuche oder Watch Next veroeffentlichen, solange der jeweilige Schutz aktiv ist.
- Android-TV-Systemsuche nur aus produktiven Room-Daten befuellen; keine Staging-Daten verwenden.
- Android-TV-Systemsuche auf Live-TV, Filme und Serien begrenzen; keine EPG-Treffer, keine Episoden als eigene Treffer und kein Catch-Up veroeffentlichen.
- Watch Next nur fuer Filme und Serienepisoden aus internem Playback Progress und manuellen Gesehen-/Ungesehen-Aktionen pflegen.
- Externe Player duerfen keine Watch-Next-Updates erzeugen.
- Provider-Deaktivierung, Provider-Loeschung, Restore, Migration, Schutzregel-Aenderung und Abschlussstatus-Aenderung muessen Android-TV-Systemsuche und Watch Next bereinigen.
- Auto-Next in DataStore als `autoNextEpisodeEnabled = false` und `autoNextEpisodeCountdownSeconds = 10` vorbelegen; fuer den Countdown nur 5, 10, 15 oder 30 zulassen.
- Die Countdown-Einstellung bei deaktiviertem Auto-Next sichtbar, aber nicht bedienbar darstellen; den gespeicherten Wert nicht zuruecksetzen.
- Im Live-TV Browser beim blossen Senderfokus keinen Stream starten.
- Erstes OK in der Senderspalte muss fest Sender-Modus, EPG-Spalte und Live-Vorschau starten und den Fokus auf die aktuelle EPG-Sendung setzen, sofern vorhanden; sonst auf den No-EPG-Placeholder.
- OK auf der fokussierten aktuellen EPG-Sendung oder auf dem No-EPG-Placeholder startet Vollbild. Keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK anbieten.
- Alle Screens müssen per D-Pad bedienbar sein.
- Fokuszustände müssen klar sichtbar sein.
- OK öffnet Aktionen oder Player-Overlay.
- Zurück schließt aktive Overlays vor Navigation zurück.
- Keine Touch-only Interaktionen.

## IPTV-Regeln

- Provider niemals automatisch zusammenführen.
- Kategorien unverändert vom Provider übernehmen.
- Sender ohne Kategorie intern `__UNCATEGORIZED__` zuordnen.
- EPG-Quellen separat verwalten.
- Das globale EPG-Aktualisierungsintervall in DataStore speichern und mit 24 Stunden vorbelegen.
- App-Start, Playlist-Aenderung und manuelle EPG-Aktualisierung als separate Ausloeser behandeln.
- Dieselbe EPG-Quelle pro Refresh-Zyklus nur einmal aktualisieren.
- EPG-Daten quellbezogen als `EPGSource -> EPGChannel -> EPGProgram` speichern; keine providerbezogenen EPG-Programmkopien anlegen.
- Provider-Sender nur ueber EPG-Mapping und Provider-EPG-Prioritaeten mit EPG-Programmen verbinden.
- Manuelle EPG-Zuordnung immer vor automatischer Zuordnung auswerten und nie automatisch ueberschreiben.
- EPG-Zeiten intern als UTC speichern und `timeShiftMinutes` beim Import anwenden.
- Fehlende EPG-Endzeiten nur aus dem naechsten Programmbeginn desselben EPG-Kanals ableiten; keine erfundene Endzeit speichern.
- EPG-Programmdaten ausserhalb von `epgPastRetentionDays` und `epgFutureRetentionDays` loeschen; EPG-Quellen, EPG-Kanäle, Provider-Zuordnungen und manuelle Mappings behalten.
- `epgPastRetentionDays` mit 1 und `epgFutureRetentionDays` mit 7 vorbelegen; fuer beide nur 1 bis 14 zulassen.

## Identitaets- und Restore-Regeln

- Lokale Room-IDs nie als Backup-, Restore- oder Provider-Identitaet verwenden.
- Provider und providerbezogene Entities mit fachlich stabilem `stableKey` fuehren; Provider besitzen einen unveraenderlichen `providerStableKey`.
- Xtream-Stable-Keys bevorzugt aus `category_id`, `stream_id`, `series_id` und `episode_id` ableiten.
- M3U-Stable-Keys deterministisch aus nicht geheimen normalisierten Attributen ableiten; Zugangswerte, Tokens und private URLs duerfen nicht als Klartextbestandteil verwendet werden.
- Favoriten, Live-TV-Verlauf und Wiedergabefortschritt ueber `providerStableKey + mediaType + mediaStableKey` backupfaehig referenzieren.
- Manuelle EPG-Mappings ueber `providerStableKey + channelStableKey + epgSourceStableKey + epgChannelStableKey` backupfaehig referenzieren.
- Nach Restore duerfen nutzerbezogene Referenzen pending bleiben, bis ein erfolgreicher Provider-Refresh sie mit lokalen Entities verbindet.
- Restore aus Backup ersetzt in v1 den Backup-Umfang; kein Zusammenfuehren, kein Konfliktdialog und kein `Als Kopie importieren`.
- Kompatible alte Backup-Schema-Versionen duerfen migriert werden; das ist kein Zusammenfuehren lokaler und importierter Daten.
- Vor dem Ersetzen Backup-Datei, Passphrase, Schema und Inhalt validieren und danach ein internes Sicherheitsbackup versuchen.
- Wenn das interne Sicherheitsbackup fehlschlaegt, Nutzer zwischen Abbruch und bewusstem Fortsetzen waehlen lassen.

## Live-TV- und Player-Regeln

- Erstes OK auf einen Sender oeffnet Sender-Modus, EPG-Spalte und Preview; es startet nicht direkt Vollbild.
- Wenn keine aktuelle EPG-Sendung vorhanden ist, muss der No-EPG-Placeholder fokussiert werden.
- OK auf den No-EPG-Placeholder startet die Vollbildwiedergabe des gewählten Senders ohne Catch-Up-Kontext.
- CH+ und CH- bewegen im Live-TV-Browser den Fokus in der Senderliste; im Player wechseln sie direkt den Sender.
- Player-Overlay-Aktionen sind nur Audio, Untertitel, Bildformat und Mehr. Kein EPG-Chip, keine separaten Pause-/Vor-/Zurückspulbuttons.
- Senderstart umfasst maximal 5 Versuche inklusive Erstversuch; Time-to-playable pro Versuch maximal 10 Sekunden.
- Retry-Abstaende sind 0,5 s, 1 s, 2 s und 4 s.
- Manuelles `Erneut versuchen` setzt den Zaehler zurueck.
- Senderwechsel bricht laufende Start- oder Retry-Vorgaenge ab.
- Reconnect gilt nur fuer den zuletzt aktiven Sender und zeigt einen nicht blockierenden Hinweis.
- Hintergrundwechsel stoppt interne Wiedergabe kontrolliert; VOD-Fortschritt vorher speichern, Live-TV ohne PlaybackProgress, Timeshift-Puffer verwerfen.

## Parser- und Quellenregeln

- M3U, Xtream Codes und XMLTV gemaess `prd/PRD-v1/12-parser-source-contracts.md` implementieren.
- Toleranten Teilimport verwenden; einzelne fehlerhafte Eintraege ueberspringen, solange verwertbare Eintraege importiert werden koennen.
- Importstatus `Erfolgreich mit Teilfehlern` unterstuetzen.
- Parserdiagnose nur mit technischen Zaehlern und Fehlerkategorien fuehren; keine Zugangswerte, Tokens, HTTP-Header, URLs, Rohdaten, Provider-/Inhaltsnamen oder ungefilterte Playlist-/XMLTV-Inhalte schreiben.
- M3U `UTF-8` mit BOM, `LF`, `CRLF` und gemischte Zeilenenden tolerant verarbeiten.
- M3U bekannte Attribute `tvg-id`, `tvg-name`, `tvg-logo`, `group-title`, `tvg-chno`, `catchup`, `catchup-days` und `catchup-source` auswerten; unbekannte Attribute ignorieren.
- M3U-Catch-Up-Modi `default` und `append` unterstuetzen; `catchup-source` erst beim Wiedergabestart aus EPG-Start, EPG-Ende und Dauer aufloesen.
- Xtream-Serveradressen normalisieren und Zugangsdaten nur geschuetzt speichern.
- Xtream-Remote-IDs fuer Kategorien, Streams, Serien und Episoden gemaess Stable-Key-Vertrag verwenden.
- Xtream-Catch-Up erst beim Wiedergabestart aus Archiv-/Catch-Up-Metadaten, Stream-ID, EPG-Zeitfenster und geschuetzten Zugangsdaten ableiten.
- Erzeugte Stream- oder Catch-Up-URLs nicht dauerhaft als Klartext speichern und nicht loggen.
- XMLTV plain XML, gzip und ZIP mit genau einer XML-Datei unterstuetzen.
- XMLTV-Zeitzonen auswerten, Zeiten als UTC speichern und fehlende Endzeiten nur aus dem naechsten Programmbeginn desselben EPG-Kanals ableiten.

## Atomare Import-/Refresh-Regeln

- Provider-Refreshes pro Provider atomar committen.
- EPG-Refreshes pro EPG-Quelle atomar committen.
- Fuer jeden Lauf `Download -> Parse -> Validate -> Stage -> Diff -> Commit -> Cleanup` einhalten.
- Vor `Commit` keine produktiven Provider-, Medien-, EPG- oder nutzerbezogenen Daten veraendern.
- Commit fuer Provider oder EPG-Quelle in einer Room-Transaktion ausfuehren.
- Fehler vor Commit muessen alte produktive Daten unveraendert lassen.
- Fehler innerhalb der Commit-Transaktion muessen vollstaendig zurueckrollen.
- Verwaiste Staging-Daten nach Prozessabbruch beim naechsten Start oder vor dem naechsten Lauf bereinigen.
- Bei `Erfolgreich mit Teilfehlern` valide Eintraege uebernehmen, aber destruktive Loeschungen nur fuer vollstaendig gelesene und validierte Teilbereiche ausfuehren.
- Fuer denselben Provider oder dieselbe EPG-Quelle nie mehrere produktive Refreshes parallel starten.
- Manuelle Aktualisierung darf einen ausstehenden automatischen Lauf ersetzen oder vorziehen, aber keinen parallelen Lauf fuer dieselbe Einheit erzeugen.

## VOD-Fortschrittsregeln

- Die zentrale fachliche Konstante `PLAYBACK_COMPLETION_THRESHOLD_PERCENT` auf 95 setzen; keinen DataStore-Schluessel und keine sichtbare Einstellung dafuer anlegen.
- Die Schwelle ausschliesslich auf `MOVIE` und `EPISODE` anwenden; keine Abschlussentitaet fuer komplette Staffeln oder Serien einfuehren.
- Bei bekannter positiver Dauer ab `progressPercent >= 95` sowie bei jedem tatsaechlichen Medienende `isCompleted = true` setzen.
- Abgeschlossene Filme und Episoden nicht als direkte Resume-Ziele in Home, Fortsetzen, Continue Watching oder Watch Next ausgeben.
- Bei Serien darf ein abgeschlossener Episodendatensatz zur Ermittlung der naechsten verfuegbaren Episode bei Position 0 verwendet werden; nach der letzten Episode den Serien-Eintrag entfernen, sofern kein anderer unvollstaendiger Episodenfortschritt existiert.
- `Als gesehen markieren` muss den Datensatz bei Bedarf anlegen oder aktualisieren und `isCompleted = true` setzen.
- `Als ungesehen markieren` muss den gesamten zugehoerigen `PlaybackProgressEntity`-Datensatz einschliesslich Fortschritt und Zeitstempeln loeschen.
- Auf der fokussierten Episode die passende Markierungsaktion per D-Pad erreichbar machen; keine Staffel- oder Serien-Markierungsaktion anbieten.
- Freie Verlaufslimits nicht als v1-Einstellung anbieten; Suchverlauf fest auf maximal 20 Eintraege begrenzen.
- Filmverlauf-Loeschung muss den zugehoerigen Film-Wiedergabefortschritt loeschen.
- Serienverlauf-Loeschung muss den zugehoerigen Episoden-Wiedergabefortschritt loeschen.
- Automatische VOD-Fortschritte, Medienende-Erkennung und Abschlussstatus nur aus dem internen Vivicast-Player schreiben.
- Automatische Fortschrittsdatensaetze erst ab mindestens 10 Sekunden Position oder mindestens 1 Prozent Fortschritt bei bekannter positiver Dauer anlegen.
- Nach Anlage mindestens alle 10 Sekunden sowie bei Pause, abgeschlossenem Seek, Player-Verlassen, App-Hintergrund und Medienende speichern.
- Live-TV und Catch-Up duerfen keinen `PlaybackProgressEntity`-Datensatz erzeugen; Live-TV nutzt Verlauf, Catch-Up bleibt EPG-Kontext ohne Resume-Ziel.
- Rueckgabewerte externer Player nicht als Position, Dauer, Fortschritt oder Medienende uebernehmen.
- Externe Film- oder Episodenwiedergabe darf keinen `PlaybackProgressEntity`-Datensatz erzeugen oder aktualisieren und darf `isCompleted` nicht setzen.
- Nach Rueckkehr aus externer VOD-Wiedergabe einen Hinweis anzeigen, dass der Fortschritt nicht automatisch ermittelt werden konnte.

## Player-Regeln

- Es existiert immer nur eine aktive Wiedergabe.
- Vor jedem Wiedergabestart einen unveraenderlichen PlaybackRequest mit Medientyp, Provider, stabiler Medienreferenz, Herkunft, Rueckkehrziel, Startposition und EPG-/Timeshift-Kontext erzeugen.
- Stream-URLs just-in-time erzeugen oder aufloesen; nur `http`/`https` und Media3-/ExoPlayer-kompatible Formate als v1-Ziel behandeln.
- HTTP-Streamstarts ueber die zentrale Netzwerkpolicy fuehren; unsichere HTTP-Quellen sichtbar kennzeichnen.
- HTTP-Weiterleitungen nur als Laufzeitdaten behandeln; finale Redirect-URLs nicht speichern oder loggen und nicht erlaubte Zielschema sichtbar als Playerfehler behandeln.
- Externe Player nur fuer Filme und einzelne Episoden anbieten; Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.
- Catch-Up nur starten, wenn Sender/Provider es unterstuetzen, EPG-Start und -Ende verwertbar sind und der Programmpunkt im erlaubten Rueckblickfenster liegt.
- Aktuelle Live-Sendungen ueber Live-TV oder Timeshift behandeln, nicht als Catch-Up.
- Senderstart umfasst maximal 5 Versuche inklusive Erstversuch; ein Versuch gilt nach ExoPlayer-Fehler oder 10 Sekunden ohne abspielbaren Zustand als fehlgeschlagen.
- Retry-Abstaende sind 0,5 s, 1 s, 2 s und 4 s; manuelles `Erneut versuchen` setzt den Zaehler zurueck.
- Streamabbruch startet Reconnect nur fuer den zuletzt aktiven Sender und endet nach 5 Gesamtversuchen im Fehlerdialog.
- Senderwechsel bricht laufende Start-, Retry- und Reconnect-Vorgaenge ab.
- Timeshift wird bei Senderwechsel verworfen.
- Timeshift nur fuer Live-TV verwenden; bei Speicher- oder Ressourcenfehlern Live-TV ohne Timeshift weiterlaufen lassen, Seek sperren und einen Hinweis zeigen.
- Refreshes dürfen aktive Streams nicht unterbrechen.
- Globale Audio-/Untertitel-Defaults beim Streamstart anwenden; manuelle Track-Auswahl im Player nur fuer die aktuelle Wiedergabe verwenden.
- Bei deaktiviertem Auto-Next die Aktion `Nächste Folge abspielen` erst nach dem tatsaechlichen Episodenende anzeigen und niemals ohne OK starten.
- Bei aktiviertem Auto-Next `Nächste Folge in X` um den konfigurierten Zeitraum vor dem Episodenende anzeigen; X sekundenweise aktualisieren, OK sofort starten und ohne Eingabe erst am tatsaechlichen Ende wechseln.
- In beiden Auto-Next-Zustaenden den sichtbaren Button `Zurück` zeitgleich neben dem Hauptbutton anzeigen; keinen Button `Abbrechen` anbieten.
- Den Hauptbutton initial fokussieren. OK auf `Zurück` oder die Zurück-Taste verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit wiederhergestelltem Staffel-/Episodenkontext.
- Staffeluebergaenge zur ersten verfuegbaren Episode der naechsten Staffel aufloesen; nach Serienende kein Auto-Next-Panel anzeigen.
- Das Erreichen der 95-Prozent-Abschluss-Schwelle darf die Wiedergabe nicht beenden, kein Auto-Next-Panel einblenden und keinen Episodenwechsel ausloesen; dafuer das tatsaechliche Medienende verwenden.
- Auto-Next ausschliesslich im internen Vivicast-Player ausfuehren; bei externen Playern kein Auto-Next-Panel und keinen automatischen Episodenwechsel erzeugen.

## Codequalität

- Keine unnötigen Global States.
- Keine magischen Strings ohne zentrale Definition.
- Fehlerzustände explizit modellieren.
- Loading, Empty und Error States sauber abbilden.
- Keine stillen Fehler verschlucken.

## Dokumentation

- Abweichungen vom PRD müssen begründet werden.
- Neue Architekturentscheidungen als ADR dokumentieren.
- Öffentliche APIs und komplexe Logik knapp dokumentieren.
