package com.sandlex.progressbot.bot.commands.interactions;

import com.sandlex.progressbot.bot.commands.interactions.InteractionCommand;
import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.bot.model.Status;
import com.sandlex.progressbot.bot.repo.PersonRepo;
import com.sandlex.progressbot.bot.repo.ProjectRepo;
import com.sandlex.progressbot.cache.CacheableEntity;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProjectGoalCommand implements InteractionCommand {

    private final InteractionStateMachine interactionStateMachine;
    private final PersonRepo personRepo;
    private final ProjectRepo projectRepo;

    @Override
    public String execute(CacheableEntity entity, Message message) {
        String messageContent = message.getText().trim();

        int value = 100;
        try {
            value = Integer.parseInt(messageContent);
        } catch (NumberFormatException e) {
            if (!"100%".equals(messageContent)) {
                return "I expect a number or 100%. Please try again";
            }
        }

        Integer personId = message.getFrom().getId();
        int finalValue = value;
        return personRepo.findByTelegramId(personId)
                .map(person -> {
                    Project project = (Project) entity;
                    project.setPerson(person);
                    project.setStatus(Status.ACTIVE);
                    project.setPercentage("100%".equals(messageContent));
                    project.setGoal(finalValue);
                    projectRepo.save(project);

                    interactionStateMachine.resetFor(message.getFrom().getId());
                    return "OK. Project saved";
                })
                .orElseGet(() -> {
                    log.error("Couldn't find person with telegram id " + personId);
                    return "Strange, but I don't know you. Execute command /start and create your project again";
                });
    }

}
