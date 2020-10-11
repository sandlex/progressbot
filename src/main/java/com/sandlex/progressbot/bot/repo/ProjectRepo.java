package com.sandlex.progressbot.bot.repo;

import com.sandlex.progressbot.bot.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {

    Optional<Project> findByName(String name);

}
