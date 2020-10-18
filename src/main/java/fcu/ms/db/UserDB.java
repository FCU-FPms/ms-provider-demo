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
        List<User> users = new ArrayList<User>();

        String sqlString = "select * from userData";
        try {
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                User user = new User(id, userPhone, userName, userPassword);
                users.add(user);
            }
            rs.close();
            preStmt.close();
            connection.close();


        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return users;
    }

    public User getUser(int userId) {
        User user = null;
        String sqlString = "select * from userData where userID=?";
        try {
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                user = new User(id, userPhone, userName, userPassword);
            }
            rs.close();
            preStmt.close();
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return user;

    }

    public User getUser(String name) {

        String sqlString = "select * from userData where userName=?";
        try {
            User user = null;
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, name);
            ResultSet rs = preStmt.executeQuery();
            while ( rs.next() ) {
                int id = rs.getInt("userID");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                user = new User(id, userPhone, userName, userPassword);
            }
            rs.close();
            preStmt.close();
            connection.close();

            return user;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public boolean createUser(String name, String userPhone, String userPassword) {

        boolean is_success = false;
        String sqlString = "INSERT INTO userData(userPhone,userName,userPassword) VALUES(?, ?, ?)";
        try {
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, userPhone);
            preStmt.setString(2, name);
            preStmt.setString(3, userPassword);
            preStmt.executeUpdate();
            preStmt.close();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return is_success;
    }

    public boolean deleteUser(String name) {

        boolean is_success = false;
        String sqlString = "DELETE FROM `userData` WHERE userName=?";
        try {
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, name);
            preStmt.executeUpdate();
            preStmt.close();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return is_success;
    }

    public boolean deleteUser(int userId) {

        boolean is_success = false;
        String sqlString = "DELETE FROM `userData` WHERE userId=?";
        try {
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            preStmt.executeUpdate();
            preStmt.close();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return is_success;
    }
}
