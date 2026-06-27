# 02 – Design Tokens

Status: verbindliche Design-Referenz v2

Dieses Dokument definiert die zentralen visuellen Tokens für Vivicast.

Die Tokens koennen spaeter als nicht-normativer Umsetzungshinweis fuer ein App-Repo-Designsystem-Modul dienen, zum Beispiel:

```text
:core:designsystem
```

Die konkrete Modulstruktur bleibt App-Repo- oder Codex-Baseline und ist keine Designanforderung dieser Datei.

## Token-Prinzipien

1. Dunkles TV-Theme als Standard.
2. Akzentfarbe konfigurierbar, aber Fokus immer ausreichend kontrastreich.
3. Keine wichtigen Zustände nur über Farbe darstellen.
4. Große Schrift und klare Abstände für TV-Entfernung.
5. Alle Werte müssen in Jetpack Compose for TV zentral abbildbar sein.

---

# Farben

## Standard Theme

Aktive technische Tokenwerte stehen im Compose-Template unter `design/design-system/compose-template/`.

```text
Name                         Hex        Zweck
--------------------------------------------------------------------
vc_background                #050914    App-Hintergrund
vc_background_deep           #020617    tiefster Hintergrund
vc_background_elevated       #07111F    leicht angehobene Bereiche
vc_surface                   #0B1628    Karten, Panels, Listenflächen
vc_surface_high              #101D33    fokussierbare Flächen ruhend
vc_surface_focus             #102B46    fokussierte Flächen
vc_surface_pressed           #15395C    gedrückte Flächen
vc_surface_disabled          #11182780  deaktivierte Flächen

vc_panel                     #CC07111F  Panels / Sidebar / Overlay
vc_panel_strong              #E60B1628  stärkere Panels
vc_dialog                    #F20B1628  Dialoge

vc_border                    #22334A    Standard-Rahmen
vc_border_soft               #17263A    dezenter Rahmen

vc_text_primary              #F8FAFC    Haupttext
vc_text_secondary            #CBD5E1    Sekundärtext
vc_text_tertiary             #94A3B8    Metadaten
vc_text_disabled             #64748B    deaktivierter Text
vc_text_on_accent            #031525    Text auf Akzentfläche

vc_accent                    #00C8FF    Standard-Akzent
vc_accent_strong             #38D5FF    starker Akzent
vc_focus_ring                #00D4FF    Fokusrahmen
vc_focus_glow                #6600BFFF  Fokusglow
vc_progress                  #159BFF    Wiedergabefortschritt

vc_success                   #22C55E    Erfolg / aktiv
vc_warning                   #F59E0B    Warnung
vc_error                     #EF4444    Fehler / destruktiv
vc_info                      #38BDF8    Information

vc_live                      #FF2D3A    Live-Indikator
vc_favorite                  #FACC15    Favorit
vc_catchup                   #0EA5E9    Catch-Up
```

## Hintergrundregel

Standard:

```text
App-Hintergrund: vc_background / vc_background_deep
Panels:          vc_panel oder vc_surface
Karten:          vc_surface
Fokus:           vc_surface_focus + vc_focus_ring + vc_focus_glow
```

Keine rein schwarzen Vollflächen außer im Player.

## Transparenz

Benutzerkonfiguration laut PRD:

```text
0 %
25 %
50 %
```

Token-Mapping:

```text
0 %    Alpha 1.00
25 %   Alpha 0.75
50 %   Alpha 0.50
```

Wichtig:

- Textflächen dürfen nie unter ausreichenden Kontrast fallen.
- Player-Overlays verwenden maximal 50 % Transparenz für Textbereiche.
- Dialoge verwenden keine starke Transparenz.

---

# Typografie

## Font

Standard:

```text
System Font
```

Keine externe Font-Pflicht.

## Textgrößen

```text
Name                  Größe      Gewicht      Zweck
--------------------------------------------------------------------
vc_display_large      40 sp      Bold         Hero-Titel / Detailtitel
vc_display_medium     34 sp      Bold         Screen-Titel
vc_title_large        28 sp      SemiBold     Bereichstitel
vc_title_medium       24 sp      SemiBold     Karten-/Paneltitel
vc_title_small        20 sp      SemiBold     Listenüberschriften

vc_body_large         20 sp      Regular      Haupttext TV
vc_body_medium        18 sp      Regular      Listen, Einstellungen
vc_body_small         16 sp      Regular      Metadaten, Zusatzinfos

vc_label_large        18 sp      SemiBold     Buttons, Tabs, Chips
vc_label_medium       16 sp      SemiBold     Badges, Status
vc_label_small        14 sp      Medium       kleine technische Labels
```

## Zeilenhöhe

```text
40 sp  -> 48 sp
34 sp  -> 42 sp
28 sp  -> 36 sp
24 sp  -> 32 sp
20 sp  -> 28 sp
18 sp  -> 26 sp
16 sp  -> 24 sp
14 sp  -> 20 sp
```

## Schriftgrößen-Konfiguration

Laut PRD:

```text
Klein
Mittel
Groß
Sehr groß
```

Mapping:

```text
Klein      0.90x
Mittel     1.00x
Groß       1.12x
Sehr groß  1.25x
```

Grenze:

- Layouts dürfen bei `Sehr groß` nicht brechen.
- Lange Texte werden gekürzt, nicht gequetscht.
- Detailtexte dürfen scrollen.

---

# Abstände

## Spacing Scale

```text
vc_space_0       0 dp
vc_space_1       4 dp
vc_space_2       8 dp
vc_space_3      12 dp
vc_space_4      16 dp
vc_space_5      24 dp
vc_space_6      32 dp
vc_space_7      40 dp
vc_space_8      48 dp
vc_space_9      64 dp
vc_space_10     80 dp
```

