package com.sandlex.progressbot.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StateWithEntity {

    private InteractionStates state;
    private CacheableEntity entity;

}
