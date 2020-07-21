package fcu.ms.data;
import java.util.Date;

public class Task {
    private int TaskID = -1;
    private String TaskName = "";
    private String Message = "";
    private Date PostTime = null;
    private int Salary = 0;
    private String TypeName = "";
    private int ReleaseUserID = -1;
    private Date ReleaseTime = null;
    private int ReceiveUserID = -1;
    private Date ReceiveTime = null;
    private String TaskAddress = "";
    private int TaskCity = -1;


    public Task(String taskName, String message, Date postTime, int salary,
                String typeName, String TaskAddress, int TaskCity) {
        this.TaskName = taskName;
        this.Message = message;
        this.PostTime = postTime;
        this.Salary = salary;
        this.TypeName = typeName;
        this.TaskAddress = TaskAddress;
        this.TaskCity = TaskCity;
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

    public String getTaskAddress() {
        return TaskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        TaskAddress = taskAddress;
    }

    public int getTaskCity() {
        return TaskCity;
    }

    public void setTaskCity(int taskCity) {
        TaskCity = taskCity;
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
