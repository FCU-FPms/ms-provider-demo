package fcu.ms.db;
import fcu.ms.data.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {
    @Test
    public void createMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue( messageDB.createMessage("testing", 1, 33) );
    }

    @Test
    public void getMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        Message message = messageDB.getMessage(1);
        assertEquals("testing", message.getContent());
        assertEquals(1, message.getUserID());
        assertEquals(2, message.getReceiverID());
        System.out.println(message.getContent());
    }

    @Test
    public void deleteMessage_TEST() {
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue(messageDB.deleteMessage(1));
    }
}
