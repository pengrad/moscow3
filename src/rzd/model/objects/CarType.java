package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 03.10.2010
 * Time: 16:10:49
 * To change this template use File | Settings | File Templates.
 */
public class CarType {
    private int idType;
    private String type;

    public CarType(int idType, String type) {
        this.idType = idType;
        this.type = type;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
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
        if (o == null || !(o instanceof CarType)) return false;
        else if (idType == ((CarType) o).getIdType()) return true;
        else return false;
    }
}
