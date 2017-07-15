package com.gemorelli.telegram.rpgtoolsbot;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
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

public class RPGToolsBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                handleMessage(update);
            } else if (update.hasInlineQuery()) {
                handleInlineQuery(update);
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
            String welcomeMessage = "Start message";
            sendMessage.setText(welcomeMessage);

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

            List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
            for (RPGToolsCommands command : RPGToolsService.getCommands()) {
                KeyboardRow row = new KeyboardRow();
                row.add(command.getDescription());
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
            String defaultValue = RPGToolsCommands.ROLL_DICE.getDefaultValue();

            if (text.startsWith(RPGToolsCommands.ROLL_DICE.getCommand())) {
                sendMessage.setText(RPGToolsService.rollDice(parseCommand(text, RPGToolsCommands.ROLL_DICE)));
            }

            sendMessage.setText(defaultValue);
            sendMessage(sendMessage);
        }
    }

    private String parseCommand(String text, RPGToolsCommands command) {
        return StringUtils.remove(text, command.getCommand());
    }

    private void handleInlineQuery(Update update) throws TelegramApiException {
        InlineQuery inlineQuery = update.getInlineQuery();
        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
        answerInlineQuery.setInlineQueryId(inlineQuery.getId());

        List<InlineQueryResult> results = new ArrayList<InlineQueryResult>();
        for (RPGToolsCommands command : RPGToolsService.getCommands()) {
            InputTextMessageContent messageContent = new InputTextMessageContent();
            messageContent.disableWebPagePreview();
            messageContent.enableMarkdown(true);
            messageContent.setMessageText(command.getDefaultValue());
            InlineQueryResultArticle commandItem = new InlineQueryResultArticle();
            commandItem.setInputMessageContent(messageContent);
            commandItem.setId(command.getCommand());
            commandItem.setTitle(command.getDescription());
            commandItem.setDescription(command.getDefaultValue());
            results.add(commandItem);
        }

        answerInlineQuery.setResults(results);
        System.out.println(inlineQuery.getQuery());
        answerInlineQuery(answerInlineQuery);
    }

    public String getBotUsername() {
        return System.getenv("RPGTOOLSBOT_NAME");
    }

    public String getBotToken() {
        return System.getenv("RPGTOOLSBOT_TOKEN");
    }
}
