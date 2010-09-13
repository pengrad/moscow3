package rzd.objects;

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
    private RoadType roadType;
    private String comment;
    private int position;

    public Road(int id, String name, RoadType roadType, String comment, int position) {
        this.id = id;
        this.name = name;
        this.roadType = roadType;
        this.comment = comment;
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

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
