package fcu.ms.dbUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnection {
    private static final MySqlConnection mySqlConnection = new MySqlConnection();
    private static Connection connection = getDBConnection();

    public static Connection getSingletonConnection() {
        if ( connection != null ) {
            return connection;
        } else {
            connection = getDBConnection();
            return connection;
        }

    }

    private MySqlConnection() {

    }



    private static Connection getDBConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties dbUrl = get_db_properties("/jdbc-url.properties");
            Properties dbSecret = get_db_properties("/jdbc.properties");

            con = DriverManager.getConnection(dbUrl.getProperty("db.url") + "?useSSL=false",
                    dbSecret.getProperty("db.user"), dbSecret.getProperty("db.password") );

        } catch(Exception ex){
            System.out.println("Error: "+ex);
        }
        return con;
    }

    private static Properties get_db_properties(String file_path) {
        Properties props = new Properties();
        try {
            InputStream in = MySqlConnection.class.getResourceAsStream(file_path);
            props.load(in);
            return props;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        return props;
    }

    private static void close() throws SQLException {

        if ( connection != null ) {
            connection.close();
            connection = null;
        }

    }
}

