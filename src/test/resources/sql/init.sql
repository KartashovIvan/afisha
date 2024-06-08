create schema if not exists application;

drop table if exists ticket;
drop table if exists event;
drop table if exists place;
drop table if exists event_type;

create table application.event_type (
  id serial primary key,
  name varchar(100) not null
);

insert into application.event_type (name)
values
  ('museum'),
  ('cinema'),
  ('theatre');

create table application.place (
  id serial primary key,
  name varchar(100) not null,
  address varchar(100) not null,
  city varchar(100) not null
);

create table application.event (
  id serial primary key,
  name varchar(100) not null,
  event_type_id int references application.event_type(id),
  event_date TIMESTAMP,
  place_id int references application.place(id)
);

create table application.ticket (
  id serial primary key,
  event_id int references event(id),
  client_email varchar(100),
  price numeric(9,2) not null,
  is_selled boolean default false
);

insert into application.place (name, address,city) values ('test_name_1','test_address_1','test_city_1');
insert into application.place (name, address,city) values ('test_name_2','test_address_2','test_city_2');
insert into application.place (name, address,city) values ('test_name_3','test_address_3','test_city_3');

insert into application.event (name, event_type_id, event_date, place_id) values ('test_name_1', 1, '2022-06-16 16:00:00', 1);
insert into application.event (name, event_type_id, event_date, place_id) values ('test_name_2', 2, '2022-06-16 16:00:00', 2);
insert into application.event (name, event_type_id, event_date, place_id) values ('test_name_3', 3, '2022-06-16 16:00:00', 3);

insert into application.ticket (event_id, client_email, price, is_selled) values (1,'test_email_1',100, true);
insert into application.ticket (event_id, client_email, price, is_selled) values (2,'test_email_2',200, true);
insert into application.ticket (event_id, client_email, price, is_selled) values (3,'test_email_3',300, true);
