package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class SubmitCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "SubmitCommand executed";
    }
}
