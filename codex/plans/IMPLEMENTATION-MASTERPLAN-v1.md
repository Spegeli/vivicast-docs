# Vivicast Implementation Masterplan v1

Status: active Codex implementation plan  
Target app repository: `Spegeli/vivicast`  
Reference documentation repository: `Spegeli/vivicast-docs`  
Location in docs repository: `codex/plans/IMPLEMENTATION-MASTERPLAN-v1.md`

## Purpose

This masterplan defines the high-level implementation order and package structure for building the Vivicast Android TV app.

It is an active Codex working reference, but it is not a product, design, architecture, or test specification.

The Vivicast docs repository is treated as read-only reference material during app implementation. Codex must not change files in `vivicast-docs` unless the Owner explicitly requests a documentation change.

The actual technical working plans must be created and maintained by Codex inside the app repository.

## Repository Roles

### `vivicast-docs`

The docs repository is the reference source for:

- product scope
- functional requirements
- UI and interaction requirements
- design direction
- architecture decisions
- data model rules
- security rules
- backup/restore behavior
- player/progress behavior
- test strategy
- Codex working rules

Codex may read this repository and use it as implementation guidance.

Codex must not modify this repository during app implementation unless explicitly instructed by the Owner.

### `vivicast`

The app repository is the implementation workspace.

Codex creates technical plans, implementation files, tests, status notes, and package-specific working documents in the app repository.

Recommended app-repo structure for Codex working plans:

```text
codex/plans/
  APP-IMPLEMENTATION-PLAN.md
  P00-preflight-plan.md
  P01-app-skeleton-plan.md
  P02-design-shell-plan.md
  ...
```

## Source Priority

If sources conflict, `DOCS-GOVERNANCE.md` wins.

Short priority rule:

1. PRD files define product scope, behavior, settings, data requirements, and acceptance criteria.
2. ADRs define architecture decisions.
3. Design System, Screens, Wireframes, Interaction, Components, and UI Direction Decisions define UI, focus, navigation, and visual direction.
4. `prd/PRD-v1/13-test-strategy.md` defines tests, fixtures, performance budgets, and DoD evidence.
5. Codex files define working rules for Codex.
6. This masterplan defines implementation order and package boundaries.
7. Diagrams are non-normative onboarding aids.
8. Rendered PNGs are visual style references, but not normative sources for navigation, labels, or UI text.
9. Archived files are historical work evidence and not active implementation sources.

This masterplan must not override PRD, ADR, Design, Test Strategy, Codex Rules, or Governance.

## Global Implementation Rules

Codex must not independently change:

- visible main area `Home`
- main navigation: `Home | Live-TV | Filme | Serien | Suche | Einstellungen`
- visible search group name `Kanäle`
- settings defaults
- backup/restore behavior
- PIN rules
- player/progress rules
- provider isolation
- data model baseline decisions
- security rules
- design direction
- PRD or ADR decisions

Technical names may remain English/ASCII.

Visible UI text must remain German with umlauts, except explicitly allowed exceptions such as `Home`.

## Autonomous Work with Control Points

Codex should develop the app as autonomously as possible, but only within the active Vivicast documentation.

Codex may independently:

- create and update technical working plans in the app repository
- split masterplan packages into smaller technical tasks
- decide implementation details
- add tests
- analyze and fix technical errors
- extend the app repository structure in a reasonable way
- choose the next sensible task within the current package after finishing a task

Codex must not independently:

- change product scope
- change visible UI labels
- change navigation
- change settings defaults
- change baseline data model decisions
- change backup/restore rules
- change PIN, security, or playback rules
- change active files in the `vivicast-docs` repository
- use historical or archived files as active sources

## Mandatory Context Refresh

Codex must not rely only on its own working plan or current chat context.

Before each package, Codex must re-check the relevant Vivicast docs.

Codex must also re-check the docs when:

- an implementation is unclear
- an error cannot be solved clearly
- a technical decision could affect product behavior
- the current app-repo plan might diverge from the docs
- a requirement is only known from memory
- a package runs long or gets split into multiple subtasks
- a test fails and the cause could be functional
- Codex notices that the current context is incomplete or uncertain

Rule: when uncertain, the active docs win over Codex memory and over the app-repo working plan.

## Package Lifecycle

