package fcu.ms.db;

import fcu.ms.data.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class TaskDBTest {

    TaskDB taskDB = TaskDB.getInstance();
    String taskname = "unitTest_taskName";
    @Test
    public void createTask() {
        assertTrue( taskDB.createTask(taskname, "testing-message",new Timestamp(new Date().getTime()),500));
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
        assertTrue(taskDB.setTask(id,"Setting_test","Setting_Message_Test", new Timestamp(new Date().getTime()),850));
        Task task =taskDB.getTask(id);
        assertEquals("Setting_test", task.getTaskName());

        //回復
        assertTrue(taskDB.setTask(id, taskname,"Setting_Message_Test", new Timestamp(new Date().getTime()),850));
        task =taskDB.getTask(id);
        assertEquals(taskname, task.getTaskName());
    }


    @Test
    void deleteTask() {
        int id = taskDB.getTaskIdByName(taskname);
        assertTrue(taskDB.deleteTask(id));
    }
}
