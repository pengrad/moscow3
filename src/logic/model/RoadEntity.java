package logic.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: Стас
 * Date: 19.09.2010
 * Time: 2:16:11
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

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoad;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + position;
        return result;
    }

    private Collection<LocationEntity> locations;

    @OneToMany(mappedBy = "road")
    public Collection<LocationEntity> getLocations() {
        return locations;
    }

    public void setLocations(Collection<LocationEntity> locations) {
        this.locations = locations;
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
}
