package logic.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 3:38:35
 */

public class TrainDetEntityPK implements Serializable {


    private int idTrain;

    @Id
    @Column(name = "id_train", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    private int carNumber;

    @Id
    @Column(name = "car_number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
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

        TrainDetEntityPK that = (TrainDetEntityPK) o;

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
