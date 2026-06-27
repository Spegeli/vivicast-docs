# Vivicast PRD v1
## Kapitel 4.4-4.6 - Suche, Einstellungen und Player Anforderungen

Status: bereinigt v29

---

# Verbindliche Designquellen

Konkretes Layout, Fokusverhalten, Overlay-Struktur, Settings-Struktur und UI-Zustaende sind nicht in dieser PRD-Datei definiert.

Verbindliche Quellen:

- `design/screens/03-player.md`
- `design/screens/06-search.md`
- `design/screens/07-settings.md`
- `design/screens/08-playlist-epg.md`
- `design/interaction/02-player-timeline-controls.md`
- `design/interaction/focus.md`
- `design/components/player.md`
- `design/components/settings.md`

---

# 4.4 Suche

## Ziel

Die Suche dient als globale Suchfunktion für Inhalte der App.

Sie durchsucht:

- Live-TV
- Filme
- Serien
- EPG

Episoden werden in v1 nicht als eigene Suchgruppe und nicht als eigenes Suchergebnis angeboten.

Episoden bleiben über die Serien-Detailseite erreichbar.

## Start und Eingabe

Beim Öffnen der Suche liegt der Fokus auf dem Suchfeld.

OK auf dem Suchfeld oeffnet die System-Tastatur.

## Sprachsuche

Sprachsuche startet ausschliesslich durch Benutzeraktion.

Ablauf:

1. Sprachsuche aktivieren
2. Android-Sprachsuche startet
3. erkannter Text wird ins Suchfeld übernommen
4. lokale Suche aktualisiert Ergebnisse

Sprachsuche startet nie automatisch.

## Suchverlauf

Der Suchverlauf speichert maximal 20 Eintraege.

Suchverlauf wird dauerhaft im Suchscreen angezeigt.

Er bleibt auch bei gefuelltem Suchfeld und sichtbaren Ergebnisgruppen sichtbar.

Unterstuetzt:

- einzelnen Eintrag löschen
- gesamten Verlauf löschen

Die maximale Größe ist nicht konfigurierbar.

## Live Search

Suche erfolgt während der Eingabe.

Debounce:

```text
300 ms
```

Datenquelle:

```text
Room
```

## Technischer Suchvertrag

Die interne Vivicast-Suche erfolgt vollstaendig lokal über Room.

Für v1 verwendet Vivicast Room FTS4 für die Volltextsuche.

Normale `LIKE`-Abfragen oder separate Normalisierungstabellen dürfen für Hilfsabfragen verwendet werden, sind aber nicht die primaere Suchstrategie für grosse Inhaltsmengen.

Die App sendet während der Suche keine Suchanfragen an Provider.

Interne FTS-Indexe werden getrennt nach Ergebnisgruppe gefuehrt:

```text
ChannelSearchFts
MovieSearchFts
SeriesSearchFts
EPGProgramSearchFts
```

Episoden werden nicht in den internen Suchindex aufgenommen. Nutzer erreichen Episoden über die Serien-Detailseite.

Die globale Android-TV-Systemsuche bleibt getrennt von der internen Vivicast-Suche. Sie folgt `ADR-008`, enthält keine EPG-Treffer, keine Episoden als eigene Treffer, kein Catch-Up und keine geschuetzten Inhalte bei aktivem Schutz.

## Suchnormalisierung

Query und Indextexte werden mit derselben Normalisierung verarbeitet:

- fuehrende und folgende Leerzeichen entfernen
- Kleinschreibung
- mehrere Whitespace-Zeichen zu einem Leerzeichen zusammenfassen
- Satzzeichen und Symbole als Trenner behandeln
- Umlaute und Diakritika tolerant behandeln
- `ae`, `oe`, `ue` als Varianten für `a`, `o`, `u` beruecksichtigen
- `ss` als Variante für `ß` beruecksichtigen

Normalisierung darf die angezeigten Originaltitel nicht verändern.

## Prefix-Suche und Mindestlaenge

Prefix-Suche ist tokenbasiert.

Beispiel:

```text
tat -> Tatort
```

Reine Infix-Suche ist kein v1-Ziel.

Beispiel:

```text
ort -> Tatort
```

Leere Suche zeigt Suchverlauf und fuehrt keine Ergebnisabfrage aus.

Bei einem normalisierten Zeichen sucht Vivicast nur Titel- oder Name-Prefixe in `Kanäle`, `Filme` und `Serien`.

EPG-Suche startet erst ab drei normalisierten Zeichen.

## Ranking

Ranking ist pro Ergebnisgruppe deterministisch.

Grundreihenfolge:

1. exakter Titel- oder Name-Treffer
2. Titel- oder Name-Prefix
3. Token-Treffer im Titel oder Namen
4. Treffer in Metadaten wie Kategorie, Beschreibung oder Jahr

Danach wird stabil sortiert:

- `Kanäle`: Provider-Reihenfolge, Kategorie-Reihenfolge, Sendername
- `Filme`: Titel, Jahr, Provider
- `Serien`: Titel, Jahr beziehungsweise Zeitraum, Provider
- `EPG`: Relevanzzeit, Startzeit, Sendername, Titel

EPG-Ranking bevorzugt laufende Sendungen, danach nahe zukuenftige Sendungen. Vergangene Treffer werden niedriger bewertet.

## Ergebnislimits

Jede Ergebnisgruppe liefert maximal 20 Treffer.

Dieses Limit ist ein interner v1-Wert und keine sichtbare Einstellung.

Es gibt keine verpflichtende `Alle anzeigen`-Aktion. Nutzer sollen bei zu vielen Treffern die Suche verfeinern.

## Indexpflege

FTS-Indexe werden ausschliesslich aus produktiven Daten aufgebaut.

Staging-Daten aus Import oder Refresh dürfen nicht in Suchergebnissen erscheinen.

Provider-FTS-Updates laufen im selben atomaren Provider-Commit wie die zugehoerigen produktiven Providerdaten.

EPG-FTS-Updates laufen im selben atomaren EPG-Quellen-Commit wie die zugehoerigen EPG-Programme.

Rollback einer Provider- oder EPG-Transaktion rollt die FTS-Änderungen mit zurück.

Delete, Restore, Migration und EPG-Cleanup müssen betroffene FTS-Indexe aktualisieren oder neu aufbauen.

FTS-Indexe werden nicht aus Backups wiederhergestellt. Sie werden nach Restore aus produktiven Daten neu aufgebaut.

## Ergebnisgruppen

Ergebnisse müssen gruppiert werden nach:

- Kanäle
- Filme
- Serien
- EPG

Jede Gruppe wird als horizontale Row dargestellt.

Wenn keine Treffer existieren, wird ein leerer Zustand angezeigt.

## Ergebnisaktionen

- Kanal-Ergebnis oeffnet den zugehoerigen Sender im Live-TV-Browser. Der weitere Preview-/Vollbildablauf folgt der Live-TV-Spezifikation.
- Film Ergebnis oeffnet Film-Detailseite.
- Serien Ergebnis oeffnet Serien-Detailseite.
- EPG Ergebnis oeffnet Live-TV Sender mit fokussiertem EPG-Eintrag.

EPG-Ergebnisse öffnen Live-TV im Sender-Modus. Der passende Sender ist aktiv, die EPG-Spalte ist zum Zielprogrammpunkt gescrollt und der Zielprogrammpunkt erhaelt Fokus.

Für EPG-Ergebnisse gilt:

- aktuelle Sendung -> Vollbild Live-TV
- vergangene Sendung mit Catch-Up -> Catch-Up im internen Player
- vergangene Sendung ohne Catch-Up -> Details/Info, keine Wiedergabe
- zukuenftige Sendung -> Details/Info, keine Wiedergabe

---

## Player Retry- und Reconnect-Vertrag

Senderstart umfasst maximal 5 Versuche insgesamt inklusive Erstversuch.

Ein Versuch gilt als fehlgeschlagen, wenn ExoPlayer einen Fehler meldet oder innerhalb von 10 Sekunden kein abspielbarer Zustand erreicht wird.

Retry-Abstaende:

```text
0,5 s
1 s
2 s
4 s
```

Bei manuellem `Erneut versuchen` wird der Zaehler zurueckgesetzt.

