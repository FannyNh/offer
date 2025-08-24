-- =====================================================================
--  Idempotent schema for PostgreSQL ≥ 15
--  Safe to re-run; works well as Spring Boot schema.sql
-- =====================================================================

SET lock_timeout = '5s';
SET statement_timeout = '0';
SET client_min_messages = warning;

-- =====================================================================
-- Tables
-- =====================================================================

CREATE TABLE IF NOT EXISTS groups (
    id SERIAL PRIMARY KEY,
    group_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    group_id INT,
    company_name VARCHAR,
    company_logo TEXT,
    company_address TEXT,
    settings JSONB,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS clients (
    id SERIAL PRIMARY KEY,
    user_id INT,
    name VARCHAR NOT NULL,
    email VARCHAR,
    company VARCHAR,
    billing_address TEXT,
    meta JSONB,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS units (
    id SERIAL PRIMARY KEY,
    code VARCHAR UNIQUE,
    label VARCHAR
);

CREATE TABLE IF NOT EXISTS service_categories (
    id SERIAL PRIMARY KEY,
    user_id INT,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS taxes (
    id SERIAL PRIMARY KEY,
    user_id INT,
    name VARCHAR,
    rate NUMERIC(5,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS services (
    id SERIAL PRIMARY KEY,
    user_id INT,
    category_id INT,
    title VARCHAR NOT NULL,
    description TEXT,
    unit_id INT,
    base_price NUMERIC(12,2) NOT NULL,
    default_tax_id INT,
    active BOOLEAN DEFAULT TRUE,
    meta JSONB,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS offers (
    id SERIAL PRIMARY KEY,
    user_id INT,
    client_id INT,
    reference VARCHAR,
    status VARCHAR,
    accepted_version_id INT,
    sent_at TIMESTAMP,
    accepted_at TIMESTAMP,
    rejected_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS offer_versions (
    id SERIAL PRIMARY KEY,
    offer_id INT,
    version_number INT NOT NULL,
    title VARCHAR,
    offer_description VARCHAR(1000),
    currency VARCHAR(3) NOT NULL,
    valid_until DATE,
    notes TEXT,
    discount_pct NUMERIC(5,2),
    discount_amt NUMERIC(12,2),
    tax_inclusive BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    UNIQUE (offer_id, version_number)
);

CREATE TABLE IF NOT EXISTS offer_sections (
    id SERIAL PRIMARY KEY,
    offer_version_id INT,
    parent_section_id INT,
    title VARCHAR NOT NULL,
    position INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS offer_items (
    id SERIAL PRIMARY KEY,
    section_id INT,
    service_id INT,
    title VARCHAR NOT NULL,
    description TEXT,
    unit_id INT,
    quantity NUMERIC(12,2) NOT NULL DEFAULT 1,
    unit_price NUMERIC(12,2) NOT NULL,
    discount_pct NUMERIC(5,2),
    tax_id INT,
    price_source VARCHAR,
    position INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS offer_documents (
    id SERIAL PRIMARY KEY,
    offer_version_id INT,
    format VARCHAR NOT NULL,
    storage_url TEXT NOT NULL,
    is_official BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT now()
);

-- =====================================================================
-- Foreign Keys (idempotent, safe for Spring Boot)
-- =====================================================================

-- Drop existing constraints if they exist, then add
ALTER TABLE users DROP CONSTRAINT IF EXISTS fk_group;
ALTER TABLE users ADD CONSTRAINT fk_group
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE SET NULL;

ALTER TABLE clients DROP CONSTRAINT IF EXISTS fk_client_user;
ALTER TABLE clients ADD CONSTRAINT fk_client_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE service_categories DROP CONSTRAINT IF EXISTS fk_service_category_user;
ALTER TABLE service_categories ADD CONSTRAINT fk_service_category_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE taxes DROP CONSTRAINT IF EXISTS fk_tax_user;
ALTER TABLE taxes ADD CONSTRAINT fk_tax_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE services DROP CONSTRAINT IF EXISTS fk_service_user;
ALTER TABLE services ADD CONSTRAINT fk_service_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE services DROP CONSTRAINT IF EXISTS fk_service_category;
ALTER TABLE services ADD CONSTRAINT fk_service_category
    FOREIGN KEY (category_id) REFERENCES service_categories(id);

ALTER TABLE services DROP CONSTRAINT IF EXISTS fk_service_unit;
ALTER TABLE services ADD CONSTRAINT fk_service_unit
    FOREIGN KEY (unit_id) REFERENCES units(id);

ALTER TABLE services DROP CONSTRAINT IF EXISTS fk_service_tax;
ALTER TABLE services ADD CONSTRAINT fk_service_tax
    FOREIGN KEY (default_tax_id) REFERENCES taxes(id);

ALTER TABLE offers DROP CONSTRAINT IF EXISTS fk_offer_user;
ALTER TABLE offers ADD CONSTRAINT fk_offer_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL;

ALTER TABLE offers DROP CONSTRAINT IF EXISTS fk_offer_client;
ALTER TABLE offers ADD CONSTRAINT fk_offer_client
    FOREIGN KEY (client_id) REFERENCES clients(id);

ALTER TABLE offer_versions DROP CONSTRAINT IF EXISTS fk_offer_version_offer;
ALTER TABLE offer_versions ADD CONSTRAINT fk_offer_version_offer
    FOREIGN KEY (offer_id) REFERENCES offers(id) ON DELETE CASCADE;

ALTER TABLE offers DROP CONSTRAINT IF EXISTS fk_accepted_version;
ALTER TABLE offers ADD CONSTRAINT fk_accepted_version
    FOREIGN KEY (accepted_version_id) REFERENCES offer_versions(id);

ALTER TABLE offer_sections DROP CONSTRAINT IF EXISTS fk_section_offer_version;
ALTER TABLE offer_sections ADD CONSTRAINT fk_section_offer_version
    FOREIGN KEY (offer_version_id) REFERENCES offer_versions(id) ON DELETE CASCADE;

ALTER TABLE offer_sections DROP CONSTRAINT IF EXISTS fk_section_parent;
ALTER TABLE offer_sections ADD CONSTRAINT fk_section_parent
    FOREIGN KEY (parent_section_id) REFERENCES offer_sections(id);

ALTER TABLE offer_items DROP CONSTRAINT IF EXISTS fk_item_section;
ALTER TABLE offer_items ADD CONSTRAINT fk_item_section
    FOREIGN KEY (section_id) REFERENCES offer_sections(id) ON DELETE CASCADE;

ALTER TABLE offer_items DROP CONSTRAINT IF EXISTS fk_item_service;
ALTER TABLE offer_items ADD CONSTRAINT fk_item_service
    FOREIGN KEY (service_id) REFERENCES services(id);

ALTER TABLE offer_items DROP CONSTRAINT IF EXISTS fk_item_unit;
ALTER TABLE offer_items ADD CONSTRAINT fk_item_unit
    FOREIGN KEY (unit_id) REFERENCES units(id);

ALTER TABLE offer_items DROP CONSTRAINT IF EXISTS fk_item_tax;
ALTER TABLE offer_items ADD CONSTRAINT fk_item_tax
    FOREIGN KEY (tax_id) REFERENCES taxes(id);

-- =====================================================================
-- Indexes (idempotent)
-- =====================================================================
CREATE INDEX IF NOT EXISTS idx_users_email            ON users (email);
CREATE INDEX IF NOT EXISTS idx_clients_user           ON clients (user_id);
CREATE INDEX IF NOT EXISTS idx_services_user          ON services (user_id);
CREATE INDEX IF NOT EXISTS idx_services_category      ON services (category_id);
CREATE INDEX IF NOT EXISTS idx_services_unit          ON services (unit_id);
CREATE INDEX IF NOT EXISTS idx_offers_user            ON offers (user_id);
CREATE INDEX IF NOT EXISTS idx_offers_client          ON offers (client_id);
CREATE INDEX IF NOT EXISTS idx_offer_versions_offer   ON offer_versions (offer_id);
CREATE INDEX IF NOT EXISTS idx_offer_sections_version ON offer_sections (offer_version_id);
CREATE INDEX IF NOT EXISTS idx_offer_items_section    ON offer_items (section_id);
