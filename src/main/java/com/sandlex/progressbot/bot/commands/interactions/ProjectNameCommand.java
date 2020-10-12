package com.sandlex.progressbot.bot.commands.interactions;

import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.bot.repo.ProjectRepo;
import com.sandlex.progressbot.cache.CacheableEntity;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import com.sandlex.progressbot.cache.InteractionStates;
import com.sandlex.progressbot.cache.StateWithEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectNameCommand implements InteractionCommand {

    private final InteractionStateMachine interactionStateMachine;
    private final ProjectRepo projectRepo;

    @Override
    public String execute(CacheableEntity entity, Integer personId, String messageContent) {
        if (projectRepo.findByName(messageContent).isPresent()) {
            return "You already have a project with this name. Please choose another one";
        }

        Project project = (Project) entity;
        project.setName(messageContent);
        interactionStateMachine.transition(personId, new StateWithEntity(InteractionStates.PROJECT_GOAL, project));
        return "Now send me a size of your project as a number. Send 100% if you plan to submit your progress in percents";
    }

}
