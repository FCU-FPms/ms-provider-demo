package fcu.ms.dbUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySqlConnection {
    public Connection getDBConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties props = get_db_properties("/jdbc.properties");

            con = DriverManager.getConnection(props.getProperty("db.url") + "?useSSL=false",
                    props.getProperty("db.user"), props.getProperty("db.password") );

        } catch(Exception ex){
            System.out.println("Error: "+ex);
        }
        return con;
    }

    private Properties get_db_properties(String file_path) {
        Properties props = new Properties();
        try {
            InputStream in = getClass().getResourceAsStream(file_path);
            props.load(in);
            return props;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        return props;
    }
}

