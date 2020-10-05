package fcu.ms.data;
import java.sql.Timestamp;
import java.util.Date;

public class Task {
    private int taskID = -1;
    private String taskName;
    private String message;
    private Timestamp startPostTime;
    private Timestamp endPostTime;
    private int salary;
    private String typeName;
    private int releaseUserID = -1;
    private Timestamp releaseTime;
    private int receiveUserID = -1;
    private Timestamp receiveTime;
    private String taskAddress;
    private int taskCity;

    public Task(){
        // 需要這個做反序列化, 跟 import com.fasterxml.jackson.databind.ObjectMapper; 的 convertValue 有關
    }

    public Task(String taskName, String message, Timestamp startPostTime, Timestamp endPostTime, // 新增資料表用的 不用填ID
                int salary, String typeName, int releaseUserID, Timestamp releaseTime, int receiveUserID,
                Timestamp receiveTime, String taskAddress, int taskCity) {
        this.taskName = taskName;
        this.message = message;
        this.startPostTime = startPostTime;
        this.endPostTime = endPostTime;
        this.salary = salary;
        this.typeName = typeName;
        this.releaseUserID = releaseUserID;
        this.releaseTime = releaseTime;
        this.receiveUserID = receiveUserID;
        this.receiveTime = receiveTime;
        this.taskAddress = taskAddress;
        this.taskCity = taskCity;
    }

    public Task(int taskID, String taskName, String message, Timestamp startPostTime, Timestamp endPostTime, // 用來從資料表拿Task
                int salary, String typeName, int releaseUserID, Timestamp releaseTime, int receiveUserID,
                Timestamp receiveTime, String taskAddress, int taskCity) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.message = message;
        this.startPostTime = startPostTime;
        this.endPostTime = endPostTime;
        this.salary = salary;
        this.typeName = typeName;
        this.releaseUserID = releaseUserID;
        this.releaseTime = releaseTime;
        this.receiveUserID = receiveUserID;
        this.receiveTime = receiveTime;
        this.taskAddress = taskAddress;
        this.taskCity = taskCity;
    }

    public Task(String taskName, String message, Timestamp startPostTime, Timestamp endPostTime, int salary,
                String typeName, String taskAddress, int taskCity) {
        this.taskName = taskName;
        this.message = message;
        this.startPostTime = startPostTime;
        this.endPostTime = endPostTime;
        this.salary = salary;
        this.typeName = typeName;
        this.taskAddress = taskAddress;
        this.taskCity = taskCity;
    }


    public Task(int taskID, String taskName, String message, Timestamp startPostTime, Timestamp endPostTime, int salary,
                String typeName, String taskAddress, int taskCity) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.message = message;
        this.startPostTime = startPostTime;
        this.endPostTime = endPostTime;
        this.salary = salary;
        this.typeName = typeName;
        this.taskAddress = taskAddress;
        this.taskCity = taskCity;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskAddress() {
        return taskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        this.taskAddress = taskAddress;
    }

    public int getTaskCity() {
        return taskCity;
    }

    public void setTaskCity(int taskCity) {
        this.taskCity = taskCity;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getStartPostTime() { return startPostTime; }

    public void setStartPostTime(Timestamp startPostTime) { this.startPostTime = startPostTime; }

    public Timestamp getEndPostTime() {
        return endPostTime;
    }

    public void setEndPostTime(Timestamp endPostTime) {
        this.endPostTime = endPostTime;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getReleaseUserID() {
        return releaseUserID;
    }

    public void setReleaseUserID(int releaseUserID) {
        this.releaseUserID = releaseUserID;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getReceiveUserID() {
        return receiveUserID;
    }

    public void setReceiveUserID(int receiveUserID) {
        this.receiveUserID = receiveUserID;
    }

    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }
}
