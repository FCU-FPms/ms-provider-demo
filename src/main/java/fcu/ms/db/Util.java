package fcu.ms.db;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Util {

    public static Timestamp transitLocalDateTime(LocalDateTime localDateTime) { // 如果是null 會回傳null
        if(localDateTime != null) {
            return Timestamp.valueOf(localDateTime);
        } else {
            return null;
        }
    }

    public static LocalDateTime transitTimestamp(Timestamp timestamp) { // 如果是null 會回傳null
        if(timestamp != null) {
            return timestamp.toLocalDateTime();
        } else {
            return null;
        }
    }

}
