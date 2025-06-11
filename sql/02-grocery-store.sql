CREATE DATABASE IF NOT EXISTS `grocery_store`;
USE `grocery_store`;
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `items` 
VALUES 
(1,'Beef',2,10.99),
(2,'Apple',4,3.50),
(3,'Orange Juice',6,4.74),
(4,'Chocolate',8,5),
(5,'Milk',10,2.98);
