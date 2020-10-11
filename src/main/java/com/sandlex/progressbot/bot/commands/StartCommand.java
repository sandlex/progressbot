package com.sandlex.progressbot.bot.commands;

import com.sandlex.progressbot.bot.model.Person;
import com.sandlex.progressbot.bot.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

@Component
@RequiredArgsConstructor
public class StartCommand implements ExecutableCommand {

    private final PersonRepo personRepo;

    @Override
    public String execute(Message message) {
        Person person = new Person();
        person.setTelegramId(message.getFrom().getId());
        personRepo.save(person);
        return String.format("Welcome, %s! Create your first project and start tracking progress", message.getFrom().getUserName());
    }

}
