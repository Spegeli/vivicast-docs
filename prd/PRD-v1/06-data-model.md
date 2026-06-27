# Vivicast PRD v1
## Kapitel 6 - Datenmodell, Speicherung und Sicherheit

Status: bereinigt v16

---

# 6. Ziel

Vivicast verwendet lokale Datenhaltung.

Lokale Datenhaltung dient:

- schneller Navigation
- Offline-Nutzung bereits geladener Daten
- schneller Suche
- geringer Netzwerklast
- hoher Performance
- konsistenter TV-Bedienung auch bei grossen IPTV-Bibliotheken

---

# 6.1 Speichersysteme

Vivicast verwendet vier Speicherrollen:

```text
Room
DataStore
Android Keystore
Geschuetzter Secret Store
```

## Room

Room ist verantwortlich für strukturierte lokale App-Daten und grosse Datenmengen.

Room speichert:

- Provider-Metadaten
- Kategorien
- Sender
- Filme
- Serien
- Staffeln
- Episoden
- EPG-Quellen-Metadaten
- EPG-Kanäle
- EPG-Programme
- EPG-Zuordnungen
- Favoriten
- Kanalverlauf
- Suchverlauf
- Wiedergabefortschritt
- lokale Anzeige- und Sortierdaten mit Datenbankbezug

Room speichert keine geheimen Zugangswerte.

Listen-DAOs fuer Sender, Filme und Serien müssen paging- oder limit/offset-faehige Abfragen bereitstellen. Vollständige Provider-Bibliotheken dürfen nicht als UI-State materialisiert werden.

EPG-DAO-Abfragen fuer Tagesansichten müssen sender-, quellen-, zeitfenster- und mappingbezogen eingegrenzt werden. Vollstaendige EPG-Quellen dürfen nicht fuer eine einzelne UI-Ansicht materialisiert werden.

## DataStore

DataStore ist verantwortlich für kleine App-Einstellungen.

DataStore speichert:

- Allgemein-Einstellungen
- Optik-Einstellungen
- Wiedergabe-Einstellungen
- Diagnose-Einstellungen
- Backup-Einstellungen
- lokale Kindersicherung-Schutzflags ohne PIN-Klartext
- einfache UI-Präferenzen

DataStore speichert keine grossen Listen, keine IPTV-Bibliotheken und keine geheimen Zugangswerte.

## Android Keystore

Android Keystore ist verantwortlich für nicht exportierbare kryptografische Schluessel.

## Geschuetzter Secret Store

Der geschuetzte Secret Store ist verantwortlich für geheime oder private Werte. Er liegt im privaten App-Speicher und wird mit einem Android-Keystore-gebundenen Schluessel verschlüsselt.

Dazu gehoeren:

- Xtream-Codes-Benutzername
- Xtream-Codes-Zugangswert
- private M3U-URLs mit eingebetteten Zugangswerten
- private EPG-URLs mit eingebetteten Zugangswerten
- private Stream-URL-Templates mit eingebetteten Zugangswerten
- HTTP-Header und Cookies
- SMB-Anmeldedaten
- Cloud-Anmeldedaten
- PIN-Pruefwerte

Neue geschuetzte Speicherung wird nicht auf `EncryptedSharedPreferences` oder Jetpack `security-crypto` aufgebaut.

Normale nicht-geheime Server- oder Quellenadressen dürfen als Konfiguration gespeichert werden, wenn sie für die Funktion notwendig sind.

Private Quellenadressen mit eingebetteten Zugangswerten müssen wie geheime Werte behandelt werden.

---

# 6.1a Datenklassifizierung

Vivicast unterscheidet vier Schutzklassen:

```text
Normal
Sensibel
Geheim
Sicherheitswirksam lokal
```

`Normal` sind nicht geheime App-Einstellungen, Layout-/Optikwerte, nicht private Provider-Metadaten und normale Quellenadressen ohne Zugangswerte.

`Sensibel` sind nutzerbezogene Daten wie Favoriten, Live-TV-Verlauf, Suchverlauf, Wiedergabefortschritt und EPG-Mappings.

`Geheim` sind Xtream-Benutzername und Zugangswert, SMB-Zugangsdaten, Cloud-Token, private M3U-/EPG-/Stream-URLs mit Tokens oder eingebetteten Zugangswerten, HTTP-Header, Cookies, Backup-Passphrase und PIN-Pruefwerte.

`Sicherheitswirksam lokal` sind PIN-Pruefwerte, PIN-Fehlversuchs- und Sperrstatus, aktive PIN-Freigaben und Kindersicherung-Schutzflags. Diese Werte sind kein Restore-Ziel und werden nie aus Backups übernommen.

Wenn Keystore-Schluessel oder Secret Store verloren, beschädigt oder nicht entschluesselbar sind, bleiben Provider und EPG-Quellen als normale Konfiguration erhalten. Quellen mit benoetigten Geheimnissen werden als `Zugangsdaten erforderlich` markiert. PIN und Kindersicherung werden deaktiviert und müssen bei Bedarf neu eingerichtet werden.

---

# 6.2 ProviderEntity

```text
ProviderEntity

id
stableKey
name
type
sourceConfigKey
isActive
status
includeLiveTv
includeMovies
includeSeries
refreshIntervalHours
logoPriority
createdAt
updatedAt
```

## Name

Name ist Pflichtfeld.

Der Name muss eindeutig sein.

## Typ

Unterstuetzte Werte:

```text
M3U
XTREAM_CODES
```

Der Typ kann nach dem Speichern nicht geändert werden.

## Sicherheit

Nicht in Room speichern:

- Xtream-Codes-Benutzername
- Xtream-Codes-Zugangswert
- private M3U-URLs mit eingebetteten Zugangswerten

Diese Werte werden über `sourceConfigKey` referenziert und verschlüsselt gespeichert.

Normale nicht-geheime Serveradresse oder Quellenadresse darf als Konfigurationswert gespeichert werden, wenn sie keinen eingebetteten Zugangswert enthält.

---

# 6.2a Identitaetsstrategie

Vivicast unterscheidet drei ID-Ebenen:

```text
id
stableKey
remoteId
```

`id` ist die lokale Room-ID. Sie ist nur innerhalb der aktuellen lokalen Datenbank stabil und darf nicht als Backup-, Restore- oder Provider-Identitaet verwendet werden.

