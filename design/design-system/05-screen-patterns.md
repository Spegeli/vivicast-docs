# 05 – Screen Patterns

Status: verbindliche Design-Referenz v7

Dieses Dokument definiert wiederkehrende Screen-Patterns für Vivicast.

Es ersetzt keine Wireframes. Es legt fest, welche Layout- und Zustandsmuster später in Wireframes und Implementierung verwendet werden.

---

# Pattern-Grundsätze

## TV-first

Alle Patterns müssen mit D-Pad, OK und Zurück funktionieren.

## Daten-first

Die Screens müssen auch mit großen und unvollständigen IPTV-Daten funktionieren.

## Kein blockierender Start

Wenn lokale Daten vorhanden sind, startet die App direkt mit vorhandenen Daten. Aktualisierungen laufen im Hintergrund.

## Fokus zuerst

Jedes Pattern definiert:

```text
Initialfokus
Fokusachsen
OK-Verhalten
Zurück-Verhalten
Empty State
Error State
```

---

# Pattern 1: Live-TV Browser

## Zweck

Zentrale Live-TV-Navigation mit Provider-Isolation, Kategorien, Senderliste und EPG/Vorschau.

## Struktur

```text
┌────────────────────────────────────────────────────────────────────┐
│ Provider/Kategorien │ Senderliste                  │ EPG/Vorschau │
└────────────────────────────────────────────────────────────────────┘
```

## Spalten

```text
Provider/Kategorien: 300 dp
Senderliste:         620 dp
EPG/Vorschau:        Restbreite
```

## Layout-Modi

Kategorie-Modus:

```text
Globale Favoriten / Provider / Kategorien | Senderliste | Vorschau/Details
```

Sender-Modus:

```text
Senderliste | Sender-EPG | Vorschau/Details
```

Im Sender-Modus ist die Provider-/Kategorien-Spalte ausgeblendet.

## Initialfokus

Beim frischen Oeffnen:

```text
erste Kategorie des ersten Providers
```

Die globale Favoriten-Kategorie steht oberhalb des ersten Providers, ist aber nicht der initiale Inhaltsfokus.

## Verhalten

```text
Fokus auf Kategorie          -> Senderliste aktualisiert sofort
Fokus auf Sender             -> EPG Panel aktualisiert, kein Streamstart
Erstes OK in Senderspalte    -> Sender-Modus, EPG-Spalte und Preview
                                Fokus auf aktuelle EPG-Sendung, sofern vorhanden
OK auf aktueller EPG-Sendung -> Vollbild
OK auf Provider              -> Ein-/Ausklappen
```

Der Preview-Ablauf ist fest und nicht als Einstellung konfigurierbar.

## Empty States

```text
Keine Sender in dieser Kategorie
Keine Favoriten vorhanden
Keine Programminformationen verfügbar
```

## Error States

```text
Verbindungsfehler
Anmeldedaten ungültig
Provider deaktiviert
```

## Besondere Regeln

- Provider werden nie zusammengeführt.
- Live-TV Favoriten sind global sichtbar.
- Favoriten-Eintraege bleiben intern providergebunden.
- Keine automatische Vorschau beim Fokus.
- Nur eine Vorschau gleichzeitig.

---

# Pattern 2: VOD Overview

Gilt für:

```text
Filme
Serien
```

## Zweck

Große VOD-Bibliotheken schnell durchsuchbar und visuell verständlich darstellen.

## Struktur

```text
┌──────────────────────────────────────────────────────────────┐
│ Hero Info                                                     │
├──────────────────────────────────────────────────────────────┤
│ Kategorie-Chips                                               │
├──────────────────────────────────────────────────────────────┤
│ Poster-Raster                                                 │
└──────────────────────────────────────────────────────────────┘
```

## Initialfokus

```text
Filme:  erstes Poster in Favoriten/Fortsetzen/aktiver Kategorie
Serien: erstes Poster in Fortsetzen/Favoriten/aktiver Kategorie
```

