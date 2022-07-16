package io.github.quzacks.maoi.interaction;

/**
 * Enumerates all interaction types on Discord.
 *
 * @see Interaction
 */
public enum InteractionType {
    PING(1),
    /**
     * Called when the interaction is a o command.
     */
    APPLICATION_COMMAND(2),
    MESSAGE_COMPONENT(3),
    APPLICATION_COMMAND_AUTOCOMPLETE(4),
    MODAL_SUBMIT(5);

    /**
     * Type value.
     */
    private final int value;

    /**
     * @param value Value.
     */
    InteractionType(int value) {
        this.value = value;
    }

    /**
     * @return Interaction type value.
     */
    public int getValue() {
        return value;
    }

    /**
    * Get interaction type from type value.
    *
     * @param value Value of type.
     * @return Interaction type.
     */
     public static InteractionType fromValue(int value) {
        for(InteractionType type : InteractionType.values())
            if (type.getValue() == value) return type;
        return null;
     }
}