package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class StartCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "StartCommand executed";
    }
}
