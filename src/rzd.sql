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
# Structure for the `car` table : 
#

CREATE TABLE `car` (
  `car_number` int(11) NOT NULL,
  `id_location` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `model` varchar(20) DEFAULT NULL,
  `conditioner` varchar(20) DEFAULT NULL,
  `generator` varchar(20) DEFAULT NULL,
  `generator_privod` varchar(20) DEFAULT NULL,
  `accumulator` varchar(20) DEFAULT NULL,
  `electric_device` varchar(20) DEFAULT NULL,
  `body_color` varchar(20) DEFAULT NULL,
  `ecologic_toilet` bit(1) DEFAULT b'0',
  `run` int(11) DEFAULT NULL,
  `run_TOZ` int(11) DEFAULT NULL,
  `run_norm` int(11) DEFAULT NULL,
  `run_TOZ_norm` int(11) DEFAULT NULL,
  PRIMARY KEY (`car_number`),
  KEY `id_location` (`id_location`),
  KEY `id_type` (`id_type`),
  CONSTRAINT `car_fk` FOREIGN KEY (`id_location`) REFERENCES `car_location` (`id_location`),
  CONSTRAINT `car_fk1` FOREIGN KEY (`id_type`) REFERENCES `car_type` (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `car_location` table : 
#

CREATE TABLE `car_location` (
  `id_location` int(11) NOT NULL,
  `c_location` varchar(100) NOT NULL,
  PRIMARY KEY (`id_location`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `repair_type` table : 
#

CREATE TABLE `repair_type` (
  `id_type` int(11) NOT NULL,
  `c_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `road_type` table : 
#

CREATE TABLE `road_type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Structure for the `road` table : 
#

CREATE TABLE `road` (
  `id_road` int(11) NOT NULL AUTO_INCREMENT,
  `road_name` varchar(100) NOT NULL DEFAULT '',
  `id_type` int(11) NOT NULL,
  `comments` varchar(2000) DEFAULT ' ',
  `position` int(11) DEFAULT NULL COMMENT 'порядок пути. для сортировки',
  PRIMARY KEY (`id_road`),
  KEY `id_type` (`id_type`),
  CONSTRAINT `road_fk_new` FOREIGN KEY (`id_type`) REFERENCES `road_type` (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

#
# Structure for the `repair` table : 
#

CREATE TABLE `repair` (
  `id_repair` int(11) NOT NULL AUTO_INCREMENT,
  `id_type` int(11) NOT NULL,
  `car_number` int(11) NOT NULL,
  `id_road` int(11) DEFAULT NULL,
  `date_begin` datetime NOT NULL,
  `date_end` datetime DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_repair`),
  KEY `id_type` (`id_type`),
  KEY `car_number` (`car_number`),
  KEY `id_road` (`id_road`),
  CONSTRAINT `repair_fk` FOREIGN KEY (`id_type`) REFERENCES `repair_type` (`id_type`),
  CONSTRAINT `repair_fk1` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`),
  CONSTRAINT `repair_fk2` FOREIGN KEY (`id_road`) REFERENCES `road` (`id_road`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=cp1251;

#
# Structure for the `shedule_type` table : 
#

CREATE TABLE `shedule_type` (
  `id_shedule_type` int(11) NOT NULL,
  `c_shedule_type` varchar(30) NOT NULL,
  PRIMARY KEY (`id_shedule_type`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `shedule` table : 
#

CREATE TABLE `shedule` (
  `id_shedule` int(11) NOT NULL AUTO_INCREMENT,
  `time_from` time NOT NULL,
  `time_to` time NOT NULL,
  `hours_in_way` int(11) NOT NULL,
  `minutes_in_way` int(11) NOT NULL,
  `id_shedule_type` int(11) NOT NULL,
  PRIMARY KEY (`id_shedule`),
  KEY `id_shedule_type` (`id_shedule_type`),
  CONSTRAINT `id_shedule_type` FOREIGN KEY (`id_shedule_type`) REFERENCES `shedule_type` (`id_shedule_type`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=cp1251;

#
# Structure for the `train_status` table : 
#

CREATE TABLE `train_status` (
  `id_status` int(11) NOT NULL,
  `c_status` varchar(30) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `train` table : 
#

CREATE TABLE `train` (
  `id_train` int(11) NOT NULL AUTO_INCREMENT,
  `id_shedule` int(11) NOT NULL,
  `train_chief` varchar(100) DEFAULT NULL,
  `date_from` datetime NOT NULL,
  `date_to` datetime NOT NULL,
  `id_status` int(11) NOT NULL,
  `car_from_head` bit(1) NOT NULL,
  PRIMARY KEY (`id_train`),
  KEY `id_shedule` (`id_shedule`),
  KEY `id_status` (`id_status`),
  CONSTRAINT `train_fk` FOREIGN KEY (`id_shedule`) REFERENCES `shedule` (`id_shedule`),
  CONSTRAINT `train_fk1` FOREIGN KEY (`id_status`) REFERENCES `train_status` (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=805 DEFAULT CHARSET=cp1251;

#
# Structure for the `car_history` table : 
#

CREATE TABLE `car_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_number` int(11) NOT NULL,
  `id_location` int(11) NOT NULL,
  `id_train` int(11) DEFAULT NULL,
  `id_road` int(11) DEFAULT NULL,
  `id_repair` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_location` (`id_location`),
  KEY `id_train` (`id_train`),
  KEY `id_road` (`id_road`),
  KEY `car_number` (`car_number`),
  KEY `id_repair` (`id_repair`),
  CONSTRAINT `car_history_fk` FOREIGN KEY (`id_location`) REFERENCES `car_location` (`id_location`),
  CONSTRAINT `car_history_fk1` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`),
  CONSTRAINT `car_history_fk2` FOREIGN KEY (`id_road`) REFERENCES `road` (`id_road`),
  CONSTRAINT `car_history_fk3` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`),
  CONSTRAINT `car_history_fk4` FOREIGN KEY (`id_repair`) REFERENCES `repair` (`id_repair`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=cp1251;

#
# Structure for the `car_type` table : 
#

CREATE TABLE `car_type` (
  `id_type` int(11) NOT NULL,
  `id_parent_type` int(11) DEFAULT NULL,
  `c_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type`),
  KEY `id_parent_type` (`id_parent_type`),
  CONSTRAINT `car_type_fk` FOREIGN KEY (`id_parent_type`) REFERENCES `car_type` (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `road_det` table : 
#

CREATE TABLE `road_det` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_road` int(11) NOT NULL,
  `id_train` int(11) DEFAULT NULL,
  `car_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_road` (`id_road`),
  KEY `id_train` (`id_train`),
  KEY `car_number` (`car_number`),
  CONSTRAINT `road_det_fk` FOREIGN KEY (`id_road`) REFERENCES `road` (`id_road`),
  CONSTRAINT `road_det_fk1` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`),
  CONSTRAINT `road_det_fk2` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=cp1251;

#
# Structure for the `route` table : 
#

CREATE TABLE `route` (
  `id_route` int(11) NOT NULL AUTO_INCREMENT,
  `city_from` varchar(50) NOT NULL,
  `city_to` varchar(50) NOT NULL,
  `number_forward` varchar(20) NOT NULL,
  `number_back` varchar(20) NOT NULL,
  `shedule_forward` int(11) NOT NULL,
  `shedule_back` int(11) NOT NULL,
  `length_forward` int(11) NOT NULL,
  `length_back` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id_route`),
  KEY `shedule_forward` (`shedule_forward`),
  KEY `shedule_back` (`shedule_back`),
  CONSTRAINT `shedule_back` FOREIGN KEY (`shedule_back`) REFERENCES `shedule` (`id_shedule`),
  CONSTRAINT `shedule_forward` FOREIGN KEY (`shedule_forward`) REFERENCES `shedule` (`id_shedule`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=cp1251;

#
# Structure for the `shedule_days` table : 
#

CREATE TABLE `shedule_days` (
  `id_shedule` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id_shedule`,`day`),
  KEY `id_shedule` (`id_shedule`),
  CONSTRAINT `id_shedule` FOREIGN KEY (`id_shedule`) REFERENCES `shedule` (`id_shedule`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `train_det` table : 
#

CREATE TABLE `train_det` (
  `id_train` int(11) NOT NULL,
  `car_number` int(11) NOT NULL,
  `car_number_in_train` int(11) NOT NULL,
  PRIMARY KEY (`car_number`,`id_train`),
  KEY `id_train` (`id_train`),
  KEY `car_number` (`car_number`),
  CONSTRAINT `train_det_fk` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`),
  CONSTRAINT `train_det_fk1` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Data for the `car_location` table  (LIMIT 0,500)
#

INSERT INTO `car_location` (`id_location`, `c_location`) VALUES 
  (1,'Неизвестно'),
  (2,'В составе поезда'),
  (3,'На пути, не в составе поезда'),
  (4,'В ремонте');
COMMIT;

#
# Data for the `car_type` table  (LIMIT 0,500)
#

INSERT INTO `car_type` (`id_type`, `id_parent_type`, `c_type`) VALUES 
  (1,NULL,'Пассажирский'),
  (2,NULL,'Грузовой'),
  (3,NULL,'Почтовый'),
  (10,1,'Плацкарт'),
  (11,1,'Купе'),
  (12,1,'СВ'),
  (20,2,'Хоппер'),
  (21,2,'Цистерна');
COMMIT;

#
# Data for the `car` table  (LIMIT 0,500)
#

INSERT INTO `car` (`car_number`, `id_location`, `id_type`, `model`, `conditioner`, `generator`, `generator_privod`, `accumulator`, `electric_device`, `body_color`, `ecologic_toilet`, `run`, `run_TOZ`, `run_norm`, `run_TOZ_norm`) VALUES 
  (11111111,2,10,'','','','','','','',1,11,11,11,11),
  (22222222,4,10,'Модель 2784','','','','','','',0,1,11,1,1),
  (33333333,2,10,'','','','','','','',0,3,3,3,3),
  (44444444,2,10,'','','','','','','',0,34,234,323,23);
COMMIT;

#
# Data for the `shedule_type` table  (LIMIT 0,500)
#

INSERT INTO `shedule_type` (`id_shedule_type`, `c_shedule_type`) VALUES 
  (1,'Нечетные числа'),
  (2,'Четные числа'),
  (3,'Дни недели'),
  (4,'Числа месяца');
COMMIT;

#
# Data for the `shedule` table  (LIMIT 0,500)
#

INSERT INTO `shedule` (`id_shedule`, `time_from`, `time_to`, `hours_in_way`, `minutes_in_way`, `id_shedule_type`) VALUES 
  (25,'21:50:00','07:56:00',10,6,3),
  (26,'03:04:00','09:08:00',6,4,4),
  (43,'03:00:00','06:00:00',3,0,3),
  (44,'04:00:00','12:00:00',8,0,3);
COMMIT;

#
# Data for the `train_status` table  (LIMIT 0,500)
#

INSERT INTO `train_status` (`id_status`, `c_status`) VALUES 
  (1,'Запланирован'),
  (2,'Сформирован'),
  (3,'В пути'),
  (4,'Прибыл'),
  (5,'Расформирован');
COMMIT;

#
# Data for the `train` table  (LIMIT 0,500)
#

INSERT INTO `train` (`id_train`, `id_shedule`, `train_chief`, `date_from`, `date_to`, `id_status`, `car_from_head`) VALUES 
  (385,26,'Паршин','2010-10-27 03:04:34','2010-10-27 06:08:34',5,0),
  (514,26,'Иванов','2011-02-01 03:04:34','2011-02-01 06:08:34',4,0),
  (685,43,NULL,'2010-10-28 03:00:31','2010-10-28 06:00:31',1,1),
  (686,43,NULL,'2010-10-29 03:00:31','2010-10-29 06:00:31',1,1),
  (687,43,NULL,'2010-10-30 03:00:31','2010-10-30 06:00:31',1,1),
  (688,43,NULL,'2010-10-31 03:00:31','2010-10-31 06:00:31',1,1),
  (689,43,NULL,'2010-11-01 03:00:31','2010-11-01 06:00:31',1,1),
  (690,43,NULL,'2010-11-02 03:00:31','2010-11-02 06:00:31',1,1),
  (691,43,NULL,'2010-11-03 03:00:31','2010-11-03 06:00:31',1,1),
  (692,43,NULL,'2010-11-04 03:00:31','2010-11-04 06:00:31',1,1),
  (693,43,NULL,'2010-11-05 03:00:31','2010-11-05 06:00:31',1,1),
  (694,43,NULL,'2010-11-06 03:00:31','2010-11-06 06:00:31',1,1),
  (695,43,NULL,'2010-11-07 03:00:31','2010-11-07 06:00:31',1,1),
  (696,43,NULL,'2010-11-08 03:00:31','2010-11-08 06:00:31',1,1),
  (697,43,NULL,'2010-11-09 03:00:31','2010-11-09 06:00:31',1,1),
  (698,43,NULL,'2010-11-10 03:00:31','2010-11-10 06:00:31',1,1),
  (699,43,NULL,'2010-11-11 03:00:31','2010-11-11 06:00:31',1,1),
  (700,43,NULL,'2010-11-12 03:00:31','2010-11-12 06:00:31',1,1),
  (701,43,NULL,'2010-11-13 03:00:31','2010-11-13 06:00:31',1,1),
  (702,43,NULL,'2010-11-14 03:00:31','2010-11-14 06:00:31',1,1),
  (703,43,NULL,'2010-11-15 03:00:31','2010-11-15 06:00:31',1,1),
  (704,43,NULL,'2010-11-16 03:00:31','2010-11-16 06:00:31',1,1),
  (705,44,NULL,'2010-10-28 04:00:31','2010-10-28 12:00:31',1,1),
  (706,44,NULL,'2010-10-29 04:00:31','2010-10-29 12:00:31',1,1),
  (707,44,NULL,'2010-10-30 04:00:31','2010-10-30 12:00:31',1,1),
  (708,44,NULL,'2010-10-31 04:00:31','2010-10-31 12:00:31',1,1),
  (709,44,NULL,'2010-11-01 04:00:31','2010-11-01 12:00:31',1,1),
  (710,44,NULL,'2010-11-02 04:00:31','2010-11-02 12:00:31',1,1),
  (711,44,NULL,'2010-11-03 04:00:31','2010-11-03 12:00:31',1,1),
  (712,44,NULL,'2010-11-04 04:00:31','2010-11-04 12:00:31',1,1),
  (713,44,NULL,'2010-11-05 04:00:31','2010-11-05 12:00:31',1,1),
  (714,44,NULL,'2010-11-06 04:00:31','2010-11-06 12:00:31',1,1),
  (715,44,NULL,'2010-11-07 04:00:31','2010-11-07 12:00:31',1,1),
  (716,44,NULL,'2010-11-08 04:00:31','2010-11-08 12:00:31',1,1),
  (717,44,NULL,'2010-11-09 04:00:31','2010-11-09 12:00:31',1,1),
  (718,44,NULL,'2010-11-10 04:00:31','2010-11-10 12:00:31',1,1),
  (719,44,NULL,'2010-11-11 04:00:31','2010-11-11 12:00:31',1,1),
  (720,44,NULL,'2010-11-12 04:00:31','2010-11-12 12:00:31',1,1),
  (721,44,NULL,'2010-11-13 04:00:31','2010-11-13 12:00:31',1,1),
  (722,44,NULL,'2010-11-14 04:00:31','2010-11-14 12:00:31',1,1),
  (723,44,NULL,'2010-11-15 04:00:31','2010-11-15 12:00:31',1,1),
  (724,44,NULL,'2010-11-16 04:00:31','2010-11-16 12:00:31',1,1),
  (725,25,'www','2010-10-27 21:50:48','2010-10-28 07:56:48',3,1),
  (726,25,NULL,'2010-10-28 21:50:48','2010-10-29 07:56:48',1,1),
  (727,25,NULL,'2010-10-29 21:50:48','2010-10-30 07:56:48',1,1),
  (728,25,NULL,'2010-10-30 21:50:48','2010-10-31 06:56:48',1,1),
  (729,25,NULL,'2010-10-31 21:50:48','2010-11-01 07:56:48',1,1),
  (730,25,NULL,'2010-11-01 21:50:48','2010-11-02 07:56:48',1,1),
  (731,25,NULL,'2010-11-02 21:50:48','2010-11-03 07:56:48',1,1),
  (732,25,NULL,'2010-11-03 21:50:48','2010-11-04 07:56:48',1,1),
  (733,25,NULL,'2010-11-04 21:50:48','2010-11-05 07:56:48',1,1),
  (734,25,NULL,'2010-11-05 21:50:48','2010-11-06 07:56:48',1,1),
  (735,25,NULL,'2010-11-06 21:50:48','2010-11-07 07:56:48',1,1),
  (736,25,NULL,'2010-11-07 21:50:48','2010-11-08 07:56:48',1,1),
  (737,25,NULL,'2010-11-08 21:50:48','2010-11-09 07:56:48',1,1),
  (738,25,NULL,'2010-11-09 21:50:48','2010-11-10 07:56:48',1,1),
  (739,25,NULL,'2010-11-10 21:50:48','2010-11-11 07:56:48',1,1),
  (740,25,NULL,'2010-11-11 21:50:48','2010-11-12 07:56:48',1,1),
  (741,25,NULL,'2010-11-12 21:50:48','2010-11-13 07:56:48',1,1),
  (742,25,NULL,'2010-11-13 21:50:48','2010-11-14 07:56:48',1,1),
  (743,25,NULL,'2010-11-14 21:50:48','2010-11-15 07:56:48',1,1),
  (744,25,NULL,'2010-11-15 21:50:48','2010-11-16 07:56:48',1,1),
  (745,26,NULL,'2010-11-01 03:04:48','2010-11-01 09:08:48',1,1),
  (746,26,NULL,'2010-11-07 03:04:48','2010-11-07 09:08:48',1,1),
  (747,26,NULL,'2010-11-13 03:04:48','2010-11-13 09:08:48',1,1),
  (748,26,NULL,'2010-12-01 03:04:48','2010-12-01 09:08:48',1,1),
  (749,26,NULL,'2010-12-07 03:04:48','2010-12-07 09:08:48',1,1),
  (750,26,NULL,'2010-12-13 03:04:48','2010-12-13 09:08:48',1,1),
  (751,26,NULL,'2011-01-01 03:04:48','2011-01-01 09:08:48',1,1),
  (752,26,NULL,'2011-01-07 03:04:48','2011-01-07 09:08:48',1,1),
  (753,26,NULL,'2011-01-13 03:04:48','2011-01-13 09:08:48',1,1),
  (754,26,NULL,'2011-02-01 03:04:48','2011-02-01 09:08:48',1,1),
  (755,26,NULL,'2011-02-07 03:04:48','2011-02-07 09:08:48',1,1),
  (756,26,NULL,'2011-02-13 03:04:48','2011-02-13 09:08:48',1,1),
  (757,26,NULL,'2011-03-01 03:04:48','2011-03-01 09:08:48',1,1),
  (758,26,NULL,'2011-03-07 03:04:48','2011-03-07 09:08:48',1,1),
  (759,26,NULL,'2011-03-13 03:04:48','2011-03-13 09:08:48',1,1),
  (760,26,NULL,'2011-04-01 03:04:48','2011-04-01 09:08:48',1,1),
  (761,26,NULL,'2011-04-07 03:04:48','2011-04-07 09:08:48',1,1),
  (762,26,NULL,'2011-04-13 03:04:48','2011-04-13 09:08:48',1,1),
  (763,26,NULL,'2011-05-01 03:04:48','2011-05-01 09:08:48',1,1),
  (764,26,NULL,'2011-05-07 03:04:48','2011-05-07 09:08:48',1,1),
  (795,25,NULL,'2010-11-16 21:50:48','2010-11-17 07:56:48',1,1),
  (796,25,NULL,'2010-11-17 21:50:48','2010-11-18 07:56:48',1,1),
  (797,25,NULL,'2010-11-18 21:50:48','2010-11-19 07:56:48',1,1),
  (798,25,NULL,'2010-11-19 21:50:48','2010-11-20 07:56:48',1,1),
  (799,25,NULL,'2010-11-20 21:50:48','2010-11-21 07:56:48',1,1),
  (800,25,NULL,'2010-11-21 21:50:48','2010-11-22 07:56:48',1,1),
  (801,25,NULL,'2010-11-22 21:50:48','2010-11-23 07:56:48',1,1),
  (802,25,NULL,'2010-11-23 21:50:48','2010-11-24 07:56:48',1,1),
  (803,25,NULL,'2010-11-24 21:50:48','2010-11-25 07:56:48',1,1),
  (804,25,NULL,'2010-11-25 21:50:48','2010-11-26 07:56:48',1,1);
COMMIT;

#
# Data for the `road_type` table  (LIMIT 0,500)
#

INSERT INTO `road_type` (`id_type`, `type_name`) VALUES 
  (1,'Приемоотправочные пути'),
  (2,'Пути отстоя вагонов'),
  (3,'Вытяжные пути'),
  (4,'Ремонтно-экипировочные пути'),
  (5,'Разгрузо-погрузочные пути');
COMMIT;

#
# Data for the `road` table  (LIMIT 0,500)
#

INSERT INTO `road` (`id_road`, `road_name`, `id_type`, `comments`, `position`) VALUES 
  (1,'1 (3 п. 11-го корпуса)',4,NULL,NULL),
  (2,'1 тупик',1,NULL,2),
  (3,'10 (2 п. Юго корпуса)',4,NULL,3),
  (4,'11 (1 п. Юго корпуса)',4,NULL,4),
  (5,'11 (угольный склад)',5,NULL,5),
  (6,'13 (аккомуляторная)',4,NULL,6),
  (7,'2 (2 п. 11-го корпуса)',4,NULL,7),
  (8,'2 РЭД',4,NULL,8),
  (9,'2 тупик',1,NULL,9),
  (10,'3 (1 п. 11-го корпуса)',4,NULL,10),
  (11,'3 РЭД',4,NULL,11),
  (12,'3 тупик (408)',4,NULL,12),
  (13,'31',1,NULL,13),
  (14,'32',1,NULL,14),
  (15,'33',1,NULL,15),
  (16,'34',1,NULL,16),
  (17,'35',1,NULL,1),
  (18,'36',1,NULL,1),
  (19,'38 (408)',2,NULL,1),
  (20,'39 (409)',2,NULL,1),
  (21,'4 РЭД',4,NULL,1),
  (22,'4 тупик (409)',4,NULL,1),
  (23,'40 (409)',2,NULL,1),
  (24,'41',2,NULL,1),
  (25,'437 (заводской)',3,'1',1),
  (26,'5',5,NULL,1),
  (27,'5 (объездной)',3,NULL,1),
  (28,'6-я строительная (438)',2,NULL,1),
  (29,'7',5,NULL,1),
  (30,'7-я строительная (439)',2,NULL,1),
  (31,'8 (8-я канава)',4,NULL,1),
  (32,'8 (вытежной)',3,NULL,1),
  (33,'9 (3 п. Юго корпуса)',4,NULL,1),
  (34,'9 (газовый тупик)',5,NULL,1);
COMMIT;

#
# Data for the `repair_type` table  (LIMIT 0,500)
#

INSERT INTO `repair_type` (`id_type`, `c_type`) VALUES 
  (1,'s'),
  (2,'aaaaa'),
  (3,'asdasdasd'),
  (5,'5');
COMMIT;

#
# Data for the `repair` table  (LIMIT 0,500)
#

INSERT INTO `repair` (`id_repair`, `id_type`, `car_number`, `id_road`, `date_begin`, `date_end`, `comment`) VALUES 
  (1,2,22222222,NULL,'2010-10-26 23:17:16',NULL,'');
COMMIT;

#
# Data for the `car_history` table  (LIMIT 0,500)
#

INSERT INTO `car_history` (`id`, `car_number`, `id_location`, `id_train`, `id_road`, `id_repair`, `date`) VALUES 
  (98,11111111,2,385,NULL,NULL,'2010-10-26 00:00:00'),
  (100,44444444,2,385,NULL,NULL,'2010-10-26 12:12:12'),
  (101,11111111,1,NULL,NULL,NULL,'2010-10-26 06:11:23'),
  (102,33333333,1,NULL,NULL,NULL,'2010-10-26 18:12:34'),
  (103,44444444,1,NULL,NULL,NULL,'2010-10-26 00:00:00'),
  (104,11111111,3,NULL,2,NULL,'2010-10-26 00:00:00'),
  (107,22222222,4,NULL,NULL,1,'2010-10-26 00:00:00'),
  (108,33333333,2,514,NULL,NULL,'2010-10-26 00:00:00'),
  (109,33333333,1,NULL,NULL,NULL,'2010-10-27 00:00:00'),
  (110,33333333,2,514,NULL,NULL,'2010-10-27 03:12:31'),
  (111,33333333,1,NULL,NULL,NULL,'2010-10-27 03:41:23'),
  (112,11111111,2,385,NULL,NULL,'2010-10-27 00:00:00'),
  (113,11111111,2,514,NULL,NULL,'2010-10-27 00:00:00'),
  (114,33333333,2,514,NULL,NULL,'2010-10-27 00:00:00'),
  (115,44444444,2,514,NULL,NULL,'2010-10-27 00:00:00');
COMMIT;

#
# Data for the `road_det` table  (LIMIT 0,500)
#

INSERT INTO `road_det` (`id`, `id_road`, `id_train`, `car_number`) VALUES 
  (54,9,514,NULL);
COMMIT;

#
# Data for the `route` table  (LIMIT 0,500)
#

INSERT INTO `route` (`id_route`, `city_from`, `city_to`, `number_forward`, `number_back`, `shedule_forward`, `shedule_back`, `length_forward`, `length_back`, `enabled`) VALUES 
  (9,'Москва','Владивосток','756','755',25,26,100,656,1),
  (18,'Москва','Курск','333','334',43,44,2442,32442,1);
COMMIT;

#
# Data for the `shedule_days` table  (LIMIT 0,500)
#

INSERT INTO `shedule_days` (`id_shedule`, `day`) VALUES 
  (25,1),
  (25,2),
  (25,3),
  (25,4),
  (25,5),
  (25,6),
  (25,7),
  (26,1),
  (26,7),
  (26,13),
  (43,1),
  (43,2),
  (43,3),
  (43,4),
  (43,5),
  (43,6),
  (43,7),
  (44,1),
  (44,2),
  (44,3),
  (44,4),
  (44,5),
  (44,6),
  (44,7);
COMMIT;

#
# Data for the `train_det` table  (LIMIT 0,500)
#

INSERT INTO `train_det` (`id_train`, `car_number`, `car_number_in_train`) VALUES 
  (385,11111111,0),
  (514,11111111,1),
  (514,33333333,3),
  (514,44444444,2);
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;