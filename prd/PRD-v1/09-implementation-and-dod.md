# Vivicast PRD v1
## Kapitel 9 - Akzeptanzkriterien und Definition of Done

Status: bereinigt v34

---

# Zweck

Diese Datei definiert fachliche Akzeptanzkriterien und Release-Definition-of-Done fuer Vivicast.

Sie enthaelt bewusst keinen festen Implementierungsplan und keine Codex-Vorgaben.

Codex erstellt den konkreten Umsetzungsplan auf Basis von PRD, Architektur und Design selbst.

---

# Verbindliche Quellen

Produktumfang und fachliche Anforderungen:

- `prd/PRD-v1/`

Architekturentscheidungen:

- `architecture/decisions/`

Layout, Navigation und Interaktion:

- `design/screens/`
- `design/interaction/`
- `design/components/`
- `design/design-system/`

Codex-Arbeitsregeln:

- `codex/README.md`
- `codex/coding-rules.md`

Teststrategie und messbare DoD:

- `prd/PRD-v1/13-test-strategy.md`

---

# Akzeptanzkriterien

## Home

Muss funktionieren:

- Home ist fester Bestandteil der App
- Home ist Standard-Startscreen
- Startbereich ist unter Allgemein einstellbar
- Startbereich bietet Home, Live-TV, Filme und Serien; Standard ist Home
- Suche und Einstellungen sind keine waehbaren Startbereiche
- eine Aenderung gilt ab dem naechsten regulaeren App-Start und navigiert die laufende Sitzung nicht sofort um
- App-Autostart verwendet den konfigurierten Startbereich
- explizite Deep Links und Android-TV-Ziele haben Vorrang; Rueckkehr aus dem Hintergrund behaelt den aktuellen Kontext
- leere Zielbereiche zeigen ihren normalen Empty State ohne automatischen Home-Fallback
- Home zeigt Fortsetzen fuer Filme und Serien gemischt
- Home-Fortsetzen enthaelt nur nicht abgeschlossene Filme und Episoden mit fortsetzbarem Fortschritt
- Home zeigt Live-TV zuletzt gesehene Sender getrennt von Fortsetzen
- Home zeigt Live-TV nicht in Fortsetzen
- Home zeigt keine Favoriten-Row
- Home zeigt keinen Provider- oder Update-Statusbereich
- leeres Home zeigt Hinweis plus Aktion `Wiedergabeliste hinzufuegen`
- Home startet bei Rueckkehr fresh und stellt alten Fokus nicht wieder her

## Hauptnavigation

Muss funktionieren:

- Hauptbereiche sind Home, Live-TV, Filme, Serien, Suche und Einstellungen
- Top Navigation ist auf Hauptscreens dauerhaft sichtbar
- Player blendet Top Navigation aus
- Zurueck springt auf Hauptscreens zuerst in die Top Navigation
- doppelte Zurueck-Bestaetigung beendet aus der Top Navigation
- Wechsel zwischen Hauptbereichen stellt alten Bereichsfokus nicht wieder her
- Suche verlassen springt zuerst in die Top Navigation

## Live-TV

Muss funktionieren:

- mehrere Provider anzeigen
- Provider getrennt halten
- Kategorien anzeigen
- Favoriten-Kategorie anzeigen
- Senderliste anzeigen
- Sender ohne Kategorie unter `Nicht kategorisiert` anzeigen
- EPG anzeigen, falls vorhanden
- Sender ohne EPG weiterhin anzeigen
- beim blossen Senderfokus keinen Stream starten
- erstes OK in der Senderspalte startet fest Sender-Modus und Vorschau und fokussiert die aktuelle EPG-Sendung, sofern vorhanden
- zweites OK auf der fokussierten aktuellen EPG-Sendung startet Vollbild
- keine Preview-Einstellung und kein direkter Vollbildstart beim ersten OK anbieten
- Sender oeffnen
- CH+ und CH- unterstuetzen
- Catch-Up anbieten, wenn Provider und EPG es erlauben
- aktive Streams bei Refresh nicht unterbrechen

## Filme

Muss funktionieren:

- frisches Oeffnen fokussiert die erste Kategorie des ersten Providers
- globale Kategorien Favoriten und Fortsetzen stehen oberhalb der Provider
- globale Kategorien sind sichtbar, aber nicht der initiale Inhaltsfokus
- Provider-Kategorien sind pro Provider gruppiert
- gleichnamige Provider-Kategorien werden nicht zusammengefuehrt
- Layout nutzt linke Kategorien, rechtes Poster Grid und Detailbereich darueber
- OK auf Film oeffnet Detailseite
- Film kann abgespielt werden
- begonnener Film kann fortgesetzt werden
- Film kann von Anfang gestartet werden
- Filme gelten ab mindestens 95 Prozent oder beim tatsaechlichen Medienende als abgeschlossen
- abgeschlossene Filme werden aus allen Fortsetzen-Bereichen entfernt
- `Als gesehen markieren` und `Als ungesehen markieren` sind auf der Film-Detailseite verfuegbar
- `Als ungesehen markieren` loescht den gespeicherten Filmfortschritt vollstaendig
- externe Filmwiedergabe erzeugt oder aktualisiert keinen automatischen Fortschritt und setzt keinen Abschlussstatus
- Favoriten koennen verwaltet werden
- Trailer-Verhalten ueber YouTube funktioniert
- fehlende Poster werden per Fallback behandelt
- Detailinformationen werden angezeigt, soweit vorhanden
- Zurueck geht Detailseite -> Grid -> Kategorien -> Top Navigation

