# 07 - Einstellungen

Status: verbindlich v24

## Zweck

Der Einstellungen-Screen sammelt App-Konfigurationen für Allgemein, Wiedergabelisten, EPG, Optik, Wiedergabe, Kindersicherung, Speicher & Verlauf, Backup und Über die App.

Fachliche Einstellungsoptionen bleiben im PRD.

## Quellen

- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `prd/PRD-v1/10-backup-import-requirements.md`
- `prd/PRD-v1/11-about-app-requirements.md`
- `design/screens/08-playlist-epg.md`
- `design/components/settings.md`
- `design/components/about-app.md`
- `design/interaction/focus.md`
- `design/design-system/02-design-tokens.md`
- `design/components/player.md`

## Layout-Zonen

1. Top Navigation
2. linke Einstellungsgruppen
3. rechte Optionsliste
4. Detail- oder Dialogbereich

## Einstellungsgruppen

- Allgemein
- Wiedergabelisten
- EPG
- Optik
- Wiedergabe
- Kindersicherung
- Speicher & Verlauf
- Backup
- Über die App

Diese Gruppen sind final.

## Initialfokus

Beim frischen Öffnen liegt der Fokus auf Allgemein.

## Allgemein

Allgemein enthält globale App-Optionen.

Reihenfolge der Optionen:

1. App beim TV-Start starten
2. Startbereich
3. doppelte Zurück-Taste zum Beenden
4. Sprache
5. Hintergrundaktualisierung erlauben
6. Sortierung merken
7. User-Agent

Startbereich ist eine Select Row mit Home, Live-TV, Filme und Serien. Der initial sichtbare Standardwert ist Home.

Die Änderung gilt ab dem nächsten regulaeren App-Start; die Settings-Ansicht navigiert nach der Auswahl nicht unmittelbar in den gewählten Bereich.

User-Agent steht am Ende der Allgemein-Liste.

User-Agent gilt appweit, sofern technisch anwendbar, zum Beispiel für Playlist-, EPG-, Logo- und Stream-Anfragen.

User-Agent ist keine individuelle Wiedergabelisten-, Provider-, EPG-Quellen- oder Stream-Option.

Ein leerer User-Agent-Wert nutzt den App-Standard.

## Wiedergabelisten

Die Gruppe Wiedergabelisten fuehrt zur Quellenverwaltung aus `design/screens/08-playlist-epg.md`.

Primaere Aktionen:

- Wiedergabeliste hinzufügen
- alle Wiedergabelisten aktualisieren
- vorhandene Wiedergabeliste öffnen

Der Add Flow beginnt mit Name, danach Quelltyp.

Wiedergabelisten-Formulare dürfen keine eigene User-Agent-Option anzeigen.

## EPG

Die Gruppe EPG fuehrt zur EPG-Quellenverwaltung aus `design/screens/08-playlist-epg.md`.

Primaere Aktionen:

- EPG-Quelle hinzufügen
- EPG-Quelle bearbeiten
- EPG-Quelle löschen
- globales EPG-Aktualisierungsintervall konfigurieren; Standard 24 Stunden
- EPG-Vergangenheit behalten; 1 bis 14 Tage; Standard 1 Tag
- EPG-Zukunft laden/behalten; 1 bis 14 Tage; Standard 7 Tage
- EPG beim App-Start aktualisieren; Standard Ein
- EPG bei Playlist-Änderung aktualisieren; Standard Ein
- EPG jetzt aktualisieren
- EPG-Aktualisierungshistorie anzeigen

Das 24-Stunden-Intervall gilt nur für den automatischen intervallgesteuerten Refresh. Die anderen Ausloeser bleiben separat konfigurierbar beziehungsweise manuell ausloesbar.

Die App-Start- und Playlist-Änderungs-Ausloeser sind gespeicherte DataStore-Optionen. Die Aktualisierungshistorie ist nur Anzeige aus Refresh-Metadaten und keine eigene Einstellung.

