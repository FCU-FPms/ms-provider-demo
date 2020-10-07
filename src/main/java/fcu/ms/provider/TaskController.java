package fcu.ms.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;

import fcu.ms.data.Task;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fcu.ms.db.TaskDB;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.fge.jsonpatch.JsonPatch;

@RestController
@RequestMapping(value ="/tasks")
public class TaskController {
    TaskDB taskDB = TaskDB.getInstance();
    ObjectMapper objectMapper = new ObjectMapper();

//    @PostMapping(value = "") // 這邊變數都應該要是小寫, 但如果改掉前後端都要修正, 所以先不改
//    public ResponseEntity<String> createTask(@RequestParam String TaskName, @RequestParam String Message,
//                                             @RequestParam Timestamp StartPostTime,@RequestParam Timestamp EndPostTime,
//                                             @RequestParam int Salary, @RequestParam String TypeName, @RequestParam int ReleaseUserID,
//                                             @RequestParam String TaskAddress, @RequestParam int TaskCity) {
//        // postTime 在API中要打上 yyyy-mm-dd hh:mm:ss 格式
//        Timestamp ReleaseTime = new Timestamp(new Date().getTime()); // 會自動填入發布時的時間點
//
//        Task task = new Task(TaskName, Message, StartPostTime, EndPostTime, Salary, TypeName, ReleaseUserID,
//                ReleaseTime, TaskAddress, TaskCity);
//
//        boolean is_success = taskDB.createTask(task);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//
//        if(is_success) {
//            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<String>("Error to build Task in DB", headers, HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping(value = "")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        // postTime 在API中要打上 yyyy-mm-dd hh:mm:ss 格式
        System.out.println("franky-test-task");
        System.out.println(task.getTaskName());
        System.out.println(task.getMessage());
        System.out.println(task.getStartPostTime());
        Timestamp ReleaseTime = new Timestamp(new Date().getTime()); // 會自動填入發布時的時間點

        boolean is_success = taskDB.createTask(task);

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

        for (Task task : taskList) {
            int taskId = task.getTaskID();
            JSONObject entity = getTaskEntity(task);

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
            JSONObject entity = getTaskEntity(task);
            int taskId = task.getTaskID();
            entities.put(String.valueOf(taskId), entity);
            return new ResponseEntity<Object>(entities, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping (path = "/{taskId}", consumes = "application/json-patch+json")
    public ResponseEntity<String> updateTask(@PathVariable int taskId, @RequestBody JsonPatch patch) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        boolean is_success = false;

        try {
            Task task = taskDB.getTask(taskId);
            Task taskPatched = applyPatchToTask(patch, task);
            is_success = taskDB.setTask(taskPatched);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private JSONObject getTaskEntity(Task task) {
        JSONObject entity = new JSONObject();
        entity.put("TaskName", task.getTaskName());
        entity.put("Message", task.getMessage());
        entity.put("StartPostTime", task.getStartPostTime());
        entity.put("EndPostTime", task.getEndPostTime());
        entity.put("Salary", task.getSalary());
        entity.put("ReleaseUserID",task.getReleaseUserID());
        entity.put("ReleaseTime",task.getReleaseTime());
        entity.put("ReceiveUserID",task.getReceiveUserID());
        entity.put("ReceiveTime",task.getReceiveTime());
        entity.put("TypeName",task.getTaskName());
        entity.put("TaskAddress",task.getTaskAddress());
        entity.put("TaskCity",task.getTaskCity());
        return entity;
    }

    private Task applyPatchToTask(JsonPatch patch, Task targetTask) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetTask, JsonNode.class)); //這可以自動轉換task
        return objectMapper.treeToValue(patched, Task.class);
    }

}