## Standard Padding

```text
Screen horizontal:     48 dp
Screen vertical:       32 dp
Panel innen:           24 dp
Karte innen:           16 dp
Button horizontal:     24 dp
Button vertical:       12 dp
Listenitem horizontal: 16 dp
Listenitem vertical:   12 dp
```

---

# Größen

## Navigation

```text
Top Navigation Hoehe:             72 dp
Top Navigation Item Mindestbreite: 96 dp
Top Tabs Hoehe:                    64 dp
```

## Live-TV

```text
Provider-Spalte:       300 dp
Senderliste:           620 dp
EPG/Vorschau-Spalte:   Restbreite
Sender-Item Höhe:      96 dp
Senderlogo:            64 x 40 dp
Progressbar Höhe:       6 dp
```

## VOD

```text
Poster klein:          132 x 198 dp
Poster standard:       160 x 240 dp
Poster groß:           192 x 288 dp
Poster Fokus-Skalierung: 1.06x
Poster Abstand:        24 dp
Hero Höhe:             260 dp
```

## Dialoge

```text
Dialog Breite klein:    520 dp
Dialog Breite standard: 720 dp
Dialog Breite groß:     900 dp
```

## Player Overlay

```text
Bottom Overlay Höhe Live-TV: 300 dp
Bottom Overlay Höhe VOD:     240 dp
Controls Höhe:               72 dp
```

---

# Radien

```text
vc_radius_none       0 dp
vc_radius_small      8 dp
vc_radius_medium    12 dp
vc_radius_large     16 dp
vc_radius_xlarge    24 dp
vc_radius_pill      999 dp
```

Standard:

```text
Buttons:       12 dp
Karten:        16 dp
Poster:        12 dp
Panels:        24 dp
Dialoge:       24 dp
Chips/Tabs:    999 dp
```

---

# Fokus

## Fokus-Tokens

```text
vc_focus_scale_small       1.02
vc_focus_scale_card        1.06
vc_focus_scale_button      1.03
vc_focus_ring_width        2 dp
vc_focus_ring_gap          3 dp
vc_focus_glow_elevation    18 dp
vc_focus_animation         120 ms
```

## Fokus-Kombination

Standard für Karten:

```text
Skalierung + Rahmen + leichte Schattenfläche
```

Standard für Listenitems:

```text
Hintergrundwechsel + Rahmen links oder vollständiger Rahmen
```

Standard für Buttons:

```text
Akzentfläche + Text auf Akzent + Skalierung
```

---

# Elevation und Schatten

```text
vc_elevation_0      keine
vc_elevation_1      ruhende Karte
vc_elevation_2      fokussierte Karte
vc_elevation_3      Overlay / Dropdown
vc_elevation_4      Dialog
```

TV-Regel:

- Schatten nie als einziger Fokusindikator.
- Schatten zurückhaltend nutzen, da TV-Panels Kontrast unterschiedlich darstellen.

---

# Animationen

## Dauer

```text
vc_motion_instant      0 ms
vc_motion_fast       120 ms
vc_motion_normal     180 ms
vc_motion_slow       260 ms
```

## Standard

```text
Fokuswechsel:       120 ms
Overlay öffnen:     180 ms
Dialog öffnen:      180 ms
Screenwechsel:      180 ms
Progress:           linear
```

## Benutzerkonfiguration

Laut PRD:

```text
Aus
Langsam
Normal
Schnell
```

Mapping:

```text
Aus       0 ms
Schnell   0.75x
Normal    1.00x
Langsam   1.35x
```

## Easing

```text
Fokus:      FastOutSlowIn
Overlay:    FastOutSlowIn
Progress:   Linear
```

---

# Icons

## Stil

```text
Outlined / Rounded
```

Regeln:

- Icons immer mit Text kombinieren, wenn die Aktion nicht eindeutig ist.
- Reine Iconbuttons nur fuer sehr bekannte Aktionen: Suche, Mikrofon, Favorit, Audio, Untertitel.
- Destruktive Aktionen zusätzlich textlich benennen.

## Standardgrößen

```text
Icon klein:     20 dp
Icon normal:    24 dp
Icon groß:      32 dp
Icon Player:    40 dp
```

---

# Status-Badges

```text
Aktiv                     vc_success
Aktualisierung läuft       vc_info
Verbindungsfehler          vc_error
Anmeldedaten ungültig      vc_error
Abgelaufen                 vc_warning
Deaktiviert                vc_text_tertiary
Live                       vc_live
Catch-Up                   vc_catchup
Favorit                    vc_favorite
```

Badges müssen auch ohne Farbe verständlich bleiben, also immer Text oder Symbol plus Tooltip-/Label-Kontext verwenden.

---

# Compose-Namensschema

Empfohlene Benennung für spätere Implementierung:

```text
VivicastColors
VivicastTypography
VivicastSpacing
VivicastShapes
VivicastMotion
VivicastFocus
VivicastTheme
```

Beispiel:

```text
VivicastTheme {
    AppNavHost()
}
```

---

# Anpassbarkeit

Benutzer dürfen laut PRD Farben, Transparenz, Animationen und Schriftgröße anpassen.

Nicht anpassbar:

- Grundlayoutachsen
- D-Pad-Navigation
- Mindestkontraste
- Fokusindikator-Pflicht
- Safe-Area
- Mindestgrößen fokussierbarer Elemente

Konfigurierbare Optik darf keine Bedienbarkeit zerstören.
