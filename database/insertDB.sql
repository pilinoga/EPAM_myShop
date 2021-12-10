INSERT INTO `shop`.`roles` (`id`,`name`) VALUES (1, 'admin');
INSERT INTO `shop`.`roles` (`id`,`name`) VALUES (2, 'customer');


/* password for admin_admin - admin12345, password  for customerP - customer12345 */
INSERT INTO `shop`.`users`(`id`,`role_id`,`login`,`password`) VALUES (1,1,'admin_admin', '7488e331b8b64e5794da3fa4eb10ad5d');
INSERT INTO `shop`.`users`(`id`,`role_id`,`login`,`password`) VALUES (2,2,'customerP', '2244c33addec1ac86eae06dee741f24e');

INSERT INTO `shop`.`customers`(`id`,`first_name`,`last_name`,`email`,`phone_number`,`card_balance`,`status`)
VALUES (1,'kirill','pilinoga','kirill.pilinoga@gmail.com','336118899',500,1);

INSERT INTO `shop`.`orders`(`id`,`customer_id`,`date`,`price`,`status`)
VALUES (46,2,2021-11-13,123,1);
INSERT INTO `shop`.`orders`(`id`,`customer_id`,`date`,`price`,`status`)
VALUES (61,2,2021-11-13,246,1);
INSERT INTO `shop`.`orders`(`id`,`customer_id`,`date`,`price`,`status`)
VALUES (65,2,2021-11-13,369,1);

INSERT INTO `shop`.`order_items`(`id`,`order_id`,`product_id`, `quantity`)
VALUES (161,46,11,1);
INSERT INTO `shop`.`order_items`(`id`,`order_id`,`product_id`, `quantity`)
VALUES (184,61,11,1);
INSERT INTO `shop`.`order_items`(`id`,`order_id`,`product_id`, `quantity`)
VALUES (185,61,11,1);
INSERT INTO `shop`.`order_items`(`id`,`order_id`,`product_id`, `quantity`)
VALUES (192,65,11,1);
INSERT INTO `shop`.`order_items`(`id`,`order_id`,`product_id`, `quantity`)
VALUES (193,65,11,1);
INSERT INTO `shop`.`order_items`(`id`,`order_id`,`product_id`, `quantity`)
VALUES (194,65,11,1);

INSERT INTO `shop`.`products`(`id`,`name`,`description`,`price`,`specification`)
VALUES (11,'iphone','10 max',123,'Флагман от компании Apple 2017 года. Задняя крышка выполнена из стекла, по бокам "хирургическая" нержавеющая сталь. Новый OLED-дисплей получил название Super Retina, его диагональ составляет 5,8 дюйма, разрешение — 2436x1125 точек (458 ppi), реализована поддержка технологий True Tone и 3D Touch. Вместо дактилоскопического датчика теперь используется технология Face ID — система распознавания лица для разблокировки смартфона. Аппарат получил продвинутую поддержку дополненной реальности, а также стереодинамики.');
INSERT INTO `shop`.`products`(`id`,`name`,`description`,`price`,`specification`)
VALUES (13,'iphone','12 max 128 gb',1230,'С каждым годом Apple оставляет все меньше поводов смотреть на Pro-модификации iPhone. Новый iPhone 12 отличается от флагмана всего парой вещей, и есть подозрение, что для большинства людей эта разница не будет критичной, особенно при серьезной разбежке по цене. На первый взгляд $200 кажутся неоправданно большой переплатой за третий фотосенсор, LiDAR-сканер и матовое стекло на задней крышке. В остальном iPhone 12 выглядит лучшим из новых смартфонов Apple. ');