EPG-Aufbewahrung steuert nur lokale EPG-Programmdaten. EPG-Quellen, EPG-Kanäle, Provider-Zuordnungen und manuelle Mappings bleiben erhalten.

EPG-Quellen-Formulare dürfen keine eigene User-Agent-Option anzeigen.

## Optik

Optik enthält visuelle Darstellung und UI-Dichte.

Sprache gehört nicht in Optik, sondern in Allgemein.

Optik darf Grundlayout, D-Pad-Navigation, Mindestkontraste, Fokusindikator, Safe-Area und Mindestgrößen fokussierbarer Elemente nicht verändern.

Reihenfolge der Optionen:

1. Hintergrundthema
2. Akzentfarbe
3. Transparenz
4. Schriftgröße
5. Animationen
6. globale Logo-Standardreihenfolge
7. Logos-Ordner
8. EPG-Darstellung

### Hintergrundthema

Auswahl für grundlegende Flaechenwirkung.

Beispiele:

- Standard dunkel
- Dunkel kontrastreich
- AMOLED dunkel

Das Theme bleibt immer TV-tauglich und dunkel. Helle Themes sind nicht Teil von v1.

### Akzentfarbe

Auswahl für Akzent- und Markenfarbe.

Akzentfarbe darf Fokus, Warnung, Fehler und Erfolg nicht unlesbar machen.

Fokus muss immer nach Design-System-Regeln sichtbar bleiben.

### Transparenz

Auswahl gemaess Design-System.

Transparenz darf Textbereiche, Dialoge und Player-Overlays nicht unlesbar machen.

Wenn eine Transparenzstufe technisch oder visuell unpassend ist, muss die App sie für betroffene Komponenten begrenzen.

### Schriftgröße

Auswahlwerte:

- klein
- mittel
- gross
- sehr gross

Bei `sehr gross` dürfen Layouts nicht brechen. Lange Texte werden kontrolliert gekuerzt oder in Detailbereichen scrollbar gemacht.

### Animationen

Auswahlwerte:

- aus
- schnell
- normal
- langsam

Animationen dürfen Fokuswechsel nicht unvorhersehbar machen.

### Globale Logo-Standardreihenfolge

Auswahlwerte:

- Logos aus Playlist bevorzugen
- Logos aus EPG bevorzugen
- Logos aus lokalem Ordner bevorzugen

Diese Einstellung ist globaler Standard. Eine Wiedergabeliste kann über ihre eigene Logo-Priorität abweichen.

### Logos-Ordner

Oeffnet eine Ordnerauswahl oder einen Detaildialog für lokale Logos.

Wenn kein Ordner gesetzt ist, nutzt die App Playlist- oder EPG-Logos gemaess Logo-Priorität.

Der gespeicherte Standard ist `Nicht gesetzt`. Eine gesetzte Auswahl nutzt die gespeicherte Ordner-URI und die persistierte Android-SAF-Berechtigung.

### EPG-Darstellung

Oeffnet einen Detailbereich mit mehreren Toggles.

Enthält:

- Kanalnummer anzeigen
- Sendername anzeigen
- zweizeilige Sendernamen erlauben
- Catch-Up-Symbole anzeigen
- laufendes Programm hervorheben
- zweizeilige Programmtitel erlauben
- Fortschritt markieren
- animiertes Scrollen erlauben

## Wiedergabe

Wiedergabe enthält Player- und Stream-Verhalten.

Reihenfolge der Optionen:

1. Puffergröße
2. Audio-Decoder
3. Video-Decoder
4. AFR
5. Timeshift
6. maximale Timeshift-Dauer
7. Timeshift-Speicher
8. Audio-Sprache
9. Untertitel-Sprache
10. Automatisch nächste Folge
11. Countdown nächste Folge
12. Audio-Passthrough
13. externer Player

### Puffergröße

Auswahlwerte:

- aus
- klein
- mittel
- gross
- sehr gross

Änderungen gelten beim nächsten Streamstart.

