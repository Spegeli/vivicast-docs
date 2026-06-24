# Vivicast PRD v1
## Kapitel 8 - Android TV Integration und Sicherheit

Status: bereinigt v11

---

# 8. Android TV Integration

## Ziel

Vivicast soll sich wie eine native Android-TV-App verhalten und relevante Android-TV-Integrationen unterstuetzen.

Konkrete Screen-Layouts, Fokusdarstellung und Bedienpfade sind Designquellen zugeordnet.

Verbindliche Designquellen:

- `design/screens/06-search.md`
- `design/interaction/focus.md`
- `design/interaction/nav.md`
- `design/design-system/`

Verbindliche Architekturquelle fuer globale Android-TV-Suche, Deep Links, Watch Next und Kindersicherungsabgrenzung:

- `architecture/decisions/ADR-008-android-tv-integration.md`

---

# 8.1 Sprachsuche

Sprachsuche wird in der Suche unterstuetzt.

Aktivierung:

1. Benutzer startet Sprachsuche explizit
2. Android-Sprachsuche startet
3. erkannter Text wird in die Suche uebernommen
4. lokale Suche aktualisiert Ergebnisse

Sprachsuche startet nicht automatisch.

Nicht beim Oeffnen der Suche.

Nicht allein durch Fokus auf das Suchfeld.

---

# 8.2 Android TV globale Suche

Android-TV-Suche wird unterstuetzt.

Suchquellen:

- Live-TV
- Filme
- Serien

Nicht enthalten:

- EPG-Suchergebnisse
- Episoden als eigene Treffer
- Catch-Up
- geschuetzte Inhalte, solange der jeweilige Kindersicherungs-Schutz aktiv ist

Treffer oeffnen direkt den passenden Inhalt in Vivicast.

Der Android-TV-Suchindex ist ein abgeleiteter Systemindex aus produktiven Room-Daten.

Er darf nicht aus Import-/Refresh-Staging befuellt werden.

Index-Update oder Cleanup ist erforderlich bei:

- erfolgreichem Provider-Refresh
- Provider-Deaktivierung
- Provider-Loeschung
- Restore
- Migration
- Aenderung von Kindersicherung-Schutzregeln

Suchtreffer verwenden stabile Deep-Link-Ziele und keine lokalen Room-IDs.

---

# 8.3 Deep Links

Deep Links werden unterstuetzt.

Beispiele:

```text
vivicast://channel/{providerStableKey}/{channelStableKey}
vivicast://movie/{providerStableKey}/{movieStableKey}
vivicast://series/{providerStableKey}/{seriesStableKey}
vivicast://episode/{providerStableKey}/{episodeStableKey}
```

Verwendung:

- Android TV Suche
- Watch Next
- interne Navigation

Deep Links verwenden stabile fachliche Schluessel:

- Provider: `providerStableKey`
- Sender: `providerStableKey + CHANNEL + channelStableKey`
- Film: `providerStableKey + MOVIE + movieStableKey`
- Serie: `providerStableKey + SERIES + seriesStableKey`
- Episode: `providerStableKey + EPISODE + episodeStableKey`

Deep Links duerfen keine lokalen Room-IDs, Stream-URLs, Tokens, Zugangswerte, HTTP-Header oder Cookies enthalten.

Deep Links, Android-TV-Suchergebnisse und Watch Next haben Vorrang vor dem Startbereich, oeffnen aber keinen unkontrollierten Fallback.

Wenn das Ziel fehlt, geloescht wurde, pending ist, der Provider deaktiviert ist oder Zugangsdaten fehlen, zeigt Vivicast einen kontrollierten Fehler- oder Nicht-verfuegbar-Zustand.

Wenn das Ziel aktuell geschuetzt ist, muss die aktuelle lokale PIN-Freigabe erfolgreich sein, bevor der Inhalt geoeffnet oder abgespielt wird.

---

# 8.4 Watch Next

Watch Next wird unterstuetzt fuer:

- Filme
- Serienepisoden

Nicht unterstuetzt fuer:

- Live-TV
- Catch-Up
- externe Player-Ergebnisse
- geschuetzte Inhalte, solange der jeweilige Kindersicherungs-Schutz aktiv ist

Filme zeigen Wiedergabefortschritt, solange sie nicht abgeschlossen sind. Ab mindestens 95 Prozent, beim tatsaechlichen Medienende oder nach `Als gesehen markieren` wird der Film aus Watch Next entfernt.

Serien zeigen Serie, Staffel und Episode.

