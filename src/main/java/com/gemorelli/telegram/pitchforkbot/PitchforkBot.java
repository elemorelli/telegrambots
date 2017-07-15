package com.gemorelli.telegram.pitchforkbot;

import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.ChosenInlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class PitchforkBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                handleMessage(update);
            } else if (update.hasInlineQuery()) {
                handleInlineQuery(update);
            } else if (update.hasChosenInlineQuery()) {
                handleChosenInlineQuery(update);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(Update update) throws TelegramApiException {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text = message.getText();
        SendMessage sendMessage = new SendMessage().setChatId(chatId);
        if (text.equals("/start")) {
            String welcomeMessage = "Angry at someone? Want to join an angry mob? You need to lynch someone?" + "\n" +
                    "This is the place you were looking for! Welcome to Automated Pitchfork Shop!" + "\n" +
                    "Just tell us the type of pitchfork you need! We got you covered!";
            sendMessage.setText(welcomeMessage);
            sendMessage(sendMessage);
        } else if (text.equals("/about")) {
            String welcomeMessage = "This parody bot is inspired by /r/pitchforkemporium" + "\n" +
                    "Visit www.reddit.com/r/pitchforkemporium for more options!";
            sendMessage.setText(welcomeMessage);
            sendMessage(sendMessage);
        } else if (text.equals("/markup")) {
            sendMessage.setText("Pick a pitchfork!");
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

            List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

            for (PitchforkEnum pitchfork : PitchforkService.getPitchforks()) {
                KeyboardRow row = new KeyboardRow();
                row.add(pitchfork.getName());
                keyboard.add(row);
            }

            keyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(keyboardMarkup);
            sendMessage(sendMessage);
        } else if (text.equals("/hide")) {
            sendMessage.setText("Keyboard hidden");
            ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
            sendMessage.setReplyMarkup(keyboardMarkup);
            sendMessage(sendMessage);
        } else {
            String pitchforkValue = PitchforkEnum.TRADITIONAL.getValue();
            for (PitchforkEnum pitchfork : PitchforkService.getPitchforks()) {
                if (text.startsWith(pitchfork.getCommand()) || text.equals(pitchfork.getName())) {
                    pitchforkValue = pitchfork.getValue();
                    break;
                }
            }
            sendMessage.setText(pitchforkValue);
            sendMessage(sendMessage);
        }
    }

    private void handleInlineQuery(Update update) throws TelegramApiException {
        InlineQuery inlineQuery = update.getInlineQuery();
        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
        answerInlineQuery.setInlineQueryId(inlineQuery.getId());

        List<InlineQueryResult> results = new ArrayList<InlineQueryResult>();
        for (PitchforkEnum pitchfork : PitchforkService.getPitchforks()) {
            InputTextMessageContent messageContent = new InputTextMessageContent();
            messageContent.disableWebPagePreview();
            messageContent.enableMarkdown(true);
            messageContent.setMessageText(pitchfork.getValue());
            InlineQueryResultArticle pitchforkItem = new InlineQueryResultArticle();
            pitchforkItem.setInputMessageContent(messageContent);
            pitchforkItem.setId(pitchfork.getCommand());
            pitchforkItem.setTitle(pitchfork.getName());
            pitchforkItem.setDescription(pitchfork.getValue());
            results.add(pitchforkItem);
        }

        answerInlineQuery.setResults(results);
        System.out.println(inlineQuery.getQuery());
        answerInlineQuery(answerInlineQuery);
    }

    private void handleChosenInlineQuery(Update update) {
        ChosenInlineQuery chosenInlineQuery = update.getChosenInlineQuery();
        System.out.println(chosenInlineQuery.getQuery());
    }

    public String getBotUsername() {
        System.out.println(System.getenv("pitchforkbot.name"));
        return System.getenv("pitchforkbot.name");
    }

    public String getBotToken() {
        System.out.println(System.getenv("pitchforkbot.token"));
        return System.getenv("pitchforkbot.token");
    }
}
