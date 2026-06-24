# 04 - Visual Acceptance Checklist

Status: verbindliche Review-Referenz v21

## Zweck

Diese Datei dient als visuelle Qualitaetsreferenz fuer Vivicast UI-Arbeiten.

Sie ist keine Implementierungsplanung und keine feste Aufgabenreihenfolge.

Codex soll diese Checkliste bei groesseren UI-PRs als Review-Hilfe nutzen, damit Screens, Fokus, Navigation und Komponenten konsistent bleiben.

Codex darf weiterhin selbst entscheiden, wie ein konkreter Umsetzungsplan im App-Repository aufgebaut wird.

Messbare Teststrategie und Release-DoD liegen in `prd/PRD-v1/13-test-strategy.md`.

Normative Produkt-, Architektur- und Designregeln bleiben in PRD, ADRs, Screen Specs, Wireframes, Interaction Specs, Components und Design-System. Diese Checkliste wiederholt Pruefpunkte nur als Review-Hilfe und ersetzt diese Quellen nicht.

## Anwendung

Empfohlen fuer:

- groessere UI-PRs
- neue Screens
- groessere Refactorings an Navigation, Fokus oder Komponenten
- Player-Overlay-Aenderungen
- Layout-Aenderungen an TV-Hauptscreens
- Settings-, Playlist- oder EPG-Verwaltungsflows
- Optik- und Wiedergabe-Einstellungen
- Kindersicherung und PIN-geschuetzte Aktionen
- Backup, Import und Restore

Nicht noetig fuer:

- reine Textkorrekturen
- kleine interne Code-Aufraeumarbeiten ohne UI-Auswirkung
- technische Aenderungen ohne sichtbare UI-Aenderung

## 1. Android TV Bedienung

Pruefen:

- Die gesamte Ansicht ist per D-Pad bedienbar.
- Es gibt keine Touch-only-Interaktion.
- OK, Zurueck, Hoch, Runter, Links und Rechts verhalten sich nachvollziehbar.
- CH+ und CH- verhalten sich im jeweiligen Kontext gemaess Spezifikation.
- Primaere Aktionen sind mit wenigen Tastendruecken erreichbar.

Warum wichtig:

Android TV wird aus Distanz mit Fernbedienung genutzt. Eine UI, die Maus-, Touch- oder Mobile-Muster voraussetzt, ist fuer Vivicast ungeeignet.

## 2. Fokus

Pruefen:

- Jedes interaktive Element hat sichtbaren Fokus.
- Fokus ist nicht nur ueber Farbe erkennbar.
- Der globale Fokus-Stil wird genutzt: Scale, Ring und leichte Schattenhebung.
- Der Initialfokus entspricht der jeweiligen Screen-Spec.
- Fokusbewegung ist vorhersehbar und bricht nicht in grossen Listen.
- Fokus bleibt bei Loading-, Empty- und Error-Zustaenden bedienbar.
- PIN-Dialoge verlieren den Fokus nicht nach falscher Eingabe.
- Im Live-TV Browser startet beim blossen Senderfokus kein Stream.
- Erstes OK in der Senderspalte oeffnet den Sender-Modus, startet die Preview und setzt den Fokus auf die aktuelle EPG-Sendung, sofern vorhanden.
- Zweites OK auf der fokussierten aktuellen EPG-Sendung oeffnet Vollbild.
- Es existiert keine Preview-Einstellung und kein direkter Vollbildstart beim ersten OK.

Warum wichtig:

Fokus ist der zentrale Orientierungspunkt auf TV-Geraeten. Ohne klaren Fokus verliert der Nutzer sofort die Kontrolle ueber die Bedienung.

## 3. Navigation und Zurueck-Verhalten

Pruefen:

- Die Top Navigation ist auf Hauptscreens dauerhaft sichtbar.
- Der Player ist ein Fullscreen-Kontext ohne dauerhaft sichtbare Top Navigation.
- Zurueck schliesst zuerst Dialoge oder Overlays.
- Zurueck-Verhalten folgt der jeweiligen Screen-Spec.
- Hauptbereiche starten fresh, ausser explizit anders definiert.
- Player-Rueckkehr fuehrt zum passenden Herkunftskontext.
- PIN-Abbrechen kehrt in den vorherigen sicheren Kontext zurueck.
- Backup-Import abbrechen veraendert keine lokalen Daten.
- Deep Links, Android-TV-Suche und Watch Next bauen einen passenden Herkunftskontext auf.
- Fehlende oder nicht verfuegbare Systemziele fallen nicht still auf Home zurueck.
- Geschuetzte Systemziele zeigen zuerst PIN-Abfrage oder sicheren Abbruch.

