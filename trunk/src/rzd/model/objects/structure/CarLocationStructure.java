package rzd.model.objects.structure;

import rzd.model.objects.Car;
import rzd.model.objects.Repair;
import rzd.model.objects.Road;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 15.10.2010
 * Time: 0:43:40
 * To change this template use File | Settings | File Templates.
 */
public class CarLocationStructure {
    private Car car;
    private Road road;
    private Repair repair;

    public CarLocationStructure(Car car, Road road, Repair repair) {
        this.car = car;
        this.road = road;
        this.repair = repair;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
}
