# 02 – High-Fidelity Mockup Review

Status: historische Review-Referenz v2

Rolle: historische Review-Referenz. Diese Datei dokumentiert die Bewertung der damaligen High-Fidelity-Mockups und ist keine Produktanforderung, kein Phasenplan und keine Umsetzungsreihenfolge.

## Ziel

Diese Design-QA bewertet die High-Fidelity Mockups unter:

```text
design/mockups/high-fidelity/rendered/
```

Geprüfte Dateien:

```text
01-live-tv-browser.png
02-player-overlay.png
03-vod-overview.png
04-search.png
05-settings.png
```

Die Bewertung entscheidet nicht, ob ein Bild perfekt ist. Sie entscheidet, welche visuellen Ideen in Vivicast übernommen werden sollen.

## Bewertungsmaßstab

```text
Accepted              = als Richtung übernehmen
Accepted with changes = gute Richtung, aber Anpassungen nötig
Needs redesign        = Grundidee brauchbar, aber neu ausarbeiten
Rejected              = nicht übernehmen
```

Kriterien:

```text
1. Optische Qualität
2. Android-TV-Tauglichkeit
3. Fokuszustände
4. Lesbarkeit auf TV
5. Navigation und Bedienlogik
6. Konsistenz mit PRD und Design-System
7. Umsetzbarkeit in Jetpack Compose for TV
```

---

# Gesamturteil

Die High-Fidelity-Serie ist als visuelle Zielrichtung deutlich besser geeignet als die Low-Fidelity-Previews.

Übernommen werden soll:

```text
dunkle Premium-TV-Atmosphäre
weiche Panels
starke Cyan-Fokuszustände
große Cards
filmische Player-Overlays
VOD-Hero mit Backdrop
horizontale Ergebnisreihen in der Suche
ruhige Master-Detail-Settings
```

Nicht 1:1 übernehmen:

```text
AI-generierte Logos
AI-generierte Poster
unechte Film-/Senderbilder
inkonsistente Icons
zu kleine Detailtexte
zufällige Textfehler
```

---

# 01 – Live-TV Browser

Datei:

```text
design/mockups/high-fidelity/rendered/01-live-tv-browser.png
```

## Bewertung

Status:

```text
Accepted with changes
```

## Stärken

```text
sehr gute Premium-TV-Wirkung
klare dreispaltige Grundstruktur
deutlich besserer Fokuszustand als Low-Fidelity
Senderkarten wirken hochwertiger
EPG/Vorschau-Panel wirkt wie echter TV-Screen
Top-Navigation wirkt modern
Provider-Spalte bleibt verständlich
```

## Probleme

```text
teilweise zu viele visuelle Details gleichzeitig
AI-Logos und Badges sind nicht produktionsfähig
rechte Vorschau wirkt etwas zu dekorativ
Textinhalte müssen später real und kontrolliert sein
manche Icons wirken uneinheitlich
```

## Übernehmen

```text
Dunkle Atmosphäre
Dreispalten-Aufbau
Cyan-Fokusrahmen mit Glow
Sender als Cards statt Tabellenzeilen
EPG-Panel mit großer Preview-Fläche
runde Action-Pills rechts unten
Top-Navigation mit aktiver Markierung
```

## Anpassen

```text
Senderkarten etwas ruhiger gestalten
Provider-Spalte weniger ikonlastig machen
Fokus nicht zu stark glühen lassen
Badges systematisch aus Design-System ableiten
rechte Preview weniger bildlastig, wenn keine Vorschau läuft
```

---

# 02 – Player Overlay

Datei:

```text
design/mockups/high-fidelity/rendered/02-player-overlay.png
```

## Bewertung

Status:

```text
Accepted with changes
```

## Stärken

```text
filmische Wirkung sehr gut
Overlay ist deutlich hochwertiger als Low-Fidelity
Controls sind groß und TV-tauglich
Pause-Fokus ist klar sichtbar
LIVE-Info, Fortschritt und nächste Sendung sind gut platziert
```

## Probleme

```text
Hintergrundbild darf später nicht fest eingebaut wirken
Overlay nimmt relativ viel Höhe ein
Untertitelbereich muss später berücksichtigt werden
AI-generierter Nachrichtenscreen ist nur Platzhalter
```

## Übernehmen

```text
transluzentes dunkles Bottom-Overlay
große horizontale Control-Buttons
fokussierter Primary-Control mit Cyan-Ring
Progressbar mit klarer Zeitinformation
Senderlogo und Sendungsinfo links
Nächstes-Programm-Bereich rechts
```

## Anpassen

```text
Overlayhöhe in Compose flexibel halten
Untertitelposition mitdenken
Controls optional reduzieren, wenn weniger Spuren verfügbar sind
Video-Hintergrund ist nur Demo, kein Asset
```

---

# 03 – VOD Übersicht

Datei:

```text
design/mockups/high-fidelity/rendered/03-vod-overview.png
```

## Bewertung

Status:

```text
Accepted
```

