package org.example;

public class Task {

    private Integer id;
    private String name;
    private String description;
    private String users;
    private String startDate;
    private String dueDate;
    private String status;
    private String taskGiver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskGiver() {
        return taskGiver;
    }

    public void setTaskGiver(String taskGiver) {
        this.taskGiver = taskGiver;
    }

    public Task(Integer id, String name, String description, String users, String startDate, String dueDate, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task(String name, String description, String users, String startDate, String dueDate, String status) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task(Integer id, String name, String description, String users, String startDate, String dueDate, String status, String taskGiver) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
        this.taskGiver = taskGiver;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task" +
                "\nID: " + id +
                "\nName: " + name +
                "\nDescription: " + description +
                "\nUser: " + users +
                "\nStart Date: " + startDate +
                "\nDue Date: " + dueDate +
                "\nStatus: " + status +
                "\nTask Giver: " + taskGiver;
    }
}
