package com.sandlex.progressbot.bot.commands.interactions;

import com.sandlex.progressbot.bot.model.Submission;
import com.sandlex.progressbot.bot.repo.ProjectRepo;
import com.sandlex.progressbot.bot.repo.SubmissionRepo;
import com.sandlex.progressbot.cache.CacheableEntity;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProjectInfoCommand implements InteractionCommand {

    private final InteractionStateMachine interactionStateMachine;
    private final ProjectRepo projectRepo;
    private final SubmissionRepo submissionRepo;

    @Override
    public String execute(CacheableEntity entity, Integer personId, String messageContent) {
        return projectRepo.findByPersonTelegramIdAndName(personId, messageContent)
                .map(project -> {
                    Integer max = submissionRepo.findByProjectOrderByValueDesc(project)
                            .stream()
                            .findFirst()
                            .map(Submission::getValue)
                            .orElse(0);
                    interactionStateMachine.resetFor(personId);
                    return "Project \"" + project.getName() + "\" - " + (max * 100 / project.getGoal()) + "%\n"
                            + "Started " + new SimpleDateFormat("yyyy-MM-dd").format(project.getStarted());
                })
                .orElseGet(() -> {
                    log.error("Couldn't find project " + messageContent);
                    interactionStateMachine.resetFor(personId);
                    return "Strange, but I don't know this project. Please try /submit again";
                });
    }

}
