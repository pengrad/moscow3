package logic.model;

import javax.persistence.*;

@Table(name = "car_location_history", catalog = "rzd")
@Entity
public class CarLocationHistoryEntity {

    public CarLocationHistoryEntity() {
    }

    public CarLocationHistoryEntity(CarLocationEntity carLocation, CarEntity car) {
        this.carLocationByIdLocation = carLocation;
        this.carByIdCar = car;
        this.idCar = carByIdCar.getNumber();
        this.idLocation = carLocationByIdLocation.getIdLocation();
    }

    private int idCar;

    @Column(name = "id_car", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    private int idLocation;

    @Column(name = "id_location", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarLocationHistoryEntity that = (CarLocationHistoryEntity) o;

        if (idCar != that.idCar) return false;
        if (idLocation != that.idLocation) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCar;
        result = 31 * result + idLocation;
        return result;
    }

    private CarLocationEntity carLocationByIdLocation;

    @ManyToOne
    public
    @JoinColumn(name = "id_location", referencedColumnName = "id_location", nullable = false, insertable = false, updatable = false)
    CarLocationEntity getCarLocationByIdLocation() {
        return carLocationByIdLocation;
    }

    public void setCarLocationByIdLocation(CarLocationEntity carLocationByIdLocation) {
        this.carLocationByIdLocation = carLocationByIdLocation;
        this.idLocation = carLocationByIdLocation.getIdLocation();
    }

    private CarEntity carByIdCar;

    @ManyToOne
    public
    @JoinColumn(name = "id_car", referencedColumnName = "number", nullable = false)
    CarEntity getCarByIdCar() {
        return carByIdCar;
    }

    public void setCarByIdCar(CarEntity carByIdCar) {
        this.carByIdCar = carByIdCar;
        this.idCar = carByIdCar.getNumber();
    }
}
