# 04 – Focus and Navigation

Status: verbindliche Design-Referenz v6

Dieses Dokument definiert Fokus- und Navigationsregeln für Vivicast auf Android TV.

Fokusnavigation ist kein Detail. Sie ist die primäre Bedienform der App.

---

# Eingabemodell

Vivicast muss vollständig bedienbar sein mit:

```text
D-Pad hoch
D-Pad runter
D-Pad links
D-Pad rechts
OK
Zurück
CH+
CH-
```

Optional:

```text
Mikrofon
Play/Pause
Media Seek
```

Nicht voraussetzen:

```text
Touch
Maus
Tastatur
Gamepad-Sondertasten
```

---

# Fokus-Grundsätze

## Immer ein aktiver Fokus

Jeder Screen muss nach dem Öffnen sofort ein sinnvolles fokussiertes Element besitzen.

Beispiele:

```text
Live-TV          -> erste Kategorie des ersten Providers
Filme            -> erste Kategorie des ersten Providers
Serien           -> erste Kategorie des ersten Providers
Suche            -> Suchfeld
Einstellungen    -> erster Menüpunkt links
Player Overlay   -> Timeline
Dialog           -> sichere Aktion
```

## Fokus sichtbar machen

Fokus darf nie nur über eine subtile Farbänderung erkennbar sein.

Pflichtkombination:

```text
Farbe + Form oder Größe
```

Empfohlen:

```text
Hintergrundwechsel
Fokusrahmen
leichte Skalierung
Schatten / Glow zurückhaltend
```

## Fokus und Auswahl trennen

`Focused` bedeutet:

```text
Dieses Element reagiert auf OK.
```

`Selected` bedeutet:

```text
Dieses Element ist aktuell aktiv.
```

Beispiel:

Eine Kategorie kann aktiv sein, während der Fokus gerade auf einem Sender liegt.

---

# D-Pad-Achsen

## Standardachsen

```text
Hoch / Runter    -> Bereiche, Listen, Kategorien
Links / Rechts   -> Spalten, Raster, Aktionen innerhalb eines Bereichs
OK               -> öffnen, starten, bestätigen
Zurück           -> Overlay schließen oder zurück
```

## Regel

Jeder Screen muss eine klare mentale Karte besitzen.

Nicht erlaubt:

- Fokus springt unerwartet in nicht sichtbare Bereiche.
- Links/Rechts wechselt manchmal Kategorie und manchmal Screen ohne sichtbare Logik.
- Fokus landet auf dekorativen Elementen.
- Fokus ist nach Datenrefresh verloren.

---

# Fokusreihenfolge pro Bereich

## Live-TV Browser

Layout:

```text
Provider/Kategorien | Senderliste | EPG/Vorschau
```

### Navigation

```text
Links/Rechts:
Provider/Kategorien <-> Senderliste <-> EPG/Vorschau Aktionen

Hoch/Runter:
innerhalb der jeweiligen Spalte
```

### Provider/Kategorien

```text
OK auf Provider   -> ein-/ausklappen
OK auf Kategorie  -> Kategorie aktivieren, Senderliste aktualisieren
```

Beim Fokus auf Kategorie:

```text
Senderliste aktualisiert sofort
kein zusätzlicher OK-Druck nötig
```

### Senderliste

```text
Hoch/Runter -> Sender wechseln
OK          -> Sender-Modus + EPG-Spalte + Live-Vorschau
               Fokus auf aktuelle EPG-Sendung, sofern vorhanden
Links       -> aktive Kategorie
Rechts      -> EPG/Vorschau-Aktionen, falls vorhanden
```

Beim Fokus auf Sender:

```text
EPG-Panel aktualisieren
keine Videovorschau starten
```

Nach dem ersten OK springt der Fokus auf die aktuelle EPG-Sendung, sofern vorhanden.

Der Ablauf ist fest. Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK in der Senderspalte.

### EPG/Vorschau

```text
OK auf aktueller Sendung -> Vollbild des ausgewaehlten Senders
OK auf Ansehen           -> Vollbild
OK auf EPG               -> EPG Detail / Catch-Up Liste
OK auf Catch-Up          -> Catch-Up im internen Player oeffnen
OK auf Favorit           -> Favorit umschalten
Zurück                   -> zurück zur Senderliste
```

### CH+ / CH-

Nur im Player relevant:

