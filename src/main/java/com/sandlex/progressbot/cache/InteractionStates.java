package com.sandlex.progressbot.cache;

import com.sandlex.progressbot.bot.commands.interactions.CancellationProjectCommand;
import com.sandlex.progressbot.bot.commands.interactions.CompletionProjectCommand;
import com.sandlex.progressbot.bot.commands.interactions.InteractionCommand;
import com.sandlex.progressbot.bot.commands.interactions.ProjectGoalCommand;
import com.sandlex.progressbot.bot.commands.interactions.ProjectInfoCommand;
import com.sandlex.progressbot.bot.commands.interactions.ProjectNameCommand;
import com.sandlex.progressbot.bot.commands.interactions.SubmissionProjectCommand;
import com.sandlex.progressbot.bot.commands.interactions.SubmissionValueCommand;
import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.bot.model.Submission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InteractionStates {

    PROJECT_NAME(Project.class, ProjectNameCommand.class),
    PROJECT_GOAL(Project.class, ProjectGoalCommand.class),
    SUBMISSION_PROJECT(Submission.class, SubmissionProjectCommand.class),
    SUBMISSION_VALUE(Submission.class, SubmissionValueCommand.class),
    COMPLETION_PROJECT(Project.class, CompletionProjectCommand.class),
    CANCELLATION_PROJECT(Project.class, CancellationProjectCommand.class),
    PROJECT_INFO(Project.class, ProjectInfoCommand.class);

    @Getter
    private final Class<? extends CacheableEntity> entity;

    @Getter
    private final Class<? extends InteractionCommand> commandClass;

}