Each package must follow this lifecycle:

1. Read sources  
   Codex reads the sources named in the package and any other obviously affected active sources.

2. Create a technical detail plan in the app repository  
   The plan describes scope, non-scope, affected files/modules, technical approach, tests, risks, and Owner questions.

3. Check blockers  
   If blocking Owner questions exist, Codex stops and asks them in one bundled message.

4. Implement  
   Codex implements only the defined package scope.

5. Validate  
   Codex runs build, test, lint, and relevant functional checks as far as the app repository allows.

6. Check against docs  
   Codex compares the result against PRD, ADR, Design, Test Strategy, Codex Rules, and Governance.

7. Document result  
   Codex updates the app-repo working plan or package status.

8. Mark done only if all Done criteria are fulfilled.

## Definition of Done per Package

A package is done only if:

- the defined scope is implemented
- non-scope was not accidentally implemented
- the app builds successfully
- relevant tests pass or missing tests are justified
- no known compile or runtime errors remain open
- relevant UI, focus, and D-Pad rules were checked
- relevant PRD, ADR, and Design rules are followed
- no visible text, label, or navigation was changed unintentionally
- no security, PIN, backup, restore, playback, or persistence rule was violated
- deviations are documented
- open questions are documented
- Codex clearly reports what is done, open, or intentionally not implemented

If any of these criteria are not met, Codex must not mark the package as done.

## Error and Blocker Rule

If Codex cannot complete something, it must not blindly continue following its previous plan.

Codex must:

- describe the error concretely
- evaluate relevant logs or error messages
- re-check the affected docs
- review at least one alternative technical solution
- document what was tried
- decide whether the issue is a technical blocker or an Owner question

If the cause is technical, Codex may continue debugging independently.

If the issue affects product behavior, UI, data model, security, backup/restore, PIN, or playback, Codex must stop and ask the Owner.

## Owner Questions

Codex must stop and ask bundled Owner questions when:

- current sources contradict each other
- a requirement is missing
- multiple functionally different solutions are possible
- a decision would change visible UI or user behavior
- a technical workaround could violate a product rule
- a package appears to require changing the docs
- archived or old information seems relevant but is not confirmed by current sources

Owner questions must include:

- affected source
- affected app component
- problem
- Codex recommendation
- concrete decision options

## Technical Working Plans in the App Repository

Codex creates and maintains the actual implementation plans in the app repository, not in `vivicast-docs`.

A technical app-repo plan must document:

- which Vivicast docs were read
- which masterplan package is affected
- concrete implementation scope
- non-scope
- affected files, modules, or packages in the app repository
- technical decisions made by Codex
- deviations or package splits compared with this masterplan
- tests and validation steps
- open Owner questions

Technical app-repo plans may concretize this masterplan, but must not override PRD, ADR, Design, Test Strategy, Codex Rules, or Governance.

## Autonomy Boundary

Codex should work autonomously as long as the implementation is clearly covered by the active docs.

Codex must ask the Owner if:

- current sources are contradictory
- an important requirement is missing
- multiple functionally different solutions are possible
- the decision changes visible UI or user behavior
- a workaround could violate a product rule
- a package seems solvable only by changing docs
- Codex is uncertain whether its current plan still matches the docs

## Non-Goals of This Masterplan

This masterplan does not finally define:

- exact package structure
- exact class or file names
- exact Compose component names
- exact library versions
- exact database migrations
- exact test implementation
- commit boundaries
- branching strategy

Codex decides these points in the package-specific technical plan, based on the real app repository.

## Package 0: Repository Preflight and Source Check

### Goal

Codex checks the app repository and docs repository, creates a technical overview, and confirms that the active sources are understood.

### Sources

- `README.md`
- `DOCS-GOVERNANCE.md`
- `codex/README.md`
- `codex/coding-rules.md`
- `codex/tv-compose-reference-guide.md`
- `prd/PRD-v1/00-prd-boundaries.md`
- `prd/PRD-v1/09-implementation-and-dod.md`
- `prd/PRD-v1/13-test-strategy.md`

### Scope

- inspect app repository structure
- inspect docs repository source structure
- identify build system and existing code state
- create first app-repo technical working plan
- identify blockers and Owner questions

### Non-Scope

