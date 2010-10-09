/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.model.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ЧерныхЕА
 */
public class Train {
    private int id;
    private Date dtDeparture;
    private Date dtDestination;
    private String chief;
    private Route route;
    private TrainStatus trainStatus;
    private Road road;
    private ArrayList<Car> carsIn;

    public Train(int id, Date dtDeparture, Date dtDestination, String chief, Route route, TrainStatus trainStatus, Road road, ArrayList<Car> carsIn) {
        this.id = id;
        this.dtDeparture = dtDeparture;
        this.dtDestination = dtDestination;
        this.chief = chief;
        this.route = route;
        this.trainStatus = trainStatus;
        this.road = road;
        this.carsIn = carsIn;
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public TrainStatus getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(TrainStatus trainStatus) {
        this.trainStatus = trainStatus;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public ArrayList<Car> getCarsIn() {
        return carsIn;
    }

    public void setCarsIn(ArrayList<Car> carsIn) {
        this.carsIn = carsIn;
    }
}
