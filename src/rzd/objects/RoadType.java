package rzd.objects;

import java.util.ArrayList;

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
    private ArrayList<Road> roads;

    public RoadType(int id, String name, ArrayList<Road> roads) {
        this.id = id;
        this.name = name;
        this.roads = roads;
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

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }
}