Bei Senderwechsel werden laufende Start- oder Retry-Vorgaenge abgebrochen.

Reconnect startet nur fuer den zuletzt aktiven Sender. Waehrend Reconnect zeigt der Player einen nicht blockierenden Reconnect-Hinweis. Nach 5 erfolglosen Gesamtversuchen erscheint der Fehlerdialog.

---

# 4.5 Einstellungen

## Ziel

Einstellungen stellen alle Konfigurationsmöglichkeiten der App bereit.

Die Struktur muss TV-tauglich und bei vielen Optionen navigierbar bleiben.

Beim frischen Öffnen liegt der Fokus auf Allgemein.

## Hauptbereiche

Diese Gruppen sind final:

- Allgemein
- Wiedergabelisten
- EPG
- Optik
- Wiedergabe
- Kindersicherung
- Speicher & Verlauf
- Backup
- Über die App

## Grundregeln

Einstellungen müssen lokal gespeichert werden.

Änderungen sollen sofort gelten, sofern kein Neustart, Re-Import oder Stream-Neustart technisch notwendig ist.

Wenn eine Änderung erst später wirkt, muss die UI einen kurzen Hinweis anzeigen.

Kritische Aktionen wie Löschen, Backup-Import, Restore und PIN-Änderung brauchen eine klare Bestätigung.

## Settings-Vertrag v1

Jede sichtbare v1-Option muss Typ, Werte, Default, Speicherort und Wirkung besitzen.

Detailregeln stehen in den jeweiligen Unterkapiteln. Diese Tabelle ist die kompakte Implementierungsreferenz für sichtbare Settings-Zeilen, Detailpanels, Formulare und Aktionen.

