package logic.model;

import javax.persistence.*;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:37
 */

@Table(name = "road_det", catalog = "rzd", schema = "")
@Entity
public class RoadDetEntity {

    public RoadDetEntity() {
    }

    public RoadDetEntity(RoadEntity road, CarEntity car, TrainEntity train) {
        this.road = road;
        this.car = car;
        this.train = train;
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
    @JoinColumn(name = "id_road", referencedColumnName = "id_road", nullable = false)
    RoadEntity getRoad() {
        return road;
    }

    public void setRoad(RoadEntity road) {
        this.road = road;
    }

    private CarEntity car;

    @ManyToOne
    public
    @JoinColumn(name = "car_number", referencedColumnName = "car_number")
    CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
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
}
