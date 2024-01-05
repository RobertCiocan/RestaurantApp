CREATE TABLE user (
     id integer primary key,
     username varchar(50) unique,
     password varchar(50),
     role varchar(70)
);