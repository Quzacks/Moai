package io.github.quzacks.maoi.entity.user;

/**
 * Enumerates all user status there can be in Discord.
 */
public enum UserStatus {
    ONLINE,
    DND,
    IDLE,
    INVISIBLE,
    OFFLINE;

    /**
     * @return The string representation of the status.
     */
    public String asString() {
        return this.name().toLowerCase();
    }
}