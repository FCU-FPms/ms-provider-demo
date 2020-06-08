package fcu.ms.db;

import fcu.ms.data.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Date;
import java.sql.Timestamp;

public class TaskDBTest {
    @Test
    public void createTask_TEST() {
        TaskDB taskDB = TaskDB.getInstance();
        assertTrue( taskDB.createTask("unitTest_taskName", "testing-message",new Timestamp(new Date().getTime()),500));
    }

    @Test
    public void getTask_TEST() {
        TaskDB taskDB = TaskDB.getInstance();
        Task task = taskDB.getTask("unitTest_taskName");
        assertEquals("unitTest_taskName", task.getTaskName());
        assertEquals("testing-message", task.getMessage());
        assertEquals(500,task.getSalary());
    }
    @Test
    public void deleteTask_TEST() {
        TaskDB taskDB = TaskDB.getInstance();
        assertTrue(taskDB.deleteTask("unitTest_taskName"));
    }
}
