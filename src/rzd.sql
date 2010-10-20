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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=cp1251;

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
  PRIMARY KEY (`id_train`),
  KEY `id_shedule` (`id_shedule`),
  KEY `id_status` (`id_status`),
  CONSTRAINT `train_fk` FOREIGN KEY (`id_shedule`) REFERENCES `shedule` (`id_shedule`),
  CONSTRAINT `train_fk1` FOREIGN KEY (`id_status`) REFERENCES `train_status` (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=cp1251;

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
  `date` date NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=cp1251;

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
  (2222222,3,10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,1,1),
  (12312345,4,12,'1','c','g','gp','a','ed','green',1,100,100,101,101),
  (22131231,3,10,'','','','','','','',1,23,23,232,232),
  (34324242,2,10,'erwerw','4r3r','r34r','','34r','4r3r','3r43',0,22,222,22,222);
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
  (1,'11:11:11','11:11:11',1,1,1),
  (2,'12:12:12','12:12:12',1,1,1),
  (3,'00:45:33','00:45:33',1,1,1),
  (4,'00:45:33','00:45:33',1,1,1),
  (7,'00:46:41','00:46:41',1,1,1),
  (8,'00:46:41','00:46:41',1,1,1),
  (9,'02:04:39','02:04:39',1,1,1),
  (10,'02:04:39','02:04:39',1,1,1),
  (11,'11:59:00','00:03:00',180,4,1),
  (12,'07:31:00','02:40:00',7,9,1),
  (13,'03:00:00','08:00:00',1,1,4),
  (14,'05:06:00','11:06:00',1,1,3),
  (17,'02:29:43','02:29:43',38,11,1),
  (18,'02:29:43','02:29:43',36,66,1),
  (19,'11:27:00','00:01:00',11,28,1),
  (20,'11:27:00','01:01:00',11,27,1),
  (21,'01:34:00','11:48:00',58,14,1),
  (22,'01:34:00','01:34:00',52,0,1),
  (23,'23:43:55','23:43:55',10,11,1),
  (24,'04:43:55','04:43:55',11,20,1);
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

INSERT INTO `train` (`id_train`, `id_shedule`, `train_chief`, `date_from`, `date_to`, `id_status`) VALUES 
  (125,23,NULL,'2010-10-11 23:43:55','2010-10-12 09:54:55',1),
  (126,23,'f2f2f2f','2010-10-13 23:43:55','2010-10-14 09:54:55',2),
  (127,23,'Иванов','2010-10-15 23:43:55','2010-10-16 09:54:55',2),
  (128,23,'Petrov','2010-10-17 23:43:55','2010-10-18 09:54:55',2),
  (129,23,'www','2010-10-19 23:43:55','2010-10-20 09:54:55',2),
  (130,23,NULL,'2010-10-21 23:43:55','2010-10-22 09:54:55',1),
  (131,23,NULL,'2010-10-23 23:43:55','2010-10-24 09:54:55',1),
  (132,23,NULL,'2010-10-25 23:43:55','2010-10-26 09:54:55',1),
  (133,23,NULL,'2010-10-27 23:43:55','2010-10-28 09:54:55',1),
  (134,23,'regeg','2010-10-29 23:43:55','2010-10-30 09:54:55',2),
  (135,23,NULL,'2010-10-31 23:43:55','2010-11-01 09:54:55',1),
  (136,23,NULL,'2010-11-01 23:43:55','2010-11-02 09:54:55',1),
  (137,23,NULL,'2010-11-03 23:43:55','2010-11-04 09:54:55',1),
  (138,23,NULL,'2010-11-05 23:43:55','2010-11-06 09:54:55',1),
  (139,23,NULL,'2010-11-07 23:43:55','2010-11-08 09:54:55',1),
  (140,23,NULL,'2010-11-09 23:43:55','2010-11-10 09:54:55',1),
  (141,23,NULL,'2010-11-11 23:43:55','2010-11-12 09:54:55',1),
  (142,23,NULL,'2010-11-13 23:43:55','2010-11-14 09:54:55',1),
  (143,23,NULL,'2010-11-15 23:43:55','2010-11-16 09:54:55',1),
  (144,23,NULL,'2010-11-17 23:43:55','2010-11-18 09:54:55',1),
  (145,24,NULL,'2010-10-11 04:43:55','2010-10-11 16:03:55',1),
  (146,24,NULL,'2010-10-13 04:43:55','2010-10-13 16:03:55',1),
  (147,24,NULL,'2010-10-15 04:43:55','2010-10-15 16:03:55',1),
  (148,24,NULL,'2010-10-17 04:43:55','2010-10-17 16:03:55',1),
  (149,24,NULL,'2010-10-19 04:43:55','2010-10-19 16:03:55',1),
  (150,24,NULL,'2010-10-21 04:43:55','2010-10-21 16:03:55',1),
  (151,24,NULL,'2010-10-23 04:43:55','2010-10-23 16:03:55',1),
  (152,24,NULL,'2010-10-25 04:43:55','2010-10-25 16:03:55',1),
  (153,24,NULL,'2010-10-27 04:43:55','2010-10-27 16:03:55',1),
  (154,24,NULL,'2010-10-29 04:43:55','2010-10-29 16:03:55',1),
  (155,24,NULL,'2010-10-31 04:43:55','2010-10-31 16:03:55',1),
  (156,24,NULL,'2010-11-01 04:43:55','2010-11-01 16:03:55',1),
  (157,24,NULL,'2010-11-03 04:43:55','2010-11-03 16:03:55',1),
  (158,24,NULL,'2010-11-05 04:43:55','2010-11-05 16:03:55',1),
  (159,24,NULL,'2010-11-07 04:43:55','2010-11-07 16:03:55',1),
  (160,24,NULL,'2010-11-09 04:43:55','2010-11-09 16:03:55',1),
  (161,24,NULL,'2010-11-11 04:43:55','2010-11-11 16:03:55',1),
  (162,24,NULL,'2010-11-13 04:43:55','2010-11-13 16:03:55',1),
  (163,24,NULL,'2010-11-15 04:43:55','2010-11-15 16:03:55',1),
  (164,24,NULL,'2010-11-17 04:43:55','2010-11-17 16:03:55',1);
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
  (1,1,34324242,2,'2010-10-16 19:55:31','2010-10-16 21:25:07',NULL),
  (2,1,22131231,2,'2010-10-16 21:06:01','2010-10-16 21:25:36','f3f3ff3f'),
  (3,1,34324242,27,'2010-10-16 21:25:07','2010-10-16 21:26:07',NULL),
  (4,1,22131231,19,'2010-10-16 21:25:36','2010-10-16 21:26:26',NULL),
  (5,3,34324242,2,'2010-10-16 21:26:07','2010-10-16 21:26:33',NULL),
  (6,1,22131231,25,'2010-10-16 21:26:26','2010-10-16 21:26:38',NULL),
  (7,1,34324242,2,'2010-10-16 21:26:33','2010-10-16 21:27:14',NULL),
  (8,1,22131231,2,'2010-10-16 21:26:38','2010-10-16 21:26:54',NULL),
  (9,1,22131231,NULL,'2010-10-16 21:26:54','2010-10-16 21:27:07',NULL),
  (10,1,22131231,NULL,'2010-10-16 21:27:07','2010-10-16 21:27:23',NULL),
  (11,1,34324242,NULL,'2010-10-17 00:01:25','2010-10-17 00:04:38',NULL),
  (12,3,22131231,NULL,'2010-10-16 21:27:23','2010-10-16 21:27:52',NULL),
  (13,1,34324242,5,'2010-10-16 21:27:31','2010-10-16 21:46:22',NULL),
  (14,1,22131231,34,'2010-10-16 21:27:52','2010-10-16 21:32:48',NULL),
  (15,1,22131231,34,'2010-10-16 21:32:48','2010-10-16 21:37:57',NULL),
  (16,1,22131231,34,'2010-10-16 21:37:57','2010-10-16 21:38:09',NULL),
  (17,1,22131231,NULL,'2010-10-16 21:38:09','2010-10-16 21:38:31',NULL),
  (18,1,22131231,14,'2010-10-16 21:38:31','2010-10-16 21:41:31',NULL),
  (19,1,22131231,14,'2010-10-16 21:41:31','2010-10-16 21:42:54',NULL),
  (20,1,22131231,14,'2010-10-16 21:42:54','2010-10-16 21:43:25',NULL),
  (21,1,22131231,14,'2010-10-16 21:43:25','2010-10-16 21:44:22',NULL),
  (22,1,22131231,14,'2010-10-16 21:44:22','2010-10-16 21:48:25',NULL),
  (23,1,34324242,5,'2010-10-16 21:46:22','2010-10-17 00:29:48','ddddddddddddddd'),
  (24,5,22131231,18,'2010-10-16 21:48:25','2010-10-17 00:29:17','wwww'),
  (25,1,12312345,2,'2010-10-16 23:46:43','2010-10-20 23:22:07','refewfwfwfwf'),
  (26,3,34324242,19,'2010-10-17 00:29:48','2010-10-10 11:11:11',''),
  (27,2,12312345,NULL,'2010-10-17 00:32:06','2010-10-20 23:22:36','refewfwfwfwf ewfwfwfwfwffwf'),
  (28,1,22131231,2,'2010-10-17 00:46:16','2010-10-17 00:59:27','лдосдлцуа'),
  (29,1,22131231,2,'2010-10-17 00:59:27','2010-10-17 01:23:59',''),
  (30,1,22131231,2,'2010-10-17 01:23:59','2010-10-10 10:11:10','helloi'),
  (31,1,2222222,18,'2010-10-17 01:27:14','2010-10-21 01:01:07','!!!'),
  (32,3,12312345,5,'2010-10-20 23:22:36','2010-10-10 10:10:10','refewfwfwfwf ewfwfwfwfwffwf'),
  (33,1,12312345,2,'2010-10-21 01:05:34','2010-10-21 01:15:12',''),
  (34,2,12312345,NULL,'2010-10-21 01:15:12','2010-10-21 01:17:04','aasasa'),
  (35,1,12312345,NULL,'2010-10-21 01:24:29',NULL,'');
COMMIT;

#
# Data for the `car_history` table  (LIMIT 0,500)
#

INSERT INTO `car_history` (`id`, `car_number`, `id_location`, `id_train`, `id_road`, `id_repair`, `date`) VALUES 
  (1,12312345,2,128,NULL,NULL,'2010-10-11'),
  (2,34324242,1,NULL,NULL,NULL,'2010-10-16'),
  (3,34324242,1,NULL,NULL,NULL,'2010-10-16'),
  (4,34324242,1,NULL,NULL,NULL,'2010-10-16'),
  (5,34324242,1,NULL,NULL,NULL,'2010-10-16'),
  (6,34324242,1,NULL,NULL,NULL,'2010-10-16'),
  (7,34324242,1,NULL,NULL,NULL,'2010-10-16'),
  (8,34324242,3,NULL,2,NULL,'2010-10-16'),
  (9,34324242,3,NULL,17,NULL,'2010-10-16'),
  (10,34324242,4,NULL,NULL,1,'2010-10-16'),
  (11,12312345,3,NULL,2,NULL,'2010-10-16'),
  (12,22131231,4,NULL,NULL,2,'2010-10-16'),
  (13,12312345,3,NULL,14,NULL,'2010-10-16'),
  (14,34324242,4,NULL,NULL,3,'2010-10-16'),
  (15,22131231,4,NULL,NULL,4,'2010-10-16'),
  (16,34324242,4,NULL,NULL,5,'2010-10-16'),
  (17,22131231,4,NULL,NULL,6,'2010-10-16'),
  (18,34324242,4,NULL,NULL,7,'2010-10-16'),
  (19,22131231,4,NULL,NULL,8,'2010-10-16'),
  (20,22131231,4,NULL,NULL,9,'2010-10-16'),
  (21,22131231,4,NULL,NULL,10,'2010-10-16'),
  (22,34324242,4,NULL,NULL,11,'2010-10-16'),
  (23,22131231,4,NULL,NULL,12,'2010-10-16'),
  (24,34324242,4,NULL,NULL,13,'2010-10-16'),
  (25,22131231,4,NULL,NULL,14,'2010-10-16'),
  (26,22131231,4,NULL,NULL,15,'2010-10-16'),
  (27,22131231,4,NULL,NULL,16,'2010-10-16'),
  (28,22131231,4,NULL,NULL,17,'2010-10-16'),
  (29,22131231,4,NULL,NULL,18,'2010-10-16'),
  (30,22131231,4,NULL,NULL,19,'2010-10-16'),
  (31,22131231,4,NULL,NULL,20,'2010-10-16'),
  (32,22131231,4,NULL,NULL,21,'2010-10-16'),
  (33,22131231,4,NULL,NULL,22,'2010-10-16'),
  (34,34324242,4,NULL,NULL,23,'2010-10-16'),
  (35,22131231,4,NULL,NULL,24,'2010-10-16'),
  (36,12312345,4,NULL,NULL,25,'2010-10-16'),
  (37,22131231,3,NULL,27,NULL,'2010-10-17'),
  (38,34324242,1,NULL,NULL,NULL,'2010-10-17'),
  (39,22131231,3,NULL,14,NULL,'2010-10-17'),
  (40,34324242,4,NULL,NULL,26,'2010-10-17'),
  (41,2222222,3,NULL,16,NULL,'2010-10-17'),
  (42,2222222,1,NULL,NULL,NULL,'2010-10-17'),
  (43,12312345,4,NULL,NULL,27,'2010-10-17'),
  (44,22131231,4,NULL,NULL,28,'2010-10-17'),
  (45,22131231,1,NULL,NULL,NULL,'2010-10-17'),
  (46,22131231,4,NULL,NULL,29,'2010-10-17'),
  (47,22131231,3,NULL,2,NULL,'2010-10-17'),
  (48,22131231,4,NULL,NULL,30,'2010-10-17'),
  (49,2222222,4,NULL,NULL,31,'2010-10-17'),
  (50,12312345,3,NULL,2,NULL,'2010-10-20'),
  (51,12312345,4,NULL,NULL,32,'2010-10-20'),
  (52,22131231,3,NULL,2,NULL,'2010-10-21'),
  (53,34324242,3,NULL,2,NULL,'2010-10-21'),
  (54,12312345,3,NULL,2,NULL,'2010-10-21'),
  (55,2222222,3,NULL,32,NULL,'2010-10-21'),
  (56,34324242,2,126,NULL,NULL,'2010-10-21'),
  (57,12312345,4,NULL,NULL,33,'2010-10-21'),
  (58,12312345,1,NULL,NULL,NULL,'2010-10-21'),
  (59,12312345,4,NULL,NULL,34,'2010-10-21'),
  (60,12312345,1,NULL,NULL,NULL,'2010-10-21'),
  (61,12312345,4,NULL,NULL,35,'2010-10-21');
COMMIT;

#
# Data for the `road_det` table  (LIMIT 0,500)
#

INSERT INTO `road_det` (`id`, `id_road`, `id_train`, `car_number`) VALUES 
  (2,15,127,NULL),
  (3,2,126,NULL),
  (4,23,134,NULL),
  (5,25,129,NULL),
  (6,14,128,NULL),
  (24,2,NULL,22131231),
  (29,32,NULL,2222222),
  (30,2,126,NULL);
COMMIT;

#
# Data for the `route` table  (LIMIT 0,500)
#

INSERT INTO `route` (`id_route`, `city_from`, `city_to`, `number_forward`, `number_back`, `shedule_forward`, `shedule_back`, `length_forward`, `length_back`, `enabled`) VALUES 
  (1,'Москва','Владик','101','102',2,2,1,1,1),
  (2,'Evgen','Ekaterina','www','322e2',11,12,1,1,0),
  (3,'111','222','qwe','ert',13,14,50078,101,1),
  (5,'Evgen','Ekaterina','love!!!','hate!!!',17,18,101,101,0),
  (6,'Evgen','Ekaterina','www','rrrr',19,20,101,101,1),
  (7,'Evgen','Ekaterina','тест','тест',21,22,101,101,0),
  (8,'Москва','Питер','тест','тест',23,24,101,101,1);
COMMIT;

#
# Data for the `shedule_days` table  (LIMIT 0,500)
#

INSERT INTO `shedule_days` (`id_shedule`, `day`) VALUES 
  (2,5),
  (2,11),
  (2,12),
  (7,1),
  (7,2),
  (7,4),
  (7,34),
  (8,0),
  (8,1),
  (8,3),
  (8,6),
  (9,1999),
  (10,0),
  (10,1),
  (10,3),
  (10,6),
  (13,6),
  (13,12),
  (13,18),
  (13,23),
  (14,1),
  (14,6),
  (17,1),
  (17,2),
  (17,3),
  (17,4),
  (18,6),
  (18,8),
  (18,9),
  (18,10);
COMMIT;

#
# Data for the `train_det` table  (LIMIT 0,500)
#

INSERT INTO `train_det` (`id_train`, `car_number`) VALUES 
  (126,34324242),
  (128,12312345);
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;