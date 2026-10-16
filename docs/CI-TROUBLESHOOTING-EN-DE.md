# Continuous Integration — Troubleshooting / Fehlerbehebung

## EN — Incident: missing frontend dependency lockfile

### What the uploaded GitHub Actions logs show

- Backend CI completed successfully: **23 tests passed**, zero failures and errors.
- Frontend CI stopped in `actions/setup-node` with:
  `Some specified paths were not resolved, unable to cache dependencies.`
- The workflow referenced `frontend/package-lock.json`, which was not part of the distributed starter. No frontend install, tests or build ran in that job.

### Changes in this revision

1. GitHub Actions no longer attempts to cache a lockfile that does not exist.
2. The installation step uses `npm install` for an initial checkout, or `npm ci` if a committed lockfile exists.
3. Upgrade `@vitejs/plugin-react` from 5.x to 6.x and Vitest from 3.x to 5.x for Vite 8 compatibility.
4. Use Node 24 LTS consistently in GitHub Actions and Docker.
5. Update the GitHub Actions checkout, Node and Java setup actions to current supported major versions.

### Strongly recommended next step — commit a generated lockfile

On a machine with npm registry access, run:

```bash
cd frontend
npm install --no-audit --no-fund
npm run test
npm run build
cd ..
git add frontend/package-lock.json
git commit -m "build: add reproducible frontend dependency lockfile"
git push
```

After confirming `frontend/package-lock.json` is tracked by Git, replace the frontend `setup-node` configuration with:

```yaml
- uses: actions/setup-node@v7
  with:
    node-version: '24'
    cache: npm
    cache-dependency-path: frontend/package-lock.json
```

The workflow already switches to `npm ci` when the lockfile is present; Docker does too. Until then, installations resolve versions afresh and are **not deterministic**.

### Current validation limit

This revision was prepared after reading the reported CI logs. No new GitHub Actions run is shown for this revision. The package registry was unreachable from the creation environment (`EAI_AGAIN`), so an actual `npm install`, Vitest run and Vite build could not be performed here. Do not claim that the updated frontend CI has passed until the workflow completes successfully on GitHub.

---

## DE — Vorfall: fehlende Lockdatei im Frontend

### Ergebnis der hochgeladenen GitHub-Actions-Protokolle

- Backend-CI erfolgreich: **23 Tests bestanden**, keine Fehler.
- Frontend-CI wurde in `actions/setup-node` abgebrochen mit:
  `Some specified paths were not resolved, unable to cache dependencies.`
- Das Workflow-Caching verwies auf `frontend/package-lock.json`, obwohl diese Datei im Starter fehlte. Daher wurden die Frontend-Tests und der Build nicht gestartet.

### Korrekturen

1. Das Caching einer nicht vorhandenen Datei ist deaktiviert.
2. Vorhandene Lockdatei: `npm ci`; ohne Lockdatei: `npm install`.
3. Versionsanpassung für Vite 8: React-Plugin 6.x und Vitest 5.x.
4. Node 24 LTS für CI und Docker.
5. Aktualisierte GitHub-Actions-Versionen.

### Lockdatei erzeugen und versionieren

Auf einem Rechner mit Zugang zur npm-Registry:

```bash
cd frontend
npm install --no-audit --no-fund
npm run test
npm run build
cd ..
git add frontend/package-lock.json
git commit -m "build: add reproducible frontend dependency lockfile"
git push
```

Anschliessend kann das npm-Caching mit `cache: npm` und `cache-dependency-path: frontend/package-lock.json` erneut aktiviert werden (Konfigurationsbeispiel im englischen Abschnitt). Die Lockdatei soll ins Git-Repository aufgenommen werden. Ohne Lockdatei sind neue Installationen nicht vollstaendig reproduzierbar.

### Nicht verifiziert

Mangels Erreichbarkeit der npm-Registry konnte der Frontend-Build hier nicht lokal ausgefuehrt werden. Ein erfolgreicher neuer GitHub-Actions-Lauf muss erst bestaetigt werden.
