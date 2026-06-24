# Vivicast PRD v1
## Kapitel 12 - Parser- und Quellenvertraege

Status: verbindlich v5

## Zweck

Diese Datei definiert die fachlichen Parser-Vertraege fuer M3U, Xtream Codes und XMLTV.

Sie ist die verbindliche Quelle fuer Importtoleranz, Quellenattribute, Identitaetsableitung, Teilfehler und Parser-Sicherheitsregeln.

Commit-, Rollback- und Staging-Regeln fuer Import und Refresh liegen in `prd/PRD-v1/07-background-jobs-performance.md`.

Parser-Golden-Tests, Mockserver-Faelle und Performancebudgets liegen in `prd/PRD-v1/13-test-strategy.md`.

## Grundregel

Vivicast verwendet toleranten Teilimport.

Einzelne fehlerhafte Eintraege brechen einen Import nicht ab, solange die Quelle insgesamt lesbar ist und verwertbare Eintraege enthaelt.

Fehlerhafte Eintraege werden uebersprungen und intern als technische Zaehler erfasst.

Der Nutzer sieht nach einem Import mit uebersprungenen Eintraegen einen klaren Teilfehler-Status.

Diagnose und Importstatus duerfen keine Zugangswerte, Tokens, Cookies, HTTP-Header, URLs, Rohdaten, Provider-/Inhaltsnamen oder ungefilterte Playlist-/XMLTV-Inhalte enthalten.

## Netzwerk- und Sicherheitsregeln

HTTPS wird bevorzugt.

HTTP bleibt fuer nutzer- oder providerdefinierte M3U-, Xtream-, XMLTV-, EPG- und Stream-Endpunkte erlaubt, muss aber als unsicher markiert werden.

Alle HTTP- und HTTPS-Quellenzugriffe laufen ueber die zentrale Netzwerk- und Sicherheitsrichtlinie.

TLS-Zertifikatsfehler duerfen nicht bypassed werden.

Redirects duerfen nur zu `http` oder `https` fuehren. Finale Redirect-URLs sind Laufzeitdaten und duerfen nicht dauerhaft gespeichert, geloggt oder exportiert werden.

Provider-spezifische HTTP-Header-, Cookie- und User-Agent-Einstellungen sind nicht Teil von v1. Der globale User-Agent aus Allgemein wird verwendet, sofern technisch anwendbar.

## M3U-Vertrag

### Encoding und Zeilenenden

M3U-Import bevorzugt UTF-8.

UTF-8-BOM wird akzeptiert.

Zeilenenden `LF`, `CRLF` und gemischte Zeilenenden werden tolerant verarbeitet.

Wenn der Inhalt nicht als gueltiger Text gelesen werden kann, bricht der Import mit konkretem Quellenfehler ab.

### Struktur

Unterstuetzt wird Extended M3U mit `#EXTM3U`, `#EXTINF` und nachfolgender Stream-Zeile.

Eine Stream-Zeile ohne unmittelbar zuordenbares `#EXTINF` wird uebersprungen.

Ein `#EXTINF` ohne folgende nutzbare Stream-Zeile wird uebersprungen.

Kommentar- und unbekannte Steuerzeilen brechen den Import nicht ab.

### Bekannte Attribute

Bekannte Attribute:

```text
tvg-id
tvg-name
tvg-logo
group-title
tvg-chno
catchup
catchup-days
catchup-source
```

Unbekannte Attribute werden nicht als Fehler behandelt. Sie duerfen intern gezaehlt werden, erzeugen aber keine sichtbare Warnung pro Eintrag.

### Identitaet

M3U besitzt keine verlaessliche Pflicht-ID.

Stable-Key-Ableitung:

1. Medientyp + `tvg-id`, sofern vorhanden
2. Medientyp + normalisierter Name + normalisierte Gruppe + Kanalnummer, sofern vorhanden
3. Medientyp + normalisierter Name + normalisierte Gruppe + geheimnisfrei gehashte Stream-Identitaet

Geheime URL-Bestandteile, Tokens, Benutzerinformationen oder Zugangswerte duerfen nicht im Klartext Bestandteil eines Stable Keys, Logs, Diagnoseexports oder Backups sein.

### Gruppe, Logo und Kanalnummer

