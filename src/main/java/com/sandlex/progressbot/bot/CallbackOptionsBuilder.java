package com.sandlex.progressbot.bot;

import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.bot.model.Status;
import com.sandlex.progressbot.bot.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CallbackOptionsBuilder {

    private final ProjectRepo projectRepo;

    public List<String> projectsWith(Integer personId, Status projectStatus) {
        return projectRepo.findByPersonTelegramIdAndStatus(personId, projectStatus)
                .stream()
                .map(Project::getName)
                .collect(Collectors.toList());
    }

}
