package logic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:10
 */

@javax.persistence.Table(name = "car", catalog = "rzd")
@Entity
public class CarEntity {


    private int number;

    @javax.persistence.Column(name = "number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (dateUpdate != null ? dateUpdate.hashCode() : 0);
        return result;
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

    private TrainEntity train;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_train", referencedColumnName = "id_train")
    TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    private Collection<CarLocationHistoryEntity> carLocationHistories;

    @OneToMany(mappedBy = "car")
    public Collection<CarLocationHistoryEntity> getCarLocationHistories() {
        return carLocationHistories;
    }

    public void setCarLocationHistories(Collection<CarLocationHistoryEntity> carLocationHistories) {
        this.carLocationHistories = carLocationHistories;
    }
}
