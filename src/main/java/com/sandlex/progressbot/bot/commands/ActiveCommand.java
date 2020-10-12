package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.BotResponse;
import com.sandlex.progressbot.bot.CallbackOptionsBuilder;
import com.sandlex.progressbot.bot.model.Status;
import com.sandlex.progressbot.cache.InteractionStateMachine;
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
            new BotResponse("Your don't have any active projects. Use /new to create one");
        }

        return new BotResponse(
                "You have " + activeProjects.size() + " active projects:",
                activeProjects);
    }

}
