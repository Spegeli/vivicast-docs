# 08 - Wireframe: Über die App

Status: verbindlich v6

## Zweck

Dieses Wireframe beschreibt den Settings-Tab `Über die App`.

Der Tab zeigt App-Informationen, rechtliche Hinweise sowie Diagnose und Support.

## Primaerlayout

```text
+--------------------------------------------------------------------------------+
| Vivicast        Home | Live-TV | Filme | Serien | Suche | Einstellungen   |
+----------------------+---------------------------------------------------------+
| GRUPPEN              | UEBER DIE APP                                           |
| [Allgemein]          | (*) App-Informationen                  [Oeffnen]       |
| [Wiedergabelisten]   |     Versionsinformationen kopieren     [Kopieren]      |
| [EPG]                |     Diagnose und Support               [Oeffnen]       |
| [Optik]              |     Lizenzhinweise                     [Oeffnen]       |
| [Wiedergabe]         |     Datenschutzinformationen           [Oeffnen]       |
| [Kindersicherung]    |     Drittanbieter-Lizenzen             [Oeffnen]       |
| [Backup]             |                                                         |
| (*)[Über die App]   |                                                         |
+----------------------+---------------------------------------------------------+
```

## App-Informationen

```text
+--------------------------------------------------------------------------------+
| App-Informationen                                                              |
+--------------------------------------------------------------------------------+
| App-Name              Vivicast                                                 |
| App-Version           <Version>                                                |
| Build-Nummer          <Build>                                                  |
| Paketname             com.vivicast.tv                                          |
| Datenbank-Version     <DB-Version>                                             |
| Android-Version       <Android-Version>                                        |
| Geraetemodell         <Modell>                                                 |
| Player-Engine         Media3 / ExoPlayer                                       |
| UI                    Jetpack Compose for TV                                   |
|                                                                                |
| [Schliessen]                                                                  |
+--------------------------------------------------------------------------------+
```

## Versionsinformationen kopieren

```text
+---------------------------------------------+
| Versionsinformationen kopiert.              |
|                                             |
| [OK]                                        |
+---------------------------------------------+
```

## Diagnose und Support

```text
+--------------------------------------------------------------------------------+
| Diagnose und Support                                                           |
+--------------------------------------------------------------------------------+
| App-Version                  <Version>                                         |
| Build-Nummer                 <Build>                                           |
| Paketname                    com.vivicast.tv                                   |
| Android-Version              <Android-Version>                                 |
| Geraetemodell                <Modell>                                          |
| Datenbank-Version            <DB-Version>                                      |
| Sprache                      <Sprache>                                         |
| Provider                     <n>                                               |
| EPG-Quellen                  <n>                                               |
| Letzter Import               <Datum/Uhrzeit oder Nie>                          |
| Letzter Fehler               <Datum/Uhrzeit oder Kein Fehler>                  |
|                                                                                |
| (*) Diagnoseprotokollierung                        [Aus]                       |
|     Aufbewahrungsdauer                             [1 Tag] (deaktiviert)       |
|                                                                                |
| [Diagnoseprotokoll exportieren] [Support-Informationen kopieren]               |
| [Schliessen]                                                                   |
+--------------------------------------------------------------------------------+
```

Regeln:

```text
Diagnoseprotokollierung ist standardmaessig Aus.
Aufbewahrungsdauer bietet 1 bis 7 Tage und steht standardmaessig auf 1 Tag.
Bei ausgeschalteter Diagnoseprotokollierung bleibt die Aufbewahrungsdauer sichtbar, ist aber nicht aenderbar.
Vorhandene Sitzungen bleiben bis zu ihrem regulaeren Ablauf exportierbar.
Groessenlimit und Rotation erzeugen keine sichtbare Einstellung.
Allgemeine Support-Informationen duerfen angezeigt und kopiert werden.
Quelleninhalte und Zugangswerte werden nicht angezeigt oder kopiert.
Der Inhalt des Diagnoseprotokolls wird niemals in der App angezeigt.
Es gibt keine Logvorschau und keinen kopierbaren Logtext.
Exportformat ist ein ZIP-Archiv mit MIME-Type application/zip.
Dateiname: vivicast-diagnostics-YYYYMMDD-HHmmss.zip
Verpflichtende Eintraege: vivicast-diagnostics.log, diagnostics-metadata.json
Loginhalt: nur bereinigte technische Ereignisse
Metadaten: App/Build, Geraet, Datenbank, Sprache, Zeitzone, Exportzeit, tatsaechlicher Zeitraum, Sitzungen, Grenzwerte, Kuerzungen
Interne Grenzen: 20 MiB gesamt, 2 MiB pro Segment, maximal 3 Segmente beziehungsweise 6 MiB pro Sitzung
Keine Zugangswerte, URLs, Rohdaten, Inhaltsnamen, Suchverlaeufe, Dumps, Screenshots oder ungefiltertes System-Logcat
Technische Zuordnungen nur ueber neutrale interne IDs
```

## Export erfolgreich

```text
+----------------------------------------------------------+
| Diagnoseprotokoll exportiert.                            |
| Datei: vivicast-diagnostics-YYYYMMDD-HHmmss.zip          |
|                                                          |
| [OK]                                                     |
+----------------------------------------------------------+
```

Der Erfolgs- oder Fehlerstatus darf keinen Inhalt der exportierten Logdatei anzeigen.

## Export fehlgeschlagen

```text
+----------------------------------------------------------+
| Diagnoseprotokoll konnte nicht exportiert werden.        |
| <konkrete Fehlerursache ohne Loginhalt>                   |
|                                                          |
| [Erneut versuchen] [Schliessen]                           |
+----------------------------------------------------------+
```

Es erfolgt kein stiller Wechsel auf ein anderes Dateiformat.

## Lizenzhinweise

```text
+--------------------------------------------------------------------------------+
| Lizenzhinweise                                                                 |
+--------------------------------------------------------------------------------+
| <lokaler Lizenztext oder Linkliste>                                             |
|                                                                                |
| [Schliessen]                                                                  |
+--------------------------------------------------------------------------------+
```

## Datenschutzinformationen

```text
+--------------------------------------------------------------------------------+
| Datenschutzinformationen                                                       |
+--------------------------------------------------------------------------------+
| <lokaler Datenschutztext oder Informationsseite>                                |
|                                                                                |
| [Schliessen]                                                                  |
+--------------------------------------------------------------------------------+
```

## Drittanbieter-Lizenzen

```text
+--------------------------------------------------------------------------------+
| Drittanbieter-Lizenzen                                                         |
+--------------------------------------------------------------------------------+
| (*) androidx ...                                                               |
|     Media3 ...                                                                 |
|     weitere Bibliothek ...                                                     |
|                                                                                |
| [Schliessen]                                                                  |
+--------------------------------------------------------------------------------+
```

## Nicht enthalten

```text
Anzeige oder Vorschau von Logdatei-Inhalten
Kopieren von Logdatei-Inhalten
Daten zuruecksetzen
Backup oder Restore
Wiedergabelistenverwaltung
EPG-Verwaltung
automatische Update-Installation
```

## Zustaende

```text
Copied   -> Support- oder Versionsinformationen kopiert
Exported -> ZIP-Diagnoseprotokoll erfolgreich exportiert
Error    -> Information konnte nicht geladen, kopiert oder exportiert werden
```
