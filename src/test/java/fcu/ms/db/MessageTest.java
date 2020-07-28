package fcu.ms.db;
import fcu.ms.data.Message;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {
    @Test
    public void createMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue( messageDB.createMessage("testing321", 1, 33,new Timestamp(new Date().getTime())) );
    }

    @Test
    public void getMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        Message message = messageDB.getMessage(4);
        assertEquals("testing", message.getContent());
        assertEquals(1, message.getUserID());
        assertEquals(33, message.getReceiverID());
        System.out.println(message.getContent());
    }

    @Test
    public void deleteMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue(messageDB.deleteMessage(3));
    }

}
