package io.github.quzacks.maoi.gateway;

import io.github.quzacks.maoi.DiscordClient;
import io.github.quzacks.maoi.entity.intent.GatewayIntent;
import io.github.quzacks.maoi.entity.user.UserPresence;
import io.github.quzacks.maoi.events.EventType;
import io.github.quzacks.maoi.events.GenericEvent;
import io.github.quzacks.maoi.events.client.ClientReadyEvent;
import io.github.quzacks.maoi.events.interaction.InteractionCreateEvent;
import io.github.quzacks.maoi.events.message.MessageCreateEvent;
import io.github.quzacks.maoi.exception.CommandNotRegisteredException;
import io.github.quzacks.maoi.interaction.ApplicationCommandOptionData;
import io.github.quzacks.maoi.interaction.InteractionType;
import io.github.quzacks.maoi.interaction.slash_command.SlashCommand;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
                    // TODO: Remove after project completion.
                    final JSONObject response = new JSONObject(s);
                    System.out.println(response.toString(4));
                    final Payload payload = new Payload(response);
                    handleResponse(payload);
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
     * Handles payload from the Discord gateway.
     *
     * @param payload Payload constructed from gateway response.
     */
     @SuppressWarnings("unchecked")
     private void handleResponse(final Payload payload) {
        switch (payload.op()) {
            case HELLO -> {
                final int interval = payload.data().getInt("heartbeat_interval");
                final JSONObject data = new JSONObject()
                    .put("op", 1)
                    .put("d", "null");

                Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                    () -> socket.send(data.toString()),
                    0,
                    interval, TimeUnit.MILLISECONDS
                );
                authorizeClient();
            }
            case DISPATCH -> {
                final GenericEvent event = switch(EventType.valueOf(payload.name())) {
                    case READY -> new ClientReadyEvent(payload.data(), client);
                    case MESSAGE_CREATE -> new MessageCreateEvent(payload.data(), client);
                    case INTERACTION_CREATE -> new InteractionCreateEvent(payload.data(), client);
                };

                if(event instanceof InteractionCreateEvent interactionEvent) {
                    try {
                        runCommand(interactionEvent);
                    } catch(CommandNotRegisteredException e) {
                        e.printStackTrace();
                    }
                }

                client.getListeners().stream().filter(
                    l -> l.getEventClass() == event.getClass()
                ).forEach(l -> l.run(event));
            }
        }
     }

    /**
     * Tries to run matched commands.
     *
     * @param event Interaction event.
     *
     * @throws CommandNotRegisteredException
     *  If command name does not exist in registered commands list.
     */
     private void runCommand(InteractionCreateEvent event) throws CommandNotRegisteredException {
         final List<Object> options = new ArrayList<>();
         for(ApplicationCommandOptionData option : event.getInteraction().getData().getOptions()) {
             options.add(option.getValue());
         }

         if(event.getInteraction().getType() != InteractionType.APPLICATION_COMMAND) return;

         SlashCommand command = client.getCommandRegistry().getSlashCommands().keySet().stream().filter(
                 c -> c.getName().equals(event.getInteraction().getData().getName())
         ).findFirst().orElse(null);

         if(command == null)
             throw new CommandNotRegisteredException(event.getInteraction().getData().getName());

         command.run(event.getInteraction(), options.toArray());
     }

    /**
     * Authorizes the client instance.
     */
    private void authorizeClient() {
        int intentRaw = 0;

        for (GatewayIntent intent : client.getIntents())
            intentRaw += intent.getRaw();

        final JSONObject presence = client.getPresence() != null ? new JSONObject()
            .put("activities", client.getPresence().getActivities())
            .put("status", client.getPresence().getStatus().asString())
            .put("since", client.getPresence().since())
            .put("afk", client.getPresence().isAfk()) : null;

        final JSONObject auth = new JSONObject()
            .put("op", OpCode.IDENTIFY.getCode())
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

        socket.send(auth.toString());
    }

    /**
     * Updates the clients presence.
     *
     * @param presence New bot presence.
     */
    public void updatePresence(final UserPresence presence) {
        final JSONObject data = presence != null ? new JSONObject()
                .put("activities", presence.getActivities())
                .put("status", presence.getStatus().asString())
                .put("since", presence.since())
                .put("afk", presence.isAfk()) : null;

        final JSONObject presenceUpdate = new JSONObject()
            .put("op", OpCode.PRESENCE_UPDATE.getCode())
            .put("d", data);

        socket.send(presenceUpdate.toString());
    }
}