package com.sandlex.progressbot.bot.repo;

import com.sandlex.progressbot.bot.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {
}
