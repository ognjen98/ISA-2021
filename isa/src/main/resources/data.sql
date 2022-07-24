insert into addresses (street_name, number, city, state) values ('Kuzminska', '23', 'Beograd','Srbija');
insert into addresses (street_name, number, city, state) values ('1300 kaplara', '22/12', 'Novi Sad','Srbija');
insert into addresses (street_name, number, city, state) values ('Bulevar oslobodjenja', '90', 'Novi Sad','Srbija');
insert into addresses (street_name, number, city, state) values ('Stari most', '5', 'Sremska Mitrovica','Srbija');
insert into addresses (street_name, number, city, state) values ('Parobrodska', '3', 'Podgorica','Crna Gora');
insert into addresses (street_name, number, city, state) values ('Njegoseva', '18', 'Tivat','Crna Gora');
insert into addresses (street_name, number, city, state) values ('Plava obala', '35', 'Venecija','Italija');
insert into addresses (street_name, number, city, state) values ('Maricka', '22', 'Lezimir','Srbija');
insert into addresses (street_name, number, city, state) values ('Kosovskog boja', '13', 'Arandjelovac','Srbija');
insert into addresses (street_name, number, city, state) values ('Gospodara Vucica', '99', 'Obrenovac','Srbija');
insert into addresses (street_name, number, city, state) values ('Maksima Gorkog', '20', 'Novi Sad','Srbija');
insert into addresses (street_name, number, city, state) values ('Strazilovska', '2', 'Novi Sad','Srbija');
insert into addresses (street_name, number, city, state) values ('Fruskogorska', '1', 'Novi Sad','Srbija');

INSERT INTO roles (name) VALUES ('SYSTEM_ADMIN');
INSERT INTO roles (name) VALUES ('CLIENT');
INSERT INTO roles (name) VALUES ('COTTAGE_OWNER');
INSERT INTO roles (name) VALUES ('SHIP_OWNER');
INSERT INTO roles (name) VALUES ('INSTRUCTOR');

