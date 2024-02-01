import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoRepo {
    private List<Task> tasks=new ArrayList<>();
    public void addTask(Task task)
    {
        tasks.add(task);
    }

    public Boolean validate()
    {
        if(tasks.size()==0){
            return false;
        }
        return true;
    }

    public List<Task> viewAllTasks()
    {
        return tasks;
    }

    public void deleteTask(Task task)
    {
        tasks.remove(task);
    }
}