| Gruppe | Sichtbare Option | Typ | Werte | Default | Speicherort | Wirkung |
| --- | --- | --- | --- | --- | --- | --- |
| Allgemein | App beim TV-Start starten | Toggle | Aus, Ein | Aus | DataStore `autoStartOnBootEnabled` | versucht App-Autostart, sofern Android TV dies erlaubt |
| Allgemein | Startbereich | Auswahl | Home, Live-TV, Filme, Serien | Home | DataStore `startDestination` | gilt beim nächsten regulaeren App-Start ohne explizites Ziel |
| Allgemein | Doppelte Zurück-Taste zum Beenden | Toggle | Aus, Ein | Ein | DataStore `requireDoubleBackToExit` | erzwingt zweite Zurück-Bestätigung aus Top Navigation |
| Allgemein | Sprache | Auswahl | Systemstandard, Deutsch, Englisch | Systemstandard | DataStore `appLanguage` | setzt App-Sprache beziehungsweise folgt System |
| Allgemein | Hintergrundaktualisierung erlauben | Toggle | Aus, Ein | Ein | DataStore `backgroundRefreshEnabled` | schaltet automatische Hintergrundaktualisierungen global frei oder aus |
| Allgemein | Sortierung merken | Toggle | Aus, Ein | Ein | DataStore `rememberSortOrderEnabled` | speichert Anzeige- und Sortierpräferenzen pro relevantem Bereich |
| Allgemein | User-Agent | Textfeld | leer oder gültiger User-Agent | App-Standard | DataStore `globalUserAgent` | gilt appweit für HTTP-Anfragen, sofern technisch anwendbar |
| Wiedergabelisten | Wiedergabeliste hinzufügen | Formular/Aktion | M3U, Xtream Codes | n/a | Room und verschlüsselte Quellenkonfiguration | legt neue Wiedergabeliste nach erfolgreichem Verbindungstest an |
| Wiedergabelisten | Wiedergabeliste bearbeiten | Formular/Aktion | vorhandener Quelltyp bleibt fix | n/a | Room und verschlüsselte Quellenkonfiguration | ändert Providerdaten nach erfolgreichem Test bei Quellenwechsel |
| Wiedergabelisten | Aktivieren | Toggle | Aus, Ein | Ein | Room Providerstatus | nimmt Provider in Anzeige und Refresh auf oder heraus |
| Wiedergabelisten | Logo-Priorität | Auswahl | globale Reihenfolge, Playlist, EPG, lokaler Ordner | globale Reihenfolge | Room Provider-/Playlist-Einstellung | überschreibt die globale Logo-Reihenfolge für diese Wiedergabeliste |
| Wiedergabelisten | Gruppen verwalten | Detailpanel | anzeigen, ausblenden, sortieren | alle importierten Gruppen sichtbar | Room Gruppeneinstellungen | steuert sichtbare Provider-Gruppen und Reihenfolge |
| Wiedergabelisten | Update-Optionen | Detailpanel | Intervall, App-Start, manuell | globale Hintergrundregel | Room Provider-Refresh-Einstellungen | steuert automatische und manuelle Playlist-Aktualisierung |
| Wiedergabelisten | Löschen | Bestätigte Aktion | löschen, abbrechen | n/a | Room und zugehoerige appinterne Daten | entfernt Provider und providergebundene Daten nach Bestätigung |
| EPG | Globales EPG-Aktualisierungsintervall | Auswahl | Stundenwert gemaess UI | 24 Stunden | DataStore `epgRefreshIntervalHours` | steuert intervallgesteuerten EPG-Refresh |
| EPG | EPG-Vergangenheit behalten | Auswahl | 1 bis 14 Tage | 1 Tag | DataStore `epgPastRetentionDays` | begrenzt vergangene EPG-Programmdaten |
| EPG | EPG-Zukunft laden/behalten | Auswahl | 1 bis 14 Tage | 7 Tage | DataStore `epgFutureRetentionDays` | begrenzt zukuenftige EPG-Programmdaten |
| EPG | EPG beim App-Start aktualisieren | Toggle | Aus, Ein | Ein | DataStore `epgRefreshOnAppStartEnabled` | startet EPG-Refresh beim App-Start, sofern Hintergrundaktualisierung erlaubt ist |
| EPG | EPG bei Playlist-Änderung aktualisieren | Toggle | Aus, Ein | Ein | DataStore `epgRefreshOnPlaylistChangeEnabled` | startet EPG-Refresh nach relevanter Playlist-Änderung |
| EPG | Jetzt aktualisieren | Aktion | starten | n/a | kein dauerhafter Optionswert | startet manuellen EPG-Refresh |
| EPG | EPG-Quelle hinzufügen/bearbeiten | Formular/Aktion | Quelle, Name, Zeitversatz, Aktivstatus | n/a | Room und verschlüsselte Quellenkonfiguration, falls noetig | verwaltet globale EPG-Quellen |
| EPG | EPG-Priorität pro Provider | Auswahl/Reihenfolge | eine oder mehrere EPG-Quellen | Importreihenfolge oder manuelle Reihenfolge | Room EPG-Zuordnung | bestimmt, welche EPG-Quelle für Provider/Sender gewinnt |
| EPG | EPG-Aktualisierungshistorie | Anzeige | letzte Laeufe und Fehler | n/a | appinterne Refresh-Metadaten `EpgRefreshRunMetadata` | zeigt Refresh-Verlauf ohne neue Einstellung |
| Optik | Hintergrundthema | Auswahl | Standard dunkel, Dunkel kontrastreich, AMOLED dunkel | Standard dunkel | DataStore `themeMode` | steuert dunkle Flaechenwirkung |
| Optik | Akzentfarbe | Auswahl | Vivicast Blau und weitere zulässige Akzente | Vivicast Blau | DataStore `accentColor` | steuert Marken-/Akzentfarbe ohne Fokuskontrast zu brechen |
| Optik | Transparenz | Auswahl | 0 %, 25 %, 50 % | 25 % | DataStore `panelTransparencyPercent` | steuert Panel-/Overlay-Transparenz innerhalb Lesbarkeitsgrenzen |
| Optik | Schriftgröße | Auswahl | klein, mittel, gross, sehr gross | mittel | DataStore `fontScale` | steuert Textgröße ohne Layoutbruch |
| Optik | Animationen | Auswahl | aus, schnell, normal, langsam | normal | DataStore `animationSpeed` | steuert Animationsumfang und Geschwindigkeit |
| Optik | Globale Logo-Standardreihenfolge | Auswahl | Playlist, EPG, lokaler Ordner | Playlist | DataStore `globalLogoPriority` | setzt Standardquelle für Logos, sofern Provider nicht abweicht |
| Optik | Logos-Ordner | Ordnerauswahl/Detail | nicht gesetzt oder lokaler Ordner | nicht gesetzt | DataStore `localLogoFolderUri` plus persistierte Android-SAF-Berechtigung | erlaubt lokale Logos als Quelle |
| Optik | EPG-Darstellung | Detailpanel Toggles | Kanalnummer, Sendername, zweizeilige Sendernamen, Catch-Up, laufendes Programm, zweizeilige Programmtitel, Fortschritt, animiertes Scrollen | Ein, Ein, Aus, Ein, Ein, Ein, Ein, Ein | DataStore `epgDisplayOptions` | steuert Dichte und Anzeige der EPG-UI |
| Wiedergabe | Puffergröße | Auswahl | aus, klein, mittel, gross, sehr gross | mittel | DataStore `bufferSize` | gilt beim nächsten Streamstart |
| Wiedergabe | Audio-Decoder | Auswahl | Hardware, Software | Hardware | DataStore `audioDecoderMode` | gilt beim nächsten Streamstart |
| Wiedergabe | Video-Decoder | Auswahl | Hardware, Software | Hardware | DataStore `videoDecoderMode` | gilt beim nächsten Streamstart |
| Wiedergabe | AFR | Toggle | Aus, Ein | Aus | DataStore `afrEnabled` | aktiviert automatische Bildwiederholraten-Anpassung, sofern unterstuetzt |
| Wiedergabe | Timeshift | Toggle | Aus, Ein | Ein | DataStore `timeshiftEnabled` | erlaubt Timeshift, sofern Provider, Sender und Stream es unterstuetzen |
| Wiedergabe | Maximale Timeshift-Dauer | Auswahl | 15, 30, 60, 120 Minuten | 30 Minuten | DataStore `timeshiftMaxDurationMinutes` | begrenzt aktiven Timeshift-Puffer |
| Wiedergabe | Timeshift-Speicher | Auswahl | Automatisch, RAM, Festplatte | Automatisch | DataStore `timeshiftStorage` | waehlt Speicherart für Timeshift-Puffer |
| Wiedergabe | Audio-Sprache | Auswahl | Systemstandard, Deutsch, Englisch, Original | Systemstandard | DataStore `preferredAudioLanguage` | bevorzugt passende Audiospur, sofern vorhanden |
| Wiedergabe | Untertitel-Sprache | Auswahl | Aus, Systemstandard, Deutsch, Englisch | Aus | DataStore `preferredSubtitleLanguage` | bevorzugt Untertitelspur oder deaktiviert Untertitel |
| Wiedergabe | Automatisch nächste Folge | Toggle | Aus, Ein | Aus | DataStore `autoNextEpisodeEnabled` | steuert Auto-Next für Serienepisoden im internen Player |
| Wiedergabe | Countdown nächste Folge | Auswahl | 5, 10, 15, 30 Sekunden | 10 Sekunden | DataStore `autoNextEpisodeCountdownSeconds` | legt Vorlaufzeit für Auto-Next-Panel fest |
| Wiedergabe | Audio-Passthrough | Toggle | Aus, Ein | Aus | DataStore `audioPassthroughEnabled` | aktiviert Passthrough, sofern Gerät und Ausgabe es unterstuetzen |
| Wiedergabe | Externer Player | Auswahl | immer intern, immer extern, jedes Mal fragen | immer intern | DataStore `externalPlayerMode` | steuert Wiedergabeübergabe ohne automatische Fortschrittsrueckgabe |
| Kindersicherung | PIN setzen/ändern | Dialog/Formular | 4 Ziffern | nicht gesetzt | geschuetzte Speicherung, kein Klartext | richtet PIN ein oder ändert sie nach aktueller PIN |
| Kindersicherung | Einstellungen schützen | Toggle | Aus, Ein | Aus | lokaler Sicherheitszustand | verlangt PIN für geschuetzte Settings-Aktionen |
| Kindersicherung | Filme schützen | Toggle | Aus, Ein | Aus | lokaler Sicherheitszustand | verlangt PIN für geschuetzte Filme |
| Kindersicherung | Serien schützen | Toggle | Aus, Ein | Aus | lokaler Sicherheitszustand | verlangt PIN für geschuetzte Serien |
| Kindersicherung | Inhalte ab 18 schützen | Toggle | Aus, Ein | Aus | lokaler Sicherheitszustand | verlangt PIN für eindeutig als 18+ erkannte Inhalte |
| Kindersicherung | Freigabe für aktuelle Sitzung sperren | Aktion | sperren | n/a | aktive PIN-Freigaben im Speicher | verwirft aktuelle Sitzungsfreigaben |
| Kindersicherung | Kindersicherung deaktivieren | Bestätigte Aktion | deaktivieren, abbrechen | n/a | lokaler Sicherheitszustand | entfernt Schutzbereiche nach aktueller PIN und Bestätigung |
| Backup | Backup exportieren | Aktion/Dialog | Exportdialog mit Ziel und Backup-Typ | n/a | externes Ziel; Metadaten lokal | erstellt Backup gemaess Backup-Vertrag |
| Backup | Backup importieren | Aktion/Dialog | Datei/Ziel auswählen | n/a | liest externes Backup | zeigt Zusammenfassung und startet Restore-Ersetzen nach Bestätigung |
| Backup | Backup-Ziel | Auswahl | lokaler Speicher, SMB, Google Drive | lokaler Speicher | DataStore `backupTargetType` und ggf. geschuetzte Zielauthentifizierung | bestimmt Export-/Importziel |
| Backup | Letzte Sicherung | Anzeige | Zeitpunkt oder Nie | Nie | Backup-Metadaten | zeigt letzten erfolgreichen Backup-Zeitpunkt |
| Backup | Vorhandene Backups verwalten | Detailpanel/Aktion | anzeigen, importieren, löschen | n/a | externes Ziel | verwaltet vorhandene Backup-Dateien |
| Speicher & Verlauf | Medien-Cache | Anzeige | aktuelle Größe, optional Anzahl/Grenze | n/a | Dateisystem berechnet | informiert über lokalen Medien-Cache |
| Speicher & Verlauf | Cache leeren | Bestätigte Aktion | löschen, abbrechen | n/a | appinterne Medien-Cache-Dateien | loescht nur Medien-Cache-Dateien |
| Speicher & Verlauf | Verlauf löschen | Auswahl und Bestätigung | Live-TV, Film, Serien, Suche, gesamter Verlauf | n/a | Room Verlauf und Progress, Suchverlauf | loescht gewählte Verlaufsdaten gemaess Vertrag |
| Über die App | App-Informationen | Anzeige/Detail | technische Appdaten | n/a | Laufzeit-/Build-/DB-Informationen | zeigt technische Appinformationen |
| Über die App | Versionsinformationen kopieren | Aktion | kopieren | n/a | Zwischenablage | kopiert nicht-private Versionsdaten |
| Über die App | Diagnose und Support | Detailpanel | Supportdaten, Diagnoseoptionen, Export | n/a | Laufzeitdaten, DataStore, private Diagnosedateien | oeffnet Diagnose- und Supportbereich |
| Über die App | Diagnoseprotokollierung | Toggle | Aus, Ein | Aus | DataStore `diagnosticsLoggingEnabled` | startet oder beendet interne Diagnosesitzung |
| Über die App | Aufbewahrungsdauer | Auswahl | 1 bis 7 Tage | 1 Tag | DataStore `diagnosticsRetentionDays` | begrenzt Aufbewahrung interner Diagnosesitzungen |
| Über die App | Diagnoseprotokoll exportieren | Aktion | ZIP exportieren | n/a | private Diagnosedateien zu externem OutputStream | exportiert bereinigtes ZIP ohne Logvorschau |
| Über die App | Support-Informationen kopieren | Aktion | kopieren | n/a | Zwischenablage | kopiert sichtbare nicht-private Supportdaten |
| Über die App | Lizenzhinweise | Detailpanel | anzeigen | n/a | App-Ressourcen | zeigt Lizenzhinweise |
| Über die App | Datenschutzinformationen | Detailpanel | anzeigen | n/a | App-Ressourcen oder lokaler Text | zeigt Datenschutzinformationen |
| Über die App | Drittanbieter-Lizenzen | Detailpanel | anzeigen | n/a | App-Ressourcen | zeigt Drittanbieter-Lizenzen |

