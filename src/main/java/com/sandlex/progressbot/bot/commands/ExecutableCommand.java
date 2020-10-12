package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.BotResponse;
import org.telegram.telegrambots.api.objects.Message;

public interface ExecutableCommand {

    BotResponse execute(Message message);
}
