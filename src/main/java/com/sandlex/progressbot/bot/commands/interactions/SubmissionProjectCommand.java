package com.sandlex.progressbot.bot.commands.interactions;

import com.sandlex.progressbot.bot.model.Submission;
import com.sandlex.progressbot.bot.repo.ProjectRepo;
import com.sandlex.progressbot.cache.CacheableEntity;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import com.sandlex.progressbot.cache.InteractionStates;
import com.sandlex.progressbot.cache.StateWithEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubmissionProjectCommand implements InteractionCommand {

    private final InteractionStateMachine interactionStateMachine;
    private final ProjectRepo projectRepo;

    @Override
    public String execute(CacheableEntity entity, Integer personId, String messageContent) {
        return projectRepo.findByName(messageContent)
                .map(project -> {
                    Submission submission = (Submission) entity;
                    submission.setProject(project);
                    interactionStateMachine.transition(personId, new StateWithEntity(InteractionStates.SUBMISSION_VALUE, submission));
                    return "OK. What's your progress" + (project.getPercentage() ? " in %?" : "?") + " Send me a number";
                })
                .orElseGet(() -> {
                    log.error("Couldn't find project " + messageContent);
                    interactionStateMachine.resetFor(personId);
                    return "Strange, but I don't know this project. Please try /submit again";
                });
    }

}