`stableKey` ist die fachlich stabile App-ID pro Provider und Entity. Sie wird für Refresh, Restore, Backup-Referenzen, Favoriten, Verlauf und Wiedergabefortschritt verwendet.

`remoteId` ist eine vom Provider gelieferte ID, sofern vorhanden. Sie darf für `stableKey` verwendet werden, ist aber nicht alleiniger App-Schluessel.

Provider erhalten einen unveraenderlichen `providerStableKey`. In `ProviderEntity` wird dieser Wert im Feld `stableKey` gespeichert. Dieser Schluessel wird im Backup exportiert und beim Restore erhalten. Provider werden auch mit gleichem Namen oder gleicher Quelle nicht automatisch zusammengefuehrt.

Lokale Room-IDs werden in Backups nicht als Identitaet verwendet.

## Xtream-Codes-Stable-Keys

Xtream Codes nutzt Provider-IDs bevorzugt:

- Kategorien: `category_id`
- Sender und Filme: `stream_id`
- Serien: `series_id`
- Staffeln: `series_id + seasonNumber`
- Episoden: `episode_id`, falls vorhanden; sonst `series_id + seasonNumber + episodeNumber`

## M3U-Stable-Keys

M3U besitzt keine verlaessliche Pflicht-ID. Vivicast erzeugt deterministische, nicht geheime `stableKey`-Werte aus normalisierten Attributen:

- Medientyp
- `tvg-id`, sofern vorhanden
- Gruppe beziehungsweise `__UNCATEGORIZED__`
- Name
- Kanalnummer, sofern vorhanden
- geheimnisfrei gehashte Stream-Identitaet

Zugangswerte, Tokens, private URLs und andere geheime Klartextdaten dürfen nicht Bestandteil eines sichtbaren oder exportierten Stable Keys sein.

## Pending-Referenzen nach Restore

Standard-Backups enthalten keine vollstaendige IPTV-Bibliothek. Favoriten, Live-TV-Verlauf und Wiedergabefortschritt dürfen nach Restore zunaechst als pending Referenzen vorliegen.

Pending Referenzen werden nach einem erfolgreichen Provider-Refresh über `providerStableKey + mediaType + mediaStableKey` mit lokalen Entities verbunden.

Wenn ein Provider wegen fehlender Zugangsdaten, Netzwerkfehlern, ungültiger Quelle, Abbruch oder nicht autoritativem Teilbereich nicht aktualisiert werden kann, bleiben pending Referenzen erhalten.

Wenn ein erfolgreicher autoritativer Refresh den referenzierten Inhalt nicht mehr enthält, werden die zugehoerigen Favoriten, Verlaeufe oder Fortschrittsdaten gemaess Entfernte-Inhalte-Regel geloescht.

## Import-/Refresh-Staging

Import- und Refresh-Staging-Daten sind technische Zwischendaten vor dem produktiven Commit.

Sie dürfen als temporaere Dateien, In-Memory-Strukturen oder dedizierte Staging-Tabellen umgesetzt werden.

Staging-Daten dürfen nicht als produktive App-Daten angezeigt, gesichert oder für Nutzerreferenzen verwendet werden.

Staging verwendet fachlich stabile Schluessel für Diff und Zuordnung. Lokale Room-IDs dürfen erst beim produktiven Commit erstellt oder aktualisiert werden.

Verwaiste Staging-Daten müssen über einen Laufkontext wie `refreshRunId` oder eine gleichwertige technische Zuordnung bereinigt werden können.

---

# 6.3 CategoryEntity

```text
CategoryEntity

id
providerId
stableKey
type
remoteId
name
sortOrder
isHidden
createdAt
updatedAt
```

## Kategorien

Unterstuetzt:

```text
LIVE_TV
MOVIE
SERIES
```

## Ausblenden und Sortieren

Benutzer können Kategorien ausblenden und sortieren.

Diese Werte sind providergebunden.

---

# 6.4 ChannelEntity

```text
ChannelEntity

id
providerId
categoryId
stableKey
remoteId
channelNumber
name
logoUrl
isCatchupAvailable
catchupDays
createdAt
updatedAt
```

## Senderlogos

Wenn kein Logo vorhanden ist, wird ein TV-Standard-Icon angezeigt.

## Catch-Up

Catch-Up ist senderbezogen, nicht kategoriebasiert.

---

# 6.5 MovieEntity

```text
MovieEntity

id
providerId
categoryId
stableKey
remoteId
name
originalName
containerExtension
posterUrl
backdropUrl
rating
year
genre
duration
director
cast
plot
trailerUrl
ageRating
isAdult
addedAt
createdAt
updatedAt
```

## Stream URLs

Stream-URLs werden nicht dauerhaft gespeichert.

Sie werden dynamisch erzeugt oder aus geschuetzten Quelleninformationen abgeleitet.

## Jugendschutz

`ageRating` und `isAdult` dienen dem FSK-18-Schutz.

Fehlende Altersdaten bedeuten nicht automatisch 18+.

---

# 6.6 SeriesEntity

```text
SeriesEntity

id
providerId
categoryId
stableKey
remoteId
name
originalName
posterUrl
backdropUrl
rating
year
genre
director
cast
plot
ageRating
isAdult
addedAt
createdAt
updatedAt
```

## Jugendschutz

`ageRating` und `isAdult` dienen dem FSK-18-Schutz.

Fehlende Altersdaten bedeuten nicht automatisch 18+.

---

# 6.7 SeasonEntity

```text
SeasonEntity

id
providerId
seriesId
stableKey
seasonNumber
name
posterUrl
createdAt
updatedAt
```

Auch bei unvollstaendigen Providerdaten werden Staffeln erzeugt, wenn Episoden sinnvoll gruppiert werden können.

---

# 6.8 EpisodeEntity

```text
EpisodeEntity

id
providerId
seriesId
seasonId
stableKey
remoteId
episodeNumber
seasonNumber
name
plot
thumbnailUrl
containerExtension
duration
airDate
ageRating
isAdult
createdAt
updatedAt
```

## Episodenbilder

Episodenbilder sind optional.

Falls vorhanden, werden sie verwendet.

## Jugendschutz

`ageRating` und `isAdult` dienen dem FSK-18-Schutz.

Fehlende Altersdaten bedeuten nicht automatisch 18+.

---

# 6.9 EPGSourceEntity

