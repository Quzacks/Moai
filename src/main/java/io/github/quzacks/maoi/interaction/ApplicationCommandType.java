package io.github.quzacks.maoi.interaction;

/**
 * Enumerates all types of application commands on Discord.
 *
 * @see ApplicationCommand
 */
public enum ApplicationCommandType {
    /**
     * Slash command.
     */
    CHAT_INPUT(1);

    /**
     * Type value.
     */
    private final int type;

    /**
     * @param type Type value.
     */
    ApplicationCommandType(int type) {
        this.type = type;
    }

    /**
     * @return Type value.
     */
    public int getType() {
        return type;
    }

    /**
     * Get application command type by value.
     *
     * @param value Value.
     * @return Command type.
     */
    public static ApplicationCommandType fromValue(int value) {
        for(ApplicationCommandType type : ApplicationCommandType.values())
            if(type.getType() == value) return type;
        return null;
    }
}