Nicht sichtbare interne Werte, feste Grenzwerte und Implementierungskonstanten werden nicht als Settings-Zeilen dargestellt.

## Allgemein

Allgemein enthält globale App-Optionen.

Reihenfolge:

1. App beim TV-Start starten
2. Startbereich
3. doppelte Zurück-Taste zum Beenden
4. Sprache
5. Hintergrundaktualisierung erlauben
6. Sortierung merken
7. User-Agent

### App beim TV-Start starten

Optionstyp:

```text
Toggle
```

Wenn aktiv, soll Vivicast beim Start des Android-TV-Geräts automatisch gestartet werden, sofern das Betriebssystem dies erlaubt.

Der Autostart verwendet den unter Startbereich gespeicherten Zielbereich.

### Startbereich

Optionstyp:

```text
Auswahl
```

Unterstuetzte sichtbare Werte:

- Home
- Live-TV
- Filme
- Serien

Standardwert:

```text
Home
```

Der Wert wird global in DataStore als `startDestination` gespeichert. Zulässige technische Werte sind `HOME`, `LIVE_TV`, `MOVIES` und `SERIES`. Ein fehlender oder ungültiger Wert faellt auf `HOME` zurück.

Die Auswahl gilt ab dem nächsten regulaeren App-Start ohne explizites Ziel. Eine Änderung navigiert die aktuell laufende Sitzung nicht sofort um.

Beim Rueckkehren aus dem Hintergrund bleibt der aktuelle App-Kontext erhalten; dies gilt nicht als neuer Start für diese Einstellung.

Deep Links, globale Android-TV-Suchergebnisse, Watch Next und andere explizite Android-TV-Aufrufe öffnen ihr konkretes Ziel und haben Vorrang vor dem Startbereich.

Wenn der gewählte Bereich noch keine Inhalte besitzt, zeigt er seinen normalen Empty State. Es erfolgt kein automatischer Fallback auf Home.

Suche und Einstellungen sind bewusst nicht als Startbereich waehbar.

### Doppelte Zurück-Taste zum Beenden

Optionstyp:

```text
Toggle
```

Wenn aktiv, beendet die App erst nach zweiter Zurück-Bestätigung aus einem Hauptscreen.

Dialoge, Detailseiten und Player-Overlays werden davon nicht überschrieben.

### Sprache

Optionstyp:

```text
Auswahl
```

Unterstuetzte Werte:

- Systemstandard
- Deutsch
- Englisch

Standardwert:

```text
Systemstandard
```

Sprache ist eine globale App-Einstellung und gehört nicht in Optik.

### Hintergrundaktualisierung erlauben

Optionstyp:

```text
Toggle
```

Diese Einstellung ist ein globaler Hauptschalter für automatische Hintergrundaktualisierungen.

Konkrete Aktualisierungsintervalle und Ausloeser werden in Wiedergabelisten und EPG konfiguriert.

Wenn diese Option deaktiviert ist, dürfen manuelle Aktualisierungen weiterhin verfuegbar bleiben.

### Sortierung merken

Optionstyp:

```text
Toggle
```

Wenn aktiv, merkt die App zuletzt genutzte Sortierungen und Darstellungsfilter pro relevantem Bereich.

Diese Option darf keine fachlichen Daten verändern, sondern nur Anzeige- und Bedienpräferenzen speichern.

### User-Agent

Optionstyp:

```text
Textfeld
```

User-Agent ist die letzte Option in Allgemein.

User-Agent ist eine globale Einstellung.

Sie gilt appweit für HTTP-Anfragen, sofern technisch anwendbar, zum Beispiel Playlist-, EPG-, Logo- und Stream-Anfragen.

Ein leerer Wert bedeutet: App-Standard verwenden.

Fuehrende und abschließende Leerzeichen werden beim Speichern entfernt.

User-Agent ist keine individuelle Einstellung pro Wiedergabeliste, Provider, EPG-Quelle oder Stream.

Wiedergabelisten-Add- und Edit-Formulare dürfen keine eigene User-Agent-Option anbieten.

EPG-Quellen-Add- und Edit-Formulare dürfen keine eigene User-Agent-Option anbieten.

## Wiedergabelisten

Wiedergabelisten müssen verwaltet werden können.

Unterstuetzte Aktionen:

- hinzufügen
- bearbeiten
- aktualisieren
- alle aktualisieren
- löschen
- aktivieren
- deaktivieren

### Wiedergabeliste hinzufügen

Der Add Flow ist schrittweise:

1. Name eingeben
2. Quelltyp wählen: M3U oder Xtream Codes
3. bei M3U Eingabeart wählen: URL, Datei oder Zwischenablage
4. typabhängige Zugangsdaten oder Quelle eingeben
5. Verbindung testen
6. bei erfolgreichem Test speichern
7. direkt aktualisieren/importieren

Name ist Pflichtfeld.

Der Name muss eindeutig sein.

Wenn der Name bereits existiert, wird ein Hinweis angezeigt und Speichern ist nicht möglich.

Der Quelltyp kann nach dem Speichern nicht geändert werden.

Der fachliche Parser- und Quellenvertrag für M3U, Xtream Codes und XMLTV liegt in `prd/PRD-v1/12-parser-source-contracts.md`.

Der atomare Import- und Refresh-Vertrag liegt in `prd/PRD-v1/07-background-jobs-performance.md`.

### M3U hinzufügen

M3U unterstuetzt diese Eingabearten:

- URL
- Datei
- Zwischenablage

Bei M3U gibt es keine Auswahl für Live-TV, Filme oder Serien.

M3U wird so importiert, wie die App die Playlist erhält.

Filme und Serien in M3U werden wie normale Playlist-Inhalte behandelt.

Bei M3U-Dateiimport wird keine dauerhafte Kopie der M3U-Datei lokal gespeichert.

M3U-Formulare enthalten keine eigene User-Agent-Option.

M3U-Import nutzt toleranten Teilimport. Fehlerhafte Eintraege werden übersprungen, solange verwertbare Eintraege importiert werden können.

### Xtream Codes hinzufügen

Xtream Codes Felder:

- Server
- Benutzername
- Passwort

Server erlaubt:

- http
- https
- Portangabe, zum Beispiel `http://host:8080`

Xtream Codes Importoptionen:

- Live-TV importieren
- Filme importieren
- Serien importieren

Xtream-Codes-Formulare enthalten keine eigene User-Agent-Option.

Xtream-Codes-Import nutzt toleranten Teilimport. Unvollstaendige Eintraege werden importiert, wenn Kern-ID und Name vorhanden sind.

### Verbindungstest

Vor dem Speichern muss die Verbindung geprueft werden.

Wenn der Test fehlschlaegt, wird nicht gespeichert.

Der Nutzer erhält einen konkreten Hinweis, warum der Test fehlgeschlagen ist, soweit ermittelbar.

Beispiele:

- URL ungültig
- Server nicht erreichbar
- Anmeldedaten ungültig
- Datei nicht lesbar
- Zwischenablage enthält keine nutzbare M3U-Quelle

Teilfehler einzelner Eintraege brechen einen Import nicht ab. Nach Abschluss zeigt die App einen zusammenfassenden Teilfehler-Status.

Wenn der direkte Import nach erfolgreichem Speichern fehlschlaegt, bleibt die Wiedergabeliste mit Fehlerstatus bestehen. Halb importierte Inhalte dürfen nicht sichtbar werden.

### Wiedergabeliste bearbeiten

Alle Felder dürfen bearbeitet werden, ausser der Quelltyp.

Ein Wechsel zwischen M3U und Xtream Codes ist nicht erlaubt.

Bearbeitbare Optionen:

- aktivieren oder deaktivieren
- Name
- M3U-Daten oder Xtream-Codes-Zugangsdaten
- EPG-Quellen zuweisen
- EPG-Prioritäten ändern
- Logo-Priorität
- Gruppen verwalten
- Update-Optionen
- löschen

Name bleibt Pflichtfeld und muss eindeutig sein.