```text
EPGSourceEntity

id
stableKey
name
sourceUrl
sourceConfigKey
timeShiftMinutes
isActive
lastRefreshAt
lastProgramCount
createdAt
updatedAt
```

## Sicherheit

`sourceUrl` darf nur verwendet werden, wenn die URL keine eingebetteten Zugangswerte enthält.

Private EPG-URLs mit eingebetteten Zugangswerten werden über `sourceConfigKey` verschlüsselt referenziert.

`stableKey` identifiziert die EPG-Quelle für Backup, Restore und Provider-Zuordnungen. Er darf keine geheimen URL-Bestandteile, Tokens oder Zugangswerte enthalten.

---

# 6.10 EPGChannelEntity

```text
EPGChannelEntity

id
epgSourceId
stableKey
externalChannelId
displayName
iconUrl
createdAt
updatedAt
```

## Speicherung

EPG-Kanäle werden quellbezogen gespeichert.

`externalChannelId` entspricht der Kanal-ID aus der EPG-Quelle, sofern vorhanden.

`stableKey` identifiziert den EPG-Kanal innerhalb einer EPG-Quelle und darf keine geheimen URL-Bestandteile, Tokens oder Zugangswerte enthalten.

---

# 6.11 ProviderEPGSourceEntity

```text
ProviderEPGSourceEntity

id
providerId
epgSourceId
priority
createdAt
```

## Prioritäten

Ein Provider kann mehrere EPG-Quellen haben.

Niedrigere Prioritätszahl gewinnt bei konkurrierenden EPG-Daten.

---

# 6.12 EPGProgramEntity

```text
EPGProgramEntity

id
epgSourceId
epgChannelId
stableKey
title
normalizedTitle
subtitle
description
startTimeUtc
endTimeUtc
category
iconUrl
isCatchupAvailable
importedAt
createdAt
updatedAt
```

## Speicherung

EPG-Programme werden quell- und EPG-kanalbezogen gespeichert.

EPG-Programme sind Cache-artige Daten und werden im Standard-Backup nicht exportiert.

`stableKey` dient nur der lokalen EPG-Aktualisierung und Deduplizierung. EPG-Programmdaten werden bei Restore nicht aus dem Standard-Backup wiederhergestellt.

`normalizedTitle` dient Deduplizierung, Suche und stabiler Query-Performance.

Provider-Sender erhalten EPG-Programme ausschliesslich über `EPGChannelMappingEntity` und Provider-EPG-Prioritäten.

Alle Programmzeiten werden intern als UTC-Zeitpunkte gespeichert.

---

# 6.13 EPGChannelMappingEntity

```text
EPGChannelMappingEntity

id
providerId
channelId
channelStableKey
epgSourceId
epgSourceStableKey
epgChannelId
epgChannelStableKey
isManual
confidence
createdAt
updatedAt
```

## Mapping

Unterstuetzt:

```text
Automatisch
Manuell
```

Manuelle Zuordnungen müssen backupfähig sein.

Standard-Backups referenzieren manuelle EPG-Zuordnungen über `providerStableKey + channelStableKey + epgSourceStableKey + epgChannelStableKey` und nicht über lokale Room-IDs.

Manuelle Zuordnungen gewinnen immer vor automatischen Zuordnungen.

Automatische Zuordnungen dürfen manuelle Zuordnungen nicht überschreiben.

---

# 6.14 FavoriteEntity

```text
FavoriteEntity

id
providerId
mediaType
mediaId
mediaStableKey
isPending
sortOrder
createdAt
updatedAt
```

## Unterstuetzte Typen

```text
CHANNEL
MOVIE
SERIES
```

## Regeln

Favoriten sind anbieterübergreifend sichtbar, bleiben aber intern providergebunden.

Manuelle Sortierung ist mindestens für Live-TV-Favoriten erforderlich.

Favoriten sind Teil des Standard-Backups.

Standard-Backups referenzieren Favoriten über `providerStableKey + mediaType + mediaStableKey`. `mediaId` darf nach Restore fehlen, bis der nächste erfolgreiche Provider-Refresh die Referenz wieder mit einer lokalen Entity verbindet.

---

# 6.15 PlaybackProgressEntity

```text
PlaybackProgressEntity

id
providerId
mediaType
mediaId
mediaStableKey
isPending
positionMillis
durationMillis
progressPercent
isCompleted
lastWatchedAt
createdAt
updatedAt
```

## Unterstuetzte Typen

```text
MOVIE
EPISODE
```

Komplette Staffeln und Serien besitzen in v1 keinen eigenen `PlaybackProgressEntity`- oder Abschlussdatensatz.

## Verwendet für

- Fortsetzen
- Gesehen
- Watch Next
- Continue Watching
- Home-Verlauf für VOD

## Abschlussregeln

Die feste fachliche Konstante lautet:

```text
PLAYBACK_COMPLETION_THRESHOLD_PERCENT = 95
```

Sie ist kein DataStore-Wert und keine sichtbare Einstellung.

Bei bekannter positiver `durationMillis` wird `progressPercent` konsistent aus Position und Dauer abgeleitet und auf den gültigen Bereich begrenzt. Sobald `progressPercent >= 95` gilt, wird `isCompleted = true` gesetzt.

Ein tatsaechliches Medienende setzt `isCompleted = true`, auch wenn die Dauer fehlt, ungültig ist oder der berechnete Prozentwert unter 95 liegt.

`isCompleted = true` schliesst den Datensatz als direktes Resume-Ziel aus. Bei Serien darf ein abgeschlossener Episodendatensatz weiterhin als Ausgangspunkt dienen, um die nächste verfuegbare Episode bei Position 0 zu bestimmen. Das Erreichen der Schwelle beendet die Wiedergabe nicht und erzeugt kein Auto-Next-Ereignis; Auto-Next verwendet weiterhin das tatsaechliche Medienende.

Automatische Fortschritts- und Abschlussaktualisierungen werden nur aus dem internen Vivicast-Player geschrieben.

Eine Wiedergabe über externe Player erzeugt keinen neuen `PlaybackProgressEntity`-Datensatz, aktualisiert keinen vorhandenen Datensatz, setzt `isCompleted` nicht und loest kein Auto-Next-Ereignis aus. Rueckgabewerte externer Player werden in v1 nicht als verlaessliche Position, Dauer, Fortschritt oder Medienende-Erkennung übernommen.

