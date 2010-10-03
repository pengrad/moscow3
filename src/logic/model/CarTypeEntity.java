package logic.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 3:38:32
 */

@Table(name = "car_type", catalog = "rzd", schema = "")
@Entity
public class CarTypeEntity {


    private int idType;

    @Column(name = "id_type", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    private String cType;

    @Column(name = "c_type", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
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

        CarTypeEntity that = (CarTypeEntity) o;

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

    private Collection<CarEntity> cars;

    @OneToMany(mappedBy = "carType")
    public Collection<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Collection<CarEntity> cars) {
        this.cars = cars;
    }

    private CarTypeEntity carParentType;

    @ManyToOne
    public
    @JoinColumn(name = "id_parent_type", referencedColumnName = "id_type")
    CarTypeEntity getCarParentType() {
        return carParentType;
    }

    public void setCarParentType(CarTypeEntity carParentType) {
        this.carParentType = carParentType;
    }

    private Collection<CarTypeEntity> carSubTypes;

    @OneToMany(mappedBy = "carParentType")
    public Collection<CarTypeEntity> getCarSubTypes() {
        return carSubTypes;
    }

    public void setCarSubTypes(Collection<CarTypeEntity> carSubTypes) {
        this.carSubTypes = carSubTypes;
    }
}
