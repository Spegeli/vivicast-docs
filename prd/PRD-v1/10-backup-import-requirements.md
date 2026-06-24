# Vivicast PRD v1
## Kapitel 10 - Backup, Import und Restore Anforderungen

Status: verbindlich v8

## Zweck

Diese Datei definiert fachliche Anforderungen fuer Backup, Import und Restore.

Sie konkretisiert den Backup-Bereich aus `prd/PRD-v1/04-search-settings-player-requirements.md`.

Backup-/Restore-Roundtrip-Tests und Legacy-Schema-Migrationsfaelle liegen in `prd/PRD-v1/13-test-strategy.md`.

## Ziel

Backup und Restore sollen Nutzereinstellungen, Quellenkonfiguration und persoenliche App-Daten wiederherstellbar machen, ohne grosse Cache-Daten oder unsichere Klartextdaten unkontrolliert zu exportieren.

## Backup-Ziele

Unterstuetzte Ziele:

- lokaler Speicher
- SMB
- Google Drive

Backups werden manuell gestartet.

Automatische Backups sind nicht Teil von v1.

## Backup-Umfang

Standard-Backup enthaelt:

- App-Einstellungen
- Wiedergabelisten-Metadaten
- M3U-URL, falls die URL keine eingebetteten Zugangswerte enthaelt
- Xtream-Codes-Serveradresse ohne Benutzername oder Zugangswert
- EPG-Quellen-Konfiguration, sofern keine geheimen Zugangswerte enthalten sind
- EPG-Zuordnungen pro Provider
- EPG-Prioritaeten
- Logo-Prioritaeten
- Gruppen-Sichtbarkeit und Gruppensortierung
- Favoriten
- Live-TV-Verlauf, sofern Nutzer ihn nicht geloescht hat
- Suchverlauf, sofern Nutzer ihn nicht geloescht hat
- Filmverlauf und Film-Wiedergabefortschritt, sofern Nutzer sie nicht geloescht hat
- Serienverlauf und Episoden-Wiedergabefortschritt, sofern Nutzer sie nicht geloescht hat
- nicht-sicherheitswirksame Information, ob Kindersicherung beim Export aktiv war, fuer Restore-Zusammenfassung und Hinweis
- Optik- und Wiedergabe-Einstellungen

Nicht im Standard-Backup enthalten:

- PIN-Klartext, PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags
- Medien-Cache-Dateien wie heruntergeladene Senderlogos, Film-Poster, Serien-Poster, Staffelbilder und Episodenbilder
- EPG-Programmdaten-Cache
- Stream-Puffer
- temporaere Dateien
- Fehlerlogs
- lokale M3U-Dateien, die beim Import nicht dauerhaft gespeichert wurden
- Zugangsdaten oder geheime Werte, sofern kein verschluesseltes Vollbackup gewaehlt wurde
- private Quellen-URLs mit eingebetteten Zugangswerten

## Sensible Daten

Sensible Daten sind insbesondere:

- Xtream-Codes-Benutzername
- Xtream-Codes-Zugangswert
- private M3U-URLs mit Token oder eingebetteten Zugangswerten
- private EPG-URLs mit Token oder eingebetteten Zugangswerten
- private Stream-URL-Templates mit Token oder eingebetteten Zugangswerten
- HTTP-Header und Cookies
- SMB-Zugangsdaten
- Google-Drive-Token
- Backup-Passphrase
- PIN-Pruefwerte

Regeln:

- PIN darf nie im Klartext exportiert werden.
- PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags werden nicht aus Backups wiederhergestellt.
- Falls eine Backup-Datei alte oder zusaetzliche PIN- oder Schutzflag-Felder enthaelt, muessen sie beim Restore ignoriert werden.
- Google-Drive-Token werden nicht exportiert.
- SMB-Zugangsdaten werden nicht exportiert.
- Standard-Backup exportiert keine geheimen Zugangswerte.
- Wenn eine Quelle nach Restore Zugangsdaten benoetigt, wird sie als `Zugangsdaten erforderlich` markiert.

## Verschluesseltes Vollbackup

Optional darf die App ein verschluesseltes Vollbackup anbieten.

Vollbackup darf sensible Quellen-Zugangsdaten enthalten, wenn der Nutzer aktiv eine Backup-Passphrase setzt.

Regeln:

