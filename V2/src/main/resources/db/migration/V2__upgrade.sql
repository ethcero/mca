
CREATE TABLE `chat_tmp` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `data` json NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


INSERT INTO chat_tmp(data)
select JSON_OBJECT('client', client, 'technician', technician,'product',product,'chat',JSON_ARRAYAGG(c2.m_chat))
 from chat c1 join (select id,JSON_OBJECT('date',datetime,'author', author, 'message', message) as m_chat
             from chat ) c2
 on c1.id = c2.id
 group by client,product,technician;

 DROP TABLE chat;

 rename table chat_tmp to chat;

