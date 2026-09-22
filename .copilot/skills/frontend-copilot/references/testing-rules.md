# Frontend Testing and Coverage Rules

These rules apply to all frontend changes in `offer-frontend/`.

## Test Expectations

- Add or update tests for every behavior change.
- Use unit tests for utilities, mappers, and custom hooks.
- Use component tests for interaction, conditional UI, and form behavior.
- Use integration tests for route-level user flows and API interaction paths.
- Cover failure states (validation errors, API errors, empty states).

## Coverage Baseline

Minimum thresholds:

- Lines: 80%
- Statements: 80%
- Functions: 80%
- Branches: 70%

Critical folders should target 85%+ lines/functions:

- `src/pages/`
- `src/hooks/`
- `src/context/`
- `src/api/`

## Quality Gate

Before merge:

- Lint passes.
- Frontend tests pass.
- Coverage does not regress on changed code.

## Run Commands

```zsh
cd "/Users/fanny/Desktop/GIT/offer/offer-frontend"
npm run lint
npm test
```

If `npm test` is not configured, add and document it in `offer-frontend/package.json`.


