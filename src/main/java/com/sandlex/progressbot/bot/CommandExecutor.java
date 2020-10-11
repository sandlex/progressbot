package com.sandlex.progressbot.bot;

import com.sandlex.progressbot.bot.commands.ExecutableCommand;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutor implements ApplicationContextAware {

    @Setter
    private ApplicationContext applicationContext;

    public String execute(Commands command) {
        ExecutableCommand executableCommand = applicationContext.getBean(command.getCommandClass());
        return executableCommand.execute();
    }

}