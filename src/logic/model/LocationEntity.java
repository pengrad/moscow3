package logic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:11
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

        if (idLocation != that.idLocation) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idLocation;
    }

    private Collection<CarEntity> cars;

    @OneToMany(mappedBy = "location")
    public Collection<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Collection<CarEntity> cars) {
        this.cars = cars;
    }

    private Collection<CarLocationHistoryEntity> carLocationHistories;

    @OneToMany(mappedBy = "location")
    public Collection<CarLocationHistoryEntity> getCarLocationHistories() {
        return carLocationHistories;
    }

    public void setCarLocationHistories(Collection<CarLocationHistoryEntity> carLocationHistories) {
        this.carLocationHistories = carLocationHistories;
    }

    private RoadEntity road;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_road", referencedColumnName = "id_road")
    RoadEntity getRoad() {
        return road;
    }

    public void setRoad(RoadEntity road) {
        this.road = road;
    }

    private LocationOtherEntity locationOther;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_otherlocation", referencedColumnName = "id_otherlocation")
    LocationOtherEntity getLocationOther() {
        return locationOther;
    }

    public void setLocationOther(LocationOtherEntity locationOther) {
        this.locationOther = locationOther;
    }

    private Collection<TrainEntity> trains;

    @OneToMany(mappedBy = "location")
    public Collection<TrainEntity> getTrains() {
        return trains;
    }

    public void setTrains(Collection<TrainEntity> trains) {
        this.trains = trains;
    }
}
