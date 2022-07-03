package io.github.quzacks.maoi.entity.user.activity;

/**
 * Enumerates all activities on Discord.
 */
public enum ActivityType {
    /**
     * Playing a game.
     */
    GAME(0),
    /**
     * Live streaming.
     */
    STREAMING(1),
    /**
     * Listening to something.
     */
    LISTENING(2),
    /**
     * User is watching something.
     */
    WATCHING(3),
    /**
     * Custom message.
     */
    CUSTOM(4),
    /**
     * Competing in a competition.
     */
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
