# Settings Components

Status: verbindlich v16

## Zweck

Diese Datei beschreibt wiederverwendbare Komponenten fuer Einstellungen, Quellenverwaltung, EPG-Verwaltung und Dialoge.

## Group Item

Verwendung:

- linke Einstellungsnavigation
- Hauptgruppen

Zeigt:

- Gruppenname
- optionales Icon
- optionalen Statusindikator

## Settings Row

Standardzeile fuer Optionen.

Zeigt:

- Titel
- kurze Beschreibung
- aktuellen Wert oder Status
- optionalen Hinweis

Regeln:

- Jede Zeile muss vollstaendig per D-Pad fokussierbar sein.
- Fokus darf nicht nur ueber Farbe sichtbar sein.
- Lange Beschreibungen muessen kontrolliert gekuerzt werden.
- Wenn eine Aenderung erst spaeter wirkt, zeigt die Zeile einen kurzen Hinweis.

## Toggle Row

Verwendung fuer Ein/Aus-Optionen.

Regeln:

- OK toggelt Wert.
- aktueller Zustand ist sichtbar.
- Fokus bleibt nach Toggle auf der Zeile.
- Wenn die Option andere automatische Funktionen deaktiviert, muss ein kurzer Hinweis sichtbar sein.
- Nicht unterstuetzte Optionen werden deaktiviert dargestellt und erklaeren kurz warum.

## Select Row

Verwendung fuer Auswahlwerte.

OK oeffnet Auswahl-Dialog oder Inline-Auswahl.

Beispiele:

- Startbereich; Home, Live-TV, Filme oder Serien; Standard Home
- Sprache
- Schriftgroesse
- Transparenz
- Animationen
- Puffergroesse
- Decoder
- EPG-Aktualisierungsintervall; Standard 24 Stunden
- EPG-Vergangenheit behalten; 1 bis 14 Tage; Standard 1 Tag
- EPG-Zukunft laden/behalten; 1 bis 14 Tage; Standard 7 Tage
- maximale Timeshift-Dauer
- Timeshift-Speicher
- Logo-Prioritaet
- externer Player
- Backup-Ziel

## Color Select Row

Verwendung fuer Farbeinstellungen.

Beispiele:

- Akzentfarbe
- Hintergrundthema

Regeln:

- Farbauswahl darf nie die einzige Information sein.
- Name oder Beschreibung der Auswahl muss sichtbar sein.
- Fokus-, Fehler-, Warn- und Erfolgskontraste duerfen durch Farbauswahl nicht unbrauchbar werden.
- Wenn eine Farbe Mindestkontrast verletzt, wird sie nicht gespeichert oder automatisch auf einen sicheren Wert korrigiert.

## Text Input Row

Verwendung fuer einfache Texteingaben.

Beispiele:

- User-Agent
- Name einer Wiedergabeliste
- EPG-Quellenname
- Backup-Passphrase

Regeln:

- OK oeffnet Textdialog oder System-Tastatur.
- Pflichtfelder werden sichtbar gekennzeichnet.
- Fehler werden direkt am Feld angezeigt.
- Fuehrende und abschliessende Leerzeichen koennen beim Speichern entfernt werden, wenn fachlich sinnvoll.

## Password Input Row

Verwendung fuer verdeckte Eingaben.

Beispiele:

- Xtream-Codes-Passwort
- PIN
- Backup-Passphrase

Regeln:

- Inhalt wird standardmaessig verborgen.
- Sichtbarkeit darf nur bewusst umgeschaltet werden.
- Fokus muss zwischen Eingabe, Sichtbarkeitsschalter und Aktionen eindeutig bleiben.

## PIN Input Row

Verwendung fuer PIN setzen, PIN aendern und PIN-Abfrage.

Regeln:

- Eingabe besteht aus 4 Ziffern.
- OK auf das PIN-Feld oeffnet die Android-/TV-Systemtastatur als numerische Passwort-Eingabe.
- Vivicast zeigt keine eigene Zifferntastatur.
- Eingabe wird verdeckt angezeigt.
- Keine Zwischenablage-Aktion.
- Keine Autovervollstaendigung.
- Keine Klartextanzeige.
- Nach der vierten Ziffer wird nicht automatisch gespeichert, entsperrt oder deaktiviert.
- Bestaetigung erfolgt ueber die sichtbare Aktion des Dialogs.
- Fehler werden direkt unter der Eingabe angezeigt.
- Nach fuenf falschen Eingaben wird die Eingabe temporaer blockiert.
- Sperrdauern sind 30 Sekunden, 60 Sekunden und danach 5 Minuten.
- Temporaere Sperre zeigt Restzeit und deaktiviert Eingabe.
- Eine laufende Sperre wird durch App-Neustart nicht aufgehoben.

