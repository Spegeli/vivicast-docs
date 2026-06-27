# 07 - Wireframe: Dialoge und globale Zustaende

Status: verbindlich v2

## Zweck

Dieses Dokument definiert wiederkehrende Dialoge, Empty States, Error States, Loading States und globale Hinweise.

## Dialog-Grundlayout

```text
+---------------------------------------------+
| Titel                                       |
|                                             |
| Beschreibung / Kontext                      |
|                                             |
| (*)[Primaeraktion] [Sekundaeraktion]        |
+---------------------------------------------+
```

## Fokusregeln

```text
Info-Dialog          -> Primaeraktion
Fehler               -> Erneut versuchen, wenn sinnvoll
Destruktive Aktion   -> Abbrechen
PIN Eingabe          -> erstes Feld
Auswahldialog        -> aktiver Wert
```

BACK schliesst Dialoge, wenn der Zustand abbrechbar ist.

## Bestätigung

```text
+---------------------------------------------+
| Aktion bestätigen                           |
| Möchtest du diese Aktion ausführen?        |
| (*)[Bestätigen] [Abbrechen]                 |
+---------------------------------------------+
```

## Löschen mit optionalem PIN-Schutz

```text
+---------------------------------------------+
| Wirklich löschen?                           |
| Diese Aktion kann nicht rueckgaengig werden. |
| (*)[Abbrechen] [Löschen]                    |
+---------------------------------------------+
```

Wenn Einstellungsschutz aktiv ist:

```text
+---------------------------------------------+
| PIN eingeben                                 |
| (*)[ ] [ ] [ ] [ ]                           |
| [1] [2] [3]                                  |
| [4] [5] [6]                                  |
| [7] [8] [9]                                  |
|     [0] [Löschen]                           |
| [Abbrechen]                                  |
+---------------------------------------------+
```

## Empty States

Grundregel:

```text
Empty States enthalten eine Aktion, wenn eine sinnvolle Aktion existiert.
```

Beispiele:

```text
Keine Wiedergabelisten vorhanden
(*)[Wiedergabeliste hinzufügen]
```

```text
Keine EPG-Quellen vorhanden
(*)[EPG-Quelle hinzufügen]
```

```text
Keine Suchergebnisse gefunden
Versuche einen anderen Suchbegriff.
```

## Loading States

```text
Skeleton Cards / Skeleton Rows bevorzugt
Spinner nur wenn Skeleton nicht sinnvoll ist
Navigation nicht blockieren, wenn vorhandene Daten nutzbar sind
```

## Error States

```text
Inline im betroffenen Bereich
Fullscreen nur, wenn kompletter Screen unbrauchbar ist
```

Beispiel:

```text
Provider konnte nicht aktualisiert werden.
(*)[Erneut versuchen] [Provider bearbeiten]
```

## Status Badges

```text
{Aktiv}
{Aktualisierung laeuft}
{Verbindungsfehler}
{Anmeldedaten ungültig}
{Abgelaufen}
{Deaktiviert}
{LIVE}
{Catch-Up}
{Gesehen}
```

Farbe allein reicht nicht. Badge muss textlich oder symbolisch verstaendlich sein.

## Hinweis / Toast-Ersatz

```text
+-----------------------------------+
| Zu Favoriten hinzugefuegt          |
+-----------------------------------+
```

Position innerhalb Safe Area, nicht für kritische Fehler verwenden.

## Akzeptanzkriterien

```text
Jeder Dialog hat Initialfokus
Destruktive Dialoge fokussieren Abbrechen
BACK schliesst Dialoge
Empty States bieten Aktionen nur wenn sinnvoll
Loading blockiert nur wenn keine sinnvolle Nutzung möglich ist
Fehler sind handhabbar
Status Badges sind nicht nur farblich verstaendlich
```
