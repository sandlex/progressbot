package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class CancelledCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "CancelledCommand executed";
    }
}
