# 03 – Owner High-Fidelity Feedback

Status: abgeschlossen v4

## Ziel

Dieses Dokument erfasst das Owner-Feedback zu den High-Fidelity Mockups.

Die Bewertung ist die fachlich verbindliche Design-Rueckmeldung des Product Owners.

Referenzen:

```text
design/mockups/high-fidelity/rendered/
archive/review/2026-06-24/02-high-fidelity-mockup-review.md
```

---

# 01 – Live-TV Browser

Datei:

```text
design/mockups/high-fidelity/rendered/01-live-tv-browser.png
```

## Status

```text
Behalten, aber ueberarbeiten
```

## Gefaellt

```text
Grundrichtung stimmt
Farbkonzept gefaellt und kann uebernommen werden
Aufbau mit Navigation oben gefaellt
Spalten darunter gefallen
```

## Aenderungen

Der Live-TV Browser soll ein adaptives Spaltenmodell bekommen.

Kategorie-Modus:

```text
Provider/Kategorien | Senderliste | Vorschau/Details
```

Sender-Modus:

```text
Senderliste | Sender-EPG | Vorschau/Details
```

Nach dem ersten OK in der Senderspalte:

```text
Provider/Kategorie-Spalte ausblenden
Senderliste wird linke Spalte
mittlere Spalte zeigt EPG fuer den ausgewaehlten Sender
rechte Detail-/Preview-Spalte bleibt sichtbar
Live-Vorschau startet gleichzeitig
Fokus springt auf die aktuelle EPG-Sendung, sofern vorhanden
```

Wenn der Nutzer wieder ganz nach links geht:

```text
EPG-Spalte ausblenden
Provider/Kategorie-Spalte wieder anzeigen
```

Weitere Entscheidung:

```text
EPG-Button in der rechten Spalte entfernen
EPG ist direkt in der mittleren Spalte sichtbar
```

Preview-Verhalten, ersetzt durch Owner-Entscheidung O-06 vom 2026-06-23:

```text
Der Ablauf ist fest und nicht konfigurierbar.
Beim blossen Senderfokus startet kein Stream.
Erstes OK in der Senderspalte startet Sender-Modus und Stream-Vorschau.
Der Fokus springt auf die aktuelle Sendung in der EPG-Spalte, sofern vorhanden.
OK auf der fokussierten aktuellen Sendung oeffnet Vollbild.
Es gibt keine Preview-Einstellung.
```

---

# 02 – Player Overlay

Datei:

```text
design/mockups/high-fidelity/rendered/02-player-overlay.png
```

## Status

```text
Behalten, aber ueberarbeiten
```

## Gefaellt

```text
Overlay gefaellt optisch sehr gut
transluzentes Bottom-Overlay passt
Timeline / blauer Balken passt als zentrales Bedienelement
```

## Aenderungen

Folgende Buttons entfernen:

```text
Pause
Zurueckspulen
Vorspulen
```

Timeline-Logik:

```text
Timeline ist fokussierbar
OK auf Timeline pausiert
erneutes OK auf Timeline spielt weiter
Links/Rechts auf Timeline spult zurueck oder vor
```

Zusaetzliche Stream-Information:

```text
Streamqualitaet: HD, Full HD, 4K
FPS: 25 FPS, 50 FPS, 60 FPS
Audio: Mono, Stereo, 5.1
```

Darstellung:

```text
kleine Status-Badges
Position bevorzugt oben rechts im Overlay oder Player-Bereich
```

Auto-Next-Verhalten, Owner-Entscheidung O-07 vom 2026-06-23:

```text
Auto-Next ist unter Wiedergabe ein Toggle und standardmaessig Aus
Countdown-Werte sind 5, 10, 15 und 30 Sekunden; Standard ist 10 Sekunden
bei Aus erscheint Naechste Folge abspielen erst nach dem tatsaechlichen Episodenende
bei Ein erscheint Naechste Folge in X X Sekunden vor dem tatsaechlichen Episodenende
OK startet sofort, beim Ablauf startet die naechste Episode automatisch
Zurueck erscheint in beiden Zustaenden zeitgleich neben dem Hauptbutton; kein Button Abbrechen
OK auf Zurueck oder die Zurueck-Taste verwirft den Ablauf und fuehrt mit erhaltenem Staffel-/Episodenkontext zur Serien-Detailseite
nach Serienende erscheint kein Auto-Next-Panel
```

---

# 03 – VOD Overview

Datei:

```text
design/mockups/high-fidelity/rendered/03-vod-overview.png
```

## Status

```text
Behalten
```

## Gefaellt

```text
VOD Overview gefaellt extrem gut
fast nichts auszusetzen
Hero, Kategorien und Poster-Raster passen
```

## Aenderungen

```text
Bewertung direkt auf Posterkarte anzeigen
Titel unter dem Poster anzeigen
```

Begruendung:

```text
Poster allein ist nicht immer eindeutig
bei fehlendem Poster muss der Titel sichtbar bleiben
```

Fallback-Beispiel:

```text
Kein Poster verfuegbar
Titel sichtbar darunter
```

Abschluss-Schwelle und Gesehen-Status, Owner-Entscheidung O-08 vom 2026-06-23:

```text
fester Abschlusswert 95 Prozent; keine Einstellung
gilt fuer Filme und einzelne Episoden
Medienende und manuelles Als gesehen markieren setzen ebenfalls abgeschlossen
abgeschlossene Inhalte sind keine direkten Fortsetzen-Ziele
Als ungesehen markieren loescht den gespeicherten Fortschritt vollstaendig
keine entsprechende Aktion fuer komplette Staffeln oder Serien
95 Prozent beendet die Wiedergabe nicht und loest kein Auto-Next aus
```

---

# 04 – Suche

Datei:

```text
design/mockups/high-fidelity/rendered/04-search.png
```

## Status

```text
Behalten, leicht ueberarbeiten
```

## Gefaellt

```text
Suchansicht gefaellt optisch sehr gut
grosses Suchfeld passt
Voice-Button passt
Ergebnisgruppen und horizontale Darstellung passen
```

## Aenderungen

```text
bei Filmen und Serien Bewertung anzeigen
Alle anzeigen entfernen
alle Ergebnisse je Kategorie horizontal nebeneinander anzeigen
Nutzer scrollt in der Kategorie links/rechts
```

---

# 05 – Einstellungen

Datei:

```text
design/mockups/high-fidelity/rendered/05-settings.png
```

## Status

```text
Behalten
```

## Gefaellt

```text
sehr gut getroffen
keine aktuellen Einwaende
linke Navigation und rechte Optionskarten koennen uebernommen werden
```

---

# Finale Owner-Entscheidung

```text
High-Fidelity Richtung ist freigegeben
Farbkonzept ist freigegeben
VOD Overview und Settings koennen nahezu direkt als UI-Richtung dienen
Live-TV Browser bekommt adaptives Spaltenmodell
Player Overlay bekommt Timeline-zentrierte Bedienung
Suche bekommt horizontale Ergebnisreihen ohne Alle-anzeigen-Aktion
```

## Naechster Schritt

Die UI Direction Decisions muessen diese Owner-Rueckmeldung beruecksichtigen.

Danach kann die technische UI-Demo vorbereitet werden.