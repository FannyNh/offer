# Java Clean Code and DDD Rules

Use these rules as the default baseline for backend implementation quality.

## 1) Single Responsibility and Cohesion

- Each class should have one reason to change.
- Keep controllers focused on transport concerns.
- Keep domain services focused on business behaviors.
- Keep repositories focused on persistence operations.

## 2) Ubiquitous Language

- Use business language from the domain in class names, method names, and variables.
- Avoid technical names for domain operations (for example, prefer `publishOffer` over `processEntity`).

## 3) Explicit Boundaries

- API contracts (`api/rest`) are not domain models.
- Domain models are not persistence entities.
- Keep conversion logic in dedicated mapper classes.

## 4) Dependency Direction

- Domain is core and should not depend on delivery or persistence details.
- Spring/JPA annotations should stay in infrastructure layers when possible.

## 5) Method and Class Design

- Prefer small methods with a single intent.
- Limit nested conditionals; use guard clauses for clarity.
- Avoid boolean flags that alter method behavior; split methods when intent differs.
- Keep constructors and factory methods explicit and valid-by-default.

## 6) Error Handling

- Throw specific exception types for specific failure modes.
- Do not swallow exceptions silently.
- Convert technical exceptions to domain/API-safe errors at boundaries.

## 7) Null and Optional Handling

- Avoid returning `null` for collections; return empty collections.
- Use `Optional` in return types where absence is expected and meaningful.
- Validate nullable inputs at boundaries.

## 8) Immutability and Side Effects

- Prefer immutable DTOs and value-like domain data where practical.
- Keep side effects isolated and obvious.
- Do not mix query and command behavior in the same method unless unavoidable.

## 9) Transaction and Consistency Rules

- Define transaction boundaries in service layer.
- Keep write operations atomic for business invariants.
- Read/write flows should make consistency assumptions explicit.

## 10) Testing and Maintainability

- Test behavior, not implementation details.
- Keep tests deterministic and readable.
- Use descriptive test names that communicate business scenarios.
- Refactor opportunistically only when it improves clarity without expanding scope.

