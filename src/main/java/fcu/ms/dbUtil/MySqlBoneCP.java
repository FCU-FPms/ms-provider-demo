package fcu.ms.dbUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class MySqlBoneCP {
    private static MySqlBoneCP instance = new MySqlBoneCP();
    private BoneCP connectionPool;

    private MySqlBoneCP() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // load the DB driver
            Properties dbUrl = get_db_properties("/jdbc-url.properties");

            Properties dbSecret = get_db_properties("/jdbc.properties");

            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(dbUrl.getProperty("db.url") + "?autoReconnect=true&useSSL=false");
            config.setUsername(dbSecret.getProperty("db.user"));
            config.setPassword(dbSecret.getProperty("db.password"));

            connectionPool = new BoneCP(config);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return instance.connectionPool.getConnection();
    }


    private static Properties get_db_properties(String file_path) {
        Properties props = new Properties();
        try {
            InputStream in = MySqlBoneCP.class.getResourceAsStream(file_path);
            props.load(in);
            return props;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        return props;
    }

}
