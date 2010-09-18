package logic.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:13
 */

@javax.persistence.Table(name = "route_schedule", catalog = "rzd")
@Entity
public class RouteScheduleEntity {

    public RouteScheduleEntity() {
    }

    public RouteScheduleEntity(Time timeDeparture, Time timeDestination, Date dateBegin, int dayMove, int dayStop, RouteEntity route) {
        this.timeDeparture = timeDeparture;
        this.timeDestination = timeDestination;
        this.dateBegin = dateBegin;
        this.dayMove = dayMove;
        this.dayStop = dayStop;
        this.route = route;
    }

    private int idSchedule;

    @javax.persistence.Column(name = "id_schedule", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    private Time timeDeparture;

    @javax.persistence.Column(name = "time_departure", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Time timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    private Time timeDestination;

    @javax.persistence.Column(name = "time_destination", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeDestination() {
        return timeDestination;
    }

    public void setTimeDestination(Time timeDestination) {
        this.timeDestination = timeDestination;
    }

    private Date dateBegin;

    @javax.persistence.Column(name = "date_begin", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    private int dayMove;

    @javax.persistence.Column(name = "day_move", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getDayMove() {
        return dayMove;
    }

    public void setDayMove(int dayMove) {
        this.dayMove = dayMove;
    }

    private int dayStop;

    @javax.persistence.Column(name = "day_stop", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getDayStop() {
        return dayStop;
    }

    public void setDayStop(int dayStop) {
        this.dayStop = dayStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteScheduleEntity that = (RouteScheduleEntity) o;

        if (dayMove != that.dayMove) return false;
        if (dayStop != that.dayStop) return false;
        if (idSchedule != that.idSchedule) return false;
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) return false;
        if (timeDeparture != null ? !timeDeparture.equals(that.timeDeparture) : that.timeDeparture != null)
            return false;
        if (timeDestination != null ? !timeDestination.equals(that.timeDestination) : that.timeDestination != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSchedule;
        result = 31 * result + (timeDeparture != null ? timeDeparture.hashCode() : 0);
        result = 31 * result + (timeDestination != null ? timeDestination.hashCode() : 0);
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + dayMove;
        result = 31 * result + dayStop;
        return result;
    }

    private RouteEntity route;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_route", referencedColumnName = "id_route", nullable = false)
    RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    private Collection<TrainEntity> trains;

    @OneToMany(mappedBy = "routeSchedule")
    public Collection<TrainEntity> getTrains() {
        return trains;
    }

    public void setTrains(Collection<TrainEntity> trains) {
        this.trains = trains;
    }
}
