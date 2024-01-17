CREATE TABLE rezervare (
    table VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    data DATE NOT NULL,
    time TIME NOT NULL,
    guests INT NOT NULL,
    special_requests VARCHAR(255)
);