## Serien

Muss funktionieren:

- frisches Oeffnen fokussiert die erste Kategorie des ersten Providers
- Serienbereich nutzt denselben Grundaufbau wie Filme
- globale Kategorien Favoriten und Fortsetzen stehen oberhalb der Provider
- globale Kategorien sind sichtbar, aber nicht der initiale Inhaltsfokus
- Provider-Kategorien sind pro Provider gruppiert
- gleichnamige Provider-Kategorien werden nicht zusammengefuehrt
- OK auf Serie oeffnet Serien-Detailseite
- Serien-Detailseite zeigt Serieninfos, Fortsetzen, Staffeln und Episoden
- OK auf Episode startet oder setzt direkt fort
- keine separate Episode-Detailseite erforderlich
- einzelne Episoden gelten ab mindestens 95 Prozent oder beim tatsaechlichen Medienende als abgeschlossen
- die fokussierte Episode bietet `Als gesehen markieren` beziehungsweise `Als ungesehen markieren`
- `Als ungesehen markieren` loescht den gespeicherten Episodenfortschritt vollstaendig
- komplette Staffeln und Serien besitzen in v1 keine manuelle Gesehen-/Ungesehen-Aktion
- Auto-Next ist unter Wiedergabe einstellbar und standardmaessig deaktiviert
- Auto-Next Countdown ist standardmaessig 10 Sekunden; 5, 10, 15 und 30 Sekunden sind moeglich
- Countdown-Auswahl bleibt bei deaktiviertem Auto-Next sichtbar, ist aber nicht bedienbar
- bei deaktiviertem Auto-Next erscheint `Naechste Folge abspielen` erst nach dem tatsaechlichen Episodenende und startet nur nach OK
- bei aktiviertem Auto-Next erscheint `Naechste Folge in X` X Sekunden vor dem tatsaechlichen Episodenende und aktualisiert X sekundenweise
- OK auf dem Auto-Next-Hauptbutton startet die naechste Episode sofort; ohne Eingabe startet sie beim Ablauf am Episodenende
- der sichtbare Button `Zurueck` erscheint zeitgleich neben dem Hauptbutton; das Panel besitzt keinen Button `Abbrechen`
- OK auf `Zurueck` oder die Zurueck-Taste verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit wiederhergestelltem Staffel-/Episodenkontext
- nach der letzten Episode einer Staffel folgt die erste verfuegbare Episode der naechsten Staffel
- nach der letzten Episode der Serie erscheint kein Auto-Next-Panel
- bei externer Episodenwiedergabe erscheinen kein Auto-Next-Panel und kein automatischer Wechsel zur naechsten Episode
- externe Episodenwiedergabe erzeugt oder aktualisiert keinen automatischen Fortschritt und setzt keinen Abschlussstatus
- Zurueck geht Player -> Serienkontext -> Detailseite -> Grid -> Kategorien -> Top Navigation

## Suche

Muss funktionieren:

- frisches Oeffnen fokussiert Suchfeld
- Suchfeld oeffnet System-Tastatur
- Suchhistorie wird dauerhaft im Suchscreen angezeigt
- Suchhistorie bleibt auch bei gefuelltem Suchfeld und sichtbaren Ergebnissen sichtbar
- Sprachsuche startet nur ueber explizite Mikrofon-Aktion
- Ergebnisgruppen sind Kanäle, Filme, Serien und EPG
- Episoden erscheinen nicht als eigene Suchgruppe und nicht als eigenes Suchergebnis
- Ergebnisgruppen werden als horizontale Rows dargestellt
- lokale Suche ueber Room FTS4
- Debounce von 300 ms
- leere Suche fuehrt keine Ergebnisabfrage aus
- ein normalisiertes Zeichen sucht nur Titel- oder Name-Prefixe fuer Kanäle, Filme und Serien
- EPG-Suche startet erst ab drei normalisierten Zeichen
- Prefix-Suche ist tokenbasiert; reine Infix-Suche ist kein v1-Ziel
- jede Ergebnisgruppe liefert maximal 20 Treffer
- Ranking bevorzugt exakte Titel-/Name-Treffer, dann Prefix, dann Token-Treffer, dann Metadaten
- EPG-Ranking bevorzugt laufende und nahe zukuenftige Sendungen
- Suchverlauf mit maximal 20 Eintraegen
- einzelne Suchverlaufseintraege loeschen
- gesamten Suchverlauf loeschen
- leeren Zustand anzeigen, wenn keine Ergebnisse vorhanden sind
- Kanal-Ergebnis oeffnet den zugehoerigen Sender im Live-TV-Browser; der weitere Preview-/Vollbildablauf folgt der Live-TV-Spezifikation
- Film Ergebnis oeffnet Film-Detailseite
- Serien Ergebnis oeffnet Serien-Detailseite
- EPG Ergebnis oeffnet Live-TV Sender mit fokussiertem EPG-Eintrag

