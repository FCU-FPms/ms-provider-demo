package fcu.ms.provider;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fcu.ms.db.TaskDB;

import java.sql.Timestamp;

@RestController
@RequestMapping(value ="/tasks")
public class TaskController {
    TaskDB taskDB = TaskDB.getInstance();

    @PostMapping(value = "")
    public ResponseEntity<String> createUser(@RequestParam String taskName, @RequestParam String message,
                                             @RequestParam Timestamp postTime, @RequestParam int salary) {
        boolean is_success = taskDB.createTask(taskName, message, postTime, salary);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Error to build User in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }


}
