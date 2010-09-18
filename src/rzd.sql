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
# Structure for the `location_other` table : 
#

CREATE TABLE `location_other` (
  `id_otherlocation` int(11) NOT NULL AUTO_INCREMENT,
  `parking` varchar(2000) NOT NULL,
  PRIMARY KEY (`id_otherlocation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `road_type` table : 
#

CREATE TABLE `road_type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Structure for the `road` table : 
#

CREATE TABLE `road` (
  `id_road` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `id_type` int(11) NOT NULL,
  `comments` varchar(2000) DEFAULT ' ',
  `position` int(11) DEFAULT NULL COMMENT '������� ����. ��� ����������',
  PRIMARY KEY (`id_road`),
  KEY `id_type` (`id_type`),
  CONSTRAINT `road_fk` FOREIGN KEY (`id_type`) REFERENCES `road_type` (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

#
# Structure for the `location` table : 
#

CREATE TABLE `location` (
  `id_location` int(11) NOT NULL AUTO_INCREMENT,
  `id_road` int(11) DEFAULT NULL,
  `id_otherlocation` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_location`),
  KEY `id_road` (`id_road`),
  KEY `id_otherlocation` (`id_otherlocation`),
  CONSTRAINT `location_fk1` FOREIGN KEY (`id_otherlocation`) REFERENCES `location_other` (`id_otherlocation`),
  CONSTRAINT `location_fk` FOREIGN KEY (`id_road`) REFERENCES `road` (`id_road`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=cp1251;

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
# Structure for the `route_schedule` table : 
#

CREATE TABLE `route_schedule` (
  `id_schedule` int(11) NOT NULL AUTO_INCREMENT,
  `id_route` int(11) NOT NULL COMMENT '������ �� �������',
  `time_departure` time NOT NULL COMMENT '����� ����������� ������',
  `time_destination` time NOT NULL COMMENT '����� ��������',
  `date_begin` date NOT NULL COMMENT '������ �������� ����������',
  `day_move` int(11) NOT NULL COMMENT '��� � ����',
  `day_stop` int(11) NOT NULL COMMENT '��� ������� �� �������',
  PRIMARY KEY (`id_schedule`),
  KEY `id_route` (`id_route`),
  CONSTRAINT `route_schedule_fk` FOREIGN KEY (`id_route`) REFERENCES `route` (`id_route`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `train` table : 
#

CREATE TABLE `train` (
  `id_train` int(11) NOT NULL AUTO_INCREMENT COMMENT '�������������',
  `id_schedule` int(11) NOT NULL COMMENT '���������� ������ (� ���������� �������)',
  `id_location` int(11) NOT NULL COMMENT '�������������� ������ (location)',
  `train_chief` varchar(100) DEFAULT NULL COMMENT '��������� ������',
  `dt_departure` datetime NOT NULL COMMENT '����� � ���� ����������� ������',
  `dt_destination` datetime NOT NULL COMMENT '���� � ����� �������� ������',
  PRIMARY KEY (`id_train`),
  KEY `id_schedule` (`id_schedule`),
  KEY `id_location` (`id_location`),
  CONSTRAINT `train_fk` FOREIGN KEY (`id_schedule`) REFERENCES `route_schedule` (`id_schedule`),
  CONSTRAINT `train_fk1` FOREIGN KEY (`id_location`) REFERENCES `location` (`id_location`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `car` table : 
#

CREATE TABLE `car` (
  `number` int(11) NOT NULL COMMENT '���������� ����� ������',
  `id_location` int(11) NOT NULL COMMENT '������ �� ����� ���������� ������\r\n����� ��������� �� ���� ��� ��� �� ��� � ����������� �� ���� type_location',
  `id_train` int(11) DEFAULT NULL COMMENT '�����, � ������ �������� ������ �����',
  `date_update` datetime NOT NULL COMMENT '���� ���������� ������',
  PRIMARY KEY (`number`),
  KEY `id_train` (`id_train`),
  KEY `id_location` (`id_location`),
  CONSTRAINT `car_fk` FOREIGN KEY (`id_location`) REFERENCES `location` (`id_location`),
  CONSTRAINT `car_fk1` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='type_location -1 - � ������� ������ 0 - �� ���� 1 - ������';

#
# Structure for the `car_location_history` table : 
#

CREATE TABLE `car_location_history` (
  `id_history` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `id_location` int(11) NOT NULL,
  `id_train` int(11) DEFAULT NULL,
  `ddate` datetime NOT NULL COMMENT '���� ������� ��������������',
  PRIMARY KEY (`id_history`),
  KEY `number` (`number`),
  KEY `id_location` (`id_location`),
  KEY `id_train` (`id_train`),
  CONSTRAINT `car_location_history_fk` FOREIGN KEY (`number`) REFERENCES `car` (`number`),
  CONSTRAINT `car_location_history_fk1` FOREIGN KEY (`id_location`) REFERENCES `location` (`id_location`),
  CONSTRAINT `car_location_history_fk2` FOREIGN KEY (`id_train`) REFERENCES `train` (`id_train`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Data for the `road_type` table  (LIMIT 0,500)
#

INSERT INTO `road_type` (`id_type`, `name`) VALUES 
  (1,'����������������� ����'),
  (2,'���� ������ �������'),
  (3,'�������� ����'),
  (4,'��������-������������� ����'),
  (5,'��������-����������� ����');
COMMIT;

#
# Data for the `road` table  (LIMIT 0,500)
#

INSERT INTO `road` (`id_road`, `name`, `id_type`, `comments`, `position`) VALUES 
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
# Data for the `location` table  (LIMIT 0,500)
#

INSERT INTO `location` (`id_location`, `id_road`, `id_otherlocation`) VALUES 
  (9,NULL,NULL);
COMMIT;

#
# Data for the `route` table  (LIMIT 0,500)
#

INSERT INTO `route` (`id_route`, `number`, `point_departure`, `point_destination`) VALUES 
  (7,'fff','Kursk','Kiev');
COMMIT;

#
# Data for the `car` table  (LIMIT 0,500)
#

INSERT INTO `car` (`number`, `id_location`, `id_train`, `date_update`) VALUES 
  (111,9,NULL,'2010-09-13 22:05:20');
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;