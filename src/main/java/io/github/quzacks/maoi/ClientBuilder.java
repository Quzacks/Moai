package io.github.quzacks.maoi;

import io.github.quzacks.maoi.gateway.GatewayIntents;
import io.github.quzacks.maoi.models.ClientPresence;

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
    private GatewayIntents[] intents;
    /**
     * Client presence.
     */
    private ClientPresence presence;

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
    public ClientBuilder setIntents(final GatewayIntents... intents) {
        this.intents = intents;
        return this;
    }

    /**
     * Sets the client's presence data. This is not mandatory.
     *
     * @param presence Instance of {@link ClientPresence}.
     * @return {@link ClientBuilder}
     */
    public ClientBuilder setPresence(final ClientPresence presence) {
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