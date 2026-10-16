# HAUSPILOT 🏡 — Property intelligence / Hausverwaltung

**Property, rental and WEG management workspace · Spring Boot 4 · PostgreSQL 18 · React 19 · Vite 8**

A polished, open-source portfolio demonstrator for German property-management workflows. Designed by **[Ahad Porkar](https://ahadporkar.com/)** · [GitHub](https://github.com/AhadPorkar) · [LinkedIn](https://www.linkedin.com/in/seapr/).

> **Scope / honest status:** Functional demonstration/MVP; **NOT a legally certified, security-audited, sell-ready multi-tenant SaaS**. The UI has a self-contained interactive preview. Connect the API to persist records. Real email delivery, tenant access, owner voting, legally valid WEG meeting procedure, documents/signatures, billing, accounting, and production IAM are not implemented. See `docs/PRODUCTION-ROADMAP.md`.

## Features
- Interactive EN-default / DE bilingual dashboard, animated portfolio art, mobile navigation, accessible forms and responsive tables.
- Properties (WEG, rental, mixed), tenant/service requests, operational approvals, meetings and minutes, resolution register, correspondence drafts.
- CRUD **create + list**, status transitions for requests/approvals/meetings/correspondence, record-level audit activity for mutations.
- PostgreSQL migrations and development sample records, Basic Auth **development only**, backend and frontend unit tests, GitHub Actions.
- Search, filtering, contextual shortcuts, demonstrable frontend preview even without a backend connection.

## Requirements
Docker Engine with Docker Compose (recommended), or Java 21 + Maven 3.9 + Node 24 (LTS) + PostgreSQL 18.

## Quick start — complete stack
```bash
cp .env.example .env
# Edit .env, replacing both secret values
# Start containers
docker compose up --build -d
```
Open **http://localhost:3000**. The preview renders instantly; click the **key icon** to connect to the backend using credentials from `.env`. API: http://localhost:8080/api/overview (Basic Auth). Health: http://localhost:8080/actuator/health.

## Local development
```bash
# First start the database:
docker compose up -d db
cd backend && mvn spring-boot:run
# New terminal:
cd frontend && npm install && npm run dev
```
Open http://localhost:5173. Backend demo defaults are `admin` / `change-me-before-deploy` **only for local use**. Frontend includes preview data if API isn't connected. Data entered while disconnected only lasts until refresh.

## Quality gates
```bash
cd backend && mvn test
cd frontend && npm install && npm test && npm run build
```
**CI note:** The first release omitted `frontend/package-lock.json`, causing `setup-node` caching to fail before frontend tests started. This revision fixes the workflow and aligns Vite 8 dependencies. On a networked machine, run `cd frontend && npm install` and commit `frontend/package-lock.json` to enable reproducible `npm ci` installs and re-enable CI caching. See `docs/CI-TROUBLESHOOTING-EN-DE.md` for exact steps.

GitHub Actions runs unit tests and the frontend production build on push/PR. See `docs/ARCHITECTURE.md`, `docs/USER-GUIDE-EN.md`, `docs/BENUTZERHANDBUCH-DE.md`, `docs/API.md`, `docs/PRODUCTION-ROADMAP.md`.

## API examples
```bash
curl -u admin:$APP_PASSWORD http://localhost:8080/api/properties
curl -u admin:$APP_PASSWORD -H 'Content-Type: application/json' -d '{"property_id":"11111111-1111-4111-8111-111111111111","title":"Broken lock","description":"Entrance door","requester":"M. Example","category":"ACCESS","priority":"HIGH"}' http://localhost:8080/api/requests
```

## Legal
WEG = Wohnungseigentumsgesetz; operational approvals in this application are NOT legally sufficient owner resolutions. See official law [WEG §§ 23–25](https://www.gesetze-im-internet.de/woeigg/) and consult German counsel before commercial use. **MIT License** (code only); external dependencies have their own licenses.
