# ADR-012 - Atomic Import and Refresh

## Status

Accepted

## Kontext

Vivicast verarbeitet Provider-Playlists, Xtream-Codes-Daten und EPG-Quellen mit grossen Datenmengen.

Parser duerfen laut ADR-011 toleranten Teilimport liefern. Gleichzeitig darf ein Fehler keine halb aktualisierte Bibliothek oder unkontrollierten Datenverlust verursachen.

## Entscheidung

Import und Refresh sind atomar pro Provider und pro EPG-Quelle.

Der globale Scheduler ist kein globaler Alles-oder-nichts-Commit ueber alle Provider und EPG-Quellen.

Jeder Provider-Refresh und jeder EPG-Quellen-Refresh folgt dem Ablauf:

```text
Download
Parse
Validate
Stage
Diff
Commit
Cleanup
```

Vor `Commit` werden keine produktiven Daten veraendert.

Der Provider-Commit laeuft in einer Room-Transaktion fuer diesen Provider.

Der EPG-Quellen-Commit laeuft in einer Room-Transaktion fuer diese EPG-Quelle.

Staging-Daten sind technische Zwischendaten und werden nicht angezeigt, nicht gesichert und nicht als produktive Identitaet verwendet.

Wenn Download, Parse, Validate, Stage oder Diff fehlschlagen, bleiben produktive Daten unveraendert.

Wenn eine Commit-Transaktion fehlschlaegt, muss sie vollstaendig zurueckrollen.

Wenn ein Prozess nach erfolgreichem Commit, aber vor Cleanup abbricht, gelten die committed produktiven Daten. Verwaiste Staging-Daten werden beim naechsten Start oder vor dem naechsten Lauf bereinigt.

`Erfolgreich mit Teilfehlern` darf valide Eintraege committen. Destruktive Entfernt-Loeschungen sind nur fuer Teilbereiche erlaubt, die vollstaendig gelesen und validiert wurden.

Ein erfolgreicher, autoritativer Provider-Refresh entfernt Inhalte, die nicht mehr geliefert werden, inklusive zugehoeriger Favoriten, Verlaeufe und Wiedergabefortschritte.

Ein fehlgeschlagener, abgebrochener oder nicht autoritativer Refresh darf vorhandene Inhalte und pending Restore-Referenzen nicht loeschen.

Fuer denselben Provider oder dieselbe EPG-Quelle darf nicht gleichzeitig mehr als ein produktiver Refresh laufen.

## Gruende

- Unabhaengige Provider duerfen sich nicht gegenseitig blockieren.
- Alte Daten muessen sichtbar bleiben, wenn eine Quelle temporaer fehlschlaegt.
- Toleranter Teilimport soll nutzbare Daten retten, ohne Loeschungen aus unsicheren Daten abzuleiten.
- Room-Transaktionen geben eine klare technische Grenze fuer Commit und Rollback.

## Konsequenzen

- Der Scheduler dedupliziert Refresh-Anfragen pro Provider und pro EPG-Quelle.
- Manuelle Aktualisierung darf einen ausstehenden automatischen Lauf vorziehen oder ersetzen, aber keinen parallelen Lauf fuer dieselbe Einheit starten.
- Staging braucht einen Laufkontext wie `refreshRunId` oder eine gleichwertige Bereinigungsmoeglichkeit.
- Diagnose und Aktualisierungshistorie muessen zwischen `Erfolgreich`, `Erfolgreich mit Teilfehlern`, `Fehlgeschlagen` und `Abgebrochen` unterscheiden koennen.
- Implementierungen muessen testen, dass Fehler vor, waehrend und nach Commit keine halb aktualisierte Bibliothek hinterlassen.
