# Vivicast PRD v1
## Kapitel 4.2-4.3 - Filme und Serien Anforderungen

Status: bereinigt v9

---

# Verbindliche Designquellen

Konkretes Layout, Fokusverhalten, Grid-Struktur, Detailseitenaufbau und UI-Zustaende sind nicht in dieser PRD-Datei definiert.

Verbindliche Quellen:

- `design/screens/04-movies.md`
- `design/screens/05-series.md`
- `design/interaction/focus.md`
- `design/components/list-grid-items.md`

---

# Gemeinsame VOD-Kategorieregel

Filme und Serien nutzen providergruppierte Kategorien.

Globale App-Kategorien stehen oberhalb der Provider:

- Favoriten
- Fortsetzen, falls nicht abgeschlossene Wiedergabefortschritte existieren

Darunter folgen Provider mit ihren eigenen Kategorien:

```text
Provider A
- Thriller
- Action
- Drama

Provider B
- Thriller
- Action
- Drama
```

Gleichnamige Kategorien verschiedener Provider werden nicht zusammengefuehrt.

Provider-Kategorien werden unverändert übernommen.

Der frische Inhaltsfokus liegt bei Filme und Serien auf der ersten Kategorie des ersten Providers.

Globale App-Kategorien sind sichtbar, aber nicht der initiale Inhaltsfokus.

---

# 4.2 Filme

## Ziel

Der Filmbereich muss grosse VOD-Bibliotheken performant darstellen und Filme schnell auffindbar machen.

Benutzer müssen Filme starten, fortsetzen, favorisieren und Detailinformationen lesen können.

## Kategorien

Filme nutzen die gemeinsame VOD-Kategorieregel.

## Filminformationen

Ein Film kann folgende Informationen enthalten:

- Titel
- Bewertung
- Genre
- Jahr
- Laufzeit
- Beschreibung
- Regisseur
- Schauspieler
- Poster
- Backdrop
- Trailer-URL

Fehlende Metadaten dürfen das Anzeigen oder Abspielen nicht verhindern.

## Poster und Bilder

Poster und Backdrops stammen aus Providerdaten.

Wenn kein Poster vorhanden ist, wird ein Fallback angezeigt.

Keine automatische externe Suche bei:

- TMDB
- IMDb
- TVDB
- OMDb

## Detailseite

OK auf einen Film oeffnet immer zuerst die Detailseite.

Die Wiedergabe startet über Aktionen auf der Detailseite.

## Aktionen

Neuer Film:

- Abspielen
- Trailer, falls verfuegbar oder per YouTube-Suche möglich
- Zu Favoriten hinzufügen
- Als gesehen markieren

Begonnener Film:

- Fortsetzen
- Von Anfang an
- Trailer
- Zu Favoriten hinzufügen
- Als gesehen markieren

Gesehener Film:

- Status `Gesehen` anzeigen
- Von Anfang an
- Trailer
- Zu Favoriten hinzufügen
- Als ungesehen markieren

## Trailer-Verhalten

Wenn `trailerUrl` eine gültige YouTube-URL ist, soll die YouTube-App mit diesem Trailer geoeffnet werden.

Gültige Domains:

- `youtube.com`
- `www.youtube.com`
- `youtu.be`

Wenn keine gültige URL vorhanden ist, wird die YouTube-App mit Suchbegriff geoeffnet.

Suchbegriff:

```text
<Titel> Trailer
```

Wenn die YouTube-App fehlt, wird ein Hinweis angezeigt.

## Favoriten

Standardsortierung:

- zuletzt hinzugefuegt zuerst

Alternative Sortierungen:

- A-Z
- Z-A
- Jahr
- Bewertung

## Fortsetzen und Gesehen

Gespeichert werden:

- Position
- Dauer
- Fortschritt
- Abschlussstatus
- zuletzt angesehen

Filme erscheinen nur dann in `Fortsetzen`, wenn ein gespeicherter, fortsetzbarer Fortschritt vorhanden und `isCompleted = false` ist.

Die Abschluss-Schwelle ist für v1 fest auf 95 Prozent gesetzt und nicht konfigurierbar.

Ein Film gilt als abgeschlossen, wenn mindestens eine der folgenden Bedingungen erfuellt ist:

- bei bekannter positiver Dauer erreicht der gespeicherte Wiedergabefortschritt mindestens 95 Prozent
- der interne Player meldet das tatsaechliche Medienende
- der Nutzer waehlt `Als gesehen markieren`

In diesen Faellen wird `isCompleted = true` gesetzt und der Film aus allen Fortsetzen-Bereichen entfernt.

Das Erreichen von 95 Prozent beendet die laufende Wiedergabe nicht. Der Film laeuft weiter, bis der Nutzer ihn verlaesst oder der Player das Medienende meldet.

