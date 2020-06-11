package fcu.ms.provider;

import fcu.ms.data.Task;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fcu.ms.db.TaskDB;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/task")
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
    @GetMapping("s")
    public ResponseEntity<Object> getAllTask() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Task> task = taskDB.getTasks();

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(task != null) {
            JSONObject entity = new JSONObject();

            for(Task T: task) {
                int taskId = T.getTaskID();
                entity.put("taskName", T.getTaskName());
                entity.put("Message", T.getMessage());
                entity.put("postTime", T.getPostTime());
                entity.put("Salary", T.getSalary());
                entities.put(String.valueOf(taskId), entity);
            }

            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{taskName}")
    public ResponseEntity<Object> getTaskByName(@PathVariable String taskName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Task task = taskDB.getTask(taskName);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(task != null) {
            JSONObject entity = new JSONObject();
            int taskId = task.getTaskID();
            entity.put("taskName", task.getTaskName());
            entity.put("Message", task.getMessage());
            entity.put("postTime",task.getPostTime());
            entity.put("Salary",task.getSalary());

            entities.put(String.valueOf(taskId), entity);
            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
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
