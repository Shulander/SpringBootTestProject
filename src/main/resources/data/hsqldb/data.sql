/**
 * HSQLDB script.
 * Load the database with reference data and unit test data.
 * Author:  Shulander
 * Created: 25-Apr-2016
 */
INSERT INTO Greeting (referenceId, text, version, createdBy, createdAt, updatedBy, updatedAt) VALUES ('bbc5e8a2-43c3-4860-b13a-fc0c83924681', 'Hello World!', 0, 'user', NOW(), NULL, NULL);
INSERT INTO Greeting (referenceId, text, version, createdBy, createdAt, updatedBy, updatedAt) VALUES ('a839c-884c-4e78-bbb9-7bcf8b030a61', 'Hola Mundo!', 0, 'user', NOW(), NULL, NULL);


-- password is 'password'
INSERT INTO Account (referenceId, username, password, enabled, credentialsexpired, expired, locked, version, createdBy, createdAt, updatedBy, updatedAt) VALUES ('19322efd-8a3b-44c9-bff3-45af2160a53d', 'user', '$2a$10$9/44Rne7kQqPXa0cY6NfG.3XzScMrCxFYjapoLq/wFmHz7EC9praK', true, false, false, false, 0, 'user', NOW(), NULL, NULL);
-- password is 'operations'
INSERT INTO Account (referenceId, username, password, enabled, credentialsexpired, expired, locked, version, createdBy, createdAt, updatedBy, updatedAt) VALUES ('5eef0e2d-f71b-49b3-b967-75d69233f718', 'operations', '$2a$10$CoMVfutnv1qZ.fNlHY1Na.rteiJhsDF0jB1o.76qXcfdWN6As27Zm', true, false, false, false, 0, 'user', NOW(), NULL, NULL);

INSERT INTO Role (id, code, label, ordinal, effectiveAt, expiresAt, createdAt) VALUES (1, 'ROLE_USER', 'User', 0, '2016-05-01 00:00:00', NULL, NOW());
INSERT INTO Role (id, code, label, ordinal, effectiveAt, expiresAt, createdAt) VALUES (2, 'ROLE_ADMIN', 'Admin', 1, '2016-05-01 00:00:00', NULL, NOW());
INSERT INTO Role (id, code, label, ordinal, effectiveAt, expiresAt, createdAt) VALUES (3, 'ROLE_SYSADMIN', 'System Admin', 2, '2016-05-01 00:00:00', NULL, NOW());

INSERT INTO AccountRole (accountId, roleId) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'user' and r.id = 1;
INSERT INTO AccountRole (accountId, roleId) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'operations' and r.id = 3;
