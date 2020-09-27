package fcu.ms.data;
import java.util.Date;

public class Task {
    private int taskID = -1;
    private String taskName = "";
    private String message = "";
    private Date startPostTime = null;
    private  Date endPostTime = null;
    private int salary = 0;
    private String typeName = "";
    private int releaseUserID = -1;
    private Date releaseTime = null;
    private int receiveUserID = -1;
    private Date receiveTime = null;
    private String taskAddress = "";
    private int taskCity = -1;

    public Task(){
        // 需要這個做反序列化, 跟 import com.fasterxml.jackson.databind.ObjectMapper; 的 convertValue 有關
    }

    public Task(String taskName, String message, Date startPostTime, Date endPostTime, int salary,
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


    public Task(int taskID,String taskName, String message, Date startPostTime, Date endPostTime, int salary,
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

    public Date getStartPostTime() { return startPostTime; }

    public void setStartPostTime(Date startPostTime) { this.startPostTime = startPostTime; }

    public Date getEndPostTime() {
        return endPostTime;
    }

    public void setEndPostTime(Date endPostTime) {
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

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getReceiveUserID() {
        return receiveUserID;
    }

    public void setReceiveUserID(int receiveUserID) {
        this.receiveUserID = receiveUserID;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
