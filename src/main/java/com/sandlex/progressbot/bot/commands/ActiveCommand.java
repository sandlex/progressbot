package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.BotResponse;
import com.sandlex.progressbot.bot.CallbackOptionsBuilder;
import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.bot.model.Status;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import com.sandlex.progressbot.cache.InteractionStates;
import com.sandlex.progressbot.cache.StateWithEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ActiveCommand implements ExecutableCommand {

    private final CallbackOptionsBuilder callbackOptionsBuilder;
    private final InteractionStateMachine interactionStateMachine;

    @Override
    public BotResponse execute(Message message) {
        List<String> activeProjects = callbackOptionsBuilder.projectsWith(message.getFrom().getId(), Status.ACTIVE);
        if (activeProjects.isEmpty()) {
            return new BotResponse("Your don't have any active projects. Use /new to create one");
        }

        interactionStateMachine.transition(message.getFrom().getId(), new StateWithEntity(InteractionStates.PROJECT_INFO, new Project()));
        return new BotResponse(
                "Your active projects:",
                activeProjects);
    }

}
