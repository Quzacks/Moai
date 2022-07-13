package io.github.quzacks.maoi.entity.channel;

/**
 * Enumerates all channel types on Discord.
 */
public enum ChannelType {
    /**
     * Text channel within a server.
     */
    GUILD_TEXT(0),
    /**
     * Direct message between 2 users.
     */
    DM_USER(1),
    /**
     * Voice channel.
     */
    GUILD_VOICE(2),
    /**
     * Direct message in a group.
     */
    DM_GROUP(3),
    /**
     * Category.
     */
    GUILD_CATEGORY(4),
    /**
     * News channel users can follow.
     */
    GUILD_NEWS(5),
    /**
     * Thread under a news channel.
     */
    GUILD_NEWS_THREAD(10),
    /**
     * Thread under guild text channel.
     */
    GUILD_PUBLIC_THREAD(11),
    /**
     * Private thread under guild text channel.
     */
    GUILD_PRIVATE_THREAD(12),
    /**
     * Voice channel to host events.
     */
    GUILD_STAGE_VOICE(13),
    /**
     * Channel in a hub containing listed servers.
     */
    GUILD_DIRECTORY(14),
    /**
     * Channel containing only threads.
     */
    GUILD_FORUM(15);

    /**
     * ID of the channel type.
     */
    private final int id;

    /**
     * Constructor.
     *
     * @param id ID of the type.
     */
    ChannelType(int id) {
        this.id = id;
    }

    /**
     * @return ID of the type.
     */
    public int getId() {
        return id;
    }

    public static ChannelType fromId(int id) {
        for(ChannelType type : ChannelType.values())
            if (type.getId() == id) return type;
        return null;
    }
}
