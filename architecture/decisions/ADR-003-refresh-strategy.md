# ADR-003 - Refresh Strategy

## Status

Accepted

## Entscheidung

Refreshes laufen in folgender Reihenfolge:

1. Playlists sammeln
2. Playlists aktualisieren
3. benoetigte EPG-Quellen sammeln
4. doppelte EPG-Quellen entfernen
5. EPG-Quellen einmalig aktualisieren
6. EPG-Kanäle und EPG-Programme quellbezogen speichern
7. EPG-Mapping und Provider-EPG-Prioritaeten anwenden
8. EPG-Programmdaten ausserhalb der konfigurierten Aufbewahrung entfernen
9. Logos aktualisieren
10. Cache Cleanup

## Gruende

- Vermeidung doppelter Arbeit.
- Vorhersehbarer Ablauf.
- Gute Skalierbarkeit.
- Quellbezogene EPG-Daten vermeiden providerbezogene Programmkopien.

## Konsequenzen

- Playlists koennen parallel aktualisiert werden.
- EPG-Quellen werden pro Refresh-Zyklus nur einmal verarbeitet.
- EPG-Kanäle und EPG-Programme gehoeren zur EPG-Quelle.
- Provider-Sender erhalten EPG ueber Mapping und Prioritaet.
- Laufende Streams werden durch Refreshes nicht unterbrochen.
- Commit- und Rollback-Grenzen fuer Import und Refresh sind in `ADR-012-atomic-import-refresh.md` festgelegt.
