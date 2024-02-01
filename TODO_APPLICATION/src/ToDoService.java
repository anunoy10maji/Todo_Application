import java.util.List;

public interface ToDoService {


    void addTask(Task task);

    Boolean validate();

    List<Task> viewAllTasks();

    String updateTask(int taskID,Task task);

    String deleteTask(int taskId);

    List<Task> sortById();

    List<Task> sortByStatus();

    List<Task> sortByDeadline();

    List<Task> sortByPriority();

    Task searchById(String id);


    List<Task> searchByStatus(String status);

    List<Task> searchByKeyword(String keyword);

    List<Task> searchByName(String content);

    List<Task> viewTasks(String pageNumber);
}
