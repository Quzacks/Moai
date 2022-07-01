package io.github.quzacks.maoi.gateway.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Chat server web socket client.
 */
public class DiscordWebSocket {
    private final String token; // Discord bot token.

    /**
     * Constructor for the class.
     *
     * @param token Token of the Discord bot.
     */
    public DiscordWebSocket(String token) {
        this.token = token;
    }

    /**
     * Opens a web socket connection to the Discord gateway.
     * This is required for real time events.
     */
    public void login() {
        try {
            final WebSocketClient socket = new WebSocketClient(new URI("")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) { }

                @Override
                public void onMessage(String s) {
                    System.out.println(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) { }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    // TODO: Debug this.
                }
            };

            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            // TODO: Debug this.
        }
    }
}