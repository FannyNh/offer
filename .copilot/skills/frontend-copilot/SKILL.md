---
name: frontend-copilot
description: Frontend delivery skill for the offer project (React + TypeScript + Vite). Use when implementing or refactoring UI pages, components, hooks, router flows, context state, API integrations, and frontend tests in `offer-frontend/`.
---

# Frontend Copilot Skill

## Goal

Ship frontend changes in `offer-frontend/` that are consistent, testable, and easy to maintain.

## Workflow

1. Read `references/project-context.md` before coding.
2. Read `references/testing-rules.md` when behavior changes.
3. Implement with typed, reusable patterns from `references/frontend-rules.md`.
4. Validate by running lint and tests (if configured).

## Output Rules

- Keep components focused and composable.
- Keep API calls inside `src/api/` and map responses to explicit types.
- Keep page logic in `src/pages/` and extract reusable logic to hooks.
- Add or update tests for behavior changes.
- Do not reduce coverage for modified code.

## Validation Commands

Run from project root:

```zsh
cd "/Users/fanny/Desktop/GIT/offer/offer-frontend"
npm run lint
npm test
```

If `npm test` is not configured, add a test script in `offer-frontend/package.json` in the same change or a follow-up task.