Live-TV und Catch-Up erzeugen keinen `PlaybackProgressEntity`-Datensatz. Live-TV verwendet `ChannelHistoryEntity`; Catch-Up bleibt an den EPG-Programmpunkt und den PlaybackRequest gebunden und ist kein Resume-Ziel.

Automatische Anlage:

- Ein neuer automatischer Fortschrittsdatensatz entsteht erst ab mindestens 10 Sekunden Position oder mindestens 1 Prozent Fortschritt bei bekannter positiver Dauer.
- Nach der Anlage schreibt der interne Player mindestens alle 10 Sekunden sowie bei Pause, abgeschlossenem Seek, Player-Verlassen, App-Hintergrund und tatsaechlichem Medienende.
- Position und Dauer werden auf nicht negative Werte begrenzt; `positionMillis` darf nicht größer als eine bekannte positive `durationMillis` gespeichert werden.
- `progressPercent` wird aus Position und Dauer abgeleitet, sobald eine bekannte positive Dauer vorhanden ist.
- Fortschrittsspeicherung darf keine neue Entitaet für komplette Staffeln, Serien, Live-TV, Catch-Up oder externe Player einfuehren.

Manuelle Aktionen:

- `Als gesehen markieren` legt bei Bedarf einen Datensatz an oder aktualisiert ihn und setzt `isCompleted = true`.
- `Als ungesehen markieren` loescht den zugehoerigen `PlaybackProgressEntity`-Datensatz einschliesslich Position, Dauer, Fortschritt, Abschlussstatus und Zeitstempeln.

Wiedergabefortschritt ist Teil des Standard-Backups. Ein durch `Als ungesehen markieren` geloeschter Datensatz ist folglich nicht mehr Bestandteil nachfolgender Backups.

Standard-Backups referenzieren Wiedergabefortschritt über `providerStableKey + mediaType + mediaStableKey`. `mediaId` darf nach Restore fehlen, bis der nächste erfolgreiche Provider-Refresh die Referenz wieder mit einer lokalen Movie- oder Episode-Entity verbindet.

---

# 6.16 ChannelHistoryEntity

```text
ChannelHistoryEntity

id
providerId
channelId
channelStableKey
isPending
watchedAt
durationWatchedMillis
updatedAt
```

## Speicherung

Pro Sender existiert nur ein Verlaufseintrag.

Der Verlauf dient zuletzt gesehenen Live-TV-Inhalten.

ChannelHistory ist Teil des Standard-Backups.

Standard-Backups referenzieren Live-TV-Verlauf über `providerStableKey + CHANNEL + channelStableKey`. `channelId` darf nach Restore fehlen, bis der nächste erfolgreiche Provider-Refresh die Referenz wieder mit einer lokalen Sender-Entity verbindet.

---

# 6.17 SearchHistoryEntity

```text
SearchHistoryEntity

id
query
normalizedQuery
lastUsedAt
createdAt
updatedAt
```

## Regeln

Der Suchverlauf speichert maximal 20 Eintraege.

`normalizedQuery` verwendet dieselbe Suchnormalisierung wie FTS-Query und Indextexte.

Einzelne Eintraege und der gesamte Suchverlauf können geloescht werden.

Suchverlauf ist Teil des Standard-Backups, sofern der Nutzer ihn nicht geloescht hat.

---

# 6.18 Such-FTS-Indexe

Vivicast verwendet für die interne Suche abgeleitete Room-FTS4-Indexe.

FTS-Indexe sind technische Ableitungen aus produktiven Daten. Sie sind nicht Teil des Standard-Backups und werden nach Restore, Migration oder Rebuild aus produktiven Daten neu aufgebaut.

V1 fuehrt getrennte FTS-Indexe je Suchgruppe:

```text
ChannelSearchFts
MovieSearchFts
SeriesSearchFts
EPGProgramSearchFts
```

## Gemeinsame Felder

Jeder FTS-Index enthält mindestens:

```text
sourceEntityId
providerId oder epgSourceId
stableReference
title
normalizedTitle
searchText
normalizedSearchText
rankMetadata
updatedAt
```

`sourceEntityId` verweist auf die produktive Entity, aus der der Indexeintrag abgeleitet wurde.

`stableReference` enthält den stabilen fachlichen Schluessel für Trefferauflösung:

- Kanäle: `providerStableKey + CHANNEL + channelStableKey`
- Filme: `providerStableKey + MOVIE + movieStableKey`
- Serien: `providerStableKey + SERIES + seriesStableKey`
- EPG: `epgSourceStableKey + epgChannelStableKey + epgProgramStableKey`

Lokale Room-IDs dürfen für schnelle Joins verwendet werden, aber nicht als backupstabile Suchidentitaet.

## Indexinhalt

`ChannelSearchFts` indexiert Sendername, Providername, Kategorie, Kanalnummer und nicht geheime Senderattribute.

`MovieSearchFts` indexiert Filmtitel, Originaltitel falls vorhanden, Kategorie, Jahr, Beschreibung und nicht geheime Metadaten.

`SeriesSearchFts` indexiert Serientitel, Originaltitel falls vorhanden, Kategorie, Jahr oder Zeitraum, Beschreibung und nicht geheime Metadaten.

`EPGProgramSearchFts` indexiert EPG-Titel, Untertitel, Beschreibung, Sendername, EPG-Kanalname und Programmzeit-Metadaten.

Episoden werden nicht als eigene Suchgruppe indexiert.

## Normalisierung

Die FTS-Indexbefuellung verwendet dieselbe Normalisierung wie die Such-Query:

- trimmen
- Kleinschreibung
- Whitespace zusammenfassen
- Satzzeichen und Symbole als Trenner behandeln
- Umlaute und Diakritika tolerant abbilden
- `ae`, `oe`, `ue` und `ss` als Varianten beruecksichtigen

Originaltitel und Originalmetadaten bleiben in den produktiven Entities unverändert.

## Pflege

Provider-FTS-Indexe werden im selben atomaren Provider-Commit aktualisiert wie Channels, Movies und Series.

EPG-FTS-Indexe werden im selben atomaren EPG-Quellen-Commit aktualisiert wie EPG-Programme.

FTS-Indexe dürfen nicht aus Import- oder Refresh-Staging gelesen werden.

Delete, Restore, Migration und EPG-Cleanup müssen die betroffenen FTS-Indexe aktualisieren oder einen Rebuild auslösen.

