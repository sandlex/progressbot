package com.sandlex.progressbot.cache;

import com.sandlex.progressbot.bot.commands.interactions.InteractionCommand;
import com.sandlex.progressbot.bot.commands.interactions.ProjectGoalCommand;
import com.sandlex.progressbot.bot.commands.interactions.ProjectNameCommand;
import com.sandlex.progressbot.bot.model.Project;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InteractionStates {

    PROJECT_NAME(Project.class, ProjectNameCommand.class),
    PROJECT_GOAL(Project.class, ProjectGoalCommand.class);

    @Getter
    private final Class<? extends CacheableEntity> entity;

    @Getter
    private final Class<? extends InteractionCommand> commandClass;

}