Nach Abschluss einer Episode wird Watch Next auf die naechste verfuegbare Episode aktualisiert. Nach Abschluss der letzten Episode wird der Serien-Eintrag entfernt, sofern kein anderer nicht abgeschlossener Episodenfortschritt existiert.

`Als gesehen markieren` nutzt dieselbe Abschlusslogik. `Als ungesehen markieren` loescht den gespeicherten Episodenfortschritt; ohne weiteren fortsetzbaren Episodenfortschritt besteht daraus kein Watch-Next-Eintrag.

Watch Next wird ausschliesslich aus produktiven Fortschrittsdaten des internen Vivicast-Players und manuellen Gesehen-/Ungesehen-Aktionen gepflegt.

Externe Player erzeugen, aktualisieren oder loeschen keine Watch-Next-Eintraege.

Pending Restore-Referenzen werden nicht in Watch Next veroeffentlicht, bis ein erfolgreicher Provider-Refresh sie mit lokalen Entities verbunden hat.

Watch-Next-Eintraege muessen entfernt oder aktualisiert werden bei:

- Abschluss eines Films oder einer Episode
- `Als gesehen markieren`
- `Als ungesehen markieren`
- Provider-Deaktivierung
- Provider-Loeschung
- Restore
- Migration
- Aenderung von Kindersicherung-Schutzregeln

---

# 8.5 Continue Watching

Continue Watching wird unterstuetzt fuer:

- Filme
- Serien

Abgeschlossene Filme und Episoden sind keine direkten Resume-Ziele. Bei Serien darf ein Eintrag nach einer abgeschlossenen Episode auf die naechste verfuegbare Episode bei Position 0 wechseln.

Nicht unterstuetzt fuer:

- Live-TV

Continue Watching innerhalb der App folgt denselben Abschluss- und Providerstatusregeln wie Watch Next, bleibt aber eine App-interne Ansicht. Geschuetzte Inhalte duerfen innerhalb der App erst nach erfolgreicher PIN-Freigabe geoeffnet oder abgespielt werden.

---

# 8.6 Android TV Bedienanforderungen

Die App muss vollstaendig bedienbar sein mit:

- D-Pad
- OK
- Zurueck
- CH+
- CH-

Fokus muss auf jedem interaktiven Element sichtbar sein.

Konkrete Fokusdarstellung liegt im Design-System und in `design/interaction/focus.md`.

PIN-Eingaben nutzen die Android-/TV-Systemtastatur als numerische Passwort-Eingabe.

Vivicast zeigt keine eigene Zifferntastatur fuer PINs.

PIN-Felder bleiben verdeckt, bieten keine Zwischenablage, keine Autovervollstaendigung und keine Klartextanzeige.

Nach vier Ziffern wird nicht automatisch bestaetigt. Der Nutzer aktiviert bewusst die sichtbare Aktion.

---

# 9. Sicherheit

## Ziel

Schutz sensibler Zugangsdaten, nutzerbezogener Daten, lokaler Sicherheitszustaende, Backups, Diagnoseexporte und Netzwerkzugriffe.

Verbindliche Architekturquelle:

- `architecture/decisions/ADR-014-security-data-network-backup.md`

---

# 9.1 Datenklassifizierung

Vivicast unterscheidet vier Schutzklassen:

```text
Normal
Sensibel
Geheim
Sicherheitswirksam lokal
```

`Normal` umfasst nicht geheime App-Einstellungen, Layout-/Optikwerte, normale Quellenadressen ohne Zugangswerte und nicht private Provider-Metadaten.

`Sensibel` umfasst nutzerbezogene Daten wie Favoriten, Live-TV-Verlauf, Suchverlauf, Wiedergabefortschritt und EPG-Mappings.

`Geheim` umfasst insbesondere:

- Xtream-Codes-Benutzername
- Xtream-Codes-Zugangswert
- SMB-Zugangsdaten
- Google-Drive-Token
- private M3U-, EPG- oder Stream-URLs mit Tokens oder eingebetteten Zugangswerten
- HTTP-Header, Cookies und vergleichbare Zugangswerte
- Backup-Passphrase
- PIN-Pruefwerte

`Sicherheitswirksam lokal` umfasst:

- PIN-Pruefwerte
- PIN-Fehlversuchs- und Sperrstatus
- aktive PIN-Freigaben
- Kindersicherung-Schutzflags

Sicherheitswirksam lokale Werte werden nie aus Backups wiederhergestellt.

---

# 9.2 Lokale Speicherung

