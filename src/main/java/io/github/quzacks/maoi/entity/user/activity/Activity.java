package io.github.quzacks.maoi.entity.user.activity;

import org.json.JSONPropertyIgnore;

/**
 * Represents a user's activity.
 */
public class Activity {
    /**
     * Name of the activity.
     */
    private final String name;
    /**
     * ID of the activity type.
     */
    private final int type;
    /**
     * When the activity was registered.
     */
    private final long createdAt;
    /**
     * URL of the stream (if any)
     */
     private String url;

    /**
     * Constructor used to construct non client user activities.
     *
     * @param name Name of the activity.
     * @param type Type of the activity.
     * @param createdAt When the activity was registered.
     *
     * @see ActivityType
     */
    public Activity(String name, ActivityType type, long createdAt) {
        this.name = name;
        this.type = type.getId();
        this.createdAt = createdAt;
    }

    /**
     * Construct a client's activity.
     *
     * @param name Name of the activity.
     * @param type Type of the activity.
     *
     * @see ActivityType
     */
    public Activity(String name, ActivityType type) {
        this.name = name;
        this.type = type.getId();
        this.createdAt = System.currentTimeMillis();
    }

    /**
     * Construct a client's activity.
     *
     * @param name Name of the activity.
     * @param type Type of the activity.
     * @param url URL of the stream.
     *
     * @see ActivityType
     */
    public Activity(String name, ActivityType type, String url) {
        this.name = name;
        this.type = type.getId();
        this.url = url;
        this.createdAt = System.currentTimeMillis();
    }

    /**
     * @return Activity's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Type ID of the activity.
     */
    public int getType() {
        return type;
    }

    /**
     * @return Type of the activity.
     *
     * @see ActivityType
     */
    @JSONPropertyIgnore
    public ActivityType type() {
        return ActivityType.fromId(type);
    }

    /**
     * @return When the activity was created.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * @return URL of the stream. Can be null.
     */
    public String getUrl() {
        return url;
    }
}