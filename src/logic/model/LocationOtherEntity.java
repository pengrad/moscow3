package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:11
 */

@javax.persistence.Table(name = "location_other", catalog = "rzd")
@Entity
public class LocationOtherEntity {


    private int idOtherlocation;

    @javax.persistence.Column(name = "id_otherlocation", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdOtherlocation() {
        return idOtherlocation;
    }

    public void setIdOtherlocation(int idOtherlocation) {
        this.idOtherlocation = idOtherlocation;
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

        LocationOtherEntity that = (LocationOtherEntity) o;

        if (idOtherlocation != that.idOtherlocation) return false;
        if (parking != null ? !parking.equals(that.parking) : that.parking != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOtherlocation;
        result = 31 * result + (parking != null ? parking.hashCode() : 0);
        return result;
    }

    private Collection<LocationEntity> locations;

    @OneToMany(mappedBy = "locationOther")
    public Collection<LocationEntity> getLocations() {
        return locations;
    }

    public void setLocations(Collection<LocationEntity> locations) {
        this.locations = locations;
    }
}
