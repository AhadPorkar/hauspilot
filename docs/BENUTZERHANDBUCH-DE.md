# Benutzerhandbuch — Deutsch

1. Nach dem Anpassen der `.env` die Anwendung mit `docker compose up --build -d` starten. Im Browser `http://localhost:3000` öffnen.
2. Standardmäßig ist die Oberfläche auf Englisch eingestellt. Über **DE** oben rechts auf Deutsch umschalten. Die Oberfläche zeigt auch ohne Backend Beispieldaten an. Über das Schlüsselsymbol kann eine Verbindung mit den lokalen Demo-Zugangsdaten hergestellt werden.
3. Unter **Übersicht** sehen Sie Kennzahlen, Serviceanfragen, Schnellaktionen und die Immobilienübersicht.
4. Unter **Immobilien** können Sie Objekte vom Typ WEG, Vermietung oder gemischte Nutzung erfassen. Mit **Neuer Eintrag** öffnet sich das Formular.
5. Unter **Serviceanfragen** erfassen Sie Reparaturen und Anliegen von Mietparteien einschließlich Objekt, Beschreibung und Priorität. Bestehende Anfragen können bearbeitet bzw. erledigt markiert werden.
6. Unter **Freigaben** erfassen Sie operative Ausgabenfreigaben. **Achtung:** Eine Freigabe ist kein rechtswirksamer WEG-Beschluss.
7. Unter **Versammlungen** erfassen Sie Termine, Orte und Entwürfe von Niederschriften. Für rechtsgültige Einladungen, Abstimmungen, Unterschriften und Fristen bestehen noch keine vollständigen Funktionen.
8. Die **Beschlusssammlung** speichert nummerierte Einträge mit Wortlaut und Ergebnis. Eine elektronische rechtsverbindliche Beschlussfassung findet nicht statt.
9. Unter **Korrespondenz** erstellen Sie Entwürfe. Es wird keine E-Mail oder Post automatisch versendet.
10. Die Suchfunktion filtert Einträge; über das Menüsymbol ist die Navigation auf Mobilgeräten erreichbar.

**Hinweis zum Datenschutz:** Im Vorschaumodus werden Änderungen nur im Arbeitsspeicher gehalten. Im verbundenen Modus erfolgt die Speicherung in PostgreSQL. Bitte keine echten personenbezogenen Kundendaten in einer öffentlich erreichbaren Demoumgebung verwenden. Vor produktivem Einsatz sind Mandantentrennung, Rollen und Rechte, DSGVO-Prozesse, Protokollierung und ein Sicherheitsreview erforderlich.
