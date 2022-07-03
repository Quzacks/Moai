package io.github.quzacks.maoi.entity.user;

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
     * Initializes User instance.
     *
     * @param id ID of the user.
     * @param username Username of the user.
     * @param discriminator Discriminator of the user.
     * @param avatar Avatar hash code of the user.
     * @param bot Whether user is a bot.
     * @param email Email of the user.
     * @param verified Whether user's email is verified.
     */
    public User(
        String id,
        String username,
        String discriminator,
        String avatar,
        boolean bot,
        String email,
        boolean verified
    ) {
        this.id = id;
        this.username = username;
        this.discriminator = discriminator;
        this.avatar = avatar;
        this.bot = bot;
        this.email = email;
        this.verified = verified;
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
}