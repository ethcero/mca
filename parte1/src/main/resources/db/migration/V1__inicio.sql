-- Creación de tablas:

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB;


CREATE TABLE `cliente` (
   `id` bigint NOT NULL,
   `apellidos` varchar(255) DEFAULT NULL,
   `ciudad` varchar(255) DEFAULT NULL,
   `email` varchar(255) DEFAULT NULL,
   `nombre` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `producto` (
   `id` bigint NOT NULL,
   `marca` varchar(255) DEFAULT NULL,
   `nombre` varchar(255) DEFAULT NULL,
   `precio` double NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tecnico` (
   `id` bigint NOT NULL,
   `nivel` int NOT NULL,
   `nombre` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `chat` (
   `id` bigint NOT NULL,
   `autor` char(1) NOT NULL,
   `fecha` datetime(6) DEFAULT NULL,
   `texto` varchar(255) DEFAULT NULL,
   `cliente_id` bigint DEFAULT NULL,
   `producto_id` bigint DEFAULT NULL,
   `tecnico_id` bigint DEFAULT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`tecnico_id`) REFERENCES `tecnico` (`id`),
   FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
   FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB;


-- Inserción de datos:
INSERT INTO cliente(id,nombre,apellidos,email,ciudad) VALUES
    (1,'Pedro','García','pedro.garcia@email.com','Madrid'),
    (2,'Ana','Pérez','ana.perez@email.com','Barcelona'),
    (3,'Marina','Díaz','marina.diaz@email.com','Zaragoza'),
    (4,'Carlos','Fernández','cfdez@email.com','Sevilla');

INSERT INTO producto(id,nombre,marca,precio) VALUES
    (11,'Teclado','ACME',12.5),
    (12,'Monitor 29"','Mntrs',120),
    (13,'Portátil táctil','HT',999.99),
    (14,'Home Pod','Gugel',80.3),
    (15,'Raton','Mickey',8.99);

INSERT INTO tecnico(id,nombre,nivel) VALUES
    (21,'Técnico Junior',1),
    (22,'Técnico Senior 1',3),
    (23,'Técnico Senior 2',7),
    (24,'Técnico Senior 3',9);

INSERT INTO chat(id, autor, fecha, texto, cliente_id, producto_id, tecnico_id) VALUES
(31,'C','2019-12-20 10:15','Hola, necesito ayuda con el teclado.',1,11,21),
(32,'T','2019-12-20 10:16','Hola, ¿Qué le ocurre? ¿Está conectado?.',1,11,21),
(33,'C','2019-12-20 10:17','Gracias. Problema resuelto.',1,11,21),
(34,'C','2019-12-22 20:03','El monitor no se enciende.',2,12,23),
(35,'T','2019-12-22 20:05','Hola. Revise todos los cables y contacte si sigue fallando.',2,12,23),
(36,'C','2019-12-22 20:04','El portátil no arranca.',3,13,24),
(37,'T','2019-12-22 20:05','Hola. Por favor, retire el bloqueo del disco e intente de nuevo.',3,13,24),
(38,'C','2019-12-22 20:15','Gracias, ya funciona.',3,13,24),
(39,'C','2019-12-26 09:38','El Home Pod no se escucha.',1,14,24),
(40,'T','2019-12-26 09:40','Hola. Revise la rueda de volumen.',1,14,24),
(41,'C','2019-12-26 09:45','El volumen está al máximo y no funciona.',1,14,24),
(42,'T','2019-12-26 09:46','Por favor, revise que el software esté actualizado.',1,14,24),
(43,'C','2019-12-26 09:50','Está actualizado y no funciona. Lo quiero devolver.',1,14,24),
(44,'T','2019-12-26 09:52','De acuerdo. Procedemos a cursar la devolución.',1,14,24);

-- Actualiza secuencia Hibernate para evitar conflictos:
INSERT INTO hibernate_sequence VALUES (50);