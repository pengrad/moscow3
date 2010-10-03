package logic.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: Стас
 * Date: 03.10.2010
 * Time: 16:34:51
 */

@javax.persistence.Table(name = "car", catalog = "rzd")
@Entity
public class CarEntity {

    public CarEntity() {
    }

    public CarEntity(int carNumber, String model, String conditioner, String generator, String generatorPrivod, String accumulator, String electricDevice, String bodyColor, boolean ecologicToilet, int run, int runToz, int runNorm, int runTozNorm, CarLocationEntity carLocation, CarTypeEntity carType) {
        this.carNumber = carNumber;
        this.model = model;
        this.conditioner = conditioner;
        this.generator = generator;
        this.generatorPrivod = generatorPrivod;
        this.accumulator = accumulator;
        this.electricDevice = electricDevice;
        this.bodyColor = bodyColor;
        this.ecologicToilet = ecologicToilet;
        this.run = run;
        this.runToz = runToz;
        this.runNorm = runNorm;
        this.runTozNorm = runTozNorm;
        this.carLocation = carLocation;
        this.carType = carType;
    }

    private int carNumber;

    @javax.persistence.Column(name = "car_number", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    private String model;

    @javax.persistence.Column(name = "model", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String conditioner;

    @javax.persistence.Column(name = "conditioner", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getConditioner() {
        return conditioner;
    }

    public void setConditioner(String conditioner) {
        this.conditioner = conditioner;
    }

    private String generator;

    @javax.persistence.Column(name = "generator", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    private String generatorPrivod;

    @javax.persistence.Column(name = "generator_privod", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getGeneratorPrivod() {
        return generatorPrivod;
    }

    public void setGeneratorPrivod(String generatorPrivod) {
        this.generatorPrivod = generatorPrivod;
    }

    private String accumulator;

    @javax.persistence.Column(name = "accumulator", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(String accumulator) {
        this.accumulator = accumulator;
    }

    private String electricDevice;

    @javax.persistence.Column(name = "electric_device", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getElectricDevice() {
        return electricDevice;
    }

    public void setElectricDevice(String electricDevice) {
        this.electricDevice = electricDevice;
    }

    private String bodyColor;

    @javax.persistence.Column(name = "body_color", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    private boolean ecologicToilet;

    @javax.persistence.Column(name = "ecologic_toilet", nullable = true, insertable = true, updatable = true, length = 1, precision = 0)
    @Basic
    public boolean isEcologicToilet() {
        return ecologicToilet;
    }

    public void setEcologicToilet(boolean ecologicToilet) {
        this.ecologicToilet = ecologicToilet;
    }

    private int run;

    @javax.persistence.Column(name = "run", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    private int runToz;

    @javax.persistence.Column(name = "run_TOZ", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getRunToz() {
        return runToz;
    }

    public void setRunToz(int runToz) {
        this.runToz = runToz;
    }

    private int runNorm;

    @javax.persistence.Column(name = "run_norm", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getRunNorm() {
        return runNorm;
    }

    public void setRunNorm(int runNorm) {
        this.runNorm = runNorm;
    }

    private int runTozNorm;

    @javax.persistence.Column(name = "run_TOZ_norm", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getRunTozNorm() {
        return runTozNorm;
    }

    public void setRunTozNorm(int runTozNorm) {
        this.runTozNorm = runTozNorm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (carNumber != carEntity.carNumber) return false;
        if (ecologicToilet != carEntity.ecologicToilet) return false;
        if (run != carEntity.run) return false;
        if (runNorm != carEntity.runNorm) return false;
        if (runToz != carEntity.runToz) return false;
        if (runTozNorm != carEntity.runTozNorm) return false;
        if (accumulator != null ? !accumulator.equals(carEntity.accumulator) : carEntity.accumulator != null)
            return false;
        if (bodyColor != null ? !bodyColor.equals(carEntity.bodyColor) : carEntity.bodyColor != null) return false;
        if (conditioner != null ? !conditioner.equals(carEntity.conditioner) : carEntity.conditioner != null)
            return false;
        if (electricDevice != null ? !electricDevice.equals(carEntity.electricDevice) : carEntity.electricDevice != null)
            return false;
        if (generator != null ? !generator.equals(carEntity.generator) : carEntity.generator != null) return false;
        if (generatorPrivod != null ? !generatorPrivod.equals(carEntity.generatorPrivod) : carEntity.generatorPrivod != null)
            return false;
        if (model != null ? !model.equals(carEntity.model) : carEntity.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carNumber;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (conditioner != null ? conditioner.hashCode() : 0);
        result = 31 * result + (generator != null ? generator.hashCode() : 0);
        result = 31 * result + (generatorPrivod != null ? generatorPrivod.hashCode() : 0);
        result = 31 * result + (accumulator != null ? accumulator.hashCode() : 0);
        result = 31 * result + (electricDevice != null ? electricDevice.hashCode() : 0);
        result = 31 * result + (bodyColor != null ? bodyColor.hashCode() : 0);
        result = 31 * result + (ecologicToilet ? 1 : 0);
        result = 31 * result + run;
        result = 31 * result + runToz;
        result = 31 * result + runNorm;
        result = 31 * result + runTozNorm;
        return result;
    }

    private CarLocationEntity carLocation;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_location", referencedColumnName = "id_location", nullable = false)
    CarLocationEntity getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(CarLocationEntity carLocation) {
        this.carLocation = carLocation;
    }

    private CarTypeEntity carType;

    @ManyToOne
    public
    @javax.persistence.JoinColumn(name = "id_type", referencedColumnName = "id_type", nullable = false)
    CarTypeEntity getCarType() {
        return carType;
    }

    public void setCarType(CarTypeEntity carType) {
        this.carType = carType;
    }

    private Collection<CarHistoryEntity> carHistories;

    @OneToMany(mappedBy = "car")
    public Collection<CarHistoryEntity> getCarHistories() {
        return carHistories;
    }

    public void setCarHistories(Collection<CarHistoryEntity> carHistories) {
        this.carHistories = carHistories;
    }

    private Collection<RepairEntity> repairs;

    @OneToMany(mappedBy = "car")
    public Collection<RepairEntity> getRepairs() {
        return repairs;
    }

    public void setRepairs(Collection<RepairEntity> repairs) {
        this.repairs = repairs;
    }

    private Collection<RoadDetEntity> roadDets;

    @OneToMany(mappedBy = "car")
    public Collection<RoadDetEntity> getRoadDets() {
        return roadDets;
    }

    public void setRoadDets(Collection<RoadDetEntity> roadDets) {
        this.roadDets = roadDets;
    }

    private Collection<TrainDetEntity> trainDets;

    @OneToMany(mappedBy = "car")
    public Collection<TrainDetEntity> getTrainDets() {
        return trainDets;
    }

    public void setTrainDets(Collection<TrainDetEntity> trainDets) {
        this.trainDets = trainDets;
    }
}
