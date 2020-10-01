package fcu.ms.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fcu.ms.data.Message;
import fcu.ms.dbUtil.MySqlBoneCP;


public class MessageDB {
    private static final MessageDB messageDB = new MessageDB();

    public static MessageDB getInstance() {
        return messageDB;
    }

    private MessageDB(){

    }

    public List<Message> getMessage() {
        List<Message> messages = new ArrayList<Message>();

        String sqlString = "select * from Message";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String Content = rs.getString("Content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                int taskID = rs.getInt("taskID");
                Message message = new Message(id, Content, userID, receiverID, postTime, taskID);
                messages.add(message);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return messages;
    }

    public List<Message> getMessageByID(int userId, int receiverId, int taskId) {
        List<Message> messages = new ArrayList<Message>();


        String sqlString = "SELECT * FROM Message where `userId` = ? AND `receiverId`=  ? AND `taskId`=  ?";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            preStmt.setInt(2, receiverId);
            preStmt.setInt(3, taskId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String content = rs.getString("Content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                int taskID = rs.getInt("taskID");
                Message message = new Message(id, content, userID, receiverID, postTime, taskID);
                messages.add(message);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return messages;
    }

    public Message getMessage(int messageID) {
        Message message = null;

        String sqlString = "select * from Message where messageId=?";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, messageID);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String content = rs.getString("Content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                int taskID = rs.getInt("taskID");
                message = new Message(id, content, userID, receiverID, postTime, taskID);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return message;

    }

    public boolean createMessage(String content, int userID, int receiverID, Timestamp postTime, int taskID) {

        boolean is_success = false;

        String sqlString = "INSERT INTO Message(content,userID,receiverID,postTime,taskID) VALUES( ?, ?, ?, ?, ?)";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, content);
            preStmt.setInt(2, userID);
            preStmt.setInt(3, receiverID);
            preStmt.setTimestamp(4, postTime);
            preStmt.setInt(5, taskID);
            preStmt.executeUpdate();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return is_success;
    }

    public boolean deleteMessage(int messageID) {

        boolean is_success = false;

        String sqlString = "DELETE FROM `Message` WHERE messageID=?";
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, messageID);
            preStmt.executeUpdate();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return is_success;
    }

    public List<Message> getMessageByTaskId(int taskId) {
        List<Message> messages = new ArrayList<Message>();


        String sqlString = "SELECT * FROM Message WHERE `taskId`=  ? ORDER BY postTime ASC" ;
        try {
            Connection connection = MySqlBoneCP.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String content = rs.getString("Content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                int taskID = rs.getInt("taskID");
                Message message = new Message(id, content, userID, receiverID, postTime, taskID);
                messages.add(message);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return messages;
    }

}
