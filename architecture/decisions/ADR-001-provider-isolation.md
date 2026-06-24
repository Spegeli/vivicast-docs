# ADR-001 – Provider Isolation

## Status
Accepted

## Entscheidung

Provider werden niemals automatisch zusammengeführt.

Gleiche Sender, Filme oder Serien bei unterschiedlichen Providern bleiben getrennte Datensätze.

## Gründe

- Provider bleiben unabhängig.
- Keine Datenkonflikte.
- Einfachere Architektur.
- Vorhersehbares Verhalten für den Benutzer.

## Konsequenzen

- Favoriten und Verlauf bleiben providerbezogen.
- Eindeutige IDs verwenden immer providerId + Objekt-ID.
