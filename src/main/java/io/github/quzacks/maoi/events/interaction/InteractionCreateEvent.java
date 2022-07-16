package io.github.quzacks.maoi.events.interaction;

import io.github.quzacks.maoi.DiscordClient;
import io.github.quzacks.maoi.events.GenericEvent;
import io.github.quzacks.maoi.interaction.Interaction;
import org.json.JSONObject;

/**
 * Called when an interaction is created on Discord.
 */
public class InteractionCreateEvent extends GenericEvent {
    /**
     * Interaction object.
     *
     * @see Interaction
     */
    private final Interaction interaction;

    public InteractionCreateEvent(JSONObject data, DiscordClient client) {
        super(data, client);
        this.interaction = new Interaction(data);
    }

    /**
     * @return Interaction object.
     *
     * @see Interaction
     */
    public Interaction getInteraction() {
        return interaction;
    }
}