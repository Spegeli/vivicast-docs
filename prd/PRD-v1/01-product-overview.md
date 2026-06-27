# Vivicast – Product Requirements Document (PRD)
## Version 1.0
## Kapitel 1-3: Produktübersicht, Ziele, Plattform und Architekturgrundlagen

---

# 1. Produktübersicht

## Produktname

**Vivicast**

## Paketname

```text
com.vivicast.tv
```

## Produktbeschreibung

Vivicast ist eine moderne IPTV-Anwendung für Android TV.

Die App unterstuetzt:

- Home als festen Start- und Übersichtsbereich
- Live-TV
- Filme (Video on Demand)
- Serien (Video on Demand)
- Suche
- Einstellungen

Wiedergabequellen:

- M3U
- Xtream Codes

Vivicast wurde speziell für die Nutzung auf Android TV entwickelt und setzt den Fokus auf:

- fernbedienungsoptimierte Bedienung
- hohe Performance
- grosse IPTV-Bibliotheken
- mehrere Provider gleichzeitig
- umfangreiche Anpassbarkeit
- lokale Datenhaltung
- Android-TV-Integration

---

# 2. Projektziele

## Hauptziele

### Home

Home ist fester Bestandteil von Vivicast.

Es ist der Standard-Startscreen und bietet schnellen Zugriff auf Fortsetzen sowie zuletzt gesehene Live-TV-Sender.

Fortsetzen enthält Filme und Serien gemeinsam.

Live-TV erscheint nicht in Fortsetzen, sondern nur in zuletzt gesehenen Live-TV-Sendern.

Provider- und Update-Status werden nicht im Home-Bereich angezeigt, sondern in den Einstellungen.

Der Startbereich ist global unter `Einstellungen > Allgemein` einstellbar.

Zulässige Startbereiche sind Home, Live-TV, Filme und Serien. Standard ist Home.

Die Auswahl gilt beim nächsten regulaeren App-Start ohne explizites Ziel. App-Autostart verwendet denselben Wert; Deep Links und andere explizite Android-TV-Ziele haben Vorrang. Rueckkehr aus dem Hintergrund behält den aktuellen Bereich.

Suche und Einstellungen sind keine waehbaren Startbereiche. Fehlen im gewählten Bereich Inhalte, erscheint dessen normaler Empty State ohne automatischen Fallback auf Home.

### Live-TV

Benutzer sollen grosse Senderlisten schnell durchsuchen und Sender mit möglichst wenigen Tastendruecken erreichen können.

### Video-On-Demand

Filme und Serien sollen übersichtlich dargestellt und komfortabel durchsucht werden können.

### Mehrere Provider

Die App muss mehrere IPTV-Anbieter gleichzeitig verwalten können.

Provider bleiben technisch vollstaendig voneinander getrennt.

### EPG

Unterstuetzung mehrerer EPG-Quellen.

Priorisierung pro Provider.

Manuelle EPG-Zuordnung möglich.

### Performance

Die Anwendung muss auch bei sehr grossen Bibliotheken performant bleiben.

Zielgrößen:

```text
10.000+ Sender
50.000+ Filme
20.000+ Serien
Millionen EPG-Eintraege
```

### Android TV Optimierung

Die App wird primaer für Android TV entwickelt.

Die gesamte Bedienung erfolgt über:

```text
D-Pad
OK
Zurück
CH+
CH-
```

Hauptnavigation:

- dauerhaft oben sichtbar, ausser im Player
- Hauptbereiche: Home, Live-TV, Filme, Serien, Suche, Einstellungen
- Zurück springt auf Hauptscreens zuerst in die Top Navigation
- Beenden erfolgt per doppelter Zurück-Bestätigung aus der Top Navigation

Beim Wechsel zwischen Hauptbereichen wird der interne Fokus des verlassenen Bereichs nicht wiederhergestellt.

### Hohe Konfigurierbarkeit

Benutzer können unter anderem anpassen:

- Startbereich
- Farben
- Transparenz
- Animationen
- Cachegrößen
- Timeshift
- Decoder
- Audio
- Untertitel
- EPG-Verhalten
- Sortierungen

---

# 3. Nicht-Ziele

Folgende Funktionen gehoeren nicht zu Version 1.0.

## Mehrbenutzersystem

Nicht vorgesehen.

Die App verwendet genau ein Benutzerprofil.

## Externe Metadatenanbieter

Nicht vorgesehen.

Keine automatische Nutzung von:

```text
TMDB
IMDb
TVDB
OMDb
```

## Automatische Provider-Zusammenfuehrung

Nicht vorgesehen.

Gleiche Inhalte verschiedener Provider bleiben getrennt.

## Automatische Kategorienormalisierung

Nicht vorgesehen.

Provider-Kategorien werden unverändert übernommen.

## Cloud-Konto-System

Nicht vorgesehen.

Keine:

```text
Registrierung
Benutzerkonto
Login-System
Cloud-Profil
```

## Automatische Backups

Nicht vorgesehen.

Backups werden ausschliesslich manuell gestartet.

## Benutzerdefinierte Kategorien

Nicht Bestandteil von Version 1.0.

Für spätere Versionen geplant.

---

# Zielplattform

## Primaerplattform

```text
Android TV
```

## Unterstuetzte Geräte

Beispiele:

```text
Nvidia Shield
Google TV Streamer
Chromecast mit Google TV
Sony Android TV
Philips Android TV
TCL Android TV
Xiaomi TV Box
Formuler Geräte
```

## Sekundaerplattformen

Mögliche spätere Erweiterungen:

```text
Android Smartphone
Android Tablet
```

Nicht Bestandteil von Version 1.0.

---

# Zielgruppe

## IPTV-Nutzer

Benutzer mit:

```text
M3U Playlist
Xtream Codes Zugang
```

## Power User

Benutzer mit:

```text
Mehreren Providern
Mehreren EPG-Quellen
Grossen Bibliotheken
```