## Source Card

Verwendung fuer Wiedergabelisten und EPG-Quellen.

Zeigt:

- Name
- Typ
- Status
- letzte Aktualisierung
- relevante Zaehler oder Hinweise

Regeln:

- Status darf nicht nur farblich kommuniziert werden.
- Fehlerstatus muss kurz benannt werden.
- HTTP-Quellen muessen als unsicher gekennzeichnet werden.
- `Zugangsdaten erforderlich` muss als eigener Status darstellbar sein.
- Primaere Aktion ist Details oeffnen.

## Backup Card

Verwendung fuer vorhandene Backups.

Zeigt:

- Backup-Name oder Datei
- Erstellungszeitpunkt
- App-Version
- Backup-Modus
- Groesse, falls bekannt
- Ziel oder Speicherort
- Status oder Warnhinweis

Regeln:

- Backup-Modus muss textlich sichtbar sein.
- Verschluesselte Backups muessen klar gekennzeichnet werden.
- Verschluesselte Vollbackups muessen als passphrasegeschuetzt und potentiell geheimnishaltig gekennzeichnet werden.
- Fehlerhafte oder inkompatible Backups zeigen Status und kurze Ursache.

## Step Dialog

Verwendung fuer mehrstufige Add Flows.

Beispiel Wiedergabeliste hinzufuegen:

1. Name
2. Quelltyp
3. M3U-Eingabeart, falls M3U
4. Zugangsdaten oder Quelle
5. Test und Speichern

Regeln:

- aktueller Schritt ist klar erkennbar.
- Zurueck geht zum vorherigen Schritt.
- Abbrechen schliesst den Flow.
- Pflichtfelder blockieren Weiter.
- Fehler werden direkt am Feld oder im Dialog angezeigt.
- User-Agent darf in Wiedergabelisten- und EPG-Add-Flows nicht als eigene Option erscheinen.

## Form Dialog

Verwendung fuer Hinzufuegen und Bearbeiten.

Regeln:

- Felder klar beschriften.
- Pflichtfelder sichtbar machen.
- Fehler direkt am Feld anzeigen.
- Speichern und Abbrechen klar fokussierbar machen.
- Verbindungstest darf Fokus nicht verlieren.
- Wenn Test vor Speichern erforderlich ist, bleibt Speichern bis zum erfolgreichen Test blockiert oder fuehrt den Test automatisch zuerst aus.

## Settings Detail Panel

Verwendung fuer Optionsgruppen mit mehreren Unteroptionen.

Beispiele:

- EPG-Darstellung
- Logos-Ordner
- externe Player-Auswahl
- Audio- oder Untertitelsprachen
- vorhandene Backups verwalten

Regeln:

- Linke Optionsliste bleibt als Herkunftskontext verstaendlich.
- Detailoptionen bleiben komplett per D-Pad bedienbar.
- Zurueck schliesst zuerst den Detailbereich oder Dialog.

## Protected Action Dialog

Verwendung fuer PIN-geschuetzte Bereiche und Aktionen.

Beispiele:

- Einstellungen oeffnen, wenn Einstellungsschutz aktiv ist
- Filme oder Serien oeffnen, wenn VOD-Schutz aktiv ist
- FSK-18-Inhalt oeffnen
- Restore oder Loeschen bei aktivem Einstellungsschutz

Regeln:

- Dialog benennt den geschuetzten Bereich oder die geschuetzte Aktion.
- PIN-Eingabe nutzt PIN Input Row.
- Abbrechen ist immer erreichbar.
- Bei temporaerer PIN-Sperre oeffnet keine Tastatur; der Dialog zeigt Restzeit und Abbrechen.
- Nach fuenf falschen PIN-Eingaben gelten die Sperrdauern 30 Sekunden, 60 Sekunden und danach 5 Minuten.
- Nach erfolgreicher PIN wird der urspruengliche Zielkontext fortgesetzt.
- Nach falscher PIN bleibt der Dialog offen und zeigt den Fehler.

## Backup Summary Dialog

Verwendung nach Auswahl einer Backup-Datei.

Zeigt:

