# Vivicast PRD v1
## Kapitel 4.1 - Live-TV Anforderungen

Status: bereinigt v5

---

# Ziel

Der Live-TV-Bereich ist die zentrale Funktion von Vivicast.

Benutzer müssen auch sehr grosse Senderlisten schnell durchsuchen, Sender starten und aktuelle Programminformationen erkennen können.

Die Bedienung muss vollstaendig für Android-TV-Fernbedienungen optimiert sein.

---

# Verbindliche Designquellen

Konkretes Layout, Spaltenmodell, Fokusverhalten und UI-Zustaende sind nicht in dieser PRD-Datei definiert.

Verbindliche Quellen:

- `design/screens/02-live-tv.md`
- `design/interaction/01-live-tv-adaptive-columns.md`
- `design/interaction/focus.md`
- `design/components/list-grid-items.md`

---

# Fachliche Anforderungen

## Provider

- Mehrere Provider müssen gleichzeitig unterstuetzt werden.
- Provider bleiben getrennt.
- Provider dürfen nicht automatisch zusammengefuehrt werden.
- Der Nutzer muss zwischen Providern wechseln können.
- Der erste Provider ist beim frischen Öffnen sichtbar geoeffnet.

## Kategorien

- Provider-Kategorien werden unverändert übernommen.
- Kategorien werden nicht automatisch normalisiert oder zusammengefuehrt.
- Fokus auf eine Kategorie aktualisiert die Senderliste sofort.
- Sender ohne Kategorie werden intern `__UNCATEGORIZED__` zugeordnet.
- Anzeige für diese interne Kategorie: `Nicht kategorisiert`.

## Live-TV Favoriten

Live-TV Favoriten sind anbieterübergreifend.

Sie erscheinen als eigene globale Kategorie oberhalb des ersten Providers.

Sie sind nicht Teil eines einzelnen Providers.

## Senderliste

Jeder Sender muss fachlich folgende Informationen unterstuetzen:

- Sendernummer, falls vom Provider vorhanden
- Sendername
- Senderlogo oder Fallback
- aktuelle Sendung, falls EPG vorhanden
- Fortschritt der laufenden Sendung, falls EPG vorhanden
- Favoritenstatus
- Catch-Up-Status, falls verfuegbar

Kanalnummern werden vom Provider übernommen.

Es erfolgt keine automatische Neuvergabe von Kanalnummern.

## EPG

Der Live-TV-Bereich muss aktuelle und folgende Programminformationen anzeigen können.

Mindestens unterstuetzt:

- aktuelle Sendung
- Startzeit
- Endzeit
- Beschreibung, falls vorhanden
- nächste Sendung

Wenn kein EPG vorhanden ist, bleibt der Sender sichtbar.

Anzeigehinweis:

```text
Keine Programminformationen verfuegbar
```

## Vorschau und Wiedergabe

Beim blossen Fokussieren eines Senders wird kein Stream gestartet.

Der Startablauf ist fest und nicht konfigurierbar.

Erstes OK in der Senderspalte:

- aktiviert den Sender-Modus
- zeigt die EPG-Spalte zum gewählten Sender
- startet gleichzeitig rechts die Live-Vorschau
- übergibt den Fokus an die aktuelle Sendung in der EPG-Spalte, sofern vorhanden
- übergibt den Fokus an den No-EPG-Placeholder in der EPG-Spalte, wenn keine aktuelle EPG-Sendung vorhanden ist

Zweites OK auf der fokussierten aktuellen Sendung startet die Vollbildwiedergabe des ausgewählten Senders.

Wenn keine aktuelle EPG-Sendung vorhanden ist, erhält der No-EPG-Zustand in der EPG-Spalte den Fokus. OK auf diesen Zustand startet die Vollbildwiedergabe des ausgewählten Senders. Catch-Up ist in diesem Zustand nicht verfügbar.

Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK in der Senderspalte.

Es darf immer nur eine aktive Vorschau oder Wiedergabe geben.

Neue Wiedergabe beendet die vorherige Wiedergabe.

## Kanalwechsel

Im Live-TV Browser bewegen CH+ und CH- den Fokus in der Senderliste.

Im Player starten CH+ und CH- direkt den nächsten oder vorherigen Sender.

Bei schnellem Kanalwechsel gilt:

- vorherige Startvorgaenge werden abgebrochen
- nur die letzte Auswahl wird gestartet

## Catch-Up

Catch-Up wird unterstuetzt, wenn Provider und EPG es anbieten.

Voraussetzungen:

- Sender unterstuetzt Catch-Up
- passende EPG-Daten sind vorhanden
- der EPG-Programmpunkt besitzt verwertbare Start- und Endzeit
- der Programmpunkt liegt im erlaubten Rueckblickfenster des Providers und der lokalen EPG-Aufbewahrung

Ohne EPG ist Catch-Up für den betroffenen Sender nicht verfuegbar.

Aktuelle Live-Sendungen werden über Live-TV beziehungsweise Timeshift behandelt und nicht als Catch-Up gestartet.

Catch-Up laeuft in v1 im internen Vivicast-Player. Eine Übergabe an externe Player ist für Live-TV- oder Catch-Up-Kontexte nicht Teil von v1.

Catch-Up erzeugt keinen `PlaybackProgressEntity`-Datensatz und kein VOD-Resume-Ziel. Der Start bleibt an den ausgewählten EPG-Kontext gebunden.

## Fehlerbehandlung

Bei Senderstartfehlern:

- maximal 5 Versuche insgesamt inklusive Erstversuch
- ein Versuch gilt als fehlgeschlagen, wenn ExoPlayer einen Fehler meldet oder innerhalb von 10 Sekunden kein abspielbarer Zustand erreicht wird
- Retry-Abstaende: 0,5 s, 1 s, 2 s, 4 s
- danach Fehlerzustand anzeigen

Bei Streamabbruch:

- Reconnect nur fuer den zuletzt aktiven Sender
- maximal 5 Versuche insgesamt inklusive Erstversuch
- waehrend Reconnect nicht blockierenden Reconnect-Hinweis anzeigen
- danach Fehlerzustand anzeigen

Manuelles `Erneut versuchen` setzt den Zaehler zurueck. Senderwechsel bricht laufende Start- oder Retry-Vorgaenge ab.

Refreshes dürfen aktive Streams nicht unterbrechen.

---

# Abgrenzung

Diese Datei definiert fachliche Live-TV-Anforderungen.

Nicht hier definiert:

- Spaltenlayout
- Fokuspfade
- Preview-Flaechen
- Card-Design
- konkrete Screen-Zustaende
- visuelle Darstellung

Diese Details liegen in den Designquellen.