## Einstellungen

Muss funktionieren:

- frisches Oeffnen fokussiert Allgemein
- Gruppen sind Allgemein, Wiedergabelisten, EPG, Optik, Wiedergabe, Kindersicherung, Backup und Über die App
- Allgemein bietet Startbereich als Auswahl mit Home, Live-TV, Filme und Serien; Standard ist Home
- User-Agent bleibt die letzte Option in Allgemein
- HTTP User Agent ist eine globale Einstellung unter Allgemein
- HTTP User Agent gilt appweit fuer HTTP-Anfragen, sofern technisch anwendbar
- HTTP User Agent ist nicht pro Wiedergabeliste oder Provider konfigurierbar
- Wiedergabeliste hinzufuegen startet mit Name als Pflichtfeld
- Providername ist eindeutig und zeigt Fehler, wenn bereits vorhanden
- Add Flow fragt nach Name, Quelltyp, bei M3U Eingabeart und danach Daten
- M3U unterstuetzt URL, Datei und Zwischenablage
- HTTP-Quellen sind erlaubt, werden aber sichtbar als unsicher markiert
- HTTPS wird bevorzugt
- TLS-Zertifikatsfehler werden nicht bypassed
- Provider- und EPG-Formulare bieten keine eigenen Header-, Cookie- oder User-Agent-Optionen
- M3U zeigt keine Importauswahl fuer Live-TV, Filme oder Serien
- M3U-Datei wird nicht dauerhaft lokal kopiert
- M3U importiert tolerant mit Teilfehlerstatus und bricht nicht wegen einzelner fehlerhafter Eintraege ab
- Xtream Codes nutzt Server, Benutzername und Passwort
- Xtream Server erlaubt http, https und Portangabe
- Xtream Codes bietet Importoptionen fuer Live-TV, Filme und Serien
- Xtream Codes importiert unvollstaendige Eintraege, wenn Kern-ID und Name vorhanden sind
- vor Speichern wird die Quelle getestet
- bei fehlgeschlagenem Test wird nicht gespeichert und ein Fehlerhinweis angezeigt
- nach erfolgreichem Speichern startet direkt Aktualisierung oder Import
- wenn der direkte erste Import nach dem Speichern fehlschlaegt, bleibt der Provider mit Fehlerstatus bestehen und zeigt keine halb importierten Inhalte
- Bearbeiten erlaubt alle Felder ausser Quelltyp
- Bearbeiten erlaubt Aktivieren und Deaktivieren
- Bearbeiten erlaubt Zugangsdaten oder M3U-Daten zu aendern
- bei geaenderten Zugangsdaten oder URLs wird vor Speichern erneut getestet
- EPG-Quellen werden pro Provider zugewiesen und priorisiert
- EPG-Vergangenheit behalten ist global einstellbar von 1 bis 14 Tagen und standardmaessig 1 Tag
- EPG-Zukunft laden/behalten ist global einstellbar von 1 bis 14 Tagen und standardmaessig 7 Tage
- EPG-Daten werden als EPG-Quelle, EPG-Kanal und EPG-Programm quellbezogen gespeichert
- Provider-Sender erhalten EPG ueber Mapping und Provider-EPG-Prioritaet
- manuelle EPG-Zuordnung gewinnt immer vor automatischer Zuordnung
- Logo-Prioritaet pro Provider kann gesetzt werden
- Gruppen koennen angezeigt, ausgeblendet und sortiert werden
- Update-Optionen pro Provider sind konfigurierbar
- Ablaufdatum und maximale Verbindungen werden angezeigt, falls vorhanden
- Provider loeschen nutzt einfache Bestaetigung
- wenn Einstellungsschutz aktiv ist, wird beim Loeschen zusaetzlich PIN abgefragt
- PIN-Felder oeffnen die Android-/TV-Systemtastatur als numerische Passwort-Eingabe
- Vivicast zeigt keine eigene Zifferntastatur fuer PINs
- PIN-Eingaben bestaetigen nach vier Ziffern nicht automatisch; die sichtbare Aktion bleibt erforderlich
- nach fuenf falschen PIN-Eingaben wird temporaer gesperrt
- PIN-Sperrdauern sind 30 Sekunden, 60 Sekunden und danach 5 Minuten
- App-Neustart hebt eine laufende PIN-Sperre nicht auf
- erfolgreiche PIN-Eingabe gibt nur den betroffenen Schutzbereich fuer die aktuelle App-Sitzung frei
- Timeshift ist ein- und ausschaltbar und standardmaessig aktiviert
- maximale Timeshift-Dauer bietet 15, 30, 60 und 120 Minuten
- maximale Timeshift-Dauer ist standardmaessig 30 Minuten
- Timeshift-Speicher bietet Automatisch, RAM und Festplatte
- Timeshift-Speicher ist standardmaessig Automatisch
- maximale Dauer und Speicher bleiben bei Timeshift Aus sichtbar, aber deaktiviert
- Festplatte nutzt appverwalteten persistenten Geraetespeicher ohne freie Pfadauswahl
- `Automatisch naechste Folge` ist ein Toggle unter Wiedergabe und standardmaessig Aus
- `Countdown naechste Folge` bietet 5, 10, 15 und 30 Sekunden; Standard sind 10 Sekunden
- die Countdown-Zeile bleibt bei Auto-Next Aus sichtbar, aber deaktiviert
- die feste Abschluss-Schwelle von 95 Prozent wird nicht als Einstellung angezeigt
- der externe Player ist nur eine Wiedergabeuebergabe und schreibt in v1 keinen automatischen VOD-Fortschritt zurueck
- `Über die App` enthaelt den Bereich `Diagnose und Support`
- `Diagnose und Support` bietet `Diagnoseprotokollierung` als Toggle; Standard ist `Aus`
- `Diagnose und Support` bietet `Aufbewahrungsdauer` von 1 bis 7 Tagen; Standard ist 1 Tag
- die Aufbewahrungsdauer bleibt bei ausgeschalteter Diagnoseprotokollierung sichtbar, ist dann aber deaktiviert
- vorhandene Sitzungen bleiben nach dem Ausschalten bis zu ihrem regulaeren Ablauf exportierbar
- `Diagnose und Support` bietet die Aktion `Diagnoseprotokoll exportieren`
- der Inhalt einer Log- oder Diagnoseprotokolldatei wird niemals in der App angezeigt
- Logdatei-Inhalte koennen nicht in die Zwischenablage kopiert werden
- allgemeine nicht-private Support-Informationen duerfen angezeigt und kopiert werden
- Exportstatus zeigt nur Erfolg, Fehler oder Exportziel und niemals Loginhalt
- das ZIP enthaelt verpflichtend `vivicast-diagnostics.log` und `diagnostics-metadata.json`
- die Logdatei enthaelt nur die freigegebenen technischen Ereigniskategorien
- die Metadatendatei enthaelt App-/Build-, Geraete-, Datenbank-, Sprach-, Zeitzonen-, Zeitraum-, Sitzungs-, Grenzwert- und Trunkierungsdaten
- pro App-Prozessstart bei aktiver Protokollierung entsteht eine neue logische Diagnosesitzung
- offene Sitzungen werden beim naechsten Prozessstart systemgestuetzt oder anhand von `lastRecordedAt` abgeschlossen
- Bildschirm-Aus, Standby oder TV-Aus beendet eine weiterlaufende Sitzung nicht kuenstlich
- das interne Gesamtlimit betraegt 20 MiB, die Segmentgrenze 2 MiB und die Sitzungsgrenze drei Segmente beziehungsweise 6 MiB Logdaten
- sitzungsinterne Rotation entfernt das aelteste geschlossene Segment; globale Groessenbereinigung entfernt zuerst die aeltesten abgeschlossenen Sitzungen
- aktuelles Schreibsegment und Metadaten der aktiven Sitzung bleiben geschuetzt
- Provider- und Inhaltsnamen, Suchverlauf, Rohdaten, Datenbank-Dumps, Screenshots und ungefiltertes System-Logcat werden nicht exportiert
- technische Zuordnungen verwenden bei Bedarf nur neutrale interne IDs
- Backup zeigt lokale Cache-Wartung mit aktueller Groesse des Medien-Caches und der Aktion `Cache leeren`
- die Groesse oder Rotation des Medien-Caches ist in v1 nicht frei konfigurierbar
- `Cache leeren` loescht nur Medien-Cache-Dateien wie Logos, Poster, Staffelbilder und Episodenbilder
- Cache-Loeschung entfernt keine Providerdaten, Favoriten, Verlaeufe, Wiedergabefortschritt, Suchverlauf, EPG-Zuordnungen, Zugangsdaten, EPG-Programmdaten oder aktive Stream-/Timeshift-Puffer
- Backup bietet Loeschaktionen fuer Live-TV-Verlauf, Filmverlauf mit Film-Wiedergabefortschritt, Serienverlauf mit Episoden-Wiedergabefortschritt, Suchverlauf und gesamten Verlauf
- Verlaufslimits sind in v1 nicht frei konfigurierbar; der Suchverlauf bleibt fest auf maximal 20 Eintraege begrenzt
- wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN fuer Backup/Restore verlangt, fragt Restore vor dem Einspielen die aktuell lokale PIN ab
- verschluesselte Vollbackups nutzen ein versioniertes AES-GCM-Schutzformat mit KDF-Metadaten, Salt, Nonce, Ciphertext und Authentifizierungstag
- die Backup-Passphrase wird nicht gespeichert, nicht geloggt und nicht in Diagnoseexporte geschrieben
- falsche Vollbackup-Passphrase bricht vor lokalen Datenaenderungen ab
- PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus Backup-Dateien werden nach Restore nicht uebernommen
- nach Restore ist Kindersicherung deaktiviert
- wenn Kindersicherung beim Export aktiv war, zeigt die App nach Restore einen Hinweis zur manuellen Reaktivierung unter `Einstellungen > Kindersicherung`

