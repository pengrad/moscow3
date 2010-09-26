package logic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:37
 */

@javax.persistence.Table(name = "road_det", catalog = "rzd")
@Entity
public class RoadDetEntity {


    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoadDetEntity that = (RoadDetEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    private RoadEntity road;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_road", referencedColumnName = "id_road", nullable = false)
    RoadEntity getRoad() {
        return road;
    }

    public void setRoad(RoadEntity road) {
        this.road = road;
    }

    private CarEntity car;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "car_number", referencedColumnName = "car_number")
    CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
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
}
