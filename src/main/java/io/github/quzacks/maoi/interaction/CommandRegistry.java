package io.github.quzacks.maoi.interaction;

import io.github.quzacks.maoi.interaction.slash_command.SlashCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private final Map<SlashCommand, Boolean> slashCommands = new HashMap<>();

    /**
     * Register slash command.
     *
     * @param command Slash command instance.
     *
     * @see SlashCommand
     */
    public void register(SlashCommand command, boolean global) {
        slashCommands.put(command, global);
    }

    /**
     * @return Map of registered slash commands.
     */
    public Map<SlashCommand, Boolean> getSlashCommands() {
        return slashCommands;
    }
}