package io.github.quzacks.maoi.entity.intent;

/**
 * Enumerates all available gateway intents for Discord.
 */
public enum GatewayIntents {
    GUILDS(0),
    GUILD_MEMBERS(1),
    GUILD_BANS(2),
    GUILD_EMOJIS_AND_STICKERS(3),
    GUILD_INTEGRATIONS(4),
    GUILD_WEBHOOKS(5),
    GUILD_INVITES(6),
    GUILD_VOICE_STATES(7),
    GUILD_PRESENCES(8),
    GUILD_MESSAGES(9),
    GUILD_MESSAGE_REACTIONS(10),
    GUILD_MESSAGE_TYPING(11),
    DIRECT_MESSAGES(12),
    DIRECT_MESSAGE_REACTIONS(13),
    DIRECT_MESSAGE_TYPING(14),
    MESSAGE_CONTENT(15),
    GUILD_SCHEDULED_EVENTS(16),
    AUTO_MODERATION_CONFIGURATION(20),
    AUTO_MODERATION_EXECUTION(21);

    /**
     * Raw displaced value by offset.
     */
    private final int raw;

    /**
     * Constructor for the intent.
     *
     * @param offset Offset of the intent.
     */
    GatewayIntents(int offset) {
        this.raw = 1 << offset;
    }

    /**
     * @return Raw value of the intent.
     */
    public int getRaw() {
        return raw;
    }
}