Warum wichtig:

Konsistentes Zurueck-Verhalten verhindert Sackgassen und macht die App auf der Fernbedienung berechenbar.

## 4. Screen-Struktur

Pruefen:

- Der Screen folgt seiner Datei in `design/screens/`.
- PRD-Anforderungen werden nicht durch neue UI-Ideen ueberschrieben.
- Layout-Zonen sind klar getrennt.
- Wichtige Inhalte sind nicht in Nebenzonen versteckt.
- Hauptbereiche bleiben visuell konsistent.

Warum wichtig:

Screen Specs sind die ausfuehrbare Design-Referenz fuer Codex. Abweichungen fuehren schnell zu uneinheitlicher UI.

## 5. Komponenten-Konsistenz

Pruefen:

- Wiederverwendbare Komponenten folgen `design/components/`.
- Poster Cards fuer Filme und Serien nutzen denselben Stil.
- Gesehene Filme zeigen einen eindeutigen Status und `Als ungesehen markieren`; neue oder begonnene Filme bieten `Als gesehen markieren`.
- Die fokussierte Episode zeigt rechts eine D-Pad-erreichbare Aktion `Als gesehen markieren` oder `Als ungesehen markieren`; nicht fokussierte Episode Rows bleiben ruhig.
- Staffel- und Serien-Header bieten keine Gesehen-/Ungesehen-Aktion.
- Channel Cards nutzen die richtige Informationsdichte je Kontext.
- Settings-Komponenten nutzen einheitliche Zeilen, Dialoge, Texteingaben, PIN-Eingaben, Farbauswahl, Detailbereiche und Statusanzeigen.
- Backup nutzt Backup Card, Backup Summary Dialog und Restore Safety Backup Dialog.
- Player-Komponenten nutzen die zentrale Timeline-Logik.

Warum wichtig:

Konsistente Komponenten reduzieren visuelle Brueche und erleichtern spaetere Wartung.

## 6. Lesbarkeit aus TV-Entfernung

Pruefen:

- Texte sind aus typischer TV-Entfernung lesbar.
- Wichtige Informationen sind nicht zu klein oder zu dicht.
- Lange Titel werden kontrolliert gekuerzt.
- Fokussierte Elemente duerfen mehr Informationen anzeigen.
- Kontrast reicht fuer dunkle TV-Umgebungen aus.
- Optik-Anpassungen duerfen Mindestkontrast und Fokus nicht verschlechtern.
- Sicherheits- und Fehlerhinweise in PIN- und Backup-Dialogen sind eindeutig lesbar.

Warum wichtig:

TV-UIs werden nicht aus Smartphone-Distanz gelesen. Kleine oder zu dichte Texte wirken auf dem Fernseher schnell unbrauchbar.

## 7. Loading, Empty und Error States

Pruefen:

- Loading nutzt bevorzugt Skeleton Cards oder Rows.
- Spinner werden nur genutzt, wenn Skeletons nicht sinnvoll sind.
- Empty States enthalten eine klare Aktion, wenn eine sinnvolle Aktion existiert.
- Suchergebnis-Empty-State zeigt nur einen Hinweis.
- Suche zeigt genau die Gruppen Kanäle, Filme, Serien und EPG.
- Suche zeigt keine Episoden-Gruppe und kein eigenes Episoden-Ergebnis.
- EPG-Ergebnisse duerfen bei kurzen Suchbegriffen fehlen, bis die fachliche Mindestlaenge erreicht ist.
- Es gibt keine verpflichtende `Alle anzeigen`-Aktion.
- Fehler erscheinen inline im betroffenen Bereich.
- Fullscreen-Fehler erscheinen nur, wenn der ganze Screen nicht nutzbar ist.
- Nicht unterstuetzte Wiedergabeoptionen werden deaktiviert mit Hinweis angezeigt.
- PIN-Fehler erscheinen inline im PIN-Dialog.
- Temporär blockierte PIN-Eingabe zeigt Restzeit und blockierten Zustand.
- Backup-Fehler zeigen konkrete Ursache und veraendern lokale Daten nicht teilweise unkontrolliert.

Warum wichtig:

IPTV-Daten koennen gross, langsam oder fehlerhaft sein. Gute Zustandsdarstellung verhindert, dass die App kaputt wirkt.

## 8. Player Overlay

Pruefen:

