# Research: Offer Item Service Category Management

## Decisions

### Decision 1: Service-level category storage
**Decision**: Store category assignment on `services.category_id` and reference
categories from `service_categories`.
**Rationale**: This matches the existing schema in `init.sql` and supports
service-level categorization referenced by offer items.
**Alternatives considered**:
- Offer-level category: rejected because it does not allow different categories
  per service.
- Offer item-level category: rejected because the schema already links items to
  services, and category belongs to services.

### Decision 2: Edit-only category visibility
**Decision**: Include service category data only in edit-mode responses and edit
UI state; omit from preview/client-facing responses.
**Rationale**: The feature explicitly requires categories to be hidden from
client-facing views while visible during editing.
**Alternatives considered**:
- Always include categories and hide in UI: rejected because it risks accidental
  exposure in client-facing views and complicates API usage.

### Decision 3: Authorization rule
**Decision**: Only the offer owner can assign or change service categories,
validated using Firebase-authenticated identity.
**Rationale**: Matches existing auth model and prevents unauthorized edits.
**Alternatives considered**:
- Group-based editors: rejected due to no requirement for shared ownership.

### Decision 4: Category source
**Decision**: Categories are treated as a predefined list in `service_categories`
owned by the authenticated user; updates must reference an existing category.
**Rationale**: Keeps category management out of scope while ensuring integrity
and alignment with the current schema.
**Alternatives considered**:
- Free-text categories: rejected because it conflicts with the requirement for
  an available list and reduces consistency.
