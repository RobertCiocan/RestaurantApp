CREATE TABLE ospatar (
     cnp char(13) primary key,
     id integer,
     nume varchar(50),
     prenume varchar(50),
     email varchar(70) unique,
     is_active boolean,
     external_uid varchar(36),
     create_date datetime,
     update_date datetime,
     create_uid varchar(36),
     update_uid varchar(36)
);