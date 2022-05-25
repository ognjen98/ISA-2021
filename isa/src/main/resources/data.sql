insert into addresses (street_name, number, city, state) values ('kaplara', '23', 'novi sad','srbija');

insert into users (name, surname, email, password, role, mobile, address_id, enabled)
values ('gio', 'civcic','ognjen@gmail.com', '$2a$12$JDACw4E6QeZTrdVhatJfOuNnhxoyKkQQHgvqRWCh5YXBmScvJbGuW', 0, '21310', 1, true);
insert into sys_admins(id) values (1);

insert into services (name, promo_desc, rules_of_conduct) values ('brod', 'dobar', 'budi dobar');
insert into ships(length, max_speed, id)
values (25.0, 50.0, 1);