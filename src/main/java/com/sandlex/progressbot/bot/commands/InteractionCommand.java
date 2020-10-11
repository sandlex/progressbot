package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.cache.CacheableEntity;
import org.telegram.telegrambots.api.objects.Message;

public interface InteractionCommand {

    String execute(CacheableEntity entity, Message message);

}
