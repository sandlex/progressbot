package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.CallbackOptionsBuilder;
import com.sandlex.progressbot.bot.BotResponse;
import com.sandlex.progressbot.bot.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
@RequiredArgsConstructor
public class SubmitCommand implements ExecutableCommand {

    private final CallbackOptionsBuilder callbackOptionsBuilder;

    @Override
    public BotResponse execute(Message message) {
        return new BotResponse(
                "In which project you made a progress?",
                callbackOptionsBuilder.projectsWith(message.getFrom().getId(), Status.ACTIVE)
        );
    }

}
