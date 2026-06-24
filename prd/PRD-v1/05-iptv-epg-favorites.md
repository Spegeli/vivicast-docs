# Vivicast PRD v1
## Kapitel 5 - IPTV, EPG, Provider, Favoriten, Verlauf und Backup

Status: bereinigt v10

---

# 5. IPTV Provider

## Ziel

Vivicast unterstuetzt mehrere IPTV-Anbieter gleichzeitig.

Unterstuetzte Quellen:

```text
M3U
Xtream Codes
```

Der verbindliche Parser- und Quellenvertrag fuer M3U, Xtream Codes und XMLTV liegt in:

```text
prd/PRD-v1/12-parser-source-contracts.md
```

Der verbindliche atomare Import- und Refresh-Vertrag liegt in:

```text
prd/PRD-v1/07-background-jobs-performance.md
```

Der verbindliche Sicherheitsvertrag fuer HTTP/TLS, Secret Store und Diagnosebereinigung liegt in:

```text
prd/PRD-v1/08-android-tv-security.md
architecture/decisions/ADR-014-security-data-network-backup.md
```

## Provider-Isolation

Provider werden niemals automatisch zusammengefuehrt.

Gleiche Inhalte verschiedener Provider bleiben getrennt.

Provider-Namen muessen eindeutig sein.

---

# Provider hinzufuegen

Der Add Flow ist verbindlich:

1. Name eingeben
2. Quelltyp waehlen: M3U oder Xtream Codes
3. bei M3U Eingabeart waehlen: URL, Datei oder Zwischenablage
4. typabhaengige Daten eingeben
5. Verbindung testen
6. bei erfolgreichem Test speichern
7. direkt aktualisieren/importieren

Name ist Pflichtfeld.

Jeder Name darf nur einmal als Provider vorhanden sein.

Wenn der Name bereits existiert, wird ein Hinweis angezeigt und Speichern ist nicht moeglich.

## M3U

Eingabearten:

```text
URL
Datei
Zwischenablage
```

M3U hat keine Optionen fuer Live-TV, Filme oder Serien importieren.

M3U wird so importiert, wie die App die Playlist erhaelt.

Filme und Serien in M3U werden wie normale Playlist-Inhalte behandelt.

Bei Datei-Import wird keine dauerhafte Kopie der M3U-Datei lokal gespeichert.

Zwischenablage darf M3U-URL oder M3U-Inhalt enthalten.

Private M3U-URLs mit eingebetteten Zugangswerten werden wie geheime Werte behandelt.

HTTP-M3U-URLs sind erlaubt, werden aber als unsicher markiert. HTTPS wird bevorzugt.

M3U-Import nutzt toleranten Teilimport. Fehlerhafte Eintraege werden uebersprungen, solange verwertbare Eintraege importiert werden koennen.

## Xtream Codes

Felder:

```text
Server
Benutzername
Passwort oder Zugangswert
```

Serverformat:

```text
http://host
https://host
http://host:8080
https://host:8080
```

HTTP-Server sind erlaubt, werden aber als unsicher markiert. HTTPS wird bevorzugt.

Xtream Codes Importoptionen:

```text
Live-TV importieren
Filme importieren
Serien importieren
```

Xtream-Codes-Import nutzt toleranten Teilimport. Unvollstaendige Eintraege werden importiert, wenn Kern-ID und Name vorhanden sind.

## Verbindungstest vor Speichern

Vor dem Speichern muss die Quelle getestet werden.

Bei erfolgreichem Test wird gespeichert und direkt aktualisiert/importiert.

Bei fehlgeschlagenem Test wird nicht gespeichert.

Der Nutzer erhaelt einen Hinweis zum Grund, soweit ermittelbar.

Verbindungstests laufen ueber die zentrale Netzwerk- und Sicherheitsrichtlinie. TLS-Zertifikatsfehler werden nicht bypassed.

Beispiele:

```text
Name bereits vorhanden
URL ungueltig
Server nicht erreichbar
Anmeldedaten ungueltig
Datei nicht lesbar
Zwischenablage enthaelt keine nutzbare M3U-Quelle
```

Teilfehler einzelner Eintraege brechen einen spaeteren Import nicht ab. Der Nutzer erhaelt nach Abschluss einen zusammenfassenden Teilfehler-Status.

---

# Provider bearbeiten

Alle Felder duerfen bearbeitet werden, ausser der Quelltyp.

Ein Wechsel zwischen M3U und Xtream Codes ist nicht erlaubt.

Bearbeitbare Optionen:

```text
Aktivieren / Deaktivieren
Name
M3U-Daten oder Xtream-Codes-Zugangsdaten
EPG-Quellen und Prioritaeten
Logo-Prioritaet
Gruppen verwalten
Update-Optionen
Loeschen
```

Name bleibt Pflichtfeld und muss eindeutig sein.

Wenn M3U-URL, M3U-Inhalt, Datei-Quelle oder Xtream-Zugangsdaten geaendert werden, muss beim Speichern zuerst erneut ein Verbindungstest erfolgen.

Bei erfolgreichem Test wird gespeichert und direkt aktualisiert/importiert.

Bei fehlgeschlagenem Test wird nicht gespeichert.

## EPG Quellen pro Provider

Bearbeitung umfasst:

```text
EPG-Quellen zuweisen
EPG-Prioritaet aendern
```

## Logo Prioritaet pro Provider

Optionen:

```text
Globale Logo-Standardreihenfolge verwenden
Logos aus Playlist bevorzugen
Logos aus EPG bevorzugen
Logos aus lokalem Ordner bevorzugen
```

## Gruppen verwalten

Unterstuetzt:

```text
Gruppen anzeigen
Gruppen ausblenden
Gruppen sortieren
```

## Update Optionen pro Provider

Unterstuetzt:

```text
Aktualisierungsintervall der Wiedergabeliste
beim App-Start aktualisieren
jetzt aktualisieren
letzte Aktualisierung anzeigen
```

Wenn globale Hintergrundaktualisierung deaktiviert ist, laufen keine automatischen Aktualisierungen.

Manuelle Aktualisierung bleibt moeglich.

## Provider-Detailinformationen

Anzeigen, falls vorhanden:

```text
Ablaufdatum
maximale Verbindungen
letzte Aktualisierung
technische Zaehler
```

---

# Provider Status

Unterstuetzte Status:

```text
Aktiv
Aktiv mit Teilfehlern
Aktualisierung laeuft
Verbindungsfehler
Anmeldedaten ungueltig
Abgelaufen
Deaktiviert
Zugangsdaten erforderlich
```

---

# Provider deaktivieren

Verhalten:

```text
Status = Deaktiviert
```

Alle Daten bleiben erhalten.

Deaktivierte Provider erscheinen nicht in globaler Android-TV-Suche und nicht in Watch Next.

Zugehoerige Android-TV-Systemsucheintraege und Watch-Next-Publikationen werden entfernt oder gesperrt, bis der Provider wieder aktiviert und die Inhalte produktiv verfuegbar sind.

---

# Provider loeschen

Provider loeschen nutzt eine einfache Bestaetigung.

Wenn PIN-Schutz fuer Einstellungen aktiv ist, muss vor dem Loeschen zusaetzlich die PIN bestaetigt werden.

Mitgeloescht werden:

```text
Sender
Kategorien
Filme
Serien
Staffeln
Episoden
Favoriten
Playback Progress
Verlauf
Providerbezogene EPG-Zuordnungen
Android-TV-Systemsucheintraege
Watch-Next-Publikationen
```

Nicht geloescht:

```text
Globale EPG Quellen
```

Diese bleiben erhalten.

---

# Playlist Typ

Nicht aenderbar.

```text
M3U <-> Xtream Codes
```

nicht erlaubt.

