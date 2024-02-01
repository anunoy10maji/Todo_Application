import java.util.List;
import java.util.Scanner;

public class ToDoController
{
    private ToDoService toDoService = new ToDoServiceImpl();
    private Scanner scanner = new Scanner(System.in);


    //running the application
    public void runToDoApp() {
        showMenuToUser();
        performAction();
    }


    //showing user the operations list
    private void showMenuToUser()
    {
        System.out.println("Welcome to TODO Application");
        System.out.println("Here are the Available Options----> Select the option from the below menu:");
        System.out.println("1. Add a task");
        System.out.println("2. Update a task");
        System.out.println("3. View all tasks");
        System.out.println("4. delete a task");
        System.out.println("5. View tasks by page number");
        System.out.println("6. Sort task");
        System.out.println("7 Search task");
        System.out.println("8. Exit Application");
    }


    //this is the sort operations list
    private void showMenuToUserSort()
    {
        System.out.println("1. Sort task by priority");
        System.out.println("2. Sort task by deadline");
        System.out.println("3. Sort task by status");
        System.out.println("4. Sort task by Id");
    }

    //this is the search operations list
    private void showMenuToUserSearch()
    {
        System.out.println("1. Search task by Id");
        System.out.println("2. Search task by status");
        System.out.println("3. Search task by name");
        System.out.println("4. Search task by keyword");
    }

    //taking input for the main operations
    private String takeUserInput()
    {
        System.out.print("Enter your choice: ");
        return scanner.nextLine().trim();
    }


    //taking input for the sort operations
    private String takeUserInputForSort()
    {
        System.out.print("Enter your choice of sorting: ");
        return scanner.nextLine().trim();
    }

    //taking input for the search operations
    private String takeUserInputForSearch()
    {
        System.out.print("Enter your choice of searching: ");
        return scanner.nextLine().trim();
    }

    //perform main operation as per the input from the user
    private void performAction()
    {
        Boolean flag=true;
        while(flag)
        {
            String input=takeUserInput();
            switch (input)
            {
                case "1":
                    addTask();
                    break;
                case "2":
                    updateTask();
                    break;
                case "3":
                    viewAllTasks();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    System.out.print("Enter the page number: ");
                    String pageNumber=scanner.nextLine().trim();
                    viewTasks(pageNumber);
                    break;
                case "6":
                    sortTask();
                    break;
                case "7":
                    searchTask();
                    break;
                case "8":
                    flag=false;
                    System.out.println("Exit Application");
                    break;
                default:
                    System.out.println("Invalid input. Please choose a valid option.");
                    runToDoApp();
            }
            if(flag==false){
                break;
            }
            showMenuToUser();
        }
    }

    //view all the tasks by page number
    private void viewTasks(String pageNumber)
    {
        List<Task> tasks=toDoService.viewTasks(pageNumber);
        if(tasks.size()==0){
            System.out.println("Please add a task before :)");
        }
        for (Task task:tasks) {
            System.out.println("Task ID:" + task.getId());
            System.out.println("Task Name:" + task.getName());
            System.out.println("Task Description:" + task.getDescription());
            System.out.println("Task Comments:" + task.getComments());
            System.out.println("Task Deadline:" + task.getDeadline());
            System.out.println("Task Priority:" + task.getPriority());
            System.out.println("Task Priority:" + task.getStatus());
            System.out.println("----------------------------------");
        }
    }

    //perform sort operation as per the input from the user
    private void sortTask()
    {
        showMenuToUserSort();
        String input=takeUserInputForSort();
        switch (input)
        {
            case "1":
                sortByPriority();
                break;
            case "2":
                sortByDeadline();
                break;
            case "3":
                sortByStatus();
                break;
            case "4":
                sortById();
                break;
            default:
                System.out.println("Invalid input. Please choose a valid option.");
                runToDoApp();
        }
    }


