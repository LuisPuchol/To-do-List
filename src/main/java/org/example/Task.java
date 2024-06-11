package org.example;

public class Task {
    private String taskAction;
    private boolean taskCompleted;

    public Task (String taskAction) {
        this.taskAction = taskAction;
        this.taskCompleted = false;
    }

    public String getTaskAction() {
        return taskAction;
    }

    public void setTaskAction(String taskAction) {
        this.taskAction = taskAction;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

}
