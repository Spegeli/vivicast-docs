# ADR-013 - Player Playback and Progress Contract

## Status

Accepted

## Kontext

Vivicast spielt Live-TV, Filme, Serienepisoden und Catch-Up aus M3U- und Xtream-Quellen ab.

Der Player muss grosse IPTV-Quellen, kurzlebige Stream-URLs, EPG-Kontext, Timeshift, externe Player-Uebergaben und VOD-Fortschritt sauber trennen.

Ohne einen verbindlichen Playback-Vertrag koennen Catch-Up-URLs, externe Player-Rueckgaben, Progress-Speicherung und Timeshift-Zustaende widerspruechlich umgesetzt werden.

## Entscheidung

### Unterstuetzte Wiedergabe

Interner Vivicast-Player:

- Live-TV
- Filme
- Serienepisoden
- Catch-Up aus EPG-Kontext

Externe Player-Uebergabe:

- Filme
- Serienepisoden

Live-TV und Catch-Up bleiben in v1 interne Vivicast-Player-Kontexte.

Nicht-Ziele fuer v1:

- Aufnahmen oder DVR
- Offline-Downloads
- Cast oder Second-Screen-Wiedergabe
- Picture-in-Picture
- Hintergrundwiedergabe
- DRM-Lizenzflows
- UDP, RTSP, RTMP, Acestream, MAG oder Stalker

### PlaybackRequest

Vor jedem internen oder externen Start wird ein unveraenderlicher `PlaybackRequest` erzeugt.

Der Request enthaelt mindestens:

- Medientyp
- Provider-ID und `providerStableKey`
- lokale Medien-ID, sofern vorhanden
- `mediaStableKey` oder `channelStableKey`
- Herkunft und Rueckkehrziel
- Startposition fuer Filme und Episoden
- EPG-Programmkontext fuer Catch-Up
- Timeshift-Kontext fuer Live-TV

Der PlaybackRequest ist Laufzeit- oder Use-Case-Datenmodell und kein Backup-Objekt.

Der globale User-Agent ist eine App-Einstellung und wird zentral durch Netzwerk- beziehungsweise Streamaufloesung angewendet. Der PlaybackRequest enthaelt keine provider-spezifischen Header-, Cookie- oder User-Agent-Konzepte.

### Streamaufloesung

Stream-URLs werden just-in-time erzeugt oder aufgeloest.

Sie werden nicht dauerhaft als Klartext gespeichert und duerfen nicht in Logs, Diagnoseexporten, sichtbaren Fehlermeldungen oder Backups erscheinen.

V1 akzeptiert nur `http`- und `https`-Wiedergaben in Media3-/ExoPlayer-kompatiblen Formaten.

HTTP-Weiterleitungen duerfen waehrend des Starts befolgt werden, solange die finale Ziel-URL weiterhin `http` oder `https` verwendet und die zentrale Netzwerk-/Sicherheitsrichtlinie dies erlaubt.

Redirect-Schleifen, nicht erlaubte Zielschema oder blockierte Cross-Protocol-Weiterleitungen fuehren zu einem sichtbaren Playerfehler.

Eine durch Weiterleitung erreichte finale URL bleibt Laufzeitdatum und wird nicht persistiert oder geloggt.

Es existiert immer nur eine aktive Wiedergabe. Ein neuer Start beendet die vorherige Wiedergabe und bricht ausstehende Startvorgaenge ab.

Refreshes duerfen aktive Streams nicht unterbrechen.

### Catch-Up

Catch-Up ist nur verfuegbar, wenn:

- Sender und Provider Catch-Up unterstuetzen
- ein EPG-Programm mit verwertbarer Start- und Endzeit vorhanden ist
- das Programm im erlaubten Rueckblickfenster des Providers und der lokalen EPG-Aufbewahrung liegt
- die fuer die Quelle erforderlichen Zugangsdaten oder Templates verfuegbar sind

Aktuelle Live-Sendungen werden ueber Live-TV beziehungsweise Timeshift behandelt und nicht als Catch-Up gestartet.

M3U-Catch-Up nutzt `catchup`, `catchup-days` und `catchup-source`, sofern plausibel. V1 unterstuetzt die Modi `default` und `append`; Template-Platzhalter werden beim Start aus EPG-Start, EPG-Ende und Dauer ersetzt.

