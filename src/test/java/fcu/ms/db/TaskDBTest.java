package fcu.ms.db;

import fcu.ms.data.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskDBTest {

    static final TaskDB taskDB = TaskDB.getInstance();
    static final String taskname = "中文";

    @Test
    public void createTask() {
        LocalDateTime currentTime = LocalDateTime.now();
        Task task = new Task(taskname, "testing-message", currentTime, currentTime,
                500,"EatTask", 1, currentTime, 1, "testing_Address");
        assertTrue( taskDB.createTask(task) );

        Task task2 = new Task(taskname, "testing-message", currentTime, null,
                500,"EatTask", 1, currentTime, 1, "testing_Address");
        assertTrue( taskDB.createTask(task2) );
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
        assertEquals(taskname, task.getName());
    }

    @Test
    public void setTask(){
        int id = taskDB.getTaskIdByName(taskname);

        LocalDateTime currentTime = LocalDateTime.now();


        Task task = new Task(id, taskname, "changr-message", currentTime, currentTime,
                600,"ChangeTask", 1, currentTime, 1, "change_Address");

        assertTrue(taskDB.setTask(task));
    }


    @Test
    void deleteTask() {
        assertTrue(taskDB.deleteTask(324));
    }
}
