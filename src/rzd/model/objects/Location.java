package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 14.09.2010
 * Time: 0:40:21
 * To change this template use File | Settings | File Templates.
 */
public class Location {
    private int id;
    private Road road;
    private LocationAnother otherLocation;

    public Location(int id, Road road, LocationAnother otherLocation) {
        this.id = id;
        this.road = road;
        this.otherLocation = otherLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public LocationAnother getOtherLocation() {
        return otherLocation;
    }

    public void setOtherLocation(LocationAnother otherLocation) {
        this.otherLocation = otherLocation;
    }
}
