# Commercialization & German compliance readiness checklist

**This is a functional portfolio prototype, not a commercially deployable Hausverwaltung SaaS.** The following are non-negotiable before selling to customers:

1. Identity: OIDC (e.g., Keycloak), MFA/passkeys, tenant/owner/contractor roles; complete object-level authorization and tenant isolation, permission regression tests, OIDC logout and session hardening.
2. Domain: explicit tenancies and ownership shares, maintenance assignments, evidence uploads, vendors/offers, SLAs, notifications, invoice/expense ledgers, yearly WEG budgets, utility-cost statements with German review, homeowner portals.
3. WEG compliance: valid meeting invitations, per-community voting rules, attendance, proxies, voting rights, resolution announcement and numbering, challenge/annulment workflow, signed minutes, electronically distributed documents, retention periods reviewed by German legal counsel. See WEG §§ 23–25 and §24.
4. Privacy/security: GDPR DPA, EU hosting and subprocessors, privacy notices, DSAR export/erasure workflow with retention exceptions, data minimization, per-tenant encryption strategy, audit retention, threat model, penetration test, backups/restores and breach incident plan.
5. Reliable correspondence: EU email provider API, bounce/retry, consent, templates, message history, PDF generation and immutable evidence of delivery where required.
6. Operations: SaaS deployment, TLS, HSTS, CSRF strategy, CORS allowlist, rate limiting, rotation of secrets, structured telemetry, metrics, centralized logging, health/readiness and disaster recovery.
7. Software quality: Testcontainers database integration tests for ALL endpoints, authorization matrix, Playwright E2E tests, WCAG audit, SAST/DAST, dependency license/security scans, SBOM, deterministic lockfiles and CI security gates.
8. Billing/commercial: subscription invoicing, VAT rules, support, SLAs, Terms and Conditions, proper third-party licenses and legal review.

**No marketing claim of GDPR certification or WEG legal validity is made.**

Official references:
- https://www.gesetze-im-internet.de/woeigg/__23.html
- https://www.gesetze-im-internet.de/woeigg/__24.html
- https://www.gesetze-im-internet.de/woeigg/__25.html
- https://www.bmjv.de/DE/themen/verbraucherschutz/kaufen_reisen_wohnen/mietrecht/mietrecht_node.html
