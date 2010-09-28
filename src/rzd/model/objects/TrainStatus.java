package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 27.09.2010
 * Time: 23:50:17
 * To change this template use File | Settings | File Templates.
 */
public class TrainStatus {
    private int id;
    private String nameStatus;

    public TrainStatus(int id, String nameStatus) {
        this.id = id;
        this.nameStatus = nameStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
}
