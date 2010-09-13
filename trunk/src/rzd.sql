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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Structure for the `road` table : 
#

CREATE TABLE `road` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `id_type` int(11) NOT NULL,
  `comments` varchar(2000) DEFAULT ' ',
  `position` int(11) DEFAULT NULL COMMENT '������� ����. ��� ����������',
  PRIMARY KEY (`id`),
  KEY `road_fk` (`id_type`),
  CONSTRAINT `road_fk` FOREIGN KEY (`id_type`) REFERENCES `road_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

#
# Structure for the `route` table : 
#

CREATE TABLE `route` (
  `id_route` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) NOT NULL COMMENT '����� ������ (��������)',
  `point_departure` varchar(500) NOT NULL COMMENT '����� �����������',
  `point_destination` varchar(500) NOT NULL COMMENT '����� ��������',
  PRIMARY KEY (`id_route`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Structure for the `train` table : 
#

CREATE TABLE `train` (
  `id_train` int(11) NOT NULL AUTO_INCREMENT COMMENT '�������������',
  `id_route` int(11) NOT NULL COMMENT '������ �� �������',
  `dt_departure` datetime NOT NULL COMMENT '����� � ���� ����������� ������',
  `dt_destination` datetime NOT NULL COMMENT '���� � ����� �������� ������',
  PRIMARY KEY (`id_train`),
  KEY `train_fk` (`id_route`),
  CONSTRAINT `train_fk` FOREIGN KEY (`id_route`) REFERENCES `route` (`id_route`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=cp1251;

#
# Structure for the `car` table : 
#

CREATE TABLE `car` (
  `number` int(11) NOT NULL COMMENT '���������� ����� ������',
  `id_location` int(11) NOT NULL COMMENT '������ �� ����� ���������� ������\r\n����� ��������� �� ���� ��� ��� �� ��� � ����������� �� ���� type_location',
  `date_update_location` datetime NOT NULL COMMENT '���� ��������� ��������������',
  `date_update` datetime NOT NULL COMMENT '���� ���������� ������',
  PRIMARY KEY (`number`),
  KEY `car_fk` (`id_location`),
  CONSTRAINT `car_fk` FOREIGN KEY (`id_location`) REFERENCES `car_location` (`id_location`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='type_location -1 - � ������� ������ 0 - �� ���� 1 - ������';

#
# Structure for the `route_schedule` table : 
#

CREATE TABLE `route_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_route` int(11) NOT NULL COMMENT '������ �� �������',
  `time_departure` time NOT NULL COMMENT '����� ����������� ������',
  `time_destination` time NOT NULL COMMENT '����� ��������',
  `date_begin` date NOT NULL COMMENT '������ �������� ����������',
  `day_move` int(11) NOT NULL COMMENT '��� � ����',
  `day_stop` int(11) NOT NULL COMMENT '��� ������� �� �������',
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

#
# Data for the `route` table  (LIMIT 0,500)
#

INSERT INTO `route` (`id_route`, `number`, `point_departure`, `point_destination`) VALUES 
  (7,'fff','Kursk','Kiev');
COMMIT;

#
# Data for the `train` table  (LIMIT 0,500)
#

INSERT INTO `train` (`id_train`, `id_route`, `dt_departure`, `dt_destination`) VALUES 
  (7,7,'2010-09-13 22:05:20','2010-09-13 22:05:20');
COMMIT;

#
# Data for the `road_type` table  (LIMIT 0,500)
#

INSERT INTO `road_type` (`id`, `name`) VALUES 
  (1,'����������������� ����'),
  (2,'���� ������ �������'),
  (3,'�������� ����'),
  (4,'��������-������������� ����'),
  (5,'��������-����������� ����');
COMMIT;

#
# Data for the `road` table  (LIMIT 0,500)
#

INSERT INTO `road` (`id`, `name`, `id_type`, `comments`, `position`) VALUES 
  (1,'1 (3 �. 11-�� �������)',4,NULL,NULL),
  (2,'1 �����',1,NULL,NULL),
  (3,'10 (2 �. ��� �������)',4,NULL,NULL),
  (4,'11 (1 �. ��� �������)',4,NULL,NULL),
  (5,'11 (�������� �����)',5,NULL,NULL),
  (6,'13 (��������������)',4,NULL,NULL),
  (7,'2 (2 �. 11-�� �������)',4,NULL,NULL),
  (8,'2 ���',4,NULL,NULL),
  (9,'2 �����',1,NULL,NULL),
  (10,'3 (1 �. 11-�� �������)',4,NULL,NULL),
  (11,'3 ���',4,NULL,NULL),
  (12,'3 ����� (408)',4,NULL,NULL),
  (13,'31',1,NULL,NULL),
  (14,'32',1,NULL,NULL),
  (15,'33',1,NULL,NULL),
  (16,'34',1,NULL,NULL),
  (17,'35',1,NULL,NULL),
  (18,'36',1,NULL,NULL),
  (19,'38 (408)',2,NULL,NULL),
  (20,'39 (409)',2,NULL,NULL),
  (21,'4 ���',4,NULL,NULL),
  (22,'4 ����� (409)',4,NULL,NULL),
  (23,'40 (409)',2,NULL,NULL),
  (24,'41',2,NULL,NULL),
  (25,'437 (���������)',3,NULL,NULL),
  (26,'5',5,NULL,NULL),
  (27,'5 (���������)',3,NULL,NULL),
  (28,'6-� ������������ (438)',2,NULL,NULL),
  (29,'7',5,NULL,NULL),
  (30,'7-� ������������ (439)',2,NULL,NULL),
  (31,'8 (8-� ������)',4,NULL,NULL),
  (32,'8 (��������)',3,NULL,NULL),
  (33,'9 (3 �. ��� �������)',4,NULL,NULL),
  (34,'9 (������� �����)',5,NULL,NULL);
COMMIT;

#
# Data for the `car_location` table  (LIMIT 0,500)
#

INSERT INTO `car_location` (`id_location`, `id_train`, `id_road`, `id_otherlocation`) VALUES 
  (9,NULL,NULL,NULL);
COMMIT;

#
# Data for the `car` table  (LIMIT 0,500)
#

INSERT INTO `car` (`number`, `id_location`, `date_update_location`, `date_update`) VALUES 
  (111,9,'2010-09-13 22:05:20','2010-09-13 22:05:20');
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;