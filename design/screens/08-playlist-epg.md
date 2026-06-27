# 08 - Playlist and EPG Management

Status: verbindlich v10

## Zweck

Dieser Screen beschreibt die Verwaltung von Wiedergabelisten, Provider-Zugaengen und EPG-Quellen innerhalb der Einstellungen.

Fachliche Regeln zu M3U, Xtream Codes, XMLTV, Providerstatus und EPG-Priorisierung bleiben im PRD.

Parser- und Quellenvertrag: `prd/PRD-v1/12-parser-source-contracts.md`.

Atomarer Import- und Refresh-Vertrag: `prd/PRD-v1/07-background-jobs-performance.md`.

## Quellen

- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `design/components/settings.md`
- `design/interaction/focus.md`

## Layout-Zonen

1. Einstellungsnavigation
2. Quellliste
3. Detailbereich
4. Aktionsbereich
5. Add- und Edit-Dialoge

## Initialfokus

Der Fokus startet auf der ersten vorhandenen Wiedergabeliste.

Wenn keine Wiedergabeliste existiert, startet der Fokus auf `Wiedergabeliste hinzufügen`.

## Wiedergabeliste hinzufügen

Der Flow ist mehrstufig:

1. Name eingeben
2. Quelltyp wählen: M3U oder Xtream Codes
3. bei M3U Eingabeart wählen: URL, Datei oder Zwischenablage
4. typabhängiges Formular ausfuellen
5. Verbindung testen
6. bei Erfolg speichern
7. direkt aktualisieren/importieren

Name ist Pflichtfeld.

Name muss eindeutig sein.

Wenn der Name bereits existiert, wird ein Fehler direkt am Feld angezeigt.

Der Quelltyp kann nach dem Speichern nicht geändert werden.

Der Add Flow enthält keine User-Agent-Option.

User-Agent wird global in `Einstellungen > Allgemein` gesetzt.

## M3U Add-Formular

M3U Eingabearten:

- URL
- Datei
- Zwischenablage

M3U URL zeigt:

- URL

HTTP-URLs sind erlaubt, werden aber als unsicher markiert. HTTPS wird bevorzugt.

M3U Add- und Edit-Formulare dürfen keine eigenen HTTP-Header-, Cookie- oder User-Agent-Optionen anzeigen.

M3U Datei zeigt:

- Datei auswählen

M3U Zwischenablage zeigt:

- Inhalt oder URL aus Zwischenablage übernehmen

Bei M3U gibt es keine Importauswahl für Live-TV, Filme oder Serien.

Bei M3U-Dateiimport wird keine dauerhafte Kopie der Datei lokal gespeichert.

M3U Add- und Edit-Formulare dürfen keine eigene User-Agent-Option anzeigen.

M3U-Import kann mit Teilfehlern abschließen. Die UI zeigt dann einen zusammenfassenden Status statt einzelner Rohdaten.

## Xtream Codes Add-Formular

Felder:

- Server
- Benutzername
- Passwort

Server erlaubt http, https und Portangabe, zum Beispiel `http://host:8080`.

HTTP-Server werden als unsicher markiert. HTTPS wird bevorzugt.

Importoptionen:

- Live-TV importieren
- Filme importieren
- Serien importieren

Xtream-Codes Add- und Edit-Formulare dürfen keine eigene User-Agent-Option anzeigen.

Xtream-Codes-Import kann mit Teilfehlern abschließen. Unvollstaendige, aber verwertbare Eintraege dürfen importiert werden.

## Verbindungstest

Speichern fuehrt zuerst einen Verbindungstest aus.

Bei Erfolg wird gespeichert und direkt aktualisiert/importiert.

Bei Fehler wird nicht gespeichert.

Fehler werden direkt im Formular angezeigt.

Teilfehler aus einem späteren Import werden als Quellenstatus oder Aktualisierungshistorie zusammengefasst.

Der Verbindungstest verwendet den globalen User-Agent, sofern technisch anwendbar.

Verbindungstests laufen über die zentrale Netzwerk- und Sicherheitsrichtlinie. TLS-Zertifikatsfehler werden nicht bypassed.

## Wiedergabelistenliste

Ein Eintrag zeigt:

- Name
- Typ: M3U oder Xtream Codes
- Status
- letzter Refresh
- Anzahl Live-TV, Filme und Serien, falls bekannt

Statuswerte werden als Status Badge angezeigt.

Teilfehler dürfen als zusammenfassender Status wie `Aktiv mit Teilfehlern` oder in der Aktualisierungshistorie angezeigt werden.

Bei fehlgeschlagenem oder abgebrochenem Refresh bleiben alte Inhalte sichtbar.

## Wiedergabelisten-Aktionen

Primaere Aktionen:

- Wiedergabeliste hinzufügen
- alle Wiedergabelisten aktualisieren

Aktionen pro Wiedergabeliste:

- Details öffnen
- bearbeiten
- aktualisieren
- aktivieren
- deaktivieren
- löschen

## Wiedergabelisten-Details und Bearbeiten

Der Detailbereich zeigt und erlaubt Bearbeitung von:

