package logic.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User: Стас
 * Date: 27.09.2010
 * Time: 2:28:11
 */

public class SheduleDaysEntityPK implements Serializable {


    private int idShedule;

    @Id
    @Column(name = "id_shedule", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public int getIdShedule() {
        return idShedule;
    }

    public void setIdShedule(int idShedule) {
        this.idShedule = idShedule;
    }

    private int day;

    @Id
    @Column(name = "day", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SheduleDaysEntityPK that = (SheduleDaysEntityPK) o;

        if (day != that.day) return false;
        if (idShedule != that.idShedule) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idShedule;
        result = 31 * result + day;
        return result;
    }

    @Override
    public String toString() {
        return idShedule + ":" + day;
    }
}
