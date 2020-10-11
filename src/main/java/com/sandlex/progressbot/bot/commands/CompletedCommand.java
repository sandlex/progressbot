package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class CompletedCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "CompletedCommand executed";
    }
}
