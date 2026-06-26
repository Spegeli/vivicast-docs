# Changelog

## 2026-06-26

### 📝 Documentation

- Removed the active Low-Fidelity mockup structure from current documentation references.
- Replaced the active High-Fidelity rendered mockup set with the current named renderings under `design/mockups/high-fidelity/rendered/`.
- Added `design/design-system/compose-template/` as a technical Compose designsystem template reference.
- Updated visual source priority so the current High-Fidelity renderings are the primary visual target reference.
- Clarified that rendered PNGs are not sources for labels, navigation, product logic, security, backup/restore, PIN or playback behavior.
- Clarified that Compose template Kotlin files in the docs repository are implementation templates, not app code and not final app module structure.


## 2026-06-24

### 📝 Documentation

- Added the Deep Research follow-up remediation plan with current-source scope, historical-hit classification and package validation rules.
- Added architecture onboarding diagrams for system context, module/layer responsibilities, import/refresh, EPG, player/progress and backup/restore flows.
- Moved the Home screen documentation to `design/screens/01-home.md`.
- Moved the Home wireframe documentation to `design/wireframes/00-home.md`.
- Moved completed remediation and deep-research artifacts from the active root into `archive/remediation/2026-06-24/`.
- Moved completed review and QA artifacts from active design sources into `archive/review/2026-06-24/`.
- Changed the start product area, top navigation label, start destination enum and related documentation references to Home.
- Changed documentation governance to define visible German UI naming, technical-name exceptions and changelog history handling.
- Changed documentation governance to keep visible `Home` as an explicit product-label exception and classify architecture diagrams as non-normative onboarding aids.
- Changed settings persistence documentation to use explicit DataStore keys, defaults and transient export dialog handling.
- Changed backup settings documentation so `Backup-Typ` is only an export dialog field, not a standalone Settings row.
- Changed PlaybackRequest documentation to remove legacy header-reference wording and keep provider-specific headers, cookies and user agents out of v1.
- Changed design-system module wording so `:core:designsystem` is only a non-normative Codex/App-Repo baseline hint.
- Changed mockup README files to clarify that rendered PNGs are visual style references and Markdown sources define current navigation and labels.
- Changed QA, readiness and Codex entry documentation to reference the Deep Research follow-up and architecture diagrams as non-normative support material.
- Fixed visible search group naming so current sources use `Kanäle` while technical Channel names remain unchanged.
- Fixed visible `Über die App` naming in current product and UI documentation.
- Fixed the low-fidelity mockup README to describe the current rendered PNG location instead of an old migration note.

## 2026-06-23

### 📝 Documentation

