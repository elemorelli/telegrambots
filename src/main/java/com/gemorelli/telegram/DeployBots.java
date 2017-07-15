package com.gemorelli.telegram;

import com.gemorelli.telegram.pitchforkbot.PitchforkBot;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.File;

public class DeployBots {
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        try {
            Configuration config = new Configurations().properties(new File("config.properties"));

            api.registerBot(new PitchforkBot(config));

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