Wenn ein Stream bereits laeuft, muss ein Hinweis angezeigt werden, dass die Änderung erst nach Stream-Neustart gilt.

### Audio-Decoder und Video-Decoder

Auswahlwerte:

- Hardware
- Software

Standard ist Hardware.

Software dient als Fallback für problematische Streams oder Geräte.

Änderungen gelten beim nächsten Streamstart.

### AFR

Toggle für automatische Bildwiederholraten-Anpassung, sofern Gerät und Android-Version dies unterstuetzen.

Wenn nicht unterstuetzt, wird die Option deaktiviert mit Hinweis angezeigt.

### Timeshift

Toggle für Timeshift-Unterstuetzung.

Standardwert:

```text
Ein
```

Timeshift bleibt abhängig von Provider, Sender und Stream.

Wenn Timeshift deaktiviert oder nicht verfuegbar ist, darf Live-TV-Seek nicht möglich sein und muss einen kurzen Hinweis anzeigen.

### Maximale Timeshift-Dauer

Auswahlwerte:

- 15 Minuten
- 30 Minuten
- 60 Minuten
- 120 Minuten

Standardwert:

```text
30 Minuten
```

Die Auswahl begrenzt die maximale Dauer des aktiven Timeshift-Puffers.

### Timeshift-Speicher

Auswahlwerte:

- Automatisch
- RAM
- Festplatte

Standardwert:

```text
Automatisch
```

`Automatisch` laesst Vivicast zwischen RAM und persistentem Gerätespeicher wählen.

`Festplatte` verwendet appverwalteten persistenten Gerätespeicher. Eine freie Ordner- oder Pfadauswahl ist in v1 nicht vorgesehen.

Maximale Dauer und Timeshift-Speicher bleiben bei deaktiviertem Timeshift sichtbar, werden dann aber deaktiviert dargestellt.

Änderungen an den Timeshift-Einstellungen werden beim nächsten Aufbau eines Timeshift-Puffers wirksam.

### Audio-Sprache

Auswahl für bevorzugte Audiosprache.

Werte mindestens:

- Systemstandard
- Deutsch
- Englisch
- Original

Falls die bevorzugte Sprache im Stream nicht existiert, verwendet die App die erste verfuegbare Spur.

Die Einstellung wird beim Streamstart angewendet. Eine manuelle Audioauswahl im Player gilt nur für die aktuelle Wiedergabe.

### Untertitel-Sprache

Auswahl für bevorzugte Untertitelsprache.

Werte mindestens:

- Aus
- Systemstandard
- Deutsch
- Englisch

Falls die bevorzugte Sprache im Stream nicht existiert, bleiben Untertitel aus oder die App nutzt die Stream-Vorgabe.

Die Einstellung wird beim Streamstart angewendet. Eine manuelle Untertitelauswahl im Player gilt nur für die aktuelle Wiedergabe.

### Automatisch nächste Folge

Toggle für Serienepisoden im internen Vivicast-Player.

Standardwert:

```text
Aus
```

Bei `Aus` erscheint die manuelle Aktion `Nächste Folge abspielen` erst nach dem tatsaechlichen Episodenende.

Bei `Ein` erscheint `Nächste Folge in X` um den konfigurierten Zeitraum vor dem Episodenende und startet die nächste Episode beim Ablauf automatisch.

### Countdown nächste Folge

Auswahlwerte:

- 5 Sekunden
- 10 Sekunden
- 15 Sekunden
- 30 Sekunden

Standardwert:

```text
10 Sekunden
```

Die Zeile bleibt bei deaktiviertem Auto-Next sichtbar, wird dann aber deaktiviert dargestellt und ist nicht fokussierbar oder änderbar.

Die Abschluss-Schwelle für Filme und Episoden ist in v1 fest auf 95 Prozent gesetzt und erscheint nicht als eigene Settings-Zeile.

### Audio-Passthrough

Toggle für Passthrough an kompatible Ausgabegeräte.