- Added canonical documentation role, source ownership and conflict-rule governance in `DOCS-GOVERNANCE.md`.
- Added a compact v1 Settings contract table covering visible option, type, values, default, storage location and effect.
- Added ADR-010 to define stable identities, restore keys and pending user-data references.
- Added ADR-011 and a normative M3U, Xtream Codes and XMLTV parser/source contract.
- Added ADR-012 to define atomic import and refresh boundaries.
- Added ADR-013 to define playback requests, Catch-Up playback, progress saving, track selection and Timeshift edge cases.
- Added ADR-014 to define data classification, protected local storage, full-backup crypto, PIN throttling, HTTP/TLS policy and diagnostic redaction.
- Added Android TV system-integration rules for stable deep links, global search indexing, Watch Next publishing and parental-control filtering.
- Added configurable global EPG retention settings for past and future program data.
- Added a normative v1 test strategy with parser golden tests, mockserver scenarios, migration tests, refresh cancellation checks, backup/restore roundtrips, Android TV QA and measurable performance budgets.
- Added a planned architecture diagrams index for system context, module/layer dependencies and core flow diagrams.
- Added the final cross-QA and fresh-developer dry-run with a domain traceability matrix and readiness summary.
- Added owner decision O-09: Backup now exposes media-cache size, cache clearing and history deletion actions.
- Added owner decision O-10: restore now uses the currently local PIN only for pre-restore authorization and disables parental controls after restore.
- Added owner decision O-11: external players do not write automatic progress, completion state or Auto-Next state back to Vivicast.
- Changed README and Codex entry documentation to reference the canonical governance file instead of maintaining duplicate source-priority lists.
- Changed QA and review files to explicit review or historical references with no authority to introduce new product requirements.
- Changed Codex architecture rules so architecture obligations not covered by PRD or ADR are treated as an app-repository working baseline.
- Changed the design system to a binding design reference within the governance conflict model.
- Changed player design references so the timeline is the primary control and pause or seeking no longer uses separate main buttons.
- Changed player documentation to scope external players to movies and episodes while keeping Live TV and Catch-Up internal.
- Changed Live-TV design references to use category mode, sender mode, global visible favorites and top navigation consistently.
- Changed search documentation to four result groups: channels, movies, series and EPG, with episodes kept on series detail pages.
- Changed local search documentation to use Room FTS4 with deterministic ranking, per-group limits and productive-data index maintenance.
- Changed PIN input requirements to use the Android/TV numeric password system keyboard with explicit confirmation actions.
- Changed backup restore to replace the full backup scope in v1, with schema migration kept separate from local-data merging.
- Changed diagnostics and Settings documentation so log export remains ZIP-only, export-only and free of in-app log previews or log-content copying.
- Changed provider and media identity documentation to use stable keys instead of local Room IDs for refresh, backup and restore references.
- Changed EPG documentation to use a source-based `EPGSource -> EPGChannel -> EPGProgram` pipeline with provider mapping and priority.
- Changed import documentation to make tolerant partial import the default for M3U, Xtream Codes and XMLTV.
- Changed refresh documentation to commit atomically per provider and per EPG source with staging before productive data changes.
- Changed Catch-Up documentation to generate M3U and Xtream playback URLs just in time without persisting or logging final stream URLs.
- Changed playback redirect documentation so final redirect URLs remain runtime-only data and are not persisted or logged.
- Changed VOD progress documentation to use a defined minimum position and save cadence for internal movie and episode playback.
- Changed media-cache sizing and rotation to fixed internal v1 behavior with no free user configuration.
- Changed history maintenance so Live-TV, movie, series, search and all-history deletion are defined; movie and series history deletion includes playback progress.
- Changed backup restore behavior so PIN verification values, active PIN sessions and parental-control protection flags from backups are ignored; users get a reactivation notice when parental controls were active at export time.
- Changed VOD return behavior so Vivicast keeps existing progress unchanged after external playback and shows a non-blocking progress-unavailable notice.
- Changed security documentation to separate Room, DataStore, Android Keystore and protected secret storage while defining loss handling for protected local data.
- Changed full-backup documentation to use a passphrase-protected AES-GCM container with KDF metadata and pre-restore authentication.
- Changed PIN documentation to use five failed attempts with 30-second, 60-second and 5-minute lockouts that survive app restart.
- Changed network documentation so HTTP IPTV sources remain allowed but insecure, policy-gated and distinct from TLS errors, which are never bypassed.
- Changed diagnostics documentation so redaction is required before persistent writes and again before export.
- Changed Android TV search and Watch Next documentation so protected content is not published while its parental-control protection is active.
- Changed Deep Link documentation to use stable provider and media keys instead of local Room IDs.
- Changed DoD, Codex and QA documentation to require reproducible tests or documented manual Android TV QA for critical requirements.
- Changed design, component and mockup indexes to reference current PRD, About App and UI direction documents.
- Changed PRD chapter headings so Android TV integration/security and implementation DoD no longer collide with chapters 10 and 11.
- Changed cross-document QA to record the Paket M housekeeping pass.
- Changed README and Codex references to clarify that `vivicast-docs/codex/` is only documentation reference material, not app code.
- Changed README, Codex entry guidance and implementation-readiness QA to point to the final cross-QA and dry-run.
- Changed the remediation plan status to completed after Paket N.
- Fixed implementation-readiness wording so readiness is reported per domain instead of as broad product-wide clearance.
- Fixed design-system and mockup drift around home navigation, Live-TV favorites, EPG buttons, player controls and initial focus.
- Fixed Settings product-contract gaps around PIN keyboard behavior and visible v1 option metadata.
- Fixed Restore documentation by removing v1 merge mode, import conflict dialogs and copy-import behavior.
- Fixed DataStore, Settings, cache, acceptance criteria, coding rules and QA contradictions around cache size, backup scope and configurable history limits.
- Fixed backup, data model, ADR, Settings UI and QA contradictions around restoring parental-control state from backups.
- Fixed Auto-Next scope so next-episode panels and automatic episode transitions are internal-player only.
- Fixed the T-12 readiness gap by defining measurable import, search, database, EPG and memory validation targets.
- Fixed remaining V-marker housekeeping gaps around player controls, EPG buttons, Live-TV columns, Home references, diagnostics, PIN keyboard, source priority, PRD references, diagrams, chapter numbering and Codex folder naming.
- Fixed stale cross-document QA wording that still listed follow-up reviews as open after they had been completed.
- Removed redundant `.gitkeep` placeholders from populated documentation directories.

