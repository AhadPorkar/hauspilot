# API reference / API-Referenz

Base: `/api`. All endpoints require HTTP Basic Authentication in this demo; `/actuator/health` is public.

| Method | Route | Description |
|---|---|---|
| GET | /api/overview | Module counts and urgent/pending counts |
| GET | /api/{module} | List records descending by creation time |
| POST | /api/{module} | Create entity; returns HTTP 201 |
| PATCH | /api/{module}/{id}/status | Change status; expects `{"status":"VALUE"}` |

`module`: `properties`, `requests`, `approvals`, `meetings`, `resolutions`, `correspondence`. Status patch supports only `requests`, `approvals`, `meetings`, `correspondence`. Real server-generated UUIDs, timestamps and records.

### Create examples
Property: `{"name":"Haus am See","address":"Seestraße 9","city":"Hamburg","postal_code":"20095","units":12,"kind":"WEG"}`.
Request: `{"property_id":"<existing-uuid>","title":"Leaky tap","description":"Kitchen tap","requester":"Tenant","category":"PLUMBING","priority":"NORMAL"}`.
Approval: `{"property_id":"<existing-uuid>","title":"Roof inspection","amount":500,"requested_by":"Manager"}`.
Meeting: `{"property_id":"<existing-uuid>","title":"Owners meeting","meeting_date":"2026-12-10","location":"Berlin"}`.
Resolution: `{"meeting_id":"<existing-meeting-uuid>","resolution_no":"2026-002","wording":"Approve offer","result":"PENDING"}`.
Correspondence: `{"property_id":"<existing-uuid>","subject":"Annual notice","body":"Dear residents...","recipient":"Owners","channel":"EMAIL"}`.

**Important:** No send-mail / legally binding vote / signature endpoints are implemented. Basic Auth credentials should never be embedded into a public browser build, and this API is for local demonstration only.
