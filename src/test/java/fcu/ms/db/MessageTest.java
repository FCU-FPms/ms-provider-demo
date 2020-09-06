package fcu.ms.db;
import fcu.ms.data.Message;
import fcu.ms.data.Task;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MessageTest {


    @Test
    public void createMessage_TEST() {   /* 創建一個新的 message */
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue( messageDB.createMessage("testing321", 1, 33,new Timestamp(new Date().getTime()), 25) );
    }

    @Test
    public void getMessages_TEST() { /* 根據 messageID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        Message message = messageDB.getMessage(14);
        assertEquals("testing321", message.getContent());
        assertEquals(1, message.getUserID());
        assertEquals(33, message.getReceiverID());
        assertEquals(9, message.getTaskID());
        System.out.println(message.getContent());
    }

    @Test
    public void deleteMessage_TEST() {  /* 刪除一個 message */
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue(messageDB.deleteMessage(16));
    }

    @Test
    void getMessageByID_TEST() {  /* 根據 user/receiver 以及 taskID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> message = messageDB.getMessageByID(1,33, 9);
        System.out.println(message);
    }

}
