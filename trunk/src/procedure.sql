CREATE DEFINER = 'root'@'localhost' PROCEDURE `test`()
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
*/

start transaction;

create temporary table ids (idTrain int);

set @t = CURRENT_TIMESTAMP;

insert ids
select distinct t.`id_train`
from `train` t
	join `shedule` s on t.`id_shedule` = s.`id_shedule`
where s.`id_shedule` in (select r.`shedule_forward` from route r)
	and t.`id_status` = 2 and t.`date_from` < @t;

update train t
set t.`id_status` = 3
where t.`id_train` in (select idTrain from ids);

update road_det 
set `road_det`.`id_train` = null
where road_det.`id_train` in (select idTrain from ids)
	and road_det.`car_number` is not null;
        
delete from road_det
where road_det.`id_train` is null and road_det.`car_number` is null;

delete from ids;

insert ids
select distinct t.`id_train`
from `train` t
	join `shedule` s on t.`id_shedule` = s.`id_shedule`    
where s.`id_shedule` in (select r.`shedule_forward` from route r)
	and t.`id_status` = 3 and t.`date_to` < @t;
    
update train
set train.`id_status` = 5
where train.`id_train` in (select idTrain from ids);

commit;

END;