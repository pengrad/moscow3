package rzd.model.objects;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 17.10.2010
 * Time: 2:20:46
 * To change this template use File | Settings | File Templates.
 */
public class CarHistory {
    private Date date;
    private CarLocation carLocation;
    private Train train;
    private Road road;
    private Repair repair;

    public CarHistory(Date date, CarLocation carLocation, Train train, Road road, Repair repair) {
        this.date = date;
        this.carLocation = carLocation;
        this.train = train;
        this.road = road;
        this.repair = repair;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CarLocation getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(CarLocation carLocation) {
        this.carLocation = carLocation;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
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
