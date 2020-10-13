package com.sandlex.progressbot.bot.repo;

import com.sandlex.progressbot.bot.model.Project;
import com.sandlex.progressbot.bot.model.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepo extends CrudRepository<Submission, Long> {

    List<Submission> findByProjectOrderByValueDesc(Project project);

}
