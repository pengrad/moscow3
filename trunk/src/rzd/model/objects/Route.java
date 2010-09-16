package rzd.model.objects;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 14.09.2010
 * Time: 0:24:49
 * To change this template use File | Settings | File Templates.
 */
public class Route {
    private int id;
    private String number;
    private String point_departure;
    private String point_destination;

    public Route(int id, String number, String point_departure, String point_destination) {
        this.id = id;
        this.number = number;
        this.point_departure = point_departure;
        this.point_destination = point_destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPoint_departure() {
        return point_departure;
    }

    public void setPoint_departure(String point_departure) {
        this.point_departure = point_departure;
    }

    public String getPoint_destination() {
        return point_destination;
    }

    public void setPoint_destination(String point_destination) {
        this.point_destination = point_destination;
    }

}
