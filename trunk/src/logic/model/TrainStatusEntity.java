package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:39
 */

@javax.persistence.Table(name = "train_status", catalog = "rzd")
@Entity
public class TrainStatusEntity {


    private int idStatus;

    @javax.persistence.Column(name = "id_status", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    private String cStatus;

    @javax.persistence.Column(name = "c_status", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainStatusEntity that = (TrainStatusEntity) o;

        if (idStatus != that.idStatus) return false;
        if (cStatus != null ? !cStatus.equals(that.cStatus) : that.cStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStatus;
        result = 31 * result + (cStatus != null ? cStatus.hashCode() : 0);
        return result;
    }

    private Collection<TrainEntity> trainsByIdStatus;

    @OneToMany(mappedBy = "trainStatus")
    public Collection<TrainEntity> getTrainsByIdStatus() {
        return trainsByIdStatus;
    }

    public void setTrainsByIdStatus(Collection<TrainEntity> trainsByIdStatus) {
        this.trainsByIdStatus = trainsByIdStatus;
    }
}