## IPTV und EPG

Muss funktionieren:

- M3U
- Xtream Codes
- XMLTV
- toleranter Teilimport fuer M3U, Xtream Codes und XMLTV
- Parser-Diagnose ohne Zugangswerte, URLs, Rohdaten, Provider-/Inhaltsnamen oder ungefilterte Playlist-/XMLTV-Inhalte
- mehrere Provider
- Provider-Isolation
- Delta-Synchronisation
- atomarer Import und Refresh pro Provider und pro EPG-Quelle
- Staging vor produktivem Commit
- keine produktiven Inhaltsaenderungen vor erfolgreichem Commit
- alte Daten bleiben bei fehlgeschlagenem oder abgebrochenem Refresh erhalten
- EPG-Refresh
- EPG-Mapping
- EPG-Cleanup nach konfigurierter Vergangenheit/Zukunft
- Catch-Up, wenn verfuegbar

## Player

Muss funktionieren:

- Live-TV Wiedergabe
- Filmwiedergabe
- Serienwiedergabe
- Catch-Up Wiedergabe
- externer Player nur fuer Filme und Episoden, nicht fuer Live-TV oder Catch-Up
- PlaybackRequest enthaelt Medientyp, Provider, stabile Medienreferenz, Herkunft, Rueckkehrziel, Startposition und EPG-/Timeshift-Kontext
- Stream-URLs werden just-in-time erzeugt und nicht dauerhaft als Klartext gespeichert oder geloggt
- HTTP-Weiterleitungen bleiben Laufzeitdaten; finale Redirect-URLs werden nicht gespeichert oder geloggt
- nur http/https und Media3-/ExoPlayer-kompatible Wiedergabeformate als v1-Ziel
- Overlay oeffnen und schliessen
- Timeline-Bedienung gemaess Design-Interaction-Spec
- Audio-Auswahl
- Untertitel-Auswahl
- globale Audio-/Untertitel-Defaults werden beim Streamstart angewendet; manuelle Player-Auswahl gilt nur fuer die aktuelle Wiedergabe
- Bildformat-Auswahl
- Retry bei Startfehlern
- Reconnect bei Streamabbruch
- nur eine aktive Wiedergabe gleichzeitig
- hoechstens ein aktiver Timeshift-Puffer
- Timeshift-Puffer respektiert die konfigurierte maximale Dauer
- Timeshift-Puffer verwendet Automatisch, RAM oder Festplatte gemaess Einstellung
- Senderwechsel verwirft den aktiven Timeshift-Puffer
- deaktiviertes oder nicht verfuegbares Timeshift sperrt Live-TV-Seek und zeigt einen Hinweis
- Timeshift gilt nur fuer Live-TV; Speicher- oder Ressourcenfehler lassen Live-TV ohne Timeshift weiterlaufen
- `Cache leeren` entfernt keine aktiven Stream- oder Timeshift-Puffer
- Catch-Up setzt EPG-Start und -Ende, erlaubtes Rueckblickfenster und Sender-/Provider-Unterstuetzung voraus
- aktuelle Live-Sendungen werden ueber Live-TV oder Timeshift und nicht als Catch-Up gestartet
- Catch-Up erzeugt keinen VOD-PlaybackProgress und kein Resume-Ziel
- das Serien-Folgepanel unterscheidet den manuellen Zustand nach Episodenende und den aktiven Countdown vor Episodenende
- das Erreichen der festen 95-Prozent-Abschluss-Schwelle beendet die Wiedergabe nicht und loest kein Folgepanel oder Auto-Next aus
- der Folgebutton besitzt den Initialfokus; OK startet sofort, der aktive Countdown startet ohne Eingabe bei Ablauf
- Hauptbutton und `Zurueck` erscheinen im Folgepanel zeitgleich; OK auf `Zurueck` oder die Zurueck-Taste fuehrt mit wiederhergestelltem Staffel-/Episodenkontext zur Serien-Detailseite
- Rueckkehr zu Sender, Film-Detailseite oder Serienkontext gemaess Herkunft
- der interne Player ist die einzige Quelle fuer automatische VOD-Position, Dauer, Fortschritt, Medienende, Abschlussstatus und Auto-Next
- automatischer VOD-Fortschritt wird erst ab mindestens 10 Sekunden Position oder mindestens 1 Prozent Fortschritt angelegt
- automatischer VOD-Fortschritt wird mindestens alle 10 Sekunden sowie bei Pause, Seek-Ende, Player-Verlassen, App-Hintergrund und Medienende gespeichert
- nach Rueckkehr aus einem externen Film- oder Episodenplayer bleibt vorhandener Fortschritt unveraendert und Vivicast zeigt einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte

