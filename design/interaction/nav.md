# Navigation Reference

Status: verbindlich v4

## Hauptbereiche

- Home
- Live-TV
- Filme
- Serien
- Suche
- Einstellungen

Diese Hauptbereiche sind final.

## App-Start

Der Startbereich ist global unter `Einstellungen > Allgemein` einstellbar.

Sichtbare Werte:

```text
Home
Live-TV
Filme
Serien
```

Standard:

```text
Home
```

Beim naechsten regulaeren App-Start ohne explizites Ziel oeffnet Vivicast den gespeicherten Startbereich. Eine Aenderung der Einstellung navigiert die laufende Sitzung nicht sofort um.

Der App-Autostart verwendet denselben Startbereich. Rueckkehr aus dem Hintergrund behaelt den aktuellen Screen und gilt nicht als neuer Start.

Deep Links, globale Android-TV-Suchergebnisse, Watch Next und andere explizite Systemziele haben Vorrang und oeffnen ihr konkretes Ziel.

Explizite Systemziele verwenden stabile fachliche Ziel-IDs und keine lokalen Room-IDs.

EPG-Kontext aus interner Suche ist kein Android-TV-Systemsuchziel, darf aber intern Live-TV im Sender-Modus mit fokussiertem Programmpunkt oeffnen.

Fehlt das Ziel, ist es pending, geloescht, der Provider deaktiviert oder sind Zugangsdaten erforderlich, zeigt Vivicast einen kontrollierten Fehler- oder Nicht-verfuegbar-Zustand. Es gibt keinen stillen Fallback auf Home.

Ist das Ziel durch Kindersicherung geschuetzt, oeffnet Vivicast zuerst die PIN-Abfrage. Abbrechen fuehrt in den vorherigen sicheren Kontext oder beendet den externen Einstieg ohne den geschuetzten Inhalt zu zeigen.

Fehlen im gewaehlten Bereich Inhalte, erscheint dessen normaler Empty State. Es gibt keinen automatischen Fallback auf Home.

Suche und Einstellungen sind nicht als Startbereich waehbar.

Home ist fester Bestandteil von Vivicast und bleibt der Standard-Startscreen.

## Top Navigation

Die Hauptnavigation ist dauerhaft oben sichtbar, ausser im Player.

Wenn der Fokus innerhalb eines Hauptscreens liegt, springt `Zurück` zuerst in die Top Navigation.

Erst wenn der Fokus bereits in der Top Navigation liegt, greift die Exit-Regel.

## Exit-Regel

Beenden erfolgt ueber doppelte Zurück-Bestätigung aus der Top Navigation.

## Fokus beim Bereichswechsel

Beim Wechsel zwischen Hauptbereichen wird der letzte Fokus des verlassenen Bereichs nicht wiederhergestellt.

Beispiel:

```text
Live-TV -> Serien -> Live-TV
```

Live-TV startet wieder mit seinem definierten Initialzustand.

## Suche verlassen

Wenn die Suche vollstaendig verlassen wird, springt der Fokus zuerst in die Top Navigation.

## Player Rueckkehr

Nach `Zurück` aus dem Player gilt:

- Live-TV kehrt zum fokussierten Sender zurueck
- Film kehrt zur Film-Detailseite zurueck
- Serie kehrt zur Episode oder Serien-Detailseite zurueck

Bei Einstieg ueber Deep Link, Android-TV-Suche oder Watch Next wird ein passender Herkunftskontext aufgebaut:

- Senderziel: Live-TV mit Provider/Senderkontext
- Filmziel: Film-Detailseite oder Player-Rueckkehr zur Film-Detailseite
- Serienziel: Serien-Detailseite
- Episodenziel: Serien-Detailseite mit Staffel-/Episodenkontext oder Player-Rueckkehr dorthin

## Overlay-Regel

Overlays und Dialoge werden vor Screen-Navigation geschlossen.

## Fullscreen-Kontext

Der Player ist ein eigener Fullscreen-Kontext ohne dauerhaft sichtbare Top Navigation.
