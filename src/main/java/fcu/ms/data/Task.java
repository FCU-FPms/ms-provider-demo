package fcu.ms.data;

public class Task {
    private int TaskID;
    private String TaskName;
    private String Message;
    private String PostTime;
    private int Salary;
    private String TypeName;
    private int ReleaseUserID;
    private String ReleaseTime;
    private int ReceiveUserID;
    private String ReceiveTime;

    public Task(int taskID, String taskName, String message, String postTime, int salary,
                String typeName, int releaseUserID, String releaseTime, int receiveUserID, String receiveTime) {
        this.TaskID = taskID;
        this.TaskName = taskName;
        this.Message = message;
        this.PostTime = postTime;
        this.Salary = salary;
        this.TypeName = typeName;
        this.ReleaseUserID = releaseUserID;
        this.ReleaseTime = releaseTime;
        this.ReceiveUserID = receiveUserID;
        this.ReceiveTime = receiveTime;
    }

    public int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int taskID) {
        TaskID = taskID;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPostTime() {
        return PostTime;
    }

    public void setPostTime(String postTime) {
        PostTime = postTime;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public int getReleaseUserID() {
        return ReleaseUserID;
    }

    public void setReleaseUserID(int releaseUserID) {
        ReleaseUserID = releaseUserID;
    }

    public String getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        ReleaseTime = releaseTime;
    }

    public int getReceiveUserID() {
        return ReceiveUserID;
    }

    public void setReceiveUserID(int receiveUserID) {
        ReceiveUserID = receiveUserID;
    }

    public String getReceiveTime() {
        return ReceiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        ReceiveTime = receiveTime;
    }
}
