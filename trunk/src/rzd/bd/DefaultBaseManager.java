package rzd.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public class DefaultBaseManager {
  
    public DefaultBaseManager() {

    }

    //Возврощает лист объектных массивов
    public ArrayList getMatrixObject(String query) throws Exception {
        ArrayList data = null;
        ArrayList rows = null;
        ResultSet result = null;
        Connection c = null;
        try {
            c = ConnectBD.get().getConnect();
            result = ConnectBD.get().getStatment(c).executeQuery(query);
            if (!result.next()) return null;
            data = new ArrayList(0);
            while (true) {
                rows = new ArrayList();
                for (int k = 1; k <= result.getMetaData().getColumnCount(); k++) {
                    rows.add(result.getObject(k));
                }
                data.add(rows.toArray());
                if (!result.next()) break;
            }
            return (data.size() > 0) ? data : null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) result.close();
            if (c != null) ConnectBD.get().disconnect(c);
        }
    }

    //Возврощает объектный массив
    public Object[] getRowsObject(String query) throws Exception {
        ArrayList rows = null;
        ResultSet result = null;
        Connection c = null;
        try {
            c = ConnectBD.get().getConnect();
            result = ConnectBD.get().getStatment(c).executeQuery(query);
            if (!result.next()) return null;
            rows = new ArrayList(0);
            for (int k = 1; k <= result.getMetaData().getColumnCount(); k++) {
                rows.add(result.getObject(k));
            }
            return (rows.size() > 0) ? rows.toArray() : null;
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) result.close();
            if (c != null) ConnectBD.get().disconnect(c);
        }
    }

    //Возврощает объект
    public Object getObject(String query) throws Exception {
        Connection c = null;
        ResultSet result = null;
        Object o = null;
        try {
            c = ConnectBD.get().getConnect();
            result = ConnectBD.get().getStatment(c).executeQuery(query);
            if (!result.next()) return null;
            return result.getObject(1);
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) result.close();
            if (c != null) ConnectBD.get().disconnect(c);
        }
    }

    //Обновляет состояние БД
    public int update(String s) throws Exception {
        Connection c = null;
        c = ConnectBD.get().getConnect();
        int res = -1;
        try {
            return ConnectBD.get().getStatment(c).executeUpdate(s);
        } catch (Exception e) {
            throw e;
        } finally {
            if (c != null) ConnectBD.get().disconnect(c);
        }
    }
}
