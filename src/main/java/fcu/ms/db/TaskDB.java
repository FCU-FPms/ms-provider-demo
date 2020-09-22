package fcu.ms.db;

import fcu.ms.data.Task;
import fcu.ms.dbUtil.MySqlBoneCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String taskName = task.getTaskName();
        String message = task.getMessage();
        Timestamp startPostTime = (Timestamp) task.getStartPostTime();
        Timestamp endPostTime = (Timestamp) task.getEndPostTime();
        int salary = task.getSalary();
        String typeName = task.getTypeName();
        String taskAddress = task.getTaskAddress();
        int taskCity = task.getTaskCity();

        return createTask(taskName, message, startPostTime, endPostTime, salary, typeName, taskAddress, taskCity);
    }

    public boolean createTask(String taskName, String message, Timestamp startPostTime, Timestamp endPostTime, int salary,
                              String typeName, String taskAddress, int taskCity) {
        String sqlString =
                "INSERT INTO Task(TaskName, Message, StartPostTime, EndPostTime, Salary, TypeName, TaskAddress, TaskCity) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        boolean is_success;
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskName);
            preStmt.setString(2, message);
            preStmt.setTimestamp(3, startPostTime);
            preStmt.setTimestamp(4,endPostTime);
            preStmt.setInt(5, salary);
            preStmt.setString(6,typeName);
            preStmt.setString(7,taskAddress);
            preStmt.setInt(8,taskCity);
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
        List<Task> tasks = new ArrayList<Task>();

        String sqlString = "select * from Task";
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
        String sqlString = "SELECT `TaskID` FROM Task where `TaskName` = ?";
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
        String sqlString = "SELECT * FROM Task where taskID=?";
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


    public boolean setTask(int TaskID, String TaskName, String Message, Timestamp StartPostTime, Timestamp EndPostTime
                            , int Salary, String TypeName, String TaskAddress, int TaskCity){
        boolean is_success;
        String sqlString =  "UPDATE Task SET TaskName = ?, Message = ?, StartPostTime = ?, EndPostTime = ?, Salary = ?, " +
                "TypeName = ?,TaskAddress = ?,TaskCity = ? WHERE TaskID = ?";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, TaskName);
            preStmt.setString(2, Message);
            preStmt.setTimestamp(3, StartPostTime);
            preStmt.setTimestamp(4,EndPostTime);
            preStmt.setInt(5, Salary);
            preStmt.setString(6,TypeName);
            preStmt.setString(7,TaskAddress);
            preStmt.setInt(8,TaskCity);
            preStmt.setInt(9, TaskID);
            preStmt.executeUpdate();
            preStmt.close();
            connection.close();
            is_success = true;

        }catch (Exception ex){
            System.out.println("Error: " + ex);
            is_success = false;
        }
        return is_success;
    }


    public boolean deleteTask(int taskID) {

        boolean is_success;
        String sqlString = "DELETE FROM `Task` WHERE TaskID=?";
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
    private Task parseTaskFromDbColumn(ResultSet dbResult) {
        try {
            int id = dbResult.getInt("TaskID");
            String TaskName = dbResult.getString("TaskName");
            String Message = dbResult.getString("Message");
            Timestamp StartPostTime = dbResult.getTimestamp("StartPostTime");
            Timestamp EndPostTime = dbResult.getTimestamp("EndPostTime");
            int Salary = dbResult.getInt("Salary");
            String TypeName = dbResult.getString("TypeName");
            int ReleaseUserID = dbResult.getInt("ReleaseUserID");
            Timestamp ReleaseTime = dbResult.getTimestamp("ReleaseTime");
            int ReceiveUserID = dbResult.getInt("ReceiveUserID");
            Timestamp ReceiveTime = dbResult.getTimestamp("ReceiveTime");
            String TaskAddress = dbResult.getString("TaskAddress");
            int TaskCity = dbResult.getInt("TaskCity");

            Task task = new Task(id, TaskName, Message, StartPostTime, EndPostTime, Salary, TypeName, TaskAddress, TaskCity);
            return task;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }
}