Wenn M3U-URL, M3U-Inhalt, Datei-Quelle oder Xtream-Zugangsdaten geändert wurden, muss beim Speichern zuerst erneut ein Verbindungstest erfolgen.

Bei erfolgreichem Test wird gespeichert und danach direkt aktualisiert/importiert.

Wenn der Test fehlschlaegt, wird nicht gespeichert.

### Logo-Priorität pro Wiedergabeliste

Einstellbare Quellen:

- globale Logo-Standardreihenfolge verwenden
- Logos aus Playlist bevorzugen
- Logos aus EPG bevorzugen
- Logos aus lokalem Ordner bevorzugen

### Gruppen verwalten

Unterstuetzt:

- Gruppen anzeigen
- Gruppen ausblenden
- Gruppen sortieren

### Update-Optionen pro Wiedergabeliste

Unterstuetzt:

- Aktualisierungsintervall der Wiedergabeliste
- beim App-Start aktualisieren
- jetzt aktualisieren
- letzte Aktualisierung anzeigen

Wenn Hintergrundaktualisierung in Allgemein deaktiviert ist, werden automatische Aktualisierungen nicht ausgefuehrt.

Manuelle Aktualisierung bleibt möglich.

### Detailinformationen

Anzeigen, falls vorhanden:

- Ablaufdatum
- maximale Verbindungen
- letzte Aktualisierung
- technische Zaehler

## EPG

EPG-Quellen werden global verwaltet und pro Provider zugeordnet.

Unterstuetzt:

- EPG-Quelle hinzufügen
- EPG-Quelle bearbeiten
- EPG-Quelle löschen
- automatische Zuordnung
- manuelle Zuordnung
- Priorisierung pro Provider
- mehrere EPG-Quellen pro Provider
- Zeitversatz pro Quelle
- globales EPG-Aktualisierungsintervall
- EPG-Vergangenheit behalten
- EPG-Zukunft laden/behalten
- EPG beim App-Start aktualisieren
- EPG bei Playlist-Änderung aktualisieren
- EPG-Aktualisierungshistorie anzeigen

### Globales EPG-Aktualisierungsintervall

Der verbindliche Standardwert betraegt 24 Stunden.

Der Wert wird in DataStore als `epgRefreshIntervalHours = 24` gespeichert und gilt für den automatischen intervallgesteuerten EPG-Refresh.

App-Start-Aktualisierung, Aktualisierung bei Playlist-Änderung und manuelle Aktualisierung bleiben davon getrennte Ausloeser.

EPG-Quellen-Formulare enthalten keine eigene User-Agent-Option.

### EPG-Aufbewahrung

Globale DataStore-Schluessel und Defaults:

```text
epgPastRetentionDays = 1
epgFutureRetentionDays = 7
```

Zulässige Werte:

```text
1 bis 14 Tage
```

Die Werte steuern, wie viele vergangene und zukuenftige EPG-Programmdaten lokal behalten werden.

Cleanup entfernt nur EPG-Programmdaten ausserhalb dieses Fensters. EPG-Quellen, EPG-Kanäle, Provider-Zuordnungen und manuelle Mappings bleiben erhalten.

### EPG-Quelle

Eine EPG-Quelle enthält mindestens:

- Name
- Quelle oder URL
- Zeitversatz
- Aktivstatus
- letzte Aktualisierung, falls vorhanden
- Anzahl importierter Programme, falls vorhanden

### EPG-Priorität pro Provider

Ein Provider kann mehrere EPG-Quellen haben.

Prioritäten:

```text
EPG 1 = Priorität 1
EPG 2 = Priorität 2
EPG 3 = Priorität 3
```

Niedrigere Prioritätszahl gewinnt bei konkurrierenden EPG-Daten.

## Optik

Optik enthält visuelle Darstellung und UI-Dichte.

Konfigurierbar:

- Hintergrundthema
- Akzentfarbe
- Transparenz
- Schriftgröße
- Animationen
- globale Logo-Standardreihenfolge
- Logos-Ordner
- EPG-Darstellung

Sprache gehört nicht in Optik, sondern in Allgemein.

Optik darf Grundlayout, D-Pad-Navigation, Mindestkontraste, Fokusindikator, Safe-Area und Mindestgrößen fokussierbarer Elemente nicht verändern.

### Schriftgröße

Unterstuetzte Werte:

- klein
- mittel
- gross
- sehr gross

### Animationen

Unterstuetzte Werte:

- aus
- schnell
- normal
- langsam

### Globale Logo-Standardreihenfolge

Unterstuetzte Werte:

- Logos aus Playlist bevorzugen
- Logos aus EPG bevorzugen
- Logos aus lokalem Ordner bevorzugen

Diese Einstellung ist der globale Standard.

Eine Wiedergabeliste kann diese Reihenfolge über ihre eigene Logo-Priorität überschreiben.

### EPG-Darstellung

Konfigurierbar:

- Kanalnummer anzeigen
- Sendername anzeigen
- zweizeilige Sendernamen erlauben
- Catch-Up-Symbole anzeigen
- laufendes Programm hervorheben
- zweizeilige Programmtitel erlauben
- Fortschritt markieren
- animiertes Scrollen erlauben

## Wiedergabe

Konfigurierbar:

- Puffergröße
- Audio-Decoder
- Video-Decoder
- AFR
- Timeshift
- maximale Timeshift-Dauer
- Timeshift-Speicher
- Audio-Sprache
- Untertitel-Sprache
- Automatisch nächste Folge
- Countdown nächste Folge
- Audio-Passthrough
- externer Player

### Puffergröße

Unterstuetzte Werte:

- aus
- klein
- mittel
- gross
- sehr gross

Änderungen gelten beim nächsten Streamstart.

### Decoder

Audio-Decoder und Video-Decoder unterstuetzen mindestens:

- Hardware
- Software

Standard ist Hardware.

Änderungen gelten beim nächsten Streamstart.

### AFR

AFR ist ein Toggle für automatische Bildwiederholraten-Anpassung, sofern Gerät und Android-Version dies unterstuetzen.

Wenn nicht unterstuetzt, wird die Option deaktiviert mit Hinweis angezeigt.

### Timeshift

Optionstyp:

```text
Toggle
```

Unterstuetzte Werte:

- Ein
- Aus

Standardwert:

```text
Ein
```

Timeshift bleibt abhängig von Provider, Sender und Stream.

Wenn Timeshift deaktiviert oder nicht verfuegbar ist, darf Live-TV-Seek nicht möglich sein und muss einen kurzen Hinweis anzeigen.

### Maximale Timeshift-Dauer

Optionstyp:

```text
Auswahl
```

Unterstuetzte Werte:

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

Optionstyp:

```text
Auswahl
```

Unterstuetzte Werte:

- Automatisch
- RAM
- Festplatte

Standardwert:

```text
Automatisch
```

`Automatisch` laesst Vivicast zwischen RAM und persistentem Gerätespeicher wählen.

`Festplatte` bezeichnet appverwalteten persistenten Gerätespeicher. Eine freie Ordner- oder Pfadauswahl ist in v1 nicht vorgesehen.

Maximale Dauer und Speicher bleiben bei deaktiviertem Timeshift sichtbar, sind dann aber nicht aktiv bedienbar.

Änderungen an Timeshift, maximaler Dauer oder Speicher werden beim nächsten Aufbau eines Timeshift-Puffers wirksam.

### Audio-Sprache

Unterstuetzte Werte mindestens:

- Systemstandard
- Deutsch
- Englisch
- Original

### Untertitel-Sprache

Unterstuetzte Werte mindestens:

- Aus
- Systemstandard
- Deutsch
- Englisch

### Automatisch nächste Folge

Optionstyp:

```text
Toggle
```

Unterstuetzte Werte:

- Ein
- Aus

Standardwert:

```text
Aus
```

Die Option gilt für Serienepisoden im internen Vivicast-Player.

Bei `Aus` erscheint die manuelle Aktion `Nächste Folge abspielen` erst nach dem tatsaechlichen Episodenende. Bei `Ein` erscheint der dynamische Hauptbutton `Nächste Folge in X` um den konfigurierten Zeitraum vor dem Episodenende und startet die Folge beim Ablauf automatisch.

### Countdown nächste Folge