- OK oeffnet das Overlay und fokussiert die Timeline.
- Overlay blendet nach 5 Sekunden Inaktivitaet aus.
- Zurueck schliesst sichtbares Overlay.
- Zurueck verlaesst Player nur, wenn kein Overlay sichtbar ist.
- Live-TV Overlay zeigt alle definierten Sender- und EPG-Informationen.
- VOD Overlay bleibt minimal und zeigt keine Beschreibung oder Poster.
- Timeline ist das zentrale Bedienelement.
- Live-TV ohne Timeshift zeigt Hinweis bei Seek-Versuch.
- Catch-Up bleibt im internen Player, zeigt EPG-Kontext und erzeugt keinen VOD-Fortschritt.
- Externe Player werden nur fuer Filme und einzelne Episoden angeboten, nicht fuer Live-TV oder Catch-Up.
- Audio- und Untertitelauswahl im Player ist per D-Pad bedienbar und gilt nur fuer die aktuelle Wiedergabe.
- Bei Auto-Next Aus erscheint `Naechste Folge abspielen` erst nach dem tatsaechlichen Episodenende.
- Bei Auto-Next Ein erscheint `Naechste Folge in X` X Sekunden vor dem Episodenende; X wird sekundenweise aktualisiert.
- Der Auto-Next-Hauptbutton behaelt waehrend des Countdowns Fokus, Groesse und Position.
- Das Folgepanel nutzt nicht den normalen Player-Overlay-Auto-Hide.
- OK auf dem Hauptbutton startet sofort; nur der aktivierte Countdown startet ohne Eingabe automatisch.
- Der sichtbare Button `Zurueck` wird in beiden Zustaenden zeitgleich neben dem Hauptbutton angezeigt; ein Button `Abbrechen` ist nicht vorhanden.
- OK auf `Zurueck` oder die Zurueck-Taste verwirft einen laufenden Countdown und fuehrt zur Serien-Detailseite mit wiederhergestelltem Staffel-/Episodenkontext.
- Nach der letzten Episode der Serie erscheint kein Auto-Next-Panel.
- Das Erreichen von 95 Prozent veraendert nur den Abschlussstatus, beendet die Wiedergabe nicht und loest kein Auto-Next aus.
- Bei externen Playern erscheinen kein Auto-Next-Panel und kein automatischer Episodenwechsel.
- Nach Rueckkehr aus externer Film- oder Episodenwiedergabe zeigt Vivicast einen Hinweis, dass der Fortschritt nicht automatisch ermittelt werden konnte.
- Externe Player erzeugen oder aktualisieren keinen automatischen Wiedergabefortschritt und setzen keinen Abschlussstatus.

Warum wichtig:

Der Player ist der kritischste Vollbild-Kontext. Unklare Overlays stoeren Wiedergabe und Bedienung direkt.

## 9. Performance-Gefuehl

Pruefen:

- Fokuswechsel fuehlt sich fluessig an.
- Grosse Listen und Grids werden lazy dargestellt.
- Bilder werden nicht unnoetig vorab geladen.
- Suche blockiert die UI nicht.
- Suchergebnisse bleiben auf maximal 20 Treffer pro Gruppe begrenzt.
- FTS-Rebuild- oder Indexpflege-Zustaende blockieren die Navigation nicht.
- Preview- oder Streamstart blockiert nicht die Navigation.
- Loading-Zustaende geben sofort Rueckmeldung.
- Animationseinstellung `Aus` deaktiviert nicht die Bedienlogik, sondern nur nicht notwendige Bewegung.

Warum wichtig:

Vivicast muss grosse IPTV-Bibliotheken bedienen. Auch wenn Daten geladen werden, muss die UI reaktionsfaehig bleiben.

## 10. Quellen- und Einstellungsflows

Pruefen:

