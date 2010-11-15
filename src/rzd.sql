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
) ENGINE=InnoDB AUTO_INCREMENT=1025 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=cp1251;

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
# Structure for the `log` table : 
#

CREATE TABLE `log` (
  `id_log` int(11) NOT NULL AUTO_INCREMENT,
  `ddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_log`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=cp1251;

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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=cp1251;

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
# Definition for the `sync` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `sync`()
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN


/*
1) все сформированные поезда к отправке с нашей станции, у которых
время отправления < текущего переводим в статус "В пути" и освобождаем ROAD.

2) все поезда "В пути", отправленные от нашей станции, у которых
время прибытия < текущего переводим в статус "Расформирован".
   Все вагоны из этого поезда переводим в ближайший запланированный к отправке.
 Если в ближайшем запланированном к отправке уже есть вагоны - ставим
 вагоны в статус "неизвестно".

3) все запланированные поезда к отправке из станции назначения, у которых
время отправления < текущего переводим в статус "В Пути".
*/

declare id int default 0;
declare min_id int default 0;
declare done int default 0;

Declare cur Cursor for Select idTrain from ids;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;

start transaction;

create temporary table ids (idTrain int);

set @t = CURRENT_TIMESTAMP;

/* 1 */
insert ids
select distinct t.`id_train`
from `train` t
	join `shedule` s on t.`id_shedule` = s.`id_shedule`
where s.`id_shedule` in (select r.`shedule_forward` from route r)
	and t.`id_status` = 2 and t.`date_from` < @t;

update train t
set t.`id_status` = 3
where t.`id_train` in (select idTrain from ids);

update car
set id_location = 2 
where car_number in (select car_number from train_det where id_train in
	(select idTrain from ids));

update road_det 
set `road_det`.`id_train` = null
where road_det.`id_train` in (select idTrain from ids);	
        
delete from road_det
where road_det.`id_train` is null and road_det.`car_number` is null;

delete from ids;

/* 2 */

insert ids
select distinct t.`id_train`
from `train` t
	join `shedule` s on t.`id_shedule` = s.`id_shedule`    
where s.`id_shedule` in (select r.`shedule_forward` from route r)
	and t.`id_status` = 3 and t.`date_to` < @t;
    
update train
set train.`id_status` = 5
where train.`id_train` in (select idTrain from ids);


Open cur;
WHILE done = 0 DO
	FETCH cur INTO id;
    
    /* ближайший поезд */
    set min_id := (
    	select min(t.`id_train`) from train t
			join `shedule` s on t.`id_shedule` = s.`id_shedule`
	    	join `route` r on r.`shedule_back` = s.`id_shedule`
    		join `train` tt on r.`shedule_forward` = tt.`id_shedule` 
            	and tt.`id_train` = id
		where t.`id_status` = 1 and t.`date_from` > tt.`date_to`
        );
    
	/* у ближайшего запланированного поезда уже есть вагоны */
    if exists (select * from train_det where id_train = min_id) then
    	
        /* ставим наши в вагоны в статус "неизвестно" */
    	update car
    	set id_location = 1 
    	where car_number in (select car_number from train_det where id_train = id);
    
    /* у поезда нет вагонов, закидываем наши вагоны в него */
	else 
		insert train_det(id_train, car_number, car_number_in_train)
    	select min_id, car_number, car_number_in_train from train_det 
        where id_train = id;
        
        insert `car_history`(`car_number`, `id_location`, `id_train`, `date`) 
		select car_number, 2, id_train, @t from train_det 
        where id_train = min_id;
        
    end if;
    
  
END WHILE;

Close cur; 

delete from ids;


/* 3 */

insert ids
select distinct t.`id_train`
from `train` t
	join `shedule` s on t.`id_shedule` = s.`id_shedule`
where s.`id_shedule` in (select r.`shedule_back` from route r)
	and t.`id_status` = 1 and t.`date_from` < @t;

update train t
set t.`id_status` = 3
where t.`id_train` in (select idTrain from ids);

update car
set id_location = 2 
where car_number in (select car_number from train_det where id_train in
	(select idTrain from ids));

/*insert `log` (comment) values('OK');*/

drop table ids;

commit;

END;

#
# Definition for the `sync` Event : 
#

CREATE EVENT `sync`
  ON SCHEDULE EVERY 1 MINUTE STARTS '2010-10-31 21:36:02'
  ON COMPLETION NOT PRESERVE
  DISABLE
  COMMENT ''  DO
call `sync`;

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
  (11111111,1,10,'','','','','','','',1,11,11,11,11),
  (22222222,4,10,'Модель 2784','','','','','','',0,1,11,1,1),
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
  (25,'08:50:00','18:56:00',10,6,3),
  (26,'03:04:00','09:08:00',6,4,4),
  (43,'16:00:00','14:00:00',46,0,3),
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
  (514,26,'Иванов','2011-02-01 03:04:34','2011-02-01 06:08:34',5,0),
  (725,25,'www','2010-10-27 21:50:48','2010-10-28 07:56:48',5,1),
  (805,25,NULL,'2010-11-01 08:50:04','2010-11-01 18:56:04',1,1),
  (806,25,NULL,'2010-11-02 08:50:04','2010-11-02 18:56:04',1,1),
  (807,25,NULL,'2010-11-03 08:50:04','2010-11-03 18:56:04',1,1),
  (808,25,NULL,'2010-11-04 08:50:04','2010-11-04 18:56:04',1,1),
  (809,25,NULL,'2010-11-05 08:50:04','2010-11-05 18:56:04',1,1),
  (810,25,NULL,'2010-11-06 08:50:04','2010-11-06 18:56:04',1,1),
  (811,25,NULL,'2010-11-07 08:50:04','2010-11-07 18:56:04',1,1),
  (812,25,NULL,'2010-11-08 08:50:04','2010-11-08 18:56:04',1,1),
  (813,25,NULL,'2010-11-09 08:50:04','2010-11-09 18:56:04',1,1),
  (814,25,NULL,'2010-11-10 08:50:04','2010-11-10 18:56:04',1,1),
  (815,25,NULL,'2010-11-11 08:50:04','2010-11-11 18:56:04',1,1),
  (816,25,NULL,'2010-11-12 08:50:04','2010-11-12 18:56:04',1,1),
  (817,25,NULL,'2010-11-13 08:50:04','2010-11-13 18:56:04',1,1),
  (818,25,NULL,'2010-11-14 08:50:04','2010-11-14 18:56:04',1,1),
  (819,25,NULL,'2010-11-15 08:50:04','2010-11-15 18:56:04',1,1),
  (820,25,NULL,'2010-11-16 08:50:04','2010-11-16 18:56:04',1,1),
  (821,25,NULL,'2010-11-17 08:50:04','2010-11-17 18:56:04',1,1),
  (822,25,NULL,'2010-11-18 08:50:04','2010-11-18 18:56:04',1,1),
  (823,25,NULL,'2010-11-19 08:50:04','2010-11-19 18:56:04',1,1),
  (824,25,NULL,'2010-11-20 08:50:04','2010-11-20 18:56:04',1,1),
  (825,26,'kljh','2010-11-01 03:04:04','2010-11-01 09:08:04',5,1),
  (826,26,NULL,'2010-11-07 03:04:04','2010-11-07 09:08:04',1,1),
  (827,26,NULL,'2010-11-13 03:04:04','2010-11-13 09:08:04',1,1),
  (828,26,NULL,'2010-12-01 03:04:04','2010-12-01 09:08:04',1,1),
  (829,26,NULL,'2010-12-07 03:04:04','2010-12-07 09:08:04',1,1),
  (830,26,NULL,'2010-12-13 03:04:04','2010-12-13 09:08:04',1,1),
  (831,26,NULL,'2011-01-01 03:04:04','2011-01-01 09:08:04',1,1),
  (832,26,NULL,'2011-01-07 03:04:04','2011-01-07 09:08:04',1,1),
  (833,26,NULL,'2011-01-13 03:04:04','2011-01-13 09:08:04',1,1),
  (834,26,NULL,'2011-02-01 03:04:04','2011-02-01 09:08:04',1,1),
  (835,26,NULL,'2011-02-07 03:04:04','2011-02-07 09:08:04',1,1),
  (836,26,NULL,'2011-02-13 03:04:04','2011-02-13 09:08:04',1,1),
  (837,26,NULL,'2011-03-01 03:04:04','2011-03-01 09:08:04',1,1),
  (838,26,NULL,'2011-03-07 03:04:04','2011-03-07 09:08:04',1,1),
  (839,26,NULL,'2011-03-13 03:04:04','2011-03-13 09:08:04',1,1),
  (840,26,NULL,'2011-04-01 03:04:04','2011-04-01 09:08:04',1,1),
  (841,26,NULL,'2011-04-07 03:04:04','2011-04-07 09:08:04',1,1),
  (842,26,NULL,'2011-04-13 03:04:04','2011-04-13 09:08:04',1,1),
  (843,26,NULL,'2011-05-01 03:04:04','2011-05-01 09:08:04',1,1),
  (844,26,NULL,'2011-05-07 03:04:04','2011-05-07 09:08:04',1,1),
  (965,26,NULL,'2011-05-13 03:04:04','2011-05-13 09:08:04',1,1),
  (966,26,NULL,'2011-06-01 03:04:04','2011-06-01 09:08:04',1,1),
  (967,26,NULL,'2011-06-07 03:04:04','2011-06-07 09:08:04',1,1),
  (968,26,NULL,'2011-06-13 03:04:04','2011-06-13 09:08:04',1,1),
  (969,26,NULL,'2011-07-01 03:04:04','2011-07-01 09:08:04',1,1),
  (970,26,NULL,'2011-07-07 03:04:04','2011-07-07 09:08:04',1,1),
  (971,26,NULL,'2011-07-13 03:04:04','2011-07-13 09:08:04',1,1),
  (972,26,NULL,'2011-08-01 03:04:04','2011-08-01 09:08:04',1,1),
  (973,26,NULL,'2011-08-07 03:04:04','2011-08-07 09:08:04',1,1),
  (974,26,NULL,'2011-08-13 03:04:04','2011-08-13 09:08:04',1,1),
  (975,43,'QQQQ','2010-10-31 10:12:12','2010-10-31 23:00:00',5,1),
  (976,43,'WWWW','2010-10-31 20:00:00','2010-10-31 21:00:00',5,1),
  (977,43,NULL,'2010-11-03 16:00:01','2010-11-05 14:00:01',1,1),
  (978,43,NULL,'2010-11-04 16:00:01','2010-11-06 14:00:01',1,1),
  (979,43,NULL,'2010-11-05 16:00:01','2010-11-07 14:00:01',1,1),
  (980,43,NULL,'2010-11-06 16:00:01','2010-11-08 14:00:01',1,1),
  (981,43,NULL,'2010-11-07 16:00:01','2010-11-09 14:00:01',1,1),
  (982,43,NULL,'2010-11-08 16:00:01','2010-11-10 14:00:01',1,1),
  (983,43,NULL,'2010-11-09 16:00:01','2010-11-11 14:00:01',1,1),
  (984,43,NULL,'2010-11-10 16:00:01','2010-11-12 14:00:01',1,1),
  (985,43,NULL,'2010-11-11 16:00:01','2010-11-13 14:00:01',1,1),
  (986,43,NULL,'2010-11-12 16:00:01','2010-11-14 14:00:01',1,1),
  (987,43,NULL,'2010-11-13 16:00:01','2010-11-15 14:00:01',1,1),
  (988,43,NULL,'2010-11-14 16:00:01','2010-11-16 14:00:01',1,1),
  (989,43,NULL,'2010-11-15 16:00:01','2010-11-17 14:00:01',1,1),
  (990,43,NULL,'2010-11-16 16:00:01','2010-11-18 14:00:01',1,1),
  (991,43,NULL,'2010-11-17 16:00:01','2010-11-19 14:00:01',1,1),
  (992,43,NULL,'2010-11-18 16:00:01','2010-11-20 14:00:01',1,1),
  (993,43,NULL,'2010-11-19 16:00:01','2010-11-21 14:00:01',1,1),
  (994,43,NULL,'2010-11-20 16:00:01','2010-11-22 14:00:01',1,1),
  (995,44,NULL,'2010-11-01 04:00:01','2010-11-01 12:00:01',1,1),
  (996,44,NULL,'2010-11-02 04:00:01','2010-11-02 12:00:01',1,1),
  (997,44,NULL,'2010-11-03 04:00:01','2010-11-03 12:00:01',1,1),
  (998,44,NULL,'2010-11-04 04:00:01','2010-11-04 12:00:01',1,1),
  (999,44,NULL,'2010-11-05 04:00:01','2010-11-05 12:00:01',1,1),
  (1000,44,NULL,'2010-11-06 04:00:01','2010-11-06 12:00:01',1,1),
  (1001,44,NULL,'2010-11-07 04:00:01','2010-11-07 12:00:01',1,1),
  (1002,44,NULL,'2010-11-08 04:00:01','2010-11-08 12:00:01',1,1),
  (1003,44,NULL,'2010-11-09 04:00:01','2010-11-09 12:00:01',1,1),
  (1004,44,NULL,'2010-11-10 04:00:01','2010-11-10 12:00:01',1,1),
  (1005,44,NULL,'2010-11-11 04:00:01','2010-11-11 12:00:01',1,1),
  (1006,44,NULL,'2010-11-12 04:00:01','2010-11-12 12:00:01',1,1),
  (1007,44,NULL,'2010-11-13 04:00:01','2010-11-13 12:00:01',1,1),
  (1008,44,NULL,'2010-11-14 04:00:01','2010-11-14 12:00:01',1,1),
  (1009,44,NULL,'2010-11-15 04:00:01','2010-11-15 12:00:01',1,1),
  (1010,44,NULL,'2010-11-16 04:00:01','2010-11-16 12:00:01',1,1),
  (1011,44,NULL,'2010-11-17 04:00:01','2010-11-17 12:00:01',1,1),
  (1012,44,NULL,'2010-11-18 04:00:01','2010-11-18 12:00:01',1,1),
  (1013,44,NULL,'2010-11-19 04:00:01','2010-11-19 12:00:01',1,1),
  (1014,44,NULL,'2010-11-20 04:00:01','2010-11-20 12:00:01',1,1),
  (1015,43,NULL,'2010-11-21 16:00:01','2010-11-23 14:00:01',1,1),
  (1016,43,NULL,'2010-11-22 16:00:01','2010-11-24 14:00:01',1,1),
  (1017,43,NULL,'2010-11-23 16:00:01','2010-11-25 14:00:01',1,1),
  (1018,43,NULL,'2010-11-24 16:00:01','2010-11-26 14:00:01',1,1),
  (1019,43,NULL,'2010-11-25 16:00:01','2010-11-27 14:00:01',1,1),
  (1020,43,NULL,'2010-11-26 16:00:01','2010-11-28 14:00:01',1,1),
  (1021,43,NULL,'2010-11-27 16:00:01','2010-11-29 14:00:01',1,1),
  (1022,43,NULL,'2010-11-28 16:00:01','2010-11-30 14:00:01',1,1),
  (1023,43,NULL,'2010-11-29 16:00:01','2010-12-01 14:00:01',1,1),
  (1024,43,NULL,'2010-11-30 16:00:01','2010-12-02 14:00:01',1,1);
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
  (115,44444444,2,514,NULL,NULL,'2010-10-27 00:00:00'),
  (116,33333333,2,975,NULL,NULL,'2010-10-31 23:19:18'),
  (117,33333333,2,995,NULL,NULL,'2010-10-31 23:56:02'),
  (118,44444444,2,976,NULL,NULL,'2010-11-01 00:01:16');
COMMIT;

#
# Data for the `log` table  (LIMIT 0,500)
#

INSERT INTO `log` (`id_log`, `ddate`, `comment`) VALUES 
  (3,'2010-10-31 21:40:02','OK'),
  (4,'2010-10-31 21:41:02','OK'),
  (5,'2010-10-31 21:42:02','OK'),
  (6,'2010-10-31 21:43:02','OK'),
  (7,'2010-10-31 21:44:02','OK'),
  (8,'2010-10-31 21:45:02','OK'),
  (9,'2010-10-31 21:46:02','OK'),
  (10,'2010-10-31 21:47:02','OK'),
  (11,'2010-10-31 21:48:02','OK'),
  (12,'2010-10-31 21:49:02','OK'),
  (13,'2010-10-31 21:50:02','OK'),
  (14,'2010-10-31 21:51:02','OK'),
  (15,'2010-10-31 21:52:02','OK'),
  (16,'2010-10-31 21:53:02','OK'),
  (17,'2010-10-31 21:54:02','OK'),
  (18,'2010-10-31 21:55:02','OK'),
  (19,'2010-10-31 21:56:02','OK'),
  (20,'2010-10-31 21:56:13','OK');
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
  (514,44444444,2),
  (975,33333333,0),
  (976,44444444,0),
  (995,33333333,0);
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;