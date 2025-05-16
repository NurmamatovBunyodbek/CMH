package org.example;

public class User {

    private long chatId;
    private String fullName;
    private boolean isAdmin;

    public User(long chatId, String fullName, boolean isAdmin) {
        this.chatId = chatId;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

}