Wenn nicht unterstuetzt, wird die Option deaktiviert mit Hinweis angezeigt.

### Externer Player

Auswahlwerte:

- immer intern
- immer extern
- jedes Mal fragen

Standard ist immer intern.

Bei `jedes Mal fragen` oeffnet der Playerstart einen D-Pad-bedienbaren Auswahl-Dialog.

Externe Player schreiben in v1 keinen automatischen Fortschritt zurück. Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.

Die Einstellung gilt nur für Filme und einzelne Serienepisoden. Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.

Es existiert immer nur eine aktive Wiedergabe.

## Kindersicherung

Kindersicherung enthält PIN-Optionen und Schutzbereiche.

Reihenfolge der Optionen:

1. PIN setzen oder PIN ändern
2. Einstellungen schützen
3. Filme schützen
4. Serien schützen
5. Inhalte ab 18 schützen
6. Freigabe für aktuelle Sitzung sperren, falls aktuell freigegeben
7. Kindersicherung deaktivieren, falls PIN gesetzt

### PIN setzen oder ändern

Wenn keine PIN gesetzt ist, zeigt die erste Option `PIN setzen`.

Wenn eine PIN gesetzt ist, zeigt die erste Option `PIN ändern`.

PIN-Dialoge sind D-Pad-bedienbar und nutzen verdeckte Zifferneingabe.

PIN-Felder öffnen die Android-/TV-Systemtastatur als numerische Passwort-Eingabe.

Vivicast zeigt keine eigene Zifferntastatur.

Nach der vierten Ziffer wird nicht automatisch gespeichert oder entsperrt. Der Nutzer bestätigt bewusst über `Speichern`, `Entsperren` oder `Deaktivieren`.

Bei temporaerer Sperre oeffnet sich keine Tastatur; der Dialog zeigt Restzeit und `Abbrechen`.

Nach fuenf falschen PIN-Eingaben wird die Eingabe blockiert. Sperrdauern sind 30 Sekunden, 60 Sekunden und danach 5 Minuten. Ein App-Neustart hebt eine laufende Sperre nicht auf.

PIN setzen:

1. neue PIN eingeben
2. neue PIN wiederholen
3. bei Übereinstimmung speichern

PIN ändern:

1. aktuelle PIN eingeben
2. neue PIN eingeben
3. neue PIN wiederholen
4. bei korrekter aktueller PIN und Übereinstimmung speichern

PIN-Laenge:

```text
4 Stellen
```

### Schutzbereich-Toggles

Diese Toggles sind deaktiviert, solange keine PIN gesetzt ist.

Schutzbereiche:

- Einstellungen schützen
- Filme schützen
- Serien schützen
- Inhalte ab 18 schützen

Wenn ein Schutzbereich aktiviert wird und keine PIN existiert, startet zuerst der PIN-setzen-Flow.

### PIN-Abfrage

PIN wird in einem Dialog abgefragt, wenn ein geschuetzter Bereich geöffnet oder eine geschuetzte Aktion ausgefuehrt wird.

Der Dialog zeigt:

- Titel des geschuetzten Bereichs
- verdeckte PIN-Eingabe
- numerische Passwort-Systemtastatur
- Fehlerhinweis bei falscher PIN
- verbleibende Sperrzeit, falls temporaer blockiert
- Abbrechen

### Freigabe für aktuelle Sitzung

Nach erfolgreicher PIN-Eingabe bleibt der jeweilige Schutzbereich für die aktuelle App-Sitzung freigegeben.

Wenn mindestens ein Schutzbereich aktuell freigegeben ist, zeigt Kindersicherung die Aktion `Freigabe für aktuelle Sitzung sperren`.

Diese Aktion setzt alle laufenden Freigaben zurück, ändert aber keine Einstellungen.

Session-Freigaben sind nur im Speicher und erscheinen nicht in Backups oder Restore-Zusammenfassungen.

### Kindersicherung deaktivieren

Deaktivieren erfordert aktuelle PIN und Bestätigung.