---

# 5.1 Kategorien

Kategorien werden unveraendert vom Provider uebernommen.

Keine automatische Zusammenfuehrung.

Benutzer koennen Kategorien ausblenden und sortieren.

Fehlende Kategorien:

```text
__UNCATEGORIZED__
```

Anzeige:

```text
Nicht kategorisiert
```

---

# 5.2 EPG

## Ziel

Bereitstellung vollstaendiger Programmdaten.

EPG-Daten werden quellbezogen verarbeitet:

```text
EPG-Quelle
EPG-Kanal
EPG-Programm
```

Provider-Sender werden ueber Mapping und Provider-EPG-Prioritaeten mit EPG-Kanälen verbunden.

EPG-Programme werden nicht als providerbezogene Kopien gespeichert.

## EPG Quellen

EPG-Quellen werden global verwaltet und pro Provider zugeordnet.

Unterstuetzt:

```text
Mehrere Quellen
```

Eigenschaften:

```text
Name
Quelle oder URL
Zeitversatz
Aktiviert
```

Private EPG-URLs mit eingebetteten Zugangswerten werden wie geheime Werte behandelt.

## EPG Prioritaeten

Pro Provider koennen mehrere EPG-Quellen priorisiert werden.

Beispiel:

```text
EPG 1 -> Prioritaet 1
EPG 2 -> Prioritaet 2
EPG 3 -> Prioritaet 3
```

## Automatische Zuordnung

Sender werden automatisch zugeordnet.

Automatische Zuordnung darf manuelle Zuordnung nicht ueberschreiben.

## Manuelle Zuordnung

Benutzer kann Zuordnungen festlegen.

Beispiel:

```text
ARD HD -> Das Erste HD
```

Manuelle Zuordnungen sind Teil des Standard-Backups.

Manuelle Zuordnungen gewinnen immer vor automatischen Zuordnungen.

## EPG Refresh

Das globale EPG-Aktualisierungsintervall hat den Standardwert 24 Stunden.

Es gilt fuer den automatischen intervallgesteuerten Refresh. App-Start, Playlist-Aenderung und manuelle Aktualisierung bleiben separate Ausloeser.

Mehrere Provider koennen dieselbe EPG Quelle nutzen.

Die Quelle wird pro Refresh-Zyklus nur einmal aktualisiert.

## EPG Aufbewahrung

Globale Einstellungen:

```text
EPG-Vergangenheit behalten: 1 bis 14 Tage, Standard 1 Tag
EPG-Zukunft laden/behalten: 1 bis 14 Tage, Standard 7 Tage
```

Cleanup entfernt nur EPG-Programmdaten ausserhalb dieses Fensters.

EPG-Quellen, EPG-Kanäle, Provider-Zuordnungen und manuelle Mappings bleiben erhalten.

## Zeit- und Konfliktregeln

Alle Programmzeiten werden intern als UTC-Zeitpunkte gespeichert.

Der Zeitversatz der EPG-Quelle wird beim Import angewendet.

Fehlende Endzeiten duerfen aus dem naechsten Programmbeginn desselben EPG-Kanals abgeleitet werden.

Ueberlappungen und Duplikate brechen den Import nicht ab. Innerhalb derselben Quelle und desselben EPG-Kanals werden Duplikate ueber Startzeit und normalisierten Titel dedupliziert.

## Fehlgeschlagene Aktualisierung

Alte EPG-Daten bleiben erhalten.

Ein fehlgeschlagener oder abgebrochener Provider-Refresh veraendert keine produktiven Inhalte dieses Providers.

Ein Provider-Refresh mit Teilfehlern darf valide Inhalte uebernehmen. Inhalte duerfen nur dann als entfernt geloescht werden, wenn der betroffene Teilbereich vollstaendig gelesen und validiert wurde.

Ein erfolgreicher autoritativer Provider-Refresh entfernt nicht mehr gelieferte Inhalte und die zugehoerigen Favoriten, Verlaeufe und Wiedergabefortschritte.

