package com.sandlex.progressbot.bot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandsTest {

    @Test
    void shouldParseSupportedCommandLowerCase() {
        assertThat(Commands.fromString("new")).contains(Commands.NEW);
    }

    @Test
    void shouldParseSupportedCommandUpperCase() {
        assertThat(Commands.fromString("ACTIVE")).contains(Commands.ACTIVE);
    }

    @Test
    void shouldParseSupportedCommandMixedCase() {
        assertThat(Commands.fromString("COMPLETED")).contains(Commands.COMPLETED);
    }

    @Test
    void shouldIgnoreUnsupportedCommand() {
        assertThat(Commands.fromString("die")).isEmpty();
    }

}