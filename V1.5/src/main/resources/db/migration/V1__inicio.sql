
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO `hibernate_sequence` VALUES (34);


CREATE TABLE `product` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `brand` varchar(255) NOT NULL,
    `price` double(10,2) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `product` VALUES
(1,'MacBook pro','Apple',1000.00),
(2,'Portatil HP','HP',500.00),
(3,'teclado','Logitech',100.00),
(4,'pantalla','HP',100.00),
(5,'raton','Logitech',10.00);



CREATE TABLE `client` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `city` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `client` VALUES
(6,'Juanito','Detal','juanito@f.com','Madrid'),
(7,'Fulanito','Detal','fulanito@f.com','Murcia'),
(8,'Jaimito','Borromeo','jaimito@f.com','Lorca'),
(9,'Pepito','Pascual','pepito@f.com','Alcorcon');


CREATE TABLE `technician` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `level` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `technician` VALUES
(10,'Jaime',1),
(11,'Jorge',3),
(12,'Pedro',10),
(13,'Miguel',2);


CREATE TABLE `chat` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `datetime` timestamp NOT NULL,
    `author` char NOT NULL,
    `client` bigint(20) NOT NULL,
    `technician` bigint(20) NOT NULL,
    `product` bigint(20) NOT NULL,
    `message` varchar(255) NOT NULL,
    FOREIGN KEY (client) REFERENCES client(id),
    FOREIGN KEY (technician) REFERENCES technician(id),
    FOREIGN KEY (product) REFERENCES product(id),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `chat` VALUES
(14,'2020-01-19 10:33:16','C',7,11,3,'Message body text Nº:0'),
(15,'2020-01-19 10:33:16','C',8,11,5,'Message body text Nº:1'),
(16,'2020-01-19 10:33:16','T',7,13,3,'Message body text Nº:2'),
(17,'2020-01-19 10:33:16','C',9,13,2,'Message body text Nº:3'),
(18,'2020-01-19 10:33:16','T',8,11,5,'Message body text Nº:4'),
(19,'2020-01-19 10:33:16','T',7,13,5,'Message body text Nº:5'),
(20,'2020-01-19 10:33:16','T',7,13,2,'Message body text Nº:6'),
(21,'2020-01-19 10:33:16','C',9,11,3,'Message body text Nº:7'),
(22,'2020-01-19 10:33:16','T',9,12,5,'Message body text Nº:8'),
(23,'2020-01-19 10:33:16','T',9,13,4,'Message body text Nº:9'),
(24,'2020-01-19 10:33:16','C',9,11,3,'Message body text Nº:10'),
(25,'2020-01-19 10:33:16','T',7,12,2,'Message body text Nº:11'),
(26,'2020-01-19 10:33:16','T',8,12,4,'Message body text Nº:12'),
(27,'2020-01-19 10:33:16','C',8,13,3,'Message body text Nº:13'),
(28,'2020-01-19 10:33:16','C',9,13,3,'Message body text Nº:14'),
(29,'2020-01-19 10:33:16','T',7,12,5,'Message body text Nº:15'),
(30,'2020-01-19 10:33:16','T',8,11,3,'Message body text Nº:16'),
(31,'2020-01-19 10:33:16','T',8,13,5,'Message body text Nº:17'),
(32,'2020-01-19 10:33:16','T',9,12,5,'Message body text Nº:18'),
(33,'2020-01-19 10:33:17','T',7,12,2,'Message body text Nº:19');