- Einstellungen starten frisch auf Allgemein.
- Allgemein zeigt Startbereich als Auswahl mit Home, Live-TV, Filme und Serien.
- Startbereich zeigt bei Erstinitialisierung Home und weist darauf hin, dass Aenderungen ab dem naechsten regulaeren App-Start gelten.
- Die Startbereich-Auswahl navigiert die Settings-Ansicht nicht sofort um.
- Allgemein zeigt User-Agent als letzte Option.
- Sprache liegt in Allgemein, nicht in Optik.
- User-Agent erscheint nicht in Wiedergabelisten-, Provider-, EPG-Quellen- oder Streamformularen.
- Wiedergabeliste hinzufuegen startet mit Name, danach Quelltyp M3U oder Xtream Codes.
- M3U unterstuetzt URL, Datei und Zwischenablage.
- Xtream Codes nutzt Server, Benutzername und Passwort.
- HTTP-Quellen sind erlaubt, werden aber sichtbar als unsicher markiert.
- HTTPS wird bevorzugt.
- TLS-Zertifikatsfehler werden nicht bypassed.
- Provider-Formulare bieten keine eigenen Header-, Cookie- oder User-Agent-Optionen.
- Importstatus kann `Erfolgreich mit Teilfehlern` anzeigen.
- Teilfehler zeigen nur zusammenfassende technische Informationen, keine Rohdaten, URLs, Zugangswerte oder Provider-/Inhaltsnamen.
- Fehlgeschlagener oder abgebrochener Provider-Refresh laesst alte Inhalte sichtbar und zeigt keine halb importierte Bibliothek.
- Teilfehler-Refreshes zeigen zusammenfassenden Status; Loeschungen sind nur aus autoritativen Teilbereichen ableitbar.
- Name ist bei beiden Quelltypen Pflichtfeld und eindeutig.
- EPG-Quellen werden global verwaltet und pro Provider zugeordnet.
- EPG-Aktualisierung unterscheidet zwischen globalem Intervall, App-Start, Playlist-Aenderung und manueller Aktualisierung.
- Das globale EPG-Intervall zeigt bei Erstinitialisierung den Standardwert `24 Stunden`.
- EPG-Vergangenheit behalten zeigt Werte 1 bis 14 Tage und Default 1 Tag.
- EPG-Zukunft laden/behalten zeigt Werte 1 bis 14 Tage und Default 7 Tage.
- EPG-Programme werden quellbezogen ueber EPG-Quelle und EPG-Kanal gedacht, nicht als providerbezogene Kopien.
- Manuelle EPG-Zuordnung gewinnt vor automatischer Zuordnung.
- Loeschen nutzt einfache Bestaetigung und PIN, wenn Einstellungsschutz aktiv ist.

Warum wichtig:

Quellenverwaltung ist ein Kernbereich der App. Fehlerhafte oder uneinheitliche Flows erschweren den Einstieg und erzeugen Datenprobleme.

## 11. Optik- und Wiedergabe-QA

Pruefen:

- Optik-Reihenfolge folgt der Settings-Spec: Hintergrundthema, Akzentfarbe, Transparenz, Schriftgroesse, Animationen, Logo-Reihenfolge, Logos-Ordner, EPG-Darstellung.
- Optik veraendert keine Grundlayoutachsen, Safe-Area, D-Pad-Navigation, Mindestkontraste oder Fokuspflicht.
- EPG-Darstellung ist als Detailbereich mit mehreren Toggles umgesetzt.
- Wiedergabe-Reihenfolge folgt der Settings-Spec: Puffergroesse, Audio-Decoder, Video-Decoder, AFR, Timeshift, maximale Timeshift-Dauer, Timeshift-Speicher, Audio-Sprache, Untertitel-Sprache, Automatisch naechste Folge, Countdown naechste Folge, Audio-Passthrough, externer Player.
- Timeshift ist standardmaessig aktiviert.
- Maximale Timeshift-Dauer bietet 15, 30, 60 und 120 Minuten; Standard ist 30 Minuten.
- Timeshift-Speicher bietet Automatisch, RAM und Festplatte; Standard ist Automatisch.
- Bei Timeshift Aus bleiben Dauer und Speicher sichtbar, sind aber nicht fokussierbar oder aenderbar.
- `Festplatte` wird als appverwalteter persistenter Speicher ohne freie Pfadauswahl dargestellt.
- Optionen mit Stream-Neustartbedarf zeigen einen Hinweis.
- AFR und Audio-Passthrough zeigen Unsupported-State, wenn das Geraet sie nicht unterstuetzt.
- Timeshift bleibt abhaengig von Provider, Sender und Stream.
- Timeshift-Fehler lassen Live-TV ohne Timeshift weiterlaufen, Seek ist dann gesperrt und ein Hinweis sichtbar.
- Audio- und Untertitel-Sprache werden als Defaults beim Streamstart angewendet; manuelle Player-Auswahl aendert die Settings nicht.
- Auto-Next ist standardmaessig Aus; der Countdown ist standardmaessig 10 Sekunden.
- Countdown-Werte sind 5, 10, 15 und 30 Sekunden.
- Bei Auto-Next Aus bleibt die Countdown-Zeile sichtbar, ist aber nicht fokussierbar oder aenderbar.
- Es gibt keine sichtbare Abschluss-Schwellen-Einstellung; der feste v1-Wert ist 95 Prozent.
- Externe Player-Auswahl ist voll per D-Pad bedienbar.
- Externe Player werden sichtbar als Wiedergabeuebergabe ohne automatische Fortschrittsrueckgabe behandelt.
- Die externe Player-Auswahl gilt nur fuer Filme und einzelne Episoden.

