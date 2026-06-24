# Vivicast PRD v1
## Kapitel 11 - Über die App Anforderungen

Status: verbindlich v7

## Zweck

Diese Datei definiert fachliche Anforderungen fuer den Bereich `Über die App`.

Sie konkretisiert den App-Info-Bereich aus `prd/PRD-v1/04-search-settings-player-requirements.md`.

## Ziel

`Über die App` stellt technische App-Informationen, rechtliche Hinweise sowie Diagnose- und Supportfunktionen bereit.

Der Bereich soll nur Informationen zeigen, die fuer Support, Versionserkennung und rechtliche Hinweise sinnvoll sind.

## Angezeigte Informationen

Anzeigen:

- App-Name
- App-Version
- Build-Nummer
- Paketname
- Datenbank-Version
- Build-Typ, falls sinnvoll
- Android-Version
- Geraetemodell
- Player-Engine, zum Beispiel Media3 / ExoPlayer
- UI-Technologie-Hinweis, zum Beispiel Jetpack Compose for TV

## Aktionen

Unterstuetzte Aktionen:

- Versionsinformationen kopieren
- Diagnose und Support oeffnen
- Lizenzhinweise oeffnen
- Datenschutzinformationen oeffnen
- Drittanbieter-Lizenzen oeffnen

## Diagnose und Support

Der verbindliche UI-Ort fuer den bereinigten Log-Export ist:

```text
Einstellungen > Über die App > Diagnose und Support
```

`Diagnose und Support` darf allgemeine technische Support-Informationen anzeigen.

Erlaubt:

- App-Version
- Build-Nummer
- Paketname
- Android-Version
- Geraetemodell
- Datenbank-Version
- aktive App-Sprache
- Anzahl Provider
- Anzahl EPG-Quellen
- letzter erfolgreicher Importzeitpunkt, falls vorhanden
- letzter Fehlerzeitpunkt, falls vorhanden

Sichtbare Diagnoseoptionen und Aktion:

```text
Diagnoseprotokollierung: Aus | Ein
Aufbewahrungsdauer: 1 Tag | 2 Tage | 3 Tage | 4 Tage | 5 Tage | 6 Tage | 7 Tage
Diagnoseprotokoll exportieren
```

Defaults:

```text
Diagnoseprotokollierung: Aus
Aufbewahrungsdauer: 1 Tag
```

Die Aufbewahrungsdauer bleibt bei ausgeschalteter Diagnoseprotokollierung sichtbar, ist dann aber nicht aenderbar. Bereits vorhandene Sitzungen bleiben bis zu ihrem regulaeren Ablauf exportierbar.

Groessenlimit, Segmentgroesse und Rotationsregeln sind feste interne v1-Werte und werden nicht als weitere Einstellungen angezeigt.

## Export-only-Regel fuer Logs

Logs duerfen in der App ausschliesslich als bereinigte Datei exportiert werden.

Der Inhalt einer Log- oder Diagnoseprotokolldatei darf niemals direkt in der App angezeigt werden.

Insbesondere nicht erlaubt:

- Logzeilen in einem Panel, Dialog oder Screen anzeigen
- Logdatei-Inhalt in einer Vorschau darstellen
- Logdatei-Inhalt als langen Text in `Über die App` anzeigen
- Logdatei-Inhalt in die Zwischenablage kopieren

Die App darf nach einem Export nur Statusinformationen anzeigen, zum Beispiel Erfolg, Fehler oder ausgewaehltes Exportziel. Sie darf dabei keinen Loginhalt wiedergeben.

Technische Ereignisse muessen bereits vor dem dauerhaften Schreiben nach den Ausschlussregeln bereinigt werden. Vor dem Export wird der gesamte Ausgabestrom als zusaetzliche Schutzschicht erneut zentral geprueft. Geheime Zugangswerte, Tokens, private URLs und vergleichbare sensible Werte duerfen weder intern protokolliert noch exportiert werden.

## Zentrale Bereinigung

Vivicast verwendet eine zentrale Bereinigungsschicht fuer Diagnoseereignisse.

Sie wird angewendet:

1. bevor ein Diagnoseereignis dauerhaft geschrieben wird
2. bevor ein Export in den Ziel-OutputStream geschrieben wird

Die Bereinigung entfernt, maskiert oder neutralisiert mindestens:

