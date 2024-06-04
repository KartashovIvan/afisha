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
  ('theather');

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
  client_email varchar(100) not null,
  price numeric(9,2) not null,
  is_selled boolean default false
);
