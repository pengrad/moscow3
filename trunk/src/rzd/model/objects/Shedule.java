package rzd.model.objects;

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
    private Date timeDeparture;
    private Date timeDestination;
    private Date timeInWay;
    private SheduleType sheduleType;
    private int[] days;

    public Shedule(int id, Date timeDeparture, Date timeDestination, Date timeInWay, SheduleType sheduleType, int[] days) {
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

    public Date getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Date timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Date getTimeDestination() {
        return timeDestination;
    }

    public void setTimeDestination(Date timeDestination) {
        this.timeDestination = timeDestination;
    }

    public Date getTimeInWay() {
        return timeInWay;
    }

    public void setTimeInWay(Date timeInWay) {
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
