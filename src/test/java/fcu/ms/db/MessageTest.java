package fcu.ms.db;
import fcu.ms.data.Message;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {


    @Test
    public void createMessage_TEST() {   /* 創建一個新的 message */
        MessageDB messageDB = MessageDB.getInstance();
        LocalDateTime currentTime = LocalDateTime.now();
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
    void getMessageByID_TEST() {  /* 根據 user/receiver 以及 taskID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> message = messageDB.getMessageByID(1,1, 325);
        System.out.println(message);
    }

    @Test
    void getMessageByTaskID_TEST() {  /* 根據 taskID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> messages = messageDB.getMessageByTaskId(325);
        System.out.println(messages);
    }

    @Test
    public void deleteMessage_TEST() {  /* 刪除一個 message */
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue(messageDB.deleteMessage(41));
    }

}
