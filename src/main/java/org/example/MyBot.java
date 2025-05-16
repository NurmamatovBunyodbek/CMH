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
        return "TaskManagementproject_Bot";
    }

    @Override
    public String getBotToken() {
        return "7634031460:AAEgiUsgrpnq4aljAsCz0pC9vDjI3HoEaRY";
    }


}
