package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class CancelCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "CancelCommand executed";
    }
}
