package io.github.quzacks.maoi.entity.guild;

/**
 * Partial guild information on Discord.
 */
public class PartialGuild {
    /**
     * ID of the guild
     */
    private final String id;

    /**
     * Sets the guild ID.
     *
     * @param id ID of the guild.
     */
    public PartialGuild(String id) {
        this.id = id;
    }

    /**
     * @return The guild ID.
     */
    public String getId() {
        return id;
    }
}