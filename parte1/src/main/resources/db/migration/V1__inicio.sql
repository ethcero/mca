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
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `historia_precios` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `promocion` double DEFAULT NULL,
  `proveedor` varchar(255) DEFAULT NULL,
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

CREATE TABLE `cliente_producto` (
  `fecha_compra` datetime DEFAULT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cliente_id`,`producto_id`),
  KEY  (`producto_id`),
  FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB;


-- Inserción de datos:
INSERT INTO cliente(id,nombre,apellidos,email,ciudad) VALUES
    (1,'Pedro','García','pedro.garcia@email.com','Madrid'),
    (2,'Ana','Pérez','ana.perez@email.com','Barcelona'),
    (3,'Marina','Díaz','marina.diaz@email.com','Zaragoza'),
    (4,'Carlos','Fernández','cfdez@email.com','Sevilla');

-- servicios
INSERT INTO producto(id,dtype,nombre,precio,proveedor,fecha_inicio,fecha_fin,promocion) VALUES
    (11,'servicio','Teclado',12.5,'ACME','2019-12-20 10:15','2019-12-20 10:15',1.0),
    (12,'servicio','Monitor 29"',120,'Mntrs','2019-10-20 10:15','2019-11-20 10:15',2.0);


-- retail
INSERT INTO producto(id,dtype,nombre,precio,marca,pais) VALUES
    (13,'retail','Portátil táctil',999.99,'HT','España'),
    (14,'retail','Home Pod',80.3,'Gugel','Francia'),
    (15,'retail','Raton',8.99,'Mickey','Alemania');

INSERT INTO tecnico(id,nombre,nivel) VALUES
    (21,'Técnico Junior',1),
    (22,'Técnico Senior 1',3),
    (23,'Técnico Senior 2',7),
    (24,'Técnico Senior 3',9);

INSERT INTO chat(id, autor, fecha, texto, cliente_id, producto_id, tecnico_id) VALUES
(31,'C','2019-12-20 10:15','Hola, necesito ayuda con el teclado.',1,11,21),
(32,'T','2019-12-20 10:16','Hola, Que le ocurre? Está conectado?.',1,11,21),
(33,'C','2019-12-20 10:17','Gracias. Problema resuelto.',1,11,21),
(34,'C','2019-12-22 20:03','El monitor no se enciende.',2,12,23),
(35,'T','2019-12-22 20:05','Hola. Revise todos los cables y contacte si sigue fallando.',2,12,23),
(36,'C','2019-12-22 20:04','El portatil no arranca.',3,13,24),
(37,'T','2019-12-22 20:05','Hola. Por favor, retire el bloqueo del disco e intente de nuevo.',3,13,24),
(38,'C','2019-12-22 20:15','Gracias, ya funciona.',3,13,24),
(39,'C','2019-12-26 09:38','El Home Pod no se escucha.',1,14,24),
(40,'T','2019-12-26 09:40','Hola. Revise la rueda de volumen.',1,14,24),
(41,'C','2019-12-26 09:45','El volumen está al máximo y no funciona.',1,14,24),
(42,'T','2019-12-26 09:46','Por favor, revise que el software esté actualizado.',1,14,24),
(43,'C','2019-12-26 09:50','Está actualizado y no funciona. Lo quiero devolver.',1,14,24),
(44,'T','2019-12-26 09:52','De acuerdo. Procedemos a cursar la devolucion.',1,14,24);


INSERT INTO cliente_producto(cliente_id,producto_id,fecha_compra) VALUES
(1,13,'2019-01-20 11:15'),
(1,14,'2019-02-20 12:15'),
(1,15,'2019-03-20 13:15'),
(2,11,'2019-11-20 10:15'),
(3,14,'2019-11-20 10:15'),
(4,12,'2019-11-20 10:15'),
(1,11,'2019-11-20 10:15'),
(4,11,'2019-11-20 10:15');

-- Actualiza secuencia Hibernate para evitar conflictos:
INSERT INTO hibernate_sequence VALUES (60);