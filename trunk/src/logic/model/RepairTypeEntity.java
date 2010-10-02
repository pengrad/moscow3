package logic.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 3:38:33
 */

@javax.persistence.Table(name = "repair_type", catalog = "rzd")
@Entity
public class RepairTypeEntity {


    private int idType;

    @javax.persistence.Column(name = "id_type", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    private String cType;

    @javax.persistence.Column(name = "c_type", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepairTypeEntity that = (RepairTypeEntity) o;

        if (idType != that.idType) return false;
        if (cType != null ? !cType.equals(that.cType) : that.cType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idType;
        result = 31 * result + (cType != null ? cType.hashCode() : 0);
        return result;
    }

    private Collection<RepairEntity> repairs;

    @OneToMany(mappedBy = "repairType")
    public Collection<RepairEntity> getRepairs() {
        return repairs;
    }

    public void setRepairs(Collection<RepairEntity> repairs) {
        this.repairs = repairs;
    }
}
