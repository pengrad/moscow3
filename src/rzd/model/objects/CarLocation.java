package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 14.09.2010
 * Time: 0:40:21
 * To change this template use File | Settings | File Templates.
 */
public class CarLocation {
    private int id;
    private Train train;
    private Road road;
    private CarAnotherLocation otherLocation;

    public CarLocation(int id, Train train, Road road, CarAnotherLocation otherLocation) {
        this.id = id;
        this.train = train;
        this.road = road;
        this.otherLocation = otherLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CarAnotherLocation getOtherLocation() {
        return otherLocation;
    }

    public void setOtherLocation(CarAnotherLocation otherLocation) {
        this.otherLocation = otherLocation;
    }
}
