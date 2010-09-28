package rzd.model.objects;

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
    private Shedule sheduleForward;
    private Shedule sheduleBack;

    public Route(int id, String numberForward, String numberBack, String pointDeparture, String pointDestination, Shedule sheduleForward, Shedule sheduleBack) {
        this.id = id;
        this.numberForward = numberForward;
        this.numberBack = numberBack;
        this.pointDeparture = pointDeparture;
        this.pointDestination = pointDestination;
        this.sheduleForward = sheduleForward;
        this.sheduleBack = sheduleBack;
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

    public Shedule getScheduleForward() {
        return sheduleForward;
    }

    public void setScheduleForward(Shedule sheduleForward) {
        this.sheduleForward = sheduleForward;
    }

    public Shedule getScheduleBack() {
        return sheduleBack;
    }

    public void setScheduleBack(Shedule sheduleBack) {
        this.sheduleBack = sheduleBack;
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