- App-Version
- Erstellungszeitpunkt
- Backup-Modus
- enthaltene Datenbereiche
- sensible Daten ja/nein
- Kindersicherung im Backup aktiv ja/nein; wird nach Restore deaktiviert
- Migration erforderlich ja/nein
- Hinweis, dass der Backup-Umfang lokale Daten ersetzt
- Hinweis, dass nicht im Backup enthaltene lokale Provider und Daten entfernt werden

Regeln:

- Import startet erst nach ausdruecklicher Nutzeraktion.
- Restore muss sichtbar als riskante Ersetzen-Aktion gekennzeichnet sein.
- Bei verschluesseltem Backup wird vor Import die Passphrase abgefragt.
- Eine falsche Passphrase bricht vor lokalen Datenaenderungen ab.
- Wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN fuer Backup/Restore verlangt, wird vor Restore die aktuell lokale PIN abgefragt.
- PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus der Backup-Datei duerfen nicht als Restore-Ziel angezeigt oder angewendet werden.
- Wenn das interne Sicherheitsbackup vor Restore fehlschlaegt, zeigt die UI Fortsetzen und Abbrechen als bewusste Entscheidung.

## Restore Safety Backup Dialog

Verwendung, wenn das interne Sicherheitsbackup vor Restore nicht erstellt werden konnte.

Aktionen:

- Trotzdem fortsetzen
- Abbrechen

Regeln:

- Der Dialog erklaert, dass der Restore lokale Backup-Daten ersetzt.
- Standardfokus liegt auf `Abbrechen`.
- Auswahl muss per D-Pad vollstaendig erreichbar sein.

## Validation Message

Verwendung fuer Feldfehler.

Beispiele:

- Name bereits vorhanden
- URL ungueltig
- Server nicht erreichbar
- Anmeldedaten ungueltig
- Datei nicht lesbar
- Zwischenablage enthaelt keine nutzbare M3U-Quelle
- EPG-Quelle nicht erreichbar
- User-Agent ungueltig oder nicht speicherbar
- Kontrast der Farbauswahl zu niedrig
- Geraet unterstuetzt diese Wiedergabeoption nicht
- PIN stimmt nicht ueberein
- PIN falsch
- PIN-Eingabe temporaer blockiert
- Backup-Datei ungueltig
- Backup-Version nicht unterstuetzt
- Backup beschaedigt
- Passphrase falsch
- Ziel nicht erreichbar
- Speicherzugriff verweigert
- Nicht genug Speicherplatz
- Migration fehlgeschlagen

## General Settings Section

Verwendung fuer globale App-Optionen unter Allgemein.

Abschnitte in Reihenfolge:

- App beim TV-Start starten
- Startbereich als Select Row; Home, Live-TV, Filme oder Serien; Standard Home
- doppelte Zurueck-Taste zum Beenden
- Sprache
- Hintergrundaktualisierung erlauben
- Sortierung merken
- User-Agent

Die Startbereich-Zeile zeigt den gespeicherten Wert und einen kurzen Hinweis, dass Aenderungen ab dem naechsten regulaeren App-Start gelten. Die Auswahl loest keine sofortige Screen-Navigation aus.

User-Agent bleibt die letzte Allgemein-Option.

## Provider Edit Section

Verwendung im Bearbeiten-Detail einer Wiedergabeliste.

Abschnitte:

- Status: Aktivieren / Deaktivieren
- Name
- Zugangsdaten oder Quelle
- EPG-Quellen und Prioritaet
- Logo-Prioritaet
- Gruppen verwalten
- Update-Optionen
- Detailinformationen
- Loeschen

Nicht enthalten:

- User-Agent

User-Agent wird ausschliesslich global in Allgemein gepflegt.

EPG-Prioritaeten binden Provider-Sender ueber Mapping an quellbezogene EPG-Kanäle und EPG-Programme.

## EPG Settings Section

Verwendung fuer globale EPG-Aktualisierungsoptionen.

Abschnitte:

- globales Aktualisierungsintervall als Select Row; Standard 24 Stunden
- EPG-Vergangenheit behalten als Select Row; 1 bis 14 Tage; Standard 1 Tag
- EPG-Zukunft laden/behalten als Select Row; 1 bis 14 Tage; Standard 7 Tage
- beim App-Start aktualisieren; Standard Ein
- bei Playlist-Aenderung aktualisieren; Standard Ein
- jetzt aktualisieren
- letzte Aktualisierung
- Aktualisierungshistorie

Das Intervall und die ereignisgesteuerten Ausloeser muessen als getrennte Optionen erkennbar bleiben.

App-Start- und Playlist-Aenderungs-Ausloeser sind DataStore-Optionen. Letzte Aktualisierung und Aktualisierungshistorie sind Anzeigen aus Refresh-Metadaten und keine gespeicherten Settings.

