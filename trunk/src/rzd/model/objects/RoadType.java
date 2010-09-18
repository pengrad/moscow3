package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 14.09.2010
 * Time: 0:19:59
 * To change this template use File | Settings | File Templates.
 */
public class RoadType {

    private int id;
    private String name;

    public RoadType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof RoadType) {
            if (id == ((RoadType) o).getId()) {
                return true;
            }
        }
        return false;
    }
}
