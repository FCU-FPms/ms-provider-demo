package fcu.ms.db;

import fcu.ms.data.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDBTest {

    UserDB userDB = UserDB.getInstance();
    @Test
    public void createUser_TEST() {
        User user = new User("testing123", "firebase_uid_test");
        assertTrue( userDB.createUser(user) );
    }

    @Test
    public void getUser_TEST() {
        User user = userDB.getUser(24);
        System.out.println(user.getName());
    }

    @Test
    public void deleteUser_TEST() {
        assertTrue(userDB.deleteUser("testing123"));
    }

    @Test
    void getUserByFirebaseUID() {
        User user = userDB.getUserByFirebaseUID("firebase_uid_test");
        System.out.println(user);
    }
}
