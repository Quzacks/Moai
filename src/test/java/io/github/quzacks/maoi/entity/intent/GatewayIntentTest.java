package io.github.quzacks.maoi.entity.intent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GatewayIntentTest {
    @Test
    @DisplayName("Get correct intent value.")
    void getRaw() {
        int intentRaw =
                GatewayIntent.GUILDS.getRaw() +
                GatewayIntent.GUILD_MEMBERS.getRaw() +
                GatewayIntent.GUILD_BANS.getRaw();

        assertEquals(7, intentRaw, "Sum of raw intents should be 7");
    }
}