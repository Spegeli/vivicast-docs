# 05 – Settings and Search Mockup Specification

Status: aktive High-Fidelity-Zielbeschreibung v7

Diese Datei beschreibt die visuelle Absicht für Suche und Einstellungen. Exakte Farben, Spacing, Radien, Typografie und Fokuswerte liegen zentral unter `design/design-system/compose-template/`.

## Suche

Struktur:

```text
Top Navigation
Suchfeld + Mikrofon
Suchverlauf
Ergebnisgruppen
Detailbereich für fokussiertes Ergebnis
```

Ergebnisgruppen:

```text
Kanäle
Filme
Serien
EPG
```

Regeln:

- Suchverlauf bleibt dauerhaft sichtbar
- keine eigene Episoden-Gruppe
- keine verpflichtende `Alle anzeigen`-Aktion
- Ergebnisgruppen sind horizontale Rows
- fokussierte Ergebniscard besitzt klaren cyanfarbenen Fokus

## Einstellungen

Struktur:

```text
Top Navigation
Einstellungsgruppen links
Optionsliste rechts
Detail-/Dialogbereich
```

Settings-Gruppen:

```text
Allgemein
Wiedergabelisten
EPG
Optik
Wiedergabe
Kindersicherung
Speicher & Verlauf
Backup
Über die App
```

Settings Row zeigt:

```text
Icon
Titel
kurzer Hilfetext
rechtsbuendiger Wert oder Status
Chevron / Toggle / Aktion
```

## Speicher & Verlauf

Eigener Settings-Bereich für:

```text
Medien-Cache
Cache leeren
Verlauf löschen
```

Nicht Teil der Backup-Gruppe.

## Backup

Backup enthält:

```text
Backup exportieren
Backup importieren
Backup-Ziel
Letzte Sicherung
Vorhandene Backups verwalten
```

Kein Cache und kein Verlauf.

## Visuelle Quellen

Aktuelle Zielbilder:

```text
design/mockups/high-fidelity/rendered/05_search.png
design/mockups/high-fidelity/rendered/07_settings_general.png
design/mockups/high-fidelity/rendered/08_settings_playlists.png
design/mockups/high-fidelity/rendered/09_settings_epg.png
design/mockups/high-fidelity/rendered/10_settings_appearance.png
design/mockups/high-fidelity/rendered/11_settings_playback.png
design/mockups/high-fidelity/rendered/12_settings_parental_controls_pin_dialog.png
design/mockups/high-fidelity/rendered/13_settings_backup_export_dialog.png
```

Konkrete Tokenquelle:

```text
design/design-system/compose-template/
```


## Label-Konsistenz

Sichtbare Favoriten-Aktionen verwenden `Zu Favoriten` oder `Zu Favoriten hinzufügen`. Merkliste-/Meine-Liste-Terminologie ist in v1 nicht zulässig.
