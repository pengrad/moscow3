package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:12
 */

@javax.persistence.Table(name = "route", catalog = "rzd")
@Entity
public class RouteEntity {

    public RouteEntity() {
    }

    public RouteEntity(String number, String pointDeparture, String pointDestination) {
        this.number = number;
        this.pointDeparture = pointDeparture;
        this.pointDestination = pointDestination;
    }

    private int idRoute;

    @javax.persistence.Column(name = "id_route", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    private String number;

    @javax.persistence.Column(name = "number", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String pointDeparture;

    @javax.persistence.Column(name = "point_departure", nullable = false, insertable = true, updatable = true, length = 500, precision = 0)
    @Basic
    public String getPointDeparture() {
        return pointDeparture;
    }

    public void setPointDeparture(String pointDeparture) {
        this.pointDeparture = pointDeparture;
    }

    private String pointDestination;

    @javax.persistence.Column(name = "point_destination", nullable = false, insertable = true, updatable = true, length = 500, precision = 0)
    @Basic
    public String getPointDestination() {
        return pointDestination;
    }

    public void setPointDestination(String pointDestination) {
        this.pointDestination = pointDestination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (idRoute != that.idRoute) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (pointDeparture != null ? !pointDeparture.equals(that.pointDeparture) : that.pointDeparture != null)
            return false;
        if (pointDestination != null ? !pointDestination.equals(that.pointDestination) : that.pointDestination != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoute;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (pointDeparture != null ? pointDeparture.hashCode() : 0);
        result = 31 * result + (pointDestination != null ? pointDestination.hashCode() : 0);
        return result;
    }

    private Collection<RouteScheduleEntity> routeSchedules;

    @OneToMany(mappedBy = "route")
    public Collection<RouteScheduleEntity> getRouteSchedules() {
        return routeSchedules;
    }

    public void setRouteSchedules(Collection<RouteScheduleEntity> routeSchedules) {
        this.routeSchedules = routeSchedules;
    }
}
