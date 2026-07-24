# Backend Rules

These rules apply to all backend changes in `offer-backend/`.

## Scope and Architecture

- Preserve layered architecture: `api/`, `domain/`, `data/`.
- Keep dependencies directional:
  - `api` depends on `domain`
  - `data` depends on `domain`
  - `domain` must not depend on `api` or framework-specific transport code
- Keep integration boundaries explicit through mappers and dedicated DTO/contract classes.

## API Layer Rules

- Controllers expose HTTP contracts only.
- Validate request shape and authorization inputs at API boundary.
- Do not place business decisions in controllers.
- Return explicit response models from `api/rest/`.
- Map domain exceptions to stable HTTP responses in global exception handlers.

## Domain Layer Rules

- Domain services own business invariants and workflows.
- Method names must describe business intent, not technical mechanics.
- Keep domain models coherent and free from transport annotations.
- Favor explicit return types over generic maps or loosely typed structures.

## Data Layer Rules

- Repositories express domain-oriented persistence operations.
- Keep JPA/entity details encapsulated in data layer.
- Use mapper classes for entity <-> domain translation.
- Avoid leaking entity objects outside data layer.

## Error and Security Rules

- Use typed exception classes for known business and authorization errors.
- Keep error messages deterministic and safe for API consumers.
- Enforce security checks consistently before state mutations.

## Testing Rules (Backend)

- Add or update tests for every behavior change.
- Prefer service-level tests for business rules.
- Add controller tests for request/response and error mapping behavior.
- Keep repository tests for query/mapping behavior that is not covered elsewhere.
- Cover success, validation failure, authorization failure, and not-found paths when relevant.

## Naming and Consistency

- Use clear and stable names for API contracts and domain models.
- Keep one responsibility per class.
- Avoid abbreviations unless standard and unambiguous.

