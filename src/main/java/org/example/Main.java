package org.example;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    static boolean exitMenu = false;
    //Task task = new Task();
    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {

        System.out.println("Select an option:");

        while (!exitMenu) {
            showMenu();
        }

    }

    public static void showMenu() {
        System.out.println("1. Add task");
        System.out.println("2. Show tasks");
        System.out.println("3. Remove task");
        System.out.println("4. Mark as complete");
        System.out.println("5. Exit");

        int option = sc.nextInt();

        switch (option){
            case 1:
                addTask();
                break;
            case 2:
                showTasks();
                break;
            case 3:
                removeTask();
                break;
            case 4:
                //4
                break;
            case 5:
                exitMenu = true;
                break;
            default:
                System.out.println("Error");
                break;
        }
        System.out.println("\n");

    }

    public static void addTask() {
        sc.nextLine();
        System.out.println("What you want to add:");
        String actualTask = sc.nextLine();
        Task task = new Task(actualTask);
        taskManager.addTask(task);
        System.out.print("Task added successfully");
    }

    public static void showTasks() {
        ArrayList<Task> completedTasks = new ArrayList<>();
        ArrayList<Task> uncompletedTasks = new ArrayList<>();

        for (Task actualTask : taskManager.getTaskList()){
            if (actualTask.isTaskCompleted()){
                completedTasks.add(actualTask);
            } else {
                uncompletedTasks.add(actualTask);
            }

        }

        System.out.println("Tasks completed: " + completedTasks.size());
        for (Task actualCompletedTask : completedTasks) {
            System.out.println(actualCompletedTask.getTaskAction());
        }
        System.out.println("Tasks un-completed: " + uncompletedTasks.size());
        for (Task actualUncompletedTask : uncompletedTasks) {
            System.out.println(actualUncompletedTask.getTaskAction());
        }
    }

    public static void removeTask() {
        System.out.println("Wich Task you want to delete?:");
        for (Task actualTask : taskManager.getTaskList()) {
            int counter = 1;
            System.out.println(counter + ". " + actualTask.getTaskAction());
        }
    }
}