package com.gemorelli.telegram.puteabot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class PuteaBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String text = message.getText();

            SendMessage sendMessage = new SendMessage().setChatId(chatId);

            sendMessage.setText("Eh, gato!");
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return System.getenv("PUTEABOT_NAME");
    }

    public String getBotToken() {
        return System.getenv("PUTEABOT_TOKEN");
    }
}
