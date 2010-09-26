package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:35
 */

@javax.persistence.Table(name = "car_history", catalog = "rzd")
@Entity
public class CarHistoryEntity {


    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Date date;

    @javax.persistence.Column(name = "date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarHistoryEntity that = (CarHistoryEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
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

    private CarEntity car;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "car_number", referencedColumnName = "car_number", nullable = false)
    CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