Xtream-Catch-Up wird aus Archiv-/Catch-Up-Metadaten, Stream-ID, EPG-Zeitfenster und geschuetzten Zugangsdaten abgeleitet.

Die finale Catch-Up-URL wird nicht persistiert und nicht geloggt.

Catch-Up wird im Player wie begrenztes VOD mit EPG-Kontext dargestellt, erzeugt aber keinen `PlaybackProgressEntity`-Datensatz und kein Resume-Ziel.

### Progress

Automatischer `PlaybackProgressEntity` wird nur fuer interne Filme und interne Serienepisoden geschrieben.

Keinen Playback-Progress erzeugen:

- Live-TV
- Catch-Up
- externe Player
- Trailer
- komplette Staffeln
- komplette Serien

Ein neuer automatischer Datensatz entsteht erst ab:

- mindestens 10 Sekunden Position oder
- mindestens 1 Prozent Fortschritt bei bekannter positiver Dauer

Nach der ersten Anlage schreibt der interne Player:

- mindestens alle 10 Sekunden waehrend aktiver Wiedergabe
- bei Pause
- nach abgeschlossenem Seek
- beim Verlassen des Players
- beim Wechsel der App in den Hintergrund, soweit technisch moeglich
- beim tatsaechlichen Medienende

Position, Dauer und Prozentwert werden auf gueltige Werte begrenzt.

Die feste 95-Prozent-Abschlussregel und das tatsaechliche Medienende bleiben die einzigen automatischen Abschlussausloeser. Das Erreichen der Schwelle beendet die Wiedergabe nicht und loest kein Auto-Next aus.

### Externe Player

Externe Player erzeugen oder aktualisieren keinen `PlaybackProgressEntity`-Datensatz.

Rueckgabewerte externer Player werden nicht als Position, Dauer, Fortschritt, Medienende oder Abschlussstatus uebernommen.

Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast einen nicht blockierenden Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.

### Audio und Untertitel

Die globalen Einstellungen fuer Audio-Sprache und Untertitel-Sprache werden beim Streamstart als Track-Praeferenz angewendet.

Manuelle Track-Auswahl im Player gilt nur fuer die aktuelle Wiedergabe und aendert keine globale Einstellung.

Wenn eine bevorzugte Audio- oder Untertitelspur fehlt, nutzt Vivicast die Stream-Vorgabe beziehungsweise die erste verfuegbare Audiospur; Untertitel bleiben bei fehlender passender Spur aus, sofern der Stream sie nicht technisch erzwingt.

### Timeshift

Timeshift gilt nur fuer Live-TV.

Der Puffer startet mit der aktiven Live-Wiedergabe und wird bei Senderwechsel, Player-Ende, App-Stopp oder Start eines anderen Medientyps verworfen.

Die konfigurierte maximale Dauer darf nie ueberschritten werden.

Wenn Speicher oder Stream-Eigenschaften Timeshift verhindern, laeuft Live-TV ohne Timeshift weiter. Seek ist dann gesperrt und Vivicast zeigt einen Hinweis fuer diese Sitzung.

`Cache leeren` darf aktive Stream- oder Timeshift-Puffer nicht entfernen.

### Hintergrund und App-Stopp

Beim Wechsel in den Hintergrund stoppt Vivicast interne Wiedergabe, weil Hintergrundwiedergabe kein v1-Ziel ist.

VOD-Fortschritt wird vorher gespeichert, soweit technisch möglich.

Live-TV schreibt keinen `PlaybackProgressEntity`, behaelt aber den Rueckkehrkontext.

Timeshift-Puffer wird bei App-Stopp, Prozessende oder Senderwechsel verworfen.

Bei Rueckkehr zeigt Vivicast den letzten Player-Kontext kontrolliert ohne automatische Hintergrundwiedergabe.

## Konsequenzen

- Player-Startlogik muss ueber einen expliziten PlaybackRequest laufen.
- Catch-Up-URLs sind kurzlebige Laufzeitdaten und kein persistierter Klartext.
- VOD-Progress ist auf interne Filme und Episoden begrenzt.
- Live-TV-Verlauf, Catch-Up-Kontext und PlaybackProgress bleiben unterschiedliche Datenmodelle.
- Track-Auswahl muss globale Defaults und sitzungsbezogene manuelle Auswahl trennen.
- Timeshift-Fehler duerfen Live-TV nicht abbrechen, solange Live-Wiedergabe selbst moeglich ist.
