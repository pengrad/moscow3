package logic.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 3:38:32
 */

@Table(name = "car_history", catalog = "rzd", schema = "")
@Entity
public class CarHistoryEntity {

    public CarHistoryEntity() {
    }

    public CarHistoryEntity(Date date, CarLocationEntity carLocation, TrainEntity train, RoadEntity road, CarEntity car, RepairEntity repair) {
        setDate(date);
        this.carLocation = carLocation;
        this.train = train;
        this.road = road;
        this.car = car;
        this.repair = repair;
    }

    private int id;

    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Timestamp date;

    @Column(name = "date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setDate(Date date) {
        this.date = new Timestamp(date.getTime());
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

    private CarLocationEntity carLocation;

    @ManyToOne
    public
    @JoinColumn(name = "id_location", referencedColumnName = "id_location", nullable = false)
    CarLocationEntity getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(CarLocationEntity carLocation) {
        this.carLocation = carLocation;
    }

    private TrainEntity train;

    @ManyToOne
    public
    @JoinColumn(name = "id_train", referencedColumnName = "id_train")
    TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    private RoadEntity road;

    @ManyToOne
    public
    @JoinColumn(name = "id_road", referencedColumnName = "id_road")
    RoadEntity getRoad() {
        return road;
    }

    public void setRoad(RoadEntity road) {
        this.road = road;
    }

    private CarEntity car;

    @ManyToOne
    public
    @JoinColumn(name = "car_number", referencedColumnName = "car_number", nullable = false)
    CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    private RepairEntity repair;

    @ManyToOne
    public
    @JoinColumn(name = "id_repair", referencedColumnName = "id_repair")
    RepairEntity getRepair() {
        return repair;
    }

    public void setRepair(RepairEntity repair) {
        this.repair = repair;
    }
}
