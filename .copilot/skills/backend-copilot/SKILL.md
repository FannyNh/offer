---
name: backend-copilot
description: Backend delivery skill for the offer project (Java 24 + Spring Boot 3.x, DDD-oriented layers). Use when implementing or refactoring controllers, domain services, repositories, entities, API contracts, exception handling, and backend tests in `offer-backend/`.
---

# Backend Copilot Skill

## Goal

Ship backend changes in `offer-backend/` that are domain-focused, testable, and aligned with clean code and DDD practices.

## Workflow

1. Read `references/backend-rules.md` before coding.
2. Read `references/java-clean-code-ddd-rules.md` for architecture and naming decisions.
3. Keep layers explicit: API -> domain -> data.
4. Add or update tests for behavior changes.

## Output Rules

- Keep business rules in domain services, not in controllers.
- Keep controller classes thin: mapping, validation, orchestration only.
- Keep persistence concerns in data/repository implementations.
- Use explicit API and domain contracts; avoid leaking JPA/entity internals to API responses.
- Add clear error mapping through exception handlers.

## Validation Commands

Run from project root:

```zsh
cd "/Users/fanny/Desktop/GIT/offer/offer-backend"
./mvnw test
```

For quick compile validation:

```zsh
cd "/Users/fanny/Desktop/GIT/offer/offer-backend"
./mvnw -q -DskipTests compile
```

