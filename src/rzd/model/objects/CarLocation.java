package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 14.09.2010
 * Time: 0:40:21
 * To change this template use File | Settings | File Templates.
 */
public class CarLocation {
    private int idLocation;
    private String location;

    public CarLocation(int idLocation, String location) {
        this.idLocation = idLocation;
        this.location = location;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof CarLocation)) {
            return false;
        }
        if (idLocation == ((CarLocation) o).getIdLocation()) {
            return true;
        } else {
            return false;
        }
    }
}
