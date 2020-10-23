package fcu.ms.provider;
import fcu.ms.data.Message;
import fcu.ms.db.MessageDB;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/message")
public class MessageController {
    MessageDB messageDB = MessageDB.getInstance();


    @PostMapping(value = "")
    public ResponseEntity<String> createMessage(@RequestBody Message message) {

        if(message.getPostTime() == null) { // 會自動填入傳送訊息時的時間點
            LocalDateTime postTime = LocalDateTime.now();
            message.setPostTime(postTime);
        }

        boolean is_success = messageDB.createMessage(message);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Error to build Message in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/conversation/{taskID}")
    public ResponseEntity<Object> getMessageByTaskID(@PathVariable int taskID) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Message> messages = messageDB.getMessageByTaskId(taskID);
        Map<String, JSONObject> entities = getEachMessage(messages);
        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/conversation/{taskID}/{userID}/{receiverID}/")
    public ResponseEntity<Object> getMessageByID(@PathVariable int userID, @PathVariable int receiverID, @PathVariable int taskID) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Message> messages = messageDB.getMessageByID(userID, receiverID, taskID);

        Map<String, JSONObject> entities = getEachMessage(messages);
        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
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

    private JSONObject getMessageEntity(Message message) {
        JSONObject entity = new JSONObject();
        entity.put("content", message.getContent());
        entity.put("postTime", message.getPostTime());
        entity.put("userID", message.getUserID());
        entity.put("receiverID", message.getReceiverID());
        entity.put("taskID", message.getTaskID());
        return entity;
    }

    private Map<String, JSONObject> getEachMessage(List<Message> messages) {

        Map<String, JSONObject> entities = new HashMap<>();

        for (Message message : messages) {
            int messageID = message.getId();
            JSONObject entity = getMessageEntity(message);
            entities.put(String.valueOf(messageID), entity);
        }
        return entities;
    }

}
