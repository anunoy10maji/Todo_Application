import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ToDoServiceImpl implements ToDoService{

    private ToDoRepo toDoRepo=new ToDoRepo();
    @Override
    public void addTask(Task task)
    {
        System.out.println(validateTask(task));
        toDoRepo.addTask(task);
    }

    @Override
    public Boolean validate()
    {
        return toDoRepo.validate();
    }

    @Override
    public List<Task> viewAllTasks() {
        return toDoRepo.viewAllTasks();
    }

    @Override
    public String updateTask(int taskID,Task tsk) {
        List<Task> tasks=viewAllTasks();
        Optional<Task> first=tasks.stream().filter(task -> task.getId()==taskID).findFirst();
        Task task=first.get();
        if(first.isPresent()){
            task=first.get();
        }
        if(task==null){
            return "No task found!";
        }
        task.setName(tsk.getName());
        task.setDescription(tsk.getDescription());
        task.setDeadline(tsk.getDeadline());
        task.setComments(tsk.getComments());
        task.setPriority(tsk.getPriority());
        task.setStatus(tsk.getStatus());
        return "Task Updated Successfully";
    }

    @Override
    public String deleteTask(int taskId) {
        List<Task> tasks=viewAllTasks();
        Optional<Task> first=tasks.stream().filter(task -> task.getId()==taskId).findFirst();
        Task task=null;
        if(first.isPresent()){
            task=first.get();
        }
        if(task==null){
            return "No task found!";
        }
        toDoRepo.deleteTask(task);
        return "Task deleted successfully";
    }

    @Override
    public List<Task> sortById() {
        List<Task> tasks=viewAllTasks();
        List<Task> sortedList=tasks.stream().sorted((task1, task2) -> Integer.compare(task1.getId(), task2.getId())).
                collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Task> sortByStatus() {
        List<Task> tasks=viewAllTasks();
        List<Task> sortedList=tasks.stream().sorted((task1,task2)->
        {
            if (task1.getStatus().equalsIgnoreCase("Pending") &&
                    task2.getStatus().equalsIgnoreCase("Completed")) {
                return -1;
            } else if (task1.getStatus().equalsIgnoreCase("Completed") &&
                    task2.getStatus().equalsIgnoreCase("Pending")) {
                return 1;
            }else {
                return 0;
            }
        }).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Task> sortByDeadline()
    {
        List<Task> tasks=viewAllTasks();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        List<Task> sortedList=tasks.stream().sorted(
                Comparator.comparing(task -> {
                            try {
                                return dateFormat.parse(task.getDeadline());
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
        ).collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Task> sortByPriority() {
        List<Task> tasks=viewAllTasks();
        List<Task> sortedList=tasks.stream().sorted((task1, task2) ->
                        Integer.compare(Integer.parseInt(task1.getPriority()), Integer.parseInt(task2.getPriority()))).
                collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public Task searchById(String id)
    {
        List<Task> tasks=sortById();
        int idx=Integer.parseInt(id);
        int index=binarySearch(tasks,idx);
        if(index==-1){
            return null;
        }
        return tasks.get(index);
    }

    private int binarySearch(List<Task> tasks,int id)
    {
        int low=0;
        int high= tasks.size()-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            int midId=tasks.get(mid).getId();

            if(id==midId)
            {
                return mid;
            }else if(midId<id){
                low=mid+1;
            }else {
                high=mid-1;
            }
        }
        return -1;
    }

    @Override
    public List<Task> searchByStatus(String status) {
        List<Task> tasks=viewAllTasks();
        List<Task> filteredTasks=tasks.stream().filter(task ->
                task.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
        return filteredTasks;
    }

    @Override
    public List<Task> searchByKeyword(String keyword) {
        List<Task> tasks=viewAllTasks();
        List<Task> filteredTasks=tasks.stream().filter(task ->
                task.getName().contains(keyword)).collect(Collectors.toList());
        return filteredTasks;
    }

    @Override
    public List<Task> searchByName(String content) {
        List<Task> tasks=viewAllTasks();
        List<Task> filteredTasks=tasks.stream().filter(task ->
                task.getName().equalsIgnoreCase(content)).collect(Collectors.toList());
        return filteredTasks;
    }

    @Override
    public List<Task> viewTasks(String pageNumber) {
        List<Task> tasks=toDoRepo.viewAllTasks();
        int pageSize=3;
        int items=tasks.size();
        if (items==0){
            return new ArrayList<>();
        }else if (items>=15){
            pageSize=items/Integer.parseInt(pageNumber);
        }
        int offSet=pageSize*(Integer.parseInt(pageNumber)-1);
        int startIndex=offSet;
        int endIndex=Math.min(offSet+pageSize,items);
        if(offSet>=0 && offSet<=items){
            return tasks.subList(startIndex,endIndex);
        }
        return new ArrayList<>();
    }

    public String validateTask(Task task)
    {
        if(task.getName()==null){
            return "Please add a name of the task";
        }
        if(task.getDescription()==null){
            return "Please add a description of the task";
        }
        if(task.getComments()==null){
            return "Please add a comment of the task";
        }
        if(task.getPriority()==null){
            return "Please add a priority of the task";
        }
        return "Task Validated Successfully";
    }
}
