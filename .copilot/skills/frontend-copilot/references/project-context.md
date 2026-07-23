# Project Context (Frontend)

## Stack

- TypeScript 5.8
- React 19
- Vite 7
- Axios
- React Router
- React Hook Form

## Key Directories

- `offer-frontend/src/pages/`: route-level UI and page orchestration
- `offer-frontend/src/components/`: reusable UI components
- `offer-frontend/src/hooks/`: reusable state and side-effect logic
- `offer-frontend/src/context/`: app-level context providers
- `offer-frontend/src/api/`: API clients and transport concerns
- `offer-frontend/src/router/`: routing setup
- `offer-frontend/src/types/`: shared frontend types

## Delivery Principles

- Prefer strict typing over `any`.
- Keep side effects in hooks or API modules, not presentational components.
- Handle loading, empty, and error states explicitly.
- Keep route/page files thin; extract repeated logic.
- Favor accessible UI interactions and semantics.


