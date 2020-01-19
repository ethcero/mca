

ALTER TABLE `product` ADD COLUMN `historic_price` JSON NULL;

UPDATE product SET historic_price = '[{\"date\": \"10/10/2018\", \"price\":15.50}, {\"date\": \"10/10/2018\", \"price\": 18.50}]' WHERE name = 'MacBook pro';
UPDATE product SET historic_price = '[{\"date\": \"10/10/2018\", \"price\":45.50}, {\"date\": \"10/10/2018\", \"price\": 67.50}]' WHERE name = 'Portatil HP';
UPDATE product SET historic_price = '[{\"date\": \"10/10/2018\", \"price\":23.50}, {\"date\": \"10/10/2018\", \"price\": 89.50}]' WHERE name = 'teclado';
UPDATE product SET historic_price = '[{\"date\": \"10/10/2018\", \"price\":80.50}, {\"date\": \"10/10/2018\", \"price\": 5.50}]' WHERE name = 'pantalla';
UPDATE product SET historic_price = '[{\"date\": \"10/10/2018\", \"price\":9.50}, {\"date\": \"10/10/2018\", \"price\": 12.50}]' WHERE name = 'raton';



ALTER TABLE `technician` ADD COLUMN `labels` JSON NULL;

UPDATE technician SET labels = '[\"Moviles\",\"Domotica\"]' WHERE first_name = 'Jaime';
UPDATE technician SET labels = '[\"Moviles\",\"Wearables\"]' WHERE first_name = 'Jorge';
UPDATE technician SET labels = '[\"Moviles\",\"Domotica\"]' WHERE first_name = 'Pedro';
UPDATE technician SET labels = '[\"Wearables\",\"Domotica\"]' WHERE first_name = 'Miguel';

