package fcu.ms.data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Task {
    private int taskID = -1;
    private String taskName;
    private String message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startPostTime;

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

    public Task(String taskName, String message, LocalDateTime startPostTime, Timestamp endPostTime, // 新增資料表用的 不用填ID
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

    // API新增資料用的 不用填ID 與 receiveUserID, receiveTime
    public Task(String taskName, String message, LocalDateTime startPostTime, Timestamp endPostTime,
                int salary, String typeName, int releaseUserID, Timestamp releaseTime, String taskAddress, int taskCity) {
        this.taskName = taskName;
        this.message = message;
        this.startPostTime = startPostTime;
        this.endPostTime = endPostTime;
        this.salary = salary;
        this.typeName = typeName;
        this.releaseUserID = releaseUserID;
        this.releaseTime = releaseTime;
        this.taskAddress = taskAddress;
        this.taskCity = taskCity;
    }

    public Task(int taskID, String taskName, String message, LocalDateTime startPostTime, Timestamp endPostTime, // 用來從資料表拿Task
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

    public LocalDateTime getStartPostTime() { return startPostTime; }

    public void setStartPostTime(LocalDateTime startPostTime) { this.startPostTime = startPostTime; }

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
