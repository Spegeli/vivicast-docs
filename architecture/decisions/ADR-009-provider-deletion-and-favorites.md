# ADR-009 – Provider Deletion and Favorites

## Status

Accepted

## Kontext

Vivicast unterstützt mehrere Provider. Provider können deaktiviert, umbenannt oder gelöscht werden.

Favoriten, Verlauf und Playback Progress sind providerbezogen.

## Entscheidung

Provider deaktivieren:

- Status wird auf deaktiviert gesetzt.
- Alle Daten bleiben erhalten.

Provider umbenennen:

- Nur der Anzeigename ändert sich.
- IDs und Zuordnungen bleiben unverändert.

Provider löschen:

- Sender, Kategorien, Filme, Serien, Staffeln, Episoden werden gelöscht.
- Favoriten, Playback Progress, Verlauf und providerbezogene EPG-Zuordnungen werden gelöscht.
- EPG-Quellen bleiben erhalten.

Favoriten werden über IDs gespeichert:

- providerId
- mediaId
- mediaType

Nicht über Namen.

Wenn Inhalte verschwinden, werden zugehörige Favoriten gelöscht.

## Gründe

- Keine verwaisten providerbezogenen Daten.
- EPG-Quellen können für neue Provider weiterverwendet werden.
- Favoriten bleiben bei Umbenennungen stabil.
- Provider-Isolation bleibt konsistent.

## Konsequenzen

- Löschen ist destruktiv und benötigt Sicherheitsabfrage.
- Deaktivieren ist die sichere Alternative zum Löschen.
- EPG-Quellen müssen separat verwaltet werden.
