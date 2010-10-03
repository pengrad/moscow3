package rzd.model.objects;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 14.09.2010
 * Time: 0:20:53
 * To change this template use File | Settings | File Templates.
 */
public class Road {

    private int id;
    private String name;
    private String comment;
    private RoadType roadType;
    private int position;

    public Road(int id, String name, String comment, RoadType roadType, int position) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.roadType = roadType;
        this.position = position;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o==null||!(o instanceof Road)) {
            return false;
        }
        if (id == ((Road) o).getId()) {
            return true;
        } else {
            return false;
        }
    }
}
