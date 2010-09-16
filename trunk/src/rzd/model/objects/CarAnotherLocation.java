package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 14.09.2010
 * Time: 0:42:18
 * To change this template use File | Settings | File Templates.
 */
public class CarAnotherLocation {
    private int id;
    private String parking;

    public CarAnotherLocation(int id, String parking) {
        this.id = id;
        this.parking = parking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}