- URLs und URL-Parameter
- Tokens, Cookies und HTTP-Header
- Benutzernamen, Passwoerter und sonstige Zugangswerte
- PIN und Backup-Passphrase
- private Quellenadressen und Stream-URLs
- rohe Playlist-, XMLTV- und EPG-Inhalte
- Provider- und Inhaltsnamen
- Suchverlauf

Wenn ein Feld nicht sicher als unkritisch eingeordnet werden kann, wird es verworfen oder durch einen neutralen Platzhalter ersetzt.

Feature-Code darf nicht direkt in Diagnose-Segmente schreiben, sondern muss die zentrale Bereinigungsschicht verwenden.

## Dateiformat

Der Export verwendet ein standardkonformes ZIP-Archiv.

```text
MIME-Type: application/zip
Dateiendung: .zip
Dateiname: vivicast-diagnostics-YYYYMMDD-HHmmss.zip
```

Das Archiv wird von Vivicast selbst mit den standardmaessigen Android-ZIP-APIs erzeugt. Es ist keine installierte ZIP- oder Dateimanager-App erforderlich.

Die Archivdaten werden direkt in den Ziel-OutputStream geschrieben. Das vollstaendige Archiv darf nicht zuerst als grosse Bytefolge im RAM aufgebaut werden.

Das ZIP-Archiv enthaelt verpflichtend diese UTF-8-codierten Eintraege:

```text
vivicast-diagnostics.log
diagnostics-metadata.json
```

## Inhalt von `vivicast-diagnostics.log`

Die Protokolldatei enthaelt ausschliesslich bereinigte technische Ereignisse:

- App-Start, App-Version und Abstuerze
- Playlist-Importe und Aktualisierungen mit Beginn, Ende, Ergebnis, Dauer und Anzahl verarbeiteter Eintraege
- EPG-Aktualisierung und Mapping mit Beginn, Ende, Ergebnis, Dauer und Anzahl
- Player-Startfehler, Retries, Reconnects, Decoder- und Timeshift-Fehler
- Netzwerkfehler mit Fehlerart, HTTP-Status und Dauer, jedoch ohne URL
- Backup-, Restore-, Cache- und Datenbankaktionen sowie zugehoerige Fehler
- bereinigte Stacktraces fuer Warnungen, Fehler und Abstuerze

## Inhalt von `diagnostics-metadata.json`

Die Metadatendatei enthaelt mindestens:

- App-Version und Build-Nummer
- Android-Version und Geraetemodell
- Datenbank-Version
- aktive App-Sprache und Zeitzone
- Exportzeitpunkt
- konfigurierte Aufbewahrungsdauer in Tagen
- feste Groessengrenzen und Segmentgrenze
- Beginn und Ende des tatsaechlich abgedeckten Protokollzeitraums
- `contentTruncated`
- `firstRetainedAt`
- `discardedSessionCount`
- `discardedSegmentCount`
- `discardedEventCount`
- eine Liste der im Export noch enthaltenen Diagnosesitzungen

Jede Diagnosesitzung enthaelt mindestens:

```text
sessionId
startedAt
endedAt
lastRecordedAt
endReason
endTimeAccuracy
active
segmentCount
firstRetainedAt
contentTruncated
```

Jedes noch vorhandene Segment ist mindestens durch folgende Metadaten rekonstruierbar:

```text
segmentIndex
firstRecordedAt
lastRecordedAt
byteSize
eventCount
closed
```

Bei einer zum Exportzeitpunkt aktiven Sitzung bleibt `endedAt` leer. Der Export verwendet fuer diese Sitzung `lastRecordedAt` als aktuellen Zeitraum-Endpunkt und kennzeichnet sie mit `active=true`.

## Diagnoseprotokollierung und Sitzungen

Interne Diagnoseprotokolle liegen ausschliesslich im privaten App-Speicher. Sie gehoeren weder in Room noch in das Standard-Backup.

Bei aktivierter Diagnoseprotokollierung beginnt mit jedem App-Prozessstart eine neue logische Diagnosesitzung. Wird die Protokollierung in einem bereits laufenden Prozess aktiviert, beginnt die Sitzung zum Aktivierungszeitpunkt.

Eine Sitzung kann wegen der Rotation aus mehreren physischen Segmentdateien bestehen. Interne Segmentdateinamen oder gleichwertige Metadaten enthalten mindestens den Sitzungsbeginn, eine stabile `sessionId` und den `segmentIndex`. Die Endzeit wird in den Sitzungsmetadaten gefuehrt und muss nicht durch eine spaetere Dateiumbenennung in den Dateinamen geschrieben werden.

