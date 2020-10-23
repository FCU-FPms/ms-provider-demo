package fcu.ms.db;
import java.sql.*;
import java.time.LocalDateTime;
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

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT * FROM `message`";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            rs = preStmt.executeQuery();

            while (rs.next()) {
                Message message = parseMessageFromDbColumn(rs);
                messages.add(message);
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

        return messages;
    }

    public List<Message> getMessageByID(int userId, int receiverId, int taskId) {
        List<Message> messages = new ArrayList<Message>();

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;


        String sqlString = "SELECT * FROM `message` WHERE `userID` = ? AND `receiverID`=  ? AND `taskID`=  ?";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            preStmt.setInt(2, receiverId);
            preStmt.setInt(3, taskId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Message message = parseMessageFromDbColumn(rs);
                messages.add(message);
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
        return messages;
    }

    public Message getMessage(int id) {
        Message message = null;

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT * FROM `message` WHERE `id` = ?";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, id);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                message = parseMessageFromDbColumn(rs);
            }

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        try {
            rs.close();
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return message;

    }

    public boolean createMessage(Message message) {
        boolean is_success = false;

        Connection connection = null;
        PreparedStatement preStmt = null;

        String sqlString = "INSERT INTO `message` (content, userID, receiverID, postTime, taskID) VALUES( ?, ?, ?, ?, ?)";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, message.getContent());
            preStmt.setInt(2, message.getUserID());
            preStmt.setInt(3, message.getReceiverID());
            preStmt.setTimestamp(4, Timestamp.valueOf(message.getPostTime()));
            preStmt.setInt(5, message.getTaskID());
            preStmt.executeUpdate();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return is_success;
    }

    public boolean deleteMessage(int id) {
        boolean is_success = false;

        Connection connection = null;
        PreparedStatement preStmt = null;

        String sqlString = "DELETE FROM `message` WHERE id = ?";
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();

            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        try {
            preStmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is_success;
    }

    public List<Message> getMessageByTaskId(int taskId) {
        List<Message> messages = new ArrayList<Message>();

        Connection connection = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT * FROM `message` WHERE `taskId`= ? ORDER BY `postTime` ASC" ;
        try {
            connection = MySqlBoneCP.getInstance().getConnection();
            preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, taskId);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Message message = parseMessageFromDbColumn(rs);
                messages.add(message);
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

        return messages;
    }

    private Message parseMessageFromDbColumn(ResultSet dbResult) throws Exception {
        int id = dbResult.getInt("id");
        String content = dbResult.getString("content");
        int userID = dbResult.getInt("userID");
        int receiverID = dbResult.getInt("receiverID");
        LocalDateTime postTime = dbResult.getTimestamp("postTime").toLocalDateTime();
        int taskID = dbResult.getInt("taskID");
        return new Message(id, content, userID, receiverID, postTime, taskID);
    }
}
