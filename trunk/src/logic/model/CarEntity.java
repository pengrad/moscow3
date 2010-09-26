package logic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:35
 */

@javax.persistence.Table(name = "car", catalog = "rzd")
@Entity
public class CarEntity {


    private int carNumber;

    @javax.persistence.Column(name = "car_number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (carNumber != carEntity.carNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return carNumber;
    }

    private LocationEntity location;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_location", referencedColumnName = "id_location", nullable = false)
    LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    private Collection<CarHistoryEntity> carHistories;

    @OneToMany(mappedBy = "car")
    public Collection<CarHistoryEntity> getCarHistories() {
        return carHistories;
    }

    public void setCarHistories(Collection<CarHistoryEntity> carHistories) {
        this.carHistories = carHistories;
    }

    private Collection<RoadDetEntity> roadDets;

    @OneToMany(mappedBy = "car")
    public Collection<RoadDetEntity> getRoadDets() {
        return roadDets;
    }

    public void setRoadDets(Collection<RoadDetEntity> roadDets) {
        this.roadDets = roadDets;
    }

    private Collection<TrainDetEntity> trainDets;

    @OneToMany(mappedBy = "car")
    public Collection<TrainDetEntity> getTrainDets() {
        return trainDets;
    }

    public void setTrainDets(Collection<TrainDetEntity> trainDets) {
        this.trainDets = trainDets;
    }
}
