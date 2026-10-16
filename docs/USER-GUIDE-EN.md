# User Guide — English

1. Start with `docker compose up --build -d` after configuring `.env`. Open `http://localhost:3000`.
2. The UI defaults to English and shows usable preview records. Click the key icon in the header, enter your local demo credentials and connect to PostgreSQL-backed APIs. A green LIVE indicator confirms an active connection. If unavailable, preview mode continues with browser-memory data.
3. Choose **Overview** for portfolio counts, requests, shortcuts and properties.
4. Choose **Properties** to view/add rental buildings, WEG communities and mixed properties. To create use **New record** and fill all required fields.
5. Choose **Service requests** to enter repairs and complaints. Assign property, requester, priority and category. Existing records can be progressed or resolved with status buttons.
6. Choose **Approvals** to register operational spending approvals; approve/reject a pending item. This does **not** represent a legally binding WEG vote.
7. Choose **Meetings** to record a meeting schedule and draft minutes. Meeting status transitions have an API endpoint; UI currently focuses on creation and browsing.
8. Choose **Resolutions** for a numbered meeting resolution record. Recording an item is **not** a legally valid electronic voting system.
9. Choose **Correspondence** to draft outgoing notices. Changing status to SENT in the API marks a record only; it does not send anything.
10. Switch EN/DE at any time in the top-right header. Use search to find entries. Mobile navigation opens from the menu icon.

**Important demo note:** preview-only records disappear after refresh; PostgreSQL-backed records persist. There are no tenant or owner accounts and no GDPR export/delete interface. Do not load real customer PII into a public preview installation.
