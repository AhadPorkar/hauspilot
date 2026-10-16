# Architecture and code documentation

## System context
Browser (React/Vite UI) → Nginx reverse proxy → Spring Boot REST API → PostgreSQL 18. Flyway migrations own the relational schema. The UI can render preview fixtures disconnected from the API and uses real endpoints after authentication. HTTP Basic is deliberately a local-demo mechanism, not a recommended SaaS identity system.

## Backend modules
`HauspilotApplication`: Spring Boot entry point.
`SecurityConfig`: single developer account, Basic Auth, localhost CORS, unauthenticated health probe. Replace with OAuth2/OIDC and secure cookies / proper CSRF protection.
`ApiController`: explicit overview, generic six-module list/create routes and four workflow status endpoints; error mapping.
`DomainService`: module table whitelist, parameterized SQL for values, simple status mutation, audit records and dashboard totals.
`V1__schema.sql`: PostgreSQL schema with FK, checks, indexes.
`V2__seed.sql`: fictitious German sample dataset.

## Database
- properties: buildings and communities.
- requests: tenant maintenance issues; statuses OPEN, IN_PROGRESS, WAITING, RESOLVED.
- approvals: operational spending requests only; statuses PENDING, APPROVED, REJECTED.
- meetings: calendar items with minutes draft; PLANNED, HELD, MINUTES_DRAFT, FINALIZED.
- resolutions: separately numbered Beschluss-Sammlung entries with challenged flag, result and announcement date.
- correspondence: draft outgoing messages; DRAFT, READY, SENT (changing status does NOT deliver).
- activity: auditable create/status transitions, associated actor (no read audit).

## Security / limitations
Table names are whitelisted before embedding in SQL; values use named prepared parameters. DB constraints validate enum values, required values and FKs. UI does not implement tenancy, invitation-based access, file upload or outbound email. Status updates currently allow any enumerated target (DB validates final value only), not strict domain transition guards. This is a prototype shortcut, not a legal workflow engine. Inputs are not fully DTO validated, pagination / optimistic locking / structured logging / backups are not included. Basic authentication is not acceptable as the final SaaS production posture.

## Testing
Backend `DomainServiceTest` and parameterized `ModuleContractTest`: whitelist, input guard, missing records, audited creation/listing for six modules and status paths for four modules. Frontend `i18n.test.ts`: language key parity and language selection. Add Testcontainers PostgreSQL integration tests, API permission tests, E2E Playwright tests and security scans before public launch.

## UX design
Editorial green / sage design system, restrained motion, compact service activity, status chips, modern type, decorative CSS architectural illustration (no copyrighted stock visuals), keyboard-focus rings, reduced-motion preference. Default language EN, switch to DE in the top bar.
