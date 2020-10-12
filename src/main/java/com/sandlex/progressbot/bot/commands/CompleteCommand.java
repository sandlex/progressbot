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

@Component
@RequiredArgsConstructor
public class CompleteCommand implements ExecutableCommand {

    private final CallbackOptionsBuilder callbackOptionsBuilder;
    private final InteractionStateMachine interactionStateMachine;

    @Override
    public BotResponse execute(Message message) {
        interactionStateMachine.transition(message.getFrom().getId(), new StateWithEntity(InteractionStates.COMPLETION_PROJECT, new Project()));
        return new BotResponse(
                "Alright, which project do you want to complete?",
                callbackOptionsBuilder.projectsWith(message.getFrom().getId(), Status.ACTIVE)
        );
    }

}
