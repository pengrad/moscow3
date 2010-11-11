package rzd.carFleet;

import logic.BusinessLogic;
import rzd.ControllerMain;
import rzd.ModelTable;
import rzd.Updateble;
import rzd.carFleet.hist.PCarHistory;
import rzd.model.objects.Car;
import rzd.model.objects.Repair;
import rzd.model.objects.structure.CarLocationStructure;
import rzd.utils.MakerDefaultTextInField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import rzd.model.Model;
import rzd.utils.Utils;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 10.09.2010
 * Time: 16:50:30
 * To change this template use File | Settings | File Templates.
 */
public class ControllerCar implements MouseListener, ActionListener, Updateble {

    private PCar pCar;
    private JPopupMenu popCarMenu;
    private JPopupMenu popHistLocationCar;
    private JMenuItem viewCar;
    private JMenuItem editCar;
    private JMenuItem histLocationCar;
    private JMenuItem deleteCar;
    private JMenuItem locationCar;
    private DCarEdit dCarEdit;
    private DCarLocation dCarLocation;
    private DCarRepair dCarRepair;
    private PCarHistory pCarHistory;

    public ControllerCar(PCar p) {
        this.pCar = p;
        dCarEdit = new DCarEdit(null, true);
        dCarLocation = new DCarLocation(ControllerMain.getInstans().getMF(), true);
        dCarRepair = new DCarRepair(ControllerMain.getInstans().getMF(), true, this);
        popCarMenu = new JPopupMenu();
        popHistLocationCar = new JPopupMenu();
        pCarHistory = new PCarHistory();
        popHistLocationCar.add(pCarHistory);
        viewCar = new JMenuItem("Посмотреть на карте станции", new ImageIcon(getClass().getResource("/rzd/resurce/eye.png")));
        viewCar.addActionListener(this);
        locationCar = new JMenuItem("Изменить местоположения вагона", new ImageIcon(getClass().getResource("/rzd/resurce/bt11.gif")));
        locationCar.addActionListener(this);
        editCar = new JMenuItem("Редактировать информацию о вагоне", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editCar.addActionListener(this);
        deleteCar = new JMenuItem("Удалить вагон", new ImageIcon(getClass().getResource("/rzd/resurce/bt12.gif")));
        deleteCar.addActionListener(this);
        histLocationCar = new JMenuItem("История местоположения вагон", new ImageIcon(getClass().getResource("/rzd/resurce/history.png")));
        histLocationCar.addActionListener(this);
        popCarMenu.add(viewCar);
        popCarMenu.add(locationCar);
        popCarMenu.add(editCar);
        popCarMenu.add(deleteCar);
        popCarMenu.add(histLocationCar);
        new MakerDefaultTextInField("Поиск по номеру вагона", pCar.fSearch);

        pCar.tCars.addMouseListener(this);
        pCar.bAddCar.addActionListener(this);
        pCar.fSearch.addActionListener(this);
        pCar.bSearch.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pCar.fSearch || e.getSource() == pCar.bSearch) {
            searchCar();
        } else if (e.getSource() == viewCar) {
            viewCar();
        } else if (e.getSource() == pCar.bAddCar) {
            addCar();
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
        if (e.getSource() == pCar.tCars && e.getButton() == 3) {
            int row = pCar.tCars.rowAtPoint(e.getPoint());
            pCar.tCars.addRowSelectionInterval(row, row);
            Car car = (Car) pCar.tCars.getValueAt(row, 0);
            if (car.getCarLocation().getIdLocation() == BusinessLogic.IN_TRAIN) {
                locationCar.setEnabled(false);
            } else {
                locationCar.setEnabled(true);
            }
            popCarMenu.show(pCar.tCars, e.getX(), e.getY());
        }
        if (e.getSource() == pCar.tCars && e.getButton() == 1 && e.getClickCount() == 2) {
            int row = pCar.tCars.getSelectedRow();
            int number = new Integer(pCar.tCars.getValueAt(row, 0).toString());
            ControllerMain.getInstans().showCarInf(pCar.tCars, e.getX(), e.getY(), number);
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


    private void searchCar() {
        boolean b = Utils.searchByTable(pCar.tCars, pCar.fSearch.getText(), 0);
        if (!b) {
            JOptionPane.showMessageDialog(pCar, "Ничего не найдено", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewCar() {
        int row = pCar.tCars.getSelectedRow();
        boolean b = ControllerMain.getInstans().searchCar(new Integer(pCar.tCars.getValueAt(row, 0).toString()));
        if (!b) {
            JOptionPane.showMessageDialog(pCar, "Выгон не найден", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/rzd/resurce/lightbulb.png")));
        }
    }

    private void addCar() {
        dCarEdit.setLocationRelativeTo(pCar);
        Car car = dCarEdit.open(null);
        if (car != null) {
            boolean b = Model.getModel().addCar(car);
            if (b) {
                JOptionPane.showMessageDialog(pCar, "Ввагон успешно создан.");
                ControllerMain.getInstans().update(this);
            } else {
                JOptionPane.showMessageDialog(pCar, "Вагон с таким номером уже существует.", "", JOptionPane.ERROR);
            }
        }
    }

    private void editCar() {
        dCarEdit.setLocationRelativeTo(pCar);
        int row = pCar.tCars.getSelectedRow();
        Car car = (Car) pCar.tCars.getValueAt(row, 0);
        car = dCarEdit.open(car);
        if (car != null) {
            boolean b = Model.getModel().editCar(car);
            if (b) {
                JOptionPane.showMessageDialog(pCar, "Информация о вагоне успешно изменена.", "", JOptionPane.INFORMATION_MESSAGE);
                ControllerMain.getInstans().update(this);
            } else {
                JOptionPane.showMessageDialog(pCar, "Вагон с таким номером уже существует.", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteCar() {
        int row = pCar.tCars.getSelectedRow();
        if (row >= 0) {
            Car car = (Car) pCar.tCars.getValueAt(row, 0);
            if (car != null) {
                int i = JOptionPane.showConfirmDialog(pCar, "Вы уверены, что хотите удалить вагон?", "", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    boolean b = Model.getModel().deleteCar(car);
                    if (b) {
                        JOptionPane.showMessageDialog(pCar, "Вагон успешно удален", "", JOptionPane.INFORMATION_MESSAGE);
                        ControllerMain.getInstans().update(this);
                    }
                }
            }
        }
    }

    private void locationCar() {
        int row = pCar.tCars.getSelectedRow();
        Car car = (Car) pCar.tCars.getValueAt(row, 0);
        if (car.getCarLocation().getIdLocation() == BusinessLogic.REPAIR) {
            locationRepair(car);
        } else {
            locationOther(car);
        }
    }

    private void locationRepair(Car car) {
        dCarRepair.setLocationRelativeTo(pCar);
        Repair repair = dCarRepair.open(Model.getModel().getRepairByCar(car));
        if (repair != null) {
            // System.out.println(repair.getRoad().getName());
            boolean b = Model.getModel().updateRepair(repair);
            if (b) {
                JOptionPane.showMessageDialog(pCar, "Информация о ремонте успешно изменена.", "", JOptionPane.INFORMATION_MESSAGE);
                ControllerMain.getInstans().update(this);
            } else {
                JOptionPane.showMessageDialog(pCar, "Ошибка обновлении ниформации о ремонте...", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void locationOther(Car car) {
        dCarLocation.setLocationRelativeTo(pCar);
        CarLocationStructure carLS = dCarLocation.open(car);
        if (carLS != null) {
            try {
                boolean b = Model.getModel().setCarLocation(carLS.getCar(), carLS.getRoad(), carLS.getRepair());
                if (b) {
                    JOptionPane.showMessageDialog(pCar, "Информация о вагоне успешно изменена.", "", JOptionPane.INFORMATION_MESSAGE);
                    ControllerMain.getInstans().update(this);
                } else {
                    JOptionPane.showMessageDialog(pCar, "Ошибка обновления информации о вагоне...", "Внимание", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pCar, e.getMessage(), "Внимание", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void histLocationCar() {
        int row = pCar.tCars.getSelectedRow();
        Car car = (Car) pCar.tCars.getValueAt(row, 0);
        pCarHistory.setData(car);
        popHistLocationCar.show(pCar, popCarMenu.getX(), popCarMenu.getY());
    }

    public void update() {
        ModelTable mt;
        mt = (ModelTable) pCar.tCars.getModel();
        mt.setDate(getCarsTabView());
    }

    public Component getPanel() {
        return pCar;
    }

    public void endRepair(Car car) {
        locationOther(car);
    }

    //Методы конверторы

    private ArrayList<Object[]> getCarsTabView() {
        //  System.out.println("update car");
        ArrayList<Car> cars = Model.getModel().getCars();
        // System.out.println("cars size="+cars.size());
        if (cars != null) {
            ArrayList<Object[]> res = new ArrayList<Object[]>(cars.size());
            for (Car c : cars) {
                Object[] o = new Object[4];
                o[0] = c;
                o[1] = c.getCarType().getType();
                o[2] = c.getModel();
                o[3] = c.getCarLocation().toString();
                res.add(o);
            }
            res.add(new Object[]{"Номер", "Тип", "Моедль", "Местоположение"});
            return res;
        }
        return null;
    }

}
