package fcu.ms.provider;

import fcu.ms.data.User;
import fcu.ms.db.UserDB;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/users")
public class UserController {
    UserDB userDB = UserDB.getInstance();

    @GetMapping("/name/{userName}")
    public ResponseEntity<Object> getUserByName(@PathVariable String userName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        User user = userDB.getUser(userName);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(user != null) {
            JSONObject entity = new JSONObject();
            int userId = user.getId();
            entity.put("name", user.getUserName());
            entity.put("phoneNumber", user.getPhoneNumber());
            entity.put("userPassword", user.getUserPassword());

            entities.put(String.valueOf(userId), entity);
            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
        }
    }



}
