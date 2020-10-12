package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.BotResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
public class CompletedCommand implements ExecutableCommand {
    @Override
    public BotResponse execute(Message message) {
        return new BotResponse("CompletedCommand executed");
    }
}
