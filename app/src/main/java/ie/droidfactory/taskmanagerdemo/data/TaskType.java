package ie.droidfactory.taskmanagerdemo.data;


import ie.droidfactory.taskmanagerdemo.R;

public enum TaskType {

    OPEN("OPEN", true, R.color.colorTaskOpen, "travel"),
    TRAVELLING("TRAVELLING", true, R.color.colorTaskTravelling, "work"),
    WORKING("WORKING", true, R.color.colorTaskWorking, "stop"),
    STOP("STOP", false, R.color.colorTaskWorking, null),
    DONE("DONE", false, R.color.colorTaskInactive, null),
    INACTIVE("INACTIVE", false, R.color.colorTaskInactive, null);

    private String taskStatus;
    private boolean showStatus;
    private int taskColor;
    private String buttonName;

    TaskType(String taskStatus, boolean showStatus, int taskColor, String buttonName) {
        this.taskStatus = taskStatus;
        this.showStatus = showStatus;
        this.taskColor = taskColor;
        this.buttonName = buttonName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public boolean isShowingStatus() {
        return showStatus;
    }

    public int getTaskColor() {
        return taskColor;
    }

    public String getButtonName() {
        return buttonName;
    }

}
