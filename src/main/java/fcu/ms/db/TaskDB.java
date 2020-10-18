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

        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);

            preStmt.setString(1, task.getTaskName());
            preStmt.setString(2, task.getMessage());
            preStmt.setTimestamp(3, transitLocalDateTime(task.getStartPostTime()));
            preStmt.setTimestamp(4, transitLocalDateTime(task.getEndPostTime()));
            preStmt.setInt(5, task.getSalary());
            preStmt.setString(6,task.getTypeName());
            preStmt.setInt(7,task.getReleaseUserID());
            preStmt.setTimestamp(8, transitLocalDateTime(task.getReleaseTime()));
            preStmt.setInt(9,task.getReceiveUserID());
            preStmt.setTimestamp(10, transitLocalDateTime(task.getReceiveTime()));
            preStmt.setString(11,task.getTaskAddress());
            preStmt.setInt(12,task.getTaskCity());

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

        String sqlString = "SELECT * FROM `Task`";
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
        String sqlString = "SELECT `TaskID` FROM `Task` WHERE `TaskName` = ?";

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskName);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("TaskID");
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
        String sqlString = "SELECT * FROM `Task` WHERE `taskID` = ?";

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

    private void setTaskName(String taskName, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `Task` SET `TaskName` = ? WHERE `TaskID` = ?";

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
            String sqlString = "UPDATE `Task` SET `Message` = ? WHERE `TaskID` = ?";
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
            String sqlString = "UPDATE `Task` SET `StartPostTime` = ? WHERE `TaskID` = ?";
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
            String sqlString = "UPDATE `Task` SET `EndPostTime` = ? WHERE `TaskID` = ?";
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
            String sqlString = "UPDATE `Task` SET `TypeName` = ? WHERE `TaskID` = ?";
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

    private void setTaskReceiveUserID(int receiveUserID, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `Task` SET `ReceiveUserID` = ? WHERE `TaskID` = ?";
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

    private void setTaskReceiveTime(LocalDateTime receiveTime, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `Task` SET `ReceiveTime` = ? WHERE `TaskID` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setTimestamp(1, transitLocalDateTime(receiveTime));
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
            String sqlString = "UPDATE `Task` SET `TaskAddress` = ? WHERE `TaskID` = ?";
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

    private void setTaskCity(int taskCity, int taskID) {
        Connection connection = null;
        PreparedStatement preStmt = null;

        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            String sqlString = "UPDATE `Task` SET `TaskCity` = ? WHERE `TaskID` = ?";
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskCity);
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
        String sqlString = "DELETE FROM `Task` WHERE `TaskID` = ?";
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
        int id = dbResult.getInt("TaskID");
        String TaskName = dbResult.getString("TaskName");
        String Message = dbResult.getString("Message");
        LocalDateTime StartPostTime = transitTimestamp(dbResult.getTimestamp("StartPostTime"));
        LocalDateTime EndPostTime = transitTimestamp(dbResult.getTimestamp("EndPostTime"));
        int Salary = dbResult.getInt("Salary");
        String TypeName = dbResult.getString("TypeName");
        int ReleaseUserID = dbResult.getInt("ReleaseUserID");
        LocalDateTime ReleaseTime = transitTimestamp(dbResult.getTimestamp("ReleaseTime"));
        int ReceiveUserID = dbResult.getInt("ReceiveUserID");
        LocalDateTime ReceiveTime = transitTimestamp(dbResult.getTimestamp("ReceiveTime"));
        String TaskAddress = dbResult.getString("TaskAddress");
        int TaskCity = dbResult.getInt("TaskCity");

        return new Task(id, TaskName, Message, StartPostTime, EndPostTime, Salary, TypeName, ReleaseUserID,
                ReleaseTime, ReceiveUserID, ReceiveTime, TaskAddress, TaskCity);
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
