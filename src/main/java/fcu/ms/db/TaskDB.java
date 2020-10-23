package fcu.ms.db;

import fcu.ms.data.Task;
import fcu.ms.dbUtil.MySqlBoneCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public class TaskDB {
    private static final TaskDB taskDB = new TaskDB();

    public static TaskDB getInstance() {
        return taskDB;
    }

    private TaskDB() {

    }

    public boolean createTask(Task task) {
        String sqlString = "INSERT INTO `task`" +
                "(`name`," +
                " `message`," +
                " `start_post_time`," +
                " `end_post_time`," +
                " `salary`," +
                " `type_name`," +
                " `release_user_id`," +
                " `release_time`," +
                " `receive_user_id`," +
                " `task_address`)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        boolean is_success;

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);

            preStmt.setString(1, task.getName());
            preStmt.setString(2, task.getMessage());
            preStmt.setTimestamp(3, transitLocalDateTime(task.getStartPostTime()));
            preStmt.setTimestamp(4, transitLocalDateTime(task.getEndPostTime()));
            preStmt.setInt(5, task.getSalary());
            preStmt.setString(6,task.getTypeName());
            preStmt.setInt(7,task.getReleaseUserID());
            preStmt.setTimestamp(8, transitLocalDateTime(task.getReleaseTime()));
            preStmt.setInt(9,task.getReceiveUserID());
            preStmt.setString(10, task.getTaskAddress());


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

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        String sqlString = "SELECT * FROM `task`";
        try {
            Connection connection = MySqlBoneCP.getInstance().getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                Task task = parseTaskFromDbColumn(rs);
                tasks.add(task);
            }
            rs.close();
            preStmt.close();
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return tasks;
    }

    public int getTaskIdByName(String taskName) {
        int id = -1;
        String sqlString = "SELECT `id` FROM `task` WHERE `name` = ?";

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskName);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
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

        return id;
    }


    public Task getTask(int taskId) {
        Task task = null;
        String sqlString = "SELECT * FROM `task` WHERE `id` = ?";

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                task = parseTaskFromDbColumn(rs);
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

        return task;

    }

    public boolean setTask(Task task){
        boolean is_success = false;
        try {
            int taskId = task.getTaskID();
            setTaskName(task.getName(), taskId);
            setTaskMessage(task.getMessage(), taskId);
            setTaskStartPostTime(task.getStartPostTime(), taskId);
            setTaskEndPostTime(task.getStartPostTime(), taskId);
            // 無法設定 salary
            setTaskTypeName(task.getTypeName(), taskId);
            // 無法設定 releaseUserID
            // 無法設定 releaseTime
            setTaskReceiveUserID(task.getReceiveUserID(), taskId);
            setTaskAddress(task.getTaskAddress(), taskId);

            is_success = true;
        }catch (Exception ex){
            System.out.println("Error: " + ex);
            is_success = false;
        }
        return is_success;
    }

    private void setTaskName(String taskName, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `task` SET `name` = ? WHERE `id` = ?";

            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskName);
            preStmt.setInt(2, taskID);
            preStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTaskMessage(String taskMessage, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `task` SET `message` = ? WHERE `id` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskMessage);
            preStmt.setInt(2, taskID);

            preStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setTaskStartPostTime(LocalDateTime taskStartPostTime, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `task` SET `start_post_time` = ? WHERE `id` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setTimestamp(1, transitLocalDateTime(taskStartPostTime));
            preStmt.setInt(2, taskID);

            preStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTaskEndPostTime(LocalDateTime taskEndPostTime, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `task` SET `end_post_time` = ? WHERE `id` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setTimestamp(1, transitLocalDateTime(taskEndPostTime));
            preStmt.setInt(2, taskID);

            preStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 目前沒有修改價錢的函式

    private void setTaskTypeName(String taskTypeName, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `task` SET `type_name` = ? WHERE `id` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskTypeName);
            preStmt.setInt(2, taskID);

            preStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 無法修改 release_user_id

    // 無法修改 release_time

    private void setTaskReceiveUserID(int receiveUserID, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `task` SET `receive_user_id` = ? WHERE `id` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, receiveUserID);
            preStmt.setInt(2, taskID);

            preStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setTaskAddress(String taskAddress, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `task` SET `task_address` = ? WHERE `id` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskAddress);
            preStmt.setInt(2, taskID);

            preStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean deleteTask(int taskID) {

        Connection connection = null;
        PreparedStatement preStmt = null;

        boolean is_success;
        String sqlString = "DELETE FROM `task` WHERE `id` = ?";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskID);

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

    private Task parseTaskFromDbColumn(ResultSet dbResult) throws Exception {

        // 10 cols
        int id = dbResult.getInt("id");
        String TaskName = dbResult.getString("name");
        String Message = dbResult.getString("message");
        LocalDateTime StartPostTime = transitTimestamp(dbResult.getTimestamp("start_post_time"));
        LocalDateTime EndPostTime = transitTimestamp(dbResult.getTimestamp("end_post_time"));

        int Salary = dbResult.getInt("salary");
        String TypeName = dbResult.getString("type_name");
        int ReleaseUserID = dbResult.getInt("release_user_id");
        LocalDateTime ReleaseTime = transitTimestamp(dbResult.getTimestamp("release_time"));
        int ReceiveUserID = dbResult.getInt("receive_user_id");
        String TaskAddress = dbResult.getString("task_address");

        return new Task(id, TaskName, Message, StartPostTime, EndPostTime, Salary, TypeName, ReleaseUserID,
                ReleaseTime, ReceiveUserID, TaskAddress);
    }

    private Timestamp transitLocalDateTime(LocalDateTime localDateTime) { // 如果是null 會回傳null
        if(localDateTime != null) {
            return Timestamp.valueOf(localDateTime);
        } else {
            return null;
        }
    }

    private LocalDateTime transitTimestamp(Timestamp timestamp) { // 如果是null 會回傳null
        if(timestamp != null) {
            return timestamp.toLocalDateTime();
        } else {
            return null;
        }
    }
}
