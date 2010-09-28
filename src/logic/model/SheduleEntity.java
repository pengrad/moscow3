package logic.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:38
 */

@Table(name = "shedule", catalog = "rzd")
@Entity
public class SheduleEntity {

    public SheduleEntity() {
    }

    public SheduleEntity(Time timeFrom, Time timeTo, Time timeInWay, SheduleTypeEntity sheduleType) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.timeInWay = timeInWay;
        this.sheduleType = sheduleType;
    }

    private int idShedule;

    @Column(name = "id_shedule", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdShedule() {
        return idShedule;
    }

    public void setIdShedule(int idShedule) {
        this.idShedule = idShedule;
    }

    private Time timeFrom;

    @Column(name = "time_from", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    private Time timeTo;

    @Column(name = "time_to", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    private Time timeInWay;

    @Column(name = "time_in_way", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeInWay() {
        return timeInWay;
    }

    public void setTimeInWay(Time timeInWay) {
        this.timeInWay = timeInWay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheduleEntity that = (SheduleEntity) o;

        if (idShedule != that.idShedule) return false;
        if (timeFrom != null ? !timeFrom.equals(that.timeFrom) : that.timeFrom != null) return false;
        if (timeInWay != null ? !timeInWay.equals(that.timeInWay) : that.timeInWay != null) return false;
        if (timeTo != null ? !timeTo.equals(that.timeTo) : that.timeTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idShedule;
        result = 31 * result + (timeFrom != null ? timeFrom.hashCode() : 0);
        result = 31 * result + (timeTo != null ? timeTo.hashCode() : 0);
        result = 31 * result + (timeInWay != null ? timeInWay.hashCode() : 0);
        return result;
    }

    private Collection<RouteEntity> routesBack;

    @OneToMany(mappedBy = "sheduleBack")
    public Collection<RouteEntity> getRoutesBack() {
        return routesBack;
    }

    public void setRoutesBack(Collection<RouteEntity> routesBack) {
        this.routesBack = routesBack;
    }

    private Collection<RouteEntity> routesForward;

    @OneToMany(mappedBy = "sheduleForward")
    public Collection<RouteEntity> getRoutesForward() {
        return routesForward;
    }

    public void setRoutesForward(Collection<RouteEntity> routesForward) {
        this.routesForward = routesForward;
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

    private Collection<TrainEntity> trainsByIdShedule;

    @OneToMany(mappedBy = "shedule")
    public Collection<TrainEntity> getTrainsByIdShedule() {
        return trainsByIdShedule;
    }

    public void setTrainsByIdShedule(Collection<TrainEntity> trainsByIdShedule) {
        this.trainsByIdShedule = trainsByIdShedule;
    }
}