Fuer jede Sitzung gelten diese Felder:

- `startedAt`: tatsaechlicher Beginn der Diagnosesitzung
- `lastRecordedAt`: Zeitpunkt des letzten dauerhaft geschriebenen Ereignisses
- `endedAt`: ermitteltes Sitzungsende; bei aktiver Sitzung leer
- `endReason`: `USER_EXIT`, `DIAGNOSTICS_DISABLED`, `CRASH`, `SYSTEM_KILL` oder `UNKNOWN`
- `endTimeAccuracy`: `EXACT`, `SYSTEM_REPORTED` oder `ESTIMATED`; bei aktiver Sitzung leer

Ein kontrolliertes Beenden setzt `endReason=USER_EXIT` und `endTimeAccuracy=EXACT`. Das Ausschalten der Diagnoseprotokollierung beendet die aktive Sitzung sofort mit `endReason=DIAGNOSTICS_DISABLED` und `endTimeAccuracy=EXACT`.

Bildschirm-Aus, Standby oder das Ausschalten des Fernsehers duerfen nicht allein als Sitzungsende gewertet werden. Solange der App-Prozess weiterlebt, bleibt die Sitzung aktiv.

Findet Vivicast beim naechsten App-Prozessstart eine noch offene vorherige Sitzung, wird sie nachtraeglich abgeschlossen:

1. Ab Android 11/API 30 wird ein passender Eintrag aus `ApplicationExitInfo` verwendet, sofern vorhanden. Dessen Zeitstempel wird als `endedAt` gespeichert; `endTimeAccuracy=SYSTEM_REPORTED`. Der Systemgrund wird auf `CRASH`, `SYSTEM_KILL` oder `UNKNOWN` abgebildet.
2. Ist kein passender Systemdatensatz verfuegbar, wird `endedAt=lastRecordedAt` gesetzt; `endTimeAccuracy=ESTIMATED`. Ein bereits eindeutig erkannter Absturz bleibt `CRASH`, andernfalls wird `UNKNOWN` verwendet.

Vivicast darf fuer ein nicht beobachtetes Prozessende keine scheinbar exakte Endzeit erfinden.

## Aufbewahrung und Exportzeitraum

Die Aufbewahrungsdauer ist auf 1 bis 7 Tage begrenzt; Standard ist 1 Tag.

Fuer die automatische Altersbereinigung gilt:

- geschlossene Sitzungen altern anhand von `endedAt`
- noch unvollstaendige Sitzungen altern anhand von `lastRecordedAt`
- die aktuell aktive Sitzung wird niemals vollstaendig geloescht
- Bereinigung laeuft beim App-Start, nach dem Abschluss einer Sitzung und unmittelbar nach einer Verkuerzung der Aufbewahrungsdauer
- das Ausschalten der Diagnoseprotokollierung loescht vorhandene Sitzungen nicht sofort

Der Diagnoseexport umfasst alle zum Exportzeitpunkt noch vorhandenen Segmente und Sitzungen.

Der ausgewiesene Gesamtzeitraum beginnt beim fruehesten tatsaechlich noch exportierten Ereignis und endet beim spaetesten tatsaechlich noch exportierten Ereignis. Bei einer aktiven Sitzung kann `lastRecordedAt` den Endpunkt bilden. Der Zeitraum darf weder pauschal aus der konfigurierten Tageszahl noch aus einem bereits durch Rotation verlorenen `startedAt` abgeleitet werden.

## Groessenbegrenzung und Rotation

Verbindliche feste v1-Grenzen:

```text
diagnosticsMaxTotalBytes = 20_971_520          // 20 MiB
diagnosticsSegmentMaxBytes = 2_097_152         // 2 MiB
diagnosticsMaxSegmentsPerSession = 3
diagnosticsMaxSessionLogBytes = 6_291_456      // 6 MiB
```

Diese Werte sind nicht benutzerkonfigurierbar.

Bevor das Anhaengen eines bereits bereinigten Ereignisses die Segmentgrenze ueberschreiten wuerde, wird das aktuelle Segment geschlossen und innerhalb derselben Sitzung das naechste Segment geoeffnet.

Sind bereits drei Segmente der Sitzung vorhanden, wird vor dem Anlegen des naechsten Segments das aelteste geschlossene Segment dieser Sitzung entfernt. Das aktuelle Schreibsegment wird niemals durch diese sitzungsinterne Rotation entfernt.