Optionstyp:

```text
Auswahl
```

Unterstuetzte Werte:

- 5 Sekunden
- 10 Sekunden
- 15 Sekunden
- 30 Sekunden

Standardwert:

```text
10 Sekunden
```

Die Auswahl bleibt bei deaktiviertem Auto-Next sichtbar, ist dann aber nicht bedienbar.

Der Wert bestimmt, wie viele Sekunden vor dem tatsaechlichen Episodenende das Auto-Next-Panel eingeblendet wird. Die vollstaendige Ablaufdefinition steht in `prd/PRD-v1/03-movies-series-requirements.md`.

### Keine Abschluss-Schwellen-Einstellung

Die Abschluss-Schwelle für Filme und Episoden ist in v1 fest auf 95 Prozent gesetzt.

Sie erscheint nicht unter `Wiedergabe`, besitzt keinen DataStore-Schluessel und ist nicht benutzerkonfigurierbar.

### Audio-Passthrough

Audio-Passthrough ist ein Toggle für kompatible Ausgabegeräte.

Wenn nicht unterstuetzt, wird die Option deaktiviert mit Hinweis angezeigt.

### Externer Player

Einstellung:

- immer intern
- immer extern
- jedes Mal fragen

Standard ist immer intern.

Die Einstellung gilt nur für Filme und einzelne Serienepisoden. Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.

Der externe Player ist nur eine Wiedergabeübergabe. Vivicast übernimmt in v1 keinen automatischen Fortschritt, keine Position, keine Dauer und keinen Abschlussstatus aus externen Playern.

Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte. Vorhandener Fortschritt bleibt unverändert; ohne vorherigen Fortschritt wird kein neuer Fortschrittsdatensatz erzeugt.

Auto-Next gilt nicht für externe Player.

Es existiert immer nur eine aktive Wiedergabe.

## Kindersicherung

Kindersicherung enthält PIN-Optionen und Schutzbereiche.

### Grundregeln

Kindersicherung ist deaktiviert, solange keine PIN gesetzt und kein Schutzbereich aktiv ist.

Eine PIN muss gesetzt werden, bevor Schutzbereiche aktiviert werden können.

PIN-Eingaben dürfen nicht im Klartext angezeigt werden.

PIN-Werte dürfen nicht im Klartext gespeichert werden.

Gespeichert wird nur ein langsamer gesalzener PIN-Pruefwert in geschuetzter Speicherung.

Die App muss falsche PIN-Eingaben nach der verbindlichen Sperrlogik begrenzen, damit Brute-Force-Versuche erschwert werden.

### PIN

Unterstuetzt:

- PIN setzen
- PIN ändern
- PIN deaktivieren

PIN setzen:

1. neue PIN eingeben
2. neue PIN wiederholen
3. bei Übereinstimmung speichern

PIN ändern:

1. aktuelle PIN eingeben
2. neue PIN eingeben
3. neue PIN wiederholen
4. bei korrekter aktueller PIN und Übereinstimmung speichern

PIN deaktivieren:

1. aktuelle PIN eingeben
2. Deaktivierung bestätigen
3. alle Schutzbereiche deaktivieren

PIN-Laenge:

```text
4 Stellen
```

PIN besteht aus Ziffern.

PIN-Eingabefelder verwenden die Android-/TV-Systemtastatur als numerische Passwort-Eingabe.

Vivicast baut in v1 keine eigene Zifferntastatur.

Die Eingabe bleibt verdeckt, bietet keine Zwischenablage-Aktion, keine Autovervollstaendigung und keine Klartextanzeige.

Nach Eingabe der vierten Ziffer wird nicht automatisch gespeichert oder entsperrt. Der Nutzer bestätigt bewusst über die sichtbare Aktion, zum Beispiel `Speichern`, `Entsperren` oder `Deaktivieren`.

Bei temporaerer PIN-Sperre wird keine Tastatur geoeffnet. Der Dialog zeigt Restzeit und `Abbrechen`.

### Schutzbereiche

Unterstuetzte Schutzbereiche:

- Einstellungen schützen
- Filme schützen
- Serien schützen
- Inhalte ab 18 schützen

Optional kann VOD-Schutz als gemeinsame UI-Gruppe Filme und Serien zusammenfassen, fachlich bleiben Filme und Serien getrennte Schutzbereiche.

### PIN-Abfragepunkte

PIN wird abgefragt, wenn ein geschuetzter Bereich geoeffnet oder eine geschuetzte Aktion ausgefuehrt wird.

Beispiele:

- Öffnen der Einstellungen, wenn Einstellungsschutz aktiv ist
- Öffnen von Filme, wenn Film-Schutz aktiv ist
- Öffnen von Serien, wenn Serien-Schutz aktiv ist
- Starten oder Öffnen von als FSK 18 markierten Inhalten, wenn FSK-18-Schutz aktiv ist
- Löschen, Restore oder sicherheitsrelevante Settings-Aktionen, wenn Einstellungsschutz aktiv ist

### Freigabe-Sitzung

Nach erfolgreicher PIN-Eingabe bleibt der jeweilige Schutzbereich für die aktuelle App-Sitzung freigegeben.

Die Freigabe endet:

- beim App-Neustart
- wenn die App vom System beendet wurde
- wenn der Nutzer die Freigabe manuell sperrt, sofern eine Sperraktion angeboten wird

Die Freigabe gilt nur für den freigegebenen Schutzbereich.

Aktive Session-Freigaben sind nur im Speicher, nicht persistent und kein Backup- oder Restore-Ziel.

### Falsche PIN

Bei falscher PIN wird ein Fehler direkt im Dialog angezeigt.

Nach fuenf falschen PIN-Eingaben wird die nächste Eingabe temporaer blockiert.

Sperrdauern:

```text
30 Sekunden
60 Sekunden
5 Minuten
```

Nach weiteren fuenf falschen Eingaben während derselben Sperrserie bleibt die Sperrdauer bei 5 Minuten.

Die Sperre wird in einem lokalen Sicherheitszustand mit Fehlversuchszaehler, Sperrstufe und `lockedUntil` gespeichert. Ein App-Neustart hebt eine laufende Sperre nicht auf.

Nach erfolgreicher PIN-Eingabe werden Fehlversuchszaehler und Sperrstufe für den lokalen PIN-Kontext zurückgesetzt.

### Jugendschutz-Metadaten

FSK-18-Schutz greift nur, wenn Inhalte entsprechende Altersfreigabe- oder Erwachsenen-Metadaten besitzen oder eindeutig als 18+ erkannt werden.

Fehlen Altersdaten, darf die App den Inhalt nicht automatisch als 18+ behandeln, sofern keine andere eindeutige Kennzeichnung existiert.

## Backup

Unterstuetzte Ziele:

- lokaler Speicher
- SMB
- Google Drive

Backups werden manuell gestartet.

Import und Restore müssen bestätigt werden, bevor bestehende lokale Daten überschrieben werden.

Restore aus Backup ist in v1 immer ein Ersetzen des Backup-Umfangs. `Zusammenfuehren`, Restore-Konfliktdialoge und `Als Kopie importieren` sind nicht Teil von v1.

Vor dem Ersetzen prueft die App Backup-Datei, Passphrase, Schema-Migration und Inhalt. Danach versucht sie ein internes Sicherheitsbackup der aktuellen lokalen Daten zu erstellen. Wenn dieses Sicherheitsbackup fehlschlaegt, fragt die App den Nutzer, ob Restore trotzdem fortgesetzt oder abgebrochen werden soll.

Backups aus aelteren kompatiblen App-Versionen dürfen vor Restore in das aktuelle Backup-Schema migriert werden. Diese Schema-Migration ist kein Zusammenfuehren lokaler und importierter Daten.

Lokale Provider, EPG-Quellen, EPG-Zuordnungen, Favoriten, Verlaeufe, Wiedergabefortschritte und Einstellungen, die nicht im Backup enthalten sind, werden beim Restore entfernt.

Standard-Backups enthalten keine geheimen Zugangswerte.

Cache- und Verlaufsaktionen gehoeren nicht zur Backup-Gruppe, sondern zum eigenen Bereich `Speicher & Verlauf`.

