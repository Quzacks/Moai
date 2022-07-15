package io.github.quzacks.maoi.interaction.slash_command;

/**
 * Enumerates all slash command option types.
 *
 * @see SlashCommandOptionType
 */
public enum SlashCommandOptionType {
    /**
     * Sub command.
     */
    SUB_COMMAND(1),
    /**
     * Sub command group.
     */
    SUB_COMMAND_GROUP(2),
    STRING(3),
    /**
     * Any integer between 2^53 and -2^53.
     */
    INTEGER(4),
    BOOLEAN(5),
    /**
     * Discord user.
     */
    USER(6),
    /**
     * Channel or category.
     */
    CHANNEL(7),
    /**
     * Guild role.
     */
    ROLE(8),
    /**
     * Roles or members.
     */
    MENTIONABLE(9),
    /**
     * Any double between 2^53 and -2^53.
     */
    NUMBER(10),
    /**
     * Attachment object.
     */
    ATTACHMENT(11);

    /**
     * Type value.
     */
    private final int value;

    /**
     * @param value Type value.
     */
    SlashCommandOptionType(int value) {
        this.value = value;
    }

    /**
     * @return Type value.
     */
    public int getValue() {
        return value;
    }
}