---

# 6.18a Android-TV-Systemintegrationsdaten

Android-TV-Systemsuche, Deep Links und Watch Next verwenden stabile fachliche Zielreferenzen.

Lokale Room-IDs dürfen für interne Joins verwendet werden, aber nicht als extern sichtbare System-ID, Deep-Link-ID oder Watch-Next-Identitaet.

## Deep-Link-Referenzen

Unterstuetzte Zielreferenzen:

```text
providerStableKey + CHANNEL + channelStableKey
providerStableKey + MOVIE + movieStableKey
providerStableKey + SERIES + seriesStableKey
providerStableKey + EPISODE + episodeStableKey
```

Deep Links enthalten keine Stream-URLs, Tokens, Zugangswerte, HTTP-Header, Cookies oder lokalen Room-IDs.

Die Deep-Link-Aufloesung sucht zuerst die stabile Referenz in produktiven Daten. Pending Restore-Referenzen, fehlende Inhalte, deaktivierte Provider oder Quellen mit `Zugangsdaten erforderlich` dürfen nicht direkt abgespielt werden.

## AndroidTvSearchIndex

Der Android-TV-Systemsuchindex ist ein technischer abgeleiteter Index aus produktiven Room-Daten.

Er ist kein Backup-Bestandteil.

Enthalten:

```text
CHANNEL
MOVIE
SERIES
```

Nicht enthalten:

```text
EPG
EPISODE
CATCH_UP
geschuetzte Inhalte bei aktivem Schutz
pending Restore-Referenzen
Inhalte deaktivierter Provider
```

Indexfelder enthalten mindestens:

```text
sourceEntityId
providerId
stableReference
mediaType
title
normalizedTitle
searchText
deepLinkUri
updatedAt
```

`deepLinkUri` wird aus stabilen Schluesseln erzeugt und darf keine geheimen Daten enthalten.

## WatchNextPublication

Watch-Next-Publikationen sind technische Systemzuordnungen für bereits an Android TV veroeffentlichte Eintraege.

Sie sind kein Backup-Bestandteil.

Felder:

```text
providerId
mediaType
mediaId
mediaStableKey
stableReference
systemProgramId
watchNextType
positionMillis
durationMillis
lastPublishedAt
updatedAt
```

Unterstuetzte `mediaType`-Werte:

```text
MOVIE
EPISODE
```

Nicht veroeffentlicht werden Live-TV, Catch-Up, externe Player-Ergebnisse, abgeschlossene Inhalte, pending Restore-Referenzen, Inhalte deaktivierter Provider und geschuetzte Inhalte bei aktivem Schutz.

## Pflege und Cleanup

Android-TV-Systemintegrationsdaten werden aktualisiert oder bereinigt bei:

- erfolgreichem Provider-Refresh
- Provider-Deaktivierung
- Provider-Loeschung
- Restore
- Migration
- Änderung von Kindersicherung-Schutzregeln
- Änderung von Wiedergabefortschritt oder Abschlussstatus

Systemintegrationsdaten dürfen nicht aus Import- oder Refresh-Staging erzeugt werden.

Wenn ein erfolgreicher autoritativer Refresh Inhalte entfernt, werden zugehoerige Systemsucheintraege und Watch-Next-Publikationen entfernt.

---

# 6.19 DataStore

## Settings-Persistenzregistry

Dauerhaft gespeicherte sichtbare App-Einstellungen verwenden genau die folgenden DataStore-Schluessel.

| Schluessel | Typ | Default | Erlaubte Werte |
| --- | --- | --- | --- |
| `autoStartOnBootEnabled` | Boolean | `false` | `false`, `true` |
| `startDestination` | Enum | `HOME` | `HOME`, `LIVE_TV`, `MOVIES`, `SERIES` |
| `requireDoubleBackToExit` | Boolean | `true` | `false`, `true` |
| `appLanguage` | Enum | `SYSTEM` | `SYSTEM`, `DE`, `EN` |
| `backgroundRefreshEnabled` | Boolean | `true` | `false`, `true` |
| `rememberSortOrderEnabled` | Boolean | `true` | `false`, `true` |
| `globalUserAgent` | String nullable | `null` | `null` oder gültiger User-Agent-String |
| `epgRefreshIntervalHours` | Integer | `24` | positive Stundenwerte gemaess UI |
| `epgPastRetentionDays` | Integer | `1` | `1` bis `14` |
| `epgFutureRetentionDays` | Integer | `7` | `1` bis `14` |
| `epgRefreshOnAppStartEnabled` | Boolean | `true` | `false`, `true` |
| `epgRefreshOnPlaylistChangeEnabled` | Boolean | `true` | `false`, `true` |
| `themeMode` | Enum | `STANDARD_DARK` | `STANDARD_DARK`, `HIGH_CONTRAST_DARK`, `AMOLED_DARK` |
| `accentColor` | Enum/String | `VIVICAST_BLUE` | vordefinierte zulässige Akzente |
| `panelTransparencyPercent` | Integer | `25` | `0`, `25`, `50` |
| `fontScale` | Enum | `MEDIUM` | `SMALL`, `MEDIUM`, `LARGE`, `EXTRA_LARGE` |
| `animationSpeed` | Enum | `NORMAL` | `OFF`, `FAST`, `NORMAL`, `SLOW` |
| `globalLogoPriority` | Enum | `PLAYLIST` | `PLAYLIST`, `EPG`, `LOCAL_FOLDER` |
| `localLogoFolderUri` | String nullable | `null` | `null` oder persistierbare Android-SAF-URI |
| `epgDisplayOptions` | Objekt | siehe EPG-Darstellungsdefaults | Boolean-Felder gemaess Settings-Vertrag |
| `bufferSize` | Enum | `MEDIUM` | `OFF`, `SMALL`, `MEDIUM`, `LARGE`, `EXTRA_LARGE` |
| `audioDecoderMode` | Enum | `HARDWARE` | `HARDWARE`, `SOFTWARE` |
| `videoDecoderMode` | Enum | `HARDWARE` | `HARDWARE`, `SOFTWARE` |
| `afrEnabled` | Boolean | `false` | `false`, `true` |
| `timeshiftEnabled` | Boolean | `true` | `false`, `true` |
| `timeshiftMaxDurationMinutes` | Integer | `30` | `15`, `30`, `60`, `120` |
| `timeshiftStorage` | Enum | `AUTOMATIC` | `AUTOMATIC`, `RAM`, `DISK` |
| `preferredAudioLanguage` | Enum | `SYSTEM` | `SYSTEM`, `DE`, `EN`, `ORIGINAL` |
| `preferredSubtitleLanguage` | Enum | `OFF` | `OFF`, `SYSTEM`, `DE`, `EN` |
| `autoNextEpisodeEnabled` | Boolean | `false` | `false`, `true` |
| `autoNextEpisodeCountdownSeconds` | Integer | `10` | `5`, `10`, `15`, `30` |
| `audioPassthroughEnabled` | Boolean | `false` | `false`, `true` |
| `externalPlayerMode` | Enum | `INTERNAL_ALWAYS` | `INTERNAL_ALWAYS`, `EXTERNAL_ALWAYS`, `ASK_EVERY_TIME` |
| `backupTargetType` | Enum | `LOCAL_STORAGE` | `LOCAL_STORAGE`, `SMB`, `GOOGLE_DRIVE` |
| `diagnosticsLoggingEnabled` | Boolean | `false` | `false`, `true` |
| `diagnosticsRetentionDays` | Integer | `1` | `1` bis `7` |

