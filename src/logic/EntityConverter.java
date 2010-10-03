package logic;

import logic.model.CarEntity;
import logic.model.CarLocationEntity;
import logic.model.CarTypeEntity;
import rzd.model.objects.Car;
import rzd.model.objects.CarLocation;
import rzd.model.objects.CarType;

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

}
