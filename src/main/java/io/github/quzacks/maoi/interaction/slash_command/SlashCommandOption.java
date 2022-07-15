package io.github.quzacks.maoi.interaction.slash_command;

import io.github.quzacks.maoi.entity.channel.ChannelType;

/**
 * Represents a slash command option.
 *
 * @see SlashCommand
 */
public class SlashCommandOption {
    /**
     * Type of option.
     */
     private final SlashCommandOptionType type;
    /**
     * Name of the option.
     */
     private final String name;
    /**
     * Description of option.
     */
    private final String description;
    /**
     * Whether option is required.
     */
    private final boolean required;
    /**
     * Choices for the option.
     */
    private final SlashCommandOptionChoice[] choices;
    /**
     * Only applies to sub command groups.
     */
    private final SlashCommandOption[] options;
    /**
     * If the option is of channel type this will restrict the channels that can be selected.
     */
    private final ChannelType[] channelTypes;

    public SlashCommandOption(
        SlashCommandOptionType type,
        String name,
        String description,
        boolean required,
        SlashCommandOptionChoice[] choices,
        SlashCommandOption[] options,
        ChannelType... channelTypes
    ) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = choices;
        this.options = options;
        this.channelTypes = channelTypes;
    }

    public SlashCommandOption(
            SlashCommandOptionType type,
            String name,
            String description,
            boolean required
    ) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = null;
        this.options = null;
        this.channelTypes = null;
    }

    public SlashCommandOption(
            SlashCommandOptionType type,
            String name,
            String description,
            boolean required,
            SlashCommandOptionChoice... choices
    ) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = choices;
        this.options = null;
        this.channelTypes = null;
    }

    public SlashCommandOption(
            SlashCommandOptionType type,
            String name,
            String description,
            boolean required,
            SlashCommandOption... options
    ) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = null;
        this.options = options;
        this.channelTypes = null;
    }

    public SlashCommandOption(
            SlashCommandOptionType type,
            String name,
            String description,
            boolean required,
            ChannelType... channelTypes
    ) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
        this.choices = null;
        this.options = null;
        this.channelTypes = channelTypes;
    }

    /**
     * @return Option type.
     *
     * @see SlashCommandOptionType
     */
    public SlashCommandOptionType getType() {
        return type;
    }

    /**
     * @return Name of option.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Description of option.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Whether option is required.
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @return Choices.
     *
     * @see SlashCommandOptionChoice
     */
    public SlashCommandOptionChoice[] getChoices() {
        return choices;
    }

    /**
     * @return Options.
     */
    public SlashCommandOption[] getOptions() {
        return options;
    }

    /**
     * @return Allowed channel type to mention.
     *
     * @see ChannelType
     */
    public ChannelType[] getChannelTypes() {
        return channelTypes;
    }
}