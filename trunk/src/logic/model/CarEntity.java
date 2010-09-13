package logic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@javax.persistence.Table(name = "car", catalog = "rzd")
@Entity
public class CarEntity {

    public CarEntity() {
    }

    public CarEntity(int number, Timestamp dateUpdateLocation, Timestamp dateUpdate, CarLocationEntity carLocationByIdLocation) {
        this.number = number;
        this.dateUpdateLocation = dateUpdateLocation;
        this.dateUpdate = dateUpdate;
        this.carLocationByIdLocation = carLocationByIdLocation;
    }

    private int number;

    @javax.persistence.Column(name = "number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private Timestamp dateUpdateLocation;

    @javax.persistence.Column(name = "date_update_location", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDateUpdateLocation() {
        return dateUpdateLocation;
    }

    public void setDateUpdateLocation(Timestamp dateUpdateLocation) {
        this.dateUpdateLocation = dateUpdateLocation;
    }

    private Timestamp dateUpdate;

    @javax.persistence.Column(name = "date_update", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (number != carEntity.number) return false;
        if (dateUpdate != null ? !dateUpdate.equals(carEntity.dateUpdate) : carEntity.dateUpdate != null) return false;
        if (dateUpdateLocation != null ? !dateUpdateLocation.equals(carEntity.dateUpdateLocation) : carEntity.dateUpdateLocation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (dateUpdateLocation != null ? dateUpdateLocation.hashCode() : 0);
        result = 31 * result + (dateUpdate != null ? dateUpdate.hashCode() : 0);
        return result;
    }

    private CarLocationEntity carLocationByIdLocation;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_location", referencedColumnName = "id_location", nullable = false)
    CarLocationEntity getCarLocationByIdLocation() {
        return carLocationByIdLocation;
    }

    public void setCarLocationByIdLocation(CarLocationEntity carLocationByIdLocation) {
        this.carLocationByIdLocation = carLocationByIdLocation;
    }

    private Collection<TrainCarsEntity> trainCarsesByNumber;

    @OneToMany(mappedBy = "carByIdCar")
    public Collection<TrainCarsEntity> getTrainCarsesByNumber() {
        return trainCarsesByNumber;
    }

    public void setTrainCarsesByNumber(Collection<TrainCarsEntity> trainCarsesByNumber) {
        this.trainCarsesByNumber = trainCarsesByNumber;
    }
}
