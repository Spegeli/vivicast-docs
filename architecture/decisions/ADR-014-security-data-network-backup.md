# ADR-014 - Security for Data, Network and Backup

## Status

Accepted

## Kontext

Vivicast verarbeitet lokale App-Einstellungen, nutzerbezogene Daten, IPTV-Zugangsdaten, private Quellen-URLs, PIN-Schutz und Backup-Dateien.

Ohne verbindliche Klassifizierung und Schutzregeln koennten Implementierungen versehentlich geheime Werte in Room, DataStore, Logs, Diagnoseexporte oder Standard-Backups schreiben.

Der normative Produktvertrag liegt in:

```text
prd/PRD-v1/08-android-tv-security.md
prd/PRD-v1/10-backup-import-requirements.md
prd/PRD-v1/11-about-app-requirements.md
```

## Entscheidung

Vivicast klassifiziert lokale Daten in vier Schutzklassen:

- `Normal`: nicht geheime App-Einstellungen, Layout-/Optikwerte, nicht private Provider-Metadaten und normale Quellenadressen ohne Zugangswerte.
- `Sensibel`: Favoriten, Verlauf, Suchverlauf, Wiedergabefortschritt, EPG-Mappings und vergleichbare nutzerbezogene Daten.
- `Geheim`: Xtream-Benutzername und Zugangswert, SMB-Zugangsdaten, Google-Drive-Token, private M3U-/EPG-/Stream-URLs mit Tokens oder eingebetteten Zugangswerten, HTTP-Header, Cookies, Backup-Passphrase und PIN-Pruefwerte.
- `Sicherheitswirksam lokal`: PIN-Pruefwerte, aktive PIN-Freigaben, PIN-Sperrstatus und Kindersicherung-Schutzflags. Diese Werte sind lokal wirksam und werden nie aus Backups wiederhergestellt.

Lokale Speicherung:

- Room speichert strukturierte normale Daten und nicht geheime nutzerbezogene Daten.
- DataStore speichert kleine normale App-Einstellungen und nicht geheime Flags.
- Android Keystore speichert nicht exportierbare Schluessel.
- Ein geschuetzter lokaler Secret Store speichert geheime Werte verschluesselt im privaten App-Speicher. Die Verschluesselung nutzt einen Keystore-gebundenen Schluessel.
- Neue geschuetzte Speicherung wird nicht auf `EncryptedSharedPreferences` oder Jetpack `security-crypto` aufgebaut.

Wenn Keystore-Schluessel oder Secret Store verloren, beschaedigt oder nicht entschluesselbar sind, gelten die geschuetzten lokalen Geheimnisse als verloren. Provider, EPG-Quellen und Backup-Ziele bleiben als Konfiguration erhalten, werden aber als `Zugangsdaten erforderlich` markiert, sofern sie Geheimnisse benoetigen. Standard-Backups koennen diese Werte nicht wiederherstellen. Verschluesselte Vollbackups duerfen sie nach korrekter Passphrase wiederherstellen. PIN und Kindersicherung werden in diesem Fall deaktiviert und muessen neu eingerichtet werden.

Standard-Backups enthalten keine geheimen Werte. Verschluesselte Vollbackups duerfen geheime Quellen- und Zielzugangsdaten nur enthalten, wenn der Nutzer aktiv eine Backup-Passphrase setzt.

Das verschluesselte Vollbackup nutzt einen versionierten Container:

- unverschluesselte Metadaten ohne Geheimnisse: Backup-Schema-Version, App-Version, Erstellungszeitpunkt, Exportmodus und Datenbereiche
- KDF-Metadaten: `kdf`, `salt`, `iterations` oder gleichwertige Parameter
- Verschluesselungsmetadaten: `cipher`, `nonce`, `authTag`
- verschluesselter Payload: `ciphertext`

V1 verwendet AES-GCM fuer den Payload. PBKDF2-HMAC-SHA256 ist der bevorzugte v1-KDF, weil er ohne zusaetzliche Kryptografie-Abhaengigkeit verfuegbar ist. Argon2id darf nur eingefuehrt werden, wenn die zusaetzliche Abhaengigkeit bewusst entschieden und dokumentiert wird. Die Passphrase wird nie gespeichert, nie geloggt und nie in Diagnoseexporte geschrieben. Eine falsche Passphrase bricht Restore vor jeder lokalen Datenaenderung ab.

