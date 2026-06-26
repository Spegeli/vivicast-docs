# 01 – High-Fidelity Visual Target

Status: verbindliche visuelle Zielreferenz v2

## Ziel

Dieses Dokument definiert die hochwertige UI-Richtung fuer Vivicast auf Basis der aktuellen High-Fidelity-Renderings.

Die aktiven Bilder liegen unter:

```text
design/mockups/high-fidelity/rendered/
```

Low-Fidelity-Mockups sind keine aktive Referenz mehr.

## Zielwirkung

Vivicast soll wirken wie eine moderne Premium-App fuer Android TV:

```text
hochwertig
ruhig
klar
schnell
TV-tauglich
inhaltzentriert
technisch sauber
```

Nicht gewuenscht:

```text
Admin-Oberflaeche
technische Tabellenansicht
Web-App-Optik
Mobile-App-Optik
billiger Listenbrowser
Debug-Oberflaeche
```

## Visuelle Richtung

```text
dunkler atmosphaerischer Hintergrund
weiche dunkle Panels
subtile Tiefe
klare grosse Typografie
cyanfarbener Fokus mit Ring und Glow
hochwertige Poster- und Channel-Cards
ruhige Action-Pills
starke Lesbarkeit aus TV-Entfernung
```

## Fokus

Fokus muss sofort sichtbar sein durch:

```text
cyanfarbenen Fokusrahmen
weichen Glow
leichte Skalierung
angehobene Flaeche
klaren Kontrast
```

Fokus darf nicht nur ueber eine kleine Farbaenderung dargestellt werden.

## Screen-Ziele

Home:

```text
Hero-Bereich
Fortsetzen-Row
zuletzt gesehene Live-TV-Sender
ruhige Premium-TV-Startseite
```

Live-TV:

```text
moderner dreispaltiger TV-Browser
Provider/Kategorien links
Sender als hochwertige Cards
Preview/Details rechts
kein Tabellenlook
```

Player:

```text
filmisches Vollbild
reduziertes Bottom Overlay
Timeline als zentrales Bedienelement
Stream-Badges
keine permanente UI
```

Filme und Serien:

```text
Hero-Bereich mit Atmosphaere
hochwertige Poster-Cards
klare Provider-/Kategorie-Trennung
Detailseiten mit starker Primaeraktion
```

Suche:

```text
grosses Suchfeld
dauerhaft sichtbarer Suchverlauf
Ergebnisgruppen Kanäle, Filme, Serien und EPG
hochwertiger Fokuszustand
```

Einstellungen:

```text
Master-Detail Layout
ruhige Optionskarten
klare Werte rechts
kritische Aktionen klar getrennt
TV-taugliche Dialoge
```

## Qualitätskriterien

High-Fidelity-Mockups sind nur brauchbar, wenn:

```text
sie wie eine hochwertige Android-TV-App wirken
Fokus sofort sichtbar ist
TV-Lesbarkeit erhalten bleibt
Android-TV-Navigation nachvollziehbar bleibt
Provider-Isolation nicht verletzt wird
Design spaeter in Jetpack Compose for TV realistisch umsetzbar ist
```

## Technische Umsetzungshilfe

Die technische Ableitung der aktuellen Zieloptik liegt unter:

```text
design/design-system/compose-template/
```

Diese Dateien konkretisieren Farben, Typografie, Spacing, Shapes und Fokuswerte fuer eine spaetere Compose-Umsetzung.