- Owner decision O-08 completed: movies and individual episodes use a fixed 95-percent completion threshold with no user setting.
- Reaching 95 percent or the actual media end sets `isCompleted = true` and removes the item from Continue Watching; the threshold never stops playback or triggers Auto-Next.
- Movies and focused episode rows now expose `Als gesehen markieren` or `Als ungesehen markieren`; marking an item unwatched deletes its stored playback-progress record.
- Whole seasons and series have no manual watched state or separate completion record in v1.
- VOD/player PRD, Continue Watching, data model, screens, components, wireframes, design-system, mockup specs, high-fidelity owner/UI-direction records, coding rules, acceptance criteria and QA were aligned with O-08.

- Owner decision O-07 completed: Auto-Next is a Playback toggle for series episodes, defaults to off, and uses a 5/10/15/30-second countdown with 10 seconds as default.
- With Auto-Next off, `Naechste Folge abspielen` appears only after the actual episode end and never starts automatically. With Auto-Next on, `Naechste Folge in X` appears X seconds before the end; OK starts immediately and expiry starts the next episode at the actual end.
- The visible `Zurueck` action is shown beside the next-episode button at the same time in both panel states; there is no `Abbrechen` action. Activating `Zurueck` or pressing remote Back cancels a pending transition and returns to the previous season/episode context.
- Cross-season progression, final-series behavior and DataStore defaults `autoNextEpisodeEnabled = false` and `autoNextEpisodeCountdownSeconds = 10` are now defined.
- Series/player PRD, Settings, DataStore, screens, components, wireframes, design-system, mockup specs, coding rules, acceptance criteria and QA were aligned with O-07.

- Owner decision O-06 completed: Live-TV uses a fixed preview flow with no setting and no direct fullscreen start on the first OK press.
- The first OK in the channel column activates sender mode, opens the EPG column, starts the live preview and moves focus to the current EPG programme when available; the next OK on that programme opens fullscreen.
- Live-TV PRD, screen, components, wireframe, interaction, design-system, mockup, search references, coding rules, acceptance criteria and QA were aligned with O-06.
- Owner decision O-05 completed: `Startbereich` is a Select option under General with Home, Live-TV, Filme and Serien; Home is the default.
- The DataStore contract now uses `startDestination = HOME` with the allowed values `HOME`, `LIVE_TV`, `MOVIES` and `SERIES`.
- The setting applies to the next regular app start without immediately rerouting the current session; app auto-start uses it, while explicit Android TV targets take precedence.
- Product overview, Settings, navigation, DataStore, wireframe, mockup, components, coding rules, acceptance criteria and QA were aligned with O-05.
- Owner decision O-04 completed: the global EPG refresh interval defaults to 24 hours.
- `epgRefreshIntervalHours = 24` was added to the DataStore contract; interval, app-start, playlist-change and manual refresh triggers remain distinct.
- PRD, background jobs, Settings screens, wireframe, mockup specification, components, coding rules, acceptance criteria and QA were aligned with O-04.
- Owner decision O-03c2 completed: diagnostic logging is user-controlled, defaults to disabled, and uses a retention setting from 1 to 7 days with 1 day as default.
- Active diagnostic logging creates one logical private session per app process start. Clean exits are recorded exactly; unclean exits are reconstructed on the next start from Android `ApplicationExitInfo` when available or otherwise estimated from the last durable event.
- Retained sessions remain exportable after logging is disabled and are cleaned up by age at app start, after session completion and immediately after shortening retention.
- Owner decision O-03c3 completed: diagnostics are capped at 20 MiB total, 2 MiB per physical segment and three retained segments (6 MiB) per logical session.
- Per-session rotation removes the oldest closed segment. Global size cleanup first removes the oldest completed sessions; the current write segment and active-session metadata are protected.
- Diagnostic exports report truncation and discard metadata and derive the covered period only from events that are still retained.
- PRD, DataStore/file contract, Settings, About-App components and wireframe, coding rules, acceptance criteria and QA were aligned with O-03c2 and O-03c3.

