package rzd.model.objects;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 14.09.2010
 * Time: 16:27:02
 * To change this template use File | Settings | File Templates.
 */
public class Schedule {
    private int id;
    private int type;
    private Date timeDeparture;
    private Date timeDestination;
    private Date dateBegin;
    private int dayMove;
    private int dayStop;

    public Schedule(int id, Date timeDeparture, Date timeDestination, Date dateBegin, int dayMove, int dayStop) {
        this.id = id;
        this.timeDeparture = timeDeparture;
        this.timeDestination = timeDestination;
        this.dateBegin = dateBegin;
        this.dayMove = dayMove;
        this.dayStop = dayStop;
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

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
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