Room speichert strukturierte normale App-Daten, grosse Bibliotheken und nicht geheime nutzerbezogene Daten. Room speichert keine geheimen Zugangswerte.

DataStore speichert kleine normale App-Einstellungen und lokale nicht geheime Flags. Sicherheitswirksam lokale Flags duerfen in DataStore oder gleichwertigem privaten Speicher liegen, sind aber kein Restore-Ziel.

Android Keystore speichert nicht exportierbare Schluessel.

Ein geschuetzter lokaler Secret Store speichert geheime Werte verschluesselt im privaten App-Speicher. Die Verschluesselung nutzt einen Keystore-gebundenen Schluessel.

Vivicast baut neue geschuetzte Speicherung nicht auf `EncryptedSharedPreferences` oder Jetpack `security-crypto` auf.

Normale nicht-geheime Server- oder Quellenadressen duerfen als Konfiguration gespeichert werden, wenn sie fuer die Funktion notwendig sind.

Private Quellenadressen mit eingebetteten Tokens, Zugangswerten oder personenbezogenen Zugriffsdaten werden wie geheime Werte behandelt.

---

# 9.3 Verlust geschuetzter lokaler Daten

Wenn Android-Keystore-Schluessel oder der lokale Secret Store verloren, beschaedigt oder nicht entschluesselbar sind:

- geheime lokale Quellen- und Zielzugangsdaten gelten als verloren
- Provider, EPG-Quellen und Backup-Ziele bleiben als Konfiguration erhalten
- Quellen oder Ziele, die Geheimnisse benoetigen, werden als `Zugangsdaten erforderlich` markiert
- Standard-Backups koennen diese Geheimnisse nicht wiederherstellen
- verschluesselte Vollbackups duerfen Geheimnisse nach korrekter Passphrase wiederherstellen
- PIN und Kindersicherung werden deaktiviert und muessen bei Bedarf neu eingerichtet werden

---

# 9.4 URLs, HTTP und TLS

HTTPS wird bevorzugt.

HTTP bleibt fuer nutzer- oder providerdefinierte IPTV-, EPG- und Stream-Endpunkte erlaubt, weil reale Quellen haeufig HTTP verwenden.

HTTP-Quellen muessen sichtbar als unsicher markiert werden.

Alle HTTP-Nutzung muss durch eine zentrale Netzwerk- und Sicherheitsrichtlinie laufen. App-eigene Dienste duerfen kein HTTP verwenden.

Es gibt keine unkontrollierte breite Manifest-Freigabe fuer Klartextverkehr.

TLS-Zertifikatsfehler duerfen nicht bypassed werden.

Certificate Pinning ist fuer nutzerdefinierte IPTV-Server kein v1-Default.

Provider-spezifische User-Agent-, Header- oder Cookie-Einstellungen sind nicht Teil von v1. Vivicast bietet nur den globalen User-Agent unter Allgemein.

HTTP-Weiterleitungen duerfen nur auf `http` oder `https` zeigen. Finale Redirect-URLs bleiben Laufzeitdaten und werden nicht dauerhaft gespeichert oder geloggt.

Parser fuer M3U, Xtream Codes und XMLTV duerfen Zugangswerte, Tokens, private URLs, HTTP-Header, Rohdaten und Provider-/Inhaltsnamen nicht in Logs, Diagnoseexporte oder unverschluesselte Backups schreiben.

Der vollstaendige Parser- und Quellenvertrag liegt in `prd/PRD-v1/12-parser-source-contracts.md`.

---

# 9.5 Backup Sicherheit

Standard-Backups enthalten keine geheimen Zugangswerte, keine privaten Quellen-URLs mit eingebetteten Zugangswerten und keine PIN-Pruefwerte.

Verschluesselte Vollbackups duerfen geheime Quellen- und Zielzugangsdaten enthalten, wenn der Nutzer aktiv eine Backup-Passphrase setzt.

Passphrase-Regeln:

- Passphrase wird nicht gespeichert
- Passphrase wird nicht geloggt
- Passphrase erscheint nicht in Diagnoseexporten
- falsche Passphrase bricht Restore vor jeder lokalen Datenaenderung ab

Vollbackup-Schutzformat:

- versionierter Container
- unverschluesselte Metadaten ohne Geheimnisse
- `kdf`, `salt` und `iterations` oder gleichwertige KDF-Parameter
- AES-GCM-Payload mit `nonce`, `ciphertext` und `authTag`
- v1 bevorzugt PBKDF2-HMAC-SHA256 als KDF

