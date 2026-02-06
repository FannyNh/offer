# Data Model: Offer Item Service Category Management

## Entities (aligned to `offer-backend/src/main/resources/init.sql`)

### Offer
- **Fields**: id, user_id, client_id, reference, status, accepted_version_id
- **Relationships**: Offer 1 → many OfferVersions

### OfferVersion
- **Fields**: id, offer_id, version_number, title, name, offer_description, currency
- **Relationships**: OfferVersion 1 → many OfferSections

### OfferSection
- **Fields**: id, offer_version_id, parent_section_id, title, position
- **Relationships**: OfferSection 1 → many OfferItems

### OfferItem
- **Fields**: id, section_id, service_id, title, description, unit_id, quantity
- **Relationships**: OfferItem many → 1 Service

### Service
- **Fields**: id, user_id, category_id (nullable), title, description, unit_id
- **Relationships**: Service many → 1 ServiceCategory (optional)

### ServiceCategory
- **Fields**: id, user_id, name
- **Relationships**: ServiceCategory 1 → many Services

## Validation Rules

- `services.category_id` must reference a ServiceCategory owned by the same
  user and available for assignment.
- Services may omit `category_id`; the system renders them as "Uncategorized" in
  edit mode.
- Only the offer owner can modify the category of services referenced by their
  offer items.

## State Transitions

- Service.category_id: null → category_id (assign)
- Service.category_id: category_id → different category_id (change)
- Service.category_id: category_id → null (clear)
