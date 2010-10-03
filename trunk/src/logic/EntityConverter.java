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

    public static Car convertCarEntity(CarEntity ce) throws HibernateConvertExcpetion {
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

    public static CarType convertCarTypeEntity(CarTypeEntity cte) throws HibernateConvertExcpetion {
        try {
            return new CarType(cte.getIdType(), cte.getcType());
        } catch (Exception e) {
            throw new HibernateConvertExcpetion(e);
        }
    }

}
