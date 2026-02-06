# Quickstart: Offer Item Service Category Management

## Prerequisites

- Backend dependencies installed (Maven wrapper available).
- Frontend dependencies installed (npm).
- Firebase credentials configured for authenticated endpoints.

## Run Backend

```bash
cd offer-backend
./mvnw spring-boot:run
```

## Run Frontend

```bash
cd offer-frontend
npm install
npm run dev
```

## Manual Verification (Edit Mode)

1. Sign in as the offer owner.
2. Open an offer in edit mode.
3. Confirm each service shows its category label or "Uncategorized".
4. Change a service category and save.
5. Re-open edit mode and verify the category persists.
6. Open the offer preview and client-facing offer page and verify categories are
   not shown.
