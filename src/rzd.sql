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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=445 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=cp1251;

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
  (22222222,1,10,'','','','','','','',0,1,11,1,1),
  (33333333,1,10,'','','','','','','',0,3,3,3,3),
  (44444444,1,10,'','','','','','','',0,34,234,323,23);
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
  (25,'03:04:00','07:10:00',4,6,1),
  (26,'03:04:00','06:08:00',3,4,1),
  (43,'03:00:00','06:00:00',3,0,3),
  (44,'04:00:00','08:00:00',4,0,3);
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
  (365,25,NULL,'2010-10-27 03:04:34','2010-10-27 07:10:34',1,1),
  (366,25,NULL,'2010-10-29 03:04:34','2010-10-29 07:10:34',1,1),
  (367,25,NULL,'2010-10-31 03:04:34','2010-10-31 07:10:34',1,1),
  (368,25,NULL,'2010-11-01 03:04:34','2010-11-01 07:10:34',1,1),
  (369,25,NULL,'2010-11-03 03:04:34','2010-11-03 07:10:34',1,1),
  (370,25,NULL,'2010-11-05 03:04:34','2010-11-05 07:10:34',1,1),
  (371,25,NULL,'2010-11-07 03:04:34','2010-11-07 07:10:34',1,1),
  (372,25,NULL,'2010-11-09 03:04:34','2010-11-09 07:10:34',1,1),
  (373,25,NULL,'2010-11-11 03:04:34','2010-11-11 07:10:34',1,1),
  (374,25,NULL,'2010-11-13 03:04:34','2010-11-13 07:10:34',1,1),
  (375,25,NULL,'2010-11-15 03:04:34','2010-11-15 07:10:34',1,1),
  (376,25,NULL,'2010-11-17 03:04:34','2010-11-17 07:10:34',1,1),
  (377,25,NULL,'2010-11-19 03:04:34','2010-11-19 07:10:34',1,1),
  (378,25,NULL,'2010-11-21 03:04:34','2010-11-21 07:10:34',1,1),
  (379,25,NULL,'2010-11-23 03:04:34','2010-11-23 07:10:34',1,1),
  (380,25,NULL,'2010-11-25 03:04:34','2010-11-25 07:10:34',1,1),
  (381,25,NULL,'2010-11-27 03:04:34','2010-11-27 07:10:34',1,1),
  (382,25,NULL,'2010-11-29 03:04:34','2010-11-29 07:10:34',1,1),
  (383,25,NULL,'2010-12-01 03:04:34','2010-12-01 07:10:34',1,1),
  (384,25,NULL,'2010-12-03 03:04:34','2010-12-03 07:10:34',1,1),
  (385,26,'Паршин','2010-10-27 03:04:34','2010-10-27 06:08:34',4,1),
  (386,26,NULL,'2010-10-29 03:04:34','2010-10-29 06:08:34',1,1),
  (387,26,NULL,'2010-10-31 03:04:34','2010-10-31 06:08:34',1,1),
  (388,26,NULL,'2010-11-01 03:04:34','2010-11-01 06:08:34',1,1),
  (389,26,NULL,'2010-11-03 03:04:34','2010-11-03 06:08:34',1,1),
  (390,26,NULL,'2010-11-05 03:04:34','2010-11-05 06:08:34',1,1),
  (391,26,NULL,'2010-11-07 03:04:34','2010-11-07 06:08:34',1,1),
  (392,26,NULL,'2010-11-09 03:04:34','2010-11-09 06:08:34',1,1),
  (393,26,NULL,'2010-11-11 03:04:34','2010-11-11 06:08:34',1,1),
  (394,26,NULL,'2010-11-13 03:04:34','2010-11-13 06:08:34',1,1),
  (395,26,NULL,'2010-11-15 03:04:34','2010-11-15 06:08:34',1,1),
  (396,26,NULL,'2010-11-17 03:04:34','2010-11-17 06:08:34',1,1),
  (397,26,NULL,'2010-11-19 03:04:34','2010-11-19 06:08:34',1,1),
  (398,26,NULL,'2010-11-21 03:04:34','2010-11-21 06:08:34',1,1),
  (399,26,NULL,'2010-11-23 03:04:34','2010-11-23 06:08:34',1,1),
  (400,26,NULL,'2010-11-25 03:04:34','2010-11-25 06:08:34',1,1),
  (401,26,NULL,'2010-11-27 03:04:34','2010-11-27 06:08:34',1,1),
  (402,26,NULL,'2010-11-29 03:04:34','2010-11-29 06:08:34',1,1),
  (403,26,NULL,'2010-12-01 03:04:34','2010-12-01 06:08:34',1,1),
  (404,26,NULL,'2010-12-03 03:04:34','2010-12-03 06:08:34',1,1),
  (405,43,NULL,'2010-11-03 03:00:50','2010-11-03 06:00:50',1,1),
  (406,43,NULL,'2010-11-04 03:00:50','2010-11-04 06:00:50',1,1),
  (407,43,NULL,'2010-12-03 03:00:50','2010-12-03 06:00:50',1,1),
  (408,43,NULL,'2010-12-04 03:00:50','2010-12-04 06:00:50',1,1),
  (409,43,NULL,'2011-01-03 03:00:50','2011-01-03 06:00:50',1,1),
  (410,43,NULL,'2011-01-04 03:00:50','2011-01-04 06:00:50',1,1),
  (411,43,NULL,'2011-02-03 03:00:50','2011-02-03 06:00:50',1,1),
  (412,43,NULL,'2011-02-04 03:00:50','2011-02-04 06:00:50',1,1),
  (413,43,NULL,'2011-03-03 03:00:50','2011-03-03 06:00:50',1,1),
  (414,43,NULL,'2011-03-04 03:00:50','2011-03-04 06:00:50',1,1),
  (415,43,NULL,'2011-04-03 03:00:50','2011-04-03 06:00:50',1,1),
  (416,43,NULL,'2011-04-04 03:00:50','2011-04-04 06:00:50',1,1),
  (417,43,NULL,'2011-05-03 03:00:50','2011-05-03 06:00:50',1,1),
  (418,43,NULL,'2011-05-04 03:00:50','2011-05-04 06:00:50',1,1),
  (419,43,NULL,'2011-06-03 03:00:50','2011-06-03 06:00:50',1,1),
  (420,43,NULL,'2011-06-04 03:00:50','2011-06-04 06:00:50',1,1),
  (421,43,NULL,'2011-07-03 03:00:50','2011-07-03 06:00:50',1,1),
  (422,43,NULL,'2011-07-04 03:00:50','2011-07-04 06:00:50',1,1),
  (423,43,NULL,'2011-08-03 03:00:50','2011-08-03 06:00:50',1,1),
  (424,43,NULL,'2011-08-04 03:00:50','2011-08-04 06:00:50',1,1),
  (425,44,NULL,'2010-11-03 04:00:50','2010-11-03 08:00:50',1,1),
  (426,44,NULL,'2010-11-04 04:00:50','2010-11-04 08:00:50',1,1),
  (427,44,NULL,'2010-12-03 04:00:50','2010-12-03 08:00:50',1,1),
  (428,44,NULL,'2010-12-04 04:00:50','2010-12-04 08:00:50',1,1),
  (429,44,NULL,'2011-01-03 04:00:50','2011-01-03 08:00:50',1,1),
  (430,44,NULL,'2011-01-04 04:00:50','2011-01-04 08:00:50',1,1),
  (431,44,NULL,'2011-02-03 04:00:50','2011-02-03 08:00:50',1,1),
  (432,44,NULL,'2011-02-04 04:00:50','2011-02-04 08:00:50',1,1),
  (433,44,NULL,'2011-03-03 04:00:50','2011-03-03 08:00:50',1,1),
  (434,44,NULL,'2011-03-04 04:00:50','2011-03-04 08:00:50',1,1),
  (435,44,NULL,'2011-04-03 04:00:50','2011-04-03 08:00:50',1,1),
  (436,44,NULL,'2011-04-04 04:00:50','2011-04-04 08:00:50',1,1),
  (437,44,NULL,'2011-05-03 04:00:50','2011-05-03 08:00:50',1,1),
  (438,44,NULL,'2011-05-04 04:00:50','2011-05-04 08:00:50',1,1),
  (439,44,NULL,'2011-06-03 04:00:50','2011-06-03 08:00:50',1,1),
  (440,44,NULL,'2011-06-04 04:00:50','2011-06-04 08:00:50',1,1),
  (441,44,NULL,'2011-07-03 04:00:50','2011-07-03 08:00:50',1,1),
  (442,44,NULL,'2011-07-04 04:00:50','2011-07-04 08:00:50',1,1),
  (443,44,NULL,'2011-08-03 04:00:50','2011-08-03 08:00:50',1,1),
  (444,44,NULL,'2011-08-04 04:00:50','2011-08-04 08:00:50',1,1);
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
# Data for the `car_history` table  (LIMIT 0,500)
#

INSERT INTO `car_history` (`id`, `car_number`, `id_location`, `id_train`, `id_road`, `id_repair`, `date`) VALUES 
  (98,11111111,2,385,NULL,NULL,'2010-10-26');
COMMIT;

#
# Data for the `road_det` table  (LIMIT 0,500)
#

INSERT INTO `road_det` (`id`, `id_road`, `id_train`, `car_number`) VALUES 
  (49,2,385,NULL);
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
  (43,3),
  (43,4),
  (44,3),
  (44,4);
COMMIT;

#
# Data for the `train_det` table  (LIMIT 0,500)
#

INSERT INTO `train_det` (`id_train`, `car_number`, `car_number_in_train`) VALUES 
  (385,11111111,0);
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;