package fcu.ms.db;

import fcu.ms.data.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskDBTest {
    @Test
    public void createTask_TEST() {
        TaskDB taskDB = TaskDB.getInstance();
        assertTrue( taskDB.createTask("test111", "testing-message","20000101",500));
    }

    @Test
    public void getTask_TEST() {
        TaskDB taskDB = TaskDB.getInstance();
        Task task = taskDB.getTask(1);
        assertEquals("test111", task.getTaskName());
        assertEquals("testing-message", task.getMessage());
        assertEquals("20000101",task.getPostTime());
        assertEquals(500,task.getSalary());
    }
}
