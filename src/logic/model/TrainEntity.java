package logic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:14
 */

@javax.persistence.Table(name = "train", catalog = "rzd")
@Entity
public class TrainEntity {

    public TrainEntity() {
    }

    public TrainEntity(String trainChief, Timestamp dtDeparture, Timestamp dtDestination, LocationEntity location, RouteScheduleEntity routeSchedule) {        
        this.trainChief = trainChief;
        this.dtDeparture = dtDeparture;
        this.dtDestination = dtDestination;
        this.location = location;
        this.routeSchedule = routeSchedule;
    }

    private int idTrain;

    @javax.persistence.Column(name = "id_train", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    private String trainChief;

    @javax.persistence.Column(name = "train_chief", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getTrainChief() {
        return trainChief;
    }

    public void setTrainChief(String trainChief) {
        this.trainChief = trainChief;
    }

    private Timestamp dtDeparture;

    @javax.persistence.Column(name = "dt_departure", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDtDeparture() {
        return dtDeparture;
    }

    public void setDtDeparture(Timestamp dtDeparture) {
        this.dtDeparture = dtDeparture;
    }

    private Timestamp dtDestination;

    @javax.persistence.Column(name = "dt_destination", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDtDestination() {
        return dtDestination;
    }

    public void setDtDestination(Timestamp dtDestination) {
        this.dtDestination = dtDestination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainEntity that = (TrainEntity) o;

        if (idTrain != that.idTrain) return false;
        if (dtDeparture != null ? !dtDeparture.equals(that.dtDeparture) : that.dtDeparture != null) return false;
        if (dtDestination != null ? !dtDestination.equals(that.dtDestination) : that.dtDestination != null)
            return false;
        if (trainChief != null ? !trainChief.equals(that.trainChief) : that.trainChief != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTrain;
        result = 31 * result + (trainChief != null ? trainChief.hashCode() : 0);
        result = 31 * result + (dtDeparture != null ? dtDeparture.hashCode() : 0);
        result = 31 * result + (dtDestination != null ? dtDestination.hashCode() : 0);
        return result;
    }

    private Collection<CarEntity> cars;

    @OneToMany(mappedBy = "train")
    public Collection<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Collection<CarEntity> cars) {
        this.cars = cars;
    }

    private Collection<CarLocationHistoryEntity> carLocationHistories;

    @OneToMany(mappedBy = "train")
    public Collection<CarLocationHistoryEntity> getCarLocationHistories() {
        return carLocationHistories;
    }

    public void setCarLocationHistories(Collection<CarLocationHistoryEntity> carLocationHistories) {
        this.carLocationHistories = carLocationHistories;
    }

    private LocationEntity location;

    @ManyToOne
    public
    @JoinColumn(name = "id_location", referencedColumnName = "id_location", nullable = false)
    LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    private RouteScheduleEntity routeSchedule;

    @ManyToOne
    public
    @JoinColumn(name = "id_schedule", referencedColumnName = "id_schedule", nullable = false)
    RouteScheduleEntity getRouteSchedule() {
        return routeSchedule;
    }

    public void setRouteSchedule(RouteScheduleEntity routeSchedule) {
        this.routeSchedule = routeSchedule;
    }
}
