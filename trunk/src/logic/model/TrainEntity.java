package logic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:39
 */

@Table(name = "train", catalog = "rzd")
@Entity
public class TrainEntity {


    private int idTrain;

    @Column(name = "id_train", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    private String trainChief;

    @Column(name = "train_chief", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getTrainChief() {
        return trainChief;
    }

    public void setTrainChief(String trainChief) {
        this.trainChief = trainChief;
    }

    private Timestamp dateFrom;

    @Column(name = "date_from", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    private Timestamp dateTo;

    @Column(name = "date_to", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainEntity that = (TrainEntity) o;

        if (idTrain != that.idTrain) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
        if (trainChief != null ? !trainChief.equals(that.trainChief) : that.trainChief != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTrain;
        result = 31 * result + (trainChief != null ? trainChief.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        return result;
    }

    private Collection<CarHistoryEntity> carHistories;

    @OneToMany(mappedBy = "train")
    public Collection<CarHistoryEntity> getCarHistories() {
        return carHistories;
    }

    public void setCarHistories(Collection<CarHistoryEntity> carHistories) {
        this.carHistories = carHistories;
    }

    private Collection<RoadDetEntity> roadDets;

    @OneToMany(mappedBy = "train")
    public Collection<RoadDetEntity> getRoadDets() {
        return roadDets;
    }

    public void setRoadDets(Collection<RoadDetEntity> roadDets) {
        this.roadDets = roadDets;
    }

    private SheduleEntity shedule;

    @ManyToOne
    public
    @JoinColumn(name = "id_shedule", referencedColumnName = "id_shedule", nullable = false)
    SheduleEntity getShedule() {
        return shedule;
    }

    public void setShedule(SheduleEntity shedule) {
        this.shedule = shedule;
    }

    private TrainStatusEntity trainStatus;

    @ManyToOne
    public
    @JoinColumn(name = "id_status", referencedColumnName = "id_status", nullable = false)
    TrainStatusEntity getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(TrainStatusEntity trainStatus) {
        this.trainStatus = trainStatus;
    }

    private Collection<TrainDetEntity> trainDets;

    @OneToMany(mappedBy = "train")
    public Collection<TrainDetEntity> getTrainDets() {
        return trainDets;
    }

    public void setTrainDets(Collection<TrainDetEntity> trainDets) {
        this.trainDets = trainDets;
    }
}
