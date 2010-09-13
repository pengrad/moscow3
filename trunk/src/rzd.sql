# SQL Manager 2010 for MySQL 4.5.0.9
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : rzd


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES cp1251 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `rzd`;

CREATE DATABASE `rzd`
    CHARACTER SET 'cp1251'
    COLLATE 'cp1251_general_ci';

USE `rzd`;

#
# Structure for the `car_another_location` table : 
#

CREATE TABLE `car_another_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `road_type` table : 
#

CREATE TABLE `road_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `road` table : 
#

CREATE TABLE `road` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `id_type` int(11) NOT NULL,
  `comments` varchar(2000) DEFAULT ' ',
  PRIMARY KEY (`id`),
  KEY `road_fk` (`id_type`),
  CONSTRAINT `road_fk` FOREIGN KEY (`id_type`) REFERENCES `road_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `route` table : 
#

CREATE TABLE `route` (
  `id_route` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) NOT NULL COMMENT 'Номер поезда (маршрута)',
  `point_departure` varchar(500) NOT NULL COMMENT 'Пункт отправления',
  `point_destination` varchar(500) NOT NULL COMMENT 'Пункт прибытия',
  PRIMARY KEY (`id_route`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Structure for the `train` table : 
#

CREATE TABLE `train` (
  `id_train` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор',
  `id_route` int(11) NOT NULL COMMENT 'Ссылка на маршрут',
  `dt_departure` datetime NOT NULL COMMENT 'Время и дата отправления поезда',
  `dt_destination` datetime NOT NULL COMMENT 'Дата и время прибытия поезда',
  PRIMARY KEY (`id_train`),
  KEY `train_fk` (`id_route`),
  CONSTRAINT `train_fk` FOREIGN KEY (`id_route`) REFERENCES `route` (`id_route`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Structure for the `car_location` table : 
#

CREATE TABLE `car_location` (
  `id_location` int(11) NOT NULL AUTO_INCREMENT,
  `id_train` int(11) DEFAULT NULL,
  `id_road` int(11) DEFAULT NULL,
  `id_otherlocation` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_location`),
  KEY `id_train` (`id_train`),
  KEY `id_road` (`id_road`),
  KEY `id_otherlocation` (`id_otherlocation`),
  CONSTRAINT `car_location_fk` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`),
  CONSTRAINT `car_location_fk1` FOREIGN KEY (`id_road`) REFERENCES `road` (`id`),
  CONSTRAINT `car_location_fk2` FOREIGN KEY (`id_otherlocation`) REFERENCES `car_another_location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=cp1251;

#
# Structure for the `car` table : 
#

CREATE TABLE `car` (
  `number` int(11) NOT NULL COMMENT 'Уникальный номер вагона',
  `id_location` int(11) NOT NULL COMMENT 'Ссылка на место нахождения вагона\r\nМожет ссылаться на путь или что то еще в зависимости от поля type_location',
  `date_update_location` datetime NOT NULL COMMENT 'Дата изменения местоположения',
  `date_update` datetime NOT NULL COMMENT 'Дата обновления записи',
  PRIMARY KEY (`number`),
  KEY `car_fk` (`id_location`),
  CONSTRAINT `car_fk` FOREIGN KEY (`id_location`) REFERENCES `car_location` (`id_location`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='type_location -1 - в составе поезда 0 - на пути 1 - прочее';

#
# Structure for the `route_schedule` table : 
#

CREATE TABLE `route_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_route` int(11) NOT NULL COMMENT 'ссылка на маршрут',
  `time_departure` time NOT NULL COMMENT 'Время отправления поезда',
  `time_destination` time NOT NULL COMMENT 'Время прибытия',
  `date_begin` date NOT NULL COMMENT 'Начало действия расписания',
  `day_move` int(11) NOT NULL COMMENT 'Дни в пути',
  `day_stop` int(11) NOT NULL COMMENT 'Дни простоя на станции',
  PRIMARY KEY (`id`),
  KEY `id_route` (`id_route`),
  CONSTRAINT `route_schedule_fk` FOREIGN KEY (`id_route`) REFERENCES `route` (`id_route`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `train_cars` table : 
#

CREATE TABLE `train_cars` (
  `id_train` int(11) NOT NULL,
  `id_car` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_train`,`id_car`),
  KEY `id_train` (`id_train`),
  KEY `id_car` (`id_car`),
  CONSTRAINT `train_cars_fk1` FOREIGN KEY (`id_car`) REFERENCES `car` (`number`),
  CONSTRAINT `train_cars_fk` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;