package fcu.ms.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fcu.ms.data.Message;
import fcu.ms.dbUtil.MySqlConnection;

public class MessageDB {
    private static final MessageDB messageDB = new MessageDB();
    private static final MySqlConnection mySqlConnection = new MySqlConnection();

    public static MessageDB getInstance() {
        return messageDB;
    }

    private MessageDB(){

    }

    public List<Message> getMessage() {
        List<Message> messages = new ArrayList<Message>();

        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from Message";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String Content = rs.getString("Content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                Message message = new Message(id, Content, userID, receiverID, postTime);
                messages.add(message);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return messages;
    }

    public List<Message> getMessageByID(int userId, int receiverId) {
        List<Message> messages = new ArrayList<Message>();

        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "SELECT `messageID` FROM Message where `userId` = ? AND `receiverId`=  ?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            preStmt.setInt(2, receiverId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String content = rs.getString("Content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                Message message = new Message(id, content, userID, receiverID, postTime);
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
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from Message where messageId=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, messageID);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String content = rs.getString("Content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                message = new Message(id, content, userID, receiverID, postTime);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return message;

    }

    public boolean createMessage(String content, int userID, int receiverID, Timestamp postTime) {

        boolean is_success = false;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "INSERT INTO Message(content,userID,receiverID,postTime) VALUES( ?, ?, ?, ?)";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, content);
            preStmt.setInt(2, userID);
            preStmt.setInt(3, receiverID);
            preStmt.setTimestamp(4, postTime);
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
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "DELETE FROM `Message` WHERE messageID=?";
        try {
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
    public Message getMessageByID2(int userID, int receiverID) {
        Message message = null;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "SELECT `messageID` FROM Message where `userId` = ? AND `receiverId`=  ?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userID);
            preStmt.setInt(2, receiverID);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("messageID");
                String content = rs.getString("Content");
                int userId = rs.getInt("userID");
                int receiverId = rs.getInt("receiverID");
                Timestamp postTime = rs.getTimestamp("postTime");
                message = new Message(id, content, userID, receiverID, postTime);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return message;
    }

}
