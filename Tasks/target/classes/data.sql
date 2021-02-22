
INSERT INTO user (id, e_mail, username, password, name, lastname, role)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO user (id, e_mail, username, password, name, lastname, role)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO user (id, e_mail, username, password, name, lastname, role)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
              
INSERT INTO state (id, name) VALUES (1, 'NEW');
INSERT INTO state (id, name) VALUES (2, 'IN PROGRESS');
INSERT INTO state (id, name) VALUES (3, 'FINISHED');

INSERT INTO sprint (id, name, points) VALUES (1, 'Test Sprint', '15');
INSERT INTO sprint (id, name, points) VALUES (2, 'Production Sprint', '11');

INSERT INTO task (id, employee, name, points, sprint_id, state_id) 
	VALUES (1, 'Sima', 'Kreiraj Login', '7', 1, 2);
INSERT INTO task (id, employee, name, points, sprint_id, state_id) 
	VALUES (2, 'Ivan', 'Autorizacija na back-u', '7', 1, 3);
INSERT INTO task (id, employee, name, points, sprint_id, state_id) 
	VALUES (3, 'Marko', 'Testiraj login', '1', 1, 1);
INSERT INTO task (id, employee, name, points, sprint_id, state_id) 
	VALUES (4, 'Mitar', 'Pokreni bazu', '7', 2, 3);
INSERT INTO task (id, employee, name, points, sprint_id, state_id) 
	VALUES (5, 'Jovan', 'Napisi Unit Testove', '4', 2, 1);