- no app feature implementation
- no docs modification unless explicitly requested by Owner

### Acceptance

- app repository structure is documented
- active source roles are understood
- required setup decisions are documented
- blocking Owner questions are listed, if any

## Package 1: App Skeleton and Technical Foundation

### Goal

Create the Android TV app foundation.

### Scope

- Android/Kotlin/Compose-for-TV project baseline
- package name `com.vivicast.tv`
- minimal app startup
- dependency/build foundation
- basic structure for UI, domain, data, player, settings, and tests
- logging/error foundation without sensitive data
- first CI/test foundation, if appropriate for the app repo

### Sources

- `prd/PRD-v1/01-product-overview.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `prd/PRD-v1/09-implementation-and-dod.md`
- `architecture/decisions/`
- `codex/coding-rules.md`

### Acceptance

- app builds successfully
- app starts on Android TV
- no product functionality is faked as complete
- structure does not violate ADR or Codex rules

## Package 2: Design System and Navigation Shell

### Goal

Create the visual and navigation foundation.

### Scope

- TV-suitable theme
- focus model foundation
- top navigation
- empty or skeleton screens for main areas
- back behavior on main screens
- loading/empty/error state patterns
- base components for lists, grids, cards, settings rows, and dialogs

### Sources

- `design/design-system/`
- `design/interaction/nav.md`
- `design/interaction/focus.md`
- `design/screens/`
- `design/wireframes/`
- `design/components/`
- `design/mockups/high-fidelity/02-ui-direction-decisions.md`

### Acceptance

- main navigation contains `Home | Live-TV | Filme | Serien | Suche | Einstellungen`
- D-Pad, OK, and Back work in the shell
- focus is visible and stable
- rendered PNGs are used only as visual style references

## Package 3: Persistence, Data Model, and Security Foundation

### Goal

Prepare local data storage and security foundations.

### Scope

- Room baseline
- DataStore settings baseline
- Android Keystore / secret-store baseline
- provider, category, channel, movie, series, season, episode, EPG, favorite, progress, history, and search structures according to PRD
- Stable Identity / Restore Key foundations
- sensitive data classification
- database indices as needed

### Sources

- `prd/PRD-v1/06-data-model.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `prd/PRD-v1/10-backup-import-requirements.md`
- `architecture/decisions/ADR-010-stable-identities-and-restore-keys.md`
- `architecture/decisions/ADR-014-security-data-network-backup.md`

### Acceptance

- persisted settings have defined keys, types, defaults, and allowed values
- secrets are not stored insecurely
- data model is ready for import, EPG, search, history, favorites, and backup
- no security baseline is bypassed

## Package 4: Provider, Parser, and Import Foundation

### Goal

Model and import M3U and Xtream Codes sources.

### Scope

- provider management in data layer
- M3U parser according to source contract
- Xtream Codes client/parser according to source contract
- network and timeout rules
- connection test
- import status and error structure
- staging foundation for atomic imports
- no provider mixing

### Sources

- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `prd/PRD-v1/07-background-jobs-performance.md`
- `prd/PRD-v1/12-parser-source-contracts.md`
- `architecture/decisions/ADR-001-provider-isolation.md`
- `architecture/decisions/ADR-011-parser-source-contracts.md`
- `architecture/decisions/ADR-012-atomic-import-refresh.md`

### Acceptance

- M3U and Xtream Codes can be modeled as sources
- parsers have fixture-based tests
- partial errors are traceable
- import does not violate provider isolation

## Package 5: Import/Refresh, EPG, and Background Jobs

### Goal

Build production-ready import, refresh, and EPG processing.

### Scope

- atomic playlist refresh
- XMLTV import
- EPG sources per provider
- EPG priorities
- automatic and manual EPG assignment
- EPG retention
- background job rules
- cancellation, error, and retry behavior
- performance budgets from test strategy

### Sources

- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `prd/PRD-v1/07-background-jobs-performance.md`
- `prd/PRD-v1/12-parser-source-contracts.md`
- `architecture/decisions/ADR-002-epg-strategy.md`
- `architecture/decisions/ADR-003-refresh-strategy.md`
- `architecture/decisions/ADR-012-atomic-import-refresh.md`
- `architecture/diagrams/03-import-refresh-flow.md`
- `architecture/diagrams/04-epg-flow.md`

