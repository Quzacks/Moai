package io.github.quzacks.maoi.entity.user;

/**
 * Enumerates all user status there can be in Discord.
 */
public enum UserStatus {
    /**
     * User is online.
     */
    ONLINE,
    /**
     * Do not disturb.
     */
    DND,
    /**
     * User is idle.
     */
    IDLE,
    /**
     * User is invisible.
     */
    INVISIBLE,
    /**
     * Not online.
     */
    OFFLINE;

    /**
     * @return The string representation of the status.
     */
    public String asString() {
        return this.name().toLowerCase();
    }
}