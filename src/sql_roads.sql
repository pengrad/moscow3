-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51a-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema rzd
--

CREATE DATABASE IF NOT EXISTS rzd;
USE rzd;

--
-- Definition of table `cars`
--

DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars` (
  `number` int(11) NOT NULL COMMENT 'Уникальный номер вагона',
  `id_location` int(11) NOT NULL COMMENT 'Ссылка на место нахождения вагона\r\nМожет ссылаться на путь или что то еще в зависимости от поля type_location',
  `type_location` int(11) NOT NULL COMMENT 'Тип месттоположения вагона, в зависимости от типа поле id_location может ссылаться на разные таблицы\r\n',
  `date_update_location` datetime NOT NULL COMMENT 'Дата изменения местоположения',
  `date_update` datetime NOT NULL COMMENT 'Дата обновления записи',
  KEY `namber` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='type_location -1 - в составе поезда 0 - на пути 1 - прочее';

--
-- Dumping data for table `cars`
--

/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` (`number`,`id_location`,`type_location`,`date_update_location`,`date_update`) VALUES 
 (12345678,5,0,'2010-09-11 17:02:02','0000-00-00 00:00:00'),
 (125762233,5,0,'2010-06-11 14:24:11','2010-11-12 11:11:11');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;


--
-- Definition of table `cars_another_location`
--

DROP TABLE IF EXISTS `cars_another_location`;
CREATE TABLE `cars_another_location` (
  `id` int(11) NOT NULL auto_increment,
  `parking` varchar(2000) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cars_another_location`
--

/*!40000 ALTER TABLE `cars_another_location` DISABLE KEYS */;
INSERT INTO `cars_another_location` (`id`,`parking`) VALUES 
 (1,'На ремонте'),
 (2,'Не известно');
/*!40000 ALTER TABLE `cars_another_location` ENABLE KEYS */;


--
-- Definition of table `flight_train`
--

DROP TABLE IF EXISTS `flight_train`;
CREATE TABLE `flight_train` (
  `id` int(11) NOT NULL auto_increment COMMENT 'Идентификатор',
  `id_train` int(11) NOT NULL COMMENT 'Ссылка на поезд',
  `dt_departure` datetime NOT NULL COMMENT 'Время и дата отправления поезда',
  `dt_destination` datetime NOT NULL COMMENT 'Дата и время прибытия поезда',
  PRIMARY KEY  (`id`),
  KEY `id_train` (`id_train`),
  CONSTRAINT `train_movements_fk` FOREIGN KEY (`id_train`) REFERENCES `train` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `flight_train`
--

/*!40000 ALTER TABLE `flight_train` DISABLE KEYS */;
/*!40000 ALTER TABLE `flight_train` ENABLE KEYS */;


--
-- Definition of table `r_flight_cars`
--

DROP TABLE IF EXISTS `r_flight_cars`;
CREATE TABLE `r_flight_cars` (
  `id_traintmov` int(11) NOT NULL,
  `id_cars` int(11) NOT NULL,
  `date` datetime NOT NULL,
  KEY `id_traintmov` (`id_traintmov`),
  KEY `id_cars` (`id_cars`),
  CONSTRAINT `ref_trainmov_cars_fk1` FOREIGN KEY (`id_cars`) REFERENCES `cars` (`number`),
  CONSTRAINT `ref_trainmov_cars_fk` FOREIGN KEY (`id_traintmov`) REFERENCES `road_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `r_flight_cars`
--

/*!40000 ALTER TABLE `r_flight_cars` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_flight_cars` ENABLE KEYS */;


--
-- Definition of table `road`
--

DROP TABLE IF EXISTS `road`;
CREATE TABLE `road` (
  `number` varchar(100) NOT NULL default '',
  `id_type` int(11) NOT NULL,
  `comments` varchar(2000) default ' ',
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`),
  KEY `id_type` (`id_type`),
  CONSTRAINT `road_fk` FOREIGN KEY (`id_type`) REFERENCES `road_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `road`
--

/*!40000 ALTER TABLE `road` DISABLE KEYS */;
INSERT INTO `road` (`number`,`id_type`,`comments`,`id`) VALUES 
 ('1 (3 п. 11-го корпуса)',4,'\' \'',1),
 ('1 тупик',1,'\' \'',2),
 ('10 (2 п. Юго корпуса)',4,'\' \'',3),
 ('11 (1 п. Юго корпуса)',4,'\' \'',4),
 ('11 (угольный склад)',5,'\' \'',5),
 ('13 (аккомуляторная)',4,'\' \'',6),
 ('2 (2 п. 11-го корпуса)',4,'\' \'',7),
 ('2 РЭД',4,'\' \'',8),
 ('2 тупик',1,'\' \'',9),
 ('3 (1 п. 11-го корпуса)',4,'\' \'',10),
 ('3 РЭД',4,'\' \'',11),
 ('3 тупик (408)',4,'\' \'',12),
 ('31',1,'\' \'',13),
 ('32',1,'\' \'',14),
 ('33',1,'\' \'',15),
 ('34',1,'\' \'',16),
 ('35',1,'\' \'',17),
 ('36',1,'\' \'',18),
 ('38 (408)',2,'\' \'',19),
 ('39 (409)',2,'\' \'',20),
 ('4 РЭД',4,'\' \'',21),
 ('4 тупик (409)',4,'\' \'',22),
 ('40 (409)',2,'\' \'',23),
 ('41',2,'\' \'',24),
 ('437 (заводской)',3,'\' \'',25),
 ('5',5,'\' \'',26),
 ('5 (объездной)',3,'\' \'',27),
 ('6-я строительная (438)',2,'\' \'',28),
 ('7',5,'\' \'',29),
 ('7-я строительная (439)',2,'\' \'',30),
 ('8 (8-я канава)',4,'\' \'',31),
 ('8 (вытежной)',3,'\' \'',32),
 ('9 (3 п. Юго корпуса)',4,'\' \'',33),
 ('9 (газовый тупик)',5,'\' \'',34);
/*!40000 ALTER TABLE `road` ENABLE KEYS */;


--
-- Definition of table `road_type`
--

DROP TABLE IF EXISTS `road_type`;
CREATE TABLE `road_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `road_type`
--

/*!40000 ALTER TABLE `road_type` DISABLE KEYS */;
INSERT INTO `road_type` (`id`,`name`) VALUES 
 (1,'Приемоотправочные пути'),
 (2,'Пути отстоя вагонов'),
 (3,'Вытяжные пути'),
 (4,'Ремонтно-экипировочные пути'),
 (5,'Разгрузо-погрузочные пути');
/*!40000 ALTER TABLE `road_type` ENABLE KEYS */;


--
-- Definition of table `train`
--

DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `number` int(11) NOT NULL COMMENT 'Номер поезда',
  `point_of_departure` varchar(500) NOT NULL COMMENT 'Пункт отправления',
  `point_of_destination` varchar(500) NOT NULL COMMENT 'Пункт прибытия',
  `time_departure` time NOT NULL COMMENT 'Время отправления',
  `time_destination` time NOT NULL COMMENT 'Время прибытия',
  `date_begin` date NOT NULL COMMENT 'Дата начала действия расписания',
  `cyclic_day` int(11) NOT NULL COMMENT 'Кол-во дней,через которое поезд снова отправится',
  PRIMARY KEY  (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `train`
--

/*!40000 ALTER TABLE `train` DISABLE KEYS */;
/*!40000 ALTER TABLE `train` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
