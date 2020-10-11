package com.sandlex.progressbot.bot.commands;

import org.telegram.telegrambots.api.objects.Message;

public interface ExecutableCommand {

    String execute(Message message);
}
