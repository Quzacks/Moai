package io.github.quzacks.maoi.interaction.slash_command;

import io.github.quzacks.maoi.entity.user.User;
import io.github.quzacks.maoi.interaction.ApplicationCommand;
import io.github.quzacks.maoi.interaction.ApplicationCommandType;

/**
 * Represents a Discord slash command.
 *
 * @see ApplicationCommand
 */
public abstract class SlashCommand extends ApplicationCommand {
    /**
     * Command options.
     *
     * @see SlashCommandOption
     */
    private final SlashCommandOption[] options;

    /**
     * @param name Name of command.
     * @param description Description.
     * @param options List of options.
     */
    public SlashCommand(String name, String description, SlashCommandOption... options) {
        super(name, description);
        this.options = options;
    }

    /**
     * @param name Name of command.
     * @param description Description.
     */
    public SlashCommand(String name, String description) {
        super(name, description);
        this.options = null;
    }

    /**
     * Function called when command is ran.
     *
     * @param user User who ran it.
     */
    public abstract void run(User user);

    @Override
    public ApplicationCommandType getType() {
        return ApplicationCommandType.CHAT_INPUT;
    }

    /**
     * @return Options of the command.
     *
     * @see SlashCommandOption
     */
    public SlashCommandOption[] getOptions() {
        return options;
    }
}