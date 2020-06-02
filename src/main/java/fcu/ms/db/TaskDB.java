package fcu.ms.db;

import fcu.ms.data.Task;
import fcu.ms.dbUtil.MySqlConnection;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
                String PostTime = rs.getString("PostTime");
                int Salary = rs.getInt("Salary");
                String TypeName = rs.getString("TypeName");
                int ReleaseUserID = rs.getInt("ReleaseUserID");
                String ReleaseDate = rs.getString("ReleaseDate");
                int ReceiveUserID = rs.getInt("ReceiveUserID");
                String ReceiveDate = rs.getString("ReceiveTime");
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
        String sqlString = "select * from Task where taskID=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("TaskID");
                String TaskName = rs.getString("TaskName");
                String Message = rs.getString("Message");
                String PostTime = rs.getString("PostTime");
                int Salary = rs.getInt("Salary");
                String TypeName = rs.getString("TypeName");
                int ReleaseUserID = rs.getInt("ReleaseUserID");
                String ReleaseTime = rs.getString("ReleaseTime");
                int ReceiveUserID = rs.getInt("ReceiveUserID");
                String ReceiveTime = rs.getString("ReceiveTime");
                task = new Task(id, TaskName, Message,PostTime,Salary,TypeName,ReleaseUserID,ReleaseTime,ReceiveUserID,ReceiveTime);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return task;

    }
    public boolean createTask(String TaskName, String Message,String PostTime , int Salary) {

        boolean is_success = false;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "INSERT INTO Task(TaskName,Message,PostTime,Salary) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, TaskName);
            preStmt.setString(2, Message);
            preStmt.setString(3, PostTime);
            preStmt.setInt(4,Salary);
            preStmt.executeUpdate();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return is_success;
    }
}
