# ADR-005 - Local Search

## Status

Accepted

## Kontext

Vivicast muss grosse lokale IPTV-Bibliotheken schnell durchsuchen koennen.

Zielgroessen sind mindestens 10.000 Sender, 50.000 Filme, 20.000 Serien und mehrere Millionen EPG-Eintraege.

Die interne App-Suche umfasst Live-TV, Filme, Serien und EPG.

Android-TV-Systemsuche ist getrennt davon und folgt ADR-008.

## Entscheidung

Die interne Suche erfolgt vollstaendig lokal ueber Room.

Waehrend der Nutzung werden keine Suchanfragen an Provider gesendet.

Vivicast verwendet fuer v1 Room FTS4 als primaere Volltextsuchstrategie.

Es gibt getrennte FTS-Indexe fuer:

```text
ChannelSearchFts
MovieSearchFts
SeriesSearchFts
EPGProgramSearchFts
```

Die internen Ergebnisgruppen sind exakt:

```text
Kanäle
Filme
Serien
EPG
```

Episoden sind keine eigene Suchgruppe und kein eigenes Suchergebnis.

Live Search verwendet einen Debounce von 300 ms.

Der Suchverlauf speichert maximal 20 Eintraege und ist nicht konfigurierbar.

Query und Indextexte werden gleich normalisiert:

- trimmen
- Kleinschreibung
- Whitespace zusammenfassen
- Satzzeichen und Symbole als Trenner behandeln
- Umlaute und Diakritika tolerant behandeln
- `ae`, `oe`, `ue` und `ss` als Varianten beruecksichtigen

Prefix-Suche ist tokenbasiert. Reine Infix-Suche ist kein v1-Ziel.

EPG-Suche startet erst ab drei normalisierten Zeichen.

Jede Ergebnisgruppe liefert maximal 20 Treffer.

Ranking ist deterministisch:

1. exakter Titel- oder Name-Treffer
2. Titel- oder Name-Prefix
3. Token-Treffer im Titel oder Namen
4. Treffer in Metadaten

EPG-Ranking bevorzugt laufende Sendungen, danach nahe zukuenftige Sendungen. Vergangene Treffer werden niedriger bewertet.

FTS-Indexe werden aus produktiven Daten abgeleitet, nicht aus Staging.

Provider-FTS-Aenderungen committen im atomaren Provider-Commit.

EPG-FTS-Aenderungen committen im atomaren EPG-Quellen-Commit.

FTS-Indexe sind nicht Teil von Backups und werden nach Restore, Migration oder Rebuild aus produktiven Daten neu aufgebaut.

## Gruende

- FTS4 ist fuer grosse lokale Textmengen geeigneter als breite `LIKE`-Abfragen.
- Getrennte Indexe halten Ranking und Ergebnislimits je Gruppe kontrollierbar.
- EPG-Suche kann sehr grosse Datenmengen betreffen und braucht strengere Mindestlaengen.
- Lokale Suche vermeidet Provider-Latenz und Provider-Last.
- Abgeleitete FTS-Indexe koennen aus produktiven Daten neu aufgebaut werden.

## Konsequenzen

- Room-Schema und Migrationen muessen FTS4-Tabellen beruecksichtigen.
- Import, Refresh, Delete, Restore, Migration und EPG-Cleanup muessen FTS-Indexe aktualisieren oder Rebuilds ausloesen.
- Suchqualitaet haengt von lokal importierten und erfolgreich committed Daten ab.
- Geschuetzte Inhalte muessen vor Anzeige der Treffer durch das Schutzkonzept filterbar bleiben.