`Als ungesehen markieren` loescht den vollstaendigen gespeicherten Wiedergabefortschritt dieses Films. Danach wird der Film wieder als nicht begonnen behandelt.

Die fachlichen Datenregeln stehen zusätzlich in `prd/PRD-v1/06-data-model.md`.

Automatische Fortschritte werden nur durch den internen Vivicast-Player geschrieben. Speicherrhythmus, Mindestposition und Abschlussgrenzen stehen in `prd/PRD-v1/04-search-settings-player-requirements.md`.

---

# 4.3 Serien

## Ziel

Serien müssen schnell fortgesetzt werden können.

Der Nutzer soll nicht erneut Staffel und Episode suchen müssen.

Der Serienbereich nutzt denselben Grundaufbau wie der Filmbereich.

## Serieninformationen

Eine Serie kann folgende Informationen enthalten:

- Titel
- Bewertung
- Genre
- Jahr
- Beschreibung
- Poster
- Backdrop
- Staffeln
- Episoden

Fehlende Metadaten dürfen Anzeige oder Wiedergabe nicht verhindern.

## Kategorien

Serien nutzen die gemeinsame VOD-Kategorieregel.

## Detailseite

OK auf eine Serie oeffnet immer zuerst die Serien-Detailseite.

Die Detailseite enthält Serieninformationen, Fortsetzen-Bereich, Staffelauswahl und Episodenliste.

## Staffeln

Staffeln werden als eigene Ebene unterhalb einer Serie behandelt.

Wenn Providerdaten keine saubere Staffelstruktur liefern, erzeugt die App intern eine nutzbare Staffelstruktur.

## Episoden

Eine Episode kann folgende Informationen enthalten:

- Episodennummer
- Titel
- Beschreibung
- Thumbnail
- Laufzeit
- Staffelnummer
- Wiedergabefortschritt
- Gesehen-Status

Fallback bei fehlenden Episodentiteln:

```text
Folge 1
Folge 2
Folge 3
```

OK auf eine Episode startet oder setzt diese direkt fort.

Es gibt keine separate Episode-Detailseite.

## Fortsetzen

Wenn Fortschritt existiert, muss die App speichern:

- Serie
- Staffel
- Episode
- Position
- Fortschritt

Fortsetzen startet exakt an der gespeicherten Position, solange die Episode nicht abgeschlossen ist.

Ist die zuletzt relevante Episode abgeschlossen und eine nächste Episode verfuegbar, zeigt das serienbezogene Fortsetzen stattdessen diese nächste Episode und startet sie bei Position 0. Die abgeschlossene Episode selbst wird nie als Resume-Ziel angeboten.

## Episodenfortschritt und Gesehen

Die feste Abschluss-Schwelle von 95 Prozent gilt für jede einzelne Episode.

Eine Episode gilt als abgeschlossen, wenn mindestens eine der folgenden Bedingungen erfuellt ist:

- bei bekannter positiver Dauer erreicht ihr gespeicherter Wiedergabefortschritt mindestens 95 Prozent
- der interne Player meldet das tatsaechliche Medienende
- der Nutzer waehlt für diese Episode `Als gesehen markieren`

In diesen Faellen wird für die Episode `isCompleted = true` gesetzt. Ein abgeschlossener Episodenfortschritt ist kein Fortsetzen-Ziel.

Die fokussierte Episode bietet eine sichtbare sekundaere Aktion:

- `Als gesehen markieren`, solange sie nicht abgeschlossen ist
- `Als ungesehen markieren`, sobald sie abgeschlossen ist

`Als ungesehen markieren` loescht den vollstaendigen gespeicherten Wiedergabefortschritt dieser Episode. Die Episode wird danach als nicht begonnen behandelt.

Die Markierung gilt in v1 ausschliesslich für einzelne Episoden. Komplette Staffeln und Serien besitzen weder eine manuelle Gesehen-/Ungesehen-Aktion noch einen eigenen Abschlussdatensatz.

## Automatische nächste Folge

Auto-Next gilt für Serienepisoden im internen Vivicast-Player und wird unter `Einstellungen > Wiedergabe` konfiguriert.

Optionen:

```text
Automatisch nächste Folge: Aus | Ein
Countdown nächste Folge: 5 | 10 | 15 | 30 Sekunden
```

Defaults:

```text
Automatisch nächste Folge: Aus
Countdown nächste Folge: 10 Sekunden
```

Die Countdown-Auswahl bleibt bei deaktiviertem Auto-Next sichtbar, ist aber nicht bedienbar. Der gespeicherte Countdown-Wert bleibt erhalten.

### Nächste Episode bestimmen

Als nächste Episode gilt die nächste verfuegbare Episode in der sortierten Staffel-/Episodenfolge.

Nach der letzten Episode einer Staffel folgt die erste verfuegbare Episode der nächsten Staffel.

