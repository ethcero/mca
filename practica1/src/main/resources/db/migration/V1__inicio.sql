CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB;


CREATE TABLE `producto` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `datos` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


INSERT INTO `producto`(`datos`) VALUES ('{\"nombre\": \"Macbook\", \"marca\": \"Apple\", \"etiquetas\":[\"Portátil\",\"Mac\"]}');
INSERT INTO `producto`(`datos`) VALUES ('{\"nombre\": \"P30 Lite 4\", \"marca\": \"Huawei\", "\"etiquetas\":[\"Móvil\"]}');
INSERT INTO `producto`(`datos`) VALUES ('{\"nombre\": \"iPhone 11 Pro\", \"marca\": \"Apple\", \"etiquetas\":[\"Móvil\"]}');
INSERT INTO `producto`(`datos`) VALUES ('{\"nombre\": \"Teclado 105\", \"marca\": \"Logitech\"}');