# About App Components

Status: verbindlich v6

## Zweck

Diese Datei beschreibt wiederverwendbare Komponenten für den Settings-Bereich `Über die App`.

## App Info Row

Verwendung für technische App-Informationen.

Zeigt:

- Titel
- Wert
- optionalen Hinweis

Beispiele:

- App-Version
- Build-Nummer
- Paketname
- Datenbank-Version
- Android-Version
- Gerätemodell
- Player-Engine

Regeln:

- Werte müssen aus TV-Entfernung lesbar sein.
- Lange Werte dürfen gekuerzt werden, müssen aber in Detailansicht voll sichtbar sein.
- Kopierbare Werte dürfen eine sichtbare Kopieraktion anbieten.

## About Action Row

Verwendung für Aktionen im Über-die-App-Tab.

Beispiele:

- Versionsinformationen kopieren
- Diagnose und Support öffnen
- Lizenzhinweise öffnen
- Datenschutzinformationen öffnen
- Drittanbieter-Lizenzen öffnen

Regeln:

- OK fuehrt Aktion aus oder oeffnet Detaildialog.
- Kopieraktionen zeigen nach Erfolg einen kurzen Status.
- Aktionen dürfen nicht zu Backup, Restore oder Daten-zurücksetzen fuehren.

## Legal Text Panel

Verwendung für Datenschutz, Lizenzhinweise und Drittanbieter-Lizenzen.

Regeln:

- Textbereich ist per D-Pad scrollbar.
- Zurück schliesst zuerst den Panel.
- Lange Listen dürfen lazy gerendert werden.
- Links sind optional und müssen per Fernbedienung erreichbar sein.

## Diagnose und Support Panel

Verwendung für allgemeine technische Support-Informationen und den Einstieg in den Diagnoseprotokoll-Export.

Der Panel darf anzeigen:

- App- und Build-Version
- Paketname
- Android-Version
- Gerätemodell
- Datenbank-Version
- aktive Sprache
- technische Zaehler ohne private Details
- Zeitpunkte des letzten Imports und letzten Fehlers

Regeln:

- Allgemeine Supportdaten dürfen kopiert werden.
- Kopierte Supportdaten entsprechen den sichtbar angezeigten allgemeinen Informationen.
- Der Panel zeigt keine Quelleninhalte und keine Zugangswerte.
- Der Panel zeigt niemals Logzeilen oder den Inhalt einer Diagnoseprotokolldatei.

## Diagnose Logging Toggle

Sichtbares Label:

```text
Diagnoseprotokollierung
```

Regeln:

- Werte: `Aus` und `Ein`.
- Standard ist `Aus`.
- Aktivierung beginnt im laufenden App-Prozess sofort eine neue Diagnosesitzung.
- Deaktivierung beendet die aktive Sitzung, loescht vorhandene Sitzungen aber nicht sofort.

## Diagnostics Retention Select Row

Sichtbares Label:

```text
Aufbewahrungsdauer
```

Regeln:

- Werte: 1 bis 7 Tage.
- Standard ist 1 Tag.
- Bei ausgeschalteter Diagnoseprotokollierung bleibt die Zeile sichtbar, ist aber deaktiviert.
- Eine Verkuerzung der Dauer stoesst unmittelbar die Altersbereinigung an.
- Größenlimit und Rotation werden nicht als weitere UI-Komponente dargestellt.

## Diagnoseprotokoll Export Action

Verwendung innerhalb von `Über die App > Diagnose und Support`.

Sichtbares Label:

```text
Diagnoseprotokoll exportieren
```

Exportformat:

```text
ZIP-Archiv
MIME-Type: application/zip
Dateiname: vivicast-diagnostics-YYYYMMDD-HHmmss.zip
```

Das Archiv enthält verpflichtend diese UTF-8-Eintraege:

```text
vivicast-diagnostics.log
diagnostics-metadata.json
```

Regeln:

- OK startet den Export-Flow.
- Vor dem Export werden die Daten zentral bereinigt.
- Vivicast erzeugt das ZIP-Archiv selbst; eine externe ZIP-App ist nicht erforderlich.
- Das Archiv wird direkt in den Ziel-OutputStream geschrieben und nicht vollstaendig im RAM aufgebaut.
- Nach Erfolg oder Fehler wird nur ein kurzer Status angezeigt.
- Der Inhalt der Logdatei darf vor oder nach dem Export nicht in der App dargestellt werden.
- Es gibt keine Vorschau, keinen Log-Textbereich und keine Aktion zum Kopieren des Logdatei-Inhalts.
- Bei einem Exportfehler wird nicht stillschweigend auf ein anderes Dateiformat gewechselt.
- `vivicast-diagnostics.log` enthält nur bereinigte technische Ereignisse zu App, Playlist/EPG, Player, Netzwerk, Backup/Restore, Cache und Datenbank sowie bereinigte Stacktraces.
- `diagnostics-metadata.json` enthält App-/Build-, Geräte-, Datenbank-, Sprach-, Zeitzonen-, Exportzeit-, tatsaechlichen Zeitraum-, Sitzungs-, Grenzwert- und Trunkierungsdaten.
- Der Export fuegt nur die noch vorhandenen Segmente chronologisch zusammen.
- Intern gelten 20 MiB Gesamtlimit, 2 MiB pro Segment und höchstens drei Segmente beziehungsweise 6 MiB Logdaten pro Sitzung.
- Eine gekuerzte Historie wird mit `contentTruncated` und den zugehoerigen Zaehlern ausgewiesen.
- Zugangswerte, Tokens, Cookies, HTTP-Header, URLs, Rohdaten, Provider-/Inhaltsnamen, Suchverlauf, Datenbank-Dumps, Screenshots und ungefiltertes System-Logcat sind ausgeschlossen.
- Neutrale interne IDs dürfen bei Bedarf als technische Referenz verwendet werden.

## Export Status

Verwendung nach dem Diagnoseprotokoll-Export.

Regeln:

- Status zeigt Erfolg oder konkrete Fehlerursache.
- Optional darf das gewählte Exportziel oder der ZIP-Dateiname benannt werden.
- Status darf keine Logzeilen oder Ausschnitte aus der Exportdatei enthalten.
- Eine unvollstaendige Datei darf nicht als erfolgreicher Export gemeldet werden.

## Copied Status

Verwendung nach Kopieraktionen für allgemeine Support- oder Versionsinformationen.

Regeln:

- Status ist kurz und nicht modal, wenn keine Aktion erforderlich ist.
- Bei Dialogdarstellung ist OK fokussierbar.
- Diese Komponente wird nicht zum Kopieren von Logdatei-Inhalten verwendet.