Fehlende Gruppe wird intern als `__UNCATEGORIZED__` gespeichert und als `Nicht kategorisiert` angezeigt.

`tvg-logo` ist optional. Ungueltige oder nicht ladbare Logos brechen den Import nicht ab.

`tvg-chno` ist optional. Ungueltige Kanalnummern werden ignoriert.

### Catch-Up

Catch-Up wird senderbezogen interpretiert.

`catchup`, `catchup-days` und `catchup-source` werden verwendet, sofern plausibel.

Catch-Up ist nur verfuegbar, wenn Sender, Provider und EPG-Kontext es erlauben.

V1 unterstuetzt fuer M3U-Catch-Up die Modi `default` und `append`.

`catchup-source` darf als Template genutzt werden. Platzhalter duerfen erst beim Wiedergabestart aus dem EPG-Kontext ersetzt werden, insbesondere Startzeit, Endzeit und Dauer.

Eine erzeugte Catch-Up-URL wird nicht dauerhaft als Klartext gespeichert und darf nicht in Logs, Diagnoseexporten, Backups oder sichtbaren Fehlermeldungen erscheinen.

### Duplikate und Teilfehler

Duplikate innerhalb derselben M3U-Quelle werden ueber den Stable Key zusammengefuehrt. Der spaetere Eintrag darf nicht mehrere lokale Sender fuer dieselbe stabile Identitaet erzeugen.

Eintraege ohne verwertbaren Namen oder ohne verwertbare Stream-Referenz werden uebersprungen.

Bei Teilfehlern zeigt die App einen zusammenfassenden Status, zum Beispiel `Import abgeschlossen mit Teilfehlern`.

### Grosse Dateien

M3U-Import muss streaming- oder chunkfaehig implementiert werden.

Die UI darf grosse Playlists nicht vollstaendig in UI-State laden.

Fortschritt und Abbruch muessen technisch moeglich sein, auch wenn v1 keine detaillierte Fortschrittsanzeige erzwingt.

## Xtream-Codes-Vertrag

### Server und Authentifizierung

Serveradresse erlaubt:

```text
http://host
https://host
http://host:8080
https://host:8080
```

Serveradressen werden normalisiert.

Benutzername und Passwort/Zugangswert werden geschuetzt gespeichert und nicht in Room abgelegt.

Zugangswerte duerfen nicht in Logs, Diagnoseexporten, Backups ohne Verschluesselung oder sichtbaren Fehlermeldungen erscheinen.

### Endpunkte

Verbindlicher v1-Basisendpunkt:

```text
player_api.php
```

Verwendet werden daraus Live-TV-, VOD-, Serien-, Kategorie- und Detailinformationen, sofern der Provider sie liefert.

Stream-URLs und `get.php`-artige URLs werden fuer Wiedergabe abgeleitet, aber nicht dauerhaft als Klartext gespeichert.

XMLTV-/EPG-URLs duerfen aus Providerdaten abgeleitet werden, sofern technisch verfuegbar und sicher behandelbar.

### Importoptionen

Xtream Codes bietet Importoptionen:

```text
Live-TV importieren
Filme importieren
Serien importieren
```

Deaktivierte Importbereiche werden nicht importiert und nicht als Fehler behandelt.

### Remote-IDs

Remote-ID-Ableitung:

- Kategorien: `category_id`
- Live-TV und Filme: `stream_id`
- Serien: `series_id`
- Staffeln: `series_id + seasonNumber`
- Episoden: `episode_id`, falls vorhanden; sonst `series_id + seasonNumber + episodeNumber`

Eintraege ohne verwertbare Kern-ID oder ohne verwertbaren Namen werden uebersprungen.

### Unvollstaendige Antworten

Unvollstaendige Antworten fuehren zu Teilimport, wenn Kern-ID und Name vorhanden sind.

Fehlende optionale Metadaten wie Poster, Logo, Beschreibung, Bewertung, Jahr, Dauer, Trailer oder Cast brechen den Import nicht ab.

Fehlende Kategorien werden als `__UNCATEGORIZED__` behandelt, sofern der Eintrag sonst importierbar ist.

### Timeouts, Wiederholungen und Parallelitaet

Netzwerkaufrufe muessen Timeouts besitzen.

