package org.example;

public class Task {

    private int id;
    private String name;
    private String description;
    private TaskStatus status;
    private long assigneeChatId;

    public Task(int id, String name, String description, TaskStatus status, long assigneeChatId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.assigneeChatId = assigneeChatId;
    }

}