EPG-Aufbewahrung steuert nur EPG-Programmdaten. EPG-Quellen, EPG-Kanäle, Provider-Zuordnungen und manuelle Mappings bleiben erhalten.

## EPG Source Edit Section

Verwendung im Bearbeiten-Detail einer EPG-Quelle.

Abschnitte:

- Status: Aktivieren / Deaktivieren
- Name
- Quelle oder URL
- Zeitversatz
- Nutzung durch Provider
- letzte Aktualisierung
- technische Zaehler
- Loeschen

Nicht enthalten:

- User-Agent

Regeln:

- EPG-Daten werden als EPG-Quelle, EPG-Kanal und EPG-Programm behandelt.
- EPG-Programme werden nicht als providerbezogene Kopien verwaltet.
- Manuelle EPG-Zuordnung gewinnt immer vor automatischer Zuordnung.
- Automatische Zuordnung darf manuelle Zuordnung nicht ueberschreiben.

## Optik Settings Section

Verwendung fuer Darstellung und UI-Dichte.

Abschnitte:

- Hintergrundthema
- Akzentfarbe
- Transparenz
- Schriftgroesse
- Animationen
- globale Logo-Standardreihenfolge
- Logos-Ordner
- EPG-Darstellung

Regeln:

- Optik darf Grundlayout, D-Pad-Navigation und Fokuspflicht nicht veraendern.
- Auswahlwerte muessen auch aus TV-Entfernung lesbar bleiben.
- EPG-Darstellung nutzt Detail Panel, wenn mehrere Toggles zusammengehoeren.
- Logos-Ordner zeigt `Nicht gesetzt`, solange `localLogoFolderUri` leer ist; eine gesetzte Auswahl nutzt die persistierte Android-SAF-Berechtigung.

## Playback Settings Section

Verwendung fuer Player- und Stream-Verhalten.

Abschnitte:

- Puffergroesse
- Audio-Decoder
- Video-Decoder
- AFR
- Timeshift
- maximale Timeshift-Dauer
- Timeshift-Speicher
- Audio-Sprache
- Untertitel-Sprache
- Automatisch naechste Folge
- Countdown naechste Folge
- Audio-Passthrough
- externer Player

Regeln:

- Timeshift ist standardmaessig aktiviert.
- Die maximale Timeshift-Dauer nutzt 15, 30, 60 oder 120 Minuten; Standard ist 30 Minuten.
- Timeshift-Speicher nutzt Automatisch, RAM oder Festplatte; Standard ist Automatisch.
- Bei deaktiviertem Timeshift bleiben Dauer und Speicher sichtbar, werden aber deaktiviert dargestellt.
- `Festplatte` bezeichnet appverwalteten persistenten Geraetespeicher ohne freie Pfadauswahl.
- Auto-Next ist standardmaessig deaktiviert.
- Der Auto-Next-Countdown nutzt 5, 10, 15 oder 30 Sekunden; Standard sind 10 Sekunden.
- Bei deaktiviertem Auto-Next bleibt die Countdown-Zeile sichtbar, wird aber deaktiviert dargestellt.
- Optionen mit Stream-Neustartbedarf zeigen einen Hinweis.
- Nicht unterstuetzte Geraetefunktionen werden deaktiviert angezeigt.
- Externe Player-Auswahl nutzt einen D-Pad-bedienbaren Dialog.
- Externe Player schreiben keinen automatischen Fortschritt, Abschlussstatus oder Auto-Next-Zustand zurueck.
- Nach Rueckkehr aus externer Film- oder Episodenwiedergabe wird ein nicht blockierender Hinweis zum nicht ermittelbaren Fortschritt angezeigt.
- Timeshift-Verfuegbarkeit bleibt von Provider, Sender und Stream abhaengig.
- Audio- und Untertitel-Sprache werden beim Streamstart angewendet; manuelle Auswahl im Player gilt nur fuer die aktuelle Wiedergabe.
- Externer Player gilt nur fuer Filme und einzelne Episoden; Live-TV und Catch-Up bleiben im internen Vivicast-Player.

## Parental Control Settings Section

Verwendung fuer Kindersicherung.

Abschnitte:

- PIN setzen oder PIN aendern
- Einstellungen schuetzen
- Filme schuetzen
- Serien schuetzen
- Inhalte ab 18 schuetzen
- Freigabe fuer aktuelle Sitzung sperren
- Kindersicherung deaktivieren

Regeln:

