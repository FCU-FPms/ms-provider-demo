package fcu.ms.data;

public class Message {
    private int id;
    private String content;
    private int userID;
    private int receiverID;

    public Message(int id, String content, int userID, int receiverID){
        this.id = id;
        this.content = content;
        this.userID = userID;
        this.receiverID = receiverID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }
}
