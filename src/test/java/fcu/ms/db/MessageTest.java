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
    public void createMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue( messageDB.createMessage("testing321", 1, 33,new Timestamp(new Date().getTime())) );
    }

    @Test
    public void getMessages_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        Message message = messageDB.getMessage(7);
        assertEquals("testing321", message.getContent());
        assertEquals(1, message.getUserID());
        assertEquals(33, message.getReceiverID());
        System.out.println(message.getContent());
    }

    @Test
    public void deleteMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue(messageDB.deleteMessage(3));
    }

    @Test
    void getMessageByID() {
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> message = messageDB.getMessageByID(1,33);
        System.out.println(message);
    }

}
