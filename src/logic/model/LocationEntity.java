package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:36
 */

@javax.persistence.Table(name = "location", catalog = "rzd")
@Entity
public class LocationEntity {


    private int idLocation;

    @javax.persistence.Column(name = "id_location", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    private String cLocation;

    @javax.persistence.Column(name = "c_location", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getcLocation() {
        return cLocation;
    }

    public void setcLocation(String cLocation) {
        this.cLocation = cLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

        if (idLocation != that.idLocation) return false;
        if (cLocation != null ? !cLocation.equals(that.cLocation) : that.cLocation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLocation;
        result = 31 * result + (cLocation != null ? cLocation.hashCode() : 0);
        return result;
    }

    private Collection<CarEntity> cars;

    @OneToMany(mappedBy = "location")
    public Collection<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Collection<CarEntity> cars) {
        this.cars = cars;
    }

    private Collection<CarHistoryEntity> carHistories;

    @OneToMany(mappedBy = "location")
    public Collection<CarHistoryEntity> getCarHistories() {
        return carHistories;
    }

    public void setCarHistories(Collection<CarHistoryEntity> carHistories) {
        this.carHistories = carHistories;
    }
}
