# ADR-008 - Android TV Integration

## Status

Accepted

## Kontext

Vivicast ist primaer fuer Android TV vorgesehen und soll sich wie eine native Android-TV-Anwendung verhalten.

Android-Systemfunktionen wie globale Suche, Deep Links und Watch Next koennen Inhalte ausserhalb der normalen App-Navigation sichtbar machen. Sie duerfen Provider-Isolation, stabile Identitaeten und Kindersicherung nicht umgehen.

## Entscheidung

Vivicast unterstuetzt:

- Android-TV-Sprachsuche in der App-Suche
- globale Android-TV-Suche
- Deep Links
- Watch Next
- Continue Watching

Globale Android-TV-Suche enthaelt:

- Live-TV
- Filme
- Serien

Globale Android-TV-Suche enthaelt nicht:

- EPG-Treffer
- Episoden als eigene Treffer
- Catch-Up
- geschuetzte Inhalte, solange der jeweilige Kindersicherungs-Schutz aktiv ist

Die interne Vivicast-Suche darf EPG-Treffer enthalten.

Ein EPG-Treffer aus der internen Vivicast-Suche baut keinen Android-TV-Systemsuchtreffer auf. Er oeffnet intern Live-TV im Sender-Modus, aktiviert den passenden Sender, scrollt die EPG-Spalte zum Zielprogrammpunkt und fokussiert diesen Programmpunkt.

Deep Links verwenden keine lokalen Room-IDs und keine geheimen Werte.

Deep-Link-Ziele verwenden stabile fachliche Schluessel:

- Provider: `providerStableKey`
- Sender: `providerStableKey + CHANNEL + channelStableKey`
- Film: `providerStableKey + MOVIE + movieStableKey`
- Serie: `providerStableKey + SERIES + seriesStableKey`
- Episode: `providerStableKey + EPISODE + episodeStableKey`

Beispiele:

```text
vivicast://channel/{providerStableKey}/{channelStableKey}
vivicast://movie/{providerStableKey}/{movieStableKey}
vivicast://series/{providerStableKey}/{seriesStableKey}
vivicast://episode/{providerStableKey}/{episodeStableKey}
```

Deep Links duerfen keine Stream-URLs, Tokens, Zugangswerte, HTTP-Header oder Cookies enthalten.

Deep Links, globale Android-TV-Suchergebnisse und Watch Next oeffnen ein konkretes Ziel mit passendem Herkunftskontext.

Wenn das Ziel fehlt, pending ist, geloescht wurde, der Provider deaktiviert ist, Zugangsdaten fehlen oder der aktuelle Schutzbereich eine PIN verlangt, darf Vivicast nicht still auf Home ausweichen.

Stattdessen gilt:

- fehlende oder geloeschte Inhalte zeigen einen kontrollierten Nicht-verfuegbar-Zustand
- pending Restore-Referenzen zeigen einen kontrollierten Wartet-auf-Aktualisierung-Zustand
- Provider mit fehlenden Zugangsdaten zeigen `Zugangsdaten erforderlich`
- geschuetzte Inhalte oeffnen erst nach erfolgreicher aktueller PIN-Freigabe

Watch Next und Continue Watching gelten fuer:

- Filme
- Serienepisoden

Nicht fuer:

- Live-TV
- Catch-Up
- externe Player-Ergebnisse
- geschuetzte Inhalte, solange der jeweilige Kindersicherungs-Schutz aktiv ist

Serien-Fortsetzen basiert auf Episoden-Wiedergabefortschritt.

Abgeschlossene Filme und Episoden sind keine direkten Resume-Ziele. Filme und Episoden gelten ab der im PRD festgelegten 95-Prozent-Schwelle, beim tatsaechlichen Medienende oder nach `Als gesehen markieren` als abgeschlossen. Bei Serien kann Watch Next beziehungsweise Continue Watching danach auf die naechste verfuegbare Episode bei Position 0 wechseln.

Globale Android-TV-Suche und Watch Next sind abgeleitete Systemintegrationsdaten.

Sie duerfen nur aus produktiven Room-Daten erzeugt werden, nie aus Import-/Refresh-Staging.

Sie muessen aktualisiert oder bereinigt werden bei:

- erfolgreichem Provider-Refresh
- Provider-Deaktivierung
- Provider-Loeschung
- Restore
- Migration
- Aenderung von Kindersicherung-Schutzregeln
- Aenderung von Wiedergabefortschritt oder Abschlussstatus

Pending Restore-Referenzen werden nicht in globale Android-TV-Suche oder Watch Next veroeffentlicht, bis ein erfolgreicher Provider-Refresh sie mit lokalen Entities verbunden hat.

Wenn Kindersicherung fuer Filme, Serien oder Inhalte ab 18 aktiv ist, werden betroffene Inhalte nicht in Android-Systemsuche oder Watch Next veroeffentlicht. Stale Systemeintraege muessen beim naechsten Sync entfernt werden. Stale Deep Links muessen beim Öffnen trotzdem gegen die aktuelle Schutzkonfiguration geprueft werden.

## Gruende

- Native Android-TV-Erwartung erfuellen.
- EPG-Treffer wuerden globale Suche ueberladen.
- Live-TV ist nicht sinnvoll als Watch-Next-Inhalt.
- Deep Links ermoeglichen konsistente Navigation aus Android-Systemfunktionen.
- Das Datenmodell speichert Wiedergabefortschritt fuer Filme und Episoden.
- Android-Systemoberflaechen koennen Metadaten ausserhalb der App anzeigen; geschuetzte Inhalte duerfen dort nicht sichtbar werden.
- Stabile Schluessel verhindern, dass Restore, Migration oder Refresh lokale Room-IDs in Systemintegrationen unbrauchbar machen.

## Konsequenzen

- Media-IDs muessen deep-link-faehig sein und stabile fachliche Schluessel verwenden.
- Systemsuche und Watch Next brauchen eigene abgeleitete Index-/Publish-Pflege.
- Playback Progress muss Watch Next aktualisieren und abgeschlossene direkte Resume-Ziele entfernen.
- Episodenabschluss muss die naechste Episode setzen; nach der letzten Episode muss der Serien-Eintrag entfernt werden.
- Globale Systemsuche und interne App-Suche haben unterschiedliche Ergebnisumfaenge.
- Providerstatus, Restore und Kindersicherung muessen Systemeintraege bereinigen.