Nicht in DataStore gespeichert:

- Der Exportdialog enthält das Feld `Backup-Typ` mit Dialog-Default `STANDARD`; es ist kein Settings-Eintrag und wird nicht dauerhaft gespeichert.
- EPG-Aktualisierungshistorie liegt in appinternen Refresh-Metadaten `EpgRefreshRunMetadata`.
- Diagnose-Sitzungen und Segmente liegen als private Dateien und Metadaten im App-Speicher.
- Geschuetzte Zielauthentifizierung für SMB oder Google Drive liegt im geschuetzten Secret Store.
- Android-SAF-Berechtigungen für `localLogoFolderUri` werden über die persistierbare URI-Berechtigung des Systems gehalten.

## Allgemein

```text
App beim TV-Start starten
Startbereich
Zurück Verhalten
Sprache
Hintergrundaktualisierung
Sortierung merken
User-Agent
```

DataStore-Schluessel und Default für den Startbereich:

```text
startDestination = HOME
```

Zulässige Werte:

```text
startDestination = HOME | LIVE_TV | MOVIES | SERIES
```

Ein fehlender, unbekannter oder nach einer Migration ungültiger Wert faellt auf `HOME` zurück. Der Wert ist global, Teil der App-Einstellungen im Standard-Backup und wird nur bei einem regulaeren App-Start ohne explizites Ziel ausgewertet. Explizite Navigation und das Fortsetzen einer bestehenden Sitzung verändern den gespeicherten Wert nicht.

## EPG

DataStore-Schluessel und Default:

```text
epgRefreshIntervalHours = 24
epgPastRetentionDays = 1
epgFutureRetentionDays = 7
```

Der Wert wird in Stunden gespeichert und gilt global für den automatischen intervallgesteuerten EPG-Refresh.

App-Start-Aktualisierung, Aktualisierung bei Playlist-Änderung und manuelle Aktualisierung sind separate Ausloeser und verändern diesen gespeicherten Wert nicht.

Die EPG-Aufbewahrung wird in Tagen gespeichert und global angewendet.

Zulässige Werte:

```text
epgPastRetentionDays = 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14
epgFutureRetentionDays = 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14
```

Ein fehlender, unbekannter oder ungültiger Wert faellt auf den jeweiligen Default zurück.

## Optik

```text
Hintergrundthema
Akzentfarbe
Transparenz
Schriftgröße
Animationen
Globale Logo-Standardreihenfolge
Logos-Ordner
EPG-Darstellung
```

## Wiedergabe

```text
Puffergröße
Audio-Decoder
Video-Decoder
AFR
Timeshift aktiviert
Timeshift maximale Dauer
Timeshift Speicher
Audio-Sprache
Untertitel-Sprache
Automatisch nächste Folge
Countdown nächste Folge
Audio-Passthrough
Externer Player
```

Timeshift-Schluessel und Defaults:

```text
timeshiftEnabled = true
timeshiftMaxDurationMinutes = 30
timeshiftStorage = AUTOMATIC
```

Zulässige Werte:

```text
timeshiftMaxDurationMinutes = 15 | 30 | 60 | 120
timeshiftStorage = AUTOMATIC | RAM | DISK
```

`DISK` entspricht dem sichtbaren Wert `Festplatte` und bezeichnet appverwalteten persistenten Gerätespeicher.

Player-Track- und External-Player-Schluessel:

```text
preferredAudioLanguage = SYSTEM
preferredSubtitleLanguage = OFF
externalPlayerMode = INTERNAL_ALWAYS
```

Zulässige Werte:

```text
preferredAudioLanguage = SYSTEM | DE | EN | ORIGINAL
preferredSubtitleLanguage = OFF | SYSTEM | DE | EN
externalPlayerMode = INTERNAL_ALWAYS | EXTERNAL_ALWAYS | ASK_EVERY_TIME
```

`externalPlayerMode` gilt in v1 nur für Filme und einzelne Serienepisoden. Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.

Manuelle Audio- oder Untertitelwahl im Player ist sitzungsbezogen und ändert diese globalen DataStore-Werte nicht.

Auto-Next-Schluessel und Defaults:

```text
autoNextEpisodeEnabled = false
autoNextEpisodeCountdownSeconds = 10
```

Zulässige Werte:

```text
autoNextEpisodeEnabled = false | true
autoNextEpisodeCountdownSeconds = 5 | 10 | 15 | 30
```

Der Countdown-Wert bleibt gespeichert, wenn Auto-Next deaktiviert ist. Beide Werte sind globale App-Einstellungen und Bestandteil des Standard-Backups.

Auto-Next verwendet keine eigene Room-Entity. Die nächste Episode wird aus der sortierten Staffel-/Episodenfolge ermittelt; Wiedergabefortschritt und Abschlussstatus bleiben davon getrennte Daten. Insbesondere darf `progressPercent >= 95` kein Auto-Next-Ereignis ersetzen oder auslösen.

## Diagnose

DataStore-Schluessel und Defaults:

```text
diagnosticsLoggingEnabled = false
diagnosticsRetentionDays = 1
```

Zulässige Werte:

