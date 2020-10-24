package fcu.ms.db;

import fcu.ms.data.User;
import fcu.ms.data.UserBuilder;
import fcu.ms.dbUtil.MySqlBoneCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestTaskUsersDB {

    private static final RequestTaskUsersDB requestTaskUsersDB = new RequestTaskUsersDB();


    public static RequestTaskUsersDB getInstance() {
        return requestTaskUsersDB;
    }

    private RequestTaskUsersDB() {
        // 防止在外部被初始化
    }

    // 加使用者到該task 中
    public boolean addUserToTaskRequestList(int userID, int taskID) {

        String sqlString = "INSERT INTO `request_task_users`" +
                "(`task_id`," +
                " `user_id`)" +
                " VALUES (?, ?)";

        boolean is_success;

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);

            preStmt.setInt(1, taskID);
            preStmt.setInt(2, userID);

            preStmt.executeUpdate();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            is_success = false;
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is_success;

    }

    public boolean removeUserFromTaskRequestList(int userID, int taskID) {
        String sqlString = "DELETE FROM `request_task_users` WHERE `user_id` = ? AND `task_id` = ?";

        boolean is_success;

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);

            preStmt.setInt(1, userID);
            preStmt.setInt(2, taskID);

            preStmt.executeUpdate();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            is_success = false;
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is_success;
    }





    public List<User> getRequestUsers(int taskID) {

        List<User> tasks = new ArrayList<>();

        String sqlString = "SELECT `user`.`id`, `user`.`name` " +
                           "FROM `request_task_users`, `user` " +
                           "WHERE request_task_users.task_id = ? " +
                           "AND `request_task_users`.`user_id` = `user`.`id`" +
                           "ORDER BY `request_task_users`.`id` ASC;";
        try {
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);

            preStmt.setInt(1, taskID);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("id");
                String userName = rs.getString("name");


                User user = UserBuilder.anUser(userID, userName).build();
                tasks.add(user);
            }
            rs.close();
            preStmt.close();
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return tasks;

    }












}
