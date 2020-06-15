package fcu.ms.data;
import java.util.Date;

public class Task {
    private int TaskID = -1;
    private String TaskName = "";
    private String Message = "";
    private Date PostTime = null;
    private int Salary = 0;
    private String TypeName = null;
    private int ReleaseUserID = -1;
    private Date ReleaseTime = null;
    private int ReceiveUserID = -1;
    private Date ReceiveTime = null;

    public Task(int taskID, String taskName, String message, Date postTime, int salary,
                String typeName, int releaseUserID, Date releaseTime, int receiveUserID, Date receiveTime) {
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

    public Task(int taskID, String taskName, String message, Date postTime, int salary,
                String typeName) {
        this.TaskID = taskID;
        this.TaskName = taskName;
        this.Message = message;
        this.PostTime = postTime;
        this.Salary = salary;
        this.TypeName = typeName;
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

    public Date getPostTime() {
        return PostTime;
    }

    public void setPostTime(Date postTime) {
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

    public Date getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        ReleaseTime = releaseTime;
    }

    public int getReceiveUserID() {
        return ReceiveUserID;
    }

    public void setReceiveUserID(int receiveUserID) {
        ReceiveUserID = receiveUserID;
    }

    public Date getReceiveTime() {
        return ReceiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        ReceiveTime = receiveTime;
    }
}
