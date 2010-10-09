package logic.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 1:56:38
 */

@Table(name = "shedule_type", catalog = "rzd")
@Entity
public class SheduleTypeEntity {


    private int idSheduleType;

    @Column(name = "id_shedule_type", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdSheduleType() {
        return idSheduleType;
    }

    public void setIdSheduleType(int idSheduleType) {
        this.idSheduleType = idSheduleType;
    }

    private String cSheduleType;

    @Column(name = "c_shedule_type", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    public String getcSheduleType() {
        return cSheduleType;
    }

    public void setcSheduleType(String cSheduleType) {
        this.cSheduleType = cSheduleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheduleTypeEntity that = (SheduleTypeEntity) o;

        if (idSheduleType != that.idSheduleType) return false;
        if (cSheduleType != null ? !cSheduleType.equals(that.cSheduleType) : that.cSheduleType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSheduleType;
        result = 31 * result + (cSheduleType != null ? cSheduleType.hashCode() : 0);
        return result;
    }

    private Collection<SheduleEntity> shedules;

    @OneToMany(mappedBy = "sheduleType")
    public Collection<SheduleEntity> getShedules() {
        return shedules;
    }

    public void setShedules(Collection<SheduleEntity> shedules) {
        this.shedules = shedules;
    }
}
