# High-Fidelity Rendered Images

Status: active v3

Dieser Ordner enthält die aktuellen gerenderten High-Fidelity-Bilder für Vivicast.

## Rolle

Gerenderte PNGs sind visuelle Zielreferenzen.

Sie sind keine normative Quelle für:

- Produktumfang
- Navigation
- sichtbare Labels
- UI-Texte
- Fokuspfade
- Datenmodell
- Architektur
- Sicherheitsverhalten
- Backup/Restore-Verhalten
- PIN-Regeln
- Playback-Regeln

Bei Konflikten gelten die aktiven Markdown-Quellen nach `DOCS-GOVERNANCE.md`.

Renderings v3 sind an die aktiven Markdown-Quellen angeglichen: Player-Aktionen verwenden `Mehr` statt `EPG`, VOD-/Suchaktionen verwenden Favoriten-Terminologie statt Merkliste-/Meine-Liste-Terminologie.

## Aktuelle Renderings

```text
01_home.png
02_live_tv_browser_category_mode.png
03_movies_overview.png
04_series_detail.png
05_search.png
06_player_overlay_live_tv.png
07_settings_general.png
08_settings_playlists.png
09_settings_epg.png
10_settings_appearance.png
11_settings_playback.png
12_settings_parental_controls_pin_dialog.png
13_settings_backup_export_dialog.png
```

## Abdeckung

```text
01 Home
02 Live-TV Browser Kategorie-Modus
03 Filme Übersicht
04 Serien Detailseite
05 Suche
06 Player Overlay Live-TV
07 Einstellungen Allgemein
08 Einstellungen Wiedergabelisten
09 Einstellungen EPG
10 Einstellungen Optik
11 Einstellungen Wiedergabe
12 Einstellungen Kindersicherung mit PIN-Dialog
13 Einstellungen Backup Export Dialog
```

## Aufloesung und Artboard

Die aktuellen PNGs sind 16:9-Referenzen und nicht zwingend exakt `1920 x 1080`.

Technische Designreferenz bleibt:

```text
1920 x 1080
16:9
dp/sp-basierte Compose-Umsetzung
```

## Bekannte Interpretationsregel

Sichtbare Beispielwerte in PNGs, zum Beispiel konkrete EPG-Intervallwerte, Statuszeiten oder Beispielinhalte, sind Demo-Daten.

Verbindliche Defaultwerte und sichtbare Optionslisten stehen in PRD, Screen Specs, Wireframes und Settings-Vertrag.

## Regeln

- Keine Low-Fidelity-Bilder in diesem Ordner ablegen.
- Keine historischen Renderings in diesem Ordner ablegen.
- Neue aktive High-Fidelity-Bilder müssen konsistent benannt und in dieser README nachgezogen werden.
- PNGs konkretisieren die visuelle Zieloptik, aber nicht Produktlogik oder sichtbare Texte, falls sie aktuellen Markdown-Quellen widersprechen.

## Verwandte Umsetzungsvorlage

```text
design/design-system/compose-template/
```

Die Compose-Template-Dateien konkretisieren die visuelle Zielrichtung in Tokens und Kotlin-Vorlagen für spätere App-Umsetzung.
