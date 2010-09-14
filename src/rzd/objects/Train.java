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
    private int id;
    private Date dtDeparture;
    private Date dtDestination;

    public Train(int id, Date dtDeparture, Date dtDestination) {
        this.id = id;
        this.dtDeparture = dtDeparture;
        this.dtDestination = dtDestination;
      }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDtDeparture() {
        return dtDeparture;
    }

    public void setDtDeparture(Date dtDeparture) {
        this.dtDeparture = dtDeparture;
    }

    public Date getDtDestination() {
        return dtDestination;
    }

    public void setDtDestination(Date dtDestination) {
        this.dtDestination = dtDestination;
    }
           
}
