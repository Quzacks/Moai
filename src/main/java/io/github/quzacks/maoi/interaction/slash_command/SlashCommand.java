package io.github.quzacks.maoi.interaction.slash_command;

import io.github.quzacks.maoi.entity.user.User;
import io.github.quzacks.maoi.interaction.ApplicationCommand;
import io.github.quzacks.maoi.interaction.ApplicationCommandOptionData;
import io.github.quzacks.maoi.interaction.ApplicationCommandType;
import io.github.quzacks.maoi.interaction.Interaction;

import java.util.List;

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
     * @param interaction Interaction object
     * @param options Option values.
     */
    public abstract void run(Interaction interaction, Object[] options);

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