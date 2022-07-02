package io.github.quzacks.maoi.gateway;

import io.github.quzacks.maoi.DiscordClient;
import io.github.quzacks.maoi.entity.intent.GatewayIntents;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Chat server web socket client.
 */
public class DiscordWebSocket {
    /**
     * Discord client instance.
     */
    private final DiscordClient client;
    /**
     * Actual web socket client instance.
     */
    private WebSocketClient socket;

    /**
     * Constructor for the class.
     *
     * @param client Instance of a Discord client.
     */
    public DiscordWebSocket(DiscordClient client) {
        this.client = client;

        try {
            socket = new WebSocketClient(
                    new URI("wss://gateway.discord.gg/?v=10&encoding=json")
            ) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) { }

                @Override
                public void onMessage(String s) {
                    final JSONObject json = new JSONObject(s);
                    handleResponse(json);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("Connection has been closed with reason: " + s);
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            };
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a web socket connection to the Discord gateway.
     * This is required for real time events.
     */
    public void login() {
        socket.connect();
    }

    /**
     * Handles JSON responses from the Discord gateway.
     *
     * @param response JSON response from the gateway.
     */
     private void handleResponse(final JSONObject response) {
        // TODO: Debugging purposes. Remove after completion of the project.
        System.out.println(response.toString(4));

        final int op = response.getInt("op");

        switch (op) {
            // Hello OP code.
            case 10 -> {
                final int interval = response.getJSONObject("d").getInt("heartbeat_interval");
                final JSONObject data = new JSONObject()
                    .put("op", 1)
                    .put("d", "null");

                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        socket.send(data.toString());
                    }
                }, 0, interval);

                authorizeClient();
                break;
            }
        }
     }

    /**
     * Authorizes the client instance.
     */
    private void authorizeClient() {
        int intentRaw = 0;

        for (GatewayIntents intent : client.getIntents())
            intentRaw += intent.getRaw();

        final JSONObject presence = client.getPresence() != null ? new JSONObject()
            .put("activities", client.getPresence().getActivities())
            .put("status", client.getPresence().getStatus().asString())
            .put("since", client.getPresence().since())
            .put("afk", client.getPresence().isAfk()) : null;

        // TODO: Optional presence data.
        final JSONObject auth = new JSONObject()
            .put("op", 2)
            .put("d", new JSONObject()
                .put("token", client.getToken())
                .put("properties", new JSONObject()
                    .put("os", "windows")
                    .put("browser", "moai")
                    .put("device", "moai")
                )
                .put("presence", presence)
                .put("intents", intentRaw)
            );

        System.out.println(auth.toString(2));
        socket.send(auth.toString());
    }
}