package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.BotResponse;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import com.sandlex.progressbot.cache.InteractionStates;
import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.cache.StateWithEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
@RequiredArgsConstructor
public class NewCommand implements ExecutableCommand {

    private final InteractionStateMachine interactionStateMachine;

    @Override
    public BotResponse execute(Message message) {
        interactionStateMachine.transition(message.getFrom().getId(), new StateWithEntity(InteractionStates.PROJECT_NAME, new Project()));
        return new BotResponse("OK. Send me a short name of your project");
    }

}
