package fcu.ms.data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Task {
    private int taskID = -1;
    private String name;
    private String message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startPostTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endPostTime;

    private int salary;
    private String typeName;
    private int releaseUserID = -1;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime releaseTime;

    private int receiveUserID = -1;

    private String taskAddress;

    public Task() {
        // 需要這個做反序列化, 跟 import com.fasterxml.jackson.databind.ObjectMapper; 的 convertValue 有關
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }

    public String getTaskAddress() {
        return taskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        this.taskAddress = taskAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getStartPostTime() { return startPostTime; }

    public void setStartPostTime(LocalDateTime startPostTime) { this.startPostTime = startPostTime; }

    public LocalDateTime getEndPostTime() {
        return endPostTime;
    }

    public void setEndPostTime(LocalDateTime endPostTime) {
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

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getReceiveUserID() {
        return receiveUserID;
    }

    public void setReceiveUserID(int receiveUserID) {
        this.receiveUserID = receiveUserID;
    }

}
