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
    private String pointDeparture;
    private String pointDestination;

    public Route(int id, String number, String pointDeparture, String pointDestination) {
        this.id = id;
        this.number = number;
        this.pointDeparture = pointDeparture;
        this.pointDestination = pointDestination;
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

    public String getPointDeparture() {
        return pointDeparture;
    }

    public void setPoint_departure(String pointDeparture) {
        this.pointDeparture = pointDeparture;
    }

    public String getPointDestination() {
        return pointDestination;
    }

    public void setPoint_destination(String pointDestination) {
        this.pointDestination = pointDestination;
    }

}
