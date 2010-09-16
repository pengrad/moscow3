package logic.model;

import javax.persistence.*;
import java.util.Collection;

@javax.persistence.Table(name = "road", catalog = "rzd")
@Entity
public class RoadEntity {

    public RoadEntity() {
    }

    

    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Id
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

        if (id != that.id) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    private Collection<CarLocationEntity> carLocationsById;

    @OneToMany(mappedBy = "roadByIdRoad")
    public Collection<CarLocationEntity> getCarLocationsById() {
        return carLocationsById;
    }

    public void setCarLocationsById(Collection<CarLocationEntity> carLocationsById) {
        this.carLocationsById = carLocationsById;
    }

    private RoadTypeEntity roadTypeByIdType;

    @ManyToOne
    public
    @JoinColumn(name = "id_type", referencedColumnName = "id", nullable = false)
    RoadTypeEntity getRoadTypeByIdType() {
        return roadTypeByIdType;
    }

    public void setRoadTypeByIdType(RoadTypeEntity roadTypeByIdType) {
        this.roadTypeByIdType = roadTypeByIdType;
    }
}