```text
diagnosticsLoggingEnabled = false | true
diagnosticsRetentionDays = 1 | 2 | 3 | 4 | 5 | 6 | 7
```

Die Aufbewahrungsdauer bleibt gespeichert, wenn die Protokollierung ausgeschaltet ist.

Bestehende Sitzungen liegen als appinterne Segmentdateien und Metadaten im privaten App-Speicher. Sie gehoeren nicht in Room, DataStore oder das Standard-Backup.

Pro Diagnosesitzung müssen mindestens `sessionId`, `startedAt`, `endedAt`, `lastRecordedAt`, `endReason` und `endTimeAccuracy` dauerhaft rekonstruierbar sein.

Größen- und Segmentgrenzen sind feste Implementierungskonstanten und werden nicht in DataStore gespeichert:

```text
diagnosticsMaxTotalBytes = 20_971_520          // 20 MiB
diagnosticsSegmentMaxBytes = 2_097_152         // 2 MiB
diagnosticsMaxSegmentsPerSession = 3
diagnosticsMaxSessionLogBytes = 6_291_456      // 6 MiB
```

Eine logische Diagnosesitzung darf aus mehreren physischen Segmenten bestehen. Pro Segment müssen mindestens `segmentIndex`, `firstRecordedAt`, `lastRecordedAt`, `byteSize`, `eventCount` und der Abschlussstatus rekonstruierbar sein.

Sitzungs- beziehungsweise globale Diagnosemetadaten fuehren bei Rotation oder Kuerzung mindestens:

```text
contentTruncated
firstRetainedAt
discardedSessionCount
discardedSegmentCount
discardedEventCount
```

## Backup

```text
Backup-Ziel
Letzte Sicherung
```

## Kindersicherung

```text
Schutz für Einstellungen
Schutz für Filme
Schutz für Serien
Schutz für Inhalte ab 18
PIN-Fehlversuchszaehler
PIN-Sperrstufe
PIN-Sperre bis
```

Diese Werte sind lokale Sicherheitszustaende und werden nach einem Restore nicht aus der Backup-Datei übernommen.

PIN-Klartext wird nicht gespeichert.

PIN-Pruefwerte werden als langsame gesalzene Pruefwerte geschuetzt gespeichert.

PIN-Fehlversuchszaehler, Sperrstufe und `lockedUntil` sind lokale Sicherheitszustaende. Eine laufende Sperre wird durch App-Neustart nicht aufgehoben.

Aktive PIN-Freigaben für Schutzbereiche sind nur im Speicher. Sie werden nicht in Room, DataStore, Secret Store oder Backups geschrieben.

PIN-Pruefwerte, aktive PIN-Freigaben, PIN-Sperrstatus und Kindersicherung-Schutzflags aus Backup-Dateien werden beim Restore ignoriert. Nach einem Restore ist Kindersicherung deaktiviert und muss bei Bedarf neu eingerichtet werden.

## Verlauf

Verlauf besitzt in v1 keine eigene DataStore-Einstellung.

Live-TV-Verlauf, Suchverlauf und Wiedergabefortschritt werden in Room gespeichert.

Frei konfigurierbare Verlaufslimits sind nicht Teil von v1. Der Suchverlauf bleibt als feste fachliche Regel auf maximal 20 Eintraege begrenzt.

## Cache

Der Medien-Cache besitzt in v1 keine DataStore-Einstellung für Größe, Rotation oder Aufbewahrung.

Eine sichtbare Cache-Informationszeile zeigt berechnete Werte aus dem Dateisystem, insbesondere die aktuelle Größe des Medien-Caches.

Medien-Cache-Dateien für Senderlogos, Film-Poster, Serien-Poster, Staffelbilder und Episodenbilder liegen als appverwaltete Dateien im lokalen Speicher und gehoeren nicht in Room, DataStore oder das Standard-Backup.

---

# 6.20 Android Keystore und geschuetzter Secret Store

## Speichert

```text
Keystore-Schluessel
Xtream-Codes-Benutzername
Xtream-Codes-Zugangswert
private M3U-URLs mit eingebetteten Zugangswerten
private EPG-URLs mit eingebetteten Zugangswerten
private Stream-URL-Templates mit eingebetteten Zugangswerten
HTTP-Header und Cookies
SMB-Anmeldedaten
Cloud-Anmeldedaten
PIN-Pruefwerte
```

Keystore-Schluessel bleiben nicht exportierbar.

Geheime Nutzwerte liegen im geschuetzten Secret Store und werden mit einem Keystore-gebundenen Schluessel verschlüsselt.

Wenn Keystore-Schluessel oder Secret Store verloren, beschädigt oder nicht entschluesselbar sind, gelten die enthaltenen Geheimnisse als verloren. Betroffene Quellen werden als `Zugangsdaten erforderlich` markiert.

## Backup

Standard-Backups exportieren diese Werte nicht.

Verschlüsselte Vollbackups dürfen Quellen-, SMB- und Cloud-Zugangswerte enthalten, wenn der Nutzer aktiv eine Backup-Passphrase setzt.

PIN-Pruefwerte, aktive PIN-Freigaben, PIN-Sperrstatus und Kindersicherung-Schutzflags werden auch aus verschlüsselten Backups nicht wiederhergestellt.

Verschlüsselte Vollbackups nutzen das Schutzformat aus `prd/PRD-v1/10-backup-import-requirements.md` und `architecture/decisions/ADR-014-security-data-network-backup.md`.

---

# 6.21 Datenbank-Indizes

Pflichtindizes:

## Provider

```text
name
stableKey
```

## Category

```text
providerId
type
stableKey
name
```

## Channel

```text
providerId
categoryId
stableKey
name
remoteId
```

## Movie

```text
providerId
categoryId
stableKey
name
remoteId
```

## Series

```text
providerId
categoryId
stableKey
name
remoteId
```

## Episode

```text
providerId
seriesId
seasonId
stableKey
remoteId
```

## Playback

```text
providerId
mediaType
mediaId
mediaStableKey
lastWatchedAt
isCompleted
```

## EPGSource

```text
stableKey
name
```

## EPGChannel

```text
epgSourceId
stableKey
externalChannelId
displayName
```

## EPGProgram

```text
epgSourceId
epgChannelId
stableKey
startTimeUtc
endTimeUtc
title
normalizedTitle
```

## SearchHistory

```text
query
normalizedQuery
lastUsedAt
```

## Search FTS