Verschlüsselte Vollbackups dürfen geheime Quellen- und Zielzugangsdaten nur mit aktiv gesetzter Backup-Passphrase enthalten. Passphrase, Kryptoformat und Abbruchverhalten folgen `prd/PRD-v1/10-backup-import-requirements.md` und `architecture/decisions/ADR-014-security-data-network-backup.md`.

Wenn aktuell lokal Einstellungsschutz aktiv ist oder die lokale Schutzkonfiguration eine PIN für Backup/Restore verlangt, muss vor dem Einspielen eines Backups die aktuell lokale PIN bestätigt werden.

Nach dem Restore ist Kindersicherung deaktiviert. PIN-Pruefwerte, aktive PIN-Freigaben und Kindersicherung-Schutzflags aus der Backup-Datei werden nicht übernommen.

Wenn die Backup-Datei ausweist, dass Kindersicherung beim Export aktiv war, zeigt die App nach dem Restore einen Hinweis, dass die PIN-Funktion vor dem Backup aktiv war, nach dem Restore deaktiviert wurde und in den Kindersicherungs-Einstellungen manuell wieder aktiviert werden muss.

## Speicher & Verlauf

`Speicher & Verlauf` ist ein eigener Einstellungsbereich für lokale Wartungsaktionen.

Dieser Bereich enthält Medien-Cache-Informationen, Cache-Leerung und Verlauf-Loeschaktionen. Er gehört fachlich nicht zum Backup-/Restore-Bereich.

### Cache-Informationen

Die App zeigt mindestens die aktuelle Größe des Medien-Caches an.

Optional können Dateianzahl und interne Cache-Grenze angezeigt werden, sofern diese Werte technisch verfuegbar sind.

Die Größe oder Rotation des Medien-Caches ist in v1 nicht frei konfigurierbar.

### Cache leeren

`Cache leeren` loescht heruntergeladene Medien-Cache-Dateien wie Senderlogos, Film-Poster, Serien-Poster, Staffelbilder und Episodenbilder.

Nicht geloescht werden Providerdaten, Favoriten, Verlauf, Wiedergabefortschritt, Suchverlauf, EPG-Zuordnungen, Zugangsdaten, EPG-Programmdaten sowie aktive Stream- oder Timeshift-Puffer.

Medien-Cache-Dateien sind nicht Teil des Standard-Backups und werden nach Bedarf neu aufgebaut.

### Verlauf löschen

Unterstuetzte Aktionen:

- Live-TV-Verlauf löschen
- Filmverlauf und Film-Wiedergabefortschritt löschen
- Serienverlauf und Episoden-Wiedergabefortschritt löschen
- Suchverlauf löschen
- gesamten Verlauf löschen

Verlaufslimits sind in v1 nicht frei konfigurierbar. Der Suchverlauf bleibt fest auf maximal 20 Eintraege begrenzt.

Cache- und Verlaufsloeschungen brauchen eine klare Bestätigung. Wenn Einstellungsschutz aktiv ist, muss vorher die PIN bestätigt werden.

## Über die App

Der vollstaendige fachliche Vertrag liegt in `prd/PRD-v1/11-about-app-requirements.md`.

Der Bereich enthält insbesondere:

- App-, Build- und Datenbankinformationen
- rechtliche Hinweise
- `Diagnose und Support`
- `Diagnoseprotokollierung` als Toggle; Standard `Aus`
- `Aufbewahrungsdauer` von 1 bis 7 Tagen; Standard 1 Tag
- die Aktion `Diagnoseprotokoll exportieren`

Der Diagnoseexport ist export-only. Logdatei-Inhalte werden niemals direkt in der App angezeigt oder kopiert.

Die Aufbewahrungsdauer bleibt bei ausgeschalteter Diagnoseprotokollierung sichtbar, ist dann aber nicht änderbar. Bereits vorhandene Sitzungen bleiben bis zum Ablauf der eingestellten Dauer exportierbar.

Das ZIP enthält verpflichtend `vivicast-diagnostics.log` und `diagnostics-metadata.json`. Inhalt, Sitzungszeitraum, Aufbewahrung, Größenbegrenzung, Rotation und Ausschluesse folgen `prd/PRD-v1/11-about-app-requirements.md`.

Größenlimit und Rotation sind keine sichtbaren v1-Einstellungen. Intern gelten 20 MiB Gesamtlimit, 2 MiB pro Segment und höchstens drei Segmente beziehungsweise 6 MiB Logdaten pro Sitzung.

---

# 4.6 Player

## Ziel

Der Player spielt Live-TV, Filme, Serienepisoden und Catch-Up-Inhalte performant ab.

Verbindliche Architekturquelle für PlaybackRequest, Catch-Up-URL-Erzeugung, Progress-Speicherung, Track-Auswahl und Timeshift-Grenzfaelle:

- `architecture/decisions/ADR-013-player-playback-progress.md`

## Unterstuetzte Medienarten und Nicht-Ziele

Interne Wiedergabe in v1:

- Live-TV
- Filme
- Serienepisoden
- Catch-Up aus EPG-Kontext

Die externe Player-Übergabe gilt in v1 nur für Filme und Serienepisoden. Live-TV und Catch-Up bleiben im internen Vivicast-Player, weil Senderwechsel, Timeshift, EPG-Kontext, Reconnect und kontrollierte Rueckkehr nur dort verlaesslich gesteuert werden.

Nicht-Ziele für v1:

- Aufnahmen oder DVR
- Offline-Downloads
- Cast oder Second-Screen-Wiedergabe
- Picture-in-Picture
- Hintergrundwiedergabe
- DRM-Lizenzflows
- UDP, RTSP, RTMP, Acestream, MAG oder Stalker

## Vollbildmodus

Standardmaessig ist keine UI sichtbar.

Bedienelemente erscheinen nur bei Interaktion oder relevantem Zustand.

## Overlay

Grundregeln:

- OK oeffnet Overlay, wenn es geschlossen ist
- Zurück schliesst Overlay, wenn es sichtbar ist
- wenn kein Overlay sichtbar ist, fuehrt Zurück zur vorherigen Ansicht

Konkrete Timeline-Bedienung ist in `design/interaction/02-player-timeline-controls.md` definiert.

## PlaybackRequest und Streamaufloesung

Vor jedem internen oder externen Wiedergabestart erzeugt Vivicast einen unveraenderlichen `PlaybackRequest`.

Der Request enthält mindestens:

- Medientyp
- Provider-ID und `providerStableKey`
- lokale Medien-ID, sofern vorhanden
- `mediaStableKey` beziehungsweise `channelStableKey`
- Herkunft und Rueckkehrziel
- Startposition für Filme und Episoden
- EPG-Programmkontext für Catch-Up
- Timeshift-Kontext für Live-TV

Stream-URLs werden erst unmittelbar vor dem Start aus der jeweiligen Quelle erzeugt oder aufgeloest. Sie werden nicht dauerhaft als Klartext gespeichert und dürfen nicht in Logs, Diagnoseexporten, Fehlermeldungen oder Backups erscheinen.

Der globale User-Agent ist eine App-Einstellung und wird zentral durch Netzwerk- beziehungsweise Streamaufloesung angewendet. Der `PlaybackRequest` fuehrt keine provider-spezifischen Header-, Cookie- oder User-Agent-Referenzen.

V1 startet nur `http`- und `https`-Wiedergaben in Media3-/ExoPlayer-kompatiblen Formaten. Nicht unterstuetzte Protokolle oder Formate erzeugen einen sichtbaren Fehlerzustand.

HTTP-Weiterleitungen dürfen während des Wiedergabestarts befolgt werden, solange die Ziel-URL weiterhin ein erlaubtes `http`- oder `https`-Schema verwendet und die zentrale Netzwerk-/Sicherheitsrichtlinie dies erlaubt. Redirect-Schleifen, nicht erlaubte Zielschema oder blockierte Cross-Protocol-Weiterleitungen fuehren zu einem sichtbaren Playerfehler.

Eine durch Weiterleitung erreichte finale Stream-URL ist ebenfalls nur Laufzeitdatum und wird nicht dauerhaft gespeichert oder geloggt.

Es existiert immer nur eine aktive Wiedergabe. Ein neuer Start beendet die vorherige Wiedergabe und bricht vorherige Startvorgaenge ab. Refreshes dürfen aktive Streams nicht unterbrechen.

