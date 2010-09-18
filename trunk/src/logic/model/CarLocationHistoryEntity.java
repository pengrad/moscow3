package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:10
 */

@javax.persistence.Table(name = "car_location_history", catalog = "rzd")
@Entity
public class CarLocationHistoryEntity {


    private int idHistory;

    @javax.persistence.Column(name = "id_history", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    private Timestamp ddate;

    @javax.persistence.Column(name = "ddate", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDdate() {
        return ddate;
    }

    public void setDdate(Timestamp ddate) {
        this.ddate = ddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarLocationHistoryEntity that = (CarLocationHistoryEntity) o;

        if (idHistory != that.idHistory) return false;
        if (ddate != null ? !ddate.equals(that.ddate) : that.ddate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHistory;
        result = 31 * result + (ddate != null ? ddate.hashCode() : 0);
        return result;
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

    private CarEntity car;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "number", referencedColumnName = "number", nullable = false)
    CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
