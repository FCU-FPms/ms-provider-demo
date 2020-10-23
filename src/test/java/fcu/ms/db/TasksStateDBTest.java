package fcu.ms.db;

import fcu.ms.data.TaskState;
import fcu.ms.data.TaskStateEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TasksStateDBTest {
    TasksStateDB tasksStateDB = TasksStateDB.getTasksStateDB();

    @Test
    void createTaskState() {
        int taskID = 325;

        LocalDateTime currentTime = LocalDateTime.now();

        TaskState taskState = new TaskState(taskID, TaskStateEnum.BOOS_ACCOUNT_THE_TASK_IS_DONE, currentTime);
        tasksStateDB.createTaskState(taskState);
    }

    @Test
    void getLatestStateByTaskID() {
        int taskID = 325;
        TaskState taskState = tasksStateDB.getLatestStateByTaskID(taskID);
        System.out.println(taskState);
    }
}