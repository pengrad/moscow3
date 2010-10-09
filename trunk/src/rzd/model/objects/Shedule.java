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
    private int hoursTimeInWay;
    private int minutesTimeInWay;
    private SheduleType sheduleType;
    private int[] days;

    public Shedule(int id, Time timeDeparture, Time timeDestination, int hoursTimeInWay, int minutesTimeInWay, SheduleType sheduleType, int[] days) {
        this.id = id;
        this.timeDeparture = timeDeparture;
        this.timeDestination = timeDestination;
        this.hoursTimeInWay = hoursTimeInWay;
        this.minutesTimeInWay = minutesTimeInWay;
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

    public int getHoursTimeInWay() {
        return hoursTimeInWay;
    }

    public void setHoursTimeInWay(int hoursTimeInWay) {
        this.hoursTimeInWay = hoursTimeInWay;
    }

    public int getMinutesTimeInWay() {
        return minutesTimeInWay;
    }

    public void setMinutesTimeInWay(int minutesTimeInWay) {
        this.minutesTimeInWay = minutesTimeInWay;
    }

    public SheduleType getSheduleType() {
        return sheduleType;
    }

    public void setSheduleType(SheduleType sheduleType) {
        this.sheduleType = sheduleType;
    }

    public int[] getDays() {
        return days;
    }

    public void setDays(int[] days) {
        this.days = days;
    }
}
