# ADR-002 - EPG Strategy

## Status

Accepted

## Entscheidung

EPG-Quellen sind unabhaengige globale Objekte.

Mehrere Provider koennen dieselbe EPG-Quelle nutzen.

Eine EPG-Quelle wird pro Refresh-Zyklus maximal einmal aktualisiert.

EPG-Daten werden quellbezogen gespeichert:

- EPG-Quelle
- EPG-Kanal
- EPG-Programm

Provider-Sender werden ueber EPG-Mapping und Provider-EPG-Prioritaeten mit EPG-Kanälen verbunden.

Manuelle EPG-Zuordnungen gewinnen immer vor automatischen Zuordnungen.

EPG-Programme werden nicht als providerbezogene Kopien gespeichert.

Die globale EPG-Aufbewahrung ist in v1 sichtbar konfigurierbar:

- Vergangenheit: 1 bis 14 Tage, Standard 1 Tag
- Zukunft: 1 bis 14 Tage, Standard 7 Tage

## Gruende

- Reduzierung von Netzwerkverkehr.
- Schnellere Aktualisierung.
- Weniger Speicher- und CPU-Verbrauch.
- Keine doppelten providerbezogenen EPG-Programmkopien.
- Nutzer koennen Catch-Up-/EPG-Rueckblick und Zukunftsdaten innerhalb klarer Grenzen steuern.

## Konsequenzen

- EPG-Quellen werden nicht automatisch geloescht, wenn ein Provider entfernt wird.
- EPG-Quellen koennen manuell verwaltet werden.
- Manuelle EPG-Zuordnung wird unterstuetzt.
- EPG-Kanäle und EPG-Programme gehoeren fachlich zur EPG-Quelle.
- Provider-Sender-Abfragen fuer Now/Next und Tagesansicht laufen ueber Mapping und Prioritaet.
- Cleanup entfernt nur EPG-Programmdaten ausserhalb des konfigurierten Fensters.
- EPG-Quellen, EPG-Kanäle, Provider-Zuordnungen und manuelle Mappings bleiben beim Cleanup erhalten.
