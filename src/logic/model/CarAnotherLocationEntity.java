package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@javax.persistence.Table(name = "car_another_location", catalog = "rzd")
@Entity
public class CarAnotherLocationEntity {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String parking;

    @javax.persistence.Column(name = "parking", nullable = false, insertable = true, updatable = true, length = 2000, precision = 0)
    @Basic
    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarAnotherLocationEntity that = (CarAnotherLocationEntity) o;

        if (id != that.id) return false;
        if (parking != null ? !parking.equals(that.parking) : that.parking != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (parking != null ? parking.hashCode() : 0);
        return result;
    }

    private Collection<CarLocationEntity> carLocationsById;

    @OneToMany(mappedBy = "carAnotherLocationByIdOtherlocation")
    public Collection<CarLocationEntity> getCarLocationsById() {
        return carLocationsById;
    }

    public void setCarLocationsById(Collection<CarLocationEntity> carLocationsById) {
        this.carLocationsById = carLocationsById;
    }
}
