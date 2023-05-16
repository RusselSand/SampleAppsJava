import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Manager {
    public static int counter;
    public static HashMap<Integer, Task> tasks = new HashMap<>();
    public static HashMap<Integer, SubTask> subTasks = new HashMap<>();
    public static HashMap<Integer, EpicTask> epicTasks = new HashMap<>();

    public static void main(String[] args) {
        Task task1 = new Task("Убрать террасу", "Помой все полы и подмети листья", counter, 0);
        counter++;
        Task task2 = new Task("Развесить белье", "Сними высохшее и развесь новое", counter, 0);
        counter++;
        create(task1);
        create(task2);
        EpicTask epic1 = new EpicTask("Убрать кухню", "Кухня должна блестеть", counter, 0);
        counter++;
        EpicTask epic2 = new EpicTask("Убрать ванную", "Ванная должна блестеть", counter, 0);
        counter++;
        create(epic1);
        create(epic2);
        SubTask subTask1 = new SubTask("Помыть посуду", "Загрузить всё в посудомойку", counter, 0,
                epic1.getId());
        counter++;
        SubTask subTask2 = new SubTask("Помыть полы", "Протереть зону до стола", counter, 0,
                epic1.getId());
        counter++;
        SubTask subTask3 = new SubTask("Помыть унитаз", "Вычистить вилкой", counter, 0,
                epic2.getId());
        counter++;
        create(subTask1);
        create(subTask2);
        create(subTask3);
        printAllTasks();
        task2.setState(2);
        update(task2, task2.getId());
        subTask2.setState(2);
        subTask3.setState(2);
        update(subTask2, subTask2.getId());
        update(subTask3, subTask3.getId());
        printAllTasks();
        delete("Task", task1.getId());
        delete("EpicTask", epic2.getId());
        printAllTasks();
        deleteAllTasks("Task");
        printAllTasks();
    }

    public static ArrayList<Task> getAllTasks(String className){
        switch (className) {
            case "EpicTask" -> {
                return new ArrayList<>(epicTasks.values());
            }
            case "Task" -> {
                return new ArrayList<>(tasks.values());
            }
            case "SubTask" -> {
                return new ArrayList<>(subTasks.values());
            }
        }
        return new ArrayList<>();
    }
    public static void deleteAllTasks(String className){
        switch (className) {
            case "EpicTask" -> epicTasks.clear();
            case "Task" -> tasks.clear();
            case "SubTask" -> subTasks.clear();
        }
    }
    public static Task getById(String className, int id){
        switch (className) {
            case "EpicTask" -> {
                return epicTasks.get(id);
            }
            case "Task" -> {
                return tasks.get(id);
            }
            case "SubTask" -> {
                return subTasks.get(id);
            }
        }
        return null;
    }
    public static void create(Task task){
        switch (task.getClass().getName()) {
            case "EpicTask" -> {
                epicTasks.put(task.getId(), (EpicTask) task);
                System.out.println("Added new task to epics");
            }
            case "Task" -> {
                tasks.put(task.getId(), task);
                System.out.println("Added new task to tasks");
            }
            case "SubTask" -> {
                subTasks.put(task.getId(), (SubTask) task);
                System.out.println("Added new task to subtasks");
                checkEpicState(((SubTask) task).getEpicId());
            }
        }
    }
    public static void update(Task task, int id){
        switch (task.getClass().getName()) {
            case "EpicTask" -> {
                epicTasks.put(id, (EpicTask) task);
                System.out.println("Epic updated");
            }
            case "Task" -> {
                tasks.put(id, task);
                System.out.println("Task updated");
            }
            case "SubTask" -> {
                subTasks.put(id, (SubTask) task);
                System.out.println("Subtask updated");
                checkEpicState(((SubTask) task).getEpicId());
            }
        }
    }
    public static void delete(String className, int id){
        switch (className) {
            case "EpicTask" -> {
                epicTasks.remove(id);
                ArrayList<SubTask> subtasksToDelete = getAllSubTasks(id);
                for (SubTask subTask: subtasksToDelete) {
                    subTasks.remove(subTask.getId());
                }
                System.out.println("Epic deleted");
            }
            case "Task" -> {
                tasks.remove(id);
                System.out.println("Task deleted");
            }
            case "SubTask" -> {
                SubTask task = (SubTask) getById("SubTask",id);
                subTasks.remove(id);
                System.out.println("Subtask deleted");
                if(task != null) {
                    checkEpicState(((SubTask) task).getEpicId());
                }
            }
        }
    }
    public static ArrayList<SubTask> getAllSubTasks(int epicId){
        return subTasks.values().stream().filter(subtask ->
                epicId == subtask.getEpicId()).collect(Collectors.toCollection(ArrayList::new));
    }
    public static void checkEpicState(int id){
        EpicTask epic = (EpicTask) getById("EpicTask", id);
        if(epic == null){
            return;
        }
        int epicState = epic.getState();
        ArrayList<Integer> subtasks = getAllSubTasks(id).stream().map(Task::getState)
                .collect(Collectors.toCollection(ArrayList::new));
        int subTasksSum = subtasks.stream().mapToInt(Integer::intValue).sum();
        if(subTasksSum == 0 ){
            epicState = 0;
        } else if (subTasksSum == subtasks.size() * 2) {
            epicState = 2;
        } else{
            epicState = 1;
        }
        epic.setState(epicState);
        update(epic, id);
    }
    public static void printAllTasks(){
        ArrayList<Task> tasks = getAllTasks("Task");
        ArrayList<Task> epics = getAllTasks("EpicTask");
        for (Task task : tasks) {
            System.out.println(task);
        }
        for (Task task : epics) {
            System.out.println(task);
            ArrayList<SubTask> subTasksToShow = getAllSubTasks(task.getId());
            for (SubTask subtask : subTasksToShow) {
                System.out.println("    " + subtask);
            }
        }
    }
}