    //perform search operation as per the input from the user
    private void searchTask()
    {
        showMenuToUserSearch();
        String input=takeUserInputForSearch();
        switch (input)
        {
            case "status":
                System.out.print("Enter the status: ");
                String status=scanner.nextLine().trim();
                searchByStatus(status);
                break;
            case "id":
                System.out.print("Enter the ID: ");
                String Id=scanner.nextLine().trim();
                searchById(Id);
                break;
            case "name":
                System.out.print("Enter the Name: ");
                String content=scanner.nextLine().trim();
                searchByName(content);
                break;
            case "keyword":
                System.out.print("Enter the Name: ");
                String keyword=scanner.nextLine().trim();
                searchByKeyword(keyword);
                break;
            default:
                System.out.println("Invalid input. Please choose a valid option.");
                runToDoApp();
        }
    }

    //search task by keywords
    private void searchByKeyword(String keyword) {
        List<Task> tasks=toDoService.searchByKeyword(keyword);
        if(tasks.size()==0)
        {
            System.out.println("No task found!");
        }else{
            for (Task task:tasks) {
                System.out.println("Task ID:" + task.getId());
                System.out.println("Task Name:" + task.getName());
                System.out.println("Task Description:" + task.getDescription());
                System.out.println("Task Comments:" + task.getComments());
                System.out.println("Task Deadline:" + task.getDeadline());
                System.out.println("Task Priority:" + task.getPriority());
                System.out.println("Task Status:" + task.getStatus());
                System.out.println("----------------------------------");
            }
        }
    }


    //search task by name
    private void searchByName(String content) {
        List<Task> tasks=toDoService.searchByName(content);
        if(tasks.size()==0)
        {
            System.out.println("No task found!");
        }else{
            for (Task task:tasks) {
                System.out.println("Task ID:" + task.getId());
                System.out.println("Task Name:" + task.getName());
                System.out.println("Task Description:" + task.getDescription());
                System.out.println("Task Comments:" + task.getComments());
                System.out.println("Task Deadline:" + task.getDeadline());
                System.out.println("Task Priority:" + task.getPriority());
                System.out.println("Task Status:" + task.getStatus());
                System.out.println("----------------------------------");
            }
        }
    }

    //search task by id
    private void searchById(String id) {
        Task task=toDoService.searchById(id);
        if(toDoService.searchById(id)==null){
            System.out.println("No task found!");
        }else{
            System.out.println("Task ID:" + task.getId());
            System.out.println("Task Name:" + task.getName());
            System.out.println("Task Description:" + task.getDescription());
            System.out.println("Task Comments:" + task.getComments());
            System.out.println("Task Deadline:" + task.getDeadline());
            System.out.println("Task Priority:" + task.getPriority());
            System.out.println("Task Status:" + task.getStatus());
            System.out.println("----------------------------------");
        }
    }

    //search task by status
    private void searchByStatus(String status) {
        List<Task> tasks=toDoService.searchByStatus(status);
        if (tasks.size()==0){
            System.out.println("No tasks found!");
        }else{
            for (Task task:tasks) {
                System.out.println("Task ID:" + task.getId());
                System.out.println("Task Name:" + task.getName());
                System.out.println("Task Description:" + task.getDescription());
                System.out.println("Task Comments:" + task.getComments());
                System.out.println("Task Deadline:" + task.getDeadline());
                System.out.println("Task Priority:" + task.getPriority());
                System.out.println("Task Status:" + task.getStatus());
                System.out.println("----------------------------------");
            }
        }
    }

    //sort task by id
    private void sortById()
    {
        List<Task> tasks=toDoService.sortById();
        for (Task task:tasks) {
            System.out.println("Task ID:" + task.getId());
            System.out.println("Task Name:" + task.getName());
            System.out.println("Task Description:" + task.getDescription());
            System.out.println("Task Comments:" + task.getComments());
            System.out.println("Task Deadline:" + task.getDeadline());
            System.out.println("Task Priority:" + task.getPriority());
            System.out.println("Task Status:" + task.getStatus());
            System.out.println("----------------------------------");
        }
    }