## Ruecksprung

Nach Verlassen des Players:

- Live-TV kehrt zum Live-TV-Browser zurück
- Filme kehren zur Film-Detailseite zurück
- Serien kehren zur Serien-Detailseite oder Episodenliste zurück

## Auto-Next bei Serienepisoden

Der interne Player unterstuetzt zwei Zustaende, sofern eine nächste Episode vorhanden ist:

- Auto-Next `Aus`: `Nächste Folge abspielen` erscheint erst nach dem tatsaechlichen Episodenende und startet nur nach OK.
- Auto-Next `Ein`: `Nächste Folge in X` erscheint X Sekunden vor dem tatsaechlichen Episodenende; OK startet sofort, ohne Eingabe startet die nächste Episode beim Ablauf.

Der sichtbare Button `Zurück` erscheint in beiden Zustaenden zeitgleich neben dem Hauptbutton; einen Button `Abbrechen` gibt es nicht. OK auf `Zurück` oder die Zurück-Taste verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit dem zuvor gewählten Staffel-/Episodenkontext zurück.

Nach der letzten Episode einer Serie erscheint kein Auto-Next-Panel.

Die feste 95-Prozent-Abschluss-Schwelle darf weder das Auto-Next-Panel einblenden noch den Wechsel zur nächsten Episode auslösen. Für Countdown und automatischen Start ist ausschliesslich das tatsaechliche Medienende massgeblich.

Die fachlich vollstaendige Reihenfolge- und Countdown-Logik ist in `prd/PRD-v1/03-movies-series-requirements.md` definiert.

## VOD-Progress-Speicherung

Automatische `PlaybackProgressEntity`-Aktualisierungen werden ausschliesslich vom internen Vivicast-Player für Filme und Serienepisoden geschrieben.

Live-TV und Catch-Up erzeugen keinen `PlaybackProgressEntity`-Datensatz. Live-TV verwendet `ChannelHistoryEntity`; Catch-Up bleibt ein EPG-kontextbezogener Wiedergabestart ohne Resume-Ziel.

Ein neuer automatischer Fortschrittsdatensatz wird erst angelegt, wenn die Wiedergabe eine sinnvolle Position erreicht hat:

- mindestens 10 Sekunden Position oder
- mindestens 1 Prozent Fortschritt bei bekannter positiver Dauer

Nach der ersten Anlage speichert Vivicast automatisch:

- mindestens alle 10 Sekunden während aktiver interner Film- oder Episodenwiedergabe
- bei Pause
- nach abgeschlossenem Seek
- beim Verlassen des Players
- beim Wechsel der App in den Hintergrund, soweit technisch möglich
- beim tatsaechlichen Medienende

Position, Dauer und Fortschritt werden auf gültige Werte begrenzt. Die bestehende 95-Prozent-Abschlussregel und das tatsaechliche Medienende bleiben unverändert.

## Externer Player

Einstellung:

- immer intern
- immer extern
- jedes Mal fragen

Es existiert immer nur eine aktive Wiedergabe.

Die Einstellung betrifft ausschliesslich Film- und Episodenstarts. Live-TV und Catch-Up werden nie an externe Player übergeben.

Der interne Player ist die einzige Quelle für automatische VOD-Fortschritte, Abschlussstatus, tatsaechliche Medienende-Erkennung und Auto-Next.

Bei externer Wiedergabe werden Rueckgabewerte externer Player nicht als verlaesslicher Fortschritt übernommen. Vivicast erzeugt oder aktualisiert dadurch keinen `PlaybackProgressEntity`-Datensatz und setzt keinen Abschlussstatus.

Nach Rueckkehr aus externer Film- oder Episodenwiedergabe kehrt Vivicast zum passenden Film- oder Serienkontext zurück und zeigt einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.

## Audio und Untertitel

Die globalen Wiedergabe-Einstellungen für Audio-Sprache und Untertitel-Sprache werden beim Streamstart auf die verfuegbaren Media3-Tracks angewendet.

Audio-Auswahl:

- `Systemstandard` bevorzugt die Systemsprache, sofern eine passende Spur vorhanden ist.
- `Deutsch` und `Englisch` bevorzugen die jeweilige Sprachspur.
- `Original` verwendet die Stream- beziehungsweise Provider-Vorgabe.
- Wenn keine passende Spur vorhanden ist, nutzt Vivicast die erste verfuegbare Audiospur oder die vom Stream gesetzte Standardsprache.

Untertitel-Auswahl:

- `Aus` deaktiviert Textspuren standardmaessig.
- `Systemstandard`, `Deutsch` und `Englisch` bevorzugen eine passende Untertitelspur.
- Wenn keine passende Untertitelspur vorhanden ist, bleiben Untertitel aus oder die Stream-Vorgabe bleibt erhalten, sofern der Player sie technisch erzwingt.

Manuelle Audio- oder Untertitelwahl im Player gilt nur für die aktuelle Wiedergabe und ändert keine globalen Einstellungen.

## Fehlerbehandlung

Senderstart:

- 5 Retries
- danach Fehlerzustand anzeigen

Streamabbruch:

- 5 Reconnect-Versuche
- danach Fehlerzustand anzeigen

Fehleraktionen:

- erneut versuchen
- anderen Sender wählen
- schließen

## Channel Zapping

Bei mehreren schnell gewählten Sendern wird nur die letzte Auswahl gestartet.

Vorherige Startvorgaenge werden abgebrochen.

## Catch-Up

Catch-Up ist nur verfuegbar, wenn alle Voraussetzungen erfuellt sind:

- Sender und Provider weisen Catch-Up-Unterstuetzung aus.
- Der EPG-Programmpunkt besitzt verwertbare Start- und Endzeit.
- Der Programmpunkt liegt im erlaubten Rueckblickfenster des Providers und der lokalen EPG-Aufbewahrung.
- Die für die Quelle erforderlichen Zugangsdaten oder nicht geheimen URL-Templates sind verfuegbar.

Aktuelle Live-Sendungen werden über Live-TV beziehungsweise Timeshift behandelt und nicht als Catch-Up gestartet.

M3U-Catch-Up nutzt `catchup`, `catchup-days` und `catchup-source`, sofern plausibel. V1 unterstuetzt die Modi `default` und `append` mit Platzhalterersetzung aus dem EPG-Kontext.

Xtream-Catch-Up nutzt die importierten Archiv-/Catch-Up-Metadaten, Stream-ID und Zugangsdaten. Die finale Catch-Up-URL wird just-in-time erzeugt und nicht dauerhaft gespeichert.

Catch-Up wird im Player wie begrenztes VOD mit EPG-Kontext dargestellt, erzeugt aber keinen VOD-Fortschritt und kein Resume-Ziel.

## Timeshift

Timeshift ist optional und abhängig von Provider, Sender, Stream und Wiedergabe-Einstellung.

Der aktive Timeshift-Puffer darf die konfigurierte maximale Dauer nicht überschreiten.

Der Puffer verwendet den konfigurierten Speicher `Automatisch`, `RAM` oder `Festplatte`.

Bei Senderwechsel wird der Timeshift-Puffer verworfen.

Es existiert höchstens ein aktiver Timeshift-Puffer.

Timeshift gilt nur für Live-TV. Der Puffer startet mit der aktiven Live-Wiedergabe und wird bei Senderwechsel, Player-Ende, App-Stopp oder Start eines anderen Medientyps verworfen.

Wenn der konfigurierte Timeshift-Speicher wegen Geräteressourcen, Speicherfehlern oder Stream-Eigenschaften nicht nutzbar ist, laeuft Live-TV ohne Timeshift weiter. Vivicast zeigt dann einen Hinweis und sperrt Seek für diese Wiedergabesitzung.

`Cache leeren` darf aktive Stream- oder Timeshift-Puffer nicht entfernen.

---

# Abgrenzung

Diese Datei definiert fachliche Anforderungen für Suche, Einstellungen und Player.

Nicht hier definiert:

- Suchscreen-Layout
- Settings-Screen-Struktur
- Player-Overlay-Layout
- Timeline-Fokuspfade
- konkrete UI-Komponenten

Diese Details liegen in den Designquellen.
