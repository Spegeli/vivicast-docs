# List And Grid Items

Status: verbindlich v8

## Zweck

Diese Datei beschreibt wiederverwendbare Elemente für Listen, Reihen und Grids.

Sie gilt für Live-TV, Filme, Serien, Episoden und Suche.

## Grundregeln

- Fokus muss klar sichtbar sein.
- Fokus darf nicht nur über Farbe erkennbar sein.
- Vivicast nutzt einen globalen Fokus-Stil.
- Fokus kombiniert Scale, Ring und leichte Schattenhebung.
- Fehlende Bilder nutzen Fallbacks.
- Lange Titel werden kontrolliert gekuerzt.
- Fokussierte Elemente dürfen mehr Informationen zeigen als nicht fokussierte.
- Komponenten enthalten keine fachliche Datenlogik.

## Loading

Loading States verwenden bevorzugt Skeleton Cards oder Skeleton Rows.

Spinner sind erlaubt, wenn Skeletons nicht sinnvoll sind oder ein kurzer globaler Prozess angezeigt wird.

## Fehler

Fehler werden inline im betroffenen Bereich angezeigt.

Fullscreen-Fehler werden nur genutzt, wenn der komplette Screen nicht nutzbar ist.

## Empty States

Empty States enthalten eine klare Aktion, wenn eine sinnvolle Aktion existiert.

Beispiele:

- keine Wiedergabeliste -> Wiedergabeliste hinzufügen
- keine EPG-Quelle -> EPG-Quelle hinzufügen
- keine Suchergebnisse -> nur Hinweis

## Channel Card

Channel Cards werden kontextabhängig verwendet.

Verwendung:

- Live-TV Senderliste
- Suchergebnisse für Kanäle
- zuletzt gesehene Live-TV-Sender im Home-Bereich

### Live-TV Senderliste

Informationsreich:

- Sendernummer, falls vorhanden
- Logo oder Fallback
- Sendername
- aktuelles Programm, falls vorhanden
- Fortschritt
- Favoritenstatus
- Catch-Up-Hinweis, falls vorhanden

Interaktion in der Live-TV-Senderliste:

- blosses Fokussieren aktualisiert Informationen, startet aber keinen Stream
- erstes OK aktiviert den Sender-Modus, oeffnet die EPG-Spalte, startet die Live-Vorschau und verschiebt den Fokus auf die aktuelle Sendung, sofern vorhanden
- OK auf der fokussierten aktuellen Sendung oeffnet Vollbild für den ausgewählten Sender
- dieser Ablauf ist fest und besitzt keine Preview-Einstellung

### Suche: Kanal-Ergebnisse

Mittlere Informationsdichte:

- Logo oder Fallback
- Sendername
- Provider oder Kategorie
- aktuelles Programm, falls vorhanden
- optionaler Favoritenstatus

### Home zuletzt gesehene Live-TV-Sender

Kompakt:

- Logo oder Fallback
- Sendername
- aktuelles Programm, falls vorhanden

## Poster Card

Filme und Serien verwenden denselben Poster-Card-Stil.

Verwendung:

- Filme
- Serien
- Suchergebnisse für VOD
- Fortsetzen im Home-Bereich

Inhalte:

- Poster oder Fallback
- Titel
- Jahr oder Zusatzinfo, falls vorhanden
- Bewertung, falls vorhanden
- Fortschritt, falls vorhanden und nicht abgeschlossen
- Gesehen-Status, falls abgeschlossen
- Favoritenstatus

## Episode Row

Verwendung:

- Serien-Detailseite

Episode Rows werden nicht für Suchergebnisse verwendet.

Inhalte:

- Episodennummer
- Titel oder Fallback
- Laufzeit, falls vorhanden
- Fortschritt, solange nicht abgeschlossen
- Gesehen-Status, falls abgeschlossen
- Thumbnail oder Icon, falls vorhanden
- Markierungsaktion nur bei fokussierter Episode

Interaktion auf der Serien-Detailseite:

- OK auf der Episode startet oder setzt fort.
- Rechts aus der fokussierten Episode wechselt auf `Als gesehen markieren` oder `Als ungesehen markieren`.
- Links aus der Markierungsaktion kehrt zur Episode zurück.
- Die Aktion bleibt bei nicht fokussierten Rows ausgeblendet.
- Es gibt keine Staffel- oder Serien-Markierungsaktion.

## Search Result Card

Verwendung:

- Suche

Inhalte:

- Ergebnistyp
- Titel
- Provider oder Kontext
- Miniatur oder Icon
- primaere Aktion

## Empty Fallbacks

Fehlende Bilder:

- neutraler Gradient
- Icon-Fallback
- Titel als Textanker

Fehlende Metadaten:

- Feld auslassen
- keine geratenen Werte anzeigen
