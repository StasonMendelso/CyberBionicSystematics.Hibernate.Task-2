DROP DATABASE IF EXISTS carshop;

CREATE DATABASE carshop;

USE carshop;


CREATE TABLE makes
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	make VARCHAR(20) UNIQUE
);

CREATE TABLE cars
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	make_id INT NOT NULL,
	model VARCHAR(20) NOT NULL,
	price INT NOT NULL,
	FOREIGN KEY(make_id) REFERENCES makes(id)
);

CREATE TABLE clients
(
	id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(30),
	age TINYINT,
	phone VARCHAR(20),
	PRIMARY KEY (id)
);

CREATE TABLE orders
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	car_id INT NOT NULL,
	client_id INT NOT NULL,
	FOREIGN KEY(car_id) REFERENCES cars(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(client_id) REFERENCES clients(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO makes(make) VALUES('Audi');
INSERT INTO makes(make) VALUES('Porsche');

INSERT INTO cars(make_id, model, price) VALUES (1, 'A5', 50000);
INSERT INTO cars(make_id, model, price) VALUES (2, 'Panamera', 100000);
INSERT INTO cars(make_id, model, price) VALUES (2, 'Cayman', 88000);

INSERT INTO clients(name, age, phone)
VALUES
('Max', 20, '+380(11)111-11-11'),
('John', 25, '+380(22)222-22-22'),
('Nick', 75, '+380(33)333-33-33');

INSERT INTO orders(car_id, client_id) VALUES(1, 1), (2, 2), (1, 3);

-- ************************************************

select * from makes;
select * from cars;
select * from clients;
select * from orders;
