package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:37
 */

@javax.persistence.Table(name = "route", catalog = "rzd")
@Entity
public class RouteEntity {


    private int idRoute;

    @javax.persistence.Column(name = "id_route", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    private String routeNumber;

    @javax.persistence.Column(name = "route_number", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    private int cityFrom;

    @javax.persistence.Column(name = "city_from", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(int cityFrom) {
        this.cityFrom = cityFrom;
    }

    private int cityTo;

    @javax.persistence.Column(name = "city_to", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getCityTo() {
        return cityTo;
    }

    public void setCityTo(int cityTo) {
        this.cityTo = cityTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (cityFrom != that.cityFrom) return false;
        if (cityTo != that.cityTo) return false;
        if (idRoute != that.idRoute) return false;
        if (routeNumber != null ? !routeNumber.equals(that.routeNumber) : that.routeNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoute;
        result = 31 * result + (routeNumber != null ? routeNumber.hashCode() : 0);
        result = 31 * result + cityFrom;
        result = 31 * result + cityTo;
        return result;
    }

    private SheduleEntity sheduleBack;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "shedule_back", referencedColumnName = "id_shedule", nullable = false)
    SheduleEntity getSheduleBack() {
        return sheduleBack;
    }

    public void setSheduleBack(SheduleEntity sheduleBack) {
        this.sheduleBack = sheduleBack;
    }

    private SheduleEntity sheduleForward;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "shedule_forward", referencedColumnName = "id_shedule", nullable = false)
    SheduleEntity getSheduleForward() {
        return sheduleForward;
    }

    public void setSheduleForward(SheduleEntity sheduleForward) {
        this.sheduleForward = sheduleForward;
    }
}
