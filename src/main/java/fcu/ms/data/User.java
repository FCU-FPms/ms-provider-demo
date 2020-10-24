package fcu.ms.data;

public class User {
    private int id;
    private String name;
    private String phone;
    private String firebaseUid;

    public User() {

    }

    public User(int id, String name, String phone, String firebaseUid) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.firebaseUid = firebaseUid;
    }

    public User(String name, String phone, String firebaseUid) {
        this.name = name;
        this.phone = phone;
        this.firebaseUid = firebaseUid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", firebaseUid='" + firebaseUid + '\'' +
                '}';
    }
}

