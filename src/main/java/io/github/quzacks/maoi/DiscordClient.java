package io.github.quzacks.maoi;

import io.github.quzacks.maoi.entity.intent.GatewayIntents;
import io.github.quzacks.maoi.gateway.DiscordWebSocket;
import io.github.quzacks.maoi.entity.user.UserPresence;

/**
 * Information about the Discord client.
 *
 * @see ClientBuilder
 */
public class DiscordClient {
    /**
     * Client's Discord token.
     */
    private final String token;
    /**
     * All gateway intents for the client.
     */
    private final GatewayIntents[] intents;
    /**
     * Presence data.
     */
    private final UserPresence presence;

    /**
     * Constructor for the client. Use {@link ClientBuilder} to create an instance.
     *
     * @param token Discord bot token.
     * @param intents List of gateway intents.
     */
    DiscordClient(String token, GatewayIntents[] intents, UserPresence presence) {
        this.token = token;
        this.intents = intents;
        this.presence = presence;
    }

    /**
     * Opens a web socket connection to the Discord gateway.
     */
    public void start() {
        new DiscordWebSocket(this).login();
    }

    /**
     * @return Token of the client.
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @return Array of gateway intents.
     * @see GatewayIntents
     */
    public GatewayIntents[] getIntents() {
        return intents;
    }

    /**
     * @return Client's presence data.
     * @see UserPresence
     */
    public UserPresence getPresence() {
        return presence;
    }
}