package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 03.10.2010
 * Time: 16:37:27
 * To change this template use File | Settings | File Templates.
 */
public class RepairType {
    private int id;
    private String type;

    public RepairType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof RepairType)) return false;
        else if (id == ((RepairType) o).getId()) return true;
        else return false;
    }

}
