# Copilot Instructions for `offer`

Use these project defaults when generating or editing code.

## Scope

- Backend: `offer-backend/` (Java 24, Spring Boot 3.x).
- Frontend: `offer-frontend/` (TypeScript 5.8, React, Vite).

## General Rules

- Follow `AGENTS.md` as baseline project guidance.
- Keep changes minimal and targeted to the request.
- Preserve existing architecture and naming conventions.
- Do not introduce unrelated refactors.

## Frontend Rules

- For frontend work, load and follow:
  - `.copilot/skills/frontend-copilot/SKILL.md`
  - `.copilot/skills/frontend-copilot/references/project-context.md`
  - `.copilot/skills/frontend-copilot/references/frontend-rules.md`
  - `.copilot/skills/frontend-copilot/references/testing-rules.md`
- Keep API calls in `offer-frontend/src/api/`.
- Favor typed contracts in `offer-frontend/src/types/`.
- Keep page orchestration in `offer-frontend/src/pages/`, reusable logic in `offer-frontend/src/hooks/`.

## Validation

When frontend behavior changes, run:

```zsh
cd "/Users/fanny/Desktop/GIT/offer/offer-frontend"
npm run lint
npm test
```

If `npm test` is not configured, add a test script and document it in the same change or follow-up.