PIN-Regeln:

- PINs bestehen aus vier Ziffern.
- Eingabe erfolgt verdeckt ueber die Android-/TV-Systemtastatur als numerische Passwort-Eingabe.
- Gespeichert wird nur ein langsamer gesalzener Pruefwert, niemals PIN-Klartext.
- Nach fuenf falschen Eingaben wird die PIN-Eingabe temporaer gesperrt.
- Sperrdauern sind 30 Sekunden, 60 Sekunden und danach 5 Minuten.
- App-Neustart setzt eine laufende Sperre nicht zurueck.
- Erfolgreiche PIN-Eingabe erteilt nur fuer den angefragten Schutzbereich eine Freigabe fuer die aktuelle App-Sitzung.
- Session-Freigaben sind nur im Speicher, nicht persistent und nicht backupfaehig.

Netzwerkregeln:

- HTTPS wird bevorzugt.
- HTTP bleibt fuer nutzer- oder providerdefinierte IPTV-, EPG- und Stream-Endpunkte erlaubt, weil reale Quellen haeufig HTTP verwenden.
- HTTP wird sichtbar als unsicher markiert und muss durch eine zentrale Netzwerkpolicy freigegeben werden.
- App-eigene Dienste duerfen kein HTTP verwenden.
- Es gibt keine unkontrollierte breite Manifest-Freigabe fuer Klartextverkehr.
- TLS-Zertifikatsfehler werden nicht bypassed.
- Certificate Pinning ist fuer nutzerdefinierte IPTV-Server kein v1-Default.
- Redirects sind nur auf `http` und `https` erlaubt; finale Redirect-URLs bleiben Laufzeitdaten und werden nicht gespeichert oder geloggt.
- Provider-spezifische User-Agent-, Header- oder Cookie-Einstellungen sind nicht Teil von v1. Es gibt nur den globalen User-Agent.

Diagnose:

- Diagnoseereignisse werden vor dauerhaftem Schreiben zentral bereinigt.
- Der Exportstrom wird vor Ausgabe erneut zentral geprueft.
- Blockiert oder neutralisiert werden insbesondere URLs, Tokens, Header, Cookies, Passwoerter, PIN, Backup-Passphrase, Zugangswerte, Rohdaten, Provider-/Inhaltsnamen und Suchverlauf.
- Wenn ein Wert nicht sicher als unkritisch eingeordnet werden kann, wird er verworfen oder neutralisiert statt exportiert.

## Gruende

- IPTV-Quellen verwenden haeufig unsichere oder tokenisierte URLs.
- Standard-Backups sollen risikoarm bleiben und keine Geheimnisse ausleiten.
- Vollbackups brauchen bewusstes Nutzerhandeln und ein nachvollziehbares Schutzformat.
- PIN- und Kindersicherungszustand darf nach Restore oder Secret-Store-Verlust nicht unbemerkt reaktiviert werden.
- Zentrale Redaction reduziert die Gefahr, dass einzelne Feature-Logs Sicherheitsregeln umgehen.

## Konsequenzen

- Implementierung braucht einen dedizierten Secret Store mit Keystore-Schluessel.
- Datenmodell und Backup-Vertrag muessen Geheimnisse strikt von normalen Konfigurationswerten trennen.
- Provider- und EPG-Quellen muessen `Zugangsdaten erforderlich` als sichtbaren Zustand behandeln.
- Vollbackup-Import und -Export brauchen Passphrase-Dialoge und Fehlerbehandlung vor lokalen Datenaenderungen.
- PIN-Fehlversuche, Sperrstufe und `lockedUntil` sind lokale Sicherheitszustaende und kein Restore-Ziel.
- Netzwerkzugriffe fuer Quellen und Player muessen ueber eine zentrale Policy laufen.
- Diagnose-Logging darf nur bereinigte Ereignisse dauerhaft schreiben.
