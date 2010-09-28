package rzd.model.objects;

import com.sun.org.apache.xpath.internal.operations.Equals;

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
    private String numberForward;
    private String numberBack;
    private String pointDeparture;
    private String pointDestination;
    private Schedule scheduleForward;
    private Schedule scheduleBack;

    public Route(int id, String numberForward, String numberBack, String pointDeparture, String pointDestination, Schedule scheduleForward, Schedule scheduleBack) {
        this.id = id;
        this.numberForward = numberForward;
        this.numberBack = numberBack;
        this.pointDeparture = pointDeparture;
        this.pointDestination = pointDestination;
        this.scheduleForward = scheduleForward;
        this.scheduleBack = scheduleBack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberBack() {
        return numberBack;
    }

    public void setNumberBack(String numberBack) {
        this.numberBack = numberBack;
    }

    public String getNumberForward() {
        return numberForward;
    }

    public void setNumberTo(String numberForward) {
        this.numberForward = numberForward;
    }

    public String getPointDeparture() {
        return pointDeparture;
    }

    public void setPointDeparture(String pointDeparture) {
        this.pointDeparture = pointDeparture;
    }

    public String getPointDestination() {
        return pointDestination;
    }

    public void setPointDestination(String pointDestination) {
        this.pointDestination = pointDestination;
    }

    public Schedule getScheduleForward() {
        return scheduleForward;
    }

    public void setScheduleForward(Schedule scheduleForward) {
        this.scheduleForward = scheduleForward;
    }

    public Schedule getScheduleBack() {
        return scheduleBack;
    }

    public void setScheduleBack(Schedule scheduleBack) {
        this.scheduleBack = scheduleBack;
    }

    @Override
    public String toString() {
        return numberForward + "  " + numberBack + " " + pointDeparture + " - " + pointDestination;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Route) {
            if (id == ((Route) o).getId()) {
                return true;
            }
        }
        return false;
    }
}
