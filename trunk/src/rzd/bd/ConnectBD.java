package rzd.bd;

import java.sql.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 23.10.2009
 * Time: 11:20:43
 * To change this template use File | Settings | File Templates.
 */
public class ConnectBD {
    private Connection c = null;
    private static Properties properties;

    private final static ConnectBD con = new ConnectBD();

    public static ConnectBD get() {
        properties = new Properties();
        properties.put("user", "admin");
        properties.put("password", "12345");
        properties.put("host", "gid1-2");
        properties.put("port", "3306");
        properties.put("base", "rzd");
        properties.put("pathJdbcDriver", "com.mysql.jdbc.Driver");
        properties.put("characterEncoding", "Cp1251");
        properties.put("useUnicode", "true");
        return con;
    }

    public Connection connect(Properties properties) throws Exception {
        Class.forName(properties.getProperty("pathJdbcDriver"));
        return (Connection) DriverManager.getConnection("jdbc:mysql://" + properties.getProperty("host") + ":" + properties.getProperty("port") +
                "/" + properties.getProperty("base"), properties);
    }

    public Connection getConnect() throws Exception {
        return connect(properties);
    }

    public Statement getStatment(Connection c) throws Exception {
        return c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public PreparedStatement getPreparedStatment(Connection c, String s) throws Exception {
        return c.prepareStatement(s);
    }

    public void disconnect(Connection c) throws SQLException {
        c.close();
    }
}
