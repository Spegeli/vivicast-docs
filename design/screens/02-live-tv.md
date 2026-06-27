# 02 - Live-TV

Status: verbindlich v6

## Zweck

Der Live-TV Screen dient zum schnellen Finden, Pruefen und Starten von Sendern.

Fachliche Anforderungen zu Providern, Kategorien, Sendern und EPG bleiben im PRD.

## Quellen

- `prd/PRD-v1/02-live-tv-requirements.md`
- `design/interaction/01-live-tv-adaptive-columns.md`
- `design/interaction/focus.md`
- `design/components/list-grid-items.md`

## Layout-Zonen

1. Top Navigation
2. Provider- und Kategorienbereich
3. Senderliste
4. Sender-EPG für den fokussierten Sender
5. Vorschau- und Detailbereich

## Startzustand

Beim frischen Öffnen liegt der Fokus auf der ersten Kategorie des ersten Providers.

Der erste Provider ist aufgeklappt.

Die Senderliste zeigt sofort die Sender dieser Kategorie.

## Globale Favoriten-Kategorie

Live-TV Favoriten sind anbieterübergreifend.

Die Favoriten-Kategorie wird als eigenstaendiger Punkt oberhalb des ersten Providers angezeigt.

Diese Kategorie ist nicht Teil eines Providers.

## Layout-Modi

Kategorie-Modus:

```text
Globale Favoriten / Provider / Kategorien | Senderliste | Vorschau/Details
```

Sender-Modus:

```text
Senderliste | Sender-EPG | Vorschau/Details
```

## Provider- und Kategorienbereich

Zeigt:

- globale Favoriten oberhalb des ersten Providers
- Providername
- geöffnet oder geschlossen
- Kategorien des aktiven Providers
- Nicht kategorisiert als Fallback-Kategorie

OK auf Provider klappt Provider ein oder aus.

Fokus auf Kategorie aktualisiert die Senderliste sofort.

## Senderliste

Jede Senderzeile zeigt:

- Sendernummer, falls vorhanden
- Senderlogo oder Fallback
- Sendername
- aktuelle Sendung, falls vorhanden
- Fortschritt der laufenden Sendung
- Favoritenstatus
- Catch-Up-Symbol, falls verfuegbar

Lange Sendernamen dürfen gekuerzt werden, müssen aber fokussiert lesbar bleiben.

## OK auf Sender

Der Ablauf ist fest und besitzt keine Preview-Einstellung.

Erstes OK in der Senderspalte:

- blendet Provider/Kategorien aus
- aktiviert den Sender-Modus
- zeigt zwischen Senderliste und Preview die EPG-Spalte für den gewählten Sender
- startet gleichzeitig rechts in der Preview-Spalte die Live-Vorschau
- setzt den Fokus auf die aktuelle Sendung in der EPG-Spalte, sofern vorhanden
- setzt den Fokus auf den No-EPG-Placeholder in der EPG-Spalte, wenn keine aktuelle EPG-Sendung vorhanden ist

Zweites OK auf der fokussierten aktuellen Sendung oeffnet die Vollbildwiedergabe des ausgewählten Senders.

OK auf dem fokussierten No-EPG-Placeholder oeffnet ebenfalls die Vollbildwiedergabe des ausgewählten Senders. Catch-Up bleibt in diesem Zustand deaktiviert.

## Sender-EPG

Die EPG-Spalte zeigt für den aktuell gewählten Sender:

- vergangene Sendungen, wenn relevant
- aktuelle Sendung
- kommende Sendungen
- Zeitbereich
- Catch-Up-Markierung
- klar markierten aktuellen Programmpunkt

Wenn kein EPG vorhanden ist, zeigt die Spalte einen No-EPG-Zustand. Dieser Zustand ist fokussierbar, wenn er nach dem ersten OK auf einen Sender als Fallbackziel benoetigt wird.

## Catch-Up

Catch-Up-Aktionen erscheinen nur für vergangene EPG-Programmpunkte, wenn Sender, Provider und EPG-Kontext Catch-Up erlauben.

Catch-Up startet im internen Vivicast-Player mit EPG-Kontext.

Aktuelle Sendungen werden über Live-TV oder Timeshift behandelt.

Catch-Up wird nicht an externe Player übergeben und erzeugt keinen VOD-Fortschritt.

## Vorschau- und Detailbereich

Zeigt:

- grosses Senderlogo oder Preview-Flaeche
- Sendername
- aktuelle Sendung mit Zeit
- Beschreibung, falls vorhanden
- nächste Sendung
- Streamstatus
- EPG-Hinweise

Ein separater EPG-Button ist nicht notwendig, da EPG im Sender-Modus sichtbar ist.

## Bedienung

- Links aus Senderliste im Kategorie-Modus fokussiert Provider/Kategorien.
- Fokus auf Kategorie aktualisiert die Senderliste sofort.
- OK in der Senderspalte startet Sender-Modus, EPG-Spalte und Preview und fokussiert die aktuelle EPG-Sendung, sofern vorhanden.
- Wenn keine aktuelle EPG-Sendung vorhanden ist, fokussiert OK in der Senderspalte den No-EPG-Placeholder.
- OK auf der fokussierten aktuellen EPG-Sendung startet Vollbild.
- OK auf dem fokussierten No-EPG-Placeholder startet Vollbild ohne Catch-Up-Kontext.
- Rechts aus der Senderliste fokussiert die EPG-Spalte.
- Rechts aus der EPG-Spalte oder aus dem No-EPG-Placeholder fokussiert Preview/Details.
- CH+ und CH- bewegen im Browser den Fokus in der Senderliste.
- CH+ und CH- wechseln im Player direkt den nächsten oder vorherigen Sender.
- Zurück geht stufenweise: EPG-Spalte, Senderliste, Provider/Kategorien, Top Navigation.

## Zustaende

Loading: Senderliste mit Skeleton-Zeilen.

Empty: Kategorie enthält keine Sender.

No EPG: Sender bleibt sichtbar, EPG-Bereich zeigt `Keine Programminformationen verfuegbar`. Im Sender-Modus ist dieser Zustand fokussierbar; OK startet Vollbild, Catch-Up bleibt nicht verfügbar.

No Logo: Fallback-Icon und Sendername anzeigen.

Stream Error: Fehlerhinweis im Detailbereich und Retry-Aktion.

Provider Error: Providerstatus sichtbar, andere Provider bleiben nutzbar.

## Komponenten

- Top Navigation
- Category List
- Global Favorites Item
- Channel Card
- EPG Row
- Details Panel
- Preview Surface
- Status Badge
- Favorite Indicator
- Progress Indicator