## Stärken

```text
stärkste visuelle Richtung der Serie
Hero-Bereich wirkt hochwertig und modern
Postercards wirken wie Streaming-App-Qualität
Fokus auf Poster ist gut erkennbar
Kategorie-Chips passen zur TV-Navigation
Fallback-Poster ist gut erkennbar
Fortsetzen-Status ist visuell sinnvoll
```

## Probleme

```text
Posterbilder sind nur Platzhalter
echte App darf keine externen Metadatenanbieter in v1 nutzen
Hero-Backdrop muss aus Providerdaten kommen oder fehlen können
Text muss bei fehlenden Daten kontrolliert degradieren
```

## Übernehmen

```text
Hero mit stark abgedunkeltem Backdrop
große Typografie im Hero
Posterraster mit Fokus-Skalierung
Kategorie-Chips als Pill Controls
Progress-Anzeige direkt auf Posterkarte
Fallback-Karte für fehlendes Poster
```

## Anpassen

```text
Fallbacks stärker systematisieren
Postergrößen mit Design-Tokens verbinden
keine fremden Poster als echte App-Assets verwenden
Hero darf auch ohne Backdrop gut aussehen
```

---

# 04 – Suche

Datei:

```text
design/mockups/high-fidelity/rendered/04-search.png
```

## Bewertung

Status:

```text
Accepted with changes
```

## Stärken

```text
Suchfeld ist klar und hochwertig
Ergebnisgruppen sind gut verständlich
horizontale Reihen passen zu Android TV
Fokus ist sichtbar
Voice-Button wirkt als klare Aktion
Screen wirkt deutlich hochwertiger als Low-Fidelity
```

## Probleme

```text
Ergebnisse sind sehr bildlastig
EPG-Ergebnis darf nicht zu sehr wie VOD wirken
Textmenge kann bei echten Daten schwanken
Alle-anzeigen-Links müssen D-Pad-tauglich definiert werden
```

## Übernehmen

```text
großes Suchfeld oben
separater Voice-Button
Ergebnisgruppen: Kanäle, Filme, Serien, EPG
horizontale Content Rows
fokussierte Ergebniscard mit Cyan-Glow
```

## Anpassen

```text
EPG-Ergebnisse kompakter gestalten
Kanal-Ergebnisse stärker von VOD-Karten unterscheiden
maximale Treffer pro Gruppe begrenzen
Keine-Treffer-Zustand zusätzlich ausarbeiten
```

---

# 05 – Einstellungen

Datei:

```text
design/mockups/high-fidelity/rendered/05-settings.png
```

## Bewertung

Status:

```text
Accepted
```

## Stärken

```text
Master-Detail-Layout ist klar
Optik-Bereich wirkt hochwertig
Optionskarten sind TV-tauglich
Fokuszustand ist eindeutig
Werte rechts sind schnell erfassbar
Hilfetexte sind sinnvoll platziert
```

## Probleme

```text
Icons müssen später konsistent aus einem Iconset kommen
bei sehr großer Schrift muss Umbruch getestet werden
lange deutsche Texte können Cards stärker belasten
```

## Übernehmen

```text
linke Bereichsnavigation
rechte Optionskarten
fokussierte Option mit Cyan-Ring
Wert rechts mit Chevron
kurzer Hilfetext unter Titel
Status als ruhige Pills
```

## Anpassen

```text
Zeilenhöhe für große Schriftgrößen absichern
Optionsgruppen für lange Settingsbereiche prüfen
kritische Aktionen visuell trennen
```

---

# Historische Designsystem-Empfehlungen aus Review

Diese Komponentenideen wurden aus der Review fuer eine spaetere Umsetzung empfohlen.

Verbindlich werden sie erst, wenn sie in normativen Design-, Component- oder App-Repo-Entscheidungen konkretisiert sind.

```text
VivicastBackground mit atmosphärischem Verlauf
VivicastPanel für weiche TV-Flächen
VivicastFocusContainer mit Ring, Glow und Skalierung
VivicastTopNavigation
VivicastProviderRail
VivicastChannelCard
VivicastPosterCard
VivicastHeroHeader
VivicastSearchField
VivicastSettingsRow
VivicastPlayerOverlay
VivicastActionPill
```

## Nicht als Assets übernehmen

```text
AI-generierte Logos
AI-generierte Poster
AI-generierte Videobilder
AI-generierte Icons
```

Diese Mockups sind visuelle Referenzen, keine finalen Assets.

## Folgedokumente

Diese Review wurde in folgende Folgekontexte ueberfuehrt:

```text
1. UI Direction Decisions
2. Owner High-Fidelity Feedback
3. spaetere Codex-Planung im App-Repository
```

Diese Liste ist keine Umsetzungsreihenfolge.

## Review-Status

```text
High-Fidelity Mockup Review abgeschlossen v1
Keine blockierenden Probleme
VOD und Settings akzeptiert
Live-TV, Player und Suche mit Anpassungen akzeptiert
```