- Schutzbereich-Toggles sind deaktiviert, solange keine PIN gesetzt ist.
- Wenn ein Schutzbereich aktiviert wird und keine PIN existiert, startet zuerst der PIN-setzen-Flow.
- PIN aendern erfordert aktuelle PIN.
- Kindersicherung deaktivieren erfordert aktuelle PIN und Bestaetigung.
- Session-Freigabe sperren aendert keine gespeicherten Einstellungen.
- FSK-18-Schutz darf nur bei eindeutiger 18+-Kennzeichnung greifen.

## Backup Settings Section

Verwendung fuer Backup, Import, Restore und lokale Datenwartung.

Abschnitte:

- Backup exportieren
- Backup importieren
- Backup-Ziel
- letzte Sicherung
- vorhandene Backups verwalten
- Medien-Cache
- Cache leeren
- Verlauf loeschen

Regeln:

- Standard-Backup ist risikoaermer und exportiert keine geheimen Zugangswerte.
- Verschluesseltes Vollbackup erfordert Passphrase und Warnhinweis.
- `Backup-Typ` ist nur ein Feld im Exportdialog mit Startwert `Standard`; er wird nicht als Settings-Zeile und nicht als dauerhafte Setting-Option gespeichert.
- Die Backup-Passphrase wird nicht gespeichert und darf nicht in Logs oder Diagnoseexporten erscheinen.
- Restore ersetzt in v1 immer den Backup-Umfang und braucht klare Bestaetigung.
- Es gibt keinen Restore-Modus, keinen Import-Konfliktdialog und keine Aktion `Als Kopie importieren`.
- Kompatible alte Backup-Schema-Versionen duerfen migriert werden; das ist kein Zusammenfuehren lokaler und importierter Daten.
- Bei aktivem lokalem Einstellungsschutz oder lokaler Backup-/Restore-PIN-Schutzregel wird fuer Restore die aktuell lokale PIN abgefragt.
- Nach Restore ist Kindersicherung deaktiviert; Backup-PIN, aktive PIN-Freigaben und Schutzflags aus der Backup-Datei werden nicht uebernommen.
- War Kindersicherung beim Export aktiv, zeigt die UI nach Restore einen Hinweis zur manuellen Reaktivierung unter `Einstellungen > Kindersicherung`.
- Importfehler duerfen lokale Daten nicht teilweise unkontrolliert veraendern.
- Medien-Cache zeigt mindestens die aktuelle Groesse; Dateianzahl und interne Grenze sind optional.
- Die Groesse oder Rotation des Medien-Caches ist in v1 nicht frei konfigurierbar.
- `Cache leeren` nutzt einen Bestaetigungsdialog und loescht nur Medien-Cache-Dateien fuer Logos, Poster, Staffelbilder und Episodenbilder.
- `Cache leeren` darf keine Providerdaten, Favoriten, Verlaeufe, Wiedergabefortschritt, Suchverlauf, EPG-Zuordnungen, Zugangsdaten, EPG-Programmdaten oder aktive Stream-/Timeshift-Puffer entfernen.
- `Verlauf loeschen` nutzt einen Auswahl- und Bestaetigungsdialog mit Live-TV-Verlauf, Filmverlauf plus Film-Wiedergabefortschritt, Serienverlauf plus Episoden-Wiedergabefortschritt, Suchverlauf und gesamtem Verlauf.
- Freie Verlaufslimits werden nicht angeboten; der Suchverlauf bleibt auf maximal 20 Eintraege begrenzt.
- Bei aktivem Einstellungsschutz brauchen Cache- und Verlaufsloeschung eine PIN-Abfrage.

## Action Button

Verwendung fuer eindeutige Aktionen.

Beispiele:

- Wiedergabeliste hinzufuegen
- alle Wiedergabelisten aktualisieren
- EPG-Quelle hinzufuegen
- jetzt aktualisieren
- Backup exportieren
- Backup importieren
- Freigabe fuer aktuelle Sitzung sperren

Regeln:

- Primaere Aktionen muessen schneller erreichbar sein als seltene Detailaktionen.
- Kritische Aktionen brauchen Bestaetigung.

## Warning Text

Verwendung fuer kritische Aktionen, fehlende Zugangsdaten, Loeschhinweise, Neustartbedarf, Stream-Neustart, fehlende Geraeteunterstuetzung, Re-Import, Restore-Hinweise, PIN-Deaktivierung, temporaere PIN-Sperre oder sensible Backup-Daten.

Warnungen muessen knapp und eindeutig sein.
