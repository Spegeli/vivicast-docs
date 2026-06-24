# 01 – Wireframe / Design-System Review

Status: abgeschlossen v5

Rolle: historische Review-Referenz. Diese Datei dokumentiert einen abgeschlossenen Abgleich und ist keine Produktanforderung, kein Phasenplan und keine Umsetzungsreihenfolge.

## Ziel

Diese Prüfung gleicht die vorhandenen Wireframes gegen PRD v1, ADRs und Design-System ab.

Geprüfte Quellen:

```text
prd/PRD-v1/
architecture/decisions/
design/design-system/
design/wireframes/
codex/
```

## Ergebnis

Die Wireframes sind konsistent mit PRD v1, den Architekturentscheidungen und dem Design-System.

Es wurden keine fachlichen Konflikte gefunden, die vor weiterer visueller Ausarbeitung blockieren.

## Geprüfte Kernanforderungen

| Bereich | Ergebnis | Bewertung |
|---|---:|---|
| Android TV first | erfüllt | D-Pad, OK, Zurück und CH+/CH- sind in Wireframes berücksichtigt. |
| Provider-Isolation | erfüllt | Live-TV, Favoriten, Providerverwaltung und Löschverhalten bleiben providerbezogen. |
| Live-TV Browser | erfüllt | Dreispaltenlayout entspricht PRD und Design-System. |
| Vorschauverhalten | erfüllt | Erstes OK startet festen Sender-Modus und Vorschau, Fokus springt auf die aktuelle EPG-Sendung, sofern vorhanden; zweites OK startet Vollbild. |
| VOD Übersicht | erfüllt | Hero-Info plus Poster-Raster ist abgebildet. |
| Serien-Fortsetzen | erfüllt | Staffel/Episode/Position werden sichtbar geführt. |
| Suche | erfüllt | Lokale Suche, Gruppen und Sprachsuche sind berücksichtigt. |
| Einstellungen | erfüllt | Master-Detail-Struktur bildet alle PRD-Bereiche ab. |
| Player | erfüllt | Vollbild ohne UI, OK-Overlay und BACK-Verhalten sind definiert. |
| Dialoge | erfüllt | Destruktive Dialoge fokussieren zuerst Abbrechen. |
| Empty States | erfüllt | Fehlende Inhalte werden kontrolliert dargestellt. |
| Error States | erfüllt | Fehler enthalten handhabbare Aktionen. |
| Lazy Rendering | erfüllt | Wireframes verlangen keine vollständigen Listen im UI-State. |
| Fehlende Metadaten | erfüllt | Logos, Poster, EPG und Beschreibungen haben Fallbacks. |
| Datenschutz/Sicherheit | erfüllt | Backup, Log Export und Zugangsdaten werden korrekt behandelt. |

---

# Detailprüfung

## 1. PRD-Kapitel 1 – Produktübersicht

Relevante Vorgaben:

```text
Android TV
D-Pad / OK / Zurück / CH+ / CH-
Live-TV
Filme
Serien
Mehrere Provider
Lokale Datenhaltung
Performance bei großen Bibliotheken
```

Wireframe-Abdeckung:

```text
01-live-tv-browser.md
02-movies.md
03-series.md
04-search.md
05-settings.md
06-player.md
07-dialogs-states.md
```

Bewertung:

```text
Erfüllt
```

## 2. PRD-Kapitel 2 – Live-TV

Geprüft:

```text
Provider/Kategorien/Sender/EPG Dreispaltenlayout
Provider ein-/ausklappen
Kategorie-Fokus aktualisiert Senderliste
Sender-Fokus aktualisiert EPG-Panel ohne Streamstart
Erstes OK startet Sender-Modus und Vorschau
Fokus springt auf aktuelle EPG-Sendung, sofern vorhanden
Zweites OK startet Vollbild
Keine Preview-Einstellung
Nur eine Vorschau gleichzeitig
CH+ / CH- sofortiger Senderwechsel
Catch-Up-Zugriff
Fehlerdialog nach Retries
```

Bewertung:

```text
Erfüllt
```

Hinweis:

Das Wireframe legt die Spaltenbreiten aus dem Design-System fest. Das ist fuer den historischen Review ausreichend, muss aber in der realen Implementierung responsive auf unterschiedliche TV-Aufloesungen angepasst werden.

## 3. PRD-Kapitel 3 – Filme und Serien

Geprüft Filme:

```text
Hero-Info
Kategorie-Chips
Poster-Raster
Poster-Fallback
Detailseite
Fortsetzen
Von Anfang an
Trailer
Favoriten
Gesehen-Status
Als gesehen markieren / Als ungesehen markieren
```

Geprüft Serien:

```text
Serienübersicht
Hero-Info
Detailseite
Fortsetzen mit Staffel/Folge/Position
Staffelauswahl
Episodenliste
Episoden-Fallbacks
Gesehen-Status und fokussierte Markierungsaktion pro Episode
Keine Markierungsaktion fuer Staffel oder Serie
Auto-Next Aus mit manuellem Folge-Button nach Episodenende
Auto-Next Ein mit dynamischem Countdown-Button vor Episodenende
Sichtbarer Zurueck-Button zeitgleich neben dem Folge-Button
Staffelwechsel und kein Panel nach Serienende
95-Prozent-Abschluss-Schwelle loest kein Auto-Next aus
```

Bewertung:

```text
Erfüllt
```

## 4. PRD-Kapitel 4 – Suche, Einstellungen, Player

### Suche

Geprüft:

```text
Suchfeld oben
Mikrofon-Aktion
Sprachsuche nur nach Fokus + OK
Live Search
300 ms Debounce
Suchverlauf mit 20 Einträgen
Ergebnisgruppen Kanäle/Filme/Serien/EPG
Keine Treffer Zustand
```

Bewertung:

```text
Erfüllt
```

### Einstellungen

Geprüft:

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

Bewertung:

```text
Erfüllt
```

### Player

Geprüft:

```text
Vollbild ohne UI
OK öffnet Overlay
BACK schließt Overlay
BACK ohne Overlay kehrt zum Ursprung zurück
Live-TV Overlay
VOD Overlay
Catch-Up Overlay
Timeshift
Auto-Next Panel mit stabilem Initialfokus, zwei Zustaenden und sichtbarem Zurueck-Button
Externer Player Modus
Fehlerdialoge
Audio/Untertitel
```

Bewertung:

```text
Erfüllt
```

## 5. PRD-Kapitel 5 – IPTV, EPG, Favoriten, Verlauf, Backup

Geprüft:

```text
Provider getrennt
Provider deaktivieren
Provider löschen mit Sicherheitsdialog
EPG-Quellen separat
EPG Quelle löschen mit Warnung
Favoriten über providerbezogene Logik
Fortsetzen getrennt für Filme und Serien
Backup manuell
Backup Import ersetzt Konfiguration
```

Bewertung:

```text
Erfüllt
```

## 6. PRD-Kapitel 6 – Datenmodell

Wireframes bilden keine Datenbankdetails ab, verletzen aber keine Modellvorgaben.

Geprüft:

```text
providerId-Bezug sichtbar
mediaType-Verhalten über Screens getrennt
Playback Progress sichtbar
Channel History / letzter Sender indirekt über Initialfokus
EPG Mapping über Settings/EPG Verwaltung vorgesehen
```

Bewertung:

```text
Erfüllt
```

## 7. PRD-Kapitel 7 – Hintergrundjobs und Performance

Geprüft:

```text
Kein blockierender Start bei vorhandenen Daten
Refresh im Hintergrund
Providerstatus Aktualisierung läuft
Alte Daten bleiben bei Fehler sichtbar
Lazy Rendering für Listen/Raster
Keine vollständige Bibliothek im UI-State
```

Bewertung:

```text
Erfüllt
```

## 8. PRD-Kapitel 8 – Android TV Integration

Geprüft:

```text
D-Pad Navigation
Sprachsuche
Globale Suche indirekt über Search Pattern vorbereitet
Deep Links indirekt über Ergebnis öffnen vorbereitet
Watch Next / Continue Watching im VOD-Konzept berücksichtigt
```

Bewertung:

```text
Erfüllt
```

Hinweis:

Deep-Link-Zielzustaende sollten bei betroffener Umsetzung explizit getestet werden:

