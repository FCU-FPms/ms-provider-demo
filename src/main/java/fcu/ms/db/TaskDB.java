package fcu.ms.db;

import fcu.ms.data.Task;
import fcu.ms.dbUtil.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class TaskDB {
    private static final TaskDB taskDB = new TaskDB();
    private static final MySqlConnection mySqlConnection = new MySqlConnection();

    public static TaskDB getInstance() {
        return taskDB;
    }

    private TaskDB(){

    }
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<Task>();

        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from Task";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("TaskID");
                String TaskName = rs.getString("TaskName");
                String Message = rs.getString("Message");
                Date PostTime = rs.getDate("PostTime");
                int Salary = rs.getInt("Salary");
                String TypeName = rs.getString("TypeName");
                int ReleaseUserID = rs.getInt("ReleaseUserID");
                Date ReleaseDate = rs.getDate("ReleaseDate");
                int ReceiveUserID = rs.getInt("ReceiveUserID");
                Date ReceiveDate = rs.getDate("ReceiveTime");
                Task task = new Task(id, TaskName, Message,PostTime,Salary,TypeName,ReleaseUserID,ReleaseDate,ReceiveUserID,ReceiveDate);
                tasks.add(task);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return tasks;
    }


    public Task getTask(int taskId) {
        Task task = null;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "SELECT * FROM Task where taskID=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("TaskID");
                String TaskName = rs.getString("TaskName");
                String Message = rs.getString("Message");
                Date PostTime = rs.getDate("PostTime");
                int Salary = rs.getInt("Salary");
                String TypeName = rs.getString("TypeName");
                int ReleaseUserID = rs.getInt("ReleaseUserID");
                Date ReleaseTime = rs.getDate("ReleaseTime");
                int ReceiveUserID = rs.getInt("ReceiveUserID");
                Date ReceiveTime = rs.getDate("ReceiveTime");
                task = new Task(id, TaskName, Message,PostTime,Salary,TypeName,ReleaseUserID,ReleaseTime,ReceiveUserID,ReceiveTime);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return task;

    }

    public Task getTask(String taskName) {
        Task task = null;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "SELECT * FROM Task where TaskName=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskName);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("TaskID");
                String TaskName = rs.getString("TaskName");
                String Message = rs.getString("Message");
                Date PostTime = rs.getDate("PostTime");
                int Salary = rs.getInt("Salary");
                String TypeName = rs.getString("TypeName");
                int ReleaseUserID = rs.getInt("ReleaseUserID");
                Date ReleaseTime = rs.getDate("ReleaseTime");
                int ReceiveUserID = rs.getInt("ReceiveUserID");
                Date ReceiveTime = rs.getDate("ReceiveTime");
                task = new Task(id, TaskName, Message,PostTime,Salary,TypeName,ReleaseUserID,ReleaseTime,ReceiveUserID,ReceiveTime);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return task;

    }

    public boolean createTask(String TaskName, String Message,Timestamp PostTime , int Salary) {

        boolean is_success = false;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "INSERT INTO Task(TaskName,Message,PostTime,Salary) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, TaskName);
            preStmt.setString(2, Message);
            preStmt.setTimestamp(3, PostTime);
            preStmt.setInt(4,Salary);
            preStmt.executeUpdate();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
            is_success = false;
        }
        return is_success;
    }
}
