/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.objects;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author ЧерныхЕА
 */
public class Train {
    //Номер поезда
    private int number;
    //Пункт отправления
    private String pointOfDeparture;
    //Пункт прибытия
    private String pointOfDestination;
    //Дата и время отправления
    private Date dtDeparture;
    //Дата и время прибытия
    private Date dtDestination;
    //Цикл действия расписания
    private ArrayList<Car> cars;

    public Train(int number, String pointOfDeparture, String pointOfDestination, Date dtDeparture, Date dtDestination, ArrayList<Car> cars) {
        this.number = number;
        this.pointOfDeparture = pointOfDeparture;
        this.pointOfDestination = pointOfDestination;
        this.dtDeparture = dtDeparture;
        this.dtDestination = dtDestination;
        this.cars = cars;
    }

}