```text
CH+ -> nächster Sender
CH- -> vorheriger Sender
```

Keine Bestätigung.

---

# Filme

## Übersicht

Layout:

```text
Hero Info
Kategorie-Chips
Poster-Raster
```

### Initialfokus

```text
Erstes Poster der aktiven Kategorie
```

Falls Fortsetzen vorhanden:

```text
Erstes Fortsetzen-Element
```

### Navigation

```text
Hoch      -> Kategorien / Hero-nahe Aktionen, falls vorhanden
Runter    -> nächste Posterreihe
Links/Rechts -> Poster wechseln
OK        -> Detailseite öffnen
Zurück    -> Hauptnavigation / vorheriger Screen
```

### Fokus auf Poster

```text
Hero Info aktualisieren
kein Autoplay
kein Trailer-Autostart
```

---

# Serien

## Übersicht

Analog Filme.

## Detailseite

Fokusreihenfolge:

```text
Fortsetzen
Von Anfang an / Favorit / Trailer
Staffel-Auswahl
Episodenliste
```

### Staffeln

```text
Links/Rechts -> Staffel wechseln
OK           -> Staffel aktivieren
Runter       -> Episodenliste
```

### Episoden

```text
Hoch/Runter              -> Episode wechseln
OK auf Episode           -> Episode starten oder fortsetzen
Rechts aus Episode       -> Als gesehen markieren / Als ungesehen markieren
OK auf Markierungsaktion -> Status aendern
Links aus Markierung     -> Episode
```

Die Markierungsaktion ist nur fuer die fokussierte Episode sichtbar. Staffel- und Serienebene besitzen keine entsprechende Aktion.

Fallbacks bei fehlenden Daten bleiben fokussierbar.

---

# Suche

## Initialfokus

```text
Suchfeld
```

## Suchfeld

```text
OK -> Texteingabe öffnen
Rechts -> Mikrofon
Runter -> Suchverlauf oder Ergebnisse
```

## Mikrofon

```text
OK -> Android Sprachsuche starten
```

Sprachsuche startet nie automatisch.

## Ergebnisse

Struktur:

```text
Kanäle
Filme
Serien
EPG
```

Navigation:

```text
Hoch/Runter -> Ergebnisbereich wechseln
Links/Rechts -> Ergebnis innerhalb Bereich wechseln
OK -> Ergebnis öffnen
```

## Keine Treffer

Der Keine-Treffer-Zustand ist nicht fokussierbar, außer es gibt Aktionen wie Verlauf löschen.

---

# Einstellungen

Layout:

```text
Bereiche links | Optionen rechts
```

## Initialfokus

```text
zuletzt geöffneter Einstellungsbereich
```

Fallback:

```text
Allgemein
```

## Navigation

```text
Links/Rechts:
Bereiche <-> Optionen

Hoch/Runter:
innerhalb der aktiven Spalte
```

## Optionstypen

```text
SwitchRow     -> OK toggelt
SelectRow     -> OK öffnet Auswahl
ActionRow     -> OK löst Aktion oder Dialog aus
DangerRow     -> OK öffnet Bestätigungsdialog
InfoRow       -> nicht fokussierbar, außer mit Detailaktion
```

---

# Player

## Vollbildzustand

Standard:

```text
keine UI sichtbar
```

## Overlay öffnen

```text
OK -> Player Overlay öffnen
```

## Overlay schließen

```text
Zurück -> Overlay schließen
```

Wenn Overlay geschlossen ist:

```text
Zurück Live-TV -> Live-TV Browser
Zurück Film    -> Film Detailseite
Zurück Serie   -> Serien Detailseite
```

## Live-TV Player

```text
CH+ / CH- -> Sender wechseln
OK        -> Overlay
Zurück    -> Browser
```

## VOD Player

```text
OK        -> Overlay
Zurück    -> Detailseite
Links/Rechts -> Seek, wenn Overlay offen und Timeline fokussiert
```

## Auto-Next Panel bei Serien

```text
Initialfokus       -> Naechste Folge abspielen / Naechste Folge in X
Links/Rechts       -> Hauptbutton <-> Zurueck
OK auf Hauptbutton -> naechste Episode sofort starten
OK auf Zurueck     -> Countdown verwerfen und zum Staffel-/Episodenkontext zurueckkehren
Zurueck-Taste      -> wie OK auf dem sichtbaren Button Zurueck
```