insert into users (name, surname, email, password, mobile, address_id, enabled)
values ('Ognjen', 'Civcic','ognjen@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW', '21310', 1, true);
insert into users (name, surname, email, password, mobile, address_id, enabled)
values ('Stojan', 'Petrovic','ognjen2@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 2, true);
insert into users (name, surname, email, password, mobile, address_id, enabled)
values ('Milan', 'Peric','ognjen3@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 3, true);
insert into users (name, surname, email, password, mobile, address_id, enabled)
values ('Jasmin', 'Jovanovic','ognjen4@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 4, true);
insert into users (name, surname, email, password, mobile, address_id, enabled)
values ('Juzba', 'Juzbasic','ognjencivcic23@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 7, true);
insert into users (name, surname, email, password, mobile, address_id, enabled)
values ('Juzba', 'Juzbasic','ognjen5@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 7, true);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 5);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 2);


insert into sellers (points,id,grade) values (0, 2, 0.0);
insert into sellers (points,id,grade) values (0, 3, 0.0);
insert into sellers (points,id,grade) values (0, 4, 0.0);

insert into sys_admins(id) values (1);
insert into cottage_owners(id) values (2);
insert into ship_owners(id) values (3);
insert into instructors(id) values (4);
insert into clients(id) values (5);
insert into clients(id) values (6);

insert into periods (start_time, end_time) values (to_timestamp(1663221600), to_timestamp(1663653600));
insert into periods (start_time, end_time) values (to_timestamp(1664085600), to_timestamp(1665813600));
insert into periods (start_time, end_time) values (to_timestamp(1665813600), to_timestamp(1671087600));
insert into periods (start_time, end_time) values (to_timestamp(1666159200), to_timestamp(1671433200));
insert into periods (start_time, end_time) values (to_timestamp(1671433200), to_timestamp(1672470000));
insert into periods (start_time, end_time) values (to_timestamp(1663653600), to_timestamp(1668927600));
insert into periods (start_time, end_time) values (to_timestamp(1666332000), to_timestamp(1669014000));
insert into periods (start_time, end_time) values (to_timestamp(1669359600), to_timestamp(1674630000));
insert into periods (start_time, end_time) values (to_timestamp(1668236400), to_timestamp(1670828400));
insert into periods (start_time, end_time) values (to_timestamp(1665727200), to_timestamp(1671001200));

insert into additional_infos (info,price) values ('Pecaljka', 20);
insert into additional_infos (info,price) values ('Sesir', 5);
insert into additional_infos (info,price) values ('Lezaljka', 50);
insert into additional_infos (info,price) values ('Sprej protiv komaraca', 10);
insert into additional_infos (info,price) values ('Mamac', 5);
insert into additional_infos (info,price) values ('Internet', 30);
insert into additional_infos (info,price) values ('Parking', 8);

insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Soko', 'Veoma dobar brod sa velikim izborom dodatnih usluga', 'Budite dobri', 5, 3, 0.0, 70, 5);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Jedrilicar', 'Veliko jedro na sredini dostize brzinu do 50 km/h', 'Budite dobri', 6, 3, 0.0,50, 3);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Spartanac', 'Brod srednje duzine dostize brzinu i do 250 km/h', 'Budite dobri', 7, 3, 0.0,30, 2);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Lovacki dom', 'Prostrana vikendica za sve goste', 'Budite dobri', 8, 2, 0.0,170, 3);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Lazino sokace', 'Uzivajte u najboljim specijalitetima sa ovih prostora', 'Budite dobri', 9, 2, 0.0,120, 5);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Znalac', 'Oprobajte se u brojnim igrama znanja sa ostalim gostima', 'Budite dobri', 10, 2, 0.0,80, 10);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Jasminovo pecanje varalicom', 'Pecanje varalicom sa prvakom Srbije u pecanju', 'Budite dobri', 11, 4, 0.0,50,1)
;
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Jasminovo pecanje udicom', 'Pecanje udicom sa prvakom Srbije u pecanju', 'Budite dobri', 12, 4, 0.0,200, 2);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests)
values ('Jasminovo pecanje rukama', 'Pecanje rukama sa prvakom Srbije u pecanju', 'Budite dobri', 13, 4, 0.0,300,3);

insert into services_periods (service_id, period_id) values (1,1);
insert into services_periods (service_id, period_id) values (1,2);
insert into services_periods (service_id, period_id) values (2,3);
insert into services_periods (service_id, period_id) values (3,4);
insert into services_periods (service_id, period_id) values (4,5);
insert into services_periods (service_id, period_id) values (5,6);
insert into services_periods (service_id, period_id) values (6,7);
insert into services_periods (service_id, period_id) values (7,8);
insert into services_periods (service_id, period_id) values (8,9);
insert into services_periods (service_id, period_id) values (9,10);

insert into services_additional_infos(service_id, additional_info_id) values(1,1);
insert into services_additional_infos(service_id, additional_info_id) values(1,3);
insert into services_additional_infos(service_id, additional_info_id) values(2,6);
insert into services_additional_infos(service_id, additional_info_id) values(4,7);
insert into services_additional_infos(service_id, additional_info_id) values(4,6);
insert into services_additional_infos(service_id, additional_info_id) values(4,4);
insert into services_additional_infos(service_id, additional_info_id) values(7,2);

insert into ships(length, max_speed, type, capacity, rct, id)
values (25.0, 50.0, 'katamaran', 20, 1, 1);
insert into ships(length, max_speed, type, capacity, rct, id)
values (20.0, 50.0, 'jedrilica', 10, 0, 2);
insert into ships(length, max_speed, type, capacity, rct, id)
values (10.0, 250.0, 'gliser', 3, 1, 3);

insert into cottages (no_beds_by_room, no_rooms, id)
values (2,1,4);
insert into cottages (no_beds_by_room, no_rooms, id)
values (3,2,5);
insert into cottages (no_beds_by_room, no_rooms, id)
values (2,2,6);

insert into lessons (instructor_bio, max_persons, terms, id)
values ('Dobar covek, prvak Srbije u pecanju u svim kategorijama', 2, 0, 7);
insert into lessons (instructor_bio, max_persons, terms, id)
values ('Dobar covek, prvak Srbije u pecanju u svim kategorijama', 2, 0, 8);
insert into lessons (instructor_bio, max_persons, terms, id)
values ('Dobar covek, prvak Srbije u pecanju u svim kategorijama', 2, 0, 9);

insert into reservations (dtype, start_time, end_time, cancelled, client_id, service_id)
values ('Reservation', to_timestamp(1663480800), to_timestamp(1663567200), true, 5, 1);
insert into reservations (dtype, start_time, end_time, cancelled, client_id, service_id, reserved)
values ('DiscountReservation', to_timestamp(1663480800), to_timestamp(1663567200), false, 5, 1, false);

insert into clients_cancelled_reservations (client_id, cancelled_reservations_id) values (5, 1);

