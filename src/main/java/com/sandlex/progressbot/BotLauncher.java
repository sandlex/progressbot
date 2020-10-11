package com.sandlex.progressbot;

import com.sandlex.progressbot.bot.ProgressBot;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor
public class BotLauncher {

    private final CommandExecutor commandExecutor;
    private final InteractionStateMachine interactionStateMachine;
    private final InteractionStepExecutor interactionStepExecutor;

    @PostConstruct
    public void launchBots() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotApi = new TelegramBotsApi();
        try {
            telegramBotApi.registerBot(new ProgressBot(commandExecutor, interactionStateMachine, interactionStepExecutor));
        } catch (TelegramApiRequestException e) {
            log.error("Error while launching bot", e);
        }
    }

}
