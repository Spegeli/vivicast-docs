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

## Bestaetigung

```text
+---------------------------------------------+
| Aktion bestaetigen                           |
| Moechtest du diese Aktion ausfuehren?        |
| (*)[Bestaetigen] [Abbrechen]                 |
+---------------------------------------------+
```

## Loeschen mit optionalem PIN-Schutz

```text
+---------------------------------------------+
| Wirklich loeschen?                           |
| Diese Aktion kann nicht rueckgaengig werden. |
| (*)[Abbrechen] [Loeschen]                    |
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
|     [0] [Loeschen]                           |
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
(*)[Wiedergabeliste hinzufuegen]
```

```text
Keine EPG-Quellen vorhanden
(*)[EPG-Quelle hinzufuegen]
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
{Anmeldedaten ungueltig}
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

Position innerhalb Safe Area, nicht fuer kritische Fehler verwenden.

## Akzeptanzkriterien

```text
Jeder Dialog hat Initialfokus
Destruktive Dialoge fokussieren Abbrechen
BACK schliesst Dialoge
Empty States bieten Aktionen nur wenn sinnvoll
Loading blockiert nur wenn keine sinnvolle Nutzung moeglich ist
Fehler sind handhabbar
Status Badges sind nicht nur farblich verstaendlich
```