## Verhalten

```text
Fokus auf Poster -> Hero Info aktualisiert
OK auf Poster    -> Detailseite
Hoch             -> Kategorie-Chips
Zurück           -> vorheriger Hauptbereich / Navigation
```

## Kategorien

Immer möglich:

```text
Favoriten
```

Optional, falls Daten vorhanden:

```text
Fortsetzen
```

Provider-Kategorien werden unverändert übernommen.

## Empty States

```text
Keine Filme in dieser Kategorie
Keine Serien in dieser Kategorie
Keine Favoriten vorhanden
Keine begonnenen Inhalte vorhanden
```

## Fallbacks

```text
Poster fehlt       -> Kein Bild verfügbar
Beschreibung fehlt -> Beschreibung ausblenden oder kurzer Fallback
Bewertung fehlt    -> Bewertungszeile ausblenden
```

---

# Pattern 3: Movie Detail

## Zweck

Film abspielen, fortsetzen, Trailer öffnen und Favorit verwalten.

## Struktur

```text
Poster | Metadaten + Beschreibung + Aktionen
```

## Primäraktionen

Neuer Film:

```text
Abspielen
Trailer
Zu Favoriten hinzufügen
Als gesehen markieren
```

Begonnener Film:

```text
Fortsetzen
Von Anfang an
Trailer
Zu Favoriten hinzufügen
Als gesehen markieren
```

Gesehener Film:

```text
Status Gesehen
Von Anfang an
Trailer
Zu Favoriten hinzufügen
Als ungesehen markieren
```

## Initialfokus

```text
Fortsetzen oder Abspielen
bei gesehenem Film: Von Anfang an
```

## Zurück

```text
zur vorherigen Posterposition
```

## Trailer-Regel

Trailer wird über YouTube-App geöffnet.

Wenn YouTube-App fehlt:

```text
YouTube-App erforderlich
Bitte installiere die YouTube-App, um Trailer ansehen zu können.
```

---

# Pattern 4: Series Detail

## Zweck

Serie schnell fortsetzen oder gezielt Staffel/Episode wählen.

## Struktur

```text
Titel / Beschreibung
Fortsetzen-Bereich
Aktionen
Staffel-Auswahl
Episodenliste
```

## Initialfokus

Priorität:

```text
1. Fortsetzen
2. Staffel 1 / erste verfügbare Staffel
3. erste Episode
```

## Fortsetzen-Anzeige

```text
Serie
Staffel
Episode
Position
```

## Episoden-Gesehen-Aktion

Nur die fokussierte Episode zeigt rechts eine fokussierbare Aktion:

```text
Als gesehen markieren
Als ungesehen markieren
```

Ab 95 Prozent oder beim tatsaechlichen Medienende gilt die Episode als abgeschlossen. Die Schwelle beendet die Wiedergabe nicht. `Als ungesehen markieren` loescht den gespeicherten Fortschritt. Komplette Staffeln und Serien besitzen keine entsprechende Aktion.

## Episoden-Fallbacks

Priorität:

```text
1. Thumbnail + Titel + Beschreibung
2. Thumbnail + Titel
3. Folge 1, Folge 2, Folge 3
```

## Automatische nächste Folge

Einstellung:

```text
Automatisch nächste Folge: Aus (Standard) | Ein
Countdown: 5 | 10 | 15 | 30 Sekunden (Standard 10)
```

Auto-Next Aus:

```text
Panel erscheint erst nach dem tatsächlichen Episodenende.
Hauptbutton: Nächste Folge abspielen
Ohne OK kein automatischer Start.
```

Auto-Next Ein:

```text
Panel erscheint X Sekunden vor dem tatsächlichen Episodenende.
Hauptbutton: Nächste Folge in X
OK startet sofort.
Ohne Eingabe startet die nächste Episode beim Ablauf am Episodenende.
```

