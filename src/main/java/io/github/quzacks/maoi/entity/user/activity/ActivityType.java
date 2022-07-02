package io.github.quzacks.maoi.entity.user.activity;

/**
 * Enumerates all activities on Discord.
 */
public enum ActivityType {
    GAME(0),
    STREAMING(1),
    LISTENING(2),
    WATCHING(3),
    CUSTOM(4),
    COMPETING(5);

    /**
     * ID of the activity.
     */
    private final int id;

    /**
     * Constructor.
     *
     * @param id ID of the activity.
     */
    ActivityType(int id) {
        this.id = id;
    }

    /**
     * @return ID of the activity.
     */
    public int getId() {
        return id;
    }

    /**
     * Get's the activity type based on the provided ID.
     *
     * @param id ID of the type
     * @return {@link ActivityType} with the matching ID.
     */
    public static ActivityType fromId(int id) {
        for(ActivityType type : ActivityType.values())
            if(type.id == id) return type;
        return null;
    }
}
