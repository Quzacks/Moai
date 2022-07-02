package io.github.quzacks.maoi.gateway;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GatewayIntentsTest {
    @Test
    @DisplayName("Get correct intent value.")
    void getRaw() {
        int intentRaw =
            GatewayIntents.GUILDS.getRaw() +
            GatewayIntents.GUILD_MEMBERS.getRaw() +
            GatewayIntents.GUILD_BANS.getRaw();

        assertEquals(7, intentRaw, "Sum of raw intents should be 7");
    }
}