Standardfokus liegt immer auf dem Hauptbutton. Der sichtbare Button `Zurück` erscheint in beiden Zuständen zeitgleich daneben. OK auf `Zurück` oder die Zurück-Taste verwirft einen laufenden Countdown und führt zur Serien-Detailseite mit dem zuvor gewählten Staffel-/Episodenkontext zurück. Einen Button `Abbrechen` gibt es nicht.

Nach der letzten Episode der Serie erscheint kein Panel.

Die feste 95-Prozent-Abschluss-Schwelle darf das Auto-Next-Panel nicht einblenden und keinen Episodenwechsel ausloesen.

Bei externen Playern gilt:

```text
kein automatischer Fortschritt
kein Abschlussstatus
kein Auto-Next-Panel
Hinweis nach Rueckkehr: Fortschritt konnte nicht automatisch ermittelt werden.
```

---

# Pattern 5: Search

## Zweck

Globale lokale Suche über Live-TV, Filme, Serien und EPG.

## Struktur

```text
Suchfeld + Mikrofon
Suchverlauf
Ergebnisbereiche
```

## Ergebnisbereiche

```text
Kanäle
Filme
Serien
EPG
```

## Initialfokus

```text
Suchfeld
```

## Verhalten

```text
Texteingabe -> 300 ms Debounce -> lokale Suche
Mikrofon + OK -> Android Sprachsuche
```

EPG-Ergebnisse erscheinen erst, wenn die fachliche Mindestlaenge erreicht ist.

Jede Ergebnisgruppe zeigt hoechstens die technisch gelieferten v1-Treffer. Eine `Alle anzeigen`-Aktion ist nicht verpflichtend.

## Suchverlauf

```text
maximal 20 Einträge
Eintrag löschen
Gesamten Verlauf löschen
```

## Keine Treffer

Anzeige:

```text
Keine Ergebnisse gefunden

Versuche:
- Andere Schreibweise
- Kürzeren Suchbegriff
- Teil des Titels
```

---

# Pattern 6: Settings Master Detail

## Zweck

Viele Einstellungen übersichtlich auf TV darstellen.

## Struktur

```text
Bereichsliste links | Detailoptionen rechts
```

## Bereiche

```text
Allgemein
Wiedergabelisten
EPG
Optik
Wiedergabe
Kindersicherung
Backup
Über die App
```

## Initialfokus

```text
zuletzt genutzter Bereich
```

Fallback:

```text
Allgemein
```

## Optionstypen

```text
Switch
Auswahl
Aktion
Info
Destruktive Aktion
```

## Dialogregeln

Destruktive Aktionen:

```text
Abbrechen zuerst fokussieren
```

Beispiele:

```text
Provider löschen
EPG Quelle löschen
Verlauf löschen
Cache leeren
Backup importieren, wenn aktuelle Daten ersetzt werden
```

---

# Pattern 7: Provider Management

## Zweck

M3U- und Xtream-Provider verwalten.

## Übersicht

Jeder Provider zeigt:

```text
Name
Typ
Live-TV Anzahl
Filme Anzahl
Serien Anzahl
Status
Letzte Aktualisierung optional
```

## Detail

Zeigt:

```text
Status
Ablaufdatum
Maximale Verbindungen
Letzte Aktualisierung
Live-TV Anzahl
Filme Anzahl
Serien Anzahl
```

## Aktionen

```text
Bearbeiten
Gruppen verwalten
Aktualisieren
Deaktivieren
Löschen
```

## Löschen

Dialog:

```text
Provider wirklich löschen?
Diese Aktion kann nicht rückgängig gemacht werden.

Abbrechen
Löschen
```

Standardfokus:

```text
Abbrechen
```

---

# Pattern 8: EPG Management

## Zweck

Mehrere EPG-Quellen verwalten und Providern zuordnen.

## EPG Quelle

Felder:

```text
Name
URL
Zeitversatz
Aktiviert
```

## Provider-Zuordnung

```text
EPG Quelle
Priorität
Manuelle Zuordnung optional
```

## Warnung beim Löschen

Wenn Quelle verwendet wird:

```text
Diese EPG Quelle wird noch verwendet.
```

Löschen bleibt möglich.

---

# Pattern 9: Player Fullscreen

## Zweck

Störungsfreie Wiedergabe.

## Standardzustand

```text
Vollbild ohne UI
```

## Overlay öffnen

```text
OK
```

## Overlay schließen

```text
Zurück
```

## Live-TV Overlay Inhalt

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
Audio
Untertitel
Bildformat
Mehr
```

## Film/Serie Overlay Inhalt

```text
Titel
Position
Dauer
Fortschritt
Timeline
Stream-Info-Badges
Audio
Untertitel
Bildformat
Mehr
```

Catch-Up nutzt den internen Player mit EPG-Kontext, wird aber nicht als VOD-Fortschritt oder Resume-Ziel behandelt.

## Fokus und Timeline

```text
Initialfokus beim Oeffnen -> Timeline
OK auf Timeline           -> Play/Pause
Links/Rechts auf Timeline -> Seek oder Timeshift-Bewegung
```

Pause, Vorspulen und Zurueckspulen werden nicht als separate Hauptbuttons angeboten.

Audio- und Untertitelauswahl im Player ist sitzungsbezogen und aendert keine globalen Wiedergabe-Einstellungen.

## Fehlerdialog

Aktionen:

```text
Erneut versuchen
Anderen Sender wählen
Schließen
```

---

# Pattern 10: Backup and Restore

## Zweck

Manuelle Sicherung und Wiederherstellung.

## Backup-Ziele

```text
Lokaler Speicher
SMB
Google Drive
```

## Backup enthält

```text
Provider
EPG Quellen
Favoriten
Verlauf
Playback Progress
Continue Watching
Einstellungen
Kindersicherung-Status nur als Restore-Hinweis
```

PIN-Pruefwerte, aktive PIN-Freigaben und sicherheitswirksame Kindersicherung-Schutzflags werden nach Restore nicht uebernommen. Nach Restore ist Kindersicherung deaktiviert.

## Restore-Warnung

Bei vollständigem Import:

```text
Backup importieren?
Die aktuelle Konfiguration wird ersetzt.
Kindersicherung wird nach Restore deaktiviert.

Abbrechen
Importieren
```

Standardfokus:

```text
Abbrechen
```

## Falsches Passwort

```text
Import abbrechen
```

---

# Pattern 11: Loading, Refresh and Background Work

## Appstart mit vorhandenen Daten

```text
Daten sofort anzeigen
Refresh im Hintergrund starten
Status dezent anzeigen
```

Nicht anzeigen:

```text
blockierendes Bitte warten...
```

## Refresh-Status

Möglich in Providerdetails und Settings:

```text
Aktualisierung läuft
Letzte Aktualisierung
Verbindungsfehler
```

## Große Jobs

Bei manuellen Wartungsaktionen:

```text
Progress Dialog oder Inline Status
Abbrechen nur, wenn technisch sicher möglich
```

---

# Pattern 12: Diagnostics

## Zweck

Fehleranalyse ohne sensible Daten offenzulegen.

## Standard

```text
Deaktiviert
```

## Diagnosemodus zeigt

```text
Refreshes
Fehler
Warnungen
Player Fehler
Netzwerkfehler
```

## Log Export

Vor Export entfernen:

```text
Passwörter
Tokens
URLs mit Zugangsdaten
```

---

# Screen Acceptance Checklist

Ein Screen ist designseitig freigegeben, wenn:

```text
D-Pad Navigation definiert
Initialfokus definiert
OK-Verhalten definiert
Zurück-Verhalten definiert
Empty State definiert
Error State definiert
Loading/Refresh State definiert, falls relevant
Fallbacks für fehlende Daten definiert
Schriftgröße Sehr groß berücksichtigt
Animationen Aus berücksichtigt
Provider-Isolation nicht verletzt
Keine Touch-only-Interaktion vorhanden
Lazy Rendering möglich
```
