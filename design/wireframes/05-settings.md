# 05 - Wireframe: Einstellungen

Status: verbindlich v18

## Zweck

Die Einstellungen buendeln Allgemein, Wiedergabelisten, EPG, Optik, Wiedergabe, Kindersicherung, Speicher & Verlauf, Backup und Über die App.

## Primärlayout

Die Wireframes trennen linke Gruppenliste und rechte Optionsliste bewusst, damit Codex keine Zeilenzuordnung zwischen linker Gruppe und rechter Option ableitet.

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen        |
+--------------------------------------------------------------------------------+

GRUPPEN:
(*) Allgemein
    Wiedergabelisten
    EPG
    Optik
    Wiedergabe
    Kindersicherung
    Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR ALLGEMEIN:
(*) App beim TV-Start starten                  [Aus]
    Startbereich                               [Home]
    Doppelte Zurück-Taste zum Beenden          [Ein]
    Sprache                                    [System]
    Hintergrundaktualisierung erlauben         [Ein]
    Sortierung merken                          [Ein]
    User-Agent                                 [App-Standard]
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
Speicher & Verlauf
Backup
Über die App
```

## Allgemein

```text
App beim TV-Start starten
Startbereich
Doppelte Zurück-Taste zum Beenden
Sprache
Hintergrundaktualisierung erlauben
Sortierung merken
User-Agent
```

Regeln:

```text
Startbereich ist eine Auswahl mit Home, Live-TV, Filme und Serien; Standard Home.
Eine Änderung gilt ab dem nächsten regulaeren App-Start und loest keine sofortige Navigation aus.
Explizite Android-TV-Ziele haben Vorrang; leere Zielbereiche zeigen ihren normalen Empty State.
Sprache gehört in Allgemein, nicht in Optik.
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
BACK Dialog         -> Dialog schließen
BACK Einstellungen  -> Top Navigation
```

## Wiedergabelisten

```text
GRUPPEN:
    Allgemein
(*) Wiedergabelisten
    EPG
    Optik
    Wiedergabe
    Kindersicherung
    Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR WIEDERGABELISTEN:
(*) Wiedergabeliste hinzufügen
    Alle Wiedergabelisten aktualisieren

KONFIGURIERTE WIEDERGABELISTEN:
    Provider A                         {Aktiv}
    Live-TV: <n> Filme: <n> Serien: <n>
    Letzte Aktualisierung: <Zeit>