## Sender ohne EPG

Anzeige:

```text
Keine Programminformationen verfuegbar
```

Sender bleibt sichtbar.

## EPG Quelle loeschen

Wenn eine EPG Quelle noch verwendet wird, wird eine Warnung angezeigt.

Loeschen bleibt moeglich.

---

# 5.3 Catch-Up

Unterstuetzung, wenn vom Provider angeboten.

Voraussetzungen:

```text
Sender unterstuetzt Catch-Up
+
EPG vorhanden
```

Zugriff:

- ueber vergangene EPG-Sendungen
- ueber Sender-Menue

Ohne EPG wird Catch-Up deaktiviert.

---

# 5.4 Favoriten

Unterstuetzte Inhalte:

```text
Sender
Filme
Serien
```

Favoriten werden ueber IDs gespeichert.

```text
providerId
mediaId
mediaType
```

## Live-TV

Live-TV Favoriten sind anbieteruebergreifend sichtbar, bleiben intern aber providergebunden.

Standardsortierung:

```text
Alphabetisch A-Z
```

Alternative Sortierungen:

```text
A-Z
Z-A
Manuelle Sortierung
```

## Filme

Standardsortierung:

```text
Zuletzt hinzugefuegt
```

## Serien

Standardsortierung:

```text
Zuletzt hinzugefuegt
```

## Favoritengruppen

Benutzerdefinierte Favoritengruppen werden nicht unterstuetzt.

Es existiert genau eine Favoritengruppe je Medientyp.

## Entfernte Inhalte

Wenn Inhalte verschwinden, werden zugehoerige Favoriten geloescht.

Favoriten sind Teil des Standard-Backups.

---

# 5.5 Verlauf

## Live-TV

Speicherung:

```text
Letzter Sender
Zuletzt gesehene Sender
Sehdauer
```

Pro Sender existiert nur ein Verlaufseintrag.

## Filme

Speicherung:

```text
Film
Position
Fortschritt
Abschlussstatus
Zuletzt angesehen
```

## Serien

Speicherung:

```text
Serie
Staffel
Episode
Position
Fortschritt
Abschlussstatus
```

## Suchverlauf

Suchverlauf speichert maximal 20 Eintraege.

Einzelne Eintraege und der gesamte Suchverlauf koennen geloescht werden.

## Verlauf loeschen

Unterstuetzt:

```text
Live-TV-Verlauf
Filmverlauf und Film-Wiedergabefortschritt
Serienverlauf und Episoden-Wiedergabefortschritt
Suchverlauf
Gesamten Verlauf
```

Verlaufslimits sind in v1 nicht frei konfigurierbar.

Der Suchverlauf bleibt fest auf maximal 20 Eintraege begrenzt.

Verlauf und Wiedergabefortschritt sind Teil des Standard-Backups, sofern nicht geloescht.

---

# 5.6 Continue Watching

## Filme

Eigene Kategorie:

```text
Fortsetzen
```

innerhalb des Filmbereichs.

## Serien

Eigene Kategorie:

```text
Fortsetzen
```

innerhalb des Serienbereichs.

## Home

Home zeigt Fortsetzen fuer Filme und Serien gemischt.

Live-TV erscheint nicht in Fortsetzen.

## Abschluss

Film-Fortsetzen enthaelt nur Filme mit fortsetzbarem Fortschritt und `isCompleted = false`.

Serien-Fortsetzen setzt eine nicht abgeschlossene Episode an ihrer gespeicherten Position fort. Ist die zuletzt relevante Episode abgeschlossen und eine naechste Episode verfuegbar, verweist der Serien-Eintrag stattdessen auf diese naechste Episode bei Position 0. Die abgeschlossene Episode selbst wird nicht fortgesetzt.

Filme und Episoden gelten ab mindestens 95 Prozent oder beim tatsaechlichen Medienende als abgeschlossen. Der Wert ist fuer v1 fest und nicht als Einstellung verfuegbar.

