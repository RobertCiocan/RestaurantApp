CREATE TABLE rezervare (
    uuid integer PRIMARY KEY,
    masa varchar(50) not null,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    data DATE NOT NULL,
    time TIME NOT NULL,
    time_end TIME NOT NULL,
    guests INT NOT NULL,
    special_requests VARCHAR(255)
);