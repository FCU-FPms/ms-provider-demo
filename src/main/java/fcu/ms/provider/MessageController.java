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
    public ResponseEntity<String> createMessage(@RequestParam String content, @RequestParam int userID,
                                                @RequestParam int receiverID, @RequestParam Timestamp postTime, @RequestParam int taskID) {
        boolean is_success = messageDB.createMessage(content, userID, receiverID, postTime, taskID);

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
    public ResponseEntity<Object> getMessageByID(@PathVariable int userID, @PathVariable int receiverID, @PathVariable int taskID) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Message> messages = messageDB.getMessageByID(userID, receiverID, taskID);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();


        for (Message message : messages) {
            JSONObject entity = new JSONObject();

            int messageID = message.getId();

            entity.put("content", message.getContent());
            entity.put("postTime", message.getPostTime());
            entity.put("userID", message.getUserID());
            entity.put("receiverId", message.getReceiverID());
            entity.put("taskID", message.getTaskID());

            entities.put(String.valueOf(messageID), entity);
        }

        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/conversation/{TaskID}")
    public ResponseEntity<Object> getMessageByTaskID(@PathVariable int taskID) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Message> messages = messageDB.getMessageByTaskId(taskID);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();


        for (Message message : messages) {
            JSONObject entity = new JSONObject();

            int messageID = message.getId();

            entity.put("content", message.getContent());
            entity.put("postTime", message.getPostTime());
            entity.put("userID", message.getUserID());
            entity.put("receiverId", message.getReceiverID());
            entity.put("taskID", message.getTaskID());

            entities.put(String.valueOf(messageID), entity);
        }

        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
    }

}
