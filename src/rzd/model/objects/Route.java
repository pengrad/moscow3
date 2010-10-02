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
    private boolean enabled;
    private int lengthForward;
    private int lengthBack;


    public Route(int id, String numberForward, String numberBack, String pointDeparture, String pointDestination, Shedule sheduleForward, Shedule sheduleBack, boolean enabled, int lengthForward, int lengthBack) {
        this.id = id;
        this.numberForward = numberForward;
        this.numberBack = numberBack;
        this.pointDeparture = pointDeparture;
        this.pointDestination = pointDestination;
        this.sheduleForward = sheduleForward;
        this.sheduleBack = sheduleBack;
        this.enabled = enabled;
        this.lengthForward = lengthForward;
        this.lengthBack = lengthBack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberForward() {
        return numberForward;
    }

    public void setNumberForward(String numberForward) {
        this.numberForward = numberForward;
    }

    public String getNumberBack() {
        return numberBack;
    }

    public void setNumberBack(String numberBack) {
        this.numberBack = numberBack;
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

    public Shedule getSheduleForward() {
        return sheduleForward;
    }

    public void setSheduleForward(Shedule sheduleForward) {
        this.sheduleForward = sheduleForward;
    }

    public Shedule getSheduleBack() {
        return sheduleBack;
    }

    public void setSheduleBack(Shedule sheduleBack) {
        this.sheduleBack = sheduleBack;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLengthForward() {
        return lengthForward;
    }

    public void setLengthForward(int lengthForward) {
        this.lengthForward = lengthForward;
    }

    public int getLengthBack() {
        return lengthBack;
    }

    public void setLengthBack(int lengthBack) {
        this.lengthBack = lengthBack;
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