## Android TV

Muss funktionieren:

- D-Pad Navigation
- OK
- Zurueck
- CH+
- CH-
- sichtbarer Fokus auf allen interaktiven Elementen
- Android TV globale Suche
- Deep Links
- Watch Next fuer Filme und Serienepisoden
- Android-TV-Systemsuche enthaelt Live-TV, Filme und Serien, aber keine EPG-Treffer, keine Episoden als eigene Treffer und kein Catch-Up
- geschuetzte Inhalte erscheinen nicht in Android-TV-Systemsuche oder Watch Next, solange der jeweilige Schutz aktiv ist
- Deep Links verwenden stabile fachliche Ziel-IDs statt lokaler Room-IDs
- Deep Links enthalten keine Stream-URLs, Tokens, Zugangswerte, HTTP-Header oder Cookies
- Deep Links, Android-TV-Suchergebnisse und Watch Next pruefen beim Oeffnen aktuelle Providerverfuegbarkeit, Zugangsdatenstatus und Kindersicherung
- fehlende, geloeschte, pending oder deaktivierte Ziele zeigen einen kontrollierten Fehler- oder Nicht-verfuegbar-Zustand ohne stillen Home-Fallback
- Watch Next wird nur fuer Filme und Serienepisoden aus internem Playback Progress und manuellen Gesehen-/Ungesehen-Aktionen gepflegt
- externe Player erzeugen keine Watch-Next-Updates
- Provider-Deaktivierung, Provider-Loeschung, Restore, Migration und Schutzregel-Aenderung bereinigen Android-TV-Systemsuche und Watch Next

