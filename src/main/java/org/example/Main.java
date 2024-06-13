package org.example;
import java.io.IOException;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    static boolean exitMenu = false;
    private static TaskManager taskManager = new TaskManager();
    private static final String toDoList = "toDoList.ser";

    public static void main(String[] args) {
        try {
            taskManager.loadTask(toDoList);
            System.out.println("Tasks loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Not previous tasks found or failed to load tasks.");
        }
        System.out.println("Select an option:");

        while (!exitMenu) {
            showMenu();
        }

        try {
            taskManager.saveTask(toDoList);
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save tasks.");
        }

    }

    public static void showMenu() {
        System.out.println("1. Add task");
        System.out.println("2. Show tasks");
        System.out.println("3. Remove task");
        System.out.println("4. Mark as complete");
        System.out.println("5. Save & Exit");

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
                markTask();
                break;
            case 5:
                exitMenu = true;
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    public static void addTask() {
        sc.nextLine();
        System.out.println("What you want to add:");
        String actualTask = sc.nextLine();
        Task task = new Task(actualTask);
        taskManager.getTaskList().add(task);
        //taskManager.addTask(task);
        System.out.println("Task added successfully\n");
    }

    public static void showTasks() {

        ArrayList<Task> completedTasks = tasksCompletedAndUncompleted("completed");
        ArrayList<Task> uncompletedTasks = tasksCompletedAndUncompleted("uncompleted");

        System.out.println("Tasks completed: " + completedTasks.size());
        for (Task actualCompletedTask : completedTasks) {
            System.out.println(actualCompletedTask.getTaskAction());
        }

        System.out.println("Tasks un-completed: " + uncompletedTasks.size());
        for (Task actualUncompletedTask : uncompletedTasks) {
            System.out.println(actualUncompletedTask.getTaskAction());
        }
        System.out.println("\n");
    }

    public static void removeTask() {
        System.out.println("Wich Task you want to delete?:");

        taskListPrintOrganizer("all");

        int idTask = sc.nextInt() - 1;
        taskManager.getTaskList().remove(idTask);
        System.out.println("Task removed successfully.\n");
    }

    public static void markTask() {
        System.out.println("Wich Task you want to mark as Complete?:");

        taskListPrintOrganizer("uncompleted");

        List<Task> uncompletedTasks = tasksCompletedAndUncompleted("uncompleted");

        int idTask = sc.nextInt() - 1;

        Task taskToMark = uncompletedTasks.get(idTask);
        taskToMark.setTaskCompleted(true);

        System.out.println("Task marked as completed.");

        System.out.println("Do you want to remove this completed Task?:[Y/N]");

        sc.nextLine();
        String yesNo = sc.nextLine().toLowerCase();

        if (yesNo.charAt(0) == 'y' || yesNo.charAt(0) == 'Y') {
            taskManager.getTaskList().remove(taskToMark);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Task was not removed.");
        }
    }

    public static void taskListPrintOrganizer (String list) {
        List<Task> tasksToPrint = switch (list) {
            case "all" -> taskManager.getTaskList();
            case "uncompleted" -> tasksCompletedAndUncompleted("uncompleted");
            case "completed" -> tasksCompletedAndUncompleted("completed");
            default -> new ArrayList<>();
        };
        taskListPrint((ArrayList<Task>) tasksToPrint);
    }

    public static ArrayList<Task> tasksCompletedAndUncompleted(String list) {
        ArrayList<Task> completedTasks = new ArrayList<>();
        ArrayList<Task> uncompletedTasks = new ArrayList<>();

        for (Task actualTask : taskManager.getTaskList()){
            if (actualTask.isTaskCompleted()){
                completedTasks.add(actualTask);
            } else {
                uncompletedTasks.add(actualTask);
            }
        }
        if (list.equalsIgnoreCase("completed")) {
            return completedTasks;
        } else if (list.equalsIgnoreCase("uncompleted")) {
            return uncompletedTasks;
        }
        return (ArrayList<Task>) taskManager.getTaskList();
    }

    public static void taskListPrint (ArrayList<Task> taskList) {

        int counter = 1;
        for (Task actualTask : taskList) {
            System.out.println(counter + ". " + actualTask.getTaskAction() + (actualTask.isTaskCompleted()? " (completed)" : ""));
            counter ++;
        }

    }
}