### Acceptance

- import/refresh is atomic
- EPG data is provider-related correctly
- error states are traceable
- performance targets are considered in the detail plan

## Package 6: Settings, Provider Management, and Data Maintenance UI

### Goal

Implement settings and management surfaces.

### Scope

- Settings main areas
- Allgemein
- Wiedergabelisten
- EPG
- Optik
- Wiedergabe
- Kindersicherung
- Backup
- lokale Datenwartung
- Über die App
- add/edit/delete provider
- manage EPG sources
- cache and history deletion

### Sources

- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `prd/PRD-v1/10-backup-import-requirements.md`
- `prd/PRD-v1/11-about-app-requirements.md`
- `design/screens/07-settings.md`
- `design/screens/08-playlist-epg.md`
- `design/wireframes/05-settings.md`
- `design/components/settings.md`
- `design/components/about-app.md`

### Acceptance

- Settings UI follows PRD and Design
- backup type is only a transient export-dialog value
- `Über die App` is visibly named correctly
- no provider-specific header, cookie, or User-Agent concept is introduced for v1

## Package 7: Home, History, Favorites, and Continue

### Goal

Make Home and user-related lists usable.

### Scope

- Home screen
- recently watched Live-TV channels
- continue for movies and series
- favorites for Live-TV, movies, and series
- history for Live-TV, movies, series, and search
- clear history
- empty states

### Sources

- `prd/PRD-v1/01-product-overview.md`
- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `architecture/decisions/ADR-009-provider-deletion-and-favorites.md`
- `architecture/decisions/ADR-013-player-playback-progress.md`
- `design/screens/01-home.md`
- `design/wireframes/00-home.md`

### Acceptance

- Home is the default start area
- Live-TV does not appear in Continue
- movies and series share Continue behavior as specified
- favorites remain visible across providers without violating provider isolation

## Package 8: Live-TV Browser

### Goal

Fully implement Live-TV browsing.

### Scope

- provider/category/channel navigation
- adaptive column logic
- channel list with channel number, logo, and EPG info
- mini EPG / current programme / next programme
- favorites
- hidden/sort logic
- channel selection starts player
- error states for missing streams or EPG

### Sources

- `prd/PRD-v1/02-live-tv-requirements.md`
- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `design/screens/02-live-tv.md`
- `design/wireframes/01-live-tv-browser.md`
- `design/interaction/01-live-tv-adaptive-columns.md`
- `design/mockups/02-live-tv-mockup-spec.md`

### Acceptance

- large channel lists remain usable
- focus paths are TV-suitable
- Live-TV EPG is displayed correctly
- provider isolation remains intact

## Package 9: VOD Movies and Series

### Goal

Make movies and series browsable and playable.

### Scope

- movie area
- series area
- categories
- poster grids
- detail pages
- seasons/episodes
- continue
- watched/progress rules
- Auto-Next rules
- trailer behavior according to v1 boundaries
- favorites

### Sources

- `prd/PRD-v1/03-movies-series-requirements.md`
- `prd/PRD-v1/05-iptv-epg-favorites.md`
- `architecture/decisions/ADR-007-trailer-strategy.md`
- `architecture/decisions/ADR-013-player-playback-progress.md`
- `design/screens/04-movies.md`
- `design/screens/05-series.md`
- `design/wireframes/02-movies.md`
- `design/wireframes/03-series.md`
- `design/mockups/04-vod-mockup-spec.md`

### Acceptance

- movies and series remain separate main areas
- series logic with seasons and episodes is implemented clearly
- progress and Auto-Next follow PRD
- no external metadata provider is introduced

## Package 10: Search and Android TV Search

### Goal

Implement local search and system search.

### Scope

- search screen
- search history
- live search
- search groups: Filme, Serien, Kanäle, Programme
- FTS indices
- normalization
- ranking and limits
- result actions
- Android TV system search where v1 specifies it

### Sources

- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `prd/PRD-v1/06-data-model.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `architecture/decisions/ADR-005-local-search.md`
- `architecture/decisions/ADR-008-android-tv-integration.md`
- `design/screens/06-search.md`
- `design/wireframes/04-search.md`

### Acceptance

- visible search group name is `Kanäle`
- technical Channel terms remain allowed internally
- search is local and performant
- search history can be deleted

## Package 11: Player, Playback, Catch-Up, Timeshift, and Progress

### Goal

Fully implement player functionality.

### Scope

- player screen
- player overlay
- Live-TV playback
- VOD playback
- progress persistence
- resume
- Auto-Next display
- Catch-Up
- Timeshift
- audio/subtitle/aspect ratio options
- external player behavior according to PRD
- error states

### Sources

- `prd/PRD-v1/02-live-tv-requirements.md`
- `prd/PRD-v1/03-movies-series-requirements.md`
- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `architecture/decisions/ADR-006-timeshift-strategy.md`
- `architecture/decisions/ADR-013-player-playback-progress.md`
- `design/screens/03-player.md`
- `design/wireframes/06-player.md`
- `design/components/player.md`
- `architecture/diagrams/05-player-progress-flow.md`

### Acceptance

- PlaybackRequest follows v1 network policy
- no provider-specific header, cookie, or User-Agent concept exists
- global User-Agent is applied centrally
- progress rules match PRD/ADR

## Package 12: Backup, Restore, PIN, and Protection

### Goal

Implement backup/restore and parental protection safely.

### Scope

- backup export
- backup import/restore
- full backup and partial restore according to PRD
- secret/PIN handling
- restore rules for PIN
- set/change/disable PIN
- protected areas
- PIN prompt points
- security confirmations
- data cleanup on provider deletion

### Sources

- `prd/PRD-v1/04-search-settings-player-requirements.md`
- `prd/PRD-v1/08-android-tv-security.md`
- `prd/PRD-v1/10-backup-import-requirements.md`
- `architecture/decisions/ADR-004-backup-strategy.md`
- `architecture/decisions/ADR-014-security-data-network-backup.md`
- `architecture/diagrams/06-backup-restore-flow.md`
- `design/wireframes/07-dialogs-states.md`

### Acceptance

- backup type is a transient export-dialog value
- sensitive data is handled correctly
- PIN rules during restore are not changed independently
- restore does not insecurely adopt old PIN settings

## Package 13: Android TV System Integration and Polish

### Goal

Harden Android TV integration and final UX.

### Scope

- launcher/autostart behavior according to PRD
- deep links, if specified
- Android TV Search / Watch Next according to v1
- CH+/CH-
- focus and Back polish
- large font
- overscan
- long text
- 720p/1080p/4K checks
- performance optimization
- empty/error states

### Sources

- `prd/PRD-v1/08-android-tv-security.md`
- `architecture/decisions/ADR-008-android-tv-integration.md`
- `design/interaction/`
- `prd/PRD-v1/13-test-strategy.md`

### Acceptance

- Android TV operation is consistent
- focus traps are removed
- layout works at target resolutions and large font
- system integration does not violate privacy or security

## Package 14: Test Hardening, Performance, and Release Readiness

### Goal

Bring the app to v1 release readiness.

### Scope

- parser golden tests
- mockserver tests
- database/migration tests
- backup/restore roundtrips
- player/progress tests
- Android TV focus QA
- performance budgets
- memory/index checks
- regression tests for main flows
- final app-repo evidence

### Sources

- `prd/PRD-v1/09-implementation-and-dod.md`
- `prd/PRD-v1/13-test-strategy.md`
- all affected PRDs, ADRs, and Design sources

### Acceptance

- v1 DoD is fulfilled or open points are clearly documented
- performance budgets are measured
- critical flows are tested
- no known PRD, ADR, or Design violation remains open

## Package Completion Report

After each package, Codex must report:

- implemented scope
- changed files
- sources read
- tests and results
- known limitations
- open Owner questions
- deviations from the masterplan, if any
- next recommended package

## Handling Changes to This Masterplan

This masterplan is read-only for Codex during app implementation.

Codex must not edit this masterplan or any other active file in `vivicast-docs` unless the Owner explicitly requests a documentation change.

If Codex finds that this masterplan or another doc is incomplete, contradictory, or outdated, Codex must not silently fix it.

Instead, Codex must report:

- affected file
- affected section
- problem
- impact on implementation
- Codex recommendation
- concrete Owner decision options

Only after explicit Owner approval may docs be changed.