Warum wichtig:

Optik darf die TV-Bedienbarkeit nicht zerstoeren. Wiedergabeoptionen wirken direkt auf Streamstart, Fehlerfaelle und Player-Verhalten und muessen deshalb eindeutig sein.

## 12. Kindersicherung-QA

Pruefen:

- Schutzbereich-Toggles sind deaktiviert, solange keine PIN gesetzt ist.
- Aktivieren eines Schutzbereichs ohne PIN startet zuerst den PIN-setzen-Flow.
- PIN setzen verlangt neue PIN und Wiederholung.
- PIN aendern verlangt aktuelle PIN, neue PIN und Wiederholung.
- PIN deaktivieren verlangt aktuelle PIN und klare Bestaetigung.
- PIN-Eingaben sind verdeckt und 4-stellig.
- PIN-Felder oeffnen die numerische Passwort-Systemtastatur.
- Vivicast zeigt keine eigene Zifferntastatur fuer PINs.
- Nach vier Ziffern wird nicht automatisch gespeichert, entsperrt oder deaktiviert.
- Falsche PIN zeigt Fehler inline im Dialog.
- Nach fuenf falschen Eingaben erscheint ein temporaer blockierter Zustand.
- PIN-Sperrdauern sind 30 Sekunden, 60 Sekunden und danach 5 Minuten.
- App-Neustart hebt eine laufende PIN-Sperre nicht auf.
- Erfolgreiche PIN-Eingabe gibt nur den betroffenen Schutzbereich fuer die aktuelle App-Sitzung frei.
- Session-Freigaben sind nur im Speicher und nicht backupfaehig.
- Session-Freigabe sperren setzt laufende Freigaben zurueck, aber aendert keine Einstellungen.
- FSK-18-Schutz greift nur bei eindeutiger 18+-Kennzeichnung oder vorhandenen Altersfreigabe-Metadaten.
- Abbrechen eines PIN-Dialogs oeffnet den geschuetzten Zielkontext nicht.

Warum wichtig:

Kindersicherung ist sicherheitsrelevant. Uneindeutige PIN-Flows oder zu breite Freigaben koennen geschuetzte Bereiche unbeabsichtigt freigeben.

## 13. Backup- und Restore-QA

Pruefen:

- Backup folgt `prd/PRD-v1/10-backup-import-requirements.md`.
- Standard-Backup exportiert keine geheimen Zugangswerte.
- Verschluesseltes Vollbackup erfordert Passphrase und klaren Hinweis.
- Die Backup-Passphrase wird nicht gespeichert, nicht geloggt und erscheint nicht im Diagnoseexport.
- Eine falsche Vollbackup-Passphrase bricht Restore vor lokalen Datenaenderungen ab.
- Backup-Zusammenfassung zeigt Version, Zeitpunkt, Datenbereiche, Backup-Typ, sensible Daten und Migration.
- Restore ersetzt in v1 immer den Backup-Umfang und zeigt klare Bestaetigung.
- Es gibt keinen Restore-Modus, kein Zusammenfuehren, keinen Import-Konfliktdialog und keine Aktion `Als Kopie importieren`.
- Die PIN-Abfrage vor Restore nutzt die aktuell lokale PIN, wenn lokal Einstellungsschutz aktiv ist oder Backup/Restore per PIN geschuetzt ist; sie nutzt nie eine PIN oder Schutzkonfiguration aus der Backup-Datei.
- Wenn das interne Sicherheitsbackup vor Restore fehlschlaegt, kann der Nutzer abbrechen oder bewusst trotzdem fortsetzen.
- Backup-Import veraendert lokale Daten erst nach bestaetigter Nutzeraktion.
- Fehler beim Import lassen lokale Daten in einem konsistenten Zustand.
- Nach Restore werden Cache-Daten neu aufgebaut statt aus Backup erwartet.
- Nach Restore ist Kindersicherung deaktiviert; PIN-Pruefwerte, aktive PIN-Freigaben und Schutzflags aus dem Backup werden nicht uebernommen.
- Wenn Kindersicherung beim Export aktiv war, wird ein Hinweis zur manuellen Reaktivierung in `Einstellungen > Kindersicherung` angezeigt.
- Quellen mit fehlenden Zugangsdaten werden sichtbar als `Zugangsdaten erforderlich` markiert.
- Backup zeigt die aktuelle Groesse des Medien-Caches und bietet `Cache leeren`.
- Es gibt keine frei konfigurierbare Groesse des Medien-Caches und keine sichtbare Cache-Rotation.
- `Cache leeren` nutzt Bestaetigung und loescht nur Medien-Cache-Dateien fuer Logos, Poster, Staffelbilder und Episodenbilder.
- `Cache leeren` entfernt keine Providerdaten, Favoriten, Verlaeufe, Wiedergabefortschritt, Suchverlauf, EPG-Zuordnungen, Zugangsdaten, EPG-Programmdaten oder aktive Stream-/Timeshift-Puffer.
- Verlauf loeschen bietet Live-TV-Verlauf, Filmverlauf mit Film-Wiedergabefortschritt, Serienverlauf mit Episoden-Wiedergabefortschritt, Suchverlauf und gesamten Verlauf.
- Es gibt keine frei konfigurierbaren Verlaufslimits; der Suchverlauf bleibt auf maximal 20 Eintraege begrenzt.
- Cache- und Verlaufsloeschung nutzen PIN, wenn Einstellungsschutz aktiv ist.

