package logic;

import logic.model.*;
import rzd.model.objects.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 17:06:38
 */

public class EntityConverter {

    public static Car convertCar(CarEntity ce) throws HibernateConvertExcpetion {
        try {
            CarLocationEntity cle = ce.getCarLocation();
            CarLocation cl = new CarLocation(cle.getIdLocation(), cle.getcLocation());
            return new Car(ce.getCarNumber(), ce.getModel(), cl, convertCarType(ce.getCarType()), ce.getConditioner(),
                    ce.getGenerator(), ce.getGeneratorPrivod(), ce.getAccumulator(), ce.getElectricDevice(), ce.getBodyColor(),
                    ce.isEcologicToilet(), ce.getRunNorm(), ce.getRun(), ce.getRunTozNorm(), ce.getRunToz());
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static CarEntity convertCar(Car car) throws HibernateConvertExcpetion {
        try {
            CarTypeEntity cte = convertCarType(car.getCarType());
            CarLocationEntity cle = convertCarLocation(car.getCarLocation());
            return new CarEntity(car.getNumber(), car.getModel(), car.getConditioner(), car.getGenerator(),
                    car.getGeneratorPrivod(), car.getAccumulator(), car.getElectricDevice(), car.getBodyColor(),
                    car.isEcologicCoilet(), car.getRun(), car.getRunToz(), car.getRunNorm(), car.getRunTozNorm(),
                    cle, cte);
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static CarType convertCarType(CarTypeEntity cte) throws HibernateConvertExcpetion {
        try {
            return new CarType(cte.getIdType(), cte.getcType());
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static CarTypeEntity convertCarType(CarType ct) throws HibernateConvertExcpetion {
        try {
            CarTypeEntity cte = new CarTypeEntity();
            cte.setIdType(ct.getIdType());
            return cte;
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static CarLocationEntity convertCarLocation(CarLocation cl) throws HibernateConvertExcpetion {
        try {
            CarLocationEntity cle = new CarLocationEntity();
            cle.setIdLocation(cl.getIdLocation());
            return cle;
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static RoadEntity convertRoad(Road r) {
        try {
            RoadTypeEntity rte = new RoadTypeEntity(r.getRoadType().getId(), r.getRoadType().getName());
            return new RoadEntity(r.getName(), r.getComment(), r.getPosition(), rte);
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static Road convertRoad(RoadEntity r) {
        try {
            RoadType rt = new RoadType(r.getRoadType().getIdType(), r.getRoadType().getTypeName());
            return new Road(r.getIdRoad(), r.getRoadName(), r.getComments(), rt, r.getPosition());
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static RepairEntity convertRepair(Repair r) {
        try {
            return new RepairEntity();
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static SheduleType convertSheduleType(SheduleTypeEntity st) {
        try {
            return new SheduleType(st.getIdSheduleType(), st.getcSheduleType());
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static SheduleTypeEntity convertSheduleType(SheduleType st) {
        try {
            return new SheduleTypeEntity(st.getId(), st.getName());
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static Shedule convertShedule(SheduleEntity se) {
        try {
            int[] days = null;
            Collection<SheduleDaysEntity> sde = se.getSheduleDays();
            if (sde != null && sde.size() > 0) {
                days = new int[sde.size()];
                for (int i = 0; i < sde.size(); i++)
                    days[i] = sde.toArray(new SheduleDaysEntity[1])[i].getDay();
            }
            return new Shedule(se.getIdShedule(), se.getTimeFrom(), se.getTimeTo(), se.getHoursInWay(),
                    se.getMinutesInWay(), convertSheduleType(se.getSheduleType()), days);
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static SheduleEntity convertShedule(Shedule s, Integer idS) {
        try {
            SheduleEntity sfe = new SheduleEntity(s.getTimeDeparture(), s.getTimeDestination(), s.getHoursTimeInWay(),
                    s.getMinutesTimeInWay(), convertSheduleType(s.getSheduleType()));
            if (idS != null) sfe.setIdShedule(idS);
            int[] days = s.getDays();
            if (days != null) {
                Collection<SheduleDaysEntity> sdays = new ArrayList<SheduleDaysEntity>(days.length);
                for (int day : days) sdays.add(new SheduleDaysEntity(day, sfe));
                sfe.setSheduleDays(sdays);
            }
            return sfe;
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static Route convertRoute(RouteEntity r) {
        try {
            return new Route(r.getIdRoute(), r.getNumberForward(), r.getNumberBack(), r.getCityFrom(),
                    r.getCityTo(), convertShedule(r.getSheduleForward()), convertShedule(r.getSheduleBack()),
                    r.isEnabled(), r.getLengthForward(), r.getLengthBack());
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static RouteEntity convertRoute(Route r) {
        try {
            return convertRoute(r, null, null, null);
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static RouteEntity convertRoute(Route r, Integer idRoute, Integer idSF, Integer idSB) {
        try {
            RouteEntity re = new RouteEntity(r.getCityFrom(), r.getCityTo(), r.getNumberForward(), r.getNumberBack(),
                    r.getLengthForward(), r.getLengthBack(), convertShedule(r.getSheduleForward(), idSF),
                    convertShedule(r.getSheduleBack(), idSB), r.isEnabled());
            if (idRoute != null) re.setIdRoute(idRoute);
            return re;
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

    public static Train convertTrain(TrainEntity train) {
        try {
            TrainStatus ts = new TrainStatus(train.getTrainStatus().getIdStatus(), train.getTrainStatus().getcStatus());
            // маршрут по прибывающему расписанию (поезд прибывает на станцию)
            Collection<RouteEntity> routes = train.getShedule().getRoutesBySheduleBack();
            // если маршрута нет, поезд идет по отправляющемуся расписанию (поезд отправляется со станции)
            if (routes == null || routes.size() == 0) routes = train.getShedule().getRoutesBySheduleForward();
            // маршрут у расписания должен быть всегда один!
            RouteEntity re = (RouteEntity) routes.toArray()[0];
            Route route = EntityConverter.convertRoute(re);
            // поезд должен быть максимум на одном пути!
            Road road = null;
            if (train.getRoadDets() != null && train.getRoadDets().size() > 0) {
                road = convertRoad(((RoadDetEntity) train.getRoadDets().toArray()[0]).getRoad());
            }
            ArrayList<Car> cars = null;
            if (train.getTrainDets() != null && train.getTrainDets().size() > 0) {
                cars = new ArrayList<Car>(train.getTrainDets().size());
                for (TrainDetEntity tde : train.getTrainDets()) {
                    cars.add(convertCar(tde.getCar()));
                }
            }
            return new Train(train.getIdTrain(), train.getDateFrom(), train.getDateTo(), train.getTrainChief(),
                    convertShedule(train.getShedule()), route, ts, road, cars);
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

}
