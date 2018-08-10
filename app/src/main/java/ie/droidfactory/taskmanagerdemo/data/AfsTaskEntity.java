package ie.droidfactory.taskmanagerdemo.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import ie.droidfactory.taskmanagerdemo.model.AfsTask;

@Entity(tableName = "tasks")
public class AfsTaskEntity implements AfsTask{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "task_name")
    private String taskName;

    @ColumnInfo(name = "task_description")
    private String taskDescription;

    @ColumnInfo(name = "date_start")
    private String dateStart;

    @ColumnInfo(name = "date_last")
    private String dateLastAccess;

    @ColumnInfo(name = "task_status")
    private String taskStatus;

    public AfsTaskEntity(int id, String taskName, String taskDescription, String dateStart, String dateLastAccess, String taskStatus) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.dateStart = dateStart;
        this.dateLastAccess = dateLastAccess;
        this.taskStatus = taskStatus;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String getDateStart() {
        return dateStart;
    }

    @Override
    public String getDateLastAccess() {
        return dateLastAccess;
    }

    @Override
    public String getTaskStatus() {
        return taskStatus;
    }

    public TaskType getTaskStatusType() {
        return TaskType.valueOf(taskStatus.toUpperCase());
    }

    public int getStatusColor(){
        return TaskType.valueOf(taskStatus.toUpperCase()).getTaskColor();
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateLastAccess(String dateLastAccess) {
        this.dateLastAccess = dateLastAccess;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
