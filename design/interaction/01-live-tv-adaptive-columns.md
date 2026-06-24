# 01 - Live-TV Adaptive Columns

Status: verbindlich v5

## Ziel

Der Live-TV Browser nutzt ein adaptives Spaltenmodell.

Dieses Modell reduziert Klicks und zeigt das EPG des gewaehlten Senders direkt an.

## Startzustand

Beim frischen Oeffnen liegt der Fokus auf der ersten Kategorie des ersten Providers.

Der erste Provider ist aufgeklappt.

Die globale Favoriten-Kategorie steht oberhalb des ersten Providers.

## Modi

Kategorie-Modus:

```text
Globale Favoriten / Provider / Kategorien | Senderliste | Vorschau/Details
```

Sender-Modus:

```text
Senderliste | Sender-EPG | Vorschau/Details
```

## Fokusregeln

```text
Initialfokus liegt auf erster Kategorie des ersten Providers
Globale Favoriten stehen oberhalb des ersten Providers
Fokus auf Kategorie aktualisiert Senderliste sofort
Links aus Senderliste im Kategorie-Modus fokussiert Provider/Kategorien
OK in der Senderspalte aktiviert Sender-Modus und startet gleichzeitig Preview rechts
Nach dem ersten OK springt der Fokus auf die aktuelle Sendung im Sender-EPG, sofern vorhanden
OK auf der fokussierten aktuellen Sendung oeffnet Vollbildwiedergabe
Rechts aus Senderliste fokussiert Sender-EPG
Rechts aus Sender-EPG fokussiert Preview/Details
Zurueck geht stufenweise zurueck
```

## Zurueck-Kette

```text
EPG-Spalte
-> Senderliste
-> Provider/Kategorien
-> Top Navigation
```

## EPG-Spalte

Die mittlere EPG-Spalte im Sender-Modus zeigt:

```text
vergangene Sendungen
aktuelle Sendung
kommende Sendungen
Catch-Up Kennzeichnung, falls verfuegbar
aktueller Programmpunkt klar markiert
```

## Rechte Preview/Details-Spalte

Die rechte Spalte zeigt:

```text
Senderlogo oder Fallback
Sendername
aktuelles Programm
Kurzbeschreibung
naechste Sendung
Preview-Flaeche
Aktionen ohne separaten EPG-Button
```

## Preview-Verhalten

Der Ablauf ist fest und nicht konfigurierbar.

Beim blossen Senderfokus wird keine Videovorschau gestartet.

Erstes OK in der Senderspalte blendet Provider/Kategorien aus, oeffnet den Sender-Modus mit EPG-Spalte und startet gleichzeitig die Preview im rechten Bereich.

Direkt danach erhaelt die aktuelle Sendung in der EPG-Spalte den Fokus, sofern vorhanden.

OK auf dieser aktuellen Sendung oeffnet die Vollbildwiedergabe des ausgewaehlten Senders.

Es gibt keine Preview-Einstellung und keinen direkten Vollbildstart beim ersten OK in der Senderspalte.

## CH-Tasten

Im Live-TV Browser:

```text
CH+ / CH- bewegt Fokus in der Senderliste
```

Im Player:

```text
CH+ / CH- startet direkt naechsten oder vorherigen Sender
```

## Lazy- und Performance-Regeln

```text
Senderliste lazy rendern
EPG-Zeilen lazy rendern
EPG nur fuer gewaehlten Sender laden bzw. anzeigen
keine komplette Provider-Bibliothek in UI-State laden
Fokuswechsel darf nicht blockieren
```
