/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 10.09.2010
 * Time: 16:38:04
 * To change this template use File | Settings | File Templates.
 */
package rzd.bd;

import java.util.ArrayList;

public class Base extends DefaultBaseManager {
    private static Base ourInstance = new Base();

    public static Base getInstance() {
        return ourInstance;
    }

    private Base() {
        super();
    }

    //Получаем множество типов путей
    public ArrayList getRoadTypes() throws Exception {
        return getMatrixObject("SELECT id,name FROM road_type");
    }

    //Получаем множество путей по типу пути
    public ArrayList getRoadsByType(int idType) throws Exception {
        return getMatrixObject("SELECT id,number FROM road WHERE id_type=" + idType + ";");
    }


    public ArrayList getCarsOnRoad() throws Exception {
        return getMatrixObject("SELECT rt.id,r.id,c.number FROM road_type as rt,road as r,cars as c WHERE " +
                "c.type_location=0 AND " +
                "r.id_type=rt.id AND " +
                "c.id_location=r.id");
    }

    public ArrayList getCarsAll() throws Exception {
        Object[] header = new Object[]{"number", "id_location"};
        ArrayList list = getMatrixObject("SELECT number,id_location FROM Cars");
        if (list != null) {
            list.add(header);
        }
        return list;
    }


    public ArrayList getTrainsAll() throws Exception {
        Object[] header = new Object[]{"Номер", "Пункт отправления", "Пункт прибытия", "Время отправления", "Время прибытия", "Начало действия", "Цикличность"};
        ArrayList list = getMatrixObject("SELECT number,point_of_departure,point_of_destination,time_departure,time_destination,date_begin,cyclic_day FROM train");
        if (list != null) {
            list.add(header);
        }
        return list;
    }

    public int insertTrain(ArrayList data) throws Exception {
        return update("INSERT INTO train SET number=" + data.get(0) + "," +
                "point_of_departure=" + data.get(1) + "," +
                "point_of_destination=" + data.get(2) + "," +
                "time_departure=" + data.get(3) + "," +
                "time_destination=" + data.get(4) + "," +
                "date_begin=" + data.get(5) + "," +
                "cyclic_day=" + data.get(6) + ";");
    }

    public int updateTrain(ArrayList data) throws Exception {
        return update("UPDATE train SET " +
                "point_of_departure=" + data.get(1) + "," +
                "point_of_destination=" + data.get(2) + "," +
                "time_departure=" + data.get(3) + "," +
                "time_destination=" + data.get(4) + "," +
                "date_begin=" + data.get(5) + "," +
                "cyclic_day=" + data.get(6) + " WHERE number=" + data.get(0) + ";");
    }

    public int deleteTrain(int numberTrain) throws Exception {
        return update("DELETE FROM train WHERE number=" + numberTrain + ";");
    }


}
