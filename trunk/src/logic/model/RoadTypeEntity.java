package logic.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:37
 */

@javax.persistence.Table(name = "road_type", catalog = "rzd")
@Entity
public class RoadTypeEntity {


    private int idType;

    @javax.persistence.Column(name = "id_type", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    private String typeName;

    @javax.persistence.Column(name = "type_name", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoadTypeEntity that = (RoadTypeEntity) o;

        if (idType != that.idType) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idType;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }

    private Collection<RoadEntity> roads;

    @OneToMany(mappedBy = "roadType")
    public Collection<RoadEntity> getRoads() {
        return roads;
    }

    public void setRoads(Collection<RoadEntity> roads) {
        this.roads = roads;
    }
}
