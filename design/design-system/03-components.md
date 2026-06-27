# 03 – Components

Status: verbindliche Design-Referenz v8

Dieses Dokument definiert die zentralen UI-Komponenten für Vivicast.

Alle Komponenten müssen Android-TV-tauglich sein:

```text
D-Pad bedienbar
OK-auslösbar
Zurück-kompatibel
klarer Fokuszustand
keine Touch-only-Gesten
```

---

# Komponenten-Grundregeln

## Fokuszustände

Jede fokussierbare Komponente hat mindestens diese Zustände:

```text
Default
Focused
Pressed
Selected
Disabled
Error
```

Nicht jede Komponente nutzt alle Zustände sichtbar, aber sie müssen technisch modellierbar sein.

## Mindestgröße

Fokussierbare Elemente:

```text
Mindesthöhe: 48 dp
Empfohlen TV: 64 dp oder größer
```

Listenitems und Karten dürfen größer sein.

## Textkürzung

Lange Texte werden nicht gequetscht.

Regeln:

```text
Primärtitel: maximal 1-2 Zeilen
Sekundärtext: maximal 1-2 Zeilen
Beschreibung: nur in Detail-/Hero-Flächen länger
```

Kürzung:

```text
Ellipsis
```

---

# App Shell

## Zweck

Rahmen für Hauptnavigation, Content-Bereich und globale Overlays.

## Enthält

```text
Top Navigation
Content Host
Global Loading Layer
Snackbar / Toast Ersatz
Dialog Host
Player Host
```

## Regeln

- Kein globaler Bottom-Navigation-Ansatz.
- Hauptscreens nutzen die Top Navigation.
- Der Player ist ein Fullscreen-Kontext ohne dauerhaft sichtbare Top Navigation.
- Aktiver Hauptbereich bleibt visuell eindeutig.

---

# Top Navigation

## Zweck

Zugriff auf Hauptbereiche:

```text
Home
Live-TV
Filme
Serien
Suche
Einstellungen
```

## Verhalten

```text
dauerhaft sichtbar auf Hauptscreens
nicht sichtbar im Player
Zurück aus Hauptscreen-Content -> Top Navigation
doppelte Zurück-Bestätigung aus Top Navigation -> App beenden
```

## Fokus

Focused:

```text
Fläche aufhellen
Akzentlinie unten
Label sichtbar / stärker
```

Selected:

```text
Akzentlinie bleibt sichtbar
Text primary
```

## Regeln

- Beim Wechsel zwischen Hauptbereichen wird der alte Inhaltsfokus des verlassenen Bereichs nicht wiederhergestellt.
- Suche und Einstellungen sind Hauptbereiche, aber keine waehbaren Startbereiche.
- Home ist fester Hauptbereich und Standard-Startscreen.

---

# Provider Tree

## Zweck

Darstellung mehrerer Provider und ihrer Kategorien im Live-TV-Bereich.

## Struktur

```text
Global Favorites Item
Provider Row
  Kategorie: Sport
  Kategorie: News
  Kategorie: Nicht kategorisiert
```

Das Global Favorites Item steht oberhalb des ersten Providers und ist nicht Teil eines Providers.

## Provider Row

Zeigt:

```text
Expand/Collapse Icon
Provider Name
Status Badge optional
```

Zustände:

```text
Collapsed
Expanded
Focused
SelectedProvider
DisabledProvider
ErrorProvider
```

## Category Row

Zeigt:

```text
Kategoriename
Anzahl optional
Hidden-Status optional in Einstellungen
```

## Regeln

- Provider sind standardmäßig einklappbar.
- Live-TV Favoriten sind global sichtbar.
- Favoriten-Eintraege bleiben intern providergebunden und zeigen bei Bedarf Provider-Kontext.
- Kategorien werden nicht normalisiert.
- Sender ohne Kategorie erscheinen als `Nicht kategorisiert`.

---

# Channel List Item

## Zweck

Senderanzeige im Live-TV-Browser.

## Inhalt

```text
Senderlogo oder TV-Icon
Sendername
Aktuelle Sendung
Fortschrittsbalken
Catch-Up Symbol optional
Favorit Symbol optional
```

## Maße

```text
Höhe: 96 dp
Logo: 64 x 40 dp
Innenabstand: 16 dp
```

## Fokus

Focused:

