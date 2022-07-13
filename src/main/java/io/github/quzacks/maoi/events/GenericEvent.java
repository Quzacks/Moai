package io.github.quzacks.maoi.events;

import io.github.quzacks.maoi.DiscordClient;
import org.json.JSONObject;

/**
 * Parent class of all event classes.
 */
public abstract class GenericEvent {
    /**
     * Event data in the form of a JSON object.
     * Used to de-serialize values from the JSON.
     */
    protected final JSONObject data;
    /**
     * Client instance that implements event.
     */
    protected final DiscordClient client;

    /**
    * Initializes data value.
    *
     * @param data "d" key from the gateway JSON response.
     * @param client Client instance that listens to the event.
     */
    public GenericEvent(final JSONObject data, final DiscordClient client) {
        this.data = data;
        this.client = client;
    }

    /**
     * @return Client listening to event.
     */
    public DiscordClient getClient() {
        return client;
    }
}