- Vollbackup ist nicht Standard.
- Passphrase wird nicht gespeichert.
- Passphrase wird nicht geloggt und nie in Diagnoseexporte geschrieben.
- Ohne Passphrase kann ein Vollbackup nicht wiederhergestellt werden.
- Die UI muss vor Export klar anzeigen, dass sensible Zugangsdaten enthalten sind.
- Restore eines Vollbackups erfordert die Passphrase.
- Eine falsche Passphrase bricht Restore vor jeder lokalen Datenaenderung ab.
- PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags werden auch bei verschluesselten Vollbackups nicht wiederhergestellt.

Schutzformat fuer verschluesselte Vollbackups:

```text
backup-schema-version
app-version
created-at
export-mode = ENCRYPTED_FULL
data-sections
kdf
salt
iterations
cipher
nonce
ciphertext
authTag
```

Die unverschluesselten Metadaten duerfen keine Geheimnisse, privaten URLs, Header, Cookies, PIN-Pruefwerte oder Provider-Inhaltsdaten enthalten.

Der verschluesselte Payload nutzt AES-GCM. V1 bevorzugt PBKDF2-HMAC-SHA256 als KDF. Argon2id darf nur verwendet werden, wenn die zusaetzliche Abhaengigkeit bewusst entschieden und dokumentiert wird.

Salt und Nonce muessen pro Backup neu erzeugt werden.

Der Authentication Tag wird vor jeder lokalen Datenaenderung geprueft.

PIN-Pruefwerte, aktive PIN-Freigaben, PIN-Sperrstatus und Kindersicherung-Schutzflags bleiben auch im verschluesselten Payload ausgeschlossen.

## Backup-Datei

Backup-Datei enthaelt mindestens:

- Backup-Schema-Version
- App-Version
- Erstellungszeitpunkt
- Paketname
- Datenbank-Version
- Exportmodus: Standard oder verschluesselt
- Datenbereiche
- Information, ob Kindersicherung beim Export aktiv war, sofern zutreffend

Metadaten einer Standard-Backup-Datei duerfen keine geheimen Werte enthalten.

Metadaten eines verschluesselten Vollbackups duerfen nur technische Krypto- und Exportinformationen enthalten. Geheimnisse liegen ausschliesslich im verschluesselten Payload.

Die Backup-Datei muss versioniert sein, damit zukuenftige App-Versionen Migrationen pruefen koennen.

## Backup-Schema-Migration

Backups aus aelteren kompatiblen App-Versionen duerfen vor dem Restore in das aktuelle Backup-Schema migriert werden.

Diese Migration ist kein Zusammenfuehren lokaler und importierter Daten.

Regeln:

- alte Felder fuer Zusammenfuehren, Konfliktaktionen oder Kopie-Import werden ignoriert oder in das aktuelle Ersetzen-Modell ueberfuehrt
- unbekannte optionale Felder werden ignoriert
- fehlende kompatible Felder werden mit dokumentierten Standardwerten ergaenzt
- nicht migrierbare, beschaedigte oder inkompatible Backups brechen vor jeder lokalen Datenaenderung ab

## Restore-Modell

Version 1 unterstuetzt fuer Backup-Import ausschliesslich Ersetzen.

Restore fuehrt kein Zusammenfuehren lokaler und importierter Daten durch.

`Als Kopie importieren` ist nicht Teil von v1.

Regeln:

- Restore erfordert eine klare Bestaetigung.
- Wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN fuer Backup/Restore verlangt, muss vor Restore die aktuell lokale PIN bestaetigt werden.
- Die App liest die Backup-Datei, prueft Passphrase, migriert das Backup-Schema bei Bedarf und validiert den Backup-Inhalt, bevor lokale Daten veraendert werden.
- Nach erfolgreicher Validierung versucht die App ein internes Sicherheitsbackup der aktuellen lokalen Daten zu erstellen.
- Wenn das interne Sicherheitsbackup fehlschlaegt, fragt die App den Nutzer, ob Restore trotzdem fortgesetzt oder abgebrochen werden soll.
- Bei Abbruch bleiben lokale Daten unveraendert.
- Nach Bestaetigung ersetzt Restore den gesamten Backup-Umfang durch den Backup-Stand.
- Lokale Room-IDs aus dem Backup werden nicht als Identitaet verwendet.
- Standard-Backup verwendet keine geheimen Klartextdaten als Identitaet.

