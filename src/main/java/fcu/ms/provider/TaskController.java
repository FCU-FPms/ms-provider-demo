package fcu.ms.provider;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fcu.ms.db.TaskDB;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;

@RestController
@RequestMapping(value ="/tasks")
public class TaskController {
    TaskDB taskDB = TaskDB.getInstance();

    @PostMapping(value = "")
    public ResponseEntity<String> createTask(@RequestParam String taskName, @RequestParam String message,
                                             @RequestParam Timestamp postTime, @RequestParam int salary) {
        // postTime 在API中要打上 yyyy-mm-dd hh:mm:ss 格式
        boolean is_success = taskDB.createTask(taskName, message, postTime, salary);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Error to build Task in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{taskName}")
    public ResponseEntity deleteTask(@PathVariable String taskName){
        boolean is_success = taskDB.deleteTask(taskName);

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
