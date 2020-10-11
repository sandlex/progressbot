package com.sandlex.progressbot.bot.repo;

import com.sandlex.progressbot.bot.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {
}
