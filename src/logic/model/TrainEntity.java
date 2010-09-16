package logic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Table(name = "train", catalog = "rzd")
@Entity
public class TrainEntity {

    public TrainEntity() {
    }

    public TrainEntity(Timestamp dtDeparture, Timestamp dtDestination){//}, RouteEntity routeByIdRoute) {
        this.dtDeparture = dtDeparture;
        this.dtDestination = dtDestination;
        //this.routeByIdRoute = routeByIdRoute;
    }

    private int idTrain;

    @Column(name = "id_train", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue
    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    private Timestamp dtDeparture;

    @Column(name = "dt_departure", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDtDeparture() {
        return dtDeparture;
    }

    public void setDtDeparture(Timestamp dtDeparture) {
        this.dtDeparture = dtDeparture;
    }

    private Timestamp dtDestination;

    @Column(name = "dt_destination", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTrain;
        result = 31 * result + (dtDeparture != null ? dtDeparture.hashCode() : 0);
        result = 31 * result + (dtDestination != null ? dtDestination.hashCode() : 0);
        return result;
    }

    private Collection<CarLocationEntity> carLocationsByIdTrain;

    @OneToMany(mappedBy = "trainByIdTrain")
    public Collection<CarLocationEntity> getCarLocationsByIdTrain() {
        return carLocationsByIdTrain;
    }

    public void setCarLocationsByIdTrain(Collection<CarLocationEntity> carLocationsByIdTrain) {
        this.carLocationsByIdTrain = carLocationsByIdTrain;
    }

    private Collection<TrainCarsEntity> trainCarsesByIdTrain;

    @OneToMany(mappedBy = "trainByIdTrain")
    public Collection<TrainCarsEntity> getTrainCarsesByIdTrain() {
        return trainCarsesByIdTrain;
    }

    public void setTrainCarsesByIdTrain(Collection<TrainCarsEntity> trainCarsesByIdTrain) {
        this.trainCarsesByIdTrain = trainCarsesByIdTrain;
    }

    private RouteScheduleEntity routeScheduleByIdSchedule;

    @ManyToOne
    public
    @JoinColumn(name = "id_schedule", referencedColumnName = "id", nullable = false)
    RouteScheduleEntity getRouteScheduleByIdSchedule() {
        return routeScheduleByIdSchedule;
    }

    public void setRouteScheduleByIdSchedule(RouteScheduleEntity routeScheduleByIdSchedule) {
        this.routeScheduleByIdSchedule = routeScheduleByIdSchedule;
    }
}
