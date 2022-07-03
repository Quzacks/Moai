package io.github.quzacks.maoi.entity.user;

/**
 * Enumerates all Discord user flags.
 */
public enum UserFlag {
    /**
     * Discord staff.
     */
    STAFF(0),
    /**
     * Discord partner.
     */
    PARTNER(1),
    /**
     * Hype squad events member.
     */
    HYPESQUAD(2),
    /**
     * Bug hunter level 1.
     */
    BUG_HUNTER_1(3),
    /**
     * House bravery member.
     */
    HOUSE_BRAVERY(6),
    /**
     * House brilliance member.
     */
    HOUSE_BRILLIANCE(7),
    /**
     * House balance member.
     */
    HOUSE_BALANCE(8),
    /**
     * Premium early supporter.
     */
    EARLY_SUPPORTER(9),
    /**
     * Team pseudo user.
     */
    TEAM_USER(10),
    /**
     * Bug hunter level 2.
     */
    BUG_HUNTER_2(14),
    /**
     * Verified bot.
     */
    VERIFIED_BOT(16),
    /**
     * Early verified developer.
     */
    VERIFIED_DEVELOPER(17),
    /**
     * Certified Discord moderator.
     */
    MODERATOR(18),
    /**
     * Bot uses only HTTP interactions and is shown in the online member list.
     */
    BOT_HTTP(19);

    /**
     * Raw value of the user flag.
     */
    private final int raw;

    /**
     * @param offset Value of the offset.
     */
    UserFlag(int offset) {
        this.raw = 1 << offset;
    }

    /**
     * @return Raw value of the flag.
     */
    public int getRaw() {
        return raw;
    }
}