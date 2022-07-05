package io.github.quzacks.maoi.entity.user;

import org.json.JSONObject;

import java.util.EnumSet;

/**
 * Represents a client/user on Discord.
 */
public class User {
    /**
     * ID of the user.
     */
    private final String id;
    /**
     * User's username. Not unique on all servers.
     */
    private final String username;
    /**
     * User's discriminator.
     */
    private final String discriminator;
    /**
     * User's avatar hash code.
     */
    private final String avatar;
    /**
     * Whether the user is a bot.
     */
    private final boolean bot;
    /**
     * User's email.
     */
    private final String email;
    /**
     * Whether user's email is verified.
     */
    private final boolean verified;
    /**
     * Flags of the user.
     *
     * @see UserFlag
     */
    private final EnumSet<UserFlag> flags = EnumSet.noneOf(UserFlag.class);

    /**
     * Initializes User instance from JSON.
     *
     * @param data JSON object.
     */
    public User(JSONObject data) {
        this.id = data.getString("id");
        this.username = data.getString("username");
        this.discriminator = data.getString("discriminator");
        this.avatar = data.isNull("avatar") ? null : data.getString("avatar");
        this.bot = !data.isNull("bot") && data.getBoolean("bot");
        this.email = data.isNull("email") ? null : data.getString("email");
        if(!data.isNull("verified")) {
            this.verified = data.getBoolean("verified");
        } else this.verified = false;

        if(!data.isNull("public_flags")) {
            final int publicFlags = data.getInt("public_flags");
            for (UserFlag flag : UserFlag.values()) {
                if((flag.getRaw() & publicFlags) == flag.getRaw()) flags.add(flag);
            }
        }
    }

    /**
     * @return ID of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Discriminator.
     */
    public String getDiscriminator() {
        return discriminator;
    }

    /**
     * @return Avatar hash code.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @return Whether user is a bot.
     */
    public boolean isBot() {
        return bot;
    }

    /**
     * @return Email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Whether user's email is verified.
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * @return List of flags the user has.
     */
    public EnumSet<UserFlag> getFlags() {
        return flags;
    }
}