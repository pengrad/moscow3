package logic.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * User: Стас
 * Date: 10.10.2010
 * Time: 0:43:45
 */

@javax.persistence.Table(name = "shedule", catalog = "rzd")
@Entity
public class SheduleEntity {

    public SheduleEntity() {
    }

    public SheduleEntity(Time timeFrom, Time timeTo, int hoursInWay, int minutesInWay, SheduleTypeEntity sheduleType) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.hoursInWay = hoursInWay;
        this.minutesInWay = minutesInWay;
        this.sheduleType = sheduleType;
    }

    private int idShedule;

    @javax.persistence.Column(name = "id_shedule", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue
    public int getIdShedule() {
        return idShedule;
    }

    public void setIdShedule(int idShedule) {
        this.idShedule = idShedule;
    }

    private Time timeFrom;

    @javax.persistence.Column(name = "time_from", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    private Time timeTo;

    @javax.persistence.Column(name = "time_to", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    private int hoursInWay;

    @javax.persistence.Column(name = "hours_in_way", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getHoursInWay() {
        return hoursInWay;
    }

    public void setHoursInWay(int hoursInWay) {
        this.hoursInWay = hoursInWay;
    }

    private int minutesInWay;

    @javax.persistence.Column(name = "minutes_in_way", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getMinutesInWay() {
        return minutesInWay;
    }

    public void setMinutesInWay(int minutesInWay) {
        this.minutesInWay = minutesInWay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheduleEntity that = (SheduleEntity) o;

        if (hoursInWay != that.hoursInWay) return false;
        if (idShedule != that.idShedule) return false;
        if (minutesInWay != that.minutesInWay) return false;
        if (timeFrom != null ? !timeFrom.equals(that.timeFrom) : that.timeFrom != null) return false;
        if (timeTo != null ? !timeTo.equals(that.timeTo) : that.timeTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idShedule;
        result = 31 * result + (timeFrom != null ? timeFrom.hashCode() : 0);
        result = 31 * result + (timeTo != null ? timeTo.hashCode() : 0);
        result = 31 * result + hoursInWay;
        result = 31 * result + minutesInWay;
        return result;
    }

    private Collection<RouteEntity> routesBySheduleBack;

    @OneToMany(mappedBy = "sheduleBack")
    public Collection<RouteEntity> getRoutesBySheduleBack() {
        return routesBySheduleBack;
    }

    public void setRoutesBySheduleBack(Collection<RouteEntity> routesBySheduleBack) {
        this.routesBySheduleBack = routesBySheduleBack;
    }

    private Collection<RouteEntity> routesBySheduleForward;

    @OneToMany(mappedBy = "sheduleForward")
    public Collection<RouteEntity> getRoutesBySheduleForward() {
        return routesBySheduleForward;
    }

    public void setRoutesBySheduleForward(Collection<RouteEntity> routesBySheduleForward) {
        this.routesBySheduleForward = routesBySheduleForward;
    }

    private SheduleTypeEntity sheduleType;

    @ManyToOne
    public
    @JoinColumn(name = "id_shedule_type", referencedColumnName = "id_shedule_type", nullable = false)
    SheduleTypeEntity getSheduleType() {
        return sheduleType;
    }

    public void setSheduleType(SheduleTypeEntity sheduleType) {
        this.sheduleType = sheduleType;
    }

    private Collection<SheduleDaysEntity> sheduleDays;

    @OneToMany(mappedBy = "shedule")
    public Collection<SheduleDaysEntity> getSheduleDays() {
        return sheduleDays;
    }

    public void setSheduleDays(Collection<SheduleDaysEntity> sheduleDays) {
        this.sheduleDays = sheduleDays;
    }

    private Collection<TrainEntity> trains;

    @OneToMany(mappedBy = "shedule")
    public Collection<TrainEntity> getTrains() {
        return trains;
    }

    public void setTrains(Collection<TrainEntity> trains) {
        this.trains = trains;
    }
}
