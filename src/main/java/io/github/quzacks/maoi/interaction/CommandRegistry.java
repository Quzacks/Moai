package io.github.quzacks.maoi.interaction;

import io.github.quzacks.maoi.interaction.slash_command.SlashCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores all application commands for the client.
 *
 * @see ApplicationCommand
 * @see io.github.quzacks.maoi.DiscordClient
 */
public class CommandRegistry {
    /**
     * Slash command map.
     *
     * @see SlashCommand
     */
    private final Map<SlashCommand, String> slashCommands = new HashMap<>();

    /**
     * Register slash command.
     *
     * @param command Slash command instance.
     * @param guildId Guild ID for guild commands.
     *
     * @see SlashCommand
     */
    public void register(SlashCommand command, String guildId) {
        slashCommands.put(command, guildId);
    }

    /**
     * Register slash command globally.
     *
     * @param command Slash command instance.
     *
     * @see SlashCommand
     */
    public void register(SlashCommand command) {
        slashCommands.put(command, null);
    }

    /**
     * @return Map of registered slash commands.
     */
    public Map<SlashCommand, String> getSlashCommands() {
        return slashCommands;
    }
}