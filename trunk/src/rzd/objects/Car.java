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

    private int id =-1;
    private int idRoad=-1;
    private int idTrain=-1;
    private String number="00000000";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRoad() {
        return idRoad;
    }

    public void setIdRoad(int idRoad) {
        this.idRoad = idRoad;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
