package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.BotResponse;
import com.sandlex.progressbot.bot.CallbackOptionsBuilder;
import com.sandlex.progressbot.bot.model.Status;
import com.sandlex.progressbot.bot.model.Submission;
import com.sandlex.progressbot.bot.repo.ProjectRepo;
import com.sandlex.progressbot.bot.repo.SubmissionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProgressCommand implements ExecutableCommand {

    private final CallbackOptionsBuilder callbackOptionsBuilder;
    private final ProjectRepo projectRepo;
    private final SubmissionRepo submissionRepo;

    @Override
    public BotResponse execute(Message message) {
        Integer personId = message.getFrom().getId();
        List<String> activeProjects = callbackOptionsBuilder.projectsWith(personId, Status.ACTIVE);
        if (activeProjects.isEmpty()) {
            return new BotResponse("Your don't have any active projects. Use /new to create one");
        }

        String result = activeProjects
                .stream()
                .map(projectName -> projectRepo.findByPersonTelegramIdAndName(personId, projectName)
                        .map(project -> {
                            Integer max = submissionRepo.findByProjectOrderByValueDesc(project)
                                    .stream()
                                    .findFirst()
                                    .map(Submission::getValue)
                                    .orElse(0);
                            return project.getName() + " - " + (max * 100 / project.getGoal()) + "%";
                        })
                        .orElse(""))
                .collect(Collectors.joining("\n"));
        return new BotResponse("Your progress:\n" + result);
    }

}
