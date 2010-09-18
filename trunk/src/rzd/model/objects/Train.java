/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.model.objects;

import java.util.Date;

/**
 * @author ЧерныхЕА
 */
public class Train {
    private int id;
    private Date dtDeparture;
    private Date dtDestination;
    private String chief;

    public Train(int id, Date dtDeparture, Date dtDestination, String chief) {
        this.id = id;
        this.dtDeparture = dtDeparture;
        this.dtDestination = dtDestination;
        this.chief = chief;
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

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }
}
