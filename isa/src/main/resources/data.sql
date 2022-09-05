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

insert into users (name, surname, email, password, mobile, address_id, enabled, deleted, approved)
values ('Ognjen', 'Civcic','ognjen@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW', '21310',
1, true,false,1);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted, approved)
values ('Stojan', 'Petrovic','ognjen2@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 2, true,false,1);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted,approved)
values ('Milan', 'Peric','hecas82996@xitudy.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 3, true,false,1);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted,approved)
values ('Jasmin', 'Jovanovic','ognjen4@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 4, true,false,1);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted,approved)
values ('Juzba', 'Juzbasic','ognjencivcic23@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 7, true,false,1);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted,approved)
values ('Juzba', 'Juzbasic','ognjen5@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 7, true,false,1);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted,approved)
values ('Rastko', 'Nemanjic','rnemanjic@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 2, true,false,2);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted,approved)
values ('Jovan', 'Jokic','jjokic@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 3, true,false,2);
insert into users (name, surname, email, password, mobile, address_id, enabled, deleted,approved)
values ('Marko', 'Jaric','mjaric@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW',
'21310', 4, true,false,2);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 5);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (8, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (9, 5);

insert into sellers (points,id,grade) values (0, 2, 0.0);
insert into sellers (points,id,grade) values (0, 3, 0.0);
insert into sellers (points,id,grade) values (0, 4, 0.0);
insert into sellers (points,id,grade) values (0, 7, 0.0);
insert into sellers (points,id,grade) values (0, 8, 0.0);
insert into sellers (points,id,grade) values (0, 9, 0.0);

insert into sys_admins(id) values (1);
insert into cottage_owners(id) values (2);
insert into ship_owners(id) values (3);
insert into instructors(id) values (4);
insert into clients(id, penalties, points) values (5, 0, 0);
insert into clients(id, penalties, points) values (6, 0, 0);
insert into cottage_owners(id) values (7);
insert into ship_owners(id) values (8);
insert into instructors(id) values (9);

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

insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Soko', 'Veoma dobar brod sa velikim izborom dodatnih usluga', 'Budite dobri', 5, 3, 0.0, 70, 5,false,0, 'http://www.ico.rs/wp-content/uploads/2011/06/KATAMARAN.jpg');
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Jedrilicar', 'Veliko jedro na sredini dostize brzinu do 50 km/h', 'Budite dobri', 6, 3, 0.0,50, 3,false,0, 'https://www.luxlife.rs/storage/posts/gallery/2011/Sep/183533/red-dragon-luksuzna-jedrilica.jpg');
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Spartanac', 'Brod srednje duzine dostize brzinu i do 250 km/h', 'Budite dobri', 7, 3, 0.0,30, 2,false,0,'https://media.mojtrg.me/Image/cbb95823-4bed-4ab9-8e1c-97ac8bb3f2ef/20190430/false/false/1280/960/prodajem-gliser.jpeg');
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Lovacki dom', 'Prostrana vikendica za sve goste', 'Budite dobri', 8, 2, 0.0,170, 3,false,0,'https://www.apartmanivikendice.com/images/smestaj/galerija-vikendica-pustolov-uvac-20.jpg');
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Lazino sokace', 'Uzivajte u najboljim specijalitetima sa ovih prostora', 'Budite dobri', 9, 2, 0.0,120, 5,
false,0,'https://www.gradnja.rs/wp-content/uploads/2022/02/luksuzne-vikendice-srbija-izajmljivanje-gradnja.rs_.jpg');
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Znalac', 'Oprobajte se u brojnim igrama znanja sa ostalim gostima', 'Budite dobri', 10, 2, 0.0,80, 10,false,0,'https://cf.bstatic.com/xdata/images/hotel/max1024x768/187868702.jpg?k=4368f5637f7ff4c79e5c7f993e37c48850d3e9d7d514486c15e971a4aa45ed38&o=&hp=1')
;
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Jasminovo pecanje varalicom', 'Pecanje varalicom sa prvakom Srbije u pecanju', 'Budite dobri', 11, 4, 0.0,50,1,
false,0, 'https://cdn.wallpapersafari.com/42/67/mCjYQN.jpg')
;
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Jasminovo pecanje udicom', 'Pecanje udicom sa prvakom Srbije u pecanju', 'Budite dobri', 12, 4, 0.0,200, 2,
false,0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXfNp8kiNeiDX9RdbDDZZvdMW5SP8ooz209g&usqp=CAU');
insert into services (name, promo_desc, rules_of_conduct,address_id, seller_id, grade, price, no_guests, deleted,
version, image)
values ('Jasminovo pecanje rukama', 'Pecanje rukama sa prvakom Srbije u pecanju', 'Budite dobri', 13, 4, 0.0,300,3,
false,0,'https://t3.ftcdn.net/jpg/03/34/10/06/360_F_334100631_68aaU8bT2ZfiS0g9nwiFd8osIW3nebFb.jpg');

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

--insert into reservations (start_time, end_time, cancelled, client_id, service_id, version, address_id)
--values ('Reservation', to_timestamp(1663480800), to_timestamp(1663567200), true, 5, 1,0, 5);


insert into reservations (start_time, end_time, cancelled, client_id, service_id, reserved, version, address_id,
price, disc_price, max_capacity, deleted)
values (to_timestamp(1663308000), to_timestamp(1663394400), false, null, 1, false,0, 5, 50, 25,
3, false);
insert into reservations (start_time, end_time, cancelled, client_id, service_id, reserved, version, address_id,
price, disc_price, max_capacity,deleted)
values (to_timestamp(1664949600), to_timestamp(1665036000), false, null, 1, false,0, 5, 20, 10,
5, false);
insert into reservations (start_time, end_time, cancelled, client_id, service_id, reserved, version, address_id,
price, disc_price, max_capacity, deleted)
values (to_timestamp(1660543200), to_timestamp(1660629600), false, 5, 2, true,0, 5, 50, null,
3, false);
insert into reservations (start_time, end_time, cancelled, client_id, service_id, reserved, version, address_id,
price, disc_price, max_capacity, deleted)
values (to_timestamp(1657951200), to_timestamp(1658124000), false, 5, 5, true,0, 5, 50, null,
3, false);


insert into services_discount_reservations(service_id, discount_reservations_id) values (1, 1);
insert into services_discount_reservations(service_id, discount_reservations_id) values (1, 2);

--insert into reservations_additional_infos(reservation_id, additional_info_id) values (1, 1);


insert into clients_cancelled_reservations (client_id, reservation_id) values (5, 1);

insert into clients_all_reservations (client_id, reservation_id) values (5, 1);

insert into earning_percentage(percentage) values (25.0);

insert into categories(name, type, points, discount) values ('GOLD', 'CLIENT', 2000, 50);
insert into categories(name, type, points, discount) values ('SILVER', 'CLIENT', 1500, 30);
insert into categories(name, type, points, discount) values ('BRONZE', 'CLIENT', 1000, 20);
insert into categories(name, type, points, discount) values ('GOLD', 'SELLER', 2000, 100);
insert into categories(name, type, points, discount) values ('SILVER', 'SELLER', 1500, 90);
insert into categories(name, type, points, discount) values ('BRONZE', 'SELLER', 1000, 80);


insert into points (client_points, seller_points) values(20, 25);

insert into complaints(text, status, client_id, version) values ('adadoa', 2, 5, 0);

insert into service_complaints(id, service_id) values (1, 1);

insert into earnings (date_time, money) values (to_timestamp(1642489200), 300.0);
insert into earnings (date_time, money) values (to_timestamp(1645167600), 250.0);
insert into earnings (date_time, money) values (to_timestamp(1645513200), 400.0);
insert into earnings (date_time, money) values (to_timestamp(1647932400), 124.0);
insert into earnings (date_time, money) values (to_timestamp(1647932400), 270.0);
insert into earnings (date_time, money) values (to_timestamp(1647327600), 900.0);
insert into earnings (date_time, money) values (to_timestamp(1646204400), 1500.0);
insert into earnings (date_time, money) values (to_timestamp(1646204400), 500.0);
insert into earnings (date_time, money) values (to_timestamp(1652421600), 231.0);
insert into earnings (date_time, money) values (to_timestamp(1653026400), 921.0);
insert into earnings (date_time, money) values (to_timestamp(1653372000), 293.0);
insert into earnings (date_time, money) values (to_timestamp(1656050400), 591.0);
insert into earnings (date_time, money) values (to_timestamp(1658642400), 3904.0);


--insert into revisions(grade, text, status, client_id) values (3, 'Cao', 0, null);
--insert into service_revisions(id, service_id) values (1, 2);
