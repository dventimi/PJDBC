-- Turn on SQLite foreign key support, which is not on by default.
pragma foreign_keys = on;

-- Drop our tables in safe order.
-- NOTE:  In the future, we could deduce a safe order by building a graph from the system catalogs.
drop table if exists phone;
drop table if exists person;

-- Create a referenced table.
create table if not exists person (
     id integer constraint pk primary key asc on conflict rollback autoincrement,
     first_name varchar(30) not null,
     last_name varchar(30) not null,
     age integer
);

-- Create some sample data in the referenced table.
insert into person (first_name, last_name, age) values ('David', 'Ventimiglia', 39);
insert into person (first_name, last_name, age) values ('Catherine', 'Kennedy', 26);
insert into person (first_name, last_name, age) values ('Julie', 'Hata', 39);
insert into person (first_name, last_name, age) values ('Olivia', 'Ventimiglia', 1);
insert into person (first_name, last_name, age) values ('Liam', 'Goodfellow', 3);
insert into person (first_name, last_name, age) values ('Jack', 'Goodfellow', 1);

-- Create a referencing table.
create table if not exists phone (
       id integer constraint pk primary key asc on conflict rollback autoincrement,
       person_id integer references person (id) on delete restrict on update cascade,
       phone_number varchar(30) not null
);

-- Create some sample data in the referencing table.
insert into phone (person_id, phone_number) select id, '517-575-7086' from person where first_name = 'David' and last_name = 'Ventimiglia';
insert into phone (person_id, phone_number) select id, '415-244-6112' from person where first_name = 'Julie' and last_name = 'Hata';

-- Create a view, UPON WHICH THE DOMAIN KEY WILL BE BASED
drop view if exists adult;
create view if not exists adult as select id from person where age > 18;

-- Here's where the actual domain key stuff starts
-- It will look something like this
-- create domain key <name> on <tablename>.<columnname> references <foreign table or view name>.<columnname> with <error string>
   -- 0	  1	 2   3	    4  5			6	   7	    	     	  		     8	  9

-- Create the complementary domain key view.
drop view if exists broken_key;
create view if not exists broken_key as select * from phone p left outer join adult a on p.person_id = a.id where a.id is null;

-- Create the domain key trigger on the referencing table.
drop trigger if exists phone_insert;
create trigger if not exists phone_insert after insert on phone when exists (select * from broken_key)
begin
  select raise(rollback, 'Only persons older than 18 can have a telephone.');
end;

-- Create the complementary domain key trigger on the referenced table.
drop trigger if exists person_update;
create trigger if not exists person_update after update on person when exists (select * from broken_key)
begin
  select raise(rollback, 'Only persons older than 18 can have a telephone.');
end;

drop table person2;
create table if not exists person2 (
     id integer constraint pk primary key asc on conflict rollback autoincrement,
     first_name varchar(30) not null,
     last_name varchar(30) not null,
     age integer constraint age_check check (age > 18)
);

-- select * from sqlite_master;
