package io.github.quzacks.maoi;

import io.github.quzacks.maoi.entity.application.Application;
import io.github.quzacks.maoi.entity.intent.GatewayIntent;
import io.github.quzacks.maoi.entity.user.User;
import io.github.quzacks.maoi.gateway.DiscordWebSocket;
import io.github.quzacks.maoi.entity.user.UserPresence;
import io.github.quzacks.maoi.events.EventDispatcher;
import io.github.quzacks.maoi.events.GenericEvent;
import io.github.quzacks.maoi.http.HttpRequest;
import io.github.quzacks.maoi.http.HttpRequestType;
import io.github.quzacks.maoi.interaction.CommandRegistry;
import io.github.quzacks.maoi.interaction.slash_command.SlashCommand;
import io.github.quzacks.maoi.interaction.slash_command.SlashCommandOption;
import io.github.quzacks.maoi.interaction.slash_command.SlashCommandOptionChoice;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Information about the Discord client.
 *
 * @see ClientBuilder
 */
@SuppressWarnings("rawtypes")
public class DiscordClient {
    /**
     * Client's Discord token.
     */
    private final String token;
    /**
     * All gateway intents for the client.
     */
    private final GatewayIntent[] intents;
    /**
     * Presence data.
     */
    private final UserPresence presence;
    /**
     * User data of the client.
     * Initialized once the ready event is fired.
     */
    private User user;
    /**
     * List of event listeners.
     */
    private final List<EventDispatcher> listeners = new ArrayList<>();
    /**
     * Web socket for the gateway.
     *
     * @see DiscordWebSocket
     */
    private final DiscordWebSocket socket;
    /**
     * List of all commands for the client.
     *
     * @see CommandRegistry
     */
    private final CommandRegistry commandRegistry;
    /**
     * Client application.
     *
     * @see Application
     */
    private Application application;

    /**
     * Constructor for the client. Use {@link ClientBuilder} to create an instance.
     *
     * @param token Discord bot token.
     * @param intents List of gateway intents.
     */
    DiscordClient(String token, GatewayIntent[] intents, UserPresence presence) {
        this.token = token;
        this.intents = intents;
        this.presence = presence;

        this.socket = new DiscordWebSocket(this);
        this.commandRegistry = new CommandRegistry();
    }

    /**
     * Opens a web socket connection to the Discord gateway.
     */
    public void start() {
        socket.login();
    }

    /**
     * Posts command data to the Discord HTTP API.
     */
    public void registerCommands() {
        for(Map.Entry<SlashCommand, String> entry : commandRegistry.getSlashCommands().entrySet()) {
            final SlashCommand command = entry.getKey();
            final JSONObject data = new JSONObject()
                .put("name", command.getName())
                .put("type", command.getType().getType())
                .put("description", command.getDescription());

            if(command.getOptions() != null) {
                data.put("options", new JSONArray());
                for(SlashCommandOption option : command.getOptions()) {
                    final JSONObject optionData = new JSONObject()
                        .put("name", option.getName())
                        .put("description", option.getDescription())
                        .put("type", option.getType().getValue())
                        .put("required", option.isRequired());

                    if(option.getChoices() != null) {
                        optionData.put("choices", new JSONArray());
                        for(SlashCommandOptionChoice choice : option.getChoices()) {
                            optionData.accumulate(
                                "choices", new JSONObject()
                                    .put("name", choice.getName())
                                    .put("value", choice.getValue())
                            );
                        }
                    }

                    data.accumulate("options", optionData);
                }
            }

            final String path = entry.getValue() == null ?
                "applications/<app_id>/commands" : "applications/<app_id>/guilds/" + entry.getValue() + "/commands";

            new HttpRequest(path.replace("<app_id>", getApplication().getId()))
                .requestType(HttpRequestType.POST)
                .withJson(data)
                .execute(this);
        }
    }

    /**
     * @return Token of the client.
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @return Array of gateway intents.
     *
     * @see GatewayIntent
     */
    public GatewayIntent[] getIntents() {
        return intents;
    }

    /**
     * @return Client's presence data.
     *
     * @see UserPresence
     */
    public UserPresence getPresence() {
        return presence;
    }

    /**
     * @return User data of the client.
     *
     * @see User
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user data of client.
     *
     * @param user User instance.
     *
     * @see User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Registers an event dispatcher.
     *
     * @param eventClass Event to listen to.
     * @param effect Code to run when event fires.
     *
     * @see GenericEvent
     */
    public <E extends GenericEvent> void listen(final Class<E> eventClass, final Consumer<E> effect) {
        listeners.add(new EventDispatcher<>(eventClass, effect));
    }

    /**
     * @return All registered listeners.
     *
     * @see EventDispatcher
     */
    public List<EventDispatcher> getListeners() {
        return listeners;
    }

    /**
     * Updates the client's presence.
     *
     * @param presence User presence instance.
     *
     * @see UserPresence
     */
    public void updatePresence(UserPresence presence) {
        socket.updatePresence(presence);
    }

    /**
     * @return Command registry.
     *
     * @see CommandRegistry
     */
    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    /**
     * @return Application.
     *
     * @see Application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Set client application.
     *
     * @param application Application instance.
     *
     * @see Application
     */
    public void setApplication(Application application) {
        this.application = application;
    }
}