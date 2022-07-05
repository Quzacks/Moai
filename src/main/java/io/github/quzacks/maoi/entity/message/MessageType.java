package io.github.quzacks.maoi.entity.message;

/**
 * Enumerates all message types on Discord.
 */
public enum MessageType {
    ;

    /**
     * Value of the type.
     */
    private final int id;

    /**
     * Sets ID of the type.
     *
     * @param id ID.
     */
    MessageType(int id) {
        this.id = id;
    }

    /**
     * @return ID of the type.
     */
    public int getId() {
        return id;
    }

    /**
     * Get message type from its ID.
     *
     * @param id ID of the type.
     */
    public static MessageType fromId(int id) {
        for(MessageType type : MessageType.values())
            if (type.getId() == id) return type;

        return null;
    }
}
