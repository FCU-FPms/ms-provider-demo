package fcu.ms.provider;

import fcu.ms.data.Task;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fcu.ms.db.TaskDB;

import javax.validation.constraints.Null;
import javax.websocket.server.PathParam;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/tasks")
public class TaskController {
    TaskDB taskDB = TaskDB.getInstance();

    @PostMapping(value = "")
    public ResponseEntity<String> createTask(@RequestParam String TaskName, @RequestParam String Message,
                                             @RequestParam Timestamp PostTime, @RequestParam int Salary,
                                             @RequestParam String TypeName, @RequestParam String TaskAddress,
                                             @RequestParam int TaskCity) {
        // postTime 在API中要打上 yyyy-mm-dd hh:mm:ss 格式
        boolean is_success = taskDB.createTask(TaskName, Message, PostTime, Salary, TypeName, TaskAddress, TaskCity);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Error to build Task in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("")
    public ResponseEntity<Object> getTasks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Task> taskList = taskDB.getTasks();

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();

        for(Task task: taskList) {
            JSONObject entity = new JSONObject();

            int taskId = task.getTaskID();

            entity.put("TaskName", task.getTaskName());
            entity.put("Message", task.getMessage());
            entity.put("PostTime", task.getPostTime());
            entity.put("Salary", task.getSalary());
            entity.put("ReleaseUserID",task.getReleaseUserID());
            entity.put("ReleaseTime",task.getReleaseTime());
            entity.put("ReceiveUserID",task.getReceiveUserID());
            entity.put("ReceiveTime",task.getReceiveTime());
            entity.put("TypeName",task.getTaskName());
            entity.put("TaskAddress",task.getTaskAddress());
            entity.put("TaskCity",task.getTaskCity());


            entities.put(String.valueOf(taskId), entity);
        }

        return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
    }


    @GetMapping("/{TaskID}")
    public ResponseEntity<Object> getTaskByID(@PathVariable int TaskID) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Task task = taskDB.getTask(TaskID);

        Map<String, JSONObject> entities = new HashMap<String, JSONObject>();
        if(task != null) {
            JSONObject entity = new JSONObject();
            int taskId = task.getTaskID();

            entity.put("TaskName", task.getTaskName());
            entity.put("Message", task.getMessage());
            entity.put("PostTime", task.getPostTime());
            entity.put("Salary", task.getSalary());
            entity.put("ReleaseUserID",task.getReleaseUserID());
            entity.put("ReleaseTime",task.getReleaseTime());
            entity.put("ReceiveUserID",task.getReceiveUserID());
            entity.put("ReceiveTime",task.getReceiveTime());
            entity.put("TypeName",task.getTaskName());
            entity.put("TaskAddress",task.getTaskAddress());
            entity.put("TaskCity",task.getTaskCity());

            entities.put(String.valueOf(taskId), entity);
            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping (value = "")
    public ResponseEntity<String> setTask(@RequestParam int TaskID,@RequestParam String taskName,
        @RequestParam String Message,@RequestParam Timestamp postTime,@RequestParam int Salary) {

        boolean is_success = taskDB.setTask(TaskID, taskName, Message, postTime, Salary);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if(is_success) {
            return new ResponseEntity<String>(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Error to SET Task in DB", headers, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{TaskID}")
    public ResponseEntity<String> deleteTaskByID(@PathVariable int TaskID){
        boolean is_success = taskDB.deleteTask(TaskID);

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