Nach der letzten Episode der Serie oder wenn keine nächste Episode verfuegbar ist, erscheint kein Auto-Next-Panel.

### Auto-Next deaktiviert

Die laufende Episode wird ohne Vorabhinweis bis zum tatsaechlichen Medienende abgespielt.

Erst nach dem Player-Endereignis erscheint ein Panel mit:

```text
Nächste Folge abspielen | Zurück
```

Beide Buttons erscheinen erst nach dem Player-Endereignis zeitgleich. Der Fokus liegt auf `Nächste Folge abspielen`. OK startet die nächste Episode sofort. Ohne Nutzeraktion erfolgt kein automatischer Start.

### Auto-Next aktiviert

Das Panel erscheint genau um den konfigurierten Countdown-Zeitraum vor dem tatsaechlichen Medienende.

Das Panel zeigt beide Buttons zeitgleich; der Hauptbutton besitzt den Fokus und aktualisiert seinen Wert sekundenweise:

```text
Nächste Folge in X | Zurück
```

OK auf dem Hauptbutton startet die nächste Episode sofort und überspringt damit bewusst die noch verbleibenden Sekunden.

Ohne Eingabe laeuft die aktuelle Episode bis zum tatsaechlichen Ende weiter. Bei Ablauf des Countdowns beziehungsweise beim Player-Endereignis startet die nächste Episode automatisch. Die konfigurierte Vorlaufzeit darf die letzten Sekunden nicht selbsttaetig abschneiden.

### Zurück und Rueckkehr

Das Auto-Next-Panel besitzt keinen Button `Abbrechen`.

OK auf den sichtbaren Button `Zurück` oder die Zurück-Taste beendet den Auto-Next-Ablauf, verwirft einen laufenden Countdown, beendet die aktuelle Player-Sitzung und fuehrt zur Serien-Detailseite mit dem zuvor gewählten Staffel-/Episodenkontext zurück.

Die Auto-Next-Anzeige nutzt ausschliesslich das tatsaechliche Medienende und ist von der festen 95-Prozent-Abschluss-Schwelle getrennt. Das Erreichen von 95 Prozent darf weder das Panel einblenden noch einen automatischen Episodenwechsel auslösen.

## Externer Player und Fortschritt

Der interne Vivicast-Player ist in v1 die einzige verlaessliche Quelle für automatische Position, Dauer, Fortschritt, Medienende, Abschlussstatus und Auto-Next.

Wenn ein Film oder eine Episode an einen externen Player übergeben wird:

- wird kein neuer `PlaybackProgressEntity`-Datensatz erzeugt
- wird ein vorhandener `PlaybackProgressEntity`-Datensatz nicht automatisch aktualisiert
- wird `isCompleted` nicht automatisch gesetzt
- wird ein Rueckgabewert eines externen Players nicht als verlaesslicher Fortschritt übernommen
- bleibt vorhandener Fortschritt unverändert
- erscheint nach Rueckkehr zu Vivicast ein Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte

Manuelle Aktionen bleiben verfuegbar:

- `Als gesehen markieren`
- `Als ungesehen markieren`, wenn ein Abschluss- oder Fortschrittsdatensatz vorhanden ist

Auto-Next gilt nur für Serienepisoden im internen Vivicast-Player. Bei Wiedergabe über einen externen Player erscheinen kein Auto-Next-Panel und kein automatischer Wechsel zur nächsten Episode.

Nach Rueckkehr aus einem externen Player kehrt Vivicast zum passenden Film- oder Serienkontext zurück.

Die externe Player-Auswahl gilt in v1 nur für Filme und einzelne Serienepisoden. Live-TV und Catch-Up bleiben interne Vivicast-Player-Kontexte.

## Serienabschluss

Der Abschlussstatus wird ausschliesslich pro Episode gespeichert.

Abgeschlossene Episoden werden nicht als Resume-Ziel angeboten. Solange nach der zuletzt abgeschlossenen Episode eine nächste Episode verfuegbar ist, kann das serienbezogene `Fortsetzen` auf diese nächste Episode bei Position 0 verweisen. Nach Abschluss der letzten verfuegbaren Episode verschwindet die Serie aus `Fortsetzen`, sofern kein anderer nicht abgeschlossener Episodenfortschritt vorhanden ist.

Komplette Staffeln oder Serien werden in v1 nicht separat als gesehen oder ungesehen markiert.

## Favoriten

Standardsortierung:

- zuletzt hinzugefuegt zuerst

Alternative Sortierungen:

- A-Z
- Z-A
- Jahr
- Bewertung

---

# Abgrenzung

Diese Datei definiert fachliche VOD-Anforderungen.

Nicht hier definiert:

- Poster-Grid-Layout
- Detailseitenlayout
- Fokuspfade
- Card-Größen
- visuelle Zustandsdarstellung

Diese Details liegen in den Designquellen.