## Datenhaltung

Muss funktionieren:

- Room
- DataStore
- Android Keystore und geschuetzter Secret Store
- Android Keystore fuehrt nicht exportierbare Schluessel
- geheime Nutzwerte liegen in einem geschuetzten Secret Store
- lokale Suche
- Android-TV-Systemsuche und Watch Next werden als abgeleitete Systemintegrationsdaten aus produktiven Room-Daten gepflegt
- Android-TV-Systemintegrationsdaten sind kein Backup-Bestandteil
- Favoriten
- Verlauf
- Playback Progress
- Backup
- Restore
- der Startbereich wird mit dem Standardwert `HOME` als `startDestination` in DataStore gespeichert
- `startDestination` erlaubt ausschliesslich `HOME`, `LIVE_TV`, `MOVIES` und `SERIES`; ungueltige Werte fallen auf `HOME` zurueck
- das globale EPG-Aktualisierungsintervall wird mit dem Standardwert 24 Stunden als `epgRefreshIntervalHours` in DataStore gespeichert
- EPG-App-Start- und Playlist-Aenderungs-Refresh werden als `epgRefreshOnAppStartEnabled = true` und `epgRefreshOnPlaylistChangeEnabled = true` in DataStore gespeichert
- `localLogoFolderUri` ist standardmaessig `null` und verweist bei gesetztem Wert auf eine persistierbare Android-SAF-URI
- der Exportdialog enthaelt `Backup-Typ` nur als transientes Dialogfeld mit Startwert `STANDARD`; es gibt keine eigene Settings-Zeile und keinen dauerhaft gespeicherten Wert
- Timeshift-Aktivstatus, maximale Dauer und Speicherwahl werden in DataStore gespeichert
- Audio-/Untertitel-Defaults und External-Player-Modus werden in DataStore gespeichert; sitzungsbezogene Track-Auswahl nicht
- Auto-Next wird als `autoNextEpisodeEnabled = false` und `autoNextEpisodeCountdownSeconds = 10` gespeichert; als Countdown sind nur 5, 10, 15 und 30 zulaessig
- externe Player erzeugen und aktualisieren keine `PlaybackProgressEntity`-Datensaetze
- Diagnoseprotokollierung und Aufbewahrungsdauer werden in DataStore gespeichert
- Diagnosesitzungen, Segmente und Rotationsmetadaten liegen nur als appinterne Dateien im privaten App-Speicher und nicht in Room oder im Standard-Backup
- Groesse des Medien-Caches, Cache-Rotation und frei konfigurierbare Verlaufslimits werden nicht in DataStore gespeichert
- Medien-Cache-Dateien sind nicht Teil des Standard-Backups
- PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus Backups werden nicht in lokale Sicherheitszustaende uebernommen
- PIN-Fehlversuchszaehler, Sperrstufe und `lockedUntil` werden als lokale Sicherheitszustaende behandelt
- aktive PIN-Freigaben werden nur im Speicher gehalten

