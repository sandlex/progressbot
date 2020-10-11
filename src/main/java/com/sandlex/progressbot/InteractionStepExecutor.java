package com.sandlex.progressbot;

import com.sandlex.progressbot.bot.commands.InteractionCommand;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
@RequiredArgsConstructor
public class InteractionStepExecutor implements ApplicationContextAware {

    @Setter
    private ApplicationContext applicationContext;

    private final InteractionStateMachine interactionStateMachine;

    public String execute(Message message) {
        return interactionStateMachine.stateFor(message.getFrom().getId())
                .map(stateWithEntity -> {
                    InteractionCommand interactionCommand = applicationContext.getBean(stateWithEntity.getState().getCommandClass());
                    return interactionCommand.execute(stateWithEntity.getEntity(), message);
                })
                .orElse("Use commands to interact with me");
    }

}
