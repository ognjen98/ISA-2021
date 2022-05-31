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

insert into users (name, surname, email, password, role, mobile, address_id, enabled)
values ('Ognjen', 'Civcic','ognjen@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW', 0,
'21310', 1, true);
insert into users (name, surname, email, password, role, mobile, address_id, enabled)
values ('Stojan', 'Petrovic','ognjen@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW', 2,
'21310', 2, true);
insert into users (name, surname, email, password, role, mobile, address_id, enabled)
values ('Milan', 'Peric','ognjen@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW', 3,
'21310', 3, true);
insert into users (name, surname, email, password, role, mobile, address_id, enabled)
values ('Jasmin', 'Jovanovic','ognjen@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW', 4,
'21310', 4, false);

insert into sellers (points,id) values (0, 2);
insert into sellers (points,id) values (0, 3);
insert into sellers (points,id) values (0, 4);

insert into sys_admins(id) values (1);
insert into cottage_owners(id) values (2);
insert into ship_owners(id) values (3);
insert into instructors(id) values (4);

insert into periods (start_time, end_time) values (to_timestamp(1663221600), to_timestamp(1665813600));
insert into periods (start_time, end_time) values (to_timestamp(1665813600), to_timestamp(1671087600));
insert into periods (start_time, end_time) values (to_timestamp(1666159200), to_timestamp(1671433200));
insert into periods (start_time, end_time) values (to_timestamp(1671433200), to_timestamp(1672470000));
insert into periods (start_time, end_time) values (to_timestamp(1663653600), to_timestamp(1668927600));
insert into periods (start_time, end_time) values (to_timestamp(1666332000), to_timestamp(1669014000));
insert into periods (start_time, end_time) values (to_timestamp(1669359600), to_timestamp(1674630000));
insert into periods (start_time, end_time) values (to_timestamp(1668236400), to_timestamp(1670828400));
insert into periods (start_time, end_time) values (to_timestamp(1665727200), to_timestamp(1671001200));

insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Soko', 'Veoma dobar brod sa velikim izborom dodatnih usluga', 'Budite dobri', 5, 3);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Jedrilicar', 'Veliko jedro na sredini dostize brzinu do 50 km/h', 'Budite dobri', 6, 3);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Spartanac', 'Brod srednje duzine dostize brzinu i do 250 km/h', 'Budite dobri', 7, 3);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Lovacki dom', 'Prostrana vikendica za sve goste', 'Budite dobri', 8, 2);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Lazino sokace', 'Uzivajte u najboljim specijalitetima sa ovih prostora', 'Budite dobri', 9, 2);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Znalac', 'Oprobajte se u brojnim igrama znanja sa ostalim gostima', 'Budite dobri', 10, 2);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Jasminovo pecanje varalicom', 'Pecanje varalicom sa prvakom Srbije u pecanju', 'Budite dobri', 11, 4);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Jasminovo pecanje udicom', 'Pecanje udicom sa prvakom Srbije u pecanju', 'Budite dobri', 12, 4);
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id)
values ('Jasminovo pecanje rukama', 'Pecanje rukama sa prvakom Srbije u pecanju', 'Budite dobri', 13, 4);

insert into services_periods (service_id, period_id) values (1,1);
insert into services_periods (service_id, period_id) values (2,2);
insert into services_periods (service_id, period_id) values (3,3);
insert into services_periods (service_id, period_id) values (4,4);
insert into services_periods (service_id, period_id) values (5,5);
insert into services_periods (service_id, period_id) values (6,6);
insert into services_periods (service_id, period_id) values (7,7);
insert into services_periods (service_id, period_id) values (8,8);
insert into services_periods (service_id, period_id) values (9,9);

insert into ships(length, max_speed, type, capacity, rct, id)
values (25.0, 50.0, 'katamaran', 20, 0, 1);
insert into ships(length, max_speed, type, capacity, rct, id)
values (20.0, 50.0, 'jedrilica', 10, 0, 2);
insert into ships(length, max_speed, type, capacity, rct, id)
values (10.0, 250.0, 'gliser', 3, 0, 3);

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