Nach Deaktivierung werden alle Schutzbereiche ausgeschaltet.

### Falsche PIN

Falsche PIN zeigt einen Fehler im Dialog.

Nach fuenf falschen Eingaben wird die Eingabe temporaer blockiert.

Der blockierte Zustand muss in der UI mit Restzeit sichtbar sein.

Sperrdauern:

```text
30 Sekunden
60 Sekunden
5 Minuten
```

### FSK-18-Schutz

FSK-18-Schutz greift nur bei eindeutiger 18+-Kennzeichnung oder vorhandenen Altersfreigabe-Metadaten.

Fehlende Altersdaten werden nicht automatisch als 18+ behandelt.

## Speicher & Verlauf

Speicher & Verlauf enthält lokale Wartungsaktionen für Medien-Cache und Verlauf.

Reihenfolge der Optionen:

1. Medien-Cache
2. Cache leeren
3. Verlauf löschen

## Backup

Backup enthält Export, Import, Restore und Verwaltung vorhandener Backup-Dateien.

Fachlicher Datenvertrag: `prd/PRD-v1/10-backup-import-requirements.md`.

Reihenfolge der Optionen:

1. Backup exportieren
2. Backup importieren
3. Backup-Ziel
4. letzte Sicherung
5. vorhandene Backups verwalten

### Backup exportieren

Oeffnet einen Dialog mit Zielauswahl und Exportmodus.

Ziele:

- lokaler Speicher
- SMB
- Google Drive

Exportmodi:

- Standard-Backup
- verschlüsseltes Vollbackup

Der Exportmodus ist ein transienter Dialogwert. Der Dialog startet mit `Standard-Backup`; dieser Wert wird nicht als dauerhafte Einstellung gespeichert.

Standard-Backup enthält keine geheimen Zugangswerte.

Verschlüsseltes Vollbackup erfordert eine Backup-Passphrase und zeigt vor Export einen klaren Hinweis, dass geheime Quellen- und Zielzugangsdaten enthalten sein können.

Die Passphrase wird nicht gespeichert, nicht geloggt und bei falscher Eingabe bricht Restore vor lokalen Datenaenderungen ab.

PIN-Pruefwerte und sicherheitswirksame Kindersicherung-Schutzflags werden nicht aus Backups wiederhergestellt.

### Backup importieren

Oeffnet Dateiauswahl oder Zielauswahl.

Nach Auswahl einer Backup-Datei zeigt die App eine Zusammenfassung:

- App-Version
- Backup-Zeitpunkt
- Backup-Modus
- enthaltene Datenbereiche
- ob sensible Zugangsdaten enthalten sind
- ob Kindersicherung beim Export aktiv war und nach Restore deaktiviert wird
- ob Migration erforderlich ist
- dass der Backup-Umfang lokale Daten ersetzt
- dass Provider und Daten, die nicht im Backup enthalten sind, entfernt werden

Restore ist in v1 immer ein Ersetzen des Backup-Umfangs. Es gibt keinen Restore-Modus, kein Zusammenfuehren, keinen Konfliktdialog und keine Aktion `Als Kopie importieren`.

Vor Restore validiert die App Backup-Datei, Passphrase, Schema-Migration und Inhalt.

Nach erfolgreicher Validierung versucht die App ein internes Sicherheitsbackup der aktuellen lokalen Daten zu erstellen. Wenn dieses Sicherheitsbackup fehlschlaegt, fragt die App den Nutzer, ob Restore trotzdem fortgesetzt oder abgebrochen werden soll.

Restore erfordert eine klare Bestätigung.

Wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN für Backup/Restore verlangt, muss vor Restore die aktuell lokale PIN bestätigt werden. Eine in der Backup-Datei enthaltene fruehere PIN oder fruehere Schutzkonfiguration darf für diese Autorisierung nicht verwendet werden.

