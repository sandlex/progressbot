package com.sandlex.progressbot;

import com.sandlex.progressbot.bot.Commands;
import com.sandlex.progressbot.bot.commands.ExecutableCommand;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
public class CommandExecutor implements ApplicationContextAware {

    @Setter
    private ApplicationContext applicationContext;

    public String execute(Commands command, Message message) {
        ExecutableCommand executableCommand = applicationContext.getBean(command.getCommandClass());
        return executableCommand.execute(message);
    }

}
