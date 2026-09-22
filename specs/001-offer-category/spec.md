# Feature Specification: Offer Item Service Category Management

**Feature Branch**: `001-offer-category`  
**Created**: 2026-02-04  
**Status**: Draft  
**Input**: User description: "manage offer's category"

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Assign or Change Service Category (Priority: P1)

Offer owners need to set or update a category for each service inside each
offer item so services are organized and easy to understand.

**Why this priority**: Service-level categorization is the core of the feature
and enables consistent organization across complex offers.

**Independent Test**: Create an offer item with services as the owner, assign a
category to a service, save, and verify the category is displayed for that
service.

**Acceptance Scenarios**:

1. **Given** an authenticated offer owner and an offer item with services,
   **When** they assign a category to a service and save, **Then** the service
   shows the selected category.
2. **Given** an authenticated offer owner, **When** they select a category that
   is not available, **Then** the system rejects the update with a clear message.

---

### User Story 2 - View Service Categories in Edit Mode (Priority: P2)

Offer owners need to see each service's category while editing an offer so they
can verify and adjust categorization before saving.

**Why this priority**: The category is an editing aid and should not appear in
client-facing views.

**Independent Test**: Open an offer in edit mode and confirm each service shows
its category; then view the offer preview and confirm categories are hidden.

**Acceptance Scenarios**:

1. **Given** services with categories, **When** an offer owner opens edit mode,
   **Then** each service displays its category label.
2. **Given** services with categories, **When** a user views the offer preview or
   the client-facing offer page, **Then** service categories are not shown.

---

### Edge Cases

- What happens when no categories exist to select?
- How does the system handle a service with no category assigned?
- How does the system handle unauthorized attempts to change a category?

## Assumptions

- A category “exists” if it is present in `service_categories` for the user.
- A service can have only one category at a time.
- Only the offer owner can change service categories.
- Offer items can contain multiple services, each categorized independently.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The system MUST show the list of existing categories for the
  authenticated user when creating or editing services within an offer item.
- **FR-002**: The system MUST allow an offer owner to assign exactly one category
  to each service within an offer item.
- **FR-003**: The system MUST allow an offer owner to change the category of a
  service on an existing offer item.
- **FR-004**: The system MUST reject category updates that reference a category
  that does not exist for the authenticated user.
- **FR-005**: The system MUST display the category label for each service in
  offer edit mode and MUST NOT show service categories in offer previews or
  client-facing offer pages.
- **FR-006**: The system MUST allow services to be saved without a category and
  display them as "Uncategorized".

### Security & Access Control *(mandatory for authenticated features)*

- **SA-001**: All service category assignment and update actions are
  authenticated.
- **SA-002**: Only the offer owner can assign or change service categories.
- **SA-003**: Category data is not sensitive; no masking or redaction required.

### Key Entities *(include if feature involves data)*

- **Offer**: Represents a posted offer containing one or more offer items.
- **Offer Item**: Represents a grouping of services within an offer.
- **Service**: Represents an individual service with an optional category.
- **Category**: Represents a predefined label with a name and active status.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: 95% of service category assignment attempts complete without
  user-reported errors in a 30-day period.
- **SC-002**: Users can assign or change a service category in under 30 seconds
  for a typical offer item.
- **SC-003**: 100% of services that have a category show the label in edit mode,
  and 0% of categories appear in offer previews or client-facing pages during
  spot checks.
- **SC-004**: The percentage of services left uncategorized is under 15% within
  30 days of release.
