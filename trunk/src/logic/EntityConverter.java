package logic;

import logic.model.*;
import rzd.model.objects.*;

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
            CarTypeEntity cte = ce.getCarType();
            CarType ct = new CarType(cte.getIdType(), cte.getcType());
            return new Car(ce.getCarNumber(), ce.getModel(), cl, ct, ce.getConditioner(), ce.getGenerator(),
                    ce.getGeneratorPrivod(), ce.getAccumulator(), ce.getElectricDevice(), ce.getBodyColor(),
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
            RepairEntity repair = new RepairEntity();
            return repair;
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

}
