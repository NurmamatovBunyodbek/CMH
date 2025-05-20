package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
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

        KeyboardButton button1 = new KeyboardButton("To Do");
        KeyboardButton button2 = new KeyboardButton("In Progress");
        KeyboardButton button3 = new KeyboardButton("✅ Completed");

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

    public SendMessage toDo(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Yangi vazifa berish yoki Barcha vazifalarni ko'rish");

        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton("➕ New Task");
        KeyboardButton button2 = new KeyboardButton("\uD83D\uDDD2 All Tasks");
        KeyboardButton button3 = new KeyboardButton("⬅\uFE0F Back");

        row1.add(button1);
        row1.add(button2);

        rowList.add(row1);

        reply.setKeyboard(rowList);
        reply.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(reply);
        return sendMessage;
    }
}