Wiederholungen sind begrenzt und duerfen Zugangsdaten nicht in Fehlermeldungen offenlegen.

Parallele Xtream-Requests werden intern begrenzt. Diese Begrenzung ist keine v1-UI-Einstellung.

### Catch-Up und Metadaten

Catch-Up wird genutzt, wenn Xtream-Daten, Sender und EPG-Kontext es erlauben.

Xtream-Catch-Up wird beim Wiedergabestart aus Archiv-/Catch-Up-Metadaten, Stream-ID, EPG-Zeitfenster und geschuetzten Zugangsdaten abgeleitet.

Die finale Xtream-Catch-Up-URL wird nicht dauerhaft als Klartext gespeichert und darf nicht in Logs, Diagnoseexporten, Backups oder sichtbaren Fehlermeldungen erscheinen.

Provider-Metadaten wie Ablaufdatum, maximale Verbindungen oder technische Zaehler werden angezeigt, falls vorhanden, aber fehlende Werte sind kein Fehler.

## XMLTV-Vertrag

### Encoding und Komprimierung

XMLTV-Import bevorzugt UTF-8 und respektiert die XML-Deklaration, sofern vorhanden.

UTF-8-BOM wird akzeptiert.

Unterstuetzt:

```text
plain XML
gzip
ZIP mit genau einer XML-Datei
```

ZIP-Dateien mit keiner oder mehreren XML-Dateien werden abgelehnt.

### Modellabbildung

XMLTV-Kanäle werden zu `EPGChannelEntity`.

XMLTV-Programme werden zu `EPGProgramEntity`.

`channel id` wird als externe EPG-Kanal-ID verwendet.

EPG-Programme werden quell- und EPG-kanalbezogen gespeichert und nicht als providerbezogene Kopien angelegt.

### Pflichtfelder

Programme ohne `channel` werden uebersprungen.

Programme ohne `start` werden uebersprungen.

Programme ohne Titel duerfen importiert werden, wenn Quelle, Kanal und Startzeit verwertbar sind; sie erhalten einen neutralen lokalen Fallback-Titel fuer Anzeige und Suche.

### Zeitregeln

XMLTV-Zeitzonen aus `start` und `stop` werden ausgewertet.

Programmzeiten werden intern als UTC-Zeitpunkte gespeichert.

`timeShiftMinutes` der EPG-Quelle wird beim Import angewendet.

Fehlendes `stop` bricht den Import nicht ab.

Fehlende Endzeiten duerfen aus dem naechsten Programmbeginn desselben EPG-Kanals abgeleitet werden.

### Duplikate, Ueberlappungen und Teilfehler

Duplikate werden innerhalb derselben EPG-Quelle und desselben EPG-Kanals ueber `startTimeUtc + normalizedTitle` dedupliziert.

Ueberlappungen brechen den Import nicht ab.

Fuer Anzeige und Now/Next gewinnt das plausiblere Zeitfenster. Bei gleich plausiblen Eintraegen gewinnt der spaeter importierte Datensatz innerhalb derselben Quelle und desselben EPG-Kanals.

Fehlerhafte Programme werden uebersprungen und als technische Zaehler erfasst.

### Sicherheit

Rohe XMLTV-Inhalte werden nicht in Logs, Diagnoseexporte oder Backups geschrieben.

XMLTV-URLs mit eingebetteten Zugangswerten werden wie geheime Werte behandelt.

## Importstatus und Fehler

Importstatus:

```text
Erfolgreich
Erfolgreich mit Teilfehlern
Fehlgeschlagen
Abgebrochen
```

Teilfehler enthalten nur technische Zaehler, zum Beispiel gelesene Eintraege, importierte Eintraege, uebersprungene Eintraege und Fehlerkategorien.

Ein kompletter Importfehler liegt vor, wenn die Quelle nicht gelesen werden kann, Authentifizierung fehlschlaegt, die Antwort kein nutzbares Format besitzt oder kein verwertbarer Eintrag importiert werden konnte.

Bei fehlgeschlagenem Playlist-Refresh bleiben alte Providerdaten erhalten.

Bei fehlgeschlagenem EPG-Refresh bleiben alte EPG-Daten bis zum normalen Cleanup erhalten.