```


## Add Flow: Wiedergabeliste hinzufügen

Schritt 1 Name:

```text
+---------------------------------------------+
| Wiedergabeliste hinzufügen                  |
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
| Quelltyp wählen                             |
| (*)[M3U]                                     |
| [Xtream Codes]                               |
| [Zurück] [Abbrechen]                        |
+---------------------------------------------+
```

Schritt 3 M3U Eingabeart:

```text
+---------------------------------------------+
| M3U Eingabeart                               |
| (*)[URL]                                     |
| [Datei]                                      |
| [Zwischenablage]                             |
| [Zurück] [Abbrechen]                        |
+---------------------------------------------+
```

M3U URL:

```text
+---------------------------------------------+
| M3U URL                                      |
| URL *                                        |
| (*)[https://...]                             |
| Hinweis bei HTTP: Unsichere Verbindung.      |
| [Verbindung testen] [Zurück] [Abbrechen]    |
+---------------------------------------------+
```

M3U Datei:

```text
+---------------------------------------------+
| M3U Datei                                    |
| (*)[Datei auswählen]                        |
| Hinweis: Datei wird nur importiert.          |
| Es wird keine dauerhafte Kopie gespeichert.  |
| [Verbindung testen] [Zurück] [Abbrechen]    |
+---------------------------------------------+
```

M3U Zwischenablage:

```text
+---------------------------------------------+
| M3U Zwischenablage                            |
| (*)[Aus Zwischenablage übernehmen]           |
| Erlaubt: M3U URL oder kompletter M3U Inhalt.  |
| [Verbindung testen] [Zurück] [Abbrechen]     |
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
| [Verbindung testen] [Zurück] [Abbrechen]     |
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
| Typ                    Xtream Codes  (nicht änderbar)                         |
| Zugangsdaten           [Bearbeiten]                                            |
| EPG Quellen            [Zuweisen / Priorität ändern]                         |
| Logo Priorität        [Globale Reihenfolge verwenden]                         |
| Gruppen verwalten      [Anzeigen / Ausblenden / Sortieren]                     |
| Update Optionen        [Intervall / App-Start / Jetzt aktualisieren]           |
| Letzte Aktualisierung  <Datum/Uhrzeit>                                         |
| Ablaufdatum            <falls vorhanden>                                       |
| Maximale Verbindungen  <falls vorhanden>                                       |
|                                                                                |
| [Speichern] [Löschen]                                                         |
+--------------------------------------------------------------------------------+
```

Regeln:

```text
Typ kann nicht geändert werden.
Name ist Pflichtfeld und eindeutig.
Bei geänderter URL oder geänderten Zugangsdaten: erst Verbindung testen.
Nach erfolgreichem Speichern: direkt aktualisieren/importieren.
User-Agent wird hier nicht angeboten; er liegt global unter Allgemein.
```

## EPG

```text
GRUPPEN:
    Allgemein
    Wiedergabelisten
(*) EPG
    Optik
    Wiedergabe
    Kindersicherung
    Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR EPG:
(*) EPG-Quelle hinzufügen
    Globales Aktualisierungsintervall          [24 Stunden]
    EPG-Vergangenheit behalten                [1 Tag]
    EPG-Zukunft laden/behalten                 [7 Tage]
    Beim App-Start aktualisieren               [Ein]
    Bei Playlist-Änderung aktualisieren        [Ein]
    EPG jetzt aktualisieren                    [Öffnen]
    Aktualisierungshistorie                    [Öffnen]
```


EPG-Quellen werden global verwaltet.

`24 Stunden` ist der verbindliche Standardwert des globalen Intervalls. App-Start, Playlist-Änderung und manuelle Aktualisierung bleiben getrennte Ausloeser.

`Beim App-Start aktualisieren` und `Bei Playlist-Änderung` starten bei Erstinitialisierung auf `Ein` und sind gespeicherte DataStore-Optionen. `Aktualisierungshistorie` ist nur Anzeige aus Refresh-Metadaten.

EPG-Aufbewahrung: Vergangenheit und Zukunft erlauben jeweils 1 bis 14 Tage. Defaults sind 1 Tag Vergangenheit und 7 Tage Zukunft.

Cleanup entfernt nur EPG-Programmdaten ausserhalb dieses Fensters.

EPG-Quellen werden pro Provider zugeordnet.

Mehrere EPG-Quellen pro Provider haben Priorität 1, 2, 3.

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
| [Speichern] [Löschen]                                                         |
+--------------------------------------------------------------------------------+
```

Nicht enthalten:

```text
User-Agent
```

## Optik

```text
GRUPPEN:
    Allgemein
    Wiedergabelisten
    EPG
(*) Optik
    Wiedergabe
    Kindersicherung
    Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR OPTIK:
(*) Hintergrundthema                           [Standard dunkel]
    Akzentfarbe                                [Vivicast Blau]
    Transparenz                                [25 %]
    Schriftgröße                               [Mittel]
    Animationen                                [Normal]
    Globale Logo-Standardreihenfolge           [Playlist]
    Logos-Ordner                               [Nicht gesetzt]
    EPG-Darstellung                            [Öffnen]
```


Regeln:

```text
Optik verändert keine Grundlayoutachsen.
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
GRUPPEN:
    Allgemein
    Wiedergabelisten
    EPG
    Optik
(*) Wiedergabe
    Kindersicherung
    Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR WIEDERGABE:
(*) Puffergröße                                [Mittel]
    Audio-Decoder                              [Hardware]
    Video-Decoder                              [Hardware]
    AFR                                        [Aus]
    Timeshift                                  [Ein]
    Maximale Timeshift-Dauer                   [30 Minuten]
    Timeshift-Speicher                         [Automatisch]
    Audio-Sprache                              [Systemstandard]
    Untertitel-Sprache                         [Aus]
    Automatisch nächste Folge                  [Aus]
    Countdown nächste Folge                    [10 Sekunden]
    Audio-Passthrough                          [Aus]
    Externer Player                            [Immer intern]
```


Hinweise:

```text
Timeshift ist standardmaessig Ein.
Maximale Dauer: 15 / 30 / 60 / 120 Minuten; Standard 30 Minuten.
Speicher: Automatisch / RAM / Festplatte; Standard Automatisch.
Bei Timeshift Aus bleiben Dauer und Speicher sichtbar, aber deaktiviert.
Festplatte nutzt appverwalteten persistenten Gerätespeicher ohne freie Pfadauswahl.
Puffergröße und Decoder gelten beim nächsten Streamstart.
AFR und Audio-Passthrough können auf nicht unterstuetzten Geräten deaktiviert sein.
Timeshift bleibt abhängig von Provider, Sender und Stream.
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
Automatisch nächste Folge     [Aus]
Countdown nächste Folge       [10 Sekunden] {Deaktiviert}
```

Bei `Ein` wird die Countdown-Zeile fokussierbar und änderbar.

Eine Abschluss-Schwellen-Zeile existiert nicht. Der v1-Wert von 95 Prozent ist fest.

## Externer Player Dialog

```text
+---------------------------------------------+
| Wiedergabe starten                           |
| (*)[Intern abspielen]                        |
| [Externen Player wählen]                    |
| [Abbrechen]                                  |
+---------------------------------------------+
```

Hinweis:

```text
Externe Player speichern keinen Fortschritt in Vivicast.
Gilt nur für Filme und einzelne Episoden.
Live-TV und Catch-Up bleiben intern.
```

## Kindersicherung

Ohne gesetzte PIN:

```text
GRUPPEN:
    Allgemein
    Wiedergabelisten
    EPG
    Optik
    Wiedergabe
(*) Kindersicherung
    Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR KINDERSICHERUNG:
(*) PIN setzen                                [Nicht gesetzt]
    Einstellungen schützen                    [Deaktiviert]
    Filme schützen                            [Deaktiviert]
    Serien schützen                           [Deaktiviert]
    Inhalte ab 18 schützen                    [Deaktiviert]

HINWEIS:
    PIN setzen, um Schutzbereiche zu aktivieren.
```

Mit gesetzter PIN:

```text
GRUPPEN:
    Allgemein
    Wiedergabelisten
    EPG
    Optik
    Wiedergabe
(*) Kindersicherung
    Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR KINDERSICHERUNG:
(*) PIN ändern                                [Gesetzt]
    Einstellungen schützen                    [Ein]
    Filme schützen                            [Aus]
    Serien schützen                           [Aus]
    Inhalte ab 18 schützen                    [Ein]
    Freigabe für Sitzung sperren              [Öffnen]
    Kindersicherung deaktivieren              [Öffnen]
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

PIN-Felder öffnen die numerische Passwort-Systemtastatur. Vivicast zeigt keine eigene Zifferntastatur. Nach vier Ziffern erfolgt keine automatische Bestätigung; `Speichern` bleibt die bewusste Aktion.

## PIN ändern

```text
+---------------------------------------------+
| PIN ändern                                  |
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

Auch hier öffnen PIN-Felder die numerische Passwort-Systemtastatur und bleiben verdeckt.

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

## Speicher & Verlauf

```text
GRUPPEN:
    Allgemein
    Wiedergabelisten
    EPG
    Optik
    Wiedergabe
    Kindersicherung
(*) Speicher & Verlauf
    Backup
    Über die App

OPTIONEN FÜR SPEICHER & VERLAUF:
(*) Medien-Cache                               [128 MB]
    Cache leeren                               [Öffnen]
    Verlauf löschen                            [Öffnen]
```


## Cache leeren - Bestätigung

```text
+----------------------------------------------------------+
| Cache leeren                                             |
| Medien-Cache-Dateien für Logos, Poster und Bilder werden|
| gelöscht und bei Bedarf neu geladen.                    |
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

## Verlauf löschen

```text
+----------------------------------------------------------+
| Verlauf löschen                                         |
| (*) Live-TV-Verlauf                                      |
|     Filmverlauf und Film-Wiedergabefortschritt           |
|     Serienverlauf und Episoden-Wiedergabefortschritt     |
|     Suchverlauf                                          |
|     Gesamter Verlauf                                     |
|                                                          |
| [Löschen] [Abbrechen]                                   |
+----------------------------------------------------------+
```

Wenn Einstellungsschutz aktiv ist:

```text
PIN-Abfrage vor Verlauf löschen
```

## Backup

```text
GRUPPEN:
    Allgemein
    Wiedergabelisten
    EPG
    Optik
    Wiedergabe
    Kindersicherung
    Speicher & Verlauf
(*) Backup
    Über die App

OPTIONEN FÜR BACKUP:
(*) Backup exportieren                         [Öffnen]
    Backup importieren                         [Öffnen]
    Backup-Ziel                                [Lokaler Speicher]
    Letzte Sicherung                           [Nie]
    Vorhandene Backups verwalten               [Öffnen]
```


## Backup exportieren

```text
+----------------------------------------------------------+
| Backup exportieren                                       |
| Ziel                  (*)[Lokaler Speicher] [SMB] [Drive] |
| Backup-Typ             [Standard] [Verschlüsselt]        |
|                                                          |
| Standard enthält keine geheimen Zugangswerte.           |
| Verschlüsselt kann Quellen-Zugangsdaten enthalten.      |
|                                                          |
| [Exportieren] [Abbrechen]                                |
+----------------------------------------------------------+
```

`Backup-Typ` ist ein transienter Exportdialogwert. Der Dialog startet mit `Standard`; der Wert wird nicht als dauerhafte Setting-Option gespeichert.

## Verschlüsseltes Vollbackup

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
| [Exportieren] [Zurück] [Abbrechen]                      |
+----------------------------------------------------------+
```

## Backup importieren - Zusammenfassung

```text
+----------------------------------------------------------+
| Backup importieren                                       |
| App-Version: <Version>                                   |
| Erstellt: <Datum/Uhrzeit>                                |
| Modus: Standard / Verschlüsselt                         |
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

## Restore ersetzen - Bestätigung

```text
+----------------------------------------------------------+
| Lokale Daten ersetzen                                    |
| Dieser Vorgang überschreibt lokale App-Daten.           |
| Nach Möglichkeit wird vorher ein Sicherheitsbackup erstellt.|
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
Backup-Datei ungültig
Backup-Version nicht unterstuetzt
Backup beschädigt
Passphrase falsch
Ziel nicht erreichbar
Speicherzugriff verweigert
Nicht genug Speicherplatz
Migration fehlgeschlagen
Zugangsdaten erforderlich
```

## Löschen

```text
einfache Bestätigung
wenn Einstellungsschutz aktiv: zusätzlich PIN
```

## Empty States

```text
Keine Wiedergabelisten -> [Wiedergabeliste hinzufügen]
Keine EPG-Quellen     -> [EPG-Quelle hinzufügen]
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