    //sort task by status
    private void sortByStatus()
    {
        List<Task> tasks=toDoService.sortByStatus();
        for (Task task:tasks) {
            System.out.println("Task ID:" + task.getId());
            System.out.println("Task Name:" + task.getName());
            System.out.println("Task Description:" + task.getDescription());
            System.out.println("Task Comments:" + task.getComments());
            System.out.println("Task Deadline:" + task.getDeadline());
            System.out.println("Task Priority:" + task.getPriority());
            System.out.println("Task Status:" + task.getStatus());
            System.out.println("----------------------------------");
        }
    }

    //sort task by deadline
    private void sortByDeadline()
    {
        List<Task> tasks=toDoService.sortByDeadline();
        for (Task task:tasks) {
            System.out.println("Task ID:" + task.getId());
            System.out.println("Task Name:" + task.getName());
            System.out.println("Task Description:" + task.getDescription());
            System.out.println("Task Comments:" + task.getComments());
            System.out.println("Task Deadline:" + task.getDeadline());
            System.out.println("Task Priority:" + task.getPriority());
            System.out.println("Task Status:" + task.getStatus());
            System.out.println("----------------------------------");
        }
    }

    //sort task by priority
    private void sortByPriority()
    {
        List<Task> tasks=toDoService.sortByPriority();
        for (Task task:tasks) {
            System.out.println("Task ID:" + task.getId());
            System.out.println("Task Name:" + task.getName());
            System.out.println("Task Description:" + task.getDescription());
            System.out.println("Task Comments:" + task.getComments());
            System.out.println("Task Deadline:" + task.getDeadline());
            System.out.println("Task Priority:" + task.getPriority());
            System.out.println("Task Status:" + task.getStatus());
            System.out.println("----------------------------------");
        }
    }

    //delete the task from db
    private void deleteTask()
    {
        System.out.print("Enter the task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine().trim());
        System.out.println(toDoService.deleteTask(taskId));
    }

    //get all the tasks from db
    private void viewAllTasks()
    {
        if(toDoService.validate()){
            List<Task> tasks=toDoService.viewAllTasks();
            for (Task task:tasks){
                System.out.println("Task ID:"+task.getId());
                System.out.println("Task Name:"+task.getName());
                System.out.println("Task Description:"+task.getDescription());
                System.out.println("Task Comments:"+task.getComments());
                System.out.println("Task Deadline:"+task.getDeadline());
                System.out.println("Task Priority:"+task.getPriority());
                System.out.println("Task Status:"+task.getStatus());
                System.out.println("----------------------");
            }
        }else {
            System.out.println("Please add a task before");
        }
    }

    //update the task
    private void updateTask()
    {
        System.out.print("Enter the task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter the task name: ");
        String taskName = scanner.nextLine().trim();
        System.out.print("Enter the task description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter the task deadline: ");
        String deadline = scanner.nextLine().trim();
        System.out.print("Enter the task priority: ");
        String priority = scanner.nextLine().trim();
        System.out.print("Enter any comments: ");
        String comments = scanner.nextLine().trim();
        System.out.print("Mark it as Completed: ");
        String status = scanner.nextLine().trim();
        Task task = new Task(taskName, description, deadline, priority, comments,status);
        System.out.println(toDoService.updateTask(taskId,task));
    }

    //add a task
    private void addTask()
    {
        System.out.print("Enter the task name: ");
        String taskName = scanner.nextLine().trim();
        System.out.print("Enter the task description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter the task deadline: ");
        String deadline = scanner.nextLine().trim();
        System.out.print("Enter the task priority: ");
        String priority = scanner.nextLine().trim();
        System.out.print("Enter any comments: ");
        String comments = scanner.nextLine().trim();

        Task task = new Task(taskName, description, deadline, priority, comments);
        toDoService.addTask(task);
        System.out.println("Task added successfully!");
    }
}
