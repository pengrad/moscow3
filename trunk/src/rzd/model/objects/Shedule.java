package rzd.model.objects;

import java.sql.Time;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 14.09.2010
 * Time: 16:27:02
 * To change this template use File | Settings | File Templates.
 */
public class Shedule {
    private int id;
    private Time timeDeparture;
    private Time timeDestination;
    private Time timeInWay;
    private SheduleType sheduleType;
    private int[] days;

    public Shedule(int id, Time timeDeparture, Time timeDestination, Time timeInWay, SheduleType sheduleType, int[] days) {
        this.id = id;
        this.timeDeparture = timeDeparture;
        this.timeDestination = timeDestination;
        this.timeInWay = timeInWay;
        this.sheduleType = sheduleType;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Time timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Time getTimeDestination() {
        return timeDestination;
    }

    public void setTimeDestination(Time timeDestination) {
        this.timeDestination = timeDestination;
    }

    public Time getTimeInWay() {
        return timeInWay;
    }

    public void setTimeInWay(Time timeInWay) {
        this.timeInWay = timeInWay;
    }

    public SheduleType getScheduleType() {
        return sheduleType;
    }

    public void setScheduleType(SheduleType sheduleType) {
        this.sheduleType = sheduleType;
    }

    public int[] getDays() {
        return days;
    }

    public void setDays(int[] days) {
        this.days = days;
    }
}
