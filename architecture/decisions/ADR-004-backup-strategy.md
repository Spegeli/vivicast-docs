# ADR-004 – Backup Strategy

## Status

Accepted

## Kontext

Vivicast verwaltet Provider, EPG-Quellen, Favoriten, Verlauf, Playback Progress, Einstellungen, Backup-Einstellungen und Kindersicherung lokal.

Backups muessen diese Daten nachvollziehbar sichern und bei Bedarf wiederherstellen koennen, ohne unbemerkt geheime Zugangswerte oder grosse Cache-Daten zu exportieren.

Der fachliche Backup-Datenvertrag liegt in:

```text
prd/PRD-v1/10-backup-import-requirements.md
```

Der Sicherheits- und Krypto-Vertrag fuer Vollbackup, Secret Store, PIN und Netzwerk liegt in:

```text
architecture/decisions/ADR-014-security-data-network-backup.md
```

## Entscheidung

Backups werden ausschliesslich manuell durch den Benutzer gestartet.

Automatische Backups sind nicht Bestandteil von Version 1.0.

Es gibt zwei Backup-Typen:

- Standard-Backup
- verschluesseltes Vollbackup

Standard-Backups exportieren keine geheimen Zugangswerte und keine privaten Quellen-URLs mit eingebetteten Zugangswerten.

Verschluesselte Vollbackups duerfen geheime Zugangswerte enthalten, wenn der Nutzer aktiv eine Backup-Passphrase setzt.

Das verschluesselte Vollbackup nutzt das Schutzformat aus ADR-014 und dem Backup-Datenvertrag. Passphrase, KDF-Parameter, Nonce, Ciphertext und Authentifizierungstag werden vor jeder lokalen Datenaenderung geprueft. Die Passphrase wird nie gespeichert oder geloggt.

Restore in Version 1.0:

Backups ersetzen den gesamten Backup-Umfang.

Restore fuehrt lokale und importierte Daten nicht zusammen.

`Als Kopie importieren` ist nicht Bestandteil von Version 1.0.

Vor jeder lokalen Datenaenderung liest die App die Backup-Datei, prueft Passphrase, migriert kompatible alte Backup-Schema-Versionen und validiert den Backup-Inhalt.

Nach erfolgreicher Validierung versucht die App ein internes Sicherheitsbackup der aktuellen lokalen Daten zu erstellen. Wenn dieses Sicherheitsbackup fehlschlaegt, fragt die App den Nutzer, ob Restore trotzdem fortgesetzt oder abgebrochen werden soll.

Wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN fuer Backup/Restore verlangt, muss vor Restore die aktuell lokale PIN abgefragt werden.

PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags aus einer Backup-Datei werden nicht wiederhergestellt.

Nach Restore ist Kindersicherung deaktiviert. Wenn die Backup-Datei ausweist, dass Kindersicherung beim Export aktiv war, zeigt die App einen Hinweis zur manuellen Reaktivierung.

Teilweises Wiederherstellen einzelner Backup-Bereiche ist nicht Bestandteil von Version 1.0.

Falsche Backup-Passphrase fuehrt zum Abbruch des Imports.

## Gruende

- Keine unbemerkten Hintergrunddateien.
- Benutzer kontrolliert Zeitpunkt und Speicherort.
- Reduzierte Komplexitaet fuer Version 1.0.
- Standard-Backup bleibt risikoarm.
- Verschluesseltes Vollbackup bleibt bewusst aktive Nutzerentscheidung.
- Restore hat ein eindeutiges v1-Verhalten und vermeidet Konfliktentscheidungen zwischen lokalem Stand und Backup-Stand.
- Restore darf keine alte PIN- oder Schutzkonfiguration unbemerkt reaktivieren.

## Konsequenzen

- Kein automatischer Schutz vor Datenverlust ohne manuelles Backup.
- Backup-UI muss klar kommunizieren, welcher Backup-Typ verwendet wird.
- Restore-UI muss klar kommunizieren, dass der Backup-Umfang ersetzt wird.
- Restore-UI muss klar kommunizieren, wenn Kindersicherung im Backup aktiv war und nach Restore deaktiviert wurde.
- Alte Backup-Schema-Versionen duerfen migriert werden; alte Merge-, Konflikt- oder Kopie-Felder werden nicht als Restore-Merge interpretiert.
- Cache-Daten werden nach Restore neu aufgebaut.
- Quellen mit fehlenden Zugangsdaten werden sichtbar markiert.
- Backup-Migration muss unbekannte Felder ignorieren oder kontrolliert abbrechen und fehlende Felder mit Standardwerten ergaenzen.
