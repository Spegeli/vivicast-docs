# 02 - Module and Layer Dependencies

Status: Onboarding-Referenz v1

## Rolle

Dieses Diagramm visualisiert nur bereits dokumentierte ADR- und Codex-Arbeitsregeln.

Es ist keine verbindliche App-Modulentscheidung. Die konkrete Gradle- und Modulstruktur bleibt im App-Repository zu pruefen und als App-Repo-Entscheidung zu dokumentieren.

Bei Widerspruechen gewinnen PRD, ADRs, `DOCS-GOVERNANCE.md` und dokumentierte App-Repo-Entscheidungen.

## Quellen

- `DOCS-GOVERNANCE.md`
- `codex/coding-rules.md`
- `architecture/decisions/ADR-001-provider-isolation.md`
- `architecture/decisions/ADR-010-stable-identities-and-restore-keys.md`
- `architecture/decisions/ADR-011-parser-source-contracts.md`
- `architecture/decisions/ADR-012-atomic-import-refresh.md`

## Diagramm

```mermaid
flowchart TD
  UI["UI Layer: Compose for TV, Screens, Components"]
  Domain["Domain Layer: Use Cases, fachliche Regeln"]
  Data["Data Layer: Repositories, Room, DataStore, Secret Store"]
  Parser["Parser / Source Layer: M3U, Xtream, XMLTV"]
  Worker["Worker Layer: Refresh, Import, Cleanup, Diagnostics"]
  Player["Playback Layer: PlaybackRequest, Streamaufloesung, Progress"]
  System["Android-TV Integration: Deep Links, Systemsuche, Watch Next"]

  UI --> Domain
  Domain --> Data
  Domain --> Player
  Data --> Parser
  Worker --> Domain
  Worker --> Data
  System --> Domain

  Baseline["Codex-Baseline, nicht App-Repo-Entscheidung: :feature:*, :data:*, :iptv:*, :core:*, :worker"]
  Baseline -. "App-Repo-offen bis geprueft" .- UI
  Baseline -. "App-Repo-offen bis geprueft" .- Data
  Baseline -. "App-Repo-offen bis geprueft" .- Parser
  Baseline -. "App-Repo-offen bis geprueft" .- Worker
```

## Hinweise

- Keine UI-Komponente greift direkt auf die Datenbank zu.
- Repository Pattern, Use Cases und Unidirectional Data Flow sind Codex-Arbeitsbaseline.
- Neue oder abweichende verbindliche Architekturentscheidungen muessen als ADR oder App-Repo-Entscheidung dokumentiert werden.
