package com.sandlex.progressbot.bot.commands.interactions;

import com.sandlex.progressbot.cache.CacheableEntity;

public interface InteractionCommand {

    String execute(CacheableEntity entity, Integer personId, String messageContent);

}