```text
ChannelSearchFts.sourceEntityId
ChannelSearchFts.normalizedTitle
MovieSearchFts.sourceEntityId
MovieSearchFts.normalizedTitle
SeriesSearchFts.sourceEntityId
SeriesSearchFts.normalizedTitle
EPGProgramSearchFts.sourceEntityId
EPGProgramSearchFts.epgSourceId
EPGProgramSearchFts.epgChannelId
EPGProgramSearchFts.normalizedTitle
EPGProgramSearchFts.startTimeUtc
```

---

# 6.22 Constraints, Foreign Keys und Migrationen

## Unique Constraints

Pflicht-Unique-Constraints:

```text
ProviderEntity.name
ProviderEntity.stableKey
EPGSourceEntity.stableKey
EPGChannelEntity.epgSourceId + EPGChannelEntity.stableKey
CategoryEntity.providerId + CategoryEntity.type + CategoryEntity.stableKey
ChannelEntity.providerId + ChannelEntity.stableKey
MovieEntity.providerId + MovieEntity.stableKey
SeriesEntity.providerId + SeriesEntity.stableKey
SeasonEntity.providerId + SeasonEntity.seriesId + SeasonEntity.stableKey
EpisodeEntity.providerId + EpisodeEntity.seriesId + EpisodeEntity.stableKey
EPGProgramEntity.epgSourceId + EPGProgramEntity.epgChannelId + EPGProgramEntity.stableKey
FavoriteEntity.providerId + FavoriteEntity.mediaType + FavoriteEntity.mediaStableKey
PlaybackProgressEntity.providerId + PlaybackProgressEntity.mediaType + PlaybackProgressEntity.mediaStableKey
ChannelHistoryEntity.providerId + ChannelHistoryEntity.channelStableKey
ProviderEPGSourceEntity.providerId + ProviderEPGSourceEntity.epgSourceId
EPGChannelMappingEntity.providerId + EPGChannelMappingEntity.channelStableKey + EPGChannelMappingEntity.epgSourceStableKey + EPGChannelMappingEntity.epgChannelStableKey
SearchHistoryEntity.normalizedQuery
```

Pending Favoriten, Verlaeufe und Fortschritte verwenden dieselben Stable-Key-Constraints. Mehrere pending Datensaetze für denselben `providerStableKey + mediaType + mediaStableKey` sind nicht erlaubt.

## Foreign Keys

Providerbezogene Kategorien, Sender, Filme, Serien, Staffeln, Episoden, Favoriten, Verlaeufe, Fortschritte und EPG-Zuordnungen referenzieren `ProviderEntity`.

Sender, Filme und Serien referenzieren ihre Kategorie, sofern vorhanden. Fehlende Kategorien werden durch `__UNCATEGORIZED__` normalisiert.

Staffeln und Episoden referenzieren ihre Serie. Episoden referenzieren ihre Staffel, wenn eine Staffel angelegt wurde.

EPG-Kanäle referenzieren ihre EPG-Quelle.

EPG-Programme referenzieren EPG-Quelle und EPG-Kanal.

Provider-EPG-Zuordnungen referenzieren Provider und EPG-Quelle.

Manuelle EPG-Channel-Mappings referenzieren Provider, Sender und EPG-Quelle.

Manuelle EPG-Channel-Mappings müssen zusätzlich die stabilen Schluessel für Sender, EPG-Quelle und EPG-Kanal speichern, damit sie nach Restore ohne lokale Room-ID wieder verbunden werden können.

Favoriten, Verlaeufe und Fortschritte dürfen bei pending Restore-Referenzen vorübergehend ohne lokale Medien-Foreign-Key-Verbindung existieren. Die stabile Referenz bleibt dann verbindlich.

## Delete-Verhalten

Provider löschen entfernt providerbezogene Kategorien, Sender, Filme, Serien, Staffeln, Episoden, Favoriten, Verlaeufe, Wiedergabefortschritte und Provider-EPG-Zuordnungen.

Globale EPG-Quellen werden beim Löschen eines Providers nicht geloescht.

Provider löschen entfernt zugehoerige Android-TV-Systemsucheintraege und Watch-Next-Publikationen.

Provider deaktivieren entfernt oder sperrt zugehoerige Android-TV-Systemsucheintraege und Watch-Next-Publikationen, laesst die lokalen Providerdaten aber erhalten.

Wenn ein erfolgreicher autoritativer Provider-Refresh einen Inhalt nicht mehr liefert, werden die zugehoerigen Favoriten, Live-TV-Verlaeufe und Wiedergabefortschritte entfernt.

Wenn ein Provider wegen fehlender Zugangsdaten, Netzwerkfehlern, ungültiger Quelle, Abbruch oder nicht autoritativem Teilbereich nicht erfolgreich aktualisiert werden kann, dürfen bestehende Inhalte und pending Referenzen dieses Bereichs nicht geloescht werden.

## Migrationen

Eine Datenbankmigration, die Stable Keys einfuehrt, muss vorhandene Datensaetze deterministisch befuellen.

Xtream-Datensaetze werden aus vorhandenen `remoteId`-Werten beziehungsweise Providerdaten befuellt.

M3U-Datensaetze werden aus den dokumentierten nicht geheimen normalisierten Attributen befuellt.

Wenn ein stabiler Schluessel für alte lokale Daten nicht eindeutig erzeugt werden kann, muss die Migration kontrolliert abbrechen oder den Datensatz als nicht restorefähig markieren. Sie darf keine geheimen Klartextdaten in `stableKey` schreiben.

Backup-Schema-Migrationen alter kompatibler Versionen laufen vor Restore und dürfen keine lokalen und importierten Daten zusammenfuehren.

Nach Migrationen, die suchrelevante Produktivdaten verändern, müssen betroffene FTS-Indexe aktualisiert oder vollstaendig neu aufgebaut werden.

---

# 6.23 Backup-Bezug

Der fachliche Backup-Datenvertrag liegt in:

```text
prd/PRD-v1/10-backup-import-requirements.md
```

Diese Datei definiert die Datenstruktur und Speichersysteme.

Bei Konflikten gewinnt der Backup-Datenvertrag für Export- und Restore-Verhalten.

---

# Skalierungsziel

Die Datenbank muss performant bleiben bei:

```text
10.000+ Sender
50.000+ Filme
20.000+ Serien
Millionen EPG Eintraegen
```
