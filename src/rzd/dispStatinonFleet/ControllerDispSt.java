/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.dispStatinonFleet;

import rzd.ControllerMain;
import rzd.ModelTable;
import rzd.Updateble;
import rzd.model.Model;
import rzd.utils.Utils;
import rzd.model.objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author ЧерныхЕА
 */
public class ControllerDispSt implements MouseListener, ActionListener, ItemListener, Updateble {

    private PDispStation pDispStation;
    private JPopupMenu menuTrain;
    private JMenuItem editTrain;
    private JMenuItem viewTrain;
    private JMenuItem destroyTrain;

    private JTable activeTable;
    private DEditTrain dEditTrain;

    public ControllerDispSt(PDispStation pDispStation) {
        this.pDispStation = pDispStation;
        menuTrain = new JPopupMenu();
        //   editTrain = new JMenuItem("Редактировать", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        editTrain = new JMenuItem("Редактировать", new ImageIcon(getClass().getResource("/rzd/resurce/bt5.gif")));
        viewTrain = new JMenuItem("Посмотреть на карте станции", new ImageIcon(getClass().getResource("/rzd/resurce/eye.png")));
        destroyTrain = new JMenuItem("Расформировать", new ImageIcon(getClass().getResource("/rzd/resurce/list-remove.png")));

        menuTrain.add(editTrain);
        menuTrain.add(viewTrain);
        menuTrain.add(destroyTrain);

        editTrain.addActionListener(this);
        viewTrain.addActionListener(this);
        destroyTrain.addActionListener(this);

        dEditTrain = new DEditTrain(null, true);

        pDispStation.tArrivingTrains.addMouseListener(this);
        pDispStation.tGoingTrains.addMouseListener(this);
        pDispStation.tTrainOnRoad.addMouseListener(this);
        pDispStation.cTimeBeforeArrivingTrains.addItemListener(this);
        pDispStation.cTimeBeforeGoingTrains.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == pDispStation.cTimeBeforeArrivingTrains) {
            ControllerMain.getInstans().update(this);
        }
        if (e.getSource() == pDispStation.cTimeBeforeGoingTrains) {
            ControllerMain.getInstans().update(this);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTable && e.getButton() == 3) {
            int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
            if (e.getSource() == pDispStation.tTrainOnRoad) {
                //    editTrain.setText("Редактировать");
                viewTrain.setEnabled(true);
                Train train = (Train) ((JTable) e.getSource()).getValueAt(row, 0);
                destroyTrain.setEnabled(train.getRoute().getSheduleBack().equals(train.getShedule()));
            } else {
                //    editTrain.setText("Поставить на путь");
                viewTrain.setEnabled(false);
                destroyTrain.setEnabled(false);
            }

            ((JTable) e.getSource()).addRowSelectionInterval(row, row);
            menuTrain.show((JTable) e.getSource(), e.getX(), e.getY());
            activeTable = (JTable) e.getSource();
        } else if (e.getSource() instanceof JTable && e.getButton() == 1 && e.getClickCount() == 2) {
            int row = ((JTable) e.getSource()).getSelectedRow();
            int idTrain = new Integer(((JTable) e.getSource()).getValueAt(row, 0).toString());
            ControllerMain.getInstans().showTrainInf((JTable) e.getSource(), e.getX(), e.getY(), idTrain);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editTrain) {
            editTrain(activeTable);
        } else if (e.getSource() == viewTrain) {
            viewTrain(activeTable);
        } else if (e.getSource() == destroyTrain) {
            destroyTrain(activeTable);
        }
    }

    public void update() {
        ArrayList<Train> trainsOnRoad = Model.getModel().getTrainsOnRoads();
        ((ModelTable) pDispStation.tTrainOnRoad.getModel()).setDate(getTrainTabView(trainsOnRoad));
        pDispStation.tTrainOnRoad.getColumnModel().getColumn(0).setMinWidth(0);
        pDispStation.tTrainOnRoad.getColumnModel().getColumn(0).setMaxWidth(0);
        ArrayList<Train> goingTrains = Model.getModel().getGoingTrains(new Integer(pDispStation.cTimeBeforeGoingTrains.getSelectedItem().toString()));
        ((ModelTable) pDispStation.tGoingTrains.getModel()).setDate(getTrainTabView(goingTrains));
        pDispStation.tGoingTrains.getColumnModel().getColumn(0).setMinWidth(0);
        pDispStation.tGoingTrains.getColumnModel().getColumn(0).setMaxWidth(0);
        ArrayList<Train> arrivingTrains = Model.getModel().getArrivingTrains(new Integer(pDispStation.cTimeBeforeArrivingTrains.getSelectedItem().toString()));
        ((ModelTable) pDispStation.tArrivingTrains.getModel()).setDate(getTrainTabView(arrivingTrains));
        pDispStation.tArrivingTrains.getColumnModel().getColumn(0).setMinWidth(0);
        pDispStation.tArrivingTrains.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    public Component getPanel() {
        return pDispStation;
    }

    private void editTrain(JTable activeTab) {
        int row = activeTab.getSelectedRow();
        if (row > -1 && row < activeTab.getRowCount()) {
            dEditTrain.setLocationRelativeTo(pDispStation);
            Train train = (Train) activeTab.getValueAt(row, 0);
            train = dEditTrain.open(train);
            if (train != null) {
                boolean b = false;
                try {
                    b = Model.getModel().makeTrainForGoing(train);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pDispStation, e.getMessage(), "Внимание...", JOptionPane.ERROR_MESSAGE);
                }
                if (b) {
                    JOptionPane.showMessageDialog(pDispStation, "Информация о поезде изменена", "", JOptionPane.INFORMATION_MESSAGE);
                    ControllerMain.getInstans().update(this);
                } else {
                    JOptionPane.showMessageDialog(pDispStation, "Ошибка обновления информации о поезде", "Внимание...", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void viewTrain(JTable activeTab) {
        int row = activeTab.getSelectedRow();
        boolean b = ControllerMain.getInstans().searchTrain(new Integer(activeTab.getValueAt(row, 0).toString()));
        if (!b) {
            JOptionPane.showMessageDialog(pDispStation, "Выгон не найден", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/rzd/resurce/lightbulb.png")));
        }
    }

    private void destroyTrain(JTable activeTab) {
        int row = activeTab.getSelectedRow();
        if (row > -1 && row < activeTab.getRowCount()) {
            Train train = (Train) activeTab.getValueAt(row, 0);
            if (train != null) {
                if (train.getCarsIn() == null || train.getCarsIn().size() == 0) {
                    try {
                        boolean b = Model.getModel().destroyTrain(train);
                        if (b) {
                            JOptionPane.showMessageDialog(pDispStation, "Поезд успешно расформирован", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/rzd/resurce/lightbulb.png")));
                            ControllerMain.getInstans().update(this);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(pDispStation, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(pDispStation, "Отцепите все вагон от поезд, а затем расформируйте его", "Внимание...", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/rzd/resurce/lightbulb.png")));
                }
            }
        }
    }

    //Методы конвертации

    private ArrayList<Object[]> getTrainTabView(ArrayList<Train> trains) {
        if (trains != null) {
            ArrayList<Object[]> data = new ArrayList<Object[]>(trains.size());
            for (Train train : trains) {
                ArrayList<Car> cars = train.getCarsIn();
                String route = "";
                String dt = "";
                if (train.getRoute().getSheduleForward().equals(train.getShedule())) {
                    route = train.getRoute().getNumberForward() + "  " + train.getRoute().getCityFrom() + " - " + train.getRoute().getCityTo();
                    dt = Utils.convertDateTimeToStr(train.getDtDeparture());
                }
                if (train.getRoute().getSheduleBack().equals(train.getShedule())) {
                    route = train.getRoute().getNumberBack() + "  " + train.getRoute().getCityTo() + " - " + train.getRoute().getCityFrom();
                    dt = Utils.convertDateTimeToStr(train.getDtDestination());
                }
                data.add(new Object[]{
                        train,
                        route,
                        dt,
                        train.getChief(),
                        (train.getRoad() != null ? train.getRoad().getName() : "Путь не задан"),
                        ((cars == null) ? 0 : cars.size())
                });
            }
            data.add(new Object[]{"ID", "Маршрут", "Отправление/Прибытие", "Начальник", "Путь", "Кол-во вагонов"});
            return data;
        }
        return null;
    }
}