Passt ein einzelnes bereits bereinigtes Ereignis selbst nicht in ein leeres Segment, wird sein Nutzinhalt so gekuerzt, dass der Datensatz in das Segment passt. Der Datensatz muss `recordTruncated=true` enthalten. Die Bereinigung sensibler Daten erfolgt vor Groessenpruefung und Kuerzung.

Das Gesamtlimit umfasst alle internen Logsegmente sowie zugehoerige Sitzungs-, Segment- und Rotationsmetadaten im privaten App-Speicher. DataStore-Einstellungen und das direkt in ein externes Ziel gestreamte Diagnose-ZIP sind nicht Teil dieses Limits.

Vor jeder groessenbedingten Bereinigung wird zuerst die altersbasierte Aufbewahrungsbereinigung ausgefuehrt. Reicht der Platz danach fuer den naechsten Schreibvorgang nicht aus, werden die aeltesten abgeschlossenen Sitzungen anhand ihres Endzeitpunkts vollstaendig entfernt, bis der Schreibvorgang innerhalb des Gesamtlimits moeglich ist.

Die globale Groessenbereinigung entfernt keine Segmente der aktiven Sitzung. Deren aelteste geschlossene Segmente werden ausschliesslich durch die feste Drei-Segment-Rotation ersetzt. Nicht entfernt werden:

- das aktuell offene Schreibsegment
- die Metadaten der aktiven Sitzung

Kann ein bereinigtes Ereignis trotz Altersbereinigung, Entfernung abgeschlossener Sitzungen und sitzungsinterner Rotation nicht innerhalb der Grenzen geschrieben werden, wird dieses Ereignis verworfen und in `discardedEventCount` sowie `contentTruncated` ausgewiesen. Das Gesamtlimit darf nicht ueberschritten werden.

Rotationsmetadaten muessen kompakt bleiben und duerfen keine unbegrenzte Liste verworfener Einzelelemente fuehren.

Bei jeder groessenbedingten Entfernung oder Datensatzkuerzung werden die Trunkierungs- und Zaehlerfelder fortgeschrieben. `firstRetainedAt` bezeichnet den Zeitpunkt des fruehesten noch vorhandenen Ereignisses.

`vivicast-diagnostics.log` wird beim Export aus den noch vorhandenen Segmenten in chronologischer Reihenfolge erzeugt. Die Rotation veraendert weder ZIP-Dateinamen noch verpflichtende ZIP-Eintraege.

## Ausgeschlossene Daten

Der Export darf insbesondere nicht enthalten:

- Benutzername, Passwort, PIN oder Backup-Passphrase
- Tokens, Cookies, HTTP-Header oder sonstige Zugangsdaten
- Server-, M3U-, EPG- oder Stream-URLs
- rohe M3U-, XMLTV-, Playlist- oder EPG-Inhalte
- Provider-Namen, Sendernamen, Film- oder Serientitel
- Suchverlauf
- Datenbank-Dumps
- Screenshots
- ungefiltertes System-Logcat

Wenn eine technische Zuordnung erforderlich ist, duerfen ausschliesslich neutrale interne IDs wie `providerId=3` oder `channelId=128` verwendet werden.

Wenn Erstellen oder Schreiben des ZIP-Archivs fehlschlaegt, zeigt die App einen konkreten Exportfehler. Sie wechselt nicht stillschweigend auf ein anderes Dateiformat und hinterlaesst keine als erfolgreich gemeldete unvollstaendige Datei.

## Rechtliche Hinweise

Der Bereich enthaelt Einstiege fuer:

- Datenschutz
- Open-Source-Lizenzen
- Drittanbieter-Lizenzen

Diese Inhalte duerfen als lokale Seiten oder Dialoge umgesetzt werden.

## Update-Pruefung

Eine manuelle Update-Pruefung ist fuer v1 nicht verbindlich.

Wenn spaeter eine Update-Pruefung eingebaut wird, muss klar getrennt werden zwischen:

- App-Version anzeigen
- Update suchen
- Update installieren

## Daten zuruecksetzen

`Daten zuruecksetzen` gehoert nicht in `Über die App`.

Wenn diese Funktion spaeter umgesetzt wird, gehoert sie in einen eigenen gefaehrlichen Settings-Bereich oder in Backup/Restore mit klarer Bestaetigung.

## Abgrenzung

Nicht Teil von `Über die App`:

- Wiedergabelistenverwaltung
- EPG-Verwaltung
- Backup oder Restore
- Daten zuruecksetzen
- automatische Update-Installation
