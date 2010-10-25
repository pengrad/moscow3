package rzd.test;

import rzd.model.Model;
import rzd.model.objects.Car;
import rzd.model.objects.RoadType;

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
//        ArrayList<Car> cars = Model.getModel().getCars();
//        System.out.println("car size="+cars.size());
        for (int i = 0; i < 100; i++) {
//            ArrayList<RoadType> roadTypes = Model.getModel().getRoadTypes();
            ArrayList<Car> carsAll = Model.getModel().getFreeCars();
                          if(carsAll==null);
            System.out.println("*****");

        }
    }
}
