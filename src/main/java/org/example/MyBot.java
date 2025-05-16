package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (chatId.equals(chatId)) {

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
