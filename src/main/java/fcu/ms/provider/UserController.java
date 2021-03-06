package fcu.ms.provider;

import fcu.ms.data.User;
import fcu.ms.db.UserDB;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value ="/users")
public class UserController {
    UserDB userDB = UserDB.getInstance();

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        User user = userDB.getUser(id);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(user != null) {
            JSONObject entity = new JSONObject();
            int userId = user.getId();
            entity.put("name", user.getName());
            entity.put("firebase_uid", user.getFirebaseUid());

            entities.put(String.valueOf(userId), entity);
            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/firebase/{uid}")
    public ResponseEntity<Object> getUserByID(@PathVariable String uid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        User user = userDB.getUserByFirebaseUID(uid);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(user != null) {
            JSONObject entity = new JSONObject();
            int userId = user.getId();
            entity.put("name", user.getName());
            entity.put("firebase_uid", user.getFirebaseUid());

            entities.put(String.valueOf(userId), entity);
            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        boolean is_success = userDB.createUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Error to build User in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        boolean is_success = userDB.deleteUser(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success){
            return new ResponseEntity<String>(headers, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<String>("Error to delete Task in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }
}
