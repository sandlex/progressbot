package com.sandlex.progressbot;

import com.sandlex.progressbot.bot.CommandExecutor;
import com.sandlex.progressbot.bot.ProgressBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class BotLauncher {
    
    @Autowired
    CommandExecutor commandExecutor;

    @PostConstruct
    public void launchBots() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotApi = new TelegramBotsApi();
        try {
            telegramBotApi.registerBot(new ProgressBot(commandExecutor));
        } catch (TelegramApiRequestException e) {
            log.error("Error while launching bot", e);
        }

    }
}
