package fcu.ms.provider;

import fcu.ms.data.Message;
import fcu.ms.data.User;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilForJson {
    public static JSONObject getUserEntity(User user) {
        JSONObject entity = new JSONObject();
        entity.put("name", user.getName());
        entity.put("phone", user.getPhone());
        entity.put("firebase_uid", user.getFirebaseUid());
        return entity;
    }

    public static Map<String, JSONObject> getEachUser(List<User> users) {
        Map<String, JSONObject> entities = new HashMap<>();

        for (User user : users) {
            int userId = user.getId();
            JSONObject entity = getUserEntity(user);
            entities.put(String.valueOf(userId), entity);
        }
        return entities;
    }

}
