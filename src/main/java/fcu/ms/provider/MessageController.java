package fcu.ms.provider;



import fcu.ms.data.Message;
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
                                                @RequestParam int ReceiverID, @RequestParam Timestamp postTime) {
        boolean is_success = messageDB.createMessage(Content, UserID, ReceiverID, postTime);

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

}
