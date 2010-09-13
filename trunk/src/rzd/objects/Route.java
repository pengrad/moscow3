package rzd.objects;

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
    private Time time_departure;
    private Time time_destination;
    private Date date_begin;
    private int dayMove;
    private int dayStop;

    public Route(int id, String number, String point_departure, String point_destination, Time time_departure, Time time_destination, Date date_begin, int dayMove, int dayStop) {
        this.id = id;
        this.number = number;
        this.point_departure = point_departure;
        this.point_destination = point_destination;
        this.time_departure = time_departure;
        this.time_destination = time_destination;
        this.date_begin = date_begin;
        this.dayMove = dayMove;
        this.dayStop = dayStop;
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

    public Time getTime_departure() {
        return time_departure;
    }

    public void setTime_departure(Time time_departure) {
        this.time_departure = time_departure;
    }

    public Time getTime_destination() {
        return time_destination;
    }

    public void setTime_destination(Time time_destination) {
        this.time_destination = time_destination;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }

    public int getDayMove() {
        return dayMove;
    }

    public void setDayMove(int dayMove) {
        this.dayMove = dayMove;
    }

    public int getDayStop() {
        return dayStop;
    }

    public void setDayStop(int dayStop) {
        this.dayStop = dayStop;
    }
}
