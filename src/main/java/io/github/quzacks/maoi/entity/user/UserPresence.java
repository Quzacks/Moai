package io.github.quzacks.maoi.entity.user;

import io.github.quzacks.maoi.entity.activity.Activity;

/**
 * Data class to represent a client's presence.
 */
public class UserPresence {
    /**
     * The start of when the user was idle.
     */
    private final long since;
    /**
     * List of activities.
     *
     * @see Activity
     */
    private final Activity[] activities;
    /**
     * Status of the user.
     */
    private final UserStatus status;
    /**
     * Whether the user is AFK.
     */
    private final boolean afk;

    /**
     * Constructor used to construct non client user presence.
     *
     * @param since When the presence was registered.
     * @param activities List of activities.
     * @param status Status of the user.
     * @param afk Whether the user is AFK.
     *
     * @see Activity
     * @see UserStatus
     */
    public UserPresence(long since, Activity[] activities, UserStatus status, boolean afk) {
        this.since = since;
        this.activities = activities;
        this.status = status;
        this.afk = afk;
    }

    /**
     * Constructs a client's presence.
     *
     * @param status Status of the client.
     * @param activities List of activities.
     *
     * @see Activity
     * @see UserStatus
     */
    public UserPresence(UserStatus status, Activity... activities) {
        this.since = System.currentTimeMillis();
        this.activities = activities;
        this.status = status;
        this.afk = false;
    }

    /**
     * @return When the presence was registered/updated.
     */
    public long since() {
        return since;
    }

    /**
     * @return List of activities.
     *
     * @see Activity
     */
    public Activity[] getActivities() {
        return activities;
    }

    /**
     * @return User's status.
     *
     * @see UserStatus
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * @return Whether user is AFK.
     */
    public boolean isAfk() {
        return afk;
    }
}