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
# Structure for the `location` table : 
#

CREATE TABLE `location` (
  `id_location` int(11) NOT NULL,
  `c_location` varchar(100) NOT NULL,
  PRIMARY KEY (`id_location`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `car` table : 
#

CREATE TABLE `car` (
  `car_number` int(11) NOT NULL AUTO_INCREMENT,
  `id_location` int(11) NOT NULL,
  PRIMARY KEY (`car_number`),
  KEY `id_location` (`id_location`),
  CONSTRAINT `car_fk` FOREIGN KEY (`id_location`) REFERENCES `location` (`id_location`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

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
  `time_in_way` time NOT NULL,
  `id_shedule_type` int(11) NOT NULL,
  PRIMARY KEY (`id_shedule`),
  KEY `id_shedule_type` (`id_shedule_type`),
  CONSTRAINT `id_shedule_type` FOREIGN KEY (`id_shedule_type`) REFERENCES `shedule_type` (`id_shedule_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=cp1251;

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
  `train_chief` varchar(100) NOT NULL,
  `date_from` datetime NOT NULL,
  `date_to` datetime NOT NULL,
  `id_status` int(11) NOT NULL,
  PRIMARY KEY (`id_train`),
  KEY `id_shedule` (`id_shedule`),
  KEY `id_status` (`id_status`),
  CONSTRAINT `train_fk1` FOREIGN KEY (`id_status`) REFERENCES `train_status` (`id_status`),
  CONSTRAINT `train_fk` FOREIGN KEY (`id_shedule`) REFERENCES `shedule` (`id_shedule`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=cp1251;

#
# Structure for the `car_history` table : 
#

CREATE TABLE `car_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_number` int(11) NOT NULL,
  `id_train` int(11) DEFAULT NULL,
  `id_road` int(11) DEFAULT NULL,
  `id_location` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_location` (`id_location`),
  KEY `id_train` (`id_train`),
  KEY `id_road` (`id_road`),
  KEY `car_number` (`car_number`),
  CONSTRAINT `car_history_fk3` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`),
  CONSTRAINT `car_history_fk` FOREIGN KEY (`id_location`) REFERENCES `location` (`id_location`),
  CONSTRAINT `car_history_fk1` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`),
  CONSTRAINT `car_history_fk2` FOREIGN KEY (`id_road`) REFERENCES `road` (`id_road`)
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
  CONSTRAINT `road_det_fk2` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`),
  CONSTRAINT `road_det_fk` FOREIGN KEY (`id_road`) REFERENCES `road` (`id_road`),
  CONSTRAINT `road_det_fk1` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Structure for the `route` table : 
#

CREATE TABLE `route` (
  `id_route` int(11) NOT NULL AUTO_INCREMENT,
  `route_number` varchar(20) NOT NULL,
  `city_from` int(11) NOT NULL,
  `city_to` int(11) NOT NULL,
  `shedule_forward` int(11) NOT NULL,
  `shedule_back` int(11) NOT NULL,
  PRIMARY KEY (`id_route`),
  KEY `shedule_forward` (`shedule_forward`),
  KEY `shedule_back` (`shedule_back`),
  CONSTRAINT `shedule_back` FOREIGN KEY (`shedule_back`) REFERENCES `shedule` (`id_shedule`),
  CONSTRAINT `shedule_forward` FOREIGN KEY (`shedule_forward`) REFERENCES `shedule` (`id_shedule`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

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
  CONSTRAINT `train_det_fk1` FOREIGN KEY (`car_number`) REFERENCES `car` (`car_number`),
  CONSTRAINT `train_det_fk` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Data for the `location` table  (LIMIT 0,500)
#

INSERT INTO `location` (`id_location`, `c_location`) VALUES 
  (1,'asd');
COMMIT;

#
# Data for the `car` table  (LIMIT 0,500)
#

INSERT INTO `car` (`car_number`, `id_location`) VALUES 
  (1,1);
COMMIT;

#
# Data for the `train_status` table  (LIMIT 0,500)
#

INSERT INTO `train_status` (`id_status`, `c_status`) VALUES 
  (1,'asdf');
COMMIT;

#
# Data for the `shedule_type` table  (LIMIT 0,500)
#

INSERT INTO `shedule_type` (`id_shedule_type`, `c_shedule_type`) VALUES 
  (1,'qaqqa');
COMMIT;

#
# Data for the `shedule` table  (LIMIT 0,500)
#

INSERT INTO `shedule` (`id_shedule`, `time_from`, `time_to`, `time_in_way`, `id_shedule_type`) VALUES 
  (2,'12:12:12','12:12:12','12:12:12',1);
COMMIT;

#
# Data for the `train` table  (LIMIT 0,500)
#

INSERT INTO `train` (`id_train`, `id_shedule`, `train_chief`, `date_from`, `date_to`, `id_status`) VALUES 
  (4,2,'12','1222-11-11 11:11:11','1111-11-11 11:11:11',1);
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
  (2,'1 тупик',1,NULL,NULL),
  (3,'10 (2 п. Юго корпуса)',4,NULL,NULL),
  (4,'11 (1 п. Юго корпуса)',4,NULL,NULL),
  (5,'11 (угольный склад)',5,NULL,NULL),
  (6,'13 (аккомуляторная)',4,NULL,NULL),
  (7,'2 (2 п. 11-го корпуса)',4,NULL,NULL),
  (8,'2 РЭД',4,NULL,NULL),
  (9,'2 тупик',1,NULL,NULL),
  (10,'3 (1 п. 11-го корпуса)',4,NULL,NULL),
  (11,'3 РЭД',4,NULL,NULL),
  (12,'3 тупик (408)',4,NULL,NULL),
  (13,'31',1,NULL,NULL),
  (14,'32',1,NULL,NULL),
  (15,'33',1,NULL,NULL),
  (16,'34',1,NULL,NULL),
  (17,'35',1,NULL,NULL),
  (18,'36',1,NULL,NULL),
  (19,'38 (408)',2,NULL,NULL),
  (20,'39 (409)',2,NULL,NULL),
  (21,'4 РЭД',4,NULL,NULL),
  (22,'4 тупик (409)',4,NULL,NULL),
  (23,'40 (409)',2,NULL,NULL),
  (24,'41',2,NULL,NULL),
  (25,'437 (заводской)',3,NULL,NULL),
  (26,'5',5,NULL,NULL),
  (27,'5 (объездной)',3,NULL,NULL),
  (28,'6-я строительная (438)',2,NULL,NULL),
  (29,'7',5,NULL,NULL),
  (30,'7-я строительная (439)',2,NULL,NULL),
  (31,'8 (8-я канава)',4,NULL,NULL),
  (32,'8 (вытежной)',3,NULL,NULL),
  (33,'9 (3 п. Юго корпуса)',4,NULL,NULL),
  (34,'9 (газовый тупик)',5,NULL,NULL);
COMMIT;

#
# Data for the `shedule_days` table  (LIMIT 0,500)
#

INSERT INTO `shedule_days` (`id_shedule`, `day`) VALUES 
  (2,11),
  (2,12);
COMMIT;

#
# Data for the `train_det` table  (LIMIT 0,500)
#

INSERT INTO `train_det` (`id_train`, `car_number`) VALUES 
  (4,1);
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;