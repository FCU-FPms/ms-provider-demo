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
        String sqlString = "INSERT INTO `Task`(`TaskName`," +
                " `Message`," +
                " `StartPostTime`," +
                " `EndPostTime`," +
                " `Salary`," +
                " `TypeName`," +
                " `ReleaseUserID`," +
                " `ReleaseTime`," +
                " `ReceiveUserID`," +
                " `ReceiveTime`," +
                " `TaskAddress`," +
                " `TaskCity`)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        boolean is_success;
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);

            preStmt.setString(1, task.getTaskName());
            preStmt.setString(2, task.getMessage());
            preStmt.setTimestamp(3, Timestamp.valueOf(task.getStartPostTime()));
            preStmt.setTimestamp(4, Timestamp.valueOf(task.getEndPostTime()));
            preStmt.setInt(5, task.getSalary());
            preStmt.setString(6,task.getTypeName());
            preStmt.setInt(7,task.getReleaseUserID());
            preStmt.setTimestamp(8, Timestamp.valueOf(task.getReleaseTime()));
            preStmt.setInt(9,task.getReceiveUserID());
            preStmt.setTimestamp(10, Timestamp.valueOf(task.getReceiveTime()));
            preStmt.setString(11,task.getTaskAddress());
            preStmt.setInt(12,task.getTaskCity());

            preStmt.executeUpdate();
            preStmt.close();
            connection.close();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            is_success = false;
        }
        return is_success;
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        String sqlString = "SELECT * FROM `Task`";
        try {
            Connection connection = MySqlBoneCP.getConnection();
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
        String sqlString = "SELECT `TaskID` FROM `Task` WHERE `TaskName` = ?";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskName);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("TaskID");
            }
            rs.close();
            preStmt.close();
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        return id;
    }


    public Task getTask(int taskId) {
        Task task = null;
        String sqlString = "SELECT * FROM `Task` WHERE `taskID` = ?";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                task = parseTaskFromDbColumn(rs);
            }
            rs.close();
            preStmt.close();
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return task;

    }

    public boolean setTask(Task task){
        boolean is_success = false;
        try {
            int taskId = task.getTaskID();
            setTaskName(task.getTaskName(), taskId);
            setTaskMessage(task.getMessage(), taskId);
            setTaskStartPostTime(task.getStartPostTime(), taskId);
            setTaskEndPostTime(task.getStartPostTime(), taskId);
            // 無法設定 salary
            setTaskTypeName(task.getTypeName(), taskId);
            // 無法設定 releaseUserID
            // 無法設定 releaseTime
            setTaskReceiveUserID(task.getReceiveUserID(), taskId);
            setTaskReceiveTime(task.getReceiveTime(), taskId);
            setTaskAddress(task.getTaskAddress(), taskId);
            setTaskCity(task.getTaskCity(), taskId);

            is_success = true;
        }catch (Exception ex){
            System.out.println("Error: " + ex);
            is_success = false;
        }
        return is_success;
    }

    private void setTaskName(String taskName, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `TaskName` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setString(1, taskName);
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    private void setTaskMessage(String taskMessage, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `Message` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setString(1, taskMessage);
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    private void setTaskStartPostTime(LocalDateTime taskStartPostTime, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `StartPostTime` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setTimestamp(1, Timestamp.valueOf(taskStartPostTime));
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    private void setTaskEndPostTime(LocalDateTime taskEndPostTime, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `EndPostTime` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setTimestamp(1, Timestamp.valueOf(taskEndPostTime));
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    // 目前沒有修改價錢的函式

    private void setTaskTypeName(String taskTypeName, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `TypeName` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setString(1, taskTypeName);
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    private void setTaskReceiveUserID(int receiveUserID, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `ReceiveUserID` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setInt(1, receiveUserID);
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    private void setTaskReceiveTime(LocalDateTime receiveTime, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `ReceiveTime` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setTimestamp(1, Timestamp.valueOf(receiveTime));
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    private void setTaskAddress(String taskAddress, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `TaskAddress` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setString(1, taskAddress);
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    private void setTaskCity(int taskCity, int taskID) throws Exception {
        Connection connection = MySqlBoneCP.getConnection();
        String sqlString = "UPDATE `Task` SET `TaskCity` = ? WHERE `TaskID` = ?";
        PreparedStatement preStmt = connection.prepareStatement(sqlString);
        preStmt.setInt(1, taskCity);
        preStmt.setInt(2, taskID);

        preStmt.executeUpdate();
        preStmt.close();
        connection.close();
    }

    public boolean deleteTask(int taskID) {

        boolean is_success;
        String sqlString = "DELETE FROM `Task` WHERE `TaskID` = ?";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskID);
            preStmt.executeUpdate();
            preStmt.close();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            is_success = false;
        }
        return is_success;
    }
    private Task parseTaskFromDbColumn(ResultSet dbResult) throws Exception {
        int id = dbResult.getInt("TaskID");
        String TaskName = dbResult.getString("TaskName");
        String Message = dbResult.getString("Message");
        LocalDateTime StartPostTime = dbResult.getTimestamp("StartPostTime").toLocalDateTime();
        LocalDateTime EndPostTime = dbResult.getTimestamp("EndPostTime").toLocalDateTime();
        int Salary = dbResult.getInt("Salary");
        String TypeName = dbResult.getString("TypeName");
        int ReleaseUserID = dbResult.getInt("ReleaseUserID");
        LocalDateTime ReleaseTime = dbResult.getTimestamp("ReleaseTime").toLocalDateTime();
        int ReceiveUserID = dbResult.getInt("ReceiveUserID");
        LocalDateTime ReceiveTime = dbResult.getTimestamp("ReceiveTime").toLocalDateTime();
        String TaskAddress = dbResult.getString("TaskAddress");
        int TaskCity = dbResult.getInt("TaskCity");

        return new Task(id, TaskName, Message, StartPostTime, EndPostTime, Salary, TypeName, ReleaseUserID,
                ReleaseTime, ReceiveUserID, ReceiveTime, TaskAddress, TaskCity);
    }
}
