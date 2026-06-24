# 05 - Wireframe: Einstellungen

Status: verbindlich v18

## Zweck

Die Einstellungen buendeln Allgemein, Wiedergabelisten, EPG, Optik, Wiedergabe, Kindersicherung, Backup und App-Info.

## Primaerlayout

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+----------------------+---------------------------------------------------------+
| GRUPPEN              | OPTIONEN                                                |
| (*)[Allgemein]       | (*) App beim TV-Start starten              [Aus]        |
| [Wiedergabelisten]   |     Startbereich                          [Home]  |
| [EPG]                |     Doppelte Zurueck-Taste zum Beenden     [Ein]        |
| [Optik]              |     Sprache                               [System]     |
| [Wiedergabe]         |     Hintergrundaktualisierung erlauben    [Ein]        |
| [Kindersicherung]    |     Sortierung merken                     [Ein]        |
| [Backup]             |     User-Agent                            [App-Standard]|
| [Über die App]      |                                                         |
+----------------------+---------------------------------------------------------+
```

## Initialfokus

```text
Allgemein
```

## Gruppen

```text
Allgemein
Wiedergabelisten
EPG
Optik
Wiedergabe
Kindersicherung
Backup
Über die App
```

## Allgemein

```text
App beim TV-Start starten
Startbereich
Doppelte Zurueck-Taste zum Beenden
Sprache
Hintergrundaktualisierung erlauben
Sortierung merken
User-Agent
```

Regeln:

```text
Startbereich ist eine Auswahl mit Home, Live-TV, Filme und Serien; Standard Home.
Eine Aenderung gilt ab dem naechsten regulaeren App-Start und loest keine sofortige Navigation aus.
Explizite Android-TV-Ziele haben Vorrang; leere Zielbereiche zeigen ihren normalen Empty State.
Sprache gehoert in Allgemein, nicht in Optik.
User-Agent ist die letzte Allgemein-Option.
User-Agent ist global und nicht pro Wiedergabeliste, Provider, EPG-Quelle oder Stream einstellbar.
Leerer User-Agent-Wert bedeutet App-Standard.
```

## Fokuswege

```text
UP/DOWN links       -> Gruppe wechseln
RIGHT aus Gruppen   -> Optionen
LEFT aus Optionen   -> Gruppen
OK auf Row          -> Toggle, Auswahl, Textfeld, PIN-Dialog, Dialog oder Detail
BACK Dialog         -> Dialog schliessen
BACK Einstellungen  -> Top Navigation
```

## Wiedergabelisten

```text
+----------------------+---------------------------------------------------------+
| GRUPPEN              | WIEDERGABELISTEN                                      |
| [Allgemein]          | (*) [Wiedergabeliste hinzufuegen]                     |
| (*)[Wiedergabelisten]|     [Alle Wiedergabelisten aktualisieren]             |
| [EPG]                |                                                         |
| ...                  |     Provider A                         {Aktiv}         |
|                      |     Live-TV: <n> Filme: <n> Serien: <n>                |
|                      |     Letzte Aktualisierung: <Zeit>                      |
+----------------------+---------------------------------------------------------+
```

## Add Flow: Wiedergabeliste hinzufuegen

Schritt 1 Name:

```text
+---------------------------------------------+
| Wiedergabeliste hinzufuegen                  |
| Name *                                       |
| (*)[________________________]                |
|                                             |
| Fehler: Name bereits vorhanden               |
| [Weiter] [Abbrechen]                         |
+---------------------------------------------+
```

Schritt 2 Typ:

```text
+---------------------------------------------+
| Quelltyp waehlen                             |
| (*)[M3U]                                     |
| [Xtream Codes]                               |
| [Zurueck] [Abbrechen]                        |
+---------------------------------------------+
```

Schritt 3 M3U Eingabeart:

```text
+---------------------------------------------+
| M3U Eingabeart                               |
| (*)[URL]                                     |
| [Datei]                                      |
| [Zwischenablage]                             |
| [Zurueck] [Abbrechen]                        |
+---------------------------------------------+
```

M3U URL:

```text
+---------------------------------------------+
| M3U URL                                      |
| URL *                                        |
| (*)[https://...]                             |
| Hinweis bei HTTP: Unsichere Verbindung.      |
| [Verbindung testen] [Zurueck] [Abbrechen]    |
+---------------------------------------------+
```

M3U Datei:

```text
+---------------------------------------------+
| M3U Datei                                    |
| (*)[Datei auswaehlen]                        |
| Hinweis: Datei wird nur importiert.          |
| Es wird keine dauerhafte Kopie gespeichert.  |
| [Verbindung testen] [Zurueck] [Abbrechen]    |
+---------------------------------------------+
```

M3U Zwischenablage:

```text
+---------------------------------------------+
| M3U Zwischenablage                            |
| (*)[Aus Zwischenablage uebernehmen]           |
| Erlaubt: M3U URL oder kompletter M3U Inhalt.  |
| [Verbindung testen] [Zurueck] [Abbrechen]     |
+---------------------------------------------+
```

Xtream Codes:

```text
+---------------------------------------------+
| Xtream Codes                                  |
| Server *        (*)[http://host:8080]         |
| Benutzername *  [__________________]          |
| Passwort *      [__________________]          |
|                                             |
| Hinweis bei HTTP: Unsichere Verbindung.       |
| Importieren: [x] Live-TV [x] Filme [x] Serien |
| [Verbindung testen] [Zurueck] [Abbrechen]     |
+---------------------------------------------+
```

Nicht enthalten:

```text
User-Agent
```

Vor Speichern:

```text
Verbindung testen...
```

Bei Erfolg:

```text
Quelle gespeichert.
Aktualisierung startet automatisch.
```

Bei Fehler:

```text
Quelle konnte nicht geprueft werden.
<konkreter Fehlerhinweis>
```

## Bearbeiten einer Wiedergabeliste

```text
+--------------------------------------------------------------------------------+
| Provider A                                                       {Aktiv}        |
+--------------------------------------------------------------------------------+
| Status                 [Aktiviert]                                             |
| Name *                 [Provider A]                                            |
| Typ                    Xtream Codes  (nicht aenderbar)                         |
| Zugangsdaten           [Bearbeiten]                                            |
| EPG Quellen            [Zuweisen / Prioritaet aendern]                         |
| Logo Prioritaet        [Globale Reihenfolge verwenden]                         |
| Gruppen verwalten      [Anzeigen / Ausblenden / Sortieren]                     |
| Update Optionen        [Intervall / App-Start / Jetzt aktualisieren]           |
| Letzte Aktualisierung  <Datum/Uhrzeit>                                         |
| Ablaufdatum            <falls vorhanden>                                       |
| Maximale Verbindungen  <falls vorhanden>                                       |
|                                                                                |
| [Speichern] [Loeschen]                                                         |
+--------------------------------------------------------------------------------+
```

Regeln:

```text
Typ kann nicht geaendert werden.
Name ist Pflichtfeld und eindeutig.
Bei geaenderter URL oder geaenderten Zugangsdaten: erst Verbindung testen.
Nach erfolgreichem Speichern: direkt aktualisieren/importieren.
User-Agent wird hier nicht angeboten; er liegt global unter Allgemein.
```

## EPG

```text
+----------------------+---------------------------------------------------------+
| GRUPPEN              | EPG                                                     |
| [Allgemein]          | (*) [EPG-Quelle hinzufuegen]                            |
| [Wiedergabelisten]   |     [EPG jetzt aktualisieren]                           |
| (*)[EPG]             |     Globales Intervall              [24 Stunden]        |
| [Optik]              |     Vergangenheit behalten          [1 Tag]             |
| [Wiedergabe]         |     Zukunft laden/behalten          [7 Tage]            |
| ...                  |     Beim App-Start aktualisieren    [Ein]               |
|                      |     Bei Playlist-Aenderung          [Ein]               |
|                      |     Aktualisierungshistorie         [Oeffnen]           |
+----------------------+---------------------------------------------------------+
```

EPG-Quellen werden global verwaltet.

`24 Stunden` ist der verbindliche Standardwert des globalen Intervalls. App-Start, Playlist-Aenderung und manuelle Aktualisierung bleiben getrennte Ausloeser.

`Beim App-Start aktualisieren` und `Bei Playlist-Aenderung` starten bei Erstinitialisierung auf `Ein` und sind gespeicherte DataStore-Optionen. `Aktualisierungshistorie` ist nur Anzeige aus Refresh-Metadaten.

EPG-Aufbewahrung: Vergangenheit und Zukunft erlauben jeweils 1 bis 14 Tage. Defaults sind 1 Tag Vergangenheit und 7 Tage Zukunft.

Cleanup entfernt nur EPG-Programmdaten ausserhalb dieses Fensters.

EPG-Quellen werden pro Provider zugeordnet.

Mehrere EPG-Quellen pro Provider haben Prioritaet 1, 2, 3.

EPG-Quellen-Formulare enthalten keinen User-Agent.

## EPG-Quelle bearbeiten

```text
+--------------------------------------------------------------------------------+
| EPG Quelle A                                                    {Aktiv}         |
+--------------------------------------------------------------------------------+
| Status                 [Aktiviert]                                             |
| Name *                 [EPG Quelle A]                                          |
| Quelle / URL *         [https://...]                                           |
| Zeitversatz            [+0 Stunden]                                            |
| Nutzung durch Provider <n>                                                     |
| Letzte Aktualisierung  <Datum/Uhrzeit>                                         |
| Programme              <n>                                                     |
|                                                                                |
| [Speichern] [Loeschen]                                                         |
+--------------------------------------------------------------------------------+
```

Nicht enthalten:

```text
User-Agent
```

## Optik

```text
+----------------------+---------------------------------------------------------+
| GRUPPEN              | OPTIK                                                   |
| [Allgemein]          | (*) Hintergrundthema                [Standard dunkel]   |
| [Wiedergabelisten]   |     Akzentfarbe                     [Vivicast Blau]     |
| [EPG]                |     Transparenz                     [25 %]              |
| (*)[Optik]           |     Schriftgroesse                  [Mittel]            |
| [Wiedergabe]         |     Animationen                     [Normal]            |
| ...                  |     Globale Logo-Reihenfolge        [Playlist zuerst]   |
|                      |     Logos-Ordner                    [Nicht gesetzt]     |
|                      |     EPG-Darstellung                 [Oeffnen]           |
+----------------------+---------------------------------------------------------+
```

Regeln:

```text
Optik veraendert keine Grundlayoutachsen.
Fokus, Mindestkontrast und D-Pad-Bedienung bleiben verbindlich.
Sprache ist nicht hier, sondern unter Allgemein.
Logos-Ordner ist standardmaessig Nicht gesetzt und speichert bei Auswahl eine persistierbare Ordner-URI.
```

## EPG-Darstellung Detail

```text
+--------------------------------------------------------------------------------+
| EPG-Darstellung                                                                |
+--------------------------------------------------------------------------------+
| Kanalnummer anzeigen                         [Ein]                             |
| Sendername anzeigen                          [Ein]                             |
| Zweizeilige Sendernamen erlauben             [Aus]                             |
| Catch-Up-Symbole anzeigen                    [Ein]                             |
| Laufendes Programm hervorheben               [Ein]                             |
| Zweizeilige Programmtitel erlauben           [Ein]                             |
| Fortschritt markieren                        [Ein]                             |
| Animiertes Scrollen erlauben                 [Ein]                             |
|                                                                                |
| [Fertig]                                                                       |
+--------------------------------------------------------------------------------+
```

## Wiedergabe

```text
+----------------------+---------------------------------------------------------+
| GRUPPEN              | WIEDERGABE                                             |
| [Allgemein]          | (*) Puffergroesse                  [Mittel]            |
| [Wiedergabelisten]   |     Audio-Decoder                 [Hardware]          |
| [EPG]                |     Video-Decoder                 [Hardware]          |
| [Optik]              |     AFR                           [Aus]               |
| (*)[Wiedergabe]      |     Timeshift                     [Ein]               |
| [Kindersicherung]    |     Maximale Timeshift-Dauer      [30 Minuten]        |
| [Backup]             |     Timeshift-Speicher            [Automatisch]       |
| [Über die App]      |     Audio-Sprache                 [Systemstandard]    |
|                      |     Untertitel-Sprache            [Aus]               |
|                      |     Automatisch naechste Folge     [Aus]               |
|                      |     Countdown naechste Folge [10 Sekunden] {Deaktiviert} |
|                      |     Audio-Passthrough             [Aus]               |
|                      |     Externer Player               [Immer intern]      |
+----------------------+---------------------------------------------------------+
```

Hinweise:

```text
Timeshift ist standardmaessig Ein.
Maximale Dauer: 15 / 30 / 60 / 120 Minuten; Standard 30 Minuten.
Speicher: Automatisch / RAM / Festplatte; Standard Automatisch.
Bei Timeshift Aus bleiben Dauer und Speicher sichtbar, aber deaktiviert.
Festplatte nutzt appverwalteten persistenten Geraetespeicher ohne freie Pfadauswahl.
Puffergroesse und Decoder gelten beim naechsten Streamstart.
AFR und Audio-Passthrough koennen auf nicht unterstuetzten Geraeten deaktiviert sein.
Timeshift bleibt abhaengig von Provider, Sender und Stream.
Auto-Next ist standardmaessig Aus.
Countdown: 5 / 10 / 15 / 30 Sekunden; Standard 10 Sekunden.
Bei Auto-Next Aus bleibt der Countdown sichtbar, aber deaktiviert.
```

## Wiedergabe bei deaktiviertem Timeshift

```text
Timeshift                     [Aus]
Maximale Timeshift-Dauer      [30 Minuten]  {Deaktiviert}
Timeshift-Speicher            [Automatisch] {Deaktiviert}
```

## Wiedergabe bei deaktiviertem Auto-Next

```text
Automatisch naechste Folge     [Aus]
Countdown naechste Folge       [10 Sekunden] {Deaktiviert}
```

Bei `Ein` wird die Countdown-Zeile fokussierbar und aenderbar.

Eine Abschluss-Schwellen-Zeile existiert nicht. Der v1-Wert von 95 Prozent ist fest.

## Externer Player Dialog

```text
+---------------------------------------------+
| Wiedergabe starten                           |
| (*)[Intern abspielen]                        |
| [Externen Player waehlen]                    |
| [Abbrechen]                                  |
+---------------------------------------------+
```

Hinweis:

```text
Externe Player speichern keinen Fortschritt in Vivicast.
Gilt nur fuer Filme und einzelne Episoden.
Live-TV und Catch-Up bleiben intern.
```

## Kindersicherung

Ohne gesetzte PIN:

```text
+----------------------+---------------------------------------------------------+
| GRUPPEN              | KINDERSICHERUNG                                        |
| [Allgemein]          | (*) PIN setzen                         [Nicht gesetzt] |
| [Wiedergabelisten]   |     Einstellungen schuetzen             [Deaktiviert]  |
| [EPG]                |     Filme schuetzen                     [Deaktiviert]  |
| [Optik]              |     Serien schuetzen                    [Deaktiviert]  |
| [Wiedergabe]         |     Inhalte ab 18 schuetzen             [Deaktiviert]  |
| (*)[Kindersicherung] |                                                         |
| [Backup]             | Hinweis: PIN setzen, um Schutzbereiche zu aktivieren.  |
| [Über die App]      |                                                         |
+----------------------+---------------------------------------------------------+
```

Mit gesetzter PIN:

```text
+----------------------+---------------------------------------------------------+
| GRUPPEN              | KINDERSICHERUNG                                        |
| [Allgemein]          | (*) PIN aendern                        [Gesetzt]       |
| [Wiedergabelisten]   |     Einstellungen schuetzen             [Ein]          |
| [EPG]                |     Filme schuetzen                     [Aus]          |
| [Optik]              |     Serien schuetzen                    [Aus]          |
| [Wiedergabe]         |     Inhalte ab 18 schuetzen             [Ein]          |
| (*)[Kindersicherung] |     Freigabe fuer Sitzung sperren       [Oeffnen]      |
| [Backup]             |     Kindersicherung deaktivieren        [Oeffnen]      |
| [Über die App]      |                                                         |
+----------------------+---------------------------------------------------------+
```

## PIN setzen

```text
+---------------------------------------------+
| PIN setzen                                   |
| Neue PIN                                     |
| (*)[••••]                                    |
| PIN wiederholen                              |
| [____]                                       |
|                                             |
| [Speichern] [Abbrechen]                      |
+---------------------------------------------+
```

PIN-Felder oeffnen die numerische Passwort-Systemtastatur. Vivicast zeigt keine eigene Zifferntastatur. Nach vier Ziffern erfolgt keine automatische Bestaetigung; `Speichern` bleibt die bewusste Aktion.

## PIN aendern

```text
+---------------------------------------------+
| PIN aendern                                  |
| Aktuelle PIN                                 |
| (*)[••••]                                    |
| Neue PIN                                     |
| [____]                                       |
| PIN wiederholen                              |
| [____]                                       |
|                                             |
| [Speichern] [Abbrechen]                      |
+---------------------------------------------+
```

Auch hier oeffnen PIN-Felder die numerische Passwort-Systemtastatur und bleiben verdeckt.

## PIN-Abfrage

```text
+---------------------------------------------+
| Geschuetzter Bereich                         |
| PIN eingeben                                 |
| (*)[••••]                                    |
|                                             |
| Fehler: PIN falsch                           |
| [Entsperren] [Abbrechen]                     |
+---------------------------------------------+
```

Temporär blockiert:

```text
+---------------------------------------------+
| Geschuetzter Bereich                         |
| Zu viele falsche Eingaben.                   |
| Erneut versuchen in <n> Sekunden.            |
|                                             |
| [Abbrechen]                                  |
+---------------------------------------------+
```

Die Sperre beginnt nach fuenf falschen Eingaben. Sperrdauern sind 30 Sekunden, 60 Sekunden und danach 5 Minuten. App-Neustart hebt eine laufende Sperre nicht auf.

Nach vier Ziffern wird nicht automatisch entsperrt. `Entsperren` bleibt die bewusste Aktion.

Im blockierten Zustand oeffnet keine Tastatur.

## Kindersicherung deaktivieren

```text
+---------------------------------------------+
| Kindersicherung deaktivieren                 |
| Dadurch werden alle Schutzbereiche entfernt. |
| Aktuelle PIN                                 |
| (*)[••••]                                    |
|                                             |
| [Deaktivieren] [Abbrechen]                   |
+---------------------------------------------+
```

Nach vier Ziffern wird nicht automatisch deaktiviert. `Deaktivieren` bleibt die bewusste Aktion.

## Backup

```text
+----------------------+---------------------------------------------------------+
| GRUPPEN              | BACKUP                                                  |
| [Allgemein]          | (*) Backup exportieren                  [Oeffnen]      |
| [Wiedergabelisten]   |     Backup importieren                  [Oeffnen]      |
| [EPG]                |     Backup-Ziel                         [Lokaler Speicher]|
| [Optik]              |     Letzte Sicherung                    [Nie]          |
| [Wiedergabe]         |     Vorhandene Backups verwalten        [Oeffnen]      |
| (*)[Backup]          |     Medien-Cache                        [128 MB]       |
| [Über die App]      |     Cache leeren                        [Oeffnen]      |
|                      |     Verlauf loeschen                    [Oeffnen]      |
+----------------------+---------------------------------------------------------+
```

## Cache leeren - Bestaetigung

```text
+----------------------------------------------------------+
| Cache leeren                                             |
| Medien-Cache-Dateien fuer Logos, Poster und Bilder werden|
| geloescht und bei Bedarf neu geladen.                    |
|                                                          |
| Providerdaten, Favoriten, Verlauf und Fortschritt bleiben|
| erhalten.                                                |
|                                                          |
| [Cache leeren] [Abbrechen]                               |
+----------------------------------------------------------+
```

Wenn Einstellungsschutz aktiv ist:

```text
PIN-Abfrage vor Cache leeren
```

## Verlauf loeschen

```text
+----------------------------------------------------------+
| Verlauf loeschen                                         |
| (*) Live-TV-Verlauf                                      |
|     Filmverlauf und Film-Wiedergabefortschritt           |
|     Serienverlauf und Episoden-Wiedergabefortschritt     |
|     Suchverlauf                                          |
|     Gesamter Verlauf                                     |
|                                                          |
| [Loeschen] [Abbrechen]                                   |
+----------------------------------------------------------+
```

Wenn Einstellungsschutz aktiv ist:

```text
PIN-Abfrage vor Verlauf loeschen
```

## Backup exportieren

```text
+----------------------------------------------------------+
| Backup exportieren                                       |
| Ziel                  (*)[Lokaler Speicher] [SMB] [Drive] |
| Backup-Typ             [Standard] [Verschluesselt]        |
|                                                          |
| Standard enthaelt keine geheimen Zugangswerte.           |
| Verschluesselt kann Quellen-Zugangsdaten enthalten.      |
|                                                          |
| [Exportieren] [Abbrechen]                                |
+----------------------------------------------------------+
```

`Backup-Typ` ist ein transienter Exportdialogwert. Der Dialog startet mit `Standard`; der Wert wird nicht als dauerhafte Setting-Option gespeichert.

## Verschluesseltes Vollbackup

```text
+----------------------------------------------------------+
| Vollbackup verschluesseln                                |
| Backup-Passphrase *                                      |
| (*)[____________________]                                |
| Passphrase wiederholen *                                 |
| [____________________]                                   |
|                                                          |
| Ohne Passphrase kann dieses Backup nicht importiert werden.|
| Die Passphrase wird nicht gespeichert.                   |
| [Exportieren] [Zurueck] [Abbrechen]                      |
+----------------------------------------------------------+
```

## Backup importieren - Zusammenfassung

```text
+----------------------------------------------------------+
| Backup importieren                                       |
| App-Version: <Version>                                   |
| Erstellt: <Datum/Uhrzeit>                                |
| Modus: Standard / Verschluesselt                         |
| Datenbereiche: Einstellungen, Quellen, Favoriten, Verlauf |
| Migration: Nicht erforderlich / Erforderlich             |
| Sensible Daten: Nein / Ja                                |
| Kindersicherung: Wird nach Restore deaktiviert            |
| Restore: Ersetzt lokale Backup-Daten                     |
| Nicht im Backup enthaltene Provider werden entfernt.     |
|                                                          |
| [Importieren] [Abbrechen]                                |
+----------------------------------------------------------+
```

## Restore ersetzen - Bestaetigung

```text
+----------------------------------------------------------+
| Lokale Daten ersetzen                                    |
| Dieser Vorgang ueberschreibt lokale App-Daten.           |
| Nach Moeglichkeit wird vorher ein Sicherheitsbackup erstellt.|
|                                                          |
| [Ersetzen] [Abbrechen]                                  |
+----------------------------------------------------------+
```

Wenn Einstellungsschutz aktiv ist oder Backup/Restore lokal per PIN geschuetzt ist:

```text
PIN-Abfrage mit aktuell lokaler PIN vor Ersetzen
```

## Sicherheitsbackup fehlgeschlagen

```text
+----------------------------------------------------------+
| Sicherheitsbackup fehlgeschlagen                         |
| Die aktuellen lokalen Daten konnten nicht gesichert      |
| werden. Restore ersetzt trotzdem lokale Backup-Daten,    |
| wenn du fortfaehrst.                                    |
|                                                          |
| (*)[Abbrechen] [Trotzdem fortsetzen]                    |
+----------------------------------------------------------+
```

## Restore abgeschlossen - Kindersicherung deaktiviert

```text
+----------------------------------------------------------+
| Backup wiederhergestellt                                 |
| Die PIN-Funktion war vor dem Backup aktiv und wurde nach |
| dem Restore deaktiviert.                                 |
|                                                          |
| Bitte aktiviere die Kindersicherung bei Bedarf erneut in |
| Einstellungen > Kindersicherung.                         |
|                                                          |
| [Verstanden]                                             |
+----------------------------------------------------------+
```

## Backup-Fehler

```text
Backup-Datei ungueltig
Backup-Version nicht unterstuetzt
Backup beschaedigt
Passphrase falsch
Ziel nicht erreichbar
Speicherzugriff verweigert
Nicht genug Speicherplatz
Migration fehlgeschlagen
Zugangsdaten erforderlich
```

## Loeschen

```text
einfache Bestaetigung
wenn Einstellungsschutz aktiv: zusaetzlich PIN
```

## Empty States

```text
Keine Wiedergabelisten -> [Wiedergabeliste hinzufuegen]
Keine EPG-Quellen     -> [EPG-Quelle hinzufuegen]
Keine Backups         -> [Backup exportieren]
```

## Zustaende

```text
Loading              -> nur betroffener Bereich
Error                -> inline im betroffenen Bereich
Unsupported          -> deaktivierte Option mit Hinweis
Locked               -> PIN-Abfrage erforderlich
Temporarily Blocked  -> PIN-Eingabe kurz blockiert
Migration Required   -> Import benoetigt Migration
Credentials Required -> Quelle braucht neue Zugangsdaten
```
