package org.example;
import java.io.*;
import java.util.*;

public class TaskManager {

    private List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }


    public Task getTask(int number) {
        return taskList.get(number);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void loadTask(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            taskList = (List<Task>) ois.readObject();
        }
    }

    public void saveTask(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(taskList);
        }
    }
}
