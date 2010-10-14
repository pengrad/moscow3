package rzd.model.objects;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 03.10.2010
 * Time: 16:39:54
 * To change this template use File | Settings | File Templates.
 */
public class Repair {
    private int idRepair;
    private RepairType repairType;
    private Car car;
    private Road road;
    private Timestamp dateBegin;
    private Timestamp dateEnd;
    private String comment;

    public Repair(int idRepair, RepairType repairType, Car car, Road road, Timestamp dateBegin, Timestamp dateEnd) {
        this.idRepair = idRepair;
        this.repairType = repairType;
        this.car = car;
        this.road = road;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public int getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(int idRepair) {
        this.idRepair = idRepair;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public Timestamp getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Timestamp dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
