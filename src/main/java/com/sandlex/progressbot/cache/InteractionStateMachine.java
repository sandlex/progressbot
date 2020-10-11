package com.sandlex.progressbot.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class InteractionStateMachine {

    private final Cache<Integer, StateWithEntity> cache = Caffeine.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    public Optional<StateWithEntity> stateFor(Integer personId) {
        return Optional.ofNullable(cache.getIfPresent(personId));
    }

    public void resetFor(Integer personId) {
        log.debug(String.format("reset cache for %s", personId));
        cache.invalidate(personId);
    }

    public void transition(Integer personId, StateWithEntity stateWithEntity) {
        log.debug(String.format("transitioning %d to %s", personId, stateWithEntity.getState()));
        cache.put(personId, stateWithEntity);
    }

}