```text
Hintergrund vc_surface_focus
Fokusrahmen 2 dp
Sendername stärker
EPG/Vorschau-Spalte aktualisiert sich
```

Wichtig:

Beim Fokus wird keine Videovorschau gestartet.

## Aktionen

OK in der Senderspalte:

```text
Sender-Modus aktivieren
EPG-Spalte oeffnen
Live-Vorschau starten
Fokus auf aktuelle EPG-Sendung setzen, sofern vorhanden
```

Der Ablauf ist fest. Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK.

CH+ / CH- im Player:

```text
sofortiger Senderwechsel
keine Bestätigung
```

---

# EPG Preview Panel

## Zweck

Informationen zum fokussierten Sender und aktueller/nächster Sendung.

## Inhalt

```text
Vorschaufenster oder Platzhalter
Sendername
Aktuelle Sendung
Startzeit
Endzeit
Beschreibung
Nächste Sendung
Aktionen bei aktiver Vorschau
```

## Leerer Zustand

```text
Keine Programminformationen verfügbar
```

## Regeln

- Panel aktualisiert sich beim Fokuswechsel.
- Beim blossen Senderfokus startet keine Videovorschau.
- Erstes OK in der Senderspalte startet die Vorschau und fokussiert die aktuelle EPG-Sendung, sofern vorhanden.
- OK auf der fokussierten aktuellen EPG-Sendung oeffnet Vollbild.
- Der Ablauf ist fest und besitzt keine Preview-Einstellung.
- Es darf immer nur eine Vorschau gleichzeitig laufen.

---

# Poster Card

## Zweck

Filme, Serien und Staffeln im Raster anzeigen.

## Inhalt

```text
Poster
Titel
Metadaten optional
Progress optional, solange nicht abgeschlossen
Completion Badge optional
Favorite Badge optional
```

## Varianten

```text
MoviePosterCard
SeriesPosterCard
SeasonPosterCard
EpisodeCard
```

## Fokus

Focused:

```text
Skalierung 1.06x
Fokusrahmen
leichter Schatten
Hero-Informationsbereich aktualisiert sich
```

## Fallback

Bei fehlendem Poster:

```text
Fläche vc_surface_high
Icon
Kein Bild verfügbar
Titel
```

---

# Episode Row

## Zweck

Episoden auf der Serien-Detailseite kompakt und D-Pad-tauglich darstellen.

## Inhalt

```text
Thumbnail oder Fallback
Episodennummer und Titel
Beschreibung optional
Laufzeit optional
Fortschritt oder Completion Badge
fokussierte Markierungsaktion
```

## Markierungsaktion

```text
nicht abgeschlossen -> Als gesehen markieren
abgeschlossen       -> Als ungesehen markieren
```

Regeln:

- Die Aktion wird nur bei fokussierter Episode sichtbar und fokussierbar.
- Rechts aus der Episode wechselt zur Aktion; Links kehrt zur Episode zurueck.
- OK auf der Episode startet oder setzt fort; OK auf der Aktion aendert nur den Gesehen-Status.
- `Als ungesehen markieren` loescht den gespeicherten Episodenfortschritt.
- Es gibt keine entsprechende Aktion fuer Staffel- oder Serien-Header.

---

# Hero Info Panel

## Zweck

Großer Informationsbereich über VOD-Rastern.

## Inhalt Filme

```text
Titel
Bewertung
Genre
Jahr
Laufzeit
Beschreibung
```

## Inhalt Serien

```text
Titel
Bewertung
Genre
Jahr
Beschreibung
```

## Verhalten

- Aktualisiert sich beim Fokus auf Poster.
- Keine zusätzliche Aktion nötig.
- Lange Beschreibung wird begrenzt, Detailseite enthält vollständige Informationen.

## Layout

```text
Höhe: ca. 260 dp
Position: oben
Hintergrund: dunkles Panel, optional Backdrop sehr gedimmt
```

Backdrop-Regel:

- Backdrop darf Text nicht verschlechtern.
- Immer mit dunklem Scrim.

---

# Detail Header

## Zweck

Detailseiten für Film und Serie.

## Film-Variante

```text
Poster links
Titel
Bewertung
Genre
Jahr
Laufzeit
Beschreibung
Regisseur
Schauspieler
Aktionen
```

## Serien-Variante

```text
Poster / Backdrop
Titel
Bewertung
Beschreibung
Fortsetzen-Bereich
Staffel-Auswahl
Episodenliste
```

