/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.model.objects;

/**
 *
 * @author ЧерныхЕА
 */
public class Car {

    private int number;
    private CarLocation location;

    public Car(int number, CarLocation carLocation) {
        this.number = number;
        this.location = carLocation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CarLocation getCarLocation() {
        return location;
    }

    public void setCarLocation(CarLocation carLocation) {
        this.location = carLocation;
    }
}
