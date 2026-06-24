# ADR-007 – Trailer Strategy

## Status

Accepted

## Kontext

Xtream-Codes-Daten können Trailer-Informationen enthalten. Diese sind nicht garantiert vorhanden und nicht garantiert YouTube-kompatibel.

## Entscheidung

Trailer werden ausschließlich über die YouTube-App geöffnet.

Wenn eine Trailer-URL vorhanden ist, wird geprüft, ob es sich um eine gültige YouTube-URL handelt.

Gültige YouTube-Domains:

- youtube.com
- www.youtube.com
- youtu.be

Verhalten:

1. Gültige YouTube-URL vorhanden: YouTube-App direkt mit Trailer öffnen.
2. Keine URL vorhanden: YouTube-App mit Suchbegriff öffnen.
3. Nicht-YouTube-URL vorhanden: URL ignorieren und YouTube-Suche mit Titel öffnen.
4. YouTube-App fehlt: Hinweis anzeigen, dass die YouTube-App benötigt wird.

Suchbegriff:

```text
<Titel> Trailer
```

## Gründe

- Kein eigener Trailer-Player notwendig.
- Einheitliches Verhalten auf Android TV.
- Nicht vertrauenswürdige Trailer-URLs werden nicht direkt geöffnet.
- YouTube ist auf Android TV die erwartbare Trailer-Ziel-App.

## Konsequenzen

- Ohne YouTube-App können Trailer nicht abgespielt werden.
- Nicht-YouTube-URLs werden bewusst ignoriert.
- Trailer-Funktion ist abhängig von installierter YouTube-App.
