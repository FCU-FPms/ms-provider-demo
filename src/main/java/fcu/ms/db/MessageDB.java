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
                String content = rs.getString("content");
                int userID = rs.getInt("userID");
                int receiverID = rs.getInt("receiverID");
                Message message = new Message(id, content, userID, receiverID);
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
                String content = rs.getString("content");
                int userID = rs.getInt("userID");
                int recieverID = rs.getInt("receiverID");
                message = new Message(id, content, userID, recieverID);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return message;

    }

    public boolean createMessage(String content, int userID, int receiverID) {

        boolean is_success = false;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "INSERT INTO Message(messageID,content,userID,receiverID) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, content);
            preStmt.setInt(2, userID);
            preStmt.setInt(3, receiverID);
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


}
