/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.objects;

/**
 *
 * @author ЧерныхЕА
 */
public class Car {

    private int number;
    private CarLocation carLocation;

    public Car(int number, CarLocation carLocation) {
        this.number = number;
        this.carLocation = carLocation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CarLocation getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(CarLocation carLocation) {
        this.carLocation = carLocation;
    }
}
