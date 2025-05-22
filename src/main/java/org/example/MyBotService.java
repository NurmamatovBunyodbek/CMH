package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MyBotService {
    public SendMessage start(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Salom, botimziga xush kelibsiz");

        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton("To Do");
        KeyboardButton button2 = new KeyboardButton("In Progress");
        KeyboardButton button3 = new KeyboardButton("Urgent");
        KeyboardButton button4 = new KeyboardButton("Late");
        KeyboardButton button5 = new KeyboardButton("✅ Completed");

        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        row2.add(button4);
        row3.add(button5);


        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        reply.setKeyboard(rowList);
        reply.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(reply);
        return sendMessage;
    }

    public SendMessage menu(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ushbu amallardan birini tanlang");

        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton("To Do");
        KeyboardButton button2 = new KeyboardButton("In Progress");
        KeyboardButton button3 = new KeyboardButton("Urgent");
        KeyboardButton button4 = new KeyboardButton("Late");
        KeyboardButton button5 = new KeyboardButton("✅ Completed");

        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        row2.add(button4);
        row3.add(button5);


        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        reply.setKeyboard(rowList);
        reply.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(reply);
        return sendMessage;
    }

    public SendMessage toDo(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Yangi vazifa berish yoki Barcha vazifalarni ko'rish");

        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton("➕ New Task");
        KeyboardButton button2 = new KeyboardButton("\uD83D\uDDD2 All Tasks");
        KeyboardButton button3 = new KeyboardButton("⬅\uFE0F Back");

        row1.add(button1);
        row1.add(button2);
        row2.add(button3);


        rowList.add(row1);
        rowList.add(row2);

        reply.setKeyboard(rowList);
        reply.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(reply);
        return sendMessage;
    }

    public SendMessage name(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Name: ");
        return sendMessage;
    }

    public SendMessage description(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Description: ");
        return sendMessage;
    }

    public SendMessage users(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("User: ");
        return sendMessage;
    }

    public SendMessage startDate(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("StartDate: ");
        return sendMessage;
    }

    public SendMessage dueDate(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("DueDate: ");
        return sendMessage;
    }

    public SendMessage statusOption(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Statuslaran birini tanlang");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        InlineKeyboardButton button5 = new InlineKeyboardButton();

        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        row2.add(button4);
        row3.add(button5);

        button1.setText("⚪\uFE0F TO DO");
        button2.setText("\uD83D\uDFE2 IN PROGRESS");
        button3.setText("\uD83D\uDFE1 URGENT");
        button4.setText("\uD83D\uDD34 LATE");
        button5.setText("✅ COMPLETED");

        button1.setCallbackData("toDoId");
        button2.setCallbackData("inProgressId");
        button3.setCallbackData("urgentId");
        button4.setCallbackData("lateId");
        button5.setCallbackData("completedId");

        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }

    public SendMessage statusMessage(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Status is set");

        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();

        button.setText("Complete");
        row.add(button);
        rowList.add(row);
        reply.setKeyboard(rowList);
        reply.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(reply);
        return sendMessage;
    }
}
