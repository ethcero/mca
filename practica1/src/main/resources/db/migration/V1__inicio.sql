
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO hibernate_sequence (next_val) VALUES (1);


CREATE TABLE `product` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `brand` varchar(255) NOT NULL,
    `price` double(10,2) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


CREATE TABLE `client` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `city` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `technician` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `level` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


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