## 2026-06-22

- Documentation cleanup.
- README updated.
- Governance added.
- Screen references expanded.
- Component references expanded.
- Interaction references updated.
- PRD cleanup done.
- QA decisions added.
- Visual checklist added.
- Files renamed.
- Wireframes updated.
- VOD provider categories updated.
- Search history behavior updated.
- Playlist add and edit flow updated.
- Global User-Agent setting added.
- Settings QA refinement completed.
- Settings groups clarified: Allgemein, Wiedergabelisten, EPG, Optik, Wiedergabe, Kindersicherung, Backup, Ueber die App.
- Language setting moved to Allgemein.
- User-Agent fixed as final Allgemein option and removed from playlist, provider and EPG forms.
- Playlist Add Flow QA-corrected: Name first, then source type.
- EPG update settings and history clarified.
- Settings wireframe updated after QA refinement.
- Optik settings QA refined with option order, contrast limits, EPG display detail and TV-safe customization rules.
- Wiedergabe settings QA refined with playback option order, stream-restart hints, unsupported-device states and external player dialog.
- Settings component specs extended with Color Select Row, Settings Detail Panel, Optik Settings Section and Playback Settings Section.
- Visual Acceptance Checklist extended for Optik and Wiedergabe QA.
- PIN and protected-area settings QA refined.
- Settings component specs extended with PIN Input Row, Protected Action Dialog and Parental Control Settings Section.
- Settings wireframe extended with PIN states and dialogs.
- Visual Acceptance Checklist extended for PIN settings QA.
- Backup and restore requirements added.
- Backup settings QA refined with standard backup, encrypted full backup, restore replacement and restore safety rules.
- Settings component specs extended with Backup Card, Backup Summary Dialog and Backup Settings Section.
- Settings wireframe extended with backup export, import summary, restore replacement confirmation and safety-backup dialog.
- Visual Acceptance Checklist extended for backup and restore QA.
- App info requirements added.
- App info wireframe, component specs and QA checklist added.
- Cross-document QA added.
- Data model QA completed for Room, DataStore, protected values, favorites, history, search history and playback progress.
- Background jobs and backup v1 boundaries aligned.
- IPTV, EPG, favorites, history and backup chapter aligned with the backup data contract.
- ADR alignment QA completed.
- Backup, timeshift and Android TV integration ADRs aligned with current PRD and data model.
- Codex and README references updated for ADR alignment QA.
- Codex implementation readiness QA added without fixed implementation order.
- README and Codex references updated for implementation readiness QA.
- Owner decision O-01 recorded: the first search result group is named `Kanäle`; affected search documents aligned on `docs-remediation`.
- Owner decision O-02 completed: Timeshift defaults to enabled, supports 15/30/60/120 minutes with 30 minutes as default, and offers Automatisch/RAM/Festplatte with Automatisch as default; ADR, PRD, DataStore, Settings, Player references and QA aligned on `docs-remediation`.
- Owner decision O-03a recorded: the sanitized log export is located at `Ueber die App > Diagnose und Support`; log file contents are export-only and must never be displayed or copied inside the app.
- Owner decision O-03b recorded after Android compatibility verification: diagnostics are exported as a streamed `application/zip` archive named `vivicast-diagnostics-YYYYMMDD-HHmmss.zip`; export content was deferred to O-03c.
- Owner decision O-03c1 recorded: the diagnostics ZIP contains required `vivicast-diagnostics.log` and `diagnostics-metadata.json` entries; allowed technical event categories and excluded sensitive/content data are defined. Time range and size/rotation remain open.

## 2026-06-21

- Repository structure created.
- PRD v1 started.
- Product name set: Vivicast.
