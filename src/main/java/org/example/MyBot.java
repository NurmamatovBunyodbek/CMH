package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;

public class MyBot extends TelegramLongPollingBot {

    int ida = 12;

    Boolean addTask = false;
    Boolean nameTask = false;
    Boolean descriptionTask = false;
    Boolean usersTask = false;
    Boolean startDateTask = false;
    Boolean dueDateTask = false;
    Boolean statusTask = false;

    String taskName = "";
    String taskDescription = "";
    String taskUser = "";
    String taskStartDate = "";
    String taskDueDate = "";
    String taskStatus = "";

    @Override
    public void onUpdateReceived(Update update) {
        MyBotService myBotService = new MyBotService();

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (text.equals("/start")) {
                try {
                    execute(myBotService.start(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

            if (text.equals("To Do")) {
                try {
                    execute(myBotService.toDo(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

            if(text.equals("⬅\uFE0F Back")){
                try {
                    execute(myBotService.menu(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }


            if (text.equals("➕ New Task")){
                addTask = true;
                nameTask = true;

                taskName = "";
                taskDescription = "";
                taskUser = "";
                taskStartDate = "";
                taskDueDate = "";
                taskStatus = "";

                try {
                    execute(myBotService.name(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }else if(addTask){
                if(nameTask){
                    taskName = text;

                    try {
                        execute(myBotService.description(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    
                    nameTask = false;
                    descriptionTask = true;
                } else if (descriptionTask) {
                    taskDescription = text;

                    try {
                        execute(myBotService.users(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                    descriptionTask = false;
                    usersTask = true;
                } else if (usersTask) {
                    taskUser = text;

                    try {
                        execute(myBotService.startDate(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                    usersTask = false;
                    startDateTask = true;
                } else if (startDateTask) {
                    taskStartDate = text;

                    try {
                        execute(myBotService.dueDate(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                    startDateTask = false;
                    dueDateTask = true;
                } else if (dueDateTask) {
                    taskDueDate = text;

                    try {
                        execute(myBotService.statusOption(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                    dueDateTask = false;
                    statusTask = true;
                }else if (statusTask) {
                    try {
                        String databaseurl = "jdbc:postgresql://localhost:5432/taskmanagement";
                        String username = "postgres";
                        String password = "root";

                        Connection connection = DriverManager.getConnection(databaseurl, username, password);
                        Statement statement = connection.createStatement();
                        ida++;

                        String query = "insert into tasks(id,name,description,users,startdate,duedate,status) values(" + ida + ",'" + taskName + "','" + taskDescription + "','" + taskUser + "','" + taskStartDate + "','" + taskDueDate + "', '" + taskStatus + "')";
                        statement.execute(query);

                        System.out.println("Successfully created task");

                        addTask = false;
                        statusTask = false;

                        SendMessage successMessage = new SendMessage();
                        successMessage.setChatId(chatId);
                        successMessage.setText("Task created successfully!");
                        try {
                            execute(successMessage);
                        }catch (TelegramApiException e){
                            throw new RuntimeException(e);
                        }

                    }catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                }
            }

            if (text.equals("\uD83D\uDDD2 All Tasks")){
                try {
                    String databaseurl = "jdbc:postgresql://localhost:5432/taskmanagement";
                    String username = "postgres";
                    String password = "root";

                    Connection connection = DriverManager.getConnection(databaseurl,username,password);
                    Statement statement = connection.createStatement();
                    String query = "select * from tasks order by id";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()){
                        Integer id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        String users = resultSet.getString(4);
                        String startDate = resultSet.getString(5);
                        String dueDate = resultSet.getString(6);
                        String status = resultSet.getString(7);

                        if(status.equals("TO DO")){
                            status = "⚪\uFE0F TO DO";
                        }else if(status.equals("URGENT")){
                            status = "\uD83D\uDFE1 URGENT";
                        }else if (status.equals("IN PROGRESS")) {
                            status = "\uD83D\uDFE2 IN PROGRESS";
                        }else if(status.equals("COMPLETED")){
                            status = "✅ COMPLETED";
                        }else if(status.equals("LATE")){
                            status = "\uD83D\uDD34 LATE";
                        }

                        Task tasks = new Task(id,name,description,users,startDate,dueDate,status);
                        System.out.println(tasks);

                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText(tasks.toString());
                        sendMessage.setChatId(chatId);

                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        execute(myBotService.backMessage(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(text.equals("Complete")){
                try {
                    execute(myBotService.menu(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

            if (text.equals("In Progress")) {
                try {
                    String databaseurl = "jdbc:postgresql://localhost:5432/taskmanagement";
                    String username = "postgres";
                    String password = "root";

                    Connection connection = DriverManager.getConnection(databaseurl, username, password);
                    Statement statement = connection.createStatement();
                    String query = "select * from tasks order by id";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        String users = resultSet.getString(4);
                        String startDate = resultSet.getString(5);
                        String dueDate = resultSet.getString(6);
                        String status = resultSet.getString(7);

                        if (status.equals("IN PROGRESS")) {
                            status = "\uD83D\uDFE2 IN PROGRESS";

                            Task task = new Task(id,name,description,users,startDate,dueDate,status);
                            System.out.println(task);

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setText(task.toString());
                            sendMessage.setChatId(chatId);

                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try {
                        execute(myBotService.backMessage(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (text.equals("Urgent")) {
                try {
                    String databaseurl = "jdbc:postgresql://localhost:5432/taskmanagement";
                    String username = "postgres";
                    String password = "root";

                    Connection connection = DriverManager.getConnection(databaseurl, username, password);
                    Statement statement = connection.createStatement();
                    String query = "select * from tasks order by id";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        String users = resultSet.getString(4);
                        String startDate = resultSet.getString(5);
                        String dueDate = resultSet.getString(6);
                        String status = resultSet.getString(7);

                        if (status.equals("URGENT")) {
                            status = "\uD83D\uDFE1 URGENT";

                            Task task = new Task(id,name,description,users,startDate,dueDate,status);
                            System.out.println(task);

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setText(task.toString());
                            sendMessage.setChatId(chatId);

                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try {
                        execute(myBotService.backMessage(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }




            if (text.equals("Late")) {
                try {
                    String databaseurl = "jdbc:postgresql://localhost:5432/taskmanagement";
                    String username = "postgres";
                    String password = "root";

                    Connection connection = DriverManager.getConnection(databaseurl, username, password);
                    Statement statement = connection.createStatement();
                    String query = "select * from tasks order by id";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        String users = resultSet.getString(4);
                        String startDate = resultSet.getString(5);
                        String dueDate = resultSet.getString(6);
                        String status = resultSet.getString(7);

                        if (status.equals("LATE")) {
                            status = "\uD83D\uDD34 LATE";

                            Task task = new Task(id,name,description,users,startDate,dueDate,status);
                            System.out.println(task);

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setText(task.toString());
                            sendMessage.setChatId(chatId);

                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try {
                        execute(myBotService.backMessage(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }



            if (text.equals("✅ Completed")) {
                try {
                    String databaseurl = "jdbc:postgresql://localhost:5432/taskmanagement";
                    String username = "postgres";
                    String password = "root";

                    Connection connection = DriverManager.getConnection(databaseurl, username, password);
                    Statement statement = connection.createStatement();
                    String query = "select * from tasks order by id";
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        String users = resultSet.getString(4);
                        String startDate = resultSet.getString(5);
                        String dueDate = resultSet.getString(6);
                        String status = resultSet.getString(7);

                        if (status.equals("COMPLETED")) {
                            status = "✅ COMPLETED";

                            Task task = new Task(id,name,description,users,startDate,dueDate,status);
                            System.out.println(task);

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setText(task.toString());
                            sendMessage.setChatId(chatId);

                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try {
                        execute(myBotService.backMessage(chatId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            switch (data){
                case "toDoId":taskStatus = "TO DO";
                    try {execute(myBotService.statusMessage(chatId));} catch (TelegramApiException e) {throw new RuntimeException(e);}break;
                case "inProgressId":taskStatus = "IN PROGRESS";
                    try {execute(myBotService.statusMessage(chatId));} catch (TelegramApiException e) {throw new RuntimeException(e);}break;
                case "urgentId":taskStatus = "URGENT";
                    try {execute(myBotService.statusMessage(chatId));} catch (TelegramApiException e) {throw new RuntimeException(e);}break;
                case "lateId":taskStatus = "LATE";
                    try {execute(myBotService.statusMessage(chatId));} catch (TelegramApiException e) {throw new RuntimeException(e);}break;
                case "completedId":taskStatus = "COMPLETED";
                    try {execute(myBotService.statusMessage(chatId));} catch (TelegramApiException e) {throw new RuntimeException(e);}break;
                default: break;
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "Managementtask_bot";
    }

    @Override
    public String getBotToken() {
        return "7787804258:AAF3A2N7O8UTfbxTMW5ByI4Y-MskAleqeJU";
    }


}
