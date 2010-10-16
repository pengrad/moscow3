/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.model.objects;

import com.sun.org.apache.xpath.internal.operations.Equals;

/**
 * @author ЧерныхЕА
 */
public class Car {

    private int number;
    private String model;
    private CarLocation carLocation;
    private CarType carType;
    private String conditioner;
    private String generator;
    private String generatorPrivod;
    private String accumulator;
    private String electricDevice;
    private String bodyColor;
    private boolean ecologicCoilet;
    private int runNorm;
    private int run;
    private int runTozNorm;
    private int runToz;

    public Car(int number) {
        this.number = number;
    }

    public Car(int number, CarLocation carLocation, CarType carType) {
        this.number = number;
        this.carLocation = carLocation;
        this.carType = carType;
    }

    public Car(int number, String model, CarLocation carLocation, CarType carType, String conditioner, String generator, String generatorPrivod, String accumulator, String electricDevice, String bodyColor, boolean ecologicCoilet, int runNorm, int run, int runTozNorm, int runToz) {
        this.number = number;
        this.model = model;
        this.carLocation = carLocation;
        this.carType = carType;
        this.conditioner = conditioner;
        this.generator = generator;
        this.generatorPrivod = generatorPrivod;
        this.accumulator = accumulator;
        this.electricDevice = electricDevice;
        this.bodyColor = bodyColor;
        this.ecologicCoilet = ecologicCoilet;
        this.runNorm = runNorm;
        this.run = run;
        this.runTozNorm = runTozNorm;
        this.runToz = runToz;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarLocation getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(CarLocation carLocation) {
        this.carLocation = carLocation;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getConditioner() {
        return conditioner;
    }

    public void setConditioner(String conditioner) {
        this.conditioner = conditioner;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getGeneratorPrivod() {
        return generatorPrivod;
    }

    public void setGeneratorPrivod(String generatorPrivod) {
        this.generatorPrivod = generatorPrivod;
    }

    public String getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(String accumulator) {
        this.accumulator = accumulator;
    }

    public String getElectricDevice() {
        return electricDevice;
    }

    public void setElectricDevice(String electricDevice) {
        this.electricDevice = electricDevice;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public boolean isEcologicCoilet() {
        return ecologicCoilet;
    }

    public void setEcologicCoilet(boolean ecologicCoilet) {
        this.ecologicCoilet = ecologicCoilet;
    }

    public int getRunNorm() {
        return runNorm;
    }

    public void setRunNorm(int runNorm) {
        this.runNorm = runNorm;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getRunTozNorm() {
        return runTozNorm;
    }

    public void setRunTozNorm(int runTozNorm) {
        this.runTozNorm = runTozNorm;
    }

    public int getRunToz() {
        return runToz;
    }

    public void setRunToz(int runToz) {
        this.runToz = runToz;
    }

    @Override
    public String toString() {
        return new Integer(number).toString();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Car)) return false;
        if (number == ((Car) o).getNumber()) return true;
        else return false;
    }


}
