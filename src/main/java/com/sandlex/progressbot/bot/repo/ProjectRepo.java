package com.sandlex.progressbot.bot.repo;

import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.bot.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {

    Optional<Project> findByPersonTelegramIdAndName(Integer personId, String name);

    List<Project> findByPersonTelegramIdAndStatus(Integer personId, Status status);

}
