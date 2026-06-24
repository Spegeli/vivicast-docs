# Focus Reference

Status: v6

Focus is a core Android TV requirement.

Rules:

- Every interactive element must have visible focus.
- Focus must not rely on color only.
- Initial focus must be defined per screen.
- D-Pad movement must be predictable.
- Back closes overlays and dialogs first.

Global focus style:

- Vivicast uses one global focus style across reusable components.
- The focus effect combines scale, ring, and slight shadow elevation.
- Component-specific focus may adjust size or spacing only when required by layout, but must preserve the global style.

Main screen behavior:

- On a focused episode row, Right moves to the visible `Als gesehen markieren` or `Als ungesehen markieren` action; Left returns to the episode.
- Whole seasons and series do not expose a watched-state action.
- Back first moves focus to top navigation.
- Exit confirmation only runs from top navigation.
- Switching main areas resets the previous area focus.

Player exceptions:

- Leaving Live TV player returns to focused channel.
- Leaving movie player returns to movie detail.
- Leaving series player returns to episode or series detail.
- An Auto-Next panel initially focuses `Naechste Folge abspielen` or `Naechste Folge in X`.
- OK on that primary action starts the next episode immediately.
- A visible `Zurueck` button is shown beside the primary Auto-Next action at the same time in both states; the panel has no `Abbrechen` action.
- OK on `Zurueck` or remote Back cancels any pending countdown and returns to the series detail with the previous season/episode context restored instead of merely hiding the panel.
