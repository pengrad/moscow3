package logic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@javax.persistence.Table(name = "train_cars", catalog = "rzd")
@Entity
public class TrainCarsEntity {

    public TrainCarsEntity() {
    }

    public TrainCarsEntity(TrainEntity trainByIdTrain, CarEntity carByIdCar) {
        this.trainByIdTrain = trainByIdTrain;
        this.carByIdCar = carByIdCar;
        this.idCar = carByIdCar.getNumber();
        this.idTrain = trainByIdTrain.getIdTrain();
    }

    private int idTrain;

    @javax.persistence.Column(name = "id_train", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    private int getIdTrain() {
        return idTrain;
    }

    private void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    private int idCar;

    @javax.persistence.Column(name = "id_car", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    private int getIdCar() {
        return idCar;
    }

    private void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainCarsEntity that = (TrainCarsEntity) o;

        if (idCar != that.idCar) return false;
        if (idTrain != that.idTrain) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTrain;
        result = 31 * result + idCar;
        return result;
    }

    private TrainEntity trainByIdTrain;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_train", referencedColumnName = "id_train", nullable = false, insertable = false, updatable = false)
    TrainEntity getTrainByIdTrain() {
        return trainByIdTrain;
    }

    public void setTrainByIdTrain(TrainEntity trainByIdTrain) {
        this.trainByIdTrain = trainByIdTrain;
        this.idTrain = trainByIdTrain.getIdTrain();
    }

    private CarEntity carByIdCar;

    @ManyToOne    
    public
    @javax.persistence.JoinColumn(name = "id_car", referencedColumnName = "number", nullable = false)
    CarEntity getCarByIdCar() {
        return carByIdCar;
    }

    public void setCarByIdCar(CarEntity carByIdCar) {
        this.carByIdCar = carByIdCar;
        this.idCar = carByIdCar.getNumber();
    }
}