Nach Restore ist Kindersicherung deaktiviert. Wenn Kindersicherung beim Export aktiv war, zeigt die App einen Hinweis, dass die PIN-Funktion vor dem Backup aktiv war, nach dem Restore deaktiviert wurde und in `Einstellungen > Kindersicherung` manuell wieder aktiviert werden muss.

### Backup-Ziel

Auswahlwerte:

- lokaler Speicher
- SMB
- Google Drive

SMB- und Google-Drive-Anmeldung werden nicht in Standard-Backups exportiert.

### Vorhandene Backups verwalten

Oeffnet Detailbereich mit vorhandenen Backups, falls Ziel dies unterstuetzt.

Mögliche Aktionen:

- Backup anzeigen
- Backup importieren
- Backup löschen

Backup löschen braucht eine Bestätigung.

### Import- und Restore-Fehler

Fehler werden inline angezeigt.

Beispiele:

- Backup-Datei ungültig
- Backup-Version nicht unterstuetzt
- Backup beschädigt
- Passphrase falsch
- Ziel nicht erreichbar
- Speicherzugriff verweigert
- Nicht genug Speicherplatz
- Migration fehlgeschlagen

Fehler dürfen lokale Daten nicht teilweise unkontrolliert verändern.

## Über die App

Über die App zeigt technische App-Informationen, rechtliche Hinweise sowie Diagnose und Support.

Fachlicher Datenvertrag: `prd/PRD-v1/11-about-app-requirements.md`.

Reihenfolge der Optionen:

1. App-Informationen
2. Versionsinformationen kopieren
3. Diagnose und Support
4. Lizenzhinweise
5. Datenschutzinformationen
6. Drittanbieter-Lizenzen

### App-Informationen

Anzeigen:

- App-Name
- App-Version
- Build-Nummer
- Paketname
- Datenbank-Version
- Android-Version
- Gerätemodell
- Player-Engine

### Versionsinformationen kopieren

Kopiert eine kurze technische Zusammenfassung in die Zwischenablage.

Die kopierten Daten dürfen nur nicht-private technische Informationen enthalten.

### Diagnose und Support

Oeffnet einen Detailbereich mit allgemeinen supportrelevanten technischen Informationen, zwei Diagnoseeinstellungen und der Exportaktion.

Reihenfolge:

```text
Diagnoseprotokollierung: Aus | Ein
Aufbewahrungsdauer: 1 bis 7 Tage
Diagnoseprotokoll exportieren
Support-Informationen kopieren
```

Defaults:

```text
Diagnoseprotokollierung: Aus
Aufbewahrungsdauer: 1 Tag
```

Regeln:

- Die Aufbewahrungsdauer bleibt bei ausgeschalteter Diagnoseprotokollierung sichtbar, ist dann aber nicht änderbar.
- Bereits vorhandene Sitzungen bleiben nach dem Ausschalten bis zu ihrem regulaeren Ablauf exportierbar.
- Größenlimit und Rotation sind feste interne Werte und werden nicht als weitere Settings-Zeilen angezeigt.
- Allgemeine nicht-private Support-Informationen dürfen angezeigt und kopiert werden.
- Das Diagnoseprotokoll wird vor Export zentral bereinigt.
- Diagnoseereignisse werden schon vor dauerhaftem Schreiben zentral bereinigt.
- Der Inhalt der Log- oder Diagnoseprotokolldatei wird niemals in der App angezeigt.
- Es gibt keine Logvorschau, keinen scrollbaren Logtext und keine Aktion zum Kopieren des Logdatei-Inhalts.
- Nach Export zeigt die UI nur Erfolg, Fehler oder Exportziel.
- Das ZIP enthält verpflichtend `vivicast-diagnostics.log` und `diagnostics-metadata.json`.
- Die Logdatei enthält nur freigegebene technische Ereignisse zu App, Import/Refresh, EPG, Player, Netzwerk, Backup/Restore, Cache und Datenbank sowie bereinigte Stacktraces für Warnungen, Fehler und Abstuerze.
- Die Metadatendatei enthält App-/Build-, Geräte-, Datenbank-, Sprach-, Zeitzonen-, Exportzeit-, Zeitraum-, Sitzungs-, Grenzwert- und Trunkierungsdaten.
- Der Export umfasst alle noch vorhandenen Segmente und weist den tatsaechlich enthaltenen Zeitraum aus.
- Intern gelten 20 MiB Gesamtlimit, 2 MiB pro Segment und maximal drei Segmente beziehungsweise 6 MiB Logdaten pro Sitzung.
- Zugangswerte, Tokens, Cookies, HTTP-Header, URLs, Rohdaten, Provider-/Inhaltsnamen, Suchverlauf, Datenbank-Dumps, Screenshots und ungefiltertes System-Logcat sind ausgeschlossen.
- Unsichere oder nicht eindeutig unkritische Diagnosefelder werden verworfen oder neutralisiert.
- Neutrale interne IDs dürfen bei Bedarf als technische Referenz verwendet werden.

