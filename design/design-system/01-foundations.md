# 01 – Design Foundations

Status: verbindliche Design-Referenz v2

## Ziel

Dieses Dokument definiert die Grundregeln für das Vivicast-Interface auf Android TV.

Vivicast wird für eine Fernsehoberfläche entwickelt. Das bedeutet:

- Bedienung aus mehreren Metern Entfernung
- Navigation über D-Pad statt Touch
- begrenzte Eingabemöglichkeiten
- große Inhaltsmengen
- häufige Nutzung im Vollbildmodus
- klare Rückkehrwege über die Zurück-Taste

## Grundprinzipien

### 1. Fokus vor Dekoration

Der aktuelle Fokuszustand ist das wichtigste visuelle Element der App.

Jeder Screen muss sofort beantworten:

```text
Wo bin ich?
Was ist ausgewählt?
Was passiert bei OK?
Wie komme ich zurück?
```

### 2. Wenige Ebenen

Android-TV-Nutzer sollen Inhalte mit wenigen Tastendrücken erreichen.

Deshalb gilt:

- keine verschachtelten Menüs ohne klaren Grund
- keine modalen Dialoge für normale Navigation
- keine unnötigen Zwischenansichten
- wichtige Aktionen direkt am fokussierten Inhalt anzeigen

### 3. Achsen klar verwenden

D-Pad-Navigation braucht vorhersehbare Richtungen.

Standardregel:

```text
Vertikal   = Bereiche, Kategorien, Listenwechsel
Horizontal = Inhalte innerhalb eines Bereichs
OK         = öffnen, abspielen, bestätigen
Zurück     = Overlay schließen oder eine Ebene zurück
```

### 4. Inhalte zuerst

Die App ist keine Einstellungs-App. Live-TV, Filme und Serien haben visuell Vorrang.

Einstellungen, Wartung, Backup und Diagnose sind klar erreichbar, dürfen aber nicht die Hauptnavigation dominieren.

### 5. Provider-Isolation sichtbar machen

Provider werden fachlich niemals zusammengeführt. Das UI muss diese Trennung ebenfalls zeigen.

Regeln:

- Provider sind eigenständige Navigationsgruppen.
- Kategorien hängen sichtbar unter ihrem Provider.
- Live-TV Favoriten sind global oberhalb des ersten Providers sichtbar.
- Favoriten-Eintraege bleiben intern providergebunden und duerfen bei Bedarf Provider-Kontext anzeigen.
- Gleiche Sendernamen bei mehreren Providern werden nicht optisch verschmolzen.

### 6. Degradation ist normal

IPTV-Daten sind oft unvollständig.

Fehlende Daten dürfen nie wie Fehler wirken.

Fallbacks:

```text
Fehlendes Senderlogo   -> TV-Icon + Sendername
Fehlendes Poster       -> Kein Bild verfügbar
Fehlendes EPG          -> Keine Programminformationen verfügbar
Fehlende Beschreibung  -> Bereich ausblenden oder Kurzfallback anzeigen
Fehlende Kategorie     -> Nicht kategorisiert
```

### 7. Performance sichtbar berücksichtigen

Das Design darf keine vollständige Darstellung großer Bibliotheken voraussetzen.

Pflicht:

- Listen und Raster müssen lazy gedacht sein.
- Sichtbare Bereiche sollen begrenzt bleiben.
- Keine UI, die voraussetzt, dass 50.000 Filme gleichzeitig im UI-State liegen.
- Kein Design, das permanente Autovorschau beim Fokus verlangt.

## Bildschirmgrundlage

Primäres Ziel ist 16:9-TV.

Referenzauflösung für Design-Spezifikationen:

```text
1920 x 1080
```

Alle Abstände werden in `dp` angegeben und müssen auf 4K-Geräten sauber skalieren.

## Safe Area

Wichtige UI-Elemente dürfen nicht direkt an Bildschirmkanten kleben.

Standard-Safe-Area:

```text
Horizontal: 48 dp
Vertikal:   32 dp
```

Minimal-Safe-Area für Player-Overlays:

```text
Horizontal: 48 dp
Vertikal:   40 dp
```

## Layout-Raster

Basiseinheit:

```text
8 dp
```

Alle Abstände, Höhen und Radien verwenden Vielfache von 4 oder 8 dp.

Standard-Abstände:

```text
4 dp    sehr kleiner Abstand
8 dp    kleiner Abstand
16 dp   normaler Abstand
24 dp   Abschnittsabstand
32 dp   großer Abstand
48 dp   Screen-Struktur
64 dp   TV-Safe-Area / Hauptspalte
```

## Screen-Struktur

### Live-TV

Primärlayout:

```text
Provider/Kategorien | Senderliste | EPG/Vorschau
```

Ziel:

- sehr schnelle Senderauswahl
- EPG sichtbar ohne zusätzlichen Klick
- Vorschau erst nach OK, nicht beim Fokus

### Filme und Serien

Primärlayout:

```text
Provider-/Kategorien-Spalte | Hero-Informationsbereich + Poster-Raster
```

Ziel:

- Provider- und Kategorienstruktur bleibt sichtbar getrennt
- Informationsbereich aktualisiert sich beim Fokus auf Poster
- Poster-Raster bleibt performant
- Detailseite nur bei Bedarf

### Suche

Primärlayout:

```text
Suchfeld oben
Suchverlauf / Vorschläge
Ergebnisbereiche horizontal
```

Ziel:

- lokale Live-Suche
- 300-ms-Debounce
- klare Trennung nach Kanälen, Filmen, Serien und EPG

### Einstellungen

Primärlayout:

```text
Bereichsliste links
Optionen rechts
```

Ziel:

- viele Einstellungen ohne Überladung
- klare zweite Ebene
- destructive actions nur mit Sicherheitsdialog

### Player

Primärzustand:

```text
Vollbild ohne sichtbare UI
```

Overlay öffnet durch:

```text
OK
```

Overlay schließt durch:

```text
Zurück
```

## Barrierearme Lesbarkeit

Mindestregeln:

- Keine wichtigen Texte unter 16 sp.
- Primäre Navigation mindestens 18 sp.
- Titel und Hero-Texte deutlich größer.
- Kontrast zwischen Text und Hintergrund hoch halten.
- Fokus nicht allein über Farbe darstellen, sondern zusätzlich über Größe, Rahmen oder Fläche.

## Bewegung

Animationen sind unterstützend, nicht dekorativ.

Erlaubt:

- Fokus-Skalierung
- kurze Fade-Übergänge
- leichte Panel-/Overlay-Bewegungen
- Fortschrittsanimationen

Nicht erlaubt:

- lange Introanimationen
- verspielte Bewegungen bei Listen
- Animationen, die D-Pad-Navigation verzögern
- automatische Videovorschau beim bloßen Fokus

## Grundton

Vivicast soll ruhig, schnell und technisch sauber wirken.

Nicht gewünscht:

- bunte Streaming-Portal-Überladung
- übertriebene Glas-/Blur-Effekte
- UI-Elemente ohne klare Funktion
- Touch-App-Optik auf TV übertragen

Gewünscht:

- dunkle Flächen
- starke Fokusmarkierung
- hohe Informationsdichte ohne Gedränge
- klare Typografie
- stabile, vorhersehbare Navigation