Warum wichtig:

Backup und Restore koennen lokale Daten ueberschreiben. Ohne klare Regeln riskiert Codex Datenverlust, unsichere Exporte oder unklare Konfliktbehandlung.

## 14. Android-TV-Systemintegration-QA

Pruefen:

- Android-TV-Systemsuche zeigt Live-TV, Filme und Serien.
- Android-TV-Systemsuche zeigt keine EPG-Treffer, keine Episoden als eigene Treffer und kein Catch-Up.
- Geschuetzte Inhalte erscheinen nicht in Android-TV-Systemsuche oder Watch Next.
- Deep Links enthalten keine lokalen Room-IDs, Stream-URLs, Tokens oder Zugangswerte.
- Deep-Link-Ziele verwenden stabile fachliche Schluessel.
- Pending Restore-Referenzen werden nicht in Android-TV-Systemsuche oder Watch Next veroeffentlicht.
- Watch Next enthaelt nur Filme und Serienepisoden.
- Externe Player erzeugen keine Watch-Next-Updates.
- Provider-Deaktivierung und Provider-Loeschung entfernen Systemsuche- und Watch-Next-Eintraege.
- Restore, Migration und Kindersicherung-Aenderungen bereinigen Systemsuche und Watch Next.

Warum wichtig:

Android-Systemoberflaechen liegen ausserhalb der normalen App-Navigation. Sie duerfen weder Provider-Isolation noch Kindersicherung umgehen.

## 15. Design-Abgleich vor groesseren UI-PRs

Empfohlene Kurzpruefung:

```text
1. Betroffene Screen-Spec gelesen?
2. Betroffene Interaction-Spec gelesen?
3. Betroffene Component-Spec gelesen?
4. Fokus sichtbar und konsistent?
5. Zurueck-Verhalten korrekt?
6. Loading/Empty/Error sinnvoll?
7. TV-Bedienung ohne Touch moeglich?
8. Keine PRD-Regel durch UI-Entscheidung ueberschrieben?
9. Settings-Optionen an der richtigen Stelle?
10. Optik/Wiedergabe-QA geprueft, falls betroffen?
11. Kindersicherung-QA geprueft, falls betroffen?
12. Backup/Restore-QA geprueft, falls betroffen?
13. Android-TV-Systemintegration geprueft, falls betroffen?
14. 720p, 1080p, 4K und grosse Schrift geprueft, falls UI betroffen?
15. Testnachweis gemaess PRD-Teststrategie dokumentiert?
```

## Ergebnisformat fuer PR-Beschreibungen

Empfohlen, nicht verpflichtend:

```text
Visual QA:
- Screens checked: ...
- Focus checked: yes/no
- Back behavior checked: yes/no
- Empty/loading/error checked: yes/no
- Settings placement checked: yes/no
- Optik/playback checked: yes/no
- Parental controls checked: yes/no
- Backup/restore checked: yes/no
- Android TV system integration checked: yes/no
- 720p/1080p/4K checked: yes/no
- Large font checked: yes/no
- Test strategy evidence: yes/no
- Deviations from docs: none / listed
```