### Lizenzhinweise und Drittanbieter-Lizenzen

Oeffnet lokale Seiten oder Dialoge.

Die Seiten müssen per D-Pad lesbar und scrollbar sein.

### Datenschutzinformationen

Oeffnet lokale Datenschutzinformationen oder eine rechtlich passende Informationsseite.

### Nicht enthalten

Nicht in Über die App:

- Anzeige oder Vorschau von Logdatei-Inhalten
- Kopieren von Logdatei-Inhalten
- Daten zurücksetzen
- Backup oder Restore
- Wiedergabelistenverwaltung
- EPG-Verwaltung
- automatische Update-Installation

## Optionsliste

Jede Option zeigt Titel, kurze Beschreibung, aktuellen Wert oder Status.

Optional kann ein Hinweis bei Gefahr, Neustartbedarf, Re-Import, Stream-Neustart, fehlender Geräteunterstuetzung, Diagnoseexport oder sensiblen Backup-Daten angezeigt werden.

## Bedienung

- Hoch/Runter bewegt innerhalb der Gruppen oder Optionen.
- Rechts wechselt von Gruppe zu Optionen.
- Links wechselt von Optionen zu Gruppen.
- OK oeffnet Toggle, Auswahl, Textfeld, PIN-Dialog, Dialog oder Detailseite.
- Zurück schliesst Dialoge, danach verlaesst es Einstellungen.

## Dialoge

Dialoge müssen per D-Pad bedienbar sein.

Primaere Aktion und Abbrechen müssen klar fokussierbar sein.

Kritische Aktionen brauchen eine klare Bestätigung.

Wenn der PIN-Schutz für Einstellungen aktiv ist, muss vor geschuetzten Aktionen die PIN abgefragt werden.

## Zustaende

Loading: nur für Bereiche mit Datenzugriff, zum Beispiel Backup oder Quellen.

Empty: keine Wiedergabelisten oder keine EPG-Quellen.

Error: Fehler wird im betroffenen Bereich angezeigt, nicht global blockierend.

Unsupported: Option wird deaktiviert mit kurzer Begruendung angezeigt, wenn das Gerät die Funktion nicht unterstuetzt.

Locked: geschuetzter Bereich wartet auf PIN-Freigabe.

Temporarily Blocked: PIN-Eingabe ist nach falschen Eingaben kurz blockiert.

Migration Required: Backup kann nur nach kompatibler Migration importiert werden.

Credentials Required: Quelle wurde importiert, benoetigt aber erneute Zugangsdaten.

Copied: Kopieraktion für allgemeine Support- oder Versionsinformationen wurde erfolgreich ausgefuehrt.

Exported: Diagnoseprotokoll wurde erfolgreich exportiert; Loginhalt wird nicht angezeigt.

## Komponenten

- Settings Group List
- Settings Row
- Toggle Row
- Select Row
- Text Input Row
- PIN Input Row
- Color Select Row
- Detail Panel
- Dialog Panel
- Warning Text
- Status Badge
- Diagnose und Support Panel
- Diagnoseprotokoll Export Action
