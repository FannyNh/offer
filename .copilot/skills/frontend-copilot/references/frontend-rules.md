# Frontend Rules

## Component Rules

- Build small, focused components with explicit prop contracts.
- Use composition before prop explosion.
- Keep derived state computed, not duplicated in local state.
- Avoid hidden side effects during render.

## Hook Rules

- Prefix reusable hooks with `use` and return stable, typed contracts.
- Keep data fetching/caching logic in hooks or API modules.
- Isolate async error handling and expose UI-ready status (`idle`, `loading`, `success`, `error`).

## API and Types

- Keep API calls in `src/api/` and never inline HTTP calls inside pages/components.
- Define request/response types near API clients or in `src/types/`.
- Map backend payloads before they reach UI components.
- Surface typed error objects where possible.

## Routing and Pages

- Keep page files responsible for orchestration (data + layout + action wiring).
- Move reusable behavior into hooks or shared components.
- Cover route guards and redirect behavior with tests.


