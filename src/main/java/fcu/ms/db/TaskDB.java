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

    public boolean createTask(String TaskName, String Message,Timestamp PostTime , int Salary, String TypeName,
                              String TaskAddress, int TaskCity) {

        boolean is_success;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString =
                "INSERT INTO Task(TaskName, Message, PostTime, Salary, TypeName, TaskAddress, TaskCity) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, TaskName);
            preStmt.setString(2, Message);
            preStmt.setTimestamp(3, PostTime);
            preStmt.setInt(4, Salary);
            preStmt.setString(5,TypeName);
            preStmt.setString(6,TaskAddress);
            preStmt.setInt(7,TaskCity);
            preStmt.executeUpdate();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
            is_success = false;
        }
        return is_success;
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
                Timestamp PostTime = rs.getTimestamp("PostTime");
                int Salary = rs.getInt("Salary");
                String TypeName = rs.getString("TypeName");
                int ReleaseUserID = rs.getInt("ReleaseUserID");
                Timestamp ReleaseTime = rs.getTimestamp("ReleaseTime");
                int ReceiveUserID = rs.getInt("ReceiveUserID");
                Timestamp ReceiveTime = rs.getTimestamp("ReceiveTime");
                String TaskAddress = rs.getString("TaskAddress");
                int TaskCity = rs.getInt("TaskCity");
                Task task = new Task(TaskName, Message, PostTime, Salary, TypeName, TaskAddress, TaskCity);
                tasks.add(task);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return tasks;
    }

    public int getTaskIdByName(String taskName) {
        int id = -1;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "SELECT `TaskID` FROM Task where `TaskName` = ?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, taskName);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("TaskID");
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        return id;
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
                Timestamp PostTime = rs.getTimestamp("PostTime");
                int Salary = rs.getInt("Salary");
                String TypeName = rs.getString("TypeName");
                int ReleaseUserID = rs.getInt("ReleaseUserID");
                Timestamp ReleaseTime = rs.getTimestamp("ReleaseTime");
                int ReceiveUserID = rs.getInt("ReceiveUserID");
                Timestamp ReceiveTime = rs.getTimestamp("ReceiveTime");
                String TaskAddress = rs.getString("TaskAddress");
                int TaskCity = rs.getInt("TaskCity");
                task = new Task(TaskName, Message, PostTime, Salary, TypeName, TaskAddress, TaskCity);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return task;

    }


    public boolean setTask(int TaskID, String TaskName, String Message, Timestamp PostTime, int Salary){
        boolean is_success;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString =  "UPDATE Task SET TaskName = ?, Message = ?, PostTime = ?, Salary = ? WHERE TaskID = ?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, TaskName);
            preStmt.setString(2, Message);
            preStmt.setTimestamp(3, PostTime);
            preStmt.setInt(4, Salary);
            preStmt.setInt(5, TaskID);
            preStmt.executeUpdate();
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
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "DELETE FROM `Task` WHERE TaskID=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskID);
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