`Als gesehen markieren` setzt den Abschlussstatus ebenfalls. `Als ungesehen markieren` loescht den vollstaendigen zugehoerigen Wiedergabefortschritt.

Abgeschlossene Filme und abgeschlossene Episoden werden als direkte Resume-Ziele aus Home sowie Film- und Serien-Fortsetzen entfernt. Ein Serien-Eintrag darf danach auf die naechste verfuegbare Episode wechseln; nach Abschluss der letzten Episode verschwindet er, sofern kein anderer unvollstaendiger Episodenfortschritt existiert.

Der Abschlussstatus gilt bei Serien nur fuer einzelne Episoden. Fuer komplette Staffeln oder Serien existiert in v1 kein eigener Abschlussdatensatz.

Continue Watching basiert auf `PlaybackProgressEntity`.

---

# 5.7 Backup

Der verbindliche Backup-Datenvertrag liegt in:

```text
prd/PRD-v1/10-backup-import-requirements.md
```

## Ziele

```text
Lokaler Speicher
SMB
Google Drive
```

## Backup-Typen

```text
Standard-Backup
Verschluesseltes Vollbackup
```

Standard-Backup exportiert keine geheimen Zugangswerte und keine privaten Quellen-URLs mit eingebetteten Zugangswerten.

Verschluesseltes Vollbackup darf geheime Zugangswerte enthalten, wenn der Nutzer aktiv eine Backup-Passphrase setzt.

Verschluesselte Vollbackups nutzen das Schutzformat aus `prd/PRD-v1/10-backup-import-requirements.md` und `architecture/decisions/ADR-014-security-data-network-backup.md`.

Die Backup-Passphrase wird nicht gespeichert, nicht geloggt und nicht in Diagnoseexporte geschrieben.

## Restore

Restore aus Backup ist in v1 immer ein Ersetzen des Backup-Umfangs.

Die App fuehrt lokale und importierte Provider, EPG-Quellen, Favoriten, Verlaeufe, Fortschritte oder Einstellungen nicht zusammen.

Vor Restore werden Backup-Datei, Passphrase, Schema-Migration und Inhalt validiert.

Restore erfordert eine klare Bestaetigung und bei aktivem lokalem Einstellungsschutz oder lokaler Backup-/Restore-PIN-Schutzregel eine PIN-Abfrage.

Vor dem Ersetzen versucht die App ein internes Sicherheitsbackup der aktuellen lokalen Daten zu erstellen. Wenn dieses Sicherheitsbackup fehlschlaegt, fragt die App den Nutzer, ob Restore trotzdem fortgesetzt oder abgebrochen werden soll.

Die PIN-Abfrage vor Restore nutzt immer die aktuell lokale PIN.

PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus der Backup-Datei werden nach Restore nicht uebernommen.

Wenn Kindersicherung beim Export aktiv war, wird sie nach Restore deaktiviert und die App zeigt einen Hinweis zur manuellen Reaktivierung unter `Einstellungen > Kindersicherung`.

Lokale Provider und backupbezogene Nutzerdaten, die nicht im Backup enthalten sind, werden beim Restore entfernt.

`Als Kopie importieren` und Restore-Konfliktdialoge sind nicht Teil von v1.

Alte Backup-Schema-Versionen duerfen vor Restore migriert werden. Diese Schema-Migration ist kein Zusammenfuehren lokaler und importierter Daten.

## Backup-Teilrestore

Teilweises Wiederherstellen einzelner Backup-Bereiche ist nicht Teil von v1. Restore ersetzt immer den Backup-Umfang.

## Automatische Backups

Nicht unterstuetzt.

Backups werden ausschliesslich manuell gestartet.

## Backup Rotation

Eine konfigurierbare Backup-Rotation ist nicht Teil von v1, sofern spaeter nicht explizit entschieden.
