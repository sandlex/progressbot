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
public class CancelledCommand implements ExecutableCommand {

    private final CallbackOptionsBuilder callbackOptionsBuilder;
    private final InteractionStateMachine interactionStateMachine;

    @Override
    public BotResponse execute(Message message) {
        List<String> cancelledProjects = callbackOptionsBuilder.projectsWith(message.getFrom().getId(), Status.CANCELLED);
        if (cancelledProjects.isEmpty()) {
            new BotResponse("Your don't have any cancelled projects");
        }

        return new BotResponse(
                "Your cancelled projects:",
                cancelledProjects);
    }

}