## Sicherheit

Muss funktionieren:

- keine Klartextspeicherung sensibler Zugangsdaten
- Datenklassifizierung in `Normal`, `Sensibel`, `Geheim` und `Sicherheitswirksam lokal`
- sichere Speicherung geheimer M3U-, Server-, EPG- und Stream-URLs mit Tokens oder eingebetteten Zugangswerten
- normale nicht geheime Quellenadressen duerfen als Konfiguration gespeichert werden
- geschuetzte Speicherung verwendet Keystore-Schluessel und Secret Store, nicht `EncryptedSharedPreferences` oder Jetpack `security-crypto` als neue Basis
- HTTP fuer nutzerdefinierte IPTV-, EPG- und Stream-Quellen bleibt erlaubt, aber sichtbar unsicher und zentral policygebunden
- TLS-Zertifikatsfehler werden nicht bypassed
- Diagnoseereignisse werden vor dauerhaftem Schreiben bereinigt und der Exportstrom wird vor Ausgabe erneut zentral geprueft
- bereinigte Log-Exporte enthalten keine geheimen Zugangswerte, Tokens, Cookies, HTTP-Header oder URLs
- bereinigte Log-Exporte enthalten keine Provider-/Inhaltsnamen, Suchverlaeufe, Rohdaten, Datenbank-Dumps, Screenshots oder ungefiltertes System-Logcat
- Logdatei-Inhalte werden in der App weder angezeigt noch kopiert
- optional verschluesselte Backups
- verschluesselte Vollbackups schuetzen Geheimnisse mit Passphrase und versioniertem AES-GCM-Container
- Restore verwendet fuer PIN-Autorisierung ausschliesslich die aktuell lokale PIN und nicht die Backup-Datei
- PIN-Eingaben sind verdeckt, nutzen die numerische Passwort-Systemtastatur und bieten keine Zwischenablage, Autovervollstaendigung oder Klartextanzeige
- PIN-Fehlversuche werden nach fuenf Eingaben mit 30 Sekunden, 60 Sekunden und danach 5 Minuten gesperrt
- nach Restore ist Kindersicherung deaktiviert und muss bei Bedarf manuell neu aktiviert werden
- kein Tracking
- kein Cloud-Konto-System

---

# Definition of Done

Vivicast gilt als Release-Kandidat, wenn alle folgenden Punkte erfuellt sind.

## Testabdeckung

- jede kritische Muss-Anforderung besitzt mindestens einen reproduzierbaren Testfall
- Teststrategie, Testarten, Referenzfixtures, Mockserver-Szenarien, Roundtrips und Performancebudgets folgen `prd/PRD-v1/13-test-strategy.md`
- automatisierbare fachliche Regeln sind automatisiert getestet
- manuelle Android-TV-QA ist nur fuer Fokus, D-Pad, Back-Pfade, visuelle Skalierung, echte TV-Geraete und Systemoberflaechen zulaessig
- manuelle QA besitzt protokollierte Schritte und Ergebnisnachweis
- bekannte Test- oder Budgetabweichungen sind vor Release mit Risikoentscheidung dokumentiert

## Funktional

- alle Akzeptanzkriterien sind umgesetzt
- alle Muss-Anforderungen aus dem PRD sind umgesetzt
- keine kritischen Funktionen fehlen

## Stabilitaet

- keine reproduzierbaren kritischen Fehler offen
- Fehlerzustaende werden sichtbar angezeigt
- Refreshes verursachen keinen unnoetigen Datenverlust
- Refresh-Fehler vor Commit veraendern keine produktiven Inhalte
- Commit-Fehler rollen die betroffene Provider- oder EPG-Transaktion vollstaendig zurueck
- aktive Streams werden durch Refreshes nicht unterbrochen

## Performance

Messbare Zielbudgets fuer Import, Suche, Datenbank, EPG und Speicherverbrauch liegen verbindlich in `prd/PRD-v1/13-test-strategy.md`.

Fluessige Bedienung bei Zielgroessen:

