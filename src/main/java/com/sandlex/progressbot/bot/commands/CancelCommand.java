package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
public class CancelCommand implements ExecutableCommand {
    @Override
    public String execute(Message message) {
        return "CancelCommand executed";
    }
}
