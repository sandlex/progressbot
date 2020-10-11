package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class ActiveCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "ActiveCommand executed";
    }
}
