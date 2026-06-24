# ADR-011 - Parser and Source Contracts

## Status

Accepted

## Entscheidung

Vivicast verwendet fuer M3U, Xtream Codes und XMLTV toleranten Teilimport.

Einzelne fehlerhafte Eintraege brechen einen Import nicht ab, solange die Quelle insgesamt lesbar ist und verwertbare Eintraege enthaelt.

Der normative Parser- und Quellenvertrag liegt in:

```text
prd/PRD-v1/12-parser-source-contracts.md
```

Parser duerfen Zugangswerte, Tokens, private URLs, HTTP-Header, Rohdaten, Provider-/Inhaltsnamen oder ungefilterte Playlist-/XMLTV-Inhalte nicht in Logs, Diagnoseexporte oder unverschluesselte Backups schreiben.

## Gruende

- IPTV-Quellen sind in der Praxis haeufig unvollstaendig oder uneinheitlich.
- Einzelne defekte Eintraege duerfen nicht den gesamten Import unbrauchbar machen.
- Parserfehler muessen nachvollziehbar sein, ohne sensible Daten offenzulegen.
- Stable Keys und EPG-Mapping brauchen verbindliche Quellenregeln.

## Konsequenzen

- Importstatus muss `Erfolgreich mit Teilfehlern` unterscheiden koennen.
- Parser liefern technische Zaehler und Fehlerkategorien statt Rohdaten.
- M3U-, Xtream- und XMLTV-Parser muessen grosse Quellen ohne vollstaendiges Laden in UI-State verarbeiten koennen.
- Die App muss alte Daten behalten, wenn ein kompletter Refresh fehlschlaegt.