- Aktivieren / Deaktivieren
- Name
- M3U-Daten oder Xtream-Codes-Zugangsdaten
- EPG-Quellen und Prioritäten
- Logo-Priorität
- Gruppen verwalten
- Update-Optionen
- Löschen

Der Quelltyp kann nicht geändert werden.

Wenn URL, Datei-Quelle, M3U-Inhalt oder Xtream-Zugangsdaten geändert werden, muss vor dem Speichern erneut getestet werden.

Bei erfolgreichem Test wird gespeichert und direkt aktualisiert/importiert.

## Detailinformationen

Anzeigen, falls vorhanden:

- Ablaufdatum
- maximale Verbindungen
- letzte Aktualisierung
- technische Zaehler

## EPG-Quellenliste

EPG-Quellen werden global verwaltet und pro Provider zugeordnet.

EPG-Daten werden quellbezogen als EPG-Quelle, EPG-Kanal und EPG-Programm gespeichert. Provider-Sender erhalten Programme über Mapping und Priorität.

Ein EPG-Eintrag zeigt:

- Name
- Status
- letzter Refresh
- Zeitversatz
- Anzahl Programme, falls bekannt
- Nutzung durch Provider

## EPG-Quellen-Aktionen

Primaere Aktionen:

- EPG-Quelle hinzufügen
- EPG-Quelle bearbeiten
- EPG-Quelle löschen
- manuelle Zuordnung öffnen
- Priorität ändern
- EPG jetzt aktualisieren
- Aktualisierungshistorie anzeigen

EPG-Quellen Add- und Edit-Formulare dürfen keine eigene User-Agent-Option anzeigen.

Der globale User-Agent aus Allgemein wird für EPG-HTTP-Anfragen verwendet, sofern technisch anwendbar.

HTTP-EPG-Quellen sind erlaubt, werden aber als unsicher markiert. HTTPS wird bevorzugt. EPG-Formulare enthalten keine eigenen Header-, Cookie- oder User-Agent-Optionen.

EPG-Programme werden nicht als providerbezogene Kopien dargestellt oder verwaltet.

## EPG-Quelle hinzufügen und bearbeiten

Ein EPG-Formular zeigt mindestens:

- Name
- Quelle oder URL
- Zeitversatz
- Aktivstatus

Fehler werden direkt am Feld oder im Formular angezeigt.

## EPG-Priorität

Ein Provider kann mehrere EPG-Quellen haben.

Prioritäten:

- EPG 1 = Priorität 1
- EPG 2 = Priorität 2
- EPG 3 = Priorität 3

Niedrigere Prioritätszahl gewinnt bei konkurrierenden EPG-Daten.

Manuelle Zuordnung gewinnt immer vor automatischer Zuordnung.

## EPG-Aktualisierung

Die EPG-Gruppe enthält globale Aktualisierungsoptionen:

- globales Aktualisierungsintervall; Standard 24 Stunden
- EPG-Vergangenheit behalten; 1 bis 14 Tage; Standard 1 Tag
- EPG-Zukunft laden/behalten; 1 bis 14 Tage; Standard 7 Tage
- beim App-Start aktualisieren
- bei Playlist-Änderung aktualisieren
- jetzt aktualisieren
- letzte Aktualisierung anzeigen
- Aktualisierungshistorie anzeigen

Das globale Intervall gilt nur für den automatischen intervallgesteuerten Refresh. App-Start und Playlist-Änderung bleiben separate Ausloeser.

Die EPG-Aufbewahrung steuert nur lokale EPG-Programmdaten. Quellen, EPG-Kanäle, Provider-Zuordnungen und manuelle Mappings bleiben erhalten.

Wenn Hintergrundaktualisierung in Allgemein deaktiviert ist, werden automatische EPG-Aktualisierungen nicht ausgefuehrt.

Manuelle EPG-Aktualisierung bleibt möglich.

## Löschen

Löschen nutzt eine einfache Bestätigung.

Wenn PIN-Schutz für Einstellungen aktiv ist, muss vor dem Löschen zusätzlich die PIN bestätigt werden.

## Bedienung

- OK auf Quelle oeffnet Details.
- OK auf Hinzufügen startet den Add Flow.
- Rechts wechselt zu Aktionen, wenn vorhanden.
- Links kehrt zur Liste zurück.
- Zurück schliesst Dialoge vor Rueckkehr zu Einstellungen.

## Zustaende

Empty: keine Wiedergabelisten oder keine EPG-Quellen vorhanden.

Loading: Aktualisierung laeuft.

Source Error: Quelle konnte nicht geladen werden.

Invalid Credentials: Zugangsdaten sind ungültig oder fehlen.

Duplicate Name: Providername existiert bereits.

Partial Success: Aktualisierung abgeschlossen, einzelne Eintraege wurden übersprungen.

No EPG Assigned: Provider hat keine EPG-Quelle zugeordnet.

## Komponenten

- Settings Row
- Source Card
- Detail Panel
- Action Button
- Form Dialog
- Step Dialog
- Validation Message
- Status Badge
- Warning Text
