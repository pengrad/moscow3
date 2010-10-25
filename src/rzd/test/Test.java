package rzd.test;

import rzd.model.Model;
import rzd.model.objects.Car;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 25.10.2010
 * Time: 21:54:20
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Car> cars = Model.getModel().getCars();

    }
}
