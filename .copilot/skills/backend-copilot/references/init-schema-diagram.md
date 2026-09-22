# Offer Backend Schema Diagram

This diagram is based on `offer-backend/src/main/resources/init.sql`.

## Mermaid ER Diagram

```mermaid
erDiagram
    groups {
        int id PK
        string group_name
    }

    users {
        int id PK
        string first_name
        string last_name
        string email UK
        string idp_id
        int group_id FK
        string company_name
        string company_logo
        string company_address
        jsonb settings
        timestamp created_at
        timestamp updated_at
    }

    clients {
        int id PK
        int user_id FK
        string name
        string email
        string company
        string billing_address
        jsonb meta
        timestamp created_at
        timestamp updated_at
    }

    units {
        int id PK
        string code UK
        string label
    }

    service_categories {
        int id PK
        int user_id FK
        string name
    }

    taxes {
        int id PK
        int user_id FK
        string name
        decimal rate
    }

    services {
        int id PK
        int user_id FK
        int category_id FK
        int unit_id FK
        decimal base_price
        int default_tax_id FK
        string title
        string description
        boolean active
        jsonb meta
        timestamp created_at
        timestamp updated_at
    }

    offers {
        int id PK
        int user_id FK
        int client_id FK
        string reference
        string status
        int accepted_version_id FK
        timestamp sent_at
        timestamp accepted_at
        timestamp rejected_at
        timestamp created_at
        timestamp updated_at
    }

    offer_versions {
        int id PK
        int offer_id FK
        int version_number
        string title
        string name
        string offer_description
        string currency
        date valid_until
        string notes
        decimal discount_pct
        decimal discount_amt
        boolean tax_inclusive
        timestamp created_at
        timestamp updated_at
    }

    offer_sections {
        int id PK
        int offer_version_id FK
        int parent_section_id FK
        string title
        int position
        timestamp created_at
        timestamp updated_at
    }

    offer_items {
        int id PK
        int section_id FK
        int service_id FK
        string title
        string description
        int unit_id FK
        decimal quantity
        decimal unit_price
        decimal discount_pct
        int tax_id FK
        string price_source
        int position
        timestamp created_at
        timestamp updated_at
    }

    offer_documents {
        int id PK
        int offer_version_id FK
        string format
        string storage_url
        boolean is_official
        timestamp created_at
    }

    groups ||--o{ users : "group_id"
    users ||--o{ clients : "user_id"
    users ||--o{ service_categories : "user_id"
    users ||--o{ taxes : "user_id"
    users ||--o{ services : "user_id"
    users ||--o{ offers : "user_id"

    service_categories ||--o{ services : "category_id"
    units ||--o{ services : "unit_id"
    taxes ||--o{ services : "default_tax_id"

    clients ||--o{ offers : "client_id"
    offers ||--o{ offer_versions : "offer_id"
    offer_versions ||--o{ offer_sections : "offer_version_id"
    offer_versions ||--o{ offer_documents : "offer_version_id"
    offer_sections ||--o{ offer_sections : "parent_section_id"
    offer_sections ||--o{ offer_items : "section_id"

    services ||--o{ offer_items : "service_id"
    units ||--o{ offer_items : "unit_id"
    taxes ||--o{ offer_items : "tax_id"
    offer_versions ||--o| offers : "accepted_version_id"
```

## Reading Guide

### 1. Identity / ownership

- `groups` groups users.
- `users` is the main owner table.
- Most business data belongs to one user.

### 2. Catalog data

- `service_categories` stores categories like design, consulting, etc.
- `services` stores reusable services a user can offer.
- `units` stores units such as hour, day, item.
- `taxes` stores reusable tax definitions.

### 3. Offer structure

- `offers` is the top-level commercial document.
- `offer_versions` stores versions/snapshots of one offer.
- `offer_sections` structures a version into sections/subsections.
- `offer_items` stores the priced rows inside sections.
- `offer_documents` stores generated outputs (for example PDFs).

### 4. Important relationship flow

The main business flow is:

`users` -> `services` / `clients` -> `offers` -> `offer_versions` -> `offer_sections` -> `offer_items`

### 5. Special cases to notice

- `offers.accepted_version_id` points to the chosen final row in `offer_versions`.
- `offer_sections.parent_section_id` is a self-reference, which means a section can contain child sections.
- `offer_items.service_id` is optional in practice for linking an item back to a reusable service.

## Quick Mental Map

- **User owns data**
- **Catalog prepares reusable business objects**
- **Offer stores the customer-facing document**
- **Version freezes one state of the offer**
- **Section organizes content**
- **Item stores price lines**

