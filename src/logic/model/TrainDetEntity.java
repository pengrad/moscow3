package logic.model;

import javax.persistence.*;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:39
 */

@IdClass(TrainDetEntityPK.class)
@Table(name = "train_det", catalog = "rzd", schema = "")
@Entity
public class TrainDetEntity {

    public TrainDetEntity() {
    }

    public TrainDetEntity(CarEntity car, TrainEntity train, int carNumberInTrain) {
        setCar(car);
        setTrain(train);
        this.carNumberInTrain = carNumberInTrain;
    }

    private int idTrain;

    @Column(name = "id_train", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    private int carNumber;

    @Column(name = "car_number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    private int carNumberInTrain;

    @Column(name = "car_number_in_train", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getCarNumberInTrain() {
        return carNumberInTrain;
    }

    public void setCarNumberInTrain(int carNumberInTrain) {
        this.carNumberInTrain = carNumberInTrain;
    }

    private CarEntity car;
    
    @ManyToOne
    public
    @JoinColumn(name = "car_number", referencedColumnName = "car_number", nullable = false, insertable = false, updatable = false)
    CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
        this.carNumber = car.getCarNumber();
    }

    private TrainEntity train;

    @ManyToOne
    public
    @JoinColumn(name = "id_train", referencedColumnName = "id_train", nullable = false, insertable = false, updatable = false)
    TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
        this.idTrain = train.getIdTrain();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainDetEntity that = (TrainDetEntity) o;

        if (carNumber != that.carNumber) return false;
        if (idTrain != that.idTrain) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTrain;
        result = 31 * result + carNumber;
        return result;
    }
}