## Restore-Verhalten

Nach Restore:

- App-Einstellungen werden angewendet.
- lokale Provider, EPG-Quellen, EPG-Zuordnungen, Favoriten, Verlauf und Wiedergabefortschritt entsprechen dem Backup-Stand.
- Provider und andere Daten, die lokal vorhanden waren, aber nicht im Backup enthalten sind, sind nach Restore entfernt.
- Kindersicherung ist deaktiviert.
- PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus dem Backup werden nicht uebernommen.
- Wenn die Backup-Datei ausweist, dass Kindersicherung beim Export aktiv war, zeigt die App nach dem Restore einen Hinweis, dass die PIN-Funktion vor dem Backup aktiv war, nach dem Restore deaktiviert wurde und in `Einstellungen > Kindersicherung` manuell wieder aktiviert werden muss.
- Quellen mit vollstaendigen Daten koennen aktualisiert werden.
- Quellen mit fehlenden Zugangsdaten werden als `Zugangsdaten erforderlich` markiert.
- EPG-Quellen koennen neu aktualisiert werden.
- Medien-Cache-Dateien werden nicht erwartet, sondern bei Bedarf neu aufgebaut.
- Favoriten, Live-TV-Verlauf und Wiedergabefortschritt werden ueber stabile Referenzen aus dem Backup wiederhergestellt und duerfen bis zum naechsten erfolgreichen Provider-Refresh pending bleiben.
- Manuelle EPG-Zuordnungen werden ueber stabile Provider-, Sender-, EPG-Quellen- und EPG-Kanal-Schluessel wiederhergestellt.
- Bei Datenbank- oder Backup-Schema-Unterschied wird eine Migration versucht oder der Import vor jeder lokalen Datenaenderung abgebrochen.

Standard-Backups enthalten keine vollstaendige IPTV-Bibliothek. Sie enthalten Provider-/EPG-Konfiguration und stabile Referenzen auf nutzerbezogene Daten.

Standard-Backups uebernehmen keine geheimen Zugangswerte und verwenden keine alten lokal gespeicherten Zugangsdaten weiter.

Verschluesselte Vollbackups duerfen Quellenzugangsdaten nach erfolgreicher Passphrase-Pruefung wiederherstellen. PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags bleiben auch dann ausgeschlossen.

Wenn lokal geschuetzte Geheimnisse durch Keystore- oder Secret-Store-Verlust nicht mehr entschluesselt werden koennen, bleiben Provider und EPG-Quellen erhalten. Betroffene Quellen werden als `Zugangsdaten erforderlich` markiert. Ein Standard-Backup kann diese Geheimnisse nicht wiederherstellen; ein verschluesseltes Vollbackup kann sie nach korrekter Passphrase wiederherstellen.

## Importfehler

Importfehler muessen konkret angezeigt werden.

Beispiele:

- Backup-Datei ungueltig
- Backup-Version nicht unterstuetzt
- Backup beschaedigt
- Passphrase falsch
- internes Sicherheitsbackup fehlgeschlagen
- Ziel nicht erreichbar
- Speicherzugriff verweigert
- Nicht genug Speicherplatz
- Schema-Migration fehlgeschlagen

Fehler duerfen bestehende lokale Daten nicht teilweise unkontrolliert zerstoeren.

## Sicherheitsbestaetigungen

Diese Aktionen brauchen eine klare Bestaetigung:

- Restore
- Import eines Vollbackups mit sensiblen Zugangsdaten
- Export eines Vollbackups mit sensiblen Zugangsdaten
- Fortsetzen eines Restore trotz fehlgeschlagenem internem Sicherheitsbackup
- Loeschen eines vorhandenen Backups

Wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN fuer Backup/Restore verlangt, wird fuer Restore und sicherheitsrelevante Backup-Aktionen die aktuell lokale PIN abgefragt. Eine in der Backup-Datei enthaltene fruehere PIN oder fruehere Schutzkonfiguration darf fuer diese Autorisierung nicht verwendet werden.

## Abgrenzung

Nicht Teil von v1:

- automatische Backup-Zeitplaene
- Cloud-Synchronisierung zwischen Geraeten
- Multi-Profil-Backup
- Backup von Medieninhalten
- Backup von Cache-Dateien
