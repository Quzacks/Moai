package io.github.quzacks.maoi;

import io.github.quzacks.maoi.entity.intent.GatewayIntent;
import io.github.quzacks.maoi.entity.user.UserPresence;

/**
 * Used to build a {@link DiscordClient} instance.
 *
 * @see DiscordClient
 */
public class ClientBuilder {
    /**
     * Discord bot token.
     */
    private final String token;
    /**
     * All intents.
     */
    private GatewayIntent[] intents;
    /**
     * Client presence.
     */
    private UserPresence presence;

    /**
     * Constructor for builder. Not supposed to be used outside class.
     *
     * @param token Discord bot token.
     */
    private ClientBuilder(final String token) {
        this.token = token;
    }

    /**
     * Creates a builder instance to create a client.
     *
     * @param token Discord bot token.
     * @return {@link ClientBuilder}
     */
    public static ClientBuilder create(final String token) {
        return new ClientBuilder(token);
    }

    /**
     * Sets the client's gateway intents.
     *
     * @param intents List of gateway intents.
     * @return {@link ClientBuilder}
     */
    public ClientBuilder setIntents(final GatewayIntent... intents) {
        this.intents = intents;
        return this;
    }

    /**
     * Sets the client's presence data. This is not mandatory.
     *
     * @param presence Instance of {@link UserPresence}.
     * @return {@link ClientBuilder}
     */
    public ClientBuilder setPresence(final UserPresence presence) {
        this.presence = presence;
        return this;
    }

    /**
     * Builds and returns a client instance with the provided information.
     *
     * @return {@link DiscordClient}
     */
    public DiscordClient build() {
        if(token == null || intents == null) {
            throw new NullPointerException("Token or intents cannot be null.");
        }

        return new DiscordClient(token, intents, presence);
    }
}