- 10.000+ Sender
- 50.000+ Filme
- 20.000+ Serien
- mehrere Millionen EPG-Eintraege

Pflicht:

- grosse Listen werden lazy dargestellt
- Suche blockiert die UI nicht
- Performancebudgets sind auf dem dokumentierten Referenzgeraet oder Referenzprofil gemessen
- FTS-Indexe werden aus produktiven Daten gepflegt und nicht aus Staging gelesen
- Fokuswechsel bleibt fluessig
- Datenbankabfragen sind indexiert
- Timeshift bleibt innerhalb der konfigurierten Dauer und erzeugt keinen parallelen Puffer

## Android TV UX

- vollstaendige Bedienung per Fernbedienung
- kein Touch-only-Verhalten
- Fokus ist jederzeit sichtbar
- Zurueck-Verhalten ist konsistent
- Overlays blockieren Navigation nicht dauerhaft
- Texte sind aus TV-Entfernung lesbar
- Diagnoseexport zeigt keine Logvorschau und keinen Logdatei-Inhalt

## Datenhaltung

- lokale Daten bleiben nach Neustart verfuegbar
- Refresh funktioniert wiederholbar
- Provider-Refreshes sind pro Provider atomar
- EPG-Refreshes sind pro EPG-Quelle atomar
- Teilfehler duerfen valide Eintraege uebernehmen, aber Loeschungen nur aus autoritativen Teilbereichen ableiten
- verwaiste Staging-Daten werden nach Prozessabbruch bereinigt
- Backup funktioniert
- Restore ersetzt in v1 den Backup-Umfang ohne Zusammenfuehren, Konfliktdialoge oder `Als Kopie importieren`
- kompatible alte Backup-Schema-Versionen werden vor Restore migriert oder vor jeder lokalen Datenaenderung abgebrochen
- vor Restore wird nach erfolgreicher Validierung ein internes Sicherheitsbackup versucht; bei Fehlschlag entscheidet der Nutzer zwischen Abbruch und bewusstem Fortsetzen
- Standard-Backups verwenden stabile Referenzen statt lokaler Room-IDs
- Favoriten bleiben korrekt zugeordnet
- Verlauf und Playback Progress bleiben korrekt und koennen nach Restore bis zum Provider-Refresh pending sein
- Timeshift-Einstellungen bleiben nach Neustart erhalten
- Diagnoseprotokollierung und Aufbewahrungsdauer bleiben nach Neustart erhalten
- geschlossene Diagnosesitzungen werden nach Ablauf der Aufbewahrungsdauer automatisch entfernt
- Diagnosedaten ueberschreiten weder 20 MiB Gesamtgroesse noch 2 MiB je Segment noch drei Segmente pro Sitzung
- groessenbedingte Kuerzungen und Entfernungen werden im Diagnoseexport nachvollziehbar ausgewiesen

## Sicherheit

- Zugangsdaten sind geschuetzt gespeichert
- Keystore- oder Secret-Store-Verlust fuehrt zu `Zugangsdaten erforderlich`, nicht zu Klartext-Fallbacks
- exportierte Diagnoseprotokolle sind bereinigt
- das Diagnose-ZIP enthaelt die verbindlichen Log- und Metadatendateien
- ausgeschlossene sensible und inhaltliche Daten sind in keinem ZIP-Eintrag vorhanden
- Logdatei-Inhalte sind innerhalb der App nicht einsehbar oder kopierbar
- Backup-Schutz funktioniert
- Vollbackup-Passphrase wird nie gespeichert oder geloggt
- sensible URLs werden nicht offen gespeichert

## Wartung

- Cache-Verwaltung funktioniert
- Cache-Verwaltung zeigt die aktuelle Groesse des Medien-Caches und bietet `Cache leeren`
- `Cache leeren` entfernt nur Medien-Cache-Dateien und laesst fachliche lokale Daten unveraendert
- Verlauf kann in den definierten Bereichen geloescht werden
- Diagnosemodus funktioniert
- Datenbankwartung funktioniert
- bereinigter Diagnoseprotokoll-Export ist unter `Über die App > Diagnose und Support` erreichbar
- Fehlerprotokolle sind fuer Support exportierbar, ohne ihren Inhalt in der App anzuzeigen
- Diagnose-Sitzungsabschluss, Altersbereinigung und Rotation funktionieren auch nach nicht sauberem Prozessende konsistent

---

# Abgrenzung

Diese Datei ist keine Umsetzungsplanung.

Nicht enthalten:

- feste Implementierungsphasen
- Codex-Prompts
- Sprint-Plan
- Aufgabenreihenfolge
- Demo-Datenplan

Der konkrete Ablauf wird im App-Repository aus den Referenzquellen abgeleitet.

---

# PRD Status

```text
PRD v1 abgeschlossen
```

Produktname:

```text
Vivicast
```

Paketname:

```text
com.vivicast.tv
```
