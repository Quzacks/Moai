package io.github.quzacks.maoi.events.client;

import io.github.quzacks.maoi.DiscordClient;
import io.github.quzacks.maoi.entity.guild.PartialGuild;
import io.github.quzacks.maoi.entity.user.User;
import io.github.quzacks.maoi.events.GenericEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Dispatched when a client has completed the initial handshake with the gateway.
 */
public class ClientReadyEvent extends GenericEvent {
    /**
     * ID of all guilds the client is in.
     *
     * @see PartialGuild
     */
    private final List<PartialGuild> guilds = new ArrayList<>();

    /**
     * Initializes the User object in the client.
     */
    public ClientReadyEvent(JSONObject data, DiscordClient client) {
        super(data, client);

        JSONArray guilds = data.getJSONArray("guilds");
        for(int i = 0; i < guilds.length(); i++)
            this.guilds.add(new PartialGuild(guilds.getJSONObject(i).getString("id")));

        JSONObject user = data.getJSONObject("user");

        client.setUser(new User(user));
    }

    /**
     * @return List of guilds the client is in.
     *
     * @see PartialGuild
     */
    public List<PartialGuild> getGuilds() {
        return guilds;
    }
}