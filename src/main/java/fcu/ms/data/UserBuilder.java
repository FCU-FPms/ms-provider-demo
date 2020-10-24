package fcu.ms.data;

public final class UserBuilder {
    private final int id;
    private final String name;
    private String phone;
    private String firebaseUid;

    private UserBuilder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserBuilder anUser(int id, String name) {
        return new UserBuilder(id, name);
    }

    public UserBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder withFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        user.setFirebaseUid(firebaseUid);
        return user;
    }
}
