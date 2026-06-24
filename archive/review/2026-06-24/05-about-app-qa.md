# 05 - Über die App QA

Status: verbindliche Review-Referenz v6

## Zweck

Diese Datei definiert QA-Punkte fuer den Settings-Tab `Über die App`.

Sie ergaenzt `design/review/04-visual-acceptance-checklist.md`.

## Pruefen

- Der Tab zeigt App-Name, App-Version, Build-Nummer, Paketname und Datenbank-Version.
- Android-Version, Geraetemodell und Player-Engine werden angezeigt, sofern technisch verfuegbar.
- Versionsinformationen koennen kopiert werden.
- Kopieraktion zeigt sichtbares Feedback.
- Der bereinigte Log-Export liegt unter `Über die App > Diagnose und Support`.
- `Diagnose und Support` bietet `Diagnoseprotokollierung` mit Standard `Aus`.
- `Diagnose und Support` bietet `Aufbewahrungsdauer` von 1 bis 7 Tagen mit Standard 1 Tag.
- Die Aufbewahrungsdauer bleibt bei ausgeschalteter Protokollierung sichtbar, ist aber deaktiviert.
- Vorhandene Sitzungen bleiben nach dem Ausschalten bis zu ihrem regulaeren Ablauf exportierbar.
- `Diagnose und Support` bietet die Aktion `Diagnoseprotokoll exportieren`.
- Allgemeine Support-Informationen enthalten nur nicht-private technische Daten.
- Allgemeine Support-Informationen duerfen angezeigt und kopiert werden.
- Der Inhalt einer Log- oder Diagnoseprotokolldatei wird niemals direkt in der App angezeigt.
- Es existiert keine Logvorschau, kein scrollbarer Logtext und kein Dialog mit Logzeilen.
- Logdatei-Inhalte koennen nicht in die Zwischenablage kopiert werden.
- Exportstatus zeigt nur Erfolg, Fehler oder Exportziel und niemals Loginhalt.
- Diagnoseereignisse werden vor dauerhaftem Schreiben bereinigt und vor Export erneut zentral geprueft.
- Exportformat ist ein ZIP-Archiv mit MIME-Type `application/zip`.
- Der Dateiname folgt `vivicast-diagnostics-YYYYMMDD-HHmmss.zip`.
- Das ZIP-Archiv enthaelt verpflichtend `vivicast-diagnostics.log` und `diagnostics-metadata.json` in UTF-8.
- Die Logdatei enthaelt nur die freigegebenen technischen Ereigniskategorien.
- Netzwerkereignisse enthalten Fehlerart, HTTP-Status und Dauer, jedoch keine URL.
- Die Metadatendatei enthaelt App-/Build-, Geraete-, Datenbank-, Sprach-, Zeitzonen-, Exportzeit-, tatsaechlichen Zeitraum-, Sitzungs-, Grenzwert- und Trunkierungsdaten.
- Bei aktivierter Protokollierung besteht gleichzeitig genau eine aktive logische Diagnosesitzung; App-Prozessstart oder erneute Aktivierung startet eine neue Sitzung.
- Kontrolliertes Beenden, Deaktivierung und nicht sauberes Prozessende werden mit Abschlussgrund und Zeitqualitaet unterschieden.
- Bildschirm-Aus, Standby oder TV-Aus beendet eine weiterlaufende Sitzung nicht kuenstlich.
- Altersbereinigung verwendet 1 bis 7 Tage und loescht die aktive Sitzung nie vollstaendig.
- Intern gelten 20 MiB Gesamtlimit, 2 MiB je Segment und maximal drei Segmente beziehungsweise 6 MiB Logdaten je Sitzung.
- Sitzungsinterne Rotation entfernt das aelteste geschlossene Segment; globale Bereinigung entfernt zuerst die aeltesten abgeschlossenen Sitzungen.
- Das aktuelle Schreibsegment und die Metadaten der aktiven Sitzung bleiben bei Rotation erhalten.
- Der Exportzeitraum basiert nur auf tatsaechlich noch vorhandenen Ereignissen und weist Kuerzungen aus.
- Zugangswerte, Tokens, Cookies, HTTP-Header, URLs, Rohdaten, Provider-/Inhaltsnamen, Suchverlauf, Datenbank-Dumps, Screenshots und ungefiltertes System-Logcat fehlen in allen ZIP-Eintraegen.
- Bereinigte Stacktraces werden nur fuer Warnungen, Fehler und Abstuerze exportiert.
- Technische Zuordnungen verwenden bei Bedarf nur neutrale interne IDs.
- Vivicast erzeugt das ZIP-Archiv ohne Abhaengigkeit von einer externen ZIP-App.
- Das ZIP-Archiv wird gestreamt geschrieben und nicht vollstaendig im RAM aufgebaut.
- Bei einem Exportfehler erfolgt kein stiller Wechsel auf ein anderes Dateiformat.
- Eine unvollstaendige Datei wird nicht als erfolgreicher Export gemeldet.
- Lizenzhinweise sind per D-Pad lesbar und scrollbar.
- Datenschutzinformationen sind per D-Pad lesbar und scrollbar.
- Drittanbieter-Lizenzen sind als Liste oder Detailseite bedienbar.
- Daten zuruecksetzen ist nicht Teil dieses Tabs.
- Backup und Restore sind nicht Teil dieses Tabs.
- Wiedergabelisten- und EPG-Verwaltung sind nicht Teil dieses Tabs.
- Automatische Update-Installation ist nicht Teil von v1.


## Warum wichtig

`Über die App` soll Support und Transparenz verbessern, ohne sensible Informationen oder umfangreiche Logs direkt auf dem Fernseher offenzulegen. Das standardisierte ZIP-Format muss geraeteunabhaengig durch die App selbst erzeugt werden.

## Ergebnisformat fuer PR-Beschreibungen

```text
About App QA:
- App info checked: yes/no
- Copy action checked: yes/no
- Diagnostics location checked: yes/no
- Diagnostics toggle/default checked: yes/no
- Retention range/default/disabled state checked: yes/no
- Session lifecycle checked: yes/no
- Size limits and rotation checked: yes/no
- Truncation metadata and actual covered period checked: yes/no
- ZIP export format checked: yes/no
- Streaming ZIP creation checked: yes/no
- Export-only log behavior checked: yes/no
- Log redaction checked: yes/no
- Required ZIP entries checked: yes/no
- Allowed diagnostic content checked: yes/no
- Excluded data checked: yes/no
- Legal pages checked: yes/no
- Out-of-scope actions absent: yes/no
- Deviations from docs: none / listed
```
