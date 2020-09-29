package fcu.ms.provider;



import fcu.ms.data.Message;
import fcu.ms.data.Task;
import fcu.ms.db.MessageDB;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/message")
public class MessageController {
    MessageDB messageDB = MessageDB.getInstance();



    @PostMapping(value = "")
    public ResponseEntity<String> createMessage(@RequestParam String Content, @RequestParam int UserID,
                                                @RequestParam int ReceiverID, @RequestParam Timestamp postTime, @RequestParam int taskID) {
        boolean is_success = messageDB.createMessage(Content, UserID, ReceiverID, postTime, taskID);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Error to build Message in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{messageID}")
    public ResponseEntity deleteMessage(@PathVariable int messageID){
        boolean is_success = messageDB.deleteMessage(messageID);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success){
            return new ResponseEntity<String>(headers, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<String>("Error to delete Message in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/conversation/{UserID}/{ReceiverID}/{TaskID}")
    public ResponseEntity<Object> getMessageByID(@PathVariable int UserID, @PathVariable int ReceiverID, @PathVariable int TaskID) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Message> messages = messageDB.getMessageByID(UserID, ReceiverID, TaskID);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();


        for (Message message : messages) {
            JSONObject entity = new JSONObject();

            int messageID = message.getId();

            entity.put("Content", message.getContent());
            entity.put("postTime", message.getPostTime());
            entity.put("userID", message.getUserID());
            entity.put("receiverId", message.getReceiverID());
            entity.put("taskID", message.getTaskID());

            entities.put(String.valueOf(messageID), entity);
        }

        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/conversation/{TaskID}")
    public ResponseEntity<Object> getMessageByTaskID(@PathVariable int TaskID) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Message> messages = messageDB.getMessageByTaskId(TaskID);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();


        for (Message message : messages) {
            JSONObject entity = new JSONObject();

            int messageID = message.getId();

            entity.put("Content", message.getContent());
            entity.put("postTime", message.getPostTime());
            entity.put("userID", message.getUserID());
            entity.put("receiverId", message.getReceiverID());
            entity.put("taskID", message.getTaskID());

            entities.put(String.valueOf(messageID), entity);
        }

        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
    }

}
