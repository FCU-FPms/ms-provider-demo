package fcu.ms.db;

import fcu.ms.data.Message;
import fcu.ms.data.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageDBTest {

    @Test
    public void createMessage_TEST() {   /* 創建一個新的 message */
        MessageDB messageDB = MessageDB.getInstance();
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
        Message message = new Message("4", 1, 1, currentTime, 325);
        assertTrue( messageDB.createMessage(message) );
    }

    @Test
    public void getMessages_TEST() { /* 根據 messageID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        Message message = messageDB.getMessage(41);
        assertTrue(message.getId() == 41);
    }
    @Test
    void getUserHasWhichTasks_TEST(){
        MessageDB messageDB = MessageDB.getInstance();
        List<Task> taskIDList = messageDB.getUserHasWhichTask(1);
        assertTrue(taskIDList.size() == 2);

    }

    @Test
    void getMessageByID_TEST() {  /* 根據 user/receiver 以及 taskID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> message = messageDB.getMessageByID(1,1, 325);
        System.out.println(message);
    }

    @Test
    void getMessageByTaskID_TEST() {  /* 根據 taskID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> messages = messageDB.getMessageByTaskId(348);
        System.out.println(messages);
    }
    @Test
    void getUserHasWhichTask_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        List<Task> tasks = messageDB.getUserHasWhichTask(14);
        System.out.println(tasks.size());
    }

    @Test
    void getMessageByUserID_TEST() {  /* 根據 userID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> messages = messageDB.getMessageByUserId(1);
        System.out.println(messages);
    }

    @Test
    public void deleteMessage_TEST() {  /* 刪除一個 message */
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue(messageDB.deleteMessage(41));
    }

    @Test
    void getMessageByTaskIDAndTwoUserID() {
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> messages = messageDB.getMessageByTaskIDAndTwoUserID(348, 1, 14);
        System.out.println(messages);
    }

    @Test
    void getUserRelatedWho() {
        MessageDB messageDB = MessageDB.getInstance();
        System.out.println(messageDB.getUserRelatedWho(14));
    }

    @Test
    void getMessageByTwoUserID() {
        MessageDB messageDB = MessageDB.getInstance();
        System.out.println(messageDB.getMessageByTwoUserID(14, 1));
    }
}