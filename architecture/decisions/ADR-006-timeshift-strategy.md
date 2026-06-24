# ADR-006 – Timeshift Strategy

## Status

Accepted

## Kontext

Live-TV soll Timeshift unterstuetzen, ohne die App-Architektur unnoetig komplex zu machen.

Timeshift bleibt abhaengig von Provider, Sender, Stream, Geraeteressourcen und Wiedergabe-Einstellung.

## Entscheidung

Version 1.0 stellt drei Timeshift-Wiedergabe-Einstellungen bereit.

### Timeshift

Optionstyp:

```text
Toggle
```

Werte:

```text
Ein
Aus
```

Standardwert:

```text
Ein
```

### Maximale Timeshift-Dauer

Optionstyp:

```text
Auswahl
```

Werte:

```text
15 Minuten
30 Minuten
60 Minuten
120 Minuten
```

Standardwert:

```text
30 Minuten
```

### Timeshift-Speicher

Optionstyp:

```text
Auswahl
```

Werte:

```text
Automatisch
RAM
Festplatte
```

Standardwert:

```text
Automatisch
```

`Automatisch` laesst die App zwischen RAM und persistentem Geraetespeicher waehlen.

`Festplatte` bezeichnet den von der App verwalteten persistenten Geraetespeicher. Eine freie Ordner- oder Pfadauswahl ist fuer v1 nicht vorgesehen.

Die Einstellungen fuer maximale Dauer und Speicher bleiben sichtbar, sind bei deaktiviertem Timeshift jedoch nicht aktiv bedienbar.

Wenn Timeshift deaktiviert oder fuer den aktuellen Stream nicht verfuegbar ist, darf Live-TV-Seek nicht moeglich sein und muss einen kurzen Hinweis anzeigen.

Die Timeshift-Implementierung muss hart begrenzt und berechenbar bleiben. Die konfigurierte maximale Dauer darf nicht ueberschritten werden.

Bei einem Senderwechsel wird der Timeshift-Puffer verworfen.

Es existiert immer nur eine aktive Wiedergabe und damit hoechstens ein aktiver Timeshift-Puffer.

Timeshift gilt nur fuer Live-TV. Der Puffer startet mit der aktiven Live-Wiedergabe und wird bei Senderwechsel, Player-Ende, App-Stopp oder Start eines anderen Medientyps verworfen.

Wenn der konfigurierte Timeshift-Speicher wegen Geraeteressourcen, Speicherfehlern oder Stream-Eigenschaften nicht nutzbar ist, laeuft Live-TV ohne Timeshift weiter. Seek bleibt fuer diese Wiedergabesitzung gesperrt und Vivicast zeigt einen Hinweis.

`Cache leeren` darf aktive Stream- oder Timeshift-Puffer nicht entfernen.

Der vollstaendige Player- und PlaybackRequest-Vertrag steht in `ADR-013-player-playback-progress.md`.

## Gruende

- Der Nutzer kann Timeshift bewusst deaktivieren.
- Die maximale Dauer begrenzt den Ressourcenverbrauch nachvollziehbar.
- `Automatisch` bietet einen sicheren Standard fuer unterschiedliche Android-TV-Geraete.
- RAM und Festplatte bleiben fuer Nutzer mit konkreten Anforderungen explizit waehlbar.
- Ein appverwalteter Speicherort vermeidet in v1 zusaetzliche Ordner- und Berechtigungsflows.
- Das Verwerfen beim Senderwechsel reduziert Sonderfaelle und parallele Puffer.

## Konsequenzen

- Timeshift ist standardmaessig aktiviert.
- Die maximale Timeshift-Dauer betraegt standardmaessig 30 Minuten.
- Der Timeshift-Speicher steht standardmaessig auf `Automatisch`.
- Timeshift laeuft nicht ueber Senderwechsel hinweg weiter.
- Es gibt keine parallelen Timeshift-Puffer.
- Speicherverwaltung muss begrenzt und berechenbar bleiben.
- Nicht verfuegbare oder nicht ausfuehrbare Timeshift-Zustaende muessen sichtbar behandelt werden.
- Timeshift-Fehler duerfen Live-TV nicht abbrechen, solange die Live-Wiedergabe selbst moeglich ist.
