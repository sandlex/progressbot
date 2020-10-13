package com.sandlex.progressbot.bot.commands.interactions;

import com.sandlex.progressbot.bot.model.Submission;
import com.sandlex.progressbot.bot.repo.SubmissionRepo;
import com.sandlex.progressbot.cache.CacheableEntity;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubmissionValueCommand implements InteractionCommand {

    private final InteractionStateMachine interactionStateMachine;
    private final SubmissionRepo submissionRepo;

    @Override
    public String execute(CacheableEntity entity, Integer personId, String messageContent) {
        int value;
        try {
            value = Integer.parseInt(messageContent);
        } catch (NumberFormatException e) {
            return "I expect a number. Please try again";
        }

        Submission submission = (Submission) entity;

        if (value > submission.getProject().getGoal()) {
            return "This value is greater than project size. Please try again";
        }

        Integer max = submissionRepo.findByProjectOrderByValueDesc(submission.getProject())
                .stream()
                .findFirst()
                .map(Submission::getValue)
                .orElse(0);
        if (value <= max) {
            return "This value is not greater than your current max progress. Please try again";
        }

        submission.setValue(value);
        submissionRepo.save(submission);

        interactionStateMachine.resetFor(personId);
        int progress = submission.getProject().getPercentage() ? value : value * 100 / submission.getProject().getGoal();
        return (value < submission.getProject().getGoal())
                ? String.format("OK. Submitted. You are %d%% complete", progress)
                : "Congratulations, you reached your goal! Please /complete your project";
    }

}
