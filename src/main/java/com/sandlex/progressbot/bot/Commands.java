package com.sandlex.progressbot.bot;

import com.sandlex.progressbot.bot.commands.ActiveCommand;
import com.sandlex.progressbot.bot.commands.CancelCommand;
import com.sandlex.progressbot.bot.commands.ExecutableCommand;
import com.sandlex.progressbot.bot.commands.CompleteCommand;
import com.sandlex.progressbot.bot.commands.NewCommand;
import com.sandlex.progressbot.bot.commands.StartCommand;
import com.sandlex.progressbot.bot.commands.SubmitCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public enum Commands {

    START("", StartCommand.class), // Nice to meet you, %username%
    NEW("create new project", NewCommand.class),
    SUBMIT("submit current progress", SubmitCommand.class),
    COMPLETE("complete project", CompleteCommand.class),
    CANCEL("cancel project", CancelCommand.class),
    ACTIVE("show active projects", ActiveCommand.class),
    COMPLETED("show completed projects", CompleteCommand.class),
    CANCELLED("show cancelled projects", CancelCommand.class);

    private final String description;
    @Getter
    private final Class<? extends ExecutableCommand> commandClass;

    static Optional<Commands> fromString(String command) {
        try {
            return Optional.of(Commands.valueOf(command.toUpperCase()));
        } catch (IllegalArgumentException e) {
            // Silently ignoring unsupported commands
        }
        return Optional.empty();
    }

}
