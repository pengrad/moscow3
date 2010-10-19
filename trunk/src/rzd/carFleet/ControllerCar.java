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

    private PCar pCarFleet;
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
        this.pCarFleet = p;
        dCarEdit = new DCarEdit(null, true);
        dCarLocation = new DCarLocation(null, true);
        dCarRepair = new DCarRepair(null, true, this);
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
        histLocationCar = new JMenuItem("История местоположения вагон", new ImageIcon(getClass().getResource("/rzd/resurce/bt4.gif")));
        histLocationCar.addActionListener(this);
        popCarMenu.add(viewCar);
        popCarMenu.add(locationCar);
        popCarMenu.add(editCar);
        popCarMenu.add(deleteCar);
        popCarMenu.add(histLocationCar);
        new MakerDefaultTextInField("Поиск по номеру вагона", pCarFleet.fSearch);
        update();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pCarFleet.fSearch || e.getSource() == pCarFleet.bSearch) {
            searchCar();
        } else if (e.getSource() == viewCar) {
            viewCar();
        } else if (e.getSource() == pCarFleet.bAddCar) {
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
        if (e.getSource() == pCarFleet.tCars && e.getButton() == 3) {
            int row = pCarFleet.tCars.rowAtPoint(e.getPoint());
            pCarFleet.tCars.addRowSelectionInterval(row, row);
            Car car = (Car) pCarFleet.tCars.getValueAt(row, 0);
            if (car.getCarLocation().getIdLocation() == BusinessLogic.IN_TRAIN) {
                locationCar.setEnabled(false);
            } else {
                locationCar.setEnabled(true);
            }
            popCarMenu.show(pCarFleet.tCars, e.getX(), e.getY());
        }
        if (e.getSource() == pCarFleet.tCars && e.getButton() == 1 && e.getClickCount() == 2) {
            int row = pCarFleet.tCars.getSelectedRow();
            int number = new Integer(pCarFleet.tCars.getValueAt(row, 0).toString());
            ControllerMain.getInstans().showCarInf(pCarFleet.tCars, e.getX(), e.getY(), number);
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

//    //Обновляем внешний вид (таблицу)
//    public void update() {
//        try {
//            ArrayList<Car> date = TestModel.get().getCars();
//            if (date != null) {
//                JTable t = pCarFleet.tCars;
//                ModelTable mt = (ModelTable) t.getModel();
//                mt.setDate(date);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(pCarFleet, ex.getMessage());
//        }
//    }

    private void searchCar() {
        boolean b = Utils.searchByTable(pCarFleet.tCars, pCarFleet.fSearch.getText(), 0);
        if (!b) {
            JOptionPane.showMessageDialog(pCarFleet, "Ничего не найдено", "Внимание...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewCar() {
        int row = pCarFleet.tCars.getSelectedRow();
        boolean b = ControllerMain.getInstans().searchCar(new Integer(pCarFleet.tCars.getValueAt(row, 0).toString()));
        if (!b) {
            JOptionPane.showMessageDialog(pCarFleet, "Выгон не найден", "Сообщение...", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/rzd/resurce/lightbulb.png")));
        }
    }

    private void addCar() {
        dCarEdit.setLocationRelativeTo(pCarFleet);
        Car car = dCarEdit.open(null);
        if (car != null) {
            boolean b = Model.getModel().addCar(car);
            if (b) {
                JOptionPane.showMessageDialog(pCarFleet, "Ввагон успешно создан.");
                ModelTable mt = (ModelTable) pCarFleet.tCars.getModel();
                mt.setDate(getCarsTabView());
            } else {
                JOptionPane.showMessageDialog(pCarFleet, "Вагон с таким номером уже существует.");
            }
        }
    }

    private void editCar() {
        dCarEdit.setLocationRelativeTo(pCarFleet);
        int row = pCarFleet.tCars.getSelectedRow();
        Car car = (Car) pCarFleet.tCars.getValueAt(row, 0);
        car = dCarEdit.open(car);
        if (car != null) {
            boolean b = Model.getModel().editCar(car);
            if (b) {
                JOptionPane.showMessageDialog(pCarFleet, "Информация о вагоне успешно изменена.");
                update();
            } else {
                JOptionPane.showMessageDialog(pCarFleet, "Вагон с таким номером уже существует.");
            }
        }
    }

    private void deleteCar() {

    }

    private void locationCar() {
        int row = pCarFleet.tCars.getSelectedRow();
        Car car = (Car) pCarFleet.tCars.getValueAt(row, 0);
        if (car.getCarLocation().getIdLocation() == BusinessLogic.REPAIR) {
            locationRepair(car);
        } else {
            locationOther(car);
        }
    }

    private void locationRepair(Car car) {
        dCarRepair.setLocationRelativeTo(pCarFleet);
        Repair repair = dCarRepair.open(Model.getModel().getRepairByCar(car));
        //  System.out.println(repair.getComment());
        if (repair != null) {
            boolean b = Model.getModel().updateRepair(repair);
            if (b) {
                JOptionPane.showMessageDialog(pCarFleet, "Информация о ремонте успешно изменена.");
                update();
            } else {
                JOptionPane.showMessageDialog(pCarFleet, "Ошибка обновлении ниформации о ремонте...");
            }
        }
    }

    private void locationOther(Car car) {
        dCarLocation.setLocationRelativeTo(pCarFleet);
        CarLocationStructure carLS = dCarLocation.open(car);
        if (carLS != null) {
            try {
                boolean b = Model.getModel().setCarLocation(carLS.getCar(), carLS.getRoad(), carLS.getRepair());
                if (b) {
                    JOptionPane.showMessageDialog(pCarFleet, "Информация о вагоне успешно изменена.");
                    update();
                } else {
                    JOptionPane.showMessageDialog(pCarFleet, "Ошибка обновления информации о вагон...");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pCarFleet, e.getMessage(), "Внимание", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    private void histLocationCar() {
        int row = pCarFleet.tCars.getSelectedRow();
        Car car = (Car) pCarFleet.tCars.getValueAt(row, 0);
        pCarHistory.setData(car);
        popHistLocationCar.show(pCarFleet, popCarMenu.getX(), popCarMenu.getY());
    }

    public void update() {
        ModelTable mt;
        mt = (ModelTable) pCarFleet.tCars.getModel();

        mt.setDate(getCarsTabView());
    }

    public void endRepair(Car car) {
        locationOther(car);
    }

    //Методы конверторы

    private ArrayList<Object[]> getCarsTabView() {
        ArrayList<Car> cars = Model.getModel().getCars();
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
