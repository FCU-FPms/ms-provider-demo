package fcu.ms.db;

import fcu.ms.data.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDBTest {
    @Test
    public void createUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        User user = new User("testing123", "0912345678", "firebase_uid_test");
        assertTrue( userDB.createUser(user) );
    }

    @Test
    public void getUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        User user = userDB.getUser("franky");
        System.out.println(user.getName());
    }

    @Test
    public void deleteUser_TEST() {
        UserDB userDB = UserDB.getInstance();
        assertTrue(userDB.deleteUser("testing123"));
    }
}
