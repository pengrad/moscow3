package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 27.09.2010
 * Time: 23:41:44
 * To change this template use File | Settings | File Templates.
 */
public class SheduleType {
    private int id;
    private String name;

    public SheduleType(int id, String name) {
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
    public String toString(){
    return name;
    }
}
