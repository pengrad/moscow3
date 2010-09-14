package logic.model;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "car_location", catalog = "rzd")
@Entity
public class CarLocationEntity {

    public CarLocationEntity() {
    }

    public CarLocationEntity(TrainEntity trainByIdTrain, CarAnotherLocationEntity carAnotherLocationByIdOtherlocation, RoadEntity roadByIdRoad) {
        this.trainByIdTrain = trainByIdTrain;
        this.carAnotherLocationByIdOtherlocation = carAnotherLocationByIdOtherlocation;
        this.roadByIdRoad = roadByIdRoad;
    }

    private int idLocation;

    @Column(name = "id_location", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue
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

        CarLocationEntity that = (CarLocationEntity) o;

        if (idLocation != that.idLocation) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idLocation;
    }

    private Collection<CarEntity> carsByIdLocation;

    @OneToMany(mappedBy = "carLocationByIdLocation")
    public Collection<CarEntity> getCarsByIdLocation() {
        return carsByIdLocation;
    }

    public void setCarsByIdLocation(Collection<CarEntity> carsByIdLocation) {
        this.carsByIdLocation = carsByIdLocation;
    }

    private CarAnotherLocationEntity carAnotherLocationByIdOtherlocation;

    @ManyToOne
    public
    @JoinColumn(name = "id_otherlocation", referencedColumnName = "id")
    CarAnotherLocationEntity getCarAnotherLocationByIdOtherlocation() {
        return carAnotherLocationByIdOtherlocation;
    }

    public void setCarAnotherLocationByIdOtherlocation(CarAnotherLocationEntity carAnotherLocationByIdOtherlocation) {
        this.carAnotherLocationByIdOtherlocation = carAnotherLocationByIdOtherlocation;
    }

    private RoadEntity roadByIdRoad;

    @ManyToOne
    public
    @JoinColumn(name = "id_road", referencedColumnName = "id")
    RoadEntity getRoadByIdRoad() {
        return roadByIdRoad;
    }

    public void setRoadByIdRoad(RoadEntity roadByIdRoad) {
        this.roadByIdRoad = roadByIdRoad;
    }

    private TrainEntity trainByIdTrain;

    @ManyToOne
    public
    @JoinColumn(name = "id_train", referencedColumnName = "id_train")
    TrainEntity getTrainByIdTrain() {
        return trainByIdTrain;
    }

    public void setTrainByIdTrain(TrainEntity trainByIdTrain) {
        this.trainByIdTrain = trainByIdTrain;
    }

    private Collection<CarLocationHistoryEntity> carLocationHistoriesByIdLocation;

    @OneToMany(mappedBy = "carLocationByIdLocation")
    public Collection<CarLocationHistoryEntity> getCarLocationHistoriesByIdLocation() {
        return carLocationHistoriesByIdLocation;
    }

    public void setCarLocationHistoriesByIdLocation(Collection<CarLocationHistoryEntity> carLocationHistoriesByIdLocation) {
        this.carLocationHistoriesByIdLocation = carLocationHistoriesByIdLocation;
    }
}
