package rzd.carsFleet;

import rzd.ModelTable;
import rzd.bd.Base;
import rzd.objects.Car;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 10.09.2010
 * Time: 16:50:30
 * To change this template use File | Settings | File Templates.
 */
public class Controller implements MouseListener, ActionListener {

    private PCars pCarFleet;
    private JPopupMenu popCarMenu;
    private JPopupMenu popCarInfMenu;
    private JPopupMenu popHistLocationCar;
    private JMenuItem editCar;
    private JMenuItem histLocationCar;
    private JMenuItem deleteCar;
    private JMenuItem locationCar;
    private PCarInformation carInformation;
    private DCarEditInf carEditInf;

    public Controller(PCars p) {
        this.pCarFleet = p;
        carEditInf = new DCarEditInf(null, true);
        carInformation = new PCarInformation();
        popCarMenu = new JPopupMenu();
        popCarInfMenu = new JPopupMenu();
        popCarInfMenu.add(carInformation);
        popHistLocationCar = new JPopupMenu();
        popHistLocationCar.add(new PHistory());
        locationCar = new JMenuItem("Изменить местоположения вагона", new ImageIcon(getClass().getResource("/rzd/resurce/bt11.gif")));
        locationCar.addActionListener(this);
        editCar = new JMenuItem("Редактировать информацию о вагоне", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editCar.addActionListener(this);
        deleteCar = new JMenuItem("Удалить вагон", new ImageIcon(getClass().getResource("/rzd/resurce/bt12.gif")));
        deleteCar.addActionListener(this);
        histLocationCar = new JMenuItem("История местоположения вагон", new ImageIcon(getClass().getResource("/rzd/resurce/bt4.gif")));
        histLocationCar.addActionListener(this);
        popCarMenu.add(locationCar);
        popCarMenu.add(editCar);
        popCarMenu.add(deleteCar);
        popCarMenu.add(histLocationCar);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pCarFleet.bAddCar) {
            update();
        } else if (e.getSource() == editCar) {
            editCar();
        } else if (e.getSource() == deleteCar) {
            deleteCar();
        } else if (e.getSource() == locationCar) {
            locationCar();
        } else if (e.getSource() == histLocationCar) {
          histLocationCar();
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == pCarFleet.tCars && e.getButton() == 3) {
            int row = pCarFleet.tCars.rowAtPoint(e.getPoint());
            pCarFleet.tCars.addRowSelectionInterval(row, row);
             popCarMenu.show(pCarFleet.tCars, e.getX(), e.getY());
        }
        if (e.getSource() == pCarFleet.tCars && e.getButton() == 1 && e.getClickCount() == 2) {
            popCarInfMenu.show(pCarFleet.tCars, e.getX(), e.getY());
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }

    //Обновляем внешний вид (таблицу)

    public void update() {
        try {
            ArrayList date = Base.getInstance().getCarsAll();
            if (date != null) {
                JTable t = pCarFleet.tCars;
                ModelTable mt = (ModelTable) t.getModel();
                mt.setDate(date);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(pCarFleet, ex.getMessage());
        }
    }




    private void createCar() {
        carEditInf.open(new Car());
    }

    private void editCar() {
        carEditInf.open(new Car());

    }

    private void deleteCar() {

    }

    private void locationCar() {

    }
    private void histLocationCar() {
        System.out.println("***");
     popHistLocationCar.show(pCarFleet,popCarMenu.getX(),popCarMenu.getY());
    }

}
