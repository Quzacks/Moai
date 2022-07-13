package io.github.quzacks.maoi.events.message;

import io.github.quzacks.maoi.DiscordClient;
import io.github.quzacks.maoi.entity.message.Message;
import io.github.quzacks.maoi.events.GenericEvent;
import org.json.JSONObject;

/**
 * Fired when a message is created.
 */
public class MessageCreateEvent extends GenericEvent {
    /**
     * Created message.
     *
     * @see Message
     */
    private final Message message;

    /**
     * Initializes created message.
     */
    public MessageCreateEvent(JSONObject data, DiscordClient client) {
        super(data, client);

        message = new Message(data);
    }

    /**
     * @return Created message.
     */
    public Message getMessage() {
        return message;
    }
}
