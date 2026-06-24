# Vivicast PRD v1
## Kapitel 4.1 - Live-TV Anforderungen

Status: bereinigt v5

---

# Ziel

Der Live-TV-Bereich ist die zentrale Funktion von Vivicast.

Benutzer muessen auch sehr grosse Senderlisten schnell durchsuchen, Sender starten und aktuelle Programminformationen erkennen koennen.

Die Bedienung muss vollstaendig fuer Android-TV-Fernbedienungen optimiert sein.

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

- Mehrere Provider muessen gleichzeitig unterstuetzt werden.
- Provider bleiben getrennt.
- Provider duerfen nicht automatisch zusammengefuehrt werden.
- Der Nutzer muss zwischen Providern wechseln koennen.
- Der erste Provider ist beim frischen Oeffnen sichtbar geoeffnet.

## Kategorien

- Provider-Kategorien werden unveraendert uebernommen.
- Kategorien werden nicht automatisch normalisiert oder zusammengefuehrt.
- Fokus auf eine Kategorie aktualisiert die Senderliste sofort.
- Sender ohne Kategorie werden intern `__UNCATEGORIZED__` zugeordnet.
- Anzeige fuer diese interne Kategorie: `Nicht kategorisiert`.

## Live-TV Favoriten

Live-TV Favoriten sind anbieteruebergreifend.

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

Kanalnummern werden vom Provider uebernommen.

Es erfolgt keine automatische Neuvergabe von Kanalnummern.

## EPG

Der Live-TV-Bereich muss aktuelle und folgende Programminformationen anzeigen koennen.

Mindestens unterstuetzt:

- aktuelle Sendung
- Startzeit
- Endzeit
- Beschreibung, falls vorhanden
- naechste Sendung

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
- zeigt die EPG-Spalte zum gewaehlten Sender
- startet gleichzeitig rechts die Live-Vorschau
- uebergibt den Fokus an die aktuelle Sendung in der EPG-Spalte, sofern vorhanden

Zweites OK auf der fokussierten aktuellen Sendung startet die Vollbildwiedergabe des ausgewaehlten Senders.

Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK in der Senderspalte.

Es darf immer nur eine aktive Vorschau oder Wiedergabe geben.

Neue Wiedergabe beendet die vorherige Wiedergabe.

## Kanalwechsel

Im Live-TV Browser bewegen CH+ und CH- den Fokus in der Senderliste.

Im Player starten CH+ und CH- direkt den naechsten oder vorherigen Sender.

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

Ohne EPG ist Catch-Up fuer den betroffenen Sender nicht verfuegbar.

Aktuelle Live-Sendungen werden ueber Live-TV beziehungsweise Timeshift behandelt und nicht als Catch-Up gestartet.

Catch-Up laeuft in v1 im internen Vivicast-Player. Eine Uebergabe an externe Player ist fuer Live-TV- oder Catch-Up-Kontexte nicht Teil von v1.

Catch-Up erzeugt keinen `PlaybackProgressEntity`-Datensatz und kein VOD-Resume-Ziel. Der Start bleibt an den ausgewaehlten EPG-Kontext gebunden.

## Fehlerbehandlung

Bei Senderstartfehlern:

- bis zu 5 Versuche
- danach Fehlerzustand anzeigen

Bei Streamabbruch:

- bis zu 5 Reconnect-Versuche
- danach Fehlerzustand anzeigen

Refreshes duerfen aktive Streams nicht unterbrechen.

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
