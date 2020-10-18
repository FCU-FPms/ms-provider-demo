package fcu.ms.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fcu.ms.data.User;
import fcu.ms.dbUtil.MySqlBoneCP;

public class UserDB {

    private static final UserDB userDB = new UserDB();

    public static UserDB getInstance() {
        return userDB;
    }

    private UserDB(){

    }


    public List<User> getUsers() {

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        List<User> users = new ArrayList<User>();

        String sqlString = "select * from userData";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                User user = new User(id, userPhone, userName, userPassword);
                users.add(user);
            }



        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        try {
            rs.close();
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUser(int userId) {
        User user = null;
        String sqlString = "select * from userData where userID=?";

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                user = new User(id, userPhone, userName, userPassword);
            }


        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        try {
            rs.close();
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;

    }

    public User getUser(String name) {
        User user = null;

        String sqlString = "select * from userData where userName=?";

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, name);
            rs = preStmt.executeQuery();
            while ( rs.next() ) {
                int id = rs.getInt("userID");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                user = new User(id, userPhone, userName, userPassword);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        try {
            rs.close();
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;

    }

    public boolean createUser(String name, String userPhone, String userPassword) {

        boolean is_success = false;
        String sqlString = "INSERT INTO userData(userPhone,userName,userPassword) VALUES(?, ?, ?)";

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, userPhone);
            preStmt.setString(2, name);
            preStmt.setString(3, userPassword);
            preStmt.executeUpdate();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is_success;
    }

    public boolean deleteUser(String name) {

        boolean is_success = false;
        String sqlString = "DELETE FROM `userData` WHERE userName=?";

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, name);
            preStmt.executeUpdate();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is_success;
    }

    public boolean deleteUser(int userId) {

        boolean is_success = false;
        String sqlString = "DELETE FROM `userData` WHERE userId=?";

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            preStmt.executeUpdate();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is_success;
    }
}