```text
vivicast://movie/{id}
vivicast://series/{id}
vivicast://channel/{id}
```

## 9. PRD-Kapitel 9 – Sicherheit

Geprüft:

```text
Keine Klartextanzeige sensibler Zugangsdaten in Wireframes
Backup mit Passwort
Log Export bereinigt Passwörter/Tokens/URLs
Provider bearbeiten statt Zugangsdaten offen anzeigen
```

Bewertung:

```text
Erfüllt
```

---

# ADR-Abgleich

| ADR | Ergebnis |
|---|---|
| ADR-001 Provider Isolation | erfüllt |
| ADR-002 EPG Strategy | erfüllt |
| ADR-003 Refresh Strategy | erfüllt |
| ADR-004 Backup Strategy | erfüllt |
| ADR-005 Local Search | erfüllt |
| ADR-006 Timeshift Strategy | erfüllt |
| ADR-007 Trailer Strategy | erfüllt |
| ADR-008 Android TV Integration | erfüllt |
| ADR-009 Provider Deletion and Favorites | erfüllt |

---

# Design-System-Abgleich

## Foundations

Erfüllt:

```text
TV-first
Fokus vor Dekoration
Wenige Ebenen
Achsen klar verwenden
Inhalte zuerst
Degradation normalisieren
Performance sichtbar berücksichtigen
```

## Design Tokens

Erfüllt:

```text
Dunkles Theme
Fokusrahmen
große Typografie
TV-Safe-Area
Postergrößen
Live-TV-Spalten
Player Overlay Höhen
```

## Components

Erfüllt:

```text
Provider Tree
Channel List Item
EPG Preview Panel
Poster Card
Hero Info Panel
Detail Header
Buttons
Chips/Tabs
Search Field
Settings Row
Dialog
Player Overlay
Empty/Error States
```

## Focus Navigation

Erfüllt:

```text
Initialfokus je Screen
Fokus sichtbar
Fokus und Auswahl getrennt
D-Pad-Achsen definiert
Fokus-Restore berücksichtigt
Dialog-Fokus sicher
```

## Screen Patterns

Erfüllt:

```text
Live-TV Browser
VOD Overview
Movie Detail
Series Detail
Search
Settings Master Detail
Provider Management
EPG Management
Player Fullscreen
Backup and Restore
Loading/Refresh
Diagnostics
```

---

# Festgestellte Risiken

## Risiko 1: Live-TV Spaltenbreite auf kleinen 720p-Geräten

Bewertung:

```text
Nicht blockierend
```

Maßnahme:

Bei betroffener Umsetzung responsive Varianten testen:

```text
1080p Referenz
720p Mindestlayout
4K Skalierung
```

## Risiko 2: Sehr große Schrift im Settings-Screen

Bewertung:

```text
Nicht blockierend
```

Maßnahme:

Settings-Zeilen müssen bei `Sehr groß` zweizeilige Beschreibungen sauber umbrechen oder kürzen.

## Risiko 3: EPG-Suche kann große Ergebnismengen erzeugen

Bewertung:

```text
Nicht blockierend
```

Maßnahme:

Ergebnislimit pro Gruppe bei betroffener Umsetzung pruefen.

Empfehlung:

```text
maximal 20 sichtbare Treffer pro Gruppe initial
keine verpflichtende Mehr anzeigen- oder Alle anzeigen-Aktion
```

## Risiko 4: Player Overlay darf Untertitel nicht verdecken

Bewertung:

```text
Nicht blockierend
```

Maßnahme:

In Mockups und spaeterer UI-Umsetzung das Player Overlay so positionieren, dass zentrale Untertitelbereiche moeglichst frei bleiben.

---

# Historischer Review-Befund

Die Wireframes wurden designseitig als geeignete Grundlage bewertet fuer:

```text
1. visuelle Mockup-Spezifikation
2. visuelle UI-Validierung mit Demo-Daten
3. spaetere Codex-Planung betroffener Designsystem- und Feature-Screens
```

Status:

```text
Wireframe Review abgeschlossen
Keine blockierenden Konflikte gefunden
```

Diese Bewertung ist keine Vorgabe fuer die konkrete Umsetzungsreihenfolge im App-Repository.
