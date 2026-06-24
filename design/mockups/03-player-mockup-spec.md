# 03 – Player Mockup Specification

Status: Spezifikation v4

## Ziel

Das Player-Mockup konkretisiert Vollbildwiedergabe und Overlay-Verhalten.

Der Player muss ruhig wirken und darf den Inhalt nicht dauerhaft überdecken.

## Referenz

```text
Wireframe: design/wireframes/06-player.md
Design-System: design/design-system/
```

## Bildformat

```text
1920 x 1080
16:9
```

## Mockup-Varianten

```text
1. Player ohne UI
2. Live-TV Overlay
3. VOD Overlay
4. Fehlerdialog
5. Nächste-Folge-Overlay
```

---

# Variante 1: Player ohne UI

## Beschreibung

```text
Vollbild-Videobild
keine sichtbare App-UI
kein Logo
keine Navigationsleiste
```

## Zielwirkung

```text
störungsfrei
nativ
kein Portal-Gefühl
```

---

# Variante 2: Live-TV Overlay

## Layout

Overlay unten:

```text
Höhe: ca. 300 dp
Breite: volle Safe-Area
Hintergrund: dunkler Scrim / Surface mit 75-85 % Deckkraft
```

Inhalt:

```text
Senderlogo
Sendername
LIVE Badge
Aktuelle Sendung
Startzeit / Endzeit / verbleibende Zeit
Progressbar
Beschreibung
Nächste Sendung
Control Buttons
```

## Beispiel

```text
ARD HD {LIVE}
Tagesschau
20:00 – 20:15   Noch 6 Minuten
████████░░░░░░░░░░░░
Kurznachrichten des Tages.
Danach: Wetter vor acht 20:15 – 20:20

[Timeline: OK Play/Pause, Links/Rechts Seek] [Audio] [Untertitel] [Bildformat] [Mehr]
```

## Fokus

Primärfokus:

```text
Timeline
```

Fokusdarstellung:

```text
Akzentfläche
leichte Skalierung
klarer Rahmen
```

## Untertitelbereich

Overlay darf den unteren Bereich nutzen, aber Untertitel dürfen in späterer Implementierung nicht unlesbar werden.

Empfehlung:

```text
Untertitel oberhalb des Overlays anzeigen, wenn Overlay offen ist.
```

---

# Variante 3: VOD Overlay

## Layout

Overlay unten:

```text
Höhe: ca. 240 dp
```

Inhalt:

```text
Titel
Position / Dauer
Progressbar
Controls
```

Beispiel:

```text
Dune: Part Two
00:42:18 / 02:46:00
█████░░░░░░░░░░░░░░░░░░░

[Timeline: OK Play/Pause, Links/Rechts Seek] [Audio] [Untertitel] [Bildformat] [Mehr]
```

## Serien Zusatz

```text
The Expanse
Staffel 2 Folge 7
00:18:42 / 00:44:00
```

---

# Variante 4: Fehlerdialog

## Layout

Dialog mittig.

```text
Breite: 720-900 dp
Hintergrund: #111827
Fokus: auf Erneut versuchen
```

Beispiel:

```text
Sender konnte nicht gestartet werden.

ARD HD

Bitte versuche es erneut oder wähle einen anderen Sender.

[Erneut versuchen] [Anderen Sender wählen] [Schließen]
```

## Zielwirkung

```text
klar
nicht technisch
handhabbar
```

---

# Variante 5: Nächste Folge

## Layout

Kompaktes Panel unten rechts oder mittig unten. Es bleibt bis zur Aktion beziehungsweise zum Start der naechsten Episode sichtbar.

Auto-Next deaktiviert, erst nach dem Episodenende:

```text
The Expanse
Staffel 2 Folge 8

[Nächste Folge abspielen] [Zurück]
```

Auto-Next aktiviert, Beispiel 10 Sekunden vor dem Episodenende:

```text
The Expanse
Staffel 2 Folge 8

[Nächste Folge in 10] [Zurück]
```

Primärfokus liegt in beiden Varianten auf dem ersten Button. `Zurück` wird jeweils zeitgleich daneben angezeigt; einen Button `Abbrechen` gibt es nicht.

OK auf `Zurück` oder die Zurück-Taste verwirft einen laufenden Countdown und führt zur Serien-Detailseite mit dem zuvor gewählten Staffel-/Episodenkontext zurück.

Der Zahlenwert wird sekundenweise aktualisiert, ohne Breite, Position oder Fokus des Buttons sichtbar springen zu lassen.

Nach der letzten Episode der Serie wird diese Variante nicht angezeigt.

Die feste 95-Prozent-Abschluss-Schwelle ist kein visueller Ausloeser fuer dieses Panel und beendet die Wiedergabe nicht.

Bei externen Playern wird diese Variante nicht angezeigt. Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast nur einen nicht blockierenden Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.

Externe Player werden nur fuer Filme und einzelne Episoden angeboten. Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.

Catch-Up nutzt das Player-Layout mit EPG-Kontext, erzeugt aber keinen VOD-Fortschritt und kein Resume-Ziel.

---

# Visuelle Regeln

## Hintergrundbild

Für Mockups kann ein abstrakter dunkler Videoplatzhalter verwendet werden.

Nicht nötig:

```text
realer Filmframe
```

## Player Controls

Icons plus Text verwenden:

```text
Timeline
Audio
Untertitel
Bildformat
Mehr
```

Pause und Spulen laufen ueber die fokussierte Timeline, nicht ueber separate Hauptbuttons.

Ein EPG-Button erscheint im Player-Overlay nicht.

Audio- und Untertitelauswahl im Player gilt nur fuer die aktuelle Wiedergabe.

Reine Icons nur in späterer Implementierung, wenn eindeutig und getestet.

## Progressbar

```text
Höhe: 6-8 dp
aktiver Bereich: Accent
inaktiver Bereich: dunkles Grau
```

## Badges

```text
LIVE rot
Timeshift blau/violett
```

---

# Akzeptanz für visuelles Mockup

```text
Vollbild ohne UI wirkt wirklich leer
OK-Overlay-Konzept ist visuell nachvollziehbar
Overlay ist lesbar aus TV-Entfernung
Fokus auf Control ist sofort sichtbar
Player wirkt nicht überladen
Fehlerdialog ist handlungsorientiert
Nächste-Folge-Overlay ist klar und klein genug
```
