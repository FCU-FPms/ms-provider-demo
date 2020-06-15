package fcu.ms.db;

import fcu.ms.data.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserDBTest {
    @Test
    public void createUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        assertTrue( userDB.createUser("unitTest_username", "0912345678", "test1111111") );
    }

    @Test
    public void getUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        User user = userDB.getUser("testing123");
        assertEquals("0912345678", user.getPhoneNumber());
        assertEquals("testing123", user.getUserName());
        assertEquals("ppp12345678", user.getUserPassword());
        System.out.println(user.getUserPassword());
    }

    @Test
    public void deleteUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        assertTrue(userDB.deleteUser("testing123"));
    }

}
