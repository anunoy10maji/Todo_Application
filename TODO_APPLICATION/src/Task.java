public class Task {

    private static int idCounter = 1;
    private int id;
    private String name;
    private String description;
    private String deadline;
    private String priority;


    private String status="Pending";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    private String comments;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getPriority() {
        return priority;
    }

    public String getComments() {
        return comments;
    }

    public Task(String name, String description,
                String deadline, String priority, String comments)
    {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.comments = comments;
        this.status="Pending";
    }
    public Task(String name, String description,
                String deadline, String priority, String comments,String status)
    {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.comments = comments;
        this.status=status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
