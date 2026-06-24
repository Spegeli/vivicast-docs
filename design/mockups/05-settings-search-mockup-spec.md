# 05 – Settings and Search Mockup Specification

Status: Spezifikation v7

## Ziel

Diese Spezifikation beschreibt die visuellen Mockups für Suche und Einstellungen.

Beide Screens sind informationslastig und müssen trotz vieler Optionen ruhig und TV-tauglich bleiben.

## Referenz

```text
Wireframes:
- design/wireframes/04-search.md
- design/wireframes/05-settings.md
Design-System:
- design/design-system/
```

---

# Suche Mockup

## Komposition

```text
Top Bar
Suchfeld + Mikrofon
Suchverlauf oder Ergebnisbereiche
```

## Suchfeld

```text
groß
oben positioniert
klar fokussierbar
Placeholder: Suche...
```

Mikrofon:

```text
rechts neben Suchfeld
nur durch Fokus + OK aktivierbar
```

## Ergebnisgruppen

```text
Kanäle
Filme
Serien
EPG
```

Jede Gruppe als horizontale Reihe.

## Ergebnis-Card Stil

Kanäle:

```text
Logo/Icon
Sendername
Provider oder aktuelle Sendung
```

Filme/Serien:

```text
Poster klein
Titel
Jahr/Bewertung/Fortsetzen optional
```

EPG:

```text
Sendungstitel
Sender
Zeit
```

## Keine Treffer

Mockup soll zeigen:

```text
Keine Ergebnisse gefunden
Andere Schreibweise
Kürzeren Suchbegriff
Teil des Titels
```

## Fokus

Primärfokus für Ergebnis-Mockup:

```text
erster Treffer in Kanäle
```

Primärfokus für leeren Suchscreen:

```text
Suchfeld
```

---

# Einstellungen Mockup

## Komposition

```text
Top Bar
linke Bereichsliste
rechte Optionsliste
```

## Linke Bereichsliste

Bereiche:

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

Aktiver Bereich:

```text
Akzentlinie links
aufgehellte Fläche
```

Fokussierter Bereich:

```text
Fokusrahmen oder deutlichere Fläche
```

## Rechte Optionsliste

Options Row:

```text
Titel
Beschreibung optional
aktueller Wert rechts
```

Beispiele:

```text
Startbereich                    [Home]
Hintergrundaktualisierung       [Ein]
EPG-Aktualisierungsintervall    [24 Stunden]
Schriftgröße                    [Mittel]
Timeshift                       [Ein]
Maximale Timeshift-Dauer        [30 Minuten]
Timeshift-Speicher              [Automatisch]
Automatisch nächste Folge       [Aus]
Countdown nächste Folge         [10 Sekunden] {Deaktiviert}
```

Startbereich-Darstellung:

```text
Auswahlwerte: Home, Live-TV, Filme, Serien.
Home ist bei Erstinitialisierung ausgewaehlt.
Die Zeile weist darauf hin, dass Aenderungen ab dem naechsten regulaeren App-Start gelten.
```

EPG-Darstellung:

```text
Das globale Intervall zeigt bei Erstinitialisierung 24 Stunden.
App-Start, Playlist-Aenderung und manuelle Aktualisierung bleiben getrennte Ausloeser.
App-Start und Playlist-Aenderung starten jeweils mit Ein und sind gespeicherte DataStore-Optionen.
Die EPG-Aktualisierungshistorie ist nur Anzeige aus Refresh-Metadaten.
```

Timeshift-Darstellung:

```text
Timeshift ist standardmaessig aktiviert.
Dauerwerte: 15, 30, 60 und 120 Minuten.
Speicherwerte: Automatisch, RAM und Festplatte.
Bei Timeshift Aus bleiben Dauer und Speicher sichtbar, aber deaktiviert.
```

Auto-Next-Darstellung:

```text
Automatisch nächste Folge ist standardmaessig Aus.
Countdown-Werte: 5, 10, 15 und 30 Sekunden; Standard 10 Sekunden.
Bei Aus bleibt die Countdown-Zeile sichtbar, aber deaktiviert.
Bei Ein wird sie fokussierbar und aenderbar.
Eine Abschluss-Schwelle wird nicht als Option dargestellt; v1 verwendet fest 95 Prozent.
```

Wiedergabe-Wirkung:

```text
Audio- und Untertitel-Sprache werden beim Streamstart angewendet.
Manuelle Track-Auswahl im Player gilt nur fuer die aktuelle Wiedergabe.
Externer Player gilt nur fuer Filme und einzelne Episoden.
Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.
```

## Providerverwaltung Mockup

Soll enthalten:

```text
Provider A {Aktiv}
Provider B {Verbindungsfehler}
Provider C {Deaktiviert}
Provider hinzufügen Button
```

## Destruktiver Dialog Mockup

Provider löschen:

```text
Provider wirklich löschen?
Diese Aktion kann nicht rückgängig gemacht werden.
[Abbrechen] [Löschen]
```

Standardfokus:

```text
Abbrechen
```

---

# Visuelle Regeln

## Suche

```text
luftig
wenige Ergebnisreihen gleichzeitig
Suchfeld dominant
keine überladene Startseite
```

## Einstellungen

```text
Master-Detail klar sichtbar
Optionswerte rechts bündig
Beschreibungen kurz
keine Tabellenoptik
```

## Fokus

```text
bei Suche: Ergebnisfokus deutlich als Karte
bei Settings: Zeilenfokus deutlich als breite Fläche
```

---

# Akzeptanz für visuelle Mockups

```text
Suchfeld ist sofort als primärer Einstieg sichtbar
Mikrofon wirkt wie Aktion, nicht automatische Funktion
Ergebnisgruppen sind klar getrennt
Keine-Treffer-Zustand ist hilfreich
Settings-Bereiche links sind eindeutig
Optionswerte rechts sind schnell erfassbar
Timeshift zeigt Ein/Aus, maximale Dauer und Speicher getrennt
Abhaengige Timeshift-Optionen besitzen einen klaren deaktivierten Zustand
Providerstatus ist sichtbar
Destruktive Dialoge fokussieren Abbrechen
```
