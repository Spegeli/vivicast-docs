# 02 – Live-TV Mockup Specification

Status: aktive High-Fidelity-Zielbeschreibung v6

Diese Datei beschreibt die visuelle Absicht für Live-TV-Mockups. Exakte Farben, Spacing, Radien, Typografie und Fokuswerte liegen zentral unter `design/design-system/compose-template/`.

## Ziel

Live-TV soll wie ein hochwertiger, schneller Android-TV-Browser wirken:

```text
dunkel
premium
D-Pad-first
starker cyanfarbener Fokus
Provider-Isolation sichtbar
hohe Informationsdichte ohne Gedraenge
```

## Kategorie-Modus

Layout:

```text
Provider/Favoriten/Kategorien | Senderliste | Preview/Details
```

Regeln:

- globale Live-TV-Favoriten stehen oberhalb des ersten Providers
- Provider bleiben sichtbar getrennt
- Kategorien haengen sichtbar unter dem jeweiligen Provider
- Fokus auf Kategorie aktualisiert die Senderliste sofort
- Fokus auf Sender aktualisiert Details, startet aber keine Preview
- Preview startet erst nach OK auf Sender

## Sender-Modus

Layout:

```text
Senderliste | Sender-EPG | Preview/Details
```

Regeln:

- Provider-/Kategorien-Spalte ist ausgeblendet
- ausgewählter Sender bleibt aktiv sichtbar
- aktuelle EPG-Sendung erhält Fokus, sofern vorhanden
- OK auf fokussierter aktueller EPG-Sendung startet Vollbild
- kein direkter Vollbildstart beim ersten OK in der Senderliste
- keine Preview-Einstellung

## Channel Card

Zeigt:

```text
Sendernummer
Senderlogo oder Fallback
Sendername
aktuelles Programm
Zeitbereich
Fortschritt
Favorit
Catch-Up, falls vorhanden
```

## Preview/Details

Zeigt im Kategorie-Modus noch keine laufende Preview, sondern Sender-/Programminformationen.

Zeigt im Sender-Modus eine aktive Preview sowie:

```text
Senderlogo
Sendername
aktuelle Sendung
Zeitbereich
verbleibende Minuten
Beschreibung
nächste Sendung
Streamstatus
```

## Visuelle Quelle

Aktuelle Zielbilder:

```text
design/mockups/high-fidelity/rendered/02_live_tv_browser_category_mode.png
```

Konkrete Tokenquelle:

```text
design/design-system/compose-template/
```
