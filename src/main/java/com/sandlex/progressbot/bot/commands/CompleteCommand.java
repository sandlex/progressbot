package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class CompleteCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "CompleteCommand executed";
    }
}
