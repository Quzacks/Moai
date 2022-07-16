package io.github.quzacks.maoi.interaction;

import io.github.quzacks.maoi.entity.channel.Channel;
import io.github.quzacks.maoi.entity.guild.Guild;
import io.github.quzacks.maoi.entity.guild.GuildMember;
import io.github.quzacks.maoi.entity.message.Message;
import io.github.quzacks.maoi.entity.user.User;
import org.json.JSONObject;

/**
 * Represents an interaction on Discord.
 */
public class Interaction {
    /**
     * ID of application the interaction is created for.
     */
    private final String applicationId;
    /**
     * Interaction type.
     *
     * @see InteractionType
     */
    private final InteractionType type;
    /**
     * Interaction data payload.
     *
     * @see InteractionData
     */
    private final InteractionData data;
    /**
     * Guild object if interaction was sent in a guild.
     *
     * @see Guild
     */
    private final Guild guild;
    /**
     * Channel the interaction was created in.
     *
     * @see Channel
     */
    private final Channel channel;
    /**
     * Guild member object if interaction was sent a guild.
     *
     * @see GuildMember
     */
    private final GuildMember member;
    /**
     * User object if interaction was ran in DMs.
     *
     * @see User
     */
    private final User user;
    /**
     * The message the component was attached to. If any.
     *
     * @see Message
     */
    private final Message message;

    /**
     * Construct interaction object from JSON.
     *
     * @param data JSON object.
     */
    public Interaction(JSONObject data) {
        this.applicationId = data.getString("application_id");
        this.type = InteractionType.fromValue(data.getInt("type"));
        this.data = data.isNull("data") ? null : new InteractionData(data.getJSONObject("data"));
        this.guild = null;
        this.channel = null;
        this.member = null;
        this.user = data.isNull("user") ? null : new User(data.getJSONObject("user"));
        this.message = data.isNull("message") ? null : new Message(data.getJSONObject("message"));
    }

    /**
     * @return Application ID.
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @return Interaction type.
     *
     * @see InteractionType
     */
    public InteractionType getType() {
        return type;
    }

    /**
     * @return Interaction data object.
     *
     * @see InteractionData
     */
    public InteractionData getData() {
        return data;
    }

    /**
     * @return Guild interaction was created in.
     *
     * @see Guild
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * @return Channel interaction was created in.
     *
     * @see Channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @return Member in interaction.
     *
     * @see GuildMember
     */
    public GuildMember getMember() {
        return member;
    }

    /**
     * @return User in interaction.
     *
     * @see User
     */
    public User getUser() {
        return user;
    }

    /**
     * @return Message attached to component in interaction.
     *
     * @see Message
     */
    public Message getMessage() {
        return message;
    }
}