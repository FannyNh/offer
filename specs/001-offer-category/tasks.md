---

description: "Task list template for feature implementation"
---

# Tasks: Offer Item Service Category Management

**Input**: Design documents from `/Users/fanny/Desktop/GIT/offer/specs/001-offer-category/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Backend tests are REQUIRED by the constitution for behavior changes.

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Path Conventions

- **Web app**: `offer-backend/src/`, `offer-frontend/src/`

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [ ] T001 Verify `service_categories` and `services.category_id` exist in `offer-backend/src/main/resources/init.sql` and document any required schema changes in `specs/001-offer-category/plan.md`
- [ ] T002 Add HTTP example skeletons for new endpoints in `offer-backend/http/offers.http`

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

- [ ] T003 Create `ServiceEntity` mapping `services` table in `offer-backend/src/main/java/com/thirdmoira/offer_backend/data/entities/ServiceEntity.java`
- [ ] T004 Create `ServiceCategoryEntity` mapping `service_categories` table in `offer-backend/src/main/java/com/thirdmoira/offer_backend/data/entities/ServiceCategoryEntity.java`
- [ ] T005 Create repositories in `offer-backend/src/main/java/com/thirdmoira/offer_backend/data/ServiceRepository.java` and `offer-backend/src/main/java/com/thirdmoira/offer_backend/data/ServiceCategoryRepository.java`
- [ ] T006 Add API DTOs for categories and service category updates in `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/rest/ApiServiceCategory.java`, `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/rest/ApiServiceCategoryList.java`, `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/rest/ApiServiceCategoryUpdateRequest.java`, `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/rest/ApiServiceCategoryView.java`
- [ ] T007 Create mappers for service/category API in `offer-backend/src/main/java/com/thirdmoira/offer_backend/data/mappers/ApiDomainServiceCategoryMapper.java`
- [ ] T008 Add domain models for service/category in `offer-backend/src/main/java/com/thirdmoira/offer_backend/domain/models/Service.java` and `offer-backend/src/main/java/com/thirdmoira/offer_backend/domain/models/ServiceCategory.java`

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Assign or Change Service Category (Priority: P1) 🎯 MVP

**Goal**: Allow an offer owner to assign or change a category for a service referenced by offer items.

**Independent Test**: As the offer owner, update a service category and verify it persists.

### Tests for User Story 1 (REQUIRED)

- [ ] T009 [P] [US1] Add service category update tests in `offer-backend/src/test/java/com/thirdmoira/offer_backend/api/ServiceCategoryControllerTest.java`
- [ ] T010 [P] [US1] Add service-category authorization tests in `offer-backend/src/test/java/com/thirdmoira/offer_backend/domain/ServiceCategoryServiceTest.java`

### Implementation for User Story 1

- [ ] T011 [US1] Implement category listing logic in `offer-backend/src/main/java/com/thirdmoira/offer_backend/domain/ServiceCategoryService.java`
- [ ] T012 [US1] Implement service category update logic in `offer-backend/src/main/java/com/thirdmoira/offer_backend/domain/ServiceService.java`
- [ ] T013 [US1] Add service category API routes in `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/ServiceCategoryController.java`
- [ ] T014 [US1] Add service category update route in `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/ServiceController.java`
- [ ] T015 [US1] Update HTTP examples for category list/update in `offer-backend/http/offers.http`
- [ ] T016 [US1] Add frontend API client calls in `offer-frontend/src/api/offerApi.tsx` or `offer-frontend/src/api/serviceApi.tsx`
- [ ] T017 [US1] Add frontend types for categories/services in `offer-frontend/src/types/offer.ts`
- [ ] T018 [US1] Wire category selection UI in edit flow in `offer-frontend/src/pages/OfferDetails.tsx`

**Checkpoint**: User Story 1 should be fully functional and testable independently

---

## Phase 4: User Story 2 - View Service Categories in Edit Mode (Priority: P2)

**Goal**: Show service categories in edit mode only, and hide them in preview/client views.

**Independent Test**: Edit view shows categories; preview/client view does not.

### Tests for User Story 2 (REQUIRED)

- [ ] T019 [P] [US2] Add edit-view response tests in `offer-backend/src/test/java/com/thirdmoira/offer_backend/api/OfferControllerTest.java`

### Implementation for User Story 2

- [ ] T020 [US2] Add edit-mode offer response DTOs in `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/rest/ApiOfferEditView.java` and related nested DTOs
- [ ] T021 [US2] Implement edit-mode offer retrieval in `offer-backend/src/main/java/com/thirdmoira/offer_backend/domain/OfferService.java`
- [ ] T022 [US2] Extend existing offer details endpoint to include edit-mode data in `offer-backend/src/main/java/com/thirdmoira/offer_backend/api/OfferController.java`
- [ ] T023 [US2] Update frontend edit flow to use edit-mode endpoint in `offer-frontend/src/api/offerApi.tsx`
- [ ] T024 [US2] Ensure preview/client views omit categories in `offer-frontend/src/pages/OfferDetails.tsx` or `offer-frontend/src/pages/Dashboard.tsx`

**Checkpoint**: User Story 2 should be independently functional

---

## Phase 5: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [ ] T025 [P] Update quickstart verification steps in `specs/001-offer-category/quickstart.md`
- [ ] T026 [P] Run backend tests and capture results in PR notes from `offer-backend`
- [ ] T027 [P] Run frontend lint and capture results in PR notes from `offer-frontend`

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3+)**: All depend on Foundational phase completion
  - User stories can then proceed in parallel (if staffed)
  - Or sequentially in priority order (P1 → P2)
- **Polish (Final Phase)**: Depends on all desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 2 (P2)**: Depends on User Story 1 API foundations and edit-mode data

### Within Each User Story

- Tests (if included) MUST be written and FAIL before implementation
- DTOs before services
- Services before controllers
- Backend before frontend integration

### Parallel Opportunities

- Foundational entity/repo/DTO work can run in parallel
- Test tasks marked [P] can run in parallel
- Frontend API/types can run in parallel with backend controller work after DTOs exist

---

## Parallel Example: User Story 1

```bash
# Launch tests together:
Task: "Add service category update tests in offer-backend/src/test/java/com/thirdmoira/offer_backend/api/ServiceCategoryControllerTest.java"
Task: "Add service-category authorization tests in offer-backend/src/test/java/com/thirdmoira/offer_backend/domain/ServiceCategoryServiceTest.java"

# Launch frontend wiring in parallel:
Task: "Add frontend API client calls in offer-frontend/src/api/offerApi.tsx or offer-frontend/src/api/serviceApi.tsx"
Task: "Add frontend types for categories/services in offer-frontend/src/types/offer.ts"
```

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational
3. Complete Phase 3: User Story 1
4. **STOP and VALIDATE**: Verify category updates and validation rules
5. Demo edit-mode category assignment

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test independently → Demo
3. Add User Story 2 → Verify edit-only visibility

### Parallel Team Strategy

With multiple developers:

1. Team completes Setup + Foundational together
2. Once Foundational is done:
   - Developer A: User Story 1 backend
   - Developer B: User Story 1 frontend
   - Developer C: User Story 2 edit-mode wiring

---

## Notes

- [P] tasks = different files, no dependencies
- [Story] label maps task to specific user story for traceability
- Each user story should be independently completable and testable
- Verify tests fail before implementing
- Stop at any checkpoint to validate story independently
- Avoid: vague tasks, same file conflicts, cross-story dependencies that break independence
