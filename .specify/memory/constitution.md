<!--
Sync Impact Report
- Version change: template → 1.0.0
- Modified principles: Principle 1 placeholder → I. Contract-Driven API; Principle 2 placeholder → II. Authenticated by Default; Principle 3 placeholder → III. Data Integrity & Schema Discipline; Principle 4 placeholder → IV. Tests for Behavior Changes (NON-NEGOTIABLE); Principle 5 placeholder → V. Consistent Errors & Observability
- Added sections: Architecture & Stack Constraints; Workflow & Quality Gates
- Removed sections: None
- Templates requiring updates: ✅ .specify/templates/plan-template.md; ✅ .specify/templates/spec-template.md; ✅ .specify/templates/tasks-template.md; ⚠ .specify/templates/commands/*.md (directory not present)
- Follow-up TODOs: TODO(RATIFICATION_DATE): original adoption date not found in repo history
-->
# Offer Constitution

## Core Principles

### I. Contract-Driven API
All backend endpoints MUST be represented by explicit request/response DTOs and
example HTTP requests. Any API change MUST update the backend DTOs in
`offer-backend/src/main/java/.../api/rest/`, the frontend API client in
`offer-frontend/src/api/`, and the examples in `offer-backend/http/*.http` in the
same change.
Rationale: prevents silent contract drift between client and server.

### II. Authenticated by Default
Every backend endpoint MUST require Firebase authentication unless explicitly
marked as public in the controller and the HTTP examples. User identity MUST come
from the validated Firebase token; client-supplied user IDs MUST NOT be trusted
for authorization decisions.
Rationale: reduces the risk of privilege escalation and data exposure.

### III. Data Integrity & Schema Discipline
All persisted data MUST map to JPA entities and a schema captured in
`offer-backend/src/main/resources/init.sql`. Schema changes MUST update both the
entity mappings and `init.sql` in the same change; manual, DB-only edits are not
allowed.
Rationale: ensures reproducible environments and consistent data constraints.

### IV. Tests for Behavior Changes (NON-NEGOTIABLE)
Any change to backend domain logic or API behavior MUST include automated tests
under `offer-backend/src/test/` covering success and failure paths. Frontend
behavior changes MUST include automated tests or an explicit manual test checklist
in the plan/spec.
Rationale: prevents regressions across core workflows.

### V. Consistent Errors & Observability
Controllers MUST route errors through `GlobalExceptionHandler` and return a
consistent error shape; unexpected errors MUST be logged with request context
without leaking secrets or PII.
Rationale: makes incidents diagnosable while keeping responses safe.

## Architecture & Stack Constraints

- Backend: Java 24, Spring Boot 3.x, Spring Data JPA, PostgreSQL, MapStruct.
- Auth: Firebase Admin SDK for token verification and access control.
- Frontend: React + Vite + TypeScript with Tailwind CSS, React Router, Axios.
- Data access MUST remain in repositories/services; controllers only orchestrate.

## Workflow & Quality Gates

- All feature work MUST start from a spec/plan and include a Constitution Check.
- API changes MUST include updated DTOs, HTTP examples, and frontend client calls.
- Backend changes MUST pass `mvn test`; frontend changes MUST pass `npm run lint`.
- Reviewers MUST block merges that violate any Core Principle and document any
  approved exception in the plan's Complexity Tracking section.

## Governance
The constitution supersedes all other development guidance.

Amendments MUST:
- be proposed via PR with rationale,
- update the Sync Impact Report and version line,
- include a migration/rollout note if behavior changes.

Versioning policy:
- MAJOR: removes or weakens a Core Principle or governance rule.
- MINOR: adds a new principle or materially expands constraints.
- PATCH: clarifies wording without changing obligations.

Compliance review expectations:
- Every plan MUST include Constitution Check gates aligned to this document.
- Code reviews MUST verify compliance before approval.

**Version**: 1.0.0 | **Ratified**: TODO(RATIFICATION_DATE): original adoption date not found in repo history | **Last Amended**: 2026-02-04