## Fokusreihenfolge

```text
Primäraktion
Sekundäraktionen
Staffeln / Episoden
```

---

# Buttons

## Varianten

```text
PrimaryButton
SecondaryButton
GhostButton
DangerButton
IconButton
PlayerButton
```

## PrimaryButton

Für Hauptaktion:

```text
Abspielen
Fortsetzen
Ansehen
Jetzt starten
```

Focused:

```text
Akzentfläche
Text auf Akzent
Skalierung 1.03x
```

## SecondaryButton

Für Nebenaktionen:

```text
Trailer
Von Anfang an
Zu Favoriten
Als gesehen markieren
Als ungesehen markieren
Audio
Untertitel
```

## DangerButton

Für destruktive Aktionen:

```text
Provider löschen
EPG Quelle löschen
Verlauf löschen
Cache leeren
```

Regel:

DangerButton nie allein anzeigen. Immer mit abbrechbarer Alternative.

---

# Chips / Tabs

## Zweck

Kategorien, Filter, Sortierungen.

## Varianten

```text
CategoryChip
FilterChip
SortChip
Tab
```

## Regeln

- Aktive Kategorie klar unterscheiden.
- Fokus und Auswahl sind getrennte Zustände.
- Horizontale Listen müssen sauber per Links/Rechts navigierbar sein.

## Beispiel

```text
[Favoriten] [Fortsetzen] [Action] [Drama] [Thriller]
```

---

# Search Field

## Zweck

Globale Suche.

## Inhalt

```text
Suchtext
Placeholder: Suche...
Mikrofon-Aktion
Löschen-Aktion optional
```

## Verhalten

```text
Eingabe -> 300 ms Debounce -> lokale Suche
Mikrofon fokussieren + OK -> Android Sprachsuche
```

## Regeln

- Sprachsuche startet nie automatisch.
- Suchverlauf maximal 20 Einträge.
- Suchergebnisse nach Kanäle, Filme, Serien, EPG getrennt.
- EPG-Ergebnisse duerfen bei kurzen Suchbegriffen fehlen, bis die fachliche Mindestlaenge erreicht ist.
- Es gibt keine verpflichtende `Alle anzeigen`-Aktion.

---

# Settings Row

## Zweck

Einzelne Option in Einstellungen.

## Typen

```text
SwitchRow
SelectRow
ActionRow
InfoRow
SliderRow optional
DangerActionRow
```

## Inhalt

```text
Titel
Beschreibung optional
Aktueller Wert
Status / Icon optional
```

## Fokus

Focused:

```text
Hintergrund aufhellen
Wert deutlicher
OK öffnet Auswahl oder löst Aktion aus
```

## Regeln

- Werte nie nur über Farbe anzeigen.
- Längere Erklärungen als Beschreibung unter dem Titel.
- Destruktive Aktionen mit Dialog bestätigen.

---

# Dialog

## Zweck

Bestätigungen, Fehler, PIN, destruktive Aktionen.

## Typen

```text
ConfirmDialog
ErrorDialog
InfoDialog
PinDialog
ProgressDialog
```

## Struktur

```text
Titel
Beschreibung
Optionale Details
Aktionen
```

## Regeln

- Standardfokus auf sicherer Aktion.
- Bei destruktiven Aktionen: Fokus zuerst auf Abbrechen.
- Keine unnötigen Exit-Dialoge.
- Dialoge müssen mit Zurück abbrechbar sein, außer technische Sperrzustände.

## Beispiel Provider löschen

```text
Titel: Provider wirklich löschen?
Text: Diese Aktion kann nicht rückgängig gemacht werden.
Aktionen: Abbrechen | Löschen
Standardfokus: Abbrechen
```

---

# Player Overlay

## Zweck

Bedienung während Wiedergabe.

## Live-TV Overlay

Inhalt:

```text
Senderlogo
Sendername
Aktuelle Sendung
Startzeit
Endzeit
Verbleibende Zeit
Beschreibung
Nächste Sendung
Timeline
Stream-Info-Badges
Sekundaere Aktionen: Audio, Untertitel, Bildformat, Mehr
```

## VOD Overlay

Inhalt:

```text
Titel
Position
Dauer
Progressbar
Timeline
Stream-Info-Badges
Sekundaere Aktionen: Audio, Untertitel, Bildformat, Mehr
```

