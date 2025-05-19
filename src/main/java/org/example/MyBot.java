package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {


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
