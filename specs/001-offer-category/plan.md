# Implementation Plan: Offer Item Service Category Management

**Branch**: `001-offer-category` | **Date**: 2026-02-04 | **Spec**: /Users/fanny/Desktop/GIT/offer/specs/001-offer-category/spec.md
**Input**: Feature specification from `/Users/fanny/Desktop/GIT/offer/specs/001-offer-category/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/commands/plan.md` for the execution workflow.

## Summary

Enable offer owners to assign and edit a category for each service referenced by
offer items (services.category_id), visible only in edit mode. Implement with
service-category associations aligned to `service_categories` and `services`,
edit-only response fields, and validated updates that enforce ownership and
category availability.

## Technical Context

**Language/Version**: Backend Java 24; Frontend TypeScript 5.8  
**Primary Dependencies**: Spring Boot 3.x, Spring Data JPA, Firebase Admin SDK;
React, Vite, Tailwind CSS, React Router, Axios  
**Storage**: PostgreSQL  
**Testing**: Backend JUnit/Spring Boot Test; frontend manual test checklist if no
UI test framework is added  
**Target Platform**: Web application (browser + API)  
**Project Type**: web (separate frontend + backend)  
**Performance Goals**: Edit flows feel responsive; category save < 2 seconds for
typical offers  
**Constraints**: Categories must be hidden from preview/client-facing views; use
existing `/api/*` routes (avoid introducing `/edit` endpoints unless required by
backend constraints)  
**Scale/Scope**: Expected to handle typical offer sizes (dozens of services per
offer)

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- [x] API changes include updated DTOs, HTTP examples, and frontend API clients.
- [x] Authenticated-by-default verified; public endpoints explicitly documented.
- [x] Data schema and JPA entity mappings updated together (init.sql aligned).
- [x] Backend tests added/updated for behavior changes and are passing.
- [x] Error handling routes through GlobalExceptionHandler with safe responses.

## Project Structure

### Documentation (this feature)

```text
/Users/fanny/Desktop/GIT/offer/specs/001-offer-category/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)

```text
/Users/fanny/Desktop/GIT/offer/offer-backend/
├── src/
│   ├── main/
│   │   ├── java/com/thirdmoira/offer_backend/
│   │   └── resources/
│   └── test/java/com/thirdmoira/offer_backend/
/Users/fanny/Desktop/GIT/offer/offer-frontend/
├── src/
│   ├── api/
│   ├── pages/
│   ├── components/
│   └── types/
```

**Structure Decision**: Web application with dedicated backend and frontend
projects under `offer-backend/` and `offer-frontend/`.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| N/A | N/A | N/A |
