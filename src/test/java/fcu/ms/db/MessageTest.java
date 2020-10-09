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
        Message message = new Message("testing321", 1, 1, currentTime, 200);
        assertTrue( messageDB.createMessage(message) );
    }

    @Test
    public void getMessages_TEST() { /* 根據 messageID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        Message message = messageDB.getMessage(1);
        assertTrue(message.getId() == 1);
    }

    @Test
    public void deleteMessage_TEST() {  /* 刪除一個 message */
        MessageDB messageDB = MessageDB.getInstance();
        assertTrue(messageDB.deleteMessage(1));
    }

    @Test
    void getMessageByID_TEST() {  /* 根據 user/receiver 以及 taskID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> message = messageDB.getMessageByID(1,1, 200);
        System.out.println(message);
    }

    @Test
    void getMessageByTaskID_TEST() {  /* 根據 taskID 來抓 message */
        MessageDB messageDB = MessageDB.getInstance();
        List<Message> messages = messageDB.getMessageByTaskId(200);
        for(Message message : messages) {
            System.out.println(message.getId());
        }
    }

}
