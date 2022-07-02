package shell;

import io.github.quzacks.maoi.ClientBuilder;
import io.github.quzacks.maoi.DiscordClient;
import io.github.quzacks.maoi.gateway.GatewayIntents;
import io.github.quzacks.maoi.models.ClientPresence;

public class Main {
    public static void main(String[] args) {
        String token = "OTkyNjg0MjUxMTMwNjMwMjE1.GVnGrN.8coSnbxgwrp-2eM6AmWEkYgyevTOyRULV0xJxY";

        final DiscordClient client = ClientBuilder.create(token)
            .setIntents(
                GatewayIntents.GUILD_BANS,
                GatewayIntents.GUILDS,
                GatewayIntents.GUILD_MEMBERS
            )
            .setPresence(
                new ClientPresence() // <-- Not required.
            )
            .build();

        client.start();
    }
}