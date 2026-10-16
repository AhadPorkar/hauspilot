INSERT INTO properties(id,name,address,city,postal_code,units,kind) VALUES
('11111111-1111-4111-8111-111111111111','Lindenhöfe Mitte','Linienstraße 84','Berlin','10119',42,'WEG'),
('22222222-2222-4222-8222-222222222222','Am Park Residences','Parkstraße 12','Munich','80331',28,'RENTAL'),
('33333333-3333-4333-8333-333333333333','Rheinufer Quartier','Rheinufer 8','Cologne','50668',64,'MIXED');
INSERT INTO requests(property_id,title,description,requester,category,priority,status) VALUES
('11111111-1111-4111-8111-111111111111','Heating malfunction — building B','Radiator valves not responding','Maria Schneider','HEATING','HIGH','IN_PROGRESS'),
('22222222-2222-4222-8222-222222222222','Water leak in apartment 3.02','Water under kitchen sink','Jonas Weber','PLUMBING','URGENT','OPEN'),
('33333333-3333-4333-8333-333333333333','Main entrance lighting','Entrance light flickering','Lena Becker','ELECTRICAL','NORMAL','WAITING'),
('11111111-1111-4111-8111-111111111111','Garage access fob','Replacement requested','Tom Fischer','ACCESS','LOW','RESOLVED');
INSERT INTO approvals(property_id,title,amount,requested_by,status,notes) VALUES
('11111111-1111-4111-8111-111111111111','Elevator maintenance contract',4250,'Property manager','PENDING','Operational approval; not a WEG resolution'),
('33333333-3333-4333-8333-333333333333','Courtyard landscaping',1850,'Property manager','APPROVED','');
INSERT INTO meetings(id,property_id,title,meeting_date,location,minutes,status) VALUES
('44444444-4444-4444-8444-444444444444','11111111-1111-4111-8111-111111111111','Annual owners assembly 2026','2026-11-19','Community hall, Berlin','Agenda under preparation','PLANNED'),
('55555555-5555-4555-8555-555555555555','33333333-3333-4333-8333-333333333333','Extraordinary meeting','2026-09-28','Office Köln','Minutes require signatures','MINUTES_DRAFT');
INSERT INTO resolutions(meeting_id,resolution_no,wording,result,announced_on) VALUES('55555555-5555-4555-8555-555555555555','2026-001','Approve roof assessment engagement','ADOPTED','2026-09-28');
INSERT INTO correspondence(property_id,subject,body,recipient,channel,status) VALUES('11111111-1111-4111-8111-111111111111','Scheduled inspection','Dear residents, inspection is scheduled next week.','Residents','EMAIL','DRAFT');
