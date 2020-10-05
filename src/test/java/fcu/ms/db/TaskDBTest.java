package fcu.ms.db;

import fcu.ms.data.Task;
import fcu.ms.data.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskDBTest {

    static final TaskDB taskDB = TaskDB.getInstance();
    static final String taskname = "中文";

    @Test
    public void createTask() {
        Timestamp currentTime = new Timestamp (new Date().getTime());

        Task task = new Task(taskname, "testing-message", currentTime, currentTime,
                500,"EatTask", 20, currentTime, 20, currentTime, "testing_Address",1);
        assertTrue( taskDB.createTask(task));
    }


    @Test
    void getTasks() {
        List<Task> taskList = taskDB.getTasks();
        assertTrue(taskList.size() > 0); // 如果沒資料會報錯
    }


    @Test
    void getTaskIdByName() {
        assertTrue(taskDB.getTaskIdByName(taskname) > 0);
    }


    @Test
    void getTask() {
        int id = taskDB.getTaskIdByName(taskname);
        Task task =taskDB.getTask(id);
        assertEquals(taskname, task.getTaskName());
    }

    @Test
    public void setTask(){
        int id = taskDB.getTaskIdByName(taskname);
        Timestamp currentTime = new Timestamp (new Date().getTime());

        Task task = new Task(id, taskname, "changr-message", currentTime, currentTime,
                600,"ChangeTask", 10, currentTime, 10, currentTime, "change_Address",2);

        assertTrue(taskDB.setTask(task));
    }


    @Test
    void deleteTask() {
        int id = taskDB.getTaskIdByName(taskname);
        assertTrue(taskDB.deleteTask(id));
    }
}
