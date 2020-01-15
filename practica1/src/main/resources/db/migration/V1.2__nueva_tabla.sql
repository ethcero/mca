CREATE TABLE `cliente` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


INSERT INTO `cliente`(`nombre`) VALUES ('Pedro Glez.');
INSERT INTO `cliente`(`nombre`) VALUES ('Marta Fdez.');