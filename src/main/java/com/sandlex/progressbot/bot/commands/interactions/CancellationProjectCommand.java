package com.sandlex.progressbot.bot.commands.interactions;

import com.sandlex.progressbot.bot.model.Status;
import com.sandlex.progressbot.bot.repo.ProjectRepo;
import com.sandlex.progressbot.cache.CacheableEntity;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class CancellationProjectCommand implements InteractionCommand {

    private final InteractionStateMachine interactionStateMachine;
    private final ProjectRepo projectRepo;

    @Override
    public String execute(CacheableEntity entity, Integer personId, String messageContent) {
        return projectRepo.findByPersonTelegramIdAndName(personId, messageContent)
                .map(project -> {
                    project.setStatus(Status.CANCELLED);
                    project.setCompleted(new Date());
                    projectRepo.save(project);
                    interactionStateMachine.resetFor(personId);
                    return "OK. Project marked as cancelled";
                })
                .orElseGet(() -> {
                    log.error("Couldn't find project " + messageContent);
                    interactionStateMachine.resetFor(personId);
                    return "Strange, but I don't know this project. Please try /cancel again";
                });
    }

}