Hauptbutton und `Zurueck` werden in beiden Zustaenden zeitgleich nebeneinander angezeigt. Einen Button `Abbrechen` gibt es nicht.

Bei aktiviertem Auto-Next startet die naechste Episode beim Ablauf des Countdowns automatisch. Bei deaktiviertem Auto-Next gibt es ohne OK keinen Start.

Der sekundenweise aktualisierte Zahlenwert darf Fokus, Position oder Groesse des Hauptbuttons nicht veraendern. Das Panel nutzt nicht den normalen Overlay-Auto-Hide.

---

# Dialoge

## Initialfokus

Sichere Aktion zuerst.

Beispiele:

```text
Provider löschen  -> Abbrechen
Cache leeren      -> Abbrechen
Fehlerdialog      -> Erneut versuchen oder Schließen, je nach Kontext
PIN Dialog        -> erstes Eingabefeld
```

## Zurück

```text
Zurück -> Dialog schließen
```

Ausnahme:

- nicht abbrechbare technische Sperrzustände, nur wenn zwingend nötig

## Destruktive Aktionen

Destruktive Aktion niemals als alleinige sichtbare Aktion anzeigen.

Pflicht:

```text
Abbrechen
Destruktive Aktion
```

---

# Fokus-Restore

Fokus muss nach Rückkehr wiederhergestellt werden.

Pflichtfälle:

```text
Detailseite zurück zur Posterübersicht -> vorheriges Poster
Player zurück zu Detailseite           -> vorherige Aktion oder Content
Einstellungen zurück aus Auswahl       -> vorherige Option
Dialog schließen                       -> auslösendes Element
Kategorie wechseln                     -> erster Sender der Kategorie oder letzter bekannter Sender
Provider einklappen                    -> Provider Row
```

## Refresh während Nutzung

Bei Datenrefresh:

```text
Fokus bleibt auf gleichem Inhalt, wenn Inhalt noch existiert.
Wenn Inhalt gelöscht wurde, Fokus auf nächsten sinnvollen Nachbarn.
Wenn Liste leer ist, Fokus auf Empty State Aktion oder Kategorie.
```

---

# Scroll-Verhalten

## Listen

Fokus soll sichtbar bleiben.

Regel:

```text
Fokus nicht direkt an äußerste Kante laufen lassen, wenn Liste weitergeht.
```

Empfohlen:

```text
1-2 Elemente Vorlauf sichtbar halten
```

## Raster

```text
Horizontaler Fokuswechsel innerhalb Reihe
Vertikaler Fokuswechsel zur nächsten/vorherigen Reihe
```

Bei letzter Spalte:

```text
Rechts darf nicht in unlogische Navigation springen.
```

---

# Nicht fokussierbare Elemente

Nicht fokussierbar:

```text
reine Überschriften
reine Metadaten
dekorative Icons
Progress ohne Aktion
leere Platzhalter ohne Aktion
```

Fokussierbar:

```text
Buttons
Karten
Listenitems
Chips
Tabs
Suchfeld
Mikrofon
Player Controls
Settings Rows mit Aktion
```

---

# Testkriterien

Jeder Screen gilt erst als TV-tauglich, wenn folgende Punkte erfüllt sind:

```text
Alle sichtbaren Aktionen per D-Pad erreichbar
Fokus jederzeit sichtbar
OK-Verhalten eindeutig
Zurück-Verhalten vorhersehbar
Keine Fokusfallen
Keine unerreichbaren Controls
Fokus-Restore funktioniert
Screen bleibt mit großer Schrift bedienbar
Screen bleibt mit fehlenden Bildern/EPG/Metadaten bedienbar
```

## Manuelle Testfälle

```text
1. Nur mit Fernbedienung durch App navigieren.
2. Alle Hauptbereiche ohne Touch erreichen.
3. In Live-TV Provider öffnen/schließen, Kategorie wechseln, Sender starten.
4. Filme/Serien durch Raster navigieren, Detail öffnen, zurückkehren.
5. Suche mit Texteingabe und Sprachsuche prüfen.
6. Player Overlay öffnen/schließen.
7. Dialoge mit Zurück abbrechen.
8. Bei gelöschtem Inhalt Fokus-Fallback prüfen.
9. Schriftgröße Sehr groß prüfen.
10. Animationen Aus prüfen.
```
