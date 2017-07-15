package com.gemorelli.telegram;

import com.gemorelli.telegram.pitchforkbot.PitchforkBot;
import com.gemorelli.telegram.rpgtoolsbot.RPGToolsBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class DeployBots {
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        try {
            api.registerBot(new PitchforkBot());
            api.registerBot(new RPGToolsBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
