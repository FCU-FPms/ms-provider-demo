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

        String sqlString = "SELECT * FROM `user`";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                User user = parseUserFromDbColumn(rs);
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

    public User getUser(int id) {
        User user = null;
        String sqlString = "SELECT * FROM `user` WHERE `id` = ?";

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, id);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                user = parseUserFromDbColumn(rs);
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

        String sqlString = "SELECT * FROM `user` WHERE `name` = ?";

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, name);
            rs = preStmt.executeQuery();
            while ( rs.next() ) {
                user = parseUserFromDbColumn(rs);
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

    public boolean createUser(User user) {

        boolean is_success = false;
        String sqlString = "INSERT INTO `user` (`name`, `phone`, `firebase_uid`) VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, user.getName());
            preStmt.setString(2, user.getPhone());
            preStmt.setString(3, user.getFirebaseUid());
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
        String sqlString = "DELETE FROM `user` WHERE `name` = ?";

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

    public boolean deleteUser(int id) {

        boolean is_success = false;
        String sqlString = "DELETE FROM `user` WHERE `id` = ?";

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, id);
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

    private User parseUserFromDbColumn(ResultSet dbResult) throws Exception {
        int id = dbResult.getInt("id");
        String name = dbResult.getString("name");
        String phone = dbResult.getString("phone");
        String firebase_uid = dbResult.getString("firebase_uid");

        return new User(id, name, phone, firebase_uid);
    }
}
