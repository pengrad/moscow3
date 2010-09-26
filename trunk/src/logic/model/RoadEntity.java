package logic.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:36
 */

@javax.persistence.Table(name = "road", catalog = "rzd")
@Entity
public class RoadEntity {


    private int idRoad;

    @javax.persistence.Column(name = "id_road", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdRoad() {
        return idRoad;
    }

    public void setIdRoad(int idRoad) {
        this.idRoad = idRoad;
    }

    private String roadName;

    @javax.persistence.Column(name = "road_name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    private String comments;

    @javax.persistence.Column(name = "comments", nullable = true, insertable = true, updatable = true, length = 2000, precision = 0)
    @Basic
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    private int position;

    @javax.persistence.Column(name = "position", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoadEntity that = (RoadEntity) o;

        if (idRoad != that.idRoad) return false;
        if (position != that.position) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (roadName != null ? !roadName.equals(that.roadName) : that.roadName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoad;
        result = 31 * result + (roadName != null ? roadName.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + position;
        return result;
    }

    private Collection<CarHistoryEntity> carHistories;

    @OneToMany(mappedBy = "road")
    public Collection<CarHistoryEntity> getCarHistories() {
        return carHistories;
    }

    public void setCarHistories(Collection<CarHistoryEntity> carHistories) {
        this.carHistories = carHistories;
    }

    private RoadTypeEntity roadType;

    @ManyToOne
    public
    @JoinColumn(name = "id_type", referencedColumnName = "id_type", nullable = false)
    RoadTypeEntity getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadTypeEntity roadType) {
        this.roadType = roadType;
    }

    private Collection<RoadDetEntity> roadDets;

    @OneToMany(mappedBy = "road")
    public Collection<RoadDetEntity> getRoadDets() {
        return roadDets;
    }

    public void setRoadDets(Collection<RoadDetEntity> roadDets) {
        this.roadDets = roadDets;
    }
}
