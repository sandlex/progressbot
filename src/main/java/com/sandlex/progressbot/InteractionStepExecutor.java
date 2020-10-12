package com.sandlex.progressbot;

import com.sandlex.progressbot.bot.commands.interactions.InteractionCommand;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InteractionStepExecutor implements ApplicationContextAware {

    @Setter
    private ApplicationContext applicationContext;

    private final InteractionStateMachine interactionStateMachine;

    public String execute(Integer personId, String messageContent) {
        return interactionStateMachine.stateFor(personId)
                .map(stateWithEntity -> {
                    InteractionCommand interactionCommand = applicationContext.getBean(stateWithEntity.getState().getCommandClass());
                    return interactionCommand.execute(stateWithEntity.getEntity(), personId, messageContent.trim());
                })
                .orElse("Use commands to interact with me");
    }

}
