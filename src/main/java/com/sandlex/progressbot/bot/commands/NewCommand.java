package com.sandlex.progressbot.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class NewCommand implements ExecutableCommand {
    @Override
    public String execute() {
        return "NewCommand executed";
    }
}
