# ADR-010 - Stable Identities and Restore Keys

## Status

Accepted

## Kontext

Vivicast aktualisiert Providerdaten wiederholt und stellt nutzerbezogene Daten aus Backups wieder her.

Lokale Room-IDs sind fuer diese Vorgaenge ungeeignet, weil sie bei Restore, Migration oder Neuimport neu entstehen koennen.

## Entscheidung

Vivicast unterscheidet drei ID-Ebenen:

- `id`: lokale Room-ID, nur innerhalb der aktuellen Datenbank gueltig
- `stableKey`: fachlich stabile App-ID fuer Refresh, Backup, Restore und nutzerbezogene Referenzen
- `remoteId`: Provider-ID, falls vorhanden

Provider besitzen einen unveraenderlichen `providerStableKey`, der im Backup exportiert und beim Restore erhalten bleibt.

Xtream Codes verwendet vorhandene Provider-IDs bevorzugt fuer Stable Keys:

- Kategorien: `category_id`
- Sender und Filme: `stream_id`
- Serien: `series_id`
- Staffeln: `series_id + seasonNumber`
- Episoden: `episode_id`, falls vorhanden; sonst `series_id + seasonNumber + episodeNumber`

M3U erzeugt Stable Keys deterministisch aus nicht geheimen normalisierten Attributen wie Medientyp, `tvg-id`, Gruppe, Name, Kanalnummer und geheimnisfrei gehashter Stream-Identitaet.

Zugangswerte, Tokens und private URLs duerfen nicht als Klartextbestandteil von Stable Keys verwendet werden.

Backups verwenden keine lokalen Room-IDs als Identitaet. Favoriten, Live-TV-Verlauf und Wiedergabefortschritt referenzieren Inhalte ueber `providerStableKey + mediaType + mediaStableKey`.

Manuelle EPG-Mappings referenzieren Sender, EPG-Quelle und EPG-Kanal ueber `providerStableKey + channelStableKey + epgSourceStableKey + epgChannelStableKey`.

Nach Restore duerfen diese Referenzen pending sein, bis ein erfolgreicher Provider-Refresh sie wieder mit lokalen Entities verbindet.

## Gruende

- Refresh kann bestehende Inhalte deterministisch wiedererkennen.
- Restore ist unabhaengig von lokalen Room-IDs.
- Standard-Backups muessen keine vollstaendige IPTV-Bibliothek enthalten.
- Provider-Isolation bleibt erhalten.
- Geheime Quellenwerte gelangen nicht in Backup-Identitaeten oder Logs.

## Konsequenzen

- Entities mit Providerbezug brauchen `stableKey` zusaetzlich zu `id` und optionaler `remoteId`.
- Nutzerbezogene Daten brauchen einen stabilen Backup-/Restore-Schluessel neben der lokalen Entity-Referenz.
- Manuelle EPG-Zuordnungen brauchen stabile Sender-, EPG-Quellen- und EPG-Kanal-Schluessel neben lokalen Foreign Keys.
- Pending Referenzen muessen im UI und in Queries kontrolliert behandelt werden.
- Ein erfolgreicher Provider-Refresh darf nicht mehr vorhandene Inhalte samt zugehoerigen Favoriten, Verlaeufen und Fortschritten entfernen.
- Restore-Merge ist nicht Teil von v1; Backup-Schema-Migration bleibt davon getrennt.
