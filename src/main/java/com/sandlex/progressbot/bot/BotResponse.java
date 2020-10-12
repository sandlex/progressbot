package com.sandlex.progressbot.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class BotResponse {

    private final String message;
    private final List<String> callbackOptions;

    public BotResponse(String message) {
        this.message = message;
        this.callbackOptions = Collections.emptyList();
    }

}
