package logic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 3:38:33
 */

@Table(name = "repair", catalog = "rzd", schema = "")
@Entity
public class RepairEntity {

    

    private int idRepair;

    @Column(name = "id_repair", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue
    public int getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(int idRepair) {
        this.idRepair = idRepair;
    }

    private Timestamp dateBegin;

    @Column(name = "date_begin", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Timestamp dateBegin) {
        this.dateBegin = dateBegin;
    }

    private Timestamp dateEnd;

    @Column(name = "date_end", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepairEntity that = (RepairEntity) o;

        if (idRepair != that.idRepair) return false;
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRepair;
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        return result;
    }

    private Collection<CarHistoryEntity> carHistories;

    @OneToMany(mappedBy = "repair")
    public Collection<CarHistoryEntity> getCarHistories() {
        return carHistories;
    }

    public void setCarHistories(Collection<CarHistoryEntity> carHistories) {
        this.carHistories = carHistories;
    }

    private CarEntity car;

    @ManyToOne
    public
    @JoinColumn(name = "car_number", referencedColumnName = "car_number", nullable = false)
    CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    private RepairTypeEntity repairType;

    @ManyToOne
    public
    @JoinColumn(name = "id_type", referencedColumnName = "id_type", nullable = false)
    RepairTypeEntity getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairTypeEntity repairType) {
        this.repairType = repairType;
    }

    private RoadEntity road;

    @ManyToOne
    public
    @JoinColumn(name = "id_road", referencedColumnName = "id_road")
    RoadEntity getRoad() {
        return road;
    }

    public void setRoad(RoadEntity road) {
        this.road = road;
    }
}
