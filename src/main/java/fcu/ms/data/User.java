package fcu.ms.data;

public class User {
    private int id;
    private String phoneNumber;
    private String userName;
    private String userPassword;

    public User(int id, String phoneNumber, String userName, String userPassword) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.userPassword = userPassword;
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

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
}