Wiederherstellung:

1. lokale PIN pruefen, falls aktuell lokal erforderlich
2. Backup lesen
3. Passphrase pruefen, falls verschluesselt
4. Backup-Schema migrieren, falls kompatibel
5. Inhalt validieren
6. internes Sicherheitsbackup versuchen
7. nach Bestaetigung Backup-Umfang ersetzen

PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags aus Backups werden nicht wiederhergestellt. Nach Restore ist Kindersicherung deaktiviert und muss bei Bedarf manuell neu aktiviert werden.

---

# 9.6 PIN-Schutz

PINs bestehen aus vier Ziffern.

PIN-Eingaben nutzen die Android-/TV-Systemtastatur als numerische Passwort-Eingabe.

Vivicast zeigt keine eigene Zifferntastatur fuer PINs.

PIN-Felder bleiben verdeckt, bieten keine Zwischenablage, keine Autovervollstaendigung und keine Klartextanzeige.

Nach vier Ziffern wird nicht automatisch bestaetigt. Der Nutzer aktiviert bewusst die sichtbare Aktion.

Gespeichert wird nur ein langsamer gesalzener PIN-Pruefwert, niemals PIN-Klartext.

Nach fuenf falschen PIN-Eingaben wird die Eingabe temporaer gesperrt.

Sperrdauern:

```text
30 Sekunden
60 Sekunden
5 Minuten
```

Eine laufende PIN-Sperre wird durch App-Neustart nicht aufgehoben.

Erfolgreiche PIN-Eingabe gibt nur den angefragten Schutzbereich fuer die aktuelle App-Sitzung frei.

Aktive PIN-Freigaben sind nur im Speicher, nicht persistent und nicht backupfaehig.

---

# 9.7 Diagnose und Logs

Der bereinigte Diagnoseexport wird unterstuetzt.

Diagnoseereignisse muessen vor dauerhaftem Schreiben zentral bereinigt werden.

Vor Export wird der gesamte Ausgabestrom als zusaetzliche Schutzschicht erneut zentral geprueft.

Vor dauerhaftem Schreiben und vor Export zentral entfernen oder unkenntlich machen:

- Benutzernamen und Passwoerter
- PIN und Backup-Passphrase
- Tokens, Cookies und HTTP-Header
- sonstige Zugangsdaten
- Server-, M3U-, EPG- und Stream-URLs
- rohe Playlist-, XMLTV- und EPG-Inhalte
- Provider- und Inhaltsnamen
- Suchverlauf
- Datenbank-Dumps und Screenshots

Wenn ein Wert nicht sicher als unkritisch eingeordnet werden kann, wird er verworfen oder neutralisiert statt exportiert.

Ungefiltertes System-Logcat darf nicht exportiert werden.

Bereinigte Stacktraces sind nur fuer Warnungen, Fehler und Abstuerze erlaubt.

Technische Zuordnungen duerfen bei Bedarf ausschliesslich neutrale interne IDs verwenden.

Der Export enthaelt `vivicast-diagnostics.log` und `diagnostics-metadata.json` gemaess `prd/PRD-v1/11-about-app-requirements.md`.

Interne Diagnosesitzungen und Segmente duerfen nur im privaten App-Speicher liegen. Sie sind nicht Teil des Standard-Backups.

Die Aufbewahrungsdauer ist auf 1 bis 7 Tage begrenzt und steht standardmaessig auf 1 Tag. Das interne Gesamtlimit betraegt 20 MiB, die Segmentgrenze 2 MiB und die Sitzungsgrenze drei Segmente beziehungsweise 6 MiB Logdaten. Diese Groessengrenzen sind keine Benutzereinstellungen.

---

# 9.8 Datenschutz

Vivicast speichert Daten lokal.

Nicht vorgesehen:

- Cloud-Profil
- Cloud-Synchronisierung
- Benutzerkonto
- Tracking
- Analytics
- Werbenetzwerke

---

# 9.9 Sichere Wiederherstellung

Wenn ein Backup Provider enthaelt, aber keine Zugangsdaten:

- Provider importieren
- Status: `Zugangsdaten erforderlich`
- Benutzer kann Provider bearbeiten und Daten nachtragen

---

# Abgrenzung

Diese Datei definiert Android-TV-Integrations- und Sicherheitsanforderungen.

Nicht hier definiert:

- Suchscreen-Layout
- Fokus-Effekt-Design
- Navigationspfade innerhalb der UI
- visuelle Tokens

Diese Details liegen in den Designquellen.
