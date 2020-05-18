package fcu.ms.data;

public class User {
    private int id;
    private String phoneNumber;
    private String userName;

    public User(int id, String phoneNumber, String userName) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

