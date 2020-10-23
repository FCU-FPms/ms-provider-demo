package fcu.ms.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RequestTaskUsersDBTest {

    static final RequestTaskUsersDB instance  = RequestTaskUsersDB.getInstance();

    @Test
    public void addUserToTaskRequestList() {
        int userID = 1;
        int TaskID = 325;
        assertTrue( instance.addUserToTaskRequestList(userID, TaskID) );
    }

    @Test
    public void removeUserFromTaskRequestList() {
        int userID = 1;
        int TaskID = 325;
        assertTrue( instance.removeUserFromTaskRequestList(userID, TaskID) );
    }

    @Test
    public void getRequestUsersID() {

        int TaskID = 325;
        List<Integer> usersID = instance.getRequestUsersID(TaskID);
        System.out.println(usersID);
    }
}