## Verhalten

```text
OK       -> Overlay öffnen
Zurück   -> Overlay schließen
Initialfokus -> Timeline
OK auf Timeline -> Play/Pause
Links/Rechts auf Timeline -> Seek oder Timeshift-Bewegung
Links/Rechts ausserhalb Timeline -> zwischen fokussierbaren Aktionen wechseln
CH+/CH-  -> Live-TV Senderwechsel
```

## Regeln

- Vollbildmodus zeigt standardmäßig keine UI.
- Timeline ist das primaere fokussierbare Bedienelement.
- Pause, Vorspulen und Zurückspulen werden nicht als separate Hauptbuttons angeboten.
- `Audio`, `Untertitel`, `Bildformat` und `Mehr` sind sekundaere Action Chips.
- Overlay verdeckt nicht dauerhaft zentrale Bildinformationen.
- Textbereiche erhalten dunklen Scrim.
- Catch-Up bleibt im internen Player, nutzt EPG-Kontext und erzeugt keinen VOD-Fortschritt.
- Externe Player werden nur fuer Filme und einzelne Episoden angeboten.
- Manuelle Audio- oder Untertitelauswahl gilt nur fuer die aktuelle Wiedergabe.

---

# Auto-Next Panel

## Zweck

Nächste Serienepisode manuell oder nach Countdown starten.

## Zustaende

```text
Auto-Next Aus -> nach Episodenende: `Nächste Folge abspielen`
Auto-Next Ein -> vor Episodenende: `Nächste Folge in X`
```

## Verhalten

- Hauptbutton ist initial fokussiert.
- OK auf dem Hauptbutton startet die naechste Episode sofort.
- Nur der aktivierte Countdown startet beim Ablauf automatisch.
- Der Button `Zurück` wird in beiden Zustaenden zeitgleich neben dem Hauptbutton angezeigt.
- OK auf `Zurück` oder die Zurück-Taste beendet den Ablauf und fuehrt zur Serien-Detailseite mit dem zuvor gewaehlten Staffel-/Episodenkontext zurueck.
- Das Panel besitzt keinen Button `Abbrechen`.
- X wird sekundenweise aktualisiert, ohne Fokus oder Layout zu verschieben.
- Das Panel bleibt sichtbar und nutzt nicht den normalen Overlay-Auto-Hide.
- Nach der letzten Episode einer Serie wird es nicht angezeigt.
- Bei externen Playern wird es nicht angezeigt.

---

# Progress Indicators

## Typen

```text
LinearProgress
CircularProgress
PlaybackProgress
EPGProgress
RefreshStatus
CompletionBadge
```

## Regeln

- Fortschritt muss ruhig animiert sein.
- Keine blockierende Vollbild-Ladeanzeige beim Appstart, wenn lokale Daten vorhanden sind.
- Refresh läuft im Hintergrund und wird über Status angezeigt.
- Refresh-Status darf `Erfolgreich mit Teilfehlern` als zusammenfassenden Zustand anzeigen.

---

Externe Player schreiben keinen automatischen PlaybackProgress zurueck; nach Rueckkehr wird nur ein Hinweis angezeigt.

---

# Empty States

## Zweck

Kontrollierte Darstellung fehlender Daten.

## Beispiele

```text
Keine Programminformationen verfügbar
Keine Ergebnisse gefunden
Keine Filme in dieser Kategorie
Keine Serien in dieser Kategorie
Keine Favoriten vorhanden
```

## Regeln

- Leerer Zustand ist kein Fehlerzustand.
- Aktion anbieten, wenn sinnvoll.
- Keine technischen Fehlermeldungen ohne Nutzerwert.

---

# Error States

## Zweck

Fehler verständlich und handhabbar machen.

## Beispiele

```text
Verbindungsfehler
Anmeldedaten ungültig
Stream konnte nicht gestartet werden
Backup konnte nicht importiert werden
```

## Aktionen

```text
Erneut versuchen
Bearbeiten
Anderen Sender wählen
Schließen
```

## Regeln

- Fehler dürfen keinen Datenverlust verursachen.
- Alte Daten bleiben sichtbar, wenn Refresh fehlschlägt.
- Bei abgebrochenem oder fehlgeschlagenem Refresh duerfen keine halb importierten Inhalte angezeigt werden.
- Diagnoseinformationen nur im Diagnosemodus